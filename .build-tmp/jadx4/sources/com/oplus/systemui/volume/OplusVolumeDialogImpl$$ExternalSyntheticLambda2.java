package com.oplus.systemui.volume;

import android.view.accessibility.AccessibilityManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeDialogImpl$$ExternalSyntheticLambda2 implements AccessibilityManager.AccessibilityServicesStateChangeListener {
    public /* synthetic */ OplusVolumeDialogImpl f$0;

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityServicesStateChangeListener
    public final void onAccessibilityServicesStateChanged(AccessibilityManager accessibilityManager) {
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.f$0;
        OplusVolumeDialogImpl.H h = oplusVolumeDialogImpl.mHandler;
        OplusVolumeDialogImpl$$ExternalSyntheticLambda0 oplusVolumeDialogImpl$$ExternalSyntheticLambda0 = new OplusVolumeDialogImpl$$ExternalSyntheticLambda0(2);
        oplusVolumeDialogImpl$$ExternalSyntheticLambda0.f$0 = oplusVolumeDialogImpl;
        VarHandle.storeStoreFence();
        h.post(oplusVolumeDialogImpl$$ExternalSyntheticLambda0);
    }
}
