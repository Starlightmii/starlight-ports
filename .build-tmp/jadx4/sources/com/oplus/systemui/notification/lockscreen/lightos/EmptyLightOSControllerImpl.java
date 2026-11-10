package com.oplus.systemui.notification.lockscreen.lightos;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyLightOSControllerImpl implements CapsuleLightOSController {
    @Override // com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController
    public final void init() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleListOSManager-->EmptyLightOSControllerImpl:Init");
        }
    }
}
