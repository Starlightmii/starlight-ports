package q4;

import java.util.concurrent.atomic.AtomicReferenceArray;
import n4.q;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f5499e;

    public i(long j5, i iVar, int i5) {
        super(j5, iVar, i5);
        this.f5499e = new AtomicReferenceArray(h.f5498f);
    }

    @Override // n4.q
    public final int f() {
        return h.f5498f;
    }

    @Override // n4.q
    public final void g(int i5, t3.h hVar) {
        this.f5499e.set(i5, h.f5497e);
        h();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.f4307c + ", hashCode=" + hashCode() + ']';
    }
}
