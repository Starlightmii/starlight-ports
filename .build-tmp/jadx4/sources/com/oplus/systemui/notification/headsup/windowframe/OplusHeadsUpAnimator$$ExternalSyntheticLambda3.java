package com.oplus.systemui.notification.headsup.windowframe;

import com.android.systemui.util.JankUtils;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHeadsUpAnimator$$ExternalSyntheticLambda3 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Function1 f$0;
    public /* synthetic */ OplusHeadsUpAnimator f$1;

    public /* synthetic */ OplusHeadsUpAnimator$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return OplusHeadsUpAnimator.$r8$lambda$tjRxtVfihYB2VUmhA6elwmskMKQ(this.f$0, this.f$1);
            default:
                OplusHeadsUpAnimator oplusHeadsUpAnimator = this.f$1;
                oplusHeadsUpAnimator.cleanupAndInvokeOnEnd(this.f$0);
                JankUtils.gfxSceneEnd(oplusHeadsUpAnimator.context, new int[]{42});
                return Unit.INSTANCE;
        }
    }
}
