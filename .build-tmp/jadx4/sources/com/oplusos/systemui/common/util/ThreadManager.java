package com.oplusos.systemui.common.util;

import android.os.Handler;
import android.os.Looper;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreadManager {
    private static final String TAG = "ThreadManager";
    private OCDHandler mHandler;
    private OCDMessageQueue mHandlerQueue;
    private Handler mMainHandler;
    private ExecutorService mThreadExecutor;
    public boolean startState;
    public static final Companion Companion = new Companion();
    private static final ThreadManager sThreadManager = new ThreadManager();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ThreadManager getInstance() {
            return ThreadManager.sThreadManager;
        }
    }

    public static final ThreadManager getInstance() {
        return Companion.getInstance();
    }

    public final Handler getMainHandler() {
        return this.mMainHandler;
    }

    public final Handler getThreadHandler() {
        return this.mHandler;
    }

    public final void postInMainThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            runnable.run();
            return;
        }
        Handler handler = this.mMainHandler;
        handler.getClass();
        handler.post(runnable);
    }

    public final void postInThread(Function0 function0) {
        OCDHandler oCDHandler = this.mHandler;
        if (oCDHandler != null) {
            ThreadManager$sam$java_lang_Runnable$0 threadManager$sam$java_lang_Runnable$0 = new ThreadManager$sam$java_lang_Runnable$0();
            threadManager$sam$java_lang_Runnable$0.function = function0;
            VarHandle.storeStoreFence();
            oCDHandler.post(threadManager$sam$java_lang_Runnable$0);
        }
    }

    public final void postInThreadDelay(Runnable runnable, long j) {
        OCDHandler oCDHandler;
        if (runnable == null || (oCDHandler = this.mHandler) == null) {
            return;
        }
        oCDHandler.getClass();
        oCDHandler.postDelayed(runnable, j);
    }

    public final void postInThreadPool(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (this.mThreadExecutor == null) {
            this.mThreadExecutor = Executors.newFixedThreadPool(3);
        }
        ExecutorService executorService = this.mThreadExecutor;
        executorService.getClass();
        executorService.execute(runnable);
    }

    public final void start() {
        if (this.startState) {
            return;
        }
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mHandlerQueue = OplusThreadFactoryImpl.INSTANCE.buildOCDMessageQueue(TAG, QueueAttr.QUEUE_SERIAL_TIME);
        this.mHandler = new OCDHandler(this.mHandlerQueue);
        this.startState = true;
    }
}
