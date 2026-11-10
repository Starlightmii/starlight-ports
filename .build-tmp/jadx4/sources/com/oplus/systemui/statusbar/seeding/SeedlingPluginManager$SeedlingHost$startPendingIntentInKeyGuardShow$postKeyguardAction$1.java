package com.oplus.systemui.statusbar.seeding;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import com.android.systemui.plugins.ActivityStarter;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1 implements ActivityStarter.OnDismissAction {
    public /* synthetic */ PendingIntent $pendingIntent;
    public /* synthetic */ SeedlingPluginManager.SeedlingHost this$0;

    public final boolean onDismiss() {
        try {
            ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
            activityOptionsMakeBasic.setSplashScreenStyle(0);
            activityOptionsMakeBasic.setPendingIntentBackgroundActivityLaunchAllowed(true);
            this.$pendingIntent.send(this.this$0.getContext(), 0, null, null, null, null, activityOptionsMakeBasic.toBundle());
            return false;
        } catch (PendingIntent.CanceledException e) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, "SeedlingPluginManager", "Sending intent failed: " + e);
            return false;
        }
    }

    public final boolean willRunAnimationOnKeyguard() {
        return false;
    }
}
