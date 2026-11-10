package com.oplusos.systemui.common.concurrency;

import android.os.Handler;
import android.os.Looper;
import com.oplus.dispatch.DispatchQueue;
import com.oplus.dispatch.OCDExecutorService;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplus.dispatch.QueueManager;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUiOffloadThread {
    private static final String TAG = "OplusUiOffloadThread";
    private final OCDHandler mThreadHandler;
    private final Handler mUiHandler;
    private OCDExecutorService mUiOffloadThreadPool;
    public static final Companion Companion = new Companion();
    private static final OplusUiOffloadThread sInstance = new OplusUiOffloadThread();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusUiOffloadThread getInstance() {
            return OplusUiOffloadThread.sInstance;
        }
    }

    public OplusUiOffloadThread() {
        OCDMessageQueue oCDMessageQueueMessageQueueCreate = QueueManager.messageQueueCreate(TAG, new QueueAttr.Builder().setConcurrent(false).setIsTimeQueue(true).setIoQueue(true).build(), (DispatchQueue) null);
        oCDMessageQueueMessageQueueCreate.setSlowLogThresholdMs(1000L, 1000L);
        oCDMessageQueueMessageQueueCreate.setTraceTag(4096L);
        this.mThreadHandler = new OCDHandler(oCDMessageQueueMessageQueueCreate);
        this.mUiHandler = new Handler(Looper.getMainLooper());
    }

    public static final OplusUiOffloadThread getInstance() {
        return Companion.getInstance();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusUiOffloadThread state begin:");
        printWriter.println("UiOffloadThreadPool Null");
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        printWriter.println("All thread -- size=" + allStackTraces.size() + " --");
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();
            printWriter.println(key.getName() + " state=" + key.getState() + " priority=" + key.getPriority() + " isAlive=" + key.isAlive());
            value.getClass();
            for (StackTraceElement stackTraceElement : value) {
                printWriter.println(" " + stackTraceElement);
            }
            printWriter.println("\n");
        }
        printWriter.println("OplusUiOffloadThread state end");
    }

    public final void executeOnThreadExecutor(Runnable runnable) {
        getDefaultThreadExecutor().execute(runnable);
    }

    public final synchronized OCDExecutorService getDefaultThreadExecutor() {
        OCDExecutorService oCDExecutorService;
        QueueAttr queueAttrBuild = new QueueAttr.Builder().setConcurrent(true, 3).build();
        oCDExecutorService = this.mUiOffloadThreadPool;
        if (oCDExecutorService == null) {
            oCDExecutorService = new OCDExecutorService(TAG, queueAttrBuild, (DispatchQueue) null);
            this.mUiOffloadThreadPool = oCDExecutorService;
        }
        return oCDExecutorService;
    }

    public final OCDHandler getSubHandler() {
        return this.mThreadHandler;
    }

    public final Handler getUiHandler() {
        return this.mUiHandler;
    }

    public final void runOnSubThread(Runnable runnable) {
        this.mThreadHandler.post(runnable);
    }

    public final void runOnUiThread(Runnable runnable) {
        this.mUiHandler.post(runnable);
    }

    public final void runOnUiThreadDelayed(Runnable runnable, long j) {
        this.mUiHandler.postDelayed(runnable, j);
    }

    public final Future<?> submitOnThreadExecutor(Runnable runnable) {
        return getDefaultThreadExecutor().submit(runnable);
    }

    public final Future<?> submitOnThreadExecutor(Callable<?> callable) {
        return getDefaultThreadExecutor().submit(callable);
    }
}
