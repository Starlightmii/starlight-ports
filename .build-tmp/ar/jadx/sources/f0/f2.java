package f0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f2 implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2166d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2167e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2168f;

    public /* synthetic */ f2(Object obj, int i5, Object obj2) {
        this.f2166d = i5;
        this.f2167e = obj;
        this.f2168f = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0087  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x00f8 A[LOOP:2: B:60:0x00c3->B:70:0x00f8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x00fb A[SYNTHETIC] */
    @Override // a4.a
    public final Object a() {
        List listE0;
        s2 s2VarC;
        switch (this.f2166d) {
            case 0:
                j.a0 a0Var = (j.a0) this.f2167e;
                e0 e0Var = (e0) this.f2168f;
                Object[] objArr = a0Var.f3067b;
                long[] jArr = a0Var.f3066a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i5 = 0;
                    while (true) {
                        long j5 = jArr[i5];
                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i6 = 8 - ((~(i5 - length)) >>> 31);
                            for (int i7 = 0; i7 < i6; i7++) {
                                if ((255 & j5) < 128) {
                                    e0Var.z(objArr[(i5 << 3) + i7]);
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
                return p3.n.f5026a;
            default:
                v vVar = ((q0.d) this.f2167e).f5090d;
                t2 t2Var = vVar.f2356c;
                s2 s2VarC2 = t2Var.c();
                int i8 = 0;
                while (true) {
                    try {
                        q0.i iVar = null;
                        if (i8 < t2Var.f2334e) {
                            boolean zL = s2VarC2.l(i8);
                            Object obj = this.f2168f;
                            if (zL) {
                                Object objN = s2VarC2.n(i8);
                                if (objN != obj) {
                                    o2 o2Var = objN instanceof o2 ? (o2) objN : null;
                                    if ((o2Var != null ? o2Var.f2285a : null) == obj) {
                                    }
                                }
                                q0.i iVar2 = new q0.i(i8, null);
                                s2VarC2.c();
                                iVar = iVar2;
                                if (iVar != null) {
                                    int i9 = iVar.f5094a;
                                    Integer num = iVar.f5095b;
                                    s2VarC = t2Var.c();
                                    try {
                                        ArrayList arrayListP = p3.j.P(s2VarC, i9, num);
                                        s2VarC.c();
                                        listE0 = q3.l.e0(arrayListP, vVar.D());
                                    } catch (Throwable th) {
                                        s2VarC.c();
                                        throw th;
                                    }
                                } else {
                                    listE0 = q3.s.f5477d;
                                }
                                return new q0.a(listE0);
                            }
                            int[] iArr = s2VarC2.f2306b;
                            int i10 = i8 + 1;
                            int iC = (i10 < s2VarC2.f2307c ? iArr[(i10 * 5) + 4] : s2VarC2.f2309e) - v2.c(iArr, i8);
                            int i11 = 0;
                            while (true) {
                                if (i11 >= iC) {
                                    i8 = i10;
                                } else {
                                    Object objH = s2VarC2.h(i8, i11);
                                    if (objH != obj) {
                                        o2 o2Var2 = objH instanceof o2 ? (o2) objH : null;
                                        if ((o2Var2 != null ? o2Var2.f2285a : null) != obj) {
                                            i11++;
                                        }
                                    }
                                    iVar = new q0.i(i8, Integer.valueOf(i11));
                                }
                            }
                        }
                        s2VarC2.c();
                        if (iVar != null) {
                            int i12 = iVar.f5094a;
                            Integer num2 = iVar.f5095b;
                            s2VarC = t2Var.c();
                            ArrayList arrayListP2 = p3.j.P(s2VarC, i12, num2);
                            s2VarC.c();
                            listE0 = q3.l.e0(arrayListP2, vVar.D());
                        } else {
                            listE0 = q3.s.f5477d;
                        }
                        return new q0.a(listE0);
                    } catch (Throwable th2) {
                        s2VarC2.c();
                        throw th2;
                    }
                }
        }
    }
}
