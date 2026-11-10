package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import com.android.systemui.navigationbar.buttons.KeyButtonViewEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.common.OplusSwitchCameraPreviewStartable;
import com.oplus.systemui.navigationbar.gesture.NavBarOpenPlatformCustomize;
import com.oplus.systemui.navigationbar.gesture.manager.GestureUIThreadManager;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController;
import com.oplus.systemui.navigationbar.gesture.sidegesture.NavigationGestureDetector;
import com.oplus.systemui.navigationbar.utils.OcrScreenCompatVersion;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyButtonViewExImpl extends KeyButtonViewEx {
    private static final String TAG = "KeyButtonViewExImpl";
    private final Lazy centralSurfacesImpl;
    private final Context context;
    private final kotlin.Lazy gestureDetector$delegate;
    public boolean isCalledLongPress;
    private final Lazy switchCameraPreview;
    public static final Companion Companion = new Companion();
    private static final int[] heyCastDeviceIDArray = {997, 998, 999, 1000};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$-M6v6L_h08o55j2jXTCy8wY4Ji0, reason: not valid java name */
    public static NavigationGestureDetector m23$r8$lambda$M6v6L_h08o55j2jXTCy8wY4Ji0(KeyButtonViewExImpl keyButtonViewExImpl) {
        Context context = keyButtonViewExImpl.context;
        KeyButtonViewExImpl$gestureDetector$2$1 keyButtonViewExImpl$gestureDetector$2$1 = new KeyButtonViewExImpl$gestureDetector$2$1();
        keyButtonViewExImpl$gestureDetector$2$1.this$0 = keyButtonViewExImpl;
        VarHandle.storeStoreFence();
        return new NavigationGestureDetector(context, keyButtonViewExImpl$gestureDetector$2$1);
    }

    public KeyButtonViewExImpl(Context context, Lazy lazy, Lazy lazy2) {
        this.context = context;
        this.centralSurfacesImpl = lazy;
        this.switchCameraPreview = lazy2;
        KeyButtonViewExImpl$$ExternalSyntheticLambda0 keyButtonViewExImpl$$ExternalSyntheticLambda0 = new KeyButtonViewExImpl$$ExternalSyntheticLambda0();
        keyButtonViewExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.gestureDetector$delegate = LazyKt__LazyJVMKt.lazy(keyButtonViewExImpl$$ExternalSyntheticLambda0);
    }

    private final NavigationGestureDetector getGestureDetector() {
        return (NavigationGestureDetector) this.gestureDetector$delegate.getValue();
    }

    public final void disableCameraPreview() {
        ((OplusSwitchCameraPreviewStartable) this.switchCameraPreview.get()).disableCameraPreview();
    }

    public final Lazy getCentralSurfacesImpl() {
        return this.centralSurfacesImpl;
    }

    public final Context getContext() {
        return this.context;
    }

    public Handler getGestureUIHandler() {
        return GestureUIThreadManager.INSTANCE.getGestureUIHandler();
    }

    public int getKeyEventDeviceId(MotionEvent motionEvent) {
        if (ArraysKt___ArraysKt.contains(heyCastDeviceIDArray, motionEvent.getDeviceId())) {
            return motionEvent.getDeviceId();
        }
        return -1;
    }

    public final int getKeyEventFlags(int i, int i2) {
        if (i2 == 3 && OplusNavBarUtils.isSupportOcrScreen()) {
            OcrScreenCompatVersion.INSTANCE.getClass();
            if (OcrScreenCompatVersion.isMetaDataSupport) {
                return i | OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING;
            }
        }
        return i;
    }

    public final Lazy getSwitchCameraPreview() {
        return this.switchCameraPreview;
    }

    public void onMotionEvent(MotionEvent motionEvent, int i) {
        if ((OplusNavBarUtils.isEnableOcrScreen() || CustomizeFeatureOption.sIsSupportCircleToSearchNavbarHidden) && i == 3) {
            NotificationPanelViewController shadeViewController = ((CentralSurfacesImpl) this.centralSurfacesImpl.get()).getShadeViewController();
            if (((CentralSurfacesImpl) this.centralSurfacesImpl.get()).isKeyguardShowing()) {
                return;
            }
            if (shadeViewController instanceof NotificationPanelViewController) {
                NotificationPanelViewController notificationPanelViewController = shadeViewController;
                if (!notificationPanelViewController.isFullyCollapsed()) {
                    return;
                }
                if (!(notificationPanelViewController.mCentralSurfaces.getSeparateNotificationAndQS().enableSeparateNotificationAndQS() ? notificationPanelViewController.mCentralSurfaces.getSeparateNotificationAndQS().isFullyCollapsed() : true)) {
                    return;
                }
            }
            getGestureDetector().onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1 || actionMasked == 3 || actionMasked == 5) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    Log.d("SystemUi--NavBar", MobileInputLogger$.ExternalSyntheticOutline0.m("onMotionEvent: cancel ", actionMasked, " , isCalledLongPress ", "KeyButtonViewExImpl-->", this.isCalledLongPress));
                }
                if (this.isCalledLongPress) {
                    return;
                }
                GestureHomeHandleEventController.INSTANCE.onLongPressCancel();
            }
        }
    }

    public boolean sendEvent(int i, Context context) {
        if (!NavBarOpenPlatformCustomize.Companion.getInstance(context).isNavigationBarDisabled) {
            return false;
        }
        if (i != 3 && i != 4 && i != 187) {
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_NAVBAR, "KeyButton", "NavigationBar is disabled by customized API", null, 8);
        return true;
    }
}
