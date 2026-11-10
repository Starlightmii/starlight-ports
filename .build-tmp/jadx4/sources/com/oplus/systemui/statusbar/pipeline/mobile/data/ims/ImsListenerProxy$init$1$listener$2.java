package com.oplus.systemui.statusbar.pipeline.mobile.data.ims;

import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImsListenerProxy$init$1$listener$2 implements BiConsumer {
    public /* synthetic */ ImsListenerProxy this$0;

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ImsListenerProxy.access$imsConnectorUnavailableCallback(this.this$0, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
    }
}
