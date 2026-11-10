package v;

import f0.g3;
import q1.j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5926e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f5927f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ y1.h0 f5928g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(int i5, int i6, y1.h0 h0Var) {
        super(3);
        this.f5926e = i5;
        this.f5927f = i6;
        this.f5928g = h0Var;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        ((Number) obj3).intValue();
        f0.v vVar = (f0.v) ((f0.p) obj2);
        vVar.V(408240218);
        int i5 = this.f5926e;
        int i6 = this.f5927f;
        l0.s(i5, i6);
        r0.m mVar = r0.m.f5680a;
        if (i5 == 1 && i6 == Integer.MAX_VALUE) {
            vVar.p(false);
            return mVar;
        }
        k2.b bVar = (k2.b) vVar.j(j1.f5237f);
        d2.d dVar = (d2.d) vVar.j(j1.f5240i);
        k2.k kVar = (k2.k) vVar.j(j1.f5243l);
        y1.h0 h0Var = this.f5928g;
        boolean zF = vVar.f(h0Var) | vVar.f(kVar);
        Object objK = vVar.K();
        f0.h hVar = f0.o.f2281a;
        if (zF || objK == hVar) {
            objK = p3.p.B(h0Var, kVar);
            vVar.f0(objK);
        }
        y1.h0 h0Var2 = (y1.h0) objK;
        boolean zF2 = vVar.f(dVar) | vVar.f(h0Var2);
        Object objK2 = vVar.K();
        if (zF2 || objK2 == hVar) {
            y1.a0 a0Var = h0Var2.f6780a;
            d2.p pVar = a0Var.f6714f;
            d2.k kVar2 = a0Var.f6711c;
            if (kVar2 == null) {
                kVar2 = d2.k.f1613f;
            }
            d2.i iVar = a0Var.f6712d;
            int i7 = iVar != null ? iVar.f1610a : 0;
            d2.j jVar = a0Var.f6713e;
            objK2 = ((d2.e) dVar).b(pVar, kVar2, i7, jVar != null ? jVar.f1611a : 1);
            vVar.f0(objK2);
        }
        g3 g3Var = (g3) objK2;
        boolean zF3 = vVar.f(g3Var.getValue()) | vVar.f(bVar) | vVar.f(dVar) | vVar.f(h0Var) | vVar.f(kVar);
        Object objK3 = vVar.K();
        if (zF3 || objK3 == hVar) {
            objK3 = Integer.valueOf((int) (w0.a(h0Var2, bVar, dVar, w0.f6126a, 1) & 4294967295L));
            vVar.f0(objK3);
        }
        int iIntValue = ((Number) objK3).intValue();
        boolean zF4 = vVar.f(g3Var.getValue()) | vVar.f(bVar) | vVar.f(dVar) | vVar.f(h0Var) | vVar.f(kVar);
        Object objK4 = vVar.K();
        if (zF4 || objK4 == hVar) {
            StringBuilder sb = new StringBuilder();
            String str = w0.f6126a;
            sb.append(str);
            sb.append('\n');
            sb.append(str);
            objK4 = Integer.valueOf((int) (w0.a(h0Var2, bVar, dVar, sb.toString(), 2) & 4294967295L));
            vVar.f0(objK4);
        }
        int iIntValue2 = ((Number) objK4).intValue() - iIntValue;
        Integer numValueOf = i5 == 1 ? null : Integer.valueOf(((i5 - 1) * iIntValue2) + iIntValue);
        Integer numValueOf2 = i6 != Integer.MAX_VALUE ? Integer.valueOf(((i6 - 1) * iIntValue2) + iIntValue) : null;
        r0.p pVarE = androidx.compose.foundation.layout.c.e(mVar, numValueOf != null ? bVar.i0(numValueOf.intValue()) : Float.NaN, numValueOf2 != null ? bVar.i0(numValueOf2.intValue()) : Float.NaN);
        vVar.p(false);
        return pVarE;
    }
}
