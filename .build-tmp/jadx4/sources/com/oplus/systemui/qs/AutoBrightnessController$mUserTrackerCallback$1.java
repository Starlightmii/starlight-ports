package com.oplus.systemui.qs;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AutoBrightnessController$mUserTrackerCallback$1 implements UserTracker.Callback {
    public /* synthetic */ AutoBrightnessController this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public final void onUserChanged(int i, Context context) {
        AutoBrightnessController autoBrightnessController = this.this$0;
        AutoBrightnessController.access$getMBackgroundHandler$p(autoBrightnessController).post(AutoBrightnessController.access$getMUpdateModeRunnable$p(autoBrightnessController));
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
