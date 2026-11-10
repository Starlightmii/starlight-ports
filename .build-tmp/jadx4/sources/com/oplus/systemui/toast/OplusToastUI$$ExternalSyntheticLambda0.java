package com.oplus.systemui.toast;

import android.app.ActivityThread;
import android.app.ITransientNotificationCallback;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusToastUI$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ OplusToastUI.ToastItem f$0;
    public /* synthetic */ ITransientNotificationCallback f$1;
    public /* synthetic */ int f$2;

    @Override // java.lang.Runnable
    public final void run() {
        OplusToastUI.ToastItem toastItem = this.f$0;
        ITransientNotificationCallback iTransientNotificationCallback = this.f$1;
        int i = this.f$2;
        OplusToastUI$ToastItem$$ExternalSyntheticLambda0 oplusToastUI$ToastItem$$ExternalSyntheticLambda0 = new OplusToastUI$ToastItem$$ExternalSyntheticLambda0();
        oplusToastUI$ToastItem$$ExternalSyntheticLambda0.f$0 = toastItem;
        VarHandle.storeStoreFence();
        CompletableFuture completableFutureSupplyAsync = CompletableFuture.supplyAsync(oplusToastUI$ToastItem$$ExternalSyntheticLambda0, OplusUiOffloadThread.getInstance().getDefaultThreadExecutor());
        OplusToastUI$ToastItem$$ExternalSyntheticLambda1 oplusToastUI$ToastItem$$ExternalSyntheticLambda1 = new OplusToastUI$ToastItem$$ExternalSyntheticLambda1();
        oplusToastUI$ToastItem$$ExternalSyntheticLambda1.f$0 = toastItem;
        oplusToastUI$ToastItem$$ExternalSyntheticLambda1.f$1 = iTransientNotificationCallback;
        oplusToastUI$ToastItem$$ExternalSyntheticLambda1.f$2 = i;
        VarHandle.storeStoreFence();
        completableFutureSupplyAsync.thenAcceptAsync((Consumer) oplusToastUI$ToastItem$$ExternalSyntheticLambda1, ActivityThread.currentActivityThread().getExecutor());
    }
}
