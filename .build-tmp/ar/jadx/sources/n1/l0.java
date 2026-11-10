package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f4129a;

    /* JADX WARN: Multi-variable type inference failed */
    public static final void a(l0 l0Var, m0 m0Var) {
        l0Var.getClass();
        if (m0Var instanceof p1.r0) {
            ((p1.r0) m0Var).W(l0Var.f4129a);
        }
    }

    public static void d(l0 l0Var, m0 m0Var, int i5, int i6) {
        l0Var.getClass();
        long jD = h1.c.d(i5, i6);
        a(l0Var, m0Var);
        m0Var.k0(k2.h.c(jD, m0Var.f4134h), 0.0f, null);
    }

    public static void e(l0 l0Var, m0 m0Var, long j5) {
        l0Var.getClass();
        a(l0Var, m0Var);
        m0Var.k0(k2.h.c(j5, m0Var.f4134h), 0.0f, null);
    }

    public static void f(l0 l0Var, m0 m0Var, int i5, int i6) {
        long jD = h1.c.d(i5, i6);
        if (l0Var.b() == k2.k.f3416d || l0Var.c() == 0) {
            a(l0Var, m0Var);
            m0Var.k0(k2.h.c(jD, m0Var.f4134h), 0.0f, null);
        } else {
            long jD2 = h1.c.d((l0Var.c() - m0Var.f4130d) - ((int) (jD >> 32)), (int) (jD & 4294967295L));
            a(l0Var, m0Var);
            m0Var.k0(k2.h.c(jD2, m0Var.f4134h), 0.0f, null);
        }
    }

    public static void g(l0 l0Var, m0 m0Var, int i5, int i6) {
        int i7 = o0.f4144b;
        n0 n0Var = n0.f4138f;
        long jD = h1.c.d(i5, i6);
        if (l0Var.b() == k2.k.f3416d || l0Var.c() == 0) {
            a(l0Var, m0Var);
            m0Var.k0(k2.h.c(jD, m0Var.f4134h), 0.0f, n0Var);
        } else {
            long jD2 = h1.c.d((l0Var.c() - m0Var.f4130d) - ((int) (jD >> 32)), (int) (jD & 4294967295L));
            a(l0Var, m0Var);
            m0Var.k0(k2.h.c(jD2, m0Var.f4134h), 0.0f, n0Var);
        }
    }

    public static void h(l0 l0Var, m0 m0Var, int i5, int i6, a4.c cVar, int i7) {
        if ((i7 & 8) != 0) {
            int i8 = o0.f4144b;
            cVar = n0.f4138f;
        }
        l0Var.getClass();
        long jD = h1.c.d(i5, i6);
        a(l0Var, m0Var);
        m0Var.k0(k2.h.c(jD, m0Var.f4134h), 0.0f, cVar);
    }

    public abstract k2.k b();

    public abstract int c();
}
