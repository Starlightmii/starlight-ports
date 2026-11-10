package com.oplus.systemui.seedlingservice.flashlight;

import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.oplus.transition.OplusAnimationController;
import java.util.ArrayList;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightLiveAlertOptions {
    public static final Companion Companion = new Companion(null);
    public static final int ENTRY_AOD = 4;
    public static final int ENTRY_NOTIFICATION = 16;
    public static final int ENTRY_STATUS_BAR = 8;
    public static final String KEY_IGNORE_ENTRY = "ignoreEntry";
    public static final String KEY_IMMERSIVE_CARD_TYPE = "immersiveCardType";
    public static final String KEY_IS_AUTO_CANCEL = "isAutoCancel";
    public static final String KEY_IS_DISMISSIBLE = "isDismissible";
    public static final String KEY_LIVE_ALERT_SERVICE = "liveAlertService";
    public static final String KEY_REQUEST_LOCK_SCREEN_IMMERSIVE = "requestLockScreenImmersive";
    public static final int REMIND_LEVEL_NORMAL = 0;
    public static final int REMIND_LEVEL_STRONG_ALWAYS = 13;
    public static final int REMIND_LEVEL_STRONG_LONG = 12;
    public static final int REMIND_LEVEL_STRONG_SHORT = 11;
    private final String dataSourcePkgName;
    private final Map<String, Object> extensibleActionMap;
    private int ignoreEntry;
    private int immersiveCardType;
    private final boolean isMilestone;
    private String liveAlertService;
    private final ArrayList<Integer> lockScreenShowHostMap;
    private final int remindType;
    private int requestLockScreenImmersive;
    private final ArrayList<Integer> showHostMap;

    public /* synthetic */ FlashlightLiveAlertOptions(boolean z, String str, int i, ArrayList arrayList, ArrayList arrayList2, String str2, int i2, int i3, int i4, Map map, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, (i5 & 4) != 0 ? 0 : i, arrayList, arrayList2, (i5 & 32) != 0 ? "" : str2, (i5 & 64) != 0 ? -1 : i2, (i5 & 128) != 0 ? -1 : i3, (i5 & 256) != 0 ? 0 : i4, (i5 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? null : map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FlashlightLiveAlertOptions copy$default(FlashlightLiveAlertOptions flashlightLiveAlertOptions, boolean z, String str, int i, ArrayList arrayList, ArrayList arrayList2, String str2, int i2, int i3, int i4, Map map, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z = flashlightLiveAlertOptions.isMilestone;
        }
        if ((i5 & 2) != 0) {
            str = flashlightLiveAlertOptions.dataSourcePkgName;
        }
        if ((i5 & 4) != 0) {
            i = flashlightLiveAlertOptions.remindType;
        }
        if ((i5 & 8) != 0) {
            arrayList = flashlightLiveAlertOptions.showHostMap;
        }
        if ((i5 & 16) != 0) {
            arrayList2 = flashlightLiveAlertOptions.lockScreenShowHostMap;
        }
        if ((i5 & 32) != 0) {
            str2 = flashlightLiveAlertOptions.liveAlertService;
        }
        if ((i5 & 64) != 0) {
            i2 = flashlightLiveAlertOptions.immersiveCardType;
        }
        if ((i5 & 128) != 0) {
            i3 = flashlightLiveAlertOptions.requestLockScreenImmersive;
        }
        if ((i5 & 256) != 0) {
            i4 = flashlightLiveAlertOptions.ignoreEntry;
        }
        if ((i5 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0) {
            map = flashlightLiveAlertOptions.extensibleActionMap;
        }
        int i6 = i4;
        Map map2 = map;
        int i7 = i2;
        int i8 = i3;
        ArrayList arrayList3 = arrayList2;
        String str3 = str2;
        return flashlightLiveAlertOptions.copy(z, str, i, arrayList, arrayList3, str3, i7, i8, i6, map2);
    }

    public final boolean component1() {
        return this.isMilestone;
    }

    public final Map<String, Object> component10() {
        return this.extensibleActionMap;
    }

    public final String component2() {
        return this.dataSourcePkgName;
    }

    public final int component3() {
        return this.remindType;
    }

    public final ArrayList<Integer> component4() {
        return this.showHostMap;
    }

    public final ArrayList<Integer> component5() {
        return this.lockScreenShowHostMap;
    }

    public final String component6() {
        return this.liveAlertService;
    }

    public final int component7() {
        return this.immersiveCardType;
    }

    public final int component8() {
        return this.requestLockScreenImmersive;
    }

    public final int component9() {
        return this.ignoreEntry;
    }

    public final FlashlightLiveAlertOptions copy(boolean z, String str, int i, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, String str2, int i2, int i3, int i4, Map<String, ? extends Object> map) {
        return new FlashlightLiveAlertOptions(z, str, i, arrayList, arrayList2, str2, i2, i3, i4, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashlightLiveAlertOptions)) {
            return false;
        }
        FlashlightLiveAlertOptions flashlightLiveAlertOptions = (FlashlightLiveAlertOptions) obj;
        return this.isMilestone == flashlightLiveAlertOptions.isMilestone && Intrinsics.areEqual(this.dataSourcePkgName, flashlightLiveAlertOptions.dataSourcePkgName) && this.remindType == flashlightLiveAlertOptions.remindType && Intrinsics.areEqual(this.showHostMap, flashlightLiveAlertOptions.showHostMap) && Intrinsics.areEqual(this.lockScreenShowHostMap, flashlightLiveAlertOptions.lockScreenShowHostMap) && Intrinsics.areEqual(this.liveAlertService, flashlightLiveAlertOptions.liveAlertService) && this.immersiveCardType == flashlightLiveAlertOptions.immersiveCardType && this.requestLockScreenImmersive == flashlightLiveAlertOptions.requestLockScreenImmersive && this.ignoreEntry == flashlightLiveAlertOptions.ignoreEntry && Intrinsics.areEqual(this.extensibleActionMap, flashlightLiveAlertOptions.extensibleActionMap);
    }

    public final String getDataSourcePkgName() {
        return this.dataSourcePkgName;
    }

    public final Map<String, Object> getExtensibleActionMap() {
        return this.extensibleActionMap;
    }

    public final int getIgnoreEntry() {
        return this.ignoreEntry;
    }

    public final int getImmersiveCardType() {
        return this.immersiveCardType;
    }

    public final String getLiveAlertService() {
        return this.liveAlertService;
    }

    public final ArrayList<Integer> getLockScreenShowHostMap() {
        return this.lockScreenShowHostMap;
    }

    public final int getRemindType() {
        return this.remindType;
    }

    public final int getRequestLockScreenImmersive() {
        return this.requestLockScreenImmersive;
    }

    public final ArrayList<Integer> getShowHostMap() {
        return this.showHostMap;
    }

    public int hashCode() {
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.ignoreEntry, MonoSpline$.ExternalSyntheticOutline0.m(this.requestLockScreenImmersive, MonoSpline$.ExternalSyntheticOutline0.m(this.immersiveCardType, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((this.lockScreenShowHostMap.hashCode() + ((this.showHostMap.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.remindType, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.isMilestone) * 31, 31, this.dataSourcePkgName), 31)) * 31)) * 31, 31, this.liveAlertService), 31), 31), 31);
        Map<String, Object> map = this.extensibleActionMap;
        return iM + (map == null ? 0 : map.hashCode());
    }

    public final boolean isMilestone() {
        return this.isMilestone;
    }

    public final void setIgnoreEntry(int i) {
        this.ignoreEntry = i;
    }

    public final void setImmersiveCardType(int i) {
        this.immersiveCardType = i;
    }

    public final void setLiveAlertService(String str) {
        this.liveAlertService = str;
    }

    public final void setRequestLockScreenImmersive(int i) {
        this.requestLockScreenImmersive = i;
    }

    public String toString() {
        boolean z = this.isMilestone;
        String str = this.dataSourcePkgName;
        int i = this.remindType;
        ArrayList<Integer> arrayList = this.showHostMap;
        ArrayList<Integer> arrayList2 = this.lockScreenShowHostMap;
        String str2 = this.liveAlertService;
        int i2 = this.immersiveCardType;
        int i3 = this.requestLockScreenImmersive;
        int i4 = this.ignoreEntry;
        Map<String, Object> map = this.extensibleActionMap;
        StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("FlashlightLiveAlertOptions(isMilestone=", ", dataSourcePkgName=", str, z, ", remindType=");
        sbM.append(i);
        sbM.append(", showHostMap=");
        sbM.append(arrayList);
        sbM.append(", lockScreenShowHostMap=");
        sbM.append(arrayList2);
        sbM.append(", liveAlertService=");
        sbM.append(str2);
        sbM.append(", immersiveCardType=");
        RectList$.ExternalSyntheticOutline0.m(i2, i3, ", requestLockScreenImmersive=", ", ignoreEntry=", sbM);
        sbM.append(i4);
        sbM.append(", extensibleActionMap=");
        sbM.append(map);
        sbM.append(")");
        return sbM.toString();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public FlashlightLiveAlertOptions(boolean z, String str, int i, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, String str2, int i2, int i3, int i4, Map<String, ? extends Object> map) {
        this.isMilestone = z;
        this.dataSourcePkgName = str;
        this.remindType = i;
        this.showHostMap = arrayList;
        this.lockScreenShowHostMap = arrayList2;
        this.liveAlertService = str2;
        this.immersiveCardType = i2;
        this.requestLockScreenImmersive = i3;
        this.ignoreEntry = i4;
        this.extensibleActionMap = map;
    }
}
