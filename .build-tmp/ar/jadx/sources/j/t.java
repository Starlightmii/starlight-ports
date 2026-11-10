package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f3149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3150b;

    public t(int i5) {
        this.f3149a = i5 == 0 ? l.f3117a : new long[i5];
    }

    public final boolean equals(Object obj) {
        if (obj instanceof t) {
            t tVar = (t) obj;
            int i5 = tVar.f3150b;
            int i6 = this.f3150b;
            if (i5 == i6) {
                long[] jArr = this.f3149a;
                long[] jArr2 = tVar.f3149a;
                e4.d dVarN = d4.a.N(0, i6);
                int i7 = dVarN.f2074d;
                int i8 = dVarN.f2075e;
                if (i7 > i8) {
                    return true;
                }
                while (jArr[i7] == jArr2[i7]) {
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
        long[] jArr = this.f3149a;
        int i5 = this.f3150b;
        int iHashCode = 0;
        for (int i6 = 0; i6 < i5; i6++) {
            iHashCode += Long.hashCode(jArr[i6]) * 31;
        }
        return iHashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        long[] jArr = this.f3149a;
        int i5 = this.f3150b;
        for (int i6 = 0; i6 < i5; i6++) {
            long j5 = jArr[i6];
            if (i6 == -1) {
                sb.append((CharSequence) "...");
                String string = sb.toString();
                b4.l.d("toString(...)", string);
                return string;
            }
            if (i6 != 0) {
                sb.append((CharSequence) ", ");
            }
            sb.append(j5);
        }
        sb.append((CharSequence) "]");
        String string2 = sb.toString();
        b4.l.d("toString(...)", string2);
        return string2;
    }
}
