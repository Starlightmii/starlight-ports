package com.oplus.systemui.statusbar.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.widget.TextView;
import com.android.keyguard.CarrierText;
import com.android.keyguard.CarrierTextController;
import com.android.keyguard.CarrierTextManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.CarrierTextManagerLogger;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.qs.OplusQSCarrierTextController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatCarrierTextController extends CarrierTextController implements OplusQSCarrierTextController.CarrierTextCallback, ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatCarrierTextController";
    private ValueAnimator carrierTextAnimator;
    private final ConfigurationController configurationController;
    private final KeyguardLogger keyguardLogger;
    private final CarrierTextManagerLogger logger;
    private final OplusQSCarrierTextController oplusQSCarrierTextController;
    public int statusBarState;
    private final SysuiStatusBarStateController statusBarStateController;
    private final OplusStatCarrierTextController$statusBarStateListener$1 statusBarStateListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.statusbar.widget.OplusStatCarrierTextController$statusBarStateListener$1] */
    public OplusStatCarrierTextController(OplusQSCarrierTextController oplusQSCarrierTextController, ConfigurationController configurationController, CarrierText carrierText, CarrierTextManager.Builder builder, KeyguardUpdateMonitor keyguardUpdateMonitor, SysuiStatusBarStateController sysuiStatusBarStateController, CarrierTextManagerLogger carrierTextManagerLogger, KeyguardLogger keyguardLogger) {
        super(carrierText, builder, keyguardUpdateMonitor);
        this.oplusQSCarrierTextController = oplusQSCarrierTextController;
        this.configurationController = configurationController;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.logger = carrierTextManagerLogger;
        this.keyguardLogger = keyguardLogger;
        this.statusBarState = -1;
        this.statusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.oplus.systemui.statusbar.widget.OplusStatCarrierTextController$statusBarStateListener$1
            public final void onStateChanged(int i) {
                super.onStateChanged(i);
                OplusStatCarrierTextController oplusStatCarrierTextController = this.this$0;
                oplusStatCarrierTextController.statusBarState = i;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onStateChanged ", oplusStatCarrierTextController.statusBarState, i, " -> ", "OplusStatCarrierTextController-->"));
                }
                OplusStatCarrierTextController oplusStatCarrierTextController2 = this.this$0;
                if (oplusStatCarrierTextController2.statusBarState == 2) {
                    OplusStatCarrierTextController.access$animateDisappear(oplusStatCarrierTextController2);
                } else {
                    OplusStatCarrierTextController.access$animateAppear(oplusStatCarrierTextController2);
                }
            }
        };
    }

    public static final void access$animateAppear(final OplusStatCarrierTextController oplusStatCarrierTextController) {
        ValueAnimator valueAnimator = oplusStatCarrierTextController.carrierTextAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
        }
        ValueAnimator valueAnimator2 = oplusStatCarrierTextController.carrierTextAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        oplusStatCarrierTextController.carrierTextAnimator = null;
        if (((ViewController) oplusStatCarrierTextController).mView.getVisibility() == 0 && ((ViewController) oplusStatCarrierTextController).mView.getAlpha() == 1.0f) {
            LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, "no animateAppear required", null, 8);
            return;
        }
        if (((ViewController) oplusStatCarrierTextController).mView.getVisibility() == 4 || ((ViewController) oplusStatCarrierTextController).mView.getAlpha() == 0.0f) {
            ((ViewController) oplusStatCarrierTextController).mView.setVisibility(0);
            ((ViewController) oplusStatCarrierTextController).mView.setAlpha(0.0f);
        }
        CarrierText carrierText = ((ViewController) oplusStatCarrierTextController).mView;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(carrierText, (Property<CarrierText, Float>) View.ALPHA, carrierText.getAlpha(), 1.0f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
        objectAnimatorOfFloat.setStartDelay(100L);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.widget.OplusStatCarrierTextController$animateAppear$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                this.this$0.showCarrierInternal();
            }
        });
        oplusStatCarrierTextController.carrierTextAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.start();
    }

    public static final void access$animateDisappear(final OplusStatCarrierTextController oplusStatCarrierTextController) {
        ValueAnimator valueAnimator = oplusStatCarrierTextController.carrierTextAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
        }
        ValueAnimator valueAnimator2 = oplusStatCarrierTextController.carrierTextAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        oplusStatCarrierTextController.carrierTextAnimator = null;
        if (((ViewController) oplusStatCarrierTextController).mView.getVisibility() != 0 || ((ViewController) oplusStatCarrierTextController).mView.getAlpha() == 0.0f) {
            LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, "no animateDisappear required", null, 8);
            oplusStatCarrierTextController.hideCarrierInternal();
            return;
        }
        CarrierText carrierText = ((ViewController) oplusStatCarrierTextController).mView;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(carrierText, (Property<CarrierText, Float>) View.ALPHA, carrierText.getAlpha(), 0.0f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.widget.OplusStatCarrierTextController$animateDisappear$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                this.this$0.hideCarrierInternal();
            }
        });
        oplusStatCarrierTextController.carrierTextAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.start();
    }

    public final void hideCarrierInternal() {
        this.keyguardLogger.log(LogUtil.TAG_KEYGUARD, LogLevel.DEBUG, "hideCarrierInternal", (Throwable) null);
        CarrierText carrierText = ((ViewController) this).mView;
        if (carrierText != null) {
            carrierText.setVisibility(4);
        }
        CarrierText carrierText2 = ((ViewController) this).mView;
        if (carrierText2 != null) {
            carrierText2.setAlpha(0.0f);
        }
    }

    public final void onDensityOrFontScaleChanged() {
        FontSizeUtils.updateFontSize((TextView) ((ViewController) this).mView, 2131173088);
    }

    public final void onInit() {
        super.onInit();
        this.oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        sysuiStatusBarStateController.addCallback(this.statusBarStateListener);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(sysuiStatusBarStateController.getState(), "onInit state: ", "OplusStatCarrierTextController-->", "SystemUi--Keyguard");
        }
        if (this.statusBarState == 2) {
            hideCarrierInternal();
        } else {
            showCarrierInternal();
        }
        ((OplusStatCarrierText) ((ViewController) this).mView).setLogger(this.logger);
    }

    public final void onViewAttached() {
        ((ViewController) this).mView.setSelected(true);
        this.oplusQSCarrierTextController.setListening(this);
        this.configurationController.addCallback(this);
    }

    public final void onViewDetached() {
        ((ViewController) this).mView.setSelected(false);
        this.oplusQSCarrierTextController.removeCallback(this);
        this.configurationController.removeCallback(this);
    }

    @Override // com.oplus.systemui.qs.OplusQSCarrierTextController.CarrierTextCallback
    public void setVisible(boolean z) {
        CarrierText carrierText = ((ViewController) this).mView;
        if (z) {
            carrierText.setVisibility(0);
        } else {
            carrierText.setText("");
            ((ViewController) this).mView.setVisibility(8);
        }
    }

    public final void showCarrierInternal() {
        this.keyguardLogger.log(LogUtil.TAG_KEYGUARD, LogLevel.DEBUG, "showCarrierInternal", (Throwable) null);
        CarrierText carrierText = ((ViewController) this).mView;
        if (carrierText != null) {
            carrierText.setVisibility(0);
        }
        CarrierText carrierText2 = ((ViewController) this).mView;
        if (carrierText2 != null) {
            carrierText2.setAlpha(1.0f);
        }
    }

    @Override // com.oplus.systemui.qs.OplusQSCarrierTextController.CarrierTextCallback
    public void updateCarrierInfo(OplusQSCarrierTextController.OplusCarrierTextCallbackInfo oplusCarrierTextCallbackInfo) {
        if (oplusCarrierTextCallbackInfo.carrierText == null || Intrinsics.areEqual(((ViewController) this).mView.getText(), oplusCarrierTextCallbackInfo.carrierText)) {
            return;
        }
        ((ViewController) this).mView.setText(oplusCarrierTextCallbackInfo.carrierText);
    }
}
