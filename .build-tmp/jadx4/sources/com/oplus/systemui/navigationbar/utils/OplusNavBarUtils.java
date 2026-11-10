package com.oplus.systemui.navigationbar.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.navigationbar.observer.NavButtonsPositionObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeButtonWakeUpOcrDialogObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeButtonWakeUpOcrObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeHandleWakeUpOcrDialogObserver;
import com.oplus.systemui.navigationbar.observer.OplusHomeHandleWakeUpOcrObserver;
import com.oplus.systemui.navigationbar.observer.TaskbarSettingsOberver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNavBarUtils {
    private static final String TAG = "OplusNavBarUtils";
    public static boolean mFoldState;
    public static boolean mTaskbarEnable;
    public static final OplusNavBarUtils INSTANCE = new OplusNavBarUtils();
    private static final Lazy isSupportExternalTaskBar$delegate = LazyKt__LazyJVMKt.lazy(new OplusNavBarUtils$$ExternalSyntheticLambda0());

    public static final void compatEdgeToEdge(Window window) {
        if (window != null) {
            window.setNavigationBarContrastEnforced(false);
            View decorView = window.getDecorView();
            if (decorView != null) {
                decorView.setOnApplyWindowInsetsListener(compatEdgeToEdge.1.1.INSTANCE);
            }
        }
    }

    public static final void getPackageIcon(String str, Function1 function1) {
        BuildersKt.launch$default(CoroutineScopeKt.MainScope(), (CoroutineContext) null, (CoroutineStart) null, new getPackageIcon.1(str, function1, (Continuation) null), 3);
    }

    public static final Drawable getPackageIconBlock(String str) {
        return (Drawable) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new getPackageIconBlock.1(str, (Continuation) null));
    }

    public static final void invokedCallback(View view, OnBackInvokedDispatcher onBackInvokedDispatcher, OnBackInvokedCallback onBackInvokedCallback) {
        if (view != null) {
            invokedCallback.1.1 r0 = new invokedCallback.1.1();
            r0.$onBackInvokedDispatcher = onBackInvokedDispatcher;
            r0.$onBackInvokedCallback = onBackInvokedCallback;
            r0.$it = view;
            VarHandle.storeStoreFence();
            view.addOnAttachStateChangeListener(r0);
            if (!view.isAttachedToWindow() || onBackInvokedDispatcher == null) {
                return;
            }
            onBackInvokedDispatcher.registerOnBackInvokedCallback(0, onBackInvokedCallback);
        }
    }

    public static final boolean isEnableExternalDisplay(Context context, int i) {
        DisplayManager displayManager;
        Display display;
        INSTANCE.getClass();
        return (!((Boolean) isSupportExternalTaskBar$delegate.getValue()).booleanValue() || i == 0 || (displayManager = (DisplayManager) context.getSystemService(DisplayManager.class)) == null || (display = displayManager.getDisplay(i)) == null || display.getType() != 2) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002a  */
    /* JADX WARN: Code duplicated, block: B:14:0x0030 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x0032  */
    /* JADX WARN: Code duplicated, block: B:18:0x003c  */
    /* JADX WARN: Code duplicated, block: B:20:0x0042  */
    public static final boolean isEnableOcrScreen() {
        boolean z;
        if (isSupportOcrScreen()) {
            OcrScreenCompatVersion.INSTANCE.getClass();
            if (OcrScreenCompatVersion.isMetaDataSupport) {
                boolean zIsVirtualKeyMode = NavBarUtils.isVirtualKeyMode();
                boolean zIsGestureSideMode = NavBarUtils.isGestureSideMode();
                if (zIsVirtualKeyMode) {
                    OplusHomeButtonWakeUpOcrObserver.INSTANCE.getClass();
                    if (OplusHomeButtonWakeUpOcrObserver.oplusHomeButtonWakeUpOcrState != 1) {
                        if (isHomeButtonEnableOcrDialog()) {
                            if (zIsGestureSideMode) {
                                OplusHomeHandleWakeUpOcrObserver.INSTANCE.getClass();
                                if (OplusHomeHandleWakeUpOcrObserver.OPLUS_HOME_HANDLE_WAKE_UP_OCR == 1) {
                                    z = isHomeHandleEnableOcrDialog();
                                }
                            } else if (isHomeHandleEnableOcrDialog()) {
                            }
                        }
                    }
                } else if (isHomeButtonEnableOcrDialog()) {
                    if (zIsGestureSideMode) {
                        OplusHomeHandleWakeUpOcrObserver.INSTANCE.getClass();
                        if (OplusHomeHandleWakeUpOcrObserver.OPLUS_HOME_HANDLE_WAKE_UP_OCR == 1) {
                            if (isHomeHandleEnableOcrDialog()) {
                            }
                        }
                    } else if (isHomeHandleEnableOcrDialog()) {
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("isEnableOcrScreen: result = ", "OplusNavBarUtils-->", "SystemUi--NavBar", z);
                }
                return z;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OplusNavBarUtils-->isEnableOcrScreen: not support OCR or metadata");
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0020  */
    public static final boolean isFoldOpenNavBar(Context context) {
        boolean z;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportNavBarButtonsPosition()) {
            if (1 == TaskbarSettingsOberver.INSTANCE.getTaskbarEnable()) {
                NavBarFeatureOption.INSTANCE.getClass();
                if (NavBarFeatureOption.sIsSupportTaskbar) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            mTaskbarEnable = z;
            boolean zIsOpenOrHalfOpen = z ? DeviceStateController.getInstance().isOpenOrHalfOpen() : DeviceUtil.shouldUseLargeFoldConfig(context.getResources());
            mFoldState = zIsOpenOrHalfOpen;
            LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("isFoldOpenNavBar taskbarEnable=", ",foldState=", mTaskbarEnable, zIsOpenOrHalfOpen), null, 8);
            if (mFoldState || FeatureOption.isTablet()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0020  */
    public static final boolean isFoldOpenNavigationLayout(Context context) {
        boolean z;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportNavBarButtonsPosition()) {
            if (1 == TaskbarSettingsOberver.INSTANCE.getTaskbarEnable()) {
                NavBarFeatureOption.INSTANCE.getClass();
                if (NavBarFeatureOption.sIsSupportTaskbar) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            mTaskbarEnable = z;
            boolean zIsOpenOrHalfOpen = z ? DeviceStateController.getInstance().isOpenOrHalfOpen() : DeviceUtil.shouldUseLargeFoldConfig(context.getResources());
            mFoldState = zIsOpenOrHalfOpen;
            LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("isFoldOpenNavigationLayout() taskbarEnable=", ",foldState=", mTaskbarEnable, zIsOpenOrHalfOpen), null, 8);
            if (mFoldState || FeatureOption.isTablet()) {
                NavButtonsPositionObserver.INSTANCE.getClass();
                if (NavButtonsPositionObserver.navButtonPosition == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isHomeButtonEnableOcrDialog() {
        if (!NavBarUtils.isVirtualKeyMode()) {
            return false;
        }
        OplusHomeButtonWakeUpOcrDialogObserver.INSTANCE.getClass();
        if (OplusHomeButtonWakeUpOcrDialogObserver.oplusHomeButtonWakeUpOcrDialogState != 1) {
            return false;
        }
        OplusHomeButtonWakeUpOcrObserver.INSTANCE.getClass();
        return OplusHomeButtonWakeUpOcrObserver.oplusHomeButtonWakeUpOcrState == 0;
    }

    public static final boolean isHomeHandleEnableOcrDialog() {
        if (!NavBarUtils.isGestureSideMode() && !NavBarUtils.isGestureUpMode()) {
            return false;
        }
        OplusHomeHandleWakeUpOcrDialogObserver.INSTANCE.getClass();
        if (OplusHomeHandleWakeUpOcrDialogObserver.OPLUS_HOME_HANDLE_WAKE_UP_OCR_Dialog != 1) {
            return false;
        }
        OplusHomeHandleWakeUpOcrObserver.INSTANCE.getClass();
        return OplusHomeHandleWakeUpOcrObserver.OPLUS_HOME_HANDLE_WAKE_UP_OCR == 0;
    }

    public static final boolean isSupportOcrScreen() {
        NavBarFeatureOption.INSTANCE.getClass();
        if (NavBarFeatureOption.sIsSupportTaskbar) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return !FeatureOption.isExpRegion();
    }

    public static final boolean isTrackpadFourFingerSwipe(MotionEvent motionEvent) {
        return motionEvent.getClassification() == 4 && motionEvent.getAxisValue(53) == 4.0f;
    }

    public static final boolean isTwoFingerSwipeTrackpadEvent(MotionEvent motionEvent) {
        return motionEvent.getSource() == 8194 && motionEvent.getToolType(0) == 1 && motionEvent.getClassification() == 3;
    }

    public static final boolean isEnableExternalDisplay(Display display) {
        if (display.getDisplayId() == 0) {
            return false;
        }
        INSTANCE.getClass();
        return ((Boolean) isSupportExternalTaskBar$delegate.getValue()).booleanValue() && display.getType() == 2;
    }
}
