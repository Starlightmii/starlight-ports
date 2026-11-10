package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.h3 f5110a = new f0.h3(n0.H);

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final void a(x.q qVar, l4.n nVar, v3.c cVar) {
        y1 y1Var;
        if (cVar instanceof y1) {
            y1Var = (y1) cVar;
            int i5 = y1Var.f5461e;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                y1Var.f5461e = i5 - Integer.MIN_VALUE;
            } else {
                y1Var = new y1(cVar);
            }
        } else {
            y1Var = new y1(cVar);
        }
        Object obj = y1Var.f5460d;
        int i6 = y1Var.f5461e;
        if (i6 != 0) {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
            throw new i4.p();
        }
        p3.j.O(obj);
        if (!qVar.f5681d.f5693p) {
            throw new IllegalArgumentException("establishTextInputSession called from an unattached node");
        }
        p1.e1 e1VarT = p1.f.t(qVar);
        n0.h hVar = (n0.h) p1.f.s(qVar).f4799w;
        hVar.getClass();
        if (f0.l.t(hVar, f5110a) != null) {
            throw new ClassCastException();
        }
        y1Var.f5461e = 1;
        b(e1VarT, nVar, y1Var);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final void b(p1.e1 e1Var, a4.e eVar, v3.c cVar) {
        z1 z1Var;
        if (cVar instanceof z1) {
            z1Var = (z1) cVar;
            int i5 = z1Var.f5465e;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                z1Var.f5465e = i5 - Integer.MIN_VALUE;
            } else {
                z1Var = new z1(cVar);
            }
        } else {
            z1Var = new z1(cVar);
        }
        Object obj = z1Var.f5464d;
        int i6 = z1Var.f5465e;
        if (i6 == 0) {
            p3.j.O(obj);
            z1Var.f5465e = 1;
            ((u) e1Var).F(eVar, z1Var);
        } else {
            if (i6 == 1) {
                p3.j.O(obj);
                throw new i4.p();
            }
            if (i6 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
            throw new i4.p();
        }
    }
}
