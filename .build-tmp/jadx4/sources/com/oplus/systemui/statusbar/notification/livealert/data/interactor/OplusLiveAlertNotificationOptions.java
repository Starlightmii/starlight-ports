package com.oplus.systemui.statusbar.notification.livealert.data.interactor;

import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import java.util.ArrayList;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationOptions {
    public static final Companion Companion = new Companion();
    public static final String KEY_IGNORE_ENTRY = "ignoreEntry";
    public static final String KEY_IMMERSIVE_CARD_TYPE = "immersiveCardType";
    public static final String KEY_IS_AUTO_CANCEL = "isAutoCancel";
    public static final String KEY_IS_DISMISSIBLE = "isDismissible";
    public static final String KEY_LIVE_ALERT_SERVICE = "liveAlertService";
    public static final String KEY_REQUEST_LOCK_SCREEN_IMMERSIVE = "requestLockScreenImmersive";
    private final String dataSourcePkgName;
    private final Map<String, Object> extensibleActionMap;
    public final boolean isMilestone;
    private final ArrayList<Integer> lockScreenShowHostMap;
    public final int remindType;
    private final ArrayList<Integer> showHostMap;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusLiveAlertNotificationOptions(boolean z, String str, int i, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, Map<String, ? extends Object> map) {
        this.isMilestone = z;
        this.dataSourcePkgName = str;
        this.remindType = i;
        this.showHostMap = arrayList;
        this.lockScreenShowHostMap = arrayList2;
        this.extensibleActionMap = map;
    }

    public final String component2() {
        return this.dataSourcePkgName;
    }

    public final ArrayList<Integer> component4() {
        return this.showHostMap;
    }

    public final ArrayList<Integer> component5() {
        return this.lockScreenShowHostMap;
    }

    public final Map<String, Object> component6() {
        return this.extensibleActionMap;
    }

    public final OplusLiveAlertNotificationOptions copy(boolean z, String str, int i, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, Map<String, ? extends Object> map) {
        return new OplusLiveAlertNotificationOptions(z, str, i, arrayList, arrayList2, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusLiveAlertNotificationOptions)) {
            return false;
        }
        OplusLiveAlertNotificationOptions oplusLiveAlertNotificationOptions = (OplusLiveAlertNotificationOptions) obj;
        return this.isMilestone == oplusLiveAlertNotificationOptions.isMilestone && Intrinsics.areEqual(this.dataSourcePkgName, oplusLiveAlertNotificationOptions.dataSourcePkgName) && this.remindType == oplusLiveAlertNotificationOptions.remindType && Intrinsics.areEqual(this.showHostMap, oplusLiveAlertNotificationOptions.showHostMap) && Intrinsics.areEqual(this.lockScreenShowHostMap, oplusLiveAlertNotificationOptions.lockScreenShowHostMap) && Intrinsics.areEqual(this.extensibleActionMap, oplusLiveAlertNotificationOptions.extensibleActionMap);
    }

    public final String getDataSourcePkgName() {
        return this.dataSourcePkgName;
    }

    public final Map<String, Object> getExtensibleActionMap() {
        return this.extensibleActionMap;
    }

    public final ArrayList<Integer> getLockScreenShowHostMap() {
        return this.lockScreenShowHostMap;
    }

    public final int getRemindType() {
        return this.remindType;
    }

    public final ArrayList<Integer> getShowHostMap() {
        return this.showHostMap;
    }

    public final int hashCode() {
        int iHashCode = (this.lockScreenShowHostMap.hashCode() + ((this.showHostMap.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.remindType, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.isMilestone) * 31, 31, this.dataSourcePkgName), 31)) * 31)) * 31;
        Map<String, Object> map = this.extensibleActionMap;
        return iHashCode + (map == null ? 0 : map.hashCode());
    }

    public String toString() {
        String str = this.dataSourcePkgName;
        ArrayList<Integer> arrayList = this.showHostMap;
        ArrayList<Integer> arrayList2 = this.lockScreenShowHostMap;
        Map<String, Object> map = this.extensibleActionMap;
        StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("OplusLiveAlertNotificationOptions(isMilestone=", ", dataSourcePkgName=", str, this.isMilestone, ", remindType=");
        sbM.append(this.remindType);
        sbM.append(", showHostMap=");
        sbM.append(arrayList);
        sbM.append(", lockScreenShowHostMap=");
        sbM.append(arrayList2);
        sbM.append(", extensibleActionMap=");
        sbM.append(map);
        sbM.append(")");
        return sbM.toString();
    }
}
