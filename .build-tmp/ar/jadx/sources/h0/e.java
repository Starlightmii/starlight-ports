package h0;

import b4.l;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements RandomAccess {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2576d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f2577e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2578f = 0;

    public e(Object[] objArr) {
        this.f2576d = objArr;
    }

    public final void a(int i5, Object obj) {
        int i6 = this.f2578f + 1;
        if (this.f2576d.length < i6) {
            o(i6);
        }
        Object[] objArr = this.f2576d;
        int i7 = this.f2578f;
        if (i5 != i7) {
            System.arraycopy(objArr, i5, objArr, i5 + 1, i7 - i5);
        }
        objArr[i5] = obj;
        this.f2578f++;
    }

    public final void b(Object obj) {
        int i5 = this.f2578f + 1;
        if (this.f2576d.length < i5) {
            o(i5);
        }
        Object[] objArr = this.f2576d;
        int i6 = this.f2578f;
        objArr[i6] = obj;
        this.f2578f = i6 + 1;
    }

    public final void c(int i5, e eVar) {
        int i6 = eVar.f2578f;
        if (i6 == 0) {
            return;
        }
        int i7 = this.f2578f + i6;
        if (this.f2576d.length < i7) {
            o(i7);
        }
        Object[] objArr = this.f2576d;
        int i8 = this.f2578f;
        if (i5 != i8) {
            System.arraycopy(objArr, i5, objArr, i5 + i6, i8 - i5);
        }
        System.arraycopy(eVar.f2576d, 0, objArr, i5, i6);
        this.f2578f += i6;
    }

    public final void d(int i5, List list) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        int i6 = this.f2578f + size;
        if (this.f2576d.length < i6) {
            o(i6);
        }
        Object[] objArr = this.f2576d;
        int i7 = this.f2578f;
        if (i5 != i7) {
            System.arraycopy(objArr, i5, objArr, i5 + size, i7 - i5);
        }
        int size2 = list.size();
        for (int i8 = 0; i8 < size2; i8++) {
            objArr[i5 + i8] = list.get(i8);
        }
        this.f2578f += size;
    }

    public final boolean e(int i5, Collection collection) {
        int i6 = 0;
        if (collection.isEmpty()) {
            return false;
        }
        int size = collection.size();
        int i7 = this.f2578f + size;
        if (this.f2576d.length < i7) {
            o(i7);
        }
        Object[] objArr = this.f2576d;
        int i8 = this.f2578f;
        if (i5 != i8) {
            System.arraycopy(objArr, i5, objArr, i5 + size, i8 - i5);
        }
        for (Object obj : collection) {
            int i9 = i6 + 1;
            if (i6 < 0) {
                j.N();
                throw null;
            }
            objArr[i6 + i5] = obj;
            i6 = i9;
        }
        this.f2578f += size;
        return true;
    }

    public final List f() {
        b bVar = this.f2577e;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(this);
        this.f2577e = bVar2;
        return bVar2;
    }

    public final void g() {
        Object[] objArr = this.f2576d;
        int i5 = this.f2578f;
        for (int i6 = 0; i6 < i5; i6++) {
            objArr[i6] = null;
        }
        this.f2578f = 0;
    }

    public final boolean h(Object obj) {
        int i5 = this.f2578f - 1;
        if (i5 >= 0) {
            for (int i6 = 0; !l.a(this.f2576d[i6], obj); i6++) {
                if (i6 != i5) {
                }
            }
            return true;
        }
        return false;
    }

    public final int i(Object obj) {
        Object[] objArr = this.f2576d;
        int i5 = this.f2578f;
        for (int i6 = 0; i6 < i5; i6++) {
            if (l.a(obj, objArr[i6])) {
                return i6;
            }
        }
        return -1;
    }

    public final boolean j() {
        return this.f2578f == 0;
    }

    public final boolean k() {
        return this.f2578f != 0;
    }

    public final boolean l(Object obj) {
        int i5 = i(obj);
        if (i5 < 0) {
            return false;
        }
        m(i5);
        return true;
    }

    public final Object m(int i5) {
        Object[] objArr = this.f2576d;
        Object obj = objArr[i5];
        int i6 = this.f2578f;
        if (i5 != i6 - 1) {
            int i7 = i5 + 1;
            System.arraycopy(objArr, i7, objArr, i5, i6 - i7);
        }
        int i8 = this.f2578f - 1;
        this.f2578f = i8;
        objArr[i8] = null;
        return obj;
    }

    public final void n(int i5, int i6) {
        if (i6 > i5) {
            int i7 = this.f2578f;
            if (i6 < i7) {
                Object[] objArr = this.f2576d;
                System.arraycopy(objArr, i6, objArr, i5, i7 - i6);
            }
            int i8 = this.f2578f;
            int i9 = i8 - (i6 - i5);
            int i10 = i8 - 1;
            if (i9 <= i10) {
                int i11 = i9;
                while (true) {
                    this.f2576d[i11] = null;
                    if (i11 == i10) {
                        break;
                    } else {
                        i11++;
                    }
                }
            }
            this.f2578f = i9;
        }
    }

    public final void o(int i5) {
        Object[] objArr = this.f2576d;
        int length = objArr.length;
        Object[] objArr2 = new Object[Math.max(i5, length * 2)];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        this.f2576d = objArr2;
    }

    public final void p(Comparator comparator) {
        Arrays.sort(this.f2576d, 0, this.f2578f, comparator);
    }
}
