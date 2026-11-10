package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.content.Intent;
import com.oplus.systemui.volume.observer.MultiMediaSettingsObserver;
import com.oplusos.systemui.common.receiver.IReceiverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiMediaRepository$screenStateListener$1 implements IReceiverListener {
    public /* synthetic */ MultiMediaRepository this$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        String action;
        if (this.this$0.destroyed || (action = intent.getAction()) == null) {
            return;
        }
        int iHashCode = action.hashCode();
        if (iHashCode == -2128145023) {
            if (action.equals("android.intent.action.SCREEN_OFF")) {
                this.this$0.stop();
            }
        } else if (iHashCode == -1454123155 && action.equals("android.intent.action.SCREEN_ON") && MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled) {
            this.this$0.start();
        }
    }
}
