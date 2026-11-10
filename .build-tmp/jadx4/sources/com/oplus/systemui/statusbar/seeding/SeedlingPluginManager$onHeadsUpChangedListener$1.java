package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpChangedListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$onHeadsUpChangedListener$1 implements OnHeadsUpChangedListener {
    public /* synthetic */ SeedlingPluginManager this$0;

    public final void onHeadsUpPinned(NotificationEntry notificationEntry) {
        this.this$0.onHeadsUpPinnedOrUnPinned(true, notificationEntry);
    }

    public final void onHeadsUpPinnedModeChanged(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onHeadsUpPinnedModeChanged, inPinnedMode: ", "SeedlingPluginManager-->", "SystemUi--Statusbar", z);
        }
        Bundle bundle = new Bundle();
        bundle.putString("state", "headsUpNotificationPinnedMode");
        bundle.putBoolean("headsUpNotificationPinnedMode", z);
        SeedlingPlugin seedlingPlugin = this.this$0.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle);
        }
    }

    public final void onHeadsUpUnPinned(NotificationEntry notificationEntry) {
        this.this$0.onHeadsUpPinnedOrUnPinned(false, notificationEntry);
    }
}
