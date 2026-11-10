package i1;

import i4.v;
import p.i1;
import p.w0;
import p1.f;
import p1.o1;
import p3.j;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends o implements o1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final w0 f2663q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final a1.b f2664r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f2665s = "androidx.compose.ui.input.nestedscroll.NestedScrollNode";

    public e(w0 w0Var, a1.b bVar) {
        this.f2663q = w0Var;
        this.f2664r = bVar;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x002c  */
    public final long A0(long j5, long j6, int i5) {
        long jG;
        w0 w0Var = this.f2663q;
        if (w0Var.f4626b) {
            i1 i1Var = w0Var.f4625a;
            if (i1Var.f4484a.b()) {
                jG = 0;
            } else {
                jG = i1Var.g(i1Var.c(i1Var.f4484a.e(i1Var.c(i1Var.f(j6)))));
            }
        } else {
            jG = 0;
        }
        boolean z2 = this.f5693p;
        e eVar = null;
        if (z2 && z2) {
            eVar = (e) f.j(this);
        }
        e eVar2 = eVar;
        return x0.c.h(jG, eVar2 != null ? eVar2.A0(x0.c.h(j5, jG), x0.c.g(j6, jG), i5) : 0L);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007d, code lost:
    
        if (r15 == r7) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object B0(long r13, t3.c r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof i1.d
            if (r0 == 0) goto L13
            r0 = r15
            i1.d r0 = (i1.d) r0
            int r1 = r0.f2662h
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2662h = r1
            goto L1a
        L13:
            i1.d r0 = new i1.d
            v3.c r15 = (v3.c) r15
            r0.<init>(r12, r15)
        L1a:
            java.lang.Object r15 = r0.f2660f
            int r1 = r0.f2662h
            r2 = 0
            r4 = 0
            r5 = 2
            r6 = 1
            u3.a r7 = u3.a.f5838d
            if (r1 == 0) goto L41
            if (r1 == r6) goto L39
            if (r1 != r5) goto L31
            long r13 = r0.f2659e
            p3.j.O(r15)
            goto L80
        L31:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L39:
            long r13 = r0.f2659e
            i1.e r1 = r0.f2658d
            p3.j.O(r15)
            goto L62
        L41:
            p3.j.O(r15)
            boolean r15 = r12.f5693p
            if (r15 == 0) goto L51
            if (r15 == 0) goto L51
            p1.o1 r15 = p1.f.j(r12)
            i1.e r15 = (i1.e) r15
            goto L52
        L51:
            r15 = r4
        L52:
            if (r15 == 0) goto L6a
            r0.f2658d = r12
            r0.f2659e = r13
            r0.f2662h = r6
            java.lang.Object r15 = r15.B0(r13, r0)
            if (r15 != r7) goto L61
            goto L7f
        L61:
            r1 = r12
        L62:
            k2.o r15 = (k2.o) r15
            long r8 = r15.f3424a
            r10 = r8
            r8 = r13
            r13 = r10
            goto L6d
        L6a:
            r1 = r12
            r8 = r13
            r13 = r2
        L6d:
            p.w0 r15 = r1.f2663q
            k2.o.d(r8, r13)
            r0.f2658d = r4
            r0.f2659e = r13
            r0.f2662h = r5
            k2.o r15 = new k2.o
            r15.<init>(r2)
            if (r15 != r7) goto L80
        L7f:
            return r7
        L80:
            k2.o r15 = (k2.o) r15
            long r0 = r15.f3424a
            long r13 = k2.o.e(r13, r0)
            k2.o r15 = new k2.o
            r15.<init>(r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.e.B0(long, t3.c):java.lang.Object");
    }

    public final long C0(long j5, int i5) {
        boolean z2 = this.f5693p;
        e eVar = null;
        if (z2 && z2) {
            eVar = (e) f.j(this);
        }
        long jC0 = eVar != null ? eVar.C0(j5, i5) : 0L;
        x0.c.g(j5, jC0);
        return x0.c.h(jC0, 0L);
    }

    @Override // p1.o1
    public final Object k() {
        return this.f2665s;
    }

    @Override // r0.o
    public final void r0() {
        a1.b bVar = this.f2664r;
        bVar.f23d = this;
        bVar.f24e = new b0.a(8, this);
        bVar.f25f = n0();
    }

    @Override // r0.o
    public final void s0() {
        a1.b bVar = this.f2664r;
        if (((e) bVar.f23d) == this) {
            bVar.f23d = null;
        }
    }

    public final v y0() {
        e eVar = this.f5693p ? (e) f.j(this) : null;
        if (eVar != null) {
            return eVar.y0();
        }
        v vVar = (v) this.f2664r.f25f;
        if (vVar != null) {
            return vVar;
        }
        throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    public final Object z0(long j5, long j6, v3.c cVar) {
        c cVar2;
        e eVar;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        if (cVar instanceof c) {
            cVar2 = (c) cVar;
            int i5 = cVar2.f2657i;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                cVar2.f2657i = i5 - Integer.MIN_VALUE;
            } else {
                cVar2 = new c(this, cVar);
            }
        } else {
            cVar2 = new c(this, cVar);
        }
        c cVar3 = cVar2;
        Object objA = cVar3.f2655g;
        int i6 = cVar3.f2657i;
        u3.a aVar = u3.a.f5838d;
        if (i6 == 0) {
            j.O(objA);
            cVar3.f2652d = this;
            cVar3.f2653e = j5;
            cVar3.f2654f = j6;
            cVar3.f2657i = 1;
            objA = this.f2663q.a(j5, j6, cVar3);
            if (objA != aVar) {
                eVar = this;
                j7 = j5;
                j8 = j6;
            }
            return aVar;
        }
        if (i6 == 1) {
            j8 = cVar3.f2654f;
            j7 = cVar3.f2653e;
            eVar = cVar3.f2652d;
            j.O(objA);
        } else {
            if (i6 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j11 = cVar3.f2653e;
            j.O(objA);
        }
        j10 = ((k2.o) objA).f3424a;
        j9 = j11;
        return new k2.o(k2.o.e(j9, j10));
        j9 = ((k2.o) objA).f3424a;
        boolean z2 = eVar.f5693p;
        e eVar2 = (z2 && z2) ? (e) f.j(eVar) : null;
        if (eVar2 != null) {
            long jE = k2.o.e(j7, j9);
            long jD = k2.o.d(j8, j9);
            cVar3.f2652d = null;
            cVar3.f2653e = j9;
            cVar3.f2657i = 2;
            objA = eVar2.z0(jE, jD, cVar3);
            if (objA != aVar) {
                j11 = j9;
                j10 = ((k2.o) objA).f3424a;
                j9 = j11;
            }
            return aVar;
        }
        j10 = 0;
        return new k2.o(k2.o.e(j9, j10));
    }
}
