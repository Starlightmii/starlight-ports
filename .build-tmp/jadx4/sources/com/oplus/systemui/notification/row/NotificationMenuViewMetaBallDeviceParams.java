package com.oplus.systemui.notification.row;

import android.content.Context;
import androidx.appcompat.widget.ListPopupWindow$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuViewMetaBallDeviceParams {
    private final Context context;
    public boolean isRtl;

    public NotificationMenuViewMetaBallDeviceParams(Context context) {
        this.context = context;
        this.isRtl = ListPopupWindow$.ExternalSyntheticOutline0.m(context) == 1;
    }

    public static boolean isDragBack(float f, boolean z) {
        if (f > 0.0f || !z) {
            return f >= 0.0f && !z;
        }
        return true;
    }

    public final void onConfigChange() {
        this.isRtl = ListPopupWindow$.ExternalSyntheticOutline0.m(this.context) == 1;
    }

    public final float resolveDefaultTranslationX(int i, int i2, int i3, int i4, int i5) {
        return this.isRtl ? (((i3 * i2) + i4) + i5) - i : ((i - (i3 * i2)) - i4) - i5;
    }

    public final NotificationMenuViewHorizontalState resolveWidthState(int i, int i2, float f, int i3) {
        float f2 = i - i3;
        if (f >= f2) {
            f = f2;
        }
        boolean z = this.isRtl;
        float f3 = z ? (i3 + f) - i : (i - f) - i3;
        int i4 = z ? i2 - ((int) f) : 0;
        if (!z) {
            i2 = (int) f;
        }
        return new NotificationMenuViewHorizontalState(f3, i4, i2);
    }
}
