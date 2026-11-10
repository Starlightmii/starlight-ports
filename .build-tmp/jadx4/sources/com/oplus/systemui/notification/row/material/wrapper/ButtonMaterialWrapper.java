package com.oplus.systemui.notification.row.material.wrapper;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import com.android.internal.widget.EmphasizedNotificationButton;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ButtonMaterialWrapper implements MaterialViewWrapper {
    public static final Companion Companion = new Companion();
    private static final Set<Integer> SUPPORTED_IDS = SetsKt__SetsJVMKt.setOf(Integer.valueOf(R.id.action_context_bar));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return (view instanceof Button) && !(view instanceof EmphasizedNotificationButton) && SUPPORTED_IDS.contains(Integer.valueOf(((Button) view).getId()));
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, Button button, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        button.setTextColor(notificationColorPaletteFrom.getPrimaryLabelColor());
        Drawable background = button.getBackground();
        Drawable drawableMutate = background != null ? background.mutate() : null;
        GradientDrawable gradientDrawable = drawableMutate instanceof GradientDrawable ? (GradientDrawable) drawableMutate : null;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(notificationColorPaletteFrom.getButtonBackgroundColor());
        }
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, Button button, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        button.setTextColor(notificationColorPaletteFrom.getPrimaryColor());
        Drawable background = button.getBackground();
        Drawable drawableMutate = background != null ? background.mutate() : null;
        GradientDrawable gradientDrawable = drawableMutate instanceof GradientDrawable ? (GradientDrawable) drawableMutate : null;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(notificationColorPaletteFrom.getButtonBackgroundColor());
        }
    }
}
