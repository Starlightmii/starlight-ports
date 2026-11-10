package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3997e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f3998f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3999g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f4000h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(v.c1 c1Var, boolean z2, q.k kVar) {
        super(3);
        this.f3999g = c1Var;
        this.f3998f = z2;
        this.f4000h = kVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public r(v.i iVar, boolean z2, a4.a aVar) {
        super(3);
        this.f3999g = iVar;
        this.f3998f = z2;
        this.f4000h = (b4.m) aVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(boolean z2, String str, a4.a aVar) {
        super(3);
        this.f3998f = z2;
        this.f3999g = str;
        this.f4000h = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:49:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:52:0x0110  */
    /* JADX WARN: Type inference failed for: r6v3, types: [a4.a, b4.m] */
    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        q.k kVar;
        String str;
        switch (this.f3997e) {
            case 0:
                ((Number) obj3).intValue();
                f0.v vVar = (f0.v) ((f0.p) obj2);
                vVar.V(-756081143);
                i0 i0Var = (i0) vVar.j(androidx.compose.foundation.d.f78a);
                if (i0Var instanceof n0) {
                    vVar.V(617140216);
                    vVar.p(false);
                    kVar = null;
                } else {
                    vVar.V(617248189);
                    Object objK = vVar.K();
                    if (objK == f0.o.f2281a) {
                        objK = new q.k();
                        vVar.f0(objK);
                    }
                    kVar = (q.k) objK;
                    vVar.p(false);
                }
                r0.p pVarC = androidx.compose.foundation.a.c(r0.m.f5680a, kVar, i0Var, this.f3998f, (String) this.f3999g, null, (a4.a) this.f4000h);
                vVar.p(false);
                return pVarC;
            case 1:
                o.a aVar = (o.a) obj;
                f0.p pVar = (f0.p) obj2;
                int iIntValue = ((Number) obj3).intValue();
                if ((iIntValue & 6) == 0) {
                    iIntValue |= ((f0.v) pVar).f(aVar) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18) {
                    f0.v vVar2 = (f0.v) pVar;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        str = (String) ((v.i) this.f3999g).invoke(pVar, 0);
                        if (!h4.g.T(str)) {
                            throw new IllegalStateException("Label must not be blank");
                        }
                        o.m.b(str, this.f3998f, aVar, (b4.m) this.f4000h, pVar, (iIntValue << 6) & 896);
                    }
                } else {
                    str = (String) ((v.i) this.f3999g).invoke(pVar, 0);
                    if (!h4.g.T(str)) {
                        throw new IllegalStateException("Label must not be blank");
                    }
                    o.m.b(str, this.f3998f, aVar, (b4.m) this.f4000h, pVar, (iIntValue << 6) & 896);
                }
                return p3.n.f5026a;
            default:
                ((Number) obj3).intValue();
                v.c1 c1Var = (v.c1) this.f3999g;
                f0.v vVar3 = (f0.v) ((f0.p) obj2);
                vVar3.V(805428266);
                boolean z2 = ((p.l0) c1Var.f5873e.getValue()) == p.l0.f4525d || !(vVar3.j(q1.j1.f5243l) == k2.k.f3417e);
                boolean zF = vVar3.f(c1Var);
                Object objK2 = vVar3.K();
                f0.h hVar = f0.o.f2281a;
                if (zF || objK2 == hVar) {
                    objK2 = new c0.g1(26, c1Var);
                    vVar3.f0(objK2);
                }
                f0.i1 i1VarV = f0.l.v((a4.c) objK2, vVar3);
                Object objK3 = vVar3.K();
                if (objK3 == hVar) {
                    p.n nVar = new p.n(new p.c1(i1VarV, 0));
                    vVar3.f0(nVar);
                    objK3 = nVar;
                }
                p.b1 b1Var = (p.b1) objK3;
                boolean zF2 = vVar3.f(b1Var) | vVar3.f(c1Var);
                Object objK4 = vVar3.K();
                if (zF2 || objK4 == hVar) {
                    objK4 = new v.b1(b1Var, c1Var);
                    vVar3.f0(objK4);
                }
                r0.p pVarB = androidx.compose.foundation.gestures.a.b(r0.m.f5680a, (v.b1) objK4, (p.l0) c1Var.f5873e.getValue(), null, this.f3998f && c1Var.f5870b.g() != 0.0f, z2, (q.k) this.f4000h);
                vVar3.p(false);
                return pVarB;
        }
    }
}
