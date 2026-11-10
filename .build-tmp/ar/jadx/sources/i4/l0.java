package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 extends r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ int f2750i = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f2751f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2752g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public q3.j f2753h;

    public final void n(boolean z2) {
        long j5 = this.f2751f - (z2 ? 4294967296L : 1L);
        this.f2751f = j5;
        if (j5 <= 0 && this.f2752g) {
            shutdown();
        }
    }

    public final void o(d0 d0Var) {
        q3.j jVar = this.f2753h;
        if (jVar == null) {
            jVar = new q3.j();
            this.f2753h = jVar;
        }
        jVar.addLast(d0Var);
    }

    public abstract Thread r();

    public abstract void shutdown();

    public final void u(boolean z2) {
        this.f2751f = (z2 ? 4294967296L : 1L) + this.f2751f;
        if (z2) {
            return;
        }
        this.f2752g = true;
    }

    public abstract long v();

    public final boolean w() {
        q3.j jVar = this.f2753h;
        if (jVar == null) {
            return false;
        }
        d0 d0Var = (d0) (jVar.isEmpty() ? null : jVar.removeFirst());
        if (d0Var == null) {
            return false;
        }
        d0Var.run();
        return true;
    }

    public void x(long j5, i0 i0Var) {
        z.f2800m.C(j5, i0Var);
    }
}
