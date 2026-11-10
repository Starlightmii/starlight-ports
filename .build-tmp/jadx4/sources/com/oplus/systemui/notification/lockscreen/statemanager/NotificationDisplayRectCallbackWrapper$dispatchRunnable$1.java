package com.oplus.systemui.notification.lockscreen.statemanager;

import androidx.compose.animation.core.MonoSpline$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDisplayRectCallbackWrapper$dispatchRunnable$1 implements Runnable {
    public /* synthetic */ NotificationDisplayRectCallbackWrapper this$0;

    @Override // java.lang.Runnable
    public final void run() {
        float f = this.this$0.lastTop;
        float f2 = this.this$0.lastBottom;
        int size = this.this$0.callbacks.size();
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("[dispatchDisplayRect] top=", f, ", bottom=", f2, ", callbacks=");
        sbM.append(size);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationDisplayRectCallbackWrapper", sbM.toString(), null, 8);
        NotificationDisplayRectCallbackWrapper notificationDisplayRectCallbackWrapper = this.this$0;
        NotificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0 notificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0 = new NotificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0();
        notificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0.f$0 = f;
        notificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0.f$1 = f2;
        VarHandle.storeStoreFence();
        NotificationDisplayRectCallbackWrapper.access$notifyCallbacks(notificationDisplayRectCallbackWrapper, notificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0);
    }
}
