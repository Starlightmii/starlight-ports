package com.oplus.systemui.qs.res.data.repository;

import android.content.Context;
import android.content.res.Configuration;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSConfigurationRepository implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSConfigurationRepository";
    private final MutableSharedFlow _configuration;
    private final MutableStateFlow _isNightMode;
    private final SharedFlow configuration;
    private final Context context;
    private final StateFlow isNightMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QSConfigurationRepository(Context context, ConfigurationController configurationController) {
        this.context = context;
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2);
        this._configuration = mutableSharedFlowMutableSharedFlow$default;
        this.configuration = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.valueOf(OplusDarkModeUtil.isNightMode(context)));
        this._isNightMode = MutableStateFlow;
        this.isNightMode = FlowKt.asStateFlow(MutableStateFlow);
        configurationController.addCallback(this);
        onConfigChanged(context.getResources().getConfiguration());
    }

    public final SharedFlow getConfiguration() {
        return this.configuration;
    }

    public final StateFlow isNightMode() {
        return this.isNightMode;
    }

    public void onConfigChanged(Configuration configuration) {
        if (this._configuration.tryEmit(configuration)) {
            return;
        }
        LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "configurationChange tryEmit failed");
    }

    public final void onUiModeChanged() {
        this._isNightMode.setValue(Boolean.valueOf(OplusDarkModeUtil.isNightMode(this.context)));
    }
}
