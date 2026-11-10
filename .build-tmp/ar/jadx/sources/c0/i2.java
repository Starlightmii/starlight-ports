package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class i2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.h3 f921a = new f0.h3(t.f1302m);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f0.j0 f922b = new f0.j0(t.f1301l);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j2 f923c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j2 f924d;

    static {
        long j5 = y0.u.f6694g;
        f923c = new j2(true, Float.NaN, j5);
        f924d = new j2(false, Float.NaN, j5);
    }

    public static final n.i0 a(boolean z2, float f5, long j5, f0.p pVar, int i5, int i6) {
        n.i0 j2Var;
        boolean z3 = true;
        if ((i6 & 1) != 0) {
            z2 = true;
        }
        if ((i6 & 2) != 0) {
            f5 = Float.NaN;
        }
        if ((i6 & 4) != 0) {
            j5 = y0.u.f6694g;
        }
        f0.v vVar = (f0.v) pVar;
        vVar.V(-1280632857);
        if (((Boolean) vVar.j(f921a)).booleanValue()) {
            m.z0 z0Var = b0.s.f448a;
            f0.i1 i1VarV = f0.l.v(new y0.u(j5), vVar);
            boolean z4 = (((i5 & 14) ^ 6) > 4 && vVar.g(z2)) || (i5 & 6) == 4;
            if ((((i5 & 112) ^ 48) <= 32 || !vVar.c(f5)) && (i5 & 48) != 32) {
                z3 = false;
            }
            boolean z5 = z4 | z3;
            Object objK = vVar.K();
            if (z5 || objK == f0.o.f2281a) {
                objK = new b0.g(z2, f5, i1VarV);
                vVar.f0(objK);
            }
            j2Var = (b0.g) objK;
        } else if (k2.e.a(f5, Float.NaN) && y0.u.c(j5, y0.u.f6694g)) {
            j2Var = z2 ? f923c : f924d;
        } else {
            j2Var = new j2(z2, f5, j5);
        }
        vVar.p(false);
        return j2Var;
    }
}
