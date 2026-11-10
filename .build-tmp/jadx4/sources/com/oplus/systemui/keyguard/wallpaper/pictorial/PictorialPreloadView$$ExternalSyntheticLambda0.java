package com.oplus.systemui.keyguard.wallpaper.pictorial;

import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialPreloadView$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ PictorialPreloadView f$0;

    @Override // java.lang.Runnable
    public final void run() {
        PictorialPreloadView pictorialPreloadView = this.f$0;
        int i = PictorialPreloadView.$r8$clinit;
        KgdLogUtil.i("PictorialPreloadView", "resetStartedPictorialSlidePage");
        pictorialPreloadView.setStartingPictorialSlidePage(false);
    }
}
