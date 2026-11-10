package com.oplus.systemui.notification.headsup.zoom;

import android.widget.ImageView;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class HeadsUpZoomParamsKt {
    public static final RowAnimationStateEx getRowAnimationStateEx(ExpandableView expandableView) {
        OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableView.getViewState().mExpandableViewStateEx;
        if (oplusExpandableViewStateEx != null) {
            return oplusExpandableViewStateEx.getRowAnimationStateEx();
        }
        return null;
    }

    public static final boolean isSupportZoom(ExpandableNotificationRow expandableNotificationRow) {
        ImageView dragView = expandableNotificationRow.mRowEx.getDragView();
        return dragView != null && dragView.getVisibility() == 0;
    }
}
