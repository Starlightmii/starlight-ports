package com.oplus.systemui.notification.lockscreen.statemanager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class FocusCard {
    private final String key;

    public FocusCard(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
