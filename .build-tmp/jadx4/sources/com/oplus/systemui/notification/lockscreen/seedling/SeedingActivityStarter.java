package com.oplus.systemui.notification.lockscreen.seedling;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.launch.CapsuleActivityStarter;
import com.oplus.systemui.util.NotificationUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedingActivityStarter {
    private final CapsuleActivityStarter capsuleActivityStarter;
    private final ILockScreenExtStateController lockScreenExtStateController;

    public SeedingActivityStarter(CapsuleActivityStarter capsuleActivityStarter, ILockScreenExtStateController iLockScreenExtStateController) {
        this.capsuleActivityStarter = capsuleActivityStarter;
        this.lockScreenExtStateController = iLockScreenExtStateController;
    }

    public final void sendPendingIntent(Context context, PendingIntent pendingIntent, String str, boolean z, Function0 function0) throws PendingIntent.CanceledException {
        ExpandableView expandableViewFindSeedingCurrentView = str != null ? this.lockScreenExtStateController.findSeedingCurrentView(str) : null;
        if (expandableViewFindSeedingCurrentView != null) {
            this.capsuleActivityStarter.startViewIntent(pendingIntent, null, expandableViewFindSeedingCurrentView, z, true);
        } else {
            NotificationUtil.sendPendingIntent(context == null ? 0 : context.getDisplayId(), pendingIntent, false);
            function0.invoke();
        }
    }

    public final void startActivityAsUser(Context context, Intent intent, UserHandle userHandle, String str, boolean z, Function0 function0) {
        ExpandableView expandableViewFindSeedingCurrentView = str != null ? this.lockScreenExtStateController.findSeedingCurrentView(str) : null;
        if (expandableViewFindSeedingCurrentView != null) {
            this.capsuleActivityStarter.startViewIntent(PendingIntent.getActivityAsUser(context, 2, intent, 201326592, null, userHandle), null, expandableViewFindSeedingCurrentView, z, true);
            return;
        }
        context.startActivityAsUser(intent, userHandle);
        if (function0 != null) {
            function0.invoke();
        }
    }
}
