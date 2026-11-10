package com.oplusos.systemui.common.util;

import android.os.Handler;
import android.os.Looper;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SystemUIThreadUtil {
    public static final Handler sMainHandler;
    public static final OCDHandler sWorkHandler;
    public static final OCDMessageQueue sWorkQueue;

    static {
        OCDMessageQueue oCDMessageQueueBuildOCDMessageQueue = OplusThreadFactoryImpl.INSTANCE.buildOCDMessageQueue("SystemUIThreadUtil", QueueAttr.QUEUE_SERIAL_TIME);
        sWorkQueue = oCDMessageQueueBuildOCDMessageQueue;
        sWorkHandler = new OCDHandler(oCDMessageQueueBuildOCDMessageQueue);
        sMainHandler = new Handler(Looper.getMainLooper());
    }

    public static Handler getMainHandler() {
        return sMainHandler;
    }

    public static Looper getMainLooper() {
        return sMainHandler.getLooper();
    }

    public static Handler getWorkHandler() {
        return sWorkHandler;
    }

    public static OCDMessageQueue getWorkLooper() {
        return sWorkQueue;
    }

    public static void removeCallbacksInUiThread(Runnable runnable) {
        sMainHandler.removeCallbacks(runnable);
    }

    public static boolean runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return sMainHandler.post(runnable);
        }
        runnable.run();
        return false;
    }

    public static void runOnUiThreadAtFront(Runnable runnable) {
        if (runnable == null) {
            LogUtil.i(LogUtil.TAG_COMMON, "SystemUIThreadUtil", "runOnUiThreadAtFront runnable is null");
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            sMainHandler.postAtFrontOfQueue(runnable);
        }
    }

    public static void runOnUiThreadDelay(Runnable runnable, long j) {
        sMainHandler.postDelayed(runnable, j);
    }

    public static void runOnWorkThread(Runnable runnable) {
        if (sWorkQueue == OCDMessageQueue.getCurrentThreadOCDMessageQueue()) {
            runnable.run();
        } else {
            sWorkHandler.post(runnable);
        }
    }
}
