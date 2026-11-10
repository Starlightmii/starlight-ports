package f0;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d2 implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2117d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2118e;

    public /* synthetic */ d2(int i5, Object obj) {
        this.f2117d = i5;
        this.f2118e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0073 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x0075 A[Catch: all -> 0x0086, LOOP:2: B:15:0x0031->B:26:0x0075, LOOP_END, TryCatch #0 {all -> 0x0086, blocks: (B:10:0x0016, B:12:0x001f, B:15:0x0031, B:17:0x0046, B:19:0x0052, B:21:0x005c, B:23:0x006a, B:28:0x007c, B:26:0x0075), top: B:68:0x0016, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x007c A[SYNTHETIC] */
    @Override // a4.a
    public final Object a() {
        i4.f fVarW;
        Object[] objArr;
        switch (this.f2117d) {
            case 0:
                l2 l2Var = (l2) this.f2118e;
                synchronized (l2Var.f2245c) {
                    fVarW = l2Var.w();
                    if (((g2) l2Var.f2263u.getValue()).compareTo(g2.f2178e) <= 0) {
                        Throwable th = l2Var.f2247e;
                        CancellationException cancellationException = new CancellationException("Recomposer shutdown; frame clock awaiter will never resume");
                        cancellationException.initCause(th);
                        throw cancellationException;
                    }
                }
                if (fVarW != null) {
                    ((i4.h) fVarW).resumeWith(p3.n.f5026a);
                }
                return p3.n.f5026a;
            case 1:
                return this.f2118e;
            case 2:
                o0.b bVar = (o0.b) this.f2118e;
                o0.f fVar = bVar.f4357d;
                Object obj = bVar.f4360g;
                if (obj != null) {
                    return fVar.f(bVar, obj);
                }
                throw new IllegalArgumentException("Value should be initialized");
            default:
                p0.t tVar = (p0.t) this.f2118e;
                do {
                    synchronized (tVar.f4751g) {
                        try {
                            if (!tVar.f4747c) {
                                tVar.f4747c = true;
                                try {
                                    h0.e eVar = tVar.f4750f;
                                    Object[] objArr2 = eVar.f2576d;
                                    int i5 = eVar.f2578f;
                                    int i6 = 0;
                                    while (i6 < i5) {
                                        p0.s sVar = (p0.s) objArr2[i6];
                                        j.a0 a0Var = sVar.f4738g;
                                        a4.c cVar = sVar.f4732a;
                                        Object[] objArr3 = a0Var.f3067b;
                                        long[] jArr = a0Var.f3066a;
                                        int length = jArr.length - 2;
                                        if (length >= 0) {
                                            int i7 = 0;
                                            while (true) {
                                                long j5 = jArr[i7];
                                                objArr = objArr2;
                                                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                    int i8 = 8;
                                                    int i9 = 8 - ((~(i7 - length)) >>> 31);
                                                    int i10 = 0;
                                                    while (i10 < i9) {
                                                        if ((j5 & 255) < 128) {
                                                            cVar.c(objArr3[(i7 << 3) + i10]);
                                                        }
                                                        j5 >>= i8;
                                                        i10++;
                                                        i8 = i8;
                                                    }
                                                    if (i9 == i8) {
                                                        if (i7 != length) {
                                                            i7++;
                                                            objArr2 = objArr;
                                                        }
                                                    }
                                                } else if (i7 != length) {
                                                    i7++;
                                                    objArr2 = objArr;
                                                }
                                            }
                                        } else {
                                            objArr = objArr2;
                                        }
                                        a0Var.b();
                                        i6++;
                                        objArr2 = objArr;
                                    }
                                    tVar.f4747c = false;
                                } catch (Throwable th2) {
                                    tVar.f4747c = false;
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            throw th3;
                        }
                    }
                } while (tVar.b());
                return p3.n.f5026a;
        }
    }
}
