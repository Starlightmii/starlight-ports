package p0;

import f0.x1;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 implements List, c4.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f4672d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4673e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4674f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4675g;

    public b0(q qVar, int i5, int i6) {
        this.f4672d = qVar;
        this.f4673e = i5;
        this.f4674f = r.f(qVar);
        this.f4675g = i6 - i5;
    }

    public final void a() {
        if (r.f(this.f4672d) != this.f4674f) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.List
    public final void add(int i5, Object obj) {
        a();
        int i6 = this.f4673e + i5;
        q qVar = this.f4672d;
        qVar.add(i6, obj);
        this.f4675g++;
        this.f4674f = r.f(qVar);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        a();
        int i5 = this.f4673e + this.f4675g;
        q qVar = this.f4672d;
        qVar.add(i5, obj);
        this.f4675g++;
        this.f4674f = r.f(qVar);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(int i5, Collection collection) {
        a();
        int i6 = i5 + this.f4673e;
        q qVar = this.f4672d;
        boolean zAddAll = qVar.addAll(i6, collection);
        if (zAddAll) {
            this.f4675g = collection.size() + this.f4675g;
            this.f4674f = r.f(qVar);
        }
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        return addAll(this.f4675g, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        int i5;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        if (this.f4675g > 0) {
            a();
            q qVar = this.f4672d;
            int i6 = this.f4673e;
            int i7 = this.f4675g + i6;
            do {
                synchronized (r.f4731a) {
                    w wVar = qVar.f4730d;
                    b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                    w wVar2 = (w) m.h(wVar);
                    i5 = wVar2.f4764d;
                    cVar = wVar2.f4763c;
                }
                b4.l.b(cVar);
                j0.g gVarE = cVar.e();
                gVarE.subList(i6, i7).clear();
                j0.c cVarC = gVarE.c();
                if (b4.l.a(cVarC, cVar)) {
                    break;
                }
                w wVar3 = qVar.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
                synchronized (m.f4719c) {
                    fVarJ = m.j();
                    zB = r.b((w) m.w(wVar3, qVar, fVarJ), i5, cVarC, true);
                }
                m.n(fVarJ, qVar);
            } while (!zB);
            this.f4675g = 0;
            this.f4674f = r.f(this.f4672d);
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List, java.util.Collection
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

    @Override // java.util.List
    public final Object get(int i5) {
        a();
        r.a(i5, this.f4675g);
        return this.f4672d.get(this.f4673e + i5);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        a();
        int i5 = this.f4675g;
        int i6 = this.f4673e;
        Iterator it = d4.a.N(i6, i5 + i6).iterator();
        while (it.hasNext()) {
            int iNextInt = ((e4.c) it).nextInt();
            if (b4.l.a(obj, this.f4672d.get(iNextInt))) {
                return iNextInt - i6;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f4675g == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        a();
        int i5 = this.f4675g;
        int i6 = this.f4673e;
        for (int i7 = (i5 + i6) - 1; i7 >= i6; i7--) {
            if (b4.l.a(obj, this.f4672d.get(i7))) {
                return i7 - i6;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i5) {
        a();
        b4.u uVar = new b4.u();
        uVar.f695d = i5 - 1;
        return new a0(uVar, this);
    }

    @Override // java.util.List
    public final Object remove(int i5) {
        a();
        int i6 = this.f4673e + i5;
        q qVar = this.f4672d;
        Object objRemove = qVar.remove(i6);
        this.f4675g--;
        this.f4674f = r.f(qVar);
        return objRemove;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        while (true) {
            boolean z2 = false;
            while (it.hasNext()) {
                if (remove(it.next()) || z2) {
                    z2 = true;
                }
            }
            return z2;
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        int i5;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        a();
        q qVar = this.f4672d;
        int i6 = this.f4673e;
        int i7 = this.f4675g + i6;
        int size = qVar.size();
        do {
            synchronized (r.f4731a) {
                w wVar = qVar.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i5 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.g gVarE = cVar.e();
            gVarE.subList(i6, i7).retainAll(collection);
            j0.c cVarC = gVarE.c();
            if (b4.l.a(cVarC, cVar)) {
                break;
            }
            w wVar3 = qVar.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, qVar, fVarJ), i5, cVarC, true);
            }
            m.n(fVarJ, qVar);
        } while (!zB);
        int size2 = size - qVar.size();
        if (size2 > 0) {
            this.f4674f = r.f(this.f4672d);
            this.f4675g -= size2;
        }
        return size2 > 0;
    }

    @Override // java.util.List
    public final Object set(int i5, Object obj) {
        r.a(i5, this.f4675g);
        a();
        int i6 = i5 + this.f4673e;
        q qVar = this.f4672d;
        Object obj2 = qVar.set(i6, obj);
        this.f4674f = r.f(qVar);
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f4675g;
    }

    @Override // java.util.List
    public final List subList(int i5, int i6) {
        if (!(i5 >= 0 && i5 <= i6 && i6 <= this.f4675g)) {
            x1.a("fromIndex or toIndex are out of bounds");
        }
        a();
        int i7 = this.f4673e;
        return new b0(this.f4672d, i5 + i7, i6 + i7);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return b4.k.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return b4.k.b(this, objArr);
    }
}
