package com.oplus.systemui.separate;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSQuickEntranceManager$userTrackerCallback$1 implements UserTracker.Callback {
    public /* synthetic */ OplusSeparateQSQuickEntranceManager this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public final void onProfilesChanged(List list) {
        OplusSeparateQSQuickEntranceManager.access$notifyShowUserSwitcherRefreshRequested(this.this$0);
    }

    public final void onUserChanged(int i, Context context) {
        OplusSeparateQSQuickEntranceManager.access$notifyShowUserSwitcherRefreshRequested(this.this$0);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
