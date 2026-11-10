package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f777e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0.i1 f778f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(f0.i1 i1Var, int i5) {
        super(2);
        this.f777e = i5;
        this.f778f = i1Var;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0024  */
    /* JADX WARN: Code duplicated, block: B:14:0x0050  */
    /* JADX WARN: Code duplicated, block: B:22:0x0070  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        float fMin;
        int i5 = this.f777e;
        p3.n nVar = p3.n.f5026a;
        f0.i1 i1Var = this.f778f;
        switch (i5) {
            case 0:
                k2.i iVar = (k2.i) obj;
                k2.i iVar2 = (k2.i) obj2;
                float f5 = a1.f724a;
                int i6 = iVar2.f3411a;
                int i7 = iVar2.f3414d;
                int i8 = iVar2.f3413c;
                int i9 = iVar2.f3412b;
                int i10 = iVar.f3413c;
                int i11 = iVar.f3412b;
                int i12 = iVar.f3414d;
                int i13 = iVar.f3411a;
                float fMin2 = 1.0f;
                if (i6 >= i10) {
                    fMin = 0.0f;
                } else if (i8 <= i13) {
                    fMin = 1.0f;
                } else if (i8 - i6 == 0) {
                    fMin = 0.0f;
                } else {
                    fMin = (((Math.min(iVar.f3413c, i8) + Math.max(i13, i6)) / 2) - i6) / (i8 - iVar2.f3411a);
                }
                if (i9 >= i12) {
                    fMin2 = 0.0f;
                } else if (i7 > i11) {
                    if (iVar2.a() == 0) {
                        fMin2 = 0.0f;
                    } else {
                        fMin2 = (((Math.min(i12, i7) + Math.max(i11, i9)) / 2) - i9) / iVar2.a();
                    }
                }
                i1Var.setValue(new y0.t0((((long) Float.floatToRawIntBits(fMin)) << 32) | (((long) Float.floatToRawIntBits(fMin2)) & 4294967295L)));
                break;
            default:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    f0.j0 j0Var = n2.j.f4226a;
                    ((a4.e) i1Var.getValue()).invoke(pVar, 0);
                } else {
                    f0.v vVar = (f0.v) pVar;
                    if (!vVar.z()) {
                        f0.j0 j0Var2 = n2.j.f4226a;
                        ((a4.e) i1Var.getValue()).invoke(pVar, 0);
                    } else {
                        vVar.Q();
                    }
                }
                break;
        }
        return nVar;
    }
}
