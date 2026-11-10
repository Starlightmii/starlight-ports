package com.oplus.systemui.keyguard.wallpaper.pictorial;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$9$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PictorialMediator.AnonymousClass9 f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PictorialMediator.AnonymousClass9 anonymousClass9 = this.f$0;
        switch (i) {
            case 0:
                PictorialMediator pictorialMediator = anonymousClass9.this$0;
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                pictorialMediator.doLiveCheck();
                break;
            default:
                PictorialMediator pictorialMediator2 = anonymousClass9.this$0;
                AtomicBoolean atomicBoolean2 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                pictorialMediator2.doLiveCheck();
                break;
        }
    }
}
