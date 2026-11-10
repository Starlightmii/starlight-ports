package com.oplus.systemui.navigationbar.positionview;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;
import com.android.settingslib.Utils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PositionSwitchView extends ImageView {
    public final int mDarkColor;
    public final int mLightColor;

    public PositionSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int themeAttr = Utils.getThemeAttr(2130969915, context);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(2130970672, context));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, themeAttr);
        this.mLightColor = Utils.getColorAttrDefaultColor(2130971303, 0, contextThemeWrapper);
        this.mDarkColor = Utils.getColorAttrDefaultColor(2130971303, 0, contextThemeWrapper2);
        Drawable drawable = context.getDrawable(2131235679);
        if (drawable != null) {
            drawable.setAutoMirrored(true);
        }
        setImageDrawable(drawable);
    }

    public final void updateDarkness(float f) {
        setColorFilter(new PorterDuffColorFilter(((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mLightColor), Integer.valueOf(this.mDarkColor))).intValue(), PorterDuff.Mode.SRC_ATOP));
    }
}
