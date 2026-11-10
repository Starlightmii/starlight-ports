package com.oplus.systemui.notification.row;

import android.content.Context;
import android.content.res.Resources;
import android.util.Size;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemViewParam implements MenuViewInfoProvider {
    private final Context context;
    private final Function0 getParentHeight;
    private Size sIconSize = new Size(0, 0);

    public NotificationMenuRowItemViewParam(Context context, Function0 function0) {
        this.context = context;
        this.getParentHeight = function0;
    }

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public int getIconMargin() {
        return this.context.getResources().getDimensionPixelSize(2131170720);
    }

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public int getIconMarginExtra() {
        return this.context.getResources().getDimensionPixelSize(2131170721);
    }

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public Size getIconSize(boolean z) {
        Resources resources = this.context.getResources();
        if (this.sIconSize.getWidth() == 0 || this.sIconSize.getHeight() == 0 || z) {
            int dimensionPixelSize = resources.getDimensionPixelSize(2131170724);
            Integer num = (Integer) this.getParentHeight.invoke();
            int iIntValue = num != null ? num.intValue() : dimensionPixelSize;
            Size size = new Size(resources.getDimensionPixelSize(2131170727), iIntValue > dimensionPixelSize ? resources.getDimensionPixelSize(2131170725) : iIntValue - (resources.getDimensionPixelSize(2131170726) * 2));
            this.sIconSize = size;
            NotificationMenuRowLog.i("recalculate sIconSize = " + size);
        }
        return this.sIconSize;
    }
}
