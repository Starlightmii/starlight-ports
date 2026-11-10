package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface w extends l {
    default int a0(m0 m0Var, n1.e0 e0Var, int i5) {
        return c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, 2, 1, 2), h1.c.c(0, i5, 7)).l();
    }

    n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5);

    default int g0(m0 m0Var, n1.e0 e0Var, int i5) {
        return c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, 1, 2, 2), h1.c.c(i5, 0, 13)).e();
    }

    default int m0(m0 m0Var, n1.e0 e0Var, int i5) {
        int i6 = 1;
        return c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, i6, i6, 2), h1.c.c(0, i5, 7)).l();
    }

    default int x(m0 m0Var, n1.e0 e0Var, int i5) {
        int i6 = 2;
        return c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, i6, i6, 2), h1.c.c(i5, 0, 13)).e();
    }
}
