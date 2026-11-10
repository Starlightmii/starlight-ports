package com.oplus.systemui.statusbar.pipeline.mobile.ui;

import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.material3.internal.TextFieldImplKt$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MobileThemeAdapterState {
    public final boolean isAdaptOs16Independent;
    public final boolean isAdaptOs17Independent;
    public final boolean isAdaptOs17Stacked;

    public MobileThemeAdapterState(boolean z, boolean z2, boolean z3) {
        this.isAdaptOs17Independent = z;
        this.isAdaptOs17Stacked = z2;
        this.isAdaptOs16Independent = z3;
    }

    public final MobileThemeAdapterState copy(boolean z, boolean z2, boolean z3) {
        return new MobileThemeAdapterState(z, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MobileThemeAdapterState)) {
            return false;
        }
        MobileThemeAdapterState mobileThemeAdapterState = (MobileThemeAdapterState) obj;
        return this.isAdaptOs17Independent == mobileThemeAdapterState.isAdaptOs17Independent && this.isAdaptOs17Stacked == mobileThemeAdapterState.isAdaptOs17Stacked && this.isAdaptOs16Independent == mobileThemeAdapterState.isAdaptOs16Independent;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isAdaptOs16Independent) + ChangeSize$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.isAdaptOs17Independent) * 31, 31, this.isAdaptOs17Stacked);
    }

    public final boolean isUnstackedUseTheme17() {
        return this.isAdaptOs17Independent || !this.isAdaptOs16Independent;
    }

    public String toString() {
        return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", TextFieldImplKt$.ExternalSyntheticOutline0.m("MobileThemeAdapterState(isAdaptOs17Independent=", ", isAdaptOs17Stacked=", ", isAdaptOs16Independent=", this.isAdaptOs17Independent, this.isAdaptOs17Stacked), this.isAdaptOs16Independent);
    }
}
