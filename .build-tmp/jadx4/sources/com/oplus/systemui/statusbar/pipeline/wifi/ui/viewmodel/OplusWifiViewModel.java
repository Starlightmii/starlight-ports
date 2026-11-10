package com.oplus.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import android.content.Context;
import com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor;
import com.oplus.systemui.statusbar.util.WifiUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiViewModel {
    private final Context context;
    private final CoroutineScope scope;
    private final StateFlow wifiActivityResId;
    private final Flow wifiConnected;
    private final StateFlow wifiLeftResId;
    private final StateFlow wifiLeftText;

    public OplusWifiViewModel(CoroutineScope coroutineScope, Context context, OplusWifiInteractor oplusWifiInteractor) {
        this.scope = coroutineScope;
        this.context = context;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(oplusWifiInteractor.getOplusWifiModel(), oplusWifiInteractor.getTechnicalEnable(), oplusWifiInteractor.isUseWifiStyle17(), new OplusWifiViewModel$wifiLeftResId$1(this, null)));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.wifiLeftResId = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.wifiLeftText = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(oplusWifiInteractor.getOplusWifiModel(), oplusWifiInteractor.getTechnicalEnable(), oplusWifiInteractor.isUseWifiStyle17(), new OplusWifiViewModel$wifiLeftText$1(this, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.wifiActivityResId = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(oplusWifiInteractor.isValidated(), oplusWifiInteractor.getActivity(), oplusWifiInteractor.isUseWifiStyle17(), new OplusWifiViewModel$wifiActivityResId$1(4, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.wifiConnected = oplusWifiInteractor.getWifiConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getPassPointIcon() {
        if (CustomizeFeatureOption.sIsSupportCustomizePasspointIcon) {
            return WifiUtils.isHomeProviderNetwork(this.context) ? 2131236918 : 2131236920;
        }
        return 2131236919;
    }

    public final StateFlow getWifiActivityResId() {
        return this.wifiActivityResId;
    }

    public final Flow getWifiConnected() {
        return this.wifiConnected;
    }

    public final StateFlow getWifiLeftResId() {
        return this.wifiLeftResId;
    }

    public final StateFlow getWifiLeftText() {
        return this.wifiLeftText;
    }
}
