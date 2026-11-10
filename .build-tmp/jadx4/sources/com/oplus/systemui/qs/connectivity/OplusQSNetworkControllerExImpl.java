package com.oplus.systemui.qs.connectivity;

import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import com.android.systemui.qs.connectivity.OplusQSNetworkControllerEx;
import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.SimStateModel;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository;
import com.oplus.systemui.statusbar.pipeline.wifi.shared.OplusWifiModel;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSNetworkControllerExImpl extends OplusQSNetworkControllerEx {
    private final OplusMobileConnectionsRepository mobileConnectionsRepository;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final OplusWifiRepository oplusWifiRepository;
    private final OplusStatusBarSignalPolicy statusBarSignalPolicy;

    public OplusQSNetworkControllerExImpl(OplusMobileConnectionsRepository oplusMobileConnectionsRepository, OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, OplusWifiRepository oplusWifiRepository, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        this.mobileConnectionsRepository = oplusMobileConnectionsRepository;
        this.statusBarSignalPolicy = oplusStatusBarSignalPolicy;
        this.oplusWifiRepository = oplusWifiRepository;
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
    }

    public int getConnectWifiStandard() {
        return ((OplusWifiModel) this.oplusWifiRepository.getOplusWifiModel().getValue()).getWifiStandard();
    }

    public boolean getIsWifiToData() {
        return ((Boolean) this.oplusWifiRepository.isWifiToData().getValue()).booleanValue();
    }

    public boolean[] getSimForbiddenStatus() {
        return ((SimStateModel) this.mobileConnectionsRepository.getSimState().getValue()).getSimForbidden();
    }

    public boolean[] getSimInsertedStatus() {
        return ((SimStateModel) this.mobileConnectionsRepository.getSimState().getValue()).getSimInserted();
    }

    public boolean getVoWifiState() {
        return this.statusBarSignalPolicy.getVoWifiState();
    }

    public final boolean isFiveG(int i) {
        StateFlow oplusIsNrType;
        OplusMobileIconInteractor oplusMobileIconInteractor = ((OplusMobileIconInteractor[]) this.oplusMobileIconsInteractor.getMobileIconInteractors().getValue())[i];
        if (oplusMobileIconInteractor == null || (oplusIsNrType = oplusMobileIconInteractor.getOplusIsNrType()) == null) {
            return false;
        }
        return ((Boolean) oplusIsNrType.getValue()).booleanValue();
    }

    public boolean isRoamingEx(TelephonyManager telephonyManager, SubscriptionInfo subscriptionInfo) {
        return OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getOplusMobileSignalEx().isRoamingEx(telephonyManager, subscriptionInfo.getSubscriptionId());
    }

    public final boolean isVoWifiState(int i) {
        return this.statusBarSignalPolicy.isVoWifiState(i);
    }

    public final boolean isWifiDouble() {
        return ((OplusWifiModel) this.oplusWifiRepository.getOplusWifiModel().getValue()).getWifiDouble();
    }

    public final boolean isWifiTechnicalEnable() {
        return ((Boolean) this.oplusWifiRepository.getWifiTechnicalEnable().getValue()).booleanValue();
    }

    public final boolean isWlanBandLabelOff() {
        return ((Boolean) this.oplusWifiRepository.getWlanBandLabelOff().getValue()).booleanValue();
    }
}
