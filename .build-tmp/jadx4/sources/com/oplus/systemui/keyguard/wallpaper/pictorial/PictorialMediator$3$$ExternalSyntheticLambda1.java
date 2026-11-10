package com.oplus.systemui.keyguard.wallpaper.pictorial;

import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$3$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((PictorialMediator.IPictorialMediatorCallback) obj).onMessageViewInfoChanged((PictorialMessageViewInfo) obj2);
                break;
            default:
                ((PictorialMediator.IPictorialMediatorCallback) obj).onPictorialGuideInfoChanged((PictorialGuideEntity) obj2);
                break;
        }
    }
}
