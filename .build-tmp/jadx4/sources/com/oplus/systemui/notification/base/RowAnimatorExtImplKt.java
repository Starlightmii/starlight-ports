package com.oplus.systemui.notification.base;

import android.support.v4.content.FileProvider$;
import android.view.View;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RowAnimatorExtImplKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final String tagId(View view) {
        if (view == 0) {
            return null;
        }
        if (view instanceof ExpandableNotificationRow) {
            return FileProvider$.ExternalSyntheticOutline0.m("ExpandableNotificationRow:", NotificationUtilsKt.getLogKeyNonNull(((ExpandableNotificationRow) view).getEntry()));
        }
        if (view instanceof OplusCustomRow) {
            CustomRowEntry entry = ((OplusCustomRow) view).getEntry();
            return FileProvider$.ExternalSyntheticOutline0.m("OplusCustomRow:", entry != null ? entry.getKey() : null);
        }
        if (!(view instanceof NotificationChildrenContainer)) {
            return view instanceof NotificationShelf ? "NotificationShelf" : ChildHelper$.ExternalSyntheticOutline0.m(view, "View:");
        }
        ExpandableNotificationRow containingNotification = ((NotificationChildrenContainer) view).getContainingNotification();
        return FileProvider$.ExternalSyntheticOutline0.m("NotificationChildrenContainer:", NotificationUtilsKt.getLogKey(containingNotification != null ? containingNotification.getEntry() : null));
    }
}
