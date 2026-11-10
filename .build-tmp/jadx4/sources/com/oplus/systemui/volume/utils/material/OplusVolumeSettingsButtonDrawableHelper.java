package com.oplus.systemui.volume.utils.material;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import com.oplusos.systemui.common.util.GlobalActionsUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSettingsButtonDrawableHelper {
    public static final OplusVolumeSettingsButtonDrawableHelper INSTANCE = new OplusVolumeSettingsButtonDrawableHelper();

    public static final float cornerRadiusPx(Context context) {
        return Math.min(context.getResources().getDimension(2131173935), context.getResources().getDimension(2131173934)) / 2.0f;
    }

    public static final Drawable create(Context context, int i) {
        Drawable drawableMutate;
        Drawable drawable = context.getDrawable(2131237446);
        if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
            return null;
        }
        drawableMutate.setTint(i);
        int dimensionPixelSize = (context.getResources().getDimensionPixelSize(2131173935) - GlobalActionsUtils.dp2px(context, 24.0f)) / 2;
        int i2 = dimensionPixelSize < 0 ? 0 : dimensionPixelSize;
        if (VolumeBlurManager.isVolumePlatformBlurEnabled(context)) {
            return new InsetDrawable(drawableMutate, i2);
        }
        float fMin = Math.min(context.getResources().getDimension(2131173935), context.getResources().getDimension(2131173934)) / 2.0f;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(fMin);
        INSTANCE.getClass();
        gradientDrawable.setColor(Color.argb(51, Color.red(i), Color.green(i), Color.blue(i)));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawableMutate});
        layerDrawable.setLayerInset(1, i2, i2, i2, i2);
        return layerDrawable;
    }
}
