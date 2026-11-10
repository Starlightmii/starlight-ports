package com.oplus.systemui.qs.res.data.repository;

import com.coui.appcompat.COUIMaterialStateManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSPersonalityRepository$couiSpotLightEnabled$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ QSPersonalityRepository$couiSpotLightEnabled$1$listener$1 f$0;

    public final Object invoke() {
        COUIMaterialStateManager.INSTANCE.removeOnMaterialStateChangedListener(this.f$0);
        return Unit.INSTANCE;
    }
}
