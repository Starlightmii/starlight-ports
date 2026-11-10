package com.oplus.systemui.statusbar.util;

import android.content.Context;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.HashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStatisticUtil$$ExternalSyntheticLambda5 implements Function0 {
    public /* synthetic */ int f$0;
    public /* synthetic */ String f$1;
    public /* synthetic */ long f$2;
    public /* synthetic */ Context f$3;

    public final Object invoke() {
        int i = this.f$0;
        String str = this.f$1;
        long j = this.f$2;
        Context context = this.f$3;
        HashMap map = new HashMap();
        map.put("panel_notification_count", String.valueOf(i));
        map.put("device_fold_state", str);
        map.put("panel_expand_time", String.valueOf(j));
        OplusTrackProxyUtil.onCommon(context, "20082", "notification_panel_show", map);
        return Unit.INSTANCE;
    }
}
