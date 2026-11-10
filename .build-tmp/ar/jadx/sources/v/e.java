package v;

import io.github.libxposed.service.IXposedService;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f5883f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f5884g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f5885h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f5886i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f5887j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final e f5888k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f5889l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f5890m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f5891n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final e f5892o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f5893p;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5894e;

    static {
        int i5 = 1;
        f5883f = new e(i5, 0);
        f5884g = new e(i5, 1);
        f5885h = new e(i5, 2);
        f5886i = new e(i5, 3);
        f5887j = new e(i5, 4);
        f5888k = new e(i5, 5);
        f5889l = new e(i5, 6);
        f5890m = new e(i5, 7);
        f5891n = new e(i5, 8);
        f5892o = new e(i5, 9);
        f5893p = new e(i5, 10);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i5, int i6) {
        super(i5);
        this.f5894e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5 = this.f5894e;
        p3.n nVar = p3.n.f5026a;
        switch (i5) {
            case 0:
                return nVar;
            case 1:
                return nVar;
            case 2:
                return nVar;
            case 3:
                return nVar;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                z.b0 b0Var = (z.b0) obj;
                long j5 = b0Var.f6904f;
                int i6 = y1.g0.f6769c;
                return new e2.g(((int) (j5 & 4294967295L)) - l0.p((int) (j5 & 4294967295L), b0Var.f6905g.f6759d), 0);
            case 5:
                z.b0 b0Var2 = (z.b0) obj;
                String str = b0Var2.f6905g.f6759d;
                long j6 = b0Var2.f6904f;
                int i7 = y1.g0.f6769c;
                int iM = l0.m((int) (j6 & 4294967295L), str);
                if (iM != -1) {
                    return new e2.g(0, iM - ((int) (4294967295L & b0Var2.f6904f)));
                }
                return null;
            case 6:
                z.b0 b0Var3 = (z.b0) obj;
                Integer numD = b0Var3.d();
                if (numD == null) {
                    return null;
                }
                int iIntValue = numD.intValue();
                long j7 = b0Var3.f6904f;
                int i8 = y1.g0.f6769c;
                return new e2.g(((int) (4294967295L & j7)) - iIntValue, 0);
            case 7:
                z.b0 b0Var4 = (z.b0) obj;
                Integer numC = b0Var4.c();
                if (numC == null) {
                    return null;
                }
                int iIntValue2 = numC.intValue();
                long j8 = b0Var4.f6904f;
                int i9 = y1.g0.f6769c;
                return new e2.g(0, iIntValue2 - ((int) (4294967295L & j8)));
            case 8:
                z.b0 b0Var5 = (z.b0) obj;
                e2.r rVar = b0Var5.f6902d;
                y1.e0 e0Var = b0Var5.f6901c;
                Integer numValueOf = e0Var != null ? Integer.valueOf(rVar.a(e0Var.h(e0Var.e(rVar.b(y1.g0.e(b0Var5.f6904f)))))) : null;
                if (numValueOf == null) {
                    return null;
                }
                int iIntValue3 = numValueOf.intValue();
                long j9 = b0Var5.f6904f;
                int i10 = y1.g0.f6769c;
                return new e2.g(((int) (4294967295L & j9)) - iIntValue3, 0);
            case 9:
                z.b0 b0Var6 = (z.b0) obj;
                Integer numB = b0Var6.b();
                if (numB == null) {
                    return null;
                }
                int iIntValue4 = numB.intValue();
                long j10 = b0Var6.f6904f;
                int i11 = y1.g0.f6769c;
                return new e2.g(0, iIntValue4 - ((int) (4294967295L & j10)));
            default:
                List list = (List) obj;
                Object obj2 = list.get(1);
                b4.l.c("null cannot be cast to non-null type kotlin.Boolean", obj2);
                p.l0 l0Var = ((Boolean) obj2).booleanValue() ? p.l0.f4525d : p.l0.f4526e;
                Object obj3 = list.get(0);
                b4.l.c("null cannot be cast to non-null type kotlin.Float", obj3);
                return new c1(l0Var, ((Float) obj3).floatValue());
        }
    }
}
