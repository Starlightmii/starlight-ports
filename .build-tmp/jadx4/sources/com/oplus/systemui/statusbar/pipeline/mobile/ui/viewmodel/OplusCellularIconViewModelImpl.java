package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import android.content.Context;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.log.table.DiffableExtensionsKt;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplus.systemui.statusbar.pipeline.rus.OplusStatusBarRomUpdateManager;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCellularIconViewModelImpl implements OplusMobileIconViewModelCommon {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCellularIconViewModel";
    private final Flow activity;
    private final StateFlow activityVisible;
    private final Context context;
    private final StateFlow icon;
    private final StateFlow isRoaming;
    private final StateFlow isUnstackedUseTheme17;
    private final StateFlow isVisible;
    private final StateFlow mobileActivityResId;
    private final Flow mobileSubIsDefault;
    private final StateFlow networkTypeIcon;
    private final StateFlow networkTypeText;
    private final StateFlow roamId;
    private final StateFlow showNetworkTypeIcon;
    public final int subscriptionId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusCellularIconViewModelImpl$isVisible$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function5 {
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        /* synthetic */ boolean Z$2;
        /* synthetic */ boolean Z$3;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(5, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
            boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
            boolean zBooleanValue4 = ((Boolean) obj4).booleanValue();
            AnonymousClass1 anonymousClass1 = OplusCellularIconViewModelImpl.this.new AnonymousClass1((Continuation) obj5);
            anonymousClass1.Z$0 = zBooleanValue;
            anonymousClass1.Z$1 = zBooleanValue2;
            anonymousClass1.Z$2 = zBooleanValue3;
            anonymousClass1.Z$3 = zBooleanValue4;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            boolean z3 = this.Z$2;
            boolean z4 = this.Z$3;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("isVisible subId:", OplusCellularIconViewModelImpl.this.getSubscriptionId(), " isAirplaneMode:", " isAllowedDuringAirplaneMode:", z);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" isForceHidden:", " isSatelliteForbid:", sbM, z2, z3);
            sbM.append(z4);
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusCellularIconViewModelImpl.TAG, sbM.toString(), null, 8);
            if (z3 || z4) {
                z2 = false;
            } else if (!z) {
                z2 = true;
            }
            return Boxing.boxBoolean(z2);
        }
    }

    public OplusCellularIconViewModelImpl(Context context, int i, OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl, ConnectivityConstants connectivityConstants, CoroutineScope coroutineScope, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        this.context = context;
        this.subscriptionId = i;
        Flow flowFlowOf = !connectivityConstants.getShouldShowActivityConfig() ? FlowKt.flowOf((Object) null) : oplusMobileIconInteractorImpl.getActivity();
        this.activity = flowFlowOf;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(oplusMobileIconInteractorImpl.getOplusDataConnected(), oplusMobileIconInteractorImpl.getMobileIsDefault(), oplusMobileIconsInteractor.getWifiIcon(), new activityVisible.1(this, (Continuation) null)));
        SharingStarted.Companion companion = SharingStarted.Companion;
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.activityVisible = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this.mobileActivityResId = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(flowFlowOf, getActivityVisible(), oplusMobileIconInteractorImpl.isSoftSimCard(), oplusMobileIconsInteractor.getMobileIconSets(), new OplusCellularIconViewModelImpl$mobileActivityResId$1(5, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.roamId = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(oplusMobileIconInteractorImpl.isRoaming(), oplusMobileIconInteractorImpl.isSoftSimCard(), oplusMobileIconsInteractor.getMobileIconSets(), new OplusCellularIconViewModelImpl$roamId$1(4, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.isRoaming = FlowKt.stateIn(FlowKt.distinctUntilChanged(oplusMobileIconInteractorImpl.isRoaming()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        StateFlow stateFlowStateIn = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(oplusMobileIconInteractorImpl.getMobileIsDefault(), oplusMobileIconInteractorImpl.getMobileCellularSubId(), new mobileSubIsDefault.1(this, (Continuation) null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.mobileSubIsDefault = stateFlowStateIn;
        Flow[] flowArr = {oplusMobileIconInteractorImpl.getOplusDataConnected(), oplusMobileIconInteractorImpl.isDataEnabled(), oplusMobileIconInteractorImpl.getAlwaysShowDataRatIcon(), stateFlowStateIn, oplusMobileIconInteractorImpl.getCarrierNetworkChangeActive(), oplusMobileIconInteractorImpl.isSoftSimCard()};
        OplusCellularIconViewModelImpl$special$$inlined$combine$1 oplusCellularIconViewModelImpl$special$$inlined$combine$1 = new OplusCellularIconViewModelImpl$special$$inlined$combine$1();
        oplusCellularIconViewModelImpl$special$$inlined$combine$1.$flows$inlined = flowArr;
        oplusCellularIconViewModelImpl$special$$inlined$combine$1.this$0 = this;
        VarHandle.storeStoreFence();
        StateFlow stateFlowStateIn2 = FlowKt.stateIn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(oplusCellularIconViewModelImpl$special$$inlined$combine$1), oplusMobileIconInteractorImpl.getTableLogBuffer(), "", "showNetworkTypeIcon", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.showNetworkTypeIcon = stateFlowStateIn2;
        this.networkTypeIcon = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(oplusMobileIconInteractorImpl.getNetworkTypeIconGroup(), stateFlowStateIn2, oplusMobileIconInteractorImpl.getSignalLevelIcon(), oplusMobileIconsInteractor.getMobileIconSets(), new OplusCellularIconViewModelImpl$networkTypeIcon$1(5, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.isUnstackedUseTheme17 = oplusMobileIconsInteractor.isUnstackedUseTheme17();
        this.networkTypeText = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(oplusMobileIconInteractorImpl.getNetworkTypeIconGroup(), stateFlowStateIn2, new networkTypeText.1(3, (Continuation) null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.icon = oplusMobileIconInteractorImpl.getSignalLevelIcon();
        this.isVisible = FlowKt.stateIn(DiffableExtensionsKt.logDiffsForTable(FlowKt.distinctUntilChanged(!connectivityConstants.getHasDataCapabilities() ? FlowKt.flowOf(bool) : FlowKt.combine(oplusMobileIconsInteractor.isAirplaneMode(), oplusMobileIconInteractorImpl.isAllowedDuringAirplaneMode(), oplusMobileIconInteractorImpl.isForceHidden(), oplusMobileIconsInteractor.isSatelliteForbid(), new AnonymousClass1(null))), oplusMobileIconInteractorImpl.getTableLogBuffer(), "", "visible", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
    }

    public static final boolean access$isShowWifiIndicatorWhenEnabled(OplusCellularIconViewModelImpl oplusCellularIconViewModelImpl) {
        oplusCellularIconViewModelImpl.getClass();
        if (!CustomizeFeatureOption.sWlanIconAlwaysOn) {
            return false;
        }
        OplusStatusBarRomUpdateManager companion = OplusStatusBarRomUpdateManager.Companion.getInstance(oplusCellularIconViewModelImpl.context);
        return companion != null ? companion.getShowWifiIndicatorWhenEnabled() : true;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon
    public StateFlow getActivityVisible() {
        return this.activityVisible;
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
