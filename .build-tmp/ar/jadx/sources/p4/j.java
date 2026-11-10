package p4;

import i4.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runnable f5065f;

    public j(Runnable runnable, long j5, boolean z2) {
        super(j5, z2);
        this.f5065f = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f5065f.run();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.f5065f;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(y.g(runnable));
        sb.append(", ");
        sb.append(this.f5063d);
        sb.append(", ");
        sb.append(this.f5064e ? "Blocking" : "Non-blocking");
        sb.append(']');
        return sb.toString();
    }
}
