package h;

import java.util.concurrent.Executors;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends a.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static volatile a f2566m;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f2567l;

    public a(int i5) {
        switch (i5) {
            case 1:
                this.f2567l = new Object();
                Executors.newFixedThreadPool(4, new b());
                break;
            default:
                this.f2567l = new a(1);
                break;
        }
    }
}
