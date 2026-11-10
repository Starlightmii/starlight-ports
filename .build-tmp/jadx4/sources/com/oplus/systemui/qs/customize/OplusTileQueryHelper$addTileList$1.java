package com.oplus.systemui.qs.customize;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.OplusPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.external.CustomTile;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.helper.QSTileHostHelper;
import com.oplus.systemui.qs.utils.AppQsTileBlackList;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileQueryHelper$addTileList$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $tilesToAdd;
    public /* synthetic */ OplusTileQueryHelper this$0;

    public /* synthetic */ OplusTileQueryHelper$addTileList$1(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Code duplicated, block: B:113:0x029f  */
    /* JADX WARN: Code duplicated, block: B:164:0x0189 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:0x0183 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x0205  */
    /* JADX WARN: Code duplicated, block: B:84:0x0214 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:95:0x0239  */
    /* JADX WARN: Code duplicated, block: B:98:0x0240  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        List<ResolveInfo> listQueryIntentServicesAsUser;
        Object obj;
        int i;
        String str;
        Object obj2;
        ComponentName componentName;
        boolean zIsComponentAvailable;
        String spec;
        String strAccess$parseTileMappingString;
        ServiceInfo serviceInfo;
        OplusTileQueryHelper oplusTileQueryHelper;
        Drawable drawableHandleIcon;
        String string;
        int i2 = 0;
        switch (this.$r8$classId) {
            case 0:
                ArrayList arrayList = (ArrayList) this.$tilesToAdd;
                int size = arrayList.size();
                while (i2 < size) {
                    Object obj3 = arrayList.get(i2);
                    i2++;
                    CustomTile customTile = (QSTile) obj3;
                    QSTile.State stateCopy = customTile.getState().copy();
                    stateCopy.label = customTile.getTileLabel();
                    stateCopy.state = 1;
                    if (customTile instanceof CustomTile) {
                        stateCopy.iconSupplier = customTile.getTileIconSupplier();
                    } else {
                        stateCopy.icon = customTile.getDefaultIcon();
                    }
                    OplusTileQueryHelper oplusTileQueryHelper2 = this.this$0;
                    String tileSpec = customTile.getTileSpec();
                    OplusTileQueryHelper.Companion companion = OplusTileQueryHelper.Companion;
                    oplusTileQueryHelper2.addTile(tileSpec, null, stateCopy, true, false);
                    customTile.destroy();
                }
                break;
            case 1:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "TileQueryHelper-->addCurrentTiles bg start ");
                }
                ArrayList arrayList2 = (ArrayList) this.$tilesToAdd;
                int size2 = arrayList2.size();
                while (i2 < size2) {
                    Object obj4 = arrayList2.get(i2);
                    i2++;
                    CustomTile customTile2 = (QSTile) obj4;
                    QSTile.State stateCopy2 = customTile2.getState().copy();
                    stateCopy2.state = 1;
                    if (customTile2 instanceof CustomTile) {
                        CustomTile customTile3 = customTile2;
                        stateCopy2.label = customTile3.getQsTile().getLabel();
                        stateCopy2.iconSupplier = customTile3.getTileIconSupplier();
                    } else {
                        stateCopy2.label = customTile2.getTileLabel();
                        stateCopy2.icon = customTile2.getDefaultIcon();
                    }
                    OplusTileQueryHelper oplusTileQueryHelper3 = this.this$0;
                    String tileSpec2 = customTile2.getTileSpec();
                    OplusTileQueryHelper.Companion companion2 = OplusTileQueryHelper.Companion;
                    oplusTileQueryHelper3.addTile(tileSpec2, null, stateCopy2, true, true);
                }
                OplusTileQueryHelper.access$notifyTilesChanged(this.this$0, true);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "TileQueryHelper-->addCurrentTiles bg end ");
                    break;
                }
                break;
            case 2:
                QSTileQueryHelper.ColorTileStateListener colorTileStateListener = this.this$0.mListener;
                if (colorTileStateListener != null) {
                    colorTileStateListener.onCurrentTilesChanged((ArrayList) this.$tilesToAdd);
                }
                break;
            case 3:
                QSTileQueryHelper.ColorTileStateListener colorTileStateListener2 = this.this$0.mListener;
                if (colorTileStateListener2 != null) {
                    colorTileStateListener2.onStockTilesChanged((ArrayList) this.$tilesToAdd);
                }
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "TileQueryHelper-->addStockAndPackageTiles bg start ");
                }
                PackageManager packageManager = this.this$0.mContext.getPackageManager();
                OplusPackageManager oplusPackageManager = new OplusPackageManager(this.this$0.mContext);
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isSupportAppFrozen()) {
                    listQueryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 1073741824, this.this$0.mUserTracker.getUserId());
                    listQueryIntentServicesAsUser.getClass();
                } else {
                    listQueryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 0, this.this$0.mUserTracker.getUserId());
                    listQueryIntentServicesAsUser.getClass();
                }
                List stockTileSpecs = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsTileHostHelperEx().getStockTileSpecs(this.this$0.mContext);
                for (ResolveInfo resolveInfo : listQueryIntentServicesAsUser) {
                    String str2 = resolveInfo.serviceInfo.packageName;
                    OplusTileQueryHelper.Companion companion3 = OplusTileQueryHelper.Companion;
                    Context context = this.this$0.mContext;
                    str2.getClass();
                    companion3.getClass();
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isSupportAppFrozen()) {
                        int userId = context.getUserId();
                        try {
                            obj = Result.constructor-impl(Integer.valueOf(oplusPackageManager.getOplusFreezePackageState(str2, userId)));
                        } catch (Throwable th) {
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                        }
                        Throwable th2 = Result.exceptionOrNull-impl(obj);
                        i = i2;
                        if (th2 != null) {
                            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "getOplusFreezePackageState has exception: ", th2.getMessage(), LogUtil.TAG_QS, "TileQueryHelper");
                        }
                        Integer numValueOf = Integer.valueOf(i);
                        if (obj instanceof Result.Failure) {
                            obj = numValueOf;
                        }
                        if (((Number) obj).intValue() == 2) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("unfreeze Package:", str2, "TileQueryHelper-->", "SystemUi--QuickSettings");
                            }
                            try {
                                str = "TileQueryHelper-->";
                                OplusPackageManager oplusPackageManager2 = oplusPackageManager;
                                try {
                                    try {
                                        obj2 = Result.constructor-impl(Integer.valueOf(oplusPackageManager2.oplusUnFreezePackage(str2, userId, 1, 0, context.getPackageName())));
                                    } catch (Throwable th3) {
                                        th = th3;
                                        obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    oplusPackageManager = oplusPackageManager2;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                str = "TileQueryHelper-->";
                            }
                            Throwable th6 = Result.exceptionOrNull-impl(obj2);
                            if (th6 != null) {
                                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "oplusUnFreezePackage has exception: ", th6.getMessage(), LogUtil.TAG_QS, "TileQueryHelper");
                            }
                            break;
                        }
                        componentName = new ComponentName(str2, resolveInfo.serviceInfo.name);
                        zIsComponentAvailable = QSTileHostHelper.isComponentAvailable(this.this$0.mContext, componentName);
                        spec = CustomTile.toSpec(componentName);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("Query Spec ====", spec, ", componentAvailable: ", zIsComponentAvailable, str));
                        }
                        if (zIsComponentAvailable) {
                            if (StringsKt__StringsKt.contains(TextUtils.emptyIfNull((String) this.$tilesToAdd), spec, i) && this.this$0.mFilterCustomTiles.contains(spec)) {
                                CopyOnWriteArrayList copyOnWriteArrayList = this.this$0.mStockTiles;
                                OplusTileQueryHelper.addStockAndPackageTiles.2.3 r5 = new OplusTileQueryHelper.addStockAndPackageTiles.2.3();
                                r5.$spec = spec;
                                VarHandle.storeStoreFence();
                                copyOnWriteArrayList.removeIf(r5);
                            }
                            if (!CustomizeFeatureOption.getHiddenQsTilesList().contains(spec) && (!stockTileSpecs.contains(componentName.flattenToString()) || this.this$0.mFilterCustomTiles.contains(componentName.flattenToString()))) {
                                strAccess$parseTileMappingString = OplusTileQueryHelper.access$parseTileMappingString(this.this$0, componentName.flattenToShortString());
                                if (strAccess$parseTileMappingString.length() > 0 || !stockTileSpecs.contains(strAccess$parseTileMappingString) || this.this$0.mFilterCustomTiles.contains(strAccess$parseTileMappingString)) {
                                    List<String> appQuickSettingsBlackList = AppQsTileBlackList.getAppQuickSettingsBlackList();
                                    if (!StringsKt__StringsKt.contains((String) this.$tilesToAdd, spec, false) && ((appQuickSettingsBlackList == null || !appQuickSettingsBlackList.contains(spec)) && !QSTileFilterHelper.INSTANCE.filterRemoveSpecs(this.this$0.mContext, spec) && !AppQsTileBlackList.isTabletBlackListTile(spec))) {
                                        serviceInfo = resolveInfo.serviceInfo;
                                        if ((serviceInfo.icon == 0 || serviceInfo.applicationInfo.icon != 0) && "android.permission.BIND_QUICK_SETTINGS_TILE".equals(serviceInfo.permission) && !"custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileService)".equals(spec) && !"custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotOne)".equals(spec) && !"custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotTwo)".equals(spec) && (drawableHandleIcon = (oplusTileQueryHelper = this.this$0).handleIcon(oplusTileQueryHelper.mContext, resolveInfo)) != null) {
                                            drawableHandleIcon.setTint(this.this$0.mContext.getResources().getColor(2131103746, null));
                                            CharSequence charSequenceLoadLabel = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                                            CharSequence charSequenceLoadLabel2 = resolveInfo.serviceInfo.loadLabel(packageManager);
                                            OplusTileQueryHelper oplusTileQueryHelper4 = this.this$0;
                                            string = charSequenceLoadLabel2.toString();
                                            if (string == null) {
                                                string = "null";
                                            }
                                            OplusTileQueryHelper.access$createStateAndAddTile(oplusTileQueryHelper4, spec, drawableHandleIcon, string, charSequenceLoadLabel, resolveInfo.system);
                                        }
                                    }
                                }
                            }
                            i2 = 0;
                        } else {
                            i2 = i;
                        }
                    } else {
                        i = i2;
                        oplusPackageManager = oplusPackageManager;
                    }
                    str = "TileQueryHelper-->";
                    componentName = new ComponentName(str2, resolveInfo.serviceInfo.name);
                    zIsComponentAvailable = QSTileHostHelper.isComponentAvailable(this.this$0.mContext, componentName);
                    spec = CustomTile.toSpec(componentName);
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--QuickSettings", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("Query Spec ====", spec, ", componentAvailable: ", zIsComponentAvailable, str));
                    }
                    if (zIsComponentAvailable) {
                        i2 = i;
                    } else {
                        if (StringsKt__StringsKt.contains(TextUtils.emptyIfNull((String) this.$tilesToAdd), spec, i)) {
                            CopyOnWriteArrayList copyOnWriteArrayList2 = this.this$0.mStockTiles;
                            OplusTileQueryHelper.addStockAndPackageTiles.2.3 r6 = new OplusTileQueryHelper.addStockAndPackageTiles.2.3();
                            r6.$spec = spec;
                            VarHandle.storeStoreFence();
                            copyOnWriteArrayList2.removeIf(r6);
                        }
                        if (!CustomizeFeatureOption.getHiddenQsTilesList().contains(spec)) {
                            strAccess$parseTileMappingString = OplusTileQueryHelper.access$parseTileMappingString(this.this$0, componentName.flattenToShortString());
                            if (strAccess$parseTileMappingString.length() > 0) {
                                List<String> appQuickSettingsBlackList2 = AppQsTileBlackList.getAppQuickSettingsBlackList();
                                if (!StringsKt__StringsKt.contains((String) this.$tilesToAdd, spec, false)) {
                                    serviceInfo = resolveInfo.serviceInfo;
                                    if (serviceInfo.icon == 0) {
                                        drawableHandleIcon.setTint(this.this$0.mContext.getResources().getColor(2131103746, null));
                                        CharSequence charSequenceLoadLabel3 = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                                        CharSequence charSequenceLoadLabel4 = resolveInfo.serviceInfo.loadLabel(packageManager);
                                        OplusTileQueryHelper oplusTileQueryHelper5 = this.this$0;
                                        string = charSequenceLoadLabel4.toString();
                                        if (string == null) {
                                            string = "null";
                                        }
                                        OplusTileQueryHelper.access$createStateAndAddTile(oplusTileQueryHelper5, spec, drawableHandleIcon, string, charSequenceLoadLabel3, resolveInfo.system);
                                    } else {
                                        drawableHandleIcon.setTint(this.this$0.mContext.getResources().getColor(2131103746, null));
                                        CharSequence charSequenceLoadLabel5 = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                                        CharSequence charSequenceLoadLabel6 = resolveInfo.serviceInfo.loadLabel(packageManager);
                                        OplusTileQueryHelper oplusTileQueryHelper6 = this.this$0;
                                        string = charSequenceLoadLabel6.toString();
                                        if (string == null) {
                                            string = "null";
                                        }
                                        OplusTileQueryHelper.access$createStateAndAddTile(oplusTileQueryHelper6, spec, drawableHandleIcon, string, charSequenceLoadLabel5, resolveInfo.system);
                                    }
                                }
                            } else {
                                List<String> appQuickSettingsBlackList3 = AppQsTileBlackList.getAppQuickSettingsBlackList();
                                if (!StringsKt__StringsKt.contains((String) this.$tilesToAdd, spec, false)) {
                                    serviceInfo = resolveInfo.serviceInfo;
                                    if (serviceInfo.icon == 0) {
                                        drawableHandleIcon.setTint(this.this$0.mContext.getResources().getColor(2131103746, null));
                                        CharSequence charSequenceLoadLabel7 = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                                        CharSequence charSequenceLoadLabel8 = resolveInfo.serviceInfo.loadLabel(packageManager);
                                        OplusTileQueryHelper oplusTileQueryHelper7 = this.this$0;
                                        string = charSequenceLoadLabel8.toString();
                                        if (string == null) {
                                            string = "null";
                                        }
                                        OplusTileQueryHelper.access$createStateAndAddTile(oplusTileQueryHelper7, spec, drawableHandleIcon, string, charSequenceLoadLabel7, resolveInfo.system);
                                    } else {
                                        drawableHandleIcon.setTint(this.this$0.mContext.getResources().getColor(2131103746, null));
                                        CharSequence charSequenceLoadLabel9 = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                                        CharSequence charSequenceLoadLabel10 = resolveInfo.serviceInfo.loadLabel(packageManager);
                                        OplusTileQueryHelper oplusTileQueryHelper8 = this.this$0;
                                        string = charSequenceLoadLabel10.toString();
                                        if (string == null) {
                                            string = "null";
                                        }
                                        OplusTileQueryHelper.access$createStateAndAddTile(oplusTileQueryHelper8, spec, drawableHandleIcon, string, charSequenceLoadLabel9, resolveInfo.system);
                                    }
                                }
                            }
                        }
                        i2 = 0;
                    }
                    break;
                }
                OplusTileQueryHelper.access$notifyTilesChanged(this.this$0, false);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "TileQueryHelper-->addStockAndPackageTiles bg end ");
                    break;
                }
                break;
        }
    }
}
