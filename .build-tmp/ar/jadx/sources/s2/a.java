package s2;

import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Looper;
import android.view.DisplayCutout;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static Handler a(Looper looper) {
        return Handler.createAsync(looper);
    }

    public static int b(Object obj) {
        return ((Icon) obj).getResId();
    }

    public static int c(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetBottom();
    }

    public static int d(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetLeft();
    }

    public static int e(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetRight();
    }

    public static int f(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetTop();
    }
}
