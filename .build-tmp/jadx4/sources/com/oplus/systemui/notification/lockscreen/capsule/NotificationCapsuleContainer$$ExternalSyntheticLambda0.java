package com.oplus.systemui.notification.lockscreen.capsule;

import android.view.View;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationCapsuleContainer$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ NotificationCapsuleContainer$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invoke(Object obj) {
        View view = (View) obj;
        switch (this.$r8$classId) {
            case 0:
                NotificationCapsuleContainer.Companion companion = NotificationCapsuleContainer.Companion;
                if (view instanceof CapsuleItemView) {
                    return (CapsuleItemView) view;
                }
                return null;
            case 1:
                NotificationCapsuleContainer.Companion companion2 = NotificationCapsuleContainer.Companion;
                if (view instanceof CapsuleItemView) {
                    return (CapsuleItemView) view;
                }
                return null;
            case 2:
                return NotificationCapsuleContainer.setScrimAlpha$lambda$15(view);
            default:
                NotificationCapsuleContainer.Companion companion3 = NotificationCapsuleContainer.Companion;
                if (view instanceof CapsuleItemView) {
                    return (CapsuleItemView) view;
                }
                return null;
        }
    }
}
