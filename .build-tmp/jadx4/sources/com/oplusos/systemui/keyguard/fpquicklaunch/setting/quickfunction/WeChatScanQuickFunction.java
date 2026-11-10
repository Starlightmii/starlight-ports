package com.oplusos.systemui.keyguard.fpquicklaunch.setting.quickfunction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.setting.quickfunction.AbsQuickFunction;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.utils.FpQLHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WeChatScanQuickFunction extends AbsQuickFunction {
    public static final Companion Companion = new Companion();
    public static final String QUICK_FUNCTION_FAKE_CLASSNAME_WECHAT_SCAN = "qf.fake.className.fp_ql_setting_wechat_scan";
    private static final String WECHAT_EXTRA_KEY = "LauncherUI.From.Scaner.Shortcut";
    private static final String WECHAT_SCAN_ACTION = "com.tencent.mm.action.BIZSHORTCUT";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public WeChatScanQuickFunction(Context context) {
        super(context);
    }

    public Intent getHandleStartIntent() {
        Intent intent = new Intent();
        intent.setAction(WECHAT_SCAN_ACTION);
        intent.addFlags(335544320);
        intent.putExtra(WECHAT_EXTRA_KEY, true);
        return intent;
    }

    public String getQFClassName() {
        return QUICK_FUNCTION_FAKE_CLASSNAME_WECHAT_SCAN;
    }

    public ComponentName getQFComponentName() {
        return new ComponentName("com.tencent.mm", QUICK_FUNCTION_FAKE_CLASSNAME_WECHAT_SCAN);
    }

    public Drawable getQFIcon() {
        FpQLHelper.Companion companion = FpQLHelper.Companion;
        Context mContext = getMContext();
        mContext.getClass();
        return companion.getInstance(mContext).getUxIconDrawable(getQFPkgName(), 2131233565, false);
    }

    public String getQFPkgName() {
        return "com.tencent.mm";
    }

    public String getQFTitle() {
        Context mContext = getMContext();
        if (mContext != null) {
            return mContext.getString(2131953313);
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
