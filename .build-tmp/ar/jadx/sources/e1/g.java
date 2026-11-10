package e1;

import android.graphics.PathMeasure;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g f1911f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g f1912g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1913e;

    static {
        int i5 = 0;
        f1911f = new g(i5, 0);
        f1912g = new g(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(int i5, int i6) {
        super(i5);
        this.f1913e = i6;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f1913e) {
            case 0:
                return new y0.k(new PathMeasure());
            default:
                return p3.n.f5026a;
        }
    }
}
