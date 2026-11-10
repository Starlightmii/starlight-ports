package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f4654a = ((float) 0.125d) / 18;

    /* JADX WARN: Code duplicated, block: B:24:0x006b  */
    /* JADX WARN: Code duplicated, block: B:27:0x007d A[LOOP:0: B:23:0x0069->B:27:0x007d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:54:0x0081 A[EDGE_INSN: B:54:0x0081->B:29:0x0081 BREAK  A[LOOP:0: B:23:0x0069->B:27:0x007d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r14v5, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005c -> B:22:0x005f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public static final java.lang.Object a(j1.c0 r12, long r13, v3.c r15) {
        /*
            boolean r0 = r15 instanceof p.t
            if (r0 == 0) goto L13
            r0 = r15
            p.t r0 = (p.t) r0
            int r1 = r0.f4590g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4590g = r1
            goto L18
        L13:
            p.t r0 = new p.t
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.f4589f
            int r1 = r0.f4590g
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            b4.v r12 = r0.f4588e
            j1.c0 r13 = r0.f4587d
            p3.j.O(r15)
            r11 = r13
            r13 = r12
            r12 = r11
            goto L5f
        L2d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L35:
            p3.j.O(r15)
            j1.d0 r15 = r12.f3228h
            j1.i r15 = r15.f3236u
            boolean r15 = d(r15, r13)
            if (r15 == 0) goto L44
            goto Lc4
        L44:
            b4.v r15 = new b4.v
            r15.<init>()
            r15.f696d = r13
        L4b:
            r0.f4587d = r12
            r0.f4588e = r15
            r0.f4590g = r2
            j1.j r13 = j1.j.f3261e
            java.lang.Object r13 = r12.c(r13, r0)
            u3.a r14 = u3.a.f5838d
            if (r13 != r14) goto L5c
            return r14
        L5c:
            r11 = r15
            r15 = r13
            r13 = r11
        L5f:
            j1.i r15 = (j1.i) r15
            java.lang.Object r14 = r15.f3257a
            int r1 = r14.size()
            r4 = 0
            r5 = r4
        L69:
            if (r5 >= r1) goto L80
            java.lang.Object r6 = r14.get(r5)
            r7 = r6
            j1.u r7 = (j1.u) r7
            long r7 = r7.f3274a
            long r9 = r13.f696d
            boolean r7 = j1.t.a(r7, r9)
            if (r7 == 0) goto L7d
            goto L81
        L7d:
            int r5 = r5 + 1
            goto L69
        L80:
            r6 = r3
        L81:
            j1.u r6 = (j1.u) r6
            if (r6 != 0) goto L87
            r6 = r3
            goto Lbb
        L87:
            boolean r14 = j1.s.c(r6)
            if (r14 == 0) goto Laf
            java.lang.Object r14 = r15.f3257a
            int r15 = r14.size()
        L93:
            if (r4 >= r15) goto La4
            java.lang.Object r1 = r14.get(r4)
            r5 = r1
            j1.u r5 = (j1.u) r5
            boolean r5 = r5.f3277d
            if (r5 == 0) goto La1
            goto La5
        La1:
            int r4 = r4 + 1
            goto L93
        La4:
            r1 = r3
        La5:
            j1.u r1 = (j1.u) r1
            if (r1 != 0) goto Laa
            goto Lbb
        Laa:
            long r14 = r1.f3274a
            r13.f696d = r14
            goto Lc5
        Laf:
            long r14 = j1.s.f(r6, r2)
            r4 = 0
            boolean r14 = x0.c.b(r14, r4)
            if (r14 != 0) goto Lc5
        Lbb:
            if (r6 == 0) goto Lc4
            boolean r12 = r6.b()
            if (r12 != 0) goto Lc4
            return r6
        Lc4:
            return r3
        Lc5:
            r15 = r13
            goto L4b
        */
        throw new UnsupportedOperationException("Method not decompiled: p.z.a(j1.c0, long, v3.c):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Object, java.util.List] */
    public static final Object b(j1.c0 c0Var, long j5, v3.c cVar) {
        u uVar;
        Object obj;
        j1.u uVar2;
        b4.w wVar;
        if (cVar instanceof u) {
            uVar = (u) cVar;
            int i5 = uVar.f4596g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                uVar.f4596g = i5 - Integer.MIN_VALUE;
            } else {
                uVar = new u(cVar);
            }
        } else {
            uVar = new u(cVar);
        }
        Object obj2 = uVar.f4595f;
        int i6 = uVar.f4596g;
        if (i6 == 0) {
            p3.j.O(obj2);
            if (!d(c0Var.f3228h.f3236u, j5)) {
                ?? r11 = c0Var.f3228h.f3236u.f3257a;
                int size = r11.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size) {
                        obj = null;
                        break;
                    }
                    obj = r11.get(i7);
                    if (j1.t.a(((j1.u) obj).f3274a, j5)) {
                        break;
                    }
                    i7++;
                }
                uVar2 = (j1.u) obj;
                if (uVar2 != null) {
                    b4.w wVar2 = new b4.w();
                    b4.w wVar3 = new b4.w();
                    wVar3.f697d = uVar2;
                    long jC = c0Var.g().c();
                    try {
                        a4.e vVar = new v(wVar3, wVar2, null);
                        uVar.f4593d = uVar2;
                        uVar.f4594e = wVar2;
                        uVar.f4596g = 1;
                        Object objH = c0Var.h(jC, vVar, uVar);
                        Object obj3 = u3.a.f5838d;
                        if (objH == obj3) {
                            return obj3;
                        }
                    } catch (j1.k unused) {
                        wVar = wVar2;
                    }
                }
            }
            return null;
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        wVar = uVar.f4594e;
        uVar2 = uVar.f4593d;
        try {
            p3.j.O(obj2);
            return null;
        } catch (j1.k unused2) {
        }
        j1.u uVar3 = (j1.u) wVar.f697d;
        return uVar3 == null ? uVar2 : uVar3;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0044 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x004c  */
    /* JADX WARN: Code duplicated, block: B:24:0x0052  */
    /* JADX WARN: Code duplicated, block: B:26:0x0055  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0042 -> B:18:0x0045). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public static final java.lang.Object c(j1.c0 r4, long r5, a4.c r7, v3.c r8) {
        /*
            boolean r0 = r8 instanceof p.y
            if (r0 == 0) goto L13
            r0 = r8
            p.y r0 = (p.y) r0
            int r1 = r0.f4650g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4650g = r1
            goto L18
        L13:
            p.y r0 = new p.y
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f4649f
            int r1 = r0.f4650g
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            a4.c r4 = r0.f4648e
            j1.c0 r5 = r0.f4647d
            p3.j.O(r8)
            r7 = r4
            r4 = r5
            goto L45
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            p3.j.O(r8)
        L36:
            r0.f4647d = r4
            r0.f4648e = r7
            r0.f4650g = r2
            java.lang.Object r8 = a(r4, r5, r0)
            u3.a r5 = u3.a.f5838d
            if (r8 != r5) goto L45
            return r5
        L45:
            j1.u r8 = (j1.u) r8
            if (r8 != 0) goto L4c
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        L4c:
            boolean r5 = j1.s.c(r8)
            if (r5 == 0) goto L55
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L55:
            r7.c(r8)
            long r5 = r8.f3274a
            goto L36
        */
        throw new UnsupportedOperationException("Method not decompiled: p.z.c(j1.c0, long, a4.c, v3.c):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object, java.util.List] */
    public static final boolean d(j1.i iVar, long j5) {
        Object obj;
        ?? r6 = iVar.f3257a;
        int size = r6.size();
        boolean z2 = false;
        int i5 = 0;
        while (true) {
            if (i5 >= size) {
                obj = null;
                break;
            }
            obj = r6.get(i5);
            if (j1.t.a(((j1.u) obj).f3274a, j5)) {
                break;
            }
            i5++;
        }
        j1.u uVar = (j1.u) obj;
        if (uVar != null && uVar.f3277d) {
            z2 = true;
        }
        return true ^ z2;
    }
}
