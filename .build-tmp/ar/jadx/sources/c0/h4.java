package c0;

import androidx.compose.foundation.layout.FillElement;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h4 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f907e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n0.d f908f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f909g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a4.f f910h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h4(n0.d dVar, a4.e eVar, a4.f fVar, int i5) {
        super(2);
        this.f907e = i5;
        this.f908f = dVar;
        this.f909g = eVar;
        this.f910h = fVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0020  */
    /* JADX WARN: Code duplicated, block: B:14:0x0042  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f0.v vVar;
        n0.d dVar;
        a4.e eVar;
        a4.f fVar;
        boolean zF;
        Object objK;
        switch (this.f907e) {
            case 0:
                n1.z0 z0Var = (n1.z0) obj;
                long j5 = ((k2.a) obj2).f3400a;
                int i5 = k2.a.i(j5);
                List listH = z0Var.H(this.f908f, j4.f954d);
                int size = listH.size();
                b4.u uVar = new b4.u();
                if (size > 0) {
                    uVar.f695d = i5 / size;
                }
                Integer numValueOf = 0;
                int size2 = listH.size();
                for (int i6 = 0; i6 < size2; i6++) {
                    numValueOf = Integer.valueOf(Math.max(((n1.e0) listH.get(i6)).d(uVar.f695d), numValueOf.intValue()));
                }
                int iIntValue = numValueOf.intValue();
                ArrayList arrayList = new ArrayList(listH.size());
                int size3 = listH.size();
                for (int i7 = 0; i7 < size3; i7++) {
                    n1.e0 e0Var = (n1.e0) listH.get(i7);
                    int i8 = uVar.f695d;
                    arrayList.add(e0Var.c(k2.a.a(i8, i8, iIntValue, iIntValue)));
                }
                ArrayList arrayList2 = new ArrayList(size);
                for (int i9 = 0; i9 < size; i9++) {
                    k2.e eVar2 = new k2.e(z0Var.i0(Math.min(((n1.e0) listH.get(i9)).c0(iIntValue), uVar.f695d)) - (a4.f747b * 2));
                    k2.e eVar3 = new k2.e(24);
                    if (eVar2.compareTo(eVar3) < 0) {
                        eVar2 = eVar3;
                    }
                    arrayList2.add(new b4(z0Var.i0(uVar.f695d) * i9, z0Var.i0(uVar.f695d), eVar2.f3406d));
                }
                return z0Var.F(i5, iIntValue, q3.t.f5478d, new g4(arrayList, z0Var, this.f909g, uVar, j5, iIntValue, this.f910h, arrayList2, i5));
            default:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        FillElement fillElement = androidx.compose.foundation.layout.c.f113a;
                        vVar = (f0.v) pVar;
                        dVar = this.f908f;
                        boolean zF2 = vVar.f(dVar);
                        eVar = this.f909g;
                        boolean zF3 = zF2 | vVar.f(eVar);
                        fVar = this.f910h;
                        zF = zF3 | vVar.f(fVar);
                        objK = vVar.K();
                        if (zF || objK == f0.o.f2281a) {
                            objK = new h4(dVar, eVar, fVar, 0);
                            vVar.f0(objK);
                        }
                        n1.v0.c(fillElement, (a4.e) objK, vVar, 6, 0);
                    }
                } else {
                    FillElement fillElement2 = androidx.compose.foundation.layout.c.f113a;
                    vVar = (f0.v) pVar;
                    dVar = this.f908f;
                    boolean zF4 = vVar.f(dVar);
                    eVar = this.f909g;
                    boolean zF5 = zF4 | vVar.f(eVar);
                    fVar = this.f910h;
                    zF = zF5 | vVar.f(fVar);
                    objK = vVar.K();
                    if (zF) {
                        objK = new h4(dVar, eVar, fVar, 0);
                        vVar.f0(objK);
                    } else {
                        objK = new h4(dVar, eVar, fVar, 0);
                        vVar.f0(objK);
                    }
                    n1.v0.c(fillElement2, (a4.e) objK, vVar, 6, 0);
                }
                return p3.n.f5026a;
        }
    }
}
