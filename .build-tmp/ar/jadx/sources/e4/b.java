package e4;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class b implements Iterable, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2074d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2075e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2076f;

    public b(int i5, int i6, int i7) {
        if (i7 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i7 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f2074d = i5;
        if (i7 > 0) {
            if (i5 < i6) {
                int i8 = i6 % i7;
                int i9 = i5 % i7;
                int i10 = ((i8 < 0 ? i8 + i7 : i8) - (i9 < 0 ? i9 + i7 : i9)) % i7;
                i6 -= i10 < 0 ? i10 + i7 : i10;
            }
        } else {
            if (i7 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (i5 > i6) {
                int i11 = -i7;
                int i12 = i5 % i11;
                int i13 = i6 % i11;
                int i14 = ((i12 < 0 ? i12 + i11 : i12) - (i13 < 0 ? i13 + i11 : i13)) % i11;
                i6 += i14 < 0 ? i14 + i11 : i14;
            }
        }
        this.f2075e = i6;
        this.f2076f = i7;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        if (isEmpty() && ((b) obj).isEmpty()) {
            return true;
        }
        b bVar = (b) obj;
        return this.f2074d == bVar.f2074d && this.f2075e == bVar.f2075e && this.f2076f == bVar.f2076f;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f2074d * 31) + this.f2075e) * 31) + this.f2076f;
    }

    public boolean isEmpty() {
        int i5 = this.f2076f;
        int i6 = this.f2075e;
        int i7 = this.f2074d;
        if (i5 > 0) {
            return i7 > i6;
        }
        return i7 < i6;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new c(this.f2074d, this.f2075e, this.f2076f);
    }

    public String toString() {
        StringBuilder sb;
        int i5 = this.f2075e;
        int i6 = this.f2074d;
        int i7 = this.f2076f;
        if (i7 > 0) {
            sb = new StringBuilder();
            sb.append(i6);
            sb.append("..");
            sb.append(i5);
            sb.append(" step ");
            sb.append(i7);
        } else {
            sb = new StringBuilder();
            sb.append(i6);
            sb.append(" downTo ");
            sb.append(i5);
            sb.append(" step ");
            sb.append(-i7);
        }
        return sb.toString();
    }
}
