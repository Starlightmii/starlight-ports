package com.oplus.systemui.qs.utils;

import android.content.Context;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplus.systemui.qs.tiles.FlavorOneOtgTile;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AppQsTileBlackList {
    private static final String TAG = "AppQsTileBlackList";
    public static final AppQsTileBlackList INSTANCE = new AppQsTileBlackList();
    private static final List<String> sAppQuickSettingsBlackList = new ArrayList();
    private static final List<String> sDefaultQuickSettingsBlackList = new ArrayList();
    private static final List<String> sTabletQuickSettingsBlackList = new ArrayList();

    public static final List<String> getAppQuickSettingsBlackList() {
        List<String> list = sAppQuickSettingsBlackList;
        return list.isEmpty() ? sDefaultQuickSettingsBlackList : list;
    }

    public static /* synthetic */ void getAppQuickSettingsBlackList$annotations() {
    }

    public static final List<String> getDefaultQuickSettingsBlackList() {
        return sDefaultQuickSettingsBlackList;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0028  */
    /* JADX WARN: Code duplicated, block: B:9:0x0030  */
    public static final void init(Context context) {
        List<String> list;
        INSTANCE.getClass();
        List<String> list2 = sDefaultQuickSettingsBlackList;
        list2.add("custom(com.bluewhale.vpn.shadowsocks/.bg.TileService)");
        List<String> listFromLocalRomUpdateFile = ProviderUtil.getListFromLocalRomUpdateFile(context, ProviderUtil.QUICK_SETTINGS_TILE_KEY);
        if (listFromLocalRomUpdateFile != null) {
            List<String> list3 = listFromLocalRomUpdateFile;
            if (!list3.isEmpty()) {
                List<String> list4 = sAppQuickSettingsBlackList;
                list4.clear();
                list4.addAll(list3);
            } else {
                list = sAppQuickSettingsBlackList;
                if (list.isEmpty()) {
                    list.addAll(list2);
                }
            }
        } else {
            list = sAppQuickSettingsBlackList;
            if (list.isEmpty()) {
                list.addAll(list2);
            }
        }
        String[] stringArray = context.getResources().getStringArray(2130903172);
        if (stringArray.length != 0) {
            for (String str : stringArray) {
                sTabletQuickSettingsBlackList.add(str);
            }
        }
        List<String> list5 = sAppQuickSettingsBlackList;
        List<String> list6 = sTabletQuickSettingsBlackList;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initQuickSettingsBlackList sAppQuickSettingsBlackList: " + list5 + " tabletBlackList: " + list6, null, 8);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.sOtgAutoCloseAlarmDisable) {
            list6.add(FlavorOneOtgTile.TILE_SPEC);
        }
    }

    public static final boolean isTabletBlackListTile(String str) {
        return CollectionsKt___CollectionsKt.contains(sTabletQuickSettingsBlackList, str);
    }

    public static final void setAppQuickSettingsBlackList(List<String> list) {
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List<String> list2 = sAppQuickSettingsBlackList;
        list2.clear();
        list2.addAll(list);
    }
}
