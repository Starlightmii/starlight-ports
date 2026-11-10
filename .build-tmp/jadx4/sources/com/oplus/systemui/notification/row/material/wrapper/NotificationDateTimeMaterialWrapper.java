package com.oplus.systemui.notification.row.material.wrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;
import android.widget.OplusNotificationDateTimeView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDateTimeMaterialWrapper implements MaterialViewWrapper {
    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return view instanceof OplusNotificationDateTimeView;
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, OplusNotificationDateTimeView oplusNotificationDateTimeView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        Drawable drawable;
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        oplusNotificationDateTimeView.setTextColor(notificationColorPaletteFrom.getSecondaryColor());
        Drawable[] compoundDrawables = oplusNotificationDateTimeView.getCompoundDrawables();
        int length = compoundDrawables.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                drawable = null;
                break;
            }
            drawable = compoundDrawables[i];
            if (drawable != null) {
                break;
            } else {
                i++;
            }
        }
        VectorDrawable vectorDrawable = drawable instanceof VectorDrawable ? (VectorDrawable) drawable : null;
        if (vectorDrawable != null) {
            vectorDrawable.setColorFilter(notificationColorPaletteFrom.getExpandAreaFilterColor());
        }
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, OplusNotificationDateTimeView oplusNotificationDateTimeView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        Drawable drawable;
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        oplusNotificationDateTimeView.setTextColor(notificationColorPaletteFrom.getSecondaryColor());
        Drawable[] compoundDrawables = oplusNotificationDateTimeView.getCompoundDrawables();
        int length = compoundDrawables.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                drawable = null;
                break;
            }
            drawable = compoundDrawables[i];
            if (drawable != null) {
                break;
            } else {
                i++;
            }
        }
        VectorDrawable vectorDrawable = drawable instanceof VectorDrawable ? (VectorDrawable) drawable : null;
        if (vectorDrawable != null) {
            vectorDrawable.setColorFilter(notificationColorPaletteFrom.getExpandAreaFilterColor());
        }
    }
}
