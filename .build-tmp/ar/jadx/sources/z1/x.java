package z1;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends Canvas {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f7150a;

    @Override // android.graphics.Canvas
    public final boolean clipOutPath(Path path) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return f.f7114a.a(canvas, path);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(float f5, float f6, float f7, float f8) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return f.f7114a.b(canvas, f5, f6, f7, f8);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(int i5, int i6, int i7, int i8) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return f.f7114a.c(canvas, i5, i6, i7, i8);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(Rect rect) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return f.f7114a.d(canvas, rect);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(RectF rectF) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return f.f7114a.e(canvas, rectF);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipPath(Path path) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipPath(path);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipPath(Path path, Region.Op op) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipPath(path, op);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(float f5, float f6, float f7, float f8) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(f5, f6, f7, f8);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(float f5, float f6, float f7, float f8, Region.Op op) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(f5, f6, f7, f8, op);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(int i5, int i6, int i7, int i8) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(i5, i6, i7, i8);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(Rect rect) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(rect);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(Rect rect, Region.Op op) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(rect, op);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(RectF rectF) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(rectF);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(RectF rectF, Region.Op op) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.clipRect(rectF, op);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void concat(Matrix matrix) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.concat(matrix);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void disableZ() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.a(canvas);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawARGB(int i5, int i6, int i7, int i8) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawARGB(i5, i6, i7, i8);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawArc(float f5, float f6, float f7, float f8, float f9, float f10, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawArc(f5, f6, f7, f8, f9, f10, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawArc(RectF rectF, float f5, float f6, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawArc(rectF, f5, f6, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, matrix, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Rect rect, Rect rect2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, rect, rect2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Rect rect, RectF rectF, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, rect, rectF, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(int[] iArr, int i5, int i6, float f5, float f6, int i7, int i8, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmap(iArr, i5, i6, f5, f6, i7, i8, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(int[] iArr, int i5, int i6, int i7, int i8, int i9, int i10, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmap(iArr, i5, i6, i7, i8, i9, i10, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmapMesh(Bitmap bitmap, int i5, int i6, float[] fArr, int i7, int[] iArr, int i8, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawBitmapMesh(bitmap, i5, i6, fArr, i7, iArr, i8, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawCircle(float f5, float f6, float f7, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawCircle(f5, f6, f7, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawColor(i5);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i5, BlendMode blendMode) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.b(canvas, i5, blendMode);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i5, PorterDuff.Mode mode) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawColor(i5, mode);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(long j5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.c(canvas, j5);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(long j5, BlendMode blendMode) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.d(canvas, j5, blendMode);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawDoubleRoundRect(RectF rectF, float f5, float f6, RectF rectF2, float f7, float f8, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.e(canvas, rectF, f5, f6, rectF2, f7, f8, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawDoubleRoundRect(RectF rectF, float[] fArr, RectF rectF2, float[] fArr2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.f(canvas, rectF, fArr, rectF2, fArr2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawGlyphs(int[] iArr, int i5, float[] fArr, int i6, int i7, Font font, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            j.f7117a.a(canvas, iArr, i5, fArr, i6, i7, font, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLine(float f5, float f6, float f7, float f8, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawLine(f5, f6, f7, f8, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLines(float[] fArr, int i5, int i6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawLines(fArr, i5, i6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLines(float[] fArr, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawLines(fArr, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawOval(float f5, float f6, float f7, float f8, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawOval(f5, f6, f7, f8, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawOval(RectF rectF, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawOval(rectF, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPaint(Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPaint(paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPatch(NinePatch ninePatch, Rect rect, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            j.f7117a.b(canvas, ninePatch, rect, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPatch(NinePatch ninePatch, RectF rectF, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            j.f7117a.c(canvas, ninePatch, rectF, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPath(Path path, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPath(path, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPicture(picture);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture, Rect rect) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPicture(picture, rect);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture, RectF rectF) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPicture(picture, rectF);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoint(float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPoint(f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoints(float[] fArr, int i5, int i6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPoints(fArr, i5, i6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoints(float[] fArr, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPoints(fArr, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPosText(String str, float[] fArr, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPosText(str, fArr, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPosText(char[] cArr, int i5, int i6, float[] fArr, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawPosText(cArr, i5, i6, fArr, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRGB(int i5, int i6, int i7) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawRGB(i5, i6, i7);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(float f5, float f6, float f7, float f8, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawRect(f5, f6, f7, f8, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(Rect rect, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawRect(rect, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(RectF rectF, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawRect(rectF, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRenderNode(RenderNode renderNode) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.g(canvas, renderNode);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRoundRect(float f5, float f6, float f7, float f8, float f9, float f10, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawRoundRect(f5, f6, f7, f8, f9, f10, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRoundRect(RectF rectF, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawRoundRect(rectF, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(CharSequence charSequence, int i5, int i6, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawText(charSequence, i5, i6, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(String str, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawText(str, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(String str, int i5, int i6, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawText(str, i5, i6, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(char[] cArr, int i5, int i6, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawText(cArr, i5, i6, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextOnPath(String str, Path path, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawTextOnPath(str, path, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextOnPath(char[] cArr, int i5, int i6, Path path, float f5, float f6, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawTextOnPath(cArr, i5, i6, path, f5, f6, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(MeasuredText measuredText, int i5, int i6, int i7, int i8, float f5, float f6, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.h(canvas, measuredText, i5, i6, i7, i8, f5, f6, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(CharSequence charSequence, int i5, int i6, int i7, int i8, float f5, float f6, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            e.f7113a.a(canvas, charSequence, i5, i6, i7, i8, f5, f6, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(char[] cArr, int i5, int i6, int i7, int i8, float f5, float f6, boolean z2, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            e.f7113a.b(canvas, cArr, i5, i6, i7, i8, f5, f6, z2, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawVertices(Canvas.VertexMode vertexMode, int i5, float[] fArr, int i6, float[] fArr2, int i7, int[] iArr, int i8, short[] sArr, int i9, int i10, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.drawVertices(vertexMode, i5, fArr, i6, fArr2, i7, iArr, i8, sArr, i9, i10, paint);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void enableZ() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            h.f7115a.i(canvas);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean getClipBounds(Rect rect) {
        Canvas canvas = this.f7150a;
        if (canvas == null) {
            b4.l.j("nativeCanvas");
            throw null;
        }
        boolean clipBounds = canvas.getClipBounds(rect);
        if (clipBounds) {
            rect.set(0, 0, rect.width(), Integer.MAX_VALUE);
        }
        return clipBounds;
    }

    @Override // android.graphics.Canvas
    public final int getDensity() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getDensity();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final DrawFilter getDrawFilter() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getDrawFilter();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getHeight() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getHeight();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void getMatrix(Matrix matrix) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.getMatrix(matrix);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final int getMaximumBitmapHeight() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getMaximumBitmapHeight();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getMaximumBitmapWidth() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getMaximumBitmapWidth();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getSaveCount() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getSaveCount();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getWidth() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.getWidth();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean isOpaque() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.isOpaque();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(float f5, float f6, float f7, float f8) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return i.f7116a.a(canvas, f5, f6, f7, f8);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(float f5, float f6, float f7, float f8, Canvas.EdgeType edgeType) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.quickReject(f5, f6, f7, f8, edgeType);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(Path path) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return i.f7116a.b(canvas, path);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(Path path, Canvas.EdgeType edgeType) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.quickReject(path, edgeType);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(RectF rectF) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return i.f7116a.c(canvas, rectF);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(RectF rectF, Canvas.EdgeType edgeType) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.quickReject(rectF, edgeType);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void restore() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.restore();
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void restoreToCount(int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.restoreToCount(i5);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void rotate(float f5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.rotate(f5);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final int save() {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.save();
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(float f5, float f6, float f7, float f8, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayer(f5, f6, f7, f8, paint);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(float f5, float f6, float f7, float f8, Paint paint, int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayer(f5, f6, f7, f8, paint, i5);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(RectF rectF, Paint paint) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayer(rectF, paint);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(RectF rectF, Paint paint, int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayer(rectF, paint, i5);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(float f5, float f6, float f7, float f8, int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(f5, f6, f7, f8, i5);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(float f5, float f6, float f7, float f8, int i5, int i6) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(f5, f6, f7, f8, i5, i6);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(RectF rectF, int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(rectF, i5);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(RectF rectF, int i5, int i6) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(rectF, i5, i6);
        }
        b4.l.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void scale(float f5, float f6) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.scale(f5, f6);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setBitmap(Bitmap bitmap) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.setBitmap(bitmap);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setDensity(int i5) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.setDensity(i5);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setDrawFilter(DrawFilter drawFilter) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.setDrawFilter(drawFilter);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setMatrix(Matrix matrix) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.setMatrix(matrix);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void skew(float f5, float f6) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.skew(f5, f6);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void translate(float f5, float f6) {
        Canvas canvas = this.f7150a;
        if (canvas != null) {
            canvas.translate(f5, f6);
        } else {
            b4.l.j("nativeCanvas");
            throw null;
        }
    }
}
