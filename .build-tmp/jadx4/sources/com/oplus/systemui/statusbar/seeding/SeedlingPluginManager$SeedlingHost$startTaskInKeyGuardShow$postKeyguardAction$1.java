package com.oplus.systemui.statusbar.seeding;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1 implements ActivityStarter.OnDismissAction {
    public /* synthetic */ Intent $baseIntent;
    public /* synthetic */ int $taskId;
    public /* synthetic */ int $userId;
    public /* synthetic */ SeedlingPluginManager.SeedlingHost this$0;

    public final boolean onDismiss() {
        Intent intent;
        try {
            ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
            activityOptionsMakeBasic.setSplashScreenStyle(0);
            activityOptionsMakeBasic.setPendingIntentBackgroundActivityLaunchAllowed(true);
            ActivityManagerWrapper activityManagerWrapper = ActivityManagerWrapper.getInstance();
            int i = this.$taskId;
            activityManagerWrapper.getClass();
            if (ActivityManagerWrapper.startActivityFromRecents(activityOptionsMakeBasic, i) || (intent = this.$baseIntent) == null) {
                return false;
            }
            this.this$0.getContext().startActivityAsUser(intent, new UserHandle(this.$userId));
            return false;
        } catch (ActivityNotFoundException e) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, "SeedlingPluginManager", " startTask failed: " + e);
            return false;
        }
    }

    public final boolean willRunAnimationOnKeyguard() {
        return false;
    }
}
