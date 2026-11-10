package b1;

import android.graphics.Outline;
import y0.k0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f557a = new k();

    public final void a(Outline outline, k0 k0Var) {
        if (!(k0Var instanceof y0.j)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        outline.setPath(((y0.j) k0Var).f6638a);
    }
}
