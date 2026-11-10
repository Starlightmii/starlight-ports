package com.oplus.systemui.volume.repository;

import com.oplus.systemui.volume.observer.MultiMediaSettingsObserver;
import com.oplusos.systemui.common.observer.IObserverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiMediaRepository$mMultiMediaSettingObserverListener$1 implements IObserverListener {
    public /* synthetic */ MultiMediaRepository this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        boolean z2 = MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled;
        MultiMediaRepository multiMediaRepository = this.this$0;
        if (!z2) {
            multiMediaRepository.stop();
            return;
        }
        boolean zIsScreenInteractive = multiMediaRepository.isScreenInteractive();
        MultiMediaRepository multiMediaRepository2 = this.this$0;
        if (zIsScreenInteractive) {
            multiMediaRepository2.start();
        } else {
            multiMediaRepository2.stop();
        }
    }
}
