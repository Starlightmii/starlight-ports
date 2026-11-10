package com.oplus.systemui.keyguard.wallpaper.pictorial.app;

import android.content.Context;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.SecurityController;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplus.systemui.biometrics.finger.KeyguardFingerprintUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialAppInfo implements Comparable {
    public static final String BURIED = "buried";
    public static final Companion Companion = new Companion();
    public static final String ID = "id";
    public static final String STYLE = "style";
    public static final String TAG = "PictorialAppInfo";
    public static final String TAG_APP = "app";
    public static final String TAG_CLEAR = "clear";
    public static final String TAG_ENABLE = "enable";
    public static final String TAG_PKG = "pkg";
    public static final String TIPS = "tips";
    public boolean buried;
    public boolean clearable;
    private Context context;
    public boolean enable;
    public int id;
    private String pkgName;
    public boolean showTipsFeatherSupport = isPictorialTipsFeatherSupport();
    public int style;
    public boolean supportPictorialTipsFromMeta;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PictorialAppInfo(String str, Context context) {
        this.pkgName = str;
        this.context = context;
    }

    @Override // java.lang.Comparable
    public int compareTo(PictorialAppInfo pictorialAppInfo) {
        return this.pkgName.compareTo(pictorialAppInfo.pkgName);
    }

    public final String component1() {
        return this.pkgName;
    }

    public final Context component2() {
        return this.context;
    }

    public final PictorialAppInfo copy(String str, Context context) {
        return new PictorialAppInfo(str, context);
    }

    public final void copyFrom(PictorialAppInfo pictorialAppInfo, boolean z) {
        String str = pictorialAppInfo.pkgName;
        this.pkgName = str;
        if (!z || !"com.heytap.pictorial".equals(str)) {
            this.showTipsFeatherSupport = pictorialAppInfo.showTipsFeatherSupport;
        } else if (!pictorialAppInfo.showTipsFeatherSupport) {
            this.showTipsFeatherSupport = false;
        }
        this.enable = pictorialAppInfo.enable;
        this.clearable = pictorialAppInfo.clearable;
        this.style = pictorialAppInfo.style;
        this.buried = pictorialAppInfo.buried;
        this.id = pictorialAppInfo.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PictorialAppInfo)) {
            return false;
        }
        PictorialAppInfo pictorialAppInfo = (PictorialAppInfo) obj;
        return Intrinsics.areEqual(this.pkgName, pictorialAppInfo.pkgName) && Intrinsics.areEqual(this.context, pictorialAppInfo.context);
    }

    public final boolean getBuried() {
        return this.buried;
    }

    public final boolean getClearable() {
        return this.clearable;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPkgName() {
        return this.pkgName;
    }

    public final boolean getShowTipsFeatherSupport() {
        return this.showTipsFeatherSupport;
    }

    public final int getStyle() {
        return this.style;
    }

    public final boolean getSupportPictorialTipsFromMeta() {
        return this.supportPictorialTipsFromMeta;
    }

    public final int hashCode() {
        return this.context.hashCode() + (this.pkgName.hashCode() * 31);
    }

    public final boolean isPictorialTipsFeatherSupport() {
        boolean z = false;
        if ("com.heytap.pictorial".equals(this.pkgName)) {
            if (!((SecurityController) Dependency.sDependency.getDependencyInner(SecurityController.class)).mDevicePolicyManager.isDeviceManaged() && !((SecurityController) Dependency.sDependency.getDependencyInner(SecurityController.class)).mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile()) {
                return true;
            }
            KgdLogUtil.i(TAG, "isPictorialTipsFeatherSupport false, hasWorkProfile");
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isFlavorOneDevice() && "com.heytap.pictorial".equals(this.pkgName)) {
                KgdLogUtil.i(TAG, "isShowPictorialTipDeviceOther true");
                return true;
            }
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportSidefps() && !FeatureOption.isFoldDevice()) {
            z = true;
        }
        OplusLockIconView$.ExternalSyntheticOutline0.m("isShowPictorialTipDeviceOther result=", TAG, z);
        return z;
    }

    public final boolean isShowPictorialTipDevice() {
        if (!"com.heytap.pictorial".equals(this.pkgName)) {
            return true;
        }
        if (FingerSwitchHelper.Companion.getInstance(this.context).isFingerprintUnlockEnable()) {
            float dimension = this.context.getResources().getDimension(2131171404);
            KeyguardFingerprintUtils keyguardFingerprintUtils = KeyguardFingerprintUtils.INSTANCE;
            if (keyguardFingerprintUtils.getIconMarginBottom() < (keyguardFingerprintUtils.getPressedIconSize() / 2.0f) + dimension) {
                OplusLockIconView$.ExternalSyntheticOutline0.m(keyguardFingerprintUtils.getIconMarginBottom(), "isShowPictorialTipDevice false, bottomMargin=", TAG);
                return false;
            }
        }
        if (this.supportPictorialTipsFromMeta) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(KeyguardFingerprintUtils.INSTANCE.getIconMarginBottom(), "isShowPictorialTipDevice true,bottomMargin=", TAG);
            return true;
        }
        KgdLogUtil.i(TAG, "isShowPictorialTipDevice false, supportPictorialTipsFromMeta false");
        return false;
    }

    public final void setBuried(boolean z) {
        this.buried = z;
    }

    public final void setClearable(boolean z) {
        this.clearable = z;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final void setPkgName(String str) {
        this.pkgName = str;
    }

    public final void setShowTipsFeatherSupport(boolean z) {
        this.showTipsFeatherSupport = z;
    }

    public final void setStyle(int i) {
        this.style = i;
    }

    public final void setSupportPictorialTipsFromMeta(boolean z) {
        this.supportPictorialTipsFromMeta = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" package = " + this.pkgName);
        sb.append("\n");
        sb.append(" showTipsFeatherSupport = " + this.showTipsFeatherSupport);
        sb.append("\n");
        sb.append(" enable = " + this.enable);
        sb.append("\n");
        sb.append(" clearable = " + this.clearable);
        sb.append("\n");
        sb.append(" style = " + this.style);
        sb.append("\n");
        sb.append(" isbuired = " + this.buried);
        sb.append("\n");
        sb.append(" AppId = " + this.id);
        sb.append("\n");
        return sb.toString();
    }
}
