package p1;

import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f4929f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final n f4930g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4931e;

    static {
        int i5 = 0;
        f4929f = new n(i5, 0);
        f4930g = new n(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i5, int i6) {
        super(i5);
        this.f4931e = i6;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f4931e) {
            case 0:
                return new LinkedHashMap();
            default:
                return new d0(3);
        }
    }
}
