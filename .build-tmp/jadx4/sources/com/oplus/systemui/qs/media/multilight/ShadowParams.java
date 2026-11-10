package com.oplus.systemui.qs.media.multilight;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.mechanics.haptics.SegmentHaptics$SpringTension$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadowParams {
    private static final ShadowParams BOTTOM;
    private static final ShadowParams LEFT;
    private static final ShadowParams RIGHT;
    private static final ShadowParams TOP;
    public final float fadeAlphaX;
    public final float fadeAlphaY;
    public final float fadeIn;
    public final float fadeInPara;
    public final float fadeOffsetX;
    public final float fadeOffsetY;
    public final float fadeOut;
    public final float fadeOutPara;
    public final float fadeScaleX;
    public final float fadeScaleY;
    public final float standardHeight;
    public final float standardWidth;
    public static final Companion Companion = new Companion();
    private static final ShadowParams FULL = new ShadowParams(0.2f, 0.1f, 78.0f, 0.0f, 26.0f, 1.05f, 1.2f, 0.9f, 1.0f, 0.5f);
    private static final ShadowParams MEDIA_ONE_LIGHT = new ShadowParams(0.4f, 0.0f, 140.0f, 2.0f, 70.0f, 1.43f, 1.5f, 0.0f, 0.0f, 0.0f, 483.0f, 483.0f);
    private static final ShadowParams MEDIA_ONE_NIGHT = new ShadowParams(0.5f, 0.0f, 140.0f, 2.0f, 70.0f, 1.43f, 1.5f, 0.0f, 0.0f, 0.0f, 483.0f, 483.0f);
    private static final ShadowParams MEDIA_TWO = new ShadowParams(0.7f, 0.0f, 160.0f, 0.0f, 110.0f, 1.3f, 1.16f, 0.8f, 0.0f, 0.0f, 1104.0f, 1990.0f);
    private static final ShadowParams MEDIA_TWO_DEFAULT_LIGHT = new ShadowParams(0.15f, 0.0f, 160.0f, 0.0f, 110.0f, 1.3f, 1.16f, 0.8f, 0.0f, 0.0f, 936.0f, 1695.0f);
    private static final ShadowParams MEDIA_TWO_DEFAULT_NIGHT = new ShadowParams(0.1f, 0.0f, 160.0f, 0.0f, 110.0f, 1.3f, 1.16f, 0.8f, 0.0f, 0.0f, 936.0f, 1695.0f);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public abstract /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[PositionEnum.values().length];
                try {
                    PositionEnum.Companion companion = PositionEnum.Companion;
                    iArr[1] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    PositionEnum.Companion companion2 = PositionEnum.Companion;
                    iArr[2] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    PositionEnum.Companion companion3 = PositionEnum.Companion;
                    iArr[3] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    PositionEnum.Companion companion4 = PositionEnum.Companion;
                    iArr[4] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    PositionEnum.Companion companion5 = PositionEnum.Companion;
                    iArr[5] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public final ShadowParams fromPosition(PositionEnum positionEnum) {
            int i = positionEnum == null ? -1 : WhenMappings.$EnumSwitchMapping$0[positionEnum.ordinal()];
            if (i == -1) {
                return null;
            }
            if (i == 1) {
                return getLEFT();
            }
            if (i == 2) {
                return getTOP();
            }
            if (i == 3) {
                return getRIGHT();
            }
            if (i != 4 && i == 5) {
                return getFULL();
            }
            return getBOTTOM();
        }

        public final ShadowParams getBOTTOM() {
            return ShadowParams.BOTTOM;
        }

        public final ShadowParams getFULL() {
            return ShadowParams.FULL;
        }

        public final ShadowParams getLEFT() {
            return ShadowParams.LEFT;
        }

        public final ShadowParams getMEDIA_ONE_LIGHT() {
            return ShadowParams.MEDIA_ONE_LIGHT;
        }

        public final ShadowParams getMEDIA_ONE_NIGHT() {
            return ShadowParams.MEDIA_ONE_NIGHT;
        }

        public final ShadowParams getMEDIA_TWO() {
            return ShadowParams.MEDIA_TWO;
        }

        public final ShadowParams getMEDIA_TWO_DEFAULT_LIGHT() {
            return ShadowParams.MEDIA_TWO_DEFAULT_LIGHT;
        }

        public final ShadowParams getMEDIA_TWO_DEFAULT_NIGHT() {
            return ShadowParams.MEDIA_TWO_DEFAULT_NIGHT;
        }

        public final ShadowParams getRIGHT() {
            return ShadowParams.RIGHT;
        }

        public final ShadowParams getTOP() {
            return ShadowParams.TOP;
        }
    }

    static {
        float f = 0.0f;
        LEFT = new ShadowParams(0.04f, 0.08f, 200.0f, 100.0f, f, 1.3f, 16.0f, 1.0f, 46.0f, 0.4f);
        TOP = new ShadowParams(0.3f, 0.01f, 86.0f, f, -200.0f, 3.0f, 2.0f, 0.4f, 121.0f, 0.8f);
        float f2 = 0.0f;
        RIGHT = new ShadowParams(0.04f, 0.08f, 200.0f, -100.0f, f2, 1.3f, 16.0f, 1.0f, 46.0f, 0.4f);
        BOTTOM = new ShadowParams(0.3f, 0.01f, 86.0f, f2, 200.0f, 3.0f, 2.0f, 0.4f, 121.0f, 0.8f);
    }

    public /* synthetic */ ShadowParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, 1148.0f, 300.0f);
    }

    public final void applyTo(MultiLightShaderParams multiLightShaderParams) {
        multiLightShaderParams.setFadeAlpha(Float.valueOf(this.fadeAlphaX), Float.valueOf(this.fadeAlphaY));
        multiLightShaderParams.setFadeIn(this.fadeIn);
        multiLightShaderParams.setFadeInPara(this.fadeInPara);
        multiLightShaderParams.setFadeOffset(Float.valueOf(this.fadeOffsetX), Float.valueOf(this.fadeOffsetY));
        multiLightShaderParams.setFadeScale(Float.valueOf(this.fadeScaleX), Float.valueOf(this.fadeScaleY));
        multiLightShaderParams.setFadeOut(this.fadeOut);
        multiLightShaderParams.setFadeOutPara(this.fadeOutPara);
    }

    public final ShadowParams copy(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        return new ShadowParams(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShadowParams)) {
            return false;
        }
        ShadowParams shadowParams = (ShadowParams) obj;
        return Float.compare(this.fadeAlphaX, shadowParams.fadeAlphaX) == 0 && Float.compare(this.fadeAlphaY, shadowParams.fadeAlphaY) == 0 && Float.compare(this.fadeIn, shadowParams.fadeIn) == 0 && Float.compare(this.fadeOffsetX, shadowParams.fadeOffsetX) == 0 && Float.compare(this.fadeOffsetY, shadowParams.fadeOffsetY) == 0 && Float.compare(this.fadeScaleX, shadowParams.fadeScaleX) == 0 && Float.compare(this.fadeScaleY, shadowParams.fadeScaleY) == 0 && Float.compare(this.fadeInPara, shadowParams.fadeInPara) == 0 && Float.compare(this.fadeOut, shadowParams.fadeOut) == 0 && Float.compare(this.fadeOutPara, shadowParams.fadeOutPara) == 0 && Float.compare(this.standardWidth, shadowParams.standardWidth) == 0 && Float.compare(this.standardHeight, shadowParams.standardHeight) == 0;
    }

    public final float getFadeAlphaX() {
        return this.fadeAlphaX;
    }

    public final float getFadeAlphaY() {
        return this.fadeAlphaY;
    }

    public final float getFadeIn() {
        return this.fadeIn;
    }

    public final float getFadeInPara() {
        return this.fadeInPara;
    }

    public final float getFadeOffsetX() {
        return this.fadeOffsetX;
    }

    public final float getFadeOffsetY() {
        return this.fadeOffsetY;
    }

    public final float getFadeOut() {
        return this.fadeOut;
    }

    public final float getFadeOutPara() {
        return this.fadeOutPara;
    }

    public final float getFadeScaleX() {
        return this.fadeScaleX;
    }

    public final float getFadeScaleY() {
        return this.fadeScaleY;
    }

    public final float getStandardHeight() {
        return this.standardHeight;
    }

    public final float getStandardWidth() {
        return this.standardWidth;
    }

    public final int hashCode() {
        return Float.hashCode(this.standardHeight) + ChangeSize$.ExternalSyntheticOutline0.m(this.standardWidth, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeOutPara, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeOut, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeInPara, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeScaleY, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeScaleX, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeOffsetY, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeOffsetX, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeIn, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeAlphaY, Float.hashCode(this.fadeAlphaX) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("ShadowParams(fadeAlphaX=", this.fadeAlphaX, ", fadeAlphaY=", this.fadeAlphaY, ", fadeIn=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.fadeIn, ", fadeOffsetX=", this.fadeOffsetX, ", fadeOffsetY=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.fadeOffsetY, ", fadeScaleX=", this.fadeScaleX, ", fadeScaleY=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.fadeScaleY, ", fadeInPara=", this.fadeInPara, ", fadeOut=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.fadeOut, ", fadeOutPara=", this.fadeOutPara, ", standardWidth=");
        return SegmentHaptics$SpringTension$.ExternalSyntheticOutline0.m(sbM, this.standardWidth, ", standardHeight=", this.standardHeight, ")");
    }

    public ShadowParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        this.fadeAlphaX = f;
        this.fadeAlphaY = f2;
        this.fadeIn = f3;
        this.fadeOffsetX = f4;
        this.fadeOffsetY = f5;
        this.fadeScaleX = f6;
        this.fadeScaleY = f7;
        this.fadeInPara = f8;
        this.fadeOut = f9;
        this.fadeOutPara = f10;
        this.standardWidth = f11;
        this.standardHeight = f12;
    }
}
