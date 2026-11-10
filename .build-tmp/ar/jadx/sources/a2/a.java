package a2;

import android.text.SegmentFinder;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends SegmentFinder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f38a;

    public a(f fVar) {
        this.f38a = fVar;
    }

    public final int nextEndBoundary(int i5) {
        return this.f38a.h(i5);
    }

    public final int nextStartBoundary(int i5) {
        return this.f38a.k(i5);
    }

    public final int previousEndBoundary(int i5) {
        return this.f38a.l(i5);
    }

    public final int previousStartBoundary(int i5) {
        return this.f38a.e(i5);
    }
}
