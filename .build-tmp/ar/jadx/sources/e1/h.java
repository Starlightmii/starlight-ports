package e1;

import android.graphics.Path;
import java.util.List;
import y0.l0;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public s0 f1914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List f1915c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1916d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1917e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1918f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y0.j f1919g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y0.j f1920h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f1921i;

    public h() {
        int i5 = a0.f1864a;
        this.f1915c = q3.s.f5477d;
        this.f1916d = 1.0f;
        this.f1917e = true;
        y0.j jVarH = l0.h();
        this.f1919g = jVarH;
        this.f1920h = jVarH;
        this.f1921i = h1.c.D(g.f1911f);
    }

    @Override // e1.v
    public final void a(a1.f fVar) {
        if (this.f1917e) {
            h1.c.P(this.f1915c, this.f1919g);
            e();
        } else if (this.f1918f) {
            e();
        }
        this.f1917e = false;
        this.f1918f = false;
        s0 s0Var = this.f1914b;
        if (s0Var != null) {
            a1.f.f(fVar, this.f1920h, s0Var, 1.0f, null, 56);
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, p3.d] */
    public final void e() {
        float f5 = this.f1916d;
        y0.j jVar = this.f1919g;
        if (f5 == 1.0f) {
            this.f1920h = jVar;
            return;
        }
        if (b4.l.a(this.f1920h, jVar)) {
            this.f1920h = l0.h();
        } else {
            Path.FillType fillType = this.f1920h.f6638a.getFillType();
            Path.FillType fillType2 = Path.FillType.EVEN_ODD;
            boolean z2 = fillType == fillType2;
            this.f1920h.f6638a.rewind();
            Path path = this.f1920h.f6638a;
            if (!z2) {
                fillType2 = Path.FillType.WINDING;
            }
            path.setFillType(fillType2);
        }
        ?? r1 = this.f1921i;
        ((y0.k) r1.getValue()).f6641a.setPath(jVar != null ? jVar.f6638a : null, false);
        float length = ((y0.k) r1.getValue()).f6641a.getLength();
        float f6 = 0.0f * length;
        float f7 = ((this.f1916d + 0.0f) % 1.0f) * length;
        if (f6 <= f7) {
            ((y0.k) r1.getValue()).a(f6, f7, this.f1920h);
        } else {
            ((y0.k) r1.getValue()).a(f6, length, this.f1920h);
            ((y0.k) r1.getValue()).a(0.0f, f7, this.f1920h);
        }
    }

    public final String toString() {
        return this.f1919g.toString();
    }
}
