package com.oplus.systemui.statusbar;

import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ListEntryWrapper {
    private final PipelineEntry listEntry;
    private final Long postTime;

    public ListEntryWrapper(PipelineEntry pipelineEntry, Long l) {
        this.listEntry = pipelineEntry;
        this.postTime = l;
    }

    public final PipelineEntry component1() {
        return this.listEntry;
    }

    public final Long component2() {
        return this.postTime;
    }

    public final ListEntryWrapper copy(PipelineEntry pipelineEntry, Long l) {
        return new ListEntryWrapper(pipelineEntry, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListEntryWrapper)) {
            return false;
        }
        ListEntryWrapper listEntryWrapper = (ListEntryWrapper) obj;
        return Intrinsics.areEqual(this.listEntry, listEntryWrapper.listEntry) && Intrinsics.areEqual(this.postTime, listEntryWrapper.postTime);
    }

    public final PipelineEntry getListEntry() {
        return this.listEntry;
    }

    public final Long getPostTime() {
        return this.postTime;
    }

    public final int hashCode() {
        int iHashCode = this.listEntry.hashCode() * 31;
        Long l = this.postTime;
        return iHashCode + (l == null ? 0 : l.hashCode());
    }

    public String toString() {
        return "ListEntryWrapper(listEntry=" + this.listEntry + ", postTime=" + this.postTime + ")";
    }
}
