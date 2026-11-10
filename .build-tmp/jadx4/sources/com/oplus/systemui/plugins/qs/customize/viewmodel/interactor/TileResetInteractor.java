package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileResetInteractor {
    private static final String ACTION = "com.oplus.systemui.action.RESET_QS_LAYOUT";
    public static final Companion Companion = new Companion();
    private static final String PERMISSION = "com.oplus.systemui.plugins.qs.permission.RESET_QS_LAYOUT";
    private static final String TAG = "TileResetInteractor";
    private final ContextInstance context;
    private final CoroutineScope mainScope;
    private final Provider tileConfigRepoSwitcherProvider;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final TileResetInteractor$receiver$1 receiver = new BroadcastReceiver() { // from class: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileResetInteractor$receiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            TileResetInteractor.access$reset(this.this$0);
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileResetInteractor$receiver$1] */
    public TileResetInteractor(ContextInstance contextInstance, CoroutineScope coroutineScope, Provider provider) {
        this.context = contextInstance;
        this.mainScope = coroutineScope;
        this.tileConfigRepoSwitcherProvider = provider;
    }

    public static final void access$reset(TileResetInteractor tileResetInteractor) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "reset", null, 8);
        CoroutineTracingKt.launchTraced$default(tileResetInteractor.mainScope, "TileResetInteractor-launch-reset", (CoroutineContext) null, (CoroutineStart) null, new reset.1(tileResetInteractor, (Continuation) null), 6);
    }

    public final void destroy() {
        if (!this.initialized.compareAndSet(true, false)) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "destroy: never start!");
            return;
        }
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null) {
            proxy.unregisterReceiver(this.receiver);
        }
    }

    public final void start() {
        if (!this.initialized.compareAndSet(false, true)) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "start: already initialized!");
            return;
        }
        IntentFilter intentFilter = new IntentFilter(ACTION);
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null) {
            proxy.registerReceiver(this.receiver, intentFilter, (Executor) null, UserHandle.ALL, 2, PERMISSION);
        }
    }
}
