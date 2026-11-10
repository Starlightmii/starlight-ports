package com.oplus.systemui.qs.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemProperties;
import android.telephony.OplusTelephonyManager;
import android.util.Log;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.telephony.RadioManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SimStateUtil {
    private static final String CHANNEL_LOCK_SUPPORT_PROP = "persist.sys.oplus.radio.channel_lock_support_status";
    public static final SimStateUtil INSTANCE = new SimStateUtil();
    public static final String TAG = "SimStateUtil";

    public static boolean isChannelLockSupport() {
        return SystemProperties.getInt(CHANNEL_LOCK_SUPPORT_PROP, 0) == 1;
    }

    public static final boolean isLockStatusLocked(Context context) {
        int iRequestForTelephonyEvent;
        int iRequestForTelephonyEvent2;
        if (context != null) {
            INSTANCE.getClass();
            if (isChannelLockSupport()) {
                RadioManager radioManagerCreateForSlotId = RadioManager.createForSlotId(context, 0);
                RadioManager radioManagerCreateForSlotId2 = RadioManager.createForSlotId(context, 1);
                iRequestForTelephonyEvent2 = -1;
                iRequestForTelephonyEvent = radioManagerCreateForSlotId != null ? radioManagerCreateForSlotId.getCarrierLockState() : -1;
                if (radioManagerCreateForSlotId2 != null) {
                    iRequestForTelephonyEvent2 = radioManagerCreateForSlotId2.getCarrierLockState();
                }
            } else {
                iRequestForTelephonyEvent = requestForTelephonyEvent(0, 6016, context);
                iRequestForTelephonyEvent2 = requestForTelephonyEvent(1, 6017, context);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("isLockStatusLocked: slot1State=", iRequestForTelephonyEvent, iRequestForTelephonyEvent2, ";slot2State=", "SimStateUtil-->"));
            }
            if (2 == iRequestForTelephonyEvent || 2 == iRequestForTelephonyEvent2) {
                return true;
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "SimStateUtil-->isLockStatusLocked return false for context is null!");
            return false;
        }
        return false;
    }

    public static final int requestForTelephonyEvent(int i, int i2, Context context) {
        Bundle bundleRequestForTelephonyEvent = OplusTelephonyManager.getInstance(context).requestForTelephonyEvent(i, i2, (Bundle) null);
        if (bundleRequestForTelephonyEvent == null) {
            return -1;
        }
        try {
            return bundleRequestForTelephonyEvent.getInt("result");
        } catch (Exception e) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                return -1;
            }
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("requestForTelephonyEvent exception", e.getMessage(), "SimStateUtil-->", "SystemUi--Statusbar");
            return -1;
        }
    }
}
