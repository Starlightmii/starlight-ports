package com.oplus.systemui.volume.repository;

import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAppVolumeButtonConfigRepository {
    public static final Companion Companion = new Companion();
    public final int buttonSize;
    private final Context context;
    public final float density;
    public float edgeMargin;
    public final float escapeVelocity;
    public final float flingFriction;
    public final int gestureExclusionPadding;
    public final float springDamping;
    public final float springStiffness;
    public final int touchSlop;
    public final int velocityTimeUnit;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SingleAppVolumeButtonConfigRepository(Context context) {
        this.context = context;
        float f = context.getResources().getDisplayMetrics().density;
        this.density = f;
        this.buttonSize = context.getResources().getDimensionPixelSize(2131173941);
        this.edgeMargin = calculateEdgeMargin();
        this.gestureExclusionPadding = (int) (12.0f * f);
        this.touchSlop = (int) (f * 8.0f);
        this.springStiffness = 700.0f;
        this.springDamping = 0.85f;
        this.flingFriction = 1.9f;
        this.escapeVelocity = 750.0f;
        this.velocityTimeUnit = 1000;
    }

    public final float calculateEdgeMargin() {
        return this.density * (this.context.getResources().getConfiguration().orientation == 2 ? 40.0f : 10.0f);
    }

    public final int getButtonSize() {
        return this.buttonSize;
    }

    public final float getEdgeMargin() {
        return this.edgeMargin;
    }

    public final float getEscapeVelocity() {
        return this.escapeVelocity;
    }

    public final float getFlingFriction() {
        return this.flingFriction;
    }

    public final int getGestureExclusionPadding() {
        return this.gestureExclusionPadding;
    }

    public final float getSpringDamping() {
        return this.springDamping;
    }

    public final float getSpringStiffness() {
        return this.springStiffness;
    }

    public final int getTouchSlop() {
        return this.touchSlop;
    }

    public final int getVelocityTimeUnit() {
        return this.velocityTimeUnit;
    }
}
