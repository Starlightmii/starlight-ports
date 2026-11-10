package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.SimStateModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileConnectionsRepositoryImpl$simState$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BroadcastDispatcher $broadcastDispatcher;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$simState$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
        final /* synthetic */ Ref.ObjectRef<SimStateModel> $model;
        int label;
        final /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Ref.ObjectRef objectRef, OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl, ProducerScope producerScope, Continuation continuation) {
            super(2, continuation);
            this.$model = objectRef;
            this.this$0 = oplusMobileConnectionsRepositoryImpl;
            this.$$this$conflatedCallbackFlow = producerScope;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$model, this.this$0, this.$$this$conflatedCallbackFlow, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<SimStateModel> objectRef = this.$model;
            boolean[] simInserted = ((SimStateModel) objectRef.element).getSimInserted();
            boolean[] zArrCopyOf = Arrays.copyOf(simInserted, simInserted.length);
            boolean[] simForbidden = ((SimStateModel) this.$model.element).getSimForbidden();
            boolean[] zArrCopyOf2 = Arrays.copyOf(simForbidden, simForbidden.length);
            boolean[] simStateIMSI = ((SimStateModel) this.$model.element).getSimStateIMSI();
            boolean[] zArrCopyOf3 = Arrays.copyOf(simStateIMSI, simStateIMSI.length);
            boolean[] simLoaded = ((SimStateModel) this.$model.element).getSimLoaded();
            objectRef.element = new SimStateModel(zArrCopyOf, zArrCopyOf2, zArrCopyOf3, Arrays.copyOf(simLoaded, simLoaded.length));
            this.this$0.updateSimState((SimStateModel) this.$model.element, -1);
            Ref.ObjectRef<SimStateModel> objectRef2 = this.$model;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("handleSimStatusChange init:", objectRef2.element, "OplusMobileConnectionsRepository-->", "SystemUi--Statusbar");
            }
            this.this$0.mobileSummaryLog.logChange("OplusMobileConnectionsRepository", "handleSimStatusChange_init", ((SimStateModel) this.$model.element).toString());
            this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(this.$model.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileConnectionsRepositoryImpl$simState$1(BroadcastDispatcher broadcastDispatcher, OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$broadcastDispatcher = broadcastDispatcher;
        this.this$0 = oplusMobileConnectionsRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileConnectionsRepositoryImpl$simState$1 oplusMobileConnectionsRepositoryImpl$simState$1 = new OplusMobileConnectionsRepositoryImpl$simState$1(this.$broadcastDispatcher, this.this$0, continuation);
        oplusMobileConnectionsRepositoryImpl$simState$1.L$0 = obj;
        return oplusMobileConnectionsRepositoryImpl$simState$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r11v1, types: [android.content.BroadcastReceiver, com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$simState$1$serviceStateReceiver$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.BroadcastReceiver, com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$simState$1$receiver$1, java.lang.Object] */
    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            final Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
            objectRefM.element = new SimStateModel();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(1000);
            intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            final OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = this.this$0;
            ?? r8 = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$simState$1$receiver$1
                /* JADX WARN: Code duplicated, block: B:33:0x00d5  */
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action != null && action.hashCode() == -229777127 && action.equals("android.intent.action.SIM_STATE_CHANGED")) {
                        boolean booleanExtra = intent.getBooleanExtra("rebroadcastOnUnlock", false);
                        oplusMobileConnectionsRepositoryImpl.logger.logMobileConnectionBroadcast("android.intent.action.SIM_STATE_CHANGED_" + booleanExtra);
                        if (booleanExtra) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                Log.d("SystemUi--Statusbar", "OplusMobileConnectionsRepository-->handleSimStatusChange onSimStatusChanged rebroadcast");
                                return;
                            }
                            return;
                        }
                        int intExtra = intent.getIntExtra("phone", -1);
                        if (intExtra > -1) {
                            Ref.ObjectRef objectRef = objectRefM;
                            boolean[] simInserted = ((SimStateModel) objectRef.element).getSimInserted();
                            boolean[] zArrCopyOf = Arrays.copyOf(simInserted, simInserted.length);
                            boolean[] simForbidden = ((SimStateModel) objectRefM.element).getSimForbidden();
                            boolean[] zArrCopyOf2 = Arrays.copyOf(simForbidden, simForbidden.length);
                            boolean[] simStateIMSI = ((SimStateModel) objectRefM.element).getSimStateIMSI();
                            boolean[] zArrCopyOf3 = Arrays.copyOf(simStateIMSI, simStateIMSI.length);
                            boolean[] simLoaded = ((SimStateModel) objectRefM.element).getSimLoaded();
                            objectRef.element = new SimStateModel(zArrCopyOf, zArrCopyOf2, zArrCopyOf3, Arrays.copyOf(simLoaded, simLoaded.length));
                            oplusMobileConnectionsRepositoryImpl.updateSimState((SimStateModel) objectRefM.element, intExtra);
                            String stringExtra = intent.getStringExtra("ss");
                            if (stringExtra != null) {
                                switch (stringExtra.hashCode()) {
                                    case -2044189691:
                                        if (stringExtra.equals("LOADED")) {
                                            ((SimStateModel) objectRefM.element).getSimLoaded()[intExtra] = true;
                                        }
                                        break;
                                    case 2251386:
                                        if (stringExtra.equals("IMSI")) {
                                            ((SimStateModel) objectRefM.element).getSimStateIMSI()[intExtra] = true;
                                        }
                                        break;
                                    case 433141802:
                                        if (stringExtra.equals("UNKNOWN")) {
                                            ((SimStateModel) objectRefM.element).getSimStateIMSI()[intExtra] = false;
                                            ((SimStateModel) objectRefM.element).getSimLoaded()[intExtra] = false;
                                        }
                                        break;
                                    case 1034051831:
                                        if (stringExtra.equals("NOT_READY")) {
                                            ((SimStateModel) objectRefM.element).getSimStateIMSI()[intExtra] = false;
                                            ((SimStateModel) objectRefM.element).getSimLoaded()[intExtra] = false;
                                        }
                                        break;
                                    case 1924388665:
                                        if (stringExtra.equals("ABSENT")) {
                                            ((SimStateModel) objectRefM.element).getSimStateIMSI()[intExtra] = false;
                                            ((SimStateModel) objectRefM.element).getSimLoaded()[intExtra] = false;
                                        }
                                        break;
                                }
                            }
                            Ref.ObjectRef objectRef2 = objectRefM;
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                String action2 = intent.getAction();
                                Object obj2 = objectRef2.element;
                                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(intExtra, "handleSimStatusChange ", action2, " ,slotId = ", " ,stateExtra = ");
                                sbM.append(stringExtra);
                                sbM.append(" ,model = ");
                                sbM.append(obj2);
                                Log.d("SystemUi--Statusbar", "OplusMobileConnectionsRepository-->".concat(sbM.toString()));
                            }
                        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", "OplusMobileConnectionsRepository-->handleSimStatusChange slotId <= -1");
                        }
                        oplusMobileConnectionsRepositoryImpl.mobileSummaryLog.logChange("OplusMobileConnectionsRepository", "action_sim_state_changed", ((SimStateModel) objectRefM.element).toString());
                        Object obj3 = producerScope.trySend-JP2dKIU(objectRefM.element);
                        if (obj3 instanceof ChannelResult.Failed) {
                            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, "OplusMobileConnectionsRepository", "handleSimStatusChange send fail " + ChannelResult.exceptionOrNull-impl(obj3));
                        }
                        if (obj3 instanceof ChannelResult.Closed) {
                            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, "OplusMobileConnectionsRepository", "handleSimStatusChange close " + ChannelResult.exceptionOrNull-impl(obj3));
                        }
                    }
                }
            };
            BroadcastDispatcher.registerReceiver$default(this.$broadcastDispatcher, (BroadcastReceiver) r8, intentFilter, ExecutorsKt.asExecutor(this.this$0.bgDispatcher), (UserHandle) null, 0, 56);
            IntentFilter intentFilter2 = new IntentFilter("android.intent.action.SERVICE_STATE");
            final OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl2 = this.this$0;
            final BroadcastDispatcher broadcastDispatcher = this.$broadcastDispatcher;
            ?? r11 = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$simState$1$serviceStateReceiver$1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.intent.action.SERVICE_STATE")) {
                        oplusMobileConnectionsRepositoryImpl2.logger.logMobileConnectionBroadcast("android.intent.action.SERVICE_STATE");
                        Ref.ObjectRef objectRef = objectRefM;
                        boolean[] simInserted = ((SimStateModel) objectRef.element).getSimInserted();
                        boolean[] zArrCopyOf = Arrays.copyOf(simInserted, simInserted.length);
                        boolean[] simForbidden = ((SimStateModel) objectRefM.element).getSimForbidden();
                        boolean[] zArrCopyOf2 = Arrays.copyOf(simForbidden, simForbidden.length);
                        boolean[] simStateIMSI = ((SimStateModel) objectRefM.element).getSimStateIMSI();
                        boolean[] zArrCopyOf3 = Arrays.copyOf(simStateIMSI, simStateIMSI.length);
                        boolean[] simLoaded = ((SimStateModel) objectRefM.element).getSimLoaded();
                        objectRef.element = new SimStateModel(zArrCopyOf, zArrCopyOf2, zArrCopyOf3, Arrays.copyOf(simLoaded, simLoaded.length));
                        oplusMobileConnectionsRepositoryImpl2.updateSimState((SimStateModel) objectRefM.element, -1);
                        broadcastDispatcher.unregisterReceiver(this);
                        oplusMobileConnectionsRepositoryImpl2.mobileSummaryLog.logChange("OplusMobileConnectionsRepository", "action_service_state", ((SimStateModel) objectRefM.element).toString());
                        Ref.ObjectRef objectRef2 = objectRefM;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("handleSimStatusChange service state changed ", objectRef2.element, "OplusMobileConnectionsRepository-->", "SystemUi--Statusbar");
                        }
                        producerScope.trySend-JP2dKIU(objectRefM.element);
                    }
                }
            };
            BroadcastDispatcher.registerReceiver$default(this.$broadcastDispatcher, (BroadcastReceiver) r11, intentFilter2, ExecutorsKt.asExecutor(this.this$0.bgDispatcher), (UserHandle) null, 0, 56);
            BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(this.this$0.bgDispatcher), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(objectRefM, this.this$0, producerScope, null), 3);
            BroadcastDispatcher broadcastDispatcher2 = this.$broadcastDispatcher;
            OplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0 oplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0 = new OplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0();
            oplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0.f$0 = broadcastDispatcher2;
            oplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0.f$1 = r8;
            oplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0.f$2 = r11;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(objectRefM);
            this.L$2 = SpillingKt.nullOutSpilledVariable(intentFilter);
            this.L$3 = SpillingKt.nullOutSpilledVariable((Object) r8);
            this.L$4 = SpillingKt.nullOutSpilledVariable(intentFilter2);
            this.L$5 = SpillingKt.nullOutSpilledVariable((Object) r11);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, oplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
