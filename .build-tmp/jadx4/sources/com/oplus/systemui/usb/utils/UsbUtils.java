package com.oplus.systemui.usb.utils;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class UsbUtils {
    public static String getCustomUSBPolicy() {
        try {
            return OplusDevicepolicyManager.getInstance().getData("persist.sys.usb_transfer_policy", 1);
        } catch (Exception unused) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "UsbSelection_Utils", "no class named OplusDevicepolicyManager");
            return null;
        }
    }

    public static boolean isDisableUSBDialog() {
        boolean z = false;
        try {
            z = OplusDevicepolicyManager.getInstance().getBoolean("persist.sys.disable_usb_dialog_and_enable_adb", 1, false);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "UsbSelection_Utils", "isDisableUSBDialog = " + z);
            return z;
        } catch (Exception unused) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "UsbSelection_Utils", "no class named OplusDevicepolicyManager");
            return z;
        }
    }
}
