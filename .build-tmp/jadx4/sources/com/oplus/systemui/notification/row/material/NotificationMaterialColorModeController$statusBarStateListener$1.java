package com.oplus.systemui.notification.row.material;

import android.util.Log;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialColorModeController$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ NotificationMaterialColorModeController this$0;

    public final void onStateChanged(int i) {
        NotificationMaterialColorModeController notificationMaterialColorModeController = this.this$0;
        if (((Number) notificationMaterialColorModeController.getStatusBarState().getValue()).intValue() != i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MaterialColorController-->".concat("onStateChanged: newState: " + i + ", last state=" + notificationMaterialColorModeController.getStatusBarState().getValue()));
            }
            notificationMaterialColorModeController._statusBarState.setValue(Integer.valueOf(i));
        }
    }
}
