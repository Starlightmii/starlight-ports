package com.oplusos.systemui.common.util;

import android.os.HandlerThread;
import android.os.Looper;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileHandlerThread {
    public static final Companion Companion = new Companion();
    private static final Lazy sInstance$delegate = LazyKt__LazyJVMKt.lazy(new TileHandlerThread$$ExternalSyntheticLambda0());
    private final HandlerThread mSysUiTileBgThread;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final TileHandlerThread getSInstance() {
            return (TileHandlerThread) TileHandlerThread.sInstance$delegate.getValue();
        }

        public final TileHandlerThread getInstance() {
            return getSInstance();
        }
    }

    public TileHandlerThread() {
        HandlerThread handlerThreadBuildNewThread = OplusThreadFactoryImpl.INSTANCE.buildNewThread("SysUiTileBg", 0);
        this.mSysUiTileBgThread = handlerThreadBuildNewThread;
        if (handlerThreadBuildNewThread != null) {
            handlerThreadBuildNewThread.getThreadHandler().post(TileHandlerThread$1$1.INSTANCE);
        }
    }

    public static final TileHandlerThread getInstance() {
        return Companion.getInstance();
    }

    public final Looper getLooper() {
        return this.mSysUiTileBgThread.getLooper();
    }
}
