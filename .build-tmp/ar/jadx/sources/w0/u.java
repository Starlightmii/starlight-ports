package w0;

import java.util.Comparator;
import p1.d0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f6271a = new u();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        r rVar = (r) obj;
        r rVar2 = (r) obj2;
        if (rVar == null) {
            throw new IllegalArgumentException("compare requires non-null focus targets");
        }
        if (rVar2 == null) {
            throw new IllegalArgumentException("compare requires non-null focus targets");
        }
        int i5 = 0;
        if (d.t(rVar) && d.t(rVar2)) {
            d0 d0VarS = p1.f.s(rVar);
            d0 d0VarS2 = p1.f.s(rVar2);
            if (!b4.l.a(d0VarS, d0VarS2)) {
                Object[] objArr = new d0[16];
                int i6 = 0;
                while (d0VarS != null) {
                    int i7 = i6 + 1;
                    if (objArr.length < i7) {
                        int length = objArr.length;
                        Object[] objArr2 = new Object[Math.max(i7, length * 2)];
                        System.arraycopy(objArr, 0, objArr2, 0, length);
                        objArr = objArr2;
                    }
                    if (i6 != 0) {
                        System.arraycopy(objArr, 0, objArr, 0 + 1, i6 + 0);
                    }
                    objArr[0] = d0VarS;
                    i6++;
                    d0VarS = d0VarS.r();
                }
                Object[] objArr3 = new d0[16];
                int i8 = 0;
                while (d0VarS2 != null) {
                    int i9 = i8 + 1;
                    if (objArr3.length < i9) {
                        int length2 = objArr3.length;
                        Object[] objArr4 = new Object[Math.max(i9, length2 * 2)];
                        System.arraycopy(objArr3, 0, objArr4, 0, length2);
                        objArr3 = objArr4;
                    }
                    if (i8 != 0) {
                        System.arraycopy(objArr3, 0, objArr3, 0 + 1, i8 + 0);
                    }
                    objArr3[0] = d0VarS2;
                    i8++;
                    d0VarS2 = d0VarS2.r();
                }
                int iMin = Math.min(i6 - 1, i8 - 1);
                if (iMin >= 0) {
                    while (b4.l.a(objArr[i5], objArr3[i5])) {
                        if (i5 != iMin) {
                            i5++;
                        }
                    }
                    return b4.l.f(((d0) objArr[i5]).s(), ((d0) objArr3[i5]).s());
                }
                throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.");
            }
        } else {
            if (d.t(rVar)) {
                return -1;
            }
            if (d.t(rVar2)) {
                return 1;
            }
        }
        return 0;
    }
}
