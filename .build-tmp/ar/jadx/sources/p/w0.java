package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i1 f4625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4626b;

    public w0(i1 i1Var, boolean z2) {
        this.f4625a = i1Var;
        this.f4626b = z2;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object a(long j5, long j6, v3.c cVar) {
        v0 v0Var;
        long jD;
        if (cVar instanceof v0) {
            v0Var = (v0) cVar;
            int i5 = v0Var.f4617g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                v0Var.f4617g = i5 - Integer.MIN_VALUE;
            } else {
                v0Var = new v0(this, cVar);
            }
        } else {
            v0Var = new v0(this, cVar);
        }
        Object objB = v0Var.f4615e;
        int i6 = v0Var.f4617g;
        if (i6 == 0) {
            p3.j.O(objB);
            if (this.f4626b) {
                v0Var.f4614d = j6;
                v0Var.f4617g = 1;
                objB = this.f4625a.b(j6, v0Var);
                u3.a aVar = u3.a.f5838d;
                if (objB == aVar) {
                    return aVar;
                }
            } else {
                jD = 0;
            }
            return new k2.o(jD);
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        j6 = v0Var.f4614d;
        p3.j.O(objB);
        jD = k2.o.d(j6, ((k2.o) objB).f3424a);
        return new k2.o(jD);
    }
}
