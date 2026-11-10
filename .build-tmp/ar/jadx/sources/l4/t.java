package l4;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class t extends m4.b implements o, d, m4.m {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3578h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3579i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final k4.a f3580j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Object[] f3581k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f3582l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f3583m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3584n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3585o;

    public t(int i5, int i6, k4.a aVar) {
        this.f3578h = i5;
        this.f3579i = i6;
        this.f3580j = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0081 A[Catch: all -> 0x0036, TRY_ENTER, TryCatch #1 {all -> 0x0036, blocks: (B:15:0x002f, B:32:0x0077, B:35:0x0081, B:39:0x0094, B:42:0x009b, B:43:0x009f, B:44:0x00a0, B:22:0x0049), top: B:53:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0094 A[Catch: all -> 0x0036, TryCatch #1 {all -> 0x0036, blocks: (B:15:0x002f, B:32:0x0077, B:35:0x0081, B:39:0x0094, B:42:0x009b, B:43:0x009f, B:44:0x00a0, B:22:0x0049), top: B:53:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v4, types: [l4.e] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r4v1, types: [m4.b] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4, types: [l4.t] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r9v0, types: [l4.e] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [m4.d] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [l4.v] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [l4.v] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00ae -> B:16:0x0032). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:35:0x0081
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static void k(l4.t r8, l4.e r9, t3.c r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof l4.s
            if (r0 == 0) goto L13
            r0 = r10
            l4.s r0 = (l4.s) r0
            int r1 = r0.f3577j
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f3577j = r1
            goto L18
        L13:
            l4.s r0 = new l4.s
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.f3575h
            int r1 = r0.f3577j
            r2 = 3
            r3 = 2
            if (r1 == 0) goto L5c
            r8 = 1
            if (r1 == r8) goto L4d
            if (r1 == r3) goto L41
            if (r1 != r2) goto L39
            i4.s0 r8 = r0.f3574g
            l4.v r9 = r0.f3573f
            l4.e r1 = r0.f3572e
            l4.t r4 = r0.f3571d
            p3.j.O(r10)     // Catch: java.lang.Throwable -> L36
        L32:
            r10 = r1
            r1 = r8
            r8 = r4
            goto L74
        L36:
            r8 = move-exception
            goto Lb4
        L39:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L41:
            i4.s0 r8 = r0.f3574g
            l4.v r9 = r0.f3573f
            l4.e r1 = r0.f3572e
            l4.t r4 = r0.f3571d
            p3.j.O(r10)     // Catch: java.lang.Throwable -> L36
            goto L77
        L4d:
            l4.v r9 = r0.f3573f
            l4.e r8 = r0.f3572e
            l4.t r1 = r0.f3571d
            p3.j.O(r10)     // Catch: java.lang.Throwable -> L59
            r10 = r8
            r8 = r1
            goto L68
        L59:
            r8 = move-exception
            r4 = r1
            goto Lb4
        L5c:
            p3.j.O(r10)
            m4.d r10 = r8.a()
            l4.v r10 = (l4.v) r10
            r7 = r10
            r10 = r9
            r9 = r7
        L68:
            t3.h r1 = r0.getContext()     // Catch: java.lang.Throwable -> Lb1
            i4.s r4 = i4.s.f2774e     // Catch: java.lang.Throwable -> Lb1
            t3.f r1 = r1.j(r4)     // Catch: java.lang.Throwable -> Lb1
            i4.s0 r1 = (i4.s0) r1     // Catch: java.lang.Throwable -> Lb1
        L74:
            r4 = r8
            r8 = r1
            r1 = r10
        L77:
            java.lang.Object r10 = r4.t(r9)     // Catch: java.lang.Throwable -> L36
            b3.r r5 = l4.u.f3586a     // Catch: java.lang.Throwable -> L36
            u3.a r6 = u3.a.f5838d
            if (r10 != r5) goto L92
            r0.f3571d = r4     // Catch: java.lang.Throwable -> L36
            r0.f3572e = r1     // Catch: java.lang.Throwable -> L36
            r0.f3573f = r9     // Catch: java.lang.Throwable -> L36
            r0.f3574g = r8     // Catch: java.lang.Throwable -> L36
            r0.f3577j = r3     // Catch: java.lang.Throwable -> L36
            java.lang.Object r10 = r4.i(r9, r0)     // Catch: java.lang.Throwable -> L36
            if (r10 != r6) goto L77
            goto Lb0
        L92:
            if (r8 == 0) goto La0
            boolean r5 = r8.a()     // Catch: java.lang.Throwable -> L36
            if (r5 == 0) goto L9b
            goto La0
        L9b:
            java.util.concurrent.CancellationException r8 = r8.m()     // Catch: java.lang.Throwable -> L36
            throw r8     // Catch: java.lang.Throwable -> L36
        La0:
            r0.f3571d = r4     // Catch: java.lang.Throwable -> L36
            r0.f3572e = r1     // Catch: java.lang.Throwable -> L36
            r0.f3573f = r9     // Catch: java.lang.Throwable -> L36
            r0.f3574g = r8     // Catch: java.lang.Throwable -> L36
            r0.f3577j = r2     // Catch: java.lang.Throwable -> L36
            java.lang.Object r10 = r1.d(r10, r0)     // Catch: java.lang.Throwable -> L36
            if (r10 != r6) goto L32
        Lb0:
            return
        Lb1:
            r10 = move-exception
            r4 = r8
            r8 = r10
        Lb4:
            r4.f(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: l4.t.k(l4.t, l4.e, t3.c):void");
    }

    @Override // m4.b
    public final m4.d b() {
        v vVar = new v();
        vVar.f3589a = -1L;
        return vVar;
    }

    @Override // m4.m
    public final d c(t3.h hVar, int i5, k4.a aVar) {
        return ((i5 == 0 || i5 == -3) && aVar == k4.a.f3435d) ? this : new m4.g(this, hVar, i5, aVar);
    }

    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) throws Throwable {
        Throwable th;
        t3.c[] cVarArrN;
        r rVar;
        if (q(obj)) {
            return p3.n.f5026a;
        }
        i4.h hVar = new i4.h(1, p3.j.B(cVar));
        hVar.p();
        t3.c[] cVarArrN2 = m4.c.f3817a;
        synchronized (this) {
            try {
                if (r(obj)) {
                    try {
                        hVar.resumeWith(p3.n.f5026a);
                        cVarArrN = n(cVarArrN2);
                        rVar = null;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else {
                    try {
                        r rVar2 = new r(this, o() + ((long) (this.f3584n + this.f3585o)), obj, hVar);
                        m(rVar2);
                        this.f3585o++;
                        if (this.f3579i == 0) {
                            cVarArrN2 = n(cVarArrN2);
                        }
                        cVarArrN = cVarArrN2;
                        rVar = rVar2;
                    } catch (Throwable th3) {
                        th = th3;
                        th = th;
                        throw th;
                    }
                }
                if (rVar != null) {
                    hVar.t(new i4.e(1, rVar));
                }
                for (t3.c cVar2 : cVarArrN) {
                    if (cVar2 != null) {
                        cVar2.resumeWith(p3.n.f5026a);
                    }
                }
                Object objO = hVar.o();
                u3.a aVar = u3.a.f5838d;
                if (objO != aVar) {
                    objO = p3.n.f5026a;
                }
                return objO == aVar ? objO : p3.n.f5026a;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    @Override // m4.b
    public final m4.d[] e() {
        return new v[2];
    }

    @Override // l4.d
    public final Object g(e eVar, t3.c cVar) throws Throwable {
        k(this, eVar, cVar);
        return u3.a.f5838d;
    }

    public final Object i(v vVar, s sVar) {
        i4.h hVar = new i4.h(1, p3.j.B(sVar));
        hVar.p();
        synchronized (this) {
            try {
                if (s(vVar) < 0) {
                    vVar.f3590b = hVar;
                } else {
                    hVar.resumeWith(p3.n.f5026a);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Object objO = hVar.o();
        return objO == u3.a.f5838d ? objO : p3.n.f5026a;
    }

    public final void j() {
        if (this.f3579i != 0 || this.f3585o > 1) {
            Object[] objArr = this.f3581k;
            b4.l.b(objArr);
            while (this.f3585o > 0) {
                long jO = o();
                int i5 = this.f3584n;
                int i6 = this.f3585o;
                if (objArr[((int) ((jO + ((long) (i5 + i6))) - 1)) & (objArr.length - 1)] != u.f3586a) {
                    return;
                }
                this.f3585o = i6 - 1;
                u.b(objArr, o() + ((long) (this.f3584n + this.f3585o)), null);
            }
        }
    }

    public final void l() {
        m4.d[] dVarArr;
        Object[] objArr = this.f3581k;
        b4.l.b(objArr);
        u.b(objArr, o(), null);
        this.f3584n--;
        long jO = o() + 1;
        if (this.f3582l < jO) {
            this.f3582l = jO;
        }
        if (this.f3583m < jO) {
            if (this.f3814e != 0 && (dVarArr = this.f3813d) != null) {
                for (m4.d dVar : dVarArr) {
                    if (dVar != null) {
                        v vVar = (v) dVar;
                        long j5 = vVar.f3589a;
                        if (j5 >= 0 && j5 < jO) {
                            vVar.f3589a = jO;
                        }
                    }
                }
            }
            this.f3583m = jO;
        }
    }

    public final void m(Object obj) {
        int i5 = this.f3584n + this.f3585o;
        Object[] objArrP = this.f3581k;
        if (objArrP == null) {
            objArrP = p(null, 0, 2);
        } else if (i5 >= objArrP.length) {
            objArrP = p(objArrP, i5, objArrP.length * 2);
        }
        u.b(objArrP, o() + ((long) i5), obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [t3.c[]] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r6v3 */
    public final t3.c[] n(t3.c[] cVarArr) {
        m4.d[] dVarArr;
        v vVar;
        i4.h hVar;
        int length = cVarArr.length;
        if (this.f3814e != 0 && (dVarArr = this.f3813d) != null) {
            int length2 = dVarArr.length;
            int i5 = 0;
            while (i5 < length2) {
                m4.d dVar = dVarArr[i5];
                if (dVar == null || (hVar = (vVar = (v) dVar).f3590b) == null || s(vVar) < 0) {
                    cVarArr = cVarArr;
                } else {
                    if (length >= cVarArr.length) {
                        cVarArr = cVarArr;
                        cVarArr = cVarArr;
                        Object[] objArrCopyOf = Arrays.copyOf((Object[]) cVarArr, Math.max(2, cVarArr.length * 2));
                        b4.l.d("copyOf(...)", objArrCopyOf);
                        cVarArr = objArrCopyOf;
                    }
                    cVarArr = cVarArr;
                    cVarArr = cVarArr;
                    ((t3.c[]) cVarArr)[length] = hVar;
                    vVar.f3590b = null;
                    length++;
                }
                i5++;
                cVarArr = cVarArr;
            }
            cVarArr = cVarArr;
        }
        return (t3.c[]) cVarArr;
    }

    public final long o() {
        return Math.min(this.f3583m, this.f3582l);
    }

    public final Object[] p(Object[] objArr, int i5, int i6) {
        if (i6 <= 0) {
            throw new IllegalStateException("Buffer size overflow");
        }
        Object[] objArr2 = new Object[i6];
        this.f3581k = objArr2;
        if (objArr != null) {
            long jO = o();
            for (int i7 = 0; i7 < i5; i7++) {
                long j5 = ((long) i7) + jO;
                u.b(objArr2, j5, objArr[((int) j5) & (objArr.length - 1)]);
            }
        }
        return objArr2;
    }

    public final boolean q(Object obj) {
        int i5;
        boolean z2;
        t3.c[] cVarArrN = m4.c.f3817a;
        synchronized (this) {
            if (r(obj)) {
                cVarArrN = n(cVarArrN);
                z2 = true;
            } else {
                z2 = false;
            }
        }
        for (t3.c cVar : cVarArrN) {
            if (cVar != null) {
                cVar.resumeWith(p3.n.f5026a);
            }
        }
        return z2;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0047  */
    /* JADX WARN: Code duplicated, block: B:27:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    public final boolean r(Object obj) {
        int i5;
        long jO;
        long j5;
        int i6 = this.f3814e;
        int i7 = this.f3578h;
        if (i6 != 0) {
            int i8 = this.f3584n;
            int i9 = this.f3579i;
            if (i8 < i9 || this.f3583m > this.f3582l) {
                m(obj);
                i5 = this.f3584n + 1;
                this.f3584n = i5;
                if (i5 > i9) {
                    l();
                }
                jO = o() + ((long) this.f3584n);
                j5 = this.f3582l;
                if (((int) (jO - j5)) > i7) {
                    u(1 + j5, this.f3583m, o() + ((long) this.f3584n), o() + ((long) this.f3584n) + ((long) this.f3585o));
                }
            } else {
                int iOrdinal = this.f3580j.ordinal();
                if (iOrdinal == 0) {
                    return false;
                }
                if (iOrdinal == 1) {
                    m(obj);
                    i5 = this.f3584n + 1;
                    this.f3584n = i5;
                    if (i5 > i9) {
                        l();
                    }
                    jO = o() + ((long) this.f3584n);
                    j5 = this.f3582l;
                    if (((int) (jO - j5)) > i7) {
                        u(1 + j5, this.f3583m, o() + ((long) this.f3584n), o() + ((long) this.f3584n) + ((long) this.f3585o));
                    }
                } else if (iOrdinal != 2) {
                    throw new i4.p();
                }
            }
        } else if (i7 != 0) {
            m(obj);
            int i10 = this.f3584n + 1;
            this.f3584n = i10;
            if (i10 > i7) {
                l();
            }
            this.f3583m = o() + ((long) this.f3584n);
            return true;
        }
        return true;
    }

    public final long s(v vVar) {
        long j5 = vVar.f3589a;
        if (j5 < o() + ((long) this.f3584n)) {
            return j5;
        }
        if (this.f3579i <= 0 && j5 <= o() && this.f3585o != 0) {
            return j5;
        }
        return -1L;
    }

    public final Object t(v vVar) {
        Object obj;
        t3.c[] cVarArrV = m4.c.f3817a;
        synchronized (this) {
            try {
                long jS = s(vVar);
                if (jS < 0) {
                    obj = u.f3586a;
                } else {
                    long j5 = vVar.f3589a;
                    Object[] objArr = this.f3581k;
                    b4.l.b(objArr);
                    Object obj2 = objArr[((int) jS) & (objArr.length - 1)];
                    if (obj2 instanceof r) {
                        obj2 = ((r) obj2).f3569f;
                    }
                    vVar.f3589a = jS + 1;
                    Object obj3 = obj2;
                    cVarArrV = v(j5);
                    obj = obj3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (t3.c cVar : cVarArrV) {
            if (cVar != null) {
                cVar.resumeWith(p3.n.f5026a);
            }
        }
        return obj;
    }

    public final void u(long j5, long j6, long j7, long j8) {
        long jMin = Math.min(j6, j5);
        for (long jO = o(); jO < jMin; jO++) {
            Object[] objArr = this.f3581k;
            b4.l.b(objArr);
            u.b(objArr, jO, null);
        }
        this.f3582l = j5;
        this.f3583m = j6;
        this.f3584n = (int) (j7 - jMin);
        this.f3585o = (int) (j8 - j7);
    }

    public final t3.c[] v(long j5) {
        long j6;
        long j7;
        t3.c[] cVarArr;
        t3.c[] cVarArr2;
        m4.d[] dVarArr;
        b3.r rVar = u.f3586a;
        t3.c[] cVarArr3 = m4.c.f3817a;
        if (j5 <= this.f3583m) {
            long jO = o();
            long j8 = ((long) this.f3584n) + jO;
            int i5 = this.f3579i;
            if (i5 == 0 && this.f3585o > 0) {
                j8++;
            }
            int i6 = 0;
            if (this.f3814e != 0 && (dVarArr = this.f3813d) != null) {
                for (m4.d dVar : dVarArr) {
                    if (dVar != null) {
                        long j9 = ((v) dVar).f3589a;
                        if (j9 >= 0 && j9 < j8) {
                            j8 = j9;
                        }
                    }
                }
            }
            if (j8 > this.f3583m) {
                long jO2 = o() + ((long) this.f3584n);
                int iMin = this.f3814e > 0 ? Math.min(this.f3585o, i5 - ((int) (jO2 - j8))) : this.f3585o;
                long j10 = ((long) this.f3585o) + jO2;
                if (iMin > 0) {
                    j7 = 1;
                    Object[] objArr = this.f3581k;
                    b4.l.b(objArr);
                    t3.c[] cVarArr4 = new t3.c[iMin];
                    long j11 = jO2;
                    while (true) {
                        if (jO2 >= j10) {
                            cVarArr2 = cVarArr4;
                            j6 = j8;
                            break;
                        }
                        cVarArr2 = cVarArr4;
                        Object obj = objArr[(objArr.length - 1) & ((int) jO2)];
                        if (obj != rVar) {
                            b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter", obj);
                            r rVar2 = (r) obj;
                            int i7 = i6 + 1;
                            j6 = j8;
                            cVarArr2[i6] = rVar2.f3570g;
                            u.b(objArr, jO2, rVar);
                            u.b(objArr, j11, rVar2.f3569f);
                            j11++;
                            if (i7 >= iMin) {
                                break;
                            }
                            i6 = i7;
                        } else {
                            j6 = j8;
                        }
                        jO2++;
                        cVarArr4 = cVarArr2;
                        j8 = j6;
                    }
                    jO2 = j11;
                    cVarArr = cVarArr2;
                } else {
                    j6 = j8;
                    j7 = 1;
                    cVarArr = cVarArr3;
                }
                int i8 = (int) (jO2 - jO);
                long j12 = this.f3814e == 0 ? jO2 : j6;
                long jMax = Math.max(this.f3582l, jO2 - ((long) Math.min(this.f3578h, i8)));
                if (i5 == 0 && jMax < j10) {
                    Object[] objArr2 = this.f3581k;
                    b4.l.b(objArr2);
                    if (b4.l.a(objArr2[((int) jMax) & (objArr2.length - 1)], rVar)) {
                        jO2 += j7;
                        jMax += j7;
                    }
                }
                u(jMax, j12, jO2, j10);
                j();
                return cVarArr.length == 0 ? cVarArr : n(cVarArr);
            }
        }
        return cVarArr3;
    }
}
