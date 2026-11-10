package j;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ int[] f3112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object[] f3113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ int f3114f;

    public i0() {
        int i5;
        int i6 = 4;
        while (true) {
            i5 = 40;
            if (i6 >= 32) {
                break;
            }
            int i7 = (1 << i6) - 12;
            if (40 <= i7) {
                i5 = i7;
                break;
            }
            i6++;
        }
        int i8 = i5 / 4;
        this.f3112d = new int[i8];
        this.f3113e = new Object[i8];
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final i0 clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        b4.l.c("null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>", objClone);
        i0 i0Var = (i0) objClone;
        i0Var.f3112d = (int[]) this.f3112d.clone();
        i0Var.f3113e = (Object[]) this.f3113e.clone();
        return i0Var;
    }

    public final boolean b(int i5) {
        return k.a.a(this.f3112d, this.f3114f, i5) >= 0;
    }

    public final Object c(int i5) {
        Object obj;
        int iA = k.a.a(this.f3112d, this.f3114f, i5);
        if (iA < 0 || (obj = this.f3113e[iA]) == n.f3123b) {
            return null;
        }
        return obj;
    }

    public final void d(int i5, Object obj) {
        int iA = k.a.a(this.f3112d, this.f3114f, i5);
        if (iA >= 0) {
            this.f3113e[iA] = obj;
            return;
        }
        int i6 = ~iA;
        int i7 = this.f3114f;
        if (i6 < i7) {
            Object[] objArr = this.f3113e;
            if (objArr[i6] == n.f3123b) {
                this.f3112d[i6] = i5;
                objArr[i6] = obj;
                return;
            }
        }
        if (i7 >= this.f3112d.length) {
            int i8 = (i7 + 1) * 4;
            for (int i9 = 4; i9 < 32; i9++) {
                int i10 = (1 << i9) - 12;
                if (i8 <= i10) {
                    i8 = i10;
                    break;
                }
            }
            int i11 = i8 / 4;
            int[] iArrCopyOf = Arrays.copyOf(this.f3112d, i11);
            b4.l.d("copyOf(...)", iArrCopyOf);
            this.f3112d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f3113e, i11);
            b4.l.d("copyOf(...)", objArrCopyOf);
            this.f3113e = objArrCopyOf;
        }
        int i12 = this.f3114f;
        if (i12 - i6 != 0) {
            int[] iArr = this.f3112d;
            int i13 = i6 + 1;
            q3.k.y(iArr, iArr, i13, i6, i12);
            Object[] objArr2 = this.f3113e;
            q3.k.A(objArr2, objArr2, i13, i6, this.f3114f);
        }
        this.f3112d[i6] = i5;
        this.f3113e[i6] = obj;
        this.f3114f++;
    }

    public final Object e(int i5) {
        Object[] objArr = this.f3113e;
        if (i5 < objArr.length) {
            return objArr[i5];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final String toString() {
        int i5 = this.f3114f;
        if (i5 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i5 * 28);
        sb.append('{');
        int i6 = this.f3114f;
        for (int i7 = 0; i7 < i6; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(this.f3112d[i7]);
            sb.append('=');
            Object objE = e(i7);
            if (objE != this) {
                sb.append(objE);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }
}
