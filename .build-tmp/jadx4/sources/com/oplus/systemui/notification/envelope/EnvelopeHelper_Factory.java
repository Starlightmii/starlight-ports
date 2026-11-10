package com.oplus.systemui.notification.envelope;

import android.content.Context;
import com.android.systemui.broadcast.BroadcastDispatcher;
import dagger.Lazy;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EnvelopeHelper_Factory implements Factory {
    public static EnvelopeHelper newInstance(Context context, BroadcastDispatcher broadcastDispatcher, Lazy lazy) {
        return new EnvelopeHelper(context, broadcastDispatcher, lazy);
    }
}
