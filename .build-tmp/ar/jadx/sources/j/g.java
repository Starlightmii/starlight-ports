package j;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Collection, Set, c4.b, c4.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f3103d = k.a.f3353a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f3104e = k.a.f3355c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3105f;

    public final Object a(int i5) {
        int i6 = this.f3105f;
        Object[] objArr = this.f3104e;
        Object obj = objArr[i5];
        if (i6 <= 1) {
            clear();
            return obj;
        }
        int i7 = i6 - 1;
        int[] iArr = this.f3103d;
        if (iArr.length <= 8 || i6 >= iArr.length / 3) {
            if (i5 < i7) {
                int i8 = i5 + 1;
                q3.k.y(iArr, iArr, i5, i8, i6);
                Object[] objArr2 = this.f3104e;
                q3.k.A(objArr2, objArr2, i5, i8, i6);
            }
            this.f3104e[i7] = null;
        } else {
            int i9 = i6 > 8 ? i6 + (i6 >> 1) : 8;
            int[] iArr2 = new int[i9];
            this.f3103d = iArr2;
            this.f3104e = new Object[i9];
            if (i5 > 0) {
                q3.k.B(iArr, iArr2, 0, i5, 6);
                q3.k.C(objArr, this.f3104e, 0, i5, 6);
            }
            if (i5 < i7) {
                int i10 = i5 + 1;
                q3.k.y(iArr, this.f3103d, i5, i10, i6);
                q3.k.A(objArr, this.f3104e, i5, i10, i6);
            }
        }
        if (i6 != this.f3105f) {
            throw new ConcurrentModificationException();
        }
        this.f3105f = i7;
        return obj;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i5;
        int iA;
        int i6 = this.f3105f;
        if (obj == null) {
            iA = n.a(this, null, 0);
            i5 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i5 = iHashCode;
            iA = n.a(this, obj, iHashCode);
        }
        if (iA >= 0) {
            return false;
        }
        int i7 = ~iA;
        int[] iArr = this.f3103d;
        if (i6 >= iArr.length) {
            int i8 = 8;
            if (i6 >= 8) {
                i8 = (i6 >> 1) + i6;
            } else if (i6 < 4) {
                i8 = 4;
            }
            Object[] objArr = this.f3104e;
            int[] iArr2 = new int[i8];
            this.f3103d = iArr2;
            this.f3104e = new Object[i8];
            if (i6 != this.f3105f) {
                throw new ConcurrentModificationException();
            }
            if (iArr2.length != 0) {
                q3.k.B(iArr, iArr2, 0, iArr.length, 6);
                q3.k.C(objArr, this.f3104e, 0, objArr.length, 6);
            }
        }
        if (i7 < i6) {
            int[] iArr3 = this.f3103d;
            int i9 = i7 + 1;
            q3.k.y(iArr3, iArr3, i9, i7, i6);
            Object[] objArr2 = this.f3104e;
            q3.k.A(objArr2, objArr2, i9, i7, i6);
        }
        int i10 = this.f3105f;
        if (i6 == i10) {
            int[] iArr4 = this.f3103d;
            if (i7 < iArr4.length) {
                iArr4[i7] = i5;
                this.f3104e[i7] = obj;
                this.f3105f = i10 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        b4.l.e("elements", collection);
        int size = collection.size() + this.f3105f;
        int i5 = this.f3105f;
        int[] iArr = this.f3103d;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f3104e;
            int[] iArr2 = new int[size];
            this.f3103d = iArr2;
            this.f3104e = new Object[size];
            if (i5 > 0) {
                q3.k.B(iArr, iArr2, 0, i5, 6);
                q3.k.C(objArr, this.f3104e, 0, this.f3105f, 6);
            }
        }
        if (this.f3105f != i5) {
            throw new ConcurrentModificationException();
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        if (this.f3105f != 0) {
            this.f3103d = k.a.f3353a;
            this.f3104e = k.a.f3355c;
            this.f3105f = 0;
        }
        if (this.f3105f != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? n.a(this, null, 0) : n.a(this, obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        b4.l.e("elements", collection);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set) || this.f3105f != ((Set) obj).size()) {
            return false;
        }
        try {
            int i5 = this.f3105f;
            for (int i6 = 0; i6 < i5; i6++) {
                if (!((Set) obj).contains(this.f3104e[i6])) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.f3103d;
        int i5 = this.f3105f;
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            i6 += iArr[i7];
        }
        return i6;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f3105f <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new b(this);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iA = obj == null ? n.a(this, null, 0) : n.a(this, obj, obj.hashCode());
        if (iA < 0) {
            return false;
        }
        a(iA);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        b4.l.e("elements", collection);
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        b4.l.e("elements", collection);
        boolean z2 = false;
        for (int i5 = this.f3105f - 1; -1 < i5; i5--) {
            if (!q3.l.V(collection, this.f3104e[i5])) {
                a(i5);
                z2 = true;
            }
        }
        return z2;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.f3105f;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return q3.k.D(this.f3104e, 0, this.f3105f);
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        b4.l.e("array", objArr);
        int i5 = this.f3105f;
        if (objArr.length < i5) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i5);
        } else if (objArr.length > i5) {
            objArr[i5] = null;
        }
        q3.k.A(this.f3104e, objArr, 0, 0, this.f3105f);
        return objArr;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f3105f * 14);
        sb.append('{');
        int i5 = this.f3105f;
        for (int i6 = 0; i6 < i5; i6++) {
            if (i6 > 0) {
                sb.append(", ");
            }
            Object obj = this.f3104e[i6];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
