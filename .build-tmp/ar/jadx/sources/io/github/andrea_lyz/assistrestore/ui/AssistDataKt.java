package io.github.andrea_lyz.assistrestore.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AssistDataKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final y0.c0 rasterize(Drawable drawable, int i5) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i5, i5, Bitmap.Config.ARGB_8888);
        b4.l.d("createBitmap(...)", bitmapCreateBitmap);
        drawable.setBounds(0, 0, i5, i5);
        drawable.draw(new Canvas(bitmapCreateBitmap));
        return new y0.g(bitmapCreateBitmap);
    }
}
