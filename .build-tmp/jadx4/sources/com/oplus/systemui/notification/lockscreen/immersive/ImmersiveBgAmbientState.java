package com.oplus.systemui.notification.lockscreen.immersive;

import android.view.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImmersiveBgAmbientState {
    private ImmersiveBgEntry removingEntry;
    private final HashSet<ImmersiveBgEntry> removingEntrySet;
    private ImmersiveBgEntry showingEntry;

    public ImmersiveBgAmbientState(ImmersiveBgEntry immersiveBgEntry, ImmersiveBgEntry immersiveBgEntry2, HashSet<ImmersiveBgEntry> hashSet) {
        this.showingEntry = immersiveBgEntry;
        this.removingEntry = immersiveBgEntry2;
        this.removingEntrySet = hashSet;
    }

    public final ImmersiveBgEntry component1() {
        return this.showingEntry;
    }

    public final ImmersiveBgEntry component2() {
        return this.removingEntry;
    }

    public final HashSet<ImmersiveBgEntry> component3() {
        return this.removingEntrySet;
    }

    public final ImmersiveBgAmbientState copy(ImmersiveBgEntry immersiveBgEntry, ImmersiveBgEntry immersiveBgEntry2, HashSet<ImmersiveBgEntry> hashSet) {
        return new ImmersiveBgAmbientState(immersiveBgEntry, immersiveBgEntry2, hashSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImmersiveBgAmbientState)) {
            return false;
        }
        ImmersiveBgAmbientState immersiveBgAmbientState = (ImmersiveBgAmbientState) obj;
        return Intrinsics.areEqual(this.showingEntry, immersiveBgAmbientState.showingEntry) && Intrinsics.areEqual(this.removingEntry, immersiveBgAmbientState.removingEntry) && Intrinsics.areEqual(this.removingEntrySet, immersiveBgAmbientState.removingEntrySet);
    }

    public final ImmersiveBgEntry findByView(View view) {
        ImmersiveBgEntry immersiveBgEntry = this.showingEntry;
        Object obj = null;
        if ((immersiveBgEntry != null ? immersiveBgEntry.getView() : null) == view) {
            return this.showingEntry;
        }
        ImmersiveBgEntry immersiveBgEntry2 = this.removingEntry;
        if ((immersiveBgEntry2 != null ? immersiveBgEntry2.getView() : null) == view) {
            return this.removingEntry;
        }
        for (Object obj2 : this.removingEntrySet) {
            if (((ImmersiveBgEntry) obj2).getView() == view) {
                obj = obj2;
                break;
            }
        }
        return (ImmersiveBgEntry) obj;
    }

    public final List<ImmersiveBgEntry> getAllSizeNormalEntries() {
        HashSet<ImmersiveBgEntry> hashSet = this.removingEntrySet;
        ArrayList arrayList = new ArrayList();
        for (Object obj : hashSet) {
            if (((ImmersiveBgEntry) obj).cardType == 2) {
                arrayList.add(obj);
            }
        }
        List<ImmersiveBgEntry> mutableList = CollectionsKt___CollectionsKt.toMutableList(arrayList);
        ImmersiveBgEntry immersiveBgEntry = this.showingEntry;
        if (immersiveBgEntry != null && immersiveBgEntry.cardType == 2) {
            mutableList.add(immersiveBgEntry);
        }
        ImmersiveBgEntry immersiveBgEntry2 = this.removingEntry;
        if (immersiveBgEntry2 != null && immersiveBgEntry2.cardType == 2) {
            mutableList.add(immersiveBgEntry2);
        }
        return mutableList;
    }

    public final ImmersiveBgEntry getRemovingEntry() {
        return this.removingEntry;
    }

    public final HashSet<ImmersiveBgEntry> getRemovingEntrySet() {
        return this.removingEntrySet;
    }

    public final ImmersiveBgEntry getShowingEntry() {
        return this.showingEntry;
    }

    public final int hashCode() {
        ImmersiveBgEntry immersiveBgEntry = this.showingEntry;
        int iHashCode = (immersiveBgEntry == null ? 0 : immersiveBgEntry.hashCode()) * 31;
        ImmersiveBgEntry immersiveBgEntry2 = this.removingEntry;
        return this.removingEntrySet.hashCode() + ((iHashCode + (immersiveBgEntry2 != null ? immersiveBgEntry2.hashCode() : 0)) * 31);
    }

    public final void setRemovingEntry(ImmersiveBgEntry immersiveBgEntry) {
        this.removingEntry = immersiveBgEntry;
    }

    public final void setShowingEntry(ImmersiveBgEntry immersiveBgEntry) {
        this.showingEntry = immersiveBgEntry;
    }

    public String toString() {
        return "ImmersiveBgAmbientState(showingEntry=" + this.showingEntry + ", removingEntry=" + this.removingEntry + ", removingEntrySet=" + this.removingEntrySet + ")";
    }
}
