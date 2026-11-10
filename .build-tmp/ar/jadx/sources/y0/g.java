package y0;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bitmap f6627a;

    public g(Bitmap bitmap) {
        this.f6627a = bitmap;
    }

    public final int a() {
        Bitmap.Config config = this.f6627a.getConfig();
        if (config == Bitmap.Config.ALPHA_8) {
            return 1;
        }
        if (config == Bitmap.Config.RGB_565) {
            return 2;
        }
        if (config == Bitmap.Config.ARGB_4444) {
            return 0;
        }
        if (config == Bitmap.Config.RGBA_F16) {
            return 3;
        }
        return config == Bitmap.Config.HARDWARE ? 4 : 0;
    }
}
