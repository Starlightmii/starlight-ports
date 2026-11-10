package f0;

import android.os.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v implements p {
    public int A;
    public int B;
    public boolean C;
    public final u D;
    public final ArrayList E;
    public boolean F;
    public s2 G;
    public t2 H;
    public w2 I;
    public boolean J;
    public w1 K;
    public g0.a L;
    public final g0.b M;
    public a N;
    public g0.c O;
    public final q0.d P;
    public final t3.h Q;
    public boolean R;
    public long S;
    public d0 T;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1.b f2354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b0 f2355b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final t2 f2356c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j.c0 f2357d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g0.a f2358e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final g0.a f2359f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a1.d f2360g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e0 f2361h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public v1 f2363j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2364k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2365l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2366m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int[] f2368o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public j.p f2369p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2370q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2371r;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public j.r f2375v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f2376w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f2378y;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f2362i = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w0 f2367n = new w0();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f2372s = new ArrayList();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final w0 f2373t = new w0();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public w1 f2374u = n0.h.f4069g;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final w0 f2377x = new w0();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f2379z = -1;

    public v(a1.b bVar, b0 b0Var, t2 t2Var, j.c0 c0Var, g0.a aVar, g0.a aVar2, a1.d dVar, e0 e0Var) {
        this.f2354a = bVar;
        this.f2355b = b0Var;
        this.f2356c = t2Var;
        this.f2357d = c0Var;
        this.f2358e = aVar;
        this.f2359f = aVar2;
        this.f2360g = dVar;
        this.f2361h = e0Var;
        this.C = b0Var.e() || b0Var.c();
        this.D = new u(0, this);
        this.E = new ArrayList();
        s2 s2VarC = t2Var.c();
        s2VarC.c();
        this.G = s2VarC;
        t2 t2Var2 = new t2();
        if (b0Var.e()) {
            t2Var2.b();
        }
        if (b0Var.c()) {
            t2Var2.f2343n = new j.r();
        }
        this.H = t2Var2;
        w2 w2VarD = t2Var2.d();
        w2VarD.e(true);
        this.I = w2VarD;
        this.M = new g0.b(this, aVar);
        s2 s2VarC2 = this.H.c();
        try {
            a aVarA = s2VarC2.a(0);
            s2VarC2.c();
            this.N = aVarA;
            this.O = new g0.c();
            this.P = new q0.d(this);
            t3.h hVarI = b0Var.i();
            t3.h hVarY = y();
            this.Q = hVarI.k(hVarY == null ? t3.i.f5807d : hVarY);
        } catch (Throwable th) {
            s2VarC2.c();
            throw th;
        }
    }

    public static final int M(v vVar, int i5, boolean z2, int i6) {
        s2 s2Var = vVar.G;
        if (s2Var.j(i5)) {
            int i7 = s2Var.i(i5);
            Object objP = s2Var.p(s2Var.f2306b, i5);
            if (i7 == 206 && b4.l.a(objP, z.f2433e)) {
                Object objH = s2Var.h(i5, 0);
                o2 o2Var = objH instanceof o2 ? (o2) objH : null;
                n2 n2Var = o2Var != null ? o2Var.f2285a : null;
                s sVar = n2Var instanceof s ? (s) n2Var : null;
                if (sVar != null) {
                    for (v vVar2 : sVar.f2302d.f2323e) {
                        t2 t2Var = vVar2.f2356c;
                        if (t2Var.f2334e > 0 && (t2Var.f2333d[1] & 67108864) != 0) {
                            e0 e0Var = vVar2.f2361h;
                            synchronized (e0Var.f2133g) {
                                e0Var.o();
                                j.z zVar = e0Var.f2143q;
                                e0Var.f2143q = d4.a.o();
                                try {
                                    e0Var.f2149w.b0(zVar);
                                } catch (Throwable th) {
                                    e0Var.f2143q = zVar;
                                    throw th;
                                }
                            }
                            g0.a aVar = new g0.a();
                            vVar2.L = aVar;
                            s2 s2VarC = vVar2.f2356c.c();
                            try {
                                vVar2.G = s2VarC;
                                g0.b bVar = vVar2.M;
                                g0.a aVar2 = bVar.f2457b;
                                try {
                                    bVar.f2457b = aVar;
                                    vVar2.L(0);
                                    g0.b bVar2 = vVar2.M;
                                    bVar2.b();
                                    if (bVar2.f2458c) {
                                        bVar2.f2457b.f2454k.S(g0.b0.f2468c);
                                        if (bVar2.f2458c) {
                                            bVar2.d(false);
                                            bVar2.d(false);
                                            bVar2.f2457b.f2454k.S(g0.m.f2494c);
                                            bVar2.f2458c = false;
                                        }
                                    }
                                    bVar.f2457b = aVar2;
                                    s2VarC.c();
                                } catch (Throwable th2) {
                                    bVar.f2457b = aVar2;
                                    throw th2;
                                }
                            } catch (Throwable th3) {
                                s2VarC.c();
                                throw th3;
                            }
                        }
                        vVar.f2355b.o(vVar2.f2361h);
                    }
                }
                return s2Var.o(i5);
            }
            if (!s2Var.l(i5)) {
                return s2Var.o(i5);
            }
        } else if (s2Var.d(i5)) {
            int i8 = s2Var.f2306b[(i5 * 5) + 3] + i5;
            int iM = 0;
            for (int i9 = i5 + 1; i9 < i8; i9 += s2Var.f2306b[(i9 * 5) + 3]) {
                boolean zL = s2Var.l(i9);
                if (zL) {
                    vVar.M.c();
                    g0.b bVar3 = vVar.M;
                    Object objN = s2Var.n(i9);
                    bVar3.c();
                    bVar3.f2463h.add(objN);
                }
                iM += M(vVar, i9, zL || z2, zL ? 0 : i6 + iM);
                if (zL) {
                    vVar.M.c();
                    vVar.M.a();
                }
            }
            if (!s2Var.l(i5)) {
                return iM;
            }
        } else if (!s2Var.l(i5)) {
            return s2Var.o(i5);
        }
        return 1;
    }

    public final void A(ArrayList arrayList) {
        g0.a aVar = this.f2359f;
        g0.b bVar = this.M;
        g0.a aVar2 = bVar.f2457b;
        try {
            bVar.f2457b = aVar;
            aVar.f2454k.S(g0.z.f2511c);
            if (arrayList.size() <= 0) {
                bVar.b();
                bVar.f2457b.f2454k.S(g0.n.f2495c);
                bVar.f2461f = 0;
                bVar.f2457b = aVar2;
                return;
            }
            p3.f fVar = (p3.f) arrayList.get(0);
            g1 g1Var = (g1) fVar.f5014d;
            g1Var.getClass();
            throw null;
        } catch (Throwable th) {
            bVar.f2457b = aVar2;
            throw th;
        }
    }

    public final void B(w1 w1Var, Object obj) {
        R(126665345, 0, null, null);
        C();
        g0(obj);
        long j5 = this.S;
        try {
            this.S = 126665345;
            if (this.R) {
                w2.z(this.I);
            }
            boolean z2 = (this.R || b4.l.a(this.G.f(), w1Var)) ? false : true;
            if (z2) {
                I(w1Var);
            }
            R(202, 0, z.f2431c, w1Var);
            this.K = null;
            this.f2376w = z2;
            throw null;
        } catch (Throwable th) {
            try {
                p3.p.D(th, new r(this, 2));
                throw th;
            } catch (Throwable th2) {
                p(false);
                this.K = null;
                this.S = j5;
                p(false);
                throw th2;
            }
        }
    }

    public final Object C() {
        boolean z2 = this.R;
        h hVar = o.f2281a;
        if (!z2) {
            Object objM = this.G.m();
            if (!this.f2378y || (objM instanceof r2)) {
                return objM;
            }
        } else if (this.f2371r) {
            z.a("A call to createNode(), emitNode() or useNode() expected");
            return hVar;
        }
        return hVar;
    }

    public final List D() {
        b0 b0Var = this.f2355b;
        a0 a0VarG = b0Var.g();
        e0 e0Var = a0VarG != null ? (e0) a0VarG : null;
        if (e0Var != null) {
            t2 t2Var = e0Var.f2135i;
            s2 s2VarC = t2Var.c();
            try {
                Integer numV = p3.j.v(s2VarC, b0Var, 0, s2VarC.f2307c);
                s2VarC.c();
                if (numV != null) {
                    s2 s2VarC2 = t2Var.c();
                    try {
                        return q3.l.e0(p3.j.P(s2VarC2, numV.intValue(), 0), e0Var.f2149w.D());
                    } finally {
                        s2VarC2.c();
                    }
                }
            } catch (Throwable th) {
                s2VarC.c();
                throw th;
            }
        }
        return q3.s.f5477d;
    }

    public final int E(int i5) {
        int iQ = this.G.q(i5) + 1;
        int i6 = 0;
        while (iQ < i5) {
            if (!this.G.k(iQ)) {
                i6++;
            }
            iQ += v2.a(this.G.f2306b, iQ);
        }
        return i6;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0059 A[Catch: all -> 0x0024, TRY_LEAVE, TryCatch #0 {all -> 0x0024, blocks: (B:3:0x0005, B:6:0x0012, B:8:0x0020, B:12:0x0029, B:11:0x0026, B:15:0x0030, B:18:0x0038, B:21:0x0040, B:23:0x0048, B:25:0x004e, B:26:0x0052, B:27:0x0053, B:29:0x0059, B:22:0x0044), top: B:34:0x0005, inners: #1 }] */
    public final Object F(e0 e0Var, e0 e0Var2, Integer num, List list, a4.a aVar) {
        Object objA;
        boolean z2 = this.F;
        int i5 = this.f2364k;
        try {
            this.F = true;
            this.f2364k = 0;
            int size = list.size();
            for (int i6 = 0; i6 < size; i6++) {
                p3.f fVar = (p3.f) list.get(i6);
                b2 b2Var = (b2) fVar.f5014d;
                Object obj = fVar.f5015e;
                if (obj != null) {
                    a0(b2Var, obj);
                } else {
                    a0(b2Var, null);
                }
            }
            if (e0Var == null) {
                objA = aVar.a();
            } else {
                int iIntValue = num != null ? num.intValue() : -1;
                if (e0Var2 == null || e0Var2.equals(e0Var) || iIntValue < 0) {
                    objA = aVar.a();
                } else {
                    e0Var.f2145s = e0Var2;
                    e0Var.f2146t = iIntValue;
                    try {
                        objA = aVar.a();
                        e0Var.f2145s = null;
                        e0Var.f2146t = 0;
                    } catch (Throwable th) {
                        e0Var.f2145s = null;
                        e0Var.f2146t = 0;
                        throw th;
                    }
                }
                if (objA == null) {
                    objA = aVar.a();
                }
            }
            this.F = z2;
            this.f2364k = i5;
            return objA;
        } catch (Throwable th2) {
            this.F = z2;
            this.f2364k = i5;
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x003a  */
    /* JADX WARN: Code duplicated, block: B:197:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x00f0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x00f2 A[LOOP:7: B:34:0x00b1->B:50:0x00f2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:51:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:54:0x0106  */
    /* JADX WARN: Code duplicated, block: B:61:0x0131  */
    /* JADX WARN: Code duplicated, block: B:62:0x0133  */
    /* JADX WARN: Code duplicated, block: B:65:0x0138  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:66:0x0144
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:162)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public final void G() {
        /*
            Method dump skipped, instruction units count: 882
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f0.v.G():void");
    }

    public final void H() {
        int i5;
        L(this.G.f2311g);
        g0.b bVar = this.M;
        bVar.d(false);
        w0 w0Var = bVar.f2459d;
        v vVar = bVar.f2456a;
        s2 s2Var = vVar.G;
        if (s2Var.f2307c > 0 && w0Var.a(-2) != (i5 = s2Var.f2313i)) {
            if (!bVar.f2458c && bVar.f2460e) {
                bVar.d(false);
                bVar.f2457b.f2454k.S(g0.q.f2498c);
                bVar.f2458c = true;
            }
            if (i5 > 0) {
                a aVarA = s2Var.a(i5);
                w0Var.c(i5);
                bVar.d(false);
                g0.k0 k0Var = bVar.f2457b.f2454k;
                k0Var.S(g0.p.f2497c);
                a.a.L(k0Var, 0, aVarA);
                bVar.f2458c = true;
            }
        }
        bVar.f2457b.f2454k.S(g0.x.f2509c);
        int i6 = bVar.f2461f;
        s2 s2Var2 = vVar.G;
        bVar.f2461f = s2Var2.f2306b[(s2Var2.f2311g * 5) + 3] + i6;
    }

    public final void I(w1 w1Var) {
        j.r rVar = this.f2375v;
        if (rVar == null) {
            rVar = new j.r();
            this.f2375v = rVar;
        }
        rVar.g(this.G.f2311g, w1Var);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001a  */
    public final void J(int i5, int i6, int i7) {
        s2 s2Var = this.G;
        if (i5 == i6) {
            i7 = i5;
        } else if (i5 != i7 && i6 != i7) {
            if (s2Var.q(i5) == i6) {
                i7 = i6;
            } else if (s2Var.q(i6) == i5) {
                i7 = i5;
            } else if (s2Var.q(i5) == s2Var.q(i6)) {
                i7 = s2Var.q(i5);
            } else {
                int iQ = i5;
                int i8 = 0;
                while (iQ > 0 && iQ != i7) {
                    iQ = s2Var.q(iQ);
                    i8++;
                }
                int iQ2 = i6;
                int i9 = 0;
                while (iQ2 > 0 && iQ2 != i7) {
                    iQ2 = s2Var.q(iQ2);
                    i9++;
                }
                int i10 = i8 - i9;
                int iQ3 = i5;
                for (int i11 = 0; i11 < i10; i11++) {
                    iQ3 = s2Var.q(iQ3);
                }
                int i12 = i9 - i8;
                int iQ4 = i6;
                for (int i13 = 0; i13 < i12; i13++) {
                    iQ4 = s2Var.q(iQ4);
                }
                i7 = iQ3;
                for (int iQ5 = iQ4; i7 != iQ5; iQ5 = s2Var.q(iQ5)) {
                    i7 = s2Var.q(i7);
                }
            }
        }
        while (i5 > 0 && i5 != i7) {
            if (s2Var.l(i5)) {
                this.M.a();
            }
            i5 = s2Var.q(i5);
        }
        o(i6, i7);
    }

    public final Object K() {
        boolean z2 = this.R;
        h hVar = o.f2281a;
        if (!z2) {
            Object objM = this.G.m();
            if (!this.f2378y || (objM instanceof r2)) {
                return objM instanceof o2 ? ((o2) objM).f2285a : objM;
            }
        } else if (this.f2371r) {
            z.a("A call to createNode(), emitNode() or useNode() expected");
            return hVar;
        }
        return hVar;
    }

    public final void L(int i5) {
        boolean zL = this.G.l(i5);
        g0.b bVar = this.M;
        if (zL) {
            bVar.c();
            Object objN = this.G.n(i5);
            bVar.c();
            bVar.f2463h.add(objN);
        }
        M(this, i5, zL, 0);
        bVar.c();
        if (zL) {
            bVar.a();
        }
    }

    public final boolean N(int i5, boolean z2) {
        return ((i5 & 1) == 0 && (this.R || this.f2378y)) || z2 || !z();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:30:0x00af  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:41:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ff  */
    public final void O() {
        int i5;
        long jRotateLeft;
        long jRotateLeft2;
        if (this.f2372s.isEmpty()) {
            this.f2365l = this.G.s() + this.f2365l;
            return;
        }
        s2 s2Var = this.G;
        int iG = s2Var.g();
        int[] iArr = s2Var.f2306b;
        int i6 = s2Var.f2311g;
        Object objP = i6 < s2Var.f2312h ? s2Var.p(iArr, i6) : null;
        Object objF = s2Var.f();
        int i7 = this.f2366m;
        h hVar = o.f2281a;
        if (objP == null) {
            if (objF == null || iG != 207 || objF.equals(hVar)) {
                jRotateLeft2 = Long.rotateLeft(Long.rotateLeft(this.S, 3) ^ ((long) iG), 3) ^ ((long) i7);
            } else {
                this.S = Long.rotateLeft(((long) objF.hashCode()) ^ Long.rotateLeft(this.S, 3), 3) ^ ((long) i7);
            }
            U(null, (iArr[(s2Var.f2311g * 5) + 1] & 1073741824) != 0);
            G();
            s2Var.e();
            if (objP != null) {
                if (objP instanceof Enum) {
                    this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) 0), 3) ^ ((long) ((Enum) objP).ordinal()), 3);
                } else {
                    this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) 0), 3) ^ ((long) objP.hashCode()), 3);
                }
            }
            if (objF == null && iG == 207 && !objF.equals(hVar)) {
                this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) i7), 3) ^ ((long) objF.hashCode()), 3);
                return;
            } else {
                this.S = Long.rotateRight(((long) iG) ^ Long.rotateRight(this.S ^ ((long) i7), 3), 3);
            }
        }
        if (objP instanceof Enum) {
            jRotateLeft = Long.rotateLeft(((long) ((Enum) objP).ordinal()) ^ Long.rotateLeft(this.S, 3), 3);
            i5 = 0;
        } else {
            i5 = 0;
            jRotateLeft = Long.rotateLeft(((long) objP.hashCode()) ^ Long.rotateLeft(this.S, 3), 3);
        }
        jRotateLeft2 = jRotateLeft ^ ((long) i5);
        this.S = jRotateLeft2;
        U(null, (iArr[(s2Var.f2311g * 5) + 1] & 1073741824) != 0);
        G();
        s2Var.e();
        if (objP != null) {
            if (objF == null) {
            }
            this.S = Long.rotateRight(((long) iG) ^ Long.rotateRight(this.S ^ ((long) i7), 3), 3);
        } else if (objP instanceof Enum) {
            this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) 0), 3) ^ ((long) ((Enum) objP).ordinal()), 3);
        } else {
            this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) 0), 3) ^ ((long) objP.hashCode()), 3);
        }
    }

    public final void P() {
        s2 s2Var = this.G;
        int i5 = s2Var.f2313i;
        this.f2365l = i5 >= 0 ? s2Var.f2306b[(i5 * 5) + 1] & 67108863 : 0;
        s2Var.t();
    }

    public final void Q() {
        if (this.f2365l != 0) {
            z.a("No nodes can be emitted before calling skipAndEndGroup");
        }
        if (this.R) {
            return;
        }
        b2 b2VarW = w();
        if (b2VarW != null) {
            int i5 = b2VarW.f2096b;
            if ((i5 & 128) == 0) {
                b2VarW.f2096b = i5 | 16;
            }
        }
        if (this.f2372s.isEmpty()) {
            P();
        } else {
            G();
        }
    }

    /* JADX WARN: Code duplicated, block: B:172:0x0327  */
    /* JADX WARN: Code duplicated, block: B:175:0x033d  */
    /* JADX WARN: Code duplicated, block: B:178:0x0358  */
    /* JADX WARN: Code duplicated, block: B:179:0x035e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:180:0x0360 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:182:0x0364  */
    /* JADX WARN: Code duplicated, block: B:184:0x036b  */
    /* JADX WARN: Code duplicated, block: B:186:0x036e  */
    /* JADX WARN: Code duplicated, block: B:187:0x0370  */
    /* JADX WARN: Code duplicated, block: B:191:0x039e  */
    /* JADX WARN: Code duplicated, block: B:192:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:22:0x0074  */
    /* JADX WARN: Code duplicated, block: B:24:0x007b  */
    /* JADX WARN: Code duplicated, block: B:25:0x007d  */
    /* JADX WARN: Code duplicated, block: B:28:0x0084  */
    /* JADX WARN: Code duplicated, block: B:30:0x0091  */
    /* JADX WARN: Code duplicated, block: B:31:0x0095 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x0097 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x0099  */
    /* JADX WARN: Code duplicated, block: B:35:0x009e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:43:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:48:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:51:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:56:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:57:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:60:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:61:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:65:0x010c  */
    /* JADX WARN: Code duplicated, block: B:68:0x0112  */
    /* JADX WARN: Code duplicated, block: B:70:0x0126  */
    /* JADX WARN: Code duplicated, block: B:71:0x012a  */
    /* JADX WARN: Code duplicated, block: B:76:0x014e  */
    /* JADX WARN: Code duplicated, block: B:78:0x0156  */
    /* JADX WARN: Code duplicated, block: B:79:0x0160  */
    /* JADX WARN: Code duplicated, block: B:82:0x0174  */
    /* JADX WARN: Code duplicated, block: B:83:0x0176  */
    /* JADX WARN: Code duplicated, block: B:85:0x017a  */
    /* JADX WARN: Code duplicated, block: B:87:0x0187  */
    /* JADX WARN: Code duplicated, block: B:90:0x018f  */
    /* JADX WARN: Code duplicated, block: B:92:0x0198  */
    public final void R(int i5, int i6, Object obj, Object obj2) {
        long jRotateLeft;
        long j5;
        boolean z2;
        boolean z3;
        boolean z4;
        v1 v1Var;
        v1 v1Var2;
        ArrayList arrayList;
        j.r rVar;
        int i7;
        Object objValueOf;
        j.z zVar;
        Object objE;
        j.y yVar;
        w2 w2Var;
        int i8;
        Object obj3;
        int i9;
        int i10;
        Object[] objArr;
        Object[] objArr2;
        int i11;
        int i12;
        int i13;
        s2 s2Var;
        int[] iArr;
        ArrayList arrayList2;
        int i14;
        int i15;
        int i16;
        s2 s2Var2;
        int i17;
        Object objP;
        w2 w2Var2;
        int i18;
        v1 v1Var3;
        Object obj4 = obj;
        if (this.f2371r) {
            z.a("A call to createNode(), emitNode() or useNode() expected");
        }
        int i19 = this.f2366m;
        Object obj5 = o.f2281a;
        if (obj4 == null) {
            if (obj2 == null || i5 != 207 || obj2.equals(obj5)) {
                jRotateLeft = Long.rotateLeft(Long.rotateLeft(this.S, 3) ^ ((long) i5), 3);
                j5 = i19;
            } else {
                this.S = Long.rotateLeft(Long.rotateLeft(this.S, 3) ^ ((long) obj2.hashCode()), 3) ^ ((long) i19);
            }
            if (obj4 == null) {
                this.f2366m++;
            }
            if (i6 != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.R) {
                this.G.f2315k++;
                w2Var2 = this.I;
                i18 = w2Var2.f2409t;
                if (z2) {
                    w2Var2.Q(i5, obj5, true, obj5);
                } else if (obj2 != null) {
                    if (obj4 == null) {
                        obj4 = obj5;
                    }
                    w2Var2.Q(i5, obj4, false, obj2);
                } else {
                    if (obj4 == null) {
                        obj4 = obj5;
                    }
                    w2Var2.Q(i5, obj4, false, obj5);
                }
                v1Var3 = this.f2363j;
                if (v1Var3 != null) {
                    int i20 = (-2) - i18;
                    a1 a1Var = new a1(-1, i5, i20, -1);
                    v1Var3.f2385e.g(i20, new t0(-1, this.f2364k - v1Var3.f2382b, 0));
                    v1Var3.f2384d.add(a1Var);
                }
                t(z2, null);
                return;
            }
            if (i6 != 1 && this.f2378y) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (this.f2363j == null) {
                int iG = this.G.g();
                if (!z3 && iG == i5) {
                    s2Var2 = this.G;
                    i17 = s2Var2.f2311g;
                    if (i17 < s2Var2.f2312h) {
                        objP = s2Var2.p(s2Var2.f2306b, i17);
                    } else {
                        objP = null;
                    }
                    if (b4.l.a(obj4, objP)) {
                        U(obj2, z2);
                        z4 = z3;
                    }
                }
                s2Var = this.G;
                iArr = s2Var.f2306b;
                arrayList2 = new ArrayList();
                if (s2Var.f2315k <= 0) {
                    i14 = s2Var.f2311g;
                    while (i14 < s2Var.f2312h) {
                        int i21 = i14 * 5;
                        int i22 = iArr[i21];
                        Object objP2 = s2Var.p(iArr, i14);
                        i15 = iArr[i21 + 1];
                        if ((i15 & 1073741824) != 0) {
                            i16 = 1;
                        } else {
                            i16 = i15 & 67108863;
                        }
                        arrayList2.add(new a1(objP2, i22, i14, i16));
                        i14 += iArr[i21 + 3];
                        z3 = z3;
                    }
                }
                z4 = z3;
                this.f2363j = new v1(this.f2364k, arrayList2);
            } else {
                z4 = z3;
            }
            v1Var = this.f2363j;
            if (v1Var != null) {
                arrayList = v1Var.f2384d;
                rVar = v1Var.f2385e;
                i7 = v1Var.f2382b;
                if (obj4 != null) {
                    objValueOf = new z0(Integer.valueOf(i5), obj4);
                } else {
                    objValueOf = Integer.valueOf(i5);
                }
                zVar = ((h0.a) v1Var.f2386f.getValue()).f2569a;
                objE = zVar.e(objValueOf);
                if (objE == null) {
                    objE = null;
                } else if (objE instanceof j.y) {
                    yVar = (j.y) objE;
                    Object objF = yVar.f(0);
                    if (yVar.e()) {
                        zVar.g(objValueOf);
                    }
                    if (yVar.f3175b == 1) {
                        zVar.i(objValueOf, yVar.c());
                    }
                    objE = objF;
                } else {
                    zVar.g(objValueOf);
                }
                a1 a1Var2 = (a1) objE;
                if (!z4 || a1Var2 == null) {
                    this.G.f2315k++;
                    this.R = true;
                    this.K = null;
                    if (this.I.f2412w) {
                        w2 w2VarD = this.H.d();
                        this.I = w2VarD;
                        w2VarD.M();
                        this.J = false;
                        this.K = null;
                    }
                    this.I.d();
                    w2Var = this.I;
                    int i23 = w2Var.f2409t;
                    if (z2) {
                        w2Var.Q(i5, obj5, true, obj5);
                        i8 = 0;
                    } else if (obj2 != null) {
                        if (obj != null) {
                            obj5 = obj;
                        }
                        i8 = 0;
                        w2Var.Q(i5, obj5, false, obj2);
                    } else {
                        i8 = 0;
                        if (obj == null) {
                            obj3 = obj5;
                        } else {
                            obj3 = obj;
                        }
                        w2Var.Q(i5, obj3, false, obj5);
                    }
                    this.N = this.I.b(i23);
                    int i24 = (-2) - i23;
                    a1 a1Var3 = new a1(-1, i5, i24, -1);
                    rVar.g(i24, new t0(-1, this.f2364k - i7, i8));
                    arrayList.add(a1Var3);
                    ArrayList arrayList3 = new ArrayList();
                    if (z2) {
                        i9 = i8;
                    } else {
                        i9 = this.f2364k;
                    }
                    v1Var2 = new v1(i9, arrayList3);
                } else {
                    int i25 = a1Var2.f2085c;
                    arrayList.add(a1Var2);
                    t0 t0Var = (t0) rVar.d(i25);
                    this.f2364k = (t0Var != null ? t0Var.f2327b : -1) + i7;
                    t0 t0Var2 = (t0) rVar.d(i25);
                    int i26 = t0Var2 != null ? t0Var2.f2326a : -1;
                    int i27 = v1Var.f2383c;
                    int i28 = i26 - i27;
                    int i29 = 8;
                    if (i26 <= i27) {
                        i10 = i28;
                        if (i27 > i26) {
                            Object[] objArr3 = rVar.f3140c;
                            long[] jArr = rVar.f3138a;
                            int length = jArr.length - 2;
                            if (length >= 0) {
                                int i30 = 0;
                                while (true) {
                                    long j6 = jArr[i30];
                                    if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i31 = 8 - ((~(i30 - length)) >>> 31);
                                        int i32 = 0;
                                        while (i32 < i31) {
                                            if ((j6 & 255) >= 128) {
                                                objArr2 = objArr3;
                                            } else {
                                                t0 t0Var3 = (t0) objArr3[(i30 << 3) + i32];
                                                int i33 = t0Var3.f2326a;
                                                if (i33 == i26) {
                                                    t0Var3.f2326a = i27;
                                                    objArr2 = objArr3;
                                                } else {
                                                    objArr2 = objArr3;
                                                    if (i26 + 1 <= i33 && i33 < i27) {
                                                        t0Var3.f2326a = i33 - 1;
                                                    }
                                                }
                                            }
                                            j6 >>= 8;
                                            i32++;
                                            objArr3 = objArr2;
                                        }
                                        objArr = objArr3;
                                        if (i31 != 8) {
                                            break;
                                        }
                                    } else {
                                        objArr = objArr3;
                                    }
                                    if (i30 == length) {
                                        break;
                                    }
                                    i30++;
                                    objArr3 = objArr;
                                }
                            }
                        }
                    } else {
                        Object[] objArr4 = rVar.f3140c;
                        long[] jArr2 = rVar.f3138a;
                        int length2 = jArr2.length - 2;
                        if (length2 >= 0) {
                            int i34 = 0;
                            while (true) {
                                long j7 = jArr2[i34];
                                if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                                    int i35 = 8 - ((~(i34 - length2)) >>> 31);
                                    int i36 = 0;
                                    while (i36 < i35) {
                                        if ((j7 & 255) < 128) {
                                            i13 = i29;
                                            t0 t0Var4 = (t0) objArr4[(i34 << 3) + i36];
                                            i12 = i28;
                                            int i37 = t0Var4.f2326a;
                                            if (i37 == i26) {
                                                t0Var4.f2326a = i27;
                                            } else if (i27 <= i37 && i37 < i26) {
                                                t0Var4.f2326a = i37 + 1;
                                            }
                                        } else {
                                            i12 = i28;
                                            i13 = i29;
                                        }
                                        j7 >>= i13;
                                        i36++;
                                        i28 = i12;
                                        i29 = i13;
                                    }
                                    i10 = i28;
                                    if (i35 != i29) {
                                        break;
                                    }
                                } else {
                                    i10 = i28;
                                }
                                if (i34 == length2) {
                                    break;
                                }
                                i34++;
                                i28 = i10;
                                i29 = 8;
                            }
                        } else {
                            i10 = i28;
                        }
                    }
                    g0.b bVar = this.M;
                    int i38 = bVar.f2461f;
                    v vVar = bVar.f2456a;
                    bVar.f2461f = (i25 - vVar.G.f2311g) + i38;
                    this.G.r(i25);
                    if (i10 > 0) {
                        bVar.d(false);
                        w0 w0Var = bVar.f2459d;
                        s2 s2Var3 = vVar.G;
                        if (s2Var3.f2307c > 0 && w0Var.a(-2) != (i11 = s2Var3.f2313i)) {
                            if (!bVar.f2458c && bVar.f2460e) {
                                bVar.d(false);
                                bVar.f2457b.f2454k.S(g0.q.f2498c);
                                bVar.f2458c = true;
                            }
                            if (i11 > 0) {
                                a aVarA = s2Var3.a(i11);
                                w0Var.c(i11);
                                bVar.d(false);
                                g0.k0 k0Var = bVar.f2457b.f2454k;
                                k0Var.S(g0.p.f2497c);
                                a.a.L(k0Var, 0, aVarA);
                                bVar.f2458c = true;
                            }
                        }
                        g0.k0 k0Var2 = bVar.f2457b.f2454k;
                        k0Var2.S(g0.u.f2506c);
                        k0Var2.f2489m[k0Var2.f2490n - k0Var2.f2487k[k0Var2.f2488l - 1].f2483a] = i10;
                    }
                    U(obj2, z2);
                    v1Var2 = null;
                }
            } else {
                v1Var2 = null;
            }
            t(z2, v1Var2);
        }
        jRotateLeft = Long.rotateLeft(Long.rotateLeft(this.S, 3) ^ ((long) (obj4 instanceof Enum ? ((Enum) obj4).ordinal() : obj4.hashCode())), 3);
        j5 = 0;
        this.S = jRotateLeft ^ j5;
        if (obj4 == null) {
            this.f2366m++;
        }
        if (i6 != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.R) {
            this.G.f2315k++;
            w2Var2 = this.I;
            i18 = w2Var2.f2409t;
            if (z2) {
                w2Var2.Q(i5, obj5, true, obj5);
            } else if (obj2 != null) {
                if (obj4 == null) {
                    obj4 = obj5;
                }
                w2Var2.Q(i5, obj4, false, obj2);
            } else {
                if (obj4 == null) {
                    obj4 = obj5;
                }
                w2Var2.Q(i5, obj4, false, obj5);
            }
            v1Var3 = this.f2363j;
            if (v1Var3 != null) {
                int i210 = (-2) - i18;
                a1 a1Var4 = new a1(-1, i5, i210, -1);
                v1Var3.f2385e.g(i210, new t0(-1, this.f2364k - v1Var3.f2382b, 0));
                v1Var3.f2384d.add(a1Var4);
            }
            t(z2, null);
            return;
        }
        if (i6 != 1) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (this.f2363j == null) {
            int iG2 = this.G.g();
            if (!z3) {
                s2Var2 = this.G;
                i17 = s2Var2.f2311g;
                if (i17 < s2Var2.f2312h) {
                    objP = s2Var2.p(s2Var2.f2306b, i17);
                } else {
                    objP = null;
                }
                if (b4.l.a(obj4, objP)) {
                    U(obj2, z2);
                    z4 = z3;
                }
            }
            s2Var = this.G;
            iArr = s2Var.f2306b;
            arrayList2 = new ArrayList();
            if (s2Var.f2315k <= 0) {
                i14 = s2Var.f2311g;
                while (i14 < s2Var.f2312h) {
                    int i211 = i14 * 5;
                    int i212 = iArr[i211];
                    Object objP3 = s2Var.p(iArr, i14);
                    i15 = iArr[i211 + 1];
                    if ((i15 & 1073741824) != 0) {
                        i16 = 1;
                    } else {
                        i16 = i15 & 67108863;
                    }
                    arrayList2.add(new a1(objP3, i212, i14, i16));
                    i14 += iArr[i211 + 3];
                    z3 = z3;
                }
            }
            z4 = z3;
            this.f2363j = new v1(this.f2364k, arrayList2);
        } else {
            z4 = z3;
        }
        v1Var = this.f2363j;
        if (v1Var != null) {
            arrayList = v1Var.f2384d;
            rVar = v1Var.f2385e;
            i7 = v1Var.f2382b;
            if (obj4 != null) {
                objValueOf = new z0(Integer.valueOf(i5), obj4);
            } else {
                objValueOf = Integer.valueOf(i5);
            }
            zVar = ((h0.a) v1Var.f2386f.getValue()).f2569a;
            objE = zVar.e(objValueOf);
            if (objE == null) {
                objE = null;
            } else if (objE instanceof j.y) {
                yVar = (j.y) objE;
                Object objF2 = yVar.f(0);
                if (yVar.e()) {
                    zVar.g(objValueOf);
                }
                if (yVar.f3175b == 1) {
                    zVar.i(objValueOf, yVar.c());
                }
                objE = objF2;
            } else {
                zVar.g(objValueOf);
            }
            a1 a1Var5 = (a1) objE;
            if (z4) {
            }
            this.G.f2315k++;
            this.R = true;
            this.K = null;
            if (this.I.f2412w) {
                w2 w2VarD2 = this.H.d();
                this.I = w2VarD2;
                w2VarD2.M();
                this.J = false;
                this.K = null;
            }
            this.I.d();
            w2Var = this.I;
            int i213 = w2Var.f2409t;
            if (z2) {
                w2Var.Q(i5, obj5, true, obj5);
                i8 = 0;
            } else if (obj2 != null) {
                if (obj != null) {
                    obj5 = obj;
                }
                i8 = 0;
                w2Var.Q(i5, obj5, false, obj2);
            } else {
                i8 = 0;
                if (obj == null) {
                    obj3 = obj5;
                } else {
                    obj3 = obj;
                }
                w2Var.Q(i5, obj3, false, obj5);
            }
            this.N = this.I.b(i213);
            int i214 = (-2) - i213;
            a1 a1Var6 = new a1(-1, i5, i214, -1);
            rVar.g(i214, new t0(-1, this.f2364k - i7, i8));
            arrayList.add(a1Var6);
            ArrayList arrayList4 = new ArrayList();
            if (z2) {
                i9 = i8;
            } else {
                i9 = this.f2364k;
            }
            v1Var2 = new v1(i9, arrayList4);
        } else {
            v1Var2 = null;
        }
        t(z2, v1Var2);
    }

    public final void S() {
        R(-127, 0, null, null);
    }

    public final void T(int i5, m1 m1Var) {
        R(i5, 0, m1Var, null);
    }

    public final void U(Object obj, boolean z2) {
        if (z2) {
            s2 s2Var = this.G;
            if (s2Var.f2315k <= 0) {
                if ((s2Var.f2306b[(s2Var.f2311g * 5) + 1] & 1073741824) == 0) {
                    x1.a("Expected a node group");
                }
                s2Var.u();
                return;
            }
            return;
        }
        if (obj != null && this.G.f() != obj) {
            g0.b bVar = this.M;
            bVar.getClass();
            bVar.d(false);
            g0.k0 k0Var = bVar.f2457b.f2454k;
            k0Var.S(g0.e0.f2475c);
            a.a.L(k0Var, 0, obj);
        }
        this.G.u();
    }

    public final void V(int i5) {
        int i6;
        int i7;
        if (this.f2363j != null) {
            R(i5, 0, null, null);
            return;
        }
        if (this.f2371r) {
            z.a("A call to createNode(), emitNode() or useNode() expected");
        }
        this.S = Long.rotateLeft(Long.rotateLeft(this.S, 3) ^ ((long) i5), 3) ^ ((long) this.f2366m);
        this.f2366m++;
        s2 s2Var = this.G;
        boolean z2 = this.R;
        h hVar = o.f2281a;
        if (z2) {
            s2Var.f2315k++;
            this.I.Q(i5, hVar, false, hVar);
            t(false, null);
            return;
        }
        if (s2Var.g() == i5 && ((i7 = s2Var.f2311g) >= s2Var.f2312h || (s2Var.f2306b[(i7 * 5) + 1] & 536870912) == 0)) {
            s2Var.u();
            t(false, null);
            return;
        }
        if (s2Var.f2315k <= 0 && (i6 = s2Var.f2311g) != s2Var.f2312h) {
            int i8 = this.f2364k;
            H();
            this.M.e(i8, s2Var.s());
            l.h(this.f2372s, i6, s2Var.f2311g);
        }
        s2Var.f2315k++;
        this.R = true;
        this.K = null;
        if (this.I.f2412w) {
            w2 w2VarD = this.H.d();
            this.I = w2VarD;
            w2VarD.M();
            this.J = false;
            this.K = null;
        }
        w2 w2Var = this.I;
        w2Var.d();
        int i9 = w2Var.f2409t;
        w2Var.Q(i5, hVar, false, hVar);
        this.N = w2Var.b(i9);
        t(false, null);
    }

    public final void W(int i5) {
        R(i5, 0, null, null);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0070  */
    public final v X(int i5) {
        b2 b2Var;
        boolean z2;
        V(i5);
        boolean z3 = this.R;
        a1.d dVar = this.f2360g;
        ArrayList arrayList = this.E;
        e0 e0Var = this.f2361h;
        if (z3) {
            b2 b2Var2 = new b2(e0Var);
            arrayList.add(b2Var2);
            g0(b2Var2);
            b2Var2.f2099e = this.B;
            b2Var2.f2096b &= -17;
            dVar.j();
            return this;
        }
        int i6 = this.G.f2313i;
        ArrayList arrayList2 = this.f2372s;
        int iO = l.o(i6, arrayList2);
        x0 x0Var = iO >= 0 ? (x0) arrayList2.remove(iO) : null;
        Object objM = this.G.m();
        if (b4.l.a(objM, o.f2281a)) {
            b2Var = new b2(e0Var);
            g0(b2Var);
        } else {
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl", objM);
            b2Var = (b2) objM;
        }
        if (x0Var == null) {
            int i7 = b2Var.f2096b;
            boolean z4 = (i7 & 64) != 0;
            if (z4) {
                b2Var.f2096b = i7 & (-65);
            }
            if (z4) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = true;
        }
        int i8 = b2Var.f2096b;
        b2Var.f2096b = z2 ? i8 | 8 : i8 & (-9);
        arrayList.add(b2Var);
        b2Var.f2099e = this.B;
        b2Var.f2096b &= -17;
        dVar.j();
        int i9 = b2Var.f2096b;
        if ((i9 & 256) != 0) {
            b2Var.f2096b = (i9 & (-257)) | 512;
            g0.k0 k0Var = this.M.f2457b.f2454k;
            k0Var.S(g0.c0.f2471c);
            a.a.L(k0Var, 0, b2Var);
            if (!this.f2378y) {
                int i10 = b2Var.f2096b;
                if ((i10 & 128) != 0) {
                    this.f2378y = true;
                    b2Var.f2096b = i10 | 1024;
                }
            }
        }
        return this;
    }

    public final void Y() {
        R(125, 2, null, null);
        this.f2371r = true;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void Z() {
        this.f2366m = 0;
        this.G = this.f2356c.c();
        R(100, 0, null, null);
        b0 b0Var = this.f2355b;
        b0Var.p();
        w1 w1VarH = b0Var.h();
        this.f2377x.c(this.f2376w ? 1 : 0);
        this.f2376w = f(w1VarH);
        this.K = null;
        if (!this.f2370q) {
            this.f2370q = b0Var.d();
        }
        if (!this.C) {
            this.C = b0Var.e();
        }
        if (this.C) {
            h3 h3Var = q0.e.f5091a;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>", h3Var);
            w1VarH = ((n0.h) w1VarH).b(h3Var, new i3(y()));
        }
        this.f2374u = w1VarH;
        Set set = (Set) l.t(w1VarH, q0.g.f5093a);
        if (set != null) {
            set.add(v());
            b0Var.m(set);
        }
        R(Long.hashCode(b0Var.f()), 0, null, null);
    }

    public final void a() {
        i();
        this.f2362i.clear();
        this.f2367n.f2389b = 0;
        this.f2373t.f2389b = 0;
        this.f2377x.f2389b = 0;
        this.f2375v = null;
        g0.c cVar = this.O;
        cVar.f2470l.O();
        cVar.f2469k.O();
        this.S = 0;
        this.A = 0;
        this.f2371r = false;
        this.R = false;
        this.f2378y = false;
        this.F = false;
        this.f2379z = -1;
        s2 s2Var = this.G;
        if (!s2Var.f2310f) {
            s2Var.c();
        }
        if (this.I.f2412w) {
            return;
        }
        u();
    }

    public final boolean a0(b2 b2Var, Object obj) {
        a aVar = b2Var.f2097c;
        if (aVar == null) {
            return false;
        }
        int iA = this.G.f2305a.a(aVar);
        if (!this.F || iA < this.G.f2311g) {
            return false;
        }
        ArrayList arrayList = this.f2372s;
        int iO = l.o(iA, arrayList);
        if (iO < 0) {
            int i5 = -(iO + 1);
            if (!(obj instanceof n0)) {
                obj = null;
            }
            arrayList.add(i5, new x0(b2Var, iA, obj));
            return true;
        }
        x0 x0Var = (x0) arrayList.get(iO);
        if (!(obj instanceof n0)) {
            x0Var.f2418c = null;
            return true;
        }
        Object obj2 = x0Var.f2418c;
        if (obj2 == null) {
            x0Var.f2418c = obj;
            return true;
        }
        if (obj2 instanceof j.a0) {
            ((j.a0) obj2).a(obj);
            return true;
        }
        int i6 = j.g0.f3106a;
        j.a0 a0Var = new j.a0(2);
        a0Var.f3067b[a0Var.d(obj2)] = obj2;
        a0Var.f3067b[a0Var.d(obj)] = obj;
        x0Var.f2418c = a0Var;
        return true;
    }

    public final void b(a4.e eVar, Object obj) {
        if (this.R) {
            g0.k0 k0Var = this.O.f2469k;
            k0Var.S(g0.f0.f2477c);
            a.a.L(k0Var, 0, obj);
            b4.l.c("null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>", eVar);
            b4.z.c(2, eVar);
            a.a.L(k0Var, 1, eVar);
            return;
        }
        g0.b bVar = this.M;
        bVar.b();
        g0.k0 k0Var2 = bVar.f2457b.f2454k;
        k0Var2.S(g0.f0.f2477c);
        b4.l.c("null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>", eVar);
        b4.z.c(2, eVar);
        a.a.M(k0Var2, 0, obj, 1, eVar);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0086 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x0088 A[LOOP:1: B:17:0x003a->B:32:0x0088, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:40:0x008b A[EDGE_INSN: B:40:0x008b->B:33:0x008b BREAK  A[LOOP:1: B:17:0x003a->B:32:0x0088], SYNTHETIC] */
    public final void b0(j.z zVar) {
        ArrayList arrayList = this.f2372s;
        for (int iZ = p3.j.z(arrayList); -1 < iZ; iZ--) {
            x0 x0Var = (x0) arrayList.get(iZ);
            a aVar = x0Var.f2416a.f2097c;
            if (aVar == null || !aVar.a()) {
                arrayList.remove(iZ);
            } else {
                int i5 = x0Var.f2417b;
                int i6 = aVar.f2082a;
                if (i5 != i6) {
                    x0Var.f2417b = i6;
                }
            }
        }
        Object[] objArr = zVar.f3177b;
        Object[] objArr2 = zVar.f3178c;
        long[] jArr = zVar.f3176a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i7 = 0;
            while (true) {
                long j5 = jArr[i7];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i7 != length) {
                        break;
                        break;
                    }
                    i7++;
                } else {
                    int i8 = 8 - ((~(i7 - length)) >>> 31);
                    for (int i9 = 0; i9 < i8; i9++) {
                        if ((255 & j5) < 128) {
                            int i10 = (i7 << 3) + i9;
                            Object obj = objArr[i10];
                            Object obj2 = objArr2[i10];
                            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl", obj);
                            b2 b2Var = (b2) obj;
                            a aVar2 = b2Var.f2097c;
                            if (aVar2 != null) {
                                int i11 = aVar2.f2082a;
                                if (obj2 == h.f2188i) {
                                    obj2 = null;
                                }
                                arrayList.add(new x0(b2Var, i11, obj2));
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i8 != 8) {
                        break;
                    } else if (i7 != length) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
        }
        q3.p.T(arrayList, l.f2231c);
    }

    public final boolean c(float f5) {
        Object objC = C();
        if ((objC instanceof Float) && f5 == ((Number) objC).floatValue()) {
            return false;
        }
        g0(Float.valueOf(f5));
        return true;
    }

    public final void c0(int i5, int i6) {
        if (h0(i5) != i6) {
            if (i5 < 0) {
                j.p pVar = this.f2369p;
                if (pVar == null) {
                    pVar = new j.p();
                    this.f2369p = pVar;
                }
                pVar.e(i5, i6);
                return;
            }
            int[] iArr = this.f2368o;
            if (iArr == null) {
                int i7 = this.G.f2307c;
                int[] iArr2 = new int[i7];
                Arrays.fill(iArr2, 0, i7, -1);
                this.f2368o = iArr2;
                iArr = iArr2;
            }
            iArr[i5] = i6;
        }
    }

    public final boolean d(int i5) {
        Object objC = C();
        if ((objC instanceof Integer) && i5 == ((Number) objC).intValue()) {
            return false;
        }
        g0(Integer.valueOf(i5));
        return true;
    }

    public final void d0(int i5, int i6) {
        int iH0 = h0(i5);
        if (iH0 != i6) {
            int i7 = i6 - iH0;
            ArrayList arrayList = this.f2362i;
            int size = arrayList.size() - 1;
            while (i5 != -1) {
                int iH1 = h0(i5) + i7;
                c0(i5, iH1);
                for (int i8 = size; -1 < i8; i8--) {
                    v1 v1Var = (v1) arrayList.get(i8);
                    if (v1Var != null && v1Var.a(i5, iH1)) {
                        size = i8 - 1;
                        break;
                    }
                }
                if (i5 < 0) {
                    i5 = this.G.f2313i;
                } else if (this.G.l(i5)) {
                    return;
                } else {
                    i5 = this.G.q(i5);
                }
            }
        }
    }

    public final boolean e(long j5) {
        Object objC = C();
        if ((objC instanceof Long) && j5 == ((Number) objC).longValue()) {
            return false;
        }
        g0(Long.valueOf(j5));
        return true;
    }

    public final n0.h e0(w1 w1Var, n0.h hVar) {
        n0.h hVar2 = (n0.h) w1Var;
        hVar2.getClass();
        n0.g gVar = new n0.g(hVar2);
        gVar.putAll(hVar);
        n0.h hVarA = gVar.a();
        T(204, z.f2432d);
        C();
        g0(hVarA);
        C();
        g0(hVar);
        p(false);
        return hVarA;
    }

    public final boolean f(Object obj) {
        if (b4.l.a(C(), obj)) {
            return false;
        }
        g0(obj);
        return true;
    }

    public final void f0(Object obj) {
        if (obj instanceof n2) {
            o2 o2Var = new o2((n2) obj, this.f2366m - 1);
            if (this.R) {
                g0.k0 k0Var = this.M.f2457b.f2454k;
                k0Var.S(g0.w.f2508c);
                a.a.L(k0Var, 0, o2Var);
            }
            this.f2357d.add(obj);
            obj = o2Var;
        }
        g0(obj);
    }

    public final boolean g(boolean z2) {
        Object objC = C();
        if ((objC instanceof Boolean) && z2 == ((Boolean) objC).booleanValue()) {
            return false;
        }
        g0(Boolean.valueOf(z2));
        return true;
    }

    public final void g0(Object obj) {
        if (this.R) {
            w2 w2Var = this.I;
            if (w2Var.f2403n <= 0 || w2Var.f2398i == w2Var.f2400k) {
                w2Var.F(obj);
                return;
            }
            j.r rVar = w2Var.f2408s;
            if (rVar == null) {
                rVar = new j.r();
            }
            w2Var.f2408s = rVar;
            int i5 = w2Var.f2411v;
            Object objD = rVar.d(i5);
            if (objD == null) {
                objD = new j.y();
                rVar.g(i5, objD);
            }
            ((j.y) objD).a(obj);
            return;
        }
        s2 s2Var = this.G;
        boolean z2 = s2Var.f2318n;
        g0.b bVar = this.M;
        if (!z2) {
            a aVarA = s2Var.a(s2Var.f2313i);
            g0.k0 k0Var = bVar.f2457b.f2454k;
            k0Var.S(g0.e.f2474c);
            a.a.M(k0Var, 0, aVarA, 1, obj);
            return;
        }
        int iC = (s2Var.f2316l - v2.c(s2Var.f2306b, s2Var.f2313i)) - 1;
        if (bVar.f2456a.G.f2313i - bVar.f2461f >= 0) {
            bVar.d(true);
            g0.k0 k0Var2 = bVar.f2457b.f2454k;
            k0Var2.S(g0.r.f2502g);
            a.a.L(k0Var2, 0, obj);
            k0Var2.f2489m[k0Var2.f2490n - k0Var2.f2487k[k0Var2.f2488l - 1].f2483a] = iC;
            return;
        }
        s2 s2Var2 = this.G;
        a aVarA2 = s2Var2.a(s2Var2.f2313i);
        g0.k0 k0Var3 = bVar.f2457b.f2454k;
        k0Var3.S(g0.r.f2501f);
        a.a.M(k0Var3, 0, obj, 1, aVarA2);
        k0Var3.f2489m[k0Var3.f2490n - k0Var3.f2487k[k0Var3.f2488l - 1].f2483a] = iC;
    }

    public final boolean h(Object obj) {
        if (C() == obj) {
            return false;
        }
        g0(obj);
        return true;
    }

    public final int h0(int i5) {
        int i6;
        if (i5 >= 0) {
            int[] iArr = this.f2368o;
            return (iArr == null || (i6 = iArr[i5]) < 0) ? this.G.o(i5) : i6;
        }
        j.p pVar = this.f2369p;
        if (pVar == null || pVar.c(i5) < 0) {
            return 0;
        }
        int iC = pVar.c(i5);
        if (iC >= 0) {
            return pVar.f3132c[iC];
        }
        k.a.e("Cannot find value for key " + i5);
        throw null;
    }

    public final void i() {
        this.f2363j = null;
        this.f2364k = 0;
        this.f2365l = 0;
        this.S = 0L;
        this.f2371r = false;
        g0.b bVar = this.M;
        bVar.f2458c = false;
        bVar.f2459d.f2389b = 0;
        bVar.f2461f = 0;
        bVar.f2460e = true;
        bVar.f2462g = 0;
        bVar.f2463h.clear();
        bVar.f2464i = -1;
        bVar.f2465j = -1;
        bVar.f2466k = -1;
        bVar.f2467l = 0;
        this.E.clear();
        this.f2368o = null;
        this.f2369p = null;
    }

    public final void i0() {
        if (!this.f2371r) {
            z.a("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.f2371r = false;
        if (this.R) {
            z.a("useNode() called while inserting");
        }
        s2 s2Var = this.G;
        Object objN = s2Var.n(s2Var.f2313i);
        g0.b bVar = this.M;
        bVar.c();
        bVar.f2463h.add(objN);
        if (this.f2378y && (objN instanceof m)) {
            bVar.b();
            bVar.f2457b.f2454k.S(g0.h0.f2481c);
        }
    }

    public final Object j(y1 y1Var) {
        return l.t(l(), y1Var);
    }

    public final void k(a4.a aVar) {
        if (!this.f2371r) {
            z.a("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.f2371r = false;
        if (!this.R) {
            z.a("createNode() can only be called when inserting");
        }
        w0 w0Var = this.f2367n;
        int i5 = w0Var.f2388a[w0Var.f2389b - 1];
        w2 w2Var = this.I;
        a aVarB = w2Var.b(w2Var.f2411v);
        this.f2365l++;
        g0.c cVar = this.O;
        g0.k0 k0Var = cVar.f2469k;
        k0Var.S(g0.r.f2499d);
        a.a.L(k0Var, 0, aVar);
        k0Var.f2489m[k0Var.f2490n - k0Var.f2487k[k0Var.f2488l - 1].f2483a] = i5;
        a.a.L(k0Var, 1, aVarB);
        g0.k0 k0Var2 = cVar.f2470l;
        k0Var2.S(g0.r.f2500e);
        k0Var2.f2489m[k0Var2.f2490n - k0Var2.f2487k[k0Var2.f2488l - 1].f2483a] = i5;
        a.a.L(k0Var2, 0, aVarB);
    }

    public final w1 l() {
        w1 w1Var;
        w1 w1Var2 = this.K;
        if (w1Var2 != null) {
            return w1Var2;
        }
        int iQ = this.G.f2313i;
        boolean z2 = this.R;
        m1 m1Var = z.f2431c;
        if (z2 && this.J) {
            int iE = this.I.f2411v;
            while (iE > 0) {
                if (this.I.s(iE) == 202 && b4.l.a(this.I.t(iE), m1Var)) {
                    Object objQ = this.I.q(iE);
                    b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap", objQ);
                    w1 w1Var3 = (w1) objQ;
                    this.K = w1Var3;
                    return w1Var3;
                }
                w2 w2Var = this.I;
                iE = w2Var.E(w2Var.f2391b, iE);
            }
        }
        if (this.G.f2307c > 0) {
            while (iQ > 0) {
                if (this.G.i(iQ) == 202) {
                    s2 s2Var = this.G;
                    if (b4.l.a(s2Var.p(s2Var.f2306b, iQ), m1Var)) {
                        j.r rVar = this.f2375v;
                        if (rVar == null || (w1Var = (w1) rVar.d(iQ)) == null) {
                            s2 s2Var2 = this.G;
                            Object objB = s2Var2.b(s2Var2.f2306b, iQ);
                            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap", objB);
                            w1Var = (w1) objB;
                        }
                        this.K = w1Var;
                        return w1Var;
                    }
                }
                iQ = this.G.q(iQ);
            }
        }
        w1 w1Var4 = this.f2374u;
        this.K = w1Var4;
        return w1Var4;
    }

    public final q0.a m() {
        Collection collection;
        if (!this.f2355b.j()) {
            return null;
        }
        r3.b bVar = new r3.b(10);
        w2 w2Var = this.I;
        bVar.addAll(p3.j.n(w2Var, null, w2Var.f2409t, null));
        s2 s2Var = this.G;
        boolean z2 = s2Var.f2310f;
        int[] iArr = s2Var.f2306b;
        if (z2 || s2Var.f2307c == 0) {
            collection = q3.s.f5477d;
        } else {
            q0.j jVar = new q0.j(s2Var);
            int iQ = s2Var.f2313i;
            Object objValueOf = Integer.valueOf(s2Var.f2316l - v2.c(iArr, iQ));
            while (iQ >= 0) {
                jVar.c(s2Var.i(iQ), s2Var.k(iQ) ? s2Var.p(iArr, iQ) : o.f2281a, s2Var.f2305a.f(iQ), objValueOf);
                objValueOf = s2Var.a(iQ);
                iQ = s2Var.q(iQ);
            }
            collection = jVar.f5096a;
        }
        bVar.addAll(collection);
        bVar.addAll(D());
        return new q0.a(p3.j.m(bVar));
    }

    public final void n(j.z zVar, n0.d dVar) {
        ArrayList arrayList = this.f2372s;
        if (this.F) {
            z.a("Reentrant composition is not supported");
        }
        this.f2360g.j();
        Trace.beginSection("Compose:recompose");
        try {
            this.B = Long.hashCode(p0.m.j().g());
            this.f2375v = null;
            b0(zVar);
            this.f2364k = 0;
            this.F = true;
            try {
                Z();
                Object objC = C();
                if (objC != dVar && dVar != null) {
                    g0(dVar);
                }
                u uVar = this.D;
                h0.e eVarM = l.m();
                try {
                    eVarM.b(uVar);
                    m1 m1Var = z.f2429a;
                    if (dVar != null) {
                        T(200, m1Var);
                        b4.z.c(2, dVar);
                        dVar.invoke(this, 1);
                        p(false);
                    } else if (!this.f2376w || objC == null || objC.equals(o.f2281a)) {
                        O();
                    } else {
                        T(200, m1Var);
                        b4.z.c(2, objC);
                        a4.e eVar = (a4.e) objC;
                        b4.z.c(2, eVar);
                        eVar.invoke(this, 1);
                        p(false);
                    }
                    eVarM.m(eVarM.f2578f - 1);
                    s();
                    this.F = false;
                    arrayList.clear();
                    if (!this.I.f2412w) {
                        z.a("Check failed");
                    }
                    u();
                    Trace.endSection();
                } catch (Throwable th) {
                    eVarM.m(eVarM.f2578f - 1);
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    p3.p.D(th2, new r(this, 1));
                    throw th2;
                } catch (Throwable th3) {
                    this.F = false;
                    arrayList.clear();
                    a();
                    if (!this.I.f2412w) {
                        z.a("Check failed");
                    }
                    u();
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Trace.endSection();
            throw th4;
        }
    }

    public final void o(int i5, int i6) {
        if (i5 <= 0 || i5 == i6) {
            return;
        }
        o(this.G.q(i5), i6);
        if (this.G.l(i5)) {
            Object objN = this.G.n(i5);
            g0.b bVar = this.M;
            bVar.c();
            bVar.f2463h.add(objN);
        }
    }

    /* JADX WARN: Code duplicated, block: B:150:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:201:0x0516  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v29, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v32 */
    public final void p(boolean z2) {
        long jRotateRight;
        w0 w0Var;
        ArrayList arrayList;
        int i5;
        ?? r4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        w0 w0Var2;
        int i11;
        LinkedHashSet linkedHashSet;
        int i12;
        int i13;
        ArrayList arrayList2;
        ArrayList arrayList3;
        HashSet hashSet;
        int i14;
        int i15;
        Object[] objArr;
        long[] jArr;
        int i16;
        Object[] objArr2;
        long[] jArr2;
        int i17;
        Object[] objArr3;
        long[] jArr3;
        int i18;
        Object[] objArr4;
        long[] jArr4;
        long jRotateRight2;
        w0 w0Var3 = this.f2367n;
        int i19 = w0Var3.f2388a[w0Var3.f2389b - 2] - 1;
        boolean z3 = this.R;
        h hVar = o.f2281a;
        if (z3) {
            w2 w2Var = this.I;
            int i20 = w2Var.f2411v;
            int iS = w2Var.s(i20);
            Object objT = this.I.t(i20);
            Object objQ = this.I.q(i20);
            if (objT != null) {
                jRotateRight2 = Long.rotateRight(this.S ^ ((long) 0), 3) ^ ((long) (objT instanceof Enum ? ((Enum) objT).ordinal() : objT.hashCode()));
            } else if (objQ == null || iS != 207 || objQ.equals(hVar)) {
                jRotateRight2 = Long.rotateRight(this.S ^ ((long) i19), 3) ^ ((long) iS);
            } else {
                this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) i19), 3) ^ ((long) objQ.hashCode()), 3);
            }
            this.S = Long.rotateRight(jRotateRight2, 3);
        } else {
            s2 s2Var = this.G;
            int i21 = s2Var.f2313i;
            int i22 = s2Var.i(i21);
            s2 s2Var2 = this.G;
            Object objP = s2Var2.p(s2Var2.f2306b, i21);
            s2 s2Var3 = this.G;
            Object objB = s2Var3.b(s2Var3.f2306b, i21);
            if (objP != null) {
                jRotateRight = Long.rotateRight(this.S ^ ((long) 0), 3) ^ ((long) (objP instanceof Enum ? ((Enum) objP).ordinal() : objP.hashCode()));
            } else if (objB == null || i22 != 207 || objB.equals(hVar)) {
                jRotateRight = Long.rotateRight(this.S ^ ((long) i19), 3) ^ ((long) i22);
            } else {
                this.S = Long.rotateRight(Long.rotateRight(this.S ^ ((long) i19), 3) ^ ((long) objB.hashCode()), 3);
            }
            this.S = Long.rotateRight(jRotateRight, 3);
        }
        int i23 = this.f2365l;
        v1 v1Var = this.f2363j;
        ArrayList arrayList4 = this.f2372s;
        g0.b bVar = this.M;
        if (v1Var != null) {
            j.r rVar = v1Var.f2385e;
            int i24 = v1Var.f2382b;
            ArrayList arrayList5 = v1Var.f2381a;
            if (arrayList5.size() > 0) {
                ArrayList arrayList6 = v1Var.f2384d;
                HashSet hashSet2 = new HashSet(arrayList6.size());
                int size = arrayList6.size();
                for (int i25 = 0; i25 < size; i25++) {
                    hashSet2.add(arrayList6.get(i25));
                }
                i5 = -1;
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                int size2 = arrayList6.size();
                int size3 = arrayList5.size();
                int i26 = 0;
                int i27 = 0;
                int i28 = 0;
                while (i26 < size3) {
                    a1 a1Var = (a1) arrayList5.get(i26);
                    if (hashSet2.contains(a1Var)) {
                        w0Var2 = w0Var3;
                        i11 = i26;
                        if (!linkedHashSet2.contains(a1Var)) {
                            int i29 = i27;
                            if (i29 < size2) {
                                a1 a1Var2 = (a1) arrayList6.get(i29);
                                if (a1Var2 != a1Var) {
                                    t0 t0Var = (t0) rVar.d(a1Var2.f2085c);
                                    int i30 = t0Var != null ? t0Var.f2327b : -1;
                                    linkedHashSet2.add(a1Var2);
                                    i14 = i28;
                                    if (i30 != i14) {
                                        t0 t0Var2 = (t0) rVar.d(a1Var2.f2085c);
                                        int i31 = t0Var2 != null ? t0Var2.f2328c : a1Var2.f2086d;
                                        linkedHashSet = linkedHashSet2;
                                        int i32 = i30 + i24;
                                        i12 = size2;
                                        int i33 = i14 + i24;
                                        if (i31 > 0) {
                                            i13 = i24;
                                            int i34 = bVar.f2467l;
                                            if (i34 > 0) {
                                                arrayList2 = arrayList5;
                                                if (bVar.f2465j == i32 - i34 && bVar.f2466k == i33 - i34) {
                                                    bVar.f2467l = i34 + i31;
                                                }
                                            } else {
                                                arrayList2 = arrayList5;
                                            }
                                            bVar.c();
                                            bVar.f2465j = i32;
                                            bVar.f2466k = i33;
                                            bVar.f2467l = i31;
                                        } else {
                                            i13 = i24;
                                            arrayList2 = arrayList5;
                                            bVar.getClass();
                                        }
                                        if (i30 <= i14) {
                                            int i35 = i31;
                                            arrayList4 = arrayList4;
                                            arrayList3 = arrayList6;
                                            hashSet = hashSet2;
                                            if (i14 > i30) {
                                                Object[] objArr5 = rVar.f3140c;
                                                long[] jArr5 = rVar.f3138a;
                                                int length = jArr5.length - 2;
                                                if (length >= 0) {
                                                    int i36 = 0;
                                                    while (true) {
                                                        long j5 = jArr5[i36];
                                                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                            int i37 = 8 - ((~(i36 - length)) >>> 31);
                                                            int i38 = 0;
                                                            while (i38 < i37) {
                                                                if ((j5 & 255) < 128) {
                                                                    objArr2 = objArr5;
                                                                    t0 t0Var3 = (t0) objArr5[(i36 << 3) + i38];
                                                                    jArr2 = jArr5;
                                                                    int i39 = t0Var3.f2327b;
                                                                    i17 = i30;
                                                                    if (i30 <= i39 && i39 < i17 + i35) {
                                                                        t0Var3.f2327b = (i39 - i17) + i14;
                                                                    } else if (i17 + 1 <= i39 && i39 < i14) {
                                                                        t0Var3.f2327b = i39 - i35;
                                                                    }
                                                                } else {
                                                                    objArr2 = objArr5;
                                                                    jArr2 = jArr5;
                                                                    i17 = i30;
                                                                }
                                                                j5 >>= 8;
                                                                i38++;
                                                                jArr5 = jArr2;
                                                                objArr5 = objArr2;
                                                                i30 = i17;
                                                            }
                                                            objArr = objArr5;
                                                            jArr = jArr5;
                                                            i16 = i30;
                                                            if (i37 != 8) {
                                                                break;
                                                            }
                                                        } else {
                                                            objArr = objArr5;
                                                            jArr = jArr5;
                                                            i16 = i30;
                                                        }
                                                        if (i36 == length) {
                                                            break;
                                                        }
                                                        i36++;
                                                        jArr5 = jArr;
                                                        objArr5 = objArr;
                                                        i30 = i16;
                                                    }
                                                }
                                            }
                                        } else {
                                            Object[] objArr6 = rVar.f3140c;
                                            long[] jArr6 = rVar.f3138a;
                                            int length2 = jArr6.length - 2;
                                            if (length2 >= 0) {
                                                arrayList3 = arrayList6;
                                                hashSet = hashSet2;
                                                int i40 = 0;
                                                while (true) {
                                                    long j6 = jArr6[i40];
                                                    int i41 = i31;
                                                    arrayList4 = arrayList4;
                                                    if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int i42 = 8 - ((~(i40 - length2)) >>> 31);
                                                        int i43 = 0;
                                                        while (i43 < i42) {
                                                            if ((j6 & 255) < 128) {
                                                                i18 = i43;
                                                                t0 t0Var4 = (t0) objArr6[(i40 << 3) + i43];
                                                                objArr4 = objArr6;
                                                                int i44 = t0Var4.f2327b;
                                                                jArr4 = jArr6;
                                                                if (i30 <= i44 && i44 < i30 + i41) {
                                                                    t0Var4.f2327b = (i44 - i30) + i14;
                                                                } else if (i14 <= i44 && i44 < i30) {
                                                                    t0Var4.f2327b = i44 + i41;
                                                                }
                                                            } else {
                                                                i18 = i43;
                                                                objArr4 = objArr6;
                                                                jArr4 = jArr6;
                                                            }
                                                            j6 >>= 8;
                                                            i43 = i18 + 1;
                                                            objArr6 = objArr4;
                                                            jArr6 = jArr4;
                                                        }
                                                        objArr3 = objArr6;
                                                        jArr3 = jArr6;
                                                        if (i42 != 8) {
                                                            break;
                                                        }
                                                    } else {
                                                        objArr3 = objArr6;
                                                        jArr3 = jArr6;
                                                    }
                                                    if (i40 == length2) {
                                                        break;
                                                    }
                                                    i40++;
                                                    arrayList4 = arrayList4;
                                                    i31 = i41;
                                                    objArr6 = objArr3;
                                                    jArr6 = jArr3;
                                                }
                                            }
                                        }
                                        i15 = i11;
                                    } else {
                                        linkedHashSet = linkedHashSet2;
                                        i12 = size2;
                                        i13 = i24;
                                        arrayList2 = arrayList5;
                                    }
                                    arrayList3 = arrayList6;
                                    hashSet = hashSet2;
                                    i15 = i11;
                                } else {
                                    arrayList4 = arrayList4;
                                    linkedHashSet = linkedHashSet2;
                                    i12 = size2;
                                    i13 = i24;
                                    arrayList2 = arrayList5;
                                    arrayList3 = arrayList6;
                                    hashSet = hashSet2;
                                    i14 = i28;
                                    i15 = i11 + 1;
                                }
                                i27 = i29 + 1;
                                t0 t0Var5 = (t0) rVar.d(a1Var2.f2085c);
                                int i45 = i14 + (t0Var5 != null ? t0Var5.f2328c : a1Var2.f2086d);
                                i26 = i15;
                                v1Var = v1Var;
                                linkedHashSet2 = linkedHashSet;
                                size2 = i12;
                                i24 = i13;
                                arrayList5 = arrayList2;
                                arrayList6 = arrayList3;
                                hashSet2 = hashSet;
                                arrayList4 = arrayList4;
                                i28 = i45;
                                w0Var3 = w0Var2;
                            } else {
                                i27 = i29;
                                w0Var3 = w0Var2;
                                i26 = i11;
                            }
                        }
                    } else {
                        w0Var2 = w0Var3;
                        t0 t0Var6 = (t0) rVar.d(a1Var.f2085c);
                        int i46 = t0Var6 != null ? t0Var6.f2327b : -1;
                        int i47 = a1Var.f2085c;
                        i11 = i26;
                        bVar.e(i46 + i24, a1Var.f2086d);
                        v1Var.a(i47, 0);
                        bVar.f2461f = (i47 - bVar.f2456a.G.f2311g) + bVar.f2461f;
                        this.G.r(i47);
                        H();
                        this.G.s();
                        l.h(arrayList4, i47, this.G.f2306b[(i47 * 5) + 3] + i47);
                    }
                    i26 = i11 + 1;
                    w0Var3 = w0Var2;
                }
                w0Var = w0Var3;
                arrayList = arrayList4;
                bVar.c();
                if (arrayList5.size() > 0) {
                    s2 s2Var4 = this.G;
                    bVar.f2461f = (s2Var4.f2312h - bVar.f2456a.G.f2311g) + bVar.f2461f;
                    s2Var4.t();
                }
            } else {
                w0Var = w0Var3;
                arrayList = arrayList4;
                i5 = -1;
            }
        } else {
            w0Var = w0Var3;
            arrayList = arrayList4;
            i5 = -1;
        }
        boolean z4 = this.R;
        if (!z4) {
            s2 s2Var5 = this.G;
            int i48 = s2Var5.f2317m - s2Var5.f2316l;
            if (i48 > 0) {
                if (i48 > 0) {
                    bVar.d(false);
                    w0 w0Var4 = bVar.f2459d;
                    s2 s2Var6 = bVar.f2456a.G;
                    if (s2Var6.f2307c > 0 && w0Var4.a(-2) != (i10 = s2Var6.f2313i)) {
                        if (!bVar.f2458c && bVar.f2460e) {
                            bVar.d(false);
                            bVar.f2457b.f2454k.S(g0.q.f2498c);
                            bVar.f2458c = true;
                        }
                        if (i10 > 0) {
                            a aVarA = s2Var6.a(i10);
                            w0Var4.c(i10);
                            bVar.d(false);
                            g0.k0 k0Var = bVar.f2457b.f2454k;
                            k0Var.S(g0.p.f2497c);
                            a.a.L(k0Var, 0, aVarA);
                            bVar.f2458c = true;
                        }
                    }
                    g0.k0 k0Var2 = bVar.f2457b.f2454k;
                    k0Var2.S(g0.d0.f2473c);
                    k0Var2.f2489m[k0Var2.f2490n - k0Var2.f2487k[k0Var2.f2488l - 1].f2483a] = i48;
                } else {
                    bVar.getClass();
                }
            }
        }
        int i49 = this.f2364k;
        while (true) {
            s2 s2Var7 = this.G;
            if (s2Var7.f2315k > 0 || (i9 = s2Var7.f2311g) == s2Var7.f2312h) {
                break;
            }
            H();
            bVar.e(i49, this.G.s());
            l.h(arrayList, i9, this.G.f2311g);
        }
        if (z4) {
            if (z2) {
                g0.c cVar = this.O;
                g0.k0 k0Var3 = cVar.f2470l;
                if (!k0Var3.R()) {
                    z.a("Cannot end node insertion, there are no pending operations that can be realized.");
                }
                g0.k0 k0Var4 = cVar.f2469k;
                g0.i0[] i0VarArr = k0Var3.f2487k;
                int i50 = k0Var3.f2488l - 1;
                k0Var3.f2488l = i50;
                g0.i0 i0Var = i0VarArr[i50];
                i0VarArr[i50] = null;
                k0Var4.S(i0Var);
                Object[] objArr7 = k0Var3.f2491o;
                Object[] objArr8 = k0Var4.f2491o;
                int i51 = k0Var4.f2492p;
                int i52 = i0Var.f2484b;
                int i53 = k0Var3.f2492p;
                int i54 = i53 - i52;
                System.arraycopy(objArr7, i54, objArr8, i51 - i52, i53 - i54);
                Object[] objArr9 = k0Var3.f2491o;
                int i55 = k0Var3.f2492p;
                q3.k.E(objArr9, i55 - i52, i55);
                int[] iArr = k0Var3.f2489m;
                int[] iArr2 = k0Var4.f2489m;
                int i56 = k0Var4.f2490n;
                int i57 = i0Var.f2483a;
                int i58 = k0Var3.f2490n;
                q3.k.y(iArr, iArr2, i56 - i57, i58 - i57, i58);
                k0Var3.f2492p -= i52;
                k0Var3.f2490n -= i57;
                i23 = 1;
            }
            s2 s2Var8 = this.G;
            if (s2Var8.f2315k <= 0) {
                x1.a("Unbalanced begin/end empty");
            }
            s2Var8.f2315k--;
            w2 w2Var2 = this.I;
            int i59 = w2Var2.f2411v;
            w2Var2.j();
            if (this.G.f2315k <= 0) {
                int i60 = (-2) - i59;
                this.I.k();
                this.I.e(true);
                a aVar = this.N;
                if (this.O.f2469k.Q()) {
                    t2 t2Var = this.H;
                    bVar.b();
                    bVar.d(false);
                    w0 w0Var5 = bVar.f2459d;
                    s2 s2Var9 = bVar.f2456a.G;
                    if (s2Var9.f2307c <= 0 || w0Var5.a(-2) == (i8 = s2Var9.f2313i)) {
                        i7 = 1;
                    } else {
                        if (!bVar.f2458c && bVar.f2460e) {
                            bVar.d(false);
                            bVar.f2457b.f2454k.S(g0.q.f2498c);
                            bVar.f2458c = true;
                        }
                        if (i8 > 0) {
                            a aVarA2 = s2Var9.a(i8);
                            w0Var5.c(i8);
                            bVar.d(false);
                            g0.k0 k0Var5 = bVar.f2457b.f2454k;
                            k0Var5.S(g0.p.f2497c);
                            a.a.L(k0Var5, 0, aVarA2);
                            i7 = 1;
                            bVar.f2458c = true;
                        } else {
                            i7 = 1;
                        }
                    }
                    bVar.c();
                    g0.k0 k0Var6 = bVar.f2457b.f2454k;
                    k0Var6.S(g0.s.f2504c);
                    a.a.M(k0Var6, 0, aVar, i7, t2Var);
                    r4 = 0;
                } else {
                    t2 t2Var2 = this.H;
                    g0.c cVar2 = this.O;
                    bVar.b();
                    bVar.d(false);
                    w0 w0Var6 = bVar.f2459d;
                    s2 s2Var10 = bVar.f2456a.G;
                    if (s2Var10.f2307c > 0 && w0Var6.a(-2) != (i6 = s2Var10.f2313i)) {
                        if (!bVar.f2458c && bVar.f2460e) {
                            bVar.d(false);
                            bVar.f2457b.f2454k.S(g0.q.f2498c);
                            bVar.f2458c = true;
                        }
                        if (i6 > 0) {
                            a aVarA3 = s2Var10.a(i6);
                            w0Var6.c(i6);
                            bVar.d(false);
                            g0.k0 k0Var7 = bVar.f2457b.f2454k;
                            k0Var7.S(g0.p.f2497c);
                            a.a.L(k0Var7, 0, aVarA3);
                            bVar.f2458c = true;
                        }
                    }
                    bVar.c();
                    g0.k0 k0Var8 = bVar.f2457b.f2454k;
                    k0Var8.S(g0.t.f2505c);
                    int i61 = k0Var8.f2492p - k0Var8.f2487k[k0Var8.f2488l - 1].f2484b;
                    Object[] objArr10 = k0Var8.f2491o;
                    objArr10[i61] = aVar;
                    objArr10[i61 + 1] = t2Var2;
                    objArr10[i61 + 2] = cVar2;
                    this.O = new g0.c();
                    r4 = 0;
                }
                this.R = r4;
                if (this.f2356c.f2334e != 0) {
                    c0(i60, r4);
                    d0(i60, i23);
                }
            }
        } else {
            if (z2) {
                bVar.a();
            }
            int i62 = bVar.f2456a.G.f2313i;
            w0 w0Var7 = bVar.f2459d;
            int i63 = i5;
            if (w0Var7.a(i63) > i62) {
                z.a("Missed recording an endGroup");
            }
            if (w0Var7.a(i63) == i62) {
                bVar.d(false);
                w0Var7.b();
                bVar.f2457b.f2454k.S(g0.m.f2494c);
            }
            int i64 = this.G.f2313i;
            if (i23 != h0(i64)) {
                d0(i64, i23);
            }
            if (z2) {
                i23 = 1;
            }
            this.G.e();
            bVar.c();
        }
        ArrayList arrayList7 = this.f2362i;
        v1 v1Var2 = (v1) arrayList7.remove(arrayList7.size() - 1);
        if (v1Var2 != null && !z4) {
            v1Var2.f2383c++;
        }
        this.f2363j = v1Var2;
        this.f2364k = w0Var.b() + i23;
        this.f2366m = w0Var.b();
        this.f2365l = w0Var.b() + i23;
    }

    public final void q() {
        p(false);
        b2 b2VarW = w();
        if (b2VarW != null) {
            int i5 = b2VarW.f2096b;
            if ((i5 & 1) != 0) {
                b2VarW.f2096b = i5 | 2;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x007d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x007f A[LOOP:0: B:15:0x003e->B:27:0x007f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:28:0x0082 A[EDGE_INSN: B:28:0x0082->B:29:0x0083 BREAK  A[LOOP:0: B:15:0x003e->B:27:0x007f]] */
    /* JADX WARN: Code duplicated, block: B:55:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:60:0x0082 A[SYNTHETIC] */
    public final b2 r() {
        b2 b2Var;
        a aVarA;
        a4.c cVar;
        ArrayList arrayList = this.E;
        final b2 b2Var2 = !arrayList.isEmpty() ? (b2) arrayList.remove(arrayList.size() - 1) : null;
        if (b2Var2 != null) {
            b2Var2.f2096b &= -9;
            this.f2360g.j();
            final int i5 = this.B;
            final j.x xVar = b2Var2.f2100f;
            if (xVar == null || (b2Var2.f2096b & 16) != 0) {
                cVar = null;
                break;
            }
            Object[] objArr = xVar.f3169b;
            int[] iArr = xVar.f3170c;
            long[] jArr = xVar.f3168a;
            int length = jArr.length - 2;
            if (length < 0) {
                cVar = null;
                break;
            }
            int i6 = 0;
            loop0: while (true) {
                long j5 = jArr[i6];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((j5 & 255) < 128) {
                            int i9 = (i6 << 3) + i8;
                            Object obj = objArr[i9];
                            if (iArr[i9] != i5) {
                                cVar = new a4.c() { // from class: f0.a2
                                    @Override // a4.c
                                    public final Object c(Object obj2) {
                                        a0 a0Var;
                                        a0 a0Var2;
                                        int i10;
                                        a0 a0Var3 = (a0) obj2;
                                        b2 b2Var3 = b2Var2;
                                        int i11 = b2Var3.f2099e;
                                        int i12 = i5;
                                        if (i11 == i12) {
                                            j.x xVar2 = b2Var3.f2100f;
                                            j.x xVar3 = xVar;
                                            if (b4.l.a(xVar3, xVar2) && (a0Var3 instanceof e0)) {
                                                long[] jArr2 = xVar3.f3168a;
                                                int length2 = jArr2.length - 2;
                                                if (length2 >= 0) {
                                                    int i13 = 0;
                                                    while (true) {
                                                        long j6 = jArr2[i13];
                                                        if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                            int i14 = 8;
                                                            int i15 = 8 - ((~(i13 - length2)) >>> 31);
                                                            int i16 = 0;
                                                            while (i16 < i15) {
                                                                if ((255 & j6) < 128) {
                                                                    int i17 = (i13 << 3) + i16;
                                                                    Object obj3 = xVar3.f3169b[i17];
                                                                    boolean z2 = xVar3.f3170c[i17] != i12;
                                                                    if (z2) {
                                                                        i10 = i14;
                                                                        e0 e0Var = (e0) a0Var3;
                                                                        j.z zVar = e0Var.f2136j;
                                                                        d4.a.F(zVar, obj3, b2Var3);
                                                                        a0Var2 = a0Var3;
                                                                        if (obj3 instanceof n0) {
                                                                            n0 n0Var = (n0) obj3;
                                                                            if (!zVar.b(n0Var)) {
                                                                                d4.a.G(e0Var.f2139m, n0Var);
                                                                            }
                                                                            j.z zVar2 = b2Var3.f2101g;
                                                                            if (zVar2 != null) {
                                                                                zVar2.g(obj3);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        a0Var2 = a0Var3;
                                                                        i10 = i14;
                                                                    }
                                                                    if (z2) {
                                                                        xVar3.e(i17);
                                                                    }
                                                                } else {
                                                                    a0Var2 = a0Var3;
                                                                    i10 = i14;
                                                                }
                                                                j6 >>= i10;
                                                                i16++;
                                                                i14 = i10;
                                                                a0Var3 = a0Var2;
                                                            }
                                                            a0Var = a0Var3;
                                                            if (i15 != i14) {
                                                                break;
                                                            }
                                                        } else {
                                                            a0Var = a0Var3;
                                                        }
                                                        if (i13 == length2) {
                                                            break;
                                                        }
                                                        i13++;
                                                        a0Var3 = a0Var;
                                                    }
                                                }
                                            }
                                        }
                                        return p3.n.f5026a;
                                    }
                                };
                                break loop0;
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i7 == 8) {
                        if (i6 == length) {
                            i6++;
                        }
                    }
                    cVar = null;
                    break;
                }
                if (i6 == length) {
                    cVar = null;
                    break;
                }
                i6++;
            }
            g0.b bVar = this.M;
            if (cVar != null) {
                g0.k0 k0Var = bVar.f2457b.f2454k;
                k0Var.S(g0.l.f2493c);
                a.a.M(k0Var, 0, cVar, 1, this.f2361h);
            }
            int i10 = b2Var2.f2096b;
            if ((i10 & 512) != 0) {
                b2Var2.f2096b = i10 & (-513);
                g0.k0 k0Var2 = bVar.f2457b.f2454k;
                k0Var2.S(g0.o.f2496c);
                a.a.L(k0Var2, 0, b2Var2);
                int i11 = b2Var2.f2096b;
                b2Var2.f2096b = i11 & (-129);
                if ((i11 & 1024) != 0) {
                    b2Var2.f2096b = i11 & (-1153);
                    this.f2378y = false;
                }
            }
        }
        if (b2Var2 != null) {
            int i12 = b2Var2.f2096b;
            if ((i12 & 16) == 0 && ((i12 & 1) != 0 || this.f2370q)) {
                if (b2Var2.f2097c == null) {
                    if (this.R) {
                        w2 w2Var = this.I;
                        aVarA = w2Var.b(w2Var.f2411v);
                    } else {
                        s2 s2Var = this.G;
                        aVarA = s2Var.a(s2Var.f2313i);
                    }
                    b2Var2.f2097c = aVarA;
                }
                b2Var2.f2096b &= -5;
                b2Var = b2Var2;
            } else {
                b2Var = null;
            }
        } else {
            b2Var = null;
        }
        p(false);
        return b2Var;
    }

    public final void s() {
        p(false);
        this.f2355b.b();
        p(false);
        g0.b bVar = this.M;
        if (bVar.f2458c) {
            bVar.d(false);
            bVar.d(false);
            bVar.f2457b.f2454k.S(g0.m.f2494c);
            bVar.f2458c = false;
        }
        bVar.b();
        if (bVar.f2459d.f2389b != 0) {
            z.a("Missed recording an endGroup()");
        }
        if (!this.f2362i.isEmpty()) {
            z.a("Start/end imbalance");
        }
        i();
        this.G.c();
        this.f2376w = this.f2377x.b() != 0;
    }

    public final void t(boolean z2, v1 v1Var) {
        this.f2362i.add(this.f2363j);
        this.f2363j = v1Var;
        int i5 = this.f2365l;
        w0 w0Var = this.f2367n;
        w0Var.c(i5);
        w0Var.c(this.f2366m);
        w0Var.c(this.f2364k);
        if (z2) {
            this.f2364k = 0;
        }
        this.f2365l = 0;
        this.f2366m = 0;
    }

    public final void u() {
        t2 t2Var = new t2();
        if (this.C) {
            t2Var.b();
        }
        if (this.f2355b.c()) {
            t2Var.f2343n = new j.r();
        }
        this.H = t2Var;
        w2 w2VarD = t2Var.d();
        w2VarD.e(true);
        this.I = w2VarD;
    }

    public final q0.c v() {
        d0 d0Var = this.T;
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0(this.f2361h);
        this.T = d0Var2;
        return d0Var2;
    }

    public final b2 w() {
        if (this.A != 0) {
            return null;
        }
        ArrayList arrayList = this.E;
        if (arrayList.isEmpty()) {
            return null;
        }
        return (b2) arrayList.get(arrayList.size() - 1);
    }

    public final boolean x() {
        if (!z() || this.f2376w) {
            return true;
        }
        b2 b2VarW = w();
        return (b2VarW == null || (b2VarW.f2096b & 4) == 0) ? false : true;
    }

    public final q0.d y() {
        if (this.f2355b.j()) {
            return this.P;
        }
        return null;
    }

    public final boolean z() {
        b2 b2VarW;
        return (this.R || this.f2378y || this.f2376w || (b2VarW = w()) == null || (b2VarW.f2096b & 8) != 0) ? false : true;
    }
}
