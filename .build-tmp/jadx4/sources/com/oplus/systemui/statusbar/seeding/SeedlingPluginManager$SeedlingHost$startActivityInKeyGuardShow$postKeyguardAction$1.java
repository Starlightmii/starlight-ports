package com.oplus.systemui.statusbar.seeding;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.plugins.ActivityStarter;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1 implements ActivityStarter.OnDismissAction {
    public /* synthetic */ Intent $intent;
    public /* synthetic */ int $userId;
    public /* synthetic */ SeedlingPluginManager.SeedlingHost this$0;

    public final boolean onDismiss() {
        try {
            this.this$0.getContext().startActivityAsUser(this.$intent, new UserHandle(this.$userId));
            return false;
        } catch (ActivityNotFoundException unused) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, "SeedlingPluginManager", "Activity not found after dismiss keyguard");
            return false;
        }
    }

    public final boolean willRunAnimationOnKeyguard() {
        return false;
    }
}
