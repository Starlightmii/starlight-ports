package com.oplus.systemui.notification.lockscreen.capsule;

import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleEarShapeTool;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleBackgroundHelper$$ExternalSyntheticLambda0 implements Function1 {
    public /* synthetic */ ViewBlurProxy f$0;

    public final Object invoke(Object obj) {
        ViewBlurProxy viewBlurProxy = this.f$0;
        MetaBallBlurDrawable metaBallBlurDrawable = (BlurDrawable) obj;
        if (metaBallBlurDrawable instanceof MetaBallBlurDrawable) {
            CapsuleEarShapeTool.INSTANCE.initMetaBallParams(metaBallBlurDrawable, viewBlurProxy.getBlurConfig().getCornerRadius(), false);
        }
        return Unit.INSTANCE;
    }
}
