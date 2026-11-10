package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import android.content.Context;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconViewModel implements OplusMobileIconViewModelCommon {
    private final StateFlow activityVisible;
    private final Lazy cellProvider$delegate;
    private final Context context;
    private final MutableStateFlow homeMobileIconTintColor;
    private final StateFlow icon;
    private final StateFlow isRoaming;
    private final StateFlow isUnstackedUseTheme17;
    private final StateFlow isVisible;
    private final StateFlow mobileActivityResId;
    private final StateFlow networkTypeIcon;
    private final StateFlow networkTypeText;
    private final StateFlow roamId;
    private final Lazy satelliteProvider$delegate;
    public final int subscriptionId;
    private final Flow vmProvider;

    public static OplusCellularIconViewModelImpl $r8$lambda$Zje1awkGLimrMvvvgxlYdqfFpjI(OplusMobileIconViewModel oplusMobileIconViewModel, OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl, ConnectivityConstants connectivityConstants, CoroutineScope coroutineScope, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        return new OplusCellularIconViewModelImpl(oplusMobileIconViewModel.context, oplusMobileIconViewModel.getSubscriptionId(), oplusMobileIconInteractorImpl, connectivityConstants, coroutineScope, oplusMobileIconsInteractor);
    }

    public OplusMobileIconViewModel(Context context, int i, OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl, ConnectivityConstants connectivityConstants, CoroutineScope coroutineScope, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        this.context = context;
        this.subscriptionId = i;
        OplusMobileIconViewModel$$ExternalSyntheticLambda0 oplusMobileIconViewModel$$ExternalSyntheticLambda0 = new OplusMobileIconViewModel$$ExternalSyntheticLambda0();
        oplusMobileIconViewModel$$ExternalSyntheticLambda0.f$0 = this;
        oplusMobileIconViewModel$$ExternalSyntheticLambda0.f$1 = oplusMobileIconInteractorImpl;
        oplusMobileIconViewModel$$ExternalSyntheticLambda0.f$2 = connectivityConstants;
        oplusMobileIconViewModel$$ExternalSyntheticLambda0.f$3 = coroutineScope;
        oplusMobileIconViewModel$$ExternalSyntheticLambda0.f$4 = oplusMobileIconsInteractor;
        VarHandle.storeStoreFence();
        this.cellProvider$delegate = LazyKt__LazyJVMKt.lazy(oplusMobileIconViewModel$$ExternalSyntheticLambda0);
        OplusMobileIconViewModel$$ExternalSyntheticLambda1 oplusMobileIconViewModel$$ExternalSyntheticLambda1 = new OplusMobileIconViewModel$$ExternalSyntheticLambda1();
        oplusMobileIconViewModel$$ExternalSyntheticLambda1.f$0 = this;
        oplusMobileIconViewModel$$ExternalSyntheticLambda1.f$1 = oplusMobileIconInteractorImpl;
        oplusMobileIconViewModel$$ExternalSyntheticLambda1.f$2 = oplusMobileIconsInteractor;
        oplusMobileIconViewModel$$ExternalSyntheticLambda1.f$3 = coroutineScope;
        VarHandle.storeStoreFence();
        this.satelliteProvider$delegate = LazyKt__LazyJVMKt.lazy(oplusMobileIconViewModel$$ExternalSyntheticLambda1);
        Flow flowMapLatest = FlowKt.mapLatest(oplusMobileIconInteractorImpl.isNonTerrestrial(), new OplusMobileIconViewModel$vmProvider$1(this, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(flowMapLatest, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), getCellProvider());
        this.vmProvider = stateFlowStateIn;
        Flow flowTransformLatest = FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$$inlined$flatMapLatest$1(3, null));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.activityVisible = FlowKt.stateIn(flowTransformLatest, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this.mobileActivityResId = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.roamId = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$$inlined$flatMapLatest$3(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.isRoaming = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$$inlined$flatMapLatest$4(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.networkTypeIcon = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$$inlined$flatMapLatest$5(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.networkTypeText = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$$inlined$flatMapLatest$6(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.isUnstackedUseTheme17 = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$.inlined.flatMapLatest.7(3, (Continuation) null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.TRUE);
        this.icon = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$.inlined.flatMapLatest.8(3, (Continuation) null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), oplusMobileIconInteractorImpl.getSignalLevelIcon().getValue());
        this.isVisible = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusMobileIconViewModel$special$.inlined.flatMapLatest.9(3, (Continuation) null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.homeMobileIconTintColor = StateFlowKt.MutableStateFlow(-855638017);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusCellularIconViewModelImpl getCellProvider() {
        return (OplusCellularIconViewModelImpl) this.cellProvider$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusCarrierBasedSatelliteViewModelImpl getSatelliteProvider() {
        return (OplusCarrierBasedSatelliteViewModelImpl) this.satelliteProvider$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getActivityVisible() {
        return this.activityVisible;
    }

    public final MutableStateFlow getHomeMobileIconTintColor() {
        return this.homeMobileIconTintColor;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getIcon() {
        return this.icon;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getMobileActivityResId() {
        return this.mobileActivityResId;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getNetworkTypeIcon() {
        return this.networkTypeIcon;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getNetworkTypeText() {
        return this.networkTypeText;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getRoamId() {
        return this.roamId;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public int getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow isRoaming() {
        return this.isRoaming;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow isUnstackedUseTheme17() {
        return this.isUnstackedUseTheme17;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow isVisible() {
        return this.isVisible;
    }
}
