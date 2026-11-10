package com.oplus.systemui.statusbar.seeding.livealert;

import com.oplus.wrapper.os.SystemProperties;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LiteUmsLoader$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        return Long.valueOf(SystemProperties.getLong("persist.debug.sys.ums.retry_delay", 300000L));
    }
}
