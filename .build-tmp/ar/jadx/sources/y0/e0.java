package y0;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 extends o0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f6622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f6623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6625f;

    public e0(ArrayList arrayList, ArrayList arrayList2, long j5, long j6) {
        this.f6622c = arrayList;
        this.f6623d = arrayList2;
        this.f6624e = j5;
        this.f6625f = j6;
    }

    @Override // y0.o0
    public final Shader b(long j5) {
        long j6 = this.f6624e;
        float fD = x0.c.d(j6) == Float.POSITIVE_INFINITY ? x0.f.d(j5) : x0.c.d(j6);
        float fB = x0.c.e(j6) == Float.POSITIVE_INFINITY ? x0.f.b(j5) : x0.c.e(j6);
        long j7 = this.f6625f;
        float fD2 = x0.c.d(j7) == Float.POSITIVE_INFINITY ? x0.f.d(j5) : x0.c.d(j7);
        float fB2 = x0.c.e(j7) == Float.POSITIVE_INFINITY ? x0.f.b(j5) : x0.c.e(j7);
        long jB = p3.j.b(fD, fB);
        long jB2 = p3.j.b(fD2, fB2);
        ArrayList arrayList = this.f6622c;
        int size = arrayList.size();
        ArrayList arrayList2 = this.f6623d;
        if (size != arrayList2.size()) {
            throw new IllegalArgumentException("colors and colorStops arguments must have equal length.");
        }
        float fD3 = x0.c.d(jB);
        float fE = x0.c.e(jB);
        float fD4 = x0.c.d(jB2);
        float fE2 = x0.c.e(jB2);
        int size2 = arrayList.size();
        int[] iArr = new int[size2];
        int i5 = 0;
        for (int i6 = 0; i6 < size2; i6++) {
            iArr[i6] = l0.w(((u) arrayList.get(i6)).f6696a);
        }
        float[] fArr = new float[arrayList2.size()];
        int size3 = arrayList2.size();
        int i7 = 0;
        while (i7 < size3) {
            Object obj = arrayList2.get(i7);
            i7++;
            fArr[i5] = ((Number) obj).floatValue();
            i5++;
        }
        return new LinearGradient(fD3, fE, fD4, fE2, iArr, fArr, Shader.TileMode.CLAMP);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return this.f6622c.equals(e0Var.f6622c) && this.f6623d.equals(e0Var.f6623d) && x0.c.b(this.f6624e, e0Var.f6624e) && x0.c.b(this.f6625f, e0Var.f6625f);
    }

    public final int hashCode() {
        return Integer.hashCode(0) + b.b.b(b.b.b((this.f6623d.hashCode() + (this.f6622c.hashCode() * 31)) * 31, 31, this.f6624e), 31, this.f6625f);
    }

    public final String toString() {
        String str;
        long j5 = this.f6624e;
        String str2 = "";
        if (p3.j.C(j5)) {
            str = "start=" + ((Object) x0.c.j(j5)) + ", ";
        } else {
            str = "";
        }
        long j6 = this.f6625f;
        if (p3.j.C(j6)) {
            str2 = "end=" + ((Object) x0.c.j(j6)) + ", ";
        }
        return "LinearGradient(colors=" + this.f6622c + ", stops=" + this.f6623d + ", " + str + str2 + "tileMode=" + ((Object) "Clamp") + ')';
    }
}
