package r3;

import b4.l;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import k0.h;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Map, Serializable, c4.c {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f f5724q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f5725d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f5726e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f5727f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f5728g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5729h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5730i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5731j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f5732k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5733l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g f5734m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public h f5735n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public g f5736o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5737p;

    static {
        f fVar = new f(0);
        fVar.f5737p = true;
        f5724q = fVar;
    }

    public f(int i5) {
        if (i5 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i5];
        int[] iArr = new int[i5];
        int iHighestOneBit = Integer.highestOneBit((i5 < 1 ? 1 : i5) * 3);
        this.f5725d = objArr;
        this.f5726e = null;
        this.f5727f = iArr;
        this.f5728g = new int[iHighestOneBit];
        this.f5729h = 2;
        this.f5730i = 0;
        this.f5731j = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }

    public final int a(Object obj) {
        b();
        while (true) {
            int i5 = i(obj);
            int i6 = this.f5729h * 2;
            int length = this.f5728g.length / 2;
            if (i6 > length) {
                i6 = length;
            }
            int i7 = 0;
            while (true) {
                int[] iArr = this.f5728g;
                int i8 = iArr[i5];
                if (i8 == 0) {
                    int i9 = this.f5730i;
                    Object[] objArr = this.f5725d;
                    if (i9 >= objArr.length) {
                        f(1);
                        break;
                    }
                    int i10 = i9 + 1;
                    this.f5730i = i10;
                    objArr[i9] = obj;
                    this.f5727f[i9] = i5;
                    iArr[i5] = i10;
                    this.f5733l++;
                    this.f5732k++;
                    if (i7 > this.f5729h) {
                        this.f5729h = i7;
                    }
                    return i9;
                }
                if (l.a(this.f5725d[i8 - 1], obj)) {
                    return -i8;
                }
                i7++;
                if (i7 > i6) {
                    j(this.f5728g.length * 2);
                    break;
                }
                i5 = i5 == 0 ? this.f5728g.length - 1 : i5 - 1;
            }
        }
    }

    public final void b() {
        if (this.f5737p) {
            throw new UnsupportedOperationException();
        }
    }

    public final void c(boolean z2) {
        int i5;
        Object[] objArr = this.f5726e;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i5 = this.f5730i;
            if (i6 >= i5) {
                break;
            }
            int[] iArr = this.f5727f;
            int i8 = iArr[i6];
            if (i8 >= 0) {
                Object[] objArr2 = this.f5725d;
                objArr2[i7] = objArr2[i6];
                if (objArr != null) {
                    objArr[i7] = objArr[i6];
                }
                if (z2) {
                    iArr[i7] = i8;
                    this.f5728g[i8] = i7 + 1;
                }
                i7++;
            }
            i6++;
        }
        q.f.r(this.f5725d, i7, i5);
        if (objArr != null) {
            q.f.r(objArr, i7, this.f5730i);
        }
        this.f5730i = i7;
    }

    @Override // java.util.Map
    public final void clear() {
        b();
        int i5 = this.f5730i - 1;
        if (i5 >= 0) {
            int i6 = 0;
            while (true) {
                int[] iArr = this.f5727f;
                int i7 = iArr[i6];
                if (i7 >= 0) {
                    this.f5728g[i7] = 0;
                    iArr[i6] = -1;
                }
                if (i6 == i5) {
                    break;
                } else {
                    i6++;
                }
            }
        }
        q.f.r(this.f5725d, 0, this.f5730i);
        Object[] objArr = this.f5726e;
        if (objArr != null) {
            q.f.r(objArr, 0, this.f5730i);
        }
        this.f5733l = 0;
        this.f5730i = 0;
        this.f5732k++;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return g(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    public final boolean d(Collection collection) {
        l.e("m", collection);
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!e((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean e(Map.Entry entry) {
        l.e("entry", entry);
        int iG = g(entry.getKey());
        if (iG < 0) {
            return false;
        }
        Object[] objArr = this.f5726e;
        l.b(objArr);
        return l.a(objArr[iG], entry.getValue());
    }

    @Override // java.util.Map
    public final Set entrySet() {
        g gVar = this.f5736o;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this, 0);
        this.f5736o = gVar2;
        return gVar2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        return this.f5733l == map.size() && d(map.entrySet());
    }

    public final void f(int i5) {
        Object[] objArrCopyOf;
        Object[] objArr = this.f5725d;
        int length = objArr.length;
        int i6 = this.f5730i;
        int i7 = length - i6;
        int i8 = i6 - this.f5733l;
        if (i7 < i5 && i7 + i8 >= i5 && i8 >= objArr.length / 4) {
            c(true);
            return;
        }
        int i9 = i6 + i5;
        if (i9 < 0) {
            throw new OutOfMemoryError();
        }
        if (i9 > objArr.length) {
            int length2 = objArr.length;
            int i10 = length2 + (length2 >> 1);
            if (i10 - i9 < 0) {
                i10 = i9;
            }
            if (i10 - 2147483639 > 0) {
                i10 = i9 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i10);
            l.d("copyOf(...)", objArrCopyOf2);
            this.f5725d = objArrCopyOf2;
            Object[] objArr2 = this.f5726e;
            if (objArr2 != null) {
                objArrCopyOf = Arrays.copyOf(objArr2, i10);
                l.d("copyOf(...)", objArrCopyOf);
            } else {
                objArrCopyOf = null;
            }
            this.f5726e = objArrCopyOf;
            int[] iArrCopyOf = Arrays.copyOf(this.f5727f, i10);
            l.d("copyOf(...)", iArrCopyOf);
            this.f5727f = iArrCopyOf;
            int iHighestOneBit = Integer.highestOneBit((i10 >= 1 ? i10 : 1) * 3);
            if (iHighestOneBit > this.f5728g.length) {
                j(iHighestOneBit);
            }
        }
    }

    public final int g(Object obj) {
        int i5 = i(obj);
        int i6 = this.f5729h;
        while (true) {
            int i7 = this.f5728g[i5];
            if (i7 == 0) {
                return -1;
            }
            int i8 = i7 - 1;
            if (l.a(this.f5725d[i8], obj)) {
                return i8;
            }
            i6--;
            if (i6 < 0) {
                return -1;
            }
            i5 = i5 == 0 ? this.f5728g.length - 1 : i5 - 1;
        }
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int iG = g(obj);
        if (iG < 0) {
            return null;
        }
        Object[] objArr = this.f5726e;
        l.b(objArr);
        return objArr[iG];
    }

    public final int h(Object obj) {
        int i5 = this.f5730i;
        while (true) {
            i5--;
            if (i5 < 0) {
                return -1;
            }
            if (this.f5727f[i5] >= 0) {
                Object[] objArr = this.f5726e;
                l.b(objArr);
                if (l.a(objArr[i5], obj)) {
                    return i5;
                }
            }
        }
    }

    @Override // java.util.Map
    public final int hashCode() {
        d dVar = new d(this, 0);
        int i5 = 0;
        while (dVar.hasNext()) {
            int i6 = dVar.f1501d;
            f fVar = (f) dVar.f1504g;
            if (i6 >= fVar.f5730i) {
                throw new NoSuchElementException();
            }
            dVar.f1501d = i6 + 1;
            dVar.f1502e = i6;
            Object obj = fVar.f5725d[i6];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = fVar.f5726e;
            l.b(objArr);
            Object obj2 = objArr[dVar.f1502e];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            dVar.c();
            i5 += iHashCode ^ iHashCode2;
        }
        return i5;
    }

    public final int i(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.f5731j;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f5733l == 0;
    }

    public final void j(int i5) {
        int[] iArr;
        this.f5732k++;
        int i6 = 0;
        if (this.f5730i > this.f5733l) {
            c(false);
        }
        this.f5728g = new int[i5];
        this.f5731j = Integer.numberOfLeadingZeros(i5) + 1;
        while (i6 < this.f5730i) {
            int i7 = i6 + 1;
            int i8 = i(this.f5725d[i6]);
            int i9 = this.f5729h;
            while (true) {
                iArr = this.f5728g;
                if (iArr[i8] == 0) {
                    break;
                }
                i9--;
                if (i9 < 0) {
                    throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
                }
                i8 = i8 == 0 ? iArr.length - 1 : i8 - 1;
            }
            iArr[i8] = i7;
            this.f5727f[i6] = i8;
            i6 = i7;
        }
    }

    public final void k(int i5) {
        int i6;
        int i7;
        int i8;
        int[] iArr;
        Object[] objArr = this.f5725d;
        l.e("<this>", objArr);
        objArr[i5] = null;
        Object[] objArr2 = this.f5726e;
        if (objArr2 != null) {
            objArr2[i5] = null;
        }
        int length = this.f5727f[i5];
        loop0: while (true) {
            int i9 = length;
            int i10 = 0;
            do {
                length = length == 0 ? this.f5728g.length - 1 : length - 1;
                int[] iArr2 = this.f5728g;
                i6 = iArr2[length];
                i10++;
                if (i10 > this.f5729h) {
                    iArr2[i9] = 0;
                    break loop0;
                } else if (i6 == 0) {
                    iArr2[i9] = 0;
                    break loop0;
                } else {
                    i7 = i6 - 1;
                    i8 = i(this.f5725d[i7]) - length;
                    iArr = this.f5728g;
                }
            } while ((i8 & (iArr.length - 1)) < i10);
            iArr[i9] = i6;
            this.f5727f[i7] = i9;
        }
        this.f5727f[i5] = -1;
        this.f5733l--;
        this.f5732k++;
    }

    @Override // java.util.Map
    public final Set keySet() {
        g gVar = this.f5734m;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this, 1);
        this.f5734m = gVar2;
        return gVar2;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        b();
        int iA = a(obj);
        Object[] objArr = this.f5726e;
        if (objArr == null) {
            int length = this.f5725d.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            this.f5726e = objArr;
        }
        if (iA >= 0) {
            objArr[iA] = obj2;
            return null;
        }
        int i5 = (-iA) - 1;
        Object obj3 = objArr[i5];
        objArr[i5] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        l.e("from", map);
        b();
        Set<Map.Entry> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        f(setEntrySet.size());
        for (Map.Entry entry : setEntrySet) {
            int iA = a(entry.getKey());
            Object[] objArr = this.f5726e;
            if (objArr == null) {
                int length = this.f5725d.length;
                if (length < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                objArr = new Object[length];
                this.f5726e = objArr;
            }
            if (iA >= 0) {
                objArr[iA] = entry.getValue();
            } else {
                int i5 = (-iA) - 1;
                if (!l.a(entry.getValue(), objArr[i5])) {
                    objArr[i5] = entry.getValue();
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        b();
        int iG = g(obj);
        if (iG < 0) {
            return null;
        }
        Object[] objArr = this.f5726e;
        l.b(objArr);
        Object obj2 = objArr[iG];
        k(iG);
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f5733l;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f5733l * 3) + 2);
        sb.append("{");
        int i5 = 0;
        d dVar = new d(this, 0);
        while (dVar.hasNext()) {
            if (i5 > 0) {
                sb.append(", ");
            }
            int i6 = dVar.f1501d;
            f fVar = (f) dVar.f1504g;
            if (i6 >= fVar.f5730i) {
                throw new NoSuchElementException();
            }
            dVar.f1501d = i6 + 1;
            dVar.f1502e = i6;
            Object obj = fVar.f5725d[i6];
            if (obj == fVar) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = fVar.f5726e;
            l.b(objArr);
            Object obj2 = objArr[dVar.f1502e];
            if (obj2 == fVar) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            dVar.c();
            i5++;
        }
        sb.append("}");
        String string = sb.toString();
        l.d("toString(...)", string);
        return string;
    }

    @Override // java.util.Map
    public final Collection values() {
        h hVar = this.f5735n;
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h(1, this);
        this.f5735n = hVar2;
        return hVar2;
    }
}
