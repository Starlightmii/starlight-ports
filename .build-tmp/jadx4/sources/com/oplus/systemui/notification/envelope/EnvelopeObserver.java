package com.oplus.systemui.notification.envelope;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EnvelopeObserver extends AbstractObserver {
    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "";
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final Uri getUri(Context context) {
        return Uri.parse("content://com.androids.systemui/envelope");
    }
}
