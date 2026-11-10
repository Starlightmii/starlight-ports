package b0;

import i4.h1;
import i4.s0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f421f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(Object obj, t3.c cVar, int i5) {
        super(2, cVar);
        this.f419d = i5;
        this.f421f = obj;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f419d) {
            case 0:
                l lVar = new l((m) this.f421f, cVar, 0);
                lVar.f420e = obj;
                return lVar;
            case 1:
                l lVar2 = new l((m) this.f421f, cVar, 1);
                lVar2.f420e = obj;
                return lVar2;
            default:
                l lVar3 = new l((x.j) this.f421f, cVar, 2);
                lVar3.f420e = obj;
                return lVar3;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f419d) {
            case 0:
                break;
            case 1:
                break;
        }
        return ((l) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        boolean z2;
        switch (this.f419d) {
            case 0:
                p3.j.O(obj);
                i4.v vVar = (i4.v) this.f420e;
                m mVar = (m) this.f421f;
                t3.c cVar = null;
                i4.y.n(vVar, null, new k(mVar, cVar, 0), 3);
                i4.y.n(vVar, null, new k(mVar, cVar, 1), 3);
                return i4.y.n(vVar, null, new k(mVar, cVar, 2), 3);
            case 1:
                p3.j.O(obj);
                return i4.y.n((i4.v) this.f420e, null, new k((m) this.f421f, null, 3), 3);
            default:
                p3.j.O(obj);
                i4.v vVar2 = (i4.v) this.f420e;
                x.j jVar = (x.j) this.f421f;
                s0 s0Var = (s0) jVar.f6471a.getAndSet(null);
                AtomicReference atomicReference = jVar.f6471a;
                h1 h1VarN = i4.y.n(vVar2, null, new t(s0Var, jVar, null, 21), 3);
                while (!atomicReference.compareAndSet(null, h1VarN)) {
                    if (atomicReference.get() != null) {
                        z2 = false;
                        return Boolean.valueOf(z2);
                    }
                }
                z2 = true;
                return Boolean.valueOf(z2);
        }
    }
}
