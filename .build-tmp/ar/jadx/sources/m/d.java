package m;

import c0.g1;
import java.util.concurrent.CancellationException;
import q1.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f3642a = new m(Float.POSITIVE_INFINITY);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f3643b = new n(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f3644c = new o(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p f3645d = new p(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f3646e = new m(Float.NEGATIVE_INFINITY);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f3647f = new n(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final o f3648g = new o(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p f3649h = new p(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    public static c a(float f5) {
        return new c(Float.valueOf(f5), b1.f3620a, Float.valueOf(0.01f), 8);
    }

    /* JADX WARN: Code duplicated, block: B:59:0x012b  */
    /* JADX WARN: Code duplicated, block: B:62:0x0138  */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    public static final Object b(l lVar, g gVar, long j5, a4.c cVar, v3.c cVar2) {
        k0 k0Var;
        b4.w wVar;
        l lVar2;
        l lVar3;
        b4.w wVar2;
        a4.c cVar3;
        j jVar;
        j jVar2;
        n0 n0Var;
        g gVar2 = gVar;
        if (cVar2 instanceof k0) {
            k0Var = (k0) cVar2;
            int i5 = k0Var.f3694i;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                k0Var.f3694i = i5 - Integer.MIN_VALUE;
            } else {
                k0Var = new k0(cVar2);
            }
        } else {
            k0Var = new k0(cVar2);
        }
        k0 k0Var2 = k0Var;
        Object obj = k0Var2.f3693h;
        int i6 = k0Var2.f3694i;
        u3.a aVar = u3.a.f5838d;
        if (i6 == 0) {
            p3.j.O(obj);
            Object objB = gVar2.b(0L);
            q qVarF = gVar2.f(0L);
            wVar = new b4.w();
            if (j5 == Long.MIN_VALUE) {
                try {
                    lVar2 = lVar;
                    try {
                        m0 m0Var = new m0(wVar, objB, gVar2, qVarF, lVar2, f(k0Var2.getContext()), cVar);
                        wVar2 = wVar;
                        try {
                            k0Var2.f3689d = lVar2;
                            k0Var2.f3690e = gVar2;
                            k0Var2.f3691f = cVar;
                            k0Var2.f3692g = wVar2;
                            k0Var2.f3694i = 1;
                            if (c(gVar2, m0Var, k0Var2) != aVar) {
                                lVar3 = lVar2;
                                cVar3 = cVar;
                                wVar = wVar2;
                            }
                            return aVar;
                        } catch (CancellationException e5) {
                            e = e5;
                            lVar3 = lVar2;
                            wVar = wVar2;
                            jVar = (j) wVar.f697d;
                            if (jVar != null) {
                                jVar.f3686i.setValue(Boolean.FALSE);
                            }
                            jVar2 = (j) wVar.f697d;
                            if (jVar2 != null) {
                                lVar3.f3700i = false;
                            }
                            throw e;
                        }
                    } catch (CancellationException e6) {
                        e = e6;
                        lVar3 = lVar2;
                        jVar = (j) wVar.f697d;
                        if (jVar != null) {
                            jVar.f3686i.setValue(Boolean.FALSE);
                        }
                        jVar2 = (j) wVar.f697d;
                        if (jVar2 != null) {
                            lVar3.f3700i = false;
                        }
                        throw e;
                    }
                } catch (CancellationException e7) {
                    e = e7;
                    lVar2 = lVar;
                }
            } else {
                wVar2 = wVar;
                try {
                    j jVar3 = new j(objB, gVar2.d(), qVarF, j5, gVar2.e(), j5, new l0(lVar, 1));
                    e(jVar3, j5, f(k0Var2.getContext()), gVar2, lVar, cVar);
                    wVar2.f697d = jVar3;
                    lVar3 = lVar;
                    gVar2 = gVar;
                    cVar3 = cVar;
                    wVar = wVar2;
                } catch (CancellationException e8) {
                    e = e8;
                    lVar3 = lVar;
                    wVar = wVar2;
                    jVar = (j) wVar.f697d;
                    if (jVar != null) {
                        jVar.f3686i.setValue(Boolean.FALSE);
                    }
                    jVar2 = (j) wVar.f697d;
                    if (jVar2 != null && jVar2.f3684g == lVar3.f3698g) {
                        lVar3.f3700i = false;
                    }
                    throw e;
                }
            }
        } else {
            if (i6 != 1 && i6 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            wVar = k0Var2.f3692g;
            cVar3 = k0Var2.f3691f;
            gVar2 = k0Var2.f3690e;
            lVar3 = k0Var2.f3689d;
            try {
                p3.j.O(obj);
            } catch (CancellationException e9) {
                e = e9;
                jVar = (j) wVar.f697d;
                if (jVar != null) {
                    jVar.f3686i.setValue(Boolean.FALSE);
                }
                jVar2 = (j) wVar.f697d;
                if (jVar2 != null) {
                    lVar3.f3700i = false;
                }
                throw e;
            }
        }
        do {
            Object obj2 = wVar.f697d;
            b4.l.b(obj2);
            if (!((Boolean) ((j) obj2).f3686i.getValue()).booleanValue()) {
                return p3.n.f5026a;
            }
            b4.w wVar3 = wVar;
            a4.c cVar4 = cVar3;
            g gVar3 = gVar2;
            l lVar4 = lVar3;
            try {
                n0Var = new n0(wVar3, f(k0Var2.getContext()), gVar3, lVar4, cVar4);
                wVar = wVar3;
                gVar2 = gVar3;
                lVar3 = lVar4;
                cVar3 = cVar4;
                k0Var2.f3689d = lVar3;
                k0Var2.f3690e = gVar2;
                k0Var2.f3691f = cVar3;
                k0Var2.f3692g = wVar;
                k0Var2.f3694i = 2;
            } catch (CancellationException e10) {
                e = e10;
                wVar = wVar3;
                lVar3 = lVar4;
                jVar = (j) wVar.f697d;
                if (jVar != null) {
                    jVar.f3686i.setValue(Boolean.FALSE);
                }
                jVar2 = (j) wVar.f697d;
                if (jVar2 != null) {
                    lVar3.f3700i = false;
                }
                throw e;
            }
        } while (c(gVar2, n0Var, k0Var2) != aVar);
        return aVar;
    }

    public static final Object c(g gVar, a4.c cVar, k0 k0Var) {
        if (!gVar.a()) {
            return f0.l.q(k0Var.getContext()).d(new g1(cVar), k0Var);
        }
        if (k0Var.getContext().j(s1.f5361d) == null) {
            return f0.l.q(k0Var.getContext()).d(cVar, k0Var);
        }
        throw new ClassCastException();
    }

    public static final q d(q qVar) {
        q qVarC = qVar.c();
        int iB = qVarC.b();
        for (int i5 = 0; i5 < iB; i5++) {
            qVarC.e(qVar.a(i5), i5);
        }
        return qVarC;
    }

    public static final void e(j jVar, long j5, float f5, g gVar, l lVar, a4.c cVar) {
        long jC = f5 == 0.0f ? gVar.c() : (long) ((j5 - jVar.f3680c) / f5);
        jVar.f3684g = j5;
        jVar.f3682e.setValue(gVar.b(jC));
        jVar.f3683f = gVar.f(jC);
        if (gVar.g(jC)) {
            jVar.f3685h = jVar.f3684g;
            jVar.f3686i.setValue(Boolean.FALSE);
        }
        i(jVar, lVar);
        cVar.c(jVar);
    }

    public static final float f(t3.h hVar) {
        r0.q qVar = (r0.q) hVar.j(r0.b.f5668s);
        float fP = qVar != null ? qVar.p() : 1.0f;
        if (fP >= 0.0f) {
            return fP;
        }
        throw new IllegalStateException("negative scale factor");
    }

    public static h0 g(int i5, Object obj) {
        if ((i5 & 4) != 0) {
            obj = null;
        }
        return new h0(1.0f, 1500.0f, obj);
    }

    public static z0 h(int i5, int i6, v vVar) {
        int i7 = (i6 & 2) != 0 ? 0 : 74;
        if ((i6 & 4) != 0) {
            vVar = w.f3797a;
        }
        return new z0(i5, i7, vVar);
    }

    public static final void i(j jVar, l lVar) {
        lVar.f3696e.setValue(jVar.f3682e.getValue());
        q qVar = lVar.f3697f;
        q qVar2 = jVar.f3683f;
        int iB = qVar.b();
        for (int i5 = 0; i5 < iB; i5++) {
            qVar.e(qVar2.a(i5), i5);
        }
        lVar.f3699h = jVar.f3685h;
        lVar.f3698g = jVar.f3684g;
        lVar.f3700i = ((Boolean) jVar.f3686i.getValue()).booleanValue();
    }
}
