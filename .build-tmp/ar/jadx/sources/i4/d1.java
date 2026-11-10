package i4;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends t3.a implements s0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d1 f2724e = new d1(s.f2774e);

    @Override // i4.s0
    public final boolean a() {
        return true;
    }

    @Override // i4.s0
    public final void b(CancellationException cancellationException) {
    }

    @Override // i4.s0
    public final f0 c(boolean z2, boolean z3, v0 v0Var) {
        return e1.f2728d;
    }

    @Override // i4.s0
    public final f0 g(a4.c cVar) {
        return e1.f2728d;
    }

    @Override // i4.s0
    public final k i(a1 a1Var) {
        return e1.f2728d;
    }

    @Override // i4.s0
    public final CancellationException m() {
        throw new IllegalStateException("This job is always active");
    }

    @Override // i4.s0
    public final Object q(v3.c cVar) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // i4.s0
    public final boolean start() {
        return false;
    }

    public final String toString() {
        return "NonCancellable";
    }
}
