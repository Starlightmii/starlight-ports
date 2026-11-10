package y0;

import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j implements k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RectF f6639b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float[] f6640c;

    public j(Path path) {
        this.f6638a = path;
    }

    public final boolean c(k0 k0Var, k0 k0Var2, int i5) {
        Path.Op op;
        if (i5 == 0) {
            op = Path.Op.DIFFERENCE;
        } else if (i5 == 1) {
            op = Path.Op.INTERSECT;
        } else if (i5 == 4) {
            op = Path.Op.REVERSE_DIFFERENCE;
        } else {
            op = i5 == 2 ? Path.Op.UNION : Path.Op.XOR;
        }
        if (!(k0Var instanceof j)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        Path path = ((j) k0Var).f6638a;
        if (k0Var2 instanceof j) {
            return this.f6638a.op(path, ((j) k0Var2).f6638a, op);
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    public final void d() {
        this.f6638a.reset();
    }
}
