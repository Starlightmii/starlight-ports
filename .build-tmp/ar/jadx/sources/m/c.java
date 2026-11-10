package m;

import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1 f3629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3630b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l f3631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s1 f3632d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f3633e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e0 f3634f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q f3635g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final q f3636h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q f3637i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final q f3638j;

    public c(Object obj, a1 a1Var, Object obj2) {
        this.f3629a = a1Var;
        this.f3630b = obj2;
        l lVar = new l(a1Var, obj, null, 60);
        this.f3631c = lVar;
        this.f3632d = f0.l.s(Boolean.FALSE);
        this.f3633e = f0.l.s(obj);
        this.f3634f = new e0();
        new h0(obj2);
        q qVar = lVar.f3697f;
        boolean z2 = qVar instanceof m;
        q qVar2 = z2 ? d.f3646e : qVar instanceof n ? d.f3647f : qVar instanceof o ? d.f3648g : d.f3649h;
        this.f3635g = qVar2;
        q qVar3 = z2 ? d.f3642a : qVar instanceof n ? d.f3643b : qVar instanceof o ? d.f3644c : d.f3645d;
        this.f3636h = qVar3;
        this.f3637i = qVar2;
        this.f3638j = qVar3;
    }

    public /* synthetic */ c(Object obj, a1 a1Var, Object obj2, int i5) {
        this(obj, a1Var, (i5 & 4) != 0 ? null : obj2);
    }

    public static final Object a(c cVar, Object obj) {
        a1 a1Var = cVar.f3629a;
        q qVar = cVar.f3638j;
        q qVar2 = cVar.f3637i;
        if (!b4.l.a(qVar2, cVar.f3635g) || !b4.l.a(qVar, cVar.f3636h)) {
            q qVar3 = (q) a1Var.f3614a.c(obj);
            int iB = qVar3.b();
            boolean z2 = false;
            for (int i5 = 0; i5 < iB; i5++) {
                if (qVar3.a(i5) < qVar2.a(i5) || qVar3.a(i5) > qVar.a(i5)) {
                    qVar3.e(d4.a.l(qVar3.a(i5), qVar2.a(i5), qVar.a(i5)), i5);
                    z2 = true;
                }
            }
            if (z2) {
                return a1Var.f3615b.c(qVar3);
            }
        }
        return obj;
    }

    public static Object b(c cVar, Object obj, k kVar, t3.c cVar2) {
        Object objC = cVar.f3629a.f3615b.c(cVar.f3631c.f3697f);
        Object objC2 = cVar.c();
        a1 a1Var = cVar.f3629a;
        o0 o0Var = new o0(kVar, a1Var, objC2, obj, (q) a1Var.f3614a.c(objC));
        long j5 = cVar.f3631c.f3698g;
        e0 e0Var = cVar.f3634f;
        a aVar = new a(cVar, objC, o0Var, j5, null);
        e0Var.getClass();
        return i4.y.c(new e(e0Var, aVar, null), cVar2);
    }

    public final Object c() {
        return this.f3631c.f3696e.getValue();
    }

    public final Object d(Object obj, t3.c cVar) {
        b bVar = new b(this, obj, null);
        e0 e0Var = this.f3634f;
        e0Var.getClass();
        Object objC = i4.y.c(new e(e0Var, bVar, null), cVar);
        return objC == u3.a.f5838d ? objC : p3.n.f5026a;
    }
}
