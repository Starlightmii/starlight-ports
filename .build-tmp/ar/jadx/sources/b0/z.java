package b0;

import m.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f468d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f470f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f471g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f472h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(a0 a0Var, float f5, m.k kVar, t3.c cVar) {
        super(2, cVar);
        this.f471g = a0Var;
        this.f469e = f5;
        this.f472h = kVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(s0 s0Var, t3.c cVar) {
        super(2, cVar);
        this.f472h = s0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f468d) {
            case 0:
                return new z((a0) this.f471g, this.f469e, (m.k) this.f472h, cVar);
            default:
                z zVar = new z((s0) this.f472h, cVar);
                zVar.f471g = obj;
                return zVar;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f468d) {
            case 0:
                break;
        }
        return ((z) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        float f5;
        i4.v vVar;
        switch (this.f468d) {
            case 0:
                int i5 = this.f470f;
                if (i5 == 0) {
                    p3.j.O(obj);
                    m.c cVar = (m.c) ((a0) this.f471g).f375c;
                    Float f6 = new Float(this.f469e);
                    m.k kVar = (m.k) this.f472h;
                    this.f470f = 1;
                    Object objB = m.c.b(cVar, f6, kVar, this);
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
            default:
                int i6 = this.f470f;
                if (i6 == 0) {
                    p3.j.O(obj);
                    i4.v vVar2 = (i4.v) this.f471g;
                    f5 = m.d.f(vVar2.h());
                    vVar = vVar2;
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    f5 = this.f469e;
                    vVar = (i4.v) this.f471g;
                    p3.j.O(obj);
                }
                while (i4.y.l(vVar)) {
                    d0.m mVar = new d0.m((s0) this.f472h, f5);
                    this.f471g = vVar;
                    this.f469e = f5;
                    this.f470f = 1;
                    Object objD = f0.l.q(getContext()).d(mVar, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objD == aVar2) {
                        return aVar2;
                    }
                }
                return p3.n.f5026a;
        }
    }
}
