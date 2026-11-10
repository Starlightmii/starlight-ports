package com.oplus.systemui.qs.tileex;

import android.os.customize.OplusCustomizeRestrictionManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class AirplaneModeTileEx$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ AirplaneModeTileEx f$0;

    public final Object invoke() {
        AirplaneModeTileEx airplaneModeTileEx = this.f$0;
        int i = AirplaneModeTileEx.$r8$clinit;
        return OplusCustomizeRestrictionManager.getInstance(airplaneModeTileEx.getContext());
    }
}
