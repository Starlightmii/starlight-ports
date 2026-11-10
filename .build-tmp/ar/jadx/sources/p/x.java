package p;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f4627e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Serializable f4628f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j1.c0 f4629g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b4.v f4630h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public t1 f4631i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public j1.u f4632j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4633k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f4634l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4635m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4636n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ b4.m f4637o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ l0 f4638p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ b4.m f4639q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ b4.m f4640r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ b4.m f4641s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ b4.m f4642t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public x(a4.a aVar, l0 l0Var, a4.e eVar, a4.e eVar2, a4.a aVar2, a4.c cVar, t3.c cVar2) {
        super(cVar2);
        this.f4637o = (b4.m) aVar;
        this.f4638p = l0Var;
        this.f4639q = (b4.m) eVar;
        this.f4640r = (b4.m) eVar2;
        this.f4641s = (b4.m) aVar2;
        this.f4642t = (b4.m) cVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference failed for: r3v0, types: [a4.e, b4.m] */
    /* JADX WARN: Type inference failed for: r4v0, types: [a4.e, b4.m] */
    /* JADX WARN: Type inference failed for: r5v0, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference failed for: r6v0, types: [a4.c, b4.m] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        x xVar = new x(this.f4637o, this.f4638p, this.f4639q, this.f4640r, this.f4641s, this.f4642t, cVar);
        xVar.f4636n = obj;
        return xVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((x) create((j1.c0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:118:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:120:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:121:0x02f1 A[PHI: r1 r2 r5 r18 r19
      0x02f1: PHI (r1v25 ??) = (r1v42 ??), (r1v34 ??) binds: [B:119:0x02ec, B:169:0x03b6] A[DONT_GENERATE, DONT_INLINE]
      0x02f1: PHI (r2v39 long) = (r2v3 long), (r2v42 long) binds: [B:119:0x02ec, B:169:0x03b6] A[DONT_GENERATE, DONT_INLINE]
      0x02f1: PHI (r5v16 j1.c0) = (r5v3 j1.c0), (r5v26 j1.c0) binds: [B:119:0x02ec, B:169:0x03b6] A[DONT_GENERATE, DONT_INLINE]
      0x02f1: PHI (r18v11 p.l0) = (r18v1 p.l0), (r18v12 p.l0) binds: [B:119:0x02ec, B:169:0x03b6] A[DONT_GENERATE, DONT_INLINE]
      0x02f1: PHI (r19v12 j1.j) = (r19v1 j1.j), (r19v14 j1.j) binds: [B:119:0x02ec, B:169:0x03b6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:167:0x03aa  */
    /* JADX WARN: Code duplicated, block: B:168:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:179:0x0161 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x0149  */
    /* JADX WARN: Code duplicated, block: B:46:0x0164 A[LOOP:2: B:42:0x0147->B:46:0x0164, LOOP_END] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [a4.e, b4.m] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v28, types: [a4.c, b4.m] */
    /* JADX WARN: Type inference failed for: r1v29, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v4, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r2v30, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [a4.e, b4.m] */
    /* JADX WARN: Type inference failed for: r4v39, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x029d -> B:107:0x029e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:123:0x0316 -> B:125:0x0319). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00f7 -> B:31:0x00fb). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r25) {
        /*
            Method dump skipped, instruction units count: 978
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p.x.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
