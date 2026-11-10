package com.oplusos.systemui.common.shader;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MetaballLightConfig {
    public static final Companion Companion = new Companion();
    private final MetaballLightCenterProvider centerProvider;
    public final float centerXRatio;
    public final float centerYOffsetPx;
    public final long playDurationMs;
    public final float rotationSpeed;
    public final float shadeAlpha;
    public final float shadeRadius;
    public final int shaderResId;
    public final int textureResId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MetaballLightConfig(int i, int i2, long j, float f, float f2, float f3, float f4, float f5, MetaballLightCenterProvider metaballLightCenterProvider) {
        this.textureResId = i;
        this.shaderResId = i2;
        this.playDurationMs = j;
        this.shadeAlpha = f;
        this.rotationSpeed = f2;
        this.shadeRadius = f3;
        this.centerXRatio = f4;
        this.centerYOffsetPx = f5;
        this.centerProvider = metaballLightCenterProvider;
    }

    public final MetaballLightCenterProvider component9() {
        return this.centerProvider;
    }

    public final MetaballLightConfig copy(int i, int i2, long j, float f, float f2, float f3, float f4, float f5, MetaballLightCenterProvider metaballLightCenterProvider) {
        return new MetaballLightConfig(i, i2, j, f, f2, f3, f4, f5, metaballLightCenterProvider);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetaballLightConfig)) {
            return false;
        }
        MetaballLightConfig metaballLightConfig = (MetaballLightConfig) obj;
        return this.textureResId == metaballLightConfig.textureResId && this.shaderResId == metaballLightConfig.shaderResId && this.playDurationMs == metaballLightConfig.playDurationMs && Float.compare(this.shadeAlpha, metaballLightConfig.shadeAlpha) == 0 && Float.compare(this.rotationSpeed, metaballLightConfig.rotationSpeed) == 0 && Float.compare(this.shadeRadius, metaballLightConfig.shadeRadius) == 0 && Float.compare(this.centerXRatio, metaballLightConfig.centerXRatio) == 0 && Float.compare(this.centerYOffsetPx, metaballLightConfig.centerYOffsetPx) == 0 && Intrinsics.areEqual(this.centerProvider, metaballLightConfig.centerProvider);
    }

    public final MetaballLightCenterProvider getCenterProvider() {
        return this.centerProvider;
    }

    public final float getCenterXRatio() {
        return this.centerXRatio;
    }

    public final float getCenterYOffsetPx() {
        return this.centerYOffsetPx;
    }

    public final float getFinalTimeSec() {
        long j = this.playDurationMs;
        if (j < 0) {
            j = 0;
        }
        return j / 1000.0f;
    }

    public final long getPlayDurationMs() {
        return this.playDurationMs;
    }

    public final float getRotationSpeed() {
        return this.rotationSpeed;
    }

    public final float getShadeAlpha() {
        return this.shadeAlpha;
    }

    public final float getShadeRadius() {
        return this.shadeRadius;
    }

    public final int getShaderResId() {
        return this.shaderResId;
    }

    public final int getTextureResId() {
        return this.textureResId;
    }

    public final int hashCode() {
        return this.centerProvider.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.centerYOffsetPx, ChangeSize$.ExternalSyntheticOutline0.m(this.centerXRatio, ChangeSize$.ExternalSyntheticOutline0.m(this.shadeRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.rotationSpeed, ChangeSize$.ExternalSyntheticOutline0.m(this.shadeAlpha, ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.shaderResId, Integer.hashCode(this.textureResId) * 31, 31), 31, this.playDurationMs), 31), 31), 31), 31), 31);
    }

    public String toString() {
        return "MetaballLightConfig(textureResId=" + this.textureResId + ", shaderResId=" + this.shaderResId + ", playDurationMs=" + this.playDurationMs + ", shadeAlpha=" + this.shadeAlpha + ", rotationSpeed=" + this.rotationSpeed + ", shadeRadius=" + this.shadeRadius + ", centerXRatio=" + this.centerXRatio + ", centerYOffsetPx=" + this.centerYOffsetPx + ", centerProvider=" + this.centerProvider + ')';
    }
}
