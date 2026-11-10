package com.oplus.systemui.preventmode;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPreventModeView extends RelativeLayout implements View.OnTouchListener {
    public LottieAnimationView mArrowView;
    public ImageView mPhone;
    public Resources mResources;
    public PreventModeSwipeView mSwipeCircle;
    public int mSwipeThresholdInVertical;

    public OplusPreventModeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public final void init() {
        KgdLogUtil.i("OplusPreventModeView", "init");
        this.mResources = ((RelativeLayout) this).mContext.getResources();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnTouchListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnTouchListener(null);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        KgdLogUtil.i("OplusPreventModeView", "onFinishInflate");
        this.mPhone = (ImageView) findViewById(2131364625);
        this.mArrowView = findViewById(2131364621);
        this.mSwipeCircle = (PreventModeSwipeView) findViewById(2131364622);
        this.mSwipeThresholdInVertical = this.mResources.getDimensionPixelOffset(2131171470);
        this.mSwipeCircle.setMaxSwipeDistanceInHorizontal(this.mResources.getDimensionPixelOffset(2131171469));
        this.mSwipeCircle.setMaxSwipeDistanceInVertical(this.mSwipeThresholdInVertical);
        this.mSwipeCircle.setOplusPreventModeView(this);
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        if ((getAlpha() == 1.0f && ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusPreventMode().isPreventModeViewShown()) || ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().isTouchDownNow()) {
            return false;
        }
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusPreventMode().updateLayoutParams$1();
        this.mArrowView.playAnimation();
        return true;
    }

    public final void updateArrowAlpha(float f) {
        if (this.mArrowView.isAnimating()) {
            this.mArrowView.cancelAnimation();
            this.mArrowView.setFrame(0);
        }
        this.mArrowView.setImageAlpha(255 - ((int) ((f / this.mSwipeThresholdInVertical) * 255.0f)));
    }

    public OplusPreventModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public OplusPreventModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public OplusPreventModeView(Context context) {
        super(context);
        init();
    }
}
