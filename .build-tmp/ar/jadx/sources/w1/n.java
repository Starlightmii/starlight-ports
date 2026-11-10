package w1;

import c0.p0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n1.v0;
import p1.d0;
import p1.y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.o f6315a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6316b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d0 f6317c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f6318d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6319e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public n f6320f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6321g;

    public n(r0.o oVar, boolean z2, d0 d0Var, i iVar) {
        this.f6315a = oVar;
        this.f6316b = z2;
        this.f6317c = d0Var;
        this.f6318d = iVar;
        this.f6321g = d0Var.f4781e;
    }

    public static /* synthetic */ List h(n nVar, int i5) {
        return nVar.g((i5 & 1) != 0 ? !nVar.f6316b : false, (i5 & 2) == 0);
    }

    public final n a(f fVar, a4.c cVar) {
        i iVar = new i();
        iVar.f6309e = false;
        iVar.f6310f = false;
        cVar.c(iVar);
        n nVar = new n(new m(cVar), false, new d0(this.f6321g + (fVar != null ? 1000000000 : 2000000000), true), iVar);
        nVar.f6319e = true;
        nVar.f6320f = this;
        return nVar;
    }

    public final void b(d0 d0Var, ArrayList arrayList) {
        h0.e eVarT = d0Var.t();
        int i5 = eVarT.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarT.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if (d0Var2.D() && !d0Var2.G) {
                    if (d0Var2.f4801y.d(8)) {
                        arrayList.add(p3.p.c(d0Var2, this.f6316b));
                    } else {
                        b(d0Var2, arrayList);
                    }
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final y0 c() {
        if (this.f6319e) {
            n nVarJ = j();
            if (nVarJ != null) {
                return nVarJ.c();
            }
            return null;
        }
        p1.l lVarR = p3.p.r(this.f6317c);
        if (lVarR == null) {
            lVarR = this.f6315a;
        }
        return p1.f.q(lVarR, 8);
    }

    public final void d(ArrayList arrayList) {
        List listO = o(false);
        int size = listO.size();
        for (int i5 = 0; i5 < size; i5++) {
            n nVar = (n) listO.get(i5);
            if (nVar.l()) {
                arrayList.add(nVar);
            } else if (!nVar.f6318d.f6310f) {
                nVar.d(arrayList);
            }
        }
    }

    public final x0.d e() {
        y0 y0VarC = c();
        if (y0VarC != null) {
            if (!y0VarC.L0().f5693p) {
                y0VarC = null;
            }
            if (y0VarC != null) {
                return v0.f(y0VarC).k(y0VarC, true);
            }
        }
        return x0.d.f6533e;
    }

    public final x0.d f() {
        y0 y0VarC = c();
        if (y0VarC != null) {
            if (!y0VarC.L0().f5693p) {
                y0VarC = null;
            }
            if (y0VarC != null) {
                return v0.e(y0VarC);
            }
        }
        return x0.d.f6533e;
    }

    public final List g(boolean z2, boolean z3) {
        if (!z2 && this.f6318d.f6310f) {
            return q3.s.f5477d;
        }
        if (!l()) {
            return o(z3);
        }
        ArrayList arrayList = new ArrayList();
        d(arrayList);
        return arrayList;
    }

    public final i i() {
        boolean zL = l();
        i iVar = this.f6318d;
        if (!zL) {
            return iVar;
        }
        i iVar2 = new i();
        iVar2.f6309e = iVar.f6309e;
        iVar2.f6310f = iVar.f6310f;
        iVar2.f6308d.putAll(iVar.f6308d);
        n(iVar2);
        return iVar2;
    }

    public final n j() {
        d0 d0VarR;
        n nVar = this.f6320f;
        if (nVar != null) {
            return nVar;
        }
        d0 d0Var = this.f6317c;
        boolean z2 = this.f6316b;
        if (!z2) {
            d0VarR = null;
            break;
        }
        d0VarR = d0Var.r();
        while (true) {
            if (d0VarR == null) {
                d0VarR = null;
                break;
            }
            i iVarN = d0VarR.n();
            if (iVarN != null && iVarN.f6309e) {
                break;
            }
            d0VarR = d0VarR.r();
        }
        if (d0VarR == null) {
            for (d0 d0VarR2 = d0Var.r(); d0VarR2 != null; d0VarR2 = d0VarR2.r()) {
                if (d0VarR2.f4801y.d(8)) {
                    d0VarR = d0VarR2;
                }
            }
            d0VarR = null;
        }
        if (d0VarR == null) {
            return null;
        }
        return p3.p.c(d0VarR, z2);
    }

    public final i k() {
        return this.f6318d;
    }

    public final boolean l() {
        return this.f6316b && this.f6318d.f6309e;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x002b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:? A[RETURN, SYNTHETIC] */
    public final boolean m() {
        if (this.f6319e || !h(this, 4).isEmpty()) {
            return false;
        }
        d0 d0VarR = this.f6317c.r();
        while (d0VarR != null) {
            i iVarN = d0VarR.n();
            if (iVarN != null && iVarN.f6309e) {
                if (d0VarR == null) {
                    return true;
                }
                return false;
            }
            d0VarR = d0VarR.r();
        }
        d0VarR = null;
        if (d0VarR == null) {
            return true;
        }
        return false;
    }

    public final void n(i iVar) {
        if (this.f6318d.f6310f) {
            return;
        }
        List listO = o(false);
        int size = listO.size();
        for (int i5 = 0; i5 < size; i5++) {
            n nVar = (n) listO.get(i5);
            if (!nVar.l()) {
                i iVar2 = nVar.f6318d;
                LinkedHashMap linkedHashMap = iVar.f6308d;
                for (Map.Entry entry : iVar2.f6308d.entrySet()) {
                    t tVar = (t) entry.getKey();
                    Object value = entry.getValue();
                    Object obj = linkedHashMap.get(tVar);
                    b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsPropertyKey<kotlin.Any?>", tVar);
                    Object objInvoke = tVar.f6365b.invoke(obj, value);
                    if (objInvoke != null) {
                        linkedHashMap.put(tVar, objInvoke);
                    }
                }
                nVar.n(iVar);
            }
        }
    }

    public final List o(boolean z2) {
        if (this.f6319e) {
            return q3.s.f5477d;
        }
        ArrayList arrayList = new ArrayList();
        b(this.f6317c, arrayList);
        if (z2) {
            i iVar = this.f6318d;
            LinkedHashMap linkedHashMap = iVar.f6308d;
            Object obj = linkedHashMap.get(q.f6353r);
            if (obj == null) {
                obj = null;
            }
            f fVar = (f) obj;
            if (fVar != null && iVar.f6309e && !arrayList.isEmpty()) {
                arrayList.add(a(fVar, new l(0, fVar)));
            }
            t tVar = q.f6336a;
            if (linkedHashMap.containsKey(tVar) && !arrayList.isEmpty() && iVar.f6309e) {
                Object obj2 = linkedHashMap.get(tVar);
                if (obj2 == null) {
                    obj2 = null;
                }
                List list = (List) obj2;
                String str = list != null ? (String) q3.l.X(list) : null;
                if (str != null) {
                    arrayList.add(0, a(null, new p0(str, 1)));
                }
            }
        }
        return arrayList;
    }
}
