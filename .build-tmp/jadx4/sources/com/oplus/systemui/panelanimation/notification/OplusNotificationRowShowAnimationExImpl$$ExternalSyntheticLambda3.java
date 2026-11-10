package com.oplus.systemui.panelanimation.notification;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationRowShowAnimationExImpl f$0;
    public /* synthetic */ ExpandableView f$1;
    public /* synthetic */ boolean f$2;

    public /* synthetic */ OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.updateViewStateAlpha(this.f$1, ((Float) obj).floatValue(), this.f$2);
                break;
            case 1:
                this.f$0.updateViewStateScale(this.f$1, ((Float) obj).floatValue(), this.f$2);
                break;
            case 2:
                this.f$0.updateViewStateAlpha(this.f$1, ((Float) obj).floatValue(), this.f$2);
                break;
            default:
                this.f$0.updateViewStateScale(this.f$1, ((Float) obj).floatValue(), this.f$2);
                break;
        }
        return Unit.INSTANCE;
    }
}
