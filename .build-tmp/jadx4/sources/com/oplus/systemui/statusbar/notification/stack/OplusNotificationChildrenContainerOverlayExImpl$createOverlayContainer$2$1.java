package com.oplus.systemui.statusbar.notification.stack;

import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ GroupOverlayContainerView $this_apply;
    public /* synthetic */ OplusNotificationChildrenContainerOverlayExImpl this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String name = DebugToolKt.getName(view, false);
            OplusNotificationChildrenContainerOverlayExImpl.Companion companion = OplusNotificationChildrenContainerOverlayExImpl.Companion;
            ExpandableNotificationRow containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onViewAttachedToWindow view=", name, ",key=", (containingNotification == null || (entry = containingNotification.getEntry()) == null || (sbn = entry.getSbn()) == null) ? null : sbn.getKey(), "OplusNCCOverlayEx-->"));
        }
        OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl2 = this.this$0;
        OplusNotificationChildrenContainerOverlayExImpl.Companion companion2 = OplusNotificationChildrenContainerOverlayExImpl.Companion;
        oplusNotificationChildrenContainerOverlayExImpl2.refreshOverlayContent(oplusNotificationChildrenContainerOverlayExImpl2.getChildrenContainer().getContainingNotification(), "attach to window");
        OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl3 = this.this$0;
        oplusNotificationChildrenContainerOverlayExImpl3.flushBackground(view, oplusNotificationChildrenContainerOverlayExImpl3.getChildrenContainer().getContainingNotification(), "attach to window");
        OplusNotificationChildrenContainerOverlayExImpl.access$addPreDrawListener(this.this$0, view);
        if (ScrimUtil.isLowGaussianLevel(this.$this_apply.getContext())) {
            ((MaterialColorManager) this.this$0.materialColorManager.get()).addMaterialColorCallback(this.this$0.getMaterialColorChangeCallback());
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String name = DebugToolKt.getName(view, false);
            OplusNotificationChildrenContainerOverlayExImpl.Companion companion = OplusNotificationChildrenContainerOverlayExImpl.Companion;
            ExpandableNotificationRow containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onViewDetachedFromWindow view=", name, ",key=", (containingNotification == null || (entry = containingNotification.getEntry()) == null || (sbn = entry.getSbn()) == null) ? null : sbn.getKey(), "OplusNCCOverlayEx-->"));
        }
        OplusNotificationChildrenContainerOverlayExImpl.access$releaseRes(this.this$0);
    }
}
