package com.oplus.systemui.qs.external;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.external.TileLifecycleManagerEx;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileLifecycleManagerExImpl extends TileLifecycleManagerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusTileLifecycleManagerExImpl";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusTileLifecycleManagerExImpl(Context context) {
        this.context = context;
    }

    public void startPackageReplaceListening(BroadcastReceiver broadcastReceiver, AtomicBoolean atomicBoolean, BroadcastDispatcher broadcastDispatcher, UserHandle userHandle) {
        Object obj;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusTileLifecycleManagerExImpl-->startPackageReplaceListening");
        }
        if (atomicBoolean.get()) {
            return;
        }
        try {
            BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, broadcastReceiver, intentFilter, (Executor) null, userHandle, 0, 48);
            obj = Result.constructor-impl(Boolean.TRUE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e(LogUtil.TAG_QS, TAG, "Could not register package receiver", th2);
            obj = Boolean.FALSE;
        }
        atomicBoolean.set(((Boolean) obj).booleanValue());
    }

    public void stopPackageReplaceListening(BroadcastReceiver broadcastReceiver, AtomicBoolean atomicBoolean, BroadcastDispatcher broadcastDispatcher) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusTileLifecycleManagerExImpl-->stopPackageReplaceListening");
        }
        if (atomicBoolean.compareAndSet(true, false)) {
            broadcastDispatcher.unregisterReceiver(broadcastReceiver);
        }
    }
}
