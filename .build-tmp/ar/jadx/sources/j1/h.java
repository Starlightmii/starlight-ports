package j1;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h0.e f3256a;

    public h(int i5) {
        switch (i5) {
            case 1:
                this.f3256a = new h0.e(new p.h[16]);
                break;
            default:
                this.f3256a = new h0.e(new g[16]);
                break;
        }
    }

    public boolean a(j.m mVar, n1.p pVar, a2.h hVar, boolean z2) {
        h0.e eVar = this.f3256a;
        int i5 = eVar.f2578f;
        if (i5 <= 0) {
            return false;
        }
        Object[] objArr = eVar.f2576d;
        int i6 = 0;
        boolean z3 = false;
        do {
            z3 = ((g) objArr[i6]).a(mVar, pVar, hVar, z2) || z3;
            i6++;
        } while (i6 < i5);
        return z3;
    }

    public void b(CancellationException cancellationException) {
        h0.e eVar = this.f3256a;
        int i5 = eVar.f2578f;
        i4.f[] fVarArr = new i4.f[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            fVarArr[i6] = ((p.h) eVar.f2576d[i6]).f4468b;
        }
        for (int i7 = 0; i7 < i5; i7++) {
            fVarArr[i7].e(cancellationException);
        }
        if (!eVar.j()) {
            throw new IllegalStateException("uncancelled requests present");
        }
    }

    public void c(a2.h hVar) {
        h0.e eVar = this.f3256a;
        int i5 = eVar.f2578f;
        while (true) {
            i5--;
            if (-1 >= i5) {
                return;
            }
            if (((g) eVar.f2576d[i5]).f3249c.f3377a == 0) {
                eVar.m(i5);
            }
        }
    }

    public void d() {
        int i5 = 0;
        while (true) {
            h0.e eVar = this.f3256a;
            if (i5 >= eVar.f2578f) {
                return;
            }
            g gVar = (g) eVar.f2576d[i5];
            if (gVar.f3248b.f5693p) {
                i5++;
                gVar.d();
            } else {
                gVar.f();
                eVar.m(i5);
            }
        }
    }

    public void e() {
        h0.e eVar = this.f3256a;
        int i5 = 0;
        int i6 = new e4.d(0, eVar.f2578f - 1, 1).f2075e;
        if (i6 >= 0) {
            while (true) {
                ((p.h) eVar.f2576d[i5]).f4468b.resumeWith(p3.n.f5026a);
                if (i5 == i6) {
                    break;
                } else {
                    i5++;
                }
            }
        }
        eVar.g();
    }
}
