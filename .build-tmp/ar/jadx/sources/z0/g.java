package z0;

import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f7064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f7065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f7066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f7067d;

    /* JADX WARN: Code duplicated, block: B:28:0x0069  */
    /* JADX WARN: Illegal instructions before constructor call */
    public g(c cVar, c cVar2, int i5) {
        float[] fArr;
        long j5 = cVar.f7038b;
        long j6 = b.f7032a;
        c cVarA = b.a(j5, j6) ? j.a(cVar) : cVar;
        c cVarA2 = b.a(cVar2.f7038b, j6) ? j.a(cVar2) : cVar2;
        if (i5 == 3) {
            boolean zA = b.a(cVar.f7038b, j6);
            boolean zA2 = b.a(cVar2.f7038b, j6);
            if (!(zA && zA2) && (zA || zA2)) {
                s sVar = ((q) (zA ? cVar : cVar2)).f7088d;
                float[] fArrA = j.f7073e;
                float[] fArrA2 = zA ? sVar.a() : fArrA;
                fArrA = zA2 ? sVar.a() : fArrA;
                fArr = new float[]{fArrA2[0] / fArrA[0], fArrA2[1] / fArrA[1], fArrA2[2] / fArrA[2]};
            } else {
                fArr = null;
            }
        } else {
            fArr = null;
        }
        this(cVar2, cVarA, cVarA2, fArr);
    }

    public g(c cVar, c cVar2, c cVar3, float[] fArr) {
        this.f7064a = cVar;
        this.f7065b = cVar2;
        this.f7066c = cVar3;
        this.f7067d = fArr;
    }

    public long a(long j5) {
        float fH = u.h(j5);
        float fG = u.g(j5);
        float fE = u.e(j5);
        float fD = u.d(j5);
        c cVar = this.f7065b;
        long jD = cVar.d(fH, fG, fE);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jD >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jD & 4294967295L));
        float fE2 = cVar.e(fH, fG, fE);
        float[] fArr = this.f7067d;
        if (fArr != null) {
            fIntBitsToFloat *= fArr[0];
            fIntBitsToFloat2 *= fArr[1];
            fE2 *= fArr[2];
        }
        float f5 = fIntBitsToFloat;
        float f6 = fIntBitsToFloat2;
        return this.f7066c.f(f5, f6, fE2, fD, this.f7064a);
    }
}
