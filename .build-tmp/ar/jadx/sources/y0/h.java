package y0;

import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f6629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6630b = 3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Shader f6631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f6632d;

    public h(Paint paint) {
        this.f6629a = paint;
    }

    public final int a() {
        Paint.Cap strokeCap = this.f6629a.getStrokeCap();
        int i5 = strokeCap == null ? -1 : i.f6634a[strokeCap.ordinal()];
        if (i5 == 1) {
            return 0;
        }
        if (i5 != 2) {
            return i5 != 3 ? 0 : 2;
        }
        return 1;
    }

    public final int b() {
        Paint.Join strokeJoin = this.f6629a.getStrokeJoin();
        int i5 = strokeJoin == null ? -1 : i.f6635b[strokeJoin.ordinal()];
        if (i5 == 1) {
            return 0;
        }
        if (i5 != 2) {
            return i5 != 3 ? 0 : 1;
        }
        return 2;
    }

    public final void c(float f5) {
        this.f6629a.setAlpha((int) Math.rint(f5 * 255.0f));
    }

    public final void d(int i5) {
        if (this.f6630b == i5) {
            return;
        }
        this.f6630b = i5;
        int i6 = Build.VERSION.SDK_INT;
        Paint paint = this.f6629a;
        if (i6 >= 29) {
            u0.f6697a.a(paint, i5);
        } else {
            paint.setXfermode(new PorterDuffXfermode(l0.z(i5)));
        }
    }

    public final void e(long j5) {
        this.f6629a.setColor(l0.w(j5));
    }

    public final void f(m mVar) {
        this.f6632d = mVar;
        this.f6629a.setColorFilter(mVar != null ? mVar.f6646a : null);
    }

    public final void g(int i5) {
        this.f6629a.setFilterBitmap(!(i5 == 0));
    }

    public final void h(Shader shader) {
        this.f6631c = shader;
        this.f6629a.setShader(shader);
    }

    public final void i(int i5) {
        Paint.Cap cap;
        if (i5 == 2) {
            cap = Paint.Cap.SQUARE;
        } else if (i5 == 1) {
            cap = Paint.Cap.ROUND;
        } else {
            cap = i5 == 0 ? Paint.Cap.BUTT : Paint.Cap.BUTT;
        }
        this.f6629a.setStrokeCap(cap);
    }

    public final void j(int i5) {
        Paint.Join join;
        if (i5 == 0) {
            join = Paint.Join.MITER;
        } else if (i5 == 2) {
            join = Paint.Join.BEVEL;
        } else {
            join = i5 == 1 ? Paint.Join.ROUND : Paint.Join.MITER;
        }
        this.f6629a.setStrokeJoin(join);
    }

    public final void k(float f5) {
        this.f6629a.setStrokeWidth(f5);
    }

    public final void l(int i5) {
        this.f6629a.setStyle(i5 == 1 ? Paint.Style.STROKE : Paint.Style.FILL);
    }
}
