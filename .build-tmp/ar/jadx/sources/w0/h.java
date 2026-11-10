package w0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final h f6236f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f6237g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f6238h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f6239i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final h f6240j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6241e;

    static {
        int i5 = 1;
        f6236f = new h(i5, 0);
        f6237g = new h(i5, 1);
        f6238h = new h(i5, 2);
        f6239i = new h(i5, 3);
        f6240j = new h(i5, 4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(int i5) {
        super(1);
        this.f6241e = 5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(int i5, int i6) {
        super(i5);
        this.f6241e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f6241e) {
            case 0:
                ((i) obj).b(false);
                return p3.n.f5026a;
            case 1:
                int i5 = ((b) obj).f6225a;
                return n.f6254b;
            case 2:
                int i6 = ((b) obj).f6225a;
                return n.f6254b;
            case 3:
                Boolean boolB = d.B((r) obj);
                return Boolean.valueOf(boolB != null ? boolB.booleanValue() : false);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                Boolean boolB2 = d.B((r) obj);
                return Boolean.valueOf(boolB2 != null ? boolB2.booleanValue() : false);
            default:
                Boolean boolB3 = d.B((r) obj);
                return Boolean.valueOf(boolB3 != null ? boolB3.booleanValue() : false);
        }
    }
}
