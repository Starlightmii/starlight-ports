package y1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f6767a = new f("", null, 6);

    public static final ArrayList a(List list, int i5, int i6) {
        if (i5 > i6) {
            throw new IllegalArgumentException(("start (" + i5 + ") should be less than or equal to end (" + i6 + ')').toString());
        }
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                Object obj = list.get(i7);
                d dVar = (d) obj;
                if (c(i5, i6, dVar.f6740b, dVar.f6741c)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i8 = 0; i8 < size2; i8++) {
                d dVar2 = (d) arrayList.get(i8);
                arrayList2.add(new d(dVar2.f6739a, Math.max(i5, dVar2.f6740b) - i5, Math.min(i6, dVar2.f6741c) - i5, dVar2.f6742d));
            }
            if (!arrayList2.isEmpty()) {
                return arrayList2;
            }
        }
        return null;
    }

    public static final List b(f fVar, int i5, int i6) {
        List list;
        if (i5 == i6 || (list = fVar.f6760e) == null) {
            return null;
        }
        if (i5 == 0 && i6 >= fVar.f6759d.length()) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            Object obj = list.get(i7);
            d dVar = (d) obj;
            if (c(i5, i6, dVar.f6740b, dVar.f6741c)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i8 = 0; i8 < size2; i8++) {
            d dVar2 = (d) arrayList.get(i8);
            arrayList2.add(new d(d4.a.m(dVar2.f6740b, i5, i6) - i5, d4.a.m(dVar2.f6741c, i5, i6) - i5, dVar2.f6739a));
        }
        return arrayList2;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x001f  */
    /* JADX WARN: Code duplicated, block: B:19:0x0025 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0027  */
    /* JADX WARN: Code duplicated, block: B:21:0x0029  */
    /* JADX WARN: Code duplicated, block: B:23:0x002c  */
    /* JADX WARN: Code duplicated, block: B:24:0x002e  */
    public static final boolean c(int i5, int i6, int i7, int i8) {
        boolean z2;
        boolean z3;
        if (Math.max(i5, i7) >= Math.min(i6, i8)) {
            if (i5 > i7 || i8 > i6) {
                if (i7 <= i5 && i6 <= i8) {
                    if (i8 == i6) {
                        if (i5 == i6) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (i7 == i8) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z2 == z3) {
                        }
                    }
                }
                return false;
            }
            if (i6 == i8) {
                if ((i7 == i8) != (i5 == i6)) {
                    if (i7 <= i5) {
                        if (i8 == i6) {
                            if (i5 == i6) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (i7 == i8) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z2 == z3) {
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
