package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f911d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m.c f912e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f913f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f914g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ j f915h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q.i f916i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(m.c cVar, float f5, boolean z2, j jVar, q.i iVar, t3.c cVar2) {
        super(2, cVar2);
        this.f912e = cVar;
        this.f913f = f5;
        this.f914g = z2;
        this.f915h = jVar;
        this.f916i = iVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        return new i(this.f912e, this.f913f, this.f914g, this.f915h, this.f916i, cVar);
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:63:0x00d5 A[RETURN] */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        Object dVar;
        Object objD;
        int i5 = this.f911d;
        p3.n nVar = p3.n.f5026a;
        if (i5 == 0) {
            p3.j.O(obj);
            m.c cVar = this.f912e;
            float f5 = ((k2.e) cVar.f3633e.getValue()).f3406d;
            float f6 = this.f913f;
            if (!k2.e.a(f5, f6)) {
                boolean z2 = this.f914g;
                u3.a aVar = u3.a.f5838d;
                if (z2) {
                    float f7 = ((k2.e) cVar.f3633e.getValue()).f3406d;
                    j jVar = this.f915h;
                    m.z0 z0Var = null;
                    if (k2.e.a(f7, jVar.f936b)) {
                        dVar = new q.m(0L);
                    } else if (k2.e.a(f7, jVar.f938d)) {
                        dVar = new q.g();
                    } else {
                        dVar = k2.e.a(f7, jVar.f937c) ? new q.d() : null;
                    }
                    this.f911d = 2;
                    m.z0 z0Var2 = d0.e.f1528b;
                    m.z0 z0Var3 = d0.e.f1527a;
                    q.i iVar = this.f916i;
                    if (iVar != null) {
                        if ((iVar instanceof q.m) || (iVar instanceof q.b) || (iVar instanceof q.g) || (iVar instanceof q.d)) {
                            z0Var = z0Var3;
                        }
                    } else if (dVar != null) {
                        if ((dVar instanceof q.m) || (dVar instanceof q.b)) {
                            z0Var = z0Var2;
                        } else if (dVar instanceof q.g) {
                            z0Var = d0.e.f1529c;
                        } else if (dVar instanceof q.d) {
                            z0Var = z0Var2;
                        }
                    }
                    if (z0Var == null ? (objD = cVar.d(new k2.e(f6), this)) != aVar : (objD = m.c.b(cVar, new k2.e(f6), z0Var, this)) != aVar) {
                        objD = nVar;
                    }
                    if (objD == aVar) {
                        return aVar;
                    }
                } else {
                    k2.e eVar = new k2.e(f6);
                    this.f911d = 1;
                    if (cVar.d(eVar, this) == aVar) {
                        return aVar;
                    }
                }
            }
        } else {
            if (i5 != 1 && i5 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
        }
        return nVar;
    }
}
