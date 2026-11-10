package com.oplusos.systemui.keyguard.fpquicklaunch.setting.quickfunction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.setting.quickfunction.AbsQuickFunction;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.utils.FpQLHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AlipayScanQuickFunction extends AbsQuickFunction {
    private static final String ALIPAY_SCAN_DEEPLINK = "alipays://platformapi/startapp?appId=10000007&chInfo=ch_oppoSide";
    public static final Companion Companion = new Companion();
    public static final String QUICK_FUNCTION_FAKE_CLASSNAME_ALIPAY_SCAN = "qf.fake.className.fp_ql_setting_alipay_scan";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AlipayScanQuickFunction(Context context) {
        super(context);
    }

    public Intent getHandleStartIntent() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(ALIPAY_SCAN_DEEPLINK));
        intent.setPackage("com.eg.android.AlipayGphone");
        intent.addFlags(270532608);
        return intent;
    }

    public String getQFClassName() {
        return QUICK_FUNCTION_FAKE_CLASSNAME_ALIPAY_SCAN;
    }

    public ComponentName getQFComponentName() {
        return new ComponentName("com.eg.android.AlipayGphone", QUICK_FUNCTION_FAKE_CLASSNAME_ALIPAY_SCAN);
    }

    public Drawable getQFIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getUxIconDrawable(getQFPkgName(), 2131233558, false);
    }

    public String getQFPkgName() {
        return "com.eg.android.AlipayGphone";
    }

    public String getQFTitle() {
        Context mContext = getMContext();
        if (mContext != null) {
            return mContext.getString(2131953250);
        }
        return null;
    }

    public Drawable getQkFAppIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getAppIcon("com.eg.android.AlipayGphone");
    }
}
