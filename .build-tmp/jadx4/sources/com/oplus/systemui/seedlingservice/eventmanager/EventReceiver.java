package com.oplus.systemui.seedlingservice.eventmanager;

import android.os.Bundle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EventReceiver {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "EventReceiver";

    public final void onLocaleChanged() throws JSONException {
        EventDispatch.INSTANCE.onLocaleChanged();
    }

    public final void onReceive(Bundle bundle) {
        Event bundle2 = EventParse.INSTANCE.parseBundle(bundle);
        if (bundle2 != null) {
            EventDispatch.INSTANCE.dispatch(bundle2);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
