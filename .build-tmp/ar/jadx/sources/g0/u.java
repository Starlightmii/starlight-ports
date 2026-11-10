package g0;

import f0.v2;
import f0.w2;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final u f2506c = new u(1, 0, 2);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        f0.a aVar;
        int iC;
        int iC2 = lVar.c(0);
        if (w2Var.f2403n != 0) {
            f0.z.a("Cannot move a group while inserting");
        }
        if (iC2 < 0) {
            f0.z.a("Parameter offset is out of bounds");
        }
        if (iC2 == 0) {
            return;
        }
        int i5 = w2Var.f2409t;
        int i6 = w2Var.f2411v;
        int i7 = w2Var.f2410u;
        int i8 = i5;
        while (iC2 > 0) {
            i8 += w2Var.f2391b[(w2Var.r(i8) * 5) + 3];
            if (i8 > i7) {
                f0.z.a("Parameter offset is out of bounds");
            }
            iC2--;
        }
        int i9 = w2Var.f2391b[(w2Var.r(i8) * 5) + 3];
        int iG = w2Var.g(w2Var.f2391b, w2Var.r(w2Var.f2409t));
        int iG2 = w2Var.g(w2Var.f2391b, w2Var.r(i8));
        int i10 = i8 + i9;
        int iG3 = w2Var.g(w2Var.f2391b, w2Var.r(i10));
        int i11 = iG3 - iG2;
        w2Var.x(i11, Math.max(w2Var.f2409t - 1, 0));
        w2Var.w(i9);
        int[] iArr = w2Var.f2391b;
        int iR = w2Var.r(i10) * 5;
        q3.k.y(iArr, iArr, w2Var.r(i5) * 5, iR, (i9 * 5) + iR);
        if (i11 > 0) {
            Object[] objArr = w2Var.f2392c;
            int iH = w2Var.h(iG2 + i11);
            System.arraycopy(objArr, iH, objArr, iG, w2Var.h(iG3 + i11) - iH);
        }
        int i12 = iG2 + i11;
        int i13 = i12 - iG;
        int i14 = w2Var.f2400k;
        int i15 = w2Var.f2401l;
        int length = w2Var.f2392c.length;
        int i16 = w2Var.f2402m;
        int i17 = i5 + i9;
        int i18 = i5;
        while (i18 < i17) {
            int iR2 = w2Var.r(i18);
            int i19 = i13;
            int[] iArr2 = iArr;
            iArr2[(iR2 * 5) + 4] = w2.i(w2.i(w2Var.g(iArr, iR2) - i19, i16 < iR2 ? 0 : i14, i15, length), w2Var.f2400k, w2Var.f2401l, w2Var.f2392c.length);
            i18++;
            i13 = i19;
            iArr = iArr2;
            i14 = i14;
        }
        int i20 = i10 + i9;
        int iP = w2Var.p();
        int iB = v2.b(w2Var.f2393d, i10, iP);
        ArrayList arrayList = new ArrayList();
        if (iB >= 0) {
            while (iB < w2Var.f2393d.size() && (iC = w2Var.c((aVar = (f0.a) w2Var.f2393d.get(iB)))) >= i10 && iC < i20) {
                arrayList.add(aVar);
            }
        }
        int i21 = i5 - i10;
        int size = arrayList.size();
        for (int i22 = 0; i22 < size; i22++) {
            f0.a aVar2 = (f0.a) arrayList.get(i22);
            int iC3 = w2Var.c(aVar2) + i21;
            if (iC3 >= w2Var.f2396g) {
                aVar2.f2082a = -(iP - iC3);
            } else {
                aVar2.f2082a = iC3;
            }
            w2Var.f2393d.add(v2.b(w2Var.f2393d, iC3, iP), aVar2);
        }
        if (w2Var.I(i10, i9)) {
            f0.z.a("Unexpectedly removed anchors");
        }
        w2Var.m(i6, w2Var.f2410u, i5);
        if (i11 > 0) {
            w2Var.J(i12, i11, i10 - 1);
        }
    }
}
