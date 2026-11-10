package com.oplusos.systemui.common.util;

import android.os.HandlerThread;
import android.os.Looper;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsBgThread {
    public static final QsBgThread INSTANCE = new QsBgThread();
    private static final String QS = "SysUiQsBg";
    private static HandlerThread handlerThread;

    static {
        HandlerThread handlerThreadBuildNewThread = OplusThreadFactoryImpl.INSTANCE.buildNewThread(QS);
        handlerThread = handlerThreadBuildNewThread;
        if (handlerThreadBuildNewThread != null) {
            handlerThreadBuildNewThread.getThreadHandler().post(QsBgThread$1$1.INSTANCE);
        }
    }

    public final Looper getThreadLooper() {
        return handlerThread.getLooper();
    }
}
