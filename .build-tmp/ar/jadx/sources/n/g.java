package n;

import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;
import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements x0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x0.c f3905d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final z f3906e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f3907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f3908g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3909h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f3910i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public j1.t f3911j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final r0.p f3912k;

    public g(Context context, v0 v0Var) {
        z zVar = new z(context, y0.l0.w(v0Var.f4034a));
        this.f3906e = zVar;
        f0.h hVar = f0.h.f2186g;
        p3.n nVar = p3.n.f5026a;
        this.f3907f = new s1(nVar, hVar);
        this.f3908g = true;
        this.f3910i = 0L;
        this.f3912k = j1.z.a(r0.m.f5680a, nVar, new b0.t(this, null, 8)).e(Build.VERSION.SDK_INT >= 31 ? new y(this, zVar) : new y(this, zVar, v0Var));
    }

    public final void a() {
        boolean zIsFinished;
        z zVar = this.f3906e;
        EdgeEffect edgeEffect = zVar.f4049d;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = edgeEffect.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = zVar.f4050e;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished = edgeEffect2.isFinished() || zIsFinished;
        }
        EdgeEffect edgeEffect3 = zVar.f4051f;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished = edgeEffect3.isFinished() || zIsFinished;
        }
        EdgeEffect edgeEffect4 = zVar.f4052g;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished = edgeEffect4.isFinished() || zIsFinished;
        }
        if (zIsFinished) {
            g();
        }
    }

    @Override // n.x0
    public final boolean b() {
        z zVar = this.f3906e;
        EdgeEffect edgeEffect = zVar.f4049d;
        h hVar = h.f3917a;
        if (edgeEffect != null) {
            if ((Build.VERSION.SDK_INT >= 31 ? hVar.b(edgeEffect) : 0.0f) != 0.0f) {
                return true;
            }
        }
        EdgeEffect edgeEffect2 = zVar.f4050e;
        if (edgeEffect2 != null) {
            if ((Build.VERSION.SDK_INT >= 31 ? hVar.b(edgeEffect2) : 0.0f) != 0.0f) {
                return true;
            }
        }
        EdgeEffect edgeEffect3 = zVar.f4051f;
        if (edgeEffect3 != null) {
            if ((Build.VERSION.SDK_INT >= 31 ? hVar.b(edgeEffect3) : 0.0f) != 0.0f) {
                return true;
            }
        }
        EdgeEffect edgeEffect4 = zVar.f4052g;
        if (edgeEffect4 != null) {
            return (Build.VERSION.SDK_INT >= 31 ? hVar.b(edgeEffect4) : 0.0f) != 0.0f;
        }
        return false;
    }

    public final long c() {
        x0.c cVar = this.f3905d;
        long jW = cVar != null ? cVar.f6532a : p3.j.w(this.f3910i);
        return p3.j.b(x0.c.d(jW) / x0.f.d(this.f3910i), x0.c.e(jW) / x0.f.b(this.f3910i));
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0065  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:62:0x0135  */
    /* JADX WARN: Code duplicated, block: B:64:0x013a  */
    /* JADX WARN: Code duplicated, block: B:66:0x0142  */
    /* JADX WARN: Code duplicated, block: B:67:0x0146  */
    /* JADX WARN: Code duplicated, block: B:69:0x0149 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:71:0x014d  */
    @Override // n.x0
    public final long d(long j5, int i5, c0.g1 g1Var) {
        float fH;
        float fJ;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (x0.f.e(this.f3910i)) {
            g1Var.getClass();
            p.i1 i1Var = (p.i1) g1Var.f871f;
            return new x0.c(p.i1.a(i1Var, i1Var.f4491h, j5, i1Var.f4490g)).f6532a;
        }
        boolean z6 = this.f3909h;
        boolean z7 = true;
        z zVar = this.f3906e;
        if (!z6) {
            if (z.g(zVar.f4051f)) {
                i(0L);
            }
            if (z.g(zVar.f4052g)) {
                j(0L);
            }
            if (z.g(zVar.f4049d)) {
                k(0L);
            }
            if (z.g(zVar.f4050e)) {
                h(0L);
            }
            this.f3909h = true;
        }
        if (x0.c.e(j5) == 0.0f) {
            fH = 0.0f;
        } else if (z.g(zVar.f4049d)) {
            fH = k(j5);
            if (!z.g(zVar.f4049d)) {
                zVar.e().onRelease();
            }
        } else if (z.g(zVar.f4050e)) {
            fH = h(j5);
            if (!z.g(zVar.f4050e)) {
                zVar.b().onRelease();
            }
        } else {
            fH = 0.0f;
        }
        if (x0.c.d(j5) == 0.0f) {
            fJ = 0.0f;
        } else if (z.g(zVar.f4051f)) {
            fJ = i(j5);
            if (!z.g(zVar.f4051f)) {
                zVar.c().onRelease();
            }
        } else if (z.g(zVar.f4052g)) {
            fJ = j(j5);
            if (!z.g(zVar.f4052g)) {
                zVar.d().onRelease();
            }
        } else {
            fJ = 0.0f;
        }
        long jB = p3.j.b(fJ, fH);
        if (!x0.c.b(jB, 0L)) {
            g();
        }
        long jG = x0.c.g(j5, jB);
        g1Var.getClass();
        p.i1 i1Var2 = (p.i1) g1Var.f871f;
        long j6 = new x0.c(p.i1.a(i1Var2, i1Var2.f4491h, jG, i1Var2.f4490g)).f6532a;
        long jG2 = x0.c.g(jG, j6);
        if (i5 != 1) {
            z2 = false;
        } else {
            if (x0.c.d(jG2) > 0.5f) {
                i(jG2);
            } else {
                if (x0.c.d(jG2) < -0.5f) {
                    j(jG2);
                } else {
                    z4 = false;
                }
                if (x0.c.e(jG2) > 0.5f) {
                    k(jG2);
                } else {
                    if (x0.c.e(jG2) < -0.5f) {
                        h(jG2);
                    } else {
                        z5 = false;
                    }
                    if (!z4 || z5) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                z5 = true;
                if (z4) {
                }
                z2 = true;
            }
            z4 = true;
            if (x0.c.e(jG2) > 0.5f) {
                k(jG2);
            } else {
                if (x0.c.e(jG2) < -0.5f) {
                    h(jG2);
                } else {
                    z5 = false;
                }
                if (z4) {
                }
                z2 = true;
            }
            z5 = true;
            if (z4) {
            }
            z2 = true;
        }
        if (!z.f(zVar.f4051f) || x0.c.d(j5) >= 0.0f) {
            z3 = false;
        } else {
            EdgeEffect edgeEffectC = zVar.c();
            float fD = x0.c.d(j5);
            if (edgeEffectC instanceof g0) {
                g0 g0Var = (g0) edgeEffectC;
                float f5 = g0Var.f3914b + fD;
                g0Var.f3914b = f5;
                if (Math.abs(f5) > g0Var.f3913a) {
                    g0Var.onRelease();
                }
            } else {
                edgeEffectC.onRelease();
            }
            z3 = !z.f(zVar.f4051f);
        }
        if (z.f(zVar.f4052g) && x0.c.d(j5) > 0.0f) {
            EdgeEffect edgeEffectD = zVar.d();
            float fD2 = x0.c.d(j5);
            if (edgeEffectD instanceof g0) {
                g0 g0Var2 = (g0) edgeEffectD;
                float f6 = g0Var2.f3914b + fD2;
                g0Var2.f3914b = f6;
                if (Math.abs(f6) > g0Var2.f3913a) {
                    g0Var2.onRelease();
                }
            } else {
                edgeEffectD.onRelease();
            }
            z3 = z3 || !z.f(zVar.f4052g);
        }
        if (z.f(zVar.f4049d) && x0.c.e(j5) < 0.0f) {
            EdgeEffect edgeEffectE = zVar.e();
            float fE = x0.c.e(j5);
            if (edgeEffectE instanceof g0) {
                g0 g0Var3 = (g0) edgeEffectE;
                float f7 = g0Var3.f3914b + fE;
                g0Var3.f3914b = f7;
                if (Math.abs(f7) > g0Var3.f3913a) {
                    g0Var3.onRelease();
                }
            } else {
                edgeEffectE.onRelease();
            }
            z3 = z3 || !z.f(zVar.f4049d);
        }
        if (z.f(zVar.f4050e) && x0.c.e(j5) > 0.0f) {
            EdgeEffect edgeEffectB = zVar.b();
            float fE2 = x0.c.e(j5);
            if (edgeEffectB instanceof g0) {
                g0 g0Var4 = (g0) edgeEffectB;
                float f8 = g0Var4.f3914b + fE2;
                g0Var4.f3914b = f8;
                if (Math.abs(f8) > g0Var4.f3913a) {
                    g0Var4.onRelease();
                }
            } else {
                edgeEffectB.onRelease();
            }
            z3 = z3 || !z.f(zVar.f4050e);
        }
        if (!z3 && !z2) {
            z7 = false;
        }
        if (z7) {
            g();
        }
        return x0.c.h(jB, j6);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // n.x0
    public final Object e(long j5, p.h1 h1Var, t3.c cVar) {
        e eVar;
        float fB;
        float fC;
        long jD;
        g gVar;
        if (cVar instanceof e) {
            eVar = (e) cVar;
            int i5 = eVar.f3892h;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                eVar.f3892h = i5 - Integer.MIN_VALUE;
            } else {
                eVar = new e(this, (v3.c) cVar);
            }
        } else {
            eVar = new e(this, (v3.c) cVar);
        }
        Object objInvokeSuspend = eVar.f3890f;
        int i6 = eVar.f3892h;
        p3.n nVar = p3.n.f5026a;
        if (i6 == 0) {
            p3.j.O(objInvokeSuspend);
            boolean zE = x0.f.e(this.f3910i);
            u3.a aVar = u3.a.f5838d;
            if (zE) {
                eVar.f3892h = 1;
                h1Var.getClass();
                p.h1 h1Var2 = new p.h1(h1Var.f4473g, eVar);
                h1Var2.f4472f = j5;
                if (h1Var2.invokeSuspend(nVar) != aVar) {
                    return nVar;
                }
            } else {
                float fB2 = k2.o.b(j5);
                z zVar = this.f3906e;
                if (fB2 > 0.0f && z.g(zVar.f4051f)) {
                    EdgeEffect edgeEffectC = zVar.c();
                    int iH = d4.a.H(k2.o.b(j5));
                    if (Build.VERSION.SDK_INT >= 31 || edgeEffectC.isFinished()) {
                        edgeEffectC.onAbsorb(iH);
                    }
                    fB = k2.o.b(j5);
                } else if (k2.o.b(j5) >= 0.0f || !z.g(zVar.f4052g)) {
                    fB = 0.0f;
                } else {
                    EdgeEffect edgeEffectD = zVar.d();
                    int i7 = -d4.a.H(k2.o.b(j5));
                    if (Build.VERSION.SDK_INT >= 31 || edgeEffectD.isFinished()) {
                        edgeEffectD.onAbsorb(i7);
                    }
                    fB = k2.o.b(j5);
                }
                if (k2.o.c(j5) > 0.0f && z.g(zVar.f4049d)) {
                    EdgeEffect edgeEffectE = zVar.e();
                    int iH2 = d4.a.H(k2.o.c(j5));
                    if (Build.VERSION.SDK_INT >= 31 || edgeEffectE.isFinished()) {
                        edgeEffectE.onAbsorb(iH2);
                    }
                    fC = k2.o.c(j5);
                } else if (k2.o.c(j5) >= 0.0f || !z.g(zVar.f4050e)) {
                    fC = 0.0f;
                } else {
                    EdgeEffect edgeEffectB = zVar.b();
                    int i8 = -d4.a.H(k2.o.c(j5));
                    if (Build.VERSION.SDK_INT >= 31 || edgeEffectB.isFinished()) {
                        edgeEffectB.onAbsorb(i8);
                    }
                    fC = k2.o.c(j5);
                }
                long jF = h1.c.f(fB, fC);
                if (jF != 0) {
                    g();
                }
                jD = k2.o.d(j5, jF);
                eVar.f3888d = this;
                eVar.f3889e = jD;
                eVar.f3892h = 2;
                h1Var.getClass();
                p.h1 h1Var3 = new p.h1(h1Var.f4473g, eVar);
                h1Var3.f4472f = jD;
                objInvokeSuspend = h1Var3.invokeSuspend(nVar);
                if (objInvokeSuspend != aVar) {
                    gVar = this;
                }
            }
            return aVar;
        }
        if (i6 == 1) {
            p3.j.O(objInvokeSuspend);
            return nVar;
        }
        if (i6 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        jD = eVar.f3889e;
        gVar = eVar.f3888d;
        p3.j.O(objInvokeSuspend);
        long jD2 = k2.o.d(jD, ((k2.o) objInvokeSuspend).f3424a);
        gVar.f3909h = false;
        z zVar2 = gVar.f3906e;
        if (k2.o.b(jD2) > 0.0f) {
            EdgeEffect edgeEffectC2 = zVar2.c();
            int iH3 = d4.a.H(k2.o.b(jD2));
            if (Build.VERSION.SDK_INT >= 31 || edgeEffectC2.isFinished()) {
                edgeEffectC2.onAbsorb(iH3);
            }
        } else if (k2.o.b(jD2) < 0.0f) {
            EdgeEffect edgeEffectD2 = zVar2.d();
            int i9 = -d4.a.H(k2.o.b(jD2));
            if (Build.VERSION.SDK_INT >= 31 || edgeEffectD2.isFinished()) {
                edgeEffectD2.onAbsorb(i9);
            }
        }
        if (k2.o.c(jD2) > 0.0f) {
            EdgeEffect edgeEffectE2 = zVar2.e();
            int iH4 = d4.a.H(k2.o.c(jD2));
            if (Build.VERSION.SDK_INT >= 31 || edgeEffectE2.isFinished()) {
                edgeEffectE2.onAbsorb(iH4);
            }
        } else if (k2.o.c(jD2) < 0.0f) {
            EdgeEffect edgeEffectB2 = zVar2.b();
            int i10 = -d4.a.H(k2.o.c(jD2));
            if (Build.VERSION.SDK_INT >= 31 || edgeEffectB2.isFinished()) {
                edgeEffectB2.onAbsorb(i10);
            }
        }
        if (jD2 != 0) {
            gVar.g();
        }
        gVar.a();
        return nVar;
    }

    @Override // n.x0
    public final r0.p f() {
        return this.f3912k;
    }

    public final void g() {
        if (this.f3908g) {
            this.f3907f.setValue(p3.n.f5026a);
        }
    }

    public final float h(long j5) {
        float fD = x0.c.d(c());
        float fE = x0.c.e(j5) / x0.f.b(this.f3910i);
        EdgeEffect edgeEffectB = this.f3906e.b();
        float fC = -fE;
        float f5 = 1 - fD;
        int i5 = Build.VERSION.SDK_INT;
        h hVar = h.f3917a;
        if (i5 >= 31) {
            fC = hVar.c(edgeEffectB, fC, f5);
        } else {
            edgeEffectB.onPull(fC, f5);
        }
        return (i5 >= 31 ? hVar.b(edgeEffectB) : 0.0f) == 0.0f ? x0.f.b(this.f3910i) * (-fC) : x0.c.e(j5);
    }

    public final float i(long j5) {
        float fE = x0.c.e(c());
        float fD = x0.c.d(j5) / x0.f.d(this.f3910i);
        EdgeEffect edgeEffectC = this.f3906e.c();
        float f5 = 1 - fE;
        int i5 = Build.VERSION.SDK_INT;
        h hVar = h.f3917a;
        if (i5 >= 31) {
            fD = hVar.c(edgeEffectC, fD, f5);
        } else {
            edgeEffectC.onPull(fD, f5);
        }
        return (i5 >= 31 ? hVar.b(edgeEffectC) : 0.0f) == 0.0f ? x0.f.d(this.f3910i) * fD : x0.c.d(j5);
    }

    public final float j(long j5) {
        float fE = x0.c.e(c());
        float fD = x0.c.d(j5) / x0.f.d(this.f3910i);
        EdgeEffect edgeEffectD = this.f3906e.d();
        float fC = -fD;
        int i5 = Build.VERSION.SDK_INT;
        h hVar = h.f3917a;
        if (i5 >= 31) {
            fC = hVar.c(edgeEffectD, fC, fE);
        } else {
            edgeEffectD.onPull(fC, fE);
        }
        return (i5 >= 31 ? hVar.b(edgeEffectD) : 0.0f) == 0.0f ? x0.f.d(this.f3910i) * (-fC) : x0.c.d(j5);
    }

    public final float k(long j5) {
        float fD = x0.c.d(c());
        float fE = x0.c.e(j5) / x0.f.b(this.f3910i);
        EdgeEffect edgeEffectE = this.f3906e.e();
        int i5 = Build.VERSION.SDK_INT;
        h hVar = h.f3917a;
        if (i5 >= 31) {
            fE = hVar.c(edgeEffectE, fE, fD);
        } else {
            edgeEffectE.onPull(fE, fD);
        }
        return (i5 >= 31 ? hVar.b(edgeEffectE) : 0.0f) == 0.0f ? x0.f.b(this.f3910i) * fE : x0.c.e(j5);
    }

    public final void l(long j5) {
        boolean zA = x0.f.a(this.f3910i, 0L);
        boolean zA2 = x0.f.a(j5, this.f3910i);
        this.f3910i = j5;
        if (!zA2) {
            long jF = a.a.f(d4.a.H(x0.f.d(j5)), d4.a.H(x0.f.b(j5)));
            z zVar = this.f3906e;
            zVar.f4048c = jF;
            EdgeEffect edgeEffect = zVar.f4049d;
            if (edgeEffect != null) {
                edgeEffect.setSize((int) (jF >> 32), (int) (jF & 4294967295L));
            }
            EdgeEffect edgeEffect2 = zVar.f4050e;
            if (edgeEffect2 != null) {
                edgeEffect2.setSize((int) (jF >> 32), (int) (jF & 4294967295L));
            }
            EdgeEffect edgeEffect3 = zVar.f4051f;
            if (edgeEffect3 != null) {
                edgeEffect3.setSize((int) (jF & 4294967295L), (int) (jF >> 32));
            }
            EdgeEffect edgeEffect4 = zVar.f4052g;
            if (edgeEffect4 != null) {
                edgeEffect4.setSize((int) (jF & 4294967295L), (int) (jF >> 32));
            }
            EdgeEffect edgeEffect5 = zVar.f4053h;
            if (edgeEffect5 != null) {
                edgeEffect5.setSize((int) (jF >> 32), (int) (jF & 4294967295L));
            }
            EdgeEffect edgeEffect6 = zVar.f4054i;
            if (edgeEffect6 != null) {
                edgeEffect6.setSize((int) (jF >> 32), (int) (jF & 4294967295L));
            }
            EdgeEffect edgeEffect7 = zVar.f4055j;
            if (edgeEffect7 != null) {
                edgeEffect7.setSize((int) (jF & 4294967295L), (int) (jF >> 32));
            }
            EdgeEffect edgeEffect8 = zVar.f4056k;
            if (edgeEffect8 != null) {
                edgeEffect8.setSize((int) (jF & 4294967295L), (int) (jF >> 32));
            }
        }
        if (zA || zA2) {
            return;
        }
        g();
        a();
    }
}
