package a;

import a2.f;
import a4.c;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.inputmethod.ExtractedText;
import b0.t;
import b4.l;
import c0.c2;
import c0.z0;
import e1.a0;
import e1.b0;
import e1.d;
import e1.e;
import e2.x;
import f0.b2;
import f0.o;
import f0.v;
import f0.w1;
import g0.k0;
import h4.g;
import j1.d0;
import j1.s;
import j1.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import k2.b;
import k2.m;
import k2.n;
import n1.f0;
import n1.u0;
import o.h;
import o.i;
import p3.j;
import q3.k;
import r0.p;
import y0.l0;
import y0.s0;
import y0.u;
import y1.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static e f10d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static e f11e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static e f12f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static e f13g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static e f14h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static e f15i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static e f16j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static e f17k;

    public static final y1.f A(x xVar, int i5) {
        y1.f fVar = xVar.f2062a;
        long j5 = xVar.f2063b;
        return fVar.subSequence(g0.d(j5), Math.min(g0.d(j5) + i5, xVar.f2062a.f6759d.length()));
    }

    public static final y1.f B(x xVar, int i5) {
        y1.f fVar = xVar.f2062a;
        long j5 = xVar.f2063b;
        return fVar.subSequence(Math.max(0, g0.e(j5) - i5), g0.e(j5));
    }

    public static final e C() {
        e eVar = f17k;
        if (eVar != null) {
            return eVar;
        }
        d dVar = new d("Rounded.Tune");
        int i5 = a0.f1864a;
        s0 s0Var = new s0(u.f6689b);
        e1.f fVar = new e1.f();
        fVar.h(3.0f, 18.0f);
        fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar.e(5.0f);
        fVar.l(-2.0f);
        fVar.f(4.0f, 17.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.a();
        fVar.h(3.0f, 6.0f);
        fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar.e(9.0f);
        fVar.f(13.0f, 5.0f);
        fVar.f(4.0f, 5.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.a();
        fVar.h(13.0f, 20.0f);
        fVar.l(-1.0f);
        fVar.e(7.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.j(-0.45f, -1.0f, -1.0f, -1.0f);
        fVar.e(-7.0f);
        fVar.l(-1.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.j(-1.0f, 0.45f, -1.0f, 1.0f);
        fVar.l(4.0f);
        fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar.j(1.0f, -0.45f, 1.0f, -1.0f);
        fVar.a();
        fVar.h(7.0f, 10.0f);
        fVar.l(1.0f);
        fVar.f(4.0f, 11.0f);
        fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        fVar.j(0.45f, 1.0f, 1.0f, 1.0f);
        fVar.e(3.0f);
        fVar.l(1.0f);
        fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar.j(1.0f, -0.45f, 1.0f, -1.0f);
        fVar.l(-4.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.j(-1.0f, 0.45f, -1.0f, 1.0f);
        fVar.a();
        fVar.h(21.0f, 12.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.e(-9.0f);
        fVar.l(2.0f);
        fVar.e(9.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.a();
        fVar.h(16.0f, 9.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.f(17.0f, 7.0f);
        fVar.e(3.0f);
        fVar.c(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        fVar.j(-0.45f, -1.0f, -1.0f, -1.0f);
        fVar.e(-3.0f);
        fVar.f(17.0f, 4.0f);
        fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        fVar.j(-1.0f, 0.45f, -1.0f, 1.0f);
        fVar.l(4.0f);
        fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        fVar.a();
        d.a(dVar, fVar.f1910d, s0Var);
        e eVarB = dVar.b();
        f17k = eVarB;
        return eVarB;
    }

    public static final float D(float f5, float f6, float f7) {
        return (f7 * f6) + ((1 - f7) * f5);
    }

    public static final int E(float f5, int i5, int i6) {
        return i5 + ((int) Math.round(((double) (i6 - i5)) * ((double) f5)));
    }

    public static final void G(float[] fArr, float[] fArr2, int i5, float[] fArr3) {
        if (i5 == 0) {
            d4.a.K("At least one point must be provided");
            throw null;
        }
        int i6 = 2 >= i5 ? i5 - 1 : 2;
        int i7 = i6 + 1;
        float[][] fArr4 = new float[i7][];
        for (int i8 = 0; i8 < i7; i8++) {
            fArr4[i8] = new float[i5];
        }
        for (int i9 = 0; i9 < i5; i9++) {
            fArr4[0][i9] = 1.0f;
            for (int i10 = 1; i10 < i7; i10++) {
                fArr4[i10][i9] = fArr4[i10 - 1][i9] * fArr[i9];
            }
        }
        float[][] fArr5 = new float[i7][];
        for (int i11 = 0; i11 < i7; i11++) {
            fArr5[i11] = new float[i5];
        }
        float[][] fArr6 = new float[i7][];
        for (int i12 = 0; i12 < i7; i12++) {
            fArr6[i12] = new float[i7];
        }
        int i13 = 0;
        while (i13 < i7) {
            float[] fArr7 = fArr5[i13];
            float[] fArr8 = fArr4[i13];
            l.e("<this>", fArr8);
            l.e("destination", fArr7);
            System.arraycopy(fArr8, 0, fArr7, 0, i5);
            for (int i14 = 0; i14 < i13; i14++) {
                float[] fArr9 = fArr5[i14];
                float fR = r(fArr7, fArr9);
                for (int i15 = 0; i15 < i5; i15++) {
                    fArr7[i15] = fArr7[i15] - (fArr9[i15] * fR);
                }
            }
            float fSqrt = (float) Math.sqrt(r(fArr7, fArr7));
            if (fSqrt < 1.0E-6f) {
                fSqrt = 1.0E-6f;
            }
            float f5 = 1.0f / fSqrt;
            for (int i16 = 0; i16 < i5; i16++) {
                fArr7[i16] = fArr7[i16] * f5;
            }
            float[] fArr10 = fArr6[i13];
            int i17 = 0;
            while (i17 < i7) {
                fArr10[i17] = i17 < i13 ? 0.0f : r(fArr7, fArr4[i17]);
                i17++;
            }
            i13++;
        }
        for (int i18 = i6; -1 < i18; i18--) {
            float fR2 = r(fArr5[i18], fArr2);
            float[] fArr11 = fArr6[i18];
            int i19 = i18 + 1;
            if (i19 <= i6) {
                int i20 = i6;
                while (true) {
                    fR2 -= fArr11[i20] * fArr3[i20];
                    if (i20 != i19) {
                        i20--;
                    }
                }
            }
            fArr3[i18] = fR2 / fArr11[i18];
        }
    }

    public static final float I(long j5, float f5, b bVar) {
        float fC;
        long jB = m.b(j5);
        if (n.a(jB, 4294967296L)) {
            if (bVar.j() <= 1.05d) {
                return bVar.U(j5);
            }
            fC = m.c(j5) / m.c(bVar.d0(f5));
        } else {
            if (!n.a(jB, 8589934592L)) {
                return Float.NaN;
            }
            fC = m.c(j5);
        }
        return fC * f5;
    }

    public static final void J(Spannable spannable, long j5, int i5, int i6) {
        if (j5 != 16) {
            spannable.setSpan(new ForegroundColorSpan(l0.w(j5)), i5, i6, 33);
        }
    }

    public static final void K(Spannable spannable, long j5, b bVar, int i5, int i6) {
        long jB = m.b(j5);
        if (n.a(jB, 4294967296L)) {
            spannable.setSpan(new AbsoluteSizeSpan(d4.a.H(bVar.U(j5)), false), i5, i6, 33);
        } else if (n.a(jB, 8589934592L)) {
            spannable.setSpan(new RelativeSizeSpan(m.c(j5)), i5, i6, 33);
        }
    }

    public static final void L(k0 k0Var, int i5, Object obj) {
        k0Var.f2491o[(k0Var.f2492p - k0Var.f2487k[k0Var.f2488l - 1].f2484b) + i5] = obj;
    }

    public static final void M(k0 k0Var, int i5, Object obj, int i6, Object obj2) {
        int i7 = k0Var.f2492p - k0Var.f2487k[k0Var.f2488l - 1].f2484b;
        Object[] objArr = k0Var.f2491o;
        objArr[i5 + i7] = obj;
        objArr[i7 + i6] = obj2;
    }

    public static final ExtractedText N(x xVar) {
        ExtractedText extractedText = new ExtractedText();
        String str = xVar.f2062a.f6759d;
        extractedText.text = str;
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = str.length();
        extractedText.partialStartOffset = -1;
        long j5 = xVar.f2063b;
        extractedText.selectionStart = g0.e(j5);
        extractedText.selectionEnd = g0.d(j5);
        extractedText.flags = !g.Q(xVar.f2062a.f6759d, '\n') ? 1 : 0;
        return extractedText;
    }

    public static final long O(long j5) {
        return j.f((int) (j5 >> 32), (int) (j5 & 4294967295L));
    }

    public static final void a(p pVar, c cVar, f0.p pVar2, int i5) {
        v vVar = (v) pVar2;
        vVar.X(-932836462);
        if ((((vVar.f(pVar) ? 4 : 2) | i5 | (vVar.h(cVar) ? 32 : 16)) & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            r.c.a(vVar, androidx.compose.ui.draw.a.a(pVar, cVar));
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.l(i5, 5, pVar, cVar);
        }
    }

    public static final void b(o.j jVar, a4.a aVar, p pVar, c2 c2Var, f0.p pVar2, int i5) {
        int i6;
        v vVar = (v) pVar2;
        vVar.X(645832757);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(jVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        int i7 = i6 | 384;
        if ((i5 & 3072) == 0) {
            i7 |= vVar.h(c2Var) ? 2048 : 1024;
        }
        if ((i7 & 1171) == 1170 && vVar.z()) {
            vVar.Q();
        } else {
            i iVar = (i) jVar.f4340a.getValue();
            if (!(iVar instanceof h)) {
                b2 b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new u0(jVar, aVar, c2Var, i5);
                    return;
                }
                return;
            }
            boolean zF = vVar.f(iVar);
            Object objK = vVar.K();
            if (zF || objK == o.f2281a) {
                objK = new o.d(h1.c.M(((h) iVar).f4339a));
                vVar.f0(objK);
            }
            o.m.c((o.d) objK, aVar, c2Var, vVar, i7 & 8176);
            pVar = r0.m.f5680a;
        }
        p pVar3 = pVar;
        b2 b2VarR2 = vVar.r();
        if (b2VarR2 != null) {
            b2VarR2.f2098d = new c0.s0(jVar, aVar, pVar3, c2Var, i5, 1);
        }
    }

    public static final void c(o.j jVar, a4.a aVar, c2 c2Var, p pVar, boolean z2, n0.d dVar, f0.p pVar2, int i5) {
        int i6;
        c2 c2Var2;
        p pVar3;
        p pVarA;
        v vVar = (v) pVar2;
        vVar.X(-84584070);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(jVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            c2Var2 = c2Var;
            i6 |= vVar.h(c2Var2) ? 256 : 128;
        } else {
            c2Var2 = c2Var;
        }
        int i7 = i6 | 3072;
        if ((i5 & 24576) == 0) {
            i7 |= vVar.g(z2) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i7 |= vVar.h(dVar) ? 131072 : 65536;
        }
        if ((74899 & i7) == 74898 && vVar.z()) {
            vVar.Q();
            pVar3 = pVar;
        } else {
            pVar3 = r0.m.f5680a;
            if (z2) {
                pVarA = z.a(pVar3, o.c.f4322a, new t(jVar, null, 10));
            } else {
                pVarA = pVar3;
            }
            f0 f0VarE = r.o.e(r0.b.f5653d, true);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            p pVarC = r0.a.c(vVar, pVarA);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, f0VarE);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            dVar.invoke(vVar, Integer.valueOf((i7 >> 15) & 14));
            b(jVar, aVar, null, c2Var2, vVar, (i7 & 126) | ((i7 << 3) & 7168));
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z0(jVar, aVar, c2Var, pVar3, z2, dVar, i5);
        }
    }

    public static final long d(float f5, float f6) {
        return (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
    }

    public static final long f(int i5, int i6) {
        return (((long) i6) & 4294967295L) | (((long) i5) << 32);
    }

    public static final void g(k1.c cVar, j1.u uVar) {
        k1.b bVar = cVar.f3398b;
        k1.b bVar2 = cVar.f3397a;
        boolean zA = s.a(uVar);
        long j5 = uVar.f3275b;
        if (zA) {
            k1.a[] aVarArr = bVar2.f3392b;
            k.E(aVarArr, 0, aVarArr.length);
            bVar2.f3393c = 0;
            k1.a[] aVarArr2 = bVar.f3392b;
            k.E(aVarArr2, 0, aVarArr2.length);
            bVar.f3393c = 0;
            cVar.f3399c = 0L;
        }
        if (!s.c(uVar)) {
            List list = uVar.f3284k;
            if (list == null) {
                list = q3.s.f5477d;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                j1.d dVar = (j1.d) list.get(i5);
                long j6 = dVar.f3229a;
                long j7 = dVar.f3231c;
                bVar2.a(x0.c.d(j7), j6);
                bVar.a(x0.c.e(j7), j6);
            }
            long j8 = uVar.f3285l;
            bVar2.a(x0.c.d(j8), j5);
            bVar.a(x0.c.e(j8), j5);
        }
        if (s.c(uVar) && j5 - cVar.f3399c > 40) {
            k1.a[] aVarArr3 = bVar2.f3392b;
            k.E(aVarArr3, 0, aVarArr3.length);
            bVar2.f3393c = 0;
            k1.a[] aVarArr4 = bVar.f3392b;
            k.E(aVarArr4, 0, aVarArr4.length);
            bVar.f3393c = 0;
            cVar.f3399c = 0L;
        }
        cVar.f3399c = j5;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0064  */
    /* JADX WARN: Code duplicated, block: B:26:0x006f A[LOOP:0: B:22:0x0062->B:26:0x006f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x004a A[EDGE_INSN: B:31:0x004a->B:18:0x004a BREAK  A[LOOP:0: B:22:0x0062->B:26:0x006f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0056 -> B:21:0x0059). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public static final java.lang.Object i(j1.c0 r6, v3.a r7) {
        /*
            boolean r0 = r7 instanceof p.j0
            if (r0 == 0) goto L13
            r0 = r7
            p.j0 r0 = (p.j0) r0
            int r1 = r0.f4501f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4501f = r1
            goto L18
        L13:
            p.j0 r0 = new p.j0
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f4500e
            int r1 = r0.f4501f
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 != r3) goto L28
            j1.c0 r6 = r0.f4499d
            p3.j.O(r7)
            goto L59
        L28:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L30:
            p3.j.O(r7)
            j1.d0 r7 = r6.f3228h
            j1.i r7 = r7.f3236u
            java.lang.Object r7 = r7.f3257a
            int r1 = r7.size()
            r4 = r2
        L3e:
            if (r4 >= r1) goto L75
            java.lang.Object r5 = r7.get(r4)
            j1.u r5 = (j1.u) r5
            boolean r5 = r5.f3277d
            if (r5 == 0) goto L72
        L4a:
            r0.f4499d = r6
            r0.f4501f = r3
            j1.j r7 = j1.j.f3262f
            java.lang.Object r7 = r6.c(r7, r0)
            u3.a r1 = u3.a.f5838d
            if (r7 != r1) goto L59
            return r1
        L59:
            j1.i r7 = (j1.i) r7
            java.lang.Object r7 = r7.f3257a
            int r1 = r7.size()
            r4 = r2
        L62:
            if (r4 >= r1) goto L75
            java.lang.Object r5 = r7.get(r4)
            j1.u r5 = (j1.u) r5
            boolean r5 = r5.f3277d
            if (r5 == 0) goto L6f
            goto L4a
        L6f:
            int r4 = r4 + 1
            goto L62
        L72:
            int r4 = r4 + 1
            goto L3e
        L75:
            p3.n r6 = p3.n.f5026a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.i(j1.c0, v3.a):java.lang.Object");
    }

    public static final Object j(d0 d0Var, a4.e eVar, t3.c cVar) {
        Object objY0 = d0Var.y0(new p.k0(cVar.getContext(), eVar, null), cVar);
        return objY0 == u3.a.f5838d ? objY0 : p3.n.f5026a;
    }

    public static final void m(int i5, int i6) {
        if (i5 < 0 || i5 >= i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
    }

    public static final void n(int i5, int i6) {
        if (i5 < 0 || i5 > i6) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", i6));
        }
    }

    public static final void o(int i5, int i6, int i7) {
        if (i5 >= 0 && i6 <= i7) {
            if (i5 > i6) {
                throw new IllegalArgumentException(b.b.d("fromIndex: ", i5, " > toIndex: ", i6));
            }
            return;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i5 + ", toIndex: " + i6 + ", size: " + i7);
    }

    public static final void q(e1.b bVar, e1.z zVar) {
        ArrayList arrayList = zVar.f1972e;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            b0 b0Var = (b0) arrayList.get(i5);
            if (b0Var instanceof e1.d0) {
                e1.h hVar = new e1.h();
                e1.d0 d0Var = (e1.d0) b0Var;
                hVar.f1915c = d0Var.f1897d;
                hVar.f1917e = true;
                hVar.c();
                hVar.f1920h.f6638a.setFillType(Path.FillType.WINDING);
                hVar.c();
                hVar.c();
                hVar.f1914b = d0Var.f1898e;
                hVar.c();
                hVar.c();
                hVar.c();
                hVar.c();
                hVar.c();
                hVar.c();
                hVar.c();
                hVar.c();
                hVar.f1918f = true;
                hVar.c();
                hVar.f1916d = 1.0f;
                hVar.f1918f = true;
                hVar.c();
                hVar.f1918f = true;
                hVar.c();
                bVar.e(i5, hVar);
            } else if (b0Var instanceof e1.z) {
                e1.b bVar2 = new e1.b();
                e1.z zVar2 = (e1.z) b0Var;
                bVar2.f1874k = "";
                bVar2.c();
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1875l = 1.0f;
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1876m = 1.0f;
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1877n = true;
                bVar2.c();
                bVar2.f1869f = zVar2.f1971d;
                bVar2.f1870g = true;
                bVar2.c();
                q(bVar2, zVar2);
                bVar.e(i5, bVar2);
            }
        }
    }

    public static final float r(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        float f5 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            f5 += fArr[i5] * fArr2[i5];
        }
        return f5;
    }

    public static final float s(float f5) {
        float fIntBitsToFloat = Float.intBitsToFloat(((int) ((((long) Float.floatToRawIntBits(f5)) & 8589934591L) / ((long) 3))) + 709952852);
        float f6 = fIntBitsToFloat - ((fIntBitsToFloat - (f5 / (fIntBitsToFloat * fIntBitsToFloat))) * 0.33333334f);
        return f6 - ((f6 - (f5 / (f6 * f6))) * 0.33333334f);
    }

    public static final int u(d2.k kVar, int i5) {
        boolean z2 = l.f(kVar.f1615d, d2.k.f1612e.f1615d) >= 0;
        boolean z3 = i5 == 1;
        if (z3 && z2) {
            return 3;
        }
        if (z2) {
            return 1;
        }
        return z3 ? 2 : 0;
    }

    public static final float v(Layout layout, int i5, Paint paint) {
        float fAbs;
        float width;
        float lineLeft = layout.getLineLeft(i5);
        z1.x xVar = z1.z.f7167a;
        if (layout.getEllipsisCount(i5) <= 0 || layout.getParagraphDirection(i5) != 1 || lineLeft >= 0.0f) {
            return 0.0f;
        }
        float fMeasureText = paint.measureText("…") + (layout.getPrimaryHorizontal(layout.getEllipsisStart(i5) + layout.getLineStart(i5)) - lineLeft);
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i5);
        if ((paragraphAlignment == null ? -1 : b2.d.f577a[paragraphAlignment.ordinal()]) == 1) {
            fAbs = Math.abs(lineLeft);
            width = (layout.getWidth() - fMeasureText) / 2.0f;
        } else {
            fAbs = Math.abs(lineLeft);
            width = layout.getWidth() - fMeasureText;
        }
        return width + fAbs;
    }

    public static final float w(Layout layout, int i5, Paint paint) {
        float width;
        float width2;
        z1.x xVar = z1.z.f7167a;
        if (layout.getEllipsisCount(i5) <= 0) {
            return 0.0f;
        }
        if (layout.getParagraphDirection(i5) != -1 || layout.getWidth() >= layout.getLineRight(i5)) {
            return 0.0f;
        }
        float fMeasureText = paint.measureText("…") + (layout.getLineRight(i5) - layout.getPrimaryHorizontal(layout.getEllipsisStart(i5) + layout.getLineStart(i5)));
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i5);
        if ((paragraphAlignment != null ? b2.d.f577a[paragraphAlignment.ordinal()] : -1) == 1) {
            width = layout.getWidth() - layout.getLineRight(i5);
            width2 = (layout.getWidth() - fMeasureText) / 2.0f;
        } else {
            width = layout.getWidth() - layout.getLineRight(i5);
            width2 = layout.getWidth() - fMeasureText;
        }
        return width - width2;
    }

    public static Set x() {
        try {
            Object objInvoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);
            if (objInvoke == null) {
                return Collections.EMPTY_SET;
            }
            Set set = (Set) objInvoke;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof int[])) {
                    return Collections.EMPTY_SET;
                }
            }
            return set;
        } catch (Throwable unused) {
            return Collections.EMPTY_SET;
        }
    }

    public static final Bundle y(String str, Bundle bundle) {
        l.e("key", str);
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 != null) {
            return bundle2;
        }
        throw new IllegalArgumentException("No valid saved state was found for the key '" + str + "'. It may be missing, null, or not of the expected type. This can occur if the value was saved with a different type or if the saved state was modified unexpectedly.");
    }

    public static final y1.f z(x xVar) {
        y1.f fVar = xVar.f2062a;
        long j5 = xVar.f2063b;
        fVar.getClass();
        return fVar.subSequence(g0.e(j5), g0.d(j5));
    }

    public abstract int F(int i5);

    public abstract int H(int i5);

    @Override // a2.f
    public int e(int i5) {
        return H(i5);
    }

    @Override // a2.f
    public int h(int i5) {
        return F(i5);
    }

    @Override // a2.f
    public int k(int i5) {
        int iF = F(i5);
        if (iF == -1 || F(iF) == -1) {
            return -1;
        }
        return iF;
    }

    @Override // a2.f
    public int l(int i5) {
        int iH = H(i5);
        if (iH == -1 || H(iH) == -1) {
            return -1;
        }
        return iH;
    }

    public abstract boolean p(o1.g gVar);

    public abstract Object t(o1.g gVar);
}
