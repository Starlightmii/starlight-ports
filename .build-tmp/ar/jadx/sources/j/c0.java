package j;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 implements c4.d, Set, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a0 f3089d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a0 f3090e;

    public c0(a0 a0Var) {
        this.f3089d = a0Var;
        this.f3090e = a0Var;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        return this.f3090e.a(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        b4.l.e("elements", collection);
        a0 a0Var = this.f3090e;
        int i5 = a0Var.f3069d;
        for (Object obj : collection) {
            a0Var.f3067b[a0Var.d(obj)] = obj;
        }
        return i5 != a0Var.f3069d;
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.f3090e.b();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f3089d.c(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        b4.l.e("elements", collection);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!this.f3089d.c(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c0.class != obj.getClass()) {
            return false;
        }
        return b4.l.a(this.f3089d, ((c0) obj).f3089d);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        return this.f3089d.hashCode();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.f3089d.g();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new g4.e(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        return this.f3090e.j(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int iNumberOfTrailingZeros;
        b4.l.e("elements", collection);
        a0 a0Var = this.f3090e;
        a0Var.getClass();
        int i5 = a0Var.f3069d;
        Iterator it = collection.iterator();
        while (true) {
            int i6 = 1;
            int i7 = 0;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            int iHashCode = (next != null ? next.hashCode() : 0) * (-862048943);
            int i8 = iHashCode ^ (iHashCode << 16);
            int i9 = i8 & 127;
            int i10 = a0Var.f3068c;
            int i11 = (i8 >>> 7) & i10;
            while (true) {
                long[] jArr = a0Var.f3066a;
                int i12 = i11 >> 3;
                int i13 = (i11 & 7) << 3;
                int i14 = i6;
                int i15 = i7;
                long j5 = (((-i13) >> 63) & (jArr[i12 + i6] << (64 - i13))) | (jArr[i12] >>> i13);
                long j6 = (((long) i9) * 72340172838076673L) ^ j5;
                long j7 = -9187201950435737472L;
                long j8 = (~j6) & (j6 - 72340172838076673L) & (-9187201950435737472L);
                while (j8 != 0) {
                    iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j8) >> 3) + i11) & i10;
                    long j9 = j7;
                    if (b4.l.a(a0Var.f3067b[iNumberOfTrailingZeros], next)) {
                        break;
                    }
                    j8 &= j8 - 1;
                    j7 = j9;
                }
                if ((j5 & ((~j5) << 6) & j7) != 0) {
                    iNumberOfTrailingZeros = -1;
                    break;
                }
                i7 = i15 + 8;
                i11 = (i11 + i7) & i10;
                i6 = i14;
            }
            if (iNumberOfTrailingZeros >= 0) {
                a0Var.k(iNumberOfTrailingZeros);
            }
        }
        return i5 != a0Var.f3069d;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        boolean z2;
        b4.l.e("elements", collection);
        a0 a0Var = this.f3090e;
        a0Var.getClass();
        Object[] objArr = a0Var.f3067b;
        int i5 = a0Var.f3069d;
        long[] jArr = a0Var.f3066a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i6 = 0;
            while (true) {
                long j5 = jArr[i6];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((255 & j5) < 128) {
                            int i9 = (i6 << 3) + i8;
                            if (!q3.l.V(collection, objArr[i9])) {
                                a0Var.k(i9);
                            }
                        }
                        j5 >>= 8;
                    }
                    z2 = false;
                    if (i7 != 8) {
                        break;
                    }
                } else {
                    z2 = false;
                }
                if (i6 == length) {
                    break;
                }
                i6++;
            }
        } else {
            z2 = false;
        }
        if (i5 != a0Var.f3069d) {
            return true;
        }
        return z2;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.f3089d.f3069d;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return b4.k.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        b4.l.e("array", objArr);
        return b4.k.b(this, objArr);
    }

    public final String toString() {
        return this.f3089d.toString();
    }
}
