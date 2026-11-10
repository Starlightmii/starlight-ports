package d0;

import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements q.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1537a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f1538b = new LinkedHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a2.h f1539c;

    public h(q.k kVar, long j5) {
        this.f1537a = j5;
        this.f1539c = new a2.h(kVar.f5082a, 3, this);
    }

    @Override // q.j
    public final l4.d a() {
        return this.f1539c;
    }
}
