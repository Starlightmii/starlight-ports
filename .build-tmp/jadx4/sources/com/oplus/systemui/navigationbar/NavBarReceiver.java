package com.oplus.systemui.navigationbar;

import android.content.Context;
import android.os.Handler;
import androidx.collection.FloatList$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.oplus.systemui.navigationbar.observer.NavButtonsPositionObserver;
import com.oplus.systemui.navigationbar.observer.NavButtonsSwitchDirectObserver;
import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplus.systemui.navigationbar.observer.SuperPowerSaveObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureBarTypeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureBarTypeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureKeysModeObserver;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarReceiver {
    public Context mContext;
    public AnonymousClass5 mFoldStateListener;
    public NavBarController mNavBarController;
    public AnonymousClass1 mNavButtonsPositionListener;
    public AnonymousClass1 mNavButtonsSwitchDirectListener;
    public AnonymousClass1 mNavStateListener;
    public AnonymousClass1 mSideGestureBarTypeListener;
    public AnonymousClass11 mSuperPowerSaveListener;
    public AnonymousClass6 mTaskStackChangeListener;
    public AnonymousClass1 mTaskbarEnableListener;
    public AnonymousClass1 mUpGestureBarTypeListener;
    public AnonymousClass1 mUpGestureKeysModeListener;
    public AnonymousClass1 mVirtualKeyCombinationTypeListener;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarReceiver$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IObserverListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ NavBarReceiver this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // com.oplusos.systemui.common.observer.IObserverListener
        public final void onChange(boolean z) {
            int i = 2;
            int i2 = 1;
            int i3 = 3;
            switch (this.$r8$classId) {
                case 0:
                    NavBarReceiver navBarReceiver = this.this$0;
                    NavBarUtils.updateNavGestureVisibility(navBarReceiver.mContext);
                    navBarReceiver.mNavBarController.onNavStateChange(NavStateObserver.INSTANCE.getNavState());
                    break;
                case 1:
                    LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarReceiver", "onChange onTaskbarChange");
                    NavBarController navBarController = this.this$0.mNavBarController;
                    Handler handler = navBarController.mHandler;
                    NavigationBarController navigationBarController = navBarController.mNavigationBarController;
                    Objects.requireNonNull(navigationBarController);
                    NavBarController$$ExternalSyntheticLambda0 navBarController$$ExternalSyntheticLambda0 = new NavBarController$$ExternalSyntheticLambda0(3);
                    navBarController$$ExternalSyntheticLambda0.f$0 = navigationBarController;
                    VarHandle.storeStoreFence();
                    handler.post(navBarController$$ExternalSyntheticLambda0);
                    break;
                case 2:
                    NavBarReceiver navBarReceiver2 = this.this$0;
                    NavBarUtils.updateNavGestureVisibility(navBarReceiver2.mContext);
                    NavBarController navBarController2 = navBarReceiver2.mNavBarController;
                    int swipeSideGestureBarType = SwipeSideGestureBarTypeObserver.INSTANCE.getSwipeSideGestureBarType();
                    Handler handler2 = navBarController2.mHandler;
                    NavBarController$$ExternalSyntheticLambda2 navBarController$$ExternalSyntheticLambda2 = new NavBarController$$ExternalSyntheticLambda2(i);
                    navBarController$$ExternalSyntheticLambda2.f$0 = navBarController2;
                    navBarController$$ExternalSyntheticLambda2.f$1 = swipeSideGestureBarType;
                    VarHandle.storeStoreFence();
                    handler2.post(navBarController$$ExternalSyntheticLambda2);
                    break;
                case 3:
                    NavBarController navBarController3 = this.this$0.mNavBarController;
                    int swipeUpGestureBarType = SwipeUpGestureBarTypeObserver.INSTANCE.getSwipeUpGestureBarType();
                    Handler handler3 = navBarController3.mHandler;
                    NavBarController$$ExternalSyntheticLambda2 navBarController$$ExternalSyntheticLambda3 = new NavBarController$$ExternalSyntheticLambda2(0);
                    navBarController$$ExternalSyntheticLambda3.f$0 = navBarController3;
                    navBarController$$ExternalSyntheticLambda3.f$1 = swipeUpGestureBarType;
                    VarHandle.storeStoreFence();
                    handler3.post(navBarController$$ExternalSyntheticLambda3);
                    break;
                case 4:
                    NavBarController navBarController4 = this.this$0.mNavBarController;
                    SwipeUpGestureKeysModeObserver.INSTANCE.getSwipeUpGestureKeysMode();
                    Handler handler4 = navBarController4.mHandler;
                    NavBarController$$ExternalSyntheticLambda0 navBarController$$ExternalSyntheticLambda1 = new NavBarController$$ExternalSyntheticLambda0(2);
                    navBarController$$ExternalSyntheticLambda1.f$0 = navBarController4;
                    VarHandle.storeStoreFence();
                    handler4.post(navBarController$$ExternalSyntheticLambda1);
                    break;
                case 5:
                    NavBarController navBarController5 = this.this$0.mNavBarController;
                    Handler handler5 = navBarController5.mHandler;
                    NavBarController$$ExternalSyntheticLambda0 navBarController$$ExternalSyntheticLambda4 = new NavBarController$$ExternalSyntheticLambda0(1);
                    navBarController$$ExternalSyntheticLambda4.f$0 = navBarController5;
                    VarHandle.storeStoreFence();
                    handler5.post(navBarController$$ExternalSyntheticLambda4);
                    break;
                case 6:
                    NavBarController navBarController6 = this.this$0.mNavBarController;
                    int navButtonsPosition = NavButtonsPositionObserver.INSTANCE.getNavButtonsPosition();
                    Handler handler6 = navBarController6.mHandler;
                    NavBarController$$ExternalSyntheticLambda2 navBarController$$ExternalSyntheticLambda5 = new NavBarController$$ExternalSyntheticLambda2(i2);
                    navBarController$$ExternalSyntheticLambda5.f$0 = navBarController6;
                    navBarController$$ExternalSyntheticLambda5.f$1 = navButtonsPosition;
                    VarHandle.storeStoreFence();
                    handler6.post(navBarController$$ExternalSyntheticLambda5);
                    break;
                default:
                    NavBarController navBarController7 = this.this$0.mNavBarController;
                    int navButtonsSwitchDirect = NavButtonsSwitchDirectObserver.INSTANCE.getNavButtonsSwitchDirect();
                    Handler handler7 = navBarController7.mHandler;
                    NavBarController$$ExternalSyntheticLambda2 navBarController$$ExternalSyntheticLambda6 = new NavBarController$$ExternalSyntheticLambda2(i3);
                    navBarController$$ExternalSyntheticLambda6.f$0 = navBarController7;
                    navBarController$$ExternalSyntheticLambda6.f$1 = navButtonsSwitchDirect;
                    VarHandle.storeStoreFence();
                    handler7.post(navBarController$$ExternalSyntheticLambda6);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarReceiver$11, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass11 implements IObserverListener {
        public int mCurrentUser;
        public /* synthetic */ NavBarReceiver this$0;

        @Override // com.oplusos.systemui.common.observer.IObserverListener
        public final void onChange(boolean z) {
            int superPowerSaveState = SuperPowerSaveObserver.INSTANCE.getSuperPowerSaveState();
            int currentUser = KeyguardUpdateMonitor.getCurrentUser();
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mCurrentUser, LogUtil.TAG_NAVBAR, "NavBarReceiver", FloatList$.ExternalSyntheticOutline0.m("onChange mSuperPowerSaveChange, superPowerSaveState = ", superPowerSaveState, currentUser, " , currentUser: ", " , mCurrentUser: "));
            if ((superPowerSaveState == 1 || superPowerSaveState == 0) && currentUser == this.mCurrentUser) {
                this.this$0.mNavBarController.mNavigationBarController.mOverviewProxyService.updateEnabledAndBinding();
            }
            this.mCurrentUser = currentUser;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarReceiver$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements DeviceStateController.StateChangedListener {
        public /* synthetic */ NavBarReceiver this$0;

        @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
        public final void onStateChanged(int i) {
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "mSystemFoldModeListener -- change:", LogUtil.TAG, LogUtil.TAG_NAVBAR);
            NavBarController navBarController = this.this$0.mNavBarController;
            navBarController.getClass();
            NavButtonsPositionObserver.INSTANCE.getClass();
            boolean z = NavButtonsPositionObserver.navButtonPosition == 1;
            Handler handler = navBarController.mHandler;
            NavBarController$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new NavBarController$.ExternalSyntheticLambda9();
            externalSyntheticLambda9.f$0 = navBarController;
            externalSyntheticLambda9.f$1 = z;
            VarHandle.storeStoreFence();
            handler.post(externalSyntheticLambda9);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarReceiver$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements TaskStackChangeListener {
        public final void onLockTaskModeChanged(int i) {
            NavBarUtils.setScreenPinningActive(i == 2);
        }
    }
}
