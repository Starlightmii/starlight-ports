package com.oplus.systemui.notification.row;

import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "一段动效参数_回弹spring曲线", 0.55f, 0.4f, 0.0f, 0.0f, 0.0f, null, null, "一段动效参数", 496);
            case 1:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "删除按钮动效参数_模糊spring曲线", 0.3f, 0.5f, 0.0f, 0.0f, 0.0f, null, null, "完全删除动效参数", 496);
            case 2:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "删除按钮动效参数_透明度spring曲线", 0.3f, 0.5f, 0.0f, 0.0f, 0.0f, null, null, "完全删除动效参数", 496);
            case 3:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "二段动效参数_删除按钮的宽度+位移", 0.3f, 0.5f, 0.0f, 0.0f, 0.0f, null, null, "二段动效参数", 496);
            case 4:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "二段动效参数_设置按钮的缩放+透明度+模糊", 0.3f, 0.5f, 0.0f, 0.0f, 0.0f, null, null, "二段动效参数", 496);
            case 5:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "二段动效参数_(反向)删除按钮的宽度+位移", 0.3f, 0.5f, 0.0f, 0.0f, 0.0f, null, null, "二段动效参数", 496);
            default:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "二段动效参数_(反向)设置按钮的缩放+透明度+模糊", 0.3f, 0.5f, 0.0f, 0.0f, 0.0f, null, null, "二段动效参数", 496);
        }
    }
}
