package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import com.oplus.systemui.statusbar.notification.dnd.seedling.DndSeedling;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationKeyguardHelper_Factory implements Factory {
    public static NotificationKeyguardHelper newInstance(Context context, DndSeedling dndSeedling) {
        return new NotificationKeyguardHelper(context, dndSeedling);
    }
}
