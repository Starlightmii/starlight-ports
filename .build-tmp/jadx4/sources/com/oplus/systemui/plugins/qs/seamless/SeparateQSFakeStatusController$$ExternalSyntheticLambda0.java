package com.oplus.systemui.plugins.qs.seamless;

import android.widget.FrameLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SeparateQSFakeStatusController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SeparateQSFakeStatusController f$0;

    public /* synthetic */ SeparateQSFakeStatusController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        SeparateQSFakeStatusController separateQSFakeStatusController = this.f$0;
        switch (i) {
            case 0:
                SeparateQSFakeStatusController.Companion companion = SeparateQSFakeStatusController.Companion;
                separateQSFakeStatusController.updateElementTranslation$1();
                return Unit.INSTANCE;
            case 1:
                FrameLayout frameLayout = separateQSFakeStatusController.fakeCarrierContainer;
                return Float.valueOf((frameLayout != null ? frameLayout : null).getTransitionAlpha());
            case 2:
                FrameLayout frameLayout2 = separateQSFakeStatusController.fakeKeyguardStatusIconContainer;
                return Float.valueOf((frameLayout2 != null ? frameLayout2 : null).getTransitionAlpha());
            default:
                FrameLayout frameLayout3 = separateQSFakeStatusController.fakeCarrierContainer;
                return Float.valueOf((frameLayout3 != null ? frameLayout3 : null).getTransitionAlpha());
        }
    }
}
