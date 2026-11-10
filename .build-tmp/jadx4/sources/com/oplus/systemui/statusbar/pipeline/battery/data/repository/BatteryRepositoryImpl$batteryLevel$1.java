package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import android.os.BatteryManager;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplusos.systemui.common.battery.OplusBatteryController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class BatteryRepositoryImpl$batteryLevel$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ BatteryRepositoryImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$batteryLevel$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
        final /* synthetic */ Ref.IntRef $level;
        int I$0;
        Object L$0;
        int label;
        final /* synthetic */ BatteryRepositoryImpl this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$batteryLevel$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01481 extends SuspendLambda implements Function2 {
            final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
            final /* synthetic */ int $initLevel;
            final /* synthetic */ Ref.IntRef $level;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01481(int i, Ref.IntRef intRef, ProducerScope producerScope, Continuation continuation) {
                super(2, continuation);
                this.$initLevel = i;
                this.$level = intRef;
                this.$$this$conflatedCallbackFlow = producerScope;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01481(this.$initLevel, this.$level, this.$$this$conflatedCallbackFlow, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                int i;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "BatteryRepository", TextFieldBuffer$.ExternalSyntheticOutline0.m(this.$initLevel, this.$level.element, "main init initLevel: ", " level: "), null, 8);
                Ref.IntRef intRef = this.$level;
                if (intRef.element <= 0 && (i = this.$initLevel) > 0) {
                    if (i < 0) {
                        i = 0;
                    }
                    if (i > 100) {
                        i = 100;
                    }
                    intRef.element = i;
                    LogUtil.i$default(LogUtil.TAG_STATUSBAR, "BatteryRepository", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "init level: "), null, 8);
                    this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Boxing.boxInt(this.$level.element));
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BatteryRepositoryImpl batteryRepositoryImpl, Ref.IntRef intRef, ProducerScope producerScope, Continuation continuation) {
            super(2, continuation);
            this.this$0 = batteryRepositoryImpl;
            this.$level = intRef;
            this.$$this$conflatedCallbackFlow = producerScope;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$level, this.$$this$conflatedCallbackFlow, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object systemService = this.this$0.context.getSystemService("batterymanager");
                BatteryManager batteryManager = systemService instanceof BatteryManager ? (BatteryManager) systemService : null;
                int intProperty = batteryManager != null ? batteryManager.getIntProperty(4) : 0;
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "BatteryRepository", TextFieldBuffer$.ExternalSyntheticOutline0.m(intProperty, this.$level.element, "IO init initLevel: ", " level: "), null, 8);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C01481 c01481 = new C01481(intProperty, this.$level, this.$$this$conflatedCallbackFlow, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(batteryManager);
                this.I$0 = intProperty;
                this.label = 1;
                if (BuildersKt.withContext(main, c01481, this) == coroutine_suspended) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryRepositoryImpl$batteryLevel$1(BatteryRepositoryImpl batteryRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        BatteryRepositoryImpl$batteryLevel$1 batteryRepositoryImpl$batteryLevel$1 = new BatteryRepositoryImpl$batteryLevel$1(this.this$0, continuation);
        batteryRepositoryImpl$batteryLevel$1.L$0 = obj;
        return batteryRepositoryImpl$batteryLevel$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.IntRef intRef = new Ref.IntRef();
            BatteryRepositoryImpl batteryRepositoryImpl = this.this$0;
            BatteryRepositoryImpl$batteryLevel$1$listener$1 batteryRepositoryImpl$batteryLevel$1$listener$1 = new BatteryRepositoryImpl$batteryLevel$1$listener$1();
            batteryRepositoryImpl$batteryLevel$1$listener$1.this$0 = batteryRepositoryImpl;
            batteryRepositoryImpl$batteryLevel$1$listener$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            OplusBatteryController.Companion.getInstance().registerIBatteryStateChanged(batteryRepositoryImpl$batteryLevel$1$listener$1);
            if (intRef.element <= 0) {
                BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.this$0, intRef, producerScope, null), 3);
            }
            BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 = new BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0(1);
            batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0.f$0 = batteryRepositoryImpl$batteryLevel$1$listener$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(intRef);
            this.L$2 = SpillingKt.nullOutSpilledVariable(batteryRepositoryImpl$batteryLevel$1$listener$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
