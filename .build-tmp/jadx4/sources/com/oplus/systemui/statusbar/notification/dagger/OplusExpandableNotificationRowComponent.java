package com.oplus.systemui.statusbar.notification.dagger;

import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.row.BigPictureIconManager;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.dagger.ExpandableNotificationRowComponent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusExpandableNotificationRowComponent implements ExpandableNotificationRowComponent {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Builder extends ExpandableNotificationRowComponent.Builder {
        OplusExpandableNotificationRowComponent build();

        /* synthetic */ ExpandableNotificationRowComponent.Builder expandableNotificationRow(ExpandableNotificationRow expandableNotificationRow);

        /* synthetic */ ExpandableNotificationRowComponent.Builder onExpandClickListener(ExpandableNotificationRow.OnExpandClickListener onExpandClickListener);

        /* synthetic */ ExpandableNotificationRowComponent.Builder pipelineEntry(PipelineEntry pipelineEntry);
    }

    public abstract /* synthetic */ BigPictureIconManager getBigPictureIconManager();

    public abstract /* synthetic */ ExpandableNotificationRowController getExpandableNotificationRowController();
}
