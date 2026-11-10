package v;

import f0.b2;
import f0.s1;
import q1.k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t0 f6006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b2 f6007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k2 f6008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a2.h f6009d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e2.d0 f6010e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f6011f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final s1 f6012g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public n1.p f6013h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final s1 f6014i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public y1.f f6015j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final s1 f6016k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final s1 f6017l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final s1 f6018m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final s1 f6019n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final s1 f6020o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6021p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final s1 f6022q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final a1.b f6023r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a4.c f6024s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final q f6025t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final q f6026u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final y0.h f6027v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f6028w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final s1 f6029x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final s1 f6030y;

    public o0(t0 t0Var, b2 b2Var, k2 k2Var) {
        this.f6006a = t0Var;
        this.f6007b = b2Var;
        this.f6008c = k2Var;
        a2.h hVar = new a2.h(5);
        y1.f fVar = y1.g.f6767a;
        long j5 = y1.g0.f6768b;
        e2.x xVar = new e2.x(fVar, j5, (y1.g0) null);
        hVar.f49e = xVar;
        hVar.f50f = new e2.j(fVar, xVar.f2063b);
        this.f6009d = hVar;
        Boolean bool = Boolean.FALSE;
        this.f6011f = f0.l.s(bool);
        this.f6012g = f0.l.s(new k2.e(0));
        this.f6014i = f0.l.s(null);
        this.f6016k = f0.l.s(f0.f5917d);
        this.f6017l = f0.l.s(bool);
        this.f6018m = f0.l.s(bool);
        this.f6019n = f0.l.s(bool);
        this.f6020o = f0.l.s(bool);
        this.f6021p = true;
        this.f6022q = f0.l.s(Boolean.TRUE);
        a1.b bVar = new a1.b();
        bVar.f23d = k2Var;
        this.f6023r = bVar;
        this.f6024s = e.f5886i;
        this.f6025t = new q(this, 5);
        this.f6026u = new q(this, 4);
        this.f6027v = y0.l0.g();
        this.f6028w = y0.u.f6694g;
        this.f6029x = f0.l.s(new y1.g0(j5));
        this.f6030y = f0.l.s(new y1.g0(j5));
    }

    public final f0 a() {
        return (f0) this.f6016k.getValue();
    }

    public final boolean b() {
        return ((Boolean) this.f6011f.getValue()).booleanValue();
    }

    public final n1.p c() {
        n1.p pVar = this.f6013h;
        if (pVar == null || !pVar.N()) {
            return null;
        }
        return pVar;
    }

    public final e1 d() {
        return (e1) this.f6014i.getValue();
    }

    public final void e(long j5) {
        this.f6030y.setValue(new y1.g0(j5));
    }

    public final void f(long j5) {
        this.f6029x.setValue(new y1.g0(j5));
    }
}
