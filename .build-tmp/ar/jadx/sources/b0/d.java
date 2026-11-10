package b0;

import f0.g3;
import f0.k2;
import i4.f0;
import io.github.libxposed.service.IXposedService;
import j1.d0;
import p.g0;
import p.g1;
import p.i1;
import p.o0;
import p.p0;
import p1.y0;
import v.u0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f396d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f397e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f398f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f399g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f400h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public d(a4.f fVar, o0 o0Var, j1.u uVar, t3.c cVar) {
        super(2, cVar);
        this.f396d = 8;
        this.f398f = (v3.i) fVar;
        this.f399g = o0Var;
        this.f400h = uVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(Object obj, Object obj2, Object obj3, t3.c cVar, int i5) {
        super(2, cVar);
        this.f396d = i5;
        this.f398f = obj;
        this.f399g = obj2;
        this.f400h = obj3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(Object obj, Object obj2, t3.c cVar, int i5) {
        super(2, cVar);
        this.f396d = i5;
        this.f399g = obj;
        this.f400h = obj2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(k4.c cVar, t3.c cVar2) {
        super(2, cVar2);
        this.f396d = 9;
        this.f400h = cVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public d(i1 i1Var, a4.e eVar, t3.c cVar) {
        super(2, cVar);
        this.f396d = 7;
        this.f399g = i1Var;
        this.f400h = (v3.i) eVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public d(s.g gVar, y0 y0Var, a4.a aVar, t3.c cVar) {
        super(2, cVar);
        this.f396d = 10;
        this.f398f = gVar;
        this.f399g = y0Var;
        this.f400h = (b4.m) aVar;
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [a4.f, v3.i] */
    /* JADX WARN: Type inference failed for: r2v12, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference failed for: r2v8, types: [a4.e, v3.i] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f396d) {
            case 0:
                return new d((m) this.f398f, (e) this.f399g, (q.m) this.f400h, cVar, 0);
            case 1:
                d dVar = new d((q.j) this.f399g, (b) this.f400h, cVar, 1);
                dVar.f398f = obj;
                return dVar;
            case 2:
                d dVar2 = new d((k2) this.f399g, (f0.g) this.f400h, cVar, 2);
                dVar2.f398f = obj;
                return dVar2;
            case 3:
                d dVar3 = new d((l4.e) this.f399g, (m4.f) this.f400h, cVar, 3);
                dVar3.f398f = obj;
                return dVar3;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return new d((q.k) this.f398f, (q.i) this.f399g, (f0) this.f400h, cVar, 4);
            case 5:
                d dVar4 = new d((p.n) this.f399g, (d) this.f400h, cVar, 5);
                dVar4.f398f = obj;
                return dVar4;
            case 6:
                d dVar5 = new d((g0) this.f399g, (i1) this.f400h, cVar, 6);
                dVar5.f398f = obj;
                return dVar5;
            case 7:
                d dVar6 = new d((i1) this.f399g, (v3.i) this.f400h, cVar);
                dVar6.f398f = obj;
                return dVar6;
            case 8:
                return new d((a4.f) this.f398f, (o0) this.f399g, (j1.u) this.f400h, cVar);
            case 9:
                return new d((k4.c) this.f400h, cVar);
            case 10:
                return new d((s.g) this.f398f, (y0) this.f399g, (a4.a) this.f400h, cVar);
            case 11:
                d dVar7 = new d((u0) this.f399g, (e0) this.f400h, cVar, 11);
                dVar7.f398f = obj;
                return dVar7;
            case 12:
                d dVar8 = new d((a1.d) this.f399g, (u0) this.f400h, cVar, 12);
                dVar8.f398f = obj;
                return dVar8;
            default:
                d dVar9 = new d((g3) this.f399g, (m.c) this.f400h, cVar, 13);
                dVar9.f398f = obj;
                return dVar9;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f396d) {
            case 0:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 2:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 3:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 5:
                return ((d) create((p0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 6:
                return ((d) create((g1) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 7:
                return ((d) create((p0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 8:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 9:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 10:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 11:
                return ((d) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 12:
                return ((d) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                return ((d) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x01b6 */
    /* JADX WARN: Code duplicated, block: B:104:0x0202 A[Catch: all -> 0x01b6, TryCatch #2 {, blocks: (B:82:0x01b2, B:93:0x01db, B:95:0x01e3, B:96:0x01f1, B:102:0x01ff, B:90:0x01ce, B:104:0x0202, B:106:0x0207, B:107:0x0208, B:89:0x01c8, B:97:0x01f2, B:99:0x01f8), top: B:244:0x01a6, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:242:0x01f2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x01da  */
    /* JADX WARN: Code duplicated, block: B:95:0x01e3 A[Catch: all -> 0x01b6, TryCatch #2 {, blocks: (B:82:0x01b2, B:93:0x01db, B:95:0x01e3, B:96:0x01f1, B:102:0x01ff, B:90:0x01ce, B:104:0x0202, B:106:0x0207, B:107:0x0208, B:89:0x01c8, B:97:0x01f2, B:99:0x01f8), top: B:244:0x01a6, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x01f8 A[Catch: all -> 0x0206, TRY_LEAVE, TryCatch #1 {all -> 0x0206, blocks: (B:97:0x01f2, B:99:0x01f8), top: B:242:0x01f2, outer: #2 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v42, types: [a4.e, v3.i] */
    /* JADX WARN: Type inference failed for: r13v46, types: [a4.f, v3.i] */
    /* JADX WARN: Type inference failed for: r6v9, types: [a4.a, b4.m] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:91:0x01d8 -> B:93:0x01db). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // v3.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 1140
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b0.d.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
