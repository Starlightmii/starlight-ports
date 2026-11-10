package com.oplus.systemui.keyguard.wallpaper.pictorial;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda11 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        PictorialMediator.IPictorialMediatorCallback iPictorialMediatorCallback = (PictorialMediator.IPictorialMediatorCallback) obj;
        switch (this.$r8$classId) {
            case 0:
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                iPictorialMediatorCallback.onSwitchPictorialApp();
                break;
            case 1:
                AtomicBoolean atomicBoolean2 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                iPictorialMediatorCallback.getClass();
                break;
            default:
                iPictorialMediatorCallback.onFingerPrintIconChanged();
                break;
        }
    }
}
