package g0;

import f0.b2;
import f0.o2;
import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends i0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final r f2499d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final r f2500e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final r f2501f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final r f2502g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f2503c;

    static {
        int i5 = 1;
        f2499d = new r(i5, 2, 0);
        int i6 = 1;
        f2500e = new r(i6, i6, 1);
        f2501f = new r(i5, 2, 2);
        int i7 = 1;
        f2502g = new r(i7, i7, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(int i5, int i6, int i7) {
        super(i5, i6);
        this.f2503c = i7;
    }

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        switch (this.f2503c) {
            case 0:
                Object objA = ((a4.a) lVar.d(0)).a();
                f0.a aVar = (f0.a) lVar.d(1);
                int iC = lVar.c(0);
                aVar.getClass();
                w2Var.U(w2Var.c(aVar), objA);
                cVar.h(iC, objA);
                cVar.e(objA);
                break;
            case 1:
                f0.a aVar2 = (f0.a) lVar.d(0);
                int iC2 = lVar.c(0);
                cVar.p();
                aVar2.getClass();
                cVar.c(iC2, w2Var.D(w2Var.c(aVar2)));
                break;
            case 2:
                Object objD = lVar.d(0);
                f0.a aVar3 = (f0.a) lVar.d(1);
                int iC3 = lVar.c(0);
                if (objD instanceof o2) {
                    o2 o2Var = (o2) objD;
                    jVar.f4076e.b(o2Var);
                    jVar.f4075d.a(o2Var);
                }
                Object objK = w2Var.K(w2Var.c(aVar3), iC3, objD);
                if (objK instanceof o2) {
                    jVar.d((o2) objK);
                } else if (objK instanceof b2) {
                    ((b2) objK).d();
                }
                break;
            default:
                Object objD2 = lVar.d(0);
                int iC4 = lVar.c(0);
                if (objD2 instanceof o2) {
                    o2 o2Var2 = (o2) objD2;
                    jVar.f4076e.b(o2Var2);
                    jVar.f4075d.a(o2Var2);
                }
                Object objK2 = w2Var.K(w2Var.f2409t, iC4, objD2);
                if (objK2 instanceof o2) {
                    jVar.d((o2) objK2);
                } else if (objK2 instanceof b2) {
                    ((b2) objK2).d();
                }
                break;
        }
    }

    @Override // g0.i0
    public f0.a b(e2.l lVar) {
        switch (this.f2503c) {
            case 0:
                return (f0.a) lVar.d(1);
            case 1:
                return (f0.a) lVar.d(0);
            default:
                return super.b(lVar);
        }
    }
}
