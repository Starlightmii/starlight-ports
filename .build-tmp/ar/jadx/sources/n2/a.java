package n2;

import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f4195f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f4196g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4197e;

    static {
        int i5 = 0;
        f4195f = new a(i5, 0);
        f4196g = new a(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i5, int i6) {
        super(i5);
        this.f4197e = i6;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f4197e) {
            case 0:
                return "DEFAULT_TEST_TAG";
            default:
                return UUID.randomUUID();
        }
    }
}
