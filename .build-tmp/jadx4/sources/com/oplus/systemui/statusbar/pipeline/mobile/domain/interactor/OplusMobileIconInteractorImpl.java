package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.content.Context;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepository;
import com.oplus.systemui.statusbar.phone.signal.model.OplusIMSModel;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.CapabilityTypeModel;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.SimStateModel;
import com.oplus.systemui.statusbar.policy.TelephonyIcons;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconInteractorImpl implements MobileIconInteractor, OplusMobileIconInteractor {
    private static final String TAG = "OplusMobileIconInteractorImpl";
    public final /* synthetic */ MobileIconInteractor $$delegate_0;
    private final Context context;
    private final StateFlow isEnableShowVoLte;
    private final StateFlow oplusDataConnected;
    private final StateFlow oplusIMSModel;
    private final StateFlow oplusIsLteType;
    private final StateFlow oplusIsNrType;
    private final Flow oplusLevelIcon;
    private final StateFlow signalLevelIcon;
    private final Flow subscriptionModel;
    public static final Companion Companion = new Companion();
    private static final Integer[] nrTelephonyIcons = {Integer.valueOf(TelephonyIcons.FIVE_G.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_BASIC.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_SA.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_UWB.dataType), Integer.valueOf(TelephonyIcons.NR_5G_PLUS.dataType), Integer.valueOf(TelephonyIcons.NR_5G.dataType), Integer.valueOf(TelephonyIcons.LTE_CA_5G_E.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_UC.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_PLUS.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_E.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_A.dataType), Integer.valueOf(TelephonyIcons.FIVE_G_PLUS_PLUS.dataType), Integer.valueOf(TelephonyIcons.FIVE_DOT_FIVE_G.dataType), Integer.valueOf(TelephonyIcons.TIGO_5G_CONNECTED.dataType), Integer.valueOf(TelephonyIcons.TIGO_5G_NOT_RESTRICTED.dataType)};
    private static final Integer[] lteTelephonyIcons = {Integer.valueOf(TelephonyIcons.FOUR_G.dataType), Integer.valueOf(TelephonyIcons.FOUR_G_PLUS.dataType), Integer.valueOf(TelephonyIcons.FOUR_DOT_FIVE_G.dataType), Integer.valueOf(TelephonyIcons.LTE.dataType), Integer.valueOf(TelephonyIcons.LTE_PLUS.dataType), Integer.valueOf(TelephonyIcons.FOUR_DOT_FIVE_G_PLUS.dataType)};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMobileIconInteractorImpl(CoroutineScope coroutineScope, MobileIconInteractor mobileIconInteractor, OplusMobileConnectionRepository oplusMobileConnectionRepository, OplusMobileConnectionsRepository oplusMobileConnectionsRepository, ConnectivityRepository connectivityRepository, OplusMobileIconsInteractor oplusMobileIconsInteractor, Flow flow, Context context) {
        this.$$delegate_0 = mobileIconInteractor;
        this.subscriptionModel = flow;
        this.context = context;
        Flow[] flowArr = {mobileIconInteractor.isInService(), mobileIconInteractor.isRoaming(), mobileIconInteractor.isSoftSimCard(), oplusMobileConnectionRepository.getCapabilityType(), flow, connectivityRepository.getDefaultConnections(), mobileIconInteractor.getSignalLevelIcon(), oplusMobileConnectionsRepository.isCMCCDataPlan()};
        OplusMobileIconInteractorImpl$special$$inlined$combine$1 oplusMobileIconInteractorImpl$special$$inlined$combine$1 = new OplusMobileIconInteractorImpl$special$$inlined$combine$1(0);
        oplusMobileIconInteractorImpl$special$$inlined$combine$1.$flows$inlined = flowArr;
        VarHandle.storeStoreFence();
        this.oplusLevelIcon = oplusMobileIconInteractorImpl$special$$inlined$combine$1;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.transformLatest(isNonTerrestrial(), new OplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1(null, mobileIconInteractor, this)));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.signalLevelIcon = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), mobileIconInteractor.getSignalLevelIcon().getValue());
        Flow flowDistinctUntilChanged2 = FlowKt.distinctUntilChanged(FlowKt.combine(mobileIconInteractor.isDataConnected(), oplusMobileIconsInteractor.getActiveMobileDataSubscriptionId(), oplusMobileIconsInteractor.isDualPsOpen(), flow, new OplusMobileIconInteractorImpl$oplusDataConnected$1(5, null)));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.oplusDataConnected = FlowKt.stateIn(flowDistinctUntilChanged2, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this.oplusIsNrType = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(getNetworkTypeIconGroup(), flow, new OplusMobileIconInteractorImpl$oplusIsNrType$1(3, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.oplusIsLteType = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(getNetworkTypeIconGroup(), flow, new OplusMobileIconInteractorImpl$oplusIsLteType$1(3, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        Flow[] flowArr2 = {oplusMobileConnectionRepository.getOplusFeatureChanged(), oplusMobileConnectionsRepository.getSimState(), getOplusIsNrType(), getOplusIsLteType(), flow, isNonTerrestrial(), oplusMobileConnectionRepository.getCapabilityType()};
        OplusMobileIconInteractorImpl$special$$inlined$combine$1 oplusMobileIconInteractorImpl$special$$inlined$combine$2 = new OplusMobileIconInteractorImpl$special$$inlined$combine$1(1);
        oplusMobileIconInteractorImpl$special$$inlined$combine$2.$flows$inlined = flowArr2;
        VarHandle.storeStoreFence();
        StateFlow stateFlowStateIn = FlowKt.stateIn(oplusMobileIconInteractorImpl$special$$inlined$combine$2, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.isEnableShowVoLte = stateFlowStateIn;
        Flow[] flowArr3 = {oplusMobileConnectionRepository.getCapabilityType(), oplusMobileConnectionRepository.isVoNrEnabled(), oplusMobileConnectionsRepository.getSimState(), getDisplayInfoNetworkType(), stateFlowStateIn, flow};
        OplusMobileIconInteractorImpl$special$$inlined$combine$3 oplusMobileIconInteractorImpl$special$$inlined$combine$3 = new OplusMobileIconInteractorImpl$special$$inlined$combine$3();
        oplusMobileIconInteractorImpl$special$$inlined$combine$3.$flows$inlined = flowArr3;
        oplusMobileIconInteractorImpl$special$$inlined$combine$3.this$0 = this;
        VarHandle.storeStoreFence();
        this.oplusIMSModel = FlowKt.stateIn(oplusMobileIconInteractorImpl$special$$inlined$combine$3, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new OplusIMSModel());
    }

    /* JADX WARN: Code duplicated, block: B:114:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:132:0x0218  */
    /* JADX WARN: Code duplicated, block: B:61:0x0132  */
    /* JADX WARN: Code duplicated, block: B:7:0x0026  */
    /* JADX WARN: Code duplicated, block: B:89:0x0182  */
    public static final Object access$createImsModel(OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl, int i, CapabilityTypeModel capabilityTypeModel, boolean z, SimStateModel simStateModel, boolean z2, boolean z3, ContinuationImpl continuationImpl) {
        OplusMobileIconInteractorImpl$createImsModel$1 oplusMobileIconInteractorImpl$createImsModel$1;
        int i2;
        CapabilityTypeModel capabilityTypeModel2;
        SimStateModel simStateModel2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        CapabilityTypeModel capabilityTypeModel3;
        SimStateModel simStateModel3;
        Object obj;
        int i13;
        int i14 = i;
        boolean z4 = z;
        SimStateModel simStateModel4 = simStateModel;
        boolean z5 = z2;
        oplusMobileIconInteractorImpl.getClass();
        if (continuationImpl instanceof OplusMobileIconInteractorImpl$createImsModel$1) {
            oplusMobileIconInteractorImpl$createImsModel$1 = (OplusMobileIconInteractorImpl$createImsModel$1) continuationImpl;
            int i15 = oplusMobileIconInteractorImpl$createImsModel$1.label;
            if ((i15 & Integer.MIN_VALUE) != 0) {
                oplusMobileIconInteractorImpl$createImsModel$1.label = i15 - Integer.MIN_VALUE;
            } else {
                oplusMobileIconInteractorImpl$createImsModel$1 = new OplusMobileIconInteractorImpl$createImsModel$1(oplusMobileIconInteractorImpl, continuationImpl);
            }
        } else {
            oplusMobileIconInteractorImpl$createImsModel$1 = new OplusMobileIconInteractorImpl$createImsModel$1(oplusMobileIconInteractorImpl, continuationImpl);
        }
        Object obj2 = oplusMobileIconInteractorImpl$createImsModel$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i16 = oplusMobileIconInteractorImpl$createImsModel$1.label;
        if (i16 == 0) {
            ResultKt.throwOnFailure(obj2);
            int registerTech = capabilityTypeModel.getRegisterTech();
            boolean z6 = capabilityTypeModel.isWfcEnabledByUser;
            boolean z7 = capabilityTypeModel.isCrossSimCallingEnabledByUser;
            boolean z8 = capabilityTypeModel.isVolteSwitchOn;
            if (registerTech != 0) {
                if (registerTech == 1) {
                    if (z6 && (capabilityTypeModel.getVoiceCapable() || capabilityTypeModel.getVideoCapable())) {
                        if (!CustomizeFeatureOption.checkSlotIdValid(i14) ? false : CustomizeFeatureOption.sIsSupportHideVoWifiIcon[i14]) {
                            i8 = 0;
                        } else {
                            i8 = 1;
                        }
                    } else {
                        i8 = 0;
                    }
                    i9 = i8;
                    i7 = (z6 && (capabilityTypeModel.getVoiceCapable() || capabilityTypeModel.getVideoCapable())) ? 1 : 0;
                    i10 = 0;
                } else if (registerTech == 2) {
                    i9 = (z7 && (capabilityTypeModel.getVoiceCapable() || capabilityTypeModel.getVideoCapable())) ? 1 : 0;
                    i7 = (z7 && (capabilityTypeModel.getVoiceCapable() || capabilityTypeModel.getVideoCapable())) ? 1 : 0;
                    i11 = 1;
                    i10 = 0;
                    i12 = 0;
                } else if (registerTech != 3) {
                    i7 = 0;
                    i9 = 0;
                    i10 = 0;
                } else {
                    i12 = (z8 && capabilityTypeModel.getImsRegistered() && (capabilityTypeModel.getVoiceCapable() || capabilityTypeModel.getVideoCapable()) && z3) ? 1 : 0;
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isExpRegion()) {
                        if (z5) {
                            capabilityTypeModel3 = capabilityTypeModel;
                            simStateModel3 = simStateModel4;
                            i7 = 0;
                            i9 = 0;
                            i11 = 0;
                        } else {
                            oplusMobileIconInteractorImpl$createImsModel$1.L$0 = capabilityTypeModel;
                            oplusMobileIconInteractorImpl$createImsModel$1.L$1 = simStateModel4;
                            oplusMobileIconInteractorImpl$createImsModel$1.I$0 = i14;
                            oplusMobileIconInteractorImpl$createImsModel$1.Z$0 = z4;
                            oplusMobileIconInteractorImpl$createImsModel$1.Z$1 = z5;
                            oplusMobileIconInteractorImpl$createImsModel$1.Z$2 = z3;
                            oplusMobileIconInteractorImpl$createImsModel$1.I$1 = 0;
                            oplusMobileIconInteractorImpl$createImsModel$1.I$2 = 0;
                            oplusMobileIconInteractorImpl$createImsModel$1.I$3 = i12;
                            oplusMobileIconInteractorImpl$createImsModel$1.I$4 = 0;
                            oplusMobileIconInteractorImpl$createImsModel$1.I$5 = 0;
                            oplusMobileIconInteractorImpl$createImsModel$1.label = 2;
                            Object objIsOplusVo5gSmoothDisabled = oplusMobileIconInteractorImpl.isOplusVo5gSmoothDisabled(oplusMobileIconInteractorImpl$createImsModel$1);
                            if (objIsOplusVo5gSmoothDisabled != coroutine_suspended) {
                                obj = objIsOplusVo5gSmoothDisabled;
                                capabilityTypeModel3 = capabilityTypeModel;
                                simStateModel3 = simStateModel4;
                                i7 = 0;
                                i9 = 0;
                                i13 = 0;
                                i11 = 0;
                                if (!((Boolean) obj).booleanValue()) {
                                }
                                i10 = i13;
                                simStateModel4 = simStateModel3;
                            }
                        }
                        if (!capabilityTypeModel3.isVolteSwitchOn) {
                            i13 = 0;
                        } else {
                            i13 = 0;
                        }
                        i10 = i13;
                        simStateModel4 = simStateModel3;
                    } else {
                        i7 = 0;
                        i9 = 0;
                        i10 = 0;
                        i11 = 0;
                    }
                }
                i11 = i10;
                i12 = i11;
            } else {
                int i17 = (z8 && capabilityTypeModel.getImsRegistered() && (capabilityTypeModel.getVoiceCapable() || capabilityTypeModel.getVideoCapable()) && z3) ? 1 : 0;
                oplusMobileIconInteractorImpl$createImsModel$1.L$0 = capabilityTypeModel;
                oplusMobileIconInteractorImpl$createImsModel$1.L$1 = simStateModel4;
                oplusMobileIconInteractorImpl$createImsModel$1.I$0 = i14;
                oplusMobileIconInteractorImpl$createImsModel$1.Z$0 = z4;
                oplusMobileIconInteractorImpl$createImsModel$1.Z$1 = z5;
                oplusMobileIconInteractorImpl$createImsModel$1.Z$2 = z3;
                oplusMobileIconInteractorImpl$createImsModel$1.I$1 = 0;
                oplusMobileIconInteractorImpl$createImsModel$1.I$2 = 0;
                oplusMobileIconInteractorImpl$createImsModel$1.I$3 = i17;
                oplusMobileIconInteractorImpl$createImsModel$1.I$4 = 0;
                oplusMobileIconInteractorImpl$createImsModel$1.I$5 = 0;
                oplusMobileIconInteractorImpl$createImsModel$1.label = 1;
                Object objIsOplusVo5gSmoothDisabled2 = oplusMobileIconInteractorImpl.isOplusVo5gSmoothDisabled(oplusMobileIconInteractorImpl$createImsModel$1);
                if (objIsOplusVo5gSmoothDisabled2 != coroutine_suspended) {
                    i2 = i14;
                    capabilityTypeModel2 = capabilityTypeModel;
                    simStateModel2 = simStateModel4;
                    i3 = i17;
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                    obj2 = objIsOplusVo5gSmoothDisabled2;
                    i7 = 0;
                    if (((Boolean) obj2).booleanValue()) {
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion()) {
                            if (!capabilityTypeModel2.isVolteSwitchOn) {
                                i5 = 0;
                            } else {
                                i5 = 0;
                            }
                        }
                    }
                    i12 = i3;
                    i10 = i5;
                    i11 = i6;
                    simStateModel4 = simStateModel2;
                    i9 = i4;
                    i14 = i2;
                }
            }
            return coroutine_suspended;
        }
        if (i16 == 1) {
            i7 = oplusMobileIconInteractorImpl$createImsModel$1.I$5;
            i4 = oplusMobileIconInteractorImpl$createImsModel$1.I$4;
            i3 = oplusMobileIconInteractorImpl$createImsModel$1.I$3;
            int i18 = oplusMobileIconInteractorImpl$createImsModel$1.I$2;
            int i19 = oplusMobileIconInteractorImpl$createImsModel$1.I$1;
            z5 = oplusMobileIconInteractorImpl$createImsModel$1.Z$1;
            boolean z9 = oplusMobileIconInteractorImpl$createImsModel$1.Z$0;
            i2 = oplusMobileIconInteractorImpl$createImsModel$1.I$0;
            simStateModel2 = (SimStateModel) oplusMobileIconInteractorImpl$createImsModel$1.L$1;
            capabilityTypeModel2 = (CapabilityTypeModel) oplusMobileIconInteractorImpl$createImsModel$1.L$0;
            ResultKt.throwOnFailure(obj2);
            i5 = i18;
            z4 = z9;
            i6 = i19;
            if (((Boolean) obj2).booleanValue()) {
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isExpRegion() && z5) {
                    if (!capabilityTypeModel2.isVolteSwitchOn && capabilityTypeModel2.getImsRegistered() && ((capabilityTypeModel2.getVoiceCapable() || capabilityTypeModel2.getVideoCapable()) && z4)) {
                        if (!CustomizeFeatureOption.checkSlotIdValid(i2) ? false : CustomizeFeatureOption.sIsSupportShowVoltefForVo5G[i2]) {
                            i5 = 0;
                        } else {
                            i5 = 1;
                        }
                    } else {
                        i5 = 0;
                    }
                }
            }
            i12 = i3;
            i10 = i5;
            i11 = i6;
            simStateModel4 = simStateModel2;
            i9 = i4;
            i14 = i2;
        } else {
            if (i16 != 2) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            i7 = oplusMobileIconInteractorImpl$createImsModel$1.I$5;
            int i20 = oplusMobileIconInteractorImpl$createImsModel$1.I$4;
            int i21 = oplusMobileIconInteractorImpl$createImsModel$1.I$3;
            int i22 = oplusMobileIconInteractorImpl$createImsModel$1.I$2;
            int i23 = oplusMobileIconInteractorImpl$createImsModel$1.I$1;
            boolean z10 = oplusMobileIconInteractorImpl$createImsModel$1.Z$0;
            int i24 = oplusMobileIconInteractorImpl$createImsModel$1.I$0;
            simStateModel3 = (SimStateModel) oplusMobileIconInteractorImpl$createImsModel$1.L$1;
            capabilityTypeModel3 = (CapabilityTypeModel) oplusMobileIconInteractorImpl$createImsModel$1.L$0;
            ResultKt.throwOnFailure(obj2);
            i9 = i20;
            i14 = i24;
            obj = obj2;
            i12 = i21;
            i13 = i22;
            z4 = z10;
            i11 = i23;
            if (!((Boolean) obj).booleanValue()) {
                if (!capabilityTypeModel3.isVolteSwitchOn && capabilityTypeModel3.getImsRegistered() && ((capabilityTypeModel3.getVoiceCapable() || capabilityTypeModel3.getVideoCapable()) && z4)) {
                    if (!CustomizeFeatureOption.checkSlotIdValid(i14) ? false : CustomizeFeatureOption.sIsSupportShowVoltefForVo5G[i14]) {
                        i13 = 0;
                    } else {
                        i13 = 1;
                    }
                } else {
                    i13 = 0;
                }
            }
            i10 = i13;
            simStateModel4 = simStateModel3;
        }
        return new OplusIMSModel(i12 != 0 && simStateModel4.getSimInserted()[i14], i9 != 0 && simStateModel4.getSimInserted()[i14], simStateModel4.getSimInserted()[i14], !simStateModel4.getSimInserted()[i14] || (simStateModel4.getSimInserted()[i14] && simStateModel4.getSimForbidden()[i14]), i11 != 0, i10 != 0, i7 != 0);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor
    public void dump(PrintWriter printWriter, String[] strArr, int i) {
        printWriter.println(TAG + i);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("oplusIMSModel: ", getOplusIMSModel().getValue(), printWriter);
        printWriter.println("isEnableShowVoLte: " + this.isEnableShowVoLte.getValue() + " isSupportHideVoLTE:" + (!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSupportHideVoLTEIcon[i]));
        printWriter.println("oplusIsLteType: " + getOplusIsLteType().getValue() + " oplusIsNrType: " + getOplusIsNrType().getValue());
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("oplusDataConnected: ", getOplusDataConnected().getValue(), printWriter);
    }

    public Flow getActivity() {
        return this.$$delegate_0.getActivity();
    }

    public Flow getAlwaysShowDataRatIcon() {
        return this.$$delegate_0.getAlwaysShowDataRatIcon();
    }

    public Flow getCarrierName() {
        return this.$$delegate_0.getCarrierName();
    }

    public Flow getCarrierNetworkChangeActive() {
        return this.$$delegate_0.getCarrierNetworkChangeActive();
    }

    public final Context getContext() {
        return this.context;
    }

    public StateFlow getDisplayInfoNetworkType() {
        return this.$$delegate_0.getDisplayInfoNetworkType();
    }

    public Flow getMobileCellularSubId() {
        return this.$$delegate_0.getMobileCellularSubId();
    }

    public Flow getMobileIsDefault() {
        return this.$$delegate_0.getMobileIsDefault();
    }

    public Flow getNetworkName() {
        return this.$$delegate_0.getNetworkName();
    }

    public Flow getNetworkTypeIconGroup() {
        return this.$$delegate_0.getNetworkTypeIconGroup();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor
    public StateFlow getOplusDataConnected() {
        return this.oplusDataConnected;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor
    public StateFlow getOplusIMSModel() {
        return this.oplusIMSModel;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor
    public StateFlow getOplusIsLteType() {
        return this.oplusIsLteType;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor
    public StateFlow getOplusIsNrType() {
        return this.oplusIsNrType;
    }

    public Flow getShowSliceAttribution() {
        return this.$$delegate_0.getShowSliceAttribution();
    }

    public StateFlow getSignalLevelIcon() {
        return this.signalLevelIcon;
    }

    public int getSubscriptionId() {
        return this.$$delegate_0.getSubscriptionId();
    }

    public final Flow getSubscriptionModel() {
        return this.subscriptionModel;
    }

    public TableLogBuffer getTableLogBuffer() {
        return this.$$delegate_0.getTableLogBuffer();
    }

    public Flow isAllowedDuringAirplaneMode() {
        return this.$$delegate_0.isAllowedDuringAirplaneMode();
    }

    public Flow isDataConnected() {
        return this.$$delegate_0.isDataConnected();
    }

    public Flow isDataEnabled() {
        return this.$$delegate_0.isDataEnabled();
    }

    public Flow isEmergencyOnly() {
        return this.$$delegate_0.isEmergencyOnly();
    }

    public Flow isForceHidden() {
        return this.$$delegate_0.isForceHidden();
    }

    public Flow isInService() {
        return this.$$delegate_0.isInService();
    }

    public Flow isNonTerrestrial() {
        return this.$$delegate_0.isNonTerrestrial();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object isOplusVo5gSmoothDisabled(ContinuationImpl continuationImpl) {
        isOplusVo5gSmoothDisabled.1 r0;
        if (continuationImpl instanceof isOplusVo5gSmoothDisabled.1) {
            r0 = (isOplusVo5gSmoothDisabled.1) continuationImpl;
            int i = r0.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                r0.label = i - Integer.MIN_VALUE;
            } else {
                r0 = new isOplusVo5gSmoothDisabled.1(this, continuationImpl);
            }
        } else {
            r0 = new isOplusVo5gSmoothDisabled.1(this, continuationImpl);
        }
        Object objFirstOrNull = r0.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = r0.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objFirstOrNull);
            Flow flow = this.subscriptionModel;
            r0.label = 1;
            objFirstOrNull = FlowKt.firstOrNull(flow, r0);
            if (objFirstOrNull == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(objFirstOrNull);
        }
        SubscriptionModel subscriptionModel = (SubscriptionModel) objFirstOrNull;
        int subscriptionId = subscriptionModel != null ? subscriptionModel.getSubscriptionId() : -1;
        Object systemService = this.context.getSystemService("carrier_config");
        CarrierConfigManager carrierConfigManager = systemService instanceof CarrierConfigManager ? (CarrierConfigManager) systemService : null;
        PersistableBundle configForSubId = carrierConfigManager != null ? carrierConfigManager.getConfigForSubId(subscriptionId) : null;
        boolean z = configForSubId != null ? configForSubId.getBoolean("carrier_oplus_disable_vo5g_smooth") : false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", MobileInputLogger$.ExternalSyntheticOutline0.m("subId:", subscriptionId, ", isOplusVo5gSmoothDisabled:", "OplusMobileIconInteractorImpl-->", z));
        }
        return Boxing.boxBoolean(z);
    }

    public Flow isRoaming() {
        return this.$$delegate_0.isRoaming();
    }

    public Flow isSingleCarrier() {
        return this.$$delegate_0.isSingleCarrier();
    }

    public StateFlow isSoftSimCard() {
        return this.$$delegate_0.isSoftSimCard();
    }
}
