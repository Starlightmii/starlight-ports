package com.oplus.systemui.notification.lockscreen.olivelink;

import java.util.Arrays;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OLiveLinkBaseParams$OLiveLinkGroup$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OLiveLinkBaseParams.OLiveLinkGroup f$0;
    public /* synthetic */ String f$1;

    public final Object invoke() {
        OLiveLinkBaseParams.OLiveLinkGroup oLiveLinkGroup = this.f$0;
        return String.format("%02d.%s", Arrays.copyOf(new Object[]{Integer.valueOf(oLiveLinkGroup.groupIndex), this.f$1}, 2));
    }
}
