package com.oplus.systemui.notification.lockscreen.statemanager;

import com.android.systemui.plugins.custom.CustomRowEntry;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingFocusCard extends FocusCard {
    private final CustomRowEntry data;
    private final OplusCustomRow stackRow;

    public SeedlingFocusCard(CustomRowEntry customRowEntry, OplusCustomRow oplusCustomRow) {
        super(customRowEntry.getKey());
        this.data = customRowEntry;
        this.stackRow = oplusCustomRow;
    }

    public final CustomRowEntry component1() {
        return this.data;
    }

    public final OplusCustomRow component2() {
        return this.stackRow;
    }

    public final SeedlingFocusCard copy(CustomRowEntry customRowEntry, OplusCustomRow oplusCustomRow) {
        return new SeedlingFocusCard(customRowEntry, oplusCustomRow);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeedlingFocusCard)) {
            return false;
        }
        SeedlingFocusCard seedlingFocusCard = (SeedlingFocusCard) obj;
        return Intrinsics.areEqual(this.data, seedlingFocusCard.data) && Intrinsics.areEqual(this.stackRow, seedlingFocusCard.stackRow);
    }

    public final CustomRowEntry getData() {
        return this.data;
    }

    public final OplusCustomRow getStackRow() {
        return this.stackRow;
    }

    public final int hashCode() {
        return this.stackRow.hashCode() + (this.data.hashCode() * 31);
    }

    public String toString() {
        return "SeedlingFocusCard(data=" + this.data + ", stackRow=" + this.stackRow + ")";
    }
}
