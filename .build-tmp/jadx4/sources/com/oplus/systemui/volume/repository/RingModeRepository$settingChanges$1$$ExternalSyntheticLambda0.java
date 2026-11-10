package com.oplus.systemui.volume.repository;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class RingModeRepository$settingChanges$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ RingModeRepository f$0;
    public /* synthetic */ RingModeRepository$settingChanges$1$observer$1 f$1;

    public final Object invoke() {
        RingModeRepository ringModeRepository = this.f$0;
        ringModeRepository.context.getContentResolver().unregisterContentObserver(this.f$1);
        return Unit.INSTANCE;
    }
}
