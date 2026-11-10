package l4;

import i4.b1;
import i4.h1;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b3.r f3586a = new b3.r("NO_VALUE", 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b3.r f3587b = new b3.r("NONE", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b3.r f3588c = new b3.r("PENDING", 1);

    public static t a(int i5, k4.a aVar) {
        int i6 = (i5 & 1) != 0 ? 0 : 1;
        int i7 = (i5 & 2) == 0 ? 16 : 0;
        if (i6 <= 0 && i7 <= 0 && aVar != k4.a.f3435d) {
            throw new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + aVar).toString());
        }
        int i8 = i7 + i6;
        if (i8 < 0) {
            i8 = Integer.MAX_VALUE;
        }
        return new t(i6, i8, aVar);
    }

    public static final void b(Object[] objArr, long j5, Object obj) {
        objArr[((int) j5) & (objArr.length - 1)] = obj;
    }

    public static final d c(d dVar) {
        return ((dVar instanceof b0) || (dVar instanceof c)) ? dVar : new c(dVar);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0062  */
    /* JADX WARN: Code duplicated, block: B:27:0x0063  */
    /* JADX WARN: Code duplicated, block: B:30:0x006f A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #0 {all -> 0x0035, blocks: (B:13:0x002f, B:24:0x0052, B:28:0x0067, B:30:0x006f, B:20:0x0047, B:23:0x004e), top: B:47:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0084 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0086  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0081, code lost:
    
        if (r1.d(r10, r0) == r5) goto L32;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [k4.o] */
    /* JADX WARN: Type inference failed for: r8v1, types: [k4.q] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v2, types: [k4.q] */
    /* JADX WARN: Type inference failed for: r8v3, types: [k4.q] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0081 -> B:14:0x0032). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object d(l4.e r7, k4.o r8, boolean r9, v3.c r10) {
        /*
            boolean r0 = r10 instanceof l4.f
            if (r0 == 0) goto L13
            r0 = r10
            l4.f r0 = (l4.f) r0
            int r1 = r0.f3534i
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f3534i = r1
            goto L18
        L13:
            l4.f r0 = new l4.f
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.f3533h
            int r1 = r0.f3534i
            r2 = 0
            r3 = 2
            r4 = 1
            u3.a r5 = u3.a.f5838d
            if (r1 == 0) goto L4b
            if (r1 == r4) goto L3f
            if (r1 != r3) goto L37
            boolean r9 = r0.f3532g
            k4.b r7 = r0.f3531f
            k4.q r8 = r0.f3530e
            l4.e r1 = r0.f3529d
            p3.j.O(r10)     // Catch: java.lang.Throwable -> L35
        L32:
            r10 = r7
            r7 = r1
            goto L52
        L35:
            r7 = move-exception
            goto L8c
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            boolean r9 = r0.f3532g
            k4.b r7 = r0.f3531f
            k4.q r8 = r0.f3530e
            l4.e r1 = r0.f3529d
            p3.j.O(r10)     // Catch: java.lang.Throwable -> L35
            goto L67
        L4b:
            p3.j.O(r10)
            k4.b r10 = r8.iterator()     // Catch: java.lang.Throwable -> L35
        L52:
            r0.f3529d = r7     // Catch: java.lang.Throwable -> L35
            r0.f3530e = r8     // Catch: java.lang.Throwable -> L35
            r0.f3531f = r10     // Catch: java.lang.Throwable -> L35
            r0.f3532g = r9     // Catch: java.lang.Throwable -> L35
            r0.f3534i = r4     // Catch: java.lang.Throwable -> L35
            java.lang.Object r1 = r10.b(r0)     // Catch: java.lang.Throwable -> L35
            if (r1 != r5) goto L63
            goto L83
        L63:
            r6 = r1
            r1 = r7
            r7 = r10
            r10 = r6
        L67:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L35
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r10 == 0) goto L84
            java.lang.Object r10 = r7.c()     // Catch: java.lang.Throwable -> L35
            r0.f3529d = r1     // Catch: java.lang.Throwable -> L35
            r0.f3530e = r8     // Catch: java.lang.Throwable -> L35
            r0.f3531f = r7     // Catch: java.lang.Throwable -> L35
            r0.f3532g = r9     // Catch: java.lang.Throwable -> L35
            r0.f3534i = r3     // Catch: java.lang.Throwable -> L35
            java.lang.Object r10 = r1.d(r10, r0)     // Catch: java.lang.Throwable -> L35
            if (r10 != r5) goto L32
        L83:
            return r5
        L84:
            if (r9 == 0) goto L89
            r8.b(r2)
        L89:
            p3.n r7 = p3.n.f5026a
            return r7
        L8c:
            throw r7     // Catch: java.lang.Throwable -> L8d
        L8d:
            r10 = move-exception
            if (r9 == 0) goto La6
            boolean r9 = r7 instanceof java.util.concurrent.CancellationException
            if (r9 == 0) goto L97
            r2 = r7
            java.util.concurrent.CancellationException r2 = (java.util.concurrent.CancellationException) r2
        L97:
            if (r2 != 0) goto La3
            java.util.concurrent.CancellationException r2 = new java.util.concurrent.CancellationException
            java.lang.String r9 = "Channel was consumed, consumer had failed"
            r2.<init>(r9)
            r2.initCause(r7)
        La3:
            r8.b(r2)
        La6:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: l4.u.d(l4.e, k4.o, boolean, v3.c):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0082  */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v9, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r5v0, types: [l4.d] */
    /* JADX WARN: Type inference failed for: r6v0, types: [a4.e, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.StringBuilder] */
    public static final Object e(d dVar, a4.e eVar, v3.c cVar) {
        l lVar;
        ?? r1;
        b4.w wVar;
        m4.a e5;
        b0.h hVar;
        ?? r4;
        b3.r rVar = m4.c.f3818b;
        if (cVar instanceof l) {
            lVar = (l) cVar;
            int i5 = lVar.f3558h;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                lVar.f3558h = i5 - Integer.MIN_VALUE;
            } else {
                lVar = new l(cVar);
            }
        } else {
            lVar = new l(cVar);
        }
        Object obj = lVar.f3557g;
        int i6 = lVar.f3558h;
        if (i6 == 0) {
            p3.j.O(obj);
            b4.w wVar2 = new b4.w();
            wVar2.f697d = rVar;
            b0.h hVar2 = new b0.h((Object) eVar, 5, wVar2);
            try {
                lVar.f3554d = (v3.i) eVar;
                lVar.f3555e = wVar2;
                lVar.f3556f = hVar2;
                lVar.f3558h = 1;
                Object objG = dVar.g(hVar2, lVar);
                u3.a aVar = u3.a.f5838d;
                if (objG == aVar) {
                    return aVar;
                }
                r4 = eVar;
                wVar = wVar2;
            } catch (m4.a e6) {
                r1 = eVar;
                wVar = wVar2;
                e5 = e6;
                hVar = hVar2;
                r4 = r1;
                if (e5.f3812d != hVar) {
                    throw e5;
                }
            }
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            hVar = lVar.f3556f;
            wVar = lVar.f3555e;
            r1 = (a4.e) lVar.f3554d;
            try {
                p3.j.O(obj);
                r4 = r1;
            } catch (m4.a e7) {
                e5 = e7;
                r4 = r1;
                if (e5.f3812d != hVar) {
                    throw e5;
                }
            }
        }
        Object obj2 = wVar.f697d;
        if (obj2 != rVar) {
            return obj2;
        }
        throw new NoSuchElementException("Expected at least one element matching the predicate " + r4);
    }

    public static final q f(a1.d dVar, n4.c cVar, a0 a0Var, Float f5) {
        k4.g.f3474b.getClass();
        k4.f fVar = k4.f.f3472a;
        a2.h hVar = new a2.h(dVar, 14, t3.i.f5807d);
        d0 d0Var = new d0(f5);
        t3.h hVar2 = (t3.h) hVar.f50f;
        d dVar2 = (d) hVar.f49e;
        i4.w wVar = a0Var.equals(x.f3595a) ? i4.w.f2779d : i4.w.f2782g;
        n nVar = new n(a0Var, dVar2, d0Var, f5, null, 1);
        t3.h hVarF = i4.y.f(cVar.h(), hVar2, true);
        p4.e eVar = i4.e0.f2727a;
        if (hVarF != eVar && hVarF.j(t3.d.f5806d) == null) {
            hVarF = hVarF.k(eVar);
        }
        i4.a b1Var = wVar == i4.w.f2780e ? new b1(hVarF, nVar) : new h1(hVarF, true);
        b1Var.c0(wVar, b1Var, nVar);
        return new q(d0Var);
    }
}
