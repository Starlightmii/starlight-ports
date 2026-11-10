package com.oplus.systemui.keyguard.wallpaper.pictorial;

import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda3 implements IObserverListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PictorialMediator f$0;

    public /* synthetic */ PictorialMediator$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        int i = this.$r8$classId;
        PictorialMediator pictorialMediator = this.f$0;
        switch (i) {
            case 0:
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(1);
                pictorialMediator$$ExternalSyntheticLambda1.f$0 = pictorialMediator;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(pictorialMediator$$ExternalSyntheticLambda1);
                break;
            default:
                pictorialMediator.setChildModeOplusGLStatus(ChildrenModeObserver.getInstance().mIsChildrenMode);
                break;
        }
    }
}
