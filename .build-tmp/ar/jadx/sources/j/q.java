package j;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f3136a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3137b;

    public /* synthetic */ q() {
        this(16);
    }

    public q(int i5) {
        this.f3136a = i5 == 0 ? k.f3116a : new int[i5];
    }

    public final void a(int i5) {
        b(this.f3137b + 1);
        int[] iArr = this.f3136a;
        int i6 = this.f3137b;
        iArr[i6] = i5;
        this.f3137b = i6 + 1;
    }

    public final void b(int i5) {
        int[] iArr = this.f3136a;
        if (iArr.length < i5) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, Math.max(i5, (iArr.length * 3) / 2));
            b4.l.d("copyOf(...)", iArrCopyOf);
            this.f3136a = iArrCopyOf;
        }
    }

    public final int c(int i5) {
        if (i5 >= 0 && i5 < this.f3137b) {
            return this.f3136a[i5];
        }
        k.a.d("Index must be between 0 and size");
        throw null;
    }

    public final void d(int i5, int i6) {
        if (i5 < 0 || i5 >= this.f3137b) {
            k.a.d("Index must be between 0 and size");
            throw null;
        }
        int[] iArr = this.f3136a;
        int i7 = iArr[i5];
        iArr[i5] = i6;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof q) {
            q qVar = (q) obj;
            int i5 = qVar.f3137b;
            int i6 = this.f3137b;
            if (i5 == i6) {
                int[] iArr = this.f3136a;
                int[] iArr2 = qVar.f3136a;
                e4.d dVarN = d4.a.N(0, i6);
                int i7 = dVarN.f2074d;
                int i8 = dVarN.f2075e;
                if (i7 > i8) {
                    return true;
                }
                while (iArr[i7] == iArr2[i7]) {
                    if (i7 == i8) {
                        return true;
                    }
                    i7++;
                }
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        int[] iArr = this.f3136a;
        int i5 = this.f3137b;
        int iHashCode = 0;
        for (int i6 = 0; i6 < i5; i6++) {
            iHashCode += Integer.hashCode(iArr[i6]) * 31;
        }
        return iHashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        int[] iArr = this.f3136a;
        int i5 = this.f3137b;
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = iArr[i6];
            if (i6 == -1) {
                sb.append((CharSequence) "...");
                String string = sb.toString();
                b4.l.d("toString(...)", string);
                return string;
            }
            if (i6 != 0) {
                sb.append((CharSequence) ", ");
            }
            sb.append(i7);
        }
        sb.append((CharSequence) "]");
        String string2 = sb.toString();
        b4.l.d("toString(...)", string2);
        return string2;
    }
}
