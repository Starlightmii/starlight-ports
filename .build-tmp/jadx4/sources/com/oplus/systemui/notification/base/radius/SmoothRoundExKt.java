package com.oplus.systemui.notification.base.radius;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.res.ResourcesCompat;
import com.oplus.graphics.IOplusPath;
import com.oplus.posteffect.params.CornerParams;
import com.oplus.posteffect.params.CornerType;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SmoothRoundExKt {
    private static final String TAG = "SmoothRoundEx(3):";

    public static final void addSmoothRoundRect(Path path, RectF rectF, float f, float f2, float f3, Path.Direction direction, boolean z) {
        (z ? new NotificationPath(path) : new OrginPath(path)).addSmoothRoundRect(rectF, f, f2, f3, direction);
    }

    public static /* synthetic */ void addSmoothRoundRect$default(Path path, RectF rectF, float f, float f2, boolean z, int i) {
        float defaultWeight = SmoothRoundEx.INSTANCE.getDefaultWeight();
        Path.Direction direction = Path.Direction.CCW;
        if ((i & 32) != 0) {
            z = true;
        }
        addSmoothRoundRect(path, rectF, f, f2, defaultWeight, direction, z);
    }

    public static final Drawable getSmoothDrawable(Context context, int i) {
        Drawable drawableMutate;
        Drawable drawable = context.getDrawable(2131235736);
        if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
            return null;
        }
        if (drawableMutate instanceof GradientDrawable) {
            ((GradientDrawable) drawableMutate).setColor(ResourcesCompat.getColorStateList(context.getTheme(), context.getResources(), i));
        }
        return drawableMutate;
    }

    public static final Drawable getSmoothLayerDrawable(Context context, int i) {
        Drawable smoothDrawable = getSmoothDrawable(context, i);
        if (smoothDrawable == null) {
            smoothDrawable = new ColorDrawable(0);
        }
        return new LayerDrawable(new Drawable[]{smoothDrawable, smoothDrawable});
    }

    public static final boolean isCapsuleSmooth(CornerParams cornerParams) {
        return cornerParams.getType() == CornerType.G2 && cornerParams.getRadius() != 0.0f && cornerParams.getWeight() == 2.0f;
    }

    public static final void addSmoothRoundRect(IOplusPath iOplusPath, RectF rectF, float f, float f2) {
        iOplusPath.addSmoothRoundRect(rectF, f, f2, SmoothRoundEx.INSTANCE.getDefaultWeight(), Path.Direction.CCW);
    }

    public static final void addSmoothRoundRect(IOplusPath iOplusPath, RectF rectF, float[] fArr, float f, Path.Direction direction) {
        iOplusPath.addSmoothRoundRect(rectF, fArr, direction, f);
    }
}
