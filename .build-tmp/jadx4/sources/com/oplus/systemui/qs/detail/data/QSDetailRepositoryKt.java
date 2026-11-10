package com.oplus.systemui.qs.detail.data;

import android.content.Context;
import com.oplus.systemui.qs.base.util.QsColorUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QSDetailRepositoryKt {
    private static final String TAG = "QSDetailRepository";

    public static final boolean isQSDetailDarkTheme(Context context) {
        return QsColorUtil.isNeedDarkThemeColor(context, false);
    }
}
