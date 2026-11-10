package q0;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f5086a;

    public a(List list) {
        this.f5086a = list;
    }

    public final boolean a() {
        List list = this.f5086a;
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((b) list.get(i5)).getClass();
        }
        return false;
    }
}
