package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.stealthmode;

import android.content.Context;
import android.os.SystemProperties;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StealthModeRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StealthModeRepository";
    private final StateFlow _stealthModeState;
    private final Context context;
    private final StateFlow stealthModeState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StealthModeRepository(Context context, CoroutineScope coroutineScope) {
        this.context = context;
        boolean z = SystemProperties.getBoolean("debug.systemui_stealth_mode", false);
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new StealthModeRepository$_stealthModeState$1(this, null)));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        MutableStateFlow mutableStateFlowStateIn = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this._stealthModeState = mutableStateFlowStateIn;
        if (!z) {
            VolumeFeatureOption.INSTANCE.getClass();
            if (!VolumeFeatureOption.isSupportsStealthMode()) {
                mutableStateFlowStateIn = StateFlowKt.MutableStateFlow(bool);
            }
        }
        this.stealthModeState = mutableStateFlowStateIn;
    }

    public final StateFlow getStealthModeState() {
        return this.stealthModeState;
    }
}
