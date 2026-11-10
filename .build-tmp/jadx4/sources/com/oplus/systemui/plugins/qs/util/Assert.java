package com.oplus.systemui.plugins.qs.util;

import android.os.Looper;
import io.grpc.Status$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class Assert {
    public static final Looper sMainLooper = Looper.getMainLooper();
    public static Thread sTestThread = null;

    public static void isMainThread() {
        Looper looper = sMainLooper;
        if (looper.isCurrentThread()) {
            return;
        }
        Thread thread = sTestThread;
        if (thread == null || thread != Thread.currentThread()) {
            Status$.ExternalSyntheticBUOutline0.m("should be called from the main thread. sMainLooper.threadName=", looper.getThread().getName(), " Thread.currentThread()=", Thread.currentThread().getName());
        }
    }

    public static void setTestThread(Thread thread) {
        sTestThread = thread;
    }

    public static void setTestableLooper(Looper looper) {
        setTestThread(looper == null ? null : looper.getThread());
    }
}
