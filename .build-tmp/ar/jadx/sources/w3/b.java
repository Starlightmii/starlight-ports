package w3;

import java.io.Serializable;
import java.util.RandomAccess;
import q3.d;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends d implements a, RandomAccess, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Enum[] f6457d;

    public b(Enum[] enumArr) {
        this.f6457d = enumArr;
    }

    @Override // q3.a
    public final int a() {
        return this.f6457d.length;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0015  */
    @Override // q3.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        Enum r1;
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r4 = (Enum) obj;
        int iOrdinal = r4.ordinal();
        if (iOrdinal >= 0) {
            Enum[] enumArr = this.f6457d;
            if (iOrdinal < enumArr.length) {
                r1 = enumArr[iOrdinal];
            } else {
                r1 = null;
            }
        } else {
            r1 = null;
        }
        return r1 == r4;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        Enum[] enumArr = this.f6457d;
        int length = enumArr.length;
        if (i5 < 0 || i5 >= length) {
            throw new IndexOutOfBoundsException(b.b.d("index: ", i5, ", size: ", length));
        }
        return enumArr[i5];
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0016  */
    @Override // q3.d, java.util.List
    public final int indexOf(Object obj) {
        Enum r4;
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r5 = (Enum) obj;
        int iOrdinal = r5.ordinal();
        if (iOrdinal >= 0) {
            Enum[] enumArr = this.f6457d;
            if (iOrdinal < enumArr.length) {
                r4 = enumArr[iOrdinal];
            } else {
                r4 = null;
            }
        } else {
            r4 = null;
        }
        if (r4 == r5) {
            return iOrdinal;
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0016  */
    @Override // q3.d, java.util.List
    public final int lastIndexOf(Object obj) {
        Enum r4;
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r5 = (Enum) obj;
        int iOrdinal = r5.ordinal();
        if (iOrdinal >= 0) {
            Enum[] enumArr = this.f6457d;
            if (iOrdinal < enumArr.length) {
                r4 = enumArr[iOrdinal];
            } else {
                r4 = null;
            }
        } else {
            r4 = null;
        }
        if (r4 == r5) {
            return iOrdinal;
        }
        return -1;
    }
}
