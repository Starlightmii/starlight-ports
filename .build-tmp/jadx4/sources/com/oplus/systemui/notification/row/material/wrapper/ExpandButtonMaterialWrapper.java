package com.oplus.systemui.notification.row.material.wrapper;

import android.content.Context;
import android.view.View;
import com.android.internal.widget.NotificationExpandButton;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExpandButtonMaterialWrapper implements MaterialViewWrapper {
    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return view instanceof NotificationExpandButton;
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, NotificationExpandButton notificationExpandButton, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        notificationExpandButton.setDefaultTextColor(NotificationColorPalette.Companion.from(materialColorMode).getSecondaryColor());
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, NotificationExpandButton notificationExpandButton, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        notificationExpandButton.setDefaultTextColor(NotificationColorPalette.Companion.from(materialColorMode).getSecondaryColor());
    }
}
