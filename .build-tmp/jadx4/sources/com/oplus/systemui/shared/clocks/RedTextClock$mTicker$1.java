package com.oplus.systemui.shared.clocks;

import android.os.SystemClock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RedTextClock$mTicker$1 implements Runnable {
    public /* synthetic */ RedTextClock this$0;

    @Override // java.lang.Runnable
    public final void run() {
        RedTextClock redTextClock = this.this$0;
        RedTextClock.Companion companion = RedTextClock.Companion;
        redTextClock.onTimeChanged();
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.this$0.getHandler().postAtTime(this, (1000 - (jUptimeMillis % 1000)) + jUptimeMillis);
    }
}
