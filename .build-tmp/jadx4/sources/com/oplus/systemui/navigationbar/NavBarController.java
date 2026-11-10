package com.oplus.systemui.navigationbar;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.keyguard.Lifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.navigationbar.NavBarControllerEx;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.ScreenPinningNotify;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.systemui.navigationbar.gesture.NavBarOpenPlatformCustomize;
import com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.OtherBusinessManager;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;
import com.oplus.systemui.navigationbar.observer.IpeSlideGestureRejectionObserver;
import com.oplus.systemui.navigationbar.observer.NavButtonsPositionObserver;
import com.oplus.systemui.navigationbar.observer.NavButtonsSwitchDirectObserver;
import com.oplus.systemui.navigationbar.observer.NavGestureProcessObserver;
import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplus.systemui.navigationbar.observer.OpBreathModeObserver;
import com.oplus.systemui.navigationbar.observer.OplusGameModeObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeButtonWakeUpCuiObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeButtonWakeUpOcrDialogObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeButtonWakeUpOcrObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeHandleWakeUpOcrDialogObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeHandleWakeUpOcrObserver;
import com.oplus.systemui.navigationbar.observer.OplusKeyboardShortModelObserver;
import com.oplus.systemui.navigationbar.observer.SuperPowerSaveObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureBackVibrateObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureBarTypeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureHandleCuiObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureMistouchPreventionObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureBarTypeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureKeysModeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureMistouchPreventionObserver;
import com.oplus.systemui.navigationbar.observer.TaskbarSettingsOberver;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.utils.OplusThemeController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.navigationbar.EdgeBackGestureHandlerController;
import com.oplusos.systemui.navigationbar.OplusNavigationBarView;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.navigationbar.observer.VirtualKeyCombinationTypeObserver;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NavBarController extends NavBarControllerEx implements NavigationModeController.ModeChangedListener, ConfigurationController.ConfigurationListener {
    public final Context mContext;
    public final Handler mHandler;
    public final AnonymousClass1 mKeyguardCallback;
    public final KeyguardStateController mKeyguardStateController;
    public NavBarHelper mNavbarHelper;
    public final NavigationBarController mNavigationBarController;
    public final NavigationModeController mNavigationModeController;
    public final IOverlayManager mOverlayManager;
    public final AnonymousClass2 mThemeListener;
    public final AnonymousClass3 mWakefulnessObserver;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements KeyguardStateController.Callback {
        public /* synthetic */ NavBarController this$0;

        public final void onKeyguardShowingChanged() {
            this.this$0.setKeyguardStateToHandle();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarController$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements OplusThemeController.ThemeListener {
        public /* synthetic */ NavBarController this$0;

        @Override // com.oplus.systemui.utils.OplusThemeController.ThemeListener
        public final void onThemeChanged() {
            NavigationBarController navigationBarController = this.this$0.mNavigationBarController;
            for (int i = 0; i < navigationBarController.getNavigationBars().size(); i++) {
                NavigationBar navigationBar = (NavigationBar) navigationBarController.getNavigationBars().valueAt(i);
                if (navigationBar == null) {
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onThemeChanged: navbar is null");
                } else {
                    OplusNavigationBarView oplusNavigationBarView = (OplusNavigationBarView) navigationBar.getView();
                    if (oplusNavigationBarView != null) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onThemeChanged: update");
                        oplusNavigationBarView.updateMainIcons();
                    }
                }
            }
            for (int i2 = 0; i2 < EdgeBackGestureHandlerController.getEdgeBackHandlerArrs().size(); i2++) {
                EdgeBackGestureHandler edgeBackGestureHandlerValueAt = EdgeBackGestureHandlerController.getEdgeBackHandlerArrs().valueAt(i2);
                if (edgeBackGestureHandlerValueAt.mSideGestureViewManager != null) {
                    ClipboardOverlayView$.ExternalSyntheticOutline0.m(edgeBackGestureHandlerValueAt.mMainDisplayId, LogUtil.TAG_NAVBAR, "EdgeBackGestureHandler", new StringBuilder("updateBackIcon displayId :"));
                    edgeBackGestureHandlerValueAt.mSideGestureViewManager.updateBackIcon();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.NavBarController$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements WakefulnessLifecycle.Observer {
        public /* synthetic */ NavBarController this$0;

        public final void onFinishedGoingToSleep() {
            ScreenPinningNotify screenPinningNotify;
            Toast toast;
            ScreenPinningNotify screenPinningNotify2;
            Toast toast2;
            NavigationBarController navigationBarController = this.this$0.mNavigationBarController;
            if (NavBarUtils.isVirtualKeyMode()) {
                if (navigationBarController.getTaskbarDelegate() != null && navigationBarController.getTaskbarDelegate().mInitialized && (toast2 = (screenPinningNotify2 = navigationBarController.getTaskbarDelegate().mScreenPinningNotify).mPinningEnterExitToast) != null) {
                    toast2.cancel();
                    screenPinningNotify2.mPinningEnterExitToast = null;
                }
                for (int i = 0; i < navigationBarController.getNavigationBars().size(); i++) {
                    NavigationBar navigationBar = (NavigationBar) navigationBarController.getNavigationBars().valueAt(i);
                    if (navigationBar != null && (toast = (screenPinningNotify = navigationBar.getView().mScreenPinningNotify).mPinningEnterExitToast) != null) {
                        toast.cancel();
                        screenPinningNotify.mPinningEnterExitToast = null;
                    }
                }
            }
        }
    }

    public NavBarController(Context context, Handler handler, NavigationBarController navigationBarController, NavigationModeController navigationModeController, OtherBusinessManager otherBusinessManager, OplusThemeController oplusThemeController, KeyguardStateController keyguardStateController, NavBarHelper navBarHelper, WakefulnessLifecycle wakefulnessLifecycle, ConfigurationController configurationController) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mContext = context;
        this.mHandler = handler;
        this.mNavigationBarController = navigationBarController;
        this.mNavigationModeController = navigationModeController;
        navigationModeController.addListener(this);
        this.mKeyguardStateController = keyguardStateController;
        this.mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
        NavBarReceiver navBarReceiver = new NavBarReceiver();
        NavBarReceiver.AnonymousClass1 anonymousClass4 = new NavBarReceiver.AnonymousClass1(0);
        anonymousClass4.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass1 anonymousClass5 = new NavBarReceiver.AnonymousClass1(2);
        anonymousClass5.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass1 anonymousClass6 = new NavBarReceiver.AnonymousClass1(3);
        anonymousClass6.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass1 anonymousClass7 = new NavBarReceiver.AnonymousClass1(4);
        anonymousClass7.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass5 anonymousClass8 = new NavBarReceiver.AnonymousClass5();
        anonymousClass8.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass6 anonymousClass9 = new NavBarReceiver.AnonymousClass6();
        NavBarReceiver.AnonymousClass1 anonymousClass10 = new NavBarReceiver.AnonymousClass1(5);
        anonymousClass10.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass1 anonymousClass11 = new NavBarReceiver.AnonymousClass1(6);
        anonymousClass11.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass1 anonymousClass12 = new NavBarReceiver.AnonymousClass1(7);
        anonymousClass12.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass1 anonymousClass13 = new NavBarReceiver.AnonymousClass1(1);
        anonymousClass13.this$0 = navBarReceiver;
        VarHandle.storeStoreFence();
        NavBarReceiver.AnonymousClass11 anonymousClass14 = new NavBarReceiver.AnonymousClass11();
        anonymousClass14.this$0 = navBarReceiver;
        anonymousClass14.mCurrentUser = OsBinderCacheUtils.getCurrentUserId();
        VarHandle.storeStoreFence();
        navBarReceiver.mNavBarController = this;
        navBarReceiver.mContext = context;
        NavStateObserver navStateObserver = NavStateObserver.INSTANCE;
        navStateObserver.registerForUserSwitch(context, false);
        SwipeSideGestureBackVibrateObserver.INSTANCE.registerForUserSwitch(context, false);
        SwipeSideGestureBarTypeObserver swipeSideGestureBarTypeObserver = SwipeSideGestureBarTypeObserver.INSTANCE;
        swipeSideGestureBarTypeObserver.registerForUserSwitch(context, false);
        SwipeSideGestureMistouchPreventionObserver.INSTANCE.registerForUserSwitch(context, false);
        SwipeUpGestureBarTypeObserver swipeUpGestureBarTypeObserver = SwipeUpGestureBarTypeObserver.INSTANCE;
        swipeUpGestureBarTypeObserver.registerForUserSwitch(context, false);
        SwipeUpGestureKeysModeObserver swipeUpGestureKeysModeObserver = SwipeUpGestureKeysModeObserver.INSTANCE;
        swipeUpGestureKeysModeObserver.registerForUserSwitch(context, false);
        SwipeUpGestureMistouchPreventionObserver.INSTANCE.registerForUserSwitch(context, false);
        VirtualKeyCombinationTypeObserver virtualKeyCombinationTypeObserver = VirtualKeyCombinationTypeObserver.INSTANCE;
        virtualKeyCombinationTypeObserver.registerForUserSwitch(context, false);
        SwipeSideGestureHandleCuiObserver.INSTANCE.registerForUserSwitch(context, false);
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            NavBarFeatureOption.INSTANCE.getClass();
            if (NavBarFeatureOption.sIsSupportTaskbar) {
                OplusHomeButtonWakeUpCuiObserver.INSTANCE.registerForUserSwitch(context, false);
            } else {
                OplusHomeButtonWakeUpOcrObserver.INSTANCE.registerForUserSwitch(context, false);
                OplusHomeHandleWakeUpOcrObserver.INSTANCE.registerForUserSwitch(context, false);
                OplusHomeButtonWakeUpOcrDialogObserver.INSTANCE.registerForUserSwitch(context, false);
                OplusHomeHandleWakeUpOcrDialogObserver.INSTANCE.registerForUserSwitch(context, false);
            }
        }
        if (FeatureOption.isSupportNavBarButtonsPosition()) {
            NavButtonsPositionObserver.INSTANCE.registerForUserSwitch(context, false);
            NavButtonsSwitchDirectObserver.INSTANCE.registerForUserSwitch(context, false);
        }
        OplusKeyboardShortModelObserver.INSTANCE.registerForUserSwitch(context, false);
        NavGestureProcessObserver.Companion companion = NavGestureProcessObserver.Companion;
        companion.getInstance().registerForUserSwitch(context, false);
        companion.getInstance().registerForUserSwitch(context, false);
        OpBreathModeObserver.INSTANCE.registerForUserSwitch(context, false);
        TaskbarSettingsOberver taskbarSettingsOberver = TaskbarSettingsOberver.INSTANCE;
        taskbarSettingsOberver.registerForUserSwitch(context, false);
        SuperPowerSaveObserver superPowerSaveObserver = SuperPowerSaveObserver.INSTANCE;
        superPowerSaveObserver.registerForUserSwitch(context, false);
        OplusGameModeObserver.INSTANCE.registerForUserSwitch(context, false);
        IpeSlideGestureRejectionObserver.INSTANCE.registerForUserSwitch(context, false);
        NavBarOliveLinkConnector.INSTANCE.start(context, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME);
        navStateObserver.addListener(anonymousClass4);
        swipeSideGestureBarTypeObserver.addListener(anonymousClass5);
        swipeUpGestureBarTypeObserver.addListener(anonymousClass6);
        swipeUpGestureKeysModeObserver.addListener(anonymousClass7);
        virtualKeyCombinationTypeObserver.addListener(anonymousClass10);
        if (FeatureOption.isSupportNavBarButtonsPosition()) {
            NavButtonsPositionObserver.INSTANCE.addListener(anonymousClass11);
            NavButtonsSwitchDirectObserver.INSTANCE.addListener(anonymousClass12);
        }
        if (FeatureOption.isFoldDevice()) {
            DeviceStateController.getInstance().addCallback(anonymousClass8);
        }
        taskbarSettingsOberver.addListener(anonymousClass13);
        superPowerSaveObserver.addListener(anonymousClass14);
        TaskStackChangeListeners.getInstance().registerTaskStackListener(anonymousClass9);
        onNavStateChange(navStateObserver.getNavState());
        VarHandle.storeStoreFence();
        NavBarOpenPlatformCustomize.Companion.getInstance(context);
        otherBusinessManager.initOtherBusinessManager();
        configurationController.addCallback(this);
        if (oplusThemeController != null) {
            oplusThemeController.addCallback(anonymousClass2);
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(anonymousClass1);
        ((Lifecycle) wakefulnessLifecycle).mObservers.add(anonymousClass3);
    }

    private OplusNavigationHandle getHomeHandle() {
        for (NavigationBar navigationBar : getNavigationBars()) {
            if (navigationBar.getView() != null && navigationBar.getView().getHomeHandle() != null) {
                return (OplusNavigationHandle) navigationBar.getView().getHomeHandle().getCurrentView();
            }
        }
        return null;
    }

    private List<NavigationBar> getNavigationBars() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            NavigationBarController navigationBarController = this.mNavigationBarController;
            if (i >= navigationBarController.getNavigationBars().size()) {
                return arrayList;
            }
            NavigationBar navigationBar = (NavigationBar) navigationBarController.getNavigationBars().valueAt(i);
            if (navigationBar != null) {
                arrayList.add(navigationBar);
            } else {
                LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "navbar is null");
            }
            i++;
        }
    }

    public final boolean isOverlayInfoAvailable(int i, String str) {
        OverlayInfo overlayInfo;
        try {
            overlayInfo = this.mOverlayManager.getOverlayInfo(str, i);
        } catch (RemoteException e) {
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "Cannot get Overlay info - apk: ", str, ", userId: ", ", exception: ");
            sbM.append(e);
            LogUtil.e(LogUtil.TAG_NAVBAR, "NavBarController", sbM.toString());
            overlayInfo = null;
        } catch (Exception e2) {
            StringBuilder sbM2 = ConstraintSet$.ExternalSyntheticOutline0.m(i, "Cannot get Overlay info - apk: ", str, ", userId: ", ", exception: ");
            sbM2.append(e2);
            LogUtil.e(LogUtil.TAG_NAVBAR, "NavBarController", sbM2.toString());
            overlayInfo = null;
        }
        return overlayInfo != null;
    }

    public final void onConfigChanged(Configuration configuration) {
        NavBarFeatureOption.INSTANCE.getClass();
        if (NavBarFeatureOption.sIsSupportTaskbar) {
            setKeyguardStateToHandle();
        }
    }

    public final void onNavStateChange(int i) {
        NavigationModeController navigationModeController = this.mNavigationModeController;
        if (i == 0 || i == 1) {
            navigationModeController.updateCurrentInteractionMode(0);
            int currentInteractionMode = navigationModeController.getCurrentInteractionMode();
            int i2 = QuickStepContract.$r8$clinit;
            if (currentInteractionMode != 0) {
                LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onNavStateChange: 3buttons");
                setCurrentSystemNavigationMode(this.mOverlayManager, "system_nav_3buttons");
            }
        } else if (i == 2) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet() || FeatureOption.isFoldDevice()) {
                NavBarSettingsValueProxy.setNavState(this.mContext, 3);
                LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "change swipe_up to swipe_side");
            } else {
                navigationModeController.updateCurrentInteractionMode(2);
                if (!QuickStepContract.isGesturalMode(navigationModeController.getCurrentInteractionMode())) {
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onNavStateChange: swipe up gesture");
                    setCurrentSystemNavigationMode(this.mOverlayManager, "system_nav_gestural");
                }
            }
        } else if (i == 3) {
            navigationModeController.updateCurrentInteractionMode(2);
            if (!QuickStepContract.isGesturalMode(navigationModeController.getCurrentInteractionMode())) {
                LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onNavStateChange: gesture");
                setCurrentSystemNavigationMode(this.mOverlayManager, "system_nav_gestural");
            }
        }
        NavBarController$$ExternalSyntheticLambda0 navBarController$$ExternalSyntheticLambda0 = new NavBarController$$ExternalSyntheticLambda0(0);
        navBarController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mHandler.post(navBarController$$ExternalSyntheticLambda0);
    }

    public final void onNavigationModeChanged(int i) {
    }

    public final void setCurrentSystemNavigationMode(IOverlayManager iOverlayManager, String str) {
        NavBarController$$ExternalSyntheticLambda8 navBarController$$ExternalSyntheticLambda8 = new NavBarController$$ExternalSyntheticLambda8();
        navBarController$$ExternalSyntheticLambda8.f$0 = this;
        navBarController$$ExternalSyntheticLambda8.f$1 = iOverlayManager;
        navBarController$$ExternalSyntheticLambda8.f$2 = str;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(navBarController$$ExternalSyntheticLambda8);
    }

    public final void setKeyguardStateToHandle() {
        OplusNavigationHandle homeHandle = getHomeHandle();
        if (homeHandle != null) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguardStateController;
            homeHandle.setKeyguardState(keyguardStateControllerImpl.mShowing);
            GestureHomeHandleEventController.INSTANCE.setKeyguardState(keyguardStateControllerImpl.mShowing);
        }
    }
}
