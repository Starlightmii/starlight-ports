package com.oplus.systemui.statusbar.notification.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.appcompat.widget.AppCompatButton;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCloseAllButton extends AppCompatButton {
    public static final Companion Companion = new Companion();
    public float currentScale;
    public boolean isNeedToDelayCancelScaleAnim;
    private final OplusCloseAllButton$op$1 op;
    private final Interpolator scaleAnimationInterpolator;
    private ValueAnimator scaleAnimator;
    private NotificationSpotLightDelegate.ClipSpec spotLightClipSpec;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusCloseAllButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scaleAnimationInterpolator = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
        this.currentScale = 1.0f;
        OplusCloseAllButton$op$1 oplusCloseAllButton$op$1 = new OplusCloseAllButton$op$1();
        this.op = oplusCloseAllButton$op$1;
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.CLOSE_ALL_BUTTON);
        setClipToOutline(true);
        setOutlineProvider(oplusCloseAllButton$op$1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final NotificationSpotLightDelegate.ClipSpec getSpotLightClipSpec() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        float f = height / 2.0f;
        return new NotificationSpotLightDelegate.ClipSpec(0, 0, width, height, f, f, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setScale(float f) {
        if (1.0f <= f) {
            f = 1.0f;
        }
        if (0.92f >= f) {
            f = 0.92f;
        }
        setScaleX(f);
        setScaleY(f);
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void draw(Canvas canvas) {
        super/*android.widget.Button*/.draw(canvas);
        NotificationSpotLightDelegate.Companion companion = NotificationSpotLightDelegate.Companion;
        draw.1 r1 = new draw.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        companion.draw(this, canvas, r1);
    }

    public final void executeScaleAnimator$1(boolean z) {
        this.isNeedToDelayCancelScaleAnim = false;
        ValueAnimator valueAnimator = this.scaleAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            boolean z2 = !z && ((float) valueAnimator.getCurrentPlayTime()) < ((float) valueAnimator.getDuration()) * 0.4f;
            this.isNeedToDelayCancelScaleAnim = z2;
            if (!z2) {
                valueAnimator.cancel();
            }
        }
        if (this.isNeedToDelayCancelScaleAnim) {
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(z ? 1.0f : this.currentScale, z ? 0.92f : 1.0f);
        this.scaleAnimator = valueAnimatorOfFloat;
        if (valueAnimatorOfFloat != null) {
            valueAnimatorOfFloat.setInterpolator(this.scaleAnimationInterpolator);
        }
        ValueAnimator valueAnimator2 = this.scaleAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.setDuration(z ? 200L : 340L);
        }
        ValueAnimator valueAnimator3 = this.scaleAnimator;
        if (valueAnimator3 != null) {
            executeScaleAnimator.1 r1 = new executeScaleAnimator.1();
            r1.this$0 = this;
            r1.$isPressed = z;
            VarHandle.storeStoreFence();
            valueAnimator3.addUpdateListener(r1);
        }
        ValueAnimator valueAnimator4 = this.scaleAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.start();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.widget.Button*/.onSizeChanged(i, i2, i3, i4);
        this.spotLightClipSpec = getSpotLightClipSpec();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            if (((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isAnimRunning()) {
                return super/*android.widget.Button*/.onTouchEvent(motionEvent);
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                executeScaleAnimator$1(true);
            } else if (action == 1 || action == 3) {
                executeScaleAnimator$1(false);
            }
        }
        return super/*android.widget.Button*/.onTouchEvent(motionEvent);
    }

    public OplusCloseAllButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OplusCloseAllButton(Context context) {
        this(context, null);
    }
}
