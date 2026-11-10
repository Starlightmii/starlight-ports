package com.oplus.systemui.statusbar.util;

import android.content.Context;
import com.oplus.compatui.OplusCompatDcsUtils$;
import java.util.HashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStatisticUtil$$ExternalSyntheticLambda4 implements Function0 {
    public /* synthetic */ String f$0;
    public /* synthetic */ String f$1;
    public /* synthetic */ long f$2;
    public /* synthetic */ Context f$3;

    public final Object invoke() {
        String str = this.f$0;
        String str2 = this.f$1;
        long j = this.f$2;
        Context context = this.f$3;
        HashMap mapM = OplusCompatDcsUtils$.ExternalSyntheticOutline0.m("type", str, "location", str2);
        mapM.put("event_time", NotificationStatisticUtil.formatDate(j));
        mapM.put("is_oversea_support", "1");
        OplusBatchTrack.onCommon(context, "20082", "notification_slide_action_batch", mapM);
        return Unit.INSTANCE;
    }
}
