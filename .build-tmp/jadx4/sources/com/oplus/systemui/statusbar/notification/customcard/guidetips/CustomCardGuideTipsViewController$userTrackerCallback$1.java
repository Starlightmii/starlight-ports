package com.oplus.systemui.statusbar.notification.customcard.guidetips;

import android.content.Context;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.settings.UserTracker;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomCardGuideTipsViewController$userTrackerCallback$1 implements UserTracker.Callback {
    public /* synthetic */ CustomCardGuideTipsViewController this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public final void onUserChanged(int i, Context context) {
        CustomCardGuideTipsViewController customCardGuideTipsViewController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onUserChanged: newUser=", "CustomCardGuideTipsVC-->", "SystemUi--Notification");
        }
        customCardGuideTipsViewController.sharedPreferences = customCardGuideTipsViewController.userFileManager.getSharedPreferences("la_immersive_tip_count", 0, i);
        customCardGuideTipsViewController.startEndAnimator();
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
