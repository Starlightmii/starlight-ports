package com.oplus.systemui.notification.row.material.wrapper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ProgressBar;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.qs.helper.QSFragmentHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ProgressBarMaterialWrapper implements MaterialViewWrapper {
    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return view instanceof ProgressBar;
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, ProgressBar progressBar, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        progressBar.setProgressBackgroundTintMode(PorterDuff.Mode.SRC);
        progressBar.setProgressTintList(ColorStateList.valueOf(notificationColorPaletteFrom.getProcessForeground()));
        progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(notificationColorPaletteFrom.getProcessBackground()));
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, ProgressBar progressBar, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(QSFragmentHelper.getInstance().getActiveColorWithDarkMode(context));
        ColorStateList colorStateListValueOf2 = ColorStateList.valueOf(notificationColorPaletteFrom.getProcessBackground());
        progressBar.setProgressBackgroundTintMode(PorterDuff.Mode.SRC);
        progressBar.setProgressBackgroundTintList(colorStateListValueOf2);
        progressBar.setProgressTintList(colorStateListValueOf);
    }
}
