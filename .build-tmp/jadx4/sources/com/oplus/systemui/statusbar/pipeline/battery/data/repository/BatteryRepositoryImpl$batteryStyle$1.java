package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.statusbar.observer.BatteryStyleObserver;
import com.oplus.systemui.statusbar.observer.PowerPercentObserver;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.BatteryStyleModel;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class BatteryRepositoryImpl$batteryStyle$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ CoroutineScope $scope;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ BatteryRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryRepositoryImpl$batteryStyle$1(BatteryRepositoryImpl batteryRepositoryImpl, CoroutineScope coroutineScope, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryRepositoryImpl;
        this.$scope = coroutineScope;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        BatteryRepositoryImpl$batteryStyle$1 batteryRepositoryImpl$batteryStyle$1 = new BatteryRepositoryImpl$batteryStyle$1(this.this$0, this.$scope, continuation);
        batteryRepositoryImpl$batteryStyle$1.L$0 = obj;
        return batteryRepositoryImpl$batteryStyle$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$batteryStyle$1$powerPercentObserver$1, com.oplusos.systemui.common.observer.AbstractObserver, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$batteryStyle$1$batteryStyleObserver$1, com.oplusos.systemui.common.observer.AbstractObserver, java.lang.Object] */
    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            final Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
            FlavorTwoFeatureOption.INSTANCE.getClass();
            objectRefM.element = new BatteryStyleModel(FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? 3 : 1);
            final BatteryRepositoryImpl batteryRepositoryImpl = this.this$0;
            ?? r2 = new PowerPercentObserver() { // from class: com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$batteryStyle$1$powerPercentObserver$1
                @Override // com.oplusos.systemui.common.observer.AbstractObserver
                public final void onChange(boolean z) {
                    super.onChange(z);
                    BatteryRepositoryImpl batteryRepositoryImpl2 = batteryRepositoryImpl;
                    int userId = batteryRepositoryImpl2.userTracker.getUserId();
                    Ref.ObjectRef objectRef = objectRefM;
                    objectRef.element = BatteryStyleModel.copy$default((BatteryStyleModel) objectRef.element, batteryRepositoryImpl2.getDisplayPowerPercent(userId, ((BatteryStyleModel) objectRef.element).getIconStyle()), 0, null, 0, false, 126);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("percent style changed, model: ", objectRef.element, "BatteryRepository-->", "SystemUi--Statusbar");
                    }
                    producerScope.trySend-JP2dKIU(objectRef.element);
                }
            };
            final BatteryRepositoryImpl batteryRepositoryImpl2 = this.this$0;
            ?? r4 = new BatteryStyleObserver() { // from class: com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$batteryStyle$1$batteryStyleObserver$1
                @Override // com.oplusos.systemui.common.observer.AbstractObserver
                public final void onChange(boolean z) {
                    super.onChange(z);
                    BatteryRepositoryImpl batteryRepositoryImpl3 = batteryRepositoryImpl2;
                    int batteryStyle = batteryRepositoryImpl3.getBatteryStyle(batteryRepositoryImpl3.userTracker.getUserId());
                    Ref.ObjectRef objectRef = objectRefM;
                    objectRef.element = BatteryStyleModel.copy$default((BatteryStyleModel) objectRef.element, 0, batteryStyle, null, 0, false, 125);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("battery style changed, model: ", objectRef.element, "BatteryRepository-->", "SystemUi--Statusbar");
                    }
                    producerScope.trySend-JP2dKIU(objectRef.element);
                }
            };
            BatteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1 batteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1 = new BatteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1();
            BatteryRepositoryImpl$batteryStyle$1$configurationListener$1 batteryRepositoryImpl$batteryStyle$1$configurationListener$1 = new BatteryRepositoryImpl$batteryStyle$1$configurationListener$1();
            batteryRepositoryImpl$batteryStyle$1$configurationListener$1.$model = objectRefM;
            batteryRepositoryImpl$batteryStyle$1$configurationListener$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            BatteryRepositoryImpl batteryRepositoryImpl3 = this.this$0;
            CoroutineScope coroutineScope = this.$scope;
            BatteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1 batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1 = new BatteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1();
            batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1.this$0 = batteryRepositoryImpl3;
            batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1.$model = objectRefM;
            batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1.$scope = coroutineScope;
            batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            batteryRepositoryImpl3.userTracker.addCallback(batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1, this.this$0.bgExecutor);
            r4.registerForUserSwitch(this.this$0.context, -1, false);
            r2.registerForUserSwitch(this.this$0.context, -1, false);
            this.this$0.flavorManager.getMiniCapsuleManager().registerCapsuleShowListener(batteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1);
            this.this$0.configurationController.addCallback(batteryRepositoryImpl$batteryStyle$1$configurationListener$1);
            BatteryRepositoryImpl batteryRepositoryImpl4 = this.this$0;
            BatteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0 batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0 = new BatteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0();
            batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0.f$0 = r4;
            batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0.f$1 = batteryRepositoryImpl4;
            batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0.f$2 = r2;
            batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0.f$3 = batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1;
            batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0.f$4 = batteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1;
            batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0.f$5 = batteryRepositoryImpl$batteryStyle$1$configurationListener$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(objectRefM);
            this.L$2 = SpillingKt.nullOutSpilledVariable((Object) r2);
            this.L$3 = SpillingKt.nullOutSpilledVariable((Object) r4);
            this.L$4 = SpillingKt.nullOutSpilledVariable(batteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1);
            this.L$5 = SpillingKt.nullOutSpilledVariable(batteryRepositoryImpl$batteryStyle$1$configurationListener$1);
            this.L$6 = SpillingKt.nullOutSpilledVariable(batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, batteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
