package h0;

import b4.l;
import j.e0;
import j.y;
import j.z;
import java.util.NoSuchElementException;
import q3.k;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f2569a;

    public static final Object a(z zVar) {
        Object objE = zVar.e(null);
        if (objE == null) {
            return null;
        }
        if (!(objE instanceof y)) {
            zVar.g(null);
            return objE;
        }
        y yVar = (y) objE;
        if (yVar.e()) {
            throw new NoSuchElementException("List is empty.");
        }
        int i5 = yVar.f3175b - 1;
        Object objD = yVar.d(i5);
        yVar.f(i5);
        l.c("null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap", objD);
        if (yVar.e()) {
            zVar.g(null);
        }
        if (yVar.f3175b == 1) {
            zVar.i(null, yVar.c());
        }
        return objD;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0081 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x0083 A[LOOP:0: B:9:0x001c->B:28:0x0083, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x0086 A[EDGE_INSN: B:31:0x0086->B:29:0x0086 BREAK  A[LOOP:0: B:9:0x001c->B:28:0x0083], SYNTHETIC] */
    public static final y b(z zVar) {
        if (zVar.f3180e == 0) {
            y yVar = e0.f3098b;
            l.c("null cannot be cast to non-null type androidx.collection.ObjectList<E of androidx.collection.ObjectListKt.emptyObjectList>", yVar);
            return yVar;
        }
        y yVar2 = new y();
        Object[] objArr = zVar.f3178c;
        long[] jArr = zVar.f3176a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i5 != length) {
                        break;
                        break;
                    }
                    i5++;
                } else {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128) {
                            Object obj = objArr[(i5 << 3) + i7];
                            if (obj instanceof y) {
                                y yVar3 = (y) obj;
                                if (!yVar3.e()) {
                                    int i8 = yVar2.f3175b + yVar3.f3175b;
                                    Object[] objArr2 = yVar2.f3174a;
                                    if (objArr2.length < i8) {
                                        yVar2.g(i8, objArr2);
                                    }
                                    k.A(yVar3.f3174a, yVar2.f3174a, yVar2.f3175b, 0, yVar3.f3175b);
                                    yVar2.f3175b += yVar3.f3175b;
                                }
                            } else {
                                l.c("null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap", obj);
                                yVar2.a(obj);
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i6 != 8) {
                        break;
                    }
                    if (i5 != length) {
                        break;
                    }
                    i5++;
                }
            }
        }
        return yVar2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return l.a(this.f2569a, ((a) obj).f2569a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2569a.hashCode();
    }

    public final String toString() {
        return "MultiValueMap(map=" + this.f2569a + ')';
    }
}
