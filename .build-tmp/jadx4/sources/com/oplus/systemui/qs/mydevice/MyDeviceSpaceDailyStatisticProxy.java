package com.oplus.systemui.qs.mydevice;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MyDeviceSpaceDailyStatisticProxy extends AbsDailyStatisticProxy {
    public static final Companion Companion = new Companion();
    private static final String EVENT_ID_SHOW_OR_HIDE_DEVICE_SPACE_STATUS = "event_id_show_or_hide_device_space_status";
    private static final String EVENT_SHOW_OR_HIDE_DEVICE_SPACE = "event_show_or_hide_device_space";
    private static final String EVENT_TAG = "20082";
    private static final String HIDE_DEVICE_SPACE = "hide_device_space";
    private static final String QS_DEVICE_SPACE = "qs_device_space";
    private static final String SHOW_DEVICE = "true";
    private static final String SHOW_DEVICE_SPACE = "show_device_space";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy
    public void onStatistic(Context context) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportMyDevice()) {
            String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), QS_DEVICE_SPACE, -2);
            if (stringForUser == null) {
                stringForUser = "";
            }
            boolean zIsEmpty = TextUtils.isEmpty(stringForUser);
            String str = SHOW_DEVICE_SPACE;
            if (!zIsEmpty && !stringForUser.equals("true")) {
                str = HIDE_DEVICE_SPACE;
            }
            HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m(EVENT_SHOW_OR_HIDE_DEVICE_SPACE, str);
            OplusNotificationLogger.logDevicePanel(EVENT_TAG, "MyDeviceSpaceDailyStatistic myDeviceShowStateString: ".concat(str));
            OplusTrackProxyUtil.onCommon(context, EVENT_TAG, EVENT_ID_SHOW_OR_HIDE_DEVICE_SPACE_STATUS, mapM);
        }
    }
}
