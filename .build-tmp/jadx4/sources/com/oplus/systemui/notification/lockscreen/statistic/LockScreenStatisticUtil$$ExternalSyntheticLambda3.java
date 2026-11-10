package com.oplus.systemui.notification.lockscreen.statistic;

import android.content.Context;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import oplus.util.OplusStatistics;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockScreenStatisticUtil$$ExternalSyntheticLambda3 implements Function0 {
    public /* synthetic */ String f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ List f$3;
    public /* synthetic */ String f$4;
    public /* synthetic */ Context f$5;

    public final Object invoke() {
        String str;
        JSONObject capsuleDetailJson;
        String string;
        String str2 = this.f$0;
        int i = this.f$1;
        int i2 = this.f$2;
        List list = this.f$3;
        String str3 = this.f$4;
        Context context = this.f$5;
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("state_switch", str2);
        if (i == 101) {
            str = "1";
        } else if (i == 111) {
            str = ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH;
        } else if (i == 303) {
            str = "4";
        } else if (i != 121) {
            str = i != 122 ? "6" : "3";
        } else {
            str = "5";
        }
        mapM.put("switch_reason", str);
        try {
            capsuleDetailJson = LockScreenStatisticUtil.getCapsuleDetailJson(LockScreenStatisticUtil.getReportExtStateValue(i2), list, str3);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, "LockScreenStatisticUtil", "getCapsuleDetailJson function meet some error :" + e);
            capsuleDetailJson = null;
        }
        if (capsuleDetailJson == null || (string = capsuleDetailJson.toString()) == null) {
            string = "";
        }
        mapM.put("capsule_info", string);
        LockScreenStatisticUtil lockScreenStatisticUtil = LockScreenStatisticUtil.INSTANCE;
        context.getClass();
        Map sinkCommonMap = lockScreenStatisticUtil.getSinkCommonMap(context);
        sinkCommonMap.putAll(mapM);
        OplusStatistics.onCommon(context, "20082", "20082003", "capsule_list_style_switch", sinkCommonMap, true);
        return Unit.INSTANCE;
    }
}
