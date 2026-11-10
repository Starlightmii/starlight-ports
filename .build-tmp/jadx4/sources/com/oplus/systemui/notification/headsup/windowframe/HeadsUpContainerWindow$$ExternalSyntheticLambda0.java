package com.oplus.systemui.notification.headsup.windowframe;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class HeadsUpContainerWindow$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ HeadsUpContainerWindow f$0;

    public /* synthetic */ HeadsUpContainerWindow$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        HeadsUpContainerWindow headsUpContainerWindow = this.f$0;
        switch (i) {
            case 0:
                return headsUpContainerWindow.getView();
            case 1:
                headsUpContainerWindow.notifySizeOrLocationChanged(null);
                return Unit.INSTANCE;
            case 2:
                return HeadsUpContainerWindow.$r8$lambda$cmsM9WJ_brVkYFsf0YyrHYhsS6U(headsUpContainerWindow);
            default:
                return HeadsUpContainerWindow.m59$r8$lambda$KmrlM24zAWPkoRTgPXqLvpsHi0(headsUpContainerWindow);
        }
    }
}
