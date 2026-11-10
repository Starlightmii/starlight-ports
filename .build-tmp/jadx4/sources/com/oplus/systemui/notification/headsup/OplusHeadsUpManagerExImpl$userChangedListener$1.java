package com.oplus.systemui.notification.headsup;

import android.provider.Settings;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManagerImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpManagerExImpl$userChangedListener$1 implements NotificationLockscreenUserManager.UserChangedListener {
    public /* synthetic */ OplusHeadsUpManagerExImpl this$0;

    public final void onUserChanged(int i) {
        OplusHeadsUpManagerExImpl oplusHeadsUpManagerExImpl = this.this$0;
        ((HeadsUpManagerImpl) oplusHeadsUpManagerExImpl.headsUpManager.get()).cleanSnoozePkgs("user changed");
        oplusHeadsUpManagerExImpl.context.getContentResolver().unregisterContentObserver(oplusHeadsUpManagerExImpl.settingsObserver);
        oplusHeadsUpManagerExImpl.user = i;
        oplusHeadsUpManagerExImpl.loadSnoozeMode("user changed:" + i);
        oplusHeadsUpManagerExImpl.context.getContentResolver().registerContentObserver(oplusHeadsUpManagerExImpl.suppressBannerModeUri, false, oplusHeadsUpManagerExImpl.settingsObserver, oplusHeadsUpManagerExImpl.user);
        oplusHeadsUpManagerExImpl.needSoonzePrompt = Settings.System.getIntForUser(oplusHeadsUpManagerExImpl.context.getContentResolver(), "should_show_snooze_prompt", 1, oplusHeadsUpManagerExImpl.user);
    }
}
