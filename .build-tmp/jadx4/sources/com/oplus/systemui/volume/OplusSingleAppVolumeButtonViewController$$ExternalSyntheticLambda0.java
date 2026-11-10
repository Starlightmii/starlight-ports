package com.oplus.systemui.volume;

import com.oplus.systemui.volume.view.SingleAppVolumeButtonContainer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusSingleAppVolumeButtonViewController f$0;

    public /* synthetic */ OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController = this.f$0;
        switch (i) {
            case 0:
                return OplusSingleAppVolumeButtonViewController.$r8$lambda$8lhrL_QuKpH8he6bKK9EDTZvKQQ(oplusSingleAppVolumeButtonViewController);
            case 1:
                return OplusSingleAppVolumeButtonViewController.$r8$lambda$y7UOe4Vd7fHtlsTA2nPL8yghXZw(oplusSingleAppVolumeButtonViewController);
            case 2:
                return OplusSingleAppVolumeButtonViewController.$r8$lambda$C9AjzqSJWZrKyctfsJjxyrkwj8Y(oplusSingleAppVolumeButtonViewController);
            default:
                oplusSingleAppVolumeButtonViewController.savePosition();
                SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = oplusSingleAppVolumeButtonViewController.containerView;
                if (singleAppVolumeButtonContainer != null) {
                    singleAppVolumeButtonContainer.setVisibility(8);
                }
                oplusSingleAppVolumeButtonViewController.isVisible = false;
                return Unit.INSTANCE;
        }
    }
}
