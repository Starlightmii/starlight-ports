package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.app.Notification;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationDecoratedCustomViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationMessagingTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationMessagingTemplateViewWrapperEx;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationMessagingTemplateViewWrapperExImpl extends OplusNotificationTemplateViewWrapperExImpl implements OplusNotificationMessagingTemplateViewWrapperEx {
    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl
    public final void onFindViews() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationMessagingTemplateViewWrapper notificationMessagingTemplateViewWrapperM459getBase;
        View view;
        NotificationContentView privateLayout;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        String appName;
        super.onFindViews();
        NotificationMessagingTemplateViewWrapper notificationMessagingTemplateViewWrapperM459getBase2 = m459getBase();
        if (notificationMessagingTemplateViewWrapperM459getBase2 == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationMessagingTemplateViewWrapperM459getBase2).mRow) == null || (notificationMessagingTemplateViewWrapperM459getBase = m459getBase()) == null || (view = ((NotificationViewWrapper) notificationMessagingTemplateViewWrapperM459getBase).mView) == null || (privateLayout = expandableNotificationRow.getPrivateLayout()) == null || Intrinsics.areEqual(privateLayout.getExpandedChild(), view) || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (notification = sbn.getNotification()) == null || !TextUtils.isEmpty(notification.extras.getCharSequence("android.conversationTitle"))) {
            return;
        }
        View conversationText = getConversationText();
        if (conversationText == null || conversationText.getVisibility() != 0) {
            View conversationText2 = getConversationText();
            TextView textView = conversationText2 instanceof TextView ? (TextView) conversationText2 : null;
            if (textView == null || (appName = expandableNotificationRow.getAppName()) == null) {
                return;
            }
            String str = appName.length() > 0 ? appName : null;
            if (str == null) {
                return;
            }
            textView.setText(str);
            textView.setVisibility(0);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl, com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp
    public void setIsChildInGroup(boolean z) {
        super.setIsChildInGroup(z);
        NotificationMessagingTemplateViewWrapper notificationMessagingTemplateViewWrapperM459getBase = m459getBase();
        NotificationDecoratedCustomViewWrapper.hasCustomView(notificationMessagingTemplateViewWrapperM459getBase != null ? ((NotificationViewWrapper) notificationMessagingTemplateViewWrapperM459getBase).mView : null);
    }
}
