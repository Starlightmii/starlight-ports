package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.internal.widget.MessagingLinearLayout;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1 implements Runnable {
    public /* synthetic */ boolean $imageMessageContainerVisible;
    public /* synthetic */ ViewGroup $it;
    public /* synthetic */ boolean $rightIconVisible;
    public /* synthetic */ OplusNotificationTemplateViewWrapperExImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        ImageView mRightIcon;
        View view;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = this.this$0.m459getBase();
        MessagingLinearLayout messagingLinearLayout = (notificationTemplateViewWrapperM459getBase == null || (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mView) == null) ? null : (MessagingLinearLayout) view.findViewById(R.id.pin_cancel_button);
        int measuredWidth = 0;
        if (this.$imageMessageContainerVisible) {
            MessagingLinearLayout imageMessageContainer = this.this$0.getImageMessageContainer();
            if (imageMessageContainer != null) {
                measuredWidth = imageMessageContainer.getMeasuredWidth();
            }
        } else if (this.$rightIconVisible && (mRightIcon = this.this$0.getMRightIcon()) != null) {
            measuredWidth = mRightIcon.getMeasuredWidth();
        }
        if (messagingLinearLayout != null) {
            messagingLinearLayout.setPaddingRelative(this.$it.getPaddingStart(), this.$it.getPaddingTop(), measuredWidth, this.$it.getPaddingBottom());
        }
    }
}
