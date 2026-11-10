package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.privacyspeaker;

import android.os.SystemProperties;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacySpeakerRepository {
    public static final Companion Companion = new Companion();
    private static final String PRIVACY_CALL_MODE_KEY = "oplus_call_privacy_mode";
    private static final String TAG = "PrivacySpeakerRepository";
    private final StateFlow _callPrivacyMode;
    private final StateFlow callPrivacyMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PrivacySpeakerRepository(GlobalSettings globalSettings, CoroutineScope coroutineScope) {
        boolean z = SystemProperties.getBoolean("debug.systemui_privacy_speaker", false);
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new PrivacySpeakerRepository$_callPrivacyMode$1(globalSettings, null));
        PrivacySpeakerRepository$special$$inlined$map$1 privacySpeakerRepository$special$$inlined$map$1 = new PrivacySpeakerRepository$special$$inlined$map$1();
        privacySpeakerRepository$special$$inlined$map$1.$this_unsafeTransform$inlined = flowConflatedCallbackFlow;
        privacySpeakerRepository$special$$inlined$map$1.$globalSettings$inlined = globalSettings;
        VarHandle.storeStoreFence();
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(privacySpeakerRepository$special$$inlined$map$1);
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        MutableStateFlow mutableStateFlowStateIn = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this._callPrivacyMode = mutableStateFlowStateIn;
        if (!z) {
            StatusBarFeatureOption.INSTANCE.getClass();
            if (!StatusBarFeatureOption.isSupportPrivacyCallMode() || StatusBarFeatureOption.isSupportPrivacyNoiseMode()) {
                mutableStateFlowStateIn = StateFlowKt.MutableStateFlow(bool);
            }
        }
        this.callPrivacyMode = mutableStateFlowStateIn;
    }

    public final StateFlow getCallPrivacyMode() {
        return this.callPrivacyMode;
    }
}
