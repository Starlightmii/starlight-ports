package p1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r implements List, c4.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4960g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f4957d = new Object[16];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long[] f4958e = new long[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4959f = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4961h = true;

    public final long a() {
        long jA = f.a(Float.POSITIVE_INFINITY, false);
        int i5 = this.f4959f + 1;
        int iZ = p3.j.z(this);
        if (i5 <= iZ) {
            while (true) {
                long j5 = this.f4958e[i5];
                if (f.g(j5, jA) < 0) {
                    jA = j5;
                }
                if (Float.intBitsToFloat((int) (jA >> 32)) < 0.0f && ((int) (4294967295L & jA)) != 0) {
                    return jA;
                }
                if (i5 != iZ) {
                    i5++;
                }
            }
        }
        return jA;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ void add(int i5, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i5, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void b(r0.o oVar, float f5, boolean z2, a4.a aVar) {
        int i5 = this.f4959f;
        int i6 = i5 + 1;
        this.f4959f = i6;
        Object[] objArr = this.f4957d;
        if (i6 >= objArr.length) {
            int length = objArr.length + 16;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, length);
            b4.l.d("copyOf(this, newSize)", objArrCopyOf);
            this.f4957d = objArrCopyOf;
            long[] jArrCopyOf = Arrays.copyOf(this.f4958e, length);
            b4.l.d("copyOf(this, newSize)", jArrCopyOf);
            this.f4958e = jArrCopyOf;
        }
        Object[] objArr2 = this.f4957d;
        int i7 = this.f4959f;
        objArr2[i7] = oVar;
        this.f4958e[i7] = f.a(f5, z2);
        c();
        aVar.a();
        this.f4959f = i5;
    }

    public final void c() {
        int i5 = this.f4959f + 1;
        int iZ = p3.j.z(this);
        if (i5 <= iZ) {
            while (true) {
                this.f4957d[i5] = null;
                if (i5 == iZ) {
                    break;
                } else {
                    i5++;
                }
            }
        }
        this.f4960g = this.f4959f + 1;
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.f4959f = -1;
        c();
        this.f4961h = true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return (obj instanceof r0.o) && indexOf((r0.o) obj) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains((r0.o) it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        Object obj = this.f4957d[i5];
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.Modifier.Node", obj);
        return (r0.o) obj;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof r0.o)) {
            return -1;
        }
        r0.o oVar = (r0.o) obj;
        int iZ = p3.j.z(this);
        if (iZ >= 0) {
            int i5 = 0;
            while (!b4.l.a(this.f4957d[i5], oVar)) {
                if (i5 != iZ) {
                    i5++;
                }
            }
            return i5;
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f4960g == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new p0.v(this, 0, 7);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof r0.o)) {
            return -1;
        }
        r0.o oVar = (r0.o) obj;
        for (int iZ = p3.j.z(this); -1 < iZ; iZ--) {
            if (b4.l.a(this.f4957d[iZ], oVar)) {
                return iZ;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new p0.v(this, 0, 7);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i5) {
        return new p0.v(this, i5, 6);
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i5) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i5, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f4960g;
    }

    @Override // java.util.List
    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final List subList(int i5, int i6) {
        return new q(this, i5, i6);
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
