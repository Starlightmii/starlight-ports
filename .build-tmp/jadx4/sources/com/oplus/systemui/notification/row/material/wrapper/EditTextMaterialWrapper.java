package com.oplus.systemui.notification.row.material.wrapper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.EditText;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditTextMaterialWrapper implements MaterialViewWrapper {
    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return view instanceof EditText;
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, EditText editText, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        editText.setTextColor(notificationColorPaletteFrom.getPrimaryLabelColor());
        editText.setHintTextColor(notificationColorPaletteFrom.getSecondaryColorLabel());
        editText.setBackgroundTintList(ColorStateList.valueOf(notificationColorPaletteFrom.getButtonBackgroundColor()));
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, EditText editText, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        editText.setTextColor(notificationColorPaletteFrom.getPrimaryColor());
        editText.setHintTextColor(notificationColorPaletteFrom.getSecondaryColor());
        editText.setBackgroundTintList(ColorStateList.valueOf(notificationColorPaletteFrom.getButtonBackgroundColor()));
    }
}
