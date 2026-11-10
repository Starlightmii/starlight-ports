package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o4 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1135d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1136e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1137f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ float f1138g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o4(r4 r4Var, float f5, t3.c cVar, int i5) {
        super(2, cVar);
        this.f1135d = i5;
        this.f1137f = r4Var;
        this.f1138g = f5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o4(v1.c cVar, t3.c cVar2) {
        super(2, cVar2);
        this.f1135d = 2;
        this.f1137f = cVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f1135d) {
            case 0:
                return new o4((r4) this.f1137f, this.f1138g, cVar, 0);
            case 1:
                return new o4((r4) this.f1137f, this.f1138g, cVar, 1);
            default:
                o4 o4Var = new o4((v1.c) this.f1137f, cVar);
                o4Var.f1138g = ((Number) obj).floatValue();
                return o4Var;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1135d) {
            case 0:
                return ((o4) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((o4) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                return ((o4) create(Float.valueOf(((Number) obj).floatValue()), (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f1135d) {
            case 0:
                r4 r4Var = (r4) this.f1137f;
                int i5 = this.f1136e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    m.c cVar = r4Var.f1241u;
                    if (cVar != null) {
                        Float f5 = new Float(this.f1138g);
                        m.k kVar = r4Var.f1239s ? androidx.compose.material3.a.f157f : androidx.compose.material3.a.f158g;
                        this.f1136e = 1;
                        obj = m.c.b(cVar, f5, kVar, this);
                        u3.a aVar = u3.a.f5838d;
                        if (obj == aVar) {
                            return aVar;
                        }
                    }
                    return p3.n.f5026a;
                }
                if (i5 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                p3.j.O(obj);
                return p3.n.f5026a;
            case 1:
                r4 r4Var2 = (r4) this.f1137f;
                int i6 = this.f1136e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    m.c cVar2 = r4Var2.f1240t;
                    if (cVar2 != null) {
                        Float f6 = new Float(this.f1138g);
                        m.k kVar2 = r4Var2.f1239s ? androidx.compose.material3.a.f157f : androidx.compose.material3.a.f158g;
                        this.f1136e = 1;
                        obj = m.c.b(cVar2, f6, kVar2, this);
                        u3.a aVar2 = u3.a.f5838d;
                        if (obj == aVar2) {
                            return aVar2;
                        }
                    }
                    return p3.n.f5026a;
                }
                if (i6 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                p3.j.O(obj);
                return p3.n.f5026a;
            default:
                v1.c cVar3 = (v1.c) this.f1137f;
                int i7 = this.f1136e;
                if (i7 == 0) {
                    p3.j.O(obj);
                    float f7 = this.f1138g;
                    Object obj2 = cVar3.f6189a.f6318d.f6308d.get(w1.h.f6286e);
                    if (obj2 == null) {
                        obj2 = null;
                    }
                    a4.e eVar = (a4.e) obj2;
                    if (eVar == null) {
                        d4.a.M("Required value was null.");
                        throw null;
                    }
                    x0.c cVar4 = new x0.c(p3.j.b(0.0f, f7));
                    this.f1136e = 1;
                    obj = eVar.invoke(cVar4, this);
                    u3.a aVar3 = u3.a.f5838d;
                    if (obj == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return new Float(x0.c.e(((x0.c) obj).f6532a));
        }
    }
}
