package q3;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import p0.a0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f5480d;

    public w(List list) {
        b4.l.e("delegate", list);
        this.f5480d = list;
    }

    @Override // q3.a
    public final int a() {
        return this.f5480d.size();
    }

    @Override // java.util.List
    public final Object get(int i5) {
        if (i5 >= 0 && i5 <= p3.j.z(this)) {
            return this.f5480d.get(p3.j.z(this) - i5);
        }
        StringBuilder sbG = b.b.g("Element index ", i5, " must be in range [");
        sbG.append(new e4.d(0, p3.j.z(this), 1));
        sbG.append("].");
        throw new IndexOutOfBoundsException(sbG.toString());
    }

    @Override // q3.d, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new a0(this, 0);
    }

    @Override // q3.d, java.util.List
    public final ListIterator listIterator() {
        return new a0(this, 0);
    }

    @Override // q3.d, java.util.List
    public final ListIterator listIterator(int i5) {
        return new a0(this, i5);
    }
}
