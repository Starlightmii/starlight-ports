package com.oplus.systemui.statusbar.notification.power.supersave;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusSuperSaveControllerImpl f$0;

    public /* synthetic */ OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl = this.f$0;
        switch (i) {
            case 0:
                return OplusSuperSaveControllerImpl.$r8$lambda$iHYiGv8SZ2NvWW3qhxU0VL_0_fg(oplusSuperSaveControllerImpl);
            case 1:
                OplusSuperSaveControllerImpl.Companion companion = OplusSuperSaveControllerImpl.Companion;
                return new OplusSuperSaveControllerImpl.ExitTimerTaskListener(oplusSuperSaveControllerImpl);
            default:
                OplusSuperSaveControllerImpl.Companion companion2 = OplusSuperSaveControllerImpl.Companion;
                return new OplusSuperSaveControllerImpl.EnterTimerTaskListener(oplusSuperSaveControllerImpl);
        }
    }
}
