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
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$$ExternalSyntheticOutline0;
import com.android.keyguard.OplusLockIconView$$ExternalSyntheticOutline0;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
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
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okio.Path$$ExternalSyntheticBUOutline0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class OplusNavBarUtils {
    private static final String TAG = "OplusNavBarUtils";
    public static boolean mFoldState;
    public static boolean mTaskbarEnable;
    public static final OplusNavBarUtils INSTANCE = new OplusNavBarUtils();
    private static final Lazy isSupportExternalTaskBar$delegate = LazyKt__LazyJVMKt.lazy(new OplusNavBarUtils$$ExternalSyntheticLambda0());

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.utils.OplusNavBarUtils$getPackageIcon$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    /* JADX INFO: loaded from: classes9.dex */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $callback;
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$pkg = str;
            this.$callback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$pkg, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationResolverEx notificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
                String str = this.$pkg;
                this.label = 1;
                obj = notificationResolverEx.getPackageIcon(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$$ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$callback.invoke((Drawable) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.utils.OplusNavBarUtils$getPackageIconBlock$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    /* JADX INFO: loaded from: classes9.dex */
    final class C20741 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C20741(String str, Continuation continuation) {
            super(2, continuation);
            this.$pkg = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new C20741(this.$pkg, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((C20741) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                Path$$ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            NotificationResolverEx notificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
            String str = this.$pkg;
            this.label = 1;
            Object packageIcon = notificationResolverEx.getPackageIcon(str, this);
            return packageIcon == coroutine_suspended ? coroutine_suspended : packageIcon;
        }
    }

    public static final void compatEdgeToEdge(Window window) {
        if (window != null) {
            window.setNavigationBarContrastEnforced(false);
            View decorView = window.getDecorView();
            if (decorView != null) {
                decorView.setOnApplyWindowInsetsListener(OplusNavBarUtils$compatEdgeToEdge$1$1.INSTANCE);
            }
        }
    }

    public static final void getPackageIcon(String str, Function1 function1) {
        BuildersKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new AnonymousClass1(str, function1, null), 3);
    }

    public static final Drawable getPackageIconBlock(String str) {
        return (Drawable) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new C20741(str, null));
    }

    public static final void invokedCallback(View view, OnBackInvokedDispatcher onBackInvokedDispatcher, OnBackInvokedCallback onBackInvokedCallback) {
        if (view != null) {
            OplusNavBarUtils$invokedCallback$1$1 oplusNavBarUtils$invokedCallback$1$1 = new OplusNavBarUtils$invokedCallback$1$1();
            oplusNavBarUtils$invokedCallback$1$1.$onBackInvokedDispatcher = onBackInvokedDispatcher;
            oplusNavBarUtils$invokedCallback$1$1.$onBackInvokedCallback = onBackInvokedCallback;
            oplusNavBarUtils$invokedCallback$1$1.$it = view;
            VarHandle.storeStoreFence();
            view.addOnAttachStateChangeListener(oplusNavBarUtils$invokedCallback$1$1);
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
                    OplusLockIconView$$ExternalSyntheticOutline0.m("isEnableOcrScreen: result = ", "OplusNavBarUtils-->", "SystemUi--NavBar", z);
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
            LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, PriorityNestedScrollConnection$$ExternalSyntheticOutline0.m("isFoldOpenNavBar taskbarEnable=", ",foldState=", mTaskbarEnable, zIsOpenOrHalfOpen), null, 8);
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
            LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, PriorityNestedScrollConnection$$ExternalSyntheticOutline0.m("isFoldOpenNavigationLayout() taskbarEnable=", ",foldState=", mTaskbarEnable, zIsOpenOrHalfOpen), null, 8);
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
