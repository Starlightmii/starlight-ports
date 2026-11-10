package com.oplus.systemui.statusbar.pipeline.mobile.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMobileThemeManagerRepositoryImpl$themeAdapterState$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusMobileThemeManagerRepositoryImpl f$0;
    public /* synthetic */ OplusMobileThemeManagerRepositoryImpl$themeAdapterState$1$themeListener$1 f$1;

    public final Object invoke() {
        OplusMobileThemeManagerRepositoryImpl oplusMobileThemeManagerRepositoryImpl = this.f$0;
        oplusMobileThemeManagerRepositoryImpl.oplusThemeController.removeCallback(this.f$1);
        return Unit.INSTANCE;
    }
}
