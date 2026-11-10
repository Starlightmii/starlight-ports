package com.oplus.systemui.seedlingservice.mediaControl;

import com.oplus.systemui.media.seedling.utils.StatisticsManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MediaActionRunnable implements Runnable {
    private String cardEntrance = StatisticsManager.UPLOAD_VALUE_DEFAULT;

    public final String getCardEntrance() {
        return this.cardEntrance;
    }

    public final void runOn(String str) {
        this.cardEntrance = str;
        run();
    }

    public final void setCardEntrance(String str) {
        this.cardEntrance = str;
    }
}
