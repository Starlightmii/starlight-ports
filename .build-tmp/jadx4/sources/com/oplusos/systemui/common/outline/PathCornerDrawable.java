package com.oplusos.systemui.common.outline;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface PathCornerDrawable {
    float getCornerRadius();

    Float getCornerWeight();

    CornerOutlineProvider getPathProvider();

    void invalidatePath();

    void setAnimationEnable(boolean z);

    void setBlockPathProvider(CornerOutlineProvider cornerOutlineProvider);

    default void setDeformFraction(Float f) {
    }

    void setPathProvider(CornerOutlineProvider cornerOutlineProvider);
}
