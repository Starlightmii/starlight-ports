package f0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n0.d f2229a = new n0.d(954879418, false, new k(0));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n0.d f2230b = new n0.d(1918065384, false, new k(1));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final w f2231c = new w(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f2232d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q0 f2233e = new q0();

    public static final n0.h A(z1[] z1VarArr, w1 w1Var, w1 w1Var2) {
        n0.g gVar = new n0.g(n0.h.f4069g);
        for (z1 z1Var : z1VarArr) {
            y1 y1Var = z1Var.f2436a;
            if (z1Var.f2441f || !((n0.h) w1Var).containsKey(y1Var)) {
                gVar.put(y1Var, y1Var.c(z1Var, (j3) ((n0.h) w1Var2).get(y1Var)));
            }
        }
        return gVar.a();
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:49:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:51:? A[RETURN, SYNTHETIC] */
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
    public static final void a(z1 z1Var, a4.e eVar, p pVar, int i5) {
        j3 j3Var;
        boolean z2;
        b2 b2VarR;
        v vVar = (v) pVar;
        vVar.X(-149765515);
        w0 w0Var = vVar.f2377x;
        w1 w1VarL = vVar.l();
        vVar.T(201, z.f2430b);
        Object objK = vVar.K();
        if (b4.l.a(objK, o.f2281a)) {
            j3Var = null;
        } else {
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.ValueHolder<kotlin.Any?>", objK);
            j3Var = (j3) objK;
        }
        y1 y1Var = z1Var.f2436a;
        j3 j3VarC = y1Var.c(z1Var, j3Var);
        boolean zEquals = j3VarC.equals(j3Var);
        if (!zEquals) {
            vVar.f0(j3VarC);
        }
        if (!vVar.R) {
            s2 s2Var = vVar.G;
            Object objB = s2Var.b(s2Var.f2306b, s2Var.f2311g);
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap", objB);
            w1 w1Var = (w1) objB;
            if (!(vVar.z() && zEquals) && (z1Var.f2441f || !((n0.h) w1VarL).containsKey(y1Var))) {
                w1VarL = ((n0.h) w1VarL).b(y1Var, j3VarC);
            } else if ((zEquals && !vVar.f2376w) || !vVar.f2376w) {
                w1VarL = w1Var;
            }
            if (vVar.f2378y || w1Var != w1VarL) {
                z2 = true;
            }
            if (z2 && !vVar.R) {
                vVar.I(w1VarL);
            }
            w0Var.c(vVar.f2376w ? 1 : 0);
            vVar.f2376w = z2;
            vVar.K = w1VarL;
            vVar.R(202, 0, z.f2431c, w1VarL);
            eVar.invoke(vVar, Integer.valueOf((i5 >> 3) & 14));
            vVar.p(false);
            vVar.p(false);
            vVar.f2376w = w0Var.b() != 0;
            vVar.K = null;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new f0(i5, 0, z1Var, eVar);
            }
        }
        if (z1Var.f2441f || !((n0.h) w1VarL).containsKey(y1Var)) {
            w1VarL = ((n0.h) w1VarL).b(y1Var, j3VarC);
        }
        vVar.J = true;
        z2 = false;
        if (z2) {
            vVar.I(w1VarL);
        }
        w0Var.c(vVar.f2376w ? 1 : 0);
        vVar.f2376w = z2;
        vVar.K = w1VarL;
        vVar.R(202, 0, z.f2431c, w1VarL);
        eVar.invoke(vVar, Integer.valueOf((i5 >> 3) & 14));
        vVar.p(false);
        vVar.p(false);
        vVar.f2376w = w0Var.b() != 0;
        vVar.K = null;
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f0(i5, 0, z1Var, eVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:29:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:31:? A[RETURN, SYNTHETIC] */
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
    public static final void b(z1[] z1VarArr, a4.e eVar, p pVar, int i5) {
        w1 w1VarE0;
        boolean z2;
        b2 b2VarR;
        v vVar = (v) pVar;
        vVar.X(415205898);
        w0 w0Var = vVar.f2377x;
        w1 w1VarL = vVar.l();
        vVar.T(201, z.f2430b);
        if (vVar.R) {
            w1VarE0 = vVar.e0(w1VarL, A(z1VarArr, w1VarL, n0.h.f4069g));
            vVar.J = true;
        } else {
            s2 s2Var = vVar.G;
            Object objH = s2Var.h(s2Var.f2311g, 0);
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap", objH);
            w1 w1Var = (w1) objH;
            s2 s2Var2 = vVar.G;
            Object objH2 = s2Var2.h(s2Var2.f2311g, 1);
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap", objH2);
            w1 w1Var2 = (w1) objH2;
            n0.h hVarA = A(z1VarArr, w1VarL, w1Var2);
            if (!vVar.z() || vVar.f2378y || !w1Var2.equals(hVarA)) {
                w1VarE0 = vVar.e0(w1VarL, hVarA);
                if (vVar.f2378y || !b4.l.a(w1VarE0, w1Var)) {
                    z2 = true;
                }
                if (z2 && !vVar.R) {
                    vVar.I(w1VarE0);
                }
                w0Var.c(vVar.f2376w ? 1 : 0);
                vVar.f2376w = z2;
                vVar.K = w1VarE0;
                vVar.R(202, 0, z.f2431c, w1VarE0);
                eVar.invoke(vVar, Integer.valueOf((i5 >> 3) & 14));
                vVar.p(false);
                vVar.p(false);
                vVar.f2376w = w0Var.b() != 0;
                vVar.K = null;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new f0(i5, 1, z1VarArr, eVar);
                }
            }
            vVar.f2365l = vVar.G.s() + vVar.f2365l;
            w1VarE0 = w1Var;
        }
        z2 = false;
        if (z2) {
            vVar.I(w1VarE0);
        }
        w0Var.c(vVar.f2376w ? 1 : 0);
        vVar.f2376w = z2;
        vVar.K = w1VarE0;
        vVar.R(202, 0, z.f2431c, w1VarE0);
        eVar.invoke(vVar, Integer.valueOf((i5 >> 3) & 14));
        vVar.p(false);
        vVar.p(false);
        vVar.f2376w = w0Var.b() != 0;
        vVar.K = null;
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f0(i5, 1, z1VarArr, eVar);
        }
    }

    public static final void c(Object obj, a4.c cVar, p pVar) {
        v vVar = (v) pVar;
        boolean zF = vVar.f(obj);
        Object objK = vVar.K();
        if (zF || objK == o.f2281a) {
            objK = new o0(cVar);
            vVar.f0(objK);
        }
    }

    public static final void d(Object obj, Object obj2, a4.c cVar, p pVar) {
        v vVar = (v) pVar;
        boolean zF = vVar.f(obj) | vVar.f(obj2);
        Object objK = vVar.K();
        if (zF || objK == o.f2281a) {
            objK = new o0(cVar);
            vVar.f0(objK);
        }
    }

    public static final void e(a4.e eVar, p pVar, Object obj) {
        v vVar = (v) pVar;
        t3.h hVar = vVar.Q;
        boolean zF = vVar.f(obj);
        Object objK = vVar.K();
        if (zF || objK == o.f2281a) {
            objK = new d1(hVar, eVar);
            vVar.f0(objK);
        }
    }

    public static final void f(Object obj, Object obj2, a4.e eVar, v vVar) {
        t3.h hVar = vVar.Q;
        boolean zF = vVar.f(obj) | vVar.f(obj2);
        Object objK = vVar.K();
        if (zF || objK == o.f2281a) {
            objK = new d1(hVar, eVar);
            vVar.f0(objK);
        }
    }

    public static final void g(a4.a aVar, p pVar) {
        g0.k0 k0Var = ((v) pVar).M.f2457b.f2454k;
        k0Var.S(g0.a0.f2455c);
        a.a.L(k0Var, 0, aVar);
    }

    public static final void h(List list, int i5, int i6) {
        int iO = o(i5, list);
        if (iO < 0) {
            iO = -(iO + 1);
        }
        while (iO < list.size() && ((x0) list.get(iO)).f2417b < i6) {
        }
    }

    public static final void i(j.q qVar, int i5) {
        if (qVar.f3137b == 0 || !(qVar.c(0) == i5 || qVar.c(qVar.f3137b - 1) == i5)) {
            int i6 = qVar.f3137b;
            qVar.a(i5);
            while (i6 > 0) {
                int i7 = ((i6 + 1) >>> 1) - 1;
                int iC = qVar.c(i7);
                if (i5 <= iC) {
                    break;
                }
                qVar.d(i6, iC);
                i6 = i7;
            }
            qVar.d(i6, i5);
        }
    }

    public static void j(w2 w2Var, List list, e0 e0Var) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            int iC = w2Var.c((a) list.get(i5));
            int iN = w2Var.N(w2Var.f2391b, w2Var.r(iC));
            Object obj = iN < w2Var.g(w2Var.f2391b, w2Var.r(iC + 1)) ? w2Var.f2392c[w2Var.h(iN)] : o.f2281a;
            b2 b2Var = obj instanceof b2 ? (b2) obj : null;
            if (b2Var != null) {
                b2Var.f2095a = e0Var;
            }
        }
    }

    public static final void k(s2 s2Var, ArrayList arrayList, int i5) {
        boolean zL = s2Var.l(i5);
        int[] iArr = s2Var.f2306b;
        if (zL) {
            arrayList.add(s2Var.n(i5));
            return;
        }
        int i6 = iArr[(i5 * 5) + 3] + i5;
        for (int i7 = i5 + 1; i7 < i6; i7 += iArr[(i7 * 5) + 3]) {
            k(s2Var, arrayList, i7);
        }
    }

    public static final i4.v l(p pVar) {
        return new q2(((v) pVar).Q);
    }

    public static final h0.e m() {
        a1.b bVar = c3.f2110b;
        h0.e eVar = (h0.e) bVar.q();
        if (eVar != null) {
            return eVar;
        }
        h0.e eVar2 = new h0.e(new u[0]);
        bVar.z(eVar2);
        return eVar2;
    }

    public static final n0 n(a4.a aVar) {
        a1.b bVar = c3.f2109a;
        return new n0(aVar, null);
    }

    public static final int o(int i5, List list) {
        int size = list.size() - 1;
        int i6 = 0;
        while (i6 <= size) {
            int i7 = (i6 + size) >>> 1;
            int iF = b4.l.f(((x0) list.get(i7)).f2417b, i5);
            if (iF < 0) {
                i6 = i7 + 1;
            } else {
                if (iF <= 0) {
                    return i7;
                }
                size = i7 - 1;
            }
        }
        return -(i6 + 1);
    }

    public static final int p(p pVar) {
        pVar.getClass();
        return Long.hashCode(((v) pVar).S);
    }

    public static final g q(t3.h hVar) {
        g gVar = (g) hVar.j(h.f2185f);
        if (gVar != null) {
            return gVar;
        }
        throw new IllegalStateException("A MonotonicFrameClock is not available in this CoroutineContext. Callers should supply an appropriate MonotonicFrameClock using withContext.");
    }

    public static List r(w2 w2Var, int i5, w2 w2Var2, boolean z2, boolean z3, boolean z4) {
        List list;
        boolean z5;
        int iU = w2Var.u(i5);
        int i6 = i5 + iU;
        int iF = w2Var.f(i5);
        int iF2 = w2Var.f(i6);
        int i7 = iF2 - iF;
        boolean z6 = i5 >= 0 && (w2Var.f2391b[(w2Var.r(i5) * 5) + 1] & 201326592) != 0;
        w2Var2.w(iU);
        w2Var2.x(i7, w2Var2.f2409t);
        if (w2Var.f2396g < i6) {
            w2Var.B(i6);
        }
        if (w2Var.f2400k < iF2) {
            w2Var.C(iF2, i6);
        }
        int[] iArr = w2Var2.f2391b;
        int i8 = w2Var2.f2409t;
        int i9 = i8 * 5;
        q3.k.y(w2Var.f2391b, iArr, i9, i5 * 5, i6 * 5);
        Object[] objArr = w2Var2.f2392c;
        int i10 = w2Var2.f2398i;
        System.arraycopy(w2Var.f2392c, iF, objArr, i10, i7);
        int i11 = w2Var2.f2411v;
        iArr[i9 + 2] = i11;
        int i12 = i8 - i5;
        int i13 = i8 + iU;
        int iG = i10 - w2Var2.g(iArr, i8);
        int i14 = w2Var2.f2402m;
        int i15 = w2Var2.f2401l;
        int length = objArr.length;
        boolean z7 = z6;
        int i16 = i14;
        int i17 = i8;
        while (i17 < i13) {
            if (i17 != i8) {
                int i18 = (i17 * 5) + 2;
                iArr[i18] = iArr[i18] + i12;
            }
            int[] iArr2 = iArr;
            iArr2[(i17 * 5) + 4] = w2.i(w2Var2.g(iArr, i17) + iG, i16 < i17 ? 0 : w2Var2.f2400k, i15, length);
            if (i17 == i16) {
                i16++;
            }
            i17++;
            i8 = i8;
            iArr = iArr2;
        }
        int[] iArr3 = iArr;
        w2Var2.f2402m = i16;
        int iB = v2.b(w2Var.f2393d, i5, w2Var.p());
        int iB2 = v2.b(w2Var.f2393d, i6, w2Var.p());
        if (iB < iB2) {
            ArrayList arrayList = w2Var.f2393d;
            ArrayList arrayList2 = new ArrayList(iB2 - iB);
            for (int i19 = iB; i19 < iB2; i19++) {
                a aVar = (a) arrayList.get(i19);
                aVar.f2082a += i12;
                arrayList2.add(aVar);
            }
            w2Var2.f2393d.addAll(v2.b(w2Var2.f2393d, w2Var2.f2409t, w2Var2.p()), arrayList2);
            arrayList.subList(iB, iB2).clear();
            list = arrayList2;
        } else {
            list = q3.s.f5477d;
        }
        if (!list.isEmpty()) {
            HashMap map = w2Var.f2394e;
            HashMap map2 = w2Var2.f2394e;
            if (map != null && map2 != null) {
                int size = list.size();
                for (int i20 = 0; i20 < size; i20++) {
                }
            }
        }
        int i21 = w2Var2.f2411v;
        w2Var2.O(i11);
        int iE = w2Var.E(w2Var.f2391b, i5);
        if (!z4) {
            z5 = false;
        } else if (z2) {
            boolean z8 = iE >= 0;
            if (z8) {
                w2Var.P();
                w2Var.a(iE - w2Var.f2409t);
                w2Var.P();
            }
            w2Var.a(i5 - w2Var.f2409t);
            boolean zH = w2Var.H();
            if (z8) {
                w2Var.M();
                w2Var.j();
                w2Var.M();
                w2Var.j();
            }
            z5 = zH;
        } else {
            boolean zI = w2Var.I(i5, iU);
            w2Var.J(iF, i7, i5 - 1);
            z5 = zI;
        }
        if (z5) {
            z.a("Unexpectedly removed anchors");
        }
        int i22 = w2Var2.f2404o;
        int i23 = iArr3[i9 + 1];
        w2Var2.f2404o = i22 + ((1073741824 & i23) != 0 ? 1 : i23 & 67108863);
        if (z3) {
            w2Var2.f2409t = i13;
            w2Var2.f2398i = i10 + i7;
        }
        if (z7) {
            w2Var2.T(i11);
        }
        return list;
    }

    public static s1 s(Object obj) {
        return new s1(obj, h.f2189j);
    }

    public static final Object t(w1 w1Var, y1 y1Var) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>", y1Var);
        n0.h hVar = (n0.h) w1Var;
        Object objB = hVar.get(y1Var);
        if (objB == null) {
            objB = y1Var.b();
        }
        return ((j3) objB).a(hVar);
    }

    public static final t u(p pVar) {
        v vVar = (v) pVar;
        vVar.T(206, z.f2433e);
        if (vVar.R) {
            w2.z(vVar.I);
        }
        Object objC = vVar.C();
        o2 r2Var = objC instanceof o2 ? (o2) objC : null;
        if (r2Var == null) {
            r2Var = new r2(new s(new t(vVar, vVar.S, vVar.f2370q, vVar.C, vVar.f2361h.f2147u)), -1);
            vVar.g0(r2Var);
        }
        n2 n2Var = r2Var.f2285a;
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl.CompositionContextHolder", n2Var);
        t tVar = ((s) n2Var).f2302d;
        tVar.f2324f.setValue(vVar.l());
        vVar.p(false);
        return tVar;
    }

    public static final i1 v(Object obj, p pVar) {
        v vVar = (v) pVar;
        Object objK = vVar.K();
        if (objK == o.f2281a) {
            objK = s(obj);
            vVar.f0(objK);
        }
        i1 i1Var = (i1) objK;
        i1Var.setValue(obj);
        return i1Var;
    }

    public static final void w(w2 w2Var, int i5, Object obj) {
        int iH = w2Var.h(i5);
        Object[] objArr = w2Var.f2392c;
        Object obj2 = objArr[iH];
        objArr[iH] = o.f2281a;
        if (obj == obj2) {
            return;
        }
        z.a("Slot table is out of sync (expected " + obj + ", got " + obj2 + ')');
    }

    public static final void x(a4.e eVar, p pVar, Object obj) {
        v vVar = (v) pVar;
        if (vVar.R || !b4.l.a(vVar.K(), obj)) {
            vVar.f0(obj);
            vVar.b(eVar, obj);
        }
    }

    public static final int y(j.q qVar) {
        int iC;
        int i5 = qVar.f3137b;
        int iC2 = qVar.c(0);
        while (qVar.f3137b != 0 && qVar.c(0) == iC2) {
            int i6 = qVar.f3137b;
            if (i6 == 0) {
                k.a.e("IntList is empty.");
                throw null;
            }
            qVar.d(0, qVar.f3136a[i6 - 1]);
            int i7 = qVar.f3137b;
            int i8 = i7 - 1;
            if (i8 >= 0 && i8 < i7) {
                int[] iArr = qVar.f3136a;
                int i9 = iArr[i8];
                if (i8 != i7 - 1) {
                    q3.k.y(iArr, iArr, i8, i7, i7);
                }
                int i10 = qVar.f3137b - 1;
                qVar.f3137b = i10;
                int i11 = i10 >>> 1;
                int i12 = 0;
                while (i12 < i11) {
                    int iC3 = qVar.c(i12);
                    int i13 = (i12 + 1) * 2;
                    int i14 = i13 - 1;
                    int iC4 = qVar.c(i14);
                    if (i13 < i10 && (iC = qVar.c(i13)) > iC4) {
                        if (iC <= iC3) {
                            break;
                        }
                        qVar.d(i12, iC);
                        qVar.d(i13, iC3);
                        i12 = i13;
                    } else {
                        if (iC4 <= iC3) {
                            break;
                        }
                        qVar.d(i12, iC4);
                        qVar.d(i14, iC3);
                        i12 = i14;
                    }
                }
            } else {
                k.a.d("Index must be between 0 and size");
                throw null;
            }
        }
        return iC2;
    }

    public static final int z(int i5) {
        int i6 = 306783378 & i5;
        int i7 = 613566756 & i5;
        return (i5 & (-920350135)) | (i7 >> 1) | i6 | ((i6 << 1) & i7);
    }
}
