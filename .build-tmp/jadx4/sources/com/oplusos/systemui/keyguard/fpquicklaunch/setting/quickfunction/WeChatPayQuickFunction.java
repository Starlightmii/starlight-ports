package com.oplusos.systemui.keyguard.fpquicklaunch.setting.quickfunction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.setting.quickfunction.AbsQuickFunction;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.utils.FpQLHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WeChatPayQuickFunction extends AbsQuickFunction {
    public static final Companion Companion = new Companion();
    private static final String EXTRA_CHANNEL = "extra_channel";
    private static final String EXTRA_PKG_NAME = "extra_pkg_name";
    public static final String QUICK_FUNCTION_FAKE_CLASSNAME_WECHAT_PAY = "qf.fake.className.wechat_pay";
    private static final String WECHAT_CHANNEL = "wxpay";
    private static final String WECHAT_PAYCODE_ACTION = "com.nearme.pay.qrcode";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public WeChatPayQuickFunction(Context context) {
        super(context);
    }

    public Intent getHandleStartIntent() {
        Intent intent = new Intent(WECHAT_PAYCODE_ACTION);
        intent.putExtra(EXTRA_CHANNEL, WECHAT_CHANNEL);
        Context mContext = getMContext();
        mContext.getClass();
        intent.putExtra(EXTRA_PKG_NAME, mContext.getPackageName());
        intent.addFlags(268435456);
        return intent;
    }

    public String getQFClassName() {
        return QUICK_FUNCTION_FAKE_CLASSNAME_WECHAT_PAY;
    }

    public ComponentName getQFComponentName() {
        return new ComponentName("com.tencent.mm", QUICK_FUNCTION_FAKE_CLASSNAME_WECHAT_PAY);
    }

    public Drawable getQFIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getUxIconDrawable(getQFPkgName(), 2131233564, false);
    }

    public String getQFPkgName() {
        return "com.tencent.mm";
    }

    public String getQFTitle() {
        Context mContext = getMContext();
        if (mContext != null) {
            return mContext.getString(2131953312);
        }
        return null;
    }

    public Drawable getQkFAppIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getAppIcon("com.tencent.mm");
    }
}
