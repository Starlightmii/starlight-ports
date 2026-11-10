package p1;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f4803f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f4804g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f4805h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f4806i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f4807j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final e f4808k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f4809l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f4810m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f4811n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final e f4812o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f4813p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e f4814q;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4815e;

    static {
        int i5 = 1;
        f4803f = new e(i5, 0);
        f4804g = new e(i5, 1);
        f4805h = new e(i5, 2);
        f4806i = new e(i5, 3);
        f4807j = new e(i5, 4);
        f4808k = new e(i5, 5);
        f4809l = new e(i5, 6);
        f4810m = new e(i5, 7);
        f4811n = new e(i5, 8);
        f4812o = new e(i5, 9);
        f4813p = new e(i5, 10);
        f4814q = new e(i5, 11);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i5, int i6) {
        super(i5);
        this.f4815e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4815e) {
            case 0:
                ((c) obj).A0();
                break;
            case 1:
                i1 i1Var = (i1) obj;
                if (i1Var.w()) {
                    i1Var.f4891e.p0(i1Var);
                }
                break;
            case 2:
                d1 d1Var = ((y0) obj).G;
                if (d1Var != null) {
                    d1Var.invalidate();
                }
                break;
            case 3:
                y0 y0Var = (y0) obj;
                if (y0Var.w()) {
                    u uVar = y0Var.C;
                    if (uVar == null) {
                        y0Var.h1(true);
                    } else {
                        u uVar2 = y0.I;
                        uVar2.getClass();
                        uVar2.f4976a = uVar.f4976a;
                        uVar2.f4977b = uVar.f4977b;
                        uVar2.f4978c = uVar.f4978c;
                        uVar2.f4979d = uVar.f4979d;
                        y0Var.h1(true);
                        if (uVar2.f4976a != uVar.f4976a || uVar2.f4977b != uVar.f4977b || uVar2.f4978c != uVar.f4978c || uVar2.f4979d != uVar.f4979d) {
                            d0 d0Var = y0Var.f4998o;
                            j0 j0Var = d0Var.f4802z;
                            if (j0Var.f4906n > 0) {
                                if (j0Var.f4905m || j0Var.f4904l) {
                                    d0Var.P(false);
                                }
                                j0Var.f4910r.r0();
                            }
                            e1 e1Var = d0Var.f4788l;
                            if (e1Var != null) {
                                q1.u uVar3 = (q1.u) e1Var;
                                ((h0.e) uVar3.J.f4949e.f49e).b(d0Var);
                                d0Var.F = true;
                                uVar3.B(null);
                            }
                        }
                    }
                }
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                b1 b1Var = (b1) obj;
                if (b1Var.w()) {
                    b1Var.f4772d.J();
                }
                break;
            case 5:
                d0 d0Var2 = (d0) obj;
                if (d0Var2.D()) {
                    d0Var2.P(false);
                }
                break;
            case 6:
                d0 d0Var3 = (d0) obj;
                if (d0Var3.D()) {
                    d0Var3.P(false);
                }
                break;
            case 7:
                d0 d0Var4 = (d0) obj;
                if (d0Var4.D()) {
                    d0Var4.N(false);
                }
                break;
            case 8:
                d0 d0Var5 = (d0) obj;
                if (d0Var5.D()) {
                    d0Var5.N(false);
                }
                break;
            case 9:
                d0 d0Var6 = (d0) obj;
                if (d0Var6.D()) {
                    d0.O(d0Var6, false, 7);
                }
                break;
            case 10:
                d0 d0Var7 = (d0) obj;
                if (d0Var7.D()) {
                    d0.Q(d0Var7, false, 7);
                }
                break;
            default:
                d0 d0Var8 = (d0) obj;
                if (d0Var8.D()) {
                    d0Var8.B();
                }
                break;
        }
        return p3.n.f5026a;
    }
}
