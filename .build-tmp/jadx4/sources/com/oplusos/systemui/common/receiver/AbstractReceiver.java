package com.oplusos.systemui.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Trace;
import android.os.UserHandle;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplus.dispatch.OCDHandler;
import com.oplus.systemui.provider.ProviderUtil;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbstractReceiver {
    public boolean mHasRegister;
    private WeakReference<AbstractReceiver> weakReference = new WeakReference<>(this);
    private final AbstractReceiver$mReceiver$1 mReceiver = new BroadcastReceiver() { // from class: com.oplusos.systemui.common.receiver.AbstractReceiver$mReceiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            Trace.traceBegin(8L, "AbstractReceiver_" + intent.getAction());
            AbstractReceiver abstractReceiver = (AbstractReceiver) this.this$0.weakReference.get();
            if (abstractReceiver != null) {
                abstractReceiver.onReceive(context, intent);
            }
            Trace.traceEnd(8L);
        }
    };
    private final CopyOnWriteArraySet<IReceiverListener> mListeners = new CopyOnWriteArraySet<>();

    public void addListener(IReceiverListener iReceiverListener) {
        this.mListeners.add(iReceiverListener);
    }

    public void afterRegister(Context context) {
    }

    public void beforeRegister(IntentFilter intentFilter) {
    }

    public abstract String[] getActions();

    public CopyOnWriteArraySet<IReceiverListener> getListeners() {
        return this.mListeners;
    }

    public void onReceive(Context context, Intent intent) {
        for (IReceiverListener iReceiverListener : this.mListeners) {
            Trace.traceBegin(8L, iReceiverListener.toString());
            iReceiverListener.onReceive(context, intent);
            Trace.traceEnd(8L);
        }
    }

    public final void register(Context context, UserHandle userHandle, Handler handler, boolean z, boolean z2, boolean z3) {
        BroadcastDispatcher broadcastDispatcher;
        if (this.mHasRegister) {
            return;
        }
        String[] actions = getActions();
        if (actions.length == 0) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        for (String str : actions) {
            intentFilter.addAction(str);
        }
        beforeRegister(intentFilter);
        HandlerExecutor handlerExecutor = null;
        try {
            broadcastDispatcher = (BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class);
        } catch (Exception unused) {
            broadcastDispatcher = null;
        }
        if (broadcastDispatcher != null) {
            if (z2) {
                handlerExecutor = handler instanceof OCDHandler ? new HandlerExecutor(handler) : (handler == null || Intrinsics.areEqual(handler.getLooper(), Looper.getMainLooper())) ? context.getMainExecutor() : new HandlerExecutor(handler);
            }
            HandlerExecutor handlerExecutor2 = handlerExecutor;
            AbstractReceiver$mReceiver$1 abstractReceiver$mReceiver$1 = this.mReceiver;
            if (!z) {
                BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, abstractReceiver$mReceiver$1, intentFilter, (Executor) null, (UserHandle) null, 0, 60);
            } else if (z3) {
                broadcastDispatcher.registerReceiver(abstractReceiver$mReceiver$1, intentFilter, handlerExecutor2, userHandle, 2, ProviderUtil.OPLUS_COMPONENT_SAFE);
            } else {
                BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, abstractReceiver$mReceiver$1, intentFilter, handlerExecutor2, userHandle, 2, 32);
            }
        } else {
            AbstractReceiver$mReceiver$1 abstractReceiver$mReceiver$2 = this.mReceiver;
            if (z) {
                context.registerReceiverAsUser(abstractReceiver$mReceiver$2, userHandle, intentFilter, ProviderUtil.OPLUS_COMPONENT_SAFE, handler, 2);
            } else if (z2) {
                context.registerReceiver(abstractReceiver$mReceiver$2, intentFilter, ProviderUtil.OPLUS_COMPONENT_SAFE, handler, 2);
            } else {
                context.registerReceiver(abstractReceiver$mReceiver$2, intentFilter, 2);
            }
        }
        this.mHasRegister = true;
        afterRegister(context);
    }

    public void removeListener(IReceiverListener iReceiverListener) {
        this.mListeners.remove(iReceiverListener);
    }

    public void unregister(Context context) {
        if (this.mHasRegister) {
            try {
                ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).unregisterReceiver(this.mReceiver);
            } catch (Exception unused) {
                context.unregisterReceiver(this.mReceiver);
            }
            this.mHasRegister = false;
        }
    }

    public void register(Context context, Handler handler) {
        register(context, UserHandle.CURRENT, handler, false, true, false);
    }

    public void register(Context context, UserHandle userHandle, Handler handler) {
        register(context, userHandle, handler, true, true, true);
    }

    public void register(Context context, UserHandle userHandle, boolean z) {
        register(context, userHandle, null, true, true, z);
    }

    public void register(Context context) {
        register(context, UserHandle.CURRENT, null, false, false, false);
    }
}
