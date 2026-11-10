package y0;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Region;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f6617a = e.f6621a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Rect f6618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f6619c;

    @Override // y0.r
    public final void a(k0 k0Var) {
        Canvas canvas = this.f6617a;
        if (!(k0Var instanceof j)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.clipPath(((j) k0Var).f6638a, Region.Op.INTERSECT);
    }

    @Override // y0.r
    public final void b(k0 k0Var, h hVar) {
        Canvas canvas = this.f6617a;
        if (!(k0Var instanceof j)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.drawPath(((j) k0Var).f6638a, hVar.f6629a);
    }

    @Override // y0.r
    public final void c(float f5, long j5, h hVar) {
        this.f6617a.drawCircle(x0.c.d(j5), x0.c.e(j5), f5, hVar.f6629a);
    }

    @Override // y0.r
    public final void d(float f5, float f6) {
        this.f6617a.scale(f5, f6);
    }

    @Override // y0.r
    public final void e(x0.d dVar, h hVar) {
        this.f6617a.saveLayer(dVar.f6534a, dVar.f6535b, dVar.f6536c, dVar.f6537d, hVar.f6629a, 31);
    }

    @Override // y0.r
    public final void f(float f5, float f6, float f7, float f8, h hVar) {
        this.f6617a.drawRect(f5, f6, f7, f8, hVar.f6629a);
    }

    @Override // y0.r
    public final void g(c0 c0Var, long j5, long j6, long j7, h hVar) {
        if (this.f6618b == null) {
            this.f6618b = new Rect();
            this.f6619c = new Rect();
        }
        Canvas canvas = this.f6617a;
        Bitmap bitmapK = l0.k(c0Var);
        Rect rect = this.f6618b;
        b4.l.b(rect);
        int i5 = (int) (j5 >> 32);
        rect.left = i5;
        int i6 = (int) (j5 & 4294967295L);
        rect.top = i6;
        rect.right = i5 + ((int) (j6 >> 32));
        rect.bottom = i6 + ((int) (j6 & 4294967295L));
        Rect rect2 = this.f6619c;
        b4.l.b(rect2);
        int i7 = (int) 0;
        rect2.left = i7;
        int i8 = (int) 0;
        rect2.top = i8;
        rect2.right = i7 + ((int) (j7 >> 32));
        rect2.bottom = i8 + ((int) (4294967295L & j7));
        canvas.drawBitmap(bitmapK, rect, rect2, hVar.f6629a);
    }

    @Override // y0.r
    public final void i(float f5, float f6, float f7, float f8, int i5) {
        this.f6617a.clipRect(f5, f6, f7, f8, i5 == 0 ? Region.Op.DIFFERENCE : Region.Op.INTERSECT);
    }

    @Override // y0.r
    public final void j(c0 c0Var, h hVar) {
        this.f6617a.drawBitmap(l0.k(c0Var), x0.c.d(0L), x0.c.e(0L), hVar.f6629a);
    }

    @Override // y0.r
    public final void k(float f5, float f6) {
        this.f6617a.translate(f5, f6);
    }

    @Override // y0.r
    public final void l() {
        this.f6617a.rotate(45.0f);
    }

    @Override // y0.r
    public final void m() {
        this.f6617a.restore();
    }

    @Override // y0.r
    public final void n(float f5, float f6, float f7, float f8, float f9, float f10, h hVar) {
        this.f6617a.drawRoundRect(f5, f6, f7, f8, f9, f10, hVar.f6629a);
    }

    @Override // y0.r
    public final void o(long j5, long j6, h hVar) {
        this.f6617a.drawLine(x0.c.d(j5), x0.c.e(j5), x0.c.d(j6), x0.c.e(j6), hVar.f6629a);
    }

    @Override // y0.r
    public final void p() {
        this.f6617a.save();
    }

    @Override // y0.r
    public final void q() {
        l0.n(this.f6617a, false);
    }

    @Override // y0.r
    public final void r(float[] fArr) {
        int i5 = 0;
        while (i5 < 4) {
            int i6 = 0;
            while (i6 < 4) {
                if (fArr[(i5 * 4) + i6] != (i5 == i6 ? 1.0f : 0.0f)) {
                    Matrix matrix = new Matrix();
                    l0.q(matrix, fArr);
                    this.f6617a.concat(matrix);
                    return;
                }
                i6++;
            }
            i5++;
        }
    }

    @Override // y0.r
    public final void s() {
        l0.n(this.f6617a, true);
    }
}
