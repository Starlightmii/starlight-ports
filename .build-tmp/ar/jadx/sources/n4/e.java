package n4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends RuntimeException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient t3.h f4278d;

    public e(t3.h hVar) {
        this.f4278d = hVar;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public final String getLocalizedMessage() {
        return this.f4278d.toString();
    }
}
