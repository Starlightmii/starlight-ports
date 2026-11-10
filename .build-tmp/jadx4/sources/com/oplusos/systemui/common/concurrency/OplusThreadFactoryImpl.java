package com.oplusos.systemui.common.concurrency;

import android.os.HandlerThread;
import android.util.Log;
import com.oplus.dispatch.DispatchQueue;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplus.dispatch.QueueManager;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusThreadFactoryImpl implements OplusThreadFactory {
    public static final OplusThreadFactoryImpl INSTANCE = new OplusThreadFactoryImpl();
    private static final String TAG = "OplusThreadFactory";

    @Override // com.oplusos.systemui.common.concurrency.OplusThreadFactory
    public HandlerThread buildNewBgThread(String str) {
        return buildNewThread(str, 10);
    }

    @Override // com.oplusos.systemui.common.concurrency.OplusThreadFactory
    public HandlerThread buildNewThread(String str, int i) {
        Log.d("OplusThreadFactory", "buildNewThread " + str + " with priority=" + i);
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        if (i == 0) {
            handlerThread.getLooper().setSlowLogThresholdMs(500L, 500L);
        } else if (i == 10) {
            handlerThread.getLooper().setSlowLogThresholdMs(1000L, 1000L);
        } else if (i < 0) {
            handlerThread.getLooper().setSlowLogThresholdMs(200L, 200L);
        }
        handlerThread.getLooper().setTraceTag(4096L);
        return handlerThread;
    }

    public final OCDMessageQueue buildOCDMessageQueue(String str, QueueAttr queueAttr) {
        OCDMessageQueue oCDMessageQueueMessageQueueCreate = QueueManager.messageQueueCreate(str, queueAttr, (DispatchQueue) null);
        if (Intrinsics.areEqual(queueAttr, QueueAttr.QUEUE_SERIAL_TIME)) {
            oCDMessageQueueMessageQueueCreate.setSlowLogThresholdMs(500L, 500L);
        } else if (Intrinsics.areEqual(queueAttr, QueueAttr.QUEUE_BG_TIME)) {
            oCDMessageQueueMessageQueueCreate.setSlowLogThresholdMs(1000L, 1000L);
        } else if (Intrinsics.areEqual(queueAttr, QueueAttr.QUEUE_FG_TIME)) {
            oCDMessageQueueMessageQueueCreate.setSlowLogThresholdMs(200L, 200L);
        }
        oCDMessageQueueMessageQueueCreate.setTraceTag(4096L);
        return oCDMessageQueueMessageQueueCreate;
    }

    @Override // com.oplusos.systemui.common.concurrency.OplusThreadFactory
    public HandlerThread buildNewThread(String str) {
        return buildNewThread(str, 0);
    }
}
