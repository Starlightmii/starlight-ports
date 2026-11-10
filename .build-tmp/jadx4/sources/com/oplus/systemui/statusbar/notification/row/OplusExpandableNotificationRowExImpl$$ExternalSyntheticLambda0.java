package com.oplus.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.notification.NotificationUtils;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusExpandableNotificationRowExImpl f$0;

    public /* synthetic */ OplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int fontScaledHeight;
        int i = this.$r8$classId;
        OplusExpandableNotificationRowExImpl oplusExpandableNotificationRowExImpl = this.f$0;
        switch (i) {
            case 0:
                fontScaledHeight = NotificationUtils.getFontScaledHeight(2131170737, oplusExpandableNotificationRowExImpl.context);
                break;
            default:
                fontScaledHeight = NotificationUtils.getFontScaledHeight(2131170713, oplusExpandableNotificationRowExImpl.context);
                break;
        }
        return Integer.valueOf(fontScaledHeight);
    }
}
