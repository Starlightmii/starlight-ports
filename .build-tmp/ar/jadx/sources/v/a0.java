package v;

import c0.j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f5851d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ j1.d0 f5853f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ u0 f5854g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a0(j1.d0 d0Var, u0 u0Var, t3.c cVar, int i5) {
        super(2, cVar);
        this.f5851d = i5;
        this.f5853f = d0Var;
        this.f5854g = u0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f5851d) {
            case 0:
                return new a0(this.f5853f, this.f5854g, cVar, 0);
            case 1:
                return new a0(this.f5853f, this.f5854g, cVar, 1);
            default:
                return new a0(this.f5853f, this.f5854g, cVar, 2);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f5851d) {
            case 0:
                break;
            case 1:
                break;
        }
        return ((a0) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f5851d;
        u0 u0Var = this.f5854g;
        j1.d0 d0Var = this.f5853f;
        u3.a aVar = u3.a.f5838d;
        p3.n nVar = p3.n.f5026a;
        switch (i5) {
            case 0:
                int i6 = this.f5852e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    this.f5852e = 1;
                    Object objC = i4.y.c(new p0(d0Var, u0Var, null), this);
                    if (objC != aVar) {
                        objC = nVar;
                    }
                    if (objC == aVar) {
                        return aVar;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return nVar;
            case 1:
                int i7 = this.f5852e;
                if (i7 == 0) {
                    p3.j.O(obj);
                    this.f5852e = 1;
                    Object objJ = a.a.j(d0Var, new p.k0(u0Var, null), this);
                    if (objJ != aVar) {
                        objJ = nVar;
                    }
                    if (objJ == aVar) {
                        return aVar;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return nVar;
            default:
                int i8 = this.f5852e;
                if (i8 == 0) {
                    p3.j.O(obj);
                    this.f5852e = 1;
                    q0 q0Var = new q0(u0Var, 0);
                    r0 r0Var = new r0(u0Var, 0);
                    r0 r0Var2 = new r0(u0Var, 1);
                    j1 j1Var = new j1(12, u0Var);
                    float f5 = p.z.f4654a;
                    Object objJ2 = a.a.j(d0Var, new p.x(p.w.f4624e, null, new j1(3, q0Var), j1Var, r0Var2, new c0.g1(14, r0Var), null), this);
                    if (objJ2 != aVar) {
                        objJ2 = nVar;
                    }
                    if (objJ2 != aVar) {
                        objJ2 = nVar;
                    }
                    if (objJ2 != aVar) {
                        objJ2 = nVar;
                    }
                    if (objJ2 == aVar) {
                        return aVar;
                    }
                } else {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return nVar;
        }
    }
}
