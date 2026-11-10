package n2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f4209f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f4210g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f4211h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f4212i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f4213j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4214e;

    static {
        int i5 = 1;
        f4209f = new e(i5, 0);
        f4210g = new e(i5, 1);
        f4211h = new e(i5, 2);
        f4212i = new e(i5, 3);
        f4213j = new e(i5, 4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i5, int i6) {
        super(i5);
        this.f4214e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5 = this.f4214e;
        p3.n nVar = p3.n.f5026a;
        switch (i5) {
            case 0:
                ((Number) obj).longValue();
                break;
            case 1:
                break;
            case 2:
                f4.d[] dVarArr = w1.s.f6363a;
                ((w1.i) obj).c(w1.q.f6352q, nVar);
                break;
            case 3:
                break;
            default:
                p pVar = (p) obj;
                if (pVar.isAttachedToWindow()) {
                    pVar.l();
                }
                break;
        }
        return nVar;
    }
}
