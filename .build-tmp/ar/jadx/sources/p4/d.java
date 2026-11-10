package p4;

import i4.m0;
import i4.r;
import java.util.concurrent.Executor;
import n4.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends m0 implements Executor {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d f5058f = new d();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final r f5059g;

    static {
        l lVar = l.f5072f;
        int i5 = s.f4309a;
        if (64 >= i5) {
            i5 = 64;
        }
        f5059g = lVar.l(n4.a.j(i5, 12, "kotlinx.coroutines.io.parallelism"));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // i4.r
    public final void e(t3.h hVar, Runnable runnable) {
        f5059g.e(hVar, runnable);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        e(t3.i.f5807d, runnable);
    }

    @Override // i4.r
    public final String toString() {
        return "Dispatchers.IO";
    }
}
