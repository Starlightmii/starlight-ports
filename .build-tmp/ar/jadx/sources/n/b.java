package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3864d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3865e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f3866f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ p.o0 f3867g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f3868h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q.k f3869i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ t f3870j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(p.o0 o0Var, long j5, q.k kVar, t tVar, t3.c cVar) {
        super(2, cVar);
        this.f3867g = o0Var;
        this.f3868h = j5;
        this.f3869i = kVar;
        this.f3870j = tVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        b bVar = new b(this.f3867g, this.f3868h, this.f3869i, this.f3870j, cVar);
        bVar.f3866f = obj;
        return bVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((b) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009c  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b3  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00bd, code lost:
    
        if (r15.b(r1, r18) == r11) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00da, code lost:
    
        if (r15.b(r3, r18) == r11) goto L43;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.lang.Object, java.util.concurrent.CancellationException] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v5, types: [q.m] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v4, types: [i4.s0] */
    @Override // v3.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n.b.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
