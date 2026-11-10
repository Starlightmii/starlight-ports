package j;

import java.util.ConcurrentModificationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f3122a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f3123b = new Object();

    public static final int a(g gVar, Object obj, int i5) {
        int i6 = gVar.f3105f;
        if (i6 == 0) {
            return -1;
        }
        try {
            int iA = k.a.a(gVar.f3103d, i6, i5);
            if (iA < 0 || b4.l.a(obj, gVar.f3104e[iA])) {
                return iA;
            }
            int i7 = iA + 1;
            while (i7 < i6 && gVar.f3103d[i7] == i5) {
                if (b4.l.a(obj, gVar.f3104e[i7])) {
                    return i7;
                }
                i7++;
            }
            for (int i8 = iA - 1; i8 >= 0 && gVar.f3103d[i8] == i5; i8--) {
                if (b4.l.a(obj, gVar.f3104e[i8])) {
                    return i8;
                }
            }
            return ~i7;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }
}
