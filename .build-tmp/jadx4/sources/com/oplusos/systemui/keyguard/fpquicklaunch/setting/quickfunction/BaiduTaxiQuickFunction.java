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
public final class BaiduTaxiQuickFunction extends AbsQuickFunction {
    public static final Companion Companion = new Companion();
    private static final String DEEPLINK = "baidumap://map/component?popRoot=no&needLocation=yes&needCloud=yes&comName=rentcar&target=rentcar_entrance&mode=CLEAN_MODE&param={\"src_from\":\"oppo\"}&src=oppo.shortcuts";
    public static final String QUICK_FUNCTION_FAKE_CLASSNAME = "qf.fake.className.fp_ql_setting_baidu_taxi";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BaiduTaxiQuickFunction(Context context) {
        super(context);
    }

    public Intent getHandleStartIntent() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(DEEPLINK));
        intent.setPackage("com.baidu.BaiduMap");
        intent.addFlags(270532608);
        return intent;
    }

    public String getQFClassName() {
        return QUICK_FUNCTION_FAKE_CLASSNAME;
    }

    public ComponentName getQFComponentName() {
        return new ComponentName("com.baidu.BaiduMap", QUICK_FUNCTION_FAKE_CLASSNAME);
    }

    public Drawable getQFIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getUxIconDrawable(getQFPkgName(), 2131233556, false);
    }

    public String getQFPkgName() {
        return "com.baidu.BaiduMap";
    }

    public String getQFTitle() {
        Context mContext = getMContext();
        if (mContext != null) {
            return mContext.getString(2131953254);
        }
        return null;
    }

    public Drawable getQkFAppIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getAppIcon("com.baidu.BaiduMap");
    }
}
