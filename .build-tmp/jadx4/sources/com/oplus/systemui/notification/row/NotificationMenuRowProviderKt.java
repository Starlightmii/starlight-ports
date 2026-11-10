package com.oplus.systemui.notification.row;

import android.graphics.drawable.Drawable;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplusos.systemui.common.blurability.drawable.MaskBlurDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationMenuRowProviderKt {
    public static final void setBlendRange(MetaBallBlurDrawable metaBallBlurDrawable, int i, String str) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, LogUtil.TAG_COMMON, (metaBallBlurDrawable != null ? Integer.valueOf(metaBallBlurDrawable.hashCode()) : null) + " - setBlendRange(" + i + "); reason=" + str, null, 8);
        if (metaBallBlurDrawable != null) {
            metaBallBlurDrawable.setBlendRange(i);
        }
    }

    public static final MetaBallBlurDrawable tryGetMetaBallBlurDrawable(Drawable drawable) {
        Drawable blurDrawable;
        if (drawable instanceof MetaBallBlurDrawable) {
            return (MetaBallBlurDrawable) drawable;
        }
        if (drawable instanceof PlatformBlurDrawable) {
            MetaBallBlurDrawable blurDrawable2 = ((PlatformBlurDrawable) drawable).getBlurDrawable();
            if (blurDrawable2 instanceof MetaBallBlurDrawable) {
                return blurDrawable2;
            }
        } else if ((drawable instanceof MaskBlurDrawable) && (blurDrawable = ((MaskBlurDrawable) drawable).getViewBlurProxy().getBlurDrawable(null)) != null) {
            return tryGetMetaBallBlurDrawable(blurDrawable);
        }
        return null;
    }
}
