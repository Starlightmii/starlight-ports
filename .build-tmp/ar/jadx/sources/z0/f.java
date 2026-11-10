package z0;

import java.util.Arrays;
import y0.l0;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f7061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f7062f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float[] f7063g;

    public f(q qVar, q qVar2) {
        float[] fArrH;
        super(qVar2, qVar, qVar2, null);
        this.f7061e = qVar;
        this.f7062f = qVar2;
        float[] fArr = a.f7030b.f7031a;
        s sVar = qVar.f7088d;
        float[] fArrH2 = qVar.f7093i;
        s sVar2 = qVar2.f7088d;
        float[] fArrG = qVar2.f7094j;
        if (j.d(sVar, sVar2)) {
            fArrH = j.h(fArrG, fArrH2);
        } else {
            float[] fArrA = sVar.a();
            float[] fArrA2 = sVar2.a();
            s sVar3 = j.f7070b;
            boolean zD = j.d(sVar, sVar3);
            float[] fArr2 = j.f7073e;
            if (!zD) {
                float[] fArrCopyOf = Arrays.copyOf(fArr2, 3);
                b4.l.d("copyOf(this, size)", fArrCopyOf);
                fArrH2 = j.h(j.c(fArr, fArrA, fArrCopyOf), fArrH2);
            }
            if (!j.d(sVar2, sVar3)) {
                float[] fArrCopyOf2 = Arrays.copyOf(fArr2, 3);
                b4.l.d("copyOf(this, size)", fArrCopyOf2);
                fArrG = j.g(j.h(j.c(fArr, fArrA2, fArrCopyOf2), qVar2.f7093i));
            }
            fArrH = j.h(fArrG, fArrH2);
        }
        this.f7063g = fArrH;
    }

    @Override // z0.g
    public final long a(long j5) {
        float fH = u.h(j5);
        float fG = u.g(j5);
        float fE = u.e(j5);
        float fD = u.d(j5);
        m mVar = this.f7061e.f7100p;
        float fB = (float) mVar.b(fH);
        float fB2 = (float) mVar.b(fG);
        float fB3 = (float) mVar.b(fE);
        float[] fArr = this.f7063g;
        float f5 = (fArr[6] * fB3) + (fArr[3] * fB2) + (fArr[0] * fB);
        float f6 = (fArr[7] * fB3) + (fArr[4] * fB2) + (fArr[1] * fB);
        float f7 = (fArr[8] * fB3) + (fArr[5] * fB2) + (fArr[2] * fB);
        q qVar = this.f7062f;
        float fB4 = (float) qVar.f7097m.b(f5);
        m mVar2 = qVar.f7097m;
        return l0.b(fB4, (float) mVar2.b(f6), (float) mVar2.b(f7), fD, qVar);
    }
}
