package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 f$0;

    public final Object invoke() {
        OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 = this.f$0;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionRepositoryImpl.TAG, "slot oplusFeatureChanged removed", null, 8);
        CustomizeFeatureOption.sSlotFeatureChangeCallBacks.remove(oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1);
        return Unit.INSTANCE;
    }
}
