package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplus.app.OPlusAccessControlManager;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmartAntiVoyeurAppListManagerImpl implements SmartAntiVoyeurAppListManager {
    public static final Companion Companion = new Companion();
    private static final String SMART_ANTI_VOYEUR_APP_LIST = "smart_anti_voyeur_app_list";
    private static final String TAG = "SmartAntiVoyeurAppListManager";
    private final AntiVoyeurConfig config;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SmartAntiVoyeurAppListManagerImpl(Context context, AntiVoyeurConfig antiVoyeurConfig) {
        this.context = context;
        this.config = antiVoyeurConfig;
    }

    private final String getAppListStr() {
        return Settings.Secure.getStringForUser(this.context.getContentResolver(), SMART_ANTI_VOYEUR_APP_LIST, -2);
    }

    private final void setAppListStr(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(this.context.getUserId(), "appListStr(", "):changed", "SmartAntiVoyeurAppListManager-->", "SystemUi--Notification");
        }
        Settings.Secure.putStringForUser(this.context.getContentResolver(), SMART_ANTI_VOYEUR_APP_LIST, str, -2);
    }

    public final Map<String, Boolean> getAllAntiVoyeurAppsMap() {
        String appListStr = getAppListStr();
        if (appListStr == null) {
            appListStr = "{}";
        }
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(appListStr);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, Boolean.valueOf(jSONObject.getBoolean(next)));
            }
            return map;
        } catch (JSONException unused) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "loadString is illegal!");
            return map;
        }
    }

    public final boolean getAppEncryptSwitchState() {
        OPlusAccessControlManager oPlusAccessControlManager = OPlusAccessControlManager.getInstance();
        int i = OPlusAccessControlManager.USER_CURRENT;
        return oPlusAccessControlManager.getAccessControlEnabled("type_encrypt", i) && !OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_encrypt", i).isEmpty();
    }

    public final void notifyEncryptAppsChange() {
        JSONObject jSONObject;
        List listEmptyList;
        List listEmptyList2;
        if (!getAppEncryptSwitchState()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "SmartAntiVoyeurAppListManager-->notifyEncryptAppsChange: not AppEncryptSwitch");
            }
            removeAllEncryptedAppFromSettings();
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "SmartAntiVoyeurAppListManager-->notifyEncryptAppsChange:");
        }
        Map accessControlAppsInfo = OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_encrypt", OPlusAccessControlManager.USER_CURRENT);
        Set setKeySet = accessControlAppsInfo.keySet();
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.context.getPackageManager();
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo((String) it.next(), 0);
                AppInfo appInfo = new AppInfo();
                appInfo.setPkg(applicationInfo.packageName);
                appInfo.setUid(applicationInfo.uid);
                appInfo.setEncrypted(true);
                arrayList.add(appInfo);
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "app is not exist in this device!");
            }
        }
        Map accessControlAppsInfo2 = OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_encrypt", 999);
        Iterator it2 = accessControlAppsInfo2.keySet().iterator();
        while (it2.hasNext()) {
            try {
                ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo((String) it2.next(), 0);
                AppInfo appInfo2 = new AppInfo();
                appInfo2.setPkg(applicationInfo2.packageName);
                appInfo2.setUid(applicationInfo2.uid + 99900000);
                appInfo2.setEncrypted(true);
                arrayList.add(appInfo2);
            } catch (PackageManager.NameNotFoundException unused2) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "loadString is illegal!");
            }
        }
        setAllAntiVoyeurApps(arrayList, this.config.getAllowAntiVoyeurEncryptedAppState());
        Map<String, Boolean> allAntiVoyeurAppsMap = getAllAntiVoyeurAppsMap();
        Set<String> setKeySet2 = allAntiVoyeurAppsMap.keySet();
        String appListStr = getAppListStr();
        try {
            jSONObject = appListStr != null ? new JSONObject(appListStr) : new JSONObject();
        } catch (JSONException unused3) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "loadString is illegal!");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return;
        }
        for (String str : setKeySet2) {
            if (Intrinsics.areEqual(allAntiVoyeurAppsMap.get(str), Boolean.TRUE)) {
                List listSplit = new Regex(" ").split(str, 0);
                if (listSplit.isEmpty()) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    break;
                }
                ListIterator listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (((String) listIterator.previous()).length() != 0) {
                            listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                            break;
                        }
                    } else {
                        listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                }
                String str2 = ((String[]) listEmptyList.toArray(new String[0]))[0];
                List listSplit2 = new Regex(" ").split(str, 0);
                if (listSplit2.isEmpty()) {
                    listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                    break;
                }
                ListIterator listIterator2 = listSplit2.listIterator(listSplit2.size());
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        if (((String) listIterator2.previous()).length() != 0) {
                            listEmptyList2 = CollectionsKt___CollectionsKt.take(listSplit2, listIterator2.nextIndex() + 1);
                            break;
                        }
                    } else {
                        listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                }
                int i = Integer.parseInt(((String[]) listEmptyList2.toArray(new String[0]))[1]);
                if (accessControlAppsInfo.get(str2) == null && i != 999) {
                    jSONObject.remove(str);
                }
                if (accessControlAppsInfo2.get(str2) == null && i == 999) {
                    jSONObject.remove(str);
                }
            }
        }
        setAppListStr(jSONObject.toString());
    }

    @Override // com.oplus.systemui.notification.notificationcenter.antivoyeur.SmartAntiVoyeurAppListManager
    public void notifyPkgRemoved(String str, boolean z) {
        JSONObject jSONObject;
        List listEmptyList;
        List listEmptyList2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("notifyPkgRemoved:: pkg = ", str, ", isMultiApps = ", z, "SmartAntiVoyeurAppListManager-->"));
        }
        String appListStr = getAppListStr();
        try {
            jSONObject = appListStr == null ? new JSONObject() : new JSONObject(appListStr);
        } catch (JSONException unused) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "loadString is illegal!");
            jSONObject = null;
        }
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                next.getClass();
                List listSplit = new Regex(" ").split(next, 0);
                if (!listSplit.isEmpty()) {
                    ListIterator listIterator = listSplit.listIterator(listSplit.size());
                    while (true) {
                        if (!listIterator.hasPrevious()) {
                            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                            break;
                        } else if (((String) listIterator.previous()).length() != 0) {
                            listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                } else {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    break;
                }
                String str2 = ((String[]) listEmptyList.toArray(new String[0]))[0];
                List listSplit2 = new Regex(" ").split(next, 0);
                if (!listSplit2.isEmpty()) {
                    ListIterator listIterator2 = listSplit2.listIterator(listSplit2.size());
                    while (true) {
                        if (!listIterator2.hasPrevious()) {
                            listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                            break;
                        } else if (((String) listIterator2.previous()).length() != 0) {
                            listEmptyList2 = CollectionsKt___CollectionsKt.take(listSplit2, listIterator2.nextIndex() + 1);
                            break;
                        }
                    }
                } else {
                    listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                    break;
                }
                int i = Integer.parseInt(((String[]) listEmptyList2.toArray(new String[0]))[1]) / 100000;
                boolean zStartsWith = str != null ? StringsKt__StringsJVMKt.startsWith(str2, str, false) : false;
                if (zStartsWith && !z) {
                    itKeys.remove();
                } else if (zStartsWith && i == 999) {
                    itKeys.remove();
                }
            }
            setAppListStr(jSONObject.toString());
        }
    }

    public final void removeAllEncryptedAppFromSettings() {
        String appListStr = getAppListStr();
        try {
            JSONObject jSONObject = appListStr != null ? new JSONObject(appListStr) : new JSONObject();
            Iterator it = jSONObject.keySet().iterator();
            while (it.hasNext()) {
                if (jSONObject.getBoolean((String) it.next())) {
                    it.remove();
                }
            }
            setAppListStr(jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "loadString is illegal!");
        }
    }

    public final void setAllAntiVoyeurApps(List<AppInfo> list, boolean z) {
        String appListStr = getAppListStr();
        try {
            JSONObject jSONObject = appListStr != null ? new JSONObject(appListStr) : new JSONObject();
            for (AppInfo appInfo : list) {
                if (z) {
                    jSONObject.put(appInfo.getPackageAndUid(), appInfo.isEncrypted);
                } else {
                    jSONObject.remove(appInfo.getPackageAndUid());
                }
            }
            setAppListStr(jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "loadString is illegal!");
        }
    }
}
