package com.oplus.systemui.plugins.qs.dagger;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.oplus.systemui.plugins.qs.util.DelayableExecutor;
import com.oplus.systemui.plugins.qs.util.ExecutorImpl;
import com.oplus.systemui.plugins.qs.util.ExecutorImpl$;
import com.oplus.systemui.plugins.qs.util.QSThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.android.HandlerDispatcherKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalConcurrencyModule {
    public static final GlobalConcurrencyModule INSTANCE = new GlobalConcurrencyModule();

    public final CoroutineScope provideBgCoroutineScope(CoroutineDispatcher coroutineDispatcher) {
        return CoroutineScopeKt.CoroutineScope(coroutineDispatcher);
    }

    public final CoroutineDispatcher provideBgDispatcher() {
        return HandlerDispatcherKt.from(QSThreadUtil.getWorkHandler(), (String) null);
    }

    public final CoroutineScope provideMainCoroutineScope() {
        return CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().getImmediate());
    }

    public final DelayableExecutor provideMainDelayableExecutor(Looper looper) {
        ExecutorImpl executorImpl = new ExecutorImpl();
        ExecutorImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ExecutorImpl$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = executorImpl;
        VarHandle.storeStoreFence();
        executorImpl.mHandler = new Handler(looper, externalSyntheticLambda0);
        VarHandle.storeStoreFence();
        return executorImpl;
    }

    public final Executor provideMainExecutor(Context context) {
        return context.getMainExecutor();
    }

    public final Handler provideMainHandler(Looper looper) {
        return new Handler(looper);
    }

    public final Looper provideMainLooper() {
        return Looper.getMainLooper();
    }
}
