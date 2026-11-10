package com.oplus.systemui.seedlingservice.utils;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreadUtil {
    public static final ThreadUtil INSTANCE = new ThreadUtil();
    private static final String TAG = "ThreadUtil";
    private static final Handler workHandler;
    private static final HandlerThread workThread;

    static {
        HandlerThread handlerThreadBuildNewThread = buildNewThread(TAG, 0);
        workThread = handlerThreadBuildNewThread;
        workHandler = new Handler(handlerThreadBuildNewThread.getLooper());
    }

    private ThreadUtil() {
    }

    public static final HandlerThread buildNewThread(String str, int i) {
        LogUtil.d(TAG, "buildNewThread " + str + " with priority=" + i);
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        return handlerThread;
    }

    public final Handler getWorkHandler() {
        return workHandler;
    }

    public final HandlerThread getWorkThread() {
        return workThread;
    }
}
