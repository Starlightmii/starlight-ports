package b0;

import f0.s1;
import i4.a1;
import i4.o0;
import i4.r0;
import i4.x0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x0.c f422a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f424c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Float f425d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public x0.c f426e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m.c f427f = m.d.a(0.0f);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final m.c f428g = m.d.a(0.0f);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final m.c f429h = m.d.a(0.0f);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i4.m f430i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final s1 f431j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final s1 f432k;

    public m(x0.c cVar, float f5, boolean z2) {
        this.f422a = cVar;
        this.f423b = f5;
        this.f424c = z2;
        i4.m mVar = new i4.m(true);
        mVar.L(null);
        this.f430i = mVar;
        Boolean bool = Boolean.FALSE;
        this.f431j = f0.l.s(bool);
        this.f432k = f0.l.s(bool);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ca A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object a(v3.c cVar) throws Throwable {
        j jVar;
        m mVar;
        Object objR;
        Object objC;
        if (cVar instanceof j) {
            jVar = (j) cVar;
            int i5 = jVar.f415g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                jVar.f415g = i5 - Integer.MIN_VALUE;
            } else {
                jVar = new j(this, cVar);
            }
        } else {
            jVar = new j(this, cVar);
        }
        Object obj = jVar.f413e;
        int i6 = jVar.f415g;
        u3.a aVar = u3.a.f5838d;
        p3.n nVar = p3.n.f5026a;
        t3.c cVar2 = null;
        if (i6 == 0) {
            p3.j.O(obj);
            jVar.f412d = this;
            jVar.f415g = 1;
            Object objC2 = i4.y.c(new l(this, cVar2, 0), jVar);
            if (objC2 != aVar) {
                objC2 = nVar;
            }
            if (objC2 != aVar) {
                mVar = this;
            }
            return aVar;
        }
        if (i6 == 1) {
            mVar = jVar.f412d;
            p3.j.O(obj);
        } else {
            if (i6 != 2) {
                if (i6 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                p3.j.O(obj);
                return nVar;
            }
            mVar = jVar.f412d;
            p3.j.O(obj);
        }
        jVar.f412d = null;
        jVar.f415g = 3;
        mVar.getClass();
        objC = i4.y.c(new l(mVar, cVar2, 1), jVar);
        if (objC != aVar) {
            objC = nVar;
        }
        if (objC != aVar) {
            return aVar;
        }
        return nVar;
        mVar.f431j.setValue(Boolean.TRUE);
        i4.m mVar2 = mVar.f430i;
        jVar.f412d = mVar;
        jVar.f415g = 2;
        mVar2.getClass();
        while (true) {
            Object obj2 = a1.f2715d.get(mVar2);
            if (!(obj2 instanceof o0)) {
                if (!(obj2 instanceof i4.o)) {
                    objR = i4.y.r(obj2);
                    break;
                }
                throw ((i4.o) obj2).f2763a;
            }
            if (mVar2.W(obj2) >= 0) {
                x0 x0Var = new x0(p3.j.B(jVar), mVar2);
                x0Var.p();
                x0Var.t(new i4.e(1, i4.y.k(mVar2, true, new r0(1, x0Var))));
                objR = x0Var.o();
                break;
            }
        }
        if (objR != aVar) {
            jVar.f412d = null;
            jVar.f415g = 3;
            mVar.getClass();
            objC = i4.y.c(new l(mVar, cVar2, 1), jVar);
            if (objC != aVar) {
                objC = nVar;
            }
            if (objC != aVar) {
                return nVar;
            }
        }
        return aVar;
    }
}
