package com.oplus.systemui.statusbar.notification.stack;

import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.BootCompleteCache;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.render.MediaContainerController;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerEx;
import com.android.systemui.util.SeparateModeHelper;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.notification.row.NotificationBackgroundCustomRowHandler;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuControllerImpl;
import com.oplus.systemui.statusbar.seeding.Listener;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationSectionsManagerExImpl extends OplusNotificationSectionsManagerEx implements Listener, OnHeadsUpChangedListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationSectionsManagerExImpl";
    private final BootCompleteCache bootCompleteCache;
    private final OplusCustomRowController customRowController;
    private final HeadsUpManager headsUpManagerPhone;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final Lazy keyguardViewMediatorLazy;
    private final Lazy mFullScreenBannerHelper;
    private final MediaContainerController mediaContainerController;
    private final Lazy notificationStackScrollLayoutController;
    private final OplusCustomPopupMenuController oplusCustomPopupMenuController;
    private OplusCustomRowControllerManager oplusCustomRowControllerManager;
    private SeedlingPlugin plugin;
    private final SeedlingPluginManager seedlingPluginManager;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final IObserverListener superPowerSaveSettingsListener;
    private final KeyguardUpdateMonitorCallback userSwitchingCallback;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerExImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01281 implements FlowCollector {
            public static final C01281 INSTANCE = new C01281();

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusNotificationSectionsManagerExImpl.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("separate mode change: ", zBooleanValue), null, 8);
                SeparateModeHelper.separateMode = zBooleanValue;
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusNotificationSectionsManagerExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow enableSeparateNotificationAndQS = OplusNotificationSectionsManagerExImpl.this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS();
                this.label = 1;
                if (enableSeparateNotificationAndQS.collect(C01281.INSTANCE, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationSectionsManagerExImpl(SeedlingPluginManager seedlingPluginManager, OplusCustomRowController oplusCustomRowController, KeyguardUpdateMonitor keyguardUpdateMonitor, OplusCustomRowControllerManager oplusCustomRowControllerManager, HeadsUpManager headsUpManager, BootCompleteCache bootCompleteCache, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, Lazy lazy, OplusCustomPopupMenuController oplusCustomPopupMenuController, MediaContainerController mediaContainerController, CoroutineScope coroutineScope, Lazy lazy2, Lazy lazy3) {
        this.seedlingPluginManager = seedlingPluginManager;
        this.customRowController = oplusCustomRowController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.oplusCustomRowControllerManager = oplusCustomRowControllerManager;
        this.headsUpManagerPhone = headsUpManager;
        this.bootCompleteCache = bootCompleteCache;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.notificationStackScrollLayoutController = lazy;
        this.oplusCustomPopupMenuController = oplusCustomPopupMenuController;
        this.mediaContainerController = mediaContainerController;
        this.keyguardViewMediatorLazy = lazy2;
        this.mFullScreenBannerHelper = lazy3;
        OplusNotificationSectionsManagerExImpl$userSwitchingCallback$1 oplusNotificationSectionsManagerExImpl$userSwitchingCallback$1 = new OplusNotificationSectionsManagerExImpl$userSwitchingCallback$1();
        oplusNotificationSectionsManagerExImpl$userSwitchingCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userSwitchingCallback = oplusNotificationSectionsManagerExImpl$userSwitchingCallback$1;
        this.superPowerSaveSettingsListener = new OplusNotificationSectionsManagerExImpl$superPowerSaveSettingsListener$1();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "init seedling", null, 8);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    private final OplusCustomRow getCustomRow() {
        return this.customRowController.getOplusCustomRow();
    }

    public void addPluginCallBack(NotificationStackScrollLayout notificationStackScrollLayout) {
        this.oplusCustomRowControllerManager.setParentView(notificationStackScrollLayout);
        this.seedlingPluginManager.addCallback(this);
    }

    public final boolean canShowHeadsUpNotification() {
        return this.oplusCustomRowControllerManager.canShowHeadsUpNotification();
    }

    public final void dismissSeedlingPopupMenu(String str) {
        OplusCustomPopupMenuController.dismissPopupMenu$default(this.oplusCustomPopupMenuController, str, !((KeyguardViewMediator) this.keyguardViewMediatorLazy.get()).mGoingToSleep, 4);
    }

    public Integer getCustomBucket(View view) {
        return view instanceof OplusCustomRow ? 16 : null;
    }

    public final boolean hasMediaNotification() {
        MediaContainerView mediaContainerView = this.mediaContainerController.getMediaContainerView();
        return mediaContainerView != null && mediaContainerView.getVisibility() == 0;
    }

    public final boolean hasNotificationSeedingContainer() {
        Bundle bundleCall;
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "hasNotificationSeeding");
        SeedlingPlugin seedlingPlugin = this.plugin;
        return ((seedlingPlugin == null || (bundleCall = seedlingPlugin.call(bundleM)) == null) ? false : bundleCall.getBoolean("NotificationSeeding")) || this.oplusCustomRowControllerManager.isShowMediaController();
    }

    public final boolean hasSeedlingCard() {
        Iterator<OplusCustomRowController> it = this.oplusCustomRowControllerManager.getOplusCustomRowControllerList().iterator();
        while (it.hasNext()) {
            LaunchableView oplusCustomRow = it.next().getOplusCustomRow();
            if (oplusCustomRow != null && oplusCustomRow.getVisibility() == 0) {
                return true;
            }
        }
        return false;
    }

    public String hideHeadsUpNotification(boolean z, boolean z2) {
        String packageName;
        StatusBarNotification sbn;
        if (!z2 && ((NotificationStackScrollLayoutController) this.notificationStackScrollLayoutController.get()).getView().isHeadsUpTransition()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationSectionsManagerExImpl-->hideHeadsUpNotification: fail,because nssl isHeadsUpTransition");
            }
            return null;
        }
        ((FullScreenBannerHelper) this.mFullScreenBannerHelper.get()).hideBanner(new Runnable[0]);
        NotificationEntry topEntry = this.headsUpManagerPhone.getTopEntry();
        String key = topEntry != null ? topEntry.getKey() : null;
        long jCount = this.headsUpManagerPhone.getAllEntries().count();
        NotificationEntry topEntry2 = this.headsUpManagerPhone.getTopEntry();
        if (topEntry2 == null || (sbn = topEntry2.getSbn()) == null || (packageName = sbn.getPackageName()) == null) {
            packageName = "";
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(jCount, "topEntryKey: ", key, " entriesCount: ");
            sbM.append(", packageName: ");
            sbM.append(packageName);
            Log.d("SystemUi--Notification", "OplusNotificationSectionsManagerExImpl-->".concat(sbM.toString()));
        }
        if (key != null && jCount == 1) {
            this.headsUpManagerPhone.removeNotification(key, true, z, "oplus hideHeadsUpNotification");
            return packageName;
        }
        if (!z) {
            this.headsUpManagerPhone.setHeadsUpGoingAwayAnimationsAllowed(false);
        }
        this.headsUpManagerPhone.unpinAll(false, "ONSMEI.hideHeadsUpNotification");
        if (!z) {
            this.headsUpManagerPhone.setHeadsUpGoingAwayAnimationsAllowed(true);
        }
        return null;
    }

    public boolean isNotificationSeedingContainer(ExpandableView expandableView) {
        return (expandableView instanceof NotificationSeedingContainer) && ((NotificationSeedingContainer) expandableView).getIntrinsicHeight() != 0;
    }

    public boolean isOplusCustomContainer(ExpandableView expandableView) {
        return (expandableView instanceof OplusCustomRow) && ((OplusCustomRow) expandableView).getIntrinsicHeight() != 0;
    }

    public final boolean isSeedlingPopupMenuShowing() {
        boolean z = ((OplusCustomPopupMenuControllerImpl) this.oplusCustomPopupMenuController).isPopupMenuShowing;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusCustomPopupMenuController", KeyguardLogger$.ExternalSyntheticOutline0.m("isPopupMenuShowing = ", z), null, 8);
        return z;
    }

    public boolean isUsingCustomBuket(View view) {
        return view instanceof OplusCustomRow;
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public /* bridge */ /* synthetic */ void onDispatchTouchEvent(MotionEvent motionEvent) {
        super.onDispatchTouchEvent(motionEvent);
    }

    public final void onHeadsUpPinnedModeChanged(boolean z) {
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
        this.plugin = seedlingPlugin;
        this.oplusCustomRowControllerManager.initPlugin(seedlingPlugin);
        if (seedlingPlugin != null) {
            seedlingPlugin.onCreateView(2, getCustomRow());
        }
        this.keyguardUpdateMonitor.registerCallback(this.userSwitchingCallback);
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this.superPowerSaveSettingsListener);
        this.headsUpManagerPhone.addListener(this);
        NotificationBackgroundCustomRowHandler.INSTANCE.onPluginAdded(seedlingPlugin, context);
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginRemove(SeedlingPlugin seedlingPlugin) {
        this.oplusCustomRowControllerManager.setCustomContainerVisibilityGone();
        this.oplusCustomRowControllerManager.removePlugin();
        this.keyguardUpdateMonitor.removeCallback(this.userSwitchingCallback);
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this.superPowerSaveSettingsListener);
        this.headsUpManagerPhone.removeListener(this);
    }

    public void setDismissUsingRowTranslationX(boolean z) {
        this.oplusCustomRowControllerManager.setDismissUsingRowTranslationX(z);
    }
}
