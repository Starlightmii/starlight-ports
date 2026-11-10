package com.oplus.systemui.qs.base.util;

import android.text.TextUtils;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioStatUtil$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((ScenarioStatUtil.ScenarioStat) obj).toString();
            default:
                Map.Entry entry = (Map.Entry) obj;
                String str = (String) entry.getKey();
                Integer num = (Integer) entry.getValue();
                if (TextUtils.isEmpty(str)) {
                    return "";
                }
                return str + ":" + num;
        }
    }
}
