package com.oplus.systemui.statusbar.policy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.VectorDrawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.car.app.serialization.Bundler$;
import com.android.settingslib.SignalIcon;
import com.android.settingslib.mobile.MobileMappings;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MobileIconSets {
    private static final String TAG = "MobileIconSets";
    public static final Companion Companion = new Companion();
    private static final int[] STACKED_SIGNAL_STRENGTH_PRIMARY = {2131236810, 2131236811, 2131236812, 2131236813, 2131236814};
    private static final int[] STACKED_SIGNAL_STRENGTH_SECONDARY = {2131236816, 2131236817, 2131236818, 2131236819, 2131236820};
    private static final int[] STACKED_SIGNAL_STRENGTH_PRIMARY_NO_VOICE = {2131236849, 2131236843, 2131236844, 2131236845, 2131236846};
    private static final int[] STACKED_SIGNAL_STRENGTH_SECONDARY_NO_VOICE = {2131236850, 2131236851, 2131236852, 2131236853, 2131236854};
    private static final int[] STACKED_SIGNAL_STRENGTH_SOFT_PRIMARY = {2131236868, 2131236869, 2131236870, 2131236871, 2131236872};
    private static final int[] STACKED_SIGNAL_STRENGTH_SOFT_SECONDARY = {2131236874, 2131236875, 2131236876, 2131236877, 2131236878};
    private static final int[] SIGNAL_STRENGTH = {2131236824, 2131236826, 2131236828, 2131236830, 2131236832};
    private static final int[] SIGNAL_STRENGTH_NO_VOICE = {2131236847, 2131236834, 2131236836, 2131236838, 2131236840};
    private static final int[] SIGNAL_STRENGTH_SOFT = {2131236858, 2131236860, 2131236862, 2131236864, 2131236866};
    private static final int[] SIGNAL_STRENGTH_OS17 = {2131236825, 2131236827, 2131236829, 2131236831, 2131236833};
    private static final int[] SIGNAL_STRENGTH_SOFT_OS17 = {2131236859, 2131236861, 2131236863, 2131236865, 2131236867};
    private static final int[] SIGNAL_STRENGTH_NO_VOICE_OS17 = {2131236848, 2131236835, 2131236837, 2131236839, 2131236841};
    private static final int[] DATA_ACTIVITY_DIRECTION_OS17 = {2131236745, 2131236747, 2131236751, 2131236749, 2131236745};
    private static final int[] DATA_ACTIVITY_DIRECTION_SOFT_OS17 = {2131236753, 2131236755, 2131236759, 2131236757, 2131236753};
    private static final int[] DATA_ACTIVITY_DIRECTION_SOFT = {2131236752, 2131236754, 2131236758, 2131236756, 2131236752};
    private static final int[] DATA_ACTIVITY_DIRECTION = {2131236744, 2131236746, 2131236750, 2131236748, 2131236744};
    private static final int[] DATA_ACTIVITY_STACKED_INDICATORS = {2131236883, 2131236884, 2131236886, 2131236885, 2131236883};
    private static final int[] DATA_ACTIVITY_SOFT_STACKED_INDICATORS = {2131236879, 2131236880, 2131236882, 2131236881, 2131236879};
    private static final int[] VOLTE_ICON = {2131236894, 2131236901, 2131236902, 2131236903};
    private static final int[] VOLTE_ICON_EX = {2131236897, 2131236898, 2131236899, 2131236900};
    private static final int[] VOWIFI_ICON = {2131236909, 2131236911, 2131236912, 2131236910};
    private static final int[] VOWIFI_LETTER_ICON = {2131236806, 2131236808, 2131236809, 2131236807};
    private static final int[] DATA_TYPE = {2131236780, 2131236779, 2131236768, 2131236769, 2131236770, 2131236781, 2131236782, 2131236773, 2131236774, 2131236771, 2131236783, 2131236784, 2131236775, 2131236949, 2131236948, 2131236947, 2131236946};
    private static int[][] DATA_TYPE_ICON_SIZE = {new int[]{2131236780, 19}, new int[]{2131236779, 19}, new int[]{2131236768, 21}, new int[]{2131236769, 21}, new int[]{2131236770, 21}, new int[]{2131236781, 19}, new int[]{2131236782, 19}, new int[]{2131236773, 21}, new int[]{2131236774, 21}, new int[]{2131236771, 21}, new int[]{2131236783, 22}, new int[]{2131236784, 22}, new int[]{2131236775, 21}};
    private static final int[] DATA_TYPE_SOFT = {2131236793, 2131236792, 2131236785, 2131236786, 2131236787, 2131236794, 2131236795, 2131236789, 2131236790, 2131236789, 2131236796, 2131236797, 2131236791};
    private static final int[][] SIGNAL_OS16_TO_OS17 = {new int[]{2131236824, 2131236825}, new int[]{2131236826, 2131236827}, new int[]{2131236828, 2131236829}, new int[]{2131236830, 2131236831}, new int[]{2131236832, 2131236833}, new int[]{2131236847, 2131236848}, new int[]{2131236834, 2131236835}, new int[]{2131236836, 2131236837}, new int[]{2131236838, 2131236839}, new int[]{2131236840, 2131236841}, new int[]{2131236858, 2131236859}, new int[]{2131236860, 2131236861}, new int[]{2131236862, 2131236863}, new int[]{2131236864, 2131236865}, new int[]{2131236866, 2131236867}, new int[]{2131236821, 2131236822}, new int[]{2131236855, 2131236856}};
    private static final int[][] DATA_ACTIVITY_OS16_TO_OS17 = {new int[]{2131236744, 2131236745}, new int[]{2131236746, 2131236747}, new int[]{2131236750, 2131236751}, new int[]{2131236748, 2131236749}, new int[]{2131236752, 2131236753}, new int[]{2131236754, 2131236755}, new int[]{2131236758, 2131236759}, new int[]{2131236756, 2131236757}};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getDATA_ACTIVITY_DIRECTION_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic$annotations() {
        }

        public static /* synthetic */ void getDATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic$annotations() {
        }

        public static /* synthetic */ void getDATA_TYPE$annotations() {
        }

        public static /* synthetic */ void getDATA_TYPE_SOFT$annotations() {
        }

        public static /* synthetic */ void getSIGNAL_STRENGTH_NO_VOICE_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic$annotations() {
        }

        public static /* synthetic */ void getSIGNAL_STRENGTH_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic$annotations() {
        }

        public static /* synthetic */ void getSIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic$annotations() {
        }

        public static int getVowifiIcon(int i) {
            if (i >= MobileIconSets.VOWIFI_ICON.length || i < 0) {
                return 0;
            }
            return CustomizeFeatureOption.sIsLetterVowifiIcon ? MobileIconSets.VOWIFI_LETTER_ICON[i] : MobileIconSets.VOWIFI_ICON[i];
        }

        public static boolean isThemeDrawable(int i, Context context) {
            try {
                return !(AppCompatResources.getDrawable(context, i) instanceof VectorDrawable);
            } catch (Resources.NotFoundException e) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, MobileIconSets.TAG, "isThemeDrawable failed, resId=" + i + ", " + e);
                return false;
            }
        }

        public final int[] getDATA_ACTIVITY_DIRECTION_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
            return MobileIconSets.DATA_ACTIVITY_DIRECTION_OS17;
        }

        public final int[] getDATA_ACTIVITY_DIRECTION_SOFT() {
            return MobileIconSets.DATA_ACTIVITY_DIRECTION_SOFT;
        }

        public final int[] getDATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
            return MobileIconSets.DATA_ACTIVITY_DIRECTION_SOFT_OS17;
        }

        public final int[] getDATA_TYPE() {
            return MobileIconSets.DATA_TYPE;
        }

        public final int[][] getDATA_TYPE_ICON_SIZE() {
            return MobileIconSets.DATA_TYPE_ICON_SIZE;
        }

        public final int[] getDATA_TYPE_SOFT() {
            return MobileIconSets.DATA_TYPE_SOFT;
        }

        public final int getNoHdIcon() {
            return 2131236798;
        }

        public final int[] getSIGNAL_STRENGTH_NO_VOICE_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
            return MobileIconSets.SIGNAL_STRENGTH_NO_VOICE_OS17;
        }

        public final int[] getSIGNAL_STRENGTH_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
            return MobileIconSets.SIGNAL_STRENGTH_OS17;
        }

        public final int[] getSIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
            return MobileIconSets.SIGNAL_STRENGTH_SOFT_OS17;
        }

        public final int getVolteIcon(Context context, int i) {
            if (context.getResources().getBoolean(2131034183)) {
                if (i < 0 || i >= MobileIconSets.VOLTE_ICON_EX.length) {
                    return 0;
                }
                return MobileIconSets.VOLTE_ICON_EX[i];
            }
            if (i < 0 || i >= MobileIconSets.VOLTE_ICON.length) {
                return 0;
            }
            return MobileIconSets.VOLTE_ICON[i];
        }

        public final boolean isAdaptOs16IndependentSignal(Context context) {
            Integer numValueOf = Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.SIGNAL_STRENGTH));
            Integer numValueOf2 = Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.SIGNAL_STRENGTH_SOFT));
            Integer numValueOf3 = Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.DATA_ACTIVITY_DIRECTION));
            Integer numValueOf4 = Integer.valueOf(ArraysKt___ArraysKt.first(getDATA_ACTIVITY_DIRECTION_SOFT()));
            int[][] iArr = TelephonyIcons.DATA_TYPE;
            List listListOf = CollectionsKt__CollectionsKt.listOf(new Integer[]{numValueOf, numValueOf2, numValueOf3, numValueOf4, 2131236775});
            if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
                return false;
            }
            Iterator it = listListOf.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                MobileIconSets.Companion.getClass();
                if (isThemeDrawable(iIntValue, context)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isAdaptOs17IndependentSignal(Context context) {
            List listListOf = CollectionsKt__CollectionsKt.listOf(new Integer[]{Integer.valueOf(ArraysKt___ArraysKt.first(getSIGNAL_STRENGTH_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic())), Integer.valueOf(ArraysKt___ArraysKt.first(getSIGNAL_STRENGTH_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic())), Integer.valueOf(ArraysKt___ArraysKt.first(getDATA_ACTIVITY_DIRECTION_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic())), Integer.valueOf(ArraysKt___ArraysKt.first(getDATA_ACTIVITY_DIRECTION_SOFT_OS17$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic()))});
            if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
                return false;
            }
            Iterator it = listListOf.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                MobileIconSets.Companion.getClass();
                if (isThemeDrawable(iIntValue, context)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isAdaptOs17StackedSignal(Context context) {
            List listListOf = CollectionsKt__CollectionsKt.listOf(new Integer[]{Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.STACKED_SIGNAL_STRENGTH_PRIMARY)), Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.STACKED_SIGNAL_STRENGTH_SECONDARY)), Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.STACKED_SIGNAL_STRENGTH_SOFT_PRIMARY)), Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.STACKED_SIGNAL_STRENGTH_SOFT_SECONDARY)), Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.DATA_ACTIVITY_STACKED_INDICATORS)), Integer.valueOf(ArraysKt___ArraysKt.first(MobileIconSets.DATA_ACTIVITY_SOFT_STACKED_INDICATORS))});
            if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
                return false;
            }
            Iterator it = listListOf.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                MobileIconSets.Companion.getClass();
                if (isThemeDrawable(iIntValue, context)) {
                    return true;
                }
            }
            return false;
        }

        public final void setDATA_TYPE_ICON_SIZE(int[][] iArr) {
            MobileIconSets.DATA_TYPE_ICON_SIZE = iArr;
        }

        public final String toDisplayIconKey(int i) {
            if (i == 1) {
                return Bundler$.ExternalSyntheticOutline0.m(toIconKey(13), "_CA");
            }
            if (i == 2) {
                return Bundler$.ExternalSyntheticOutline0.m(toIconKey(13), "_CA_Plus");
            }
            if (i == 3 || i == 4) {
                return toIconKey(20);
            }
            if (i == 5) {
                return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_Plus");
            }
            if (i == 201) {
                return Bundler$.ExternalSyntheticOutline0.m(toIconKey(13), "_4_DOT_5G");
            }
            if (i == 202) {
                return Bundler$.ExternalSyntheticOutline0.m(toIconKey(13), "_4_DOT_5G_PLUS");
            }
            switch (i) {
                case 101:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_UC");
                case 102:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_5GUWB");
                case 103:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_5GPlus");
                case 104:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_5GE");
                case 105:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_5GA");
                case ZoomAnimationUtils.TRANSIT_POCKET_STUDIO_OPEN_TO_ZOOM /* 106 */:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_5GPlusPlus");
                case 107:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_5_DOT_5G");
                case 108:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_TIGO_CONNECTED");
                case 109:
                    return Bundler$.ExternalSyntheticOutline0.m(toIconKey(20), "_TIGO_NOT_RESTRICTED");
                default:
                    return "unsupported";
            }
        }

        public final String toIconKey(int i) {
            return String.valueOf(i);
        }
    }

    public static final int[] getDATA_TYPE() {
        return Companion.getDATA_TYPE();
    }

    public static final int[] getDATA_TYPE_SOFT() {
        return Companion.getDATA_TYPE_SOFT();
    }

    public static int getStackedActivityIndicatorIcon(boolean z, boolean z2, boolean z3) {
        char c;
        if (z && z2) {
            c = 3;
        } else if (z) {
            c = 1;
        } else {
            c = z2 ? (char) 2 : (char) 0;
        }
        return z3 ? DATA_ACTIVITY_SOFT_STACKED_INDICATORS[c] : DATA_ACTIVITY_STACKED_INDICATORS[c];
    }

    public int getDataActivityDirection(boolean z, boolean z2, boolean z3) {
        char c;
        if (z && z2) {
            c = 3;
        } else if (!z || z2) {
            c = (z || !z2) ? (char) 0 : (char) 2;
        } else {
            c = 1;
        }
        return z3 ? DATA_ACTIVITY_DIRECTION_SOFT[c] : DATA_ACTIVITY_DIRECTION[c];
    }

    public int getDataTypeIcon(int i, boolean z) {
        if (z) {
            int[][] iArr = TelephonyIcons.DATA_TYPE;
            for (int i2 = 0; i2 < 20; i2++) {
                int[] iArr2 = TelephonyIcons.DATA_TYPE[i2];
                if (iArr2[0] == i) {
                    return iArr2[1];
                }
            }
        }
        return i;
    }

    public int getNoServiceIcon(Context context, boolean z) {
        return z ? 2131236855 : 2131236821;
    }

    public final int getSignalLevel(int i, int[] iArr) {
        if (i < 0 || i >= iArr.length) {
            return 0;
        }
        return iArr[i];
    }

    public int getSignalStrength(int i, boolean z) {
        if (z) {
            if (i >= 0) {
                int[] iArr = SIGNAL_STRENGTH_SOFT;
                if (i < iArr.length) {
                    return iArr[i];
                }
            }
            if (!CustomizeFeatureOption.sIsSupportFiveSignalBar) {
                return 0;
            }
            int[] iArr2 = SIGNAL_STRENGTH;
            if (i >= iArr2.length) {
                return iArr2[iArr2.length - 1];
            }
            return 0;
        }
        if (i >= 0) {
            int[] iArr3 = SIGNAL_STRENGTH;
            if (i < iArr3.length) {
                return iArr3[i];
            }
        }
        if (!CustomizeFeatureOption.sIsSupportFiveSignalBar) {
            return 0;
        }
        int[] iArr4 = SIGNAL_STRENGTH;
        if (i >= iArr4.length) {
            return iArr4[iArr4.length - 1];
        }
        return 0;
    }

    public int getStackedNoServiceIcon(Context context, boolean z, boolean z2) {
        if (z2) {
            return z ? 2131236873 : 2131236874;
        }
        return z ? 2131236815 : 2131236816;
    }

    public final int getStackedSignalStrength(boolean z, boolean z2, int i, boolean z3, boolean z4, int i2) {
        int[] iArr;
        if (z3) {
            iArr = z ? STACKED_SIGNAL_STRENGTH_SOFT_PRIMARY : STACKED_SIGNAL_STRENGTH_SOFT_SECONDARY;
        } else {
            if (!(!CustomizeFeatureOption.checkSlotIdValid(i2) ? false : CustomizeFeatureOption.sIsSoftBankVoiceType[i2]) || z4 || z2) {
                iArr = z ? STACKED_SIGNAL_STRENGTH_PRIMARY : STACKED_SIGNAL_STRENGTH_SECONDARY;
            } else {
                iArr = z ? STACKED_SIGNAL_STRENGTH_PRIMARY_NO_VOICE : STACKED_SIGNAL_STRENGTH_SECONDARY_NO_VOICE;
            }
        }
        return getSignalLevel(i, iArr);
    }

    public final Map<String, SignalIcon.MobileIconGroup> mapNetworkIconSets(MobileMappings.Config config) {
        HashMap map = new HashMap();
        Companion companion = Companion;
        String iconKey = companion.toIconKey(5);
        SignalIcon.MobileIconGroup mobileIconGroup = TelephonyIcons.THREE_G;
        map.put(iconKey, mobileIconGroup);
        map.put(companion.toIconKey(6), mobileIconGroup);
        map.put(companion.toIconKey(12), mobileIconGroup);
        map.put(companion.toIconKey(14), mobileIconGroup);
        if (config.show4gFor3g) {
            map.put(companion.toIconKey(3), TelephonyIcons.FOUR_G);
        } else {
            map.put(companion.toIconKey(3), mobileIconGroup);
        }
        map.put(companion.toIconKey(17), mobileIconGroup);
        map.put(companion.toIconKey(20), TelephonyIcons.FIVE_G_SA);
        String iconKey2 = companion.toIconKey(2);
        SignalIcon.MobileIconGroup mobileIconGroup2 = TelephonyIcons.E;
        map.put(iconKey2, mobileIconGroup2);
        map.put(companion.toIconKey(0), TelephonyIcons.UNKNOWN);
        map.put(companion.toIconKey(2), mobileIconGroup2);
        String iconKey3 = companion.toIconKey(4);
        SignalIcon.MobileIconGroup mobileIconGroup3 = TelephonyIcons.TWO_G;
        map.put(iconKey3, mobileIconGroup3);
        map.put(companion.toIconKey(7), mobileIconGroup3);
        map.put(companion.toIconKey(1), mobileIconGroup3);
        map.put(companion.toIconKey(16), mobileIconGroup3);
        map.put(companion.toIconKey(11), mobileIconGroup3);
        SignalIcon.MobileIconGroup mobileIconGroup4 = TelephonyIcons.H;
        SignalIcon.MobileIconGroup mobileIconGroup5 = TelephonyIcons.H_PLUS;
        map.put(companion.toIconKey(8), mobileIconGroup4);
        map.put(companion.toIconKey(9), mobileIconGroup4);
        map.put(companion.toIconKey(10), mobileIconGroup4);
        map.put(companion.toIconKey(15), mobileIconGroup5);
        String iconKey4 = companion.toIconKey(13);
        SignalIcon.MobileIconGroup mobileIconGroup6 = TelephonyIcons.FOUR_G;
        map.put(iconKey4, mobileIconGroup6);
        String displayIconKey = companion.toDisplayIconKey(1);
        SignalIcon.MobileIconGroup mobileIconGroup7 = TelephonyIcons.FOUR_G_PLUS;
        map.put(displayIconKey, mobileIconGroup7);
        map.put(companion.toIconKey(19), mobileIconGroup7);
        map.put(companion.toIconKey(18), mobileIconGroup6);
        map.put(companion.toDisplayIconKey(2), TelephonyIcons.LTE_CA_5G_E);
        String displayIconKey2 = companion.toDisplayIconKey(3);
        SignalIcon.MobileIconGroup mobileIconGroup8 = TelephonyIcons.NR_5G;
        map.put(displayIconKey2, mobileIconGroup8);
        map.put(companion.toDisplayIconKey(4), TelephonyIcons.NR_5G_PLUS);
        map.put(companion.toDisplayIconKey(5), mobileIconGroup8);
        map.put(companion.toDisplayIconKey(101), TelephonyIcons.FIVE_G_UC);
        map.put(companion.toDisplayIconKey(102), TelephonyIcons.FIVE_G_UWB);
        map.put(companion.toDisplayIconKey(103), TelephonyIcons.FIVE_G_PLUS);
        map.put(companion.toDisplayIconKey(104), TelephonyIcons.FIVE_G_E);
        map.put(companion.toDisplayIconKey(105), TelephonyIcons.FIVE_G_A);
        map.put(companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_STATE_SWITCH), TelephonyIcons.FOUR_DOT_FIVE_G);
        map.put(companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_FULL), TelephonyIcons.FOUR_DOT_FIVE_G_PLUS);
        map.put(companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_POCKET_STUDIO_OPEN_TO_ZOOM), TelephonyIcons.FIVE_G_PLUS_PLUS);
        map.put(companion.toDisplayIconKey(107), TelephonyIcons.FIVE_DOT_FIVE_G);
        map.put(companion.toDisplayIconKey(108), TelephonyIcons.TIGO_5G_CONNECTED);
        map.put(companion.toDisplayIconKey(109), TelephonyIcons.TIGO_5G_NOT_RESTRICTED);
        return map;
    }

    public int getSignalStrength(Context context, int i, boolean z, int i2, boolean z2, boolean z3) {
        if (z2) {
            return getSignalLevel(i2, SIGNAL_STRENGTH_SOFT);
        }
        if ((!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSoftBankVoiceType[i]) && !z3 && !z) {
            return getSignalLevel(i2, SIGNAL_STRENGTH_NO_VOICE);
        }
        return getSignalLevel(i2, SIGNAL_STRENGTH);
    }
}
