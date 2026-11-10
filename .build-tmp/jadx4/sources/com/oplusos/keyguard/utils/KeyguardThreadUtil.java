package com.oplusos.keyguard.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import com.oplus.dispatch.DispatchQueue;
import com.oplus.dispatch.OCDExecutorService;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplus.dispatch.QueueManager;
import com.oplus.uifirst.OplusUIFirstManager;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.android.HandlerDispatcher;
import kotlinx.coroutines.android.HandlerDispatcherKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class KeyguardThreadUtil {
    private static final String GC_QUEUE_NAME = "KgdGcQueue";
    private static final String KGD_ANIM_THREAD_NAME = "KgdAnimThread";
    private static final String TAG = "KeyguardThreadUtil";
    private static final String THREAD_NAME = "KeyguardWorkerThread";
    private static final HandlerDispatcher sAnimCoroutineDispatcher;
    private static final Handler sAnimHandler;
    private static final HandlerThread sAnimThread;
    private static final OCDHandler sGcHandler;
    private static final HandlerDispatcher sIOCoroutineDispatcher;
    private static final OCDHandler sIOHandler;
    private static ExecutorService sThreadExecutor;
    public static int sUxAnimState;
    private static final HandlerDispatcher sWorkCoroutineDispatcher;
    private static final Handler sWorkHandler;
    private static final HandlerThread sWorkThread;
    public static final Companion Companion = new Companion();
    private static final Object sUxAnimLock = new Object();

    static {
        OplusThreadFactoryImpl oplusThreadFactoryImpl = OplusThreadFactoryImpl.INSTANCE;
        HandlerThread handlerThreadBuildNewThread = oplusThreadFactoryImpl.buildNewThread(THREAD_NAME);
        sWorkThread = handlerThreadBuildNewThread;
        Handler handler = new Handler(handlerThreadBuildNewThread.getLooper());
        sWorkHandler = handler;
        sWorkCoroutineDispatcher = HandlerDispatcherKt.from(handler, "kgd_work");
        HandlerThread handlerThreadBuildNewThread2 = oplusThreadFactoryImpl.buildNewThread(KGD_ANIM_THREAD_NAME, -8);
        sAnimThread = handlerThreadBuildNewThread2;
        Handler handler2 = new Handler(handlerThreadBuildNewThread2.getLooper());
        sAnimHandler = handler2;
        sAnimCoroutineDispatcher = HandlerDispatcherKt.from(handler2, "kgd_anim");
        OCDHandler oCDHandler = new OCDHandler(QueueManager.messageQueueCreate("KgdIoQueue", new QueueAttr.Builder().setConcurrent(false).setIoQueue(true).setIsTimeQueue(true).build(), (DispatchQueue) null));
        sIOHandler = oCDHandler;
        sIOCoroutineDispatcher = HandlerDispatcherKt.from(oCDHandler, "kgd_io");
        sGcHandler = new OCDHandler(QueueManager.messageQueueCreate(GC_QUEUE_NAME, QueueAttr.QUEUE_BG_TIME, (DispatchQueue) null));
        handler.post(KeyguardThreadUtil$Companion$1$1.INSTANCE);
        handler2.post(Companion.AnonymousClass2.INSTANCE);
    }

    public static final boolean canRunOnCurrentThread(OCDHandler oCDHandler) {
        return Companion.canRunOnCurrentThread(oCDHandler);
    }

    public static final Handler getAnimHandler() {
        return Companion.getAnimHandler();
    }

    public static final OCDHandler getGcHandler() {
        return Companion.getGcHandler();
    }

    public static final OCDHandler getIOHandler() {
        return Companion.getIOHandler();
    }

    private static final ExecutorService getThreadExecutor() {
        return Companion.getThreadExecutor();
    }

    public static final Handler getWorkHandler() {
        return Companion.getWorkHandler();
    }

    public static final boolean hasSameTaskInWorkLooper(Runnable runnable) {
        return Companion.hasSameTaskInWorkLooper(runnable);
    }

    public static final Future<Object> postOnBackgroundThread(Callable<Object> callable) {
        return Companion.postOnBackgroundThread(callable);
    }

    public static final void postOnMainThread(Runnable runnable) {
        Companion.postOnMainThread(runnable);
    }

    public static final void postOnMainThreadDelay(Runnable runnable, long j) {
        Companion.postOnMainThreadDelay(runnable, j);
    }

    public static final void postOnMainThreadFront(Runnable runnable) {
        Companion.postOnMainThreadFront(runnable);
    }

    public static final void postOnMainThreadImmediately(Runnable runnable) {
        Companion.postOnMainThreadImmediately(runnable);
    }

    public static final void runOnAnimThread(Runnable runnable) {
        Companion.runOnAnimThread(runnable);
    }

    public static final void runOnGcThread(Runnable runnable) {
        Companion.runOnGcThread(runnable);
    }

    public static final void runOnGcThreadDelay(Runnable runnable, long j) {
        Companion.runOnGcThreadDelay(runnable, j);
    }

    public static final void runOnIOThread(Runnable runnable) {
        Companion.runOnIOThread(runnable);
    }

    public static final void runOnWorkThread(Runnable runnable) {
        Companion.runOnWorkThread(runnable);
    }

    public static final void runOnWorkThreadDelay(Runnable runnable, long j) {
        Companion.runOnWorkThreadDelay(runnable, j);
    }

    public static final void postOnBackgroundThread(Runnable runnable) {
        Companion.postOnBackgroundThread(runnable);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {

        /* JADX INFO: renamed from: com.oplusos.keyguard.utils.KeyguardThreadUtil$Companion$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass2 implements Runnable {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            @Override // java.lang.Runnable
            public final void run() {
                OplusSysUiSchedMgr.Companion.getInstance().reportKeyThread(KeyguardThreadUtil.KGD_ANIM_THREAD_NAME, Process.myTid(), OplusSysUiSchedMgr.UafSceneEvent.KEYGUARD_ANIM_RUNNING, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ExecutorService getThreadExecutor() {
            OCDExecutorService oCDExecutorService;
            ExecutorService executorService = KeyguardThreadUtil.sThreadExecutor;
            if (executorService != null) {
                return executorService;
            }
            synchronized (KeyguardThreadUtil.class) {
                oCDExecutorService = KeyguardThreadUtil.sThreadExecutor;
                if (oCDExecutorService == null) {
                    oCDExecutorService = new OCDExecutorService("KeyguardThreadUtil_ExecutorService", QueueAttr.QUEUE_CONCURRENT_BG, (DispatchQueue) null);
                    KeyguardThreadUtil.sThreadExecutor = oCDExecutorService;
                }
            }
            return oCDExecutorService;
        }

        public static void setKgdAnimUADState(int i, boolean z) {
            synchronized (KeyguardThreadUtil.sUxAnimLock) {
                KgdLogUtil.i(KeyguardThreadUtil.TAG, "setKgdAnimUADState type=" + i + " enable=" + z + " lastState=" + KeyguardThreadUtil.sUxAnimState);
                if (i == 0) {
                    KeyguardThreadUtil.Companion.requestKgdAnimUXResourceOnMainThread(z);
                    return;
                }
                if (z) {
                    KeyguardThreadUtil.sUxAnimState = i | KeyguardThreadUtil.sUxAnimState;
                } else {
                    int i2 = (~i) & KeyguardThreadUtil.sUxAnimState;
                    KeyguardThreadUtil.sUxAnimState = i2;
                    if (i2 > 0) {
                        KgdLogUtil.i(KeyguardThreadUtil.TAG, "setKgdAnimUADState uad running and skip, curState=" + i2);
                        return;
                    }
                }
                boolean z2 = KeyguardThreadUtil.sUxAnimState > 0;
                if (z2) {
                    KeyguardThreadUtil.Companion.requestKgdAnimUXResourceOnMainThread(true);
                } else {
                    KeyguardThreadUtil.Companion.requestKgdAnimUXResourceOnMainThread(false);
                }
                KgdLogUtil.i(KeyguardThreadUtil.TAG, "setKgdAnimUADState done UADEnable=" + z2 + " finishState=" + KeyguardThreadUtil.sUxAnimState);
            }
        }

        public final boolean canRunOnCurrentThread(OCDHandler oCDHandler) {
            if (oCDHandler == null) {
                return false;
            }
            return Intrinsics.areEqual(OCDMessageQueue.getCurrentThreadOCDMessageQueue(), oCDHandler.getOcdMessageQueue());
        }

        public final HandlerDispatcher getAnimCoroutineDispatcher() {
            return KeyguardThreadUtil.sAnimCoroutineDispatcher;
        }

        public final Handler getAnimHandler() {
            return KeyguardThreadUtil.sAnimHandler;
        }

        public final OCDHandler getGcHandler() {
            return KeyguardThreadUtil.sGcHandler;
        }

        public final HandlerDispatcher getIOCoroutineDispatcher() {
            return KeyguardThreadUtil.sIOCoroutineDispatcher;
        }

        public final OCDHandler getIOHandler() {
            return KeyguardThreadUtil.sIOHandler;
        }

        public final HandlerDispatcher getWorkCoroutineDispatcher() {
            return KeyguardThreadUtil.sWorkCoroutineDispatcher;
        }

        public final Handler getWorkHandler() {
            return KeyguardThreadUtil.sWorkHandler;
        }

        public final boolean hasSameTaskInWorkLooper(Runnable runnable) {
            return KeyguardThreadUtil.sWorkHandler.hasCallbacks(runnable);
        }

        public final Future<Object> postOnBackgroundThread(Callable<Object> callable) {
            return getThreadExecutor().submit(callable);
        }

        public final void postOnMainThread(Runnable runnable) {
            Handler.getMain().post(runnable);
        }

        public final void postOnMainThreadDelay(Runnable runnable, long j) {
            Handler.getMain().postDelayed(runnable, j);
        }

        public final void postOnMainThreadFront(Runnable runnable) {
            Handler.getMain().postAtFrontOfQueue(runnable);
        }

        public final void postOnMainThreadImmediately(Runnable runnable) {
            Handler main = Handler.getMain();
            if (Intrinsics.areEqual(main.getLooper().getThread(), Thread.currentThread())) {
                runnable.run();
            } else {
                main.post(runnable);
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.oplusos.keyguard.utils.KeyguardThreadUtil$Companion$$ExternalSyntheticLambda0, kotlin.jvm.functions.Function0] */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.oplusos.keyguard.utils.KeyguardThreadUtil$Companion$$ExternalSyntheticLambda0, kotlin.jvm.functions.Function0] */
        public final void requestKgdAnimUXResourceOnMainThread(boolean z) {
            final int i = 0;
            final ?? r0 = new Function0() { // from class: com.oplusos.keyguard.utils.KeyguardThreadUtil$Companion$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i) {
                        case 0:
                            OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.KEYGUARD_ANIM_RUNNING);
                            break;
                        default:
                            OplusSysUiSchedMgr.Companion.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.KEYGUARD_ANIM_RUNNING);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
            final int i2 = 1;
            final ?? r1 = new Function0() { // from class: com.oplusos.keyguard.utils.KeyguardThreadUtil$Companion$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i2) {
                        case 0:
                            OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.KEYGUARD_ANIM_RUNNING);
                            break;
                        default:
                            OplusSysUiSchedMgr.Companion.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.KEYGUARD_ANIM_RUNNING);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
            if (Intrinsics.areEqual(Thread.currentThread(), Looper.getMainLooper().getThread())) {
                if (z) {
                    r0.invoke();
                    return;
                } else {
                    r1.invoke();
                    return;
                }
            }
            if (z) {
                postOnMainThreadFront(new Runnable() { // from class: com.oplusos.keyguard.utils.KeyguardThreadUtil$sam$java_lang_Runnable$0
                    @Override // java.lang.Runnable
                    public final /* synthetic */ void run() {
                        r0.invoke();
                    }
                });
            } else {
                postOnMainThreadFront(new Runnable() { // from class: com.oplusos.keyguard.utils.KeyguardThreadUtil$sam$java_lang_Runnable$0
                    @Override // java.lang.Runnable
                    public final /* synthetic */ void run() {
                        r1.invoke();
                    }
                });
            }
        }

        public final void runOnAnimThread(Runnable runnable) {
            if (Intrinsics.areEqual(Looper.myLooper(), KeyguardThreadUtil.sAnimThread.getLooper())) {
                runnable.run();
            } else {
                KeyguardThreadUtil.sAnimHandler.post(runnable);
            }
        }

        public final void runOnAsyncBinderUx(Function0 function0) {
            try {
                OplusUIFirstManager.getInstance().setBinderThreadUxFlag(-1, 1);
                function0.invoke();
            } finally {
                OplusUIFirstManager.getInstance().setBinderThreadUxFlag(-1, 0);
            }
        }

        public final void runOnGcThread(Runnable runnable) {
            if (canRunOnCurrentThread(KeyguardThreadUtil.sGcHandler)) {
                runnable.run();
            } else {
                KeyguardThreadUtil.sGcHandler.post(runnable);
            }
        }

        public final void runOnGcThreadDelay(Runnable runnable, long j) {
            KeyguardThreadUtil.sGcHandler.postDelayed(runnable, j);
        }

        public final void runOnIOThread(Runnable runnable) {
            if (canRunOnCurrentThread(KeyguardThreadUtil.sIOHandler)) {
                runnable.run();
            } else {
                KeyguardThreadUtil.sIOHandler.post(runnable);
            }
        }

        public final void runOnWorkThread(Runnable runnable) {
            if (Intrinsics.areEqual(Looper.myLooper(), KeyguardThreadUtil.sWorkThread.getLooper())) {
                runnable.run();
            } else {
                KeyguardThreadUtil.sWorkHandler.post(runnable);
            }
        }

        public final void runOnWorkThreadDelay(Runnable runnable, long j) {
            KeyguardThreadUtil.sWorkHandler.postDelayed(runnable, j);
        }

        public final void postOnBackgroundThread(Runnable runnable) {
            getThreadExecutor().execute(runnable);
        }
    }
}
