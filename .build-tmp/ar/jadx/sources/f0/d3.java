package f0;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d3 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2119d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2120e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f2121f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f2122g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f2123h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f2124i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Object f2125j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public /* synthetic */ Object f2126k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2127l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public d3(a4.a aVar, t3.c cVar) {
        super(2, cVar);
        this.f2127l = (b4.m) aVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d3(ContentResolver contentResolver, Uri uri, q1.b3 b3Var, k4.c cVar, Context context, t3.c cVar2) {
        super(2, cVar2);
        this.f2123h = contentResolver;
        this.f2124i = uri;
        this.f2125j = b3Var;
        this.f2126k = cVar;
        this.f2127l = context;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a4.a, b4.m] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f2119d) {
            case 0:
                d3 d3Var = new d3((b4.m) this.f2127l, cVar);
                d3Var.f2126k = obj;
                return d3Var;
            default:
                d3 d3Var2 = new d3((ContentResolver) this.f2123h, (Uri) this.f2124i, (q1.b3) this.f2125j, (k4.c) this.f2126k, (Context) this.f2127l, cVar);
                d3Var2.f2121f = obj;
                return d3Var2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        l4.e eVar = (l4.e) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f2119d) {
            case 0:
                ((d3) create(eVar, cVar)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
            default:
                return ((d3) create(eVar, cVar)).invokeSuspend(p3.n.f5026a);
        }
    }

    /* JADX WARN: Code duplicated, block: B:165:0x01fe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:166:0x01f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:89:0x01f3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:90:0x01f5 A[Catch: all -> 0x025f, LOOP:1: B:74:0x01ad->B:90:0x01f5, LOOP_END, TryCatch #9 {all -> 0x025f, blocks: (B:82:0x01de, B:98:0x020c, B:102:0x0216, B:105:0x021d, B:109:0x0237, B:111:0x0240, B:86:0x01e9, B:90:0x01f5), top: B:159:0x01de }] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r12v4, types: [a4.a, b4.m] */
    /* JADX WARN: Type inference failed for: r4v7, types: [a4.a, b4.m] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x0258 -> B:117:0x0259). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x009c -> B:20:0x005d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f0.d3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
