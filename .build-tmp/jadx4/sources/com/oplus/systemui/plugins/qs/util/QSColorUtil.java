package com.oplus.systemui.plugins.qs.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundKt;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSColorUtil {
    public static final QSColorUtil INSTANCE = new QSColorUtil();

    public static final void adaptiveColor(ImageView imageView, Drawable drawable) {
        if (QsColorUtil.isTextNeedUseLightColorWhenLight(imageView.getContext(), QsColorUtil.getQsColorState()) && drawable != null) {
            drawable.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        }
        imageView.setImageDrawable(drawable);
    }

    public static final boolean isColorfulTileMixColor(Context context) {
        return QsViewBackgroundKt.isMixColorSupport(context, isGaussBlurDisabled()) && ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isTileColorfulEnable().getValue()).booleanValue();
    }

    public static final boolean isGaussBlurDisabled() {
        NotificationFeatureOption.INSTANCE.getClass();
        return NotificationFeatureOption.isGaussBlurDisabled || ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue();
    }

    public static final void adaptiveColor(TextView textView, int i) {
        if (QsColorUtil.isTextNeedUseLightColorWhenLight(textView.getContext(), QsColorUtil.getQsColorState())) {
            i = -1;
        }
        QsColorUtil.compareAndUpdateTextColor(textView, i);
    }
}
