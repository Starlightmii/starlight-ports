package com.oplus.systemui.statusbar.policy;

import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.oplus.systemui.qs.helper.QSFragmentHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBrightnessMirrorController$beginClosingCallback$1 implements QSFragmentHelper.BeginClosingCallback {
    public /* synthetic */ OplusBrightnessMirrorController this$0;

    @Override // com.oplus.systemui.qs.helper.QSFragmentHelper.BeginClosingCallback
    public final void onClosingBegin() {
        OplusBrightnessMirrorController oplusBrightnessMirrorController = this.this$0;
        if (((BrightnessMirrorController) oplusBrightnessMirrorController).mBrightnessMirror.getVisibility() == 0) {
            oplusBrightnessMirrorController.hideMirrorDirect();
        }
    }

    @Override // com.oplus.systemui.qs.helper.QSFragmentHelper.BeginClosingCallback
    public final void onClosingEnd() {
    }
}
