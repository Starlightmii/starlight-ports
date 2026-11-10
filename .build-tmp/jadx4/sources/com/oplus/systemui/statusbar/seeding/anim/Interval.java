package com.oplus.systemui.statusbar.seeding.anim;

import androidx.appcompat.widget.ListPopupWindow$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Interval {
    public final float end;
    public final float start;

    public Interval(float f, float f2) {
        this.start = f;
        this.end = f2;
    }

    public final Interval copy(float f, float f2) {
        return new Interval(f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Interval)) {
            return false;
        }
        Interval interval = (Interval) obj;
        return Float.compare(this.start, interval.start) == 0 && Float.compare(this.end, interval.end) == 0;
    }

    public final float getEnd() {
        return this.end;
    }

    public final float getStart() {
        return this.start;
    }

    public final int hashCode() {
        return Float.hashCode(this.end) + (Float.hashCode(this.start) * 31);
    }

    public final boolean intersects(Interval interval) {
        return this.end >= interval.start && interval.end >= this.start;
    }

    public String toString() {
        return ListPopupWindow$.ExternalSyntheticOutline0.m("Interval(start=", this.start, ", end=", this.end, ")");
    }
}
