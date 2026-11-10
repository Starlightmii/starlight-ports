package com.oplusos.systemui.common.multiuser;

import android.os.Handler;
import android.os.Trace;
import com.oplusos.systemui.common.util.CommonLog;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiUserContentHelper$Companion$LISTENER$1 implements IMultiUserListener {
    @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
    public final void onSwitched(int i) {
        MultiUserContentHelper.Companion.getClass();
        Trace.traceBegin(8L, "MultiUserContentHelper.sync");
        CommonLog.Companion.normal("MultiUserContentHelper", "updateSync");
        synchronized (MultiUserContentHelper.sLock) {
            try {
                MultiUserContentHelper.sTraversing = true;
                Handler handler = new Handler();
                for (MultiUserContentHolder multiUserContentHolder : MultiUserContentHelper.HOLDERS_REGISTERED) {
                    MultiUserContentHelper$Companion$updateSync$1$1$1 multiUserContentHelper$Companion$updateSync$1$1$1 = new MultiUserContentHelper$Companion$updateSync$1$1$1();
                    multiUserContentHelper$Companion$updateSync$1$1$1.$tmp0 = multiUserContentHolder;
                    VarHandle.storeStoreFence();
                    handler.post(multiUserContentHelper$Companion$updateSync$1$1$1);
                }
                MultiUserContentHelper.HOLDERS_REGISTERED.removeAll(MultiUserContentHelper.HOLDERS_PENDING_DELETE);
                MultiUserContentHelper.HOLDERS_REGISTERED.addAll(MultiUserContentHelper.HOLDERS_PENDING_ADD);
                MultiUserContentHelper.HOLDERS_PENDING_DELETE.clear();
                MultiUserContentHelper.HOLDERS_PENDING_ADD.clear();
                MultiUserContentHelper.sTraversing = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        Trace.traceEnd(8L);
    }
}
