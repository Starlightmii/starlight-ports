package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements e, h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5560b;

    public f(float f5) {
        this.f5559a = f5;
        this.f5560b = f5;
    }

    @Override // r.e, r.h
    public final float a() {
        return this.f5560b;
    }

    @Override // r.e
    public final void b(n1.h0 h0Var, int i5, int[] iArr, k2.k kVar, int[] iArr2) {
        int i6;
        int iMin;
        if (iArr.length == 0) {
            return;
        }
        int iM = h0Var.M(this.f5559a);
        boolean z2 = kVar == k2.k.f3417e;
        b bVar = j.f5571a;
        if (z2) {
            i6 = 0;
            iMin = 0;
            for (int length = iArr.length - 1; -1 < length; length--) {
                int i7 = iArr[length];
                int iMin2 = Math.min(i6, i5 - i7);
                iArr2[length] = iMin2;
                iMin = Math.min(iM, (i5 - iMin2) - i7);
                i6 = iArr2[length] + i7 + iMin;
            }
        } else {
            int length2 = iArr.length;
            int i8 = 0;
            i6 = 0;
            iMin = 0;
            int i9 = 0;
            while (i8 < length2) {
                int i10 = iArr[i8];
                int iMin3 = Math.min(i6, i5 - i10);
                iArr2[i9] = iMin3;
                int iMin4 = Math.min(iM, (i5 - iMin3) - i10);
                int i11 = iArr2[i9] + i10 + iMin4;
                i8++;
                iMin = iMin4;
                i6 = i11;
                i9++;
            }
        }
        int i12 = i6 - iMin;
        if (i12 < i5) {
            int iRound = Math.round((1 + (kVar != k2.k.f3416d ? (-1.0f) * (-1) : -1.0f)) * ((i5 - i12) / 2.0f));
            int length3 = iArr2.length;
            for (int i13 = 0; i13 < length3; i13++) {
                iArr2[i13] = iArr2[i13] + iRound;
            }
        }
    }

    @Override // r.h
    public final void c(int i5, n1.h0 h0Var, int[] iArr, int[] iArr2) {
        b(h0Var, i5, iArr, k2.k.f3416d, iArr2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f) || !k2.e.a(this.f5559a, ((f) obj).f5559a)) {
            return false;
        }
        Object obj2 = i.f5567e;
        return obj2.equals(obj2);
    }

    public final int hashCode() {
        return i.f5567e.hashCode() + b.b.c(Float.hashCode(this.f5559a) * 31, 31, true);
    }

    public final String toString() {
        return "Arrangement#spacedAligned(" + ((Object) k2.e.b(this.f5559a)) + ", " + i.f5567e + ')';
    }
}
