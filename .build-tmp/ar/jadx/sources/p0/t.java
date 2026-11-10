package p0;

import f0.c2;
import f0.n0;
import f0.x1;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b4.m f4745a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4747c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y0.x f4752h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public s f4753i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReference f4746b = new AtomicReference(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f0.y f4748d = new f0.y(6, this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c2 f4749e = new c2(5, this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h0.e f4750f = new h0.e(new s[16]);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f4751g = new Object();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f4754j = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public t(a4.c cVar) {
        this.f4745a = (b4.m) cVar;
    }

    public final void a() {
        synchronized (this.f4751g) {
            h0.e eVar = this.f4750f;
            Object[] objArr = eVar.f2576d;
            int i5 = eVar.f2578f;
            for (int i6 = 0; i6 < i5; i6++) {
                s sVar = (s) objArr[i6];
                sVar.f4736e.a();
                sVar.f4737f.a();
                sVar.f4743l.a();
                sVar.f4744m.clear();
            }
        }
    }

    public final boolean b() {
        boolean z2;
        Set set;
        Set set2;
        synchronized (this.f4751g) {
            z2 = this.f4747c;
        }
        if (z2) {
            return false;
        }
        boolean z3 = false;
        while (true) {
            AtomicReference atomicReference = this.f4746b;
            while (true) {
                Object obj = atomicReference.get();
                set = null;
                Object obj2 = null;
                Object objSubList = null;
                if (obj == null) {
                    break;
                }
                if (obj instanceof Set) {
                    set2 = (Set) obj;
                } else {
                    if (!(obj instanceof List)) {
                        f0.z.b("Unexpected notification");
                        throw new i4.p();
                    }
                    List list = (List) obj;
                    Set set3 = (Set) list.get(0);
                    if (list.size() == 2) {
                        objSubList = list.get(1);
                    } else if (list.size() > 2) {
                        objSubList = list.subList(1, list.size());
                    }
                    set2 = set3;
                    obj2 = objSubList;
                }
                do {
                    if (atomicReference.compareAndSet(obj, obj2)) {
                        set = set2;
                        break;
                    }
                } while (atomicReference.get() == obj);
            }
            if (set == null) {
                return z3;
            }
            synchronized (this.f4751g) {
                h0.e eVar = this.f4750f;
                Object[] objArr = eVar.f2576d;
                int i5 = eVar.f2578f;
                for (int i6 = 0; i6 < i5; i6++) {
                    z3 = ((s) objArr[i6]).a(set) || z3;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:90:0x01f7  */
    public final void c(Object obj, a4.c cVar, a4.a aVar) {
        Object obj2;
        s sVar;
        boolean z2;
        j.x xVar;
        f c0Var;
        Object obj3;
        Object obj4;
        Object obj5;
        long[] jArr;
        Object obj6;
        int i5;
        long[] jArr2;
        long j5;
        synchronized (this.f4751g) {
            h0.e eVar = this.f4750f;
            Object[] objArr = eVar.f2576d;
            int i6 = eVar.f2578f;
            int i7 = 0;
            while (true) {
                if (i7 >= i6) {
                    obj2 = null;
                    break;
                }
                obj2 = objArr[i7];
                if (((s) obj2).f4732a == cVar) {
                    break;
                } else {
                    i7++;
                }
            }
            sVar = (s) obj2;
            z2 = true;
            if (sVar == null) {
                b4.l.c("null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>", cVar);
                b4.z.c(1, cVar);
                sVar = new s(cVar);
                eVar.b(sVar);
            }
        }
        s sVar2 = this.f4753i;
        long j6 = this.f4754j;
        if (j6 != -1 && j6 != n0.i.b()) {
            x1.a("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + j6 + "), currentThread={id=" + n0.i.b() + ", name=" + Thread.currentThread().getName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
        }
        try {
            this.f4753i = sVar;
            this.f4754j = n0.i.b();
            c2 c2Var = this.f4749e;
            Object obj7 = sVar.f4733b;
            j.x xVar2 = sVar.f4734c;
            int i8 = sVar.f4735d;
            sVar.f4733b = obj;
            sVar.f4734c = (j.x) sVar.f4737f.e(obj);
            if (sVar.f4735d == -1) {
                sVar.f4735d = Long.hashCode(m.j().g());
            }
            f0.u uVar = sVar.f4740i;
            h0.e eVarM = f0.l.m();
            try {
                eVarM.b(uVar);
                if (c2Var == null) {
                    aVar.a();
                    xVar = xVar2;
                } else {
                    f fVar = (f) m.f4718b.q();
                    if (fVar instanceof c0) {
                        xVar = xVar2;
                        if (((c0) fVar).f4683t == n0.i.b()) {
                            a4.c cVar2 = ((c0) fVar).f4681r;
                            a4.c cVar3 = ((c0) fVar).f4682s;
                            try {
                                ((c0) fVar).f4681r = m.k(c2Var, cVar2, true);
                                ((c0) fVar).f4682s = cVar3;
                                aVar.a();
                                ((c0) fVar).f4681r = cVar2;
                                ((c0) fVar).f4682s = cVar3;
                            } catch (Throwable th) {
                                ((c0) fVar).f4681r = cVar2;
                                ((c0) fVar).f4682s = cVar3;
                                throw th;
                            }
                        }
                    } else {
                        xVar = xVar2;
                    }
                    if (fVar == null || (fVar instanceof b)) {
                        c0Var = new c0(fVar instanceof b ? (b) fVar : null, c2Var, null, true, false);
                    } else {
                        c0Var = fVar.u(c2Var);
                    }
                    try {
                        f fVarJ = c0Var.j();
                        try {
                            aVar.a();
                            f.q(fVarJ);
                            c0Var.c();
                        } catch (Throwable th2) {
                            try {
                                f.q(fVarJ);
                                throw th2;
                            } catch (Throwable th3) {
                                th = th3;
                                try {
                                    c0Var.c();
                                    throw th;
                                } catch (Throwable th4) {
                                    th = th4;
                                    eVarM.m(eVarM.f2578f - 1);
                                    throw th;
                                }
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                eVarM.m(eVarM.f2578f - 1);
                Object obj8 = sVar.f4733b;
                b4.l.b(obj8);
                int i9 = sVar.f4735d;
                j.x xVar3 = sVar.f4734c;
                if (xVar3 != null) {
                    long[] jArr3 = xVar3.f3168a;
                    int length = jArr3.length - 2;
                    if (length >= 0) {
                        int i10 = 0;
                        while (true) {
                            long j7 = jArr3[i10];
                            boolean z3 = z2;
                            obj4 = obj7;
                            if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i11 = 8 - ((~(i10 - length)) >>> 31);
                                int i12 = 0;
                                while (i12 < i11) {
                                    if ((j7 & 255) < 128) {
                                        i5 = i12;
                                        int i13 = (i10 << 3) + i5;
                                        jArr2 = jArr3;
                                        Object obj9 = xVar3.f3169b[i13];
                                        j5 = j7;
                                        boolean z4 = xVar3.f3170c[i13] != i9 ? z3 : false;
                                        if (z4) {
                                            j.z zVar = sVar.f4736e;
                                            d4.a.F(zVar, obj9, obj8);
                                            obj6 = obj8;
                                            if ((obj9 instanceof n0) && !zVar.b(obj9)) {
                                                d4.a.G(sVar.f4743l, obj9);
                                                sVar.f4744m.remove(obj9);
                                            }
                                        } else {
                                            obj6 = obj8;
                                        }
                                        if (z4) {
                                            xVar3.e(i13);
                                        }
                                    } else {
                                        obj6 = obj8;
                                        i5 = i12;
                                        jArr2 = jArr3;
                                        j5 = j7;
                                    }
                                    j7 = j5 >> 8;
                                    i12 = i5 + 1;
                                    jArr3 = jArr2;
                                    obj8 = obj6;
                                }
                                obj5 = obj8;
                                jArr = jArr3;
                                if (i11 != 8) {
                                    break;
                                }
                            } else {
                                obj5 = obj8;
                                jArr = jArr3;
                            }
                            if (i10 == length) {
                                break;
                            }
                            i10++;
                            z2 = z3;
                            obj7 = obj4;
                            jArr3 = jArr;
                            obj8 = obj5;
                        }
                        obj3 = obj4;
                    } else {
                        obj3 = obj7;
                    }
                } else {
                    obj3 = obj7;
                }
                sVar.f4733b = obj3;
                sVar.f4734c = xVar;
                sVar.f4735d = i8;
                this.f4753i = sVar2;
                this.f4754j = j6;
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (Throwable th7) {
            this.f4753i = sVar2;
            this.f4754j = j6;
            throw th7;
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.Collection] */
    public final void d() {
        f0.y yVar = this.f4748d;
        m.e(m.f4717a);
        synchronized (m.f4719c) {
            m.f4724h = q3.l.d0(m.f4724h, yVar);
        }
        this.f4752h = new y0.x(2, yVar);
    }
}
