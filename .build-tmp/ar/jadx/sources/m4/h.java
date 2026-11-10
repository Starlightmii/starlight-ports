package m4;

import b4.w;
import i4.v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3827d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3828e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f3829f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ k f3830g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ l4.e f3831h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(k kVar, l4.e eVar, Object obj, t3.c cVar) {
        super(2, cVar);
        this.f3830g = kVar;
        this.f3831h = eVar;
        this.f3829f = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(k kVar, l4.e eVar, t3.c cVar) {
        super(2, cVar);
        this.f3830g = kVar;
        this.f3831h = eVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3827d) {
            case 0:
                return new h(this.f3830g, this.f3831h, this.f3829f, cVar);
            default:
                h hVar = new h(this.f3830g, this.f3831h, cVar);
                hVar.f3829f = obj;
                return hVar;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        v vVar = (v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f3827d) {
            case 0:
                break;
        }
        return ((h) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Type inference failed for: r9v2, types: [a4.f, v3.i] */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f3827d) {
            case 0:
                int i5 = this.f3828e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    ?? r9 = this.f3830g.f3842h;
                    Object obj2 = this.f3829f;
                    this.f3828e = 1;
                    Object objB = r9.b(this.f3831h, obj2, this);
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
                int i6 = this.f3828e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    v vVar = (v) this.f3829f;
                    w wVar = new w();
                    k kVar = this.f3830g;
                    l4.d dVar = kVar.f3826g;
                    j jVar = new j(wVar, vVar, kVar, this.f3831h, 0);
                    this.f3828e = 1;
                    Object objG = dVar.g(jVar, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objG == aVar2) {
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
