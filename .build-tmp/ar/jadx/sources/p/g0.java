package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4460d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b4.w f4461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b4.w f4462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f4464h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a1 f4465i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(b4.w wVar, a1 a1Var, t3.c cVar) {
        super(2, cVar);
        this.f4462f = wVar;
        this.f4465i = a1Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(a1 a1Var, t3.c cVar) {
        super(2, cVar);
        this.f4465i = a1Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4460d) {
            case 0:
                g0 g0Var = new g0(this.f4462f, this.f4465i, cVar);
                g0Var.f4464h = obj;
                return g0Var;
            default:
                g0 g0Var2 = new g0(this.f4465i, cVar);
                g0Var2.f4464h = obj;
                return g0Var2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4460d) {
            case 0:
                return ((g0) create((a4.c) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                return ((g0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x003a A[PHI: r0 r6
      0x003a: PHI (r0v14 b4.w) = (r0v6 b4.w), (r0v18 b4.w) binds: [B:15:0x0037, B:41:0x00bb] A[DONT_GENERATE, DONT_INLINE]
      0x003a: PHI (r6v6 i4.v) = (r6v4 i4.v), (r6v7 i4.v) binds: [B:15:0x0037, B:41:0x00bb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x005e A[PHI: r7
      0x005e: PHI (r7v8 i4.v) = (r7v0 i4.v), (r7v3 i4.v), (r7v3 i4.v), (r7v3 i4.v), (r7v6 i4.v), (r7v9 i4.v) binds: [B:20:0x0056, B:50:0x00d8, B:52:0x00e5, B:46:0x00d1, B:32:0x008a, B:13:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:23:0x0064  */
    /* JADX WARN: Code duplicated, block: B:25:0x006d  */
    /* JADX WARN: Code duplicated, block: B:28:0x007e  */
    /* JADX WARN: Code duplicated, block: B:33:0x008c  */
    /* JADX WARN: Code duplicated, block: B:36:0x009d  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:49:0x00d6 A[Catch: CancellationException -> 0x00d4, TryCatch #2 {CancellationException -> 0x00d4, blocks: (B:43:0x00be, B:45:0x00c4, B:49:0x00d6, B:51:0x00da), top: B:91:0x00be }] */
    /* JADX WARN: Code duplicated, block: B:51:0x00da A[Catch: CancellationException -> 0x00d4, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x00d4, blocks: (B:43:0x00be, B:45:0x00c4, B:49:0x00d6, B:51:0x00da), top: B:91:0x00be }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0120  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x008a -> B:21:0x005e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00d1 -> B:21:0x005e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00d8 -> B:21:0x005e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x00e5 -> B:21:0x005e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x00f3 -> B:13:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x0147 -> B:81:0x0148). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x014c -> B:83:0x014d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p.g0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
