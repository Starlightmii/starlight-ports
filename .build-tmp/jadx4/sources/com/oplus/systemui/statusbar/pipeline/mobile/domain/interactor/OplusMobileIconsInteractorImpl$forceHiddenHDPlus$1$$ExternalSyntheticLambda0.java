package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.tuner.TunerService;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ TunerService f$0;
    public /* synthetic */ OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1 f$1;

    public final Object invoke() {
        this.f$0.removeTunable(this.f$1);
        return Unit.INSTANCE;
    }
}
