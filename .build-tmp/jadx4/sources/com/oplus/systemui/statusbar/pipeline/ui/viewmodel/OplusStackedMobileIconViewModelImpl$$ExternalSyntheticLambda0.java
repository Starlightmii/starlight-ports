package com.oplus.systemui.statusbar.pipeline.ui.viewmodel;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusStackedMobileIconViewModelImpl f$0;

    public /* synthetic */ OplusStackedMobileIconViewModelImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        boolean activityInVisible;
        int i = this.$r8$classId;
        OplusStackedMobileIconViewModelImpl oplusStackedMobileIconViewModelImpl = this.f$0;
        switch (i) {
            case 0:
                activityInVisible = oplusStackedMobileIconViewModelImpl.stackedMobileIconViewModel.getActivityInVisible();
                break;
            default:
                activityInVisible = oplusStackedMobileIconViewModelImpl.stackedMobileIconViewModel.getActivityOutVisible();
                break;
        }
        return Boolean.valueOf(activityInVisible);
    }
}
