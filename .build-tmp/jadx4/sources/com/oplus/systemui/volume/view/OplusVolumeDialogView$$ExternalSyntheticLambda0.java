package com.oplus.systemui.volume.view;

import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeDialogView$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusVolumeDialogView f$0;

    public /* synthetic */ OplusVolumeDialogView$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusVolumeDialogView oplusVolumeDialogView = this.f$0;
        switch (i) {
            case 0:
                return oplusVolumeDialogView.blurHostHelper.panelBackgroundDrawable();
            case 1:
                return Boolean.valueOf(oplusVolumeDialogView.flushSideStructuralUpdateIfReady());
            case 2:
                oplusVolumeDialogView.flushRowAndIconParent();
                return Unit.INSTANCE;
            case 3:
                oplusVolumeDialogView.flushRowAndIconParent();
                return Unit.INSTANCE;
            case 4:
                return Boolean.valueOf(oplusVolumeDialogView.flushSideStructuralUpdateIfReady());
            case 5:
                return oplusVolumeDialogView.mDialogView;
            case 6:
                return Boolean.valueOf(oplusVolumeDialogView.mIsDefaultVolumeTheme);
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return Boolean.valueOf(OplusVolumeDialogView.$r8$lambda$1BFdyl26VaukWNmNIpFbwyq2h0E(oplusVolumeDialogView));
            case 8:
                return OplusVolumeDialogView.m486$r8$lambda$5hMb88bCr1DOf6UnBJA7J1RGg0(oplusVolumeDialogView);
            case 9:
                return oplusVolumeDialogView.mVerticalRowIconParent;
            case 10:
                return OplusVolumeDialogView.$r8$lambda$3TLQ4uLzK5Lh9hj4X1udsgcbcCs(oplusVolumeDialogView);
            default:
                oplusVolumeDialogView.updateAppVolumeAdjust();
                return Unit.INSTANCE;
        }
    }
}
