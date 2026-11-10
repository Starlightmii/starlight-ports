package com.oplus.systemui.seedlingservice.eventmanager;

import android.os.Bundle;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EventParse {
    public static final EventParse INSTANCE = new EventParse();
    public static final String SEEDLING_EVENT = "seedling_event";
    private static final String TAG = "EventParse";

    private EventParse() {
    }

    public final Event parseBundle(Bundle bundle) {
        String string = bundle.getString("seedling_event");
        if (string != null && !StringsKt__StringsKt.isBlank(string)) {
            return new Event(string, bundle);
        }
        LogUtil.w(TAG, "event code is null or blank");
        return null;
    }
}
