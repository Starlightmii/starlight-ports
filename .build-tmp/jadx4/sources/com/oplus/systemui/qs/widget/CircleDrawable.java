package com.oplus.systemui.qs.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CircleDrawable extends BitmapDrawable {
    private final Paint drawPaint;

    public CircleDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
        Paint paint = new Paint();
        this.drawPaint = paint;
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int width = getBitmap().getWidth();
        int height = getBitmap().getHeight();
        int i = width / 2;
        int i2 = height / 2;
        int iMin = Math.min(i, i2);
        int i3 = iMin + iMin;
        if (width <= i3) {
            i = iMin;
        }
        if (height <= i3) {
            i2 = iMin;
        }
        canvas.drawCircle(i, i2, iMin, this.drawPaint);
    }
}
