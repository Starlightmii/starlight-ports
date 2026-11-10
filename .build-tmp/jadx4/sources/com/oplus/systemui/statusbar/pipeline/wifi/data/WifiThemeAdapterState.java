package com.oplus.systemui.statusbar.pipeline.wifi.data;

import com.android.launcher3.icons.IconProvider$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WifiThemeAdapterState {
    public final boolean isAdaptOs16Independent;
    public final boolean isAdaptOs17Independent;

    public WifiThemeAdapterState(boolean z, boolean z2) {
        this.isAdaptOs16Independent = z;
        this.isAdaptOs17Independent = z2;
    }

    public final WifiThemeAdapterState copy(boolean z, boolean z2) {
        return new WifiThemeAdapterState(z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WifiThemeAdapterState)) {
            return false;
        }
        WifiThemeAdapterState wifiThemeAdapterState = (WifiThemeAdapterState) obj;
        return this.isAdaptOs16Independent == wifiThemeAdapterState.isAdaptOs16Independent && this.isAdaptOs17Independent == wifiThemeAdapterState.isAdaptOs17Independent;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isAdaptOs17Independent) + (Boolean.hashCode(this.isAdaptOs16Independent) * 31);
    }

    public String toString() {
        return IconProvider$.ExternalSyntheticOutline0.m("WifiThemeAdapterState(isAdaptOs16Independent=", ", isAdaptOs17Independent=", ")", this.isAdaptOs16Independent, this.isAdaptOs17Independent);
    }
}
