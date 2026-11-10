package com.oplusos.systemui.common.statistic;

import android.content.Context;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DailyStatisticProxyManager$onStatistic$1 implements Consumer {
    public /* synthetic */ Context $context;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AbsDailyStatisticProxy) obj).onStatistic(this.$context);
    }
}
