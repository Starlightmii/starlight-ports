package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q1 extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f4571e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f4572f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b4.w f4573g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f4574h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4575i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f4576j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ i4.v f4577k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a4.f f4578l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ v.j f4579m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o0 f4580n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q1(i4.v vVar, a4.f fVar, v.j jVar, o0 o0Var, t3.c cVar) {
        super(cVar);
        this.f4577k = vVar;
        this.f4578l = fVar;
        this.f4579m = jVar;
        this.f4580n = o0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        q1 q1Var = new q1(this.f4577k, this.f4578l, this.f4579m, this.f4580n, cVar);
        q1Var.f4576j = obj;
        return q1Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((q1) create((j1.c0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0167 A[Catch: k -> 0x0171, TryCatch #1 {k -> 0x0171, blocks: (B:51:0x0161, B:53:0x0167, B:55:0x0175), top: B:71:0x0161 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0175 A[Catch: k -> 0x0171, TRY_LEAVE, TryCatch #1 {k -> 0x0171, blocks: (B:51:0x0161, B:53:0x0167, B:55:0x0175), top: B:71:0x0161 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008a, code lost:
    
        if (r15.h(r4, r1, r18) == r12) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b1, code lost:
    
        if (p.s1.a(r3, r18) == r12) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0111, code lost:
    
        if (r2 == r12) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x019b, code lost:
    
        return r12;
     */
    @Override // v3.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p.q1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
