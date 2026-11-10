package com.oplus.systemui.qs.helper;

import android.content.Context;
import android.content.res.Resources;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileSizeHelper {
    public static final OplusQSTileSizeHelper INSTANCE = new OplusQSTileSizeHelper();

    public static final int getQsTileSize(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 0:
                return resources.getDimensionPixelSize(2131172137);
            case 1:
                return resources.getDimensionPixelSize(2131172135);
            case 2:
                return resources.getDimensionPixelSize(2131172180);
            case 3:
                return resources.getDimensionPixelSize(2131172138);
            case 4:
                return resources.getDimensionPixelSize(2131172136);
            case 5:
                return resources.getDimensionPixelSize(2131172181);
            case 6:
                return resources.getDimensionPixelSize(2131172212);
            default:
                return 0;
        }
    }
}
