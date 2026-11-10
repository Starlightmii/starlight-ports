package com.oplus.systemui.notification.row;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DateTimeView;
import android.widget.OplusNotificationDateTimeView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotifRemoteViewsFactory;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationDateTimeViewFactory implements NotifRemoteViewsFactory {
    public View instantiate(ExpandableNotificationRow expandableNotificationRow, int i, View view, String str, Context context, AttributeSet attributeSet) {
        if (Intrinsics.areEqual(str, DateTimeView.class.getName()) || Intrinsics.areEqual(str, "DateTimeView") || Intrinsics.areEqual(str, OplusNotificationDateTimeView.class.getName()) || Intrinsics.areEqual(str, "OplusNotificationDateTimeView")) {
            return new com.oplus.systemui.notification.row.widget.OplusNotificationDateTimeView(context, attributeSet, 4, 0);
        }
        return null;
    }
}
