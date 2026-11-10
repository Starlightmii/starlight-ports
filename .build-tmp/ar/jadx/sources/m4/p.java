package m4;

import i4.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends v3.c implements l4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l4.e f3847d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t3.h f3848e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3849f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public t3.h f3850g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public t3.c f3851h;

    public p(l4.e eVar, t3.h hVar) {
        super(n.f3845d, t3.i.f5807d);
        this.f3847d = eVar;
        this.f3848e = hVar;
        this.f3849f = ((Number) hVar.t(new f0.k(22), 0)).intValue();
    }

    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) {
        try {
            Object objF = f(cVar, obj);
            return objF == u3.a.f5838d ? objF : p3.n.f5026a;
        } catch (Throwable th) {
            this.f3850g = new l(th, cVar.getContext());
            throw th;
        }
    }

    public final Object f(t3.c cVar, Object obj) {
        t3.h context = cVar.getContext();
        y.e(context);
        t3.h hVar = this.f3850g;
        if (hVar != context) {
            if (hVar instanceof l) {
                throw new IllegalStateException(h4.b.P("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((l) hVar).f3844e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
            }
            if (((Number) context.t(new f0.y(5, this), 0)).intValue() != this.f3849f) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.f3848e + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.f3850g = context;
        }
        this.f3851h = cVar;
        a4.f fVar = r.f3853a;
        l4.e eVar = this.f3847d;
        b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>", eVar);
        Object objB = fVar.b(eVar, obj, this);
        if (!b4.l.a(objB, u3.a.f5838d)) {
            this.f3851h = null;
        }
        return objB;
    }

    @Override // v3.a, v3.d
    public final v3.d getCallerFrame() {
        t3.c cVar = this.f3851h;
        if (cVar instanceof v3.d) {
            return (v3.d) cVar;
        }
        return null;
    }

    @Override // v3.c, t3.c
    public final t3.h getContext() {
        t3.h hVar = this.f3850g;
        return hVar == null ? t3.i.f5807d : hVar;
    }

    @Override // v3.a
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        Throwable thA = p3.i.a(obj);
        if (thA != null) {
            this.f3850g = new l(thA, getContext());
        }
        t3.c cVar = this.f3851h;
        if (cVar != null) {
            cVar.resumeWith(obj);
        }
        return u3.a.f5838d;
    }
}
