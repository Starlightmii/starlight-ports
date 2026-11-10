package com.oplus.systemui.notification.material;

import android.util.Log;
import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightDelegate$uiModeListener$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationSpotLightDelegate this$0;

    public /* synthetic */ NotificationSpotLightDelegate$uiModeListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationSpotLightDelegate-->[NotifCard-SpotLight] uiMode change");
                }
                this.this$0.applySpotLightParamsForCurrentUiMode();
                View view = this.this$0.sessionHost;
                if (view != null) {
                    view.invalidate();
                }
                break;
            default:
                NotificationSpotLightDelegate.access$syncEnabledState(this.this$0);
                break;
        }
    }
}
