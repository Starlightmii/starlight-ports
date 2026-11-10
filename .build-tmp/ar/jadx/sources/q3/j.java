package q3;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object[] f5472g = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5473d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f5474e = f5472g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5475f;

    @Override // q3.f
    public final int a() {
        return this.f5475f;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i5, Object obj) {
        int length;
        int i6 = this.f5475f;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        if (i5 == i6) {
            addLast(obj);
            return;
        }
        if (i5 == 0) {
            addFirst(obj);
            return;
        }
        i();
        d(this.f5475f + 1);
        int iH = h(this.f5473d + i5);
        int i7 = this.f5475f;
        if (i5 < ((i7 + 1) >> 1)) {
            if (iH == 0) {
                Object[] objArr = this.f5474e;
                b4.l.e("<this>", objArr);
                iH = objArr.length;
            }
            int i8 = iH - 1;
            int i9 = this.f5473d;
            if (i9 == 0) {
                Object[] objArr2 = this.f5474e;
                b4.l.e("<this>", objArr2);
                length = objArr2.length - 1;
            } else {
                length = i9 - 1;
            }
            int i10 = this.f5473d;
            if (i8 >= i10) {
                Object[] objArr3 = this.f5474e;
                objArr3[length] = objArr3[i10];
                k.A(objArr3, objArr3, i10, i10 + 1, i8 + 1);
            } else {
                Object[] objArr4 = this.f5474e;
                k.A(objArr4, objArr4, i10 - 1, i10, objArr4.length);
                Object[] objArr5 = this.f5474e;
                objArr5[objArr5.length - 1] = objArr5[0];
                k.A(objArr5, objArr5, 0, 1, i8 + 1);
            }
            this.f5474e[i8] = obj;
            this.f5473d = length;
        } else {
            int iH2 = h(i7 + this.f5473d);
            if (iH < iH2) {
                Object[] objArr6 = this.f5474e;
                k.A(objArr6, objArr6, iH + 1, iH, iH2);
            } else {
                Object[] objArr7 = this.f5474e;
                k.A(objArr7, objArr7, 1, 0, iH2);
                Object[] objArr8 = this.f5474e;
                objArr8[0] = objArr8[objArr8.length - 1];
                k.A(objArr8, objArr8, iH + 1, iH, objArr8.length - 1);
            }
            this.f5474e[iH] = obj;
        }
        this.f5475f++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i5, Collection collection) {
        b4.l.e("elements", collection);
        int i6 = this.f5475f;
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        if (collection.isEmpty()) {
            return false;
        }
        if (i5 == this.f5475f) {
            return addAll(collection);
        }
        i();
        d(collection.size() + this.f5475f);
        int iH = h(this.f5475f + this.f5473d);
        int iH2 = h(this.f5473d + i5);
        int size = collection.size();
        if (i5 >= ((this.f5475f + 1) >> 1)) {
            int i7 = iH2 + size;
            if (iH2 < iH) {
                int i8 = size + iH;
                Object[] objArr = this.f5474e;
                if (i8 <= objArr.length) {
                    k.A(objArr, objArr, i7, iH2, iH);
                } else if (i7 >= objArr.length) {
                    k.A(objArr, objArr, i7 - objArr.length, iH2, iH);
                } else {
                    int length = iH - (i8 - objArr.length);
                    k.A(objArr, objArr, 0, length, iH);
                    Object[] objArr2 = this.f5474e;
                    k.A(objArr2, objArr2, i7, iH2, length);
                }
            } else {
                Object[] objArr3 = this.f5474e;
                k.A(objArr3, objArr3, size, 0, iH);
                Object[] objArr4 = this.f5474e;
                if (i7 >= objArr4.length) {
                    k.A(objArr4, objArr4, i7 - objArr4.length, iH2, objArr4.length);
                } else {
                    k.A(objArr4, objArr4, 0, objArr4.length - size, objArr4.length);
                    Object[] objArr5 = this.f5474e;
                    k.A(objArr5, objArr5, i7, iH2, objArr5.length - size);
                }
            }
            c(iH2, collection);
            return true;
        }
        int i9 = this.f5473d;
        int length2 = i9 - size;
        if (iH2 < i9) {
            Object[] objArr6 = this.f5474e;
            k.A(objArr6, objArr6, length2, i9, objArr6.length);
            if (size >= iH2) {
                Object[] objArr7 = this.f5474e;
                k.A(objArr7, objArr7, objArr7.length - size, 0, iH2);
            } else {
                Object[] objArr8 = this.f5474e;
                k.A(objArr8, objArr8, objArr8.length - size, 0, size);
                Object[] objArr9 = this.f5474e;
                k.A(objArr9, objArr9, 0, size, iH2);
            }
        } else if (length2 >= 0) {
            Object[] objArr10 = this.f5474e;
            k.A(objArr10, objArr10, length2, i9, iH2);
        } else {
            Object[] objArr11 = this.f5474e;
            length2 += objArr11.length;
            int i10 = iH2 - i9;
            int length3 = objArr11.length - length2;
            if (length3 >= i10) {
                k.A(objArr11, objArr11, length2, i9, iH2);
            } else {
                k.A(objArr11, objArr11, length2, i9, i9 + length3);
                Object[] objArr12 = this.f5474e;
                k.A(objArr12, objArr12, 0, this.f5473d + length3, iH2);
            }
        }
        this.f5473d = length2;
        c(f(iH2 - size), collection);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        b4.l.e("elements", collection);
        if (collection.isEmpty()) {
            return false;
        }
        i();
        d(collection.size() + a());
        c(h(a() + this.f5473d), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        i();
        d(this.f5475f + 1);
        int length = this.f5473d;
        if (length == 0) {
            Object[] objArr = this.f5474e;
            b4.l.e("<this>", objArr);
            length = objArr.length;
        }
        int i5 = length - 1;
        this.f5473d = i5;
        this.f5474e[i5] = obj;
        this.f5475f++;
    }

    public final void addLast(Object obj) {
        i();
        d(a() + 1);
        this.f5474e[h(a() + this.f5473d)] = obj;
        this.f5475f = a() + 1;
    }

    @Override // q3.f
    public final Object b(int i5) {
        int i6 = this.f5475f;
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
        if (i5 == p3.j.z(this)) {
            return removeLast();
        }
        if (i5 == 0) {
            return removeFirst();
        }
        i();
        int iH = h(this.f5473d + i5);
        Object[] objArr = this.f5474e;
        Object obj = objArr[iH];
        if (i5 < (this.f5475f >> 1)) {
            int i7 = this.f5473d;
            if (iH >= i7) {
                k.A(objArr, objArr, i7 + 1, i7, iH);
            } else {
                k.A(objArr, objArr, 1, 0, iH);
                Object[] objArr2 = this.f5474e;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i8 = this.f5473d;
                k.A(objArr2, objArr2, i8 + 1, i8, objArr2.length - 1);
            }
            Object[] objArr3 = this.f5474e;
            int i9 = this.f5473d;
            objArr3[i9] = null;
            this.f5473d = e(i9);
        } else {
            int iH2 = h(p3.j.z(this) + this.f5473d);
            if (iH <= iH2) {
                Object[] objArr4 = this.f5474e;
                k.A(objArr4, objArr4, iH, iH + 1, iH2 + 1);
            } else {
                Object[] objArr5 = this.f5474e;
                k.A(objArr5, objArr5, iH, iH + 1, objArr5.length);
                Object[] objArr6 = this.f5474e;
                objArr6[objArr6.length - 1] = objArr6[0];
                k.A(objArr6, objArr6, 0, 1, iH2 + 1);
            }
            this.f5474e[iH2] = null;
        }
        this.f5475f--;
        return obj;
    }

    public final void c(int i5, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f5474e.length;
        while (i5 < length && it.hasNext()) {
            this.f5474e[i5] = it.next();
            i5++;
        }
        int i6 = this.f5473d;
        for (int i7 = 0; i7 < i6 && it.hasNext(); i7++) {
            this.f5474e[i7] = it.next();
        }
        this.f5475f = collection.size() + this.f5475f;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            i();
            g(this.f5473d, h(a() + this.f5473d));
        }
        this.f5473d = 0;
        this.f5475f = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void d(int i5) {
        if (i5 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f5474e;
        if (i5 <= objArr.length) {
            return;
        }
        if (objArr == f5472g) {
            if (i5 < 10) {
                i5 = 10;
            }
            this.f5474e = new Object[i5];
            return;
        }
        int length = objArr.length;
        int i6 = length + (length >> 1);
        if (i6 - i5 < 0) {
            i6 = i5;
        }
        if (i6 - 2147483639 > 0) {
            i6 = i5 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i6];
        k.A(objArr, objArr2, 0, this.f5473d, objArr.length);
        Object[] objArr3 = this.f5474e;
        int length2 = objArr3.length;
        int i7 = this.f5473d;
        k.A(objArr3, objArr2, length2 - i7, 0, i7);
        this.f5473d = 0;
        this.f5474e = objArr2;
    }

    public final int e(int i5) {
        Object[] objArr = this.f5474e;
        b4.l.e("<this>", objArr);
        if (i5 == objArr.length - 1) {
            return 0;
        }
        return i5 + 1;
    }

    public final int f(int i5) {
        return i5 < 0 ? i5 + this.f5474e.length : i5;
    }

    public final void g(int i5, int i6) {
        if (i5 < i6) {
            k.E(this.f5474e, i5, i6);
            return;
        }
        Object[] objArr = this.f5474e;
        k.E(objArr, i5, objArr.length);
        k.E(this.f5474e, 0, i6);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i5) {
        int iA = a();
        if (i5 < 0 || i5 >= iA) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", iA));
        }
        return this.f5474e[h(this.f5473d + i5)];
    }

    public final int h(int i5) {
        Object[] objArr = this.f5474e;
        return i5 >= objArr.length ? i5 - objArr.length : i5;
    }

    public final void i() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i5;
        int iH = h(a() + this.f5473d);
        int length = this.f5473d;
        if (length < iH) {
            while (length < iH) {
                if (b4.l.a(obj, this.f5474e[length])) {
                    i5 = this.f5473d;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (isEmpty() || (length = this.f5473d) < iH) {
            return -1;
        }
        int length2 = this.f5474e.length;
        while (length < length2) {
            if (b4.l.a(obj, this.f5474e[length])) {
                i5 = this.f5473d;
            } else {
                length++;
            }
        }
        for (int i6 = 0; i6 < iH; i6++) {
            if (b4.l.a(obj, this.f5474e[i6])) {
                length = i6 + this.f5474e.length;
                i5 = this.f5473d;
            }
        }
        return -1;
        return length - i5;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return a() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i5;
        int iH = h(this.f5475f + this.f5473d);
        int i6 = this.f5473d;
        if (i6 < iH) {
            length = iH - 1;
            if (i6 <= length) {
                while (!b4.l.a(obj, this.f5474e[length])) {
                    if (length != i6) {
                        length--;
                    }
                }
                i5 = this.f5473d;
                return length - i5;
            }
            return -1;
        }
        if (!isEmpty() && this.f5473d >= iH) {
            for (int i7 = iH - 1; -1 < i7; i7--) {
                if (b4.l.a(obj, this.f5474e[i7])) {
                    length = i7 + this.f5474e.length;
                    i5 = this.f5473d;
                    return length - i5;
                }
            }
            Object[] objArr = this.f5474e;
            b4.l.e("<this>", objArr);
            length = objArr.length - 1;
            int i8 = this.f5473d;
            if (i8 <= length) {
                while (!b4.l.a(obj, this.f5474e[length])) {
                    if (length != i8) {
                        length--;
                    }
                }
                i5 = this.f5473d;
                return length - i5;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        b(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iH;
        b4.l.e("elements", collection);
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f5474e.length != 0) {
            int iH2 = h(this.f5475f + this.f5473d);
            int i5 = this.f5473d;
            if (i5 < iH2) {
                iH = i5;
                while (i5 < iH2) {
                    Object obj = this.f5474e[i5];
                    if (collection.contains(obj)) {
                        z2 = true;
                    } else {
                        this.f5474e[iH] = obj;
                        iH++;
                    }
                    i5++;
                }
                k.E(this.f5474e, iH, iH2);
            } else {
                int length = this.f5474e.length;
                boolean z3 = false;
                int i6 = i5;
                while (i5 < length) {
                    Object[] objArr = this.f5474e;
                    Object obj2 = objArr[i5];
                    objArr[i5] = null;
                    if (collection.contains(obj2)) {
                        z3 = true;
                    } else {
                        this.f5474e[i6] = obj2;
                        i6++;
                    }
                    i5++;
                }
                iH = h(i6);
                for (int i7 = 0; i7 < iH2; i7++) {
                    Object[] objArr2 = this.f5474e;
                    Object obj3 = objArr2[i7];
                    objArr2[i7] = null;
                    if (collection.contains(obj3)) {
                        z3 = true;
                    } else {
                        this.f5474e[iH] = obj3;
                        iH = e(iH);
                    }
                }
                z2 = z3;
            }
            if (z2) {
                i();
                this.f5475f = f(iH - this.f5473d);
            }
        }
        return z2;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        i();
        Object[] objArr = this.f5474e;
        int i5 = this.f5473d;
        Object obj = objArr[i5];
        objArr[i5] = null;
        this.f5473d = e(i5);
        this.f5475f = a() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        i();
        int iH = h(p3.j.z(this) + this.f5473d);
        Object[] objArr = this.f5474e;
        Object obj = objArr[iH];
        objArr[iH] = null;
        this.f5475f = a() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i5, int i6) {
        p3.p.i(i5, i6, this.f5475f);
        int i7 = i6 - i5;
        if (i7 == 0) {
            return;
        }
        if (i7 == this.f5475f) {
            clear();
            return;
        }
        if (i7 == 1) {
            b(i5);
            return;
        }
        i();
        if (i5 < this.f5475f - i6) {
            int iH = h(this.f5473d + (i5 - 1));
            int iH2 = h(this.f5473d + (i6 - 1));
            while (i5 > 0) {
                int i8 = iH + 1;
                int iMin = Math.min(i5, Math.min(i8, iH2 + 1));
                Object[] objArr = this.f5474e;
                int i9 = iH2 - iMin;
                int i10 = iH - iMin;
                k.A(objArr, objArr, i9 + 1, i10 + 1, i8);
                iH = f(i10);
                iH2 = f(i9);
                i5 -= iMin;
            }
            int iH3 = h(this.f5473d + i7);
            g(this.f5473d, iH3);
            this.f5473d = iH3;
        } else {
            int iH4 = h(this.f5473d + i6);
            int iH5 = h(this.f5473d + i5);
            int i11 = this.f5475f;
            while (true) {
                i11 -= i6;
                if (i11 <= 0) {
                    break;
                }
                Object[] objArr2 = this.f5474e;
                i6 = Math.min(i11, Math.min(objArr2.length - iH4, objArr2.length - iH5));
                Object[] objArr3 = this.f5474e;
                int i12 = iH4 + i6;
                k.A(objArr3, objArr3, iH5, iH4, i12);
                iH4 = h(i12);
                iH5 = h(iH5 + i6);
            }
            int iH6 = h(this.f5475f + this.f5473d);
            g(f(iH6 - i7), iH6);
        }
        this.f5475f -= i7;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iH;
        b4.l.e("elements", collection);
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f5474e.length != 0) {
            int iH2 = h(this.f5475f + this.f5473d);
            int i5 = this.f5473d;
            if (i5 < iH2) {
                iH = i5;
                while (i5 < iH2) {
                    Object obj = this.f5474e[i5];
                    if (collection.contains(obj)) {
                        this.f5474e[iH] = obj;
                        iH++;
                    } else {
                        z2 = true;
                    }
                    i5++;
                }
                k.E(this.f5474e, iH, iH2);
            } else {
                int length = this.f5474e.length;
                boolean z3 = false;
                int i6 = i5;
                while (i5 < length) {
                    Object[] objArr = this.f5474e;
                    Object obj2 = objArr[i5];
                    objArr[i5] = null;
                    if (collection.contains(obj2)) {
                        this.f5474e[i6] = obj2;
                        i6++;
                    } else {
                        z3 = true;
                    }
                    i5++;
                }
                iH = h(i6);
                for (int i7 = 0; i7 < iH2; i7++) {
                    Object[] objArr2 = this.f5474e;
                    Object obj3 = objArr2[i7];
                    objArr2[i7] = null;
                    if (collection.contains(obj3)) {
                        this.f5474e[iH] = obj3;
                        iH = e(iH);
                    } else {
                        z3 = true;
                    }
                }
                z2 = z3;
            }
            if (z2) {
                i();
                this.f5475f = f(iH - this.f5473d);
            }
        }
        return z2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i5, Object obj) {
        int iA = a();
        if (i5 < 0 || i5 >= iA) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", iA));
        }
        int iH = h(this.f5473d + i5);
        Object[] objArr = this.f5474e;
        Object obj2 = objArr[iH];
        objArr[iH] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[a()]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        b4.l.e("array", objArr);
        int length = objArr.length;
        int i5 = this.f5475f;
        if (length < i5) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i5);
            b4.l.c("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>", objNewInstance);
            objArr = (Object[]) objNewInstance;
        }
        int iH = h(this.f5475f + this.f5473d);
        int i6 = this.f5473d;
        if (i6 < iH) {
            k.C(this.f5474e, objArr, i6, iH, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f5474e;
            k.A(objArr2, objArr, 0, this.f5473d, objArr2.length);
            Object[] objArr3 = this.f5474e;
            k.A(objArr3, objArr, objArr3.length - this.f5473d, 0, iH);
        }
        int i7 = this.f5475f;
        if (i7 < objArr.length) {
            objArr[i7] = null;
        }
        return objArr;
    }
}
