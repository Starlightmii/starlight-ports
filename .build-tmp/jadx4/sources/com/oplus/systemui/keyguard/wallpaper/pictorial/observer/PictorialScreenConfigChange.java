package com.oplus.systemui.keyguard.wallpaper.pictorial.observer;

import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialScreenConfigChange implements ScreenLifecycle.Observer {
    public final void onScreenTurnedOn() {
        KgdLogUtil.i("PictorialScreenConfigChange", "onScreenTurnedOn uploadInstalledEvent");
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (pictorialMediator.isTbStyle()) {
            ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialBuriedHelper().uploadInstalledEvent(pictorialMediator.getContext());
        }
    }
}
