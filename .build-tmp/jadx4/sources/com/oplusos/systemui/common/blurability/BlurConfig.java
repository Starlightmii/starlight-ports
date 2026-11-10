package com.oplusos.systemui.common.blurability;

import android.graphics.Rect;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplus.posteffect.params.CornerParams;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.posteffect.params.OpticsParams;
import com.oplus.posteffect.path.BlurDrawablePathProvider;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BlurConfig {
    public static final Companion Companion = new Companion();
    public int blurColor;
    public int blurRadius;
    private Rect captureRegion;
    public int cardType;
    public float cornerRadius;
    private final float[] cornerRadiusFloatArray;
    public boolean enableMotionAmountWithAlphaToUser;
    public boolean enableMotionSmoothCorner;
    public boolean enableStaticBlurCorner;
    private CornerParams gradientStrokeCornerParam;
    private GradientStrokeLineParams gradientStrokeLineParam;
    private InnerShadowParams innerShadowParams;
    public final boolean isFullScreen;
    public boolean isSupportMetaBall;
    public float leftBottomCornerRadius;
    public float leftTopCornerRadius;
    private BlurMixConfig.BlurMixSingle motionBlurMixConfig;
    public int motionBlurRadius;
    private Function1 onBlurDrawableCreated;
    private OpticsParams opticsParams;
    private BlurDrawablePathProvider pathProvider;
    public boolean platformBlurDrawableFullOpacity;
    private BlurMixConfig platformMixConfig;
    private Float radiusWeight;
    public float rightBottomCornerRadius;
    public float rightTopCornerRadius;
    public boolean scaleBlurRadiusWithAmount;
    private final WindowBlurConfig windowBlurConfig;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ BlurConfig(int i, int i2, Float f, GradientStrokeLineParams gradientStrokeLineParams, CornerParams cornerParams, InnerShadowParams innerShadowParams, boolean z, BlurMixConfig blurMixConfig, BlurMixConfig.BlurMixSingle blurMixSingle, int i3, WindowBlurConfig windowBlurConfig, int i4) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, 0.0f, 0.0f, 0.0f, 0.0f, (i4 & 64) != 0 ? null : f, null, (i4 & 256) != 0 ? null : gradientStrokeLineParams, (i4 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? new CornerParams(6) : cornerParams, (i4 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? null : innerShadowParams, null, false, (i4 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0, false, (32768 & i4) != 0 ? false : z, (65536 & i4) != 0 ? BlurMixConfig.None.INSTANCE : blurMixConfig, false, true, (524288 & i4) != 0 ? null : blurMixSingle, (1048576 & i4) != 0 ? 0 : i3, (i4 & 2097152) != 0 ? null : windowBlurConfig, false, null, null);
    }

    public final CornerParams component10() {
        return this.gradientStrokeCornerParam;
    }

    public final InnerShadowParams component11() {
        return this.innerShadowParams;
    }

    public final OpticsParams component12() {
        return this.opticsParams;
    }

    public final BlurMixConfig component17() {
        return this.platformMixConfig;
    }

    public final BlurMixConfig.BlurMixSingle component20() {
        return this.motionBlurMixConfig;
    }

    public final WindowBlurConfig component22() {
        return this.windowBlurConfig;
    }

    public final Rect component24() {
        return this.captureRegion;
    }

    public final Function1 component25() {
        return this.onBlurDrawableCreated;
    }

    public final Float component7() {
        return this.radiusWeight;
    }

    public final BlurDrawablePathProvider component8() {
        return this.pathProvider;
    }

    public final GradientStrokeLineParams component9() {
        return this.gradientStrokeLineParam;
    }

    public final BlurConfig copy(int i, int i2, float f, float f2, float f3, float f4, Float f5, BlurDrawablePathProvider blurDrawablePathProvider, GradientStrokeLineParams gradientStrokeLineParams, CornerParams cornerParams, InnerShadowParams innerShadowParams, OpticsParams opticsParams, boolean z, boolean z2, boolean z3, boolean z4, BlurMixConfig blurMixConfig, boolean z5, boolean z6, BlurMixConfig.BlurMixSingle blurMixSingle, int i3, WindowBlurConfig windowBlurConfig, boolean z7, Rect rect, Function1 function1) {
        return new BlurConfig(i, i2, f, f2, f3, f4, f5, blurDrawablePathProvider, gradientStrokeLineParams, cornerParams, innerShadowParams, opticsParams, z, z2, z3, z4, blurMixConfig, z5, z6, blurMixSingle, i3, windowBlurConfig, z7, rect, function1);
    }

    public final float[] cornerRadiusFloatArray() {
        float[] fArr = this.cornerRadiusFloatArray;
        float f = this.leftTopCornerRadius;
        fArr[0] = f;
        fArr[1] = f;
        float f2 = this.rightTopCornerRadius;
        fArr[2] = f2;
        fArr[3] = f2;
        float f3 = this.rightBottomCornerRadius;
        fArr[4] = f3;
        fArr[5] = f3;
        float f4 = this.leftBottomCornerRadius;
        fArr[6] = f4;
        fArr[7] = f4;
        return fArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlurConfig)) {
            return false;
        }
        BlurConfig blurConfig = (BlurConfig) obj;
        return this.blurRadius == blurConfig.blurRadius && this.blurColor == blurConfig.blurColor && Float.compare(this.leftTopCornerRadius, blurConfig.leftTopCornerRadius) == 0 && Float.compare(this.rightTopCornerRadius, blurConfig.rightTopCornerRadius) == 0 && Float.compare(this.rightBottomCornerRadius, blurConfig.rightBottomCornerRadius) == 0 && Float.compare(this.leftBottomCornerRadius, blurConfig.leftBottomCornerRadius) == 0 && Intrinsics.areEqual(this.radiusWeight, blurConfig.radiusWeight) && Intrinsics.areEqual(this.pathProvider, blurConfig.pathProvider) && Intrinsics.areEqual(this.gradientStrokeLineParam, blurConfig.gradientStrokeLineParam) && Intrinsics.areEqual(this.gradientStrokeCornerParam, blurConfig.gradientStrokeCornerParam) && Intrinsics.areEqual(this.innerShadowParams, blurConfig.innerShadowParams) && Intrinsics.areEqual(this.opticsParams, blurConfig.opticsParams) && this.enableStaticBlurCorner == blurConfig.enableStaticBlurCorner && this.enableMotionAmountWithAlphaToUser == blurConfig.enableMotionAmountWithAlphaToUser && this.enableMotionSmoothCorner == blurConfig.enableMotionSmoothCorner && this.isFullScreen == blurConfig.isFullScreen && Intrinsics.areEqual(this.platformMixConfig, blurConfig.platformMixConfig) && this.platformBlurDrawableFullOpacity == blurConfig.platformBlurDrawableFullOpacity && this.scaleBlurRadiusWithAmount == blurConfig.scaleBlurRadiusWithAmount && Intrinsics.areEqual(this.motionBlurMixConfig, blurConfig.motionBlurMixConfig) && this.motionBlurRadius == blurConfig.motionBlurRadius && Intrinsics.areEqual(this.windowBlurConfig, blurConfig.windowBlurConfig) && this.isSupportMetaBall == blurConfig.isSupportMetaBall && Intrinsics.areEqual(this.captureRegion, blurConfig.captureRegion) && Intrinsics.areEqual(this.onBlurDrawableCreated, blurConfig.onBlurDrawableCreated);
    }

    public final int getBlurColor() {
        return this.blurColor;
    }

    public final int getBlurRadius() {
        return this.blurRadius;
    }

    public final Rect getCaptureRegion() {
        return this.captureRegion;
    }

    public final int getCardType() {
        return this.cardType;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final boolean getEnableMotionAmountWithAlphaToUser() {
        return this.enableMotionAmountWithAlphaToUser;
    }

    public final boolean getEnableMotionSmoothCorner() {
        return this.enableMotionSmoothCorner;
    }

    public final boolean getEnableStaticBlurCorner() {
        return this.enableStaticBlurCorner;
    }

    public final CornerParams getGradientStrokeCornerParam() {
        return this.gradientStrokeCornerParam;
    }

    public final GradientStrokeLineParams getGradientStrokeLineParam() {
        return this.gradientStrokeLineParam;
    }

    public final InnerShadowParams getInnerShadowParams() {
        return this.innerShadowParams;
    }

    public final float getLeftBottomCornerRadius() {
        return this.leftBottomCornerRadius;
    }

    public final float getLeftTopCornerRadius() {
        return this.leftTopCornerRadius;
    }

    public final BlurMixConfig.BlurMixSingle getMotionBlurMixConfig() {
        return this.motionBlurMixConfig;
    }

    public final int getMotionBlurRadius() {
        return this.motionBlurRadius;
    }

    public final Function1 getOnBlurDrawableCreated() {
        return this.onBlurDrawableCreated;
    }

    public final OpticsParams getOpticsParams() {
        return this.opticsParams;
    }

    public final BlurDrawablePathProvider getPathProvider() {
        return this.pathProvider;
    }

    public final boolean getPlatformBlurDrawableFullOpacity() {
        return this.platformBlurDrawableFullOpacity;
    }

    public final BlurMixConfig getPlatformMixConfig() {
        return this.platformMixConfig;
    }

    public final Float getRadiusWeight() {
        return this.radiusWeight;
    }

    public final float getRightBottomCornerRadius() {
        return this.rightBottomCornerRadius;
    }

    public final float getRightTopCornerRadius() {
        return this.rightTopCornerRadius;
    }

    public final boolean getScaleBlurRadiusWithAmount() {
        return this.scaleBlurRadiusWithAmount;
    }

    public final WindowBlurConfig getWindowBlurConfig() {
        return this.windowBlurConfig;
    }

    public final int hashCode() {
        int iM = ChangeSize$.ExternalSyntheticOutline0.m(this.leftBottomCornerRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.rightBottomCornerRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.rightTopCornerRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.leftTopCornerRadius, MonoSpline$.ExternalSyntheticOutline0.m(this.blurColor, Integer.hashCode(this.blurRadius) * 31, 31), 31), 31), 31), 31);
        Float f = this.radiusWeight;
        int iHashCode = (iM + (f == null ? 0 : f.hashCode())) * 31;
        BlurDrawablePathProvider blurDrawablePathProvider = this.pathProvider;
        int iHashCode2 = (iHashCode + (blurDrawablePathProvider == null ? 0 : blurDrawablePathProvider.hashCode())) * 31;
        GradientStrokeLineParams gradientStrokeLineParams = this.gradientStrokeLineParam;
        int iHashCode3 = (this.gradientStrokeCornerParam.hashCode() + ((iHashCode2 + (gradientStrokeLineParams == null ? 0 : gradientStrokeLineParams.hashCode())) * 31)) * 31;
        InnerShadowParams innerShadowParams = this.innerShadowParams;
        int iHashCode4 = (iHashCode3 + (innerShadowParams == null ? 0 : innerShadowParams.hashCode())) * 31;
        OpticsParams opticsParams = this.opticsParams;
        int iM2 = ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m((this.platformMixConfig.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m((iHashCode4 + (opticsParams == null ? 0 : opticsParams.hashCode())) * 31, 31, this.enableStaticBlurCorner), 31, this.enableMotionAmountWithAlphaToUser), 31, this.enableMotionSmoothCorner), 31, this.isFullScreen)) * 31, 31, this.platformBlurDrawableFullOpacity), 31, this.scaleBlurRadiusWithAmount);
        BlurMixConfig.BlurMixSingle blurMixSingle = this.motionBlurMixConfig;
        int iM3 = MonoSpline$.ExternalSyntheticOutline0.m(this.motionBlurRadius, (iM2 + (blurMixSingle == null ? 0 : blurMixSingle.hashCode())) * 31, 31);
        WindowBlurConfig windowBlurConfig = this.windowBlurConfig;
        int iM4 = ChangeSize$.ExternalSyntheticOutline0.m((iM3 + (windowBlurConfig == null ? 0 : windowBlurConfig.hashCode())) * 31, 31, this.isSupportMetaBall);
        Rect rect = this.captureRegion;
        int iHashCode5 = (iM4 + (rect == null ? 0 : rect.hashCode())) * 31;
        Function1 function1 = this.onBlurDrawableCreated;
        return iHashCode5 + (function1 != null ? function1.hashCode() : 0);
    }

    public final int platformBlurRadiusForAmount(float f) {
        boolean z = this.scaleBlurRadiusWithAmount;
        int i = this.blurRadius;
        return z ? (int) (i * f) : i;
    }

    public final void setBlurColor(int i) {
        this.blurColor = i;
    }

    public final void setBlurRadius(int i) {
        this.blurRadius = i;
    }

    public final void setCaptureRegion(Rect rect) {
        this.captureRegion = rect;
    }

    public final void setCardType(int i) {
        this.cardType = i;
    }

    public final void setCornerRadius(float f) {
        if (f != this.cornerRadius) {
            this.cornerRadius = f;
        }
        if (f == this.leftTopCornerRadius && f == this.leftBottomCornerRadius && f == this.rightTopCornerRadius && f == this.rightBottomCornerRadius) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("[setCornerRadius] ", f, "IBlurConfig-->", "SystemUi--Common");
        }
        this.leftTopCornerRadius = f;
        this.rightTopCornerRadius = f;
        this.rightBottomCornerRadius = f;
        this.leftBottomCornerRadius = f;
    }

    public final void setEnableMotionAmountWithAlphaToUser(boolean z) {
        this.enableMotionAmountWithAlphaToUser = z;
    }

    public final void setEnableMotionSmoothCorner(boolean z) {
        this.enableMotionSmoothCorner = z;
    }

    public final void setEnableStaticBlurCorner(boolean z) {
        this.enableStaticBlurCorner = z;
    }

    public final void setGradientStrokeCornerParam(CornerParams cornerParams) {
        this.gradientStrokeCornerParam = cornerParams;
    }

    public final void setGradientStrokeLineParam(GradientStrokeLineParams gradientStrokeLineParams) {
        this.gradientStrokeLineParam = gradientStrokeLineParams;
    }

    public final void setInnerShadowParams(InnerShadowParams innerShadowParams) {
        this.innerShadowParams = innerShadowParams;
    }

    public final void setLeftBottomCornerRadius(float f) {
        this.leftBottomCornerRadius = f;
    }

    public final void setLeftTopCornerRadius(float f) {
        this.leftTopCornerRadius = f;
    }

    public final void setMotionBlurMixConfig(BlurMixConfig.BlurMixSingle blurMixSingle) {
        this.motionBlurMixConfig = blurMixSingle;
    }

    public final void setMotionBlurRadius(int i) {
        this.motionBlurRadius = i;
    }

    public final void setOnBlurDrawableCreated(Function1 function1) {
        this.onBlurDrawableCreated = function1;
    }

    public final void setOpticsParams(OpticsParams opticsParams) {
        this.opticsParams = opticsParams;
    }

    public final void setPathProvider(BlurDrawablePathProvider blurDrawablePathProvider) {
        this.pathProvider = blurDrawablePathProvider;
    }

    public final void setPlatformBlurDrawableFullOpacity(boolean z) {
        this.platformBlurDrawableFullOpacity = z;
    }

    public final void setPlatformMixConfig(BlurMixConfig blurMixConfig) {
        this.platformMixConfig = blurMixConfig;
    }

    public final void setRadiusWeight(Float f) {
        this.radiusWeight = f;
    }

    public final void setRightBottomCornerRadius(float f) {
        this.rightBottomCornerRadius = f;
    }

    public final void setRightTopCornerRadius(float f) {
        this.rightTopCornerRadius = f;
    }

    public final void setScaleBlurRadiusWithAmount(boolean z) {
        this.scaleBlurRadiusWithAmount = z;
    }

    public final void setSupportMetaBall(boolean z) {
        this.isSupportMetaBall = z;
    }

    public String toString() {
        return "BlurConfig(blurRadius=" + this.blurRadius + ", blurColor=" + this.blurColor + ", leftTopCornerRadius=" + this.leftTopCornerRadius + ", rightTopCornerRadius=" + this.rightTopCornerRadius + ", rightBottomCornerRadius=" + this.rightBottomCornerRadius + ", leftBottomCornerRadius=" + this.leftBottomCornerRadius + ", radiusWeight=" + this.radiusWeight + ", pathProvider=" + this.pathProvider + ", gradientStrokeLineParam=" + this.gradientStrokeLineParam + ", gradientStrokeCornerParam=" + this.gradientStrokeCornerParam + ", innerShadowParams=" + this.innerShadowParams + ", opticsParams=" + this.opticsParams + ", enableStaticBlurCorner=" + this.enableStaticBlurCorner + ", enableMotionAmountWithAlphaToUser=" + this.enableMotionAmountWithAlphaToUser + ", enableMotionSmoothCorner=" + this.enableMotionSmoothCorner + ", isFullScreen=" + this.isFullScreen + ", platformMixConfig=" + this.platformMixConfig + ", platformBlurDrawableFullOpacity=" + this.platformBlurDrawableFullOpacity + ", scaleBlurRadiusWithAmount=" + this.scaleBlurRadiusWithAmount + ", motionBlurMixConfig=" + this.motionBlurMixConfig + ", motionBlurRadius=" + this.motionBlurRadius + ", windowBlurConfig=" + this.windowBlurConfig + ", isSupportMetaBall=" + this.isSupportMetaBall + ", captureRegion=" + this.captureRegion + ", onBlurDrawableCreated=" + this.onBlurDrawableCreated + ')';
    }

    public BlurConfig(int i, int i2, float f, float f2, float f3, float f4, Float f5, BlurDrawablePathProvider blurDrawablePathProvider, GradientStrokeLineParams gradientStrokeLineParams, CornerParams cornerParams, InnerShadowParams innerShadowParams, OpticsParams opticsParams, boolean z, boolean z2, boolean z3, boolean z4, BlurMixConfig blurMixConfig, boolean z5, boolean z6, BlurMixConfig.BlurMixSingle blurMixSingle, int i3, WindowBlurConfig windowBlurConfig, boolean z7, Rect rect, Function1 function1) {
        this.blurRadius = i;
        this.blurColor = i2;
        this.leftTopCornerRadius = f;
        this.rightTopCornerRadius = f2;
        this.rightBottomCornerRadius = f3;
        this.leftBottomCornerRadius = f4;
        this.radiusWeight = f5;
        this.pathProvider = blurDrawablePathProvider;
        this.gradientStrokeLineParam = gradientStrokeLineParams;
        this.gradientStrokeCornerParam = cornerParams;
        this.innerShadowParams = innerShadowParams;
        this.opticsParams = opticsParams;
        this.enableStaticBlurCorner = z;
        this.enableMotionAmountWithAlphaToUser = z2;
        this.enableMotionSmoothCorner = z3;
        this.isFullScreen = z4;
        this.platformMixConfig = blurMixConfig;
        this.platformBlurDrawableFullOpacity = z5;
        this.scaleBlurRadiusWithAmount = z6;
        this.motionBlurMixConfig = blurMixSingle;
        this.motionBlurRadius = i3;
        this.windowBlurConfig = windowBlurConfig;
        this.isSupportMetaBall = z7;
        this.captureRegion = rect;
        this.onBlurDrawableCreated = function1;
        this.cardType = -1;
        this.cornerRadiusFloatArray = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }
}
