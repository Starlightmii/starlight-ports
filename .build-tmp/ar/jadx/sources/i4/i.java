package i4;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2739c = AtomicIntegerFieldUpdater.newUpdater(i.class, "_resumed$volatile");
    private volatile /* synthetic */ int _resumed$volatile;

    public i(h hVar, Throwable th, boolean z2) {
        if (th == null) {
            th = new CancellationException("Continuation " + hVar + " was cancelled normally");
        }
        super(th, z2);
        this._resumed$volatile = 0;
    }
}
