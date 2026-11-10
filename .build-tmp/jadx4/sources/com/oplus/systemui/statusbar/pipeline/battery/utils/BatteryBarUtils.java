package com.oplus.systemui.statusbar.pipeline.battery.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryBarUtils {
    public static final BatteryBarUtils INSTANCE = new BatteryBarUtils();
    private static final String TAG = "BatteryBarUtils";

    public static final Pair<Float, Float> getDrawableOriginSize(Context context, int i) {
        Float fValueOf = Float.valueOf(0.0f);
        try {
            Drawable drawable = context.getResources().getDrawable(i);
            return drawable != null ? new Pair<>(Float.valueOf(drawable.getIntrinsicWidth()), Float.valueOf(drawable.getIntrinsicHeight())) : new Pair<>(fValueOf, fValueOf);
        } catch (Resources.NotFoundException e) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "BatteryBarUtils-->".concat("getDrawableOriginSize failed! " + e));
            }
            return new Pair<>(fValueOf, fValueOf);
        }
    }

    public static final Pair<Float, Float> getThirdThemePicPadding(Context context, int i) {
        Pair<Float, Float> drawableOriginSize = getDrawableOriginSize(context, i);
        float dimension = context.getResources().getDimension(2131172998);
        float dimension2 = context.getResources().getDimension(2131172989);
        float fMax = Math.max(dimension - ((Number) drawableOriginSize.getFirst()).floatValue(), 0.0f);
        float fMax2 = Math.max(dimension2 - ((Number) drawableOriginSize.getSecond()).floatValue(), 0.0f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Object first = drawableOriginSize.getFirst();
            Object second = drawableOriginSize.getSecond();
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("batteryWidth: ", dimension, ", batteryHeight: ", dimension2, " ,DrawableOriginSize width: ");
            sbM.append(first);
            sbM.append(" ,height: ");
            sbM.append(second);
            sbM.append(",widthPadding: ");
            Log.d("SystemUi--Statusbar", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, fMax, ", heightPadding:", fMax2, "BatteryBarUtils-->"));
        }
        return new Pair<>(Float.valueOf(fMax / 2.0f), Float.valueOf(fMax2 / 2.0f));
    }

    public static final boolean hasThirdPictureO14(Context context) {
        return ((context.getResources().getDrawable(2131236710) instanceof VectorDrawable) || (context.getResources().getDrawable(2131236688) instanceof VectorDrawable)) ? false : true;
    }

    public static final boolean percentageInHasThirdPicture(Context context) {
        return ((context.getResources().getDrawable(2131236693) instanceof VectorDrawable) && (context.getResources().getDrawable(2131236709) instanceof VectorDrawable) && (context.getResources().getDrawable(2131236692) instanceof VectorDrawable)) ? false : true;
    }

    public static final boolean percentageOutHasThirdPicture(Context context) {
        return ((context.getResources().getDrawable(2131236703) instanceof VectorDrawable) || (context.getResources().getDrawable(2131236704) instanceof VectorDrawable)) ? false : true;
    }

    public static final boolean useHorizontalStyleOf14(Context context, boolean z) {
        return z && context.getResources().getInteger(2131427778) > 0 && hasThirdPictureO14(context);
    }

    public static final boolean useHorizontalStyleOf15(Context context, boolean z, boolean z2, int i) {
        if (z2 && i >= 15) {
            return true;
        }
        if (z && percentageInHasThirdPicture(context)) {
            return true;
        }
        return !z && percentageOutHasThirdPicture(context);
    }
}
