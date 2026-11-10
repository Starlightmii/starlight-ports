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
import com.oplusos.systemui.common.util.TextShadowUtils;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExpandGroupHeaderReverseWrapper implements MaterialViewWrapper {
    private final Lazy materialColorEx;

    public ExpandGroupHeaderReverseWrapper(Lazy lazy) {
        this.materialColorEx = lazy;
    }

    public static void applyReverseColor(ViewGroup viewGroup) {
        NotificationMaterialColorConfig notificationMaterialColorConfig = NotificationMaterialColorConfig.INSTANCE;
        int expandHeaderTextReverseColor = notificationMaterialColorConfig.getExpandHeaderTextReverseColor();
        int expandHeaderArrowReverseColor = notificationMaterialColorConfig.getExpandHeaderArrowReverseColor();
        View viewFindViewById = viewGroup.findViewById(2131364386);
        TextView textView = viewFindViewById instanceof TextView ? (TextView) viewFindViewById : null;
        if (textView != null) {
            textView.setTextColor(expandHeaderTextReverseColor);
            TextShadowUtils.setInverseColorTextShadow(textView, true, 18.0f);
        }
        View viewFindViewById2 = viewGroup.findViewById(2131364385);
        ImageView imageView = viewFindViewById2 instanceof ImageView ? (ImageView) viewFindViewById2 : null;
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(expandHeaderArrowReverseColor));
        }
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public boolean accept(View view) {
        return (view instanceof ViewGroup) && ((ViewGroup) view).getId() == 2131364384 && ((Boolean) ((NotificationMaterialColorEx) this.materialColorEx.get()).isKeyguardReverseColor().getValue()).booleanValue();
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyMaterial(Context context, ViewGroup viewGroup, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        applyReverseColor(viewGroup);
    }

    @Override // com.oplus.systemui.notification.row.material.wrapper.MaterialViewWrapper
    public void applyNormal(Context context, ViewGroup viewGroup, ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        applyReverseColor(viewGroup);
    }
}
