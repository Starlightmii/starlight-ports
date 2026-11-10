package p1;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n1.m0 f4816a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4820e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4821f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4822g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f4823h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f4825j;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4817b = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashMap f4824i = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    public e0(a aVar, int i5) {
        this.f4825j = i5;
        this.f4816a = (n1.m0) aVar;
    }

    /* JADX WARN: Type inference failed for: r10v4, types: [a4.e, b4.j] */
    /* JADX WARN: Type inference failed for: r9v4, types: [n1.m0, p1.a] */
    public static final void a(e0 e0Var, n1.l lVar, int i5, y0 y0Var) {
        HashMap map = e0Var.f4824i;
        float f5 = i5;
        long jB = p3.j.b(f5, f5);
        while (true) {
            switch (e0Var.f4825j) {
                case 0:
                    y0.n0 n0Var = y0.H;
                    jB = y0Var.d1(jB);
                    break;
                default:
                    n0 n0VarJ0 = y0Var.J0();
                    b4.l.b(n0VarJ0);
                    long j5 = n0VarJ0.f4933p;
                    jB = x0.c.h(p3.j.b((int) (j5 >> 32), (int) (j5 & 4294967295L)), jB);
                    break;
            }
            y0Var = y0Var.f5000q;
            b4.l.b(y0Var);
            if (y0Var.equals(e0Var.f4816a.x())) {
                int iRound = Math.round(lVar instanceof n1.l ? x0.c.e(jB) : x0.c.d(jB));
                if (map.containsKey(lVar)) {
                    b4.l.e("<this>", map);
                    Object obj = map.get(lVar);
                    if (obj == null && !map.containsKey(lVar)) {
                        throw new NoSuchElementException("Key " + lVar + " is missing in the map.");
                    }
                    int iIntValue = ((Number) obj).intValue();
                    n1.l lVar2 = n1.c.f4106a;
                    iRound = ((Number) lVar.f4128a.invoke(Integer.valueOf(iIntValue), Integer.valueOf(iRound))).intValue();
                }
                map.put(lVar, Integer.valueOf(iRound));
                return;
            }
            if (e0Var.b(y0Var).containsKey(lVar)) {
                float fC = e0Var.c(y0Var, lVar);
                jB = p3.j.b(fC, fC);
            }
        }
    }

    public final Map b(y0 y0Var) {
        switch (this.f4825j) {
            case 0:
                return y0Var.u0().m();
            default:
                n0 n0VarJ0 = y0Var.J0();
                b4.l.b(n0VarJ0);
                return n0VarJ0.u0().m();
        }
    }

    public final int c(y0 y0Var, n1.l lVar) {
        switch (this.f4825j) {
            case 0:
                return y0Var.f0(lVar);
            default:
                n0 n0VarJ0 = y0Var.J0();
                b4.l.b(n0VarJ0);
                return n0VarJ0.f0(lVar);
        }
    }

    public final boolean d() {
        return this.f4818c || this.f4820e || this.f4821f || this.f4822g;
    }

    public final boolean e() {
        h();
        return this.f4823h != null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [n1.m0, p1.a] */
    public final void f() {
        this.f4817b = true;
        ?? r1 = this.f4816a;
        a aVarZ = r1.z();
        if (aVarZ == null) {
            return;
        }
        if (this.f4818c) {
            aVarZ.b0();
        } else if (this.f4820e || this.f4819d) {
            aVarZ.requestLayout();
        }
        if (this.f4821f) {
            r1.b0();
        }
        if (this.f4822g) {
            r1.requestLayout();
        }
        aVarZ.m().f();
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [n1.m0, p1.a] */
    public final void g() {
        HashMap map = this.f4824i;
        map.clear();
        c0.g1 g1Var = new c0.g1(17, this);
        ?? r4 = this.f4816a;
        r4.r(g1Var);
        map.putAll(b(r4.x()));
        this.f4817b = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [n1.m0, p1.a] */
    /* JADX WARN: Type inference failed for: r1v1, types: [p1.a] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final void h() {
        a aVar;
        e0 e0VarM;
        e0 e0VarM2;
        boolean zD = d();
        ?? r1 = this.f4816a;
        ?? r4 = r1;
        if (!zD) {
            a aVarZ = r1.z();
            if (aVarZ == null) {
                return;
            }
            aVar = aVarZ.m().f4823h;
            if (aVar == null || !aVar.m().d()) {
                r4 = aVar;
                a aVar2 = this.f4823h;
                if (aVar2 == null || aVar2.m().d()) {
                    return;
                }
                a aVarZ2 = aVar2.z();
                if (aVarZ2 != null && (e0VarM2 = aVarZ2.m()) != null) {
                    e0VarM2.h();
                }
                a aVarZ3 = aVar2.z();
                r4 = (aVarZ3 == null || (e0VarM = aVarZ3.m()) == null) ? 0 : e0VarM.f4823h;
            }
        }
        r4 = aVar;
        this.f4823h = r4;
    }
}
