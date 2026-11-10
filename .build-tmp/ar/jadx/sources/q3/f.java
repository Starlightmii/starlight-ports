package q3;

import java.util.AbstractList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f extends AbstractList implements List, c4.b {
    public abstract int a();

    public abstract Object b(int i5);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i5) {
        return b(i5);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return a();
    }
}
