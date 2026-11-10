package com.oplus.systemui.navigationbar.utils;

import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DisplaySizeUtils {
    public static final DisplaySizeUtils INSTANCE = new DisplaySizeUtils();

    public static final int convertDpToPixel(Context context, float f) {
        return (int) (f * context.getResources().getDisplayMetrics().density);
    }

    public static final float convertPixelToDp(Context context, int i) {
        return i / context.getResources().getDisplayMetrics().density;
    }
}
