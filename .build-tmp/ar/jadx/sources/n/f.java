package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3898e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3899f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f3900g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3901h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(Object obj, t3.c cVar, int i5) {
        super(cVar);
        this.f3898e = i5;
        this.f3901h = obj;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3898e) {
            case 0:
                f fVar = new f((g) this.f3901h, cVar, 0);
                fVar.f3900g = obj;
                return fVar;
            case 1:
                f fVar2 = new f((c0.g1) this.f3901h, cVar, 1);
                fVar2.f3900g = obj;
                return fVar2;
            default:
                f fVar3 = new f((a4.c) this.f3901h, cVar, 2);
                fVar3.f3900g = obj;
                return fVar3;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        j1.c0 c0Var = (j1.c0) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f3898e) {
            case 0:
                return ((f) create(c0Var, cVar)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((f) create(c0Var, cVar)).invokeSuspend(p3.n.f5026a);
            default:
                ((f) create(c0Var, cVar)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x011d  */
    /* JADX WARN: Code duplicated, block: B:53:0x0128  */
    /* JADX WARN: Code duplicated, block: B:58:0x0135  */
    /* JADX WARN: Code duplicated, block: B:61:0x0143  */
    /* JADX WARN: Code duplicated, block: B:67:0x0151  */
    /* JADX WARN: Code duplicated, block: B:69:0x015a  */
    /* JADX WARN: Code duplicated, block: B:72:0x0172  */
    /* JADX WARN: Code duplicated, block: B:76:0x012b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x014c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x0149 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v10, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0030 -> B:14:0x0033). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0104 -> B:49:0x0108). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:36:0x00b6
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n.f.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
