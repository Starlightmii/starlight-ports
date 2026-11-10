package com.oplus.systemui.notification.icdf;

import android.util.Log;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfHeadsUpDecisionRecorder {
    public static final Companion Companion = new Companion();
    private static final String TAG = "IcdfHeadsUpRecorder";
    private final IcdfHeadsUpDecisionCache cache;
    private final KeyguardStateController keyguardStateController;
    private final StatusBarStateController statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public IcdfHeadsUpDecisionRecorder(IcdfHeadsUpDecisionCache icdfHeadsUpDecisionCache, StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController) {
        this.cache = icdfHeadsUpDecisionCache;
        this.statusBarStateController = statusBarStateController;
        this.keyguardStateController = keyguardStateController;
    }

    public final void clearAll() {
        this.cache.clear();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "IcdfHeadsUpRecorder-->clearAll peek decision cache");
        }
    }

    public final void recordIfAwake(boolean z, NotificationEntry notificationEntry) {
        NotificationFeatureOption.INSTANCE.getClass();
        if (!NotificationFeatureOption.isPcSynergyEnable || this.statusBarStateController.isDozing() || this.statusBarStateController.getState() == 1 || this.keyguardStateController.mShowing || this.statusBarStateController.getState() != 0) {
            return;
        }
        this.cache.put(notificationEntry.getKey(), z);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "IcdfHeadsUpRecorder-->".concat("record phone peek decision=" + z + " for " + NotificationUtilsKt.getLogKeyNonNull(notificationEntry)));
        }
    }
}
