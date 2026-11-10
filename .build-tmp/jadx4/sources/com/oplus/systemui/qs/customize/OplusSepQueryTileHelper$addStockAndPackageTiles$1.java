package com.oplus.systemui.qs.customize;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.OplusPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.qs.external.CustomTile;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.qs.external.OplusCustomTileStub;
import com.oplus.systemui.qs.helper.QSTileHostHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSepQueryTileHelper$addStockAndPackageTiles$1 implements Runnable {
    public /* synthetic */ OplusSepQueryTileHelper this$0;

    @Override // java.lang.Runnable
    public final void run() {
        List<ResolveInfo> listQueryIntentServicesAsUser;
        Icon iconCreateWithResource;
        Object obj;
        Drawable drawableCompoundBitmap;
        Object obj2;
        Object obj3;
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSepQueryTileHelper", "addStockAndPackageTiles bg start ", null, 8);
        PackageManager packageManager = this.this$0.context.getPackageManager();
        OplusPackageManager oplusPackageManager = new OplusPackageManager(this.this$0.context);
        FeatureOption.INSTANCE.getClass();
        int i = 0;
        if (FeatureOption.isSupportAppFrozen()) {
            listQueryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 1073741824, this.this$0.userTracker.getUserId());
            listQueryIntentServicesAsUser.getClass();
        } else {
            listQueryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 0, this.this$0.userTracker.getUserId());
            listQueryIntentServicesAsUser.getClass();
        }
        for (ResolveInfo resolveInfo : listQueryIntentServicesAsUser) {
            String str = resolveInfo.serviceInfo.packageName;
            OplusSepQueryTileHelper.Companion companion = OplusSepQueryTileHelper.Companion;
            Context context = this.this$0.context;
            str.getClass();
            companion.getClass();
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isSupportAppFrozen()) {
                int userId = context.getUserId();
                try {
                    obj2 = Result.constructor-impl(Integer.valueOf(oplusPackageManager.getOplusFreezePackageState(str, userId)));
                } catch (Throwable th) {
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj2);
                if (th2 != null) {
                    OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "getOplusFreezePackageState has exception: ", th2.getMessage(), LogUtil.TAG_QS, "OplusSepQueryTileHelper");
                }
                Integer numValueOf = Integer.valueOf(i);
                if (obj2 instanceof Result.Failure) {
                    obj2 = numValueOf;
                }
                if (((Number) obj2).intValue() == 2) {
                    LogUtil.i$default(LogUtil.TAG_QS, "OplusSepQueryTileHelper", "unfreeze Package:".concat(str), null, 8);
                    try {
                        obj3 = Result.constructor-impl(Integer.valueOf(oplusPackageManager.oplusUnFreezePackage(str, userId, 1, 0, context.getPackageName())));
                    } catch (Throwable th3) {
                        obj3 = Result.constructor-impl(ResultKt.createFailure(th3));
                    }
                    Throwable th4 = Result.exceptionOrNull-impl(obj3);
                    if (th4 != null) {
                        OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "oplusUnFreezePackage has exception: ", th4.getMessage(), LogUtil.TAG_QS, "OplusSepQueryTileHelper");
                    }
                }
            }
            ComponentName componentName = new ComponentName(str, resolveInfo.serviceInfo.name);
            boolean zIsComponentAvailable = QSTileHostHelper.isComponentAvailable(this.this$0.context, componentName);
            String spec = CustomTile.toSpec(componentName);
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("Query Spec ====", spec, ", system=", resolveInfo.system, " componentAvailable: ");
            sbM.append(zIsComponentAvailable);
            LogUtil.i$default(LogUtil.TAG_QS, "OplusSepQueryTileHelper", sbM.toString(), null, 8);
            if (!OplusSepQueryTileHelper.access$shouldSkipTile(this.this$0, resolveInfo.serviceInfo, componentName, spec)) {
                Context context2 = this.this$0.context;
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                String str2 = serviceInfo.packageName;
                int i2 = serviceInfo.icon;
                if (i2 != 0) {
                    iconCreateWithResource = Icon.createWithResource(str2, i2);
                    iconCreateWithResource.getClass();
                } else {
                    iconCreateWithResource = Icon.createWithResource(str2, serviceInfo.applicationInfo.icon);
                    iconCreateWithResource.getClass();
                }
                try {
                    obj = Result.constructor-impl(iconCreateWithResource.loadDrawable(context2));
                } catch (Throwable th5) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th5));
                }
                if (Result.exceptionOrNull-impl(obj) != null) {
                    LogUtil.w$default(8, LogUtil.TAG_QS, "OplusSepQueryTileHelper", "failed to load drawable");
                }
                if (obj instanceof Result.Failure) {
                    obj = null;
                }
                Drawable drawable = (Drawable) obj;
                if (drawable == null) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "OplusSepQueryTileHelper-->".concat("icon is null, info = " + resolveInfo.serviceInfo));
                    }
                    drawableCompoundBitmap = null;
                } else {
                    OplusCustomTileStub oplusCustomTileStub = OplusCustomTileStub.INSTANCE;
                    int customTileIconSize = oplusCustomTileStub.getCustomTileIconSize(context2, resolveInfo.serviceInfo.getComponentName());
                    drawableCompoundBitmap = oplusCustomTileStub.compoundBitmap(drawable, customTileIconSize, customTileIconSize, oplusCustomTileStub.getCustomTileIconRealSize(context2), null, null);
                }
                if (drawableCompoundBitmap != null) {
                    drawableCompoundBitmap.setTint(this.this$0.context.getResources().getColor(2131103746, null));
                    CharSequence charSequenceLoadLabel = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                    CharSequence charSequenceLoadLabel2 = resolveInfo.serviceInfo.loadLabel(packageManager);
                    OplusSepQueryTileHelper oplusSepQueryTileHelper = this.this$0;
                    String string = charSequenceLoadLabel2.toString();
                    if (string == null) {
                        string = "null";
                    }
                    OplusSepQueryTileHelper.access$createStateAndAddTile(oplusSepQueryTileHelper, spec, drawableCompoundBitmap, string, charSequenceLoadLabel, resolveInfo.system);
                }
            }
            i = 0;
        }
        OplusSepQueryTileHelper.access$notifyTilesChanged(this.this$0);
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSepQueryTileHelper", "addStockAndPackageTiles bg end ", null, 8);
    }
}
