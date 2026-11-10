package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4513e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4514f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f4515g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f4516h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f4517i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public k0(t3.h hVar, a4.e eVar, t3.c cVar) {
        super(cVar);
        this.f4516h = hVar;
        this.f4517i = (v3.h) eVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(v.u0 u0Var, t3.c cVar) {
        super(cVar);
        this.f4517i = u0Var;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [a4.e, v3.h] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4513e) {
            case 0:
                k0 k0Var = new k0((t3.h) this.f4516h, (v3.h) this.f4517i, cVar);
                k0Var.f4515g = obj;
                return k0Var;
            default:
                k0 k0Var2 = new k0((v.u0) this.f4517i, cVar);
                k0Var2.f4515g = obj;
                return k0Var2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        j1.c0 c0Var = (j1.c0) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f4513e) {
            case 0:
                break;
        }
        return ((k0) create(c0Var, cVar)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v19, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [j1.c0, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v3, types: [j1.c0, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8, types: [a4.e, v3.h] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0059 -> B:19:0x005c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x00e0 -> B:39:0x00af). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x00f5 -> B:39:0x00af). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p.k0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
