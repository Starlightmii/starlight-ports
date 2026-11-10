package com.oplus.utrace.sdk.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.oplus.utrace.lib.IUTraceInterface;
import com.oplus.utrace.sdk.UTraceApp;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.UtilsKt;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ExecutorService;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTraceManager$mConnection$1 implements ServiceConnection {
    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        super.onNullBinding(componentName);
        UTraceManager uTraceManager = UTraceManager.INSTANCE;
        UTraceManager.bindInfo = Intrinsics.stringPlus(componentName, "nullbinding:name=");
        UtilsKt.submitSafe(UTraceManager.mExecutor, new UTraceManager$$ExternalSyntheticLambda2(2));
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Logs.INSTANCE.i("UTrace.Sdk.Manager", "onServiceConnected() name=" + componentName + " service=" + iBinder + " proc=" + UtilsKt.getProcessName(UTraceApp.mContext));
        UTraceManager uTraceManager = UTraceManager.INSTANCE;
        UTraceManager.bindInfo = Intrinsics.stringPlus(componentName, "connected:name=");
        UTraceManager.INSTANCE.resetUnbindMessage();
        IUTraceInterface iUTraceInterfaceAsInterface = IUTraceInterface.Stub.asInterface(iBinder);
        ExecutorService executorService = UTraceManager.mExecutor;
        UTraceManager$mConnection$1$$ExternalSyntheticLambda0 uTraceManager$mConnection$1$$ExternalSyntheticLambda0 = new UTraceManager$mConnection$1$$ExternalSyntheticLambda0();
        uTraceManager$mConnection$1$$ExternalSyntheticLambda0.f$0 = iUTraceInterfaceAsInterface;
        VarHandle.storeStoreFence();
        UtilsKt.submitSafe(executorService, uTraceManager$mConnection$1$$ExternalSyntheticLambda0);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Logs.INSTANCE.i("UTrace.Sdk.Manager", Intrinsics.stringPlus(UtilsKt.getProcessName(UTraceApp.mContext), "onServiceDisconnected() proc="));
        UTraceManager uTraceManager = UTraceManager.INSTANCE;
        UTraceManager.bindInfo = Intrinsics.stringPlus(componentName, "disconnected:name=");
        UtilsKt.submitSafe(UTraceManager.mExecutor, new UTraceManager$$ExternalSyntheticLambda2(1));
    }
}
