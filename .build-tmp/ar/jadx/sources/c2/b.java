package c2;

import b4.l;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f1498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f1499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1500c;

    public final int a(int i5, Object obj) {
        int i6;
        int i7 = this.f1500c;
        if (i7 == 0) {
            return -1;
        }
        int[] iArr = this.f1498a;
        int i8 = i7 - 1;
        int i9 = 0;
        while (true) {
            if (i9 > i8) {
                i6 = ~i9;
                break;
            }
            i6 = (i9 + i8) >>> 1;
            int i10 = iArr[i6];
            if (i10 >= i5) {
                if (i10 <= i5) {
                    break;
                }
                i8 = i6 - 1;
            } else {
                i9 = i6 + 1;
            }
        }
        if (i6 < 0 || l.a(obj, this.f1499b[i6 << 1])) {
            return i6;
        }
        int i11 = i6 + 1;
        while (i11 < i7 && this.f1498a[i11] == i5) {
            if (l.a(obj, this.f1499b[i11 << 1])) {
                return i11;
            }
            i11++;
        }
        for (int i12 = i6 - 1; i12 >= 0 && this.f1498a[i12] == i5; i12--) {
            if (l.a(obj, this.f1499b[i12 << 1])) {
                return i12;
            }
        }
        return ~i11;
    }

    public final int b() {
        int i5;
        int i6 = this.f1500c;
        if (i6 == 0) {
            return -1;
        }
        int[] iArr = this.f1498a;
        int i7 = i6 - 1;
        int i8 = 0;
        while (true) {
            if (i8 > i7) {
                i5 = ~i8;
                break;
            }
            i5 = (i8 + i7) >>> 1;
            int i9 = iArr[i5];
            if (i9 >= 0) {
                if (i9 <= 0) {
                    break;
                }
                i7 = i5 - 1;
            } else {
                i8 = i5 + 1;
            }
        }
        if (i5 < 0 || this.f1499b[i5 << 1] == null) {
            return i5;
        }
        int i10 = i5 + 1;
        while (i10 < i6 && this.f1498a[i10] == 0) {
            if (this.f1499b[i10 << 1] == null) {
                return i10;
            }
            i10++;
        }
        for (int i11 = i5 - 1; i11 >= 0 && this.f1498a[i11] == 0; i11--) {
            if (this.f1499b[i11 << 1] == null) {
                return i11;
            }
        }
        return ~i10;
    }

    public final boolean equals(Object obj) {
        int i5;
        if (this != obj) {
            try {
                if (!(obj instanceof b)) {
                    if ((obj instanceof Map) && this.f1500c == ((Map) obj).size()) {
                        int i6 = this.f1500c;
                        for (0; i5 < i6; i5 + 1) {
                            Object[] objArr = this.f1499b;
                            int i7 = i5 << 1;
                            Object obj2 = objArr[i7];
                            Object obj3 = objArr[i7 + 1];
                            Object obj4 = ((Map) obj).get(obj2);
                            if (obj3 == null) {
                                i5 = (obj4 == null && ((Map) obj).containsKey(obj2)) ? i5 + 1 : 0;
                            } else if (obj3.equals(obj4)) {
                            }
                        }
                    }
                    return false;
                }
                b bVar = (b) obj;
                int i8 = this.f1500c;
                if (i8 == bVar.f1500c) {
                    for (int i9 = 0; i9 < i8; i9++) {
                        Object[] objArr2 = this.f1499b;
                        int i10 = i9 << 1;
                        Object obj5 = objArr2[i10];
                        Object obj6 = objArr2[i10 + 1];
                        int iB = obj5 == null ? bVar.b() : bVar.a(obj5.hashCode(), obj5);
                        Object obj7 = iB >= 0 ? bVar.f1499b[(iB << 1) + 1] : null;
                        if (obj6 == null) {
                            if (obj7 == null) {
                                if ((obj5 == null ? bVar.b() : bVar.a(obj5.hashCode(), obj5)) >= 0) {
                                }
                            }
                        } else if (obj6.equals(obj7)) {
                        }
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return true;
    }

    public final int hashCode() {
        int[] iArr = this.f1498a;
        Object[] objArr = this.f1499b;
        int i5 = this.f1500c;
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

    public final String toString() {
        int i5 = this.f1500c;
        if (i5 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i5 * 28);
        sb.append('{');
        int i6 = this.f1500c;
        for (int i7 = 0; i7 < i6; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            int i8 = i7 << 1;
            Object obj = this.f1499b[i8];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.f1499b[i8 + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
