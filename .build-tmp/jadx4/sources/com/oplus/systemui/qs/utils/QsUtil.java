package com.oplus.systemui.qs.utils;

import android.app.WallpaperColors;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.OplusPackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.statusbar.policy.BatteryController;
import com.oplus.materialsdk.uxcolor.UxWallpaperColors;
import com.oplus.materialsdk.uxcolor.color.Material3ColorManager;
import com.oplus.materialsdk.uxcolor.color.MaterialStyle;
import com.oplus.systemui.qs.widget.OplusQSMultiUserSwitch;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsUtil {
    private static final String AUTHORITY = "com.coloros.assistantscreen.config";
    private static final String TAG = "QsUtil";
    public static final QsUtil INSTANCE = new QsUtil();
    private static ArrayList<Integer> sQsStatusBarCutOutPaddingArray = new ArrayList<>();
    private static final String PATH_SYSTEMUI_ENTER_WXPAY = "systemui_enter_wxpay";
    public static final Uri URI_SYSTEMUI_ENTER_WXPAY = Uri.withAppendedPath(Uri.parse("content://com.coloros.assistantscreen.config"), PATH_SYSTEMUI_ENTER_WXPAY);

    public static final int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final void getAndUnFreezePackage(String str, int i, Context context) {
        Object obj;
        Object obj2;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportAppFrozen()) {
            OplusPackageManager oplusPackageManager = new OplusPackageManager(context);
            try {
                obj = Result.constructor-impl(Integer.valueOf(oplusPackageManager.getOplusFreezePackageState(str, i)));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (obj instanceof Result.Failure) {
                obj = 0;
            }
            int iIntValue = ((Number) obj).intValue();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("getAndUnFreezePackage packageName: ", str, " packageState: ", iIntValue), null, 8);
            OplusPackageManager oplusPackageManager2 = iIntValue == 2 ? oplusPackageManager : null;
            if (oplusPackageManager2 != null) {
                try {
                    obj2 = Result.constructor-impl(Integer.valueOf(oplusPackageManager2.oplusUnFreezePackage(str, i, 1, 0, context.getPackageName())));
                } catch (Throwable th2) {
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
                }
                if (Result.exceptionOrNull-impl(obj2) != null) {
                    LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "oplusUnFreezePackage RemoteException");
                }
                if (!(obj2 instanceof Result.Failure)) {
                    ((Number) obj2).intValue();
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "getAndUnFreezePackage unfreeze Package", null, 8);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static final Float getCornerRadiusForShapeDrawable(View view) {
        if (view instanceof OplusQSMultiUserSwitch) {
            return Float.valueOf(dp2px(((OplusQSMultiUserSwitch) view).getContext(), 20.0f));
        }
        return null;
    }

    public static final List<Integer> getPrimaryColor(Context context, Bitmap bitmap, List<Double> list) {
        ArrayList arrayList = new ArrayList();
        if (bitmap != null) {
            int argb = WallpaperColors.fromBitmap(bitmap).getPrimaryColor().toArgb();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                double dDoubleValue = ((Number) it.next()).doubleValue();
                int i = UxWallpaperColors.COLOR_MIDDLE_GRAY;
                Context context2 = context;
                Integer uxPn = new Material3ColorManager().getUxPn(context2, argb, true, dDoubleValue, MaterialStyle.CONTENT);
                uxPn.getClass();
                arrayList.add(uxPn);
                context = context2;
            }
        }
        return arrayList;
    }

    public static final Pair<Integer, Integer> getQsStatusBarCutoutPadding() {
        if (sQsStatusBarCutOutPaddingArray.size() != 2) {
            return null;
        }
        return new Pair<>(sQsStatusBarCutOutPaddingArray.get(0), sQsStatusBarCutOutPaddingArray.get(1));
    }

    public static final boolean isPowerSavingMode() {
        return ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getSuperPowerSaveRepository().getInSuperPowerSaveMode() || ((BatteryController) DependencyEx.sDependency.getDependency(BatteryController.class)).mPowerSave;
    }

    public static final void openWeChatPay(Context context) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = context.getContentResolver().query(URI_SYSTEMUI_ENTER_WXPAY, null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    int i = cursorQuery.getInt(0);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", "QsUtil-->".concat("systemui state:" + i));
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "QsUtil-->".concat("systemui cursor:" + cursorQuery));
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (RemoteException e) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, e.getMessage());
                if (0 != 0) {
                    cursor.close();
                }
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursor.close();
            throw th;
        }
    }

    public static final Intent resolveIntent(Context context, Intent intent) {
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return null;
        }
        Intent intent2 = new Intent(intent.getAction());
        ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
        return intent2.setClassName(activityInfo.packageName, activityInfo.name);
    }

    public static final void setQsStatusBarCutOutPadding(int i, int i2) {
        if (sQsStatusBarCutOutPaddingArray.size() != 2) {
            sQsStatusBarCutOutPaddingArray.clear();
            sQsStatusBarCutOutPaddingArray.add(Integer.valueOf(i));
            sQsStatusBarCutOutPaddingArray.add(Integer.valueOf(i2));
        }
    }
}
