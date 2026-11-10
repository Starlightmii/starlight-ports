package com.oplus.systemui.qs.customize;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.widget.Button;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.external.OplusCustomTileStub;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.utils.AppQsTileBlackList;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.TileHandlerThread;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileQueryHelper extends QSTileQueryHelper {
    public static final Companion Companion = new Companion();
    private static final String SETTING_SEPARATOR = ":";
    private static final String TAG = "TileQueryHelper";
    private final Looper backgroundLooper;
    public boolean isFinished;
    private final ArrayList<QSTileQueryHelper.OplusTileInfo> mAllTiles;
    private final Handler mBgHandler;
    private final LinkedHashMap<String, QSTile> mCacheTiles;
    private final Context mContext;
    private String mCurrentSettingSpecs;
    private final CopyOnWriteArraySet<String> mCurrentSpecs;
    private final ArrayList<QSTileQueryHelper.OplusTileInfo> mCurrentTiles;
    private final String mDefaultTiles;
    private final ArrayList<String> mExtraDefaultTiles;
    private final ArraySet<String> mExtraSpecs;
    private final LinkedHashMap<String, QSTileQueryHelper.OplusTileInfo> mExtraTilesInfo;
    private final ArrayList<String> mFilterCustomTiles;
    private QSTileQueryHelper.ColorTileStateListener mListener;
    private final Handler mMainHandler;
    private final QSHost mQSHost;
    private final CopyOnWriteArraySet<String> mStockSpecs;
    private final CopyOnWriteArrayList<QSTileQueryHelper.OplusTileInfo> mStockTiles;
    private final UserTracker mUserTracker;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Factory {
        private final Context mContext;
        private final QSHost mQSHost;
        private final UserTracker mUserTracker;
        private final Looper mainLooper;
        private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;

        public Factory(Context context, UserTracker userTracker, Looper looper, QSHost qSHost, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
            this.mContext = context;
            this.mUserTracker = userTracker;
            this.mainLooper = looper;
            this.mQSHost = qSHost;
            this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        }

        public final OplusTileQueryHelper create() {
            return new OplusTileQueryHelper(this.mContext, this.mUserTracker, this.mainLooper, this.mQSHost, this.separateNotificationAndQSState);
        }
    }

    public OplusTileQueryHelper(Context context, UserTracker userTracker, Looper looper, QSHost qSHost, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        this.mContext = context;
        this.mUserTracker = userTracker;
        this.mQSHost = qSHost;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        Looper looper2 = TileHandlerThread.Companion.getInstance().getLooper();
        this.backgroundLooper = looper2;
        this.mMainHandler = new Handler(looper);
        this.mBgHandler = new Handler(looper2);
        this.mAllTiles = new ArrayList<>();
        this.mCurrentTiles = new ArrayList<>();
        this.mStockTiles = new CopyOnWriteArrayList<>();
        this.mExtraTilesInfo = new LinkedHashMap<>();
        this.mCurrentSpecs = new CopyOnWriteArraySet<>();
        this.mStockSpecs = new CopyOnWriteArraySet<>();
        this.mCacheTiles = new LinkedHashMap<>();
        this.mExtraSpecs = new ArraySet<>();
        ArrayList<String> arrayList = new ArrayList<>();
        this.mExtraDefaultTiles = arrayList;
        this.mFilterCustomTiles = new ArrayList<>();
        OplusQuickSettingsEx oplusQuickSettingsEx = (OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class);
        arrayList.addAll(oplusQuickSettingsEx.getQsTileHostHelperEx().getExtraTileSpecs(context));
        this.mDefaultTiles = oplusQuickSettingsEx.getQsTileHostHelperEx().getDefaultTileString(context);
    }

    public static final void access$createStateAndAddTile(OplusTileQueryHelper oplusTileQueryHelper, String str, Drawable drawable, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        QSTile.State state = new QSTile.State();
        state.state = 1;
        state.label = charSequence;
        state.contentDescription = charSequence;
        state.icon = new QSTileImpl.DrawableIcon(drawable);
        state.toggleable = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).isCustomTileCnTips(str);
        if (oplusTileQueryHelper.mFilterCustomTiles.contains(str)) {
            for (QSTileQueryHelper.OplusTileInfo oplusTileInfo : oplusTileQueryHelper.mStockTiles) {
                if (Intrinsics.areEqual(oplusTileInfo.getSpec(), str)) {
                    oplusTileInfo.getState().label = charSequence;
                }
            }
        }
        oplusTileQueryHelper.addTile(str, charSequence2, state, z, false);
    }

    public static final void access$notifyTilesChanged(OplusTileQueryHelper oplusTileQueryHelper, boolean z) {
        if (z) {
            oplusTileQueryHelper.isFinished = true;
            ArrayList arrayList = new ArrayList(oplusTileQueryHelper.mCurrentTiles);
            Handler handler = oplusTileQueryHelper.mMainHandler;
            OplusTileQueryHelper$addTileList$1 oplusTileQueryHelper$addTileList$1 = new OplusTileQueryHelper$addTileList$1(2);
            oplusTileQueryHelper$addTileList$1.this$0 = oplusTileQueryHelper;
            oplusTileQueryHelper$addTileList$1.$tilesToAdd = arrayList;
            VarHandle.storeStoreFence();
            handler.post(oplusTileQueryHelper$addTileList$1);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<String> arrayList4 = oplusTileQueryHelper.mExtraDefaultTiles;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            String str = arrayList4.get(i);
            i++;
            QSTileQueryHelper.OplusTileInfo oplusTileInfo = oplusTileQueryHelper.mExtraTilesInfo.get(str);
            if (oplusTileInfo != null) {
                arrayList3.add(oplusTileInfo);
            }
        }
        arrayList2.addAll(arrayList3);
        arrayList2.addAll(oplusTileQueryHelper.mStockTiles);
        oplusTileQueryHelper.mAllTiles.clear();
        oplusTileQueryHelper.mAllTiles.addAll(oplusTileQueryHelper.mCurrentTiles);
        oplusTileQueryHelper.mAllTiles.addAll(arrayList3);
        oplusTileQueryHelper.mAllTiles.addAll(oplusTileQueryHelper.mStockTiles);
        Handler handler2 = oplusTileQueryHelper.mMainHandler;
        OplusTileQueryHelper$addTileList$1 oplusTileQueryHelper$addTileList$2 = new OplusTileQueryHelper$addTileList$1(3);
        oplusTileQueryHelper$addTileList$2.this$0 = oplusTileQueryHelper;
        oplusTileQueryHelper$addTileList$2.$tilesToAdd = arrayList2;
        VarHandle.storeStoreFence();
        handler2.post(oplusTileQueryHelper$addTileList$2);
    }

    public static final String access$parseTileMappingString(OplusTileQueryHelper oplusTileQueryHelper, String str) {
        String str2 = "";
        for (String str3 : (String[]) ArraysKt___ArraysJvmKt.plus(oplusTileQueryHelper.mContext.getResources().getStringArray(2130903277), oplusTileQueryHelper.mContext.getResources().getStringArray(2130903278))) {
            str3.getClass();
            if (StringsKt__StringsKt.contains(str3, str, false)) {
                List listSplit = new Regex(SETTING_SEPARATOR).split(str3, 0);
                if (listSplit.size() == 2) {
                    str2 = (String) listSplit.get(1);
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "customTile in stock array: ".concat(str3));
                }
            }
        }
        return str2;
    }

    public final void addCurrentTiles(QSHost qSHost) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileQueryHelper-->addCurrentTiles:");
        }
        ArrayList arrayList = new ArrayList(qSHost.getTiles());
        Handler handler = this.mBgHandler;
        OplusTileQueryHelper$addTileList$1 oplusTileQueryHelper$addTileList$1 = new OplusTileQueryHelper$addTileList$1(1);
        oplusTileQueryHelper$addTileList$1.$tilesToAdd = arrayList;
        oplusTileQueryHelper$addTileList$1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(oplusTileQueryHelper$addTileList$1);
    }

    public final void addStockAndPackageTiles(QSHost qSHost) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileQueryHelper-->addStockAndPackageTiles start ");
        }
        String str = this.mCurrentSettingSpecs;
        if (str == null) {
            str = this.mDefaultTiles;
        }
        int i = 0;
        List listSplit = new Regex(OplusPreventModeData.SEPARATOR).split(str, 0);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit) {
            if (!StringsKt__StringsJVMKt.startsWith((String) obj, "custom(", false)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        List<String> stockTileSpecs = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsTileHostHelperEx().getStockTileSpecs(this.mContext);
        this.mFilterCustomTiles.clear();
        for (String str2 : stockTileSpecs) {
            if (!arrayList.contains(str2)) {
                arrayList2.add(str2);
            }
            if (StringsKt__StringsKt.contains(TextUtils.emptyIfNull(str2), "custom(", false)) {
                this.mFilterCustomTiles.add(str2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            String str3 = (String) obj2;
            List<String> appQuickSettingsBlackList = AppQsTileBlackList.getAppQuickSettingsBlackList();
            if (appQuickSettingsBlackList == null || !appQuickSettingsBlackList.contains(str3)) {
                if (!AppQsTileBlackList.isTabletBlackListTile(str3)) {
                    if (QSTileFilterHelper.SPACE_GESTRUE_TILE.equals(str3)) {
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        if (!FlavorOneFeatureOption.isSupportActiveSpaceGesture()) {
                        }
                    }
                    if (!CustomizeFeatureOption.getHiddenQsTilesList().contains(str3) && !StringsKt__StringsJVMKt.startsWith(str3, "custom(", false)) {
                        QSTile qSTileCreateTile = this.mCacheTiles.get(str3);
                        if (qSTileCreateTile == null) {
                            qSTileCreateTile = qSHost.createTile(str3);
                            if (qSTileCreateTile != null) {
                                this.mCacheTiles.put(str3, qSTileCreateTile);
                            } else {
                                qSTileCreateTile = null;
                            }
                        }
                        if (qSTileCreateTile != null) {
                            if (qSTileCreateTile.isAvailable()) {
                                qSTileCreateTile.setTileSpec(str3);
                                arrayList3.add(qSTileCreateTile);
                            } else {
                                qSTileCreateTile.setTileSpec(str3);
                                qSTileCreateTile.destroy();
                            }
                        }
                    }
                }
            }
        }
        if (!arrayList3.isEmpty()) {
            Handler handler = this.mBgHandler;
            OplusTileQueryHelper$addTileList$1 oplusTileQueryHelper$addTileList$1 = new OplusTileQueryHelper$addTileList$1(i);
            oplusTileQueryHelper$addTileList$1.$tilesToAdd = arrayList3;
            oplusTileQueryHelper$addTileList$1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(oplusTileQueryHelper$addTileList$1);
        }
        Handler handler2 = this.mBgHandler;
        OplusTileQueryHelper$addTileList$1 oplusTileQueryHelper$addTileList$2 = new OplusTileQueryHelper$addTileList$1(4);
        oplusTileQueryHelper$addTileList$2.this$0 = this;
        oplusTileQueryHelper$addTileList$2.$tilesToAdd = str;
        VarHandle.storeStoreFence();
        handler2.post(oplusTileQueryHelper$addTileList$2);
    }

    public final void addTile(String str, CharSequence charSequence, QSTile.State state, boolean z, boolean z2) {
        if (z2 && this.mCurrentSpecs.contains(str)) {
            return;
        }
        if (z2 || !this.mStockSpecs.contains(str)) {
            if (z2 || !this.mExtraSpecs.contains(str)) {
                if (QSTileFilterHelper.SPACE_GESTRUE_TILE.equals(str)) {
                    FlavorOneFeatureOption.INSTANCE.getClass();
                    if (!FlavorOneFeatureOption.isSupportActiveSpaceGesture()) {
                        return;
                    }
                }
                QSTileQueryHelper.OplusTileInfo oplusTileInfo = new QSTileQueryHelper.OplusTileInfo(str, state, z);
                if (!((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                    oplusTileInfo.getState().dualTarget = false;
                }
                oplusTileInfo.getState().expandedAccessibilityClassName = Button.class.getName();
                QSTile.State state2 = oplusTileInfo.getState();
                if (z || TextUtils.equals(state.label, charSequence)) {
                    charSequence = null;
                }
                state2.secondaryLabel = charSequence;
                if (z2) {
                    this.mCurrentTiles.add(oplusTileInfo);
                    this.mCurrentSpecs.add(str);
                } else if (this.mExtraDefaultTiles.contains(str)) {
                    this.mExtraTilesInfo.put(str, oplusTileInfo);
                    this.mExtraSpecs.add(str);
                } else {
                    this.mStockTiles.add(oplusTileInfo);
                    this.mStockSpecs.add(str);
                }
            }
        }
    }

    public final void clearCacheTile() {
        this.mCurrentTiles.clear();
        this.mStockTiles.clear();
        this.mExtraTilesInfo.clear();
        this.mStockSpecs.clear();
        this.mCurrentSpecs.clear();
        this.mExtraSpecs.clear();
        this.isFinished = false;
        this.mBgHandler.removeCallbacksAndEqualMessages(null);
        Iterator<String> it = this.mCacheTiles.keySet().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            LinkedHashMap<String, QSTile> linkedHashMap = this.mCacheTiles;
            if (!zHasNext) {
                linkedHashMap.clear();
                this.mAllTiles.clear();
                return;
            } else {
                CustomTile customTile = linkedHashMap.get(it.next());
                CustomTile customTile2 = customTile instanceof CustomTile ? customTile : null;
                if (customTile2 != null) {
                    customTile2.destroy();
                }
            }
        }
    }

    public List<QSTileQueryHelper.OplusTileInfo> getAllTiles() {
        return this.mAllTiles;
    }

    public List<String> getDefaultTileSpecs() {
        return ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsTileHostHelperEx().getDefaultTileSpecs(this.mContext);
    }

    public final Drawable handleIcon(Context context, ResolveInfo resolveInfo) {
        Icon iconCreateWithResource;
        Object obj;
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        String str = serviceInfo.packageName;
        int i = serviceInfo.icon;
        if (i != 0) {
            iconCreateWithResource = Icon.createWithResource(str, i);
            iconCreateWithResource.getClass();
        } else {
            iconCreateWithResource = Icon.createWithResource(str, serviceInfo.applicationInfo.icon);
            iconCreateWithResource.getClass();
        }
        try {
            obj = Result.constructor-impl(iconCreateWithResource.loadDrawable(context));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "failed to load drawable");
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Drawable drawable = (Drawable) obj;
        if (drawable != null) {
            OplusCustomTileStub oplusCustomTileStub = OplusCustomTileStub.INSTANCE;
            int customTileIconSize = oplusCustomTileStub.getCustomTileIconSize(context, resolveInfo.serviceInfo.getComponentName());
            return oplusCustomTileStub.compoundBitmap(drawable, customTileIconSize, customTileIconSize, oplusCustomTileStub.getCustomTileIconRealSize(this.mContext), null, null);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileQueryHelper-->".concat("icon is null, info = " + resolveInfo.serviceInfo));
        }
        return null;
    }

    public final boolean isFinished() {
        return this.isFinished;
    }

    public final void queryTiles() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileQueryHelper-->QueryTiles start");
        }
        this.mCurrentTiles.clear();
        this.mStockTiles.clear();
        this.mExtraTilesInfo.clear();
        this.mStockSpecs.clear();
        this.mCurrentSpecs.clear();
        this.mExtraSpecs.clear();
        this.isFinished = false;
        this.mCurrentSettingSpecs = QsSettingsValueProxy.Companion.getTileSettings(this.mContext);
        addCurrentTiles(this.mQSHost);
        addStockAndPackageTiles(this.mQSHost);
    }

    public void setColorTileStateListener(QSTileQueryHelper.ColorTileStateListener colorTileStateListener) {
        this.mListener = colorTileStateListener;
    }
}
