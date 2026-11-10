package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.content.Context;
import com.android.systemui.shared.settings.data.repository.SystemSettingsRepository;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractor;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepository;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.statusbar.phone.signal.network.BigTypeMobileIconSets;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.MobileThemeAdapterState;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.OplusMobileThemeManagerRepository;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import com.oplus.systemui.statusbar.policy.Os17MobileIconSets;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconsInteractorImpl implements OplusMobileIconsInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMobileIconsInteractorImpl";
    private final StateFlow activeMobileDataSubscriptionId;
    private final Lazy bigTypeIconSets$delegate;
    private final StateFlow defaultConnections;
    private final Lazy defaultIconSets$delegate;
    private final StateFlow forceHiddenHDPlus;
    private final StateFlow is5GNewCall;
    private final StateFlow isAirplaneMode;
    private final StateFlow isDualPsOpen;
    private final StateFlow isDualSim;
    private final StateFlow isSatelliteForbid;
    private final StateFlow isUnstackedUseTheme17;
    private final StateFlow mobileIconInteractors;
    private final StateFlow mobileIconSets;
    private final OplusMobileThemeManagerRepository oplusMobileThemeManagerRepository;
    private final Lazy os17IconSets$delegate;
    private final CoroutineScope scope;
    private final StateFlow signalIconDisplayMode;
    private final StateFlow simState;
    private final StateFlow wifiIcon;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMobileIconsInteractorImpl(CoroutineScope coroutineScope, OplusMobileConnectionsRepository oplusMobileConnectionsRepository, OplusMobileThemeManagerRepository oplusMobileThemeManagerRepository, MobileConnectionsRepository mobileConnectionsRepository, ConnectivityRepository connectivityRepository, MobileIconsInteractor mobileIconsInteractor, WifiViewModel wifiViewModel, AirplaneModeInteractor airplaneModeInteractor, TunerService tunerService, Context context, SystemSettingsRepository systemSettingsRepository) {
        this.scope = coroutineScope;
        this.oplusMobileThemeManagerRepository = oplusMobileThemeManagerRepository;
        final int i = 0;
        this.defaultIconSets$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractorImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new MobileIconSets();
                    case 1:
                        return new Os17MobileIconSets();
                    default:
                        return new BigTypeMobileIconSets();
                }
            }
        });
        final int i2 = 1;
        this.os17IconSets$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractorImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new MobileIconSets();
                    case 1:
                        return new Os17MobileIconSets();
                    default:
                        return new BigTypeMobileIconSets();
                }
            }
        });
        final int i3 = 2;
        this.bigTypeIconSets$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractorImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return new MobileIconSets();
                    case 1:
                        return new Os17MobileIconSets();
                    default:
                        return new BigTypeMobileIconSets();
                }
            }
        });
        StatusBarFeatureOption.INSTANCE.getClass();
        int i4 = !StatusBarFeatureOption.isSignalMobileIconDefaultParallel() ? 1 : 0;
        this.isDualPsOpen = oplusMobileConnectionsRepository.isDualPsSim();
        this.activeMobileDataSubscriptionId = mobileConnectionsRepository.getActiveMobileDataSubscriptionId();
        this.isSatelliteForbid = oplusMobileConnectionsRepository.isSatelliteForbid();
        this.wifiIcon = wifiViewModel.getWifiIcon();
        this.defaultConnections = connectivityRepository.getDefaultConnections();
        this.isAirplaneMode = airplaneModeInteractor.isAirplaneMode();
        this.simState = oplusMobileConnectionsRepository.getSimState();
        this.is5GNewCall = oplusMobileConnectionsRepository.is5GNewCall();
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1(tunerService, context, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.forceHiddenHDPlus = FlowKt.stateIn(flowConflatedCallbackFlow, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        Flow filteredSubscriptions = mobileIconsInteractor.getFilteredSubscriptions();
        OplusMobileIconsInteractorImpl$special$$inlined$map$1 oplusMobileIconsInteractorImpl$special$$inlined$map$1 = new OplusMobileIconsInteractorImpl$special$$inlined$map$1(0);
        oplusMobileIconsInteractorImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = filteredSubscriptions;
        oplusMobileIconsInteractorImpl$special$$inlined$map$1.$mobileIconsInteractor$inlined = mobileIconsInteractor;
        VarHandle.storeStoreFence();
        this.mobileIconInteractors = FlowKt.stateIn(oplusMobileIconsInteractorImpl$special$$inlined$map$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new OplusMobileIconInteractor[2]);
        Flow filteredSubscriptions2 = mobileIconsInteractor.getFilteredSubscriptions();
        OplusMobileIconsInteractorImpl$special$$inlined$map$2 oplusMobileIconsInteractorImpl$special$$inlined$map$2 = new OplusMobileIconsInteractorImpl$special$$inlined$map$2(0);
        oplusMobileIconsInteractorImpl$special$$inlined$map$2.$this_unsafeTransform$inlined = filteredSubscriptions2;
        VarHandle.storeStoreFence();
        this.isDualSim = FlowKt.stateIn(oplusMobileIconsInteractorImpl$special$$inlined$map$2, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        StateFlow themeAdapterState = oplusMobileThemeManagerRepository.getThemeAdapterState();
        OplusMobileIconsInteractorImpl$special$$inlined$map$2 oplusMobileIconsInteractorImpl$special$$inlined$map$3 = new OplusMobileIconsInteractorImpl$special$$inlined$map$2(1);
        oplusMobileIconsInteractorImpl$special$$inlined$map$3.$this_unsafeTransform$inlined = themeAdapterState;
        VarHandle.storeStoreFence();
        this.isUnstackedUseTheme17 = FlowKt.stateIn(oplusMobileIconsInteractorImpl$special$$inlined$map$3, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.valueOf(((MobileThemeAdapterState) oplusMobileThemeManagerRepository.getThemeAdapterState().getValue()).isUnstackedUseTheme17()));
        this.signalIconDisplayMode = FlowKt.stateIn(systemSettingsRepository.intSetting(CommonSettingsValueProxy.KEY_SIGNAL_ICON_DISPLAY_MODE, i4), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Integer.valueOf(i4));
        StateFlow themeAdapterState2 = oplusMobileThemeManagerRepository.getThemeAdapterState();
        OplusMobileIconsInteractorImpl$special$$inlined$map$1 oplusMobileIconsInteractorImpl$special$$inlined$map$4 = new OplusMobileIconsInteractorImpl$special$$inlined$map$1(1);
        oplusMobileIconsInteractorImpl$special$$inlined$map$4.$this_unsafeTransform$inlined = themeAdapterState2;
        oplusMobileIconsInteractorImpl$special$$inlined$map$4.$mobileIconsInteractor$inlined = this;
        VarHandle.storeStoreFence();
        this.mobileIconSets = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.onEach(oplusMobileIconsInteractorImpl$special$$inlined$map$4, new OplusMobileIconsInteractorImpl$mobileIconSets$2(2, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), resolveMobileIconSets(((MobileThemeAdapterState) oplusMobileThemeManagerRepository.getThemeAdapterState().getValue()).isUnstackedUseTheme17()));
    }

    private final MobileIconSets getBigTypeIconSets() {
        return (MobileIconSets) this.bigTypeIconSets$delegate.getValue();
    }

    private final MobileIconSets getDefaultIconSets() {
        return (MobileIconSets) this.defaultIconSets$delegate.getValue();
    }

    private final MobileIconSets getOs17IconSets() {
        return (MobileIconSets) this.os17IconSets$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getActiveMobileDataSubscriptionId() {
        return this.activeMobileDataSubscriptionId;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public MobileIconSets getCurrentMobileIconSets() {
        return resolveMobileIconSets(((MobileThemeAdapterState) this.oplusMobileThemeManagerRepository.getThemeAdapterState().getValue()).isUnstackedUseTheme17());
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getDefaultConnections() {
        return this.defaultConnections;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getForceHiddenHDPlus() {
        return this.forceHiddenHDPlus;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getMobileIconInteractors() {
        return this.mobileIconInteractors;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getMobileIconSets() {
        return this.mobileIconSets;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getSignalIconDisplayMode() {
        return this.signalIconDisplayMode;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getSimState() {
        return this.simState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow getWifiIcon() {
        return this.wifiIcon;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow is5GNewCall() {
        return this.is5GNewCall;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow isAirplaneMode() {
        return this.isAirplaneMode;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow isDualPsOpen() {
        return this.isDualPsOpen;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow isDualSim() {
        return this.isDualSim;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow isSatelliteForbid() {
        return this.isSatelliteForbid;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor
    public StateFlow isUnstackedUseTheme17() {
        return this.isUnstackedUseTheme17;
    }

    public final MobileIconSets resolveMobileIconSets(boolean z) {
        if (z) {
            return getOs17IconSets();
        }
        return CustomizeFeatureOption.sIsShowBig5G ? getBigTypeIconSets() : getDefaultIconSets();
    }
}
