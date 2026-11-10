package com.oplus.systemui.statusbar.notification.flashlight.seeding;

import android.os.Bundle;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FlashlightSeedling$$ExternalSyntheticLambda1 implements Function1 {
    public final Object invoke(Object obj) {
        FlashlightTip.INSTANCE.handleClick((Bundle) obj);
        return Unit.INSTANCE;
    }
}
