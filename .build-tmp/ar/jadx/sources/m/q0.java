package m;

import f0.g3;
import f0.o1;
import f0.q1;
import f0.s1;
import f0.z2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q0 implements g3 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a1 f3736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f3737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f3738f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final s1 f3739g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final s1 f3740h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o1 f3741i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3742j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final s1 f3743k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public q f3744l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final q1 f3745m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3746n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h0 f3747o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s0 f3748p;

    /* JADX WARN: Type inference failed for: r9v9, types: [java.lang.Object, java.util.Map] */
    public q0(s0 s0Var, Object obj, q qVar, a1 a1Var) {
        this.f3748p = s0Var;
        this.f3736d = a1Var;
        s1 s1VarS = f0.l.s(obj);
        this.f3737e = s1VarS;
        Object objC = null;
        s1 s1VarS2 = f0.l.s(d.g(7, null));
        this.f3738f = s1VarS2;
        this.f3739g = f0.l.s(new o0((x) s1VarS2.getValue(), a1Var, obj, s1VarS.getValue(), qVar));
        this.f3740h = f0.l.s(Boolean.TRUE);
        this.f3741i = new o1(-1.0f);
        this.f3743k = f0.l.s(obj);
        this.f3744l = qVar;
        this.f3745m = new q1(a().c());
        Float f5 = (Float) f1.f3662a.get(a1Var);
        if (f5 != null) {
            float fFloatValue = f5.floatValue();
            q qVar2 = (q) a1Var.f3614a.c(obj);
            int iB = qVar2.b();
            for (int i5 = 0; i5 < iB; i5++) {
                qVar2.e(fFloatValue, i5);
            }
            objC = this.f3736d.f3615b.c(qVar2);
        }
        this.f3747o = d.g(3, objC);
    }

    public final o0 a() {
        return (o0) this.f3739g.getValue();
    }

    public final void b(Object obj, boolean z2) {
        s0 s0Var = this.f3748p;
        s1 s1Var = s0Var.f3756f;
        s1 s1Var2 = this.f3737e;
        boolean zA = b4.l.a(null, s1Var2.getValue());
        q1 q1Var = this.f3745m;
        s1 s1Var3 = this.f3739g;
        x xVar = this.f3747o;
        if (zA) {
            s1Var3.setValue(new o0(xVar, this.f3736d, obj, obj, this.f3744l.c()));
            this.f3742j = true;
            q1Var.g(a().c());
            return;
        }
        s1 s1Var4 = this.f3738f;
        if (!z2 || this.f3746n || (((x) s1Var4.getValue()) instanceof h0)) {
            xVar = (x) s1Var4.getValue();
        }
        s1Var3.setValue(new o0(s0Var.d() <= 0 ? xVar : new i0(xVar, s0Var.d()), this.f3736d, obj, s1Var2.getValue(), this.f3744l));
        q1Var.g(a().c());
        this.f3742j = false;
        s1Var.setValue(Boolean.TRUE);
        if (((Boolean) s0Var.f3759i.getValue()).booleanValue()) {
            p0.q qVar = s0Var.f3757g;
            int size = qVar.size();
            long jMax = 0;
            for (int i5 = 0; i5 < size; i5++) {
                q0 q0Var = (q0) qVar.get(i5);
                q1 q1Var2 = q0Var.f3745m;
                s1 s1Var5 = q0Var.f3743k;
                jMax = Math.max(jMax, ((z2) p0.m.t(q1Var2.f2294e, q1Var2)).f2442c);
                if (q0Var.f3741i.g() == -1.0f) {
                    q0Var.f3746n = true;
                    if (b4.l.a(q0Var.a().f3723c, q0Var.a().f3724d)) {
                        s1Var5.setValue(q0Var.a().f3723c);
                    } else {
                        s1Var5.setValue(q0Var.a().b(0L));
                        q0Var.f3744l = q0Var.a().f(0L);
                    }
                }
            }
            s1Var.setValue(Boolean.FALSE);
        }
    }

    @Override // f0.g3
    public final Object getValue() {
        return this.f3743k.getValue();
    }

    public final String toString() {
        return "current value: " + this.f3743k.getValue() + ", target: " + this.f3737e.getValue() + ", spec: " + ((x) this.f3738f.getValue());
    }
}
