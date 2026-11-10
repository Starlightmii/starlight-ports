package com.oplus.systemui.statusbar.notification.power;

import android.content.Context;
import android.os.OplusManager;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PowerUIStatistics {
    public static volatile PowerUIStatistics sInstance;

    public static void collectPowerSaveClickData(int i, int i2, Context context) {
        HashMap map = new HashMap();
        map.put("dp_reserved_proto_js_channel", MutableVectorKt$.ExternalSyntheticOutline0.m("{\"item\": [{\"power_save_action\": {\"event_id\": ", i, i2, ", \"dialog_click_state\": ", "}}]}"));
        OplusTrackProxyUtil.onCommon(context, "atom", "atom", map, OplusTrackProxyUtil.FLAG_SEND_TO_ATOM);
    }

    public static void collectPowerUIErrorDialogTimes(int i, Context context) {
        String str;
        switch (i) {
            case 1:
                str = "charge_adapter_v_high";
                break;
            case 2:
            default:
                str = null;
                break;
            case 3:
                str = "low_temp_charge_stop";
                break;
            case 4:
                str = "high_temp_charge_stop";
                break;
            case 5:
                str = "charge_poor_contact";
                break;
            case 6:
                str = "charge_overtime";
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                str = "normal_charge_full_state";
                break;
            case 8:
                str = "high_temp_charge_unfull";
                break;
            case 9:
                str = "low_temp_charge_unfull";
                break;
        }
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("PowerUIStatistics", str);
        mapM.put(str, NotificationStatisticUtil.getEventTime());
        OplusTrackProxyUtil.onCommon(context, "20082", str, null);
        try {
            OplusManager.onStamp("030101", mapM);
        } catch (Exception unused) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "PowerUIStatistics", "Exception");
        }
    }

    public static PowerUIStatistics getInstance() {
        if (sInstance == null) {
            synchronized (PowerUIStatistics.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new PowerUIStatistics();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }
}
