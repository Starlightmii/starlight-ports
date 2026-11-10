package q3;

import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class v extends p3.p {
    public static final int F(int i5) {
        if (i5 < 0) {
            return i5;
        }
        if (i5 < 3) {
            return i5 + 1;
        }
        if (i5 < 1073741824) {
            return (int) ((i5 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static final void G(HashMap map, p3.f[] fVarArr) {
        for (p3.f fVar : fVarArr) {
            map.put(fVar.f5014d, fVar.f5015e);
        }
    }
}
