package com.oplus.systemui.qs.customize;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.widget.Button;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.SepQSTileQueryHelper;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.helper.QSTileHostHelper;
import com.oplus.systemui.qs.utils.AppQsTileBlackList;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.TileHandlerThread;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSepQueryTileHelper extends SepQSTileQueryHelper {
    public static final Companion Companion = new Companion();
    private static final String SETTING_SEPARATOR = ":";
    private static final String TAG = "OplusSepQueryTileHelper";
    private final Looper backgroundLooper;
    private final Handler bgHandler;
    private final Context context;
    public List currentSettingSpecs;
    private final CopyOnWriteArraySet<String> currentSpecs;
    private final ArrayList<SepQSTileQueryHelper.TileInfo> currentTiles;
    private final ArrayList<String> extraDefaultTiles;
    private final ArraySet<String> extraSpecs;
    private final LinkedHashMap<String, SepQSTileQueryHelper.TileInfo> extraTilesInfo;
    private final ArrayList<String> filterCustomTiles;
    private final Handler mainHandler;
    private Function1 onStockTilesChanged;
    private final QSHost qsHost;
    private final CopyOnWriteArraySet<String> stockSpecs;
    public List stockSplit;
    private final CopyOnWriteArrayList<SepQSTileQueryHelper.TileInfo> stockTiles;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Factory {
        private final Context context;
        private final Looper mainLooper;
        private final QSHost qsHost;
        private final UserTracker userTracker;

        public Factory(Context context, UserTracker userTracker, QSHost qSHost, Looper looper) {
            this.context = context;
            this.userTracker = userTracker;
            this.qsHost = qSHost;
            this.mainLooper = looper;
        }

        public final OplusSepQueryTileHelper create() {
            return new OplusSepQueryTileHelper(this.context, this.userTracker, this.qsHost, this.mainLooper);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusSepQueryTileHelper$addTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Predicate {
        public /* synthetic */ String $spec;

        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return Intrinsics.areEqual(((SepQSTileQueryHelper.TileInfo) obj).getSpec(), this.$spec);
        }
    }

    public OplusSepQueryTileHelper(Context context, UserTracker userTracker, QSHost qSHost, Looper looper) {
        this.context = context;
        this.userTracker = userTracker;
        this.qsHost = qSHost;
        Looper looper2 = TileHandlerThread.Companion.getInstance().getLooper();
        this.backgroundLooper = looper2;
        this.mainHandler = new Handler(looper);
        this.bgHandler = new Handler(looper2);
        this.currentTiles = new ArrayList<>();
        this.stockTiles = new CopyOnWriteArrayList<>();
        this.extraTilesInfo = new LinkedHashMap<>();
        this.currentSpecs = new CopyOnWriteArraySet<>();
        this.stockSpecs = new CopyOnWriteArraySet<>();
        this.extraSpecs = new ArraySet<>();
        ArrayList<String> arrayList = new ArrayList<>();
        this.extraDefaultTiles = arrayList;
        this.filterCustomTiles = new ArrayList<>();
        arrayList.addAll(((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsTileHostHelperEx().getExtraTileSpecs(context));
    }

    public static final void access$createStateAndAddTile(OplusSepQueryTileHelper oplusSepQueryTileHelper, String str, Drawable drawable, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        QSTile.State state = new QSTile.State();
        state.state = 1;
        state.label = charSequence;
        state.contentDescription = charSequence;
        state.icon = new QSTileImpl.DrawableIcon(drawable);
        state.toggleable = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).isCustomTileCnTips(str);
        if (oplusSepQueryTileHelper.filterCustomTiles.contains(str)) {
            for (SepQSTileQueryHelper.TileInfo tileInfo : oplusSepQueryTileHelper.stockTiles) {
                if (Intrinsics.areEqual(tileInfo.getSpec(), str)) {
                    tileInfo.getState().label = charSequence;
                }
            }
        }
        oplusSepQueryTileHelper.addTile(str, charSequence2, state, z, false);
    }

    public static final void access$notifyTilesChanged(OplusSepQueryTileHelper oplusSepQueryTileHelper) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<String> arrayList3 = oplusSepQueryTileHelper.extraDefaultTiles;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            String str = arrayList3.get(i);
            i++;
            SepQSTileQueryHelper.TileInfo tileInfo = oplusSepQueryTileHelper.extraTilesInfo.get(str);
            if (tileInfo != null) {
                arrayList2.add(tileInfo);
            }
        }
        arrayList.addAll(oplusSepQueryTileHelper.currentTiles);
        arrayList.addAll(arrayList2);
        arrayList.addAll(oplusSepQueryTileHelper.stockTiles);
        Function1 function1 = oplusSepQueryTileHelper.onStockTilesChanged;
        if (function1 != null) {
            function1.invoke(arrayList);
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00a5  */
    public static final boolean access$shouldSkipTile(OplusSepQueryTileHelper oplusSepQueryTileHelper, ServiceInfo serviceInfo, ComponentName componentName, String str) {
        List<String> appQuickSettingsBlackList;
        if (QSTileHostHelper.isComponentAvailable(oplusSepQueryTileHelper.context, componentName)) {
            List list = oplusSepQueryTileHelper.stockSplit;
            if (list == null) {
                list = null;
            }
            if (!list.contains(componentName.flattenToString()) || oplusSepQueryTileHelper.filterCustomTiles.contains(componentName.flattenToString())) {
                String strFlattenToShortString = componentName.flattenToShortString();
                String str2 = "";
                for (String str3 : (String[]) ArraysKt___ArraysJvmKt.plus(oplusSepQueryTileHelper.context.getResources().getStringArray(2130903277), oplusSepQueryTileHelper.context.getResources().getStringArray(2130903278))) {
                    str3.getClass();
                    if (StringsKt__StringsKt.contains(str3, strFlattenToShortString, false)) {
                        List listSplit = new Regex(SETTING_SEPARATOR).split(str3, 0);
                        if (listSplit.size() == 2) {
                            str2 = (String) listSplit.get(1);
                        } else {
                            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "customTile in stock array: ".concat(str3));
                        }
                    }
                }
                if (str2.length() <= 0) {
                    appQuickSettingsBlackList = AppQsTileBlackList.getAppQuickSettingsBlackList();
                    if ((appQuickSettingsBlackList != null || !appQuickSettingsBlackList.contains(str)) && !QSTileFilterHelper.INSTANCE.filterRemoveSpecs(oplusSepQueryTileHelper.context, str) && !AppQsTileBlackList.isTabletBlackListTile(str) && ((serviceInfo.icon != 0 || serviceInfo.applicationInfo.icon != 0) && "android.permission.BIND_QUICK_SETTINGS_TILE".equals(serviceInfo.permission) && !"custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileService)".equals(str) && !"custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotOne)".equals(str) && !"custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotTwo)".equals(str))) {
                    }
                } else {
                    List list2 = oplusSepQueryTileHelper.stockSplit;
                    if (!(list2 != null ? list2 : null).contains(str2) || oplusSepQueryTileHelper.filterCustomTiles.contains(str2)) {
                        appQuickSettingsBlackList = AppQsTileBlackList.getAppQuickSettingsBlackList();
                        return appQuickSettingsBlackList != null ? false : false;
                    }
                }
            }
        }
        return true;
    }

    public final void addTile(String str, CharSequence charSequence, QSTile.State state, boolean z, boolean z2) {
        if (z2 && this.currentSpecs.contains(str)) {
            return;
        }
        if (z2 || !(this.stockSpecs.contains(str) || this.extraSpecs.contains(str))) {
            if (QSTileFilterHelper.SPACE_GESTRUE_TILE.equals(str)) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (!FlavorOneFeatureOption.isSupportActiveSpaceGesture()) {
                    return;
                }
            }
            SepQSTileQueryHelper.TileInfo tileInfo = new SepQSTileQueryHelper.TileInfo(str, state, z);
            tileInfo.getState().expandedAccessibilityClassName = Button.class.getName();
            QSTile.State state2 = tileInfo.getState();
            if (z || TextUtils.equals(state.label, charSequence)) {
                charSequence = null;
            }
            state2.secondaryLabel = charSequence;
            if (z2) {
                this.currentTiles.add(tileInfo);
                this.currentSpecs.add(str);
                return;
            }
            if (this.currentSpecs.contains(str)) {
                ArrayList<SepQSTileQueryHelper.TileInfo> arrayList = this.currentTiles;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.$spec = str;
                VarHandle.storeStoreFence();
                arrayList.removeIf(anonymousClass1);
                this.currentSpecs.remove(str);
            }
            if (this.extraDefaultTiles.contains(str)) {
                this.extraTilesInfo.put(str, tileInfo);
                this.extraSpecs.add(str);
            } else {
                this.stockTiles.add(tileInfo);
                this.stockSpecs.add(str);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0116  */
    /* JADX WARN: Code duplicated, block: B:43:0x0118  */
    /* JADX WARN: Code duplicated, block: B:46:0x011f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0125  */
    /* JADX WARN: Code duplicated, block: B:49:0x012c  */
    public void querySepTile(List<String> list, List<String> list2, Function1 function1) {
        QSTile qSTileCreateTile;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "QueryTiles start", null, 8);
        this.stockSplit = list;
        this.currentSettingSpecs = list2;
        this.onStockTilesChanged = function1;
        this.currentTiles.clear();
        this.stockTiles.clear();
        this.extraTilesInfo.clear();
        this.stockSpecs.clear();
        this.currentSpecs.clear();
        this.extraSpecs.clear();
        QSHost qSHost = this.qsHost;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusSepQueryTileHelper-->addCurrentTiles:");
        }
        ArrayList arrayList = new ArrayList(qSHost.getTiles());
        Handler handler = this.bgHandler;
        OplusSepQueryTileHelper$addTileList$1 oplusSepQueryTileHelper$addTileList$1 = new OplusSepQueryTileHelper$addTileList$1(1);
        oplusSepQueryTileHelper$addTileList$1.$tilesToAdd = arrayList;
        oplusSepQueryTileHelper$addTileList$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusSepQueryTileHelper$addTileList$1);
        QSHost qSHost2 = this.qsHost;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "addStockAndPackageTiles start ", null, 8);
        List list3 = this.currentSettingSpecs;
        if (list3 == null) {
            list3 = null;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list3) {
            if (!StringsKt__StringsJVMKt.startsWith((String) obj, "custom(", false)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        this.filterCustomTiles.clear();
        List<String> list4 = this.stockSplit;
        if (list4 == null) {
            list4 = null;
        }
        for (String str : list4) {
            if (!arrayList2.contains(str)) {
                arrayList3.add(str);
            }
            if (StringsKt__StringsKt.contains(TextUtils.emptyIfNull(str), "custom(", false)) {
                this.filterCustomTiles.add(str);
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "possibleTiles is " + arrayList3, null, 8);
        ArrayList arrayList4 = new ArrayList();
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList3.get(i);
            i++;
            String str2 = (String) obj2;
            List<String> appQuickSettingsBlackList = AppQsTileBlackList.getAppQuickSettingsBlackList();
            if ((appQuickSettingsBlackList != null && appQuickSettingsBlackList.contains(str2)) || AppQsTileBlackList.isTabletBlackListTile(str2)) {
                qSTileCreateTile = null;
            } else if (QSTileFilterHelper.SPACE_GESTRUE_TILE.equals(str2)) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (FlavorOneFeatureOption.isSupportActiveSpaceGesture()) {
                    qSTileCreateTile = qSHost2.createTile(str2);
                    if (qSTileCreateTile != null) {
                        qSTileCreateTile = null;
                    } else if (qSTileCreateTile.isAvailable()) {
                        qSTileCreateTile.setTileSpec(str2);
                    } else {
                        qSTileCreateTile.setTileSpec(str2);
                        qSTileCreateTile.destroy();
                        qSTileCreateTile = null;
                    }
                } else {
                    qSTileCreateTile = null;
                }
            } else {
                qSTileCreateTile = qSHost2.createTile(str2);
                if (qSTileCreateTile != null) {
                    qSTileCreateTile = null;
                } else if (qSTileCreateTile.isAvailable()) {
                    qSTileCreateTile.setTileSpec(str2);
                    qSTileCreateTile.destroy();
                    qSTileCreateTile = null;
                } else {
                    qSTileCreateTile.setTileSpec(str2);
                }
            }
            if (qSTileCreateTile != null) {
                arrayList4.add(qSTileCreateTile);
            }
        }
        if (!arrayList4.isEmpty()) {
            Handler handler2 = this.bgHandler;
            OplusSepQueryTileHelper$addTileList$1 oplusSepQueryTileHelper$addTileList$2 = new OplusSepQueryTileHelper$addTileList$1(0);
            oplusSepQueryTileHelper$addTileList$2.$tilesToAdd = arrayList4;
            oplusSepQueryTileHelper$addTileList$2.this$0 = this;
            VarHandle.storeStoreFence();
            handler2.post(oplusSepQueryTileHelper$addTileList$2);
        }
        Handler handler3 = this.bgHandler;
        OplusSepQueryTileHelper$addStockAndPackageTiles$1 oplusSepQueryTileHelper$addStockAndPackageTiles$1 = new OplusSepQueryTileHelper$addStockAndPackageTiles$1();
        oplusSepQueryTileHelper$addStockAndPackageTiles$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler3.post(oplusSepQueryTileHelper$addStockAndPackageTiles$1);
    }
}
