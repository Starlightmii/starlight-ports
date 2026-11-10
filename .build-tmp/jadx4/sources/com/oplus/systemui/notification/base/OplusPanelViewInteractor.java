package com.oplus.systemui.notification.base;

import com.android.systemui.CoreStartable;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.ShadeStateListener;
import dagger.Lazy;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewInteractor extends NotificationPanelViewExt implements CoreStartable, ShadeStateListener {
    private final MutableStateFlow _isVisibility;
    private final StateFlow isVisibility;
    public boolean lastPanelStateVisibility;
    public boolean lastPanelViewVisibility;
    private final Lazy shadeExpansionStateManager;

    public OplusPanelViewInteractor(Lazy lazy) {
        this.shadeExpansionStateManager = lazy;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isVisibility = MutableStateFlow;
        this.isVisibility = MutableStateFlow;
    }

    public final StateFlow isVisibility() {
        return this.isVisibility;
    }

    public final void onPanelStateChanged(int i) {
        this.lastPanelStateVisibility = i != 0;
        updatePanelVisibility();
    }

    public final void start() {
        ((ShadeExpansionStateManager) this.shadeExpansionStateManager.get()).addStateListener(this);
    }

    @Override // com.oplus.systemui.notification.base.NotificationPanelViewExt
    public final void updatePanelViewVisibility(boolean z) {
        this.lastPanelViewVisibility = z;
        updatePanelVisibility();
    }

    public final void updatePanelVisibility() {
        boolean z = this.lastPanelViewVisibility || this.lastPanelStateVisibility;
        if (((Boolean) this._isVisibility.getValue()).booleanValue() != z) {
            this._isVisibility.setValue(Boolean.valueOf(z));
        }
    }
}
