package k0;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f3378b;

    public l(m mVar, int i5) {
        this.f3378b = mVar;
        this.f3377a = i5;
    }

    public void a(long j5) {
        if (b(j5)) {
            return;
        }
        int i5 = this.f3377a;
        long[] jArr = (long[]) this.f3378b;
        if (i5 >= jArr.length) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, Math.max(i5 + 1, jArr.length * 2));
            b4.l.d("copyOf(this, newSize)", jArrCopyOf);
            this.f3378b = jArrCopyOf;
        }
        ((long[]) this.f3378b)[i5] = j5;
        if (i5 >= this.f3377a) {
            this.f3377a = i5 + 1;
        }
    }

    public boolean b(long j5) {
        int i5 = this.f3377a;
        for (int i6 = 0; i6 < i5; i6++) {
            if (((long[]) this.f3378b)[i6] == j5) {
                return true;
            }
        }
        return false;
    }

    public void c(int i5) {
        int i6 = this.f3377a;
        if (i5 < i6) {
            int i7 = i6 - 1;
            while (i5 < i7) {
                long[] jArr = (long[]) this.f3378b;
                int i8 = i5 + 1;
                jArr[i5] = jArr[i8];
                i5 = i8;
            }
            this.f3377a--;
        }
    }
}
