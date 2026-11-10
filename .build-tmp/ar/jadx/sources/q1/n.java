package q1;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f5266f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n f5267g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final n f5268h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5269e;

    static {
        int i5 = 1;
        f5266f = new n(i5, 0);
        f5267g = new n(i5, 1);
        f5268h = new n(i5, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(int i5) {
        super(1);
        this.f5269e = 5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i5, int i6) {
        super(i5);
        this.f5269e = i6;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(w0.b bVar, int i5) {
        super(1);
        this.f5269e = i5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f5269e) {
            case 0:
                return p3.n.f5026a;
            case 1:
                return Boolean.TRUE;
            case 2:
                return Boolean.FALSE;
            case 3:
                Boolean boolB = w0.d.B((w0.r) obj);
                return Boolean.valueOf(boolB != null ? boolB.booleanValue() : true);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                Boolean boolB2 = w0.d.B((w0.r) obj);
                return Boolean.valueOf(boolB2 != null ? boolB2.booleanValue() : true);
            default:
                Boolean boolB3 = w0.d.B((w0.r) obj);
                return Boolean.valueOf(boolB3 != null ? boolB3.booleanValue() : false);
        }
    }
}
