package d1;

import a1.c;
import a1.f;
import android.graphics.Bitmap;
import k2.h;
import k2.j;
import p1.f0;
import y0.c0;
import y0.g;
import y0.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c0 f1593e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f1594f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f1595g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1596h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m f1597i;

    public a(c0 c0Var, long j5) {
        int i5;
        int i6;
        this.f1593e = c0Var;
        this.f1594f = j5;
        if (((int) 0) >= 0 && ((int) 0) >= 0 && (i5 = (int) (j5 >> 32)) >= 0 && (i6 = (int) (4294967295L & j5)) >= 0) {
            Bitmap bitmap = ((g) c0Var).f6627a;
            if (i5 <= bitmap.getWidth() && i6 <= bitmap.getHeight()) {
                this.f1595g = j5;
                this.f1596h = 1.0f;
                return;
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    @Override // d1.b
    public final void a(float f5) {
        this.f1596h = f5;
    }

    @Override // d1.b
    public final void b(m mVar) {
        this.f1597i = mVar;
    }

    @Override // d1.b
    public final long d() {
        return a.a.O(this.f1595g);
    }

    @Override // d1.b
    public final void e(f0 f0Var) {
        c cVar = f0Var.f4828d;
        f.Q(f0Var, this.f1593e, this.f1594f, a.a.f(Math.round(x0.f.d(cVar.b())), Math.round(x0.f.b(cVar.b()))), this.f1596h, this.f1597i, 328);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f1593e.equals(aVar.f1593e) && h.a(0L, 0L) && j.a(this.f1594f, aVar.f1594f);
    }

    public final int hashCode() {
        return Integer.hashCode(1) + b.b.b(b.b.b(this.f1593e.hashCode() * 31, 31, 0L), 31, this.f1594f);
    }

    public final String toString() {
        return "BitmapPainter(image=" + this.f1593e + ", srcOffset=" + ((Object) h.d(0L)) + ", srcSize=" + ((Object) j.b(this.f1594f)) + ", filterQuality=" + ((Object) "Low") + ')';
    }
}
