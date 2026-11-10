package com.oplus.systemui.statusbar.phone.signal.network;

import android.content.Context;
import com.android.settingslib.SignalIcon;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import com.oplus.systemui.statusbar.policy.TelephonyIcons;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusCustMobileTypeSets {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCustMobileTypeSets";
    private static OplusCustMobileTypeSets instance;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusCustMobileTypeSets getInstance(Context context) {
            if (OplusCustMobileTypeSets.instance == null) {
                OplusCustMobileTypeSets.instance = new OplusCustMobileTypeSets(context);
            }
            OplusCustMobileTypeSets oplusCustMobileTypeSets = OplusCustMobileTypeSets.instance;
            oplusCustMobileTypeSets.getClass();
            return oplusCustMobileTypeSets;
        }
    }

    public OplusCustMobileTypeSets(Context context) {
        this.context = context;
    }

    public final Map<String, SignalIcon.MobileIconGroup> getCustomizeNetworkIconSets(HashMap<String, SignalIcon.MobileIconGroup> map, int i, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "getCustomizeNetworkIconSets has slotId = ", "OplusCustMobileTypeSets-->", "SystemUi--Statusbar");
        }
        HashMap<String, SignalIcon.MobileIconGroup> map2 = new HashMap<>(map);
        boolean z2 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShowGForGPRS[i];
        boolean z3 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow2GForE[i];
        boolean z4 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow3GForH[i];
        boolean z5 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow3GForHPlus[i];
        boolean z6 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShowLTEFor4G[i];
        boolean z7 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShowLTEFor4GPlus[i];
        boolean z8 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow4GFor4GPlus[i];
        boolean z9 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow4Dot5GFor4G[i];
        boolean z10 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow4Dot5GFor4GPlus[i];
        boolean z11 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShowLTEPlusFor4GPlus[i];
        boolean z12 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShowGFor2G[i];
        boolean z13 = !CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportShow3GPlusForH[i];
        boolean z14 = CustomizeFeatureOption.checkSlotIdValid(i) ? CustomizeFeatureOption.sIsSupportShowHForHPlus[i] : false;
        Iterator<Map.Entry<String, SignalIcon.MobileIconGroup>> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, SignalIcon.MobileIconGroup> next = it.next();
            it = it;
            z2 = z2;
            if (MobileIconSets.Companion.toIconKey(1).equals(next.getKey()) && z2) {
                next.setValue(TelephonyIcons.G);
            }
            if (TelephonyIcons.E.equals(next.getValue()) && z3) {
                next.setValue(TelephonyIcons.TWO_G);
            } else {
                SignalIcon.MobileIconGroup mobileIconGroup = TelephonyIcons.H;
                if (mobileIconGroup.equals(next.getValue()) && z4) {
                    next.setValue(TelephonyIcons.THREE_G);
                } else {
                    SignalIcon.MobileIconGroup mobileIconGroup2 = TelephonyIcons.H_PLUS;
                    boolean z15 = z14;
                    if (mobileIconGroup2.equals(next.getValue()) && z5) {
                        next.setValue(TelephonyIcons.THREE_G);
                    } else if (mobileIconGroup2.equals(next.getValue()) && z15) {
                        next.setValue(mobileIconGroup);
                    } else {
                        SignalIcon.MobileIconGroup mobileIconGroup3 = TelephonyIcons.FOUR_G;
                        if (mobileIconGroup3.equals(next.getValue()) && z6) {
                            next.setValue(TelephonyIcons.LTE);
                        } else {
                            SignalIcon.MobileIconGroup mobileIconGroup4 = TelephonyIcons.FOUR_G_PLUS;
                            boolean z16 = z3;
                            if (mobileIconGroup4.equals(next.getValue()) && z7) {
                                next.setValue(TelephonyIcons.LTE);
                            } else if (mobileIconGroup4.equals(next.getValue()) && z8) {
                                next.setValue(mobileIconGroup3);
                            } else if (mobileIconGroup3.equals(next.getValue()) && z9) {
                                next.setValue(TelephonyIcons.FOUR_DOT_FIVE_G);
                            } else if (mobileIconGroup4.equals(next.getValue()) && z10) {
                                next.setValue(TelephonyIcons.FOUR_DOT_FIVE_G);
                            } else if (mobileIconGroup4.equals(next.getValue()) && z11) {
                                next.setValue(TelephonyIcons.LTE_PLUS);
                            } else if (TelephonyIcons.TWO_G.equals(next.getValue()) && z12) {
                                next.setValue(TelephonyIcons.G);
                            } else if (mobileIconGroup.equals(next.getValue()) && z13) {
                                next.setValue(TelephonyIcons.THREE_G_PLUS);
                            }
                            it = it;
                            z14 = z15;
                            z3 = z16;
                        }
                    }
                    z14 = z15;
                }
            }
        }
        getCustomizeTmoNetworkIconSets(map2, z);
        return map2;
    }

    public final Map<String, SignalIcon.MobileIconGroup> getCustomizeTmoNetworkIconSets(HashMap<String, SignalIcon.MobileIconGroup> map, boolean z) {
        if (CustomizeFeatureOption.sIsTmoSignalViewLayout) {
            if (z) {
                map.put(MobileIconSets.Companion.toIconKey(15), TelephonyIcons.THREE_G);
            } else {
                map.put(MobileIconSets.Companion.toIconKey(15), TMOMobileIconSets.Companion.getICON_4G_H_PLUS());
            }
            MobileIconSets.Companion companion = MobileIconSets.Companion;
            map.put(companion.toIconKey(10), TelephonyIcons.THREE_G);
            map.put(companion.toIconKey(1), TelephonyIcons.G);
            map.put(companion.toIconKey(2), TelephonyIcons.TWO_G);
        }
        return map;
    }
}
