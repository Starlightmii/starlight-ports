package com.oplusos.keyguard.statistic;

import android.content.Context;
import android.util.Log;
import androidx.car.app.model.TimerSpan$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UnlockTimeStat$sendDateOfAbnormalUnlockTime$1 implements Runnable {
    public /* synthetic */ Context $context;
    public /* synthetic */ UnlockTimeStat this$0;

    @Override // java.lang.Runnable
    public final void run() {
        long j;
        boolean z;
        long j2;
        int i;
        HashMap map = new HashMap();
        long j3 = UnlockTimeStat.onSystemUIKeyguardGoingAwayTime;
        long j4 = UnlockTimeStat.onAuthenticatedTime;
        long j5 = j3 - j4;
        long j6 = UnlockTimeStat.onStartKeyguardExitAnimationTime;
        long j7 = j6 - j3;
        long j8 = UnlockTimeStat.onKeyguardLayerHideTime;
        long j9 = j8 - j6;
        long j10 = j8 - j4;
        long j11 = j8 - j3;
        int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(this.$context, "oplus_customize_face_unlock_remain_unlock_switch", -1);
        int i2 = UnlockTimeStat.mUnlockType;
        StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(j5, "stageAuthenticatedToKeyguardGoingawayTime:", ",stageKeyguardGoingAwayToExitAnimationTime:");
        sbM.append(j7);
        DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, ",stageKeyguardExitAnimationToLayerHideTime:", j9, ",mUnlockType:");
        sbM.append(i2);
        sbM.append(",totalTimeOne:");
        sbM.append(j10);
        sbM.append(",totalTimeTwo:");
        sbM.append(j11);
        UnlockTimeStat.mCustomStr = sbM.toString();
        if (j5 < 0 || j7 < 0 || j9 < 0) {
            UnlockTimeStat.mWakeupFromSleep = false;
            UnlockTimeStat.onAuthenticatedTime = 0L;
            UnlockTimeStat.onSystemUIKeyguardGoingAwayTime = 0L;
            UnlockTimeStat.onStartKeyguardExitAnimationTime = 0L;
            UnlockTimeStat.onKeyguardLayerHideTime = 0L;
            UnlockTimeStat.isAuthenticated = false;
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isLightOs()) {
            j = 0;
            z = false;
        } else {
            int i3 = UnlockTimeStat.mUnlockType;
            j = 0;
            z = false;
            if ((i3 == 0 || i3 == 4) && j11 > 1500) {
                StringBuilder sbM2 = TimerSpan$.ExternalSyntheticOutline0.m(j7, "1: ", "|2: ");
                sbM2.append(j9);
                map.put("stage_time", sbM2.toString());
                map.put("unlock_type", String.valueOf(UnlockTimeStat.mUnlockType));
                KeyguardStatistic.sendEventData(this.$context, "abnormal_unlock_time_cost", map);
                Log.i("UnlockTimeStat", "collectDateOfAbnormalUnlockTime, " + UnlockTimeStat.mCustomStr);
            } else {
                if (i3 != 2 && (i3 != 3 || secureIntValue == 1)) {
                    i = i3;
                    j2 = j5;
                } else if (j10 > 1500) {
                    StringBuilder sbM3 = TimerSpan$.ExternalSyntheticOutline0.m(j5, "0: ", "|1: ");
                    sbM3.append(j7);
                    sbM3.append("|2: ");
                    sbM3.append(j9);
                    map.put("stage_time", sbM3.toString());
                    map.put("unlock_type", String.valueOf(UnlockTimeStat.mUnlockType));
                    KeyguardStatistic.sendEventData(this.$context, "abnormal_unlock_time_cost", map);
                    Log.i("UnlockTimeStat", "collectDateOfAbnormalUnlockTime, " + UnlockTimeStat.mCustomStr);
                } else {
                    j2 = j5;
                    i = i3;
                }
                if (i == 1 && j10 > 1000) {
                    StringBuilder sbM4 = TimerSpan$.ExternalSyntheticOutline0.m(j2, "0: ", "|1: ");
                    sbM4.append(j7);
                    sbM4.append("|2: ");
                    sbM4.append(j9);
                    map.put("stage_time", sbM4.toString());
                    map.put("unlock_type", String.valueOf(UnlockTimeStat.mUnlockType));
                    KeyguardStatistic.sendEventData(this.$context, "abnormal_unlock_time_cost", map);
                    Log.i("UnlockTimeStat", "collectDateOfAbnormalUnlockTime, " + UnlockTimeStat.mCustomStr);
                }
            }
        }
        UnlockTimeStat.mWakeupFromSleep = z;
        UnlockTimeStat.onAuthenticatedTime = j;
        UnlockTimeStat.onSystemUIKeyguardGoingAwayTime = j;
        UnlockTimeStat.onStartKeyguardExitAnimationTime = j;
        UnlockTimeStat.onKeyguardLayerHideTime = j;
        UnlockTimeStat.isAuthenticated = z;
    }
}
