package l2;

import androidx.lifecycle.k0;
import b4.l;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f3503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f3504b;

    public c(float[] fArr, float[] fArr2) {
        if (fArr.length != fArr2.length || fArr.length == 0) {
            throw new IllegalArgumentException("Array lengths must match and be nonzero");
        }
        this.f3503a = fArr;
        this.f3504b = fArr2;
    }

    @Override // l2.a
    public final float a(float f5) {
        return k0.c(f5, this.f3504b, this.f3503a);
    }

    @Override // l2.a
    public final float b(float f5) {
        return k0.c(f5, this.f3503a, this.f3504b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Arrays.equals(this.f3503a, cVar.f3503a) && Arrays.equals(this.f3504b, cVar.f3504b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f3504b) + (Arrays.hashCode(this.f3503a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FontScaleConverter{fromSpValues=");
        String string = Arrays.toString(this.f3503a);
        l.d("toString(this)", string);
        sb.append(string);
        sb.append(", toDpValues=");
        String string2 = Arrays.toString(this.f3504b);
        l.d("toString(this)", string2);
        sb.append(string2);
        sb.append('}');
        return sb.toString();
    }
}
