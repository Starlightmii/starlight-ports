package com.oplus.systemui.plugins.qs.seamless;

import android.widget.FrameLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SeparateQSFakeStatusController$$ExternalSyntheticLambda1 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SeparateQSFakeStatusController f$0;

    public /* synthetic */ SeparateQSFakeStatusController$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        SeparateQSFakeStatusController separateQSFakeStatusController = this.f$0;
        float fFloatValue = ((Float) obj).floatValue();
        ((Float) obj2).getClass();
        switch (i) {
            case 0:
                return SeparateQSFakeStatusController.$r8$lambda$d4RsmOdVPSBUyc2Ct8XPh8GMlug(separateQSFakeStatusController, fFloatValue);
            case 1:
                return SeparateQSFakeStatusController.$r8$lambda$sWakutcQwggJXmnyGQk43cw4Wxk(separateQSFakeStatusController, fFloatValue);
            case 2:
                FrameLayout frameLayout = separateQSFakeStatusController.fakeCarrierContainer;
                if (frameLayout == null) {
                    frameLayout = null;
                }
                SeparateQSFakeStatusController.setFakeTransitionAlpha(fFloatValue, frameLayout);
                FrameLayout frameLayout2 = separateQSFakeStatusController.fakeKeyguardCarrierContainer;
                SeparateQSFakeStatusController.setFakeTransitionAlpha(fFloatValue, frameLayout2 != null ? frameLayout2 : null);
                return Unit.INSTANCE;
            default:
                FrameLayout frameLayout3 = separateQSFakeStatusController.fakeCarrierContainer;
                if (frameLayout3 == null) {
                    frameLayout3 = null;
                }
                SeparateQSFakeStatusController.setFakeTransitionAlpha(fFloatValue, frameLayout3);
                if (!separateQSFakeStatusController.isSmallScreen) {
                    FrameLayout frameLayout4 = separateQSFakeStatusController.fakeClockContainer;
                    if (frameLayout4 == null) {
                        frameLayout4 = null;
                    }
                    SeparateQSFakeStatusController.setFakeTransitionAlpha(fFloatValue, frameLayout4);
                    FrameLayout frameLayout5 = separateQSFakeStatusController.fakeNotificationIconContainer;
                    SeparateQSFakeStatusController.setFakeTransitionAlpha(fFloatValue, frameLayout5 != null ? frameLayout5 : null);
                }
                return Unit.INSTANCE;
        }
    }
}
