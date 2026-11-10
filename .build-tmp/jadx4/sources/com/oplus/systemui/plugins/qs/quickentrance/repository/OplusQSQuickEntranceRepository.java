package com.oplus.systemui.plugins.qs.quickentrance.repository;

import android.content.Context;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparateQSPrivacyRepository";
    private final MutableStateFlow _isEditMode;
    private final MutableStateFlow _isListening;
    private final Context context;
    private final StateFlow isEditMode;
    private final StateFlow isListening;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSQuickEntranceRepository(Context context) {
        this.context = context;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isListening = MutableStateFlow;
        this.isListening = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isEditMode = MutableStateFlow2;
        this.isEditMode = FlowKt.asStateFlow(MutableStateFlow2);
    }

    public final StateFlow isEditMode() {
        return this.isEditMode;
    }

    public final StateFlow isListening() {
        return this.isListening;
    }

    public final void setEditMode(boolean z) {
        this._isEditMode.setValue(Boolean.valueOf(z));
    }

    public final void setListening(boolean z) {
        this._isListening.setValue(Boolean.valueOf(z));
    }
}
