package com.oplusos.systemui.common.view;

import android.graphics.Rect;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FrameInfo {
    public long frameNumber;
    private final Rect rect;

    public FrameInfo(Rect rect, long j) {
        this.rect = rect;
        this.frameNumber = j;
    }

    public final Rect component1() {
        return this.rect;
    }

    public final FrameInfo copy(Rect rect, long j) {
        return new FrameInfo(rect, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FrameInfo)) {
            return false;
        }
        FrameInfo frameInfo = (FrameInfo) obj;
        return Intrinsics.areEqual(this.rect, frameInfo.rect) && this.frameNumber == frameInfo.frameNumber;
    }

    public final long getFrameNumber() {
        return this.frameNumber;
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final int hashCode() {
        return Long.hashCode(this.frameNumber) + (this.rect.hashCode() * 31);
    }

    public final void setFrameNumber(long j) {
        this.frameNumber = j;
    }

    public String toString() {
        return "FrameInfo(rect=" + this.rect + ", frameNumber=" + this.frameNumber + ')';
    }
}
