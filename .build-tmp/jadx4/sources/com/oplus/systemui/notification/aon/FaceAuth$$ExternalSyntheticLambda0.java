package com.oplus.systemui.notification.aon;

import android.content.Context;
import android.hardware.face.FaceManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FaceAuth$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ FaceAuth$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                Context context = (Context) obj;
                if (context.getPackageManager().hasSystemFeature("android.hardware.biometrics.face")) {
                    return (FaceManager) context.getSystemService("face");
                }
                return null;
            default:
                return FaceAuth.$r8$lambda$S5CxGlnQl_DxT52jThnga0v0e2w((FaceAuth) obj);
        }
    }
}
