package q1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h2 implements p1.f1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f5219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Float f5220f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Float f5221g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public w1.g f5222h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public w1.g f5223i = null;

    public h2(int i5, ArrayList arrayList) {
        this.f5218d = i5;
        this.f5219e = arrayList;
    }

    @Override // p1.f1
    public final boolean w() {
        return this.f5219e.contains(this);
    }
}
