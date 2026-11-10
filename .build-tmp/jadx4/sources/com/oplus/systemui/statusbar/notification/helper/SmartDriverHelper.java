package com.oplus.systemui.statusbar.notification.helper;

import android.app.OplusNotificationManager;
import android.content.Context;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmartDriverHelper {
    public Context mContext;
    public OplusNotificationManager mManager;
    public String mSmartDriverPkg;

    public final boolean getSmartDriverBannerBlocked(String str) {
        if (this.mContext.getString(2131956480).equals(str)) {
            return false;
        }
        try {
            if (this.mManager == null) {
                this.mManager = new OplusNotificationManager();
            }
            return this.mManager.isSuppressedByDriveMode(-1);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, "Notification--SmartDriverHelper", "Notification--getSmartDriverBannerBlocked--" + e.getMessage(), e);
            return false;
        }
    }

    public final boolean isNavigationMode() {
        try {
            if (this.mManager == null) {
                this.mManager = new OplusNotificationManager();
            }
            return this.mManager.isNavigationMode(-1);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, "Notification--SmartDriverHelper", "Notification--isNavigationMode--" + e.getMessage(), e);
            return false;
        }
    }
}
