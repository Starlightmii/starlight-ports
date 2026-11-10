package com.oplus.systemui.panelanimation.notification;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationRowShowAnimationExImpl f$0;
    public /* synthetic */ ExpandableView f$1;

    public /* synthetic */ OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return OplusNotificationRowShowAnimationExImpl.getCurrentAlphaAndScale(this.f$1);
            case 1:
                return OplusNotificationRowShowAnimationExImpl.getCurrentAlphaAndScale(this.f$1);
            default:
                return OplusNotificationRowShowAnimationExImpl.$r8$lambda$y7FPPIW_sER3LVhYC1RbCZk0Tok(this.f$0, this.f$1, ((Float) obj).floatValue());
        }
    }
}
