package v;

import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l implements n1.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o0 f5952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a4.c f5953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e2.x f5954c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ e2.r f5955d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ k2.b f5956e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f5957f;

    public l(o0 o0Var, a4.c cVar, e2.x xVar, e2.r rVar, k2.b bVar, int i5) {
        this.f5952a = o0Var;
        this.f5953b = cVar;
        this.f5954c = xVar;
        this.f5955d = rVar;
        this.f5956e = bVar;
        this.f5957f = i5;
    }

    @Override // n1.f0
    public final int d(n1.m mVar, List list, int i5) {
        o0 o0Var = this.f5952a;
        o0Var.f6006a.a(mVar.getLayoutDirection());
        f3.a aVar = o0Var.f6006a.f6107j;
        if (aVar != null) {
            return l0.k(aVar.c());
        }
        throw new IllegalStateException("layoutIntrinsics must be called first");
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0217  */
    /* JADX WARN: Code duplicated, block: B:101:0x0222  */
    /* JADX WARN: Code duplicated, block: B:104:0x026e  */
    /* JADX WARN: Code duplicated, block: B:106:0x0276  */
    /* JADX WARN: Code duplicated, block: B:64:0x0123  */
    /* JADX WARN: Code duplicated, block: B:68:0x012a  */
    /* JADX WARN: Code duplicated, block: B:70:0x012e  */
    /* JADX WARN: Code duplicated, block: B:73:0x0140  */
    /* JADX WARN: Code duplicated, block: B:75:0x014e  */
    /* JADX WARN: Code duplicated, block: B:76:0x0150  */
    /* JADX WARN: Code duplicated, block: B:78:0x0156  */
    /* JADX WARN: Code duplicated, block: B:79:0x0158  */
    /* JADX WARN: Code duplicated, block: B:83:0x0161  */
    /* JADX WARN: Code duplicated, block: B:86:0x0170  */
    /* JADX WARN: Code duplicated, block: B:88:0x0175  */
    /* JADX WARN: Code duplicated, block: B:92:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:94:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:95:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:97:0x020d  */
    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        o0 o0Var;
        y1.e0 e0Var;
        y1.e0 e0Var2;
        int i5;
        y1.e0 e0Var3;
        l lVar;
        o0 o0Var2;
        int i6;
        int iK;
        n1.p pVar;
        int i7;
        int iK2;
        int i8;
        int i9;
        f3.a aVar;
        int iMin;
        int iMin2;
        int i10;
        boolean z2;
        f3.a aVar2;
        o0 o0Var3 = this.f5952a;
        p0.f fVarD = p0.r.d();
        a4.c cVarE = fVarD != null ? fVarD.e() : null;
        p0.f fVarG = p0.r.g(fVarD);
        try {
            e1 e1VarD = o0Var3.d();
            p0.r.j(fVarD, fVarG, cVarE);
            y1.e0 e0Var4 = e1VarD != null ? e1VarD.f5899a : null;
            t0 t0Var = o0Var3.f6006a;
            k2.k layoutDirection = h0Var.getLayoutDirection();
            int i11 = t0Var.f6103f;
            boolean z3 = t0Var.f6102e;
            int i12 = t0Var.f6100c;
            if (e0Var4 != null) {
                y1.m mVar = e0Var4.f6754b;
                y1.d0 d0Var = e0Var4.f6753a;
                y1.f fVar = t0Var.f6098a;
                y1.h0 h0Var2 = t0Var.f6099b;
                List list2 = t0Var.f6106i;
                k2.b bVar = t0Var.f6104g;
                d2.d dVar = t0Var.f6105h;
                y1.e0 e0Var5 = e0Var4;
                if (!mVar.f6789a.b()) {
                    y1.f fVar2 = d0Var.f6743a;
                    o0Var = o0Var3;
                    long j6 = d0Var.f6752j;
                    if (b4.l.a(fVar2, fVar) && d0Var.f6744b.c(h0Var2) && b4.l.a(d0Var.f6745c, list2) && d0Var.f6746d == i12 && d0Var.f6747e == z3 && d0Var.f6748f == i11 && b4.l.a(d0Var.f6749g, bVar) && d0Var.f6750h == layoutDirection && b4.l.a(d0Var.f6751i, dVar) && k2.a.k(j5) == k2.a.k(j6)) {
                        if ((z3 || i11 == 2) && !(k2.a.i(j5) == k2.a.i(j6) && k2.a.h(j5) == k2.a.h(j6))) {
                            j5 = j5;
                            i7 = 2;
                            e0Var = e0Var5;
                            t0Var.a(layoutDirection);
                            iK2 = k2.a.k(j5);
                            i8 = ((!z3 || i11 == i7) && k2.a.e(j5)) ? k2.a.i(j5) : Integer.MAX_VALUE;
                            if (z3 && i11 == i7) {
                                i9 = 1;
                            } else {
                                i9 = i12;
                            }
                            if (iK2 != i8) {
                                aVar2 = t0Var.f6107j;
                                if (aVar2 != null) {
                                    throw new IllegalStateException("layoutIntrinsics must be called first");
                                }
                                i8 = d4.a.m(l0.k(aVar2.c()), iK2, i8);
                            }
                            aVar = t0Var.f6107j;
                            if (aVar != null) {
                                throw new IllegalStateException("layoutIntrinsics must be called first");
                            }
                            int iH = k2.a.h(j5);
                            iMin = Math.min(0, 262142);
                            if (i8 == Integer.MAX_VALUE) {
                                iMin2 = Integer.MAX_VALUE;
                            } else {
                                iMin2 = Math.min(i8, 262142);
                            }
                            if (iMin2 == Integer.MAX_VALUE) {
                                i10 = iMin;
                            } else {
                                i10 = iMin2;
                            }
                            int iH2 = h1.c.h(i10);
                            long jB = h1.c.b(iMin, iMin2, Math.min(iH2, 0), iH != Integer.MAX_VALUE ? Math.min(iH2, iH) : Integer.MAX_VALUE);
                            if (i11 == i7) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            y1.m mVar2 = new y1.m(aVar, jB, i9, z2);
                            i5 = i7;
                            e0Var2 = e0Var;
                            e0Var3 = new y1.e0(new y1.d0(t0Var.f6098a, t0Var.f6099b, t0Var.f6106i, t0Var.f6100c, t0Var.f6102e, t0Var.f6103f, t0Var.f6104g, layoutDirection, t0Var.f6105h, j5), mVar2, h1.c.n(j5, a.a.f(l0.k(mVar2.f6792d), l0.k(mVar2.f6793e))));
                        } else {
                            i5 = 2;
                            e0Var3 = new y1.e0(new y1.d0(d0Var.f6743a, t0Var.f6099b, d0Var.f6745c, d0Var.f6746d, d0Var.f6747e, d0Var.f6748f, d0Var.f6749g, d0Var.f6750h, d0Var.f6751i, j5), mVar, h1.c.n(j5, a.a.f(l0.k(mVar.f6792d), l0.k(mVar.f6793e))));
                            e0Var2 = e0Var5;
                        }
                    }
                    long j7 = e0Var3.f6755c;
                    Integer numValueOf = Integer.valueOf((int) (j7 >> 32));
                    Integer numValueOf2 = Integer.valueOf((int) (j7 & 4294967295L));
                    int iIntValue = numValueOf.intValue();
                    int iIntValue2 = numValueOf2.intValue();
                    if (b4.l.a(e0Var2, e0Var3)) {
                        lVar = this;
                        o0Var2 = o0Var;
                        i6 = 0;
                    } else {
                        if (e1VarD != 0) {
                            pVar = e1VarD.f5901c;
                        } else {
                            pVar = null;
                        }
                        o0Var2 = o0Var;
                        o0Var2.f6014i.setValue(new e1(e0Var3, pVar));
                        i6 = 0;
                        o0Var2.f6021p = false;
                        lVar = this;
                        lVar.f5953b.c(e0Var3);
                        l0.q(o0Var2, lVar.f5954c, lVar.f5955d);
                    }
                    if (lVar.f5957f == 1) {
                        iK = l0.k(e0Var3.f6754b.b(i6));
                    } else {
                        iK = i6;
                    }
                    o0Var2.f6012g.setValue(new k2.e(lVar.f5956e.i0(iK)));
                    p3.f[] fVarArr = {new p3.f(n1.c.f4106a, Integer.valueOf(Math.round(e0Var3.f6756d))), new p3.f(n1.c.f4107b, Integer.valueOf(Math.round(e0Var3.f6757e)))};
                    LinkedHashMap linkedHashMap = new LinkedHashMap(q3.v.F(i5));
                    q3.v.G(linkedHashMap, fVarArr);
                    return h0Var.F(iIntValue, iIntValue2, linkedHashMap, e.f5884g);
                }
                o0Var = o0Var3;
                e0Var = e0Var5;
            } else {
                j5 = j5;
                o0Var = o0Var3;
                e0Var = e0Var4;
            }
            i7 = 2;
            t0Var.a(layoutDirection);
            iK2 = k2.a.k(j5);
            if (z3) {
            }
            if (z3) {
                i9 = i12;
            } else {
                i9 = i12;
            }
            if (iK2 != i8) {
                aVar2 = t0Var.f6107j;
                if (aVar2 != null) {
                    throw new IllegalStateException("layoutIntrinsics must be called first");
                }
                i8 = d4.a.m(l0.k(aVar2.c()), iK2, i8);
            }
            aVar = t0Var.f6107j;
            if (aVar != null) {
                throw new IllegalStateException("layoutIntrinsics must be called first");
            }
            int iH3 = k2.a.h(j5);
            iMin = Math.min(0, 262142);
            if (i8 == Integer.MAX_VALUE) {
                iMin2 = Integer.MAX_VALUE;
            } else {
                iMin2 = Math.min(i8, 262142);
            }
            if (iMin2 == Integer.MAX_VALUE) {
                i10 = iMin;
            } else {
                i10 = iMin2;
            }
            int iH4 = h1.c.h(i10);
            long jB2 = h1.c.b(iMin, iMin2, Math.min(iH4, 0), iH3 != Integer.MAX_VALUE ? Math.min(iH4, iH3) : Integer.MAX_VALUE);
            if (i11 == i7) {
                z2 = true;
            } else {
                z2 = false;
            }
            y1.m mVar3 = new y1.m(aVar, jB2, i9, z2);
            i5 = i7;
            e0Var2 = e0Var;
            e0Var3 = new y1.e0(new y1.d0(t0Var.f6098a, t0Var.f6099b, t0Var.f6106i, t0Var.f6100c, t0Var.f6102e, t0Var.f6103f, t0Var.f6104g, layoutDirection, t0Var.f6105h, j5), mVar3, h1.c.n(j5, a.a.f(l0.k(mVar3.f6792d), l0.k(mVar3.f6793e))));
            long j8 = e0Var3.f6755c;
            Integer numValueOf3 = Integer.valueOf((int) (j8 >> 32));
            Integer numValueOf4 = Integer.valueOf((int) (j8 & 4294967295L));
            int iIntValue3 = numValueOf3.intValue();
            int iIntValue4 = numValueOf4.intValue();
            if (b4.l.a(e0Var2, e0Var3)) {
                if (e1VarD != 0) {
                    pVar = e1VarD.f5901c;
                } else {
                    pVar = null;
                }
                o0Var2 = o0Var;
                o0Var2.f6014i.setValue(new e1(e0Var3, pVar));
                i6 = 0;
                o0Var2.f6021p = false;
                lVar = this;
                lVar.f5953b.c(e0Var3);
                l0.q(o0Var2, lVar.f5954c, lVar.f5955d);
            } else {
                lVar = this;
                o0Var2 = o0Var;
                i6 = 0;
            }
            if (lVar.f5957f == 1) {
                iK = l0.k(e0Var3.f6754b.b(i6));
            } else {
                iK = i6;
            }
            o0Var2.f6012g.setValue(new k2.e(lVar.f5956e.i0(iK)));
            p3.f[] fVarArr2 = {new p3.f(n1.c.f4106a, Integer.valueOf(Math.round(e0Var3.f6756d))), new p3.f(n1.c.f4107b, Integer.valueOf(Math.round(e0Var3.f6757e)))};
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(q3.v.F(i5));
            q3.v.G(linkedHashMap2, fVarArr2);
            return h0Var.F(iIntValue3, iIntValue4, linkedHashMap2, e.f5884g);
        } catch (Throwable th) {
            p0.r.j(fVarD, fVarG, cVarE);
            throw th;
        }
    }
}
