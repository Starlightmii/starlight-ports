package com.oplus.systemui.qs.media;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GradientFadeImageView extends AppCompatImageView {
    public static final Companion Companion = new Companion();
    public final int fadeHeightPx;
    private final Paint fadePaint;
    private LinearGradient gradientShader;
    public final float middlePos;
    private final PorterDuffXfermode xfermode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GradientFadeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint(1);
        this.fadePaint = paint;
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        this.xfermode = porterDuffXfermode;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131171046);
        this.fadeHeightPx = dimensionPixelSize;
        this.middlePos = dimensionPixelSize * 0.3f;
        paint.setXfermode(porterDuffXfermode);
        setLayerType(2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        if (this.gradientShader == null) {
            super/*android.widget.ImageView*/.onDraw(canvas);
            return;
        }
        int iSaveLayer = canvas.saveLayer(-2.0f, -2.0f, getWidth() + 2.0f, getHeight() + 2.0f, null);
        super/*android.widget.ImageView*/.onDraw(canvas);
        canvas.drawRect(-2.0f, -2.0f, getWidth() + 2.0f, getHeight() + 2.0f, this.fadePaint);
        canvas.restoreToCount(iSaveLayer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.widget.ImageView*/.onSizeChanged(i, i2, i3, i4);
        if (i <= 0 || i2 <= 0) {
            return;
        }
        float f = i2;
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, f, new int[]{-16777216, -16777216, 0, 0}, new float[]{0.0f, (f - this.fadeHeightPx) / f, (f - this.middlePos) / f, 1.0f}, Shader.TileMode.CLAMP);
        this.gradientShader = linearGradient;
        this.fadePaint.setShader(linearGradient);
    }

    public GradientFadeImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ GradientFadeImageView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public GradientFadeImageView(Context context) {
        this(context, null, 0, 6, null);
    }
}
