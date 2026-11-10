package com.oplus.systemui.notification.lockscreen.statemanager;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.shade.NotificationPanelView;
import com.oplus.systemui.notification.base.radius.RadiusParams;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl;
import com.oplus.systemui.notification.lockscreen.capsule.event.CapsuleSpotLightParams;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimSinkingParams;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleAnimParams;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleInternalAnimParams;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.row.NotificationMenuRowParams;
import com.oplus.systemui.notification.row.customrow.guidetips.OplusCustomRowLockScreenInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationCoreStartable implements CoreStartable {
    private final Context context;
    private final CapsuleNotificationDataController controller;
    private final CapsuleLightOSController lightOSController;
    private final LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager;
    private final LockScreenSeedingInteractor lockScreenSeedingInteractor;
    private final OplusNotificationCapsuleInteractorImpl notificationCapsuleInteractorImpl;
    private final OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor;
    private final OplusImmersiveViewManager oplusImmersiveViewManager;
    private final NotificationPanelView panView;
    private final LockScreenNotificationViewBinder viewBinder;

    public LockScreenNotificationCoreStartable(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, NotificationPanelView notificationPanelView, LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager, LockScreenSeedingInteractor lockScreenSeedingInteractor, OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl, Context context, CapsuleNotificationDataController capsuleNotificationDataController, OplusImmersiveViewManager oplusImmersiveViewManager, OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor, CapsuleLightOSController capsuleLightOSController) {
        this.viewBinder = lockScreenNotificationViewBinder;
        this.panView = notificationPanelView;
        this.lockScreenNotificationExtStateManager = lockScreenNotificationExtStateManager;
        this.lockScreenSeedingInteractor = lockScreenSeedingInteractor;
        this.notificationCapsuleInteractorImpl = oplusNotificationCapsuleInteractorImpl;
        this.context = context;
        this.controller = capsuleNotificationDataController;
        this.oplusImmersiveViewManager = oplusImmersiveViewManager;
        this.oplusCustomRowLockScreenInteractor = oplusCustomRowLockScreenInteractor;
        this.lightOSController = capsuleLightOSController;
    }

    public final void start() {
        ExtStateAnimParams.INSTANCE.initOliveLink(this.context);
        ExtStateAnimSinkingParams.INSTANCE.initOliveLink(this.context);
        RadiusParams.INSTANCE.initOliveLink(this.context);
        CapsuleConfig.INSTANCE.initConfig(this.context);
        CapsuleAnimParams.INSTANCE.initOliveLink(this.context);
        CapsuleSpringAnimParams.INSTANCE.initOliveLink(this.context);
        CapsuleSpotLightParams.INSTANCE.initOliveLink(this.context);
        CapsuleInternalAnimParams.INSTANCE.initOliveLink(this.context);
        CapsuleMetaBallParams.INSTANCE.initOliveLink(this.context);
        NotificationMenuRowParams.INSTANCE.initOliveLink(this.context);
        this.viewBinder.bindWhileAttached(this.panView);
        this.lockScreenNotificationExtStateManager.init();
        this.lockScreenSeedingInteractor.start();
        this.oplusImmersiveViewManager.start();
        this.notificationCapsuleInteractorImpl.start();
        this.controller.registerSystemStateListeners();
        this.lightOSController.init();
    }
}
