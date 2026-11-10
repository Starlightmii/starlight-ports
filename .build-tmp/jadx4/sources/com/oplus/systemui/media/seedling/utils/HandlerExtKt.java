package com.oplus.systemui.media.seedling.utils;

import android.os.Handler;
import android.os.Looper;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class HandlerExtKt {
    public static final void removeTaskIfExist(Handler handler, Runnable runnable) {
        if (handler.hasCallbacks(runnable)) {
            handler.removeCallbacks(runnable);
        }
    }

    public static final void runTask(Handler handler, Runnable runnable) {
        if (Intrinsics.areEqual(Looper.myLooper(), handler.getLooper())) {
            removeTaskIfExist(handler, runnable);
            runnable.run();
        } else {
            if (handler.hasCallbacks(runnable)) {
                return;
            }
            handler.post(runnable);
        }
    }

    public static final void runTaskDelayed(Handler handler, Runnable runnable, long j) {
        if (j <= 0) {
            runTask(handler, runnable);
        } else {
            removeTaskIfExist(handler, runnable);
            handler.postDelayed(runnable, j);
        }
    }

    public static final void runTaskImmediately(Handler handler, Runnable runnable) {
        if (Intrinsics.areEqual(Looper.myLooper(), handler.getLooper())) {
            removeTaskIfExist(handler, runnable);
            runnable.run();
        } else {
            if (handler.hasCallbacks(runnable)) {
                removeTaskIfExist(handler, runnable);
            }
            handler.post(runnable);
        }
    }
}
