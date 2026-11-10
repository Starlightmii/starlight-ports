package com.oplus.systemui.navigationbar.gesture.manager;

import android.os.Handler;
import android.os.HandlerThread;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.util.LogUtil;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GestureUIThreadManager {
    public static final GestureUIThreadManager INSTANCE = new GestureUIThreadManager();
    private static final String TAG = "GestureUIThreadManager";
    private static final String THREAD_NAME = "GestureUI";
    private static Handler mGestureUIHandler;
    private static HandlerThread mGestureUiThread;

    static {
        initGestureUIThread();
    }

    public static void initGestureUIThread() {
        if (mGestureUiThread == null) {
            HandlerThread handlerThreadBuildNewThread = OplusThreadFactoryImpl.INSTANCE.buildNewThread(THREAD_NAME, -8);
            mGestureUiThread = handlerThreadBuildNewThread;
            if (handlerThreadBuildNewThread != null) {
                mGestureUIHandler = new Handler(handlerThreadBuildNewThread.getLooper());
                LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, "GestureUIThread ----- init and mGestureUIHandler is start", null, 8);
                Handler handler = mGestureUIHandler;
                if (handler != null) {
                    handler.post(GestureUIThreadManager$initGestureUIThread$1$1$1.INSTANCE);
                }
            }
        }
    }

    public final Handler getGestureUIHandler() {
        Handler handler = mGestureUIHandler;
        if (handler != null) {
            return handler;
        }
        initGestureUIThread();
        Handler handler2 = mGestureUIHandler;
        if (handler2 != null) {
            return handler2;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("mGestureUIHandler is still null after initialization");
        return null;
    }

    public final HandlerThread getGestureUiThread() {
        HandlerThread handlerThread = mGestureUiThread;
        if (handlerThread != null) {
            return handlerThread;
        }
        initGestureUIThread();
        HandlerThread handlerThread2 = mGestureUiThread;
        if (handlerThread2 != null) {
            return handlerThread2;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("mGestureUiThread is still null after initialization");
        return null;
    }
}
