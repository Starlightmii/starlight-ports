package com.oplusos.systemui.common.util;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LogUtil$resetLogBufferRunnable$1 implements Runnable {
    public static final LogUtil$resetLogBufferRunnable$1 INSTANCE = new LogUtil$resetLogBufferRunnable$1();

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.setLogBufferDisabled(false);
    }
}
