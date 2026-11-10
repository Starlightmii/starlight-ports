package f0;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2387a;

    public /* synthetic */ w(int i5) {
        this.f2387a = i5;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f2387a) {
            case 0:
                return b4.l.f(((x0) obj).f2417b, ((x0) obj2).f2417b);
            case 1:
                p1.d0 d0Var = (p1.d0) obj;
                p1.d0 d0Var2 = (p1.d0) obj2;
                float f5 = d0Var.f4802z.f4910r.B;
                float f6 = d0Var2.f4802z.f4910r.B;
                return f5 == f6 ? b4.l.f(d0Var.s(), d0Var2.s()) : Float.compare(f5, f6);
            case 2:
                w1.n nVar = (w1.n) obj2;
                Object objValueOf = Float.valueOf(0.0f);
                w1.i iVar = ((w1.n) obj).f6318d;
                w1.t tVar = w1.q.f6349n;
                Object obj3 = iVar.f6308d.get(tVar);
                if (obj3 == null) {
                    obj3 = objValueOf;
                }
                float fFloatValue = ((Number) obj3).floatValue();
                Object obj4 = nVar.f6318d.f6308d.get(tVar);
                if (obj4 != null) {
                    objValueOf = obj4;
                }
                return Float.compare(fFloatValue, ((Number) objValueOf).floatValue());
            case 3:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i5 = 0; i5 < bArr.length; i5++) {
                    byte b5 = bArr[i5];
                    byte b6 = bArr2[i5];
                    if (b5 != b6) {
                        return b5 - b6;
                    }
                }
                return 0;
            default:
                p3.f fVar = (p3.f) obj;
                p3.f fVar2 = (p3.f) obj2;
                return (((Number) fVar.f5015e).intValue() - ((Number) fVar.f5014d).intValue()) - (((Number) fVar2.f5015e).intValue() - ((Number) fVar2.f5014d).intValue());
        }
    }
}
