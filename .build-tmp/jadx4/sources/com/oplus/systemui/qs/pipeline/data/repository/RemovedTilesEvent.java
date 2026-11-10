package com.oplus.systemui.qs.pipeline.data.repository;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemovedTilesEvent {
    private final List<String> tileSpecs;
    public final int userId;

    public RemovedTilesEvent(int i, List<String> list) {
        this.userId = i;
        this.tileSpecs = list;
    }

    public final List<String> component2() {
        return this.tileSpecs;
    }

    public final RemovedTilesEvent copy(int i, List<String> list) {
        return new RemovedTilesEvent(i, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RemovedTilesEvent)) {
            return false;
        }
        RemovedTilesEvent removedTilesEvent = (RemovedTilesEvent) obj;
        return this.userId == removedTilesEvent.userId && Intrinsics.areEqual(this.tileSpecs, removedTilesEvent.tileSpecs);
    }

    public final List<String> getTileSpecs() {
        return this.tileSpecs;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final int hashCode() {
        return this.tileSpecs.hashCode() + (Integer.hashCode(this.userId) * 31);
    }

    public String toString() {
        return "RemovedTilesEvent(userId=" + this.userId + ", tileSpecs=" + this.tileSpecs + ")";
    }
}
