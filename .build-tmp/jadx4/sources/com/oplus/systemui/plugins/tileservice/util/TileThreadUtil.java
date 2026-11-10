package com.oplus.systemui.plugins.tileservice.util;

import android.os.Handler;
import com.oplus.dispatch.DispatchQueue;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplus.dispatch.QueueManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileThreadUtil {
    public static final OCDHandler sWorkHandler;
    public static final OCDMessageQueue sWorkThread;

    static {
        OCDMessageQueue oCDMessageQueueMessageQueueCreate = QueueManager.messageQueueCreate("TileWorkerThread", QueueAttr.QUEUE_SERIAL_TIME, (DispatchQueue) null);
        sWorkThread = oCDMessageQueueMessageQueueCreate;
        sWorkHandler = new OCDHandler(oCDMessageQueueMessageQueueCreate);
    }

    public static Handler getWorkHandler() {
        return sWorkHandler;
    }

    public static void runOnWorkThread(Runnable runnable) {
        if (OCDMessageQueue.getCurrentThreadOCDMessageQueue() == sWorkThread) {
            runnable.run();
        } else {
            sWorkHandler.post(runnable);
        }
    }
}
