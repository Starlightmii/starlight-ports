package com.oplus.systemui.notification.data;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.helper.NotificationApplyDelayHelper;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.notification.aonaccess.AonAuthStarter;
import com.oplus.systemui.notification.flashlight.FlashlightSeedlingChecker;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommonImpl;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.notification.flashlight.seeding.FlashlightSeedling;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplus.systemui.statusbar.notification.helper.NotificationApplyDelayHelperImpl;
import com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationListenerDelayImpl {
    public static final Companion Companion = new Companion();
    private static final String[] DELAY_REMOVE_APP_LIST = {"com.tencent.mobileqq", "com.tencent.mm"};
    private static final String TAG = "NotificationListenerDelayImpl";
    private final Lazy aonAuthManager;
    private final Lazy aonAuthStarter;
    private final Context context;
    private final FlashlightSeedlingChecker flashlightSeedlingChecker;
    private final Lazy headsUpHelper;
    public NotificationListenerService.RankingMap newRankingMap;
    private final Lazy notifPipeline;
    public Lazy notificationApplyDelayHelper;
    private final Lazy notificationDependOnCommon;
    private final Lazy notificationDependOnKeyguard;
    private String notificationKey;
    private final Lazy oplusLiveAlertNotificationsInteractor;
    private final Lazy privacyHelper;
    public Lazy replacedNotificationRemoveHelper;
    private final Lazy sensitiveNotificationProtectionController;
    private final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider;
    private Runnable waitingSbnPostTask;
    private final List<String> delayRemoveSbn = new ArrayList();
    private final List<StatusBarNotification> sbnWaitingForAonResult = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$jk7byhypcfTvCialK7vn1JpPG-k, reason: not valid java name */
    public static Unit m53$r8$lambda$jk7byhypcfTvCialK7vn1JpPGk(NotificationListenerDelayImpl notificationListenerDelayImpl, List list) {
        for (StatusBarNotification statusBarNotification : notificationListenerDelayImpl.sbnWaitingForAonResult) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                NotificationListener.NotificationHandler notificationHandler = (NotificationListener.NotificationHandler) it.next();
                try {
                    NotificationListenerService.RankingMap rankingMap = notificationListenerDelayImpl.newRankingMap;
                    if (rankingMap == null) {
                        rankingMap = null;
                    }
                    notificationHandler.onNotificationPosted(statusBarNotification, rankingMap);
                    Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.constructor-impl(ResultKt.createFailure(th));
                }
            }
        }
        notificationListenerDelayImpl.sbnWaitingForAonResult.clear();
        ((AonAuthStarter) notificationListenerDelayImpl.aonAuthStarter.get()).tryCloseAonCamera("sbnWaitingForAonResult cleared");
        notificationListenerDelayImpl.waitingSbnPostTask = null;
        return Unit.INSTANCE;
    }

    public NotificationListenerDelayImpl(Context context, Lazy lazy, FlashlightSeedlingChecker flashlightSeedlingChecker, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9) {
        this.context = context;
        this.aonAuthManager = lazy;
        this.flashlightSeedlingChecker = flashlightSeedlingChecker;
        this.visualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.oplusLiveAlertNotificationsInteractor = lazy2;
        this.notificationDependOnKeyguard = lazy3;
        this.notificationDependOnCommon = lazy4;
        this.headsUpHelper = lazy5;
        this.aonAuthStarter = lazy6;
        this.notifPipeline = lazy7;
        this.sensitiveNotificationProtectionController = lazy8;
        this.privacyHelper = lazy9;
    }

    public final Lazy getNotificationApplyDelayHelper() {
        Lazy lazy = this.notificationApplyDelayHelper;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getReplacedNotificationRemoveHelper() {
        Lazy lazy = this.replacedNotificationRemoveHelper;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final boolean hasAnyWaitingForAonResult() {
        return !this.sbnWaitingForAonResult.isEmpty();
    }

    public final boolean onDelayNotificationPost(StatusBarNotification statusBarNotification, List<? extends NotificationListener.NotificationHandler> list, NotificationListener notificationListener) {
        boolean z;
        long j;
        Runnable notificationApplyTask = ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).getNotificationApplyTask(statusBarNotification.getKey());
        if (notificationApplyTask != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onDelayNotificationPost removeCallbacks for pkg: ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            }
            ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).removeNotificationApplyTask(statusBarNotification.getKey());
            SystemUIThreadUtil.getMainHandler().removeCallbacks(notificationApplyTask);
        }
        boolean z2 = ((NotificationApplyDelayHelperImpl) ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get())).animating;
        boolean zShouldBeReplacedByFluid = ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).shouldBeReplacedByFluid(statusBarNotification);
        if (zShouldBeReplacedByFluid && ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).isNotificationCardShow(statusBarNotification)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("notification should be replaced by fluid directly, pkg:", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            }
            if (!Intrinsics.areEqual(this.notificationKey, statusBarNotification.getKey())) {
                NotificationStatisticUtil.collectFluidReplaceNotification(this.context, 2, statusBarNotification.getPackageName());
            }
            z = true;
        } else {
            z = false;
        }
        this.notificationKey = statusBarNotification.getKey();
        if (!z2 && !zShouldBeReplacedByFluid) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onDelayNotificationPost is animating post delay for pkg: ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
        }
        NotificationListenerDelayImpl$onDelayNotificationPost$runnable$1 notificationListenerDelayImpl$onDelayNotificationPost$runnable$1 = new NotificationListenerDelayImpl$onDelayNotificationPost$runnable$1();
        notificationListenerDelayImpl$onDelayNotificationPost$runnable$1.this$0 = this;
        notificationListenerDelayImpl$onDelayNotificationPost$runnable$1.$sbn = statusBarNotification;
        notificationListenerDelayImpl$onDelayNotificationPost$runnable$1.$notificationListener = notificationListener;
        notificationListenerDelayImpl$onDelayNotificationPost$runnable$1.$notificationHandlers = list;
        notificationListenerDelayImpl$onDelayNotificationPost$runnable$1.$shouldReplacedByFluid = zShouldBeReplacedByFluid;
        notificationListenerDelayImpl$onDelayNotificationPost$runnable$1.$isSeedlingAnimating = z2;
        VarHandle.storeStoreFence();
        ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).putNotificationApplyTask(statusBarNotification.getKey(), notificationListenerDelayImpl$onDelayNotificationPost$runnable$1);
        if (z) {
            j = 0;
        } else {
            j = zShouldBeReplacedByFluid ? 800L : 1000L;
        }
        SystemUIThreadUtil.getMainHandler().postDelayed(notificationListenerDelayImpl$onDelayNotificationPost$runnable$1, j);
        return true;
    }

    public final boolean onDelayNotificationRemove(StatusBarNotification statusBarNotification, List<? extends NotificationListener.NotificationHandler> list, NotificationListener notificationListener) {
        if (((AonAuthManager) this.aonAuthManager.get()).isAonEnable()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onDelayNotificationRemove isAonEnable for pkg: ", statusBarNotification.getKey(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            }
            Runnable runnable = this.waitingSbnPostTask;
            if (runnable != null) {
                ((AonAuthStarter) this.aonAuthStarter.get()).cancelOnGetAonAuthResultTask(runnable);
                runnable.run();
            }
        }
        String packageName = statusBarNotification.getPackageName();
        if (((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.notificationDependOnKeyguard.get())).isKeyguardShowing()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("isDelayRemoveNotification is isKeyguardShowing pkg: ", packageName, "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            }
            for (String str : DELAY_REMOVE_APP_LIST) {
                if (Intrinsics.areEqual(packageName, str)) {
                    Runnable runnable2 = new onDelayNotificationRemove.runnable.1();
                    ((onDelayNotificationRemove.runnable.1) runnable2).this$0 = this;
                    ((onDelayNotificationRemove.runnable.1) runnable2).$sbn = statusBarNotification;
                    ((onDelayNotificationRemove.runnable.1) runnable2).$notificationHandlers = list;
                    ((onDelayNotificationRemove.runnable.1) runnable2).$notificationListener = notificationListener;
                    VarHandle.storeStoreFence();
                    this.delayRemoveSbn.add(statusBarNotification.getKey());
                    SystemUIThreadUtil.getMainHandler().postDelayed(runnable2, 1101, 100L);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean onInterceptNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, List<? extends NotificationListener.NotificationHandler> list) {
        String string;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        if (this.delayRemoveSbn.contains(statusBarNotification.getKey()) && SystemUIThreadUtil.getMainHandler().hasMessages(1101)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onInterceptNotificationPosted removeMessages for pkg: ", statusBarNotification.getKey(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            }
            this.delayRemoveSbn.remove(statusBarNotification.getKey());
            SystemUIThreadUtil.getMainHandler().removeMessages(1101);
        }
        Notification notification = statusBarNotification.getNotification();
        boolean z = false;
        if (Intrinsics.areEqual((notification == null || (bundle3 = notification.extras) == null) ? null : bundle3.getString("seedling_event"), "flashlightEvent")) {
            Notification notification2 = statusBarNotification.getNotification();
            if (notification2 != null && (bundle2 = notification2.extras) != null && bundle2.getBoolean(FlashlightSeedling.KEY_FLASHLIGHT_STATE)) {
                z = true;
            }
            Notification notification3 = statusBarNotification.getNotification();
            if (notification3 == null || (bundle = notification3.extras) == null || (string = bundle.getString(FlashlightSeedling.KEY_FLASHLIGHT_TITLE)) == null) {
                string = "";
            }
            return this.flashlightSeedlingChecker.triggerFlashlightSeedling(z, string);
        }
        this.newRankingMap = rankingMap;
        if (!((PrivacyHelper) this.privacyHelper.get()).isAppLocked(statusBarNotification.getPackageName(), statusBarNotification.getUid())) {
            boolean z2 = ((AonAuthManager) this.aonAuthManager.get()).isAonEnable() && ((NotificationDependOnCommonImpl) ((NotificationDependOnCommon) this.notificationDependOnCommon.get())).isFullyCollapsed() && !((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.notificationDependOnKeyguard.get())).isKeyguardShowing();
            if (z2 && !((HeadsUpHelper) this.headsUpHelper.get()).suppressForGameSpaceOrSA(statusBarNotification.getPackageName(), statusBarNotification)) {
                NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
                if (rankingMap.getRanking(statusBarNotification.getKey(), ranking)) {
                    NotificationEntry notificationEntry = new NotificationEntry(statusBarNotification, ranking, SystemClock.uptimeMillis());
                    if (((SensitiveNotificationProtectionController) this.sensitiveNotificationProtectionController.get()).shouldProtectNotification(notificationEntry)) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[interceptPostNotification]skip delay protected notification ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
                            return false;
                        }
                    } else if (this.visualInterruptionDecisionProvider.makeUnloggedHeadsUpDecision(notificationEntry).getShouldInterrupt()) {
                        if (((OplusLiveAlertNotificationsInteractor) this.oplusLiveAlertNotificationsInteractor.get()).isLiveAlert(notificationEntry, true)) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[interceptPostNotification]skip delay isLiveAlert mOpenCameraForNotification ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
                                return false;
                            }
                        } else if (!((OplusLiveAlertNotificationsInteractor) this.oplusLiveAlertNotificationsInteractor.get()).interceptorHun(notificationEntry)) {
                            NotificationEntry entry = ((NotifPipeline) this.notifPipeline.get()).getEntry(statusBarNotification.getKey());
                            if (entry == null || !entry.interruption || (statusBarNotification.getNotification().flags & 8) == 0) {
                                List<StatusBarNotification> list2 = this.sbnWaitingForAonResult;
                                if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                                    Iterator<T> it = list2.iterator();
                                    while (it.hasNext()) {
                                        if (Intrinsics.areEqual(((StatusBarNotification) it.next()).getKey(), statusBarNotification.getKey())) {
                                            if ((statusBarNotification.getNotification().flags & 8) == 0) {
                                                break;
                                            }
                                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                                Log.d("SystemUi--Notification", "NotificationListenerDelayImpl-->".concat("[interceptPostNotification]skip delay no HeadsUp mOpenCameraForNotification " + statusBarNotification.getPackageName() + " because of alert once flag: " + (statusBarNotification.getNotification().flags & 8)));
                                                return false;
                                            }
                                        }
                                    }
                                }
                            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[interceptPostNotification]should not alert again ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
                                return false;
                            }
                        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[interceptPostNotification]skip delay interceptorHun mOpenCameraForNotification ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
                            return false;
                        }
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[interceptPostNotification]skip delay no HeadsUp mOpenCameraForNotification ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
                        return false;
                    }
                }
                this.sbnWaitingForAonResult.add(statusBarNotification);
                boolean zIsGroupSummary = statusBarNotification.getNotification().isGroupSummary();
                z = statusBarNotification.getNotification().getGroupAlertBehavior() == 1;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[tryMarkNotificationCount]isGroupSummary:", ",canGroupShow:", "NotificationListenerDelayImpl-->", zIsGroupSummary, z));
                }
                if (!zIsGroupSummary || z) {
                    ((AonAuthManager) this.aonAuthManager.get()).markNotificationIncreased();
                }
                Runnable runnable = this.waitingSbnPostTask;
                if (runnable != null) {
                    ((AonAuthStarter) this.aonAuthStarter.get()).cancelOnGetAonAuthResultTask(runnable);
                }
                AonAuthStarter aonAuthStarter = (AonAuthStarter) this.aonAuthStarter.get();
                NotificationListenerDelayImpl$$ExternalSyntheticLambda0 notificationListenerDelayImpl$$ExternalSyntheticLambda0 = new NotificationListenerDelayImpl$$ExternalSyntheticLambda0();
                notificationListenerDelayImpl$$ExternalSyntheticLambda0.f$0 = this;
                notificationListenerDelayImpl$$ExternalSyntheticLambda0.f$1 = list;
                VarHandle.storeStoreFence();
                this.waitingSbnPostTask = aonAuthStarter.openCamera("tryAonAuthBeforePostNotification", notificationListenerDelayImpl$$ExternalSyntheticLambda0);
                return true;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationListenerDelayImpl-->".concat("[interceptPostNotification]skip delay:isAonAvailable=" + z2 + ", " + statusBarNotification.getPackageName()));
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[interceptPostNotification] skip delay: is appLocked", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            return false;
        }
        return false;
    }

    public final void onNotificationRemovedBefore(StatusBarNotification statusBarNotification) {
        String string;
        Bundle bundle;
        Bundle bundle2;
        this.sbnWaitingForAonResult.remove(statusBarNotification);
        if (this.sbnWaitingForAonResult.isEmpty()) {
            ((AonAuthStarter) this.aonAuthStarter.get()).tryCloseAonCamera(statusBarNotification.getKey() + " removed");
        }
        Runnable notificationApplyTask = ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).getNotificationApplyTask(statusBarNotification.getKey());
        if (notificationApplyTask != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onNotificationRemovedBefore removeCallbacks for pkg: ", statusBarNotification.getPackageName(), "NotificationListenerDelayImpl-->", "SystemUi--Notification");
            }
            SystemUIThreadUtil.getMainHandler().removeCallbacks(notificationApplyTask);
            ((NotificationApplyDelayHelper) getNotificationApplyDelayHelper().get()).removeNotificationApplyTask(statusBarNotification.getKey());
        }
        Notification notification = statusBarNotification.getNotification();
        if (Intrinsics.areEqual((notification == null || (bundle2 = notification.extras) == null) ? null : bundle2.getString("seedling_event"), "flashlightEvent")) {
            Notification notification2 = statusBarNotification.getNotification();
            if (notification2 == null || (bundle = notification2.extras) == null || (string = bundle.getString(FlashlightSeedling.KEY_FLASHLIGHT_TITLE)) == null) {
                string = "";
            }
            this.flashlightSeedlingChecker.triggerFlashlightSeedling(false, string);
        }
    }

    public final void setNotificationApplyDelayHelper(Lazy lazy) {
        this.notificationApplyDelayHelper = lazy;
    }

    public final void setReplacedNotificationRemoveHelper(Lazy lazy) {
        this.replacedNotificationRemoveHelper = lazy;
    }
}
