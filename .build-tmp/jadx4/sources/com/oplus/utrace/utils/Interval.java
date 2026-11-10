package com.oplus.utrace.utils;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Interval {
    public static final Companion Companion = new Companion();
    public long checkTime;
    public long execTime;

    public final boolean checkInternal() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.checkTime < 1000) {
            return false;
        }
        this.checkTime = jCurrentTimeMillis;
        if (jCurrentTimeMillis / 60000 == this.execTime / 60000) {
            return false;
        }
        this.execTime = jCurrentTimeMillis;
        return true;
    }

    public final void exec(Function0 function0) {
        if (checkInternal()) {
            function0.invoke();
        }
    }
}
