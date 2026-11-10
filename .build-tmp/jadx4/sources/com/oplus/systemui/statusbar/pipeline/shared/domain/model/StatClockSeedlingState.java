package com.oplus.systemui.statusbar.pipeline.shared.domain.model;

import android.graphics.Rect;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatClockSeedlingState {
    private final ArrayList<Rect> hiddenAreas;
    public final boolean shouldScale;

    public /* synthetic */ StatClockSeedlingState() {
        this(new ArrayList(), false);
    }

    public final ArrayList<Rect> component1() {
        return this.hiddenAreas;
    }

    public final StatClockSeedlingState copy(ArrayList<Rect> arrayList, boolean z) {
        return new StatClockSeedlingState(arrayList, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StatClockSeedlingState)) {
            return false;
        }
        StatClockSeedlingState statClockSeedlingState = (StatClockSeedlingState) obj;
        return Intrinsics.areEqual(this.hiddenAreas, statClockSeedlingState.hiddenAreas) && this.shouldScale == statClockSeedlingState.shouldScale;
    }

    public final ArrayList<Rect> getHiddenAreas() {
        return this.hiddenAreas;
    }

    public final boolean getShouldScale() {
        return this.shouldScale;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.shouldScale) + (this.hiddenAreas.hashCode() * 31);
    }

    public String toString() {
        return "StatClockSeedlingState(hiddenAreas=" + this.hiddenAreas + ", shouldScale=" + this.shouldScale + ")";
    }

    public StatClockSeedlingState(ArrayList<Rect> arrayList, boolean z) {
        this.hiddenAreas = arrayList;
        this.shouldScale = z;
    }
}
