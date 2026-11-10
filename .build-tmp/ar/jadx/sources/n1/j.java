package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j implements e0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4120d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e0 f4121e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4122f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4123g;

    public /* synthetic */ j(e0 e0Var, int i5, int i6, int i7) {
        this.f4120d = i7;
        this.f4121e = e0Var;
        this.f4122f = i5;
        this.f4123g = i6;
    }

    @Override // n1.e0
    public final int a0(int i5) {
        switch (this.f4120d) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f4121e.a0(i5);
    }

    @Override // n1.e0
    public final m0 c(long j5) {
        switch (this.f4120d) {
            case 0:
                int i5 = this.f4123g;
                int i6 = this.f4122f;
                e0 e0Var = this.f4121e;
                if (i5 == 1) {
                    return new k(i6 == 2 ? e0Var.c0(k2.a.h(j5)) : e0Var.a0(k2.a.h(j5)), k2.a.d(j5) ? k2.a.h(j5) : 32767, 0);
                }
                return new k(k2.a.e(j5) ? k2.a.i(j5) : 32767, i6 == 2 ? e0Var.d(k2.a.i(j5)) : e0Var.e0(k2.a.i(j5)), 0);
            case 1:
                int i7 = this.f4123g;
                int i8 = this.f4122f;
                e0 e0Var2 = this.f4121e;
                if (i7 == 1) {
                    return new k(i8 == 2 ? e0Var2.c0(k2.a.h(j5)) : e0Var2.a0(k2.a.h(j5)), k2.a.d(j5) ? k2.a.h(j5) : 32767, 1);
                }
                return new k(k2.a.e(j5) ? k2.a.i(j5) : 32767, i8 == 2 ? e0Var2.d(k2.a.i(j5)) : e0Var2.e0(k2.a.i(j5)), 1);
            default:
                int i9 = this.f4123g;
                int i10 = this.f4122f;
                e0 e0Var3 = this.f4121e;
                if (i9 == 1) {
                    return new k(i10 == 2 ? e0Var3.c0(k2.a.h(j5)) : e0Var3.a0(k2.a.h(j5)), k2.a.d(j5) ? k2.a.h(j5) : 32767, 2);
                }
                return new k(k2.a.e(j5) ? k2.a.i(j5) : 32767, i10 == 2 ? e0Var3.d(k2.a.i(j5)) : e0Var3.e0(k2.a.i(j5)), 2);
        }
    }

    @Override // n1.e0
    public final int c0(int i5) {
        switch (this.f4120d) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f4121e.c0(i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        switch (this.f4120d) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f4121e.d(i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        switch (this.f4120d) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f4121e.e0(i5);
    }

    @Override // n1.e0
    public final Object h() {
        switch (this.f4120d) {
            case 0:
                break;
            case 1:
                break;
        }
        return this.f4121e.h();
    }
}
