package f0;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l2 extends b0 {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final l4.d0 f2241y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final AtomicReference f2242z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f2243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a2.h f2244b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2245c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i4.s0 f2246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Throwable f2247e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f2248f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f2249g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public j.a0 f2250h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h0.e f2251i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f2252j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f2253k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j.z f2254l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a2.h f2255m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final j.z f2256n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final j.z f2257o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f2258p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public LinkedHashSet f2259q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public i4.h f2260r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a1.d f2261s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f2262t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final l4.d0 f2263u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final i4.u0 f2264v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final t3.h f2265w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final h f2266x;

    static {
        Object obj = l0.b.f3495g;
        if (obj == null) {
            obj = m4.c.f3818b;
        }
        f2241y = new l4.d0(obj);
        f2242z = new AtomicReference(Boolean.FALSE);
    }

    public l2(t3.h hVar) {
        g gVar = new g(new d2(0, this));
        this.f2243a = gVar;
        this.f2244b = new a2.h(7);
        this.f2245c = new Object();
        this.f2248f = new ArrayList();
        this.f2250h = new j.a0();
        this.f2251i = new h0.e(new e0[16]);
        this.f2252j = new ArrayList();
        this.f2253k = new ArrayList();
        this.f2254l = new j.z();
        this.f2255m = new a2.h(6);
        this.f2256n = new j.z();
        this.f2257o = new j.z();
        this.f2263u = new l4.d0(g2.f2179f);
        new AtomicReference(n0.i.f4071b);
        i4.u0 u0Var = new i4.u0((i4.s0) hVar.j(i4.s.f2774e));
        u0Var.g(new c2(1, this));
        this.f2264v = u0Var;
        this.f2265w = hVar.k(gVar).k(u0Var);
        this.f2266x = new h(9);
    }

    public static final void C(ArrayList arrayList, l2 l2Var, e0 e0Var) {
        arrayList.clear();
        synchronized (l2Var.f2245c) {
            Iterator it = l2Var.f2253k.iterator();
            if (it.hasNext()) {
                ((g1) it.next()).getClass();
                throw null;
            }
        }
    }

    public static final e0 s(l2 l2Var, e0 e0Var, j.a0 a0Var) {
        p0.b bVarC;
        if (e0Var.f2149w.F || e0Var.f2150x == 3) {
            return null;
        }
        LinkedHashSet linkedHashSet = l2Var.f2259q;
        if (linkedHashSet == null || !linkedHashSet.contains(e0Var)) {
            c2 c2Var = new c2(0, e0Var);
            e2 e2Var = new e2(e0Var, 0, a0Var);
            p0.f fVarJ = p0.m.j();
            p0.b bVar = fVarJ instanceof p0.b ? (p0.b) fVarJ : null;
            if (bVar == null || (bVarC = bVar.C(c2Var, e2Var)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
            }
            try {
                p0.f fVarJ2 = bVarC.j();
                if (a0Var != null) {
                    try {
                        if (a0Var.h()) {
                            f2 f2Var = new f2(a0Var, 0, e0Var);
                            v vVar = e0Var.f2149w;
                            if (vVar.F) {
                                z.a("Preparing a composition while composing is not supported");
                            }
                            vVar.F = true;
                            try {
                                f2Var.a();
                                vVar.F = false;
                            } catch (Throwable th) {
                                vVar.F = false;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        p0.f.q(fVarJ2);
                        throw th2;
                    }
                }
                boolean zW = e0Var.w();
                p0.f.q(fVarJ2);
                u(bVarC);
                if (zW) {
                    return e0Var;
                }
            } catch (Throwable th3) {
                u(bVarC);
                throw th3;
            }
        }
        return null;
    }

    public static final boolean t(l2 l2Var) {
        boolean zY;
        synchronized (l2Var.f2245c) {
            if (l2Var.f2250h.g()) {
                return l2Var.y();
            }
            List listB = l2Var.B();
            h0.h hVar = new h0.h(l2Var.f2250h);
            l2Var.f2250h = new j.a0();
            try {
                int size = listB.size();
                for (int i5 = 0; i5 < size; i5++) {
                    ((e0) listB.get(i5)).x(hVar);
                    if (((g2) l2Var.f2263u.getValue()).compareTo(g2.f2178e) <= 0) {
                        break;
                    }
                }
                synchronized (l2Var.f2245c) {
                    if (l2Var.w() != null) {
                        throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
                    }
                    zY = l2Var.y();
                }
                return zY;
            } catch (Throwable th) {
                synchronized (l2Var.f2245c) {
                    j.a0 a0Var = l2Var.f2250h;
                    a0Var.getClass();
                    for (Object obj : hVar) {
                        a0Var.f3067b[a0Var.d(obj)] = obj;
                    }
                    throw th;
                }
            }
        }
    }

    public static void u(p0.b bVar) {
        try {
            if (bVar.w() instanceof p0.g) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.");
            }
            bVar.c();
        } catch (Throwable th) {
            bVar.c();
            throw th;
        }
    }

    public final boolean A() {
        boolean z2;
        synchronized (this.f2245c) {
            z2 = this.f2250h.h() || this.f2251i.f2578f != 0 || x() || z();
        }
        return z2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    public final List B() {
        ?? r1 = this.f2249g;
        if (r1 != 0) {
            return r1;
        }
        ArrayList arrayList = this.f2248f;
        List arrayList2 = arrayList.isEmpty() ? q3.s.f5477d : new ArrayList(arrayList);
        this.f2249g = arrayList2;
        return arrayList2;
    }

    /* JADX WARN: Code duplicated, block: B:162:0x0179 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x017b A[Catch: all -> 0x01d0, LOOP:3: B:30:0x00c3->B:55:0x017b, LOOP_END, TryCatch #0 {all -> 0x01d0, blocks: (B:38:0x0122, B:58:0x0192, B:60:0x01a2, B:62:0x01ac, B:67:0x01c8, B:70:0x01d3, B:55:0x017b, B:41:0x013a, B:42:0x0144, B:50:0x015c), top: B:141:0x0122 }] */
    public final List D(List list, j.a0 a0Var) throws Throwable {
        p0.b bVarC;
        p0.b bVar;
        p0.f fVar;
        ArrayList arrayList;
        HashMap map;
        Iterator it;
        boolean z2;
        boolean z3;
        boolean z4;
        int i5;
        boolean z5;
        int iNumberOfTrailingZeros;
        HashMap map2 = new HashMap(list.size());
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            Object obj = list.get(i6);
            ((g1) obj).getClass();
            Object arrayList2 = map2.get(null);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                map2.put(null, arrayList2);
            }
            ((ArrayList) arrayList2).add(obj);
        }
        Iterator it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            e0 e0Var = (e0) entry.getKey();
            List list2 = (List) entry.getValue();
            if (e0Var.f2149w.F) {
                z.a("Check failed");
            }
            c2 c2Var = new c2(0, e0Var);
            e2 e2Var = new e2(e0Var, 0, a0Var);
            p0.f fVarJ = p0.m.j();
            p0.b bVar2 = fVarJ instanceof p0.b ? (p0.b) fVarJ : null;
            if (bVar2 == null || (bVarC = bVar2.C(c2Var, e2Var)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
            }
            try {
                p0.f fVarJ2 = bVarC.j();
                try {
                    synchronized (this.f2245c) {
                        try {
                            arrayList = new ArrayList(list2.size());
                            int size2 = list2.size();
                            for (int i7 = 0; i7 < size2; i7++) {
                                g1 g1Var = (g1) list2.get(i7);
                                j.z zVar = this.f2254l;
                                g1Var.getClass();
                                Object objA = h0.a.a(zVar);
                                arrayList.add(new p3.f(g1Var, objA));
                            }
                            int size3 = arrayList.size();
                            int i8 = 0;
                            while (true) {
                                if (i8 >= size3) {
                                    map = map2;
                                    it = it2;
                                    bVar = bVarC;
                                    fVar = fVarJ2;
                                    z2 = false;
                                    break;
                                }
                                p3.f fVar2 = (p3.f) arrayList.get(i8);
                                if (fVar2.f5015e == null) {
                                    a2.h hVar = this.f2255m;
                                    ((g1) fVar2.f5014d).getClass();
                                    j.z zVar2 = (j.z) hVar.f49e;
                                    int i9 = zVar2.f3179d;
                                    int i10 = 0;
                                    int i11 = 0;
                                    while (true) {
                                        long[] jArr = zVar2.f3176a;
                                        int i12 = i10 >> 3;
                                        int i13 = (i10 & 7) << 3;
                                        long j5 = jArr[i12] >>> i13;
                                        it = it2;
                                        long j6 = j5 | ((jArr[i12 + 1] << (64 - i13)) & ((-i13) >> 63));
                                        map = map2;
                                        bVar = bVarC;
                                        fVar = fVarJ2;
                                        long j7 = (((long) 0) * 72340172838076673L) ^ j6;
                                        long j8 = (j7 - 72340172838076673L) & (~j7) & (-9187201950435737472L);
                                        while (j8 != 0) {
                                            try {
                                                iNumberOfTrailingZeros = (i10 + (Long.numberOfTrailingZeros(j8) >> 3)) & i9;
                                                i5 = size3;
                                                if (b4.l.a(zVar2.f3177b[iNumberOfTrailingZeros], null)) {
                                                    break;
                                                }
                                                j8 &= j8 - 1;
                                                size3 = i5;
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        }
                                        i5 = size3;
                                        if ((j6 & ((~j6) << 6) & (-9187201950435737472L)) != 0) {
                                            iNumberOfTrailingZeros = -1;
                                            break;
                                        }
                                        i11 += 8;
                                        i10 = (i10 + i11) & i9;
                                        it2 = it;
                                        map2 = map;
                                        bVarC = bVar;
                                        fVarJ2 = fVar;
                                        size3 = i5;
                                    }
                                    z5 = iNumberOfTrailingZeros >= 0;
                                    if (z5) {
                                        z2 = true;
                                        break;
                                    }
                                    i8++;
                                    it2 = it;
                                    map2 = map;
                                    bVarC = bVar;
                                    fVarJ2 = fVar;
                                    size3 = i5;
                                } else {
                                    map = map2;
                                    it = it2;
                                    i5 = size3;
                                    bVar = bVarC;
                                    fVar = fVarJ2;
                                }
                                if (z5) {
                                    z2 = true;
                                    break;
                                }
                                i8++;
                                it2 = it;
                                map2 = map;
                                bVarC = bVar;
                                fVarJ2 = fVar;
                                size3 = i5;
                            }
                            if (z2) {
                                ArrayList arrayList3 = new ArrayList(arrayList.size());
                                int size4 = arrayList.size();
                                for (int i14 = 0; i14 < size4; i14++) {
                                    p3.f fVar3 = (p3.f) arrayList.get(i14);
                                    if (fVar3.f5015e == null) {
                                        a2.h hVar2 = this.f2255m;
                                        ((g1) fVar3.f5014d).getClass();
                                        j.z zVar3 = (j.z) hVar2.f49e;
                                        if (zVar3.f3180e == 0) {
                                            ((j.z) hVar2.f50f).a();
                                        }
                                    }
                                    arrayList3.add(fVar3);
                                }
                                arrayList = arrayList3;
                            }
                            try {
                            } catch (Throwable th2) {
                                th = th2;
                                p0.f.q(fVar);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    int size5 = arrayList.size();
                    int i15 = 0;
                    while (true) {
                        if (i15 >= size5) {
                            z3 = true;
                            break;
                        }
                        if (!(((p3.f) arrayList.get(i15)).f5015e == null)) {
                            z3 = false;
                            break;
                        }
                        i15++;
                    }
                    if (!z3) {
                        int size6 = arrayList.size();
                        int i16 = 0;
                        while (true) {
                            if (i16 >= size6) {
                                z4 = true;
                                break;
                            }
                            if (!(((p3.f) arrayList.get(i16)).f5015e != null)) {
                                z4 = false;
                                break;
                            }
                            i16++;
                        }
                        if (!z4) {
                            ArrayList arrayList4 = new ArrayList(arrayList.size());
                            int size7 = arrayList.size();
                            for (int i17 = 0; i17 < size7; i17++) {
                                p3.f fVar4 = (p3.f) arrayList.get(i17);
                                if (fVar4.f5015e == null) {
                                }
                            }
                            synchronized (this.f2245c) {
                                q3.q.U(this.f2253k, arrayList4);
                            }
                            ArrayList arrayList5 = new ArrayList(arrayList.size());
                            int size8 = arrayList.size();
                            for (int i18 = 0; i18 < size8; i18++) {
                                Object obj2 = arrayList.get(i18);
                                if (((p3.f) obj2).f5015e != null) {
                                    arrayList5.add(obj2);
                                }
                            }
                            arrayList = arrayList5;
                        }
                    }
                    e0Var.q(arrayList);
                    try {
                        p0.f.q(fVar);
                        u(bVar);
                        it2 = it;
                        map2 = map;
                    } catch (Throwable th4) {
                        th = th4;
                        u(bVar);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    bVar = bVarC;
                    fVar = fVarJ2;
                }
            } catch (Throwable th6) {
                th = th6;
                bVar = bVarC;
            }
        }
        return q3.l.f0(map2.keySet());
    }

    public final void E(Throwable th, e0 e0Var) throws Throwable {
        if (!((Boolean) f2242z.get()).booleanValue() || (th instanceof n)) {
            synchronized (this.f2245c) {
                Log.e("ComposeInternal", "Error was captured in composition.", th);
                a1.d dVar = this.f2261s;
                if (dVar != null) {
                    throw ((Throwable) dVar.f31e);
                }
                this.f2261s = new a1.d(6, th);
            }
            throw th;
        }
        synchronized (this.f2245c) {
            try {
                Log.e("ComposeInternal", "Error was captured in composition while live edit was enabled.", th);
                this.f2252j.clear();
                this.f2251i.g();
                this.f2250h = new j.a0();
                this.f2253k.clear();
                this.f2254l.a();
                this.f2256n.a();
                this.f2261s = new a1.d(6, th);
                if (e0Var != null) {
                    F(e0Var);
                }
                w();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final void F(e0 e0Var) {
        ArrayList arrayList = this.f2258p;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.f2258p = arrayList;
        }
        if (!arrayList.contains(e0Var)) {
            arrayList.add(e0Var);
        }
        if (this.f2248f.remove(e0Var)) {
            this.f2249g = null;
        }
    }

    @Override // f0.b0
    public final void a(e0 e0Var, n0.d dVar) throws Throwable {
        g2 g2Var;
        boolean zContains;
        p0.b bVarC;
        boolean z2 = e0Var.f2149w.F;
        synchronized (this.f2245c) {
            g2 g2Var2 = (g2) this.f2263u.getValue();
            g2Var = g2.f2178e;
            zContains = g2Var2.compareTo(g2Var) > 0 ? true ^ B().contains(e0Var) : true;
        }
        try {
            c2 c2Var = new c2(0, e0Var);
            e2 e2Var = new e2(e0Var, 0, null);
            p0.f fVarJ = p0.m.j();
            p0.b bVar = fVarJ instanceof p0.b ? (p0.b) fVarJ : null;
            if (bVar == null || (bVarC = bVar.C(c2Var, e2Var)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
            }
            try {
                p0.f fVarJ2 = bVarC.j();
                try {
                    e0Var.i(dVar);
                    p0.f.q(fVarJ2);
                    u(bVarC);
                    synchronized (this.f2245c) {
                        if (((g2) this.f2263u.getValue()).compareTo(g2Var) > 0 && !B().contains(e0Var)) {
                            this.f2248f.add(e0Var);
                            this.f2249g = null;
                        }
                    }
                    if (!z2) {
                        p0.m.j().m();
                    }
                    try {
                        synchronized (this.f2245c) {
                            ArrayList arrayList = this.f2253k;
                            if (arrayList.size() > 0) {
                                ((g1) arrayList.get(0)).getClass();
                                throw null;
                            }
                        }
                        try {
                            e0Var.d();
                            e0Var.f();
                            if (z2) {
                                return;
                            }
                            p0.m.j().m();
                        } catch (Throwable th) {
                            E(th, null);
                        }
                    } catch (Throwable th2) {
                        E(th2, e0Var);
                    }
                } catch (Throwable th3) {
                    p0.f.q(fVarJ2);
                    throw th3;
                }
            } catch (Throwable th4) {
                u(bVarC);
                throw th4;
            }
        } catch (Throwable th5) {
            if (zContains) {
                synchronized (this.f2245c) {
                }
            }
            E(th5, e0Var);
        }
    }

    @Override // f0.b0
    public final boolean c() {
        return ((Boolean) f2242z.get()).booleanValue();
    }

    @Override // f0.b0
    public final boolean d() {
        return false;
    }

    @Override // f0.b0
    public final boolean e() {
        return false;
    }

    @Override // f0.b0
    public final long f() {
        return 1000;
    }

    @Override // f0.b0
    public final a0 g() {
        return null;
    }

    @Override // f0.b0
    public final t3.h i() {
        return this.f2265w;
    }

    @Override // f0.b0
    public final boolean j() {
        return false;
    }

    @Override // f0.b0
    public final void k(e0 e0Var) {
        i4.f fVarW;
        synchronized (this.f2245c) {
            if (this.f2251i.h(e0Var)) {
                fVarW = null;
            } else {
                this.f2251i.b(e0Var);
                fVarW = w();
            }
        }
        if (fVarW != null) {
            ((i4.h) fVarW).resumeWith(p3.n.f5026a);
        }
    }

    @Override // f0.b0
    public final f1 l(g1 g1Var) {
        f1 f1Var;
        synchronized (this.f2245c) {
            f1Var = (f1) this.f2256n.g(g1Var);
        }
        return f1Var;
    }

    @Override // f0.b0
    public final void m(Set set) {
    }

    @Override // f0.b0
    public final void o(e0 e0Var) {
        synchronized (this.f2245c) {
            try {
                LinkedHashSet linkedHashSet = this.f2259q;
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                    this.f2259q = linkedHashSet;
                }
                linkedHashSet.add(e0Var);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // f0.b0
    public final void r(e0 e0Var) {
        synchronized (this.f2245c) {
            if (this.f2248f.remove(e0Var)) {
                this.f2249g = null;
            }
            this.f2251i.l(e0Var);
            this.f2252j.remove(e0Var);
        }
    }

    public final void v() {
        synchronized (this.f2245c) {
            if (((g2) this.f2263u.getValue()).compareTo(g2.f2181h) >= 0) {
                l4.d0 d0Var = this.f2263u;
                g2 g2Var = g2.f2178e;
                d0Var.getClass();
                d0Var.i(null, g2Var);
            }
        }
        this.f2264v.b(null);
    }

    public final i4.f w() {
        l4.d0 d0Var = this.f2263u;
        int iCompareTo = ((g2) d0Var.getValue()).compareTo(g2.f2178e);
        ArrayList arrayList = this.f2253k;
        ArrayList arrayList2 = this.f2252j;
        h0.e eVar = this.f2251i;
        if (iCompareTo > 0) {
            a1.d dVar = this.f2261s;
            g2 g2Var = g2.f2182i;
            g2 g2Var2 = g2.f2179f;
            if (dVar == null) {
                if (this.f2246d == null) {
                    this.f2250h = new j.a0();
                    eVar.g();
                    if (x() || z()) {
                        g2Var2 = g2.f2180g;
                    }
                } else {
                    g2Var2 = (eVar.f2578f == 0 && !this.f2250h.h() && arrayList2.isEmpty() && arrayList.isEmpty() && !x() && !z() && this.f2254l.f3180e == 0) ? g2.f2181h : g2Var;
                }
            }
            d0Var.getClass();
            d0Var.i(null, g2Var2);
            if (g2Var2 != g2Var) {
                return null;
            }
            i4.h hVar = this.f2260r;
            this.f2260r = null;
            return hVar;
        }
        List listB = B();
        int size = listB.size();
        for (int i5 = 0; i5 < size; i5++) {
        }
        this.f2248f.clear();
        this.f2249g = q3.s.f5477d;
        this.f2250h = new j.a0();
        eVar.g();
        arrayList2.clear();
        arrayList.clear();
        this.f2258p = null;
        i4.h hVar2 = this.f2260r;
        if (hVar2 != null) {
            hVar2.e(null);
        }
        this.f2260r = null;
        this.f2261s = null;
        return null;
    }

    public final boolean x() {
        return !this.f2262t && (((n0.a) ((f3.a) this.f2243a.f2174f).f2451c).get() & 134217727) > 0;
    }

    public final boolean y() {
        return this.f2251i.f2578f != 0 || x() || z() || this.f2254l.f3180e != 0;
    }

    public final boolean z() {
        return !this.f2262t && (((n0.a) ((f3.a) this.f2244b.f50f).f2451c).get() & 134217727) > 0;
    }
}
