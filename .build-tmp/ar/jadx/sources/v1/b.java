package v1;

import b4.m;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f6185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f6186g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b f6187h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6188e;

    static {
        int i5 = 1;
        f6185f = new b(i5, 0);
        f6186g = new b(i5, 1);
        f6187h = new b(i5, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i5, int i6) {
        super(i5);
        this.f6188e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f6188e) {
            case 0:
                ((Number) obj).longValue();
                return n.f5026a;
            case 1:
                return Integer.valueOf(((j) obj).f6206b);
            default:
                return Integer.valueOf(((j) obj).f6207c.a());
        }
    }
}
