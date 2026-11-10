package com.oplus.systemui.media.seedling;

import android.os.HandlerThread;
import android.os.Looper;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaSeedlingBgThread {
    public static final MediaSeedlingBgThread INSTANCE = new MediaSeedlingBgThread();
    private static final String TAG = "MediaSeedlingBgThread";
    private static HandlerThread handlerThread = OplusThreadFactoryImpl.INSTANCE.buildNewThread(TAG, -16);

    public final Looper getThreadLooper() {
        return handlerThread.getLooper();
    }

    public final String getThreadName() {
        return TAG;
    }
}
