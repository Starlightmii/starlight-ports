package j;

import java.util.AbstractSet;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends AbstractSet {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f f3065d;

    public a(f fVar) {
        this.f3065d = fVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new d(this.f3065d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f3065d.f3110f;
    }
}
