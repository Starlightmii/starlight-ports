package com.oplus.systemui.qs.pipeline.data.repository;

import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SuperPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ SuperPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$superPowerSaveObserver$1 f$0;

    public final Object invoke() {
        SuperPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$superPowerSaveObserver$1 superPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$superPowerSaveObserver$1 = this.f$0;
        LogUtil.i$default(LogUtil.TAG_QS, "SuperPowerSaveRepositoryImpl", "removeListener:  " + superPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$superPowerSaveObserver$1, null, 8);
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(superPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$superPowerSaveObserver$1);
        return Unit.INSTANCE;
    }
}
