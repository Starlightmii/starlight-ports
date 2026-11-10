package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f3174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3175b;

    public /* synthetic */ y() {
        this(16);
    }

    public y(int i5) {
        this.f3174a = i5 == 0 ? e0.f3097a : new Object[i5];
    }

    public final void a(Object obj) {
        int i5 = this.f3175b + 1;
        Object[] objArr = this.f3174a;
        if (objArr.length < i5) {
            g(i5, objArr);
        }
        Object[] objArr2 = this.f3174a;
        int i6 = this.f3175b;
        objArr2[i6] = obj;
        this.f3175b = i6 + 1;
    }

    public final void b() {
        q3.k.E(this.f3174a, 0, this.f3175b);
        this.f3175b = 0;
    }

    public final Object c() {
        if (!e()) {
            return this.f3174a[0];
        }
        k.a.e("ObjectList is empty.");
        throw null;
    }

    public final Object d(int i5) {
        if (i5 >= 0 && i5 < this.f3175b) {
            return this.f3174a[i5];
        }
        h(i5);
        throw null;
    }

    public final boolean e() {
        return this.f3175b == 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof y) {
            y yVar = (y) obj;
            int i5 = yVar.f3175b;
            int i6 = this.f3175b;
            if (i5 == i6) {
                Object[] objArr = this.f3174a;
                Object[] objArr2 = yVar.f3174a;
                e4.d dVarN = d4.a.N(0, i6);
                int i7 = dVarN.f2074d;
                int i8 = dVarN.f2075e;
                if (i7 > i8) {
                    return true;
                }
                while (b4.l.a(objArr[i7], objArr2[i7])) {
                    if (i7 == i8) {
                        return true;
                    }
                    i7++;
                }
                return false;
            }
        }
        return false;
    }

    public final Object f(int i5) {
        int i6;
        if (i5 < 0 || i5 >= (i6 = this.f3175b)) {
            h(i5);
            throw null;
        }
        Object[] objArr = this.f3174a;
        Object obj = objArr[i5];
        if (i5 != i6 - 1) {
            q3.k.A(objArr, objArr, i5, i5 + 1, i6);
        }
        int i7 = this.f3175b - 1;
        this.f3175b = i7;
        objArr[i7] = null;
        return obj;
    }

    public final void g(int i5, Object[] objArr) {
        b4.l.e("oldContent", objArr);
        int length = objArr.length;
        Object[] objArr2 = new Object[Math.max(i5, (length * 3) / 2)];
        q3.k.A(objArr, objArr2, 0, 0, length);
        this.f3174a = objArr2;
    }

    public final void h(int i5) {
        StringBuilder sbG = b.b.g("Index ", i5, " must be in 0..");
        sbG.append(this.f3175b - 1);
        k.a.d(sbG.toString());
        throw null;
    }

    public final int hashCode() {
        Object[] objArr = this.f3174a;
        int i5 = this.f3175b;
        int iHashCode = 0;
        for (int i6 = 0; i6 < i5; i6++) {
            Object obj = objArr[i6];
            iHashCode += (obj != null ? obj.hashCode() : 0) * 31;
        }
        return iHashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        Object[] objArr = this.f3174a;
        int i5 = this.f3175b;
        for (int i6 = 0; i6 < i5; i6++) {
            Object obj = objArr[i6];
            if (i6 == -1) {
                sb.append((CharSequence) "...");
                String string = sb.toString();
                b4.l.d("toString(...)", string);
                return string;
            }
            if (i6 != 0) {
                sb.append((CharSequence) ", ");
            }
            sb.append((CharSequence) (obj == this ? "(this)" : String.valueOf(obj)));
        }
        sb.append((CharSequence) "]");
        String string2 = sb.toString();
        b4.l.d("toString(...)", string2);
        return string2;
    }
}
