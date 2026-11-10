package v;

import c0.x2;
import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 extends v3.i implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6156d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ p.o0 f6157e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ long f6158f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ i4.v f6159g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ i1 f6160h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q.k f6161i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(i4.v vVar, i1 i1Var, q.k kVar, t3.c cVar) {
        super(3, cVar);
        this.f6159g = vVar;
        this.f6160h = i1Var;
        this.f6161i = kVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        long j5 = ((x0.c) obj2).f6532a;
        i1 i1Var = this.f6160h;
        q.k kVar = this.f6161i;
        z0 z0Var = new z0(this.f6159g, i1Var, kVar, (t3.c) obj3);
        z0Var.f6157e = (p.o0) obj;
        z0Var.f6158f = j5;
        return z0Var.invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f6156d;
        i4.v vVar = this.f6159g;
        if (i5 == 0) {
            p3.j.O(obj);
            p.o0 o0Var = this.f6157e;
            i4.y.n(vVar, null, new n.a(this.f6160h, this.f6158f, this.f6161i, null, 2), 3);
            this.f6156d = 1;
            obj = o0Var.d(this);
            u3.a aVar = u3.a.f5838d;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
        }
        i4.y.n(vVar, null, new x2(this.f6160h, ((Boolean) obj).booleanValue(), this.f6161i, null), 3);
        return p3.n.f5026a;
    }
}
