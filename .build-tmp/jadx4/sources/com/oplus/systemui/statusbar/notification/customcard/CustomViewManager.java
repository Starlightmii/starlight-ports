package com.oplus.systemui.statusbar.notification.customcard;

import android.content.Intent;
import android.graphics.Bitmap;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.oplus.systemui.statusbar.notification.customcard.guidetips.CustomCardGuideTipsViewController;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomViewManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CustomViewManager";
    private final OplusCustomRowControllerManager customManager;
    private final CustomCardGuideTipsViewController guideTipsController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CustomViewManager(OplusCustomRowControllerManager oplusCustomRowControllerManager, CustomCardGuideTipsViewController customCardGuideTipsViewController) {
        this.customManager = oplusCustomRowControllerManager;
        this.guideTipsController = customCardGuideTipsViewController;
    }

    public final void dismissPopupMenu(String str) {
        this.customManager.dismissPopupMenu(str);
    }

    public final void onSeedingNotificationDataChange(List<? extends CustomRowEntry> list, boolean z) {
        this.customManager.setCustomContainerControllerList(list, z);
        this.guideTipsController.onDataChanged(list);
    }

    public final void requestImmersiveState(int i, int i2, String str) {
        this.customManager.requestImmersiveState(i, i2, str);
    }

    public final void setNssLController(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.customManager.setNssLController(notificationStackScrollLayoutController);
    }

    public final void startPopupMenu(CustomRowEntry customRowEntry) {
        this.customManager.startPopupMenu(customRowEntry);
    }

    public final void startSettingIntent(Intent intent, int i, String str, int i2) {
        this.customManager.startSeedingViewIntent(intent, i, str, i2);
    }

    public final void updateLockWallpaper(Bitmap bitmap) {
    }
}
