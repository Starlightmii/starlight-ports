package j0;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends q3.d implements List, Collection, c4.a {
    public abstract c b(int i5, Object obj);

    public abstract c c(Object obj);

    @Override // q3.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // q3.a, java.util.Collection, java.util.List
    public final boolean containsAll(Collection collection) {
        Collection collection2 = collection;
        if ((collection2 instanceof Collection) && collection2.isEmpty()) {
            return true;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public c d(Collection collection) {
        g gVarE = e();
        gVarE.addAll(collection);
        return gVarE.c();
    }

    public abstract g e();

    public abstract c f(b bVar);

    public abstract c g(int i5);

    public abstract c h(int i5, Object obj);

    @Override // q3.d, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // q3.d, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // q3.d, java.util.List
    public final List subList(int i5, int i6) {
        return new i0.a(this, i5, i6);
    }
}
