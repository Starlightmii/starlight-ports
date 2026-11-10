package com.oplus.systemui.qs.base.res.drawable;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.systemui.qs.base.res.model.TileDrawableDelegate;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.PathCornerDrawable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TileDrawableWrapper extends DrawableWrapper implements PathCornerDrawable {
    private final TileDrawableDelegate delegate;
    private OplusPathAdapter oplusPathAdapter;
    private final Path path;
    public int userAlpha;

    public TileDrawableWrapper(Drawable drawable) {
        super(drawable);
        this.delegate = new TileDrawableDelegate(this);
        this.userAlpha = 255;
        this.path = new Path();
    }

    public final OplusPathAdapter ensureOplusPathAdapter() {
        if (getPathProvider().isSmoothCorner() && this.oplusPathAdapter == null) {
            this.oplusPathAdapter = new OplusPathAdapter(this.path, 1);
        }
        return this.oplusPathAdapter;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.userAlpha;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return 0;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public float getCornerRadius() {
        return getPathProvider().getCornerRadius(this);
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public Float getCornerWeight() {
        return getPathProvider().getCornerWeight(this);
    }

    public final Path getPath() {
        return this.path;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public CornerOutlineProvider getPathProvider() {
        return this.delegate.getPathProvider();
    }

    public void invalidatePath() {
        Object drawable = getDrawable();
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

    public final boolean isAnimationEnable() {
        return this.delegate.animationEnabled && QSConstant.getQsTileAnimationAllowed();
    }

    public final boolean isDeforming() {
        return this.delegate.isDeforming();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (getDrawable() instanceof PathCornerDrawable) {
            return;
        }
        invalidatePath();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.userAlpha = i;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setAnimationEnable(boolean z) {
        this.delegate.setAnimationEnable(z);
        Object drawable = getDrawable();
        PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
        if (pathCornerDrawable != null) {
            pathCornerDrawable.setAnimationEnable(z);
        }
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setBlockPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        this.delegate.setBlockPathProvider(cornerOutlineProvider);
    }

    public void setDeformFraction(Float f) {
        this.delegate.setDeformFraction(f);
        Object drawable = getDrawable();
        PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
        if (pathCornerDrawable != null) {
            pathCornerDrawable.setDeformFraction(f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.graphics.drawable.DrawableWrapper
    public void setDrawable(Drawable drawable) {
        super.setDrawable(drawable);
        PathCornerDrawable pathCornerDrawable = drawable instanceof PathCornerDrawable ? (PathCornerDrawable) drawable : null;
        if (pathCornerDrawable != null) {
            pathCornerDrawable.setPathProvider(getPathProvider());
            pathCornerDrawable.setAnimationEnable(isAnimationEnable());
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
