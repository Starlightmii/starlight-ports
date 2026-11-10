package n1;

import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import q1.h3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 implements f0.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p1.d0 f4091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f0.b0 f4092e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4093f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4094g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4103p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4104q;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f4095h = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashMap f4096i = new HashMap();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final w f4097j = new w(this);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final u f4098k = new u(this);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f4099l = new HashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a1 f4100m = new a1();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LinkedHashMap f4101n = new LinkedHashMap();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h0.e f4102o = new h0.e(new Object[16]);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f4105r = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";

    public b0(p1.d0 d0Var) {
        this.f4091d = d0Var;
    }

    @Override // f0.m
    public final void a() {
        f(false);
    }

    @Override // f0.m
    public final void b() {
        p1.d0 d0Var = this.f4091d;
        d0Var.f4790n = true;
        HashMap map = this.f4095h;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            f0.e0 e0Var = ((t) it.next()).f4156c;
            if (e0Var != null) {
                e0Var.l();
            }
        }
        d0Var.L();
        d0Var.f4790n = false;
        map.clear();
        this.f4096i.clear();
        this.f4104q = 0;
        this.f4103p = 0;
        this.f4099l.clear();
        e();
    }

    @Override // f0.m
    public final void c() {
        f(true);
    }

    public final void d(int i5) {
        boolean z2;
        boolean z3 = false;
        this.f4103p = 0;
        int i6 = (((h0.b) this.f4091d.o()).f2570d.f2578f - this.f4104q) - 1;
        if (i5 <= i6) {
            this.f4100m.clear();
            if (i5 <= i6) {
                int i7 = i5;
                while (true) {
                    Object obj = this.f4095h.get((p1.d0) ((h0.b) this.f4091d.o()).get(i7));
                    b4.l.b(obj);
                    this.f4100m.f4089d.add(((t) obj).f4154a);
                    if (i7 == i6) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
            this.f4100m.clear();
            p0.f fVarD = p0.r.d();
            a4.c cVarE = fVarD != null ? fVarD.e() : null;
            p0.f fVarG = p0.r.g(fVarD);
            z2 = false;
            while (i6 >= i5) {
                try {
                    p1.d0 d0Var = (p1.d0) ((h0.b) this.f4091d.o()).get(i6);
                    Object obj2 = this.f4095h.get(d0Var);
                    b4.l.b(obj2);
                    t tVar = (t) obj2;
                    Object obj3 = tVar.f4154a;
                    if (this.f4100m.f4089d.contains(obj3)) {
                        this.f4103p++;
                        if (((Boolean) tVar.f4159f.getValue()).booleanValue()) {
                            p1.j0 j0Var = d0Var.f4802z;
                            j0Var.f4910r.f4877n = 3;
                            p1.h0 h0Var = j0Var.f4911s;
                            if (h0Var != null) {
                                h0Var.f4851l = 3;
                            }
                            tVar.f4159f.setValue(Boolean.FALSE);
                            z2 = true;
                        }
                    } else {
                        p1.d0 d0Var2 = this.f4091d;
                        d0Var2.f4790n = true;
                        this.f4095h.remove(d0Var);
                        f0.e0 e0Var = tVar.f4156c;
                        if (e0Var != null) {
                            e0Var.l();
                        }
                        this.f4091d.M(i6, 1);
                        d0Var2.f4790n = false;
                    }
                    this.f4096i.remove(obj3);
                    i6--;
                } catch (Throwable th) {
                    p0.r.j(fVarD, fVarG, cVarE);
                    throw th;
                }
            }
            p0.r.j(fVarD, fVarG, cVarE);
        } else {
            z2 = false;
        }
        if (z2) {
            synchronized (p0.m.f4719c) {
                j.a0 a0Var = p0.m.f4726j.f4666h;
                if (a0Var != null && a0Var.h()) {
                    z3 = true;
                }
            }
            if (z3) {
                p0.m.a();
            }
        }
        e();
    }

    public final void e() {
        int i5 = ((h0.b) this.f4091d.o()).f2570d.f2578f;
        HashMap map = this.f4095h;
        if (map.size() != i5) {
            throw new IllegalArgumentException(("Inconsistency between the count of nodes tracked by the state (" + map.size() + ") and the children count on the SubcomposeLayout (" + i5 + "). Are you trying to use the state of the disposed SubcomposeLayout?").toString());
        }
        if ((i5 - this.f4103p) - this.f4104q < 0) {
            StringBuilder sbG = b.b.g("Incorrect state. Total children ", i5, ". Reusable children ");
            sbG.append(this.f4103p);
            sbG.append(". Precomposed children ");
            sbG.append(this.f4104q);
            throw new IllegalArgumentException(sbG.toString().toString());
        }
        HashMap map2 = this.f4099l;
        if (map2.size() == this.f4104q) {
            return;
        }
        throw new IllegalArgumentException(("Incorrect state. Precomposed children " + this.f4104q + ". Map size " + map2.size()).toString());
    }

    public final void f(boolean z2) {
        this.f4104q = 0;
        this.f4099l.clear();
        p1.d0 d0Var = this.f4091d;
        int i5 = ((h0.b) d0Var.o()).f2570d.f2578f;
        if (this.f4103p != i5) {
            this.f4103p = i5;
            p0.f fVarD = p0.r.d();
            a4.c cVarE = fVarD != null ? fVarD.e() : null;
            p0.f fVarG = p0.r.g(fVarD);
            for (int i6 = 0; i6 < i5; i6++) {
                try {
                    p1.d0 d0Var2 = (p1.d0) ((h0.b) d0Var.o()).get(i6);
                    t tVar = (t) this.f4095h.get(d0Var2);
                    if (tVar != null && ((Boolean) tVar.f4159f.getValue()).booleanValue()) {
                        p1.j0 j0Var = d0Var2.f4802z;
                        j0Var.f4910r.f4877n = 3;
                        p1.h0 h0Var = j0Var.f4911s;
                        if (h0Var != null) {
                            h0Var.f4851l = 3;
                        }
                        if (z2) {
                            f0.e0 e0Var = tVar.f4156c;
                            if (e0Var != null) {
                                e0Var.k();
                            }
                            tVar.f4159f = f0.l.s(Boolean.FALSE);
                        } else {
                            tVar.f4159f.setValue(Boolean.FALSE);
                        }
                        tVar.f4154a = v0.f4177a;
                    }
                } catch (Throwable th) {
                    p0.r.j(fVarD, fVarG, cVarE);
                    throw th;
                }
            }
            p0.r.j(fVarD, fVarG, cVarE);
            this.f4096i.clear();
        }
        e();
    }

    /* JADX WARN: Code duplicated, block: B:39:0x007f A[Catch: all -> 0x00b1, TryCatch #0 {all -> 0x00b1, blocks: (B:30:0x0057, B:32:0x0063, B:34:0x0075, B:41:0x008e, B:52:0x00a5, B:42:0x0092, B:43:0x0094, B:50:0x009e, B:51:0x009f, B:57:0x00b3, B:58:0x00b4, B:39:0x007f, B:59:0x00b5, B:60:0x00bc, B:44:0x0095, B:46:0x0099), top: B:63:0x0057, inners: #2 }] */
    public final void g(p1.d0 d0Var, Enum r13, a4.e eVar) {
        boolean z2;
        HashMap map = this.f4095h;
        Object obj = map.get(d0Var);
        Object obj2 = obj;
        if (obj == null) {
            n0.d dVar = g.f4116a;
            t tVar = new t();
            tVar.f4154a = r13;
            tVar.f4155b = dVar;
            tVar.f4156c = null;
            tVar.f4159f = f0.l.s(Boolean.TRUE);
            map.put(d0Var, tVar);
            obj2 = tVar;
        }
        t tVar2 = (t) obj2;
        f0.e0 e0Var = tVar2.f4156c;
        if (e0Var != null) {
            synchronized (e0Var.f2133g) {
                z2 = e0Var.f2143q.f3180e > 0;
            }
        } else {
            z2 = true;
        }
        if (tVar2.f4155b != eVar || z2 || tVar2.f4157d) {
            tVar2.f4155b = eVar;
            p0.f fVarD = p0.r.d();
            a4.c cVarE = fVarD != null ? fVarD.e() : null;
            p0.f fVarG = p0.r.g(fVarD);
            try {
                p1.d0 d0Var2 = this.f4091d;
                d0Var2.f4790n = true;
                a4.e eVar2 = tVar2.f4155b;
                f0.e0 e0Var2 = tVar2.f4156c;
                f0.b0 b0Var = this.f4092e;
                if (b0Var == null) {
                    throw new IllegalStateException("parent composition reference not set");
                }
                boolean z3 = tVar2.f4158e;
                n0.d dVar2 = new n0.d(-1750409193, true, new c0.l(tVar2, 6, eVar2));
                if (e0Var2 == null) {
                    ViewGroup.LayoutParams layoutParams = h3.f5224a;
                    e0Var2 = new f0.e0(b0Var, new a1.b(d0Var));
                } else {
                    if (e0Var2.f2150x == 3) {
                        ViewGroup.LayoutParams layoutParams2 = h3.f5224a;
                        e0Var2 = new f0.e0(b0Var, new a1.b(d0Var));
                    }
                }
                if (z3) {
                    synchronized (e0Var2.f2133g) {
                        if (e0Var2.f2150x == 1) {
                            e0Var2.f2150x = 0;
                        }
                    }
                    e0Var2.p();
                    e0Var2.j(dVar2);
                } else {
                    e0Var2.A(dVar2);
                }
                tVar2.f4156c = e0Var2;
                tVar2.f4158e = false;
                d0Var2.f4790n = false;
                p0.r.j(fVarD, fVarG, cVarE);
                tVar2.f4157d = false;
            } catch (Throwable th) {
                p0.r.j(fVarD, fVarG, cVarE);
                throw th;
            }
        }
    }

    public final p1.d0 h(Enum r11) {
        HashMap map;
        int i5;
        if (this.f4103p == 0) {
            return null;
        }
        p1.d0 d0Var = this.f4091d;
        int i6 = ((h0.b) d0Var.o()).f2570d.f2578f - this.f4104q;
        int i7 = i6 - this.f4103p;
        int i8 = i6 - 1;
        int i9 = i8;
        while (true) {
            map = this.f4095h;
            if (i9 < i7) {
                i5 = -1;
                break;
            }
            Object obj = map.get((p1.d0) ((h0.b) d0Var.o()).get(i9));
            b4.l.b(obj);
            if (((t) obj).f4154a.equals(r11)) {
                i5 = i9;
                break;
            }
            i9--;
        }
        if (i5 == -1) {
            while (true) {
                if (i8 < i7) {
                    i9 = i8;
                    break;
                }
                Object obj2 = map.get((p1.d0) ((h0.b) d0Var.o()).get(i8));
                b4.l.b(obj2);
                t tVar = (t) obj2;
                if (tVar.f4154a == v0.f4177a) {
                    tVar.f4154a = r11;
                    i9 = i8;
                    i5 = i9;
                    break;
                }
                i8--;
            }
        }
        if (i5 == -1) {
            return null;
        }
        if (i9 != i7) {
            d0Var.f4790n = true;
            d0Var.G(i9, i7, 1);
            d0Var.f4790n = false;
        }
        this.f4103p--;
        p1.d0 d0Var2 = (p1.d0) ((h0.b) d0Var.o()).get(i7);
        Object obj3 = map.get(d0Var2);
        b4.l.b(obj3);
        t tVar2 = (t) obj3;
        tVar2.f4159f = f0.l.s(Boolean.TRUE);
        tVar2.f4158e = true;
        tVar2.f4157d = true;
        return d0Var2;
    }
}
