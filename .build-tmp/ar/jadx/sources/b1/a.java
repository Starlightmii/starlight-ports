package b1;

import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f473f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f474g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f475e;

    static {
        int i5 = 1;
        f473f = new a(i5, 0);
        f474g = new a(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i5, int i6) {
        super(i5);
        this.f475e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f475e) {
            case 0:
                break;
            default:
                a1.f.B((a1.f) obj, u.f6693f, 0L, 126);
                break;
        }
        return p3.n.f5026a;
    }
}
