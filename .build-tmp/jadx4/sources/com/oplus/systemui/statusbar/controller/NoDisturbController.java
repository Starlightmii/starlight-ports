package com.oplus.systemui.statusbar.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NoDisturbController {
    private static final String BLOCK_BANNER_CHANNEL = "BLOCK_BANNER";
    private static final String CANCEL_ACTION = "com.oplusos.qs.nodisturbservice";
    public static final Companion Companion = new Companion();
    public static final String SETTING_KEY_NO_DISTURB = "qs_no_disturb";
    private static final String TAG_NO_DISTURB = "no_disturb";
    private Callback callback;
    private final Context context;
    private final NotificationManager notificationManager;
    private final NoDisturbController$settingKeyBlockBannerObserver$1 settingKeyBlockBannerObserver;
    private final UserTracker userTracker;
    private final NoDisturbController$userTrackerCallback$1 userTrackerCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.ContentObserver, com.oplus.systemui.statusbar.controller.NoDisturbController$settingKeyBlockBannerObserver$1] */
    public NoDisturbController(Context context, NotificationManager notificationManager, UserTracker userTracker) {
        this.context = context;
        this.notificationManager = notificationManager;
        this.userTracker = userTracker;
        final Handler handler = new Handler();
        ?? r0 = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.controller.NoDisturbController$settingKeyBlockBannerObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                NoDisturbController.Callback callback = this.this$0.callback;
                if (callback != null) {
                    NoDisturbController noDisturbController = this.this$0;
                    noDisturbController.updateNoDisturbStatus();
                    ((OplusPhoneStatusBarPolicyExImpl.AnonymousClass1) callback).updateSettingblockBannerrIcon(noDisturbController.checkBlockBannerStatus());
                }
            }
        };
        this.settingKeyBlockBannerObserver = r0;
        NoDisturbController$userTrackerCallback$1 noDisturbController$userTrackerCallback$1 = new NoDisturbController$userTrackerCallback$1();
        noDisturbController$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = noDisturbController$userTrackerCallback$1;
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor(SETTING_KEY_NO_DISTURB), r0, -1);
        userTracker.addCallback(noDisturbController$userTrackerCallback$1, context.getMainExecutor());
    }

    public final void addCallback(Callback callback) {
        this.callback = callback;
        if (callback != null) {
            updateNoDisturbStatus();
            ((OplusPhoneStatusBarPolicyExImpl.AnonymousClass1) callback).updateSettingblockBannerrIcon(checkBlockBannerStatus());
        }
    }

    public final boolean checkBlockBannerStatus() {
        return Settings.Secure.getIntForUser(this.context.getContentResolver(), SETTING_KEY_NO_DISTURB, 0, this.userTracker.getUserId()) == 1;
    }

    public final void updateNoDisturbStatus() {
        if (!checkBlockBannerStatus()) {
            NotificationManager notificationManager = this.notificationManager;
            if (notificationManager != null) {
                notificationManager.cancelAsUser(TAG_NO_DISTURB, 10008, UserHandle.CURRENT);
                return;
            }
            return;
        }
        Notification.Builder builder = new Notification.Builder(this.context, BLOCK_BANNER_CHANNEL);
        String string = this.context.getString(2131955320);
        Intent intent = new Intent(CANCEL_ACTION);
        intent.setPackage(this.context.getPackageName());
        Notification.Builder ongoing = builder.addAction(2131232631, string, PendingIntent.getService(this.context, 2, intent, 201326592)).setSmallIcon(2131235716).setContentTitle(this.context.getString(2131955323)).setStyle(new Notification.BigTextStyle()).setContentText(this.context.getString(2131955322)).setOngoing(true);
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.notifyAsUser(TAG_NO_DISTURB, 10008, ongoing.build(), UserHandle.CURRENT);
        }
    }
}
