package com.oplus.systemui.statusbar.pipeline;

import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusWifiSignalExImpl f$0;
    public /* synthetic */ OplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1 f$1;

    public final Object invoke() {
        OplusWifiSignalExImpl oplusWifiSignalExImpl = this.f$0;
        SystemTypefaceHelper.Companion.getInstance(oplusWifiSignalExImpl.context).removeCallback(this.f$1);
        return Unit.INSTANCE;
    }
}
