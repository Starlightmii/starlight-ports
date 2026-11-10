package com.oplus.systemui.keyguard.wallpaper.pictorial.app;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialRomUpdateUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialAppDataImpl extends PictorialAppData {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Context mContext;
    public final PictorialAppInfo mCurrentPictorialAppInfo;
    public final int mCurrentUser;
    public final PictorialAppInfo mDefaultPictorialAppInfo;
    public boolean mIsSupportTaglineMessageView;
    public volatile boolean mIsSupportWidgetTaglineMessageView;
    public final boolean mIsVersionChanged;
    public final boolean mMainSwitchOn;
    public PictorialAppInfo mNextPictorialAppInfo;
    public final PictorialAppInfo mOplusGLPictorialAppInfo;
    public boolean mPictorialExist;
    public int mPreviousPictorialID;

    /* JADX WARN: Code duplicated, block: B:53:0x012c  */
    public PictorialAppDataImpl(Context context, boolean z) {
        boolean z2;
        int intForUser;
        int i = 0;
        this.mPreviousPictorialID = 0;
        this.mIsVersionChanged = false;
        this.mIsSupportTaglineMessageView = false;
        this.mMainSwitchOn = false;
        this.mIsSupportWidgetTaglineMessageView = false;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPreviousPictorialID = getIdFromSettings();
        FlavorOneFeatureOption.INSTANCE.getClass();
        int i2 = 2;
        int i3 = 1;
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            KeyguardFeatureOption keyguardFeatureOption = KeyguardFeatureOption.INSTANCE;
            keyguardFeatureOption.getClass();
            if (!KeyguardFeatureOption.isSupportOtherPictorial) {
                if (PictorialAppData.GLANCE_INVALID_REGION_MARK.contains(keyguardFeatureOption.getRmRegionName())) {
                    if (this.mPreviousPictorialID != 2) {
                        PictorialAppDataImpl$$ExternalSyntheticLambda0 pictorialAppDataImpl$$ExternalSyntheticLambda0 = new PictorialAppDataImpl$$ExternalSyntheticLambda0(i3);
                        pictorialAppDataImpl$$ExternalSyntheticLambda0.f$0 = this;
                        VarHandle.storeStoreFence();
                        KeyguardThreadUtil.runOnWorkThread(pictorialAppDataImpl$$ExternalSyntheticLambda0);
                    } else {
                        if (Settings.Secure.getIntForUser(applicationContext.getContentResolver(), "user_setup_complete", 0, applicationContext.getUserId()) != 0) {
                            StringBuilder sb = PictorialUtils.sStringBuilder;
                            try {
                                intForUser = Settings.System.getIntForUser(applicationContext.getContentResolver(), "oplus_customize_pictorial_enable_play", KeyguardUpdateMonitor.getCurrentUser());
                            } catch (Settings.SettingNotFoundException unused) {
                                KgdLogUtil.w("PictorialUtils", "getSwitchStatusFromSettings switch = oplus_customize_pictorial_enable_play  SettingNotFoundException return = -1");
                                intForUser = -1;
                            }
                            Settings.System.putIntForUser(this.mContext.getContentResolver(), "oplus_customize_pictorial_apply", intForUser, this.mCurrentUser);
                        }
                        PictorialAppDataImpl$$ExternalSyntheticLambda0 pictorialAppDataImpl$$ExternalSyntheticLambda1 = new PictorialAppDataImpl$$ExternalSyntheticLambda0(i2);
                        pictorialAppDataImpl$$ExternalSyntheticLambda1.f$0 = this;
                        VarHandle.storeStoreFence();
                        KeyguardThreadUtil.runOnWorkThread(pictorialAppDataImpl$$ExternalSyntheticLambda1);
                    }
                }
            }
        }
        KeyguardFeatureOption keyguardFeatureOption2 = KeyguardFeatureOption.INSTANCE;
        keyguardFeatureOption2.getClass();
        if (KeyguardFeatureOption.isSupportOtherPictorial) {
            int otherPictorialAvailable = getOtherPictorialAvailable();
            String otherPictorialName = keyguardFeatureOption2.getOtherPictorialName();
            if (!TextUtils.isEmpty(otherPictorialName)) {
                StringBuilder sb2 = PictorialUtils.sStringBuilder;
                if (KeyguardUtils.hasPackage(context, otherPictorialName) && (otherPictorialAvailable == 1 || otherPictorialAvailable == -1)) {
                    PictorialAppInfo pictorialAppInfo = new PictorialAppInfo(otherPictorialName, context);
                    this.mCurrentPictorialAppInfo = pictorialAppInfo;
                    int defaultSetting = getDefaultSetting();
                    if (pictorialAppInfo.getPkgName().equals("com.haokan.pictorial")) {
                        if (defaultSetting == 0) {
                            putSourceToSetting(1);
                        } else {
                            int i4 = this.mPreviousPictorialID;
                            if (i4 == 0) {
                                putSourceToSetting(2);
                            } else if (i4 == 1) {
                                putSourceToSetting(0);
                            }
                        }
                    }
                }
            }
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                this.mDefaultPictorialAppInfo = new PictorialAppInfo("com.heytap.pictorial", context);
                this.mOplusGLPictorialAppInfo = new PictorialAppInfo("com.glance.lockscreenOp", context);
                this.mMainSwitchOn = z;
                if (KeyguardFeatureOption.isKeepHeyTapPictorial && z) {
                    this.mCurrentPictorialAppInfo = null;
                }
            }
            if (this.mCurrentPictorialAppInfo == null) {
                this.mCurrentPictorialAppInfo = new PictorialAppInfo("com.heytap.pictorial", context);
            }
        } else {
            this.mCurrentPictorialAppInfo = new PictorialAppInfo("com.heytap.pictorial", context);
        }
        if (KeyguardFeatureOption.isPictorialDisabled) {
            z2 = false;
        } else {
            String pkgName = this.mCurrentPictorialAppInfo.getPkgName();
            StringBuilder sb3 = PictorialUtils.sStringBuilder;
            if (KeyguardUtils.hasPackage(context, pkgName)) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        this.mPictorialExist = z2;
        this.mCurrentUser = context.getUserId();
        this.mNextPictorialAppInfo = null;
        if (KeyguardFeatureOption.isSupportOtherPictorial) {
            updateRomConfig(PictorialRomUpdateUtils.loadLocalConfiguration(context), this.mCurrentPictorialAppInfo);
            boolean z3 = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "user_setup_complete", 0, this.mContext.getUserId()) != 0;
            KgdLogUtil.d("Glance_Nudge", "checkVersion isUserSetupCompleted " + z3);
            if (!z3) {
                this.mIsVersionChanged = true;
            } else if (((UserManager) this.mContext.getSystemService("user")).isSystemUser()) {
                SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("OTA_VERSION", 0);
                String string = sharedPreferences.getString("glance_ota", "");
                boolean z4 = TextUtils.isEmpty(string) || !Build.DISPLAY.equals(string);
                OplusLockIconView$.ExternalSyntheticOutline0.m("checkVersion ota update ", "Glance_Nudge", z4);
                if (z4) {
                    this.mIsVersionChanged = true;
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString("glance_ota", Build.DISPLAY);
                    editorEdit.apply();
                }
            }
        }
        putIdToSettings(this.mCurrentPictorialAppInfo.getId());
        keyguardFeatureOption2.getClass();
        if (KeyguardFeatureOption.isSupportOtherPictorial) {
            PictorialAppDataImpl$$ExternalSyntheticLambda0 pictorialAppDataImpl$$ExternalSyntheticLambda2 = new PictorialAppDataImpl$$ExternalSyntheticLambda0(i);
            pictorialAppDataImpl$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(pictorialAppDataImpl$$ExternalSyntheticLambda2);
        }
    }

    private int getDefaultSetting() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), "pictorial_keyguard_decouping_version", 0, this.mContext.getUserId());
    }

    private int getIdFromSettings() {
        try {
            return Settings.System.getIntForUser(this.mContext.getContentResolver(), "other_pictorial_support", this.mCurrentUser);
        } catch (Settings.SettingNotFoundException unused) {
            KgdLogUtil.w("PictorialAppData", "getIdFromSettings switch error");
            return -1;
        }
    }

    private int getOtherPictorialAvailable() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), "oplus_customize_other_pictorial_available", -1, this.mCurrentUser);
    }

    public static boolean isSamePackage(PictorialAppInfo pictorialAppInfo, PictorialAppInfo pictorialAppInfo2) {
        if (pictorialAppInfo == null || pictorialAppInfo2 == null) {
            return false;
        }
        return TextUtils.equals(pictorialAppInfo.getPkgName(), pictorialAppInfo2.getPkgName());
    }

    private void updateRomConfig(List<PictorialAppInfo> list, PictorialAppInfo pictorialAppInfo) {
        if (list == null || list.size() <= 0) {
            return;
        }
        PictorialAppInfo pictorialAppInfo2 = null;
        PictorialAppInfo pictorialAppInfo3 = null;
        for (PictorialAppInfo pictorialAppInfo4 : list) {
            if (pictorialAppInfo4.getEnable()) {
                pictorialAppInfo3 = pictorialAppInfo4;
            }
            if (TextUtils.equals(pictorialAppInfo.getPkgName(), pictorialAppInfo4.getPkgName())) {
                pictorialAppInfo.copyFrom(pictorialAppInfo4, true);
                pictorialAppInfo.setEnable(true);
            }
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                updateDefGLPictorialAppInfo(pictorialAppInfo4);
            }
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (!KeyguardFeatureOption.isSupportOplusGLPictorial || pictorialAppInfo3 == null) {
            pictorialAppInfo2 = pictorialAppInfo3;
        } else {
            StringBuilder sb = new StringBuilder("updateRomConfigForRus mDef: ");
            PictorialAppInfo pictorialAppInfo5 = this.mDefaultPictorialAppInfo;
            sb.append(pictorialAppInfo5);
            sb.append(" ,mGLinfo: ");
            PictorialAppInfo pictorialAppInfo6 = this.mOplusGLPictorialAppInfo;
            sb.append(pictorialAppInfo6);
            KgdLogUtil.i("PictorialAppData", sb.toString());
            if ("com.heytap.pictorial".equals(pictorialAppInfo3.getPkgName())) {
                pictorialAppInfo5.setEnable(true);
                pictorialAppInfo6.setEnable(false);
            } else if ("com.glance.lockscreenOp".equals(pictorialAppInfo3.getPkgName())) {
                pictorialAppInfo6.setEnable(true);
                if (!KeyguardFeatureOption.isKeepHeyTapPictorial || !this.mMainSwitchOn) {
                }
            }
            pictorialAppInfo2 = pictorialAppInfo3;
        }
        if (pictorialAppInfo2 != null && !isSamePackage(pictorialAppInfo, pictorialAppInfo2)) {
            Context context = this.mContext;
            String pkgName = pictorialAppInfo2.getPkgName();
            StringBuilder sb2 = PictorialUtils.sStringBuilder;
            if (KeyguardUtils.hasPackage(context, pkgName)) {
                pictorialAppInfo.copyFrom(pictorialAppInfo2, true);
            }
        }
        if (pictorialAppInfo.getPkgName().equals("com.heytap.pictorial")) {
            pictorialAppInfo.setShowTipsFeatherSupport(pictorialAppInfo.isPictorialTipsFeatherSupport());
        }
    }

    public final void clearPictorialApp(PictorialAppInfo pictorialAppInfo) {
        if (pictorialAppInfo == null) {
            return;
        }
        try {
            ActivityManager.getService().clearApplicationUserData(pictorialAppInfo.getPkgName(), false, new 1(this), this.mCurrentUser);
        } catch (Exception e) {
            KgdLogUtil.i("PictorialAppData", e.getMessage());
        }
    }

    public final void clearPictorialAppForPgName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Context context = this.mContext;
        StringBuilder sb = PictorialUtils.sStringBuilder;
        if (KeyguardUtils.hasPackage(context, str)) {
            try {
                ActivityManager.getService().clearApplicationUserData(str, false, new 2(this), this.mCurrentUser);
            } catch (Exception e) {
                KgdLogUtil.i("PictorialAppData", "clearPictorialAppForPgName error：" + e.getMessage());
            }
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData
    public PictorialAppInfo getDefPictorialAppInfo() {
        return this.mDefaultPictorialAppInfo;
    }

    public PictorialAppInfo getNextPictorialAppInfo() {
        return this.mNextPictorialAppInfo;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData
    public PictorialAppInfo getOplusGLPictorialAppInfo() {
        return this.mOplusGLPictorialAppInfo;
    }

    public PictorialAppInfo getPictorialAppInfo() {
        return this.mCurrentPictorialAppInfo;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData
    public String getPictorialAppPackage() {
        return this.mCurrentPictorialAppInfo.getPkgName();
    }

    public int getPictorialStyle() {
        return this.mCurrentPictorialAppInfo.getStyle();
    }

    public final void putIdToSettings(int i) {
        if (this.mPreviousPictorialID != i) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "other_pictorial_support", i, this.mCurrentUser);
            this.mPreviousPictorialID = i;
        }
        Settings.System.putString(this.mContext.getContentResolver(), "pictorial_region", "persist.sys.oplus.region");
    }

    public final void putSourceToSetting(int i) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        int i2 = this.mCurrentUser;
        if (Settings.System.getIntForUser(contentResolver, "other_pictorial_support", -1, i2) == -1 || this.mCurrentPictorialAppInfo.getPkgName() != "com.haokan.pictorial") {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "pictorial_update_source", i, i2);
        }
    }

    public final void setDevicesSupportTaglineState(int i, Context context) {
        if (context == null) {
            return;
        }
        PictorialAppDataImpl$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new PictorialAppDataImpl$.ExternalSyntheticLambda5();
        externalSyntheticLambda5.f$0 = this;
        externalSyntheticLambda5.f$1 = context;
        externalSyntheticLambda5.f$2 = i;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(externalSyntheticLambda5);
    }

    public void setSupportPictorialTipsFromMeta(boolean z) {
        this.mCurrentPictorialAppInfo.setSupportPictorialTipsFromMeta(z);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0036  */
    public final boolean trySwitchPictorialApp() {
        boolean z;
        PictorialAppInfo pictorialAppInfo = this.mNextPictorialAppInfo;
        if (pictorialAppInfo != null) {
            PictorialAppInfo pictorialAppInfo2 = this.mCurrentPictorialAppInfo;
            if (!isSamePackage(pictorialAppInfo, pictorialAppInfo2)) {
                if (pictorialAppInfo2.getClearable()) {
                    clearPictorialApp(pictorialAppInfo2);
                }
                pictorialAppInfo2.copyFrom(this.mNextPictorialAppInfo, false);
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isPictorialDisabled) {
                    z = false;
                } else {
                    Context context = this.mContext;
                    String pkgName = pictorialAppInfo2.getPkgName();
                    StringBuilder sb = PictorialUtils.sStringBuilder;
                    if (KeyguardUtils.hasPackage(context, pkgName)) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                this.mPictorialExist = z;
                pictorialAppInfo2.setClearable(false);
                this.mNextPictorialAppInfo = null;
                putIdToSettings(pictorialAppInfo2.getId());
                return true;
            }
            this.mNextPictorialAppInfo = null;
        }
        return false;
    }

    public final void updateCurrentPictorialToLH() {
        PictorialAppInfo pictorialAppInfo = new PictorialAppInfo("com.heytap.pictorial", this.mContext);
        updateRomConfig(PictorialRomUpdateUtils.loadLocalConfiguration(this.mContext), pictorialAppInfo);
        this.mCurrentPictorialAppInfo.setClearable(true);
        this.mNextPictorialAppInfo = pictorialAppInfo;
        PictorialAppDataImpl$$ExternalSyntheticLambda0 pictorialAppDataImpl$$ExternalSyntheticLambda0 = new PictorialAppDataImpl$$ExternalSyntheticLambda0(3);
        pictorialAppDataImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        KeyguardThreadUtil.runOnWorkThread(pictorialAppDataImpl$$ExternalSyntheticLambda0);
    }

    public final void updateDefGLPictorialAppInfo(PictorialAppInfo pictorialAppInfo) {
        if ("com.heytap.pictorial".equals(pictorialAppInfo.getPkgName())) {
            PictorialAppInfo pictorialAppInfo2 = this.mDefaultPictorialAppInfo;
            pictorialAppInfo2.copyFrom(pictorialAppInfo, false);
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isKeepHeyTapPictorial) {
                pictorialAppInfo2.setEnable(true);
            } else {
                pictorialAppInfo2.setEnable(false);
            }
        }
        if ("com.glance.lockscreenOp".equals(pictorialAppInfo.getPkgName())) {
            PictorialAppInfo pictorialAppInfo3 = this.mOplusGLPictorialAppInfo;
            pictorialAppInfo3.copyFrom(pictorialAppInfo, false);
            pictorialAppInfo3.setEnable(true);
        }
    }

    public void updateRomConfigForRus(List<PictorialAppInfo> list, boolean z) {
        PictorialAppInfo pictorialAppInfo;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<PictorialAppInfo> it = list.iterator();
        PictorialAppInfo pictorialAppInfo2 = null;
        while (true) {
            boolean zHasNext = it.hasNext();
            pictorialAppInfo = this.mCurrentPictorialAppInfo;
            if (!zHasNext) {
                break;
            }
            PictorialAppInfo next = it.next();
            if (next.getEnable()) {
                pictorialAppInfo2 = next;
            }
            if (TextUtils.equals(pictorialAppInfo.getPkgName(), next.getPkgName())) {
                pictorialAppInfo.copyFrom(next, true);
                pictorialAppInfo.setEnable(true);
            }
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                updateDefGLPictorialAppInfo(next);
            }
        }
        if (pictorialAppInfo2 != null && !isSamePackage(pictorialAppInfo, pictorialAppInfo2)) {
            Context context = this.mContext;
            String pkgName = pictorialAppInfo2.getPkgName();
            StringBuilder sb = PictorialUtils.sStringBuilder;
            if (KeyguardUtils.hasPackage(context, pkgName)) {
                this.mNextPictorialAppInfo = pictorialAppInfo2;
            }
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial && pictorialAppInfo2 != null) {
            boolean zEquals = "com.heytap.pictorial".equals(pictorialAppInfo2.getPkgName());
            PictorialAppInfo pictorialAppInfo3 = this.mOplusGLPictorialAppInfo;
            if (zEquals) {
                this.mDefaultPictorialAppInfo.setEnable(true);
                pictorialAppInfo3.setEnable(false);
            } else if ("com.glance.lockscreenOp".equals(pictorialAppInfo2.getPkgName())) {
                pictorialAppInfo3.setEnable(true);
            }
            if (KeyguardFeatureOption.isKeepHeyTapPictorial && z && this.mNextPictorialAppInfo != null) {
                this.mNextPictorialAppInfo = null;
            }
        }
        if (this.mNextPictorialAppInfo == null) {
            pictorialAppInfo.setClearable(false);
            return;
        }
        KgdLogUtil.i("PictorialAppData", "will switch app from " + pictorialAppInfo.getPkgName() + " to " + this.mNextPictorialAppInfo.getPkgName());
    }

    public void updateRomConfigForRus(List<PictorialAppInfo> list) {
        updateRomConfigForRus(list, false);
    }

    public PictorialAppDataImpl(Context context) {
        this(context, false);
    }
}
