package com.oplus.systemui.plugins.qs.seamless;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SeparateQSFakeStatusController$$ExternalSyntheticLambda4 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ViewGroup f$0;

    public /* synthetic */ SeparateQSFakeStatusController$$ExternalSyntheticLambda4(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        float transitionAlpha;
        int i = this.$r8$classId;
        ViewGroup viewGroup = this.f$0;
        switch (i) {
            case 0:
                SeparateQSFakeStatusController.Companion companion = SeparateQSFakeStatusController.Companion;
                transitionAlpha = viewGroup.getTransitionAlpha();
                break;
            default:
                SeparateQSFakeStatusController.Companion companion2 = SeparateQSFakeStatusController.Companion;
                transitionAlpha = viewGroup.getTransitionAlpha();
                break;
        }
        return Float.valueOf(transitionAlpha);
    }
}
