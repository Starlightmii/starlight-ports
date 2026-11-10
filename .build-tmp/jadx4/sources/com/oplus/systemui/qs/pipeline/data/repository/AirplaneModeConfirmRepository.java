package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.telephony.domain.interactor.TelephonyInteractor;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AirplaneModeConfirmRepository {
    private static final String BROADCAST_CONFIRM_ACTION = "oplus.intent.action.AIRPLANEMODE_CONFIRM";
    private static final String BROADCAST_PERMISSION = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final Companion Companion = new Companion();
    private static final String TAG = "AirplaneModeConfirmRepository";
    private final SharedFlow airplaneModeConfirmEvent;
    private final SharedFlow broadcastConfirmEvent;
    private final MutableSharedFlow extraConfirmEvent;
    private final StateFlow isAirplaneModeNeedConfirm;
    private final CoroutineScope scope;
    private final TelephonyInteractor telephonyInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.AirplaneModeConfirmRepository$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return AirplaneModeConfirmRepository.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AirplaneModeConfirmRepository.this.telephonyInteractor.isInCall();
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public AirplaneModeConfirmRepository(CoroutineScope coroutineScope, BroadcastDispatcher broadcastDispatcher, TelephonyInteractor telephonyInteractor) {
        this.scope = coroutineScope;
        this.telephonyInteractor = telephonyInteractor;
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new AirplaneModeConfirmRepository$isAirplaneModeNeedConfirm$1(2, null));
        AirplaneModeConfirmRepository$special$$inlined$map$1 airplaneModeConfirmRepository$special$$inlined$map$1 = new AirplaneModeConfirmRepository$special$$inlined$map$1();
        airplaneModeConfirmRepository$special$$inlined$map$1.$this_unsafeTransform$inlined = flowConflatedCallbackFlow;
        airplaneModeConfirmRepository$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(airplaneModeConfirmRepository$special$$inlined$map$1, coroutineScope, companion.getEagerly(), Boolean.valueOf(CustomizeFeatureOption.sIsAirplanemodeConfirm));
        this.isAirplaneModeNeedConfirm = stateFlowStateIn;
        this.extraConfirmEvent = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
        this.broadcastConfirmEvent = FlowKt.shareIn(FlowKt.onEach(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter(BROADCAST_CONFIRM_ACTION), (UserHandle) null, 0, "oplus.permission.OPLUS_COMPONENT_SAFE", 6), new AirplaneModeConfirmRepository$broadcastConfirmEvent$1(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.airplaneModeConfirmEvent = FlowKt.shareIn(FlowKt.flatMapConcat(stateFlowStateIn, new AirplaneModeConfirmRepository$airplaneModeConfirmEvent$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
    }

    public final SharedFlow getAirplaneModeConfirmEvent() {
        return this.airplaneModeConfirmEvent;
    }

    public final void init() {
        StateFlow stateFlow = this.isAirplaneModeNeedConfirm;
        AirplaneModeConfirmRepository$init$$inlined$filter$1 airplaneModeConfirmRepository$init$$inlined$filter$1 = new AirplaneModeConfirmRepository$init$$inlined$filter$1();
        airplaneModeConfirmRepository$init$$inlined$filter$1.$this_unsafeTransform$inlined = stateFlow;
        VarHandle.storeStoreFence();
        FlowKt.launchIn(FlowKt.flatMapConcat(airplaneModeConfirmRepository$init$$inlined$filter$1, new AnonymousClass2(null)), this.scope);
    }

    public final boolean sendConfirmEventWhenNecessary() {
        if (!((Boolean) this.isAirplaneModeNeedConfirm.getValue()).booleanValue()) {
            return false;
        }
        if (!((Boolean) this.telephonyInteractor.isInCall().getValue()).booleanValue()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "sendConfirmEventWhenNecessary: not inCall, ignore", null, 8);
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "sendConfirmEventWhenNecessary: ", null, 8);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new sendConfirmEventWhenNecessary.1(this, (Continuation) null), 3);
        return true;
    }
}
