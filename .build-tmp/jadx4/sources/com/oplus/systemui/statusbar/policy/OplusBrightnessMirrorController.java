package com.oplus.systemui.statusbar.policy;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.qs.QSBrightness;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.systemui.common.OplusSwitchCameraPreviewStartable;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusBrightnessMirrorController extends BrightnessMirrorController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusBrightnessMirrorController";
    private final Handler alphaHandler;
    private final QSFragmentHelper.BeginClosingCallback beginClosingCallback;
    public boolean hasAddCallback;
    public boolean isPrimaryBouncerShowing;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final OplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback;
    private View originView;
    private final OplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1 primaryBouncerShowingChangedCallback;
    private QSBrightness.ShowMirrorListener showMirrorListener;
    public final OplusSwitchCameraPreviewStartable switchCameraPreviewStartable;
    private final WeakReference<QSFragmentHelper.BeginClosingCallback> weakReferenceBeginClosingCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusBrightnessMirrorController(NotificationShadeWindowView notificationShadeWindowView, final ShadeViewController shadeViewController, final NotificationShadeDepthController notificationShadeDepthController, BrightnessSliderController.Factory factory, final Consumer<Boolean> consumer) {
        super(notificationShadeWindowView, shadeViewController, notificationShadeDepthController, factory, consumer);
        this.keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        this.switchCameraPreviewStartable = (OplusSwitchCameraPreviewStartable) DependencyEx.sDependency.getDependency(OplusSwitchCameraPreviewStartable.class);
        OplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1 oplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1 = new OplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1();
        oplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardUpdateMonitorCallback = oplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1;
        this.keyguardStateController = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
        OplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1 oplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1 = new OplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1();
        oplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.primaryBouncerShowingChangedCallback = oplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1;
        final Looper mainLooper = Looper.getMainLooper();
        this.alphaHandler = new Handler(mainLooper) { // from class: com.oplus.systemui.statusbar.policy.OplusBrightnessMirrorController$alphaHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                OplusBrightnessMirrorController oplusBrightnessMirrorController = this.this$0;
                KeyguardUpdateMonitor keyguardUpdateMonitor = oplusBrightnessMirrorController.keyguardUpdateMonitor;
                int i = message.what;
                int i2 = 0;
                int i3 = 1;
                if (i != 0) {
                    if (i == 1) {
                        oplusBrightnessMirrorController.onMirrorHide();
                        keyguardUpdateMonitor.removeCallback(oplusBrightnessMirrorController.keyguardUpdateMonitorCallback);
                        ((BrightnessMirrorController) oplusBrightnessMirrorController).mVisibilityCallback.accept(Boolean.FALSE);
                        int i4 = message.arg1;
                        boolean z = message.arg2 != 0;
                        if (i4 == -1) {
                            oplusBrightnessMirrorController.handleMirrorInvisible();
                        } else if (oplusBrightnessMirrorController.showMirrorListener != null) {
                            QSBrightness.ShowMirrorListener showMirrorListener = oplusBrightnessMirrorController.showMirrorListener;
                            if (showMirrorListener != null) {
                                OplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0 oplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0 = new OplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0(i3);
                                oplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0.f$0 = oplusBrightnessMirrorController;
                                VarHandle.storeStoreFence();
                                showMirrorListener.onShowMirror(i4, z, oplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0);
                            }
                        } else {
                            ((BrightnessMirrorController) oplusBrightnessMirrorController).mNotificationPanel.setAlpha(i4, z);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i4, "alphaHandler HIDE_MIRROR alpha:", "OplusBrightnessMirrorController-->", "SystemUi--Notification");
                        }
                        ((BrightnessMirrorController) oplusBrightnessMirrorController).mDepthController.setBrightnessMirrorVisible(false);
                        return;
                    }
                    return;
                }
                oplusBrightnessMirrorController.onMirrorShow();
                if (keyguardUpdateMonitor.mKeyguardShowing && !keyguardUpdateMonitor.mKeyguardOccluded) {
                    keyguardUpdateMonitor.registerCallback(oplusBrightnessMirrorController.keyguardUpdateMonitorCallback);
                    KeyguardStateControllerImpl keyguardStateControllerImpl = oplusBrightnessMirrorController.keyguardStateController;
                    OplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1 oplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$2 = oplusBrightnessMirrorController.primaryBouncerShowingChangedCallback;
                    KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
                    keyguardStateControllerImpl2.getClass();
                    keyguardStateControllerImpl2.addCallback(oplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$2);
                }
                ((BrightnessMirrorController) oplusBrightnessMirrorController).mToggleSliderController.updateSliderCornerRadius();
                IBrightnessSliderController.updateSliderColor$default(((BrightnessMirrorController) oplusBrightnessMirrorController).mToggleSliderController, false, (QSSpecialModeUpdateMonitor.ColorType) null, 3);
                ((BrightnessMirrorController) oplusBrightnessMirrorController).mBrightnessMirror.setVisibility(0);
                Object obj = message.obj;
                View view = obj instanceof View ? (View) obj : null;
                if (view != null) {
                    oplusBrightnessMirrorController.setLocationAndSize(view);
                }
                View originView = oplusBrightnessMirrorController.getOriginView();
                if (originView != null) {
                    originView.setAlpha(0.0f);
                }
                consumer.accept(Boolean.TRUE);
                int i5 = message.arg1;
                if (oplusBrightnessMirrorController.showMirrorListener != null) {
                    QSBrightness.ShowMirrorListener showMirrorListener2 = oplusBrightnessMirrorController.showMirrorListener;
                    if (showMirrorListener2 != null) {
                        OplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0 oplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda1 = new OplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda0(i2);
                        oplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda1.f$0 = oplusBrightnessMirrorController;
                        VarHandle.storeStoreFence();
                        showMirrorListener2.onShowMirror(i5, true, oplusBrightnessMirrorController$alphaHandler$1$$ExternalSyntheticLambda1);
                    }
                } else {
                    shadeViewController.setAlpha(i5, true);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i5, "alphaHandler SHOW_MIRROR alpha:", "OplusBrightnessMirrorController-->", "SystemUi--Notification");
                }
                notificationShadeDepthController.setBrightnessMirrorVisible(true);
            }
        };
        OplusBrightnessMirrorController$beginClosingCallback$1 oplusBrightnessMirrorController$beginClosingCallback$1 = new OplusBrightnessMirrorController$beginClosingCallback$1();
        oplusBrightnessMirrorController$beginClosingCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.beginClosingCallback = oplusBrightnessMirrorController$beginClosingCallback$1;
        this.weakReferenceBeginClosingCallback = new WeakReference<>(oplusBrightnessMirrorController$beginClosingCallback$1);
    }

    public final View getOriginView() {
        return this.originView;
    }

    public void handleMirrorInvisible() {
        ((BrightnessMirrorController) this).mBrightnessMirror.setVisibility(4);
        View view = this.originView;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        this.originView = null;
    }

    public final void hideMirror() {
        boolean z = !this.isPrimaryBouncerShowing;
        hideMirrorInner(z, z);
    }

    public final void hideMirrorDirect() {
        if (Intrinsics.areEqual(Thread.currentThread(), Looper.getMainLooper().getThread())) {
            handleMirrorInvisible();
        }
        hideMirrorInner(false, false);
    }

    public final void hideMirrorInner(boolean z, boolean z2) {
        this.alphaHandler.removeCallbacksAndMessages(null);
        Message message = new Message();
        message.what = 1;
        message.arg1 = z ? 255 : -1;
        message.arg2 = z2 ? 1 : 0;
        this.alphaHandler.sendMessage(message);
        this.switchCameraPreviewStartable.setIsShowMirror(false);
    }

    public void onMirrorHide() {
    }

    public void onMirrorShow() {
    }

    public void setLocationAndSize(View view) {
        int[] iArr = ((BrightnessMirrorController) this).mInt2Cache;
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationX(0.0f);
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationY(0.0f);
        ((BrightnessMirrorController) this).mBrightnessMirror.getLocationInWindow(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationX(i - i3);
        ((BrightnessMirrorController) this).mBrightnessMirror.setTranslationY(i2 - i4);
        int measuredWidth = view.getMeasuredWidth();
        ViewGroup.LayoutParams layoutParams = ((BrightnessMirrorController) this).mBrightnessMirror.getLayoutParams();
        if (measuredWidth != layoutParams.width) {
            layoutParams.width = measuredWidth;
            ((BrightnessMirrorController) this).mBrightnessMirror.setLayoutParams(layoutParams);
        }
    }

    public IBrightnessSliderController setMirrorLayout() {
        IBrightnessSliderController iBrightnessSliderControllerCreate = ((BrightnessMirrorController) this).mToggleSliderFactory.create(((BrightnessMirrorController) this).mBrightnessMirror.getContext(), ((BrightnessMirrorController) this).mBrightnessMirror);
        iBrightnessSliderControllerCreate.setIsMirror(true);
        iBrightnessSliderControllerCreate.doInit();
        ((BrightnessMirrorController) this).mBrightnessMirror.addView(iBrightnessSliderControllerCreate.getRootView(), -1, -2);
        return iBrightnessSliderControllerCreate;
    }

    public final void setOriginView(View view) {
        this.originView = view;
    }

    public final void setShowMirrorListener(QSBrightness.ShowMirrorListener showMirrorListener) {
        this.showMirrorListener = showMirrorListener;
    }

    public void showMirror(View view) {
        super.showMirror(view);
        if (!this.hasAddCallback) {
            QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
            WeakReference<QSFragmentHelper.BeginClosingCallback> weakReference = this.weakReferenceBeginClosingCallback;
            if (!qSFragmentHelper.mCallbacks.contains(weakReference)) {
                qSFragmentHelper.mCallbacks.add(weakReference);
            }
            this.hasAddCallback = true;
        }
        this.isPrimaryBouncerShowing = false;
        this.alphaHandler.removeCallbacksAndMessages(null);
        Message message = new Message();
        message.what = 0;
        message.obj = view;
        message.arg1 = 0;
        this.alphaHandler.sendMessageDelayed(message, 300L);
        this.switchCameraPreviewStartable.setIsShowMirror(true);
    }

    public void updateResources() {
        Resources resources = ((BrightnessMirrorController) this).mBrightnessMirror.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131171546);
        View rootView = ((BrightnessMirrorController) this).mToggleSliderController.getRootView();
        rootView.getClass();
        rootView.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(2131165744);
        View view = ((BrightnessMirrorController) this).mToggleSliderController.getView();
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        if (layoutParams == null || dimensionPixelSize2 == layoutParams.height) {
            return;
        }
        layoutParams.height = dimensionPixelSize2;
        view.setLayoutParams(layoutParams);
    }

    public final void showMirror() {
        showMirror(null);
    }
}
