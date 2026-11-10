package com.oplus.systemui.statusbar.phone;

import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.OplusStatusBarNotificationActivityStarterEx;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.UbiquitousClickAfterDismissedParams;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1 implements ActivityStarter.OnDismissAction {
    public /* synthetic */ boolean $animate;
    public /* synthetic */ UbiquitousClickAfterDismissedParams $params;
    public /* synthetic */ boolean $showOverLockscreen;
    public /* synthetic */ OplusCentralSurfacesExImpl this$0;

    public final boolean onDismiss() {
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = this.this$0.notificationActivityStarter;
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = statusBarNotificationActivityStarter instanceof StatusBarNotificationActivityStarter ? statusBarNotificationActivityStarter : null;
        if (statusBarNotificationActivityStarter2 == null) {
            return false;
        }
        UbiquitousClickAfterDismissedParams ubiquitousClickAfterDismissedParams = this.$params;
        boolean z = this.$animate;
        boolean z2 = this.$showOverLockscreen;
        OplusStatusBarNotificationActivityStarterEx activityStarterEx = statusBarNotificationActivityStarter2.getActivityStarterEx();
        return Intrinsics.areEqual(activityStarterEx != null ? Boolean.valueOf(activityStarterEx.handleUbiquitousClickAfterKeyguardDismissed(ubiquitousClickAfterDismissedParams, z, z2)) : null, Boolean.TRUE);
    }

    public final boolean willRunAnimationOnKeyguard() {
        return this.$animate;
    }
}
