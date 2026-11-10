package com.oplus.systemui.keyguard.wallpaper.uiengine;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.base.OplusKeyguardCtrl;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.android.systemui.Dependency;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.settings.proxy.KeyguardSettingBackupRestoreProxy$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.keyguard.pluginer.ForeignPackage;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardPluginManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardPluginManager";
    private static final String UIENGINE_CTRL_CLASS = "com.oplus.uiengine.ctrl.EngineKeyguardCtrl";
    private static final String UIENGINE_PACKAGE = "com.oplus.uiengine";
    private String mCompatEnginePkg;
    private final Context mContext;
    private ForeignPackage mForeignPackage;
    private Class<?> mKeyguardCtrlClass;
    public long mKeyguardResLastModifiedTime;
    private String mKeyguardResLastPath;
    public int mLastPackageVersionCode;
    private PackageManager mPackageManager;
    private KeyguardUpdateMonitorCallback mPlugInCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardPluginManager(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mCompatEnginePkg = context.getResources().getString(2131953157);
    }

    public final void clearKeyguardPlugIn() {
        if (this.mPlugInCallback != null) {
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).removeCallback(this.mPlugInCallback);
            this.mPlugInCallback = null;
        }
    }

    public final String getKeyguardCompatible(String str) {
        List<ResolveInfo> listQueryIntentServicesAsUser;
        if (str == null || str.length() == 0 || (listQueryIntentServicesAsUser = this.mPackageManager.queryIntentServicesAsUser(new Intent("oplus.intent.action.keyguard"), OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION, KeyguardUpdateMonitor.getCurrentUser())) == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : listQueryIntentServicesAsUser) {
            if ((resolveInfo != null ? resolveInfo.serviceInfo : null) != null) {
                String str2 = resolveInfo.serviceInfo.packageName;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("isKeyguardCompatible pkg=", str, " tempPkg=", str2, "KeyguardPluginManager-->"));
                }
                if (!TextUtils.isEmpty(str2) && !Intrinsics.areEqual(str2, "com.android.systemui")) {
                    Locale locale = Locale.ROOT;
                    String lowerCase = str.toLowerCase(locale);
                    str2.getClass();
                    if (Intrinsics.areEqual(lowerCase, str2.toLowerCase(locale))) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("isKeyguardCompatible current keyguard:", str, " is compatible to new keyguard architecture", "KeyguardPluginManager-->", "SystemUi--Keyguard");
                        }
                        return str;
                    }
                    if (str.equals(this.mCompatEnginePkg) && str2.equals(UIENGINE_PACKAGE)) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            Log.d("SystemUi--Keyguard", "KeyguardPluginManager-->isKeyguardCompatible compat uiengine.");
                        }
                        Settings.System.putStringForUser(this.mContext.getContentResolver(), "oplus_customize_unlock_change_pkg", UIENGINE_PACKAGE, KeyguardUpdateMonitor.getCurrentUser());
                        Settings.System.putStringForUser(this.mContext.getContentResolver(), "oplus_customize_unlock_change_class", UIENGINE_CTRL_CLASS, KeyguardUpdateMonitor.getCurrentUser());
                        return str2;
                    }
                }
            }
        }
        return null;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final Typeface getTypefaceFromAsset(String str) {
        ForeignPackage foreignPackage = this.mForeignPackage;
        if (foreignPackage == null) {
            KgdLogUtil.w(TAG, "error, getTypefaceFromAsset, mForeignPackage = null");
            return null;
        }
        foreignPackage.getClass();
        foreignPackage.getClass();
        KgdLogUtil.d("ForeignPackage", "getTypefaceFromAsset, path =" + str);
        if (foreignPackage.mTypefaces == null) {
            foreignPackage.mTypefaces = new HashMap();
        }
        Typeface typeface = (Typeface) foreignPackage.mTypefaces.get(str);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(foreignPackage.mForeignCtx.getAssets(), str);
        foreignPackage.mTypefaces.put(str, typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }

    public final boolean isKeyguardResModified() {
        String keyguardLockResPath = KeyguardUtils.Companion.getKeyguardLockResPath(this.mContext);
        if (keyguardLockResPath == null) {
            return false;
        }
        return (new File(keyguardLockResPath).lastModified() == this.mKeyguardResLastModifiedTime && Intrinsics.areEqual(this.mKeyguardResLastPath, keyguardLockResPath)) ? false : true;
    }

    public final boolean isKeyguardVersionUpdated() {
        int packageVersionCode = AppInfoUtil.getPackageVersionCode(this.mContext);
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("isKeyguardVersionUpdated, currentVersion=", packageVersionCode, this.mLastPackageVersionCode, ", lastVersion=", TAG);
        return packageVersionCode != -1 && packageVersionCode > this.mLastPackageVersionCode;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0195  */
    public final OplusKeyguardCtrl loadKeyguard(KeyguardEngineContainerController keyguardEngineContainerController) {
        HashMap map;
        String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "oplus_customize_unlock_change_pkg", KeyguardUpdateMonitor.getCurrentUser());
        String stringForUser2 = Settings.System.getStringForUser(this.mContext.getContentResolver(), "oplus_customize_unlock_change_class", KeyguardUpdateMonitor.getCurrentUser());
        String keyguardCompatible = getKeyguardCompatible(stringForUser);
        if (keyguardCompatible == null) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("loadKeyguard, is not compatible, packageName = ", stringForUser, TAG);
            return null;
        }
        if (UIENGINE_PACKAGE.equals(keyguardCompatible)) {
            stringForUser2 = UIENGINE_CTRL_CLASS;
            stringForUser = UIENGINE_PACKAGE;
        }
        if (stringForUser == null || stringForUser.length() == 0 || stringForUser2 == null || stringForUser2.length() == 0) {
            KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("loadKeyguard, read setting fail, pkg = ", stringForUser, ", cls = ", stringForUser2, TAG);
            return null;
        }
        KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("loadKeyguard, pkg=", stringForUser, ", cls=", stringForUser2, TAG);
        ForeignPackage foreignPackage = this.mForeignPackage;
        if (foreignPackage == null) {
            if (!TextUtils.isEmpty(stringForUser) || TextUtils.isEmpty(stringForUser2)) {
                KgdLogUtil.w(TAG, "initForeignPackage, packageName or className is null, return false");
            } else {
                try {
                    ForeignPackage foreignPackage2 = new ForeignPackage(this.mContext, stringForUser);
                    this.mForeignPackage = foreignPackage2;
                    foreignPackage2.mForeignCtx.getClassLoader();
                    ForeignPackage foreignPackage3 = this.mForeignPackage;
                    foreignPackage3.getClass();
                    Class<?> clsLoadClass = foreignPackage3.getContext().getClassLoader().loadClass(stringForUser2);
                    this.mKeyguardCtrlClass = clsLoadClass;
                    KgdLogUtil.i(TAG, "initForeignPackage, mForeignPackage = " + this.mForeignPackage + ", mKeyguardCtrlClass = " + clsLoadClass + ", ret = true");
                } catch (PackageManager.NameNotFoundException e) {
                    KgdLogUtil.w(TAG, "initForeignPackage Exception = " + e);
                }
            }
            KgdLogUtil.w(TAG, "loadKeyguard, initForeignPackage fail, return null");
            return null;
        }
        if (!stringForUser.equals(foreignPackage.getPackagename()) || isKeyguardResModified() || isKeyguardVersionUpdated()) {
            ForeignPackage foreignPackage4 = this.mForeignPackage;
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("loadKeyguard, update Foreign Package, foreignPkg=", foreignPackage4 != null ? foreignPackage4.getPackagename() : null, TAG);
            ForeignPackage foreignPackage5 = this.mForeignPackage;
            if (foreignPackage5 != null && (map = foreignPackage5.mTypefaces) != null) {
                map.clear();
                foreignPackage5.mTypefaces = null;
            }
            this.mForeignPackage = null;
            this.mKeyguardCtrlClass = null;
            if (TextUtils.isEmpty(stringForUser)) {
                KgdLogUtil.w(TAG, "initForeignPackage, packageName or className is null, return false");
            } else {
                KgdLogUtil.w(TAG, "initForeignPackage, packageName or className is null, return false");
            }
            KgdLogUtil.w(TAG, "loadKeyguard, initForeignPackage fail, return null");
            return null;
        }
        OplusKeyguardCtrl oplusKeyguardCtrl = new OplusKeyguardCtrl(this.mContext);
        try {
            Class<?> cls = this.mKeyguardCtrlClass;
            cls.getClass();
            Object objNewInstance = cls.getConstructor(Context.class, Object.class).newInstance(this.mContext, oplusKeyguardCtrl);
            oplusKeyguardCtrl.setEnginePlugin(objNewInstance);
            keyguardEngineContainerController.setViewFlag(oplusKeyguardCtrl.beInflated(this.mContext, keyguardEngineContainerController.getMKeyguardEngineContainer(), 0, (Bundle) null));
            this.mPlugInCallback = oplusKeyguardCtrl.getKeyguardUpdateMonitorCallback();
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(this.mPlugInCallback);
            String keyguardLockResPath = KeyguardUtils.Companion.getKeyguardLockResPath(this.mContext);
            if (keyguardLockResPath == null) {
                return null;
            }
            this.mKeyguardResLastModifiedTime = new File(keyguardLockResPath).lastModified();
            int packageVersionCode = AppInfoUtil.getPackageVersionCode(this.mContext);
            if (packageVersionCode != -1) {
                this.mLastPackageVersionCode = packageVersionCode;
            }
            this.mKeyguardResLastPath = keyguardLockResPath;
            KgdLogUtil.i(TAG, "loadKeyguard, keyguard load finished, enginePlugin=" + objNewInstance + ", versionCode=" + packageVersionCode);
            return oplusKeyguardCtrl;
        } catch (NoSuchMethodException e2) {
            KgdLogUtil.w(TAG, "Exception = " + e2);
            return null;
        }
    }

    public final boolean maybeReloadNormalKeyguard(boolean z) {
        String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "oplus_customize_unlock_change_pkg", KeyguardUpdateMonitor.getCurrentUser());
        if (stringForUser == null) {
            stringForUser = "com.android.systemui";
        }
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        boolean zIsCustomLockStyle = companion.isCustomLockStyle(this.mContext);
        Context context = this.mContext;
        if (zIsCustomLockStyle) {
            String stringForUser2 = Settings.System.getStringForUser(context.getContentResolver(), "oplus_customize_change_keyguard", KeyguardUpdateMonitor.getCurrentUser());
            String stringForUser3 = Settings.System.getStringForUser(this.mContext.getContentResolver(), "oplus_has_default_unlock_change_pkg", KeyguardUpdateMonitor.getCurrentUser());
            boolean z2 = (TextUtils.isEmpty(stringForUser) || "com.android.systemui".equals(stringForUser)) && TextUtils.isEmpty(stringForUser2) && TextUtils.isEmpty(stringForUser3);
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("maybeReloadNormalKeyguard, packageName:", stringForUser, ", changeKeyguard:", stringForUser2, ", hasCustomizeUnlockPkg:");
            sbM.append(stringForUser3);
            sbM.append(", isFirstReloadCustomKeyguard:");
            sbM.append(z2);
            KgdLogUtil.i(TAG, sbM.toString());
            if (z2) {
                KgdLogUtil.i(TAG, "maybeReloadNormalKeyguard, custom theme change pkg, ".concat(stringForUser));
                companion.getKeyguardUtils(this.mContext).recoverToCustomKeyguardSettings(this.mContext, UIENGINE_PACKAGE, UIENGINE_CTRL_CLASS);
                Settings.System.putStringForUser(this.mContext.getContentResolver(), "oplus_customize_change_keyguard", "true", KeyguardUpdateMonitor.getCurrentUser());
                stringForUser = UIENGINE_PACKAGE;
            }
        } else if (companion.isThirdThemeWithSmoothTransition(context)) {
            stringForUser = "com.android.systemui";
        } else if (UIENGINE_PACKAGE.equals(getKeyguardCompatible(stringForUser))) {
            stringForUser = UIENGINE_PACKAGE;
        }
        if (TextUtils.isEmpty(stringForUser)) {
            KgdLogUtil.w(TAG, "maybeReloadNormalKeyguard, read setting fail, recoverToDefaultKeyguardSettings");
            companion.getKeyguardUtils(this.mContext).recoverToDefaultKeyguardSettings(this.mContext);
            return !z;
        }
        ForeignPackage foreignPackage = this.mForeignPackage;
        if (foreignPackage == null) {
            if (stringForUser.equals("com.android.systemui")) {
                KgdLogUtil.i(TAG, "maybeReloadNormalKeyguard, uses default LockScreen");
                return false;
            }
            KgdLogUtil.i(TAG, "maybeReloadNormalKeyguard, packageName=".concat(stringForUser));
            return true;
        }
        if (stringForUser.equals(foreignPackage.getPackagename()) || companion.isCustomLockStyle(this.mContext)) {
            if (isKeyguardResModified()) {
                KgdLogUtil.w(TAG, "maybeReloadNormalKeyguard, theme resources has modified : ".concat(stringForUser));
                return true;
            }
            KgdLogUtil.i(TAG, "maybeReloadNormalKeyguard, not change : ".concat(stringForUser));
            return false;
        }
        ForeignPackage foreignPackage2 = this.mForeignPackage;
        foreignPackage2.getClass();
        KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("maybeReloadNormalKeyguard, UI-Engine change, from ", stringForUser, " to ", foreignPackage2.getPackagename(), TAG);
        ForeignPackage foreignPackage3 = this.mForeignPackage;
        foreignPackage3.getClass();
        HashMap map = foreignPackage3.mTypefaces;
        if (map != null) {
            map.clear();
            foreignPackage3.mTypefaces = null;
        }
        this.mForeignPackage = null;
        return true;
    }
}
