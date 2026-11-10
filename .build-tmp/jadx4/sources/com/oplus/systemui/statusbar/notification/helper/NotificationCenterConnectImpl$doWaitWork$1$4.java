package com.oplus.systemui.statusbar.notification.helper;

import android.util.Log;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCenterConnectImpl$doWaitWork$1$4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object this$0;

    public /* synthetic */ NotificationCenterConnectImpl$doWaitWork$1$4(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        switch (this.$r8$classId) {
            case 0:
                NotificationCenterConnectImpl.access$doWaitWork((NotificationCenterConnectImpl) this.this$0);
                break;
            case 1:
                NotificationCenterConnectImpl.access$unBind((NotificationCenterConnectImpl) this.this$0);
                if (!((NotificationCenterConnectImpl) this.this$0).waitQueue.isEmpty()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationCenterConnect-->binderDied need rebind;");
                    }
                    NotificationCenterConnectImpl.access$bindInHandler((NotificationCenterConnectImpl) this.this$0);
                }
                break;
            case 2:
                NotificationCenterConnectImpl.access$doWaitWork((NotificationCenterConnectImpl) this.this$0);
                break;
            case 3:
                NotificationCenterConnectImpl.access$bind((NotificationCenterConnectImpl) this.this$0);
                break;
            case 4:
                NotificationCenterConnectImpl.access$unBind((NotificationCenterConnectImpl) this.this$0);
                break;
            default:
                try {
                    ((Runnable) this.this$0).run();
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    MotionLayout$.ExternalSyntheticOutline0.m$1("runOnWorkThread: fail,", th2.getMessage(), "NotificationCenterConnect");
                }
                break;
        }
    }
}
