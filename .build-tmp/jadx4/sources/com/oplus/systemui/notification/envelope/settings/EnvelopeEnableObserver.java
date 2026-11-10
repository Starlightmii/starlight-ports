package com.oplus.systemui.notification.envelope.settings;

import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EnvelopeEnableObserver extends AbstractObserver {
    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "envelope_assistant_enable";
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toSecureUri();
    }
}
