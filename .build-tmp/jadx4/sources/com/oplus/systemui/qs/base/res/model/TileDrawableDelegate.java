package com.oplus.systemui.qs.base.res.model;

import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.PathCornerDrawable;
import com.oplusos.systemui.common.outline.RectOutlineProvider;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileDrawableDelegate implements PathCornerDrawable {
    private CornerOutlineProvider blockPathProvider;
    private Float deformFraction;
    private final PathCornerDrawable drawable;
    private CornerOutlineProvider pathProvider = RectOutlineProvider.INSTANCE;
    public boolean animationEnabled = true;

    public TileDrawableDelegate(PathCornerDrawable pathCornerDrawable) {
        this.drawable = pathCornerDrawable;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public float getCornerRadius() {
        return this.drawable.getCornerRadius();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public Float getCornerWeight() {
        return this.drawable.getCornerWeight();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public CornerOutlineProvider getPathProvider() {
        CornerOutlineProvider cornerOutlineProvider = this.blockPathProvider;
        return cornerOutlineProvider == null ? this.pathProvider : cornerOutlineProvider;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public final void invalidatePath() {
        this.drawable.invalidatePath();
    }

    public final boolean isDeforming() {
        return this.deformFraction != null;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setAnimationEnable(boolean z) {
        this.animationEnabled = z;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setBlockPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        if (Intrinsics.areEqual(this.blockPathProvider, cornerOutlineProvider)) {
            return;
        }
        this.blockPathProvider = cornerOutlineProvider;
        invalidatePath();
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setDeformFraction(Float f) {
        this.deformFraction = f;
    }

    @Override // com.oplusos.systemui.common.outline.PathCornerDrawable
    public void setPathProvider(CornerOutlineProvider cornerOutlineProvider) {
        if (Intrinsics.areEqual(this.pathProvider, cornerOutlineProvider)) {
            return;
        }
        this.pathProvider = cornerOutlineProvider;
        invalidatePath();
    }
}
