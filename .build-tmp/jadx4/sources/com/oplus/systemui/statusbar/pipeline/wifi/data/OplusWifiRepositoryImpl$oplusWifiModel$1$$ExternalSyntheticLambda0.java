package com.oplus.systemui.statusbar.pipeline.wifi.data;

import android.net.ConnectivityManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ ConnectivityManager f$0;
    public /* synthetic */ OplusWifiRepositoryImpl$oplusWifiModel$1$networkCallback$1 f$1;
    public /* synthetic */ BroadcastDispatcher f$2;
    public /* synthetic */ OplusWifiRepositoryImpl$oplusWifiModel$1$receiver$1 f$3;

    public final Object invoke() {
        ConnectivityManager connectivityManager = this.f$0;
        OplusWifiRepositoryImpl$oplusWifiModel$1$networkCallback$1 oplusWifiRepositoryImpl$oplusWifiModel$1$networkCallback$1 = this.f$1;
        BroadcastDispatcher broadcastDispatcher = this.f$2;
        OplusWifiRepositoryImpl$oplusWifiModel$1$receiver$1 oplusWifiRepositoryImpl$oplusWifiModel$1$receiver$1 = this.f$3;
        connectivityManager.unregisterNetworkCallback(oplusWifiRepositoryImpl$oplusWifiModel$1$networkCallback$1);
        broadcastDispatcher.unregisterReceiver(oplusWifiRepositoryImpl$oplusWifiModel$1$receiver$1);
        return Unit.INSTANCE;
    }
}
