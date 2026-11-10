package com.oplus.systemui.notification.row;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.android.systemui.statusbar.AlphaOptimizedImageView;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemImageView extends FrameLayout implements IOplusMenuRowItemView {
    private final AlphaOptimizedImageView imageView;

    public NotificationMenuRowItemImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        FrameLayout.inflate(context, 2131559313, this);
        this.imageView = findViewById(2131363424);
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.NOTIFICATION_MENU);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        NotificationSpotLightDelegate.Companion.draw(this, canvas, null);
    }

    public final AlphaOptimizedImageView getImageView() {
        return this.imageView;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void resetState() {
        updateAlpha(1.0f);
        updateBlurRatio(0.0f);
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void updateAlpha(float f) {
        this.imageView.getDrawable().setAlpha(MathKt.roundToInt(f * 255.0f));
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void updateBlurRatio(float f) {
        AlphaOptimizedImageView alphaOptimizedImageView = this.imageView;
        if (f <= 0.0f) {
            alphaOptimizedImageView.setRenderEffect(null);
        } else {
            alphaOptimizedImageView.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
        }
    }

    @Override // com.oplus.systemui.notification.row.IOplusMenuRowItemView
    public final void updateScale(float f) {
        this.imageView.setScaleX(f);
        this.imageView.setScaleY(f);
    }

    public NotificationMenuRowItemImageView(Context context) {
        this(context, null);
    }
}
