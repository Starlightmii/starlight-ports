package g0;

import f0.w0;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0.v f2456a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f2457b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2458c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2461f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2462g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2467l;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w0 f2459d = new w0();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2460e = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f2463h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2464i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2465j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2466k = -1;

    public b(f0.v vVar, a aVar) {
        this.f2456a = vVar;
        this.f2457b = aVar;
    }

    public final void a() {
        c();
        ArrayList arrayList = this.f2463h;
        if (arrayList.isEmpty()) {
            this.f2462g++;
        } else {
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public final void b() {
        int i5 = this.f2462g;
        if (i5 > 0) {
            k0 k0Var = this.f2457b.f2454k;
            k0Var.S(g0.f2479c);
            k0Var.f2489m[k0Var.f2490n - k0Var.f2487k[k0Var.f2488l - 1].f2483a] = i5;
            this.f2462g = 0;
        }
        ArrayList arrayList = this.f2463h;
        if (arrayList.isEmpty()) {
            return;
        }
        a aVar = this.f2457b;
        int size = arrayList.size();
        Object[] objArr = new Object[size];
        for (int i6 = 0; i6 < size; i6++) {
            objArr[i6] = arrayList.get(i6);
        }
        aVar.getClass();
        if (size != 0) {
            k0 k0Var2 = aVar.f2454k;
            k0Var2.S(k.f2486c);
            a.a.L(k0Var2, 0, objArr);
        }
        arrayList.clear();
    }

    public final void c() {
        int i5 = this.f2467l;
        if (i5 > 0) {
            int i6 = this.f2464i;
            if (i6 >= 0) {
                b();
                k0 k0Var = this.f2457b.f2454k;
                k0Var.S(y.f2510c);
                int i7 = k0Var.f2490n - k0Var.f2487k[k0Var.f2488l - 1].f2483a;
                int[] iArr = k0Var.f2489m;
                iArr[i7] = i6;
                iArr[i7 + 1] = i5;
                this.f2464i = -1;
            } else {
                int i8 = this.f2466k;
                int i9 = this.f2465j;
                b();
                k0 k0Var2 = this.f2457b.f2454k;
                k0Var2.S(v.f2507c);
                int i10 = k0Var2.f2490n - k0Var2.f2487k[k0Var2.f2488l - 1].f2483a;
                int[] iArr2 = k0Var2.f2489m;
                iArr2[i10 + 1] = i8;
                iArr2[i10] = i9;
                iArr2[i10 + 2] = i5;
                this.f2465j = -1;
                this.f2466k = -1;
            }
            this.f2467l = 0;
        }
    }

    public final void d(boolean z2) {
        f0.v vVar = this.f2456a;
        int i5 = z2 ? vVar.G.f2313i : vVar.G.f2311g;
        int i6 = i5 - this.f2461f;
        if (i6 < 0) {
            f0.z.a("Tried to seek backward");
        }
        if (i6 > 0) {
            k0 k0Var = this.f2457b.f2454k;
            k0Var.S(d.f2472c);
            k0Var.f2489m[k0Var.f2490n - k0Var.f2487k[k0Var.f2488l - 1].f2483a] = i6;
            this.f2461f = i5;
        }
    }

    public final void e(int i5, int i6) {
        if (i6 > 0) {
            if (!(i5 >= 0)) {
                f0.z.a("Invalid remove index " + i5);
            }
            if (this.f2464i == i5) {
                this.f2467l += i6;
                return;
            }
            c();
            this.f2464i = i5;
            this.f2467l = i6;
        }
    }
}
