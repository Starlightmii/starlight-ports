package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractor;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.StatusBarOperatorNameViewModel;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarOperatorNameViewModel extends StatusBarOperatorNameViewModel {
    private final Flow operatorName;
    private final Flow operatorNameFlow;

    public OplusStatusBarOperatorNameViewModel(Flow flow, MobileIconsInteractor mobileIconsInteractor) {
        super(mobileIconsInteractor);
        this.operatorNameFlow = flow;
        this.operatorName = flow;
    }

    public Flow getOperatorName() {
        return this.operatorName;
    }
}
