package z1;

import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.text.MeasuredText;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f7115a = new h();

    public final void a(Canvas canvas) {
        canvas.disableZ();
    }

    public final void b(Canvas canvas, int i5, BlendMode blendMode) {
        canvas.drawColor(i5, blendMode);
    }

    public final void c(Canvas canvas, long j5) {
        canvas.drawColor(j5);
    }

    public final void d(Canvas canvas, long j5, BlendMode blendMode) {
        canvas.drawColor(j5, blendMode);
    }

    public final void e(Canvas canvas, RectF rectF, float f5, float f6, RectF rectF2, float f7, float f8, Paint paint) {
        canvas.drawDoubleRoundRect(rectF, f5, f6, rectF2, f7, f8, paint);
    }

    public final void f(Canvas canvas, RectF rectF, float[] fArr, RectF rectF2, float[] fArr2, Paint paint) {
        canvas.drawDoubleRoundRect(rectF, fArr, rectF2, fArr2, paint);
    }

    public final void g(Canvas canvas, RenderNode renderNode) {
        canvas.drawRenderNode(renderNode);
    }

    public final void h(Canvas canvas, MeasuredText measuredText, int i5, int i6, int i7, int i8, float f5, float f6, boolean z2, Paint paint) {
        canvas.drawTextRun(measuredText, i5, i6, i7, i8, f5, f6, z2, paint);
    }

    public final void i(Canvas canvas) {
        canvas.enableZ();
    }
}
