package j;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class h0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f3108d = k.a.f3353a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f3109e = k.a.f3355c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3110f;

    public h0(int i5) {
    }

    public final int a(Object obj) {
        int i5 = this.f3110f * 2;
        Object[] objArr = this.f3109e;
        if (obj == null) {
            for (int i6 = 1; i6 < i5; i6 += 2) {
                if (objArr[i6] == null) {
                    return i6 >> 1;
                }
            }
            return -1;
        }
        for (int i7 = 1; i7 < i5; i7 += 2) {
            if (obj.equals(objArr[i7])) {
                return i7 >> 1;
            }
        }
        return -1;
    }

    public final int b(int i5, Object obj) {
        int i6 = this.f3110f;
        if (i6 == 0) {
            return -1;
        }
        int iA = k.a.a(this.f3108d, i6, i5);
        if (iA < 0 || b4.l.a(obj, this.f3109e[iA << 1])) {
            return iA;
        }
        int i7 = iA + 1;
        while (i7 < i6 && this.f3108d[i7] == i5) {
            if (b4.l.a(obj, this.f3109e[i7 << 1])) {
                return i7;
            }
            i7++;
        }
        for (int i8 = iA - 1; i8 >= 0 && this.f3108d[i8] == i5; i8--) {
            if (b4.l.a(obj, this.f3109e[i8 << 1])) {
                return i8;
            }
        }
        return ~i7;
    }

    public final int c(Object obj) {
        return obj == null ? d() : b(obj.hashCode(), obj);
    }

    public final void clear() {
        if (this.f3110f > 0) {
            this.f3108d = k.a.f3353a;
            this.f3109e = k.a.f3355c;
            this.f3110f = 0;
        }
        if (this.f3110f > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return c(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return a(obj) >= 0;
    }

    public final int d() {
        int i5 = this.f3110f;
        if (i5 == 0) {
            return -1;
        }
        int iA = k.a.a(this.f3108d, i5, 0);
        if (iA < 0 || this.f3109e[iA << 1] == null) {
            return iA;
        }
        int i6 = iA + 1;
        while (i6 < i5 && this.f3108d[i6] == 0) {
            if (this.f3109e[i6 << 1] == null) {
                return i6;
            }
            i6++;
        }
        for (int i7 = iA - 1; i7 >= 0 && this.f3108d[i7] == 0; i7--) {
            if (this.f3109e[i7 << 1] == null) {
                return i7;
            }
        }
        return ~i6;
    }

    public final Object e(int i5) {
        boolean z2 = false;
        if (i5 >= 0 && i5 < this.f3110f) {
            z2 = true;
        }
        if (z2) {
            return this.f3109e[i5 << 1];
        }
        k.a.c("Expected index to be within 0..size()-1, but was " + i5);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof h0) {
                int i5 = this.f3110f;
                if (i5 != ((h0) obj).f3110f) {
                    return false;
                }
                h0 h0Var = (h0) obj;
                for (int i6 = 0; i6 < i5; i6++) {
                    Object objE = e(i6);
                    Object objH = h(i6);
                    Object obj2 = h0Var.get(objE);
                    if (objH == null) {
                        if (obj2 != null || !h0Var.containsKey(objE)) {
                            return false;
                        }
                    } else if (!objH.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.f3110f != ((Map) obj).size()) {
                return false;
            }
            int i7 = this.f3110f;
            for (int i8 = 0; i8 < i7; i8++) {
                Object objE2 = e(i8);
                Object objH2 = h(i8);
                Object obj3 = ((Map) obj).get(objE2);
                if (objH2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(objE2)) {
                        return false;
                    }
                } else if (!objH2.equals(obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final Object f(int i5) {
        int i6;
        if (i5 < 0 || i5 >= (i6 = this.f3110f)) {
            k.a.c("Expected index to be within 0..size()-1, but was " + i5);
            throw null;
        }
        Object[] objArr = this.f3109e;
        int i7 = i5 << 1;
        Object obj = objArr[i7 + 1];
        if (i6 <= 1) {
            clear();
            return obj;
        }
        int i8 = i6 - 1;
        int[] iArr = this.f3108d;
        if (iArr.length <= 8 || i6 >= iArr.length / 3) {
            if (i5 < i8) {
                int i9 = i5 + 1;
                q3.k.y(iArr, iArr, i5, i9, i6);
                Object[] objArr2 = this.f3109e;
                q3.k.A(objArr2, objArr2, i7, i9 << 1, i6 << 1);
            }
            Object[] objArr3 = this.f3109e;
            int i10 = i8 << 1;
            objArr3[i10] = null;
            objArr3[i10 + 1] = null;
        } else {
            int i11 = i6 > 8 ? i6 + (i6 >> 1) : 8;
            int[] iArrCopyOf = Arrays.copyOf(iArr, i11);
            b4.l.d("copyOf(...)", iArrCopyOf);
            this.f3108d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f3109e, i11 << 1);
            b4.l.d("copyOf(...)", objArrCopyOf);
            this.f3109e = objArrCopyOf;
            if (i6 != this.f3110f) {
                throw new ConcurrentModificationException();
            }
            if (i5 > 0) {
                q3.k.y(iArr, this.f3108d, 0, 0, i5);
                q3.k.A(objArr, this.f3109e, 0, 0, i7);
            }
            if (i5 < i8) {
                int i12 = i5 + 1;
                q3.k.y(iArr, this.f3108d, i5, i12, i6);
                q3.k.A(objArr, this.f3109e, i7, i12 << 1, i6 << 1);
            }
        }
        if (i6 != this.f3110f) {
            throw new ConcurrentModificationException();
        }
        this.f3110f = i8;
        return obj;
    }

    public final Object g(int i5, Object obj) {
        boolean z2 = false;
        if (i5 >= 0 && i5 < this.f3110f) {
            z2 = true;
        }
        if (!z2) {
            k.a.c("Expected index to be within 0..size()-1, but was " + i5);
            throw null;
        }
        int i6 = (i5 << 1) + 1;
        Object[] objArr = this.f3109e;
        Object obj2 = objArr[i6];
        objArr[i6] = obj;
        return obj2;
    }

    public Object get(Object obj) {
        int iC = c(obj);
        if (iC >= 0) {
            return this.f3109e[(iC << 1) + 1];
        }
        return null;
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int iC = c(obj);
        return iC >= 0 ? this.f3109e[(iC << 1) + 1] : obj2;
    }

    public final Object h(int i5) {
        boolean z2 = false;
        if (i5 >= 0 && i5 < this.f3110f) {
            z2 = true;
        }
        if (z2) {
            return this.f3109e[(i5 << 1) + 1];
        }
        k.a.c("Expected index to be within 0..size()-1, but was " + i5);
        throw null;
    }

    public final int hashCode() {
        int[] iArr = this.f3108d;
        Object[] objArr = this.f3109e;
        int i5 = this.f3110f;
        int i6 = 1;
        int i7 = 0;
        int iHashCode = 0;
        while (i7 < i5) {
            Object obj = objArr[i6];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i7];
            i7++;
            i6 += 2;
        }
        return iHashCode;
    }

    public final boolean isEmpty() {
        return this.f3110f <= 0;
    }

    public final Object put(Object obj, Object obj2) {
        int i5 = this.f3110f;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        int iB = obj != null ? b(iHashCode, obj) : d();
        if (iB >= 0) {
            int i6 = (iB << 1) + 1;
            Object[] objArr = this.f3109e;
            Object obj3 = objArr[i6];
            objArr[i6] = obj2;
            return obj3;
        }
        int i7 = ~iB;
        int[] iArr = this.f3108d;
        if (i5 >= iArr.length) {
            int i8 = 8;
            if (i5 >= 8) {
                i8 = (i5 >> 1) + i5;
            } else if (i5 < 4) {
                i8 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i8);
            b4.l.d("copyOf(...)", iArrCopyOf);
            this.f3108d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f3109e, i8 << 1);
            b4.l.d("copyOf(...)", objArrCopyOf);
            this.f3109e = objArrCopyOf;
            if (i5 != this.f3110f) {
                throw new ConcurrentModificationException();
            }
        }
        if (i7 < i5) {
            int[] iArr2 = this.f3108d;
            int i9 = i7 + 1;
            q3.k.y(iArr2, iArr2, i9, i7, i5);
            Object[] objArr2 = this.f3109e;
            q3.k.A(objArr2, objArr2, i9 << 1, i7 << 1, this.f3110f << 1);
        }
        int i10 = this.f3110f;
        if (i5 == i10) {
            int[] iArr3 = this.f3108d;
            if (i7 < iArr3.length) {
                iArr3[i7] = iHashCode;
                Object[] objArr3 = this.f3109e;
                int i11 = i7 << 1;
                objArr3[i11] = obj;
                objArr3[i11 + 1] = obj2;
                this.f3110f = i10 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    public Object remove(Object obj) {
        int iC = c(obj);
        if (iC >= 0) {
            return f(iC);
        }
        return null;
    }

    public final boolean remove(Object obj, Object obj2) {
        int iC = c(obj);
        if (iC < 0 || !b4.l.a(obj2, h(iC))) {
            return false;
        }
        f(iC);
        return true;
    }

    public final Object replace(Object obj, Object obj2) {
        int iC = c(obj);
        if (iC >= 0) {
            return g(iC, obj2);
        }
        return null;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iC = c(obj);
        if (iC < 0 || !b4.l.a(obj2, h(iC))) {
            return false;
        }
        g(iC, obj3);
        return true;
    }

    public final int size() {
        return this.f3110f;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f3110f * 28);
        sb.append('{');
        int i5 = this.f3110f;
        for (int i6 = 0; i6 < i5; i6++) {
            if (i6 > 0) {
                sb.append(", ");
            }
            Object objE = e(i6);
            if (objE != sb) {
                sb.append(objE);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objH = h(i6);
            if (objH != sb) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
