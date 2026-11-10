package com.oplus.systemui.qs.base.util;

import android.graphics.Color;
import com.android.systemui.plugins.qs.QSTile;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsColorfulConfigUtil {
    public static boolean enableColorfulConfig;
    public static final QsColorfulConfigUtil INSTANCE = new QsColorfulConfigUtil();
    public static final int QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR = Color.parseColor("#0066FF");
    public static final int QS_COLORFUL_ICON_ACTIVE_VIOLET_COLOR = Color.parseColor("#5A47FF");
    public static final int QS_COLORFUL_ICON_ACTIVE_GREEN_COLOR = Color.parseColor("#00990F");
    public static final int QS_COLORFUL_ICON_ACTIVE_YELLOW_COLOR = Color.parseColor("#EBAB22");
    public static final int QS_COLORFUL_ICON_ACTIVE_ORANGE_COLOR = Color.parseColor("#EB6E00");
    public static final int QS_COLORFUL_ICON_ACTIVE_RED_COLOR = Color.parseColor("#DB382C");
    public static final int QS_COLORFUL_ICON_ACTIVE_BLACK_COLOR = Color.parseColor("#333333");

    static {
        int i = QSTile.State.VERSION;
    }

    public final boolean getEnableColorfulConfig() {
        return enableColorfulConfig;
    }

    public final void setEnableColorfulConfig(boolean z) {
        enableColorfulConfig = z;
    }
}
