package j1;

import java.util.ArrayList;
import java.util.List;
import p1.j1;
import p1.y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0.o f3248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k0.l f3249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j.m f3250d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y0 f3251e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i f3252f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3253g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3254h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3255i;

    public g(r0.o oVar) {
        super(0);
        this.f3248b = oVar;
        k0.l lVar = new k0.l();
        lVar.f3378b = new long[2];
        this.f3249c = lVar;
        this.f3250d = new j.m(2);
        this.f3254h = true;
        this.f3255i = true;
    }

    /* JADX WARN: Code duplicated, block: B:139:0x0290  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v0, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v25, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [int] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [h0.e] */
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
    @Override // j1.h
    public final boolean a(j.m mVar, n1.p pVar, a2.h hVar, boolean z2) {
        j.m mVar2;
        k0.l lVar;
        Object obj;
        boolean z3;
        boolean z4;
        i iVar;
        int i5;
        boolean z5;
        boolean zA = super.a(mVar, pVar, hVar, z2);
        ?? E = this.f3248b;
        boolean z6 = true;
        if (!E.f5693p) {
            return true;
        }
        ?? eVar = 0;
        while (E != 0) {
            if (E instanceof j1) {
                this.f3251e = p1.f.q((j1) E, 16);
            } else if ((E.f5683f & 16) != 0 && (E instanceof p1.m)) {
                r0.o oVar = ((p1.m) E).f4921r;
                int i6 = 0;
                while (oVar != null) {
                    if ((oVar.f5683f & 16) != 0) {
                        i6++;
                        if (i6 == 1) {
                            E = E;
                            eVar = eVar;
                            eVar = eVar;
                            E = oVar;
                        } else {
                            if (eVar == 0) {
                                eVar = new h0.e(new r0.o[16]);
                            }
                            if (E != 0) {
                                eVar.b(E);
                                E = 0;
                            }
                            eVar.b(oVar);
                        }
                    } else {
                        E = E;
                        eVar = eVar;
                    }
                    oVar = oVar.f5686i;
                    E = E;
                    eVar = eVar;
                }
                if (i6 == 1) {
                    E = E;
                    eVar = eVar;
                } else {
                    E = E;
                    eVar = eVar;
                }
            }
            E = p1.f.e(eVar);
        }
        int iC = mVar.c();
        int i7 = 0;
        while (true) {
            mVar2 = this.f3250d;
            lVar = this.f3249c;
            if (i7 >= iC) {
                break;
            }
            long jA = mVar.a(i7);
            u uVar = (u) mVar.d(i7);
            if (lVar.b(jA)) {
                boolean z7 = z6;
                long j5 = uVar.f3280g;
                List list = uVar.f3284k;
                long j6 = uVar.f3276c;
                if (x0.c.f(j5) && x0.c.f(j6)) {
                    z5 = z7;
                    q3.s sVar = q3.s.f5477d;
                    ArrayList arrayList = new ArrayList((list == null ? sVar : list).size());
                    if (list == null) {
                        list = sVar;
                    }
                    int size = list.size();
                    int i8 = 0;
                    while (i8 < size) {
                        int i9 = size;
                        d dVar = (d) list.get(i8);
                        long j7 = jA;
                        List list2 = list;
                        long j8 = dVar.f3230b;
                        if (x0.c.f(j8)) {
                            long j9 = dVar.f3229a;
                            y0 y0Var = this.f3251e;
                            b4.l.b(y0Var);
                            arrayList.add(new d(j9, y0Var.T0(pVar, j8), dVar.f3231c));
                        }
                        i8++;
                        list = list2;
                        size = i9;
                        jA = j7;
                        uVar = uVar;
                    }
                    y0 y0Var2 = this.f3251e;
                    b4.l.b(y0Var2);
                    long jT0 = y0Var2.T0(pVar, j5);
                    y0 y0Var3 = this.f3251e;
                    b4.l.b(y0Var3);
                    u uVar2 = new u(uVar.f3274a, uVar.f3275b, y0Var3.T0(pVar, j6), uVar.f3277d, uVar.f3278e, uVar.f3279f, jT0, uVar.f3281h, uVar.f3282i, arrayList, uVar.f3283j, uVar.f3285l);
                    uVar2.f3286m = uVar.f3286m;
                    mVar2.b(jA, uVar2);
                } else {
                    z5 = z7;
                }
            } else {
                z5 = z6;
            }
            i7++;
            z6 = z5;
            zA = zA;
            iC = iC;
        }
        boolean z8 = zA;
        boolean z9 = z6;
        if (mVar2.c() == 0) {
            lVar.f3377a = 0;
            this.f3256a.g();
            return z9;
        }
        int i10 = lVar.f3377a;
        while (true) {
            i10--;
            if (-1 >= i10) {
                break;
            }
            long j10 = ((long[]) lVar.f3378b)[i10];
            if (mVar.f3118d) {
                int i11 = mVar.f3121g;
                long[] jArr = mVar.f3119e;
                Object[] objArr = mVar.f3120f;
                int i12 = 0;
                for (int i13 = 0; i13 < i11; i13++) {
                    Object obj2 = objArr[i13];
                    if (obj2 != j.n.f3122a) {
                        if (i13 != i12) {
                            jArr[i12] = jArr[i13];
                            objArr[i12] = obj2;
                            objArr[i13] = null;
                        }
                        i12++;
                    }
                }
                mVar.f3118d = false;
                mVar.f3121g = i12;
            }
            if (k.a.b(mVar.f3119e, mVar.f3121g, j10) < 0) {
                lVar.c(i10);
            }
        }
        ArrayList arrayList2 = new ArrayList(mVar2.c());
        int iC2 = mVar2.c();
        for (int i14 = 0; i14 < iC2; i14++) {
            arrayList2.add(mVar2.d(i14));
        }
        i iVar2 = new i(arrayList2, hVar);
        int size2 = arrayList2.size();
        int i15 = 0;
        while (true) {
            if (i15 >= size2) {
                obj = null;
                break;
            }
            obj = arrayList2.get(i15);
            if (hVar.m(((u) obj).f3274a)) {
                break;
            }
            i15++;
        }
        u uVar3 = (u) obj;
        if (uVar3 != null) {
            boolean z10 = uVar3.f3277d;
            if (z2) {
                z3 = false;
                if (!this.f3254h && (z10 || uVar3.f3281h)) {
                    y0 y0Var4 = this.f3251e;
                    b4.l.b(y0Var4);
                    this.f3254h = !s.d(uVar3, y0Var4.f4132f);
                }
            } else {
                z3 = false;
                this.f3254h = false;
            }
            boolean z11 = this.f3254h;
            boolean z12 = this.f3253g;
            if (z11 == z12 || !((i5 = iVar2.f3259c) == 3 || i5 == 4 || i5 == 5)) {
                int i16 = iVar2.f3259c;
                if (i16 == 4 && z12 && !this.f3255i) {
                    iVar2.f3259c = 3;
                } else if (i16 == 5 && z11 && z10) {
                    iVar2.f3259c = 3;
                }
            } else {
                iVar2.f3259c = z11 ? 4 : 5;
            }
        } else {
            z3 = false;
        }
        if (!z8 && iVar2.f3259c == 3 && (iVar = this.f3252f) != null) {
            ?? r1 = iVar.f3257a;
            int size3 = r1.size();
            ?? r4 = iVar2.f3257a;
            if (size3 != r4.size()) {
                z4 = z9;
                break;
            }
            int size4 = r4.size();
            ?? r5 = z3;
            while (true) {
                if (r5 >= size4) {
                    z4 = z3;
                    break;
                }
                if (!x0.c.b(((u) r1.get(r5)).f3276c, ((u) r4.get(r5)).f3276c)) {
                    z4 = z9;
                    break;
                }
                r5++;
            }
        } else {
            z4 = z9;
            break;
        }
        this.f3252f = iVar2;
        return z4;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // j1.h
    public final void c(a2.h hVar) {
        super.c(hVar);
        i iVar = this.f3252f;
        if (iVar == null) {
            return;
        }
        this.f3253g = this.f3254h;
        ?? r1 = iVar.f3257a;
        int size = r1.size();
        for (int i5 = 0; i5 < size; i5++) {
            u uVar = (u) r1.get(i5);
            boolean z2 = uVar.f3277d;
            long j5 = uVar.f3274a;
            boolean zM = hVar.m(j5);
            boolean z3 = this.f3254h;
            if ((!z2 && !zM) || (!z2 && !z3)) {
                k0.l lVar = this.f3249c;
                int i6 = lVar.f3377a;
                for (int i7 = 0; i7 < i6; i7++) {
                    if (j5 == ((long[]) lVar.f3378b)[i7]) {
                        lVar.c(i7);
                        break;
                    }
                }
            }
        }
        this.f3254h = false;
        this.f3255i = iVar.f3259c == 5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r5v4 */
    public final void f() {
        h0.e eVar = this.f3256a;
        int i5 = eVar.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVar.f2576d;
            int i6 = 0;
            do {
                ((g) objArr[i6]).f();
                i6++;
            } while (i6 < i5);
        }
        ?? E = this.f3248b;
        ?? eVar2 = 0;
        while (E != 0) {
            if (E instanceof j1) {
                ((j1) E).W();
            } else if ((E.f5683f & 16) != 0 && (E instanceof p1.m)) {
                r0.o oVar = ((p1.m) E).f4921r;
                int i7 = 0;
                while (oVar != null) {
                    if ((oVar.f5683f & 16) != 0) {
                        i7++;
                        if (i7 == 1) {
                            E = E;
                            eVar2 = eVar2;
                            eVar2 = eVar2;
                            E = oVar;
                        } else {
                            if (eVar2 == 0) {
                                eVar2 = new h0.e(new r0.o[16]);
                            }
                            if (E != 0) {
                                eVar2.b(E);
                                E = 0;
                            }
                            eVar2.b(oVar);
                        }
                    } else {
                        E = E;
                        eVar2 = eVar2;
                    }
                    oVar = oVar.f5686i;
                    E = E;
                    eVar2 = eVar2;
                }
                if (i7 == 1) {
                    E = E;
                    eVar2 = eVar2;
                } else {
                    E = E;
                    eVar2 = eVar2;
                }
            }
            E = p1.f.e(eVar2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:4:0x000a  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final boolean g(a2.h hVar) {
        boolean z2;
        h0.e eVar;
        int i5;
        j.m mVar = this.f3250d;
        if (mVar.c() == 0) {
            z2 = false;
        } else {
            r0.o oVar = this.f3248b;
            if (oVar.f5693p) {
                i iVar = this.f3252f;
                b4.l.b(iVar);
                y0 y0Var = this.f3251e;
                b4.l.b(y0Var);
                long j5 = y0Var.f4132f;
                ?? E = oVar;
                ?? eVar2 = 0;
                while (true) {
                    z2 = true;
                    if (E == 0) {
                        break;
                    }
                    if (E instanceof j1) {
                        ((j1) E).y(iVar, j.f3262f, j5);
                    } else if ((E.f5683f & 16) != 0 && (E instanceof p1.m)) {
                        r0.o oVar2 = ((p1.m) E).f4921r;
                        int i6 = 0;
                        while (oVar2 != null) {
                            if ((oVar2.f5683f & 16) != 0) {
                                i6++;
                                if (i6 == 1) {
                                    E = E;
                                    eVar2 = eVar2;
                                    eVar2 = eVar2;
                                    E = oVar2;
                                } else {
                                    if (eVar2 == 0) {
                                        eVar2 = new h0.e(new r0.o[16]);
                                    }
                                    if (E != 0) {
                                        eVar2.b(E);
                                        E = 0;
                                    }
                                    eVar2.b(oVar2);
                                }
                            } else {
                                E = E;
                                eVar2 = eVar2;
                            }
                            oVar2 = oVar2.f5686i;
                            E = E;
                            eVar2 = eVar2;
                        }
                        if (i6 == 1) {
                            E = E;
                            eVar2 = eVar2;
                        } else {
                            E = E;
                            eVar2 = eVar2;
                        }
                    }
                    E = p1.f.e(eVar2);
                }
                if (oVar.f5693p && (i5 = (eVar = this.f3256a).f2578f) > 0) {
                    Object[] objArr = eVar.f2576d;
                    int i7 = 0;
                    do {
                        ((g) objArr[i7]).g(hVar);
                        i7++;
                    } while (i7 < i5);
                }
            } else {
                z2 = false;
            }
        }
        c(hVar);
        int i8 = mVar.f3121g;
        Object[] objArr2 = mVar.f3120f;
        for (int i9 = 0; i9 < i8; i9++) {
            objArr2[i9] = null;
        }
        mVar.f3121g = 0;
        mVar.f3118d = false;
        this.f3251e = null;
        return z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r0v3, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public final boolean h(a2.h hVar, boolean z2) {
        h0.e eVar;
        int i5;
        if (this.f3250d.c() == 0) {
            return false;
        }
        ?? E = this.f3248b;
        if (!E.f5693p) {
            return false;
        }
        i iVar = this.f3252f;
        b4.l.b(iVar);
        y0 y0Var = this.f3251e;
        b4.l.b(y0Var);
        long j5 = y0Var.f4132f;
        ?? E2 = E;
        ?? eVar2 = 0;
        while (E2 != 0) {
            if (E2 instanceof j1) {
                ((j1) E2).y(iVar, j.f3260d, j5);
            } else if ((E2.f5683f & 16) != 0 && (E2 instanceof p1.m)) {
                r0.o oVar = ((p1.m) E2).f4921r;
                int i6 = 0;
                while (oVar != null) {
                    if ((oVar.f5683f & 16) != 0) {
                        i6++;
                        if (i6 == 1) {
                            E2 = E2;
                            eVar2 = eVar2;
                            eVar2 = eVar2;
                            E2 = oVar;
                        } else {
                            if (eVar2 == 0) {
                                eVar2 = new h0.e(new r0.o[16]);
                            }
                            if (E2 != 0) {
                                eVar2.b(E2);
                                E2 = 0;
                            }
                            eVar2.b(oVar);
                        }
                    } else {
                        E2 = E2;
                        eVar2 = eVar2;
                    }
                    oVar = oVar.f5686i;
                    E2 = E2;
                    eVar2 = eVar2;
                }
                if (i6 == 1) {
                    E2 = E2;
                    eVar2 = eVar2;
                } else {
                    E2 = E2;
                    eVar2 = eVar2;
                }
            }
            E2 = p1.f.e(eVar2);
        }
        if (E.f5693p && (i5 = (eVar = this.f3256a).f2578f) > 0) {
            Object[] objArr = eVar.f2576d;
            int i7 = 0;
            do {
                g gVar = (g) objArr[i7];
                b4.l.b(this.f3251e);
                gVar.h(hVar, z2);
                i7++;
            } while (i7 < i5);
        }
        if (E.f5693p) {
            ?? eVar3 = 0;
            while (E != 0) {
                if (E instanceof j1) {
                    ((j1) E).y(iVar, j.f3261e, j5);
                } else if ((E.f5683f & 16) != 0 && (E instanceof p1.m)) {
                    r0.o oVar2 = ((p1.m) E).f4921r;
                    int i8 = 0;
                    while (oVar2 != null) {
                        if ((oVar2.f5683f & 16) != 0) {
                            i8++;
                            if (i8 == 1) {
                                E = E;
                                eVar3 = eVar3;
                                eVar3 = eVar3;
                                E = oVar2;
                            } else {
                                if (eVar3 == 0) {
                                    eVar3 = new h0.e(new r0.o[16]);
                                }
                                if (E != 0) {
                                    eVar3.b(E);
                                    E = 0;
                                }
                                eVar3.b(oVar2);
                            }
                        } else {
                            E = E;
                            eVar3 = eVar3;
                        }
                        oVar2 = oVar2.f5686i;
                        E = E;
                        eVar3 = eVar3;
                    }
                    if (i8 == 1) {
                        E = E;
                        eVar3 = eVar3;
                    } else {
                        E = E;
                        eVar3 = eVar3;
                    }
                }
                E = p1.f.e(eVar3);
            }
        }
        return true;
    }

    public final void i(long j5, j.y yVar) {
        k0.l lVar = this.f3249c;
        int i5 = 0;
        if (lVar.b(j5)) {
            int i6 = 0;
            Object[] objArr = yVar.f3174a;
            int i7 = yVar.f3175b;
            while (true) {
                if (i6 >= i7) {
                    i6 = -1;
                    break;
                } else if (equals(objArr[i6])) {
                    break;
                } else {
                    i6++;
                }
            }
            if (i6 < 0) {
                int i8 = lVar.f3377a;
                for (int i9 = 0; i9 < i8; i9++) {
                    if (j5 == ((long[]) lVar.f3378b)[i9]) {
                        lVar.c(i9);
                        break;
                    }
                }
                j.m mVar = this.f3250d;
                int iB = k.a.b(mVar.f3119e, mVar.f3121g, j5);
                if (iB >= 0) {
                    Object[] objArr2 = mVar.f3120f;
                    Object obj = objArr2[iB];
                    Object obj2 = j.n.f3122a;
                    if (obj != obj2) {
                        objArr2[iB] = obj2;
                        mVar.f3118d = true;
                    }
                }
            }
        }
        h0.e eVar = this.f3256a;
        int i10 = eVar.f2578f;
        if (i10 > 0) {
            Object[] objArr3 = eVar.f2576d;
            do {
                ((g) objArr3[i5]).i(j5, yVar);
                i5++;
            } while (i5 < i10);
        }
    }

    public final String toString() {
        return "Node(pointerInputFilter=" + this.f3248b + ", children=" + this.f3256a + ", pointerIds=" + this.f3249c + ')';
    }
}
