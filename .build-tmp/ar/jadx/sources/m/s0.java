package m;

import c0.c2;
import c0.w2;
import f0.b2;
import f0.q1;
import f0.s1;
import f0.z2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f3751a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f3752b = f0.l.s(c());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f3753c = f0.l.s(new p0(c(), c()));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q1 f3754d = new q1(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q1 f3755e = new q1(Long.MIN_VALUE);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f3756f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final p0.q f3757g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p0.q f3758h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final s1 f3759i;

    public s0(c0 c0Var) {
        this.f3751a = c0Var;
        Boolean bool = Boolean.FALSE;
        this.f3756f = f0.l.s(bool);
        this.f3757g = new p0.q();
        this.f3758h = new p0.q();
        this.f3759i = f0.l.s(bool);
        f0.l.n(new b0.a(10, this));
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:53:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:57:0x010d  */
    /* JADX WARN: Code duplicated, block: B:61:0x0118  */
    public final void a(Object obj, f0.p pVar, int i5) {
        int i6;
        Object objK;
        f0.h hVar;
        i4.v vVar;
        boolean zH;
        Object objK2;
        f0.v vVar2 = (f0.v) pVar;
        vVar2.X(-1493585151);
        if ((i5 & 6) == 0) {
            i6 = ((i5 & 8) == 0 ? vVar2.f(obj) : vVar2.h(obj) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar2.f(this) ? 32 : 16;
        }
        if ((i6 & 19) == 18 && vVar2.z()) {
            vVar2.Q();
        } else if (((Boolean) this.f3759i.getValue()).booleanValue()) {
            vVar2.V(1823861403);
            vVar2.p(false);
        } else {
            vVar2.V(1822376658);
            s1 s1Var = this.f3752b;
            boolean zA = b4.l.a(s1Var.getValue(), obj);
            s1 s1Var2 = this.f3756f;
            q1 q1Var = this.f3755e;
            if (!zA) {
                this.f3753c.setValue(new p0(s1Var.getValue(), obj));
                if (!b4.l.a(c(), s1Var.getValue())) {
                    this.f3751a.f3640b.setValue(s1Var.getValue());
                }
                s1Var.setValue(obj);
                if (!(((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c != Long.MIN_VALUE)) {
                    s1Var2.setValue(Boolean.TRUE);
                }
                h();
            }
            if (b4.l.a(obj, c())) {
                if ((((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c != Long.MIN_VALUE) || ((Boolean) s1Var2.getValue()).booleanValue()) {
                    vVar2.V(1822607949);
                    objK = vVar2.K();
                    hVar = f0.o.f2281a;
                    if (objK == hVar) {
                        f0.i0 i0Var = new f0.i0(f0.l.l(vVar2));
                        vVar2.f0(i0Var);
                        objK = i0Var;
                    }
                    vVar = ((f0.i0) objK).f2194d;
                    zH = vVar2.h(vVar) | ((i6 & 112) == 32);
                    objK2 = vVar2.K();
                    if (zH) {
                        objK2 = new c2(vVar, 4, this);
                        vVar2.f0(objK2);
                    } else {
                        objK2 = new c2(vVar, 4, this);
                        vVar2.f0(objK2);
                    }
                    f0.l.d(vVar, this, (a4.c) objK2, vVar2);
                    vVar2.p(false);
                } else {
                    vVar2.V(1823851483);
                    vVar2.p(false);
                }
            } else {
                vVar2.V(1822607949);
                objK = vVar2.K();
                hVar = f0.o.f2281a;
                if (objK == hVar) {
                    f0.i0 i0Var2 = new f0.i0(f0.l.l(vVar2));
                    vVar2.f0(i0Var2);
                    objK = i0Var2;
                }
                vVar = ((f0.i0) objK).f2194d;
                zH = vVar2.h(vVar) | ((i6 & 112) == 32);
                objK2 = vVar2.K();
                if (zH || objK2 == hVar) {
                    objK2 = new c2(vVar, 4, this);
                    vVar2.f0(objK2);
                }
                f0.l.d(vVar, this, (a4.c) objK2, vVar2);
                vVar2.p(false);
            }
            vVar2.p(false);
        }
        b2 b2VarR = vVar2.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new w2(i5, 2, this, obj);
        }
    }

    public final long b() {
        p0.q qVar = this.f3757g;
        int size = qVar.size();
        long jMax = 0;
        for (int i5 = 0; i5 < size; i5++) {
            q1 q1Var = ((q0) qVar.get(i5)).f3745m;
            jMax = Math.max(jMax, ((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c);
        }
        p0.q qVar2 = this.f3758h;
        int size2 = qVar2.size();
        for (int i6 = 0; i6 < size2; i6++) {
            jMax = Math.max(jMax, ((s0) qVar2.get(i6)).b());
        }
        return jMax;
    }

    public final Object c() {
        return this.f3751a.f3640b.getValue();
    }

    public final long d() {
        q1 q1Var = this.f3754d;
        return ((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c;
    }

    public final p0 e() {
        return (p0) this.f3753c.getValue();
    }

    public final void f(long j5, boolean z2) {
        c0 c0Var = this.f3751a;
        s1 s1Var = c0Var.f3639a;
        q1 q1Var = this.f3755e;
        if (((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c == Long.MIN_VALUE) {
            q1Var.g(j5);
            c0Var.f3639a.setValue(Boolean.TRUE);
        } else if (!((Boolean) s1Var.getValue()).booleanValue()) {
            s1Var.setValue(Boolean.TRUE);
        }
        this.f3756f.setValue(Boolean.FALSE);
        p0.q qVar = this.f3757g;
        int size = qVar.size();
        boolean z3 = true;
        for (int i5 = 0; i5 < size; i5++) {
            q0 q0Var = (q0) qVar.get(i5);
            s1 s1Var2 = q0Var.f3740h;
            s1 s1Var3 = q0Var.f3740h;
            if (!((Boolean) s1Var2.getValue()).booleanValue()) {
                long jC = z2 ? q0Var.a().c() : j5;
                q0Var.f3743k.setValue(q0Var.a().b(jC));
                q0Var.f3744l = q0Var.a().f(jC);
                if (q0Var.a().g(jC)) {
                    s1Var3.setValue(Boolean.TRUE);
                }
            }
            if (!((Boolean) s1Var3.getValue()).booleanValue()) {
                z3 = false;
            }
        }
        p0.q qVar2 = this.f3758h;
        int size2 = qVar2.size();
        for (int i6 = 0; i6 < size2; i6++) {
            s0 s0Var = (s0) qVar2.get(i6);
            if (!b4.l.a(s0Var.f3752b.getValue(), s0Var.c())) {
                s0Var.f(j5, z2);
            }
            if (!b4.l.a(s0Var.f3752b.getValue(), s0Var.c())) {
                z3 = false;
            }
        }
        if (z3) {
            g();
        }
    }

    public final void g() {
        this.f3755e.g(Long.MIN_VALUE);
        Object value = this.f3752b.getValue();
        c0 c0Var = this.f3751a;
        c0Var.f3640b.setValue(value);
        this.f3754d.g(0L);
        c0Var.f3639a.setValue(Boolean.FALSE);
        p0.q qVar = this.f3758h;
        int size = qVar.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((s0) qVar.get(i5)).g();
        }
    }

    public final void h() {
        p0.q qVar = this.f3757g;
        int size = qVar.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((q0) qVar.get(i5)).f3741i.h(-2.0f);
        }
        p0.q qVar2 = this.f3758h;
        int size2 = qVar2.size();
        for (int i6 = 0; i6 < size2; i6++) {
            ((s0) qVar2.get(i6)).h();
        }
    }

    public final String toString() {
        p0.q qVar = this.f3757g;
        int size = qVar.size();
        String str = "Transition animation values: ";
        for (int i5 = 0; i5 < size; i5++) {
            str = str + ((q0) qVar.get(i5)) + ", ";
        }
        return str;
    }
}
