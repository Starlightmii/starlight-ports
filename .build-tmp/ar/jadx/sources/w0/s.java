package w0;

import j.f0;
import j.z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6265a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f6266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6267c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f6268d;

    public s() {
        long[] jArr = f0.f3102a;
        this.f6267c = new z();
        this.f6268d = new h0.e(new a4.a[16]);
    }

    public s(boolean z2, z.n nVar, e2.l lVar) {
        this.f6266b = z2;
        this.f6267c = nVar;
        this.f6268d = lVar;
    }

    public static final void a(s sVar) {
        ((z) sVar.f6267c).a();
        int i5 = 0;
        sVar.f6266b = false;
        h0.e eVar = (h0.e) sVar.f6268d;
        int i6 = eVar.f2578f;
        if (i6 > 0) {
            Object[] objArr = eVar.f2576d;
            do {
                ((a4.a) objArr[i5]).a();
                i5++;
            } while (i5 < i6);
        }
        eVar.g();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x005e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x0060 A[LOOP:0: B:5:0x000f->B:19:0x0060, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:24:0x0063 A[SYNTHETIC] */
    public static final void b(s sVar) {
        z zVar = (z) sVar.f6267c;
        Object[] objArr = zVar.f3177b;
        long[] jArr = zVar.f3176a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128) {
                            r rVar = (r) objArr[(i5 << 3) + i7];
                            rVar.getClass();
                            q qVar = (q) ((z) d.E(rVar).f6267c).e(rVar);
                            if (qVar == null) {
                                d4.a.M("committing a node that was not updated in the current transaction");
                                throw null;
                            }
                            rVar.f6264s = qVar;
                        }
                        j5 >>= 8;
                    }
                    if (i6 == 8) {
                        if (i5 != length) {
                            i5++;
                        }
                    }
                } else if (i5 != length) {
                    i5++;
                }
            }
        }
        zVar.a();
        sVar.f6266b = false;
        ((h0.e) sVar.f6268d).g();
    }

    public int c() {
        e2.l lVar = (e2.l) this.f6268d;
        int i5 = lVar.f2033b;
        int i6 = lVar.f2034c;
        if (i5 < i6) {
            return 2;
        }
        return i5 > i6 ? 1 : 3;
    }

    public String toString() {
        String str;
        switch (this.f6265a) {
            case 1:
                StringBuilder sb = new StringBuilder("SingleSelectionLayout(isStartHandle=");
                sb.append(this.f6266b);
                sb.append(", crossed=");
                int iC = c();
                if (iC == 1) {
                    str = "CROSSED";
                } else if (iC != 2) {
                    str = iC != 3 ? "null" : "COLLAPSED";
                } else {
                    str = "NOT_CROSSED";
                }
                sb.append(str);
                sb.append(", info=\n\t");
                sb.append((e2.l) this.f6268d);
                sb.append(')');
                return sb.toString();
            default:
                return super.toString();
        }
    }
}
