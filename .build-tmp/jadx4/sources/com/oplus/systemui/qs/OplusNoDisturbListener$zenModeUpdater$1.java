package com.oplus.systemui.qs;

import androidx.databinding.Observable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNoDisturbListener$zenModeUpdater$1 extends Observable.OnPropertyChangedCallback {
    public /* synthetic */ OplusNoDisturbListener this$0;

    public final void onPropertyChanged(Observable observable, int i) {
        OplusNoDisturbListener oplusNoDisturbListener = this.this$0;
        OplusNoDisturbListener.InternalHandler internalHandler = oplusNoDisturbListener.handler;
        int iIntValue = ((Number) oplusNoDisturbListener.zenMode.get()).intValue();
        internalHandler.removeMessages(100);
        internalHandler.sendMessage(internalHandler.obtainMessage(100, Integer.valueOf(iIntValue)));
    }
}
