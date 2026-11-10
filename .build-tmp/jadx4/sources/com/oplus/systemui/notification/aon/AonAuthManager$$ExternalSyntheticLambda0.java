package com.oplus.systemui.notification.aon;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class AonAuthManager$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ AonAuthManager f$0;

    public /* synthetic */ AonAuthManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        AonAuthManager aonAuthManager = this.f$0;
        switch (i) {
            case 0:
                return aonAuthManager.getFaceAuth().getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
            case 1:
                return AonAuthManager.m52$r8$lambda$XGifQe7873HQw0ayLsd82hnUVk(aonAuthManager);
            case 2:
                return AonAuthManager.$r8$lambda$8Zx_j0GFnDZn7S7eKCEic1BdQe4(aonAuthManager);
            default:
                return AonAuthManager.$r8$lambda$a5fsIOMgQVzpFgI9HPjkVpOukZU(aonAuthManager);
        }
    }
}
