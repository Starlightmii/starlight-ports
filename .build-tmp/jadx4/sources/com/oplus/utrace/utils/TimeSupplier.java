package com.oplus.utrace.utils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TimeSupplier {
    public static final Companion Companion = new Companion();
    private static TimeSupplier instance = new TimeSupplier();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final TimeSupplier getInstance() {
            return TimeSupplier.instance;
        }

        public final void setInstance(TimeSupplier timeSupplier) {
            TimeSupplier.instance = timeSupplier;
        }
    }
}
