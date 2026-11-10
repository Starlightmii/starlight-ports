package y0;

import android.graphics.PathMeasure;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PathMeasure f6641a;

    public k(PathMeasure pathMeasure) {
        this.f6641a = pathMeasure;
    }

    public final void a(float f5, float f6, j jVar) {
        if (jVar == null) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        this.f6641a.getSegment(f5, f6, jVar.f6638a, true);
    }
}
