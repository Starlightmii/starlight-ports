package s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h0.e f5747a = new h0.e(new d[16]);

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005e, code lost:
    
        if (r8 >= r1) goto L22;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x005a -> B:20:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(x0.d r8, v3.c r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof s.b
            if (r0 == 0) goto L13
            r0 = r9
            s.b r0 = (s.b) r0
            int r1 = r0.f5746j
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f5746j = r1
            goto L18
        L13:
            s.b r0 = new s.b
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.f5744h
            int r1 = r0.f5746j
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            int r8 = r0.f5743g
            int r1 = r0.f5742f
            java.lang.Object[] r3 = r0.f5741e
            x0.d r4 = r0.f5740d
            p3.j.O(r9)
            r9 = r4
            goto L5d
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            p3.j.O(r9)
            h0.e r9 = r7.f5747a
            int r1 = r9.f2578f
            if (r1 <= 0) goto L60
            java.lang.Object[] r9 = r9.f2576d
            r3 = 0
            r6 = r9
            r9 = r8
            r8 = r3
            r3 = r6
        L46:
            r4 = r3[r8]
            s.d r4 = (s.d) r4
            r0.f5740d = r9
            r0.f5741e = r3
            r0.f5742f = r1
            r0.f5743g = r8
            r0.f5746j = r2
            java.lang.Object r4 = p3.j.K(r4, r9, r0)
            u3.a r5 = u3.a.f5838d
            if (r4 != r5) goto L5d
            return r5
        L5d:
            int r8 = r8 + r2
            if (r8 < r1) goto L46
        L60:
            p3.n r8 = p3.n.f5026a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: s.c.a(x0.d, v3.c):java.lang.Object");
    }
}
