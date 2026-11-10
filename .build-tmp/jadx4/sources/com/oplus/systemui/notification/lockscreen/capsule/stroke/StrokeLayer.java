package com.oplus.systemui.notification.lockscreen.capsule.stroke;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StrokeLayer {
    public static final Companion Companion = new Companion();
    private static final Function1 THICK_ANIMATOR_FACTORY;
    private static final Function1 THIN_ANIMATOR_FACTORY;
    private final Paint paint;
    private IStrokeAnimator strokeAnimator;
    private final StrokeShader strokeShader;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Function1 getTHICK_ANIMATOR_FACTORY() {
            return StrokeLayer.THICK_ANIMATOR_FACTORY;
        }

        public final Function1 getTHIN_ANIMATOR_FACTORY() {
            return StrokeLayer.THIN_ANIMATOR_FACTORY;
        }
    }

    static {
        final int i = 0;
        THIN_ANIMATOR_FACTORY = new Function1() { // from class: com.oplus.systemui.notification.lockscreen.capsule.stroke.StrokeLayer$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                StrokeShader strokeShader = (StrokeShader) obj;
                switch (i) {
                    case 0:
                        return new ThinStrokeAnimation(strokeShader);
                    default:
                        return new ThickStrokeAnimation(strokeShader);
                }
            }
        };
        final int i2 = 1;
        THICK_ANIMATOR_FACTORY = new Function1() { // from class: com.oplus.systemui.notification.lockscreen.capsule.stroke.StrokeLayer$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                StrokeShader strokeShader = (StrokeShader) obj;
                switch (i2) {
                    case 0:
                        return new ThinStrokeAnimation(strokeShader);
                    default:
                        return new ThickStrokeAnimation(strokeShader);
                }
            }
        };
    }

    public StrokeLayer(Function1 function1) {
        IStrokeAnimator iStrokeAnimator;
        StrokeShader strokeShaderCreate = StrokeShader.Companion.create();
        this.strokeShader = strokeShaderCreate;
        this.strokeAnimator = (function1 == null || (iStrokeAnimator = (IStrokeAnimator) function1.invoke(strokeShaderCreate)) == null) ? new ThinStrokeAnimation(strokeShaderCreate) : iStrokeAnimator;
        Paint paint = new Paint();
        paint.setShader(strokeShaderCreate);
        this.paint = paint;
    }

    public final void draw(Canvas canvas, Rect rect) {
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        setResolution(rect.width(), rect.height());
        setSize(rect.width(), rect.height());
        setOffset(rect.left, rect.top);
        canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, this.paint);
    }

    public final float getAngle() {
        return this.strokeShader.getAngle();
    }

    public final float getAntiAliasing() {
        return this.strokeShader.getAntiAliasing();
    }

    public final float getCornerRadius() {
        return this.strokeShader.getCornerRadius();
    }

    public final float getFarAlpha() {
        return this.strokeShader.getFarAlpha();
    }

    public final float getFarFadeStart() {
        return this.strokeShader.getFarFadeStart();
    }

    public final float getFarFadeWidth() {
        return this.strokeShader.getFarFadeWidth();
    }

    public final float getFarStrokeStart() {
        return this.strokeShader.getFarStrokeStart();
    }

    public final float getFarStrokeWidth() {
        return this.strokeShader.getFarStrokeWidth();
    }

    public final float getNearAlpha() {
        return this.strokeShader.getNearAlpha();
    }

    public final float getNearFadeStart() {
        return this.strokeShader.getNearFadeStart();
    }

    public final float getNearFadeWidth() {
        return this.strokeShader.getNearFadeWidth();
    }

    public final float getNearStrokeStart() {
        return this.strokeShader.getNearStrokeStart();
    }

    public final float getNearStrokeWidth() {
        return this.strokeShader.getNearStrokeWidth();
    }

    public final Paint getPaint() {
        return this.paint;
    }

    public final int getStrokeColor() {
        return this.strokeShader.getStrokeColor();
    }

    public final float getStrokePower() {
        return this.strokeShader.getStrokePower();
    }

    public final float getStrokePowerMix() {
        return this.strokeShader.getStrokePowerMix();
    }

    public final float getWeight() {
        return this.strokeShader.getWeight();
    }

    public final void setAngle(float f) {
        this.strokeShader.setAngle(f);
    }

    public final void setAnimationProgress(float f) {
        this.strokeAnimator.setProgress(f);
    }

    public final void setAntiAliasing(float f) {
        this.strokeShader.setAntiAliasing(f);
    }

    public final void setCornerRadius(float f) {
        this.strokeShader.setCornerRadius(f);
    }

    public final void setFarAlpha(float f) {
        this.strokeShader.setFarAlpha(f);
    }

    public final void setFarFadeStart(float f) {
        this.strokeShader.setFarFadeStart(f);
    }

    public final void setFarFadeWidth(float f) {
        this.strokeShader.setFarFadeWidth(f);
    }

    public final void setFarStrokeStart(float f) {
        this.strokeShader.setFarStrokeStart(f);
    }

    public final void setFarStrokeWidth(float f) {
        this.strokeShader.setFarStrokeWidth(f);
    }

    public final void setNearAlpha(float f) {
        this.strokeShader.setNearAlpha(f);
    }

    public final void setNearFadeStart(float f) {
        this.strokeShader.setNearFadeStart(f);
    }

    public final void setNearFadeWidth(float f) {
        this.strokeShader.setNearFadeWidth(f);
    }

    public final void setNearStrokeStart(float f) {
        this.strokeShader.setNearStrokeStart(f);
    }

    public final void setNearStrokeWidth(float f) {
        this.strokeShader.setNearStrokeWidth(f);
    }

    public final void setOffset(float f, float f2) {
        this.strokeShader.setOffset(f, f2);
    }

    public final void setResolution(float f, float f2) {
        this.strokeShader.setResolution(f, f2);
    }

    public final void setSize(float f, float f2) {
        this.strokeShader.setSize(f, f2);
    }

    public final void setStartAngle(float f) {
        this.strokeAnimator.setStartAngle(f);
    }

    public final void setStrokeColor(int i) {
        this.strokeShader.setStrokeColor(i);
    }

    public final void setStrokePower(float f) {
        this.strokeShader.setStrokePower(f);
    }

    public final void setStrokePowerMix(float f) {
        this.strokeShader.setStrokePowerMix(f);
    }

    public final void setWeight(float f) {
        this.strokeShader.setWeight(f);
    }

    public final void draw(Canvas canvas, float f, float f2) {
        if (f <= 0.0f || f2 <= 0.0f) {
            return;
        }
        canvas.drawRect(0.0f, 0.0f, f, f2, this.paint);
    }
}
