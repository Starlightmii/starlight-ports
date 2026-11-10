package com.oplus.systemui.seedlingservice.eventmanager;

import android.os.Bundle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Event {
    private final Bundle data;
    private final String eventCode;

    public /* synthetic */ Event(String str, Bundle bundle, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : bundle);
    }

    private final String component1() {
        return this.eventCode;
    }

    private final Bundle component2() {
        return this.data;
    }

    public static /* synthetic */ Event copy$default(Event event, String str, Bundle bundle, int i, Object obj) {
        if ((i & 1) != 0) {
            str = event.eventCode;
        }
        if ((i & 2) != 0) {
            bundle = event.data;
        }
        return event.copy(str, bundle);
    }

    public final Event copy(String str, Bundle bundle) {
        return new Event(str, bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        return Intrinsics.areEqual(this.eventCode, event.eventCode) && Intrinsics.areEqual(this.data, event.data);
    }

    public final String getEvent() {
        return this.eventCode;
    }

    public final Bundle getInfo() {
        return this.data;
    }

    public int hashCode() {
        int iHashCode = this.eventCode.hashCode() * 31;
        Bundle bundle = this.data;
        return iHashCode + (bundle == null ? 0 : bundle.hashCode());
    }

    public String toString() {
        return "Event(eventCode=" + this.eventCode + ", data=" + this.data + ")";
    }

    public Event(String str, Bundle bundle) {
        this.eventCode = str;
        this.data = bundle;
    }
}
