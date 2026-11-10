package n;

import io.github.libxposed.service.IXposedService;
import java.util.NoSuchElementException;
import p.v1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3928e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f3929f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3930g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3931h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(Object obj, Object obj2, Object obj3, int i5) {
        super(0);
        this.f3928e = i5;
        this.f3929f = obj;
        this.f3930g = obj2;
        this.f3931h = obj3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public i(s.g gVar, p1.y0 y0Var, a4.a aVar) {
        super(0);
        this.f3928e = 5;
        this.f3929f = gVar;
        this.f3930g = y0Var;
        this.f3931h = (b4.m) aVar;
    }

    /* JADX WARN: Type inference failed for: r4v12, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // a4.a
    public final Object a() {
        int i5 = this.f3928e;
        int i6 = 0;
        p3.n nVar = p3.n.f5026a;
        Object obj = this.f3931h;
        Object obj2 = this.f3930g;
        Object obj3 = this.f3929f;
        switch (i5) {
            case 0:
                p1.f0 f0Var = (p1.f0) obj;
                ((b4.w) obj3).f697d = ((j) obj2).f3939t.c(f0Var.f4828d.b(), f0Var.getLayoutDirection(), f0Var);
                return nVar;
            case 1:
                p.i iVar = (p.i) obj3;
                j1.h hVar = iVar.f4477t;
                while (true) {
                    h0.e eVar = hVar.f3256a;
                    if (eVar.k()) {
                        int i7 = eVar.f2578f;
                        if (i7 == 0) {
                            throw new NoSuchElementException("MutableVector is empty.");
                        }
                        x0.d dVar = (x0.d) ((p.h) eVar.f2576d[i7 - 1]).f4467a.a();
                        if (dVar == null ? true : iVar.A0(dVar, iVar.f4481x)) {
                            ((p.h) eVar.m(eVar.f2578f - 1)).f4468b.resumeWith(nVar);
                        }
                    }
                }
                if (iVar.f4480w) {
                    x0.d dVarZ0 = iVar.z0();
                    if (dVarZ0 != null && iVar.A0(dVarZ0, iVar.f4481x)) {
                        iVar.f4480w = false;
                    }
                }
                ((v1) obj2).f4623e = p.i.y0(iVar, (p.d) obj);
                return nVar;
            case 2:
                p1.j0 j0Var = (p1.j0) obj;
                p1.h0 h0Var = (p1.h0) obj3;
                p1.j0 j0Var2 = h0Var.A;
                j0Var2.f4902j = 0;
                h0.e eVarU = j0Var2.f4893a.u();
                int i8 = eVarU.f2578f;
                if (i8 > 0) {
                    Object[] objArr = eVarU.f2576d;
                    int i9 = 0;
                    do {
                        p1.h0 h0Var2 = ((p1.d0) objArr[i9]).f4802z.f4911s;
                        b4.l.b(h0Var2);
                        h0Var2.f4849j = h0Var2.f4850k;
                        h0Var2.f4850k = Integer.MAX_VALUE;
                        if (h0Var2.f4851l == 2) {
                            h0Var2.f4851l = 3;
                        }
                        i9++;
                    } while (i9 < i8);
                }
                p1.d0 d0Var = j0Var2.f4893a;
                p1.d0 d0Var2 = j0Var2.f4893a;
                h0.e eVarU2 = d0Var.u();
                int i10 = eVarU2.f2578f;
                if (i10 > 0) {
                    Object[] objArr2 = eVarU2.f2576d;
                    int i11 = 0;
                    do {
                        p1.h0 h0Var3 = ((p1.d0) objArr2[i11]).f4802z.f4911s;
                        b4.l.b(h0Var3);
                        h0Var3.f4859t.f4819d = false;
                        i11++;
                    } while (i11 < i10);
                }
                p1.s sVar = h0Var.x().N;
                if (sVar != null) {
                    boolean z2 = sVar.f4924k;
                    h0.b bVar = (h0.b) j0Var.f4893a.m();
                    int i12 = bVar.f2570d.f2578f;
                    for (int i13 = 0; i13 < i12; i13++) {
                        p1.n0 n0VarJ0 = ((p1.d0) bVar.get(i13)).f4801y.f4970c.J0();
                        if (n0VarJ0 != null) {
                            n0VarJ0.f4924k = z2;
                        }
                    }
                }
                ((p1.n0) obj2).u0().n();
                if (h0Var.x().N != null) {
                    h0.b bVar2 = (h0.b) j0Var.f4893a.m();
                    int i14 = bVar2.f2570d.f2578f;
                    for (int i15 = 0; i15 < i14; i15++) {
                        p1.n0 n0VarJ1 = ((p1.d0) bVar2.get(i15)).f4801y.f4970c.J0();
                        if (n0VarJ1 != null) {
                            n0VarJ1.f4924k = false;
                        }
                    }
                }
                h0.e eVarU3 = d0Var2.u();
                int i16 = eVarU3.f2578f;
                if (i16 > 0) {
                    Object[] objArr3 = eVarU3.f2576d;
                    int i17 = 0;
                    do {
                        p1.h0 h0Var4 = ((p1.d0) objArr3[i17]).f4802z.f4911s;
                        b4.l.b(h0Var4);
                        int i18 = h0Var4.f4849j;
                        int i19 = h0Var4.f4850k;
                        if (i18 != i19 && i19 == Integer.MAX_VALUE) {
                            h0Var4.p0();
                        }
                        i17++;
                    } while (i17 < i16);
                }
                h0.e eVarU4 = d0Var2.u();
                int i20 = eVarU4.f2578f;
                if (i20 > 0) {
                    Object[] objArr4 = eVarU4.f2576d;
                    do {
                        p1.h0 h0Var5 = ((p1.d0) objArr4[i6]).f4802z.f4911s;
                        b4.l.b(h0Var5);
                        p1.e0 e0Var = h0Var5.f4859t;
                        e0Var.f4820e = e0Var.f4819d;
                        i6++;
                    } while (i6 < i20);
                }
                return nVar;
            case 3:
                y0.n0 n0Var = p1.y0.H;
                ((p1.y0) obj3).F0((y0.r) obj2, (b1.b) obj);
                return nVar;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                q1.a aVar = (q1.a) obj3;
                aVar.removeOnAttachStateChangeListener((q1.x) obj2);
                e2.f0 f0Var2 = (e2.f0) obj;
                b4.l.e("listener", f0Var2);
                h1.c.v(aVar).f51a.remove(f0Var2);
                return nVar;
            default:
                s.g gVar = (s.g) obj3;
                x0.d dVarY0 = s.g.y0(gVar, (p1.y0) obj2, (b4.m) obj);
                if (dVarY0 == null) {
                    return null;
                }
                p.i iVar2 = gVar.f5758q;
                if (k2.j.a(iVar2.f4481x, 0L)) {
                    throw new IllegalStateException("Expected BringIntoViewRequester to not be used before parents are placed.");
                }
                return dVarY0.h(iVar2.C0(dVarY0, iVar2.f4481x) ^ (-9223372034707292160L));
        }
    }
}
