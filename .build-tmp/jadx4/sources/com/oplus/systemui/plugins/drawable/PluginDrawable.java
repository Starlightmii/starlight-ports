package com.oplus.systemui.plugins.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PluginDrawable extends DrawableWrapper {
    public boolean isMaterialBlurEnable;
    public boolean isSupportStroke;
    private RoundRectOutlineProvider pathProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Builder {
        PluginDrawable build(View view, boolean z);
    }

    public PluginDrawable(Drawable drawable) {
        super(drawable);
        this.pathProvider = new RoundRectOutlineProvider(0.0f, (Float) null);
    }

    public final float getMirrorScaleValue() {
        return this.isMaterialBlurEnable ? 0.94f : 1.0f;
    }

    public ViewOutlineProvider getPathProvider() {
        return this.pathProvider;
    }

    public void invalidatePath() {
    }

    public void setCornerRadius(float f, Float f2) {
        if (f == this.pathProvider.getCornerRadius(this) && Intrinsics.areEqual(f2, this.pathProvider.getCornerWeight(this))) {
            return;
        }
        this.pathProvider.update(f, f2);
        invalidatePath();
    }

    public void setMaterialBlurEnable(boolean z) {
        if (this.isMaterialBlurEnable == z) {
            return;
        }
        this.isMaterialBlurEnable = z;
        invalidatePath();
    }

    public void setSupportStroke(boolean z) {
        if (this.isSupportStroke == z) {
            return;
        }
        this.isSupportStroke = z;
        invalidatePath();
    }
}
