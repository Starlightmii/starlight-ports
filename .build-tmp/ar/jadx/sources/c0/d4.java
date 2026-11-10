package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d4 f808a = new d4();

    public static r.m0 b() {
        float f5 = d0.u.f1580b;
        return new r.m0(f5, d0.u.f1582d, f5, 0);
    }

    public void a(r0.p pVar, float f5, long j5, f0.p pVar2, int i5) {
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1498258020);
        if ((((vVar.f(pVar) ? 4 : 2) | i5 | 176) & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                f5 = e0.l.f1769a;
                j5 = u.d(vVar, 26);
            } else {
                vVar.Q();
            }
            vVar.q();
            r.o.a(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.d(pVar.e(androidx.compose.foundation.layout.c.f113a), f5), j5, y0.l0.f6642a), vVar, 0);
        }
        float f6 = f5;
        long j6 = j5;
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c4(this, pVar, f6, j6, i5);
        }
    }
}
