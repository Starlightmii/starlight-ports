package com.oplus.systemui.notification.row.material.wrapper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorConfig;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplusos.systemui.common.util.TextShadowUtils;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExpandGroupHeaderMaterialWrapper implements MaterialViewWrapper {
    private final Lazy materialColorEx;

    public ExpandGroupHeaderMaterialWrapper(Lazy lazy) {
        this.materialColorEx = lazy;
    }

    public static void applyColor(Context context, ViewGroup viewGroup, boolean z) {
        int color = (!z && NotificationColorUtil.isGlobalThemeApplied(context)) ? context.getColor(2131103705) : NotificationMaterialColorConfig.INSTANCE.getExpandHeaderTextColor();
        int color2 = (!z && NotificationColorUtil.isGlobalThemeApplied(context)) ? context.getColor(2131103705) : NotificationMaterialColorConfig.INSTANCE.getExpandHeaderArrowColor();
        View viewFindViewById = viewGroup.findViewById(2131364386);
        TextView textView = viewFindViewById instanceof TextView ? (TextView) viewFindViewById : null;
        if (textView != null) {
            textView.setTextColor(color);
            TextShadowUtils.setInverseColorTextShadow(textView, false, 18.0f);
        }
        View viewFindViewById2 = viewGroup.findViewById(2131364385);
        ImageView imageView = viewFindViewById2 instanceof ImageView ? (ImageView) viewFindViewById2 : null;
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(color2));
        }
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return (view instanceof ViewGroup) && ((ViewGroup) view).getId() == 2131364384 && !((Boolean) ((NotificationMaterialColorEx) this.materialColorEx.get()).isKeyguardReverseColor().getValue()).booleanValue();
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, ViewGroup viewGroup, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        applyColor(context, viewGroup, true);
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, ViewGroup viewGroup, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        applyColor(context, viewGroup, false);
    }
}
