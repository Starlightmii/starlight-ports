package com.oplus.systemui.qs.base.res.model;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InterestingViewFlag {
    private int[] drawableState;
    private final AtomicInteger tileState = new AtomicInteger(1);
    private AtomicBoolean dragged = new AtomicBoolean(false);
    private AtomicBoolean toggleable = new AtomicBoolean(false);

    public final int[] getDrawableState() {
        return this.drawableState;
    }

    public final int getTileState() {
        return this.tileState.get();
    }

    public final boolean isDragged() {
        return this.dragged.get();
    }

    public final boolean isToggleable() {
        return this.toggleable.get();
    }

    public final boolean setDragged(boolean z) {
        return this.dragged.compareAndSet(!z, z);
    }

    public final void setDrawableState(int[] iArr) {
        this.drawableState = iArr;
    }

    public final boolean setTileState(int i) {
        return this.tileState.getAndSet(i) != i;
    }

    public final boolean setToggleable(boolean z) {
        return this.toggleable.compareAndSet(!z, z);
    }

    public String toString() {
        return "InterestingViewFlag(drawableState=" + this.drawableState + ", tileState=" + getTileState() + ", dragged=" + this.dragged.get() + ", toggleable=" + this.toggleable + ")";
    }
}
