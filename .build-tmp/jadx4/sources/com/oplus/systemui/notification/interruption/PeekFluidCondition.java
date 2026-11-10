package com.oplus.systemui.notification.interruption;

import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerExImpl;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PeekFluidCondition extends VisualInterruptionCondition {
    private String reason;
    private final OplusNotificationSectionsManagerExImpl sectionsManagerExImpl;

    public PeekFluidCondition(OplusNotificationSectionsManagerExImpl oplusNotificationSectionsManagerExImpl) {
        super(SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK), "peek panel state");
        this.sectionsManagerExImpl = oplusNotificationSectionsManagerExImpl;
        this.reason = "peek panel state";
    }

    public String getReason() {
        return this.reason;
    }

    public final boolean shouldSuppress() {
        if (this.sectionsManagerExImpl.canShowHeadsUpNotification()) {
            return false;
        }
        this.reason = "suppress by fluid can not show headsUp";
        return true;
    }
}
