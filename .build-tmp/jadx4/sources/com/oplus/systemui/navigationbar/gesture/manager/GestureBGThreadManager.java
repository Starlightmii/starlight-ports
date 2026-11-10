package com.oplus.systemui.navigationbar.gesture.manager;

import android.os.Handler;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GestureBGThreadManager {
    public static final GestureBGThreadManager INSTANCE = new GestureBGThreadManager();
    private static final String THREAD_NAME = "GestureBg";
    private static final OCDHandler gestureBGHandler;
    private static final OCDMessageQueue gestureBGMessageQueue;

    static {
        OCDMessageQueue oCDMessageQueueBuildOCDMessageQueue = OplusThreadFactoryImpl.INSTANCE.buildOCDMessageQueue(THREAD_NAME, QueueAttr.QUEUE_BG_TIME);
        gestureBGMessageQueue = oCDMessageQueueBuildOCDMessageQueue;
        gestureBGHandler = new OCDHandler(oCDMessageQueueBuildOCDMessageQueue);
    }

    public final Handler getGestureBGHandler() {
        return gestureBGHandler;
    }
}
