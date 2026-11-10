package com.oplus.systemui.statusbar.policy;

import android.content.Context;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Os17MobileIconSets extends MobileIconSets {
    @Override // com.oplus.systemui.statusbar.policy.MobileIconSets
    public final int getDataActivityDirection(boolean z, boolean z2, boolean z3) {
        int i;
        if (z && z2) {
            i = 3;
        } else if (!z || z2) {
            i = (z || !z2) ? 0 : 2;
        } else {
            i = 1;
        }
        MobileIconSets.Companion companion = MobileIconSets.Companion;
        int[] dATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = z3 ? companion.getDATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() : companion.getDATA_ACTIVITY_DIRECTION_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
        if (i < dATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.length) {
            return dATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic[i];
        }
        return 0;
    }

    @Override // com.oplus.systemui.statusbar.policy.MobileIconSets
    public int getNoServiceIcon(Context context, boolean z) {
        return z ? 2131236856 : 2131236822;
    }

    @Override // com.oplus.systemui.statusbar.policy.MobileIconSets
    public int getSignalStrength(Context context, int i, boolean z, int i2, boolean z2, boolean z3) {
        if (z2) {
            return getSignalLevel(i2, MobileIconSets.Companion.getSIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
        }
        return (!(!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSoftBankVoiceType[i]) || z3 || z) ? getSignalLevel(i2, MobileIconSets.Companion.getSIGNAL_STRENGTH_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic()) : getSignalLevel(i2, MobileIconSets.Companion.getSIGNAL_STRENGTH_NO_VOICE_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
    }

    @Override // com.oplus.systemui.statusbar.policy.MobileIconSets
    public final int getSignalStrength(int i, boolean z) {
        MobileIconSets.Companion companion = MobileIconSets.Companion;
        int[] sIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = z ? companion.getSIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() : companion.getSIGNAL_STRENGTH_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
        if (i >= 0 && i < sIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.length) {
            return sIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic[i];
        }
        if (!CustomizeFeatureOption.sIsSupportFiveSignalBar || i < sIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.length) {
            return 0;
        }
        return sIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic[sIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.length - 1];
    }
}
