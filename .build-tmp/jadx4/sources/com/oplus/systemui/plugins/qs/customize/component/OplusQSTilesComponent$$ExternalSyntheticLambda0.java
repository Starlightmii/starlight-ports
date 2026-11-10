package com.oplus.systemui.plugins.qs.customize.component;

import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSTilesComponent$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSTilesComponent f$0;

    public /* synthetic */ OplusQSTilesComponent$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusQSTilesComponent oplusQSTilesComponent = this.f$0;
        switch (i) {
            case 0:
                return OplusQSTilesComponent.m138$r8$lambda$iODuodBQnBPQRLJ4i6bkgsAxTQ(oplusQSTilesComponent);
            case 1:
                return OplusQSTilesComponent.m137$r8$lambda$dmAUnaNumvexSA3Tm8BQKCxejk(oplusQSTilesComponent);
            case 2:
                return OplusQSTilesComponent.m136$r8$lambda$7x7OwQdhXfDCW2WrekrPTAd4a0(oplusQSTilesComponent);
            case 3:
                return OplusQSTilesComponent.$r8$lambda$2za9LQtwjyZQr6DAJ25eQnzxbI4(oplusQSTilesComponent);
            case 4:
                return oplusQSTilesComponent.getCellSizeChangeProxy().getLayoutManagerFromCellInfo();
            case 5:
                return OplusQSTilesComponent.m140$r8$lambda$xyfeCWNQKP31aO5k04MRUx7eww(oplusQSTilesComponent);
            case 6:
                return OplusQSTilesComponent.$r8$lambda$NolZBkGq9f_XUvSWIgXKDckcBaE(oplusQSTilesComponent);
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return OplusQSTilesComponent.m139$r8$lambda$kJEOkrbJN0Y9mwOdgoiopIPesA(oplusQSTilesComponent);
            default:
                return Boolean.valueOf(oplusQSTilesComponent.editModeSwitchAnimationManager.isAnyRunning());
        }
    }
}
