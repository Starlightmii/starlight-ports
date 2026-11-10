package com.oplus.systemui.notification.dagger;

import android.content.Context;
import android.util.ArrayMap;
import com.android.systemui.keyguard.KeyguardNotificationCapsuleViewControllerEx;
import com.oplus.systemui.OplusDependencyImpl;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.base.OplusNotificationQsExt;
import com.oplus.systemui.notification.base.OplusNotificationQsInteractor;
import com.oplus.systemui.notification.base.RowAnimatorExt;
import com.oplus.systemui.notification.base.RowAnimatorExtImpl;
import com.oplus.systemui.notification.clearall.ClearAllController;
import com.oplus.systemui.notification.data.NotificationForceGroupEx;
import com.oplus.systemui.notification.data.forcegroup.OplusNotificationForceGroupExImpl;
import com.oplus.systemui.notification.headsup.OplusHeadsUp;
import com.oplus.systemui.notification.headsup.OplusHeadsUpImpl;
import com.oplus.systemui.notification.icdf.SynergyCallBackHelper;
import com.oplus.systemui.notification.icdf.helper.ICDFConnectionSwitchState;
import com.oplus.systemui.notification.icdf.helper.ICDFConnectionSwitchStateImpl;
import com.oplus.systemui.notification.interruption.OplusInterrupt;
import com.oplus.systemui.notification.interruption.OplusInterruptImpl;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractor;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.notification.mcs.NotificationDismissibilityProviderExt;
import com.oplus.systemui.notification.mcs.NotificationDismissibilityProviderExtImp;
import com.oplus.systemui.notification.mediapanel.AodMediaPromptController;
import com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController;
import com.oplus.systemui.notification.row.OplusNotificationClickEffect;
import com.oplus.systemui.notification.row.OplusNotificationClickEffectImpl;
import com.oplus.systemui.notification.row.OplusNotificationPopupMenu;
import com.oplus.systemui.notification.row.OplusNotificationViewDebug;
import com.oplus.systemui.notification.row.OplusSingletonContentViewExt;
import com.oplus.systemui.notification.row.OplusSingletonContentViewExtImpl;
import com.oplus.systemui.notification.row.OplusSwipeHelperEx;
import com.oplus.systemui.notification.row.menupanel.OplusNotificationPopupMenuImpl;
import com.oplus.systemui.notification.row.oplusgroup.ExpandGroupHeaderButtonBlurHelper;
import com.oplus.systemui.notification.tempfilter.OplusTempFilter;
import com.oplus.systemui.notification.tempfilter.OplusTempFilterImpl;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;
import com.oplusos.systemui.aod.mediapanel.AodMediaControlPanel;
import com.oplusos.systemui.aod.mediapanel.AodMediaDataListener;
import com.oplusos.systemui.aod.mediapanel.AodMediaViewStateController;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationDependexcyExAdder {
    public Lazy aodMediaAonController;
    public Provider aodMediaControlPanel;
    public Lazy aodMediaDataListener;
    public Lazy aodMediaPromptController;
    public Lazy aodMediaViewStateController;
    public Lazy clearAllController;
    public Lazy clickEffect;
    public Lazy conetxt;
    public Lazy expandGroupHeaderButtonBlurHelper;
    public Lazy iCDFConnectionSwitchState;
    public Lazy immersiveCardControl;
    public Lazy immersiveViewManager;
    public Lazy keyguardNotificationCapsuleViewControllerEx;
    public Lazy lockScreenExtStateController;
    public Lazy notificationAodTransition;
    public Lazy notificationCapsuleInteractor;
    public Lazy notificationForceGroupEx;
    public Lazy notificationQsInteractor;
    public Lazy oplusFullScreenIntentInterruptImpl;
    public Lazy oplusHeadsUp;
    public Lazy oplusSingletonContentViewExt;
    public Lazy oplusTempFilter;
    public Lazy popupMenu;
    public Lazy rowAnimatorExt;
    public Lazy stackCardControl;
    public Lazy swipeHelperEx;
    public Lazy synergyCallbackHelper;
    public Lazy viewDebug;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.dagger.OplusNotificationDependexcyExAdder$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusDependencyImpl.LazyDependencyCreator {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusNotificationDependexcyExAdder this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        public final Object createDependency(Context context) {
            switch (this.$r8$classId) {
                case 0:
                    return (OplusInterruptImpl) this.this$0.getOplusFullScreenIntentInterruptImpl().get();
                case 1:
                    return (IAodMediaAonController) this.this$0.getAodMediaAonController().get();
                case 2:
                    return (AodMediaDataListener) this.this$0.getAodMediaDataListener().get();
                case 3:
                    return (AodMediaViewStateController) this.this$0.getAodMediaViewStateController().get();
                case 4:
                    return (AodMediaPromptController) this.this$0.getAodMediaPromptController().get();
                case 5:
                    return (AodMediaControlPanel) this.this$0.getAodMediaControlPanel().get();
                case 6:
                    return (OplusSwipeHelperEx) this.this$0.getSwipeHelperEx().get();
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    return (OplusNotificationQsInteractor) this.this$0.getNotificationQsInteractor().get();
                case 8:
                    return (ClearAllController) this.this$0.getClearAllController().get();
                case 9:
                    return (RowAnimatorExtImpl) this.this$0.getRowAnimatorExt().get();
                case 10:
                    return (Context) this.this$0.getConetxt().get();
                case 11:
                    return (OplusSingletonContentViewExtImpl) this.this$0.getOplusSingletonContentViewExt().get();
                case 12:
                    return (NotificationAodTransition) this.this$0.getNotificationAodTransition().get();
                case 13:
                    return (KeyguardNotificationCapsuleViewControllerEx) this.this$0.getKeyguardNotificationCapsuleViewControllerEx().get();
                case 14:
                    return (ILockScreenExtStateController) this.this$0.getLockScreenExtStateController().get();
                case 15:
                    return (ImmersiveCardControl) this.this$0.getImmersiveCardControl().get();
                case 16:
                    return (OplusImmersiveViewManager) this.this$0.getImmersiveViewManager().get();
                case 17:
                    return (OplusNotificationCapsuleInteractor) this.this$0.getNotificationCapsuleInteractor().get();
                case 18:
                    return (StackCardControl) this.this$0.getStackCardControl().get();
                case 19:
                    return (ExpandGroupHeaderButtonBlurHelper) this.this$0.getExpandGroupHeaderButtonBlurHelper().get();
                case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                    return (OplusTempFilterImpl) this.this$0.getOplusTempFilter().get();
                case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                    return (OplusHeadsUpImpl) this.this$0.getOplusHeadsUp().get();
                case 22:
                    return (OplusNotificationClickEffectImpl) this.this$0.getClickEffect().get();
                case 23:
                    return (OplusNotificationViewDebug) this.this$0.getViewDebug().get();
                case 24:
                    return (OplusNotificationPopupMenuImpl) this.this$0.getPopupMenu().get();
                case 25:
                    return (SynergyCallBackHelper) this.this$0.getSynergyCallbackHelper().get();
                case 26:
                    return (ICDFConnectionSwitchStateImpl) this.this$0.getICDFConnectionSwitchState().get();
                default:
                    return (OplusNotificationForceGroupExImpl) this.this$0.getNotificationForceGroupEx().get();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.dagger.OplusNotificationDependexcyExAdder$start$10, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass10 implements OplusDependencyImpl.LazyDependencyCreator {
        public static final AnonymousClass10 INSTANCE = new AnonymousClass10();

        public final Object createDependency(Context context) {
            return new NotificationDismissibilityProviderExtImp(context);
        }
    }

    public final Lazy getAodMediaAonController() {
        Lazy lazy = this.aodMediaAonController;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Provider getAodMediaControlPanel() {
        Provider provider = this.aodMediaControlPanel;
        if (provider != null) {
            return provider;
        }
        return null;
    }

    public final Lazy getAodMediaDataListener() {
        Lazy lazy = this.aodMediaDataListener;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getAodMediaPromptController() {
        Lazy lazy = this.aodMediaPromptController;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getAodMediaViewStateController() {
        Lazy lazy = this.aodMediaViewStateController;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getClearAllController() {
        Lazy lazy = this.clearAllController;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getClickEffect() {
        Lazy lazy = this.clickEffect;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getConetxt() {
        Lazy lazy = this.conetxt;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getExpandGroupHeaderButtonBlurHelper() {
        Lazy lazy = this.expandGroupHeaderButtonBlurHelper;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getICDFConnectionSwitchState() {
        Lazy lazy = this.iCDFConnectionSwitchState;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getImmersiveCardControl() {
        Lazy lazy = this.immersiveCardControl;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getImmersiveViewManager() {
        Lazy lazy = this.immersiveViewManager;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getKeyguardNotificationCapsuleViewControllerEx() {
        Lazy lazy = this.keyguardNotificationCapsuleViewControllerEx;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getLockScreenExtStateController() {
        Lazy lazy = this.lockScreenExtStateController;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getNotificationAodTransition() {
        Lazy lazy = this.notificationAodTransition;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getNotificationCapsuleInteractor() {
        Lazy lazy = this.notificationCapsuleInteractor;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getNotificationForceGroupEx() {
        Lazy lazy = this.notificationForceGroupEx;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getNotificationQsInteractor() {
        Lazy lazy = this.notificationQsInteractor;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getOplusFullScreenIntentInterruptImpl() {
        Lazy lazy = this.oplusFullScreenIntentInterruptImpl;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getOplusHeadsUp() {
        Lazy lazy = this.oplusHeadsUp;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getOplusSingletonContentViewExt() {
        Lazy lazy = this.oplusSingletonContentViewExt;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getOplusTempFilter() {
        Lazy lazy = this.oplusTempFilter;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getPopupMenu() {
        Lazy lazy = this.popupMenu;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getRowAnimatorExt() {
        Lazy lazy = this.rowAnimatorExt;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getStackCardControl() {
        Lazy lazy = this.stackCardControl;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getSwipeHelperEx() {
        Lazy lazy = this.swipeHelperEx;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getSynergyCallbackHelper() {
        Lazy lazy = this.synergyCallbackHelper;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getViewDebug() {
        Lazy lazy = this.viewDebug;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final void setAodMediaAonController(Lazy lazy) {
        this.aodMediaAonController = lazy;
    }

    public final void setAodMediaControlPanel(Provider provider) {
        this.aodMediaControlPanel = provider;
    }

    public final void setAodMediaDataListener(Lazy lazy) {
        this.aodMediaDataListener = lazy;
    }

    public final void setAodMediaPromptController(Lazy lazy) {
        this.aodMediaPromptController = lazy;
    }

    public final void setAodMediaViewStateController(Lazy lazy) {
        this.aodMediaViewStateController = lazy;
    }

    public final void setClearAllController(Lazy lazy) {
        this.clearAllController = lazy;
    }

    public final void setClickEffect(Lazy lazy) {
        this.clickEffect = lazy;
    }

    public final void setConetxt(Lazy lazy) {
        this.conetxt = lazy;
    }

    public final void setExpandGroupHeaderButtonBlurHelper(Lazy lazy) {
        this.expandGroupHeaderButtonBlurHelper = lazy;
    }

    public final void setICDFConnectionSwitchState(Lazy lazy) {
        this.iCDFConnectionSwitchState = lazy;
    }

    public final void setImmersiveCardControl(Lazy lazy) {
        this.immersiveCardControl = lazy;
    }

    public final void setImmersiveViewManager(Lazy lazy) {
        this.immersiveViewManager = lazy;
    }

    public final void setKeyguardNotificationCapsuleViewControllerEx(Lazy lazy) {
        this.keyguardNotificationCapsuleViewControllerEx = lazy;
    }

    public final void setLockScreenExtStateController(Lazy lazy) {
        this.lockScreenExtStateController = lazy;
    }

    public final void setNotificationAodTransition(Lazy lazy) {
        this.notificationAodTransition = lazy;
    }

    public final void setNotificationCapsuleInteractor(Lazy lazy) {
        this.notificationCapsuleInteractor = lazy;
    }

    public final void setNotificationForceGroupEx(Lazy lazy) {
        this.notificationForceGroupEx = lazy;
    }

    public final void setNotificationQsInteractor(Lazy lazy) {
        this.notificationQsInteractor = lazy;
    }

    public final void setOplusFullScreenIntentInterruptImpl(Lazy lazy) {
        this.oplusFullScreenIntentInterruptImpl = lazy;
    }

    public final void setOplusHeadsUp(Lazy lazy) {
        this.oplusHeadsUp = lazy;
    }

    public final void setOplusSingletonContentViewExt(Lazy lazy) {
        this.oplusSingletonContentViewExt = lazy;
    }

    public final void setOplusTempFilter(Lazy lazy) {
        this.oplusTempFilter = lazy;
    }

    public final void setPopupMenu(Lazy lazy) {
        this.popupMenu = lazy;
    }

    public final void setRowAnimatorExt(Lazy lazy) {
        this.rowAnimatorExt = lazy;
    }

    public final void setStackCardControl(Lazy lazy) {
        this.stackCardControl = lazy;
    }

    public final void setSwipeHelperEx(Lazy lazy) {
        this.swipeHelperEx = lazy;
    }

    public final void setSynergyCallbackHelper(Lazy lazy) {
        this.synergyCallbackHelper = lazy;
    }

    public final void setViewDebug(Lazy lazy) {
        this.viewDebug = lazy;
    }

    public final void start(ArrayMap<Object, ? super OplusDependencyImpl.LazyDependencyCreator> arrayMap) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusInterrupt.class, anonymousClass1);
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(20);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusTempFilter.class, anonymousClass2);
        AnonymousClass1 anonymousClass3 = new AnonymousClass1(21);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusHeadsUp.class, anonymousClass3);
        AnonymousClass1 anonymousClass4 = new AnonymousClass1(22);
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationClickEffect.class, anonymousClass4);
        AnonymousClass1 anonymousClass5 = new AnonymousClass1(23);
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationViewDebug.class, anonymousClass5);
        AnonymousClass1 anonymousClass6 = new AnonymousClass1(24);
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationPopupMenu.class, anonymousClass6);
        AnonymousClass1 anonymousClass7 = new AnonymousClass1(25);
        anonymousClass7.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(SynergyCallBackHelper.class, anonymousClass7);
        AnonymousClass1 anonymousClass8 = new AnonymousClass1(26);
        anonymousClass8.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ICDFConnectionSwitchState.class, anonymousClass8);
        AnonymousClass1 anonymousClass9 = new AnonymousClass1(27);
        anonymousClass9.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationForceGroupEx.class, anonymousClass9);
        arrayMap.put(NotificationDismissibilityProviderExt.class, AnonymousClass10.INSTANCE);
        AnonymousClass1 anonymousClass10 = new AnonymousClass1(1);
        anonymousClass10.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(IAodMediaAonController.class, anonymousClass10);
        AnonymousClass1 anonymousClass11 = new AnonymousClass1(2);
        anonymousClass11.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(AodMediaDataListener.class, anonymousClass11);
        AnonymousClass1 anonymousClass12 = new AnonymousClass1(3);
        anonymousClass12.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(AodMediaViewStateController.class, anonymousClass12);
        AnonymousClass1 anonymousClass13 = new AnonymousClass1(4);
        anonymousClass13.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(AodMediaPromptController.class, anonymousClass13);
        AnonymousClass1 anonymousClass14 = new AnonymousClass1(5);
        anonymousClass14.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(AodMediaControlPanel.class, anonymousClass14);
        AnonymousClass1 anonymousClass15 = new AnonymousClass1(6);
        anonymousClass15.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusSwipeHelperEx.class, anonymousClass15);
        AnonymousClass1 anonymousClass16 = new AnonymousClass1(7);
        anonymousClass16.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationQsExt.class, anonymousClass16);
        AnonymousClass1 anonymousClass17 = new AnonymousClass1(8);
        anonymousClass17.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ClearAllController.class, anonymousClass17);
        AnonymousClass1 anonymousClass18 = new AnonymousClass1(9);
        anonymousClass18.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(RowAnimatorExt.class, anonymousClass18);
        AnonymousClass1 anonymousClass19 = new AnonymousClass1(10);
        anonymousClass19.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(Context.class, anonymousClass19);
        AnonymousClass1 anonymousClass20 = new AnonymousClass1(11);
        anonymousClass20.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusSingletonContentViewExt.class, anonymousClass20);
        AnonymousClass1 anonymousClass21 = new AnonymousClass1(12);
        anonymousClass21.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationAodTransition.class, anonymousClass21);
        AnonymousClass1 anonymousClass22 = new AnonymousClass1(13);
        anonymousClass22.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(KeyguardNotificationCapsuleViewControllerEx.class, anonymousClass22);
        AnonymousClass1 anonymousClass23 = new AnonymousClass1(14);
        anonymousClass23.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ILockScreenExtStateController.class, anonymousClass23);
        AnonymousClass1 anonymousClass24 = new AnonymousClass1(15);
        anonymousClass24.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ImmersiveCardControl.class, anonymousClass24);
        AnonymousClass1 anonymousClass25 = new AnonymousClass1(16);
        anonymousClass25.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusImmersiveViewManager.class, anonymousClass25);
        AnonymousClass1 anonymousClass26 = new AnonymousClass1(17);
        anonymousClass26.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationCapsuleInteractor.class, anonymousClass26);
        AnonymousClass1 anonymousClass27 = new AnonymousClass1(18);
        anonymousClass27.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(StackCardControl.class, anonymousClass27);
        AnonymousClass1 anonymousClass28 = new AnonymousClass1(19);
        anonymousClass28.this$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ExpandGroupHeaderButtonBlurHelper.class, anonymousClass28);
    }
}
