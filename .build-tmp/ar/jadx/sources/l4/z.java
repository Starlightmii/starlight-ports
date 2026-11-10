package l4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z extends v3.i implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3598d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ e f3599e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ int f3600f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a0 f3601g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(a0 a0Var, t3.c cVar) {
        super(3, cVar);
        this.f3601g = a0Var;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj2).intValue();
        z zVar = new z(this.f3601g, (t3.c) obj3);
        zVar.f3599e = (e) obj;
        zVar.f3600f = iIntValue;
        return zVar.invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0062 A[PHI: r0
      0x0062: PHI (r0v3 l4.e) = (r0v2 l4.e), (r0v6 l4.e) binds: [B:25:0x005f, B:13:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        if (r0.d(l4.w.f3591d, r7) == r6) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007d, code lost:
    
        if (r0.d(l4.w.f3593f, r7) == r6) goto L32;
     */
    @Override // v3.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.f3598d
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            u3.a r6 = u3.a.f5838d
            if (r0 == 0) goto L34
            if (r0 == r5) goto L30
            if (r0 == r4) goto L2a
            if (r0 == r3) goto L24
            if (r0 == r2) goto L1e
            if (r0 != r1) goto L16
            goto L30
        L16:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1e:
            l4.e r0 = r7.f3599e
            p3.j.O(r8)
            goto L72
        L24:
            l4.e r0 = r7.f3599e
            p3.j.O(r8)
            goto L62
        L2a:
            l4.e r0 = r7.f3599e
            p3.j.O(r8)
            goto L55
        L30:
            p3.j.O(r8)
            goto L80
        L34:
            p3.j.O(r8)
            l4.e r0 = r7.f3599e
            int r8 = r7.f3600f
            if (r8 <= 0) goto L48
            r7.f3598d = r5
            l4.w r8 = l4.w.f3591d
            java.lang.Object r8 = r0.d(r8, r7)
            if (r8 != r6) goto L80
            goto L7f
        L48:
            r7.f3599e = r0
            r7.f3598d = r4
            r4 = 0
            java.lang.Object r8 = i4.y.d(r4, r7)
            if (r8 != r6) goto L55
            goto L7f
        L55:
            r7.f3599e = r0
            r7.f3598d = r3
            l4.w r8 = l4.w.f3592e
            java.lang.Object r8 = r0.d(r8, r7)
            if (r8 != r6) goto L62
            goto L7f
        L62:
            r7.f3599e = r0
            r7.f3598d = r2
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r8 = i4.y.d(r2, r7)
            if (r8 != r6) goto L72
            goto L7f
        L72:
            r8 = 0
            r7.f3599e = r8
            r7.f3598d = r1
            l4.w r8 = l4.w.f3593f
            java.lang.Object r8 = r0.d(r8, r7)
            if (r8 != r6) goto L80
        L7f:
            return r6
        L80:
            p3.n r8 = p3.n.f5026a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: l4.z.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
