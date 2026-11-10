package com.oplus.systemui.notification.lockscreen.launch;

import android.app.PendingIntent;
import android.content.Intent;
import com.android.systemui.statusbar.notification.row.ExpandableView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CapsuleActivityStarter {
    void startViewIntent(PendingIntent pendingIntent, Intent intent, ExpandableView expandableView, boolean z, boolean z2);
}
