package j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3264e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ b4.w f3265f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(b4.w wVar) {
        super(1);
        this.f3265f = wVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(b4.w wVar, int i5) {
        super(1);
        this.f3265f = wVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f3264e) {
            case 0:
                o oVar = (o) obj;
                b4.w wVar = this.f3265f;
                Object obj2 = wVar.f697d;
                if (obj2 == null && oVar.f3269q) {
                    wVar.f697d = oVar;
                } else if (obj2 != null) {
                    oVar.getClass();
                }
                return Boolean.TRUE;
            default:
                Boolean boolB = w0.d.B((w0.r) obj);
                this.f3265f.f697d = boolB;
                return Boolean.valueOf(boolB != null ? boolB.booleanValue() : false);
        }
    }
}
