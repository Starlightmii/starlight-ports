package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.DynamicIconModel;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
public class DynamicIconSetViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DynamicIconSetViewModel";
    private final StateFlow activeModelFlow;
    private final Flow combineFlow;
    private DynamicIconModel currentActiveModel;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private final StateFlow iconMapFlow;
    private final StateFlow isTipHidden;
    private final CoroutineScope scope;
    private final StateFlow tipFlow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconSetViewModel$isTipHidden$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = DynamicIconSetViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
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
                DynamicIconSetViewModel dynamicIconSetViewModel = DynamicIconSetViewModel.this;
                DynamicIconSetViewModel$isTipHidden$1$receiver$1 dynamicIconSetViewModel$isTipHidden$1$receiver$1 = new DynamicIconSetViewModel$isTipHidden$1$receiver$1();
                dynamicIconSetViewModel$isTipHidden$1$receiver$1.$$this$callbackFlow = producerScope;
                dynamicIconSetViewModel$isTipHidden$1$receiver$1.this$0 = dynamicIconSetViewModel;
                VarHandle.storeStoreFence();
                HiddenIconDispatcher hiddenIconDispatcher = dynamicIconSetViewModel.hiddenIconDispatcher;
                StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
                hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, dynamicIconSetViewModel$isTipHidden$1$receiver$1);
                producerScope.trySend-JP2dKIU(Boxing.boxBoolean(HiddenIconDispatcher.Companion.isValid(DynamicIconSetViewModel.this.hiddenIconDispatcher.getHiddenAreas(statusBarLocation))));
                DynamicIconSetViewModel dynamicIconSetViewModel2 = DynamicIconSetViewModel.this;
                DynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0 dynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0 = new DynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0();
                dynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0.f$0 = dynamicIconSetViewModel2;
                dynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0.f$1 = dynamicIconSetViewModel$isTipHidden$1$receiver$1;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(dynamicIconSetViewModel$isTipHidden$1$receiver$1);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, dynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    public DynamicIconSetViewModel(CoroutineScope coroutineScope, HiddenIconDispatcher hiddenIconDispatcher, DynamicEventViewModel dynamicEventViewModel, PrivacySpeakerDynamicViewModel privacySpeakerDynamicViewModel, ScenarioAssistantDynamicViewModel scenarioAssistantDynamicViewModel, SecurePaymentDynamicViewModel securePaymentDynamicViewModel, StealthModeDynamicViewModel stealthModeDynamicViewModel) {
        this.scope = coroutineScope;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(dynamicEventViewModel.getIconModel(), privacySpeakerDynamicViewModel.getIconModel(), scenarioAssistantDynamicViewModel.getIconModel(), securePaymentDynamicViewModel.getIconModel(), stealthModeDynamicViewModel.getIconModel(), new DynamicIconSetViewModel$combineFlow$1(null)));
        this.combineFlow = flowDistinctUntilChanged;
        DynamicIconSetViewModel$special$$inlined$map$1 dynamicIconSetViewModel$special$$inlined$map$1 = new DynamicIconSetViewModel$special$$inlined$map$1();
        dynamicIconSetViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = flowDistinctUntilChanged;
        VarHandle.storeStoreFence();
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.iconMapFlow = FlowKt.stateIn(dynamicIconSetViewModel$special$$inlined$map$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), MapsKt__MapsKt.emptyMap());
        StateFlow stateFlowStateIn = FlowKt.stateIn(FlowKt.transformLatest(flowDistinctUntilChanged, new DynamicIconSetViewModel$activeModelFlow$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.activeModelFlow = stateFlowStateIn;
        this.isTipHidden = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.callbackFlow(new AnonymousClass1(null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.FALSE);
        DynamicIconSetViewModel$special$$inlined$map$2 dynamicIconSetViewModel$special$$inlined$map$2 = new DynamicIconSetViewModel$special$$inlined$map$2();
        dynamicIconSetViewModel$special$$inlined$map$2.$this_unsafeTransform$inlined = stateFlowStateIn;
        dynamicIconSetViewModel$special$$inlined$map$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.tipFlow = FlowKt.stateIn(dynamicIconSetViewModel$special$$inlined$map$2, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
    }

    public static final DynamicIconModel access$selectActiveModel(DynamicIconSetViewModel dynamicIconSetViewModel, Set set, DynamicIconModel dynamicIconModel) {
        dynamicIconSetViewModel.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : set) {
            DynamicIconModel dynamicIconModel2 = (DynamicIconModel) obj;
            if (dynamicIconModel2.getTipText().length() != 0 && dynamicIconModel2.getTipDuration() != 0) {
                if (dynamicIconModel2.getTipDuration() > 0) {
                    if ((dynamicIconModel2.getTipDuration() + dynamicIconModel2.getUpdateTime()) - System.currentTimeMillis() <= 0) {
                    }
                }
                if (dynamicIconModel2.getShouldAlert() || (dynamicIconModel != null && dynamicIconModel2.getType() == dynamicIconModel.getType())) {
                    arrayList.add(obj);
                }
            }
        }
        DynamicIconSetViewModel$selectActiveModel$$inlined$compareBy$1 dynamicIconSetViewModel$selectActiveModel$$inlined$compareBy$1 = new DynamicIconSetViewModel$selectActiveModel$$inlined$compareBy$1();
        DynamicIconSetViewModel$selectActiveModel$$inlined$thenByDescending$1 dynamicIconSetViewModel$selectActiveModel$$inlined$thenByDescending$1 = new DynamicIconSetViewModel$selectActiveModel$$inlined$thenByDescending$1();
        dynamicIconSetViewModel$selectActiveModel$$inlined$thenByDescending$1.$this_thenByDescending = dynamicIconSetViewModel$selectActiveModel$$inlined$compareBy$1;
        VarHandle.storeStoreFence();
        return (DynamicIconModel) CollectionsKt___CollectionsKt.minWithOrNull(arrayList, dynamicIconSetViewModel$selectActiveModel$$inlined$thenByDescending$1);
    }

    private static /* synthetic */ void getActiveModelFlow$annotations() {
    }

    public final StateFlow getIconMapFlow() {
        return this.iconMapFlow;
    }

    public final StateFlow getTipFlow() {
        return this.tipFlow;
    }

    public final StateFlow isTipHidden() {
        return this.isTipHidden;
    }
}
