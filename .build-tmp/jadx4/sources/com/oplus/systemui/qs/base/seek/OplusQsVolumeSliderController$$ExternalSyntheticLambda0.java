package com.oplus.systemui.qs.base.seek;

import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQsVolumeSliderController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsVolumeSliderController f$0;

    public /* synthetic */ OplusQsVolumeSliderController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.f$0;
        Boolean bool = (Boolean) obj;
        switch (i) {
            case 0:
                bool.getClass();
                if (!oplusQsVolumeSliderController.isTrackingTouch) {
                    oplusQsVolumeSliderController.handleRingModeUpdate();
                }
                return Unit.INSTANCE;
            case 1:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$cSdjCv6LVRiWcBap56UxQg8PBZ0(oplusQsVolumeSliderController);
            case 2:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$aIeWhn9NIAc6lipHiut_SpJYCUU(oplusQsVolumeSliderController);
            case 3:
                bool.getClass();
                oplusQsVolumeSliderController.updateDisableShowVolumeDialog();
                return Unit.INSTANCE;
            case 4:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$a0Q4JHBBbqbVSGuma1QJQgSOsww(oplusQsVolumeSliderController);
            case 5:
                bool.getClass();
                return OplusQsVolumeSliderController.m253$r8$lambda$6HxUEDzKTzoVnlpsuClFnm51WI(oplusQsVolumeSliderController);
            case 6:
                return OplusQsVolumeSliderController.$r8$lambda$jCvILZOPEvd_K_LfotdFCvRUNx0(oplusQsVolumeSliderController, bool.booleanValue());
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return OplusQsVolumeSliderController.m258$r8$lambda$jU7Qgi_eb_ETEQBI_fOplS5Ccg(oplusQsVolumeSliderController, bool.booleanValue());
            case 8:
                bool.getClass();
                return OplusQsVolumeSliderController.m256$r8$lambda$FbouehsUVSuWb7J5OO9Yrlfb4(oplusQsVolumeSliderController);
            case 9:
                bool.getClass();
                return OplusQsVolumeSliderController.m257$r8$lambda$ciXLnT8sHzvlaI9ksTAD_OmoS8(oplusQsVolumeSliderController);
            case 10:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$XjOuZ2VCgAYSrZYERug7YY3UUzA(oplusQsVolumeSliderController);
            case 11:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$pAgHISelkoe1TJ_xh3luLKmE3Ss(oplusQsVolumeSliderController);
            case 12:
                bool.getClass();
                return OplusQsVolumeSliderController.m255$r8$lambda$CTQDzgRn8uvxJ7CNgiv9wkYXzk(oplusQsVolumeSliderController);
            case 13:
                bool.getClass();
                return OplusQsVolumeSliderController.m254$r8$lambda$72oGk9fMjNLp7RbvFdvKuiFLcc(oplusQsVolumeSliderController);
            case 14:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$hJwv7A_fvAxQ9LdwdXH6vqo2oqM(oplusQsVolumeSliderController);
            default:
                bool.getClass();
                return OplusQsVolumeSliderController.$r8$lambda$M7DsjjOkrO8kvUSPjjJuNdlFzpA(oplusQsVolumeSliderController);
        }
    }
}
