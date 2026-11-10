package o;

import f0.b2;
import f0.p;
import f0.v;
import n.r;
import p0.q;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f4324a = new q();

    public static void b(e eVar, v.i iVar, boolean z2, a4.a aVar) {
        eVar.f4324a.add(new n0.d(262103052, true, new r(iVar, z2, aVar)));
    }

    public final void a(a aVar, p pVar, int i5) {
        v vVar = (v) pVar;
        vVar.X(1320309496);
        int i6 = (vVar.f(aVar) ? 4 : 2) | i5 | (vVar.f(this) ? 32 : 16);
        if ((i6 & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            q qVar = this.f4324a;
            int size = qVar.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((a4.f) qVar.get(i7)).b(aVar, vVar, Integer.valueOf(i6 & 14));
            }
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.l(i5, 8, this, aVar);
        }
    }
}
