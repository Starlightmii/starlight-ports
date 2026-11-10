package l2;

import j.i0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f3500a = {8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile i0 f3501b = new i0();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object[] f3502c;

    static {
        Object[] objArr = new Object[0];
        f3502c = objArr;
        synchronized (objArr) {
            f3501b.d((int) 115.0f, new c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{9.2f, 11.5f, 13.8f, 16.4f, 19.8f, 21.8f, 25.2f, 30.0f, 100.0f}));
            f3501b.d((int) 130.0f, new c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{10.4f, 13.0f, 15.6f, 18.8f, 21.6f, 23.6f, 26.4f, 30.0f, 100.0f}));
            f3501b.d((int) 150.0f, new c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{12.0f, 15.0f, 18.0f, 22.0f, 24.0f, 26.0f, 28.0f, 30.0f, 100.0f}));
            f3501b.d((int) 180.0f, new c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{14.4f, 18.0f, 21.6f, 24.4f, 27.6f, 30.8f, 32.8f, 34.8f, 100.0f}));
            f3501b.d((int) 200.0f, new c(new float[]{8.0f, 10.0f, 12.0f, 14.0f, 18.0f, 20.0f, 24.0f, 30.0f, 100.0f}, new float[]{16.0f, 20.0f, 24.0f, 26.0f, 30.0f, 34.0f, 36.0f, 38.0f, 100.0f}));
        }
        if ((f3501b.f3112d[0] / 100.0f) - 0.01f <= 1.03f) {
            throw new IllegalStateException("You should only apply non-linear scaling to font scales > 1");
        }
    }

    public static a a(float f5) {
        float f6;
        a cVar;
        float[] fArr = f3500a;
        if (f5 < 1.03f) {
            return null;
        }
        int i5 = (int) (f5 * 100.0f);
        a aVar = (a) f3501b.c(i5);
        if (aVar != null) {
            return aVar;
        }
        i0 i0Var = f3501b;
        int iA = k.a.a(i0Var.f3112d, i0Var.f3114f, i5);
        if (iA >= 0) {
            return (a) f3501b.e(iA);
        }
        int i6 = -(iA + 1);
        int i7 = i6 - 1;
        if (i6 >= f3501b.f3114f) {
            c cVar2 = new c(new float[]{1.0f}, new float[]{f5});
            b(f5, cVar2);
            return cVar2;
        }
        if (i7 < 0) {
            cVar = new c(fArr, fArr);
            f6 = 1.0f;
        } else {
            f6 = f3501b.f3112d[i7] / 100.0f;
            cVar = (a) f3501b.e(i7);
        }
        float f7 = f3501b.f3112d[i6] / 100.0f;
        float fMax = (Math.max(0.0f, Math.min(1.0f, f6 == f7 ? 0.0f : (f5 - f6) / (f7 - f6))) * 1.0f) + 0.0f;
        a aVar2 = (a) f3501b.e(i6);
        float[] fArr2 = new float[9];
        for (int i8 = 0; i8 < 9; i8++) {
            float f8 = fArr[i8];
            float fB = cVar.b(f8);
            fArr2[i8] = ((aVar2.b(f8) - fB) * fMax) + fB;
        }
        c cVar3 = new c(fArr, fArr2);
        b(f5, cVar3);
        return cVar3;
    }

    public static void b(float f5, c cVar) {
        synchronized (f3502c) {
            i0 i0VarA = f3501b.clone();
            i0VarA.d((int) (f5 * 100.0f), cVar);
            f3501b = i0VarA;
        }
    }
}
