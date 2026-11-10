package b;

import android.os.Build;
import android.view.View;
import android.view.contentcapture.ContentCaptureSession;
import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h0 extends b4.j implements a4.a {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f336k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h0(int i5, Object obj, Class cls, String str, String str2, int i6, int i7, int i8) {
        super(i5, obj, cls, str, str2, i6, i7);
        this.f336k = i8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a4.a
    public final Object a() {
        ContentCaptureSession contentCaptureSessionA;
        j.a0 a0Var;
        long j5;
        char c5;
        j.a0 a0Var2;
        Object[] objArr;
        long[] jArr;
        j.a0 a0Var3;
        Object[] objArr2;
        long j6;
        long[] jArr2;
        h0.e eVar;
        long[] jArr3;
        long j7;
        Object[] objArr3;
        long[] jArr4;
        j.a0 a0Var4;
        Object[] objArr4;
        char c6;
        long j8;
        long[] jArr5;
        int i5;
        long j9;
        switch (this.f336k) {
            case 0:
                ((i0) this.f681e).d();
                return p3.n.f5026a;
            case 1:
                ((i0) this.f681e).d();
                return p3.n.f5026a;
            case 2:
                View view = (View) this.f681e;
                int i6 = Build.VERSION.SDK_INT;
                if (i6 >= 30) {
                    t1.f.a(view, 1);
                }
                if (i6 < 29 || (contentCaptureSessionA = t1.e.a(view)) == null) {
                    return null;
                }
                return new x.p(contentCaptureSessionA, view);
            case 3:
                q1.u uVar = (q1.u) this.f681e;
                if (uVar.isFocused() || uVar.hasFocus()) {
                    uVar.clearFocus();
                }
                return p3.n.f5026a;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return ((q1.u) this.f681e).u();
            case 5:
                w0.e eVar2 = (w0.e) this.f681e;
                j.a0 a0Var5 = eVar2.f6230d;
                j.a0 a0Var6 = eVar2.f6232f;
                j.a0 a0Var7 = eVar2.f6229c;
                j.a0 a0Var8 = eVar2.f6231e;
                Object[] objArr5 = a0Var8.f3067b;
                long[] jArr6 = a0Var8.f3066a;
                int length = jArr6.length - 2;
                if (length >= 0) {
                    int i7 = 0;
                    char c7 = 7;
                    j5 = 255;
                    while (true) {
                        long j10 = jArr6[i7];
                        if ((((~j10) << c7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i8 = 8 - ((~(i7 - length)) >>> 31);
                            int i9 = 0;
                            while (i9 < i8) {
                                if ((j10 & 255) < 128) {
                                    c6 = c7;
                                    r0.o oVar = (r0.o) ((w0.l) objArr5[(i7 << 3) + i9]);
                                    a0Var4 = a0Var8;
                                    r0.o oVarE = oVar.f5681d;
                                    objArr4 = objArr5;
                                    if (oVarE.f5693p) {
                                        h0.e eVar3 = null;
                                        while (oVarE != null) {
                                            h0.e eVar4 = eVar3;
                                            if (oVarE instanceof w0.r) {
                                                a0Var7.a((w0.r) oVarE);
                                            } else {
                                                if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                                    r0.o oVar2 = ((p1.m) oVarE).f4921r;
                                                    jArr5 = jArr6;
                                                    int i10 = 0;
                                                    while (oVar2 != null) {
                                                        int i11 = i9;
                                                        if ((oVar2.f5683f & 1024) == 0) {
                                                            j10 = j10;
                                                        } else {
                                                            i10++;
                                                            if (i10 == 1) {
                                                                oVarE = oVar2;
                                                                j10 = j10;
                                                            } else {
                                                                h0.e eVar5 = eVar4 == null ? new h0.e(new r0.o[16]) : eVar4;
                                                                if (oVarE != null) {
                                                                    eVar5.b(oVarE);
                                                                    oVarE = null;
                                                                }
                                                                eVar5.b(oVar2);
                                                                eVar4 = eVar5;
                                                                i10 = i10;
                                                            }
                                                        }
                                                        oVar2 = oVar2.f5686i;
                                                        i9 = i11;
                                                        j10 = j10;
                                                    }
                                                    i5 = i9;
                                                    j9 = j10;
                                                    if (i10 == 1) {
                                                        eVar3 = eVar4;
                                                    }
                                                    jArr6 = jArr5;
                                                    i9 = i5;
                                                    j10 = j9;
                                                }
                                                eVar3 = eVar4;
                                                oVarE = p1.f.e(eVar3);
                                                jArr6 = jArr5;
                                                i9 = i5;
                                                j10 = j9;
                                            }
                                            jArr5 = jArr6;
                                            i5 = i9;
                                            j9 = j10;
                                            eVar3 = eVar4;
                                            oVarE = p1.f.e(eVar3);
                                            jArr6 = jArr5;
                                            i9 = i5;
                                            j10 = j9;
                                        }
                                        jArr6 = jArr6;
                                        i9 = i9;
                                        j8 = j10;
                                        r0.o oVar3 = oVar.f5681d;
                                        if (!oVar3.f5693p) {
                                            throw new IllegalStateException("visitChildren called on an unattached node");
                                        }
                                        h0.e eVar6 = new h0.e(new r0.o[16]);
                                        r0.o oVar4 = oVar3.f5686i;
                                        if (oVar4 == null) {
                                            p1.f.b(eVar6, oVar3);
                                        } else {
                                            eVar6.b(oVar4);
                                        }
                                        while (eVar6.k()) {
                                            r0.o oVarE2 = (r0.o) eVar6.m(eVar6.f2578f - 1);
                                            if ((oVarE2.f5684g & 1024) == 0) {
                                                p1.f.b(eVar6, oVarE2);
                                            } else {
                                                while (oVarE2 != null) {
                                                    if ((oVarE2.f5683f & 1024) != 0) {
                                                        h0.e eVar7 = null;
                                                        while (oVarE2 != null) {
                                                            if (oVarE2 instanceof w0.r) {
                                                                a0Var7.a((w0.r) oVarE2);
                                                            } else if ((oVarE2.f5683f & 1024) != 0 && (oVarE2 instanceof p1.m)) {
                                                                int i12 = 0;
                                                                for (r0.o oVar5 = ((p1.m) oVarE2).f4921r; oVar5 != null; oVar5 = oVar5.f5686i) {
                                                                    if ((oVar5.f5683f & 1024) != 0) {
                                                                        i12++;
                                                                        if (i12 == 1) {
                                                                            oVarE2 = oVar5;
                                                                        } else {
                                                                            if (eVar7 == null) {
                                                                                eVar7 = new h0.e(new r0.o[16]);
                                                                            }
                                                                            if (oVarE2 != null) {
                                                                                eVar7.b(oVarE2);
                                                                                oVarE2 = null;
                                                                            }
                                                                            eVar7.b(oVar5);
                                                                        }
                                                                    }
                                                                }
                                                                if (i12 == 1) {
                                                                }
                                                            }
                                                            oVarE2 = p1.f.e(eVar7);
                                                        }
                                                    }
                                                    oVarE2 = oVarE2.f5686i;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    j10 = j8 >> 8;
                                    i9++;
                                    a0Var8 = a0Var4;
                                    c7 = c6;
                                    objArr5 = objArr4;
                                    jArr6 = jArr6;
                                } else {
                                    a0Var4 = a0Var8;
                                    objArr4 = objArr5;
                                    c6 = c7;
                                }
                                j8 = j10;
                                j10 = j8 >> 8;
                                i9++;
                                a0Var8 = a0Var4;
                                c7 = c6;
                                objArr5 = objArr4;
                                jArr6 = jArr6;
                            }
                            a0Var = a0Var8;
                            objArr3 = objArr5;
                            jArr4 = jArr6;
                            c5 = c7;
                            if (i8 == 8) {
                            }
                        } else {
                            a0Var = a0Var8;
                            objArr3 = objArr5;
                            jArr4 = jArr6;
                            c5 = c7;
                        }
                        if (i7 != length) {
                            i7++;
                            a0Var8 = a0Var;
                            c7 = c5;
                            objArr5 = objArr3;
                            jArr6 = jArr4;
                        }
                    }
                } else {
                    a0Var = a0Var8;
                    j5 = 255;
                    c5 = 7;
                }
                a0Var.b();
                Object[] objArr6 = a0Var5.f3067b;
                long[] jArr7 = a0Var5.f3066a;
                int length2 = jArr7.length - 2;
                if (length2 >= 0) {
                    int i13 = 0;
                    while (true) {
                        long j11 = jArr7[i13];
                        if ((((~j11) << c5) & j11 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i14 = 8 - ((~(i13 - length2)) >>> 31);
                            int i15 = 0;
                            while (i15 < i14) {
                                if ((j11 & j5) < 128) {
                                    w0.c cVar = (w0.c) objArr6[(i13 << 3) + i15];
                                    r0.o oVar6 = (r0.o) cVar;
                                    a0Var3 = a0Var5;
                                    r0.o oVar7 = oVar6.f5681d;
                                    objArr2 = objArr6;
                                    boolean z2 = oVar7.f5693p;
                                    w0.q qVar = w0.q.f6260f;
                                    if (z2) {
                                        w0.r rVar = null;
                                        h0.e eVar8 = null;
                                        boolean z3 = false;
                                        r0.o oVarE3 = oVar7;
                                        boolean z4 = true;
                                        while (oVarE3 != null) {
                                            w0.q qVar2 = qVar;
                                            if (oVarE3 instanceof w0.r) {
                                                w0.r rVar2 = (w0.r) oVarE3;
                                                if (rVar != null) {
                                                    z3 = true;
                                                }
                                                if (a0Var7.c(rVar2)) {
                                                    a0Var6.a(rVar2);
                                                    z4 = false;
                                                }
                                                rVar = rVar2;
                                            } else {
                                                if ((oVarE3.f5683f & 1024) != 0 && (oVarE3 instanceof p1.m)) {
                                                    r0.o oVar8 = ((p1.m) oVarE3).f4921r;
                                                    jArr3 = jArr7;
                                                    int i16 = 0;
                                                    while (oVar8 != null) {
                                                        long j12 = j11;
                                                        if ((oVar8.f5683f & 1024) != 0) {
                                                            i16++;
                                                            if (i16 == 1) {
                                                                oVarE3 = oVar8;
                                                            } else {
                                                                h0.e eVar9 = eVar8 == null ? new h0.e(new r0.o[16]) : eVar8;
                                                                if (oVarE3 != null) {
                                                                    eVar9.b(oVarE3);
                                                                    oVarE3 = null;
                                                                }
                                                                eVar9.b(oVar8);
                                                                eVar8 = eVar9;
                                                                i16 = i16;
                                                            }
                                                        }
                                                        oVar8 = oVar8.f5686i;
                                                        j11 = j12;
                                                    }
                                                    j7 = j11;
                                                    if (i16 == 1) {
                                                    }
                                                    qVar = qVar2;
                                                    jArr7 = jArr3;
                                                    j11 = j7;
                                                }
                                                oVarE3 = p1.f.e(eVar8);
                                                qVar = qVar2;
                                                jArr7 = jArr3;
                                                j11 = j7;
                                            }
                                            jArr3 = jArr7;
                                            j7 = j11;
                                            oVarE3 = p1.f.e(eVar8);
                                            qVar = qVar2;
                                            jArr7 = jArr3;
                                            j11 = j7;
                                        }
                                        w0.q qVar3 = qVar;
                                        jArr2 = jArr7;
                                        j6 = j11;
                                        r0.o oVar9 = oVar6.f5681d;
                                        if (!oVar9.f5693p) {
                                            throw new IllegalStateException("visitChildren called on an unattached node");
                                        }
                                        h0.e eVar10 = new h0.e(new r0.o[16]);
                                        r0.o oVar10 = oVar9.f5686i;
                                        if (oVar10 == null) {
                                            p1.f.b(eVar10, oVar9);
                                        } else {
                                            eVar10.b(oVar10);
                                        }
                                        while (eVar10.k()) {
                                            r0.o oVarE4 = (r0.o) eVar10.m(eVar10.f2578f - 1);
                                            if ((oVarE4.f5684g & 1024) == 0) {
                                                p1.f.b(eVar10, oVarE4);
                                            } else {
                                                while (true) {
                                                    if (oVarE4 != null) {
                                                        if ((oVarE4.f5683f & 1024) != 0) {
                                                            h0.e eVar11 = null;
                                                            while (oVarE4 != null) {
                                                                if (oVarE4 instanceof w0.r) {
                                                                    w0.r rVar3 = (w0.r) oVarE4;
                                                                    if (rVar != null) {
                                                                        z3 = true;
                                                                    }
                                                                    if (a0Var7.c(rVar3)) {
                                                                        a0Var6.a(rVar3);
                                                                        z4 = false;
                                                                    }
                                                                    rVar = rVar3;
                                                                } else {
                                                                    if ((oVarE4.f5683f & 1024) != 0 && (oVarE4 instanceof p1.m)) {
                                                                        r0.o oVar11 = ((p1.m) oVarE4).f4921r;
                                                                        int i17 = 0;
                                                                        while (oVar11 != null) {
                                                                            if ((oVar11.f5683f & 1024) != 0) {
                                                                                i17++;
                                                                                if (i17 == 1) {
                                                                                    oVarE4 = oVar11;
                                                                                } else {
                                                                                    if (eVar11 == null) {
                                                                                        eVar11 = new h0.e(new r0.o[16]);
                                                                                    }
                                                                                    if (oVarE4 != null) {
                                                                                        eVar11.b(oVarE4);
                                                                                        oVarE4 = null;
                                                                                    }
                                                                                    eVar11.b(oVar11);
                                                                                }
                                                                                oVar11 = oVar11.f5686i;
                                                                                eVar10 = eVar10;
                                                                            }
                                                                            oVar11 = oVar11.f5686i;
                                                                            eVar10 = eVar10;
                                                                        }
                                                                        eVar = eVar10;
                                                                        if (i17 == 1) {
                                                                        }
                                                                        eVar10 = eVar;
                                                                    }
                                                                    oVarE4 = p1.f.e(eVar11);
                                                                    eVar10 = eVar;
                                                                }
                                                                eVar = eVar10;
                                                                oVarE4 = p1.f.e(eVar11);
                                                                eVar10 = eVar;
                                                            }
                                                        } else {
                                                            oVarE4 = oVarE4.f5686i;
                                                            eVar10 = eVar10;
                                                        }
                                                    }
                                                }
                                            }
                                            eVar10 = eVar10;
                                        }
                                        if (z4) {
                                            cVar.k0(z3 ? w0.d.o(cVar) : rVar != null ? rVar.z0() : qVar3);
                                        }
                                    } else {
                                        cVar.k0(qVar);
                                    }
                                    j11 = j6 >> 8;
                                    i15++;
                                    a0Var5 = a0Var3;
                                    objArr6 = objArr2;
                                    jArr7 = jArr2;
                                } else {
                                    a0Var3 = a0Var5;
                                    objArr2 = objArr6;
                                }
                                jArr2 = jArr7;
                                j6 = j11;
                                j11 = j6 >> 8;
                                i15++;
                                a0Var5 = a0Var3;
                                objArr6 = objArr2;
                                jArr7 = jArr2;
                            }
                            a0Var2 = a0Var5;
                            objArr = objArr6;
                            jArr = jArr7;
                            if (i14 == 8) {
                            }
                        } else {
                            a0Var2 = a0Var5;
                            objArr = objArr6;
                            jArr = jArr7;
                        }
                        if (i13 != length2) {
                            i13++;
                            a0Var5 = a0Var2;
                            objArr6 = objArr;
                            jArr7 = jArr;
                        }
                    }
                } else {
                    a0Var2 = a0Var5;
                }
                a0Var2.b();
                Object[] objArr7 = a0Var7.f3067b;
                long[] jArr8 = a0Var7.f3066a;
                int length3 = jArr8.length - 2;
                if (length3 >= 0) {
                    int i18 = 0;
                    while (true) {
                        long j13 = jArr8[i18];
                        if ((((~j13) << c5) & j13 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i19 = 8 - ((~(i18 - length3)) >>> 31);
                            for (int i20 = 0; i20 < i19; i20++) {
                                if ((j13 & j5) < 128) {
                                    w0.r rVar4 = (w0.r) objArr7[(i18 << 3) + i20];
                                    if (rVar4.f5693p) {
                                        w0.q qVarZ0 = rVar4.z0();
                                        rVar4.C0();
                                        if (qVarZ0 != rVar4.z0() || a0Var6.c(rVar4)) {
                                            w0.d.A(rVar4);
                                        }
                                    }
                                }
                                j13 >>= 8;
                            }
                            if (i19 == 8) {
                            }
                        }
                        if (i18 != length3) {
                            i18++;
                        }
                    }
                }
                a0Var7.b();
                a0Var6.b();
                eVar2.f6228b.a();
                if (!a0Var.g()) {
                    d4.a.L("Unprocessed FocusProperties nodes");
                    throw null;
                }
                if (!a0Var2.g()) {
                    d4.a.L("Unprocessed FocusEvent nodes");
                    throw null;
                }
                if (a0Var7.g()) {
                    return p3.n.f5026a;
                }
                d4.a.L("Unprocessed FocusTarget nodes");
                throw null;
            default:
                androidx.compose.ui.focus.b bVar = (androidx.compose.ui.focus.b) this.f681e;
                if (bVar.f179f.z0() == w0.q.f6260f) {
                    bVar.f176c.a();
                }
                return p3.n.f5026a;
        }
    }
}
