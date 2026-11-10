package f0;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f2381a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2382b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2383c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f2384d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j.r f2385e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p3.k f2386f;

    public v1(int i5, ArrayList arrayList) {
        this.f2381a = arrayList;
        this.f2382b = i5;
        if (i5 < 0) {
            x1.a("Invalid start index");
        }
        this.f2384d = new ArrayList();
        j.r rVar = new j.r();
        int size = arrayList.size();
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            a1 a1Var = (a1) this.f2381a.get(i7);
            int i8 = a1Var.f2085c;
            int i9 = a1Var.f2086d;
            rVar.g(i8, new t0(i7, i6, i9));
            i6 += i9;
        }
        this.f2385e = rVar;
        this.f2386f = h1.c.E(new u1(this));
    }

    public final boolean a(int i5, int i6) {
        int i7;
        j.r rVar = this.f2385e;
        t0 t0Var = (t0) rVar.d(i5);
        if (t0Var == null) {
            return false;
        }
        int i8 = t0Var.f2327b;
        int i9 = i6 - t0Var.f2328c;
        t0Var.f2328c = i6;
        if (i9 == 0) {
            return true;
        }
        Object[] objArr = rVar.f3140c;
        long[] jArr = rVar.f3138a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i10 = 0;
        while (true) {
            long j5 = jArr[i10];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j5) < 128) {
                        t0 t0Var2 = (t0) objArr[(i10 << 3) + i12];
                        if (t0Var2.f2327b >= i8 && !t0Var2.equals(t0Var) && (i7 = t0Var2.f2327b + i9) >= 0) {
                            t0Var2.f2327b = i7;
                        }
                    }
                    j5 >>= 8;
                }
                if (i11 != 8) {
                    return true;
                }
            }
            if (i10 == length) {
                return true;
            }
            i10++;
        }
    }
}
