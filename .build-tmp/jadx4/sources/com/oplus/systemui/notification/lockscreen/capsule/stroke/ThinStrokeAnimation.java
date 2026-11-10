package com.oplus.systemui.notification.lockscreen.capsule.stroke;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThinStrokeAnimation implements IStrokeAnimator {
    public static final Companion Companion = new Companion();
    public float progress;
    public float startAngle = 0.25f;
    private final StrokeShader strokeShader;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ThinStrokeAnimation(StrokeShader strokeShader) {
        this.strokeShader = strokeShader;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public float getProgress() {
        return this.progress;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public float getStartAngle() {
        return this.startAngle;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public void setProgress(float f) {
        float startAngle;
        this.progress = f;
        StrokeShader strokeShader = this.strokeShader;
        float interpolation = 1.0f;
        if (f < 0.833f) {
            startAngle = ((1.0f - getStartAngle()) * IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.833f)) + getStartAngle();
        } else {
            startAngle = 1.0f;
        }
        strokeShader.setAngle(startAngle);
        if (f < 0.125f) {
            interpolation = IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.125f);
        } else if (f >= 0.5f) {
            interpolation = 1.0f - IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation((f - 0.5f) / 0.5f);
        }
        this.strokeShader.setNearAlpha(interpolation);
        this.strokeShader.setFarAlpha(interpolation);
        float interpolation2 = 2.0f;
        if (f < 0.125f) {
            interpolation2 = 2.0f + (IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.125f) * 10.0f);
        } else if (f < 0.5f) {
            interpolation2 = 12.0f;
        } else if (f < 0.683f) {
            interpolation2 = 12.0f - (IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation((f - 0.5f) / 0.18300003f) * 10.0f);
        }
        this.strokeShader.setNearStrokeWidth(interpolation2);
        this.strokeShader.setFarStrokeWidth(interpolation2);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public void setStartAngle(float f) {
        this.startAngle = f;
    }
}
