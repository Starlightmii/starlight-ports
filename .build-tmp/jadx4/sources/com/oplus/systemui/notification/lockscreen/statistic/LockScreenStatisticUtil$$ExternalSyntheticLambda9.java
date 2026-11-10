package com.oplus.systemui.notification.lockscreen.statistic;

import android.content.Context;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.HashMap;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import oplus.util.OplusStatistics;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockScreenStatisticUtil$$ExternalSyntheticLambda9 implements Function0 {
    public /* synthetic */ String f$0;
    public /* synthetic */ List f$1;
    public /* synthetic */ String f$2;
    public /* synthetic */ Context f$3;

    public final Object invoke() {
        JSONObject capsuleDetailJson;
        String str = this.f$0;
        List list = this.f$1;
        String str2 = this.f$2;
        Context context = this.f$3;
        try {
            capsuleDetailJson = LockScreenStatisticUtil.getCapsuleDetailJson(str, list, str2);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, "LockScreenStatisticUtil", "getCapsuleDetailJson function meet some error :" + e);
            capsuleDetailJson = null;
        }
        if (capsuleDetailJson != null) {
            if ((capsuleDetailJson.length() > 0 ? capsuleDetailJson : null) != null) {
                HashMap map = new HashMap();
                map.put("capsule_info", capsuleDetailJson.toString());
                map.put("type", "3");
                OplusStatistics.onCommon(context, "20082", "20082003", "capsule_expose", map, true);
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
