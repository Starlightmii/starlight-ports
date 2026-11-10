package com.oplus.systemui.notification.lockscreen.capsule.stroke;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThickStrokeAnimation implements IStrokeAnimator {
    public static final Companion Companion = new Companion();
    public float progress;
    public float startAngle = 0.25f;
    private final StrokeShader strokeShader;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ThickStrokeAnimation(StrokeShader strokeShader) {
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

    /* JADX WARN: Code duplicated, block: B:18:0x0068  */
    /* JADX WARN: Code duplicated, block: B:19:0x0076  */
    /* JADX WARN: Code duplicated, block: B:21:0x007a  */
    /* JADX WARN: Code duplicated, block: B:22:0x007c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x007e  */
    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public void setProgress(float f) {
        float startAngle;
        float interpolation;
        this.progress = f;
        StrokeShader strokeShader = this.strokeShader;
        if (f < 0.833f) {
            startAngle = ((1.0f - getStartAngle()) * IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.833f)) + getStartAngle();
        } else {
            startAngle = 1.0f;
        }
        strokeShader.setAngle(startAngle);
        StrokeShader strokeShader2 = this.strokeShader;
        float interpolation2 = 0.0f;
        float f2 = 0.4f;
        if (f >= 0.125f) {
            if (f >= 0.5f) {
                if (f < 0.833f) {
                    interpolation = 1.0f - IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation((f - 0.5f) / 0.333f);
                } else {
                    f2 = 0.0f;
                }
            }
            strokeShader2.setNearAlpha(f2);
            StrokeShader strokeShader3 = this.strokeShader;
            if (f < 0.125f) {
                interpolation2 = IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.125f) * 0.5f;
            } else if (f < 0.5f) {
                interpolation2 = 0.5f;
            } else if (f < 0.833f) {
                interpolation2 = (1.0f - IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation((f - 0.5f) / 0.333f)) * 0.5f;
            }
            strokeShader3.setFarAlpha(interpolation2);
        }
        interpolation = IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.125f);
        f2 = 0.4f * interpolation;
        strokeShader2.setNearAlpha(f2);
        StrokeShader strokeShader4 = this.strokeShader;
        if (f < 0.125f) {
            interpolation2 = IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation(f / 0.125f) * 0.5f;
        } else if (f < 0.5f) {
            interpolation2 = 0.5f;
        } else if (f < 0.833f) {
            interpolation2 = (1.0f - IStrokeAnimator.Companion.getBEZIER_INTERPOLATOR().getInterpolation((f - 0.5f) / 0.333f)) * 0.5f;
        }
        strokeShader4.setFarAlpha(interpolation2);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.stroke.IStrokeAnimator
    public void setStartAngle(float f) {
        this.startAngle = f;
    }
}
