package com.oplus.systemui.statusbar.icon.ui.viewmodel;

import android.graphics.Rect;
import com.android.systemui.plugins.DarkIconDispatcher;
import java.util.Collection;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaViewModel$badgeViewColors$1$1 implements NotificationBadgeViewColorLookup {
    public /* synthetic */ Collection $areas;
    public /* synthetic */ int $tint;

    @Override // com.oplus.systemui.statusbar.icon.ui.viewmodel.NotificationBadgeViewColorLookup
    public final int iconColors(Rect rect) {
        if (DarkIconDispatcher.isInAreas(this.$areas, rect)) {
            return this.$tint;
        }
        return -855638017;
    }
}
