package com.oplusos.systemui.common.blurability.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaskDrawable extends Drawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MaskDrawable";
    private final BlurConfig blurConfig;
    private final Paint maskPaint;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MaskDrawable(BlurConfig blurConfig) {
        this.blurConfig = blurConfig;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        this.maskPaint = paint;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.maskPaint.setColor(this.blurConfig.getBlurColor());
        if (this.maskPaint.getAlpha() != 0) {
            canvas.drawRect(getBounds(), this.maskPaint);
        }
        if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "MaskDrawable-->".concat("[draw] " + this));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public String toString() {
        return "MaskDrawable{ maskColor=" + Integer.toHexString(this.blurConfig.getBlurColor()) + " }";
    }
}
