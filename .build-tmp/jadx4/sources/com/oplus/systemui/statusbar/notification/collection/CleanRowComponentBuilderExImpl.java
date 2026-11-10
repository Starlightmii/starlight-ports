package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import com.android.systemui.statusbar.notification.collection.CleanRowComponentBuilderEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.dagger.ExpandableNotificationRowComponent;
import com.oplus.systemui.notification.headsup.nssl.HeadsUpTempViewCreator;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CleanRowComponentBuilderExImpl extends CleanRowComponentBuilderEx {
    private final Lazy tempViewCreator;

    public CleanRowComponentBuilderExImpl(Context context, Lazy lazy) {
        super(context);
        this.tempViewCreator = lazy;
    }

    public void releaseExpandableNotificationRowComponentBuilder(ExpandableNotificationRowComponent.Builder builder) {
        ExpandableNotificationRow row;
        NotificationEntry temp = ((HeadsUpTempViewCreator) this.tempViewCreator.get()).getTemp();
        if (temp == null || (row = temp.getRow()) == null) {
            return;
        }
        builder.expandableNotificationRow(row).pipelineEntry(temp);
    }
}
