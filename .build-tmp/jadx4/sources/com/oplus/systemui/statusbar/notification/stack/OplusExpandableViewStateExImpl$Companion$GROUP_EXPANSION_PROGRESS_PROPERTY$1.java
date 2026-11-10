package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableViewStateExImpl$Companion$GROUP_EXPANSION_PROGRESS_PROPERTY$1 implements BiConsumer {
    public static final OplusExpandableViewStateExImpl$Companion$GROUP_EXPANSION_PROGRESS_PROPERTY$1 INSTANCE = new OplusExpandableViewStateExImpl$Companion$GROUP_EXPANSION_PROGRESS_PROPERTY$1();

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) obj;
        float fFloatValue = ((Number) obj2).floatValue();
        if (expandableNotificationRow == null || (oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx) == null) {
            return;
        }
        oplusExpandableNotificationRowEx.setGroupExpansionProgress(fFloatValue);
    }
}
