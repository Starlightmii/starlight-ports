package com.oplus.systemui.qs.widget;

import android.content.Context;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.settings.UserTracker;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFgsManagerController$userTrackerCallback$1 implements UserTracker.Callback {
    public /* synthetic */ OplusFgsManagerController this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public final void onProfilesChanged(List list) {
    }

    public final void onUserChanged(int i, Context context) {
        String str = OplusFgsManagerController.TAG;
        OplusFgsManagerController oplusFgsManagerController = this.this$0;
        LogUtil.i$default(LogUtil.TAG_QS, str, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, oplusFgsManagerController.mUserId, "onUserChanged: ", " "), null, 8);
        oplusFgsManagerController.mUserId = i;
        int numRunningPackagesLocked = oplusFgsManagerController.getNumRunningPackagesLocked();
        LogUtil.i$default(LogUtil.TAG_QS, OplusFgsManagerController.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(numRunningPackagesLocked, "onUserChanged numPackagesAfter: "), null, 8);
        if (numRunningPackagesLocked != oplusFgsManagerController.lastNumberOfVisiblePackages) {
            oplusFgsManagerController.updateNumberOfVisibleRunningPackagesLocked(numRunningPackagesLocked);
        }
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
