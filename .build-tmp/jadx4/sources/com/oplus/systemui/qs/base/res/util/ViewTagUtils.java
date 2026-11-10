package com.oplus.systemui.qs.base.res.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.base.res.drawable.MixColorTileDrawable;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewTagUtils {
    public static final ViewTagUtils INSTANCE = new ViewTagUtils();

    public static final GradientTileDrawable getGradientDrawableTag(View view) {
        Drawable gradientDrawableTag = QSBlurConfigProvider.getGradientDrawableTag(view);
        if (gradientDrawableTag instanceof GradientTileDrawable) {
            return (GradientTileDrawable) gradientDrawableTag;
        }
        return null;
    }

    public static final MixColorTileDrawable getMixColorDrawableTag(View view) {
        Drawable blurDrawableTag = QSBlurConfigProvider.getBlurDrawableTag(view);
        if (blurDrawableTag instanceof MixColorTileDrawable) {
            return (MixColorTileDrawable) blurDrawableTag;
        }
        return null;
    }

    public static final QSTile.Icon getQSIconTag(View view) {
        Object tag = view.getTag(2131364775);
        if (tag instanceof QSTile.Icon) {
            return (QSTile.Icon) tag;
        }
        return null;
    }

    public static final boolean getSepQSViewTag(View view) {
        Object tag = view.getTag(2131365588);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final void setGradientDrawableTag(View view, GradientTileDrawable gradientTileDrawable) {
        QSBlurConfigProvider.setGradientDrawableTag(view, gradientTileDrawable);
    }

    public static final void setMixColorDrawableTag(View view, MixColorTileDrawable mixColorTileDrawable) {
        QSBlurConfigProvider.setBlurDrawableTag(view, mixColorTileDrawable);
    }

    public static final void setQSIconTag(View view, QSTile.Icon icon) {
        view.setTag(2131364775, icon);
    }

    public static final void setSepQSViewTag(View view, boolean z) {
        view.setTag(2131365588, Boolean.valueOf(z));
    }
}
