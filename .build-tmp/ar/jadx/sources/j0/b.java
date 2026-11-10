package j0;

import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Collection f3185e;

    public /* synthetic */ b(int i5, Collection collection) {
        this.f3184d = i5;
        this.f3185e = collection;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        boolean zContains;
        switch (this.f3184d) {
            case 0:
                zContains = this.f3185e.contains(obj);
                break;
            case 1:
                zContains = this.f3185e.contains(obj);
                break;
            default:
                zContains = ((List) obj).retainAll(this.f3185e);
                break;
        }
        return Boolean.valueOf(zContains);
    }
}
