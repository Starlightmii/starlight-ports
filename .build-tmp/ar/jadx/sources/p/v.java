package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j1.i f4608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4609f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4610g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f4611h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ b4.w f4612i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ b4.w f4613j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(b4.w wVar, b4.w wVar2, t3.c cVar) {
        super(cVar);
        this.f4612i = wVar;
        this.f4613j = wVar2;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        v vVar = new v(this.f4612i, this.f4613j, cVar);
        vVar.f4611h = obj;
        return vVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((v) create((j1.c0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0059  */
    /* JADX WARN: Code duplicated, block: B:20:0x0066 A[LOOP:2: B:16:0x0057->B:20:0x0066, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:70:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x006a A[EDGE_INSN: B:71:0x006a->B:22:0x006a BREAK  A[LOOP:2: B:16:0x0057->B:20:0x0066], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00a4 -> B:35:0x00a7). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p.v.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
