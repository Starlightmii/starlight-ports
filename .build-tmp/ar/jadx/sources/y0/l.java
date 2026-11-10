package y0;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.util.DisplayMetrics;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public static final z0.c a(Bitmap bitmap) {
        z0.c cVarB;
        ColorSpace colorSpace = bitmap.getColorSpace();
        return (colorSpace == null || (cVarB = y.b(colorSpace)) == null) ? z0.d.f7042c : cVarB;
    }

    public static final Bitmap b(int i5, int i6, int i7, boolean z2, z0.c cVar) {
        return Bitmap.createBitmap((DisplayMetrics) null, i5, i6, l0.x(i7), z2, y.a(cVar));
    }
}
