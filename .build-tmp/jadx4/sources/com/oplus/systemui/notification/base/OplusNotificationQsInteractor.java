package com.oplus.systemui.notification.base;

import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationQsInteractor extends OplusNotificationQsExt {
    private final MutableStateFlow istDetailShowingDialog = StateFlowKt.MutableStateFlow(Boolean.FALSE);

    public final MutableStateFlow getIstDetailShowingDialog() {
        return this.istDetailShowingDialog;
    }

    @Override // com.oplus.systemui.notification.base.OplusNotificationQsExt
    public void setDetailShowingDialog(boolean z) {
        if (((Boolean) this.istDetailShowingDialog.getValue()).booleanValue() != z) {
            this.istDetailShowingDialog.setValue(Boolean.valueOf(z));
        }
    }
}
