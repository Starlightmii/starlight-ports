package com.oplusos.systemui.common.utils;

import android.app.OplusActivityManager;
import android.app.StatusBarManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.slice.widget.RowView$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.google.android.collect.Sets;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.base.BaseBinderCacheManager;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AppInfoUtil {
    public static final Set BLACK_DND_PACKAGES = Sets.newHashSet(new String[]{"com.android.systemui", "com.oplus.notificationmanager"});

    public static boolean compareVersion(String str, String str2) {
        Log.i("ApkInfoUtil", "A:" + str + " B:" + str2);
        if (str != null && !str.equals("") && !str2.equals("")) {
            if (str.equals(str2)) {
                return true;
            }
            String[] strArrSplit = str.split("\\.");
            String[] strArrSplit2 = str2.split("\\.");
            int length = strArrSplit.length < strArrSplit2.length ? strArrSplit.length : strArrSplit2.length;
            for (int i = 0; i < length; i++) {
                if (Integer.parseInt(strArrSplit2[i]) > Integer.parseInt(strArrSplit[i])) {
                    Log.i("ApkInfoUtil", "B:" + Integer.parseInt(strArrSplit2[i]) + " > A:" + Integer.parseInt(strArrSplit[i]));
                    return true;
                }
                if (Integer.parseInt(strArrSplit2[i]) < Integer.parseInt(strArrSplit[i])) {
                    Log.i("ApkInfoUtil", "B:" + Integer.parseInt(strArrSplit2[i]) + " < A:" + Integer.parseInt(strArrSplit[i]));
                    return false;
                }
            }
        }
        return false;
    }

    public static String getAppName(PackageManager packageManager, String str, int i) {
        PackageInfo packageInfoAsUser;
        try {
            packageInfoAsUser = packageManager.getPackageInfoAsUser(str, 0, UserHandle.getUserId(i));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(LogUtil.TAG_COMMON, "ApkInfoUtil", "getAppName: " + e.getMessage(), e);
            packageInfoAsUser = null;
        }
        if (packageInfoAsUser != null) {
            return (OplusMultiAppUtils.isClonedForPkg(str) && OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(UserHandle.getUserId(i)))) ? OplusMultiAppUtils.getMultiAppName(Integer.valueOf(UserHandle.getUserId(i)), str) : packageInfoAsUser.applicationInfo.loadLabel(packageManager).toString();
        }
        return "";
    }

    public static String getAppNameByPkgName(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfoAsUser(str, 0, KeyguardUpdateMonitor.getCurrentUser()).loadLabel(packageManager).toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getCurrentTopAppPackageName() {
        ComponentName topActivityComponentName;
        try {
            topActivityComponentName = new OplusActivityManager().getTopActivityComponentName();
        } catch (RemoteException e) {
            LogUtil.e(LogUtil.TAG_COMMON, "ApkInfoUtil", "getCurrentTopAppPackageName() e = " + e.getMessage(), e);
            topActivityComponentName = null;
        }
        return topActivityComponentName != null ? topActivityComponentName.getPackageName() : "";
    }

    public static ApplicationInfo getCurrentTopAppPackageNameAndUid() {
        try {
            return new OplusActivityManager().getTopApplicationInfo();
        } catch (RemoteException e) {
            LogUtil.e(LogUtil.TAG_COMMON, "ApkInfoUtil", "getCurrentTopAppPackageName:" + e.getMessage(), e);
            return null;
        }
    }

    public static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.oplus.uiengine", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("ApkInfoUtil", "com.oplus.uienginegetPackageVersionCode: is not installed." + e);
            return -1;
        }
    }

    public static String getTopActivityComponentName() {
        ComponentName topActivityComponentName;
        try {
            topActivityComponentName = new OplusActivityManager().getTopActivityComponentName();
        } catch (RemoteException e) {
            LogUtil.e(LogUtil.TAG_COMMON, "ApkInfoUtil", "getCurrentTopAppPackageName() e = " + e.getMessage(), e);
            topActivityComponentName = null;
        }
        return topActivityComponentName != null ? topActivityComponentName.getClassName() : "";
    }

    @Deprecated
    public static boolean getTopIsFullscreen() {
        int statusBarWindowState;
        CentralSurfaces centralSurfaces = (CentralSurfaces) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
        boolean z = false;
        if (centralSurfaces != null) {
            statusBarWindowState = centralSurfaces.getStatusBarWindowState();
            if (statusBarWindowState == 2 || statusBarWindowState == 1) {
                z = true;
            }
        } else {
            statusBarWindowState = -1;
        }
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("getTopIsFullscreen = ", ", statusBarWindowState = ", z);
        sbM.append(StatusBarManager.windowStateToString(statusBarWindowState));
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "ApkInfoUtil", sbM.toString());
        return z;
    }

    public static String getVersion(Context context, String str) {
        int currentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(currentUserId, "getVersion: userId = ", LogUtil.TAG_NOTIFICATION, "ApkInfoUtil");
        try {
            return context.getPackageManager().getPackageInfoAsUser(str, 0, currentUserId).versionName;
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, "ApkInfoUtil", "GetVersion failed! e:" + e.getMessage(), e);
            return null;
        }
    }

    public static boolean isPackageExist(Context context, String str) {
        if (str != null && !"".equals(str)) {
            try {
                context.getPackageManager().getApplicationInfoAsUser(str, OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING, KeyguardUpdateMonitor.getCurrentUser());
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    public static boolean isPackageInstalled(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            ConstraintSet$.ExternalSyntheticOutline0.m("isPackageInstalled: ", str, " not found.", "ApkInfoUtil");
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean isSystemApk(Context context, String str) {
        BaseBinderCacheManager baseBinderCacheManager = (BaseBinderCacheManager) DependencyEx.sDependency.getDependency(BaseBinderCacheManager.class);
        if (baseBinderCacheManager != null) {
            ApplicationInfo applicationInfoCache = baseBinderCacheManager.getApplicationInfoCache(str, false);
            return applicationInfoCache != null && applicationInfoCache.isSystemApp();
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 128).isSystemApp();
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, "ApkInfoUtil", "Exception: " + e.getMessage(), e);
            return false;
        }
    }
}
