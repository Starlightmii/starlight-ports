package com.oplus.systemui.qs;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.qs.deprecated.customize.IQSCustomizerController;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.customize.OplusQSCustomizerController;
import com.oplusos.systemui.common.receiver.IReceiverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFooterViewController$$ExternalSyntheticLambda0 implements IReceiverListener {
    public /* synthetic */ OplusQSFooterViewController f$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        OplusQSFooterViewController oplusQSFooterViewController = this.f$0;
        ((OplusQSFooterImpl) ((ViewController) oplusQSFooterViewController).mView).updateEverything();
        IQSCustomizerController iQSCustomizerController = oplusQSFooterViewController.mQsPanelController.mQsCustomizerController;
        if (iQSCustomizerController != null) {
            ((OplusQSCustomizerController) iQSCustomizerController).hideCustomizer();
        }
    }
}
