package com.oplus.systemui.statusbar.pipeline.wifi.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.TransportInfo;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.statusbar.pipeline.wifi.shared.OplusWifiModel;
import com.oplus.systemui.statusbar.util.WifiUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusWifiRepositoryImpl$oplusWifiModel$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Handler $bgHandler;
    final /* synthetic */ BroadcastDispatcher $broadcastDispatcher;
    final /* synthetic */ ConnectivityManager $connectivityManager;
    final /* synthetic */ TableLogBuffer $wifiTableLog;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ OplusWifiRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusWifiRepositoryImpl$oplusWifiModel$1(ConnectivityManager connectivityManager, Handler handler, BroadcastDispatcher broadcastDispatcher, TableLogBuffer tableLogBuffer, OplusWifiRepositoryImpl oplusWifiRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$connectivityManager = connectivityManager;
        this.$bgHandler = handler;
        this.$broadcastDispatcher = broadcastDispatcher;
        this.$wifiTableLog = tableLogBuffer;
        this.this$0 = oplusWifiRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusWifiRepositoryImpl$oplusWifiModel$1 oplusWifiRepositoryImpl$oplusWifiModel$1 = new OplusWifiRepositoryImpl$oplusWifiModel$1(this.$connectivityManager, this.$bgHandler, this.$broadcastDispatcher, this.$wifiTableLog, this.this$0, continuation);
        oplusWifiRepositoryImpl$oplusWifiModel$1.L$0 = obj;
        return oplusWifiRepositoryImpl$oplusWifiModel$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.content.BroadcastReceiver, com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$oplusWifiModel$1$receiver$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v8, types: [android.net.ConnectivityManager$NetworkCallback, com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$oplusWifiModel$1$networkCallback$1, java.lang.Object] */
    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            final Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
            objectRefM.element = new OplusWifiModel();
            NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addTransportType(1).addCapability(12).build();
            IntentFilter intentFilter = new IntentFilter();
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = -1;
            intentFilter.addAction("android.net.wifi2.STATE_CHANGE");
            final TableLogBuffer tableLogBuffer = this.$wifiTableLog;
            final OplusWifiRepositoryImpl oplusWifiRepositoryImpl = this.this$0;
            ?? r11 = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$oplusWifiModel$1$receiver$1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action != null && action.hashCode() == 1536262175 && action.equals("android.net.wifi2.STATE_CHANGE")) {
                        boolean zIsWifiStateConnected = WifiUtils.isWifiStateConnected("OplusWifiRepositoryImpl", (NetworkInfo) intent.getParcelableExtra("networkInfo"));
                        tableLogBuffer.logChange("oplusWifiModel", "android.net.wifi2.STATE_CHANGE", "isWifi2Connected:" + zIsWifiStateConnected + ", currentWifi = " + objectRefM.element);
                        OplusWifiRepositoryImpl.access$logWifiBroadcastReceived(oplusWifiRepositoryImpl, "android.net.wifi2.STATE_CHANGE");
                        if (zIsWifiStateConnected) {
                            return;
                        }
                        Ref.ObjectRef objectRef = objectRefM;
                        objectRef.element = OplusWifiModel.copy$default((OplusWifiModel) objectRef.element, 0, false, false, 0, 13);
                        producerScope.trySend-JP2dKIU(objectRefM.element);
                    }
                }
            };
            final OplusWifiRepositoryImpl oplusWifiRepositoryImpl2 = this.this$0;
            final TableLogBuffer tableLogBuffer2 = this.$wifiTableLog;
            ?? r2 = new ConnectivityManager.NetworkCallback() { // from class: com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$oplusWifiModel$1$networkCallback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    TransportInfo transportInfo = networkCapabilities.getTransportInfo();
                    WifiInfo wifiInfo = transportInfo instanceof WifiInfo ? (WifiInfo) transportInfo : null;
                    if (wifiInfo != null && !wifiInfo.isPrimary()) {
                        boolean zHasCapability = networkCapabilities.hasCapability(16);
                        boolean z = zHasCapability && WifiUtils.isDualWifiConnected(oplusWifiRepositoryImpl2.context);
                        if (z) {
                            intRef.element = network.getNetId();
                        }
                        Ref.IntRef intRef2 = intRef;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            MobileInputLogger$.ExternalSyntheticOutline0.m(intRef2.element, "OplusWifiRepositoryImpl-->", "SystemUi--Statusbar", TextFieldImplKt$.ExternalSyntheticOutline0.m("onCapabilitiesChanged valid = ", ", doubleWifi = ", ", secondaryWiFiNetworkId = ", zHasCapability, z));
                        }
                        Ref.ObjectRef objectRef = objectRefM;
                        objectRef.element = OplusWifiModel.copy$default((OplusWifiModel) objectRef.element, 0, z, false, 0, 13);
                        producerScope.trySend-JP2dKIU(objectRefM.element);
                    } else if (wifiInfo != null && wifiInfo.isPrimary()) {
                        Ref.ObjectRef objectRef2 = objectRefM;
                        objectRef2.element = OplusWifiModel.copy$default((OplusWifiModel) objectRef2.element, network.getNetId(), false, WifiUtils.isPassPointAp(oplusWifiRepositoryImpl2.context), wifiInfo.getWifiStandard(), 2);
                        producerScope.trySend-JP2dKIU(objectRefM.element);
                    }
                    tableLogBuffer2.logChange("oplusWifiModel", "onCapabilitiesChanged", ((OplusWifiModel) objectRefM.element).toString());
                    OplusWifiRepositoryImpl.access$logOnCapabilitiesChanged(oplusWifiRepositoryImpl2, network);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public final void onLost(Network network) {
                    boolean z;
                    int netId = network.getNetId();
                    if (intRef.element == network.getNetId()) {
                        Ref.IntRef intRef2 = intRef;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onLost secondaryWiFiNetworkId: ", intRef2.element, netId, " networkId:", "OplusWifiRepositoryImpl-->"));
                        }
                        Ref.ObjectRef objectRef = objectRefM;
                        objectRef.element = OplusWifiModel.copy$default((OplusWifiModel) objectRef.element, 0, false, false, 0, 13);
                        producerScope.trySend-JP2dKIU(objectRefM.element);
                        intRef.element = -1;
                        return;
                    }
                    boolean z2 = true;
                    if (netId == ((OplusWifiModel) objectRefM.element).getNetworkId()) {
                        Ref.ObjectRef objectRef2 = objectRefM;
                        objectRef2.element = OplusWifiModel.copy$default((OplusWifiModel) objectRef2.element, 0, false, false, 0, 3);
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zIsDualWifiConnected = WifiUtils.isDualWifiConnected(oplusWifiRepositoryImpl2.context);
                    Ref.IntRef intRef3 = intRef;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusWifiRepositoryImpl-->", "SystemUi--Statusbar", FloatList$.ExternalSyntheticOutline0.m("onLost secondaryWiFiNetworkId: ", intRef3.element, netId, " networkId:", " doubleWifi:"), zIsDualWifiConnected);
                    }
                    if (zIsDualWifiConnected) {
                        z2 = z;
                    } else {
                        Ref.ObjectRef objectRef3 = objectRefM;
                        objectRef3.element = OplusWifiModel.copy$default((OplusWifiModel) objectRef3.element, 0, false, false, 0, 13);
                    }
                    TableLogBuffer tableLogBuffer3 = tableLogBuffer2;
                    Object obj2 = objectRefM.element;
                    StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("networkId:", netId, ",changed:", ",doubleWifi:", z2);
                    sbM.append(zIsDualWifiConnected);
                    sbM.append(", currentWifi:");
                    sbM.append(obj2);
                    tableLogBuffer3.logChange("oplusWifiModel", "onLost", sbM.toString());
                    OplusWifiRepositoryImpl.access$logOnLost(oplusWifiRepositoryImpl2, network);
                    if (z2) {
                        producerScope.trySend-JP2dKIU(objectRefM.element);
                    }
                }
            };
            this.$connectivityManager.registerNetworkCallback(networkRequestBuild, r2, this.$bgHandler);
            BroadcastDispatcher.registerReceiverWithHandler$default(this.$broadcastDispatcher, (BroadcastReceiver) r11, intentFilter, this.$bgHandler, (UserHandle) null, 0, 56);
            ConnectivityManager connectivityManager = this.$connectivityManager;
            BroadcastDispatcher broadcastDispatcher = this.$broadcastDispatcher;
            OplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0 oplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0 = new OplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0();
            oplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0.f$0 = connectivityManager;
            oplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0.f$1 = r2;
            oplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0.f$2 = broadcastDispatcher;
            oplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0.f$3 = r11;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(objectRefM);
            this.L$2 = SpillingKt.nullOutSpilledVariable(networkRequestBuild);
            this.L$3 = SpillingKt.nullOutSpilledVariable(intentFilter);
            this.L$4 = SpillingKt.nullOutSpilledVariable(intRef);
            this.L$5 = SpillingKt.nullOutSpilledVariable((Object) r11);
            this.L$6 = SpillingKt.nullOutSpilledVariable((Object) r2);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, oplusWifiRepositoryImpl$oplusWifiModel$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
