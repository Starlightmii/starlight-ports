package com.oplus.systemui.statusbar.seeding;

import com.android.systemui.statusbar.phone.StatusBarLocation;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsulePluginContainerController$setHiddenIconAreas$1 implements Runnable {
    public /* synthetic */ List $areas;
    public /* synthetic */ StatusBarLocation $location;
    public /* synthetic */ CapsulePluginContainerController this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.hiddenIconDispatcher.setHiddenIconAreas(this.$location, this.$areas);
    }
}
