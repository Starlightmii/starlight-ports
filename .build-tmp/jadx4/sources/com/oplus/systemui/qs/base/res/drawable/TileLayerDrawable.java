package com.oplus.systemui.qs.base.res.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.oplus.systemui.qs.base.res.model.TileDrawableDelegate;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.PathCornerDrawable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TileLayerDrawable extends LayerDrawable implements PathCornerDrawable {
    private final TileDrawableDelegate delegate;
    public int userAlpha;

    public TileLayerDrawable(Drawable[] drawableArr) {
        super(drawableArr);
        this.delegate = new TileDrawableDelegate(this);
        this.userAlpha = 255;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.graphics.drawable.LayerDrawable
    public int addLayer(Drawable drawable) {
        int iAddLayer = super.addLayer(drawable);
        PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
        if (pathCornerDrawable != null) {
            pathCornerDrawable.setPathProvider(getPathProvider());
            pathCornerDrawable.setAnimationEnable(this.delegate.animationEnabled);
        }
        if (drawable != 0) {
            drawable.setAlpha(getAlpha());
        }
        return iAddLayer;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.userAlpha;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public float getCornerRadius() {
        return getPathProvider().getCornerRadius(this);
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public Float getCornerWeight() {
        return getPathProvider().getCornerWeight(this);
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public CornerOutlineProvider getPathProvider() {
        return this.delegate.getPathProvider();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public final void invalidatePath() {
        int numberOfLayers = getNumberOfLayers();
        for (int i = 0; i < numberOfLayers; i++) {
            Object drawable = getDrawable(i);
            PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
            if (pathCornerDrawable != null) {
                CornerOutlineProvider pathProvider = getPathProvider();
                if (Intrinsics.areEqual(pathCornerDrawable.getPathProvider(), pathProvider)) {
                    pathCornerDrawable.invalidatePath();
                } else {
                    pathCornerDrawable.setPathProvider(pathProvider);
                }
            }
        }
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.userAlpha = i;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setAnimationEnable(boolean z) {
        this.delegate.setAnimationEnable(z);
        int numberOfLayers = getNumberOfLayers();
        for (int i = 0; i < numberOfLayers; i++) {
            Object drawable = getDrawable(i);
            PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
            if (pathCornerDrawable != null) {
                pathCornerDrawable.setAnimationEnable(z);
            }
        }
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setBlockPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        this.delegate.setBlockPathProvider(cornerOutlineProvider);
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setDeformFraction(Float f) {
        this.delegate.setDeformFraction(f);
        int numberOfLayers = getNumberOfLayers();
        for (int i = 0; i < numberOfLayers; i++) {
            Object drawable = getDrawable(i);
            PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
            if (pathCornerDrawable != null) {
                pathCornerDrawable.setDeformFraction(f);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.graphics.drawable.LayerDrawable
    public void setDrawable(int i, Drawable drawable) {
        super.setDrawable(i, drawable);
        PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
        if (pathCornerDrawable != null) {
            pathCornerDrawable.setPathProvider(getPathProvider());
            pathCornerDrawable.setAnimationEnable(this.delegate.animationEnabled);
        }
        if (drawable != 0) {
            drawable.setAlpha(getAlpha());
        }
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        this.delegate.setPathProvider(cornerOutlineProvider);
    }
}
