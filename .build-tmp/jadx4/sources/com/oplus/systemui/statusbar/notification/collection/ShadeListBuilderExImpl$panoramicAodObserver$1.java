package com.oplus.systemui.statusbar.notification.collection;

import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadeListBuilderExImpl$panoramicAodObserver$1 implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener {
    public /* synthetic */ ShadeListBuilderExImpl this$0;

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
        Consumer consumer = this.this$0.dndVisualEffectsInvalidateAction;
        if (consumer != null) {
            consumer.accept("onPanoramicAodStateChanged");
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onProgressChanged(float f) {
    }
}
