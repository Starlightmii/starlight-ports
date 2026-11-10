package com.oplusos.systemui.common.outline;

import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.ViewOutlineProvider;
import com.oplus.graphics.OplusPath;
import com.oplus.posteffect.drawable.BaseDrawable;
import com.oplus.posteffect.path.BlurDrawablePathProvider;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CornerOutlineProvider extends ViewOutlineProvider implements BlurDrawablePathProvider {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static boolean isShapeNearCircle(float f, int i, int i2) {
            if (i2 <= 0 || i <= 0) {
                return false;
            }
            float fMin = Math.min(i2, i);
            return ((float) Math.max(i2, i)) - fMin <= 10.0f && f >= (fMin / 2.0f) - 10.0f;
        }
    }

    public float getCornerRadius(Drawable drawable) {
        return 0.0f;
    }

    public Float getCornerWeight(Drawable drawable) {
        return null;
    }

    public void getPath(BaseDrawable baseDrawable, Path path, OplusPath oplusPath, Object obj, boolean z) {
        updatePath(baseDrawable, path, oplusPath, obj);
    }

    public boolean isSmoothCorner() {
        return false;
    }

    public abstract void updatePath(Drawable drawable, Path path, OplusPath oplusPath, Object obj);

    public void updateStrokeParams(AutoBlurDrawable autoBlurDrawable, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
        float cornerRadius = getCornerRadius(autoBlurDrawable);
        Float cornerWeight = getCornerWeight(autoBlurDrawable);
        int iWidth = autoBlurDrawable.getBounds().width();
        int iHeight = autoBlurDrawable.getBounds().height();
        Companion.getClass();
        GradientStrokeLineAdapter.INSTANCE.adaptGradientStrokeParams(autoBlurDrawable.getViewBlurProxy().getBlurConfig(), cornerRadius, cornerWeight, iWidth, iHeight, Companion.isShapeNearCircle(cornerRadius, iHeight, iWidth), strokeParamsTemplate);
    }
}
