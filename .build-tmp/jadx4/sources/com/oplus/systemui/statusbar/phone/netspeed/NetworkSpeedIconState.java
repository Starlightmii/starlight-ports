package com.oplus.systemui.statusbar.phone.netspeed;

import com.android.keyguard.logging.KeyguardLogger$;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NetworkSpeedIconState {
    private String contentDescription;
    private String slot;
    public long speedText;
    public boolean visible;

    public final NetworkSpeedIconState copy() {
        NetworkSpeedIconState networkSpeedIconState = new NetworkSpeedIconState();
        networkSpeedIconState.visible = this.visible;
        networkSpeedIconState.speedText = this.speedText;
        networkSpeedIconState.slot = this.slot;
        networkSpeedIconState.contentDescription = this.contentDescription;
        return networkSpeedIconState;
    }

    public boolean equals(Object obj) {
        if (obj != null && NetworkSpeedIconState.class.equals(obj.getClass())) {
            NetworkSpeedIconState networkSpeedIconState = (NetworkSpeedIconState) obj;
            if (this.visible == networkSpeedIconState.visible && this.speedText == networkSpeedIconState.speedText && Intrinsics.areEqual(this.contentDescription, networkSpeedIconState.contentDescription) && Intrinsics.areEqual(this.slot, networkSpeedIconState.slot)) {
                return true;
            }
        }
        return false;
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final String getSlot() {
        return this.slot;
    }

    public final long getSpeedText() {
        return this.speedText;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public final int hashCode() {
        return Objects.hash(Boolean.valueOf(this.visible), Long.valueOf(this.speedText), this.slot);
    }

    public final void setContentDescription(String str) {
        this.contentDescription = str;
    }

    public final void setSlot(String str) {
        this.slot = str;
    }

    public final void setSpeedText(long j) {
        this.speedText = j;
    }

    public final void setVisible(boolean z) {
        this.visible = z;
    }

    public String toString() {
        String str = this.slot;
        boolean z = this.visible;
        long j = this.speedText;
        String str2 = this.contentDescription;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("NetworkSpeedIconState(slot= ", str, " visible= ", z, " speedText:");
        sbM.append(j);
        sbM.append("contentDescription:");
        sbM.append(str2);
        sbM.append(")");
        return sbM.toString();
    }
}
