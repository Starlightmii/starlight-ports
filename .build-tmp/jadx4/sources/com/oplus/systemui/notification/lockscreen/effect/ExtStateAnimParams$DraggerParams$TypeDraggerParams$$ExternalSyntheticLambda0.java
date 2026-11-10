package com.oplus.systemui.notification.lockscreen.effect;

import androidx.car.app.serialization.Bundler$;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ExtStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ExtStateAnimParams.DraggerParams.TypeDraggerParams f$0;

    public /* synthetic */ ExtStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        ExtStateAnimParams.DraggerParams.TypeDraggerParams typeDraggerParams = this.f$0;
        switch (i) {
            case 0:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(typeDraggerParams.dragerType, " 跟手_阈值dp，到达后切三态状态"), typeDraggerParams.groupName, Integer.valueOf(typeDraggerParams.defaultValue.getDragMaxAction()), 16);
            default:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(typeDraggerParams.dragerType, " _跟手spring曲线"), typeDraggerParams.defaultValue.getSpring().getBounce(), typeDraggerParams.defaultValue.getSpring().getResponse(), 0.0f, 0.0f, 0.0f, null, null, typeDraggerParams.groupName, 496);
        }
    }
}
