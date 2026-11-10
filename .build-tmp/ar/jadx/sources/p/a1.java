package p;

import android.view.KeyEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a1 extends p1.m implements p1.a1, w0.l, h1.d, p1.l1, p1.j1, p1.k {
    public n.x0 A;
    public final a1.b B;
    public final q0 C;
    public final a2.h D;
    public final i1 E;
    public final w0 F;
    public final i G;
    public a H;
    public c0 I;
    public x0 J;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public l0 f4407s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public e f4408t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f4409u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public q.k f4410v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public k4.c f4411w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public q.b f4412x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4413y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public j1.d0 f4414z;

    public a1(n.x0 x0Var, l0 l0Var, b1 b1Var, q.k kVar, boolean z2, boolean z3) {
        e eVar = e.f4434g;
        this.f4407s = l0Var;
        this.f4408t = eVar;
        this.f4409u = z2;
        this.f4410v = kVar;
        this.A = x0Var;
        a1.b bVar = new a1.b(6);
        this.B = bVar;
        q0 q0Var = new q0();
        q0Var.f4570q = z2;
        y0(q0Var);
        this.C = q0Var;
        a2.h hVar = new a2.h(new a1.d(13, new a1.d(androidx.compose.foundation.gestures.a.f88c)));
        this.D = hVar;
        i1 i1Var = new i1(b1Var, this.A, hVar, l0Var, z3, bVar);
        this.E = i1Var;
        w0 w0Var = new w0(i1Var, z2);
        this.F = w0Var;
        i iVar = new i(l0Var, i1Var, z3);
        y0(iVar);
        this.G = iVar;
        y0(new i1.e(w0Var, bVar));
        y0(new w0.r());
        s.g gVar = new s.g();
        gVar.f5758q = iVar;
        y0(gVar);
        c0.g1 g1Var = new c0.g1(15, this);
        n.f0 f0Var = new n.f0();
        f0Var.f3903q = g1Var;
        y0(f0Var);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object B0(a1 a1Var, v3.c cVar) throws Throwable {
        d0 d0Var;
        if (cVar instanceof d0) {
            d0Var = (d0) cVar;
            int i5 = d0Var.f4428g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                d0Var.f4428g = i5 - Integer.MIN_VALUE;
            } else {
                d0Var = new d0(a1Var, cVar);
            }
        } else {
            d0Var = new d0(a1Var, cVar);
        }
        Object obj = d0Var.f4426e;
        int i6 = d0Var.f4428g;
        if (i6 == 0) {
            p3.j.O(obj);
            q.b bVar = a1Var.f4412x;
            if (bVar != null) {
                q.k kVar = a1Var.f4410v;
                if (kVar != null) {
                    q.a aVar = new q.a(bVar);
                    d0Var.f4425d = a1Var;
                    d0Var.f4428g = 1;
                    Object objB = kVar.b(aVar, d0Var);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objB == aVar2) {
                        return aVar2;
                    }
                }
            }
            a1Var.F0(0L);
            return p3.n.f5026a;
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        a1Var = d0Var.f4425d;
        p3.j.O(obj);
        a1Var.f4412x = null;
        a1Var.F0(0L);
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object C0(a1 a1Var, q qVar, v3.c cVar) {
        e0 e0Var;
        q.k kVar;
        q.b bVar;
        a1 a1Var2;
        q.b bVar2;
        if (cVar instanceof e0) {
            e0Var = (e0) cVar;
            int i5 = e0Var.f4441i;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                e0Var.f4441i = i5 - Integer.MIN_VALUE;
            } else {
                e0Var = new e0(a1Var, cVar);
            }
        } else {
            e0Var = new e0(a1Var, cVar);
        }
        Object obj = e0Var.f4439g;
        int i6 = e0Var.f4441i;
        u3.a aVar = u3.a.f5838d;
        if (i6 == 0) {
            p3.j.O(obj);
            q.b bVar3 = a1Var.f4412x;
            if (bVar3 != null && (kVar = a1Var.f4410v) != null) {
                q.a aVar2 = new q.a(bVar3);
                e0Var.f4436d = a1Var;
                e0Var.f4437e = qVar;
                e0Var.f4441i = 1;
                if (kVar.b(aVar2, e0Var) != aVar) {
                }
                return aVar;
            }
            a1Var.f4412x = bVar;
            long j5 = qVar.f4568a;
            return p3.n.f5026a;
        }
        if (i6 == 1) {
            qVar = e0Var.f4437e;
            a1Var = e0Var.f4436d;
            p3.j.O(obj);
        } else {
            if (i6 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            bVar2 = e0Var.f4438f;
            qVar = e0Var.f4437e;
            a1Var2 = e0Var.f4436d;
            p3.j.O(obj);
        }
        bVar = bVar2;
        a1Var = a1Var2;
        a1Var.f4412x = bVar;
        long j6 = qVar.f4568a;
        return p3.n.f5026a;
        bVar = new q.b();
        q.k kVar2 = a1Var.f4410v;
        if (kVar2 != null) {
            e0Var.f4436d = a1Var;
            e0Var.f4437e = qVar;
            e0Var.f4438f = bVar;
            e0Var.f4441i = 2;
            if (kVar2.b(bVar, e0Var) != aVar) {
                a1Var2 = a1Var;
                bVar2 = bVar;
                bVar = bVar2;
                a1Var = a1Var2;
            }
            return aVar;
        }
        a1Var.f4412x = bVar;
        long j7 = qVar.f4568a;
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object D0(a1 a1Var, r rVar, v3.c cVar) throws Throwable {
        f0 f0Var;
        if (cVar instanceof f0) {
            f0Var = (f0) cVar;
            int i5 = f0Var.f4449h;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                f0Var.f4449h = i5 - Integer.MIN_VALUE;
            } else {
                f0Var = new f0(a1Var, cVar);
            }
        } else {
            f0Var = new f0(a1Var, cVar);
        }
        Object obj = f0Var.f4447f;
        int i6 = f0Var.f4449h;
        if (i6 == 0) {
            p3.j.O(obj);
            q.b bVar = a1Var.f4412x;
            if (bVar != null) {
                q.k kVar = a1Var.f4410v;
                if (kVar != null) {
                    q.c cVar2 = new q.c(bVar);
                    f0Var.f4445d = a1Var;
                    f0Var.f4446e = rVar;
                    f0Var.f4449h = 1;
                    Object objB = kVar.b(cVar2, f0Var);
                    u3.a aVar = u3.a.f5838d;
                    if (objB == aVar) {
                        return aVar;
                    }
                }
            }
            a1Var.F0(rVar.f4581a);
            return p3.n.f5026a;
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        rVar = f0Var.f4446e;
        a1Var = f0Var.f4445d;
        p3.j.O(obj);
        a1Var.f4412x = null;
        a1Var.F0(rVar.f4581a);
        return p3.n.f5026a;
    }

    public final void E0() {
        q.b bVar = this.f4412x;
        if (bVar != null) {
            q.k kVar = this.f4410v;
            if (kVar != null) {
                kVar.c(new q.a(bVar));
            }
            this.f4412x = null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a4.a, b4.m] */
    public final void F0(long j5) {
        i4.v vVar = (i4.v) ((b4.m) this.B.f24e).a();
        if (vVar == null) {
            throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
        }
        i4.y.n(vVar, null, new x0(this, j5, null, 0), 3);
    }

    @Override // p1.a1
    public final void J() {
        p1.f.p(this, new b0(this, 2));
    }

    @Override // w0.l
    public final void K(w0.i iVar) {
        iVar.b(false);
    }

    @Override // h1.d
    public final boolean N(KeyEvent keyEvent) {
        long jB;
        if (!this.f4409u) {
            return false;
        }
        if ((!h1.a.a(h1.c.u(keyEvent), h1.a.f2600l) && !h1.a.a(h1.c.e(keyEvent.getKeyCode()), h1.a.f2599k)) || h1.c.y(keyEvent) != 2 || keyEvent.isCtrlPressed()) {
            return false;
        }
        l0 l0Var = this.E.f4487d;
        l0 l0Var2 = l0.f4525d;
        i iVar = this.G;
        if (l0Var == l0Var2) {
            int i5 = (int) (iVar.f4481x & 4294967295L);
            jB = p3.j.b(0.0f, h1.a.a(h1.c.e(keyEvent.getKeyCode()), h1.a.f2599k) ? i5 : -i5);
        } else {
            int i6 = (int) (iVar.f4481x >> 32);
            jB = p3.j.b(h1.a.a(h1.c.e(keyEvent.getKeyCode()), h1.a.f2599k) ? i6 : -i6, 0.0f);
        }
        i4.y.n(n0(), null, new x0(this, jB, null, 1), 3);
        return true;
    }

    @Override // p1.j1
    public final void W() {
        j1.d0 d0Var = this.f4414z;
        if (d0Var != null) {
            d0Var.W();
        }
    }

    @Override // p1.l1
    public final void Z(w1.i iVar) {
        if (this.f4409u && (this.I == null || this.J == null)) {
            this.I = new c0(this, 1);
            this.J = new x0(this, null);
        }
        c0 c0Var = this.I;
        if (c0Var != null) {
            f4.d[] dVarArr = w1.s.f6363a;
            iVar.c(w1.h.f6285d, new w1.a(null, c0Var));
        }
        x0 x0Var = this.J;
        if (x0Var != null) {
            f4.d[] dVarArr2 = w1.s.f6363a;
            iVar.c(w1.h.f6286e, x0Var);
        }
    }

    @Override // h1.d
    public final boolean i(KeyEvent keyEvent) {
        return false;
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    @Override // r0.o
    public final void r0() {
        p1.f.p(this, new b0(this, 2));
        this.H = a.f4397a;
    }

    @Override // r0.o
    public final void s0() {
        this.f4413y = false;
        E0();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.Object, java.util.List] */
    @Override // p1.j1
    public final void y(j1.i iVar, j1.j jVar, long j5) {
        t3.c cVar;
        long j6;
        ?? r1 = iVar.f3257a;
        int size = r1.size();
        int i5 = 0;
        while (true) {
            cVar = null;
            if (i5 >= size) {
                break;
            }
            if (((Boolean) this.f4408t.c((j1.u) r1.get(i5))).booleanValue()) {
                if (this.f4409u && this.f4414z == null) {
                    b0.t tVar = new b0.t(this, cVar, 11);
                    j1.i iVar2 = j1.z.f3303a;
                    j1.d0 d0Var = new j1.d0(null, null, tVar);
                    y0(d0Var);
                    this.f4414z = d0Var;
                }
                j1.d0 d0Var2 = this.f4414z;
                if (d0Var2 == null) {
                    break;
                }
                d0Var2.y(iVar, jVar, j5);
                break;
            }
            i5++;
        }
        if (jVar == j1.j.f3261e && iVar.f3259c == 6) {
            ?? r11 = iVar.f3257a;
            int size2 = r11.size();
            for (int i6 = 0; i6 < size2; i6++) {
                if (((j1.u) r11.get(i6)).b()) {
                    return;
                }
            }
            b4.l.b(this.H);
            k2.b bVar = p1.f.s(this).f4796t;
            x0.c cVar2 = new x0.c(0L);
            int size3 = r11.size();
            int i7 = 0;
            while (true) {
                j6 = cVar2.f6532a;
                if (i7 >= size3) {
                    break;
                }
                cVar2 = new x0.c(x0.c.h(j6, ((j1.u) r11.get(i7)).f3283j));
                i7++;
            }
            i4.y.n(n0(), null, new x0(this, x0.c.i(-bVar.v(64), j6), cVar, 2), 3);
            int size4 = r11.size();
            for (int i8 = 0; i8 < size4; i8++) {
                ((j1.u) r11.get(i8)).a();
            }
        }
    }
}
