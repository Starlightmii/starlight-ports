package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e1 f2427a;

    public y1(a4.a aVar) {
        this.f2427a = new e1(aVar);
    }

    public abstract z1 a(Object obj);

    public j3 b() {
        return this.f2427a;
    }

    public final j3 c(z1 z1Var, j3 j3Var) {
        r0 r0Var;
        j3 j3Var2 = null;
        j3Var2 = null;
        j3Var2 = null;
        j3Var2 = null;
        j3Var2 = null;
        j3Var2 = null;
        if (j3Var instanceof r0) {
            if (z1Var.f2439d) {
                r0Var = (r0) j3Var;
                r0Var.f2301a.setValue(z1Var.a());
            }
        } else if (j3Var instanceof i3) {
            if ((z1Var.f2437b || z1Var.f2440e != null) && !z1Var.f2439d) {
                i3 i3Var = (i3) j3Var;
                if (b4.l.a(z1Var.a(), i3Var.f2202a)) {
                    j3Var2 = i3Var;
                }
            }
        } else if (j3Var instanceof k0) {
            z1Var.getClass();
        }
        if (j3Var2 != null) {
            j3Var2 = r0Var;
            return j3Var2;
        }
        if (!z1Var.f2439d) {
            j3Var2 = r0Var;
            return new i3(z1Var.a());
        }
        Object obj = z1Var.f2440e;
        b3 b3Var = z1Var.f2438c;
        if (b3Var == null) {
            j3Var2 = r0Var;
            b3Var = h.f2189j;
        }
        j3Var2 = r0Var;
        return new r0(new s1(obj, b3Var));
    }
}
