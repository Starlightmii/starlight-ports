package com.oplus.systemui.notification.row;

import android.view.View;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExpandableNotificationRowMenuRowProvider extends NotificationMenuRowProvider {
    private final ExpandableNotificationRow notificationRow;

    public ExpandableNotificationRowMenuRowProvider(ExpandableNotificationRow expandableNotificationRow) {
        this.notificationRow = expandableNotificationRow;
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public final int provideCardActualHeight() {
        return ((ActivatableNotificationView) this.notificationRow).mBackgroundNormal.getActualHeight() - this.notificationRow.getClipBottomAmount();
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public MetaBallBlurDrawable provideCardMetaBallDrawable() {
        ViewBlurProxy viewBlurProxy = ((ActivatableNotificationView) this.notificationRow).mBackgroundNormal.getExt().getViewBlurProxy();
        return NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(viewBlurProxy != null ? viewBlurProxy.getBlurDrawable(null) : null);
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public View provideCardRowAsView() {
        return this.notificationRow;
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public final float provideCardTranslationX() {
        return this.notificationRow.getTranslation();
    }

    @Override // com.oplus.systemui.notification.row.NotificationMenuRowProvider
    public final int provideCardWidth() {
        return this.notificationRow.getWidth();
    }
}
