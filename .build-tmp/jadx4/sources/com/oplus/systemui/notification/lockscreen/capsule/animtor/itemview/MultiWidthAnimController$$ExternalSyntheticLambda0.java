package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class MultiWidthAnimController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ MultiWidthAnimController f$0;

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        MultiWidthAnimController multiWidthAnimController = this.f$0;
        float fFloatValue = ((Float) obj).floatValue();
        switch (i) {
            case 0:
                multiWidthAnimController.getCurUpdateData().setHeight(fFloatValue);
                break;
            case 1:
                multiWidthAnimController.getCurUpdateData().setP1BlurRadius(fFloatValue);
                break;
            case 2:
                multiWidthAnimController.getCurUpdateData().setP2BlurRadius(fFloatValue);
                break;
            case 3:
                multiWidthAnimController.getCurUpdateData().setEarTransX(fFloatValue);
                break;
            case 4:
                multiWidthAnimController.getCurUpdateData().setEarTransX(fFloatValue);
                break;
            default:
                multiWidthAnimController.getCurUpdateData().setEarTransX(-fFloatValue);
                break;
        }
        return Unit.INSTANCE;
    }
}
