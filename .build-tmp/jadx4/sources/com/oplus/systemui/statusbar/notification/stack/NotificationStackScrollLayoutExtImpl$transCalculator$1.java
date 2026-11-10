package com.oplus.systemui.statusbar.notification.stack;

import com.coui.appcompat.springchain.COUIGridSpringChain;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutExtImpl$transCalculator$1 implements COUIGridSpringChain.TransCalculator {
    public final float getTrans(float f, int i, int i2) {
        return f * (i == 0 ? 0.24f : 0.06f);
    }
}
