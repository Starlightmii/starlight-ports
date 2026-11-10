package com.oplus.systemui.qs.media;

import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.coui.appcompat.spotlight.COUISpotLightEffect;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightLiveAlertCard;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsMediaSpotLightHelper {
    private final ClipShape clipShape;
    private final View host;
    private COUISpotLightEffectDrawable spotLightDrawable;
    private final Path clipPath = new Path();
    private final RectF tmpRectF = new RectF();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ClipShape {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ ClipShape[] $VALUES;
        public static final ClipShape CAPSULE;
        public static final ClipShape CIRCLE;

        static {
            ClipShape clipShape = new ClipShape(FlashlightLiveAlertCard.SHAPE_CIRCLE, 0);
            CIRCLE = clipShape;
            ClipShape clipShape2 = new ClipShape("CAPSULE", 1);
            CAPSULE = clipShape2;
            ClipShape[] clipShapeArr = {clipShape, clipShape2};
            $VALUES = clipShapeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(clipShapeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static ClipShape valueOf(String str) {
            return (ClipShape) Enum.valueOf(ClipShape.class, str);
        }

        public static ClipShape[] values() {
            return (ClipShape[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpotLightEffectParams {
        public final float ambientStrength;
        private final BlendMode blendMode;
        public final float diffuseStrength;
        public final float ditherN;
        public final float endIntensity;
        public final float endRadius;
        public final float lightHeight;
        public final boolean moveAnimation;
        public final float noise;
        public final float startIntensity;
        public final float startRadius;
        public static final Companion Companion = new Companion();
        private static final SpotLightEffectParams BUTTON = new SpotLightEffectParams(BlendMode.OVERLAY, true, 0.0f, 1.0f, 200.0f, 320.0f, 80.0f, 0.2f, 0.2f, 0.0f, 0.08f);

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final SpotLightEffectParams getBUTTON() {
                return SpotLightEffectParams.BUTTON;
            }
        }

        public SpotLightEffectParams(BlendMode blendMode, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
            this.blendMode = blendMode;
            this.moveAnimation = z;
            this.startIntensity = f;
            this.endIntensity = f2;
            this.startRadius = f3;
            this.endRadius = f4;
            this.lightHeight = f5;
            this.ambientStrength = f6;
            this.diffuseStrength = f7;
            this.ditherN = f8;
            this.noise = f9;
        }

        public final void applyTo(COUISpotLightEffectDrawable cOUISpotLightEffectDrawable) {
            cOUISpotLightEffectDrawable.setPaintBlendMode(this.blendMode);
            cOUISpotLightEffectDrawable.setEnableMovingAnimation(this.moveAnimation);
            cOUISpotLightEffectDrawable.setIntensityRange(this.startIntensity, this.endIntensity);
            cOUISpotLightEffectDrawable.setRadiusRange(this.startRadius, this.endRadius);
            cOUISpotLightEffectDrawable.setLightHeight(this.lightHeight);
            cOUISpotLightEffectDrawable.setAmbientStrength(this.ambientStrength);
            cOUISpotLightEffectDrawable.setDiffuseStrength(this.diffuseStrength);
            cOUISpotLightEffectDrawable.setDitherN(this.ditherN);
            cOUISpotLightEffectDrawable.setNoise(this.noise);
        }

        public final BlendMode component1() {
            return this.blendMode;
        }

        public final SpotLightEffectParams copy(BlendMode blendMode, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
            return new SpotLightEffectParams(blendMode, z, f, f2, f3, f4, f5, f6, f7, f8, f9);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpotLightEffectParams)) {
                return false;
            }
            SpotLightEffectParams spotLightEffectParams = (SpotLightEffectParams) obj;
            return this.blendMode == spotLightEffectParams.blendMode && this.moveAnimation == spotLightEffectParams.moveAnimation && Float.compare(this.startIntensity, spotLightEffectParams.startIntensity) == 0 && Float.compare(this.endIntensity, spotLightEffectParams.endIntensity) == 0 && Float.compare(this.startRadius, spotLightEffectParams.startRadius) == 0 && Float.compare(this.endRadius, spotLightEffectParams.endRadius) == 0 && Float.compare(this.lightHeight, spotLightEffectParams.lightHeight) == 0 && Float.compare(this.ambientStrength, spotLightEffectParams.ambientStrength) == 0 && Float.compare(this.diffuseStrength, spotLightEffectParams.diffuseStrength) == 0 && Float.compare(this.ditherN, spotLightEffectParams.ditherN) == 0 && Float.compare(this.noise, spotLightEffectParams.noise) == 0;
        }

        public final float getAmbientStrength() {
            return this.ambientStrength;
        }

        public final BlendMode getBlendMode() {
            return this.blendMode;
        }

        public final float getDiffuseStrength() {
            return this.diffuseStrength;
        }

        public final float getDitherN() {
            return this.ditherN;
        }

        public final float getEndIntensity() {
            return this.endIntensity;
        }

        public final float getEndRadius() {
            return this.endRadius;
        }

        public final float getLightHeight() {
            return this.lightHeight;
        }

        public final boolean getMoveAnimation() {
            return this.moveAnimation;
        }

        public final float getNoise() {
            return this.noise;
        }

        public final float getStartIntensity() {
            return this.startIntensity;
        }

        public final float getStartRadius() {
            return this.startRadius;
        }

        public final int hashCode() {
            return Float.hashCode(this.noise) + ChangeSize$.ExternalSyntheticOutline0.m(this.ditherN, ChangeSize$.ExternalSyntheticOutline0.m(this.diffuseStrength, ChangeSize$.ExternalSyntheticOutline0.m(this.ambientStrength, ChangeSize$.ExternalSyntheticOutline0.m(this.lightHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.endRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.startRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.endIntensity, ChangeSize$.ExternalSyntheticOutline0.m(this.startIntensity, ChangeSize$.ExternalSyntheticOutline0.m(this.blendMode.hashCode() * 31, 31, this.moveAnimation), 31), 31), 31), 31), 31), 31), 31), 31);
        }

        public String toString() {
            BlendMode blendMode = this.blendMode;
            StringBuilder sb = new StringBuilder("SpotLightEffectParams(blendMode=");
            sb.append(blendMode);
            sb.append(", moveAnimation=");
            sb.append(this.moveAnimation);
            sb.append(", startIntensity=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, this.startIntensity, ", endIntensity=", this.endIntensity, ", startRadius=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, this.startRadius, ", endRadius=", this.endRadius, ", lightHeight=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, this.lightHeight, ", ambientStrength=", this.ambientStrength, ", diffuseStrength=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, this.diffuseStrength, ", ditherN=", this.ditherN, ", noise=");
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.noise, ")", sb);
        }
    }

    public QsMediaSpotLightHelper(View view, ClipShape clipShape) {
        this.host = view;
        this.clipShape = clipShape;
    }

    public final void drawSpotLightEffect(Canvas canvas) {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.spotLightDrawable;
        if (cOUISpotLightEffectDrawable != null && cOUISpotLightEffectDrawable.mEnabled) {
            int width = this.host.getWidth();
            int height = this.host.getHeight();
            if (width <= 0 || height <= 0) {
                return;
            }
            cOUISpotLightEffectDrawable.setBounds(0, 0, width, height);
            this.clipPath.rewind();
            this.tmpRectF.set(0.0f, 0.0f, width, height);
            int iOrdinal = this.clipShape.ordinal();
            if (iOrdinal == 0) {
                this.clipPath.addCircle(this.tmpRectF.centerX(), this.tmpRectF.centerY(), Math.min(this.tmpRectF.width(), this.tmpRectF.height()) / 2.0f, Path.Direction.CW);
            } else if (iOrdinal != 1) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            } else {
                float fMin = Math.min(this.tmpRectF.width(), this.tmpRectF.height()) / 2.0f;
                this.clipPath.addRoundRect(this.tmpRectF, fMin, fMin, Path.Direction.CW);
            }
            Path path = this.clipPath;
            int iSave = canvas.save();
            canvas.clipPath(path);
            try {
                cOUISpotLightEffectDrawable.draw(canvas);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public final void ensureDrawable() {
        if (this.spotLightDrawable != null) {
            return;
        }
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = new COUISpotLightEffectDrawable();
        cOUISpotLightEffectDrawable.setSpotLightType(this.host.getContext(), COUISpotLightEffect.SpotLightType.TYPE_CIRCLE_1);
        SpotLightEffectParams.Companion.getBUTTON().applyTo(cOUISpotLightEffectDrawable);
        cOUISpotLightEffectDrawable.setEnabled(this.host.isEnabled());
        cOUISpotLightEffectDrawable.setCallback(this.host);
        this.spotLightDrawable = cOUISpotLightEffectDrawable;
    }

    public final void handleMotionEvent(MotionEvent motionEvent) {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.spotLightDrawable;
        if (cOUISpotLightEffectDrawable != null && cOUISpotLightEffectDrawable.mEnabled && cOUISpotLightEffectDrawable.getBounds().width() > 0 && cOUISpotLightEffectDrawable.getBounds().height() > 0) {
            cOUISpotLightEffectDrawable.onTouchEvent(motionEvent);
            cOUISpotLightEffectDrawable.invalidateSelf();
        }
    }

    public final void releaseDrawable() {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.spotLightDrawable;
        if (cOUISpotLightEffectDrawable != null) {
            cOUISpotLightEffectDrawable.setCallback(null);
            cOUISpotLightEffectDrawable.cancel();
        }
        this.spotLightDrawable = null;
    }

    public final void syncDrawableEnabledFromHost() {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.spotLightDrawable;
        if (cOUISpotLightEffectDrawable != null) {
            cOUISpotLightEffectDrawable.setEnabled(this.host.isEnabled());
        }
    }

    public final void updateDrawableBounds(int i, int i2) {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable;
        if (i <= 0 || i2 <= 0 || (cOUISpotLightEffectDrawable = this.spotLightDrawable) == null) {
            return;
        }
        cOUISpotLightEffectDrawable.setBounds(0, 0, i, i2);
    }
}
