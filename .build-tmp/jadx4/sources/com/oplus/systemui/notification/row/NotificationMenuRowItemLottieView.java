package com.oplus.systemui.notification.row;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemLottieView extends FrameLayout implements IOplusMenuRowItemView {
    private final LottieAnimationView lottieView;

    public NotificationMenuRowItemLottieView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        FrameLayout.inflate(context, 2131559314, this);
        this.lottieView = findViewById(2131363807);
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.NOTIFICATION_MENU);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        NotificationSpotLightDelegate.Companion.draw(this, canvas, null);
    }

    public final LottieAnimationView getLottieView() {
        return this.lottieView;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void resetState() {
        updateAlpha(1.0f);
        updateBlurRatio(0.0f);
        updateLottieProgress(0.0f);
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void updateAlpha(float f) {
        Drawable drawable = this.lottieView.getDrawable();
        if (drawable != null) {
            drawable.setAlpha(MathKt.roundToInt(f * 255.0f));
        }
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void updateBlurRatio(float f) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (f <= 0.0f) {
            lottieAnimationView.setRenderEffect(null);
        } else {
            lottieAnimationView.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
        }
    }

    public final void updateLottieProgress(float f) {
        if (this.lottieView.getProgress() == f) {
            return;
        }
        this.lottieView.setProgress(f);
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void updateScale(float f) {
        this.lottieView.setScaleX(f);
        this.lottieView.setScaleY(f);
    }

    public NotificationMenuRowItemLottieView(Context context) {
        this(context, null);
    }
}
