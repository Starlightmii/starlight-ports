package com.oplus.systemui.opthreekey.widget;

import android.content.Context;
import com.oplus.systemui.opthreekey.utils.StealthModeStatisticUtil;
import com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StealthModeDailyStatisticProxy extends AbsDailyStatisticProxy {
    @Override // com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy
    public void onStatistic(Context context) {
        StealthModeStatisticUtil.uploadStealthModeStatus(context);
    }
}
