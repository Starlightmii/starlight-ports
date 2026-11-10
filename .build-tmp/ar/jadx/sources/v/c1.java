package v;

import f0.o1;
import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c1 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a2.h f5868f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o1 f5869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o1 f5870b = new o1(0.0f);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public x0.d f5871c = x0.d.f6533e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5872d = y1.g0.f6768b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f5873e;

    static {
        e eVar = e.f5893p;
        f0.k kVar = new f0.k(26);
        b4.z.c(1, eVar);
        f5868f = new a2.h(kVar, 15, eVar);
    }

    public c1(p.l0 l0Var, float f5) {
        this.f5869a = new o1(f5);
        this.f5873e = new s1(l0Var, f0.h.f2189j);
    }

    public final void a(p.l0 l0Var, x0.d dVar, int i5, int i6) {
        float f5;
        float f6 = i6 - i5;
        this.f5870b.h(f6);
        float f7 = dVar.f6534a;
        float f8 = dVar.f6535b;
        x0.d dVar2 = this.f5871c;
        float f9 = dVar2.f6534a;
        o1 o1Var = this.f5869a;
        if (f7 != f9 || f8 != dVar2.f6535b) {
            boolean z2 = l0Var == p.l0.f4525d;
            if (z2) {
                f7 = f8;
            }
            float f10 = z2 ? dVar.f6537d : dVar.f6536c;
            float fG = o1Var.g();
            float f11 = i5;
            float f12 = fG + f11;
            if (f10 <= f12 && (f7 >= fG || f10 - f7 <= f11)) {
                f5 = (f7 >= fG || f10 - f7 > f11) ? 0.0f : f7 - fG;
            } else {
                f5 = f10 - f12;
            }
            o1Var.h(o1Var.g() + f5);
            this.f5871c = dVar;
        }
        o1Var.h(d4.a.l(o1Var.g(), 0.0f, f6));
    }
}
