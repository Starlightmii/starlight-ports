package n;

import android.view.KeyEvent;
import java.util.Iterator;
import java.util.LinkedHashMap;
import p1.l1;
import p1.o1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class t extends p1.m implements p1.j1, h1.d, w0.c, l1, o1 {
    public static final u0 J = new u0(2);
    public j1.d0 A;
    public p1.l B;
    public q.m C;
    public q.g D;
    public q.k G;
    public boolean H;
    public final u0 I;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public q.k f4017s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public n0 f4018t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f4019u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public w1.f f4020v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4021w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public a4.a f4022x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final c0 f4024z;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final a0 f4023y = new a0();
    public final LinkedHashMap E = new LinkedHashMap();
    public long F = 0;

    public t(q.k kVar, n0 n0Var, boolean z2, String str, w1.f fVar, a4.a aVar) {
        this.f4017s = kVar;
        this.f4018t = n0Var;
        this.f4019u = str;
        this.f4020v = fVar;
        this.f4021w = z2;
        this.f4022x = aVar;
        this.f4024z = new c0(kVar);
        q.k kVar2 = this.f4017s;
        this.G = kVar2;
        this.H = kVar2 == null && this.f4018t != null;
        this.I = J;
    }

    public void B0(w1.i iVar) {
    }

    public final void C0() {
        q.k kVar = this.f4017s;
        LinkedHashMap linkedHashMap = this.E;
        if (kVar != null) {
            q.m mVar = this.C;
            if (mVar != null) {
                kVar.c(new q.l(mVar));
            }
            q.g gVar = this.D;
            if (gVar != null) {
                kVar.c(new q.h(gVar));
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                kVar.c(new q.l((q.m) it.next()));
            }
        }
        this.C = null;
        this.D = null;
        linkedHashMap.clear();
    }

    public final void D0() {
        n0 n0Var;
        if (this.B == null && (n0Var = this.f4018t) != null) {
            if (this.f4017s == null) {
                this.f4017s = new q.k();
            }
            this.f4024z.B0(this.f4017s);
            q.k kVar = this.f4017s;
            b4.l.b(kVar);
            p1.l lVarB = n0Var.b(kVar);
            y0(lVarB);
            this.B = lVarB;
        }
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0079  */
    public final void E0(q.k kVar, n0 n0Var, boolean z2, String str, w1.f fVar, a4.a aVar) {
        boolean z3;
        boolean z4;
        p1.l lVar;
        boolean z5 = false;
        if (b4.l.a(this.G, kVar)) {
            z3 = false;
        } else {
            C0();
            this.G = kVar;
            this.f4017s = kVar;
            z3 = true;
        }
        if (!b4.l.a(this.f4018t, n0Var)) {
            this.f4018t = n0Var;
            z3 = true;
        }
        boolean z6 = this.f4021w;
        c0 c0Var = this.f4024z;
        if (z6 != z2) {
            p1.l lVar2 = this.f4023y;
            if (z2) {
                y0(lVar2);
                y0(c0Var);
            } else {
                z0(lVar2);
                z0(c0Var);
                C0();
            }
            p1.f.n(this);
            this.f4021w = z2;
        }
        if (!b4.l.a(this.f4019u, str)) {
            this.f4019u = str;
            p1.f.n(this);
        }
        if (!b4.l.a(this.f4020v, fVar)) {
            this.f4020v = fVar;
            p1.f.n(this);
        }
        this.f4022x = aVar;
        boolean z7 = this.H;
        q.k kVar2 = this.G;
        if (z7 != (kVar2 == null && this.f4018t != null)) {
            if (kVar2 == null && this.f4018t != null) {
                z5 = true;
            }
            this.H = z5;
            z4 = (z5 || this.B != null) ? z3 : true;
        }
        if (z4 && ((lVar = this.B) != null || !this.H)) {
            if (lVar != null) {
                z0(lVar);
            }
            this.B = null;
            D0();
        }
        c0Var.B0(this.f4017s);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0066  */
    /* JADX WARN: Code duplicated, block: B:19:0x006a  */
    /* JADX WARN: Code duplicated, block: B:21:0x0072  */
    @Override // h1.d
    public final boolean N(KeyEvent keyEvent) {
        int iU;
        q.m mVar;
        int iU2;
        D0();
        boolean z2 = this.f4021w;
        int i5 = 0;
        int i6 = 1;
        t3.c cVar = null;
        LinkedHashMap linkedHashMap = this.E;
        if (z2) {
            int i7 = u.f4028b;
            if (h1.c.y(keyEvent) == 2 && ((iU2 = (int) (h1.c.u(keyEvent) >> 32)) == 23 || iU2 == 66 || iU2 == 160)) {
                if (!linkedHashMap.containsKey(new h1.a(h1.c.e(keyEvent.getKeyCode())))) {
                    q.m mVar2 = new q.m(this.F);
                    linkedHashMap.put(new h1.a(h1.c.e(keyEvent.getKeyCode())), mVar2);
                    if (this.f4017s != null) {
                        i4.y.n(n0(), null, new c(this, mVar2, cVar, i5), 3);
                    }
                    return true;
                }
            } else if (this.f4021w) {
                int i8 = u.f4028b;
                if (h1.c.y(keyEvent) == 1) {
                    iU = (int) (h1.c.u(keyEvent) >> 32);
                    if (iU == 23 && iU != 66 && iU != 160) {
                        return false;
                    }
                    mVar = (q.m) linkedHashMap.remove(new h1.a(h1.c.e(keyEvent.getKeyCode())));
                    if (mVar != null && this.f4017s != null) {
                        i4.y.n(n0(), null, new c(this, mVar, cVar, i6), 3);
                    }
                    this.f4022x.a();
                    return true;
                }
            }
        } else if (this.f4021w) {
            int i9 = u.f4028b;
            if (h1.c.y(keyEvent) == 1) {
                iU = (int) (h1.c.u(keyEvent) >> 32);
                if (iU == 23) {
                }
                mVar = (q.m) linkedHashMap.remove(new h1.a(h1.c.e(keyEvent.getKeyCode())));
                if (mVar != null) {
                    i4.y.n(n0(), null, new c(this, mVar, cVar, i6), 3);
                }
                this.f4022x.a();
                return true;
            }
        }
        return false;
    }

    @Override // p1.j1
    public final void W() {
        q.g gVar;
        q.k kVar = this.f4017s;
        if (kVar != null && (gVar = this.D) != null) {
            kVar.c(new q.h(gVar));
        }
        this.D = null;
        j1.d0 d0Var = this.A;
        if (d0Var != null) {
            d0Var.W();
        }
    }

    @Override // p1.l1
    public final void Z(w1.i iVar) {
        w1.f fVar = this.f4020v;
        if (fVar != null) {
            w1.s.d(iVar, fVar.f6279a);
        }
        String str = this.f4019u;
        b0.a aVar = new b0.a(11, this);
        f4.d[] dVarArr = w1.s.f6363a;
        iVar.c(w1.h.f6283b, new w1.a(str, aVar));
        if (this.f4021w) {
            this.f4024z.Z(iVar);
        } else {
            iVar.c(w1.q.f6344i, p3.n.f5026a);
        }
        B0(iVar);
    }

    @Override // p1.l1
    public final boolean c0() {
        return true;
    }

    @Override // h1.d
    public final boolean i(KeyEvent keyEvent) {
        return false;
    }

    @Override // p1.o1
    public final Object k() {
        return this.I;
    }

    @Override // w0.c
    public final void k0(w0.q qVar) {
        if (qVar.a()) {
            D0();
        }
        if (this.f4021w) {
            this.f4024z.k0(qVar);
        }
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    @Override // r0.o
    public final void r0() {
        if (!this.H) {
            D0();
        }
        if (this.f4021w) {
            y0(this.f4023y);
            y0(this.f4024z);
        }
    }

    @Override // r0.o
    public final void s0() {
        C0();
        if (this.G == null) {
            this.f4017s = null;
        }
        p1.l lVar = this.B;
        if (lVar != null) {
            z0(lVar);
        }
        this.B = null;
    }

    @Override // p1.j1
    public final void y(j1.i iVar, j1.j jVar, long j5) {
        long j6 = ((j5 >> 33) << 32) | (((j5 << 32) >> 33) & 4294967295L);
        this.F = p3.j.b((int) (j6 >> 32), (int) (j6 & 4294967295L));
        D0();
        t3.c cVar = null;
        if (this.f4021w && jVar == j1.j.f3261e) {
            int i5 = iVar.f3259c;
            if (i5 == 4) {
                i4.y.n(n0(), null, new d(this, cVar, 0), 3);
            } else if (i5 == 5) {
                i4.y.n(n0(), null, new d(this, cVar, 1), 3);
            }
        }
        if (this.A == null) {
            b0.t tVar = new b0.t(this, cVar, 7);
            j1.i iVar2 = j1.z.f3303a;
            j1.d0 d0Var = new j1.d0(null, null, tVar);
            y0(d0Var);
            this.A = d0Var;
        }
        j1.d0 d0Var2 = this.A;
        if (d0Var2 != null) {
            d0Var2.y(iVar, jVar, j5);
        }
    }
}
