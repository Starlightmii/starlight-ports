package com.oplusos.systemui.common.util;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PendingResult {
    public volatile Object mResult = null;
    public final CountDownLatch mLatch = new CountDownLatch(1);

    public Object getResult() {
        return this.mResult;
    }

    public void setResult(Object obj) {
        this.mResult = obj;
        this.mLatch.countDown();
    }
}
