package com.oplus.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.RemoteAnimationAdapter;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OnUserInteractionCallback;
import com.android.systemui.statusbar.phone.OplusStatusBarNotificationActivityStarterEx;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.UbiquitousClickAfterDismissedParams;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.notification.interruption.OplusWakeupInterruptionUtils;
import com.oplus.systemui.notification.lockscreen.seedling.SeedingActivityStarter;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.helper.AfterSaleModeAlertHelper;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarNotificationActivityStarterExImpl extends OplusStatusBarNotificationActivityStarterEx {
    private static final String DISABLE_TRANSITION_ANIMATOR = "DISABLE_TRANSITION_ANIMATOR";
    private static final String MCS_CLICK_SCENES = "1";
    private static final String MCS_FLOATING_WINDOW_SCENES = "2";
    private static final String TAG = "OplusStatusBarNotificat";
    private final ActivityTransitionAnimator activityLaunchAnimator;
    private final ActivityStarter activityStarter;
    private final Lazy afterSaleModeAlertHelper;
    private final Lazy assistManagerLazy;
    private final CommandQueue commandQueue;
    private final Context context;
    public final int displayId;
    private final KeyguardStateController keyguardStateController;
    private final Handler mainThreadHandler;
    private final Lazy notificationDependOnCommon;
    private final NotificationShadeWindowController notificationShadeWindowController;
    private final OnUserInteractionCallback onUserInteractionCallback;
    private final NotificationPanelViewController panel;
    private final PanelAnimationEx panelAnimationEx;
    private final Lazy seedingActivityStarter;
    private final ShadeAnimationInteractor shadeAnimationInteractor;
    private final ShadeController shadeController;
    private final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public static final Companion Companion = new Companion();
    private static final Set<String> SEAMLESS_LAUNCH_ANIM_BLACKLIST = SetsKt__SetsJVMKt.setOf("com.oplus.remotecontrol");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusStatusBarNotificationActivityStarterExImpl$handleUbiquitousClickAfterKeyguardDismissed$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ boolean $animate;
        public /* synthetic */ UbiquitousClickAfterDismissedParams $params;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusStatusBarNotificationActivityStarterExImpl this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    OplusStatusBarNotificationActivityStarterExImpl.access$handleUbiquitousClickAfterPanelCollapsed(this.this$0, this.$params, this.$animate, true);
                    break;
                default:
                    OplusStatusBarNotificationActivityStarterExImpl.access$handleUbiquitousClickAfterPanelCollapsed(this.this$0, this.$params, this.$animate, false);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusStatusBarNotificationActivityStarterExImpl$scheduleAutoCancelDismissal$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ NotifCollection.FutureDismissal $removeNotification;
        public /* synthetic */ OplusStatusBarNotificationActivityStarterExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.this$0.panel.isCollapsing()) {
                this.this$0.shadeController.addPostCollapseAction(this.$removeNotification);
            } else {
                this.$removeNotification.run();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusStatusBarNotificationActivityStarterExImpl$sendAndReturnResult$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02971 implements Runnable {
        public /* synthetic */ NotificationEntry $entry;
        public /* synthetic */ Intent $fillInIntent;
        public /* synthetic */ PendingIntent $intent;
        public /* synthetic */ Bundle $options;
        public /* synthetic */ OplusStatusBarNotificationActivityStarterExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            try {
                int iSendAndReturnResult = this.$intent.sendAndReturnResult(this.this$0.context, 0, this.$fillInIntent, null, null, null, this.$options);
                if (iSendAndReturnResult == 0) {
                    OplusNotificationLogger.logNotificationZOOM(OplusStatusBarNotificationActivityStarterExImpl.TAG, "sendAndReturnResult success");
                } else {
                    LogUtil.w$default(12, OplusStatusBarNotificationActivityStarterExImpl.TAG, "sendAndReturnResult failed result=" + iSendAndReturnResult, null);
                }
                this.this$0.checkAndSendResultToMcs(this.$entry, iSendAndReturnResult, false);
            } catch (PendingIntent.CanceledException unused) {
                LogUtil.w$default(12, OplusStatusBarNotificationActivityStarterExImpl.TAG, "sendAndReturnResult exception", null);
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$1BYqZUkHb13N6-V6M7vUTATiqBg, reason: not valid java name */
    public static Unit m467$r8$lambda$1BYqZUkHb13N6V6M7vUTATiqBg(boolean z, OplusStatusBarNotificationActivityStarterExImpl oplusStatusBarNotificationActivityStarterExImpl) {
        if (!z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationPendingIntent: collapseShade !");
            }
            oplusStatusBarNotificationActivityStarterExImpl.shadeController.collapseShade();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$o4mYW5OfBHXYiplGO-ngxopMMzU, reason: not valid java name */
    public static Unit m468$r8$lambda$o4mYW5OfBHXYiplGOngxopMMzU(boolean z, OplusStatusBarNotificationActivityStarterExImpl oplusStatusBarNotificationActivityStarterExImpl) {
        if (!z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationIntent: collapseShade !");
            }
            oplusStatusBarNotificationActivityStarterExImpl.panelAnimationEx.getPanelExpandFraction().setStartCustomRowActivity(true);
            oplusStatusBarNotificationActivityStarterExImpl.shadeController.collapseShadeForActivityStart();
        }
        return Unit.INSTANCE;
    }

    public OplusStatusBarNotificationActivityStarterExImpl(Context context, Handler handler, ShadeController shadeController, ShadeAnimationInteractor shadeAnimationInteractor, NotificationShadeWindowController notificationShadeWindowController, KeyguardStateController keyguardStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, NotificationPanelViewController notificationPanelViewController, Lazy lazy, ActivityTransitionAnimator activityTransitionAnimator, ActivityStarter activityStarter, Lazy lazy2, int i, CommandQueue commandQueue, Lazy lazy3, PanelAnimationEx panelAnimationEx, Lazy lazy4, OnUserInteractionCallback onUserInteractionCallback) {
        this.context = context;
        this.mainThreadHandler = handler;
        this.shadeController = shadeController;
        this.shadeAnimationInteractor = shadeAnimationInteractor;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.keyguardStateController = keyguardStateController;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.panel = notificationPanelViewController;
        this.assistManagerLazy = lazy;
        this.activityLaunchAnimator = activityTransitionAnimator;
        this.activityStarter = activityStarter;
        this.notificationDependOnCommon = lazy2;
        this.displayId = i;
        this.commandQueue = commandQueue;
        this.afterSaleModeAlertHelper = lazy3;
        this.panelAnimationEx = panelAnimationEx;
        this.seedingActivityStarter = lazy4;
        this.onUserInteractionCallback = onUserInteractionCallback;
    }

    public static final void access$handleUbiquitousClickAfterPanelCollapsed(OplusStatusBarNotificationActivityStarterExImpl oplusStatusBarNotificationActivityStarterExImpl, UbiquitousClickAfterDismissedParams ubiquitousClickAfterDismissedParams, boolean z, boolean z2) {
        OplusStatusBarNotificationActivityStarterExImpl oplusStatusBarNotificationActivityStarterExImpl2;
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException e) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("handleUbiquitousClickAfterPanelCollapsed: ", Log.getStackTraceString(e), TAG);
        }
        if (ubiquitousClickAfterDismissedParams == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->PanelCollapsed params is null");
                return;
            }
            return;
        }
        if (ubiquitousClickAfterDismissedParams instanceof UbiquitousClickAfterDismissedParams.UbiquitousClickIntentParam) {
            UbiquitousClickAfterDismissedParams.UbiquitousClickIntentParam ubiquitousClickIntentParam = (UbiquitousClickAfterDismissedParams.UbiquitousClickIntentParam) ubiquitousClickAfterDismissedParams;
            Intent intent = ubiquitousClickIntentParam.getIntent();
            Iterator iterator = ubiquitousClickIntentParam.getIterator();
            ubiquitousClickIntentParam.getActivityStarter();
            oplusStatusBarNotificationActivityStarterExImpl.startNotificationIntent(intent, iterator, z2, ubiquitousClickIntentParam.getSeedServiceId(), ubiquitousClickIntentParam.getOverrideTransition() ? false : z);
            oplusStatusBarNotificationActivityStarterExImpl2 = oplusStatusBarNotificationActivityStarterExImpl;
        } else {
            oplusStatusBarNotificationActivityStarterExImpl2 = oplusStatusBarNotificationActivityStarterExImpl;
            if (ubiquitousClickAfterDismissedParams instanceof UbiquitousClickAfterDismissedParams.UbiquitousClickPendingIntentParam) {
                UbiquitousClickAfterDismissedParams.UbiquitousClickPendingIntentParam ubiquitousClickPendingIntentParam = (UbiquitousClickAfterDismissedParams.UbiquitousClickPendingIntentParam) ubiquitousClickAfterDismissedParams;
                PendingIntent pendingIntent = ubiquitousClickPendingIntentParam.getPendingIntent();
                String seedServiceId = ubiquitousClickPendingIntentParam.getSeedServiceId();
                boolean z3 = ubiquitousClickPendingIntentParam.getOverrideTransition() ? false : z;
                if (oplusStatusBarNotificationActivityStarterExImpl2.context != null && pendingIntent != null) {
                    try {
                        SeedingActivityStarter seedingActivityStarter = (SeedingActivityStarter) oplusStatusBarNotificationActivityStarterExImpl2.seedingActivityStarter.get();
                        Context context = oplusStatusBarNotificationActivityStarterExImpl2.context;
                        OplusStatusBarNotificationActivityStarterExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusStatusBarNotificationActivityStarterExImpl$.ExternalSyntheticLambda0(0);
                        externalSyntheticLambda0.f$0 = z2;
                        externalSyntheticLambda0.f$1 = oplusStatusBarNotificationActivityStarterExImpl2;
                        VarHandle.storeStoreFence();
                        seedingActivityStarter.sendPendingIntent(context, pendingIntent, seedServiceId, z3, externalSyntheticLambda0);
                    } catch (PendingIntent.CanceledException e2) {
                        LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "Sending pendingIntent failed: " + e2);
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationPendingIntent: context or pendingIntent is null!");
                }
            } else {
                if (!(ubiquitousClickAfterDismissedParams instanceof UbiquitousClickAfterDismissedParams.UbiquitousClickTaskIdParam)) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                }
                UbiquitousClickAfterDismissedParams.UbiquitousClickTaskIdParam ubiquitousClickTaskIdParam = (UbiquitousClickAfterDismissedParams.UbiquitousClickTaskIdParam) ubiquitousClickAfterDismissedParams;
                int taskId = ubiquitousClickTaskIdParam.getTaskId();
                Intent baseIntent = ubiquitousClickTaskIdParam.getBaseIntent();
                String seedServiceId2 = ubiquitousClickTaskIdParam.getSeedServiceId();
                boolean z4 = ubiquitousClickTaskIdParam.getOverrideTransition() ? false : z;
                if (taskId != -1 && oplusStatusBarNotificationActivityStarterExImpl2.context != null) {
                    try {
                        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
                        activityOptionsMakeBasic.setSplashScreenStyle(0);
                        activityOptionsMakeBasic.setPendingIntentBackgroundActivityStartMode(1);
                        ActivityManagerWrapper.getInstance().getClass();
                        if (!ActivityManagerWrapper.startActivityFromRecents(activityOptionsMakeBasic, taskId)) {
                            if (baseIntent != null) {
                                ((SeedingActivityStarter) oplusStatusBarNotificationActivityStarterExImpl2.seedingActivityStarter.get()).startActivityAsUser(oplusStatusBarNotificationActivityStarterExImpl2.context, baseIntent, new UserHandle(OsBinderCacheUtils.getCurrentUserId()), seedServiceId2, z4, null);
                            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationTaskId: baseIntent is null!");
                            }
                        }
                        if (!z2) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationTaskId: collapseShade !");
                            }
                            oplusStatusBarNotificationActivityStarterExImpl2.shadeController.collapseShade();
                        }
                    } catch (ActivityNotFoundException e3) {
                        LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, " startTaskId failed: " + e3);
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationTaskId: taskId is Invalid or context is null!");
                }
            }
        }
        ((AssistManager) oplusStatusBarNotificationActivityStarterExImpl2.assistManagerLazy.get()).hideAssist();
    }

    public boolean afterKeyguardGone(KeyguardStateController keyguardStateController) {
        return ((KeyguardStateControllerImpl) keyguardStateController).mOccluded && ((KeyguardStateControllerImpl) keyguardStateController).mShowing;
    }

    public void checkAndSendResultToMcs(NotificationEntry notificationEntry, int i, boolean z) throws PendingIntent.CanceledException {
        PendingIntent pendingIntent;
        if (MCSUtil.isMCSPkg(notificationEntry.getSbn())) {
            Notification notification = notificationEntry.getSbn().getNotification();
            Intent intent = new Intent();
            if (z) {
                intent.putExtra("McsActionScenes", "1");
            } else {
                intent.putExtra("McsActionScenes", "2");
            }
            intent.putExtra("McsActionResult", String.valueOf(i));
            Bundle bundle = notification.extras;
            if (bundle == null || (pendingIntent = (PendingIntent) bundle.getParcelable("McsPendingIntent", PendingIntent.class)) == null) {
                return;
            }
            pendingIntent.send(this.context, 1, intent);
        }
    }

    public Bundle getBundleForZoomIfNeed(ExpandableNotificationRow expandableNotificationRow, NotificationPanelViewControllerEx notificationPanelViewControllerEx) {
        Bundle bundleCalculateFlexibleWindowBounds = expandableNotificationRow.mRowEx.calculateFlexibleWindowBounds(expandableNotificationRow, false, true);
        OplusNotificationLogger.logNotificationZOOM(TAG, "startNotificationIntent bundle:" + bundleCalculateFlexibleWindowBounds);
        if (bundleCalculateFlexibleWindowBounds == null || bundleCalculateFlexibleWindowBounds.getInt("androidx.flexible.ResizeMode", 0) != 4) {
            notificationPanelViewControllerEx.resetZoomAnimationParams();
            expandableNotificationRow.mRowEx.setIsZoomAnimating(false);
            return null;
        }
        expandableNotificationRow.mRowEx.setIsZoomAnimating(true);
        expandableNotificationRow.mRowEx.setZoomBundle(bundleCalculateFlexibleWindowBounds);
        return bundleCalculateFlexibleWindowBounds;
    }

    public boolean handleUbiquitousClickAfterKeyguardDismissed(Intent intent, Iterator<? extends Intent> it, boolean z, StatusBarNotificationActivityStarter statusBarNotificationActivityStarter) {
        handleUbiquitousClickAfterKeyguardDismissed.1 r0 = new handleUbiquitousClickAfterKeyguardDismissed.1(1);
        r0.this$0 = this;
        r0.$intent = intent;
        r0.$iterator = it;
        r0.$activityStarter = statusBarNotificationActivityStarter;
        VarHandle.storeStoreFence();
        if (z) {
            ShadeController shadeController = this.shadeController;
            handleUbiquitousClickAfterKeyguardDismissed.1 r1 = new handleUbiquitousClickAfterKeyguardDismissed.1(0);
            r1.this$0 = this;
            r1.$intent = intent;
            r1.$iterator = it;
            r1.$activityStarter = statusBarNotificationActivityStarter;
            VarHandle.storeStoreFence();
            shadeController.addPostCollapseAction(r1);
            this.shadeController.collapseShade(true);
        } else {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
            if (keyguardStateControllerImpl.mShowing && keyguardStateControllerImpl.mOccluded) {
                this.statusBarKeyguardViewManager.mAfterKeyguardGoneRunnables.add(r0);
                this.shadeController.collapseShade();
            } else {
                r0.run();
            }
        }
        return !this.panel.isFullyCollapsed();
    }

    public boolean isOplusActivityAnimator(NotificationEntry notificationEntry, boolean z, boolean z2, boolean z3) {
        return (!z || (ScrimUtil.isBAnimLevel() && !z2 && !z3) || ((AfterSaleModeAlertHelper) this.afterSaleModeAlertHelper.get()).isAfterSaleModeNoticeChannelNotification(notificationEntry)) ? false : true;
    }

    public boolean needInterruptWakeUp(NotificationEntry notificationEntry) {
        return OplusWakeupInterruptionUtils.needInterruptWakeUp(this.context, notificationEntry.getSbn().getPackageName());
    }

    public void notificationStartWithTransitionAnim(ActivityOptions activityOptions, Bundle bundle) {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("notificationStartFrom", "startWithTransitionAnim");
        try {
            Method declaredMethod = ActivityOptions.class.getDeclaredMethod("setExtraBundle", Bundle.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activityOptions, bundleM);
        } catch (ReflectiveOperationException unused) {
            bundle.putAll(bundleM);
        }
    }

    public void onNotiifcationClickEvent(String str, boolean z, String str2) {
        boolean zIsMCSPkg = MCSUtil.isMCSPkg(str, str2);
        Context applicationContext = ((NotificationDependOnCommon) this.notificationDependOnCommon.get()).getApplicationContext();
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("pkg_name", str);
        mapM.put("is_intent_activity", String.valueOf(z));
        mapM.put("is_opush_notification", String.valueOf(zIsMCSPkg));
        OplusTrackProxyUtil.onCommon(applicationContext, "20082", "oplus_notification_click_v2", mapM);
    }

    public void onRemoveNotification(int i, ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow.mPinnedStatus.isPinned()) {
            NotificationStatisticUtil.onNotificationRemoved(((NotificationDependOnCommon) this.notificationDependOnCommon.get()).getApplicationContext(), expandableNotificationRow.getEntry(), i, 1000, expandableNotificationRow.isChildInGroup());
        }
    }

    public boolean onStartFloatWindow(StatusBarNotificationActivityStarter statusBarNotificationActivityStarter, ExpandableNotificationRow expandableNotificationRow, float f) {
        boolean z = false;
        if (expandableNotificationRow == null || expandableNotificationRow.getEntry() == null || expandableNotificationRow.getEntry().getSbn() == null || expandableNotificationRow.getEntry().getSbn().getNotification() == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "onStartFloatWindow:" + expandableNotificationRow);
            return false;
        }
        NotificationRemoteInputManager notificationRemoteInputManager = (NotificationRemoteInputManager) DependencyEx.sDependency.getDependency(NotificationRemoteInputManager.class);
        Notification notification = expandableNotificationRow.getEntry().getSbn().getNotification();
        PendingIntent pendingIntent = notification.contentIntent;
        if (pendingIntent == null) {
            pendingIntent = notification.fullScreenIntent;
        }
        if (pendingIntent == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "onStartFloatWindow pendingIntent null");
            return false;
        }
        boolean zIsBubble = expandableNotificationRow.getEntry().isBubble();
        if (pendingIntent.isActivity() && !zIsBubble) {
            z = true;
        }
        boolean z2 = z;
        Intent intentPutExtra = null;
        CharSequence charSequence = !TextUtils.isEmpty(expandableNotificationRow.getEntry().remoteInputText) ? expandableNotificationRow.getEntry().remoteInputText : null;
        if (!TextUtils.isEmpty(charSequence)) {
            String key = expandableNotificationRow.getEntry().getKey();
            RemoteInputController remoteInputController = notificationRemoteInputManager.mRemoteInputController;
            if (remoteInputController == null || !remoteInputController.mSpinning.containsKey(key)) {
                intentPutExtra = new Intent().putExtra("android.remoteInputDraft", String.valueOf(charSequence));
            }
        }
        expandableNotificationRow.getEntry().getSbn().getNotification().extras.putFloat("key_zoom_current_speed_y", f);
        statusBarNotificationActivityStarter.startNotificationIntent(pendingIntent, intentPutExtra, expandableNotificationRow.getEntry(), expandableNotificationRow, true, z2, true);
        scheduleAutoCancelDismissal(statusBarNotificationActivityStarter, expandableNotificationRow.getEntry(), expandableNotificationRow);
        return expandableNotificationRow.mRowEx.getIsZoomAnimating();
    }

    public boolean scheduleAutoCancelDismissal(StatusBarNotificationActivityStarter statusBarNotificationActivityStarter, NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow) {
        if (notificationEntry == null || notificationEntry.mRanking.canBubble() || (notificationEntry.getSbn().getNotification().flags & 16) == 0) {
            return false;
        }
        NotifCollection.FutureDismissal futureDismissalRegisterFutureDismissal = this.onUserInteractionCallback.registerFutureDismissal(notificationEntry, 1);
        Handler handler = this.mainThreadHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$removeNotification = futureDismissalRegisterFutureDismissal;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
        return true;
    }

    public void sendAndReturnResult(NotificationEntry notificationEntry, PendingIntent pendingIntent, Intent intent, Bundle bundle) {
        RunnableC02971 runnableC02971 = new RunnableC02971();
        runnableC02971.$intent = pendingIntent;
        runnableC02971.this$0 = this;
        runnableC02971.$fillInIntent = intent;
        runnableC02971.$options = bundle;
        runnableC02971.$entry = notificationEntry;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(runnableC02971);
    }

    public void setNotificationTypeStarterLog(Bundle bundle, NotificationEntry notificationEntry) {
        bundle.putString("launch_source_type", MCSUtil.isMCSPkg(notificationEntry.getSbn()) ? "opushnotification" : "localnotification");
    }

    public boolean setZoomParamsForStart(Bundle bundle, RemoteAnimationAdapter remoteAnimationAdapter, ExpandableNotificationRow expandableNotificationRow, NotificationPanelViewControllerEx notificationPanelViewControllerEx) {
        if (remoteAnimationAdapter == null) {
            notificationPanelViewControllerEx.resetZoomAnimationParams();
            expandableNotificationRow.mRowEx.setIsZoomAnimating(false);
            return false;
        }
        bundle.putInt("extra_window_mode", 100);
        bundle.putInt("android:activity.mZoomLaunchFlags", 5);
        bundle.putInt("androidx.activity.LaunchScenario", 1);
        return true;
    }

    public boolean shouldAnimateNotificationSeamlessLaunch(PendingIntent pendingIntent, NotificationEntry notificationEntry) {
        String packageName;
        if (pendingIntent == null || (packageName = pendingIntent.getCreatorPackage()) == null) {
            packageName = notificationEntry.getSbn().getPackageName();
        }
        boolean z = false;
        if (SEAMLESS_LAUNCH_ANIM_BLACKLIST.contains(packageName)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("shouldAnimateNotificationSeamlessLaunch=false, pkg in blacklist: ", packageName, "OplusStatusBarNotificat-->", "SystemUi--Notification");
            }
            return false;
        }
        if (pendingIntent == null) {
            return true;
        }
        try {
            Intent intent = pendingIntent.getIntent();
            if (intent != null && intent.getBooleanExtra(DISABLE_TRANSITION_ANIMATOR, false)) {
                z = true;
            }
            if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->".concat("shouldAnimateNotificationSeamlessLaunch=false, DISABLE_TRANSITION_ANIMATOR set, pkg=" + packageName + " key=" + notificationEntry.getKey()));
            }
            return !z;
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, TAG, "read DISABLE_TRANSITION_ANIMATOR failed", e);
            return true;
        }
    }

    public void startLaunchableViewIntent(Intent intent, int i, LaunchableView launchableView) {
        boolean zShouldAnimateLaunch = this.activityStarter.shouldAnimateLaunch(true);
        startLaunchableViewIntent.onDismissAction.1 r2 = new startLaunchableViewIntent.onDismissAction.1();
        r2.$view = launchableView;
        r2.this$0 = this;
        r2.$animate = zShouldAnimateLaunch;
        r2.$intent = intent;
        r2.$appUid = i;
        VarHandle.storeStoreFence();
        this.activityStarter.dismissKeyguardThenExecute(r2, (Runnable) null, true);
    }

    public final void startNotificationIntent(Intent intent, Iterator it, boolean z, String str, boolean z2) {
        if (intent == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->startNotificationIntent: Intent is null!");
                return;
            }
            return;
        }
        try {
            if (intent.resolveActivity(this.context.getPackageManager()) != null) {
                SeedingActivityStarter seedingActivityStarter = (SeedingActivityStarter) this.seedingActivityStarter.get();
                Context context = this.context;
                UserHandle userHandle = new UserHandle(OsBinderCacheUtils.getCurrentUserId());
                OplusStatusBarNotificationActivityStarterExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusStatusBarNotificationActivityStarterExImpl$.ExternalSyntheticLambda0(1);
                externalSyntheticLambda0.f$0 = z;
                externalSyntheticLambda0.f$1 = this;
                VarHandle.storeStoreFence();
                seedingActivityStarter.startActivityAsUser(context, intent, userHandle, str, z2, externalSyntheticLambda0);
                return;
            }
            if (it == null || !it.hasNext()) {
                return;
            }
            Intent intent2 = (Intent) it.next();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->".concat("startNotificationIntent, intent.action: " + intent.getAction()));
            }
            startNotificationIntent(intent2, it, z, str, z2);
        } catch (Exception e) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String string = e.toString();
                if (string == null) {
                    string = "";
                }
                Log.d("SystemUi--Notification", "OplusStatusBarNotificat-->".concat(string));
            }
        }
    }

    public boolean handleUbiquitousClickAfterKeyguardDismissed(UbiquitousClickAfterDismissedParams ubiquitousClickAfterDismissedParams, boolean z, boolean z2) {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(1);
        anonymousClass2.this$0 = this;
        anonymousClass2.$params = ubiquitousClickAfterDismissedParams;
        anonymousClass2.$animate = z;
        VarHandle.storeStoreFence();
        if (z2) {
            ShadeController shadeController = this.shadeController;
            AnonymousClass2 anonymousClass3 = new AnonymousClass2(0);
            anonymousClass3.this$0 = this;
            anonymousClass3.$params = ubiquitousClickAfterDismissedParams;
            anonymousClass3.$animate = z;
            VarHandle.storeStoreFence();
            shadeController.addPostCollapseAction(anonymousClass3);
            this.shadeController.collapseShade(true);
        } else {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
            if (keyguardStateControllerImpl.mShowing && keyguardStateControllerImpl.mOccluded) {
                this.statusBarKeyguardViewManager.mAfterKeyguardGoneRunnables.add(anonymousClass2);
                this.shadeController.collapseShade();
            } else {
                anonymousClass2.run();
            }
        }
        return !this.panel.isFullyCollapsed();
    }

    public static final void access$handleUbiquitousClickAfterPanelCollapsed(OplusStatusBarNotificationActivityStarterExImpl oplusStatusBarNotificationActivityStarterExImpl, Intent intent, Iterator it, boolean z) {
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException e) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("handleUbiquitousClickAfterPanelCollapsed: ", Log.getStackTraceString(e), TAG);
        }
        oplusStatusBarNotificationActivityStarterExImpl.startNotificationIntent(intent, it, z, null, true);
        ((AssistManager) oplusStatusBarNotificationActivityStarterExImpl.assistManagerLazy.get()).hideAssist();
    }
}
