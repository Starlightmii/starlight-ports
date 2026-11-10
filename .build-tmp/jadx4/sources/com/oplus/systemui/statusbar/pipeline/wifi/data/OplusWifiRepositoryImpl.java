package com.oplus.systemui.statusbar.pipeline.wifi.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Handler;
import android.os.UserHandle;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.statusbar.pipeline.wifi.shared.OplusWifiModel;
import com.oplus.systemui.utils.OplusThemeController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiRepositoryImpl implements OplusWifiRepository {
    private static final String ACTION_WIFI_SWITCH_TO_DATA_NETWORK = "android.net.wifi.WIFI_TO_DATA";
    public static final Companion Companion = new Companion();
    private static final String EXTRA_WIFI_SWITCH_TO_DATA_NETWORK = "wifi_to_data";
    private static final String TAG = "OplusWifiRepositoryImpl";
    private static final String WIFI2_STATE_CHANGE = "android.net.wifi2.STATE_CHANGE";
    private final Context context;
    private final LogBuffer inputLogger;
    private final StateFlow isWifiToData;
    private final OplusThemeController oplusThemeController;
    private final StateFlow oplusWifiModel;
    private final StateFlow wifiTechnicalEnable;
    private final StateFlow wifiThemeAdapterState;
    private final StateFlow wlanBandLabelOff;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$isWifiToData$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ BroadcastDispatcher $broadcastDispatcher;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ OplusWifiRepositoryImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BroadcastDispatcher broadcastDispatcher, OplusWifiRepositoryImpl oplusWifiRepositoryImpl, Continuation continuation) {
            super(2, continuation);
            this.$broadcastDispatcher = broadcastDispatcher;
            this.this$0 = oplusWifiRepositoryImpl;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$broadcastDispatcher, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(OplusWifiRepositoryImpl.ACTION_WIFI_SWITCH_TO_DATA_NETWORK);
                final OplusWifiRepositoryImpl oplusWifiRepositoryImpl = this.this$0;
                BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl$isWifiToData$1$receiver$1
                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (action != null && action.hashCode() == 1739549101 && action.equals("android.net.wifi.WIFI_TO_DATA")) {
                            boolean booleanExtra = intent.getBooleanExtra("wifi_to_data", false);
                            LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusWifiRepositoryImpl", KeyguardLogger$.ExternalSyntheticOutline0.m("ACTION_WIFI_SWITCH_TO_DATA_NETWORK, mIsWifiToData:", booleanExtra), null, 8);
                            OplusWifiRepositoryImpl.access$logWifiBroadcastReceived(oplusWifiRepositoryImpl, "android.net.wifi.WIFI_TO_DATA");
                            producerScope.trySend-JP2dKIU(Boolean.valueOf(booleanExtra));
                        }
                    }
                };
                BroadcastDispatcher.registerReceiver$default(this.$broadcastDispatcher, broadcastReceiver, intentFilter, (Executor) null, UserHandle.ALL, 0, 48);
                BroadcastDispatcher broadcastDispatcher = this.$broadcastDispatcher;
                OplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0 oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0 = new OplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0(0);
                oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0.f$0 = broadcastDispatcher;
                oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0.f$1 = broadcastReceiver;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(intentFilter);
                this.L$2 = SpillingKt.nullOutSpilledVariable(broadcastReceiver);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, oplusWifiRepositoryImpl$isWifiToData$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    public OplusWifiRepositoryImpl(Context context, CoroutineScope coroutineScope, BroadcastDispatcher broadcastDispatcher, ConnectivityManager connectivityManager, Handler handler, LogBuffer logBuffer, TableLogBuffer tableLogBuffer, OplusThemeController oplusThemeController) {
        this.context = context;
        this.inputLogger = logBuffer;
        this.oplusThemeController = oplusThemeController;
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new OplusWifiRepositoryImpl$wifiTechnicalEnable$1(this, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        SharingStarted eagerly = companion.getEagerly();
        Boolean bool = Boolean.FALSE;
        this.wifiTechnicalEnable = FlowKt.stateIn(flowConflatedCallbackFlow, coroutineScope, eagerly, bool);
        this.wlanBandLabelOff = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new OplusWifiRepositoryImpl$wlanBandLabelOff$1(this, null)), coroutineScope, companion.getEagerly(), bool);
        this.oplusWifiModel = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new OplusWifiRepositoryImpl$oplusWifiModel$1(connectivityManager, handler, broadcastDispatcher, tableLogBuffer, this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new OplusWifiModel());
        this.isWifiToData = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new AnonymousClass1(broadcastDispatcher, this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.wifiThemeAdapterState = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new OplusWifiRepositoryImpl$wifiThemeAdapterState$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), calculateAdapterState(oplusThemeController.isThirdTheme));
    }

    public static final void access$logOnCapabilitiesChanged(OplusWifiRepositoryImpl oplusWifiRepositoryImpl, Network network) {
        LogBuffer logBuffer = oplusWifiRepositoryImpl.inputLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        OplusWifiRepositoryImpl$$ExternalSyntheticLambda1 oplusWifiRepositoryImpl$$ExternalSyntheticLambda1 = new OplusWifiRepositoryImpl$$ExternalSyntheticLambda1(0);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, oplusWifiRepositoryImpl$$ExternalSyntheticLambda1, (Throwable) null);
        logMessageObtain.setInt1(network.getNetId());
        logBuffer.commit(logMessageObtain);
    }

    public static final void access$logOnLost(OplusWifiRepositoryImpl oplusWifiRepositoryImpl, Network network) {
        LogBuffer logBuffer = oplusWifiRepositoryImpl.inputLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        OplusWifiRepositoryImpl$$ExternalSyntheticLambda1 oplusWifiRepositoryImpl$$ExternalSyntheticLambda1 = new OplusWifiRepositoryImpl$$ExternalSyntheticLambda1(1);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, oplusWifiRepositoryImpl$$ExternalSyntheticLambda1, (Throwable) null);
        logMessageObtain.setInt1(network.getNetId());
        logBuffer.commit(logMessageObtain);
    }

    public static final void access$logWifiBroadcastReceived(OplusWifiRepositoryImpl oplusWifiRepositoryImpl, String str) {
        LogBuffer logBuffer = oplusWifiRepositoryImpl.inputLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        OplusWifiRepositoryImpl$$ExternalSyntheticLambda1 oplusWifiRepositoryImpl$$ExternalSyntheticLambda1 = new OplusWifiRepositoryImpl$$ExternalSyntheticLambda1(2);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, oplusWifiRepositoryImpl$$ExternalSyntheticLambda1, (Throwable) null);
        logMessageObtain.setStr1(str);
        logBuffer.commit(logMessageObtain);
    }

    public static final void access$logWifiTechnicalEnable(OplusWifiRepositoryImpl oplusWifiRepositoryImpl, boolean z, int i) {
        LogBuffer logBuffer = oplusWifiRepositoryImpl.inputLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        OplusWifiRepositoryImpl$$ExternalSyntheticLambda0 oplusWifiRepositoryImpl$$ExternalSyntheticLambda0 = new OplusWifiRepositoryImpl$$ExternalSyntheticLambda0();
        oplusWifiRepositoryImpl$$ExternalSyntheticLambda0.f$0 = z;
        oplusWifiRepositoryImpl$$ExternalSyntheticLambda0.f$1 = i;
        VarHandle.storeStoreFence();
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, oplusWifiRepositoryImpl$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setBool1(z);
        logMessageObtain.setInt1(i);
        logBuffer.commit(logMessageObtain);
    }

    public final WifiThemeAdapterState calculateAdapterState(boolean z) {
        boolean z2 = z && OplusWifiIconSets.INSTANCE.hasThirdPictureWifiSignalO16(this.context);
        boolean z3 = !z || OplusWifiIconSets.INSTANCE.hasThirdPictureWifiSignalO17(this.context);
        WifiThemeAdapterState wifiThemeAdapterState = new WifiThemeAdapterState(z2, z3);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("calculateAdapterState isThirdTheme:", " isAdaptOs16Independent:", ",isAdaptOs17Independent:", z, z2);
        sbM.append(z3);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sbM.toString(), null, 8);
        return wifiThemeAdapterState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository
    public StateFlow getOplusWifiModel() {
        return this.oplusWifiModel;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository
    public StateFlow getWifiTechnicalEnable() {
        return this.wifiTechnicalEnable;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository
    public StateFlow getWifiThemeAdapterState() {
        return this.wifiThemeAdapterState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository
    public StateFlow getWlanBandLabelOff() {
        return this.wlanBandLabelOff;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository
    public StateFlow isWifiToData() {
        return this.isWifiToData;
    }
}
