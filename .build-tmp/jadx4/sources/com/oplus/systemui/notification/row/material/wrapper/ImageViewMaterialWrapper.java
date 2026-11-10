package com.oplus.systemui.notification.row.material.wrapper;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImageViewMaterialWrapper implements MaterialViewWrapper {
    public static final Companion Companion = new Companion();
    private static final Set<Integer> SUPPORTED_IDS = SetsKt__SetsKt.setOf(new Integer[]{Integer.valueOf(R.id.flagNoFullscreen), Integer.valueOf(R.id.action_context_bar), Integer.valueOf(R.id.action_divider), Integer.valueOf(R.id.action_menu_divider), Integer.valueOf(R.id.action_menu_presenter), Integer.valueOf(R.id.action_mode_bar), Integer.valueOf(R.id.clip_children_set_tag), 2131362952});

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return (view instanceof ImageView) && SUPPORTED_IDS.contains(Integer.valueOf(((ImageView) view).getId()));
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, ImageView imageView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        Drawable drawableMutate;
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        int id = imageView.getId();
        if (id == 16909091) {
            imageView.setColorFilter(notificationColorPaletteFrom.getSecondaryColor(), PorterDuff.Mode.SRC_IN);
            return;
        }
        if (id != 2131362952) {
            imageView.setColorFilter(notificationColorPaletteFrom.getPrimaryColor(), PorterDuff.Mode.SRC_ATOP);
            return;
        }
        int secondaryColor = notificationColorPaletteFrom.getSecondaryColor();
        Drawable background = imageView.getBackground();
        if (background == null || (drawableMutate = background.mutate()) == null) {
            return;
        }
        imageView.setBackground(drawableMutate);
        drawableMutate.setColorFilter(secondaryColor, PorterDuff.Mode.SRC_IN);
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, ImageView imageView, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        Drawable drawableMutate;
        NotificationColorPalette notificationColorPaletteFrom = NotificationColorPalette.Companion.from(materialColorMode);
        int id = imageView.getId();
        if (id == 16909091) {
            imageView.setColorFilter(notificationColorPaletteFrom.getSecondaryColor(), PorterDuff.Mode.SRC_IN);
            return;
        }
        if (id != 2131362952) {
            imageView.setColorFilter(notificationColorPaletteFrom.getImageViewFilterColor(), PorterDuff.Mode.SRC_ATOP);
            return;
        }
        int processBackground = notificationColorPaletteFrom.getProcessBackground();
        Drawable background = imageView.getBackground();
        if (background == null || (drawableMutate = background.mutate()) == null) {
            return;
        }
        imageView.setBackground(drawableMutate);
        drawableMutate.setColorFilter(processBackground, PorterDuff.Mode.SRC_IN);
    }
}
