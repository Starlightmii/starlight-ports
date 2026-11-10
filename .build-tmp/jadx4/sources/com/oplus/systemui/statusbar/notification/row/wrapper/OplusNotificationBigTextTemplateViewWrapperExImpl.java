package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.R;
import android.app.Notification;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationBigTextTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationDecoratedCustomViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationBigTextTemplateViewWrapperEx;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationBigTextTemplateViewWrapperExImpl extends OplusNotificationTemplateViewWrapperExImpl implements OplusNotificationBigTextTemplateViewWrapperEx {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl
    public final void resolveTitleAndText() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        NotificationBigTextTemplateViewWrapper notificationBigTextTemplateViewWrapperM459getBase;
        View view;
        TextView textView;
        CharSequence text;
        super.resolveTitleAndText();
        NotificationBigTextTemplateViewWrapper notificationBigTextTemplateViewWrapperM459getBase2 = m459getBase();
        if (notificationBigTextTemplateViewWrapperM459getBase2 == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationBigTextTemplateViewWrapperM459getBase2).mRow) == null || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (notification = sbn.getNotification()) == null || (notificationBigTextTemplateViewWrapperM459getBase = m459getBase()) == null || (view = ((NotificationViewWrapper) notificationBigTextTemplateViewWrapperM459getBase).mView) == null || (textView = (TextView) view.findViewById(R.id.chooser_edit_button)) == null) {
            return;
        }
        textView.setMaxLines(10);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        if (notification.isColorized() || (text = textView.getText()) == null) {
            return;
        }
        textView.setText(text.toString());
        textView.setTextColor(NotificationColorUtil.getCouiColorSecondaryNeutral(getContext()));
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl, com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp
    public void setIsChildInGroup(boolean z) {
        NotificationBigTextTemplateViewWrapper notificationBigTextTemplateViewWrapperM459getBase = m459getBase();
        if (NotificationDecoratedCustomViewWrapper.hasCustomView(notificationBigTextTemplateViewWrapperM459getBase != null ? ((NotificationViewWrapper) notificationBigTextTemplateViewWrapperM459getBase).mView : null)) {
            return;
        }
        resolveChildInGroup(z);
    }
}
