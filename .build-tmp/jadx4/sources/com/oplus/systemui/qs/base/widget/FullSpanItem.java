package com.oplus.systemui.qs.base.widget;

import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FullSpanItem {
    public final int position;
    public int size;
    public final int spans;

    public FullSpanItem(int i, int i2, int i3) {
        this.position = i;
        this.spans = i2;
        this.size = i3;
    }

    public final FullSpanItem copy(int i, int i2, int i3) {
        return new FullSpanItem(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FullSpanItem)) {
            return false;
        }
        FullSpanItem fullSpanItem = (FullSpanItem) obj;
        return this.position == fullSpanItem.position && this.spans == fullSpanItem.spans && this.size == fullSpanItem.size;
    }

    public final int getPosition() {
        return this.position;
    }

    public final int getSize() {
        return this.size;
    }

    public final int getSpans() {
        return this.spans;
    }

    public final int hashCode() {
        return Integer.hashCode(this.size) + MonoSpline$.ExternalSyntheticOutline0.m(this.spans, Integer.hashCode(this.position) * 31, 31);
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public String toString() {
        return SurfaceContainer$.ExternalSyntheticOutline0.m(this.size, ")", FloatList$.ExternalSyntheticOutline0.m("FullSpanItem(position=", this.position, this.spans, ", spans=", ", size="));
    }
}
