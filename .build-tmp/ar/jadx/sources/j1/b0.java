package j1;

import m.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3218d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f3220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3221g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(long j5, c0 c0Var, t3.c cVar) {
        super(2, cVar);
        this.f3220f = j5;
        this.f3221g = c0Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(m.c cVar, long j5, t3.c cVar2) {
        super(2, cVar2);
        this.f3221g = cVar;
        this.f3220f = j5;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3218d) {
            case 0:
                return new b0(this.f3220f, (c0) this.f3221g, cVar);
            default:
                return new b0((m.c) this.f3221g, this.f3220f, cVar);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f3218d) {
            case 0:
                break;
        }
        return ((b0) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0075  */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        i4.h hVar;
        switch (this.f3218d) {
            case 0:
                int i5 = this.f3219e;
                long j5 = this.f3220f;
                u3.a aVar = u3.a.f5838d;
                if (i5 != 0) {
                    if (i5 == 1) {
                        p3.j.O(obj);
                    } else {
                        if (i5 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        p3.j.O(obj);
                    }
                    hVar = ((c0) this.f3221g).f3226f;
                    if (hVar != null) {
                        hVar.resumeWith(p3.j.r(new k(j5)));
                    }
                    return p3.n.f5026a;
                }
                p3.j.O(obj);
                this.f3219e = 1;
                if (i4.y.d(j5 - 1, this) == aVar) {
                    return aVar;
                }
                this.f3219e = 2;
                if (i4.y.d(1L, this) == aVar) {
                    return aVar;
                }
                hVar = ((c0) this.f3221g).f3226f;
                if (hVar != null) {
                    hVar.resumeWith(p3.j.r(new k(j5)));
                }
                return p3.n.f5026a;
            default:
                int i6 = this.f3219e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    m.c cVar = (m.c) this.f3221g;
                    x0.c cVar2 = new x0.c(this.f3220f);
                    h0 h0Var = z.x.f7025d;
                    this.f3219e = 1;
                    Object objB = m.c.b(cVar, cVar2, h0Var, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objB == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
        }
    }
}
