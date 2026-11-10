package com.oplus.systemui.statusbar.seeding;

import android.app.Notification;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SystemSettings;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FluidReplaceNotificationManager {
    private static final String DEFAULT_FLUID_MAP_KEY = "fluid_seeding_notification_default";
    private static final String FLUID_MAP_KEY = "fluid_seeding_notification";
    private static final String FLUID_SERVICE_ID_EXTRA = "op_fluid_serviceId";
    private static final String NOTIFICATION_ID_KEY = "notificationIds";
    private static final String PACKAGE_KEY = "pkg";
    private static final String SERVICE_ID_KEY = "serviceId";
    private static final String SUPPORT_FLUID_REPLACE_NTF = "oplus.software.fluid_replace_ntf";
    private static final String TAG = "FluidReplaceNotificationManager";
    private static final String USER_ID_KEY = "userId";
    private final Handler backgroundHandler;
    private final GlobalSettings globalSettings;
    public boolean isSupportFluidReplaceNtf;
    private final SystemSettings systemSettings;
    public static final Companion Companion = new Companion();
    private static final String[] FLUID_REPLACE_PACKAGE_LIST = {"com.coloros.soundrecorder", "com.coloros.alarmclock", "com.oplus.screenrecorder", "com.android.incallui", "com.heytap.health", "com.oneplus.soundrecorder", "com.coloros.oshare", "com.oneplus.oshare", "com.oneplus.deskclock"};
    private final Map<String, FluidMapItem> defaultFluidMap = new LinkedHashMap();
    private final Map<String, FluidMapItem> fluidMap = new LinkedHashMap();
    private final Map<String, Boolean> fluidShowMap = new LinkedHashMap();
    private final Set<String> supportPackages = new LinkedHashSet();
    private final FluidReplaceNotificationManager$defaultFluidMapObserver$1 defaultFluidMapObserver = new ContentObserver() { // from class: com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager$defaultFluidMapObserver$1
        {
            super(null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "FluidReplaceNotificationManager-->default fluid map change");
            }
            this.this$0.updateDefaultMapFromSettings();
            this.this$0.updateSupportPackages();
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FluidMapParseUtils {
        private static final String DEFAULT_FLUID_MAP_END = "],";
        private static final String DEFAULT_FLUID_MAP_START = "\"fluidMap\":";
        public static final FluidMapParseUtils INSTANCE = new FluidMapParseUtils();

        public static final Map<String, FluidMapItem> getFluidMapFromJsonStr(String str) {
            FluidMapItem fluidMapItemJsonToFluidMapItem;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null && (fluidMapItemJsonToFluidMapItem = jsonToFluidMapItem(jSONObject)) != null) {
                            linkedHashMap.put(fluidMapItemJsonToFluidMapItem.getMapKey(), fluidMapItemJsonToFluidMapItem);
                        }
                    }
                } catch (JSONException unused) {
                    LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, FluidReplaceNotificationManager.TAG, "getFluidMapFromJsonStr, JSONException!");
                    return linkedHashMap;
                }
            }
            return linkedHashMap;
        }

        public static final String getFluidMapStr(String str) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default(str, DEFAULT_FLUID_MAP_START, 0, false, 6) + 11;
            int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default(str, DEFAULT_FLUID_MAP_END, 0, false, 6) + 1;
            return (1 > iIndexOf$default || iIndexOf$default >= iIndexOf$default2 || iIndexOf$default2 >= str.length()) ? "" : str.substring(iIndexOf$default, iIndexOf$default2);
        }

        public static final String getJsonStrFromFluidMap(Map<String, FluidMapItem> map) {
            JSONArray jSONArray = new JSONArray();
            Iterator<FluidMapItem> it = map.values().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJsonObject());
            }
            return jSONArray.toString();
        }

        public static final FluidMapItem jsonToFluidMapItem(JSONObject jSONObject) {
            String str = null;
            int i = 0;
            FluidMapItem fluidMapItem = new FluidMapItem(str, i, str, 15);
            fluidMapItem.setPackageName(jSONObject.optString("pkg"));
            fluidMapItem.setUserId(jSONObject.optInt(FluidReplaceNotificationManager.USER_ID_KEY));
            fluidMapItem.setServiceId(jSONObject.optString(FluidReplaceNotificationManager.SERVICE_ID_KEY));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(FluidReplaceNotificationManager.NOTIFICATION_ID_KEY);
            if (TextUtils.isEmpty(fluidMapItem.getPackageName()) || jSONArrayOptJSONArray == null) {
                return null;
            }
            int length = jSONArrayOptJSONArray.length();
            if (length > 10) {
                length = 10;
            }
            while (i < length) {
                fluidMapItem.getNotificationIdList().add(Integer.valueOf(jSONArrayOptJSONArray.optInt(i)));
                i++;
            }
            return fluidMapItem;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ FluidReplaceNotificationManager this$0;

        @Override // java.lang.Runnable
        public final void run() {
            FluidReplaceNotificationManager.access$updateMapFromSettingsData(this.this$0);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager$updateFluidMap$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Map $tempFluidMap;
        public /* synthetic */ FluidReplaceNotificationManager this$0;

        @Override // java.lang.Runnable
        public final void run() {
            FluidReplaceNotificationManager.access$updateFluidMapSettingsData(this.this$0, this.$tempFluidMap);
        }
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager$defaultFluidMapObserver$1] */
    public FluidReplaceNotificationManager(Handler handler, SystemSettings systemSettings, GlobalSettings globalSettings) {
        this.backgroundHandler = handler;
        this.systemSettings = systemSettings;
        this.globalSettings = globalSettings;
    }

    public static final void access$updateFluidMapSettingsData(FluidReplaceNotificationManager fluidReplaceNotificationManager, Map map) {
        String jsonStrFromFluidMap = FluidMapParseUtils.getJsonStrFromFluidMap(map);
        Map<String, FluidMapItem> fluidMapFromJsonStr = FluidMapParseUtils.getFluidMapFromJsonStr(fluidReplaceNotificationManager.systemSettings.getString(FLUID_MAP_KEY));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (!fluidMapFromJsonStr.keySet().contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, FluidMapItem> entry2 : fluidMapFromJsonStr.entrySet()) {
            if (!map.keySet().contains(entry2.getKey())) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Map.Entry entry3 : map.entrySet()) {
            if (fluidMapFromJsonStr.keySet().contains(entry3.getKey()) && !Intrinsics.areEqual(entry3.getValue(), fluidMapFromJsonStr.get(entry3.getKey()))) {
                linkedHashMap3.put(entry3.getKey(), entry3.getValue());
            }
        }
        boolean zIsEmpty = linkedHashMap.isEmpty();
        String str = LogUtil.TAG_NOTIFICATION;
        if (!zIsEmpty) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "FluidReplaceNotificationManager-->Added entries:");
            }
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry4 = (Map.Entry) it.next();
                Iterator it2 = it;
                String str2 = (String) entry4.getKey();
                FluidMapItem fluidMapItem = (FluidMapItem) entry4.getValue();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    String packageName = fluidMapItem.getPackageName();
                    int userId = fluidMapItem.getUserId();
                    String serviceId = fluidMapItem.getServiceId();
                    List<Integer> notificationIdList = fluidMapItem.getNotificationIdList();
                    StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("key=", str2, ", pkg=", packageName, ", uid=");
                    BluetoothUtils$.ExternalSyntheticOutline0.m(userId, ", serviceId=", serviceId, ", notifIds=", sbM);
                    sbM.append(notificationIdList);
                    Log.d("SystemUi--Notification", "FluidReplaceNotificationManager-->".concat(sbM.toString()));
                }
                it = it2;
                linkedHashMap2 = linkedHashMap2;
                linkedHashMap3 = linkedHashMap3;
                str = str;
            }
        }
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        LinkedHashMap linkedHashMap5 = linkedHashMap3;
        String str3 = str;
        if (!linkedHashMap4.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                Log.d("SystemUi--Notification", "FluidReplaceNotificationManager-->Removed entries:");
            }
            for (Iterator it3 = linkedHashMap4.entrySet().iterator(); it3.hasNext(); it3 = it3) {
                Map.Entry entry5 = (Map.Entry) it3.next();
                String str4 = (String) entry5.getKey();
                FluidMapItem fluidMapItem2 = (FluidMapItem) entry5.getValue();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                    String packageName2 = fluidMapItem2.getPackageName();
                    int userId2 = fluidMapItem2.getUserId();
                    String serviceId2 = fluidMapItem2.getServiceId();
                    List<Integer> notificationIdList2 = fluidMapItem2.getNotificationIdList();
                    StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("key=", str4, ", pkg=", packageName2, ", uid=");
                    BluetoothUtils$.ExternalSyntheticOutline0.m(userId2, ", serviceId=", serviceId2, ", notifIds=", sbM2);
                    sbM2.append(notificationIdList2);
                    Log.d("SystemUi--Notification", "FluidReplaceNotificationManager-->".concat(sbM2.toString()));
                }
            }
        }
        if (!linkedHashMap5.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                Log.d("SystemUi--Notification", "FluidReplaceNotificationManager-->Modified entries:");
            }
            for (Map.Entry entry6 : linkedHashMap5.entrySet()) {
                String str5 = (String) entry6.getKey();
                FluidMapItem fluidMapItem3 = (FluidMapItem) entry6.getValue();
                FluidMapItem fluidMapItem4 = fluidMapFromJsonStr.get(str5);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                    List<Integer> notificationIdList3 = fluidMapItem4 != null ? fluidMapItem4.getNotificationIdList() : null;
                    Log.d("SystemUi--Notification", "FluidReplaceNotificationManager-->".concat("key=" + str5 + ", oldNotifIds=" + notificationIdList3 + ", newNotifIds=" + fluidMapItem3.getNotificationIdList()));
                }
            }
        }
        fluidReplaceNotificationManager.systemSettings.putString(FLUID_MAP_KEY, jsonStrFromFluidMap);
    }

    public static final void access$updateMapFromSettingsData(FluidReplaceNotificationManager fluidReplaceNotificationManager) {
        String string = fluidReplaceNotificationManager.systemSettings.getString(FLUID_MAP_KEY);
        if (string != null) {
            fluidReplaceNotificationManager.fluidMap.clear();
            fluidReplaceNotificationManager.fluidMap.putAll(FluidMapParseUtils.getFluidMapFromJsonStr(string));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "FluidReplaceNotificationManager-->".concat("updateFluidMap, fluidMapStr: " + string + ", fluidMap: " + fluidReplaceNotificationManager.fluidMap));
            }
        }
        fluidReplaceNotificationManager.updateDefaultMapFromSettings();
        fluidReplaceNotificationManager.updateSupportPackages();
    }

    public final void init() {
        SeedlingPluginManager.Companion.getClass();
        boolean zIsFluidEntrySupported = SeedlingPluginManager.Companion.isFluidEntrySupported();
        this.isSupportFluidReplaceNtf = OplusFeatureConfigManager.getInstance().hasFeature(SUPPORT_FLUID_REPLACE_NTF);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("init, isSupportFluid: ", " ,isSupportFluidReplaceNtf: ", "FluidReplaceNotificationManager-->", zIsFluidEntrySupported, this.isSupportFluidReplaceNtf));
        }
        boolean z = this.isSupportFluidReplaceNtf && zIsFluidEntrySupported;
        this.isSupportFluidReplaceNtf = z;
        if (z) {
            Handler handler = this.backgroundHandler;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass2);
            GlobalSettings globalSettings = this.globalSettings;
            globalSettings.registerContentObserverSync(globalSettings.getUriFor(DEFAULT_FLUID_MAP_KEY), this.defaultFluidMapObserver);
        }
    }

    public final boolean isInFluidMap(FluidMapItem fluidMapItem, int i) {
        FluidMapItem fluidMapItem2;
        List<Integer> notificationIdList;
        List<Integer> notificationIdList2;
        String mapKey = fluidMapItem.getMapKey();
        FluidMapItem fluidMapItem3 = this.fluidMap.get(mapKey);
        return ((fluidMapItem3 == null || (notificationIdList2 = fluidMapItem3.getNotificationIdList()) == null || !notificationIdList2.contains(Integer.valueOf(i))) && ((fluidMapItem2 = this.defaultFluidMap.get(mapKey)) == null || (notificationIdList = fluidMapItem2.getNotificationIdList()) == null || !notificationIdList.contains(Integer.valueOf(i)))) ? false : true;
    }

    public final boolean isNotificationCardShow(int i, String str, String str2, int i2) {
        if (!supportFluidReplaceNtf(str)) {
            return false;
        }
        FluidMapItem fluidMapItem = new FluidMapItem(str, i2, str2, 8);
        return isInFluidMap(fluidMapItem, i) && Intrinsics.areEqual(this.fluidShowMap.get(fluidMapItem.getMapKey()), Boolean.TRUE);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0050 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x0052 A[ORIG_RETURN, RETURN] */
    public final boolean isNotificationInFluidMap(String str, int i, int i2, String str2) {
        if (!supportFluidReplaceNtf(str)) {
            return false;
        }
        if (str2 != null) {
            return isInFluidMap(new FluidMapItem(str, i2, str2, 8), i);
        }
        for (Object obj : this.fluidMap.values()) {
            FluidMapItem fluidMapItem = (FluidMapItem) obj;
            if (Intrinsics.areEqual(fluidMapItem.getPackageName(), str) && fluidMapItem.getUserId() == i2 && fluidMapItem.getNotificationIdList().contains(Integer.valueOf(i))) {
                if (obj != null) {
                    return true;
                }
                return false;
            }
        }
        obj = null;
        if (obj != null) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:53:0x00c2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00ac A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    public final boolean shouldSuppressNotificationPost(StatusBarNotification statusBarNotification) {
        Notification notification;
        boolean zIsNotificationCardShow;
        Collection<FluidMapItem> collectionValues;
        String packageName = statusBarNotification.getPackageName();
        packageName.getClass();
        boolean z = false;
        if (!supportFluidReplaceNtf(packageName) || (notification = statusBarNotification.getNotification()) == null) {
            return false;
        }
        Bundle bundle = notification.extras;
        String string = bundle != null ? bundle.getString(FLUID_SERVICE_ID_EXTRA) : null;
        if (string != null) {
            zIsNotificationCardShow = isNotificationCardShow(statusBarNotification.getId(), packageName, string, statusBarNotification.getUserId());
        } else {
            int id = statusBarNotification.getId();
            int userId = statusBarNotification.getUserId();
            Collection<FluidMapItem> collectionValues2 = this.fluidMap.values();
            if (!(collectionValues2 instanceof Collection) || !collectionValues2.isEmpty()) {
                Iterator<T> it = collectionValues2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        FluidMapItem fluidMapItem = (FluidMapItem) it.next();
                        if (!Intrinsics.areEqual(fluidMapItem.getPackageName(), packageName) || fluidMapItem.getUserId() != userId || !fluidMapItem.getNotificationIdList().contains(Integer.valueOf(id)) || !Intrinsics.areEqual(this.fluidShowMap.get(fluidMapItem.getMapKey()), Boolean.TRUE)) {
                        }
                    } else {
                        collectionValues = this.defaultFluidMap.values();
                        if ((collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                            for (FluidMapItem fluidMapItem2 : collectionValues) {
                                if (Intrinsics.areEqual(fluidMapItem2.getPackageName(), packageName) || fluidMapItem2.getUserId() != userId || !fluidMapItem2.getNotificationIdList().contains(Integer.valueOf(id)) || !Intrinsics.areEqual(this.fluidShowMap.get(fluidMapItem2.getMapKey()), Boolean.TRUE)) {
                                }
                            }
                        }
                    }
                    z = true;
                    break;
                }
            }
            collectionValues = this.defaultFluidMap.values();
            if (collectionValues instanceof Collection) {
                while (r6.hasNext()) {
                    if (Intrinsics.areEqual(fluidMapItem2.getPackageName(), packageName)) {
                    }
                }
            } else {
                while (r6.hasNext()) {
                    if (Intrinsics.areEqual(fluidMapItem2.getPackageName(), packageName)) {
                    }
                }
            }
            zIsNotificationCardShow = z;
        }
        if (zIsNotificationCardShow) {
            String key = statusBarNotification.getKey();
            int id2 = statusBarNotification.getId();
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("shouldSuppressNotificationPost, key=", key, ", pkg=", packageName, ", id=");
            sbM.append(id2);
            sbM.append(", serviceId=");
            sbM.append(string);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        }
        return zIsNotificationCardShow;
    }

    public final boolean supportFluidReplaceNtf(String str) {
        return this.isSupportFluidReplaceNtf && this.supportPackages.contains(str);
    }

    public final void updateDefaultMapFromSettings() {
        String string = this.globalSettings.getString(DEFAULT_FLUID_MAP_KEY);
        if (string == null) {
            return;
        }
        String fluidMapStr = FluidMapParseUtils.getFluidMapStr(string);
        this.defaultFluidMap.clear();
        this.defaultFluidMap.putAll(FluidMapParseUtils.getFluidMapFromJsonStr(fluidMapStr));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "FluidReplaceNotificationManager-->".concat("updateDefaultFluidMap, defaultMapStr: " + fluidMapStr + ", defaultFluidMap: " + this.defaultFluidMap));
        }
    }

    public final void updateFluidMap(String str, int i, String str2, int i2) {
        if (supportFluidReplaceNtf(str)) {
            FluidMapItem fluidMapItem = new FluidMapItem(str, i2, str2, 8);
            if (isInFluidMap(fluidMapItem, i)) {
                return;
            }
            String mapKey = fluidMapItem.getMapKey();
            if (!this.fluidMap.containsKey(mapKey)) {
                this.fluidMap.put(mapKey, fluidMapItem);
            }
            FluidMapItem fluidMapItem2 = this.fluidMap.get(mapKey);
            if (fluidMapItem2 != null) {
                fluidMapItem = fluidMapItem2;
            }
            if (fluidMapItem.getNotificationIdList().size() >= 10) {
                CollectionsKt__MutableCollectionsKt.removeFirst(fluidMapItem.getNotificationIdList());
            }
            fluidMapItem.getNotificationIdList().add(Integer.valueOf(i));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(this.fluidMap);
            Handler handler = this.backgroundHandler;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$tempFluidMap = linkedHashMap;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }
    }

    public final void updateFluidShowMap(String str, String str2, boolean z, int i) {
        if (supportFluidReplaceNtf(str2)) {
            this.fluidShowMap.put(new FluidMapItem(str2, i, str, 8).getMapKey(), Boolean.valueOf(z));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "FluidReplaceNotificationManager-->".concat("updateFluidShowMap, fluidShowMap: " + this.fluidShowMap));
            }
        }
    }

    public final void updateSupportPackages() {
        this.supportPackages.clear();
        CollectionsKt__MutableCollectionsKt.addAll(this.supportPackages, FLUID_REPLACE_PACKAGE_LIST);
        Iterator<T> it = this.defaultFluidMap.values().iterator();
        while (it.hasNext()) {
            this.supportPackages.add(((FluidMapItem) it.next()).getPackageName());
        }
        Iterator<T> it2 = this.fluidMap.values().iterator();
        while (it2.hasNext()) {
            this.supportPackages.add(((FluidMapItem) it2.next()).getPackageName());
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "updateSupportPackages: " + this.supportPackages, null, 8);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FluidMapItem {
        private List<Integer> notificationIdList;
        private String packageName;
        private String serviceId;
        public int userId;

        public /* synthetic */ FluidMapItem(String str, int i, String str2, int i2) {
            this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? "" : str2, new ArrayList());
        }

        public final String component1() {
            return this.packageName;
        }

        public final String component3() {
            return this.serviceId;
        }

        public final List<Integer> component4() {
            return this.notificationIdList;
        }

        public final FluidMapItem copy(String str, int i, String str2, List<Integer> list) {
            return new FluidMapItem(str, i, str2, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FluidMapItem)) {
                return false;
            }
            FluidMapItem fluidMapItem = (FluidMapItem) obj;
            return Intrinsics.areEqual(this.packageName, fluidMapItem.packageName) && this.userId == fluidMapItem.userId && Intrinsics.areEqual(this.serviceId, fluidMapItem.serviceId) && Intrinsics.areEqual(this.notificationIdList, fluidMapItem.notificationIdList);
        }

        public final String getMapKey() {
            return this.packageName + "|" + this.userId + "|" + this.serviceId;
        }

        public final List<Integer> getNotificationIdList() {
            return this.notificationIdList;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getServiceId() {
            return this.serviceId;
        }

        public final int getUserId() {
            return this.userId;
        }

        public final int hashCode() {
            return this.notificationIdList.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.userId, this.packageName.hashCode() * 31, 31), 31, this.serviceId);
        }

        public final void setNotificationIdList(List<Integer> list) {
            this.notificationIdList = list;
        }

        public final void setPackageName(String str) {
            this.packageName = str;
        }

        public final void setServiceId(String str) {
            this.serviceId = str;
        }

        public final void setUserId(int i) {
            this.userId = i;
        }

        public final JSONObject toJsonObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pkg", this.packageName);
                jSONObject.put(FluidReplaceNotificationManager.USER_ID_KEY, this.userId);
                jSONObject.put(FluidReplaceNotificationManager.SERVICE_ID_KEY, this.serviceId);
                JSONArray jSONArray = new JSONArray();
                Iterator<Integer> it = this.notificationIdList.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().intValue());
                }
                jSONObject.put(FluidReplaceNotificationManager.NOTIFICATION_ID_KEY, jSONArray);
                return jSONObject;
            } catch (JSONException unused) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, FluidReplaceNotificationManager.TAG, "toJsonObject, JSONException!");
                return jSONObject;
            }
        }

        public String toString() {
            String str = this.packageName;
            int i = this.userId;
            String str2 = this.serviceId;
            List<Integer> list = this.notificationIdList;
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "FluidMapItem(packageName=", str, ", userId=", ", serviceId=");
            sbM.append(str2);
            sbM.append(", notificationIdList=");
            sbM.append(list);
            sbM.append(")");
            return sbM.toString();
        }

        public FluidMapItem(String str, int i, String str2, List<Integer> list) {
            this.packageName = str;
            this.userId = i;
            this.serviceId = str2;
            this.notificationIdList = list;
        }
    }
}
