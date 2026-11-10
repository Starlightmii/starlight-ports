package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.view.NotificationTopLineView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OplusNotificationDateTimeView;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationMediaTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationMediaTemplateViewWrapperEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationMediaTemplateViewWrapperExImpl extends OplusNotificationTemplateViewWrapperExImpl implements OplusNotificationMediaTemplateViewWrapperEx {
    public final void updateMediaLayout() {
        NotificationTopLineView notificationTopLineView;
        View view;
        NotificationMediaTemplateViewWrapper notificationMediaTemplateViewWrapperM459getBase = m459getBase();
        if (OplusNotificationTemplateViewWrapperExImpl.MEDIA_TAG.equals((notificationMediaTemplateViewWrapperM459getBase == null || (view = ((NotificationViewWrapper) notificationMediaTemplateViewWrapperM459getBase).mView) == null) ? null : view.getTag())) {
            OplusNotificationDateTimeView dateView = getDateView();
            if (dateView != null) {
                dateView.setVisibility(8);
            }
            TextView timeDivider = getTimeDivider();
            if (timeDivider != null) {
                timeDivider.setVisibility(8);
            }
            NotificationMediaTemplateViewWrapper notificationMediaTemplateViewWrapperM459getBase2 = m459getBase();
            if (notificationMediaTemplateViewWrapperM459getBase2 == null || (notificationTopLineView = ((NotificationHeaderViewWrapper) notificationMediaTemplateViewWrapperM459getBase2).mNotificationTopLine) == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = notificationTopLineView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            if (marginLayoutParams != null) {
                marginLayoutParams.setMarginEnd(0);
                notificationTopLineView.setLayoutParams(marginLayoutParams);
            }
        }
    }
}
