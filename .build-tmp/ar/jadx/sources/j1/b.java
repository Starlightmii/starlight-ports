package j1;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends CancellationException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f3217d = new b();

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(z.f3304b);
        return this;
    }
}
