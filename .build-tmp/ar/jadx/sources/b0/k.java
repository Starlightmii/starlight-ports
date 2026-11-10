package b0;

import m.z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ m f418f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(m mVar, t3.c cVar, int i5) {
        super(2, cVar);
        this.f416d = i5;
        this.f418f = mVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f416d) {
            case 0:
                return new k(this.f418f, cVar, 0);
            case 1:
                return new k(this.f418f, cVar, 1);
            case 2:
                return new k(this.f418f, cVar, 2);
            default:
                return new k(this.f418f, cVar, 3);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f416d) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return ((k) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f416d) {
            case 0:
                int i5 = this.f417e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    m.c cVar = this.f418f.f427f;
                    Float f5 = new Float(1.0f);
                    z0 z0VarH = m.d.h(75, 2, m.w.f3799c);
                    this.f417e = 1;
                    Object objB = m.c.b(cVar, f5, z0VarH, this);
                    u3.a aVar = u3.a.f5838d;
                    if (objB == aVar) {
                        return aVar;
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            case 1:
                int i6 = this.f417e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    m.c cVar2 = this.f418f.f428g;
                    Float f6 = new Float(1.0f);
                    z0 z0VarH2 = m.d.h(225, 2, m.w.f3797a);
                    this.f417e = 1;
                    Object objB2 = m.c.b(cVar2, f6, z0VarH2, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objB2 == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            case 2:
                int i7 = this.f417e;
                if (i7 == 0) {
                    p3.j.O(obj);
                    m.c cVar3 = this.f418f.f429h;
                    Float f7 = new Float(1.0f);
                    z0 z0VarH3 = m.d.h(225, 2, m.w.f3799c);
                    this.f417e = 1;
                    Object objB3 = m.c.b(cVar3, f7, z0VarH3, this);
                    u3.a aVar3 = u3.a.f5838d;
                    if (objB3 == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            default:
                int i8 = this.f417e;
                if (i8 == 0) {
                    p3.j.O(obj);
                    m.c cVar4 = this.f418f.f427f;
                    Float f8 = new Float(0.0f);
                    z0 z0VarH4 = m.d.h(150, 2, m.w.f3799c);
                    this.f417e = 1;
                    Object objB4 = m.c.b(cVar4, f8, z0VarH4, this);
                    u3.a aVar4 = u3.a.f5838d;
                    if (objB4 == aVar4) {
                        return aVar4;
                    }
                } else {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
        }
    }
}
