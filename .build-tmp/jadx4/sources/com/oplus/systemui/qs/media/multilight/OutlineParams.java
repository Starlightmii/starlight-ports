package com.oplus.systemui.qs.media.multilight;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OutlineParams {
    public final float angle;
    public final float rectLineAlphaX;
    public final float rectLineAlphaY;
    public final float rectLineColorPara;
    public final float rectLineFadeW;
    public final float rectLineFadeX;
    public final float rectLineFadeY;
    public final float rectLineFadeZ;
    public final float rectLineFarX;
    public final float rectLineFarY;
    public final float rectLineNearX;
    public final float rectLineNearY;
    public final float rectLinePara;
    public final float standardHeight;
    public final float standardWidth;
    public static final Companion Companion = new Companion();
    private static final OutlineParams LEFT = new OutlineParams(0.01f, 0.01f, 0.5f, 1.0f, 0.1f, 0.3f, 0.01f, 0.01f, 0.746f, 0.8f);
    private static final OutlineParams TOP = new OutlineParams(0.8f, 2.6f, 0.46f, 0.3f, 0.4f, 0.05f, 0.4f, 0.05f, 0.0f, 0.67f);
    private static final OutlineParams RIGHT = new OutlineParams(0.0f, 0.0f, 0.5f, 1.0f, 0.1f, 0.3f, 0.01f, 0.01f, 0.247f, 0.8f);
    private static final OutlineParams BOTTOM = new OutlineParams(0.8f, 2.6f, 0.46f, 0.3f, 0.4f, 0.05f, 0.4f, 0.05f, 0.5f, 0.67f);
    private static final OutlineParams FULL = new OutlineParams(0.01f, 0.01f, 0.5f, 0.0f, 1.5f, 0.3f, 0.01f, 0.01f, 0.0f, 0.8f);
    private static final OutlineParams MEDIA_ONE_LIGHT = new OutlineParams(3.0f, 20.0f, 2.0f, 2.0f, 0.5f, 0.2f, 0.08f, 0.33f, 0.08f, 0.25f, 0.5f, 0.6f, 0.3f, 483.0f, 483.0f);
    private static final OutlineParams MEDIA_ONE_NIGHT = new OutlineParams(3.0f, 20.0f, 2.0f, 2.0f, 0.3f, 0.2f, 0.08f, 0.33f, 0.08f, 0.25f, 0.5f, 0.6f, 0.3f, 483.0f, 483.0f);
    private static final OutlineParams MEDIA_TWO = new OutlineParams(1.0f, 14.0f, 2.0f, 8.0f, 0.3f, 0.3f, 0.1f, 0.25f, 0.11f, 0.22f, 0.5f, 0.7f, 0.0f, 1104.0f, 1990.0f);
    private static final OutlineParams MEDIA_TWO_DEFAULT_LIGHT = new OutlineParams(1.0f, 14.0f, 2.0f, 8.0f, 0.2f, 0.12f, 0.1f, 0.25f, 0.11f, 0.22f, 0.5f, 0.7f, 0.0f, 936.0f, 1695.0f);
    private static final OutlineParams MEDIA_TWO_DEFAULT_NIGHT = new OutlineParams(1.0f, 14.0f, 2.0f, 8.0f, 0.1f, 0.06f, 0.14f, 0.25f, 0.11f, 0.22f, 0.5f, 0.7f, 0.0f, 936.0f, 1695.0f);

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
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public final OutlineParams fromPosition(PositionEnum positionEnum) {
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
            if (i != 3) {
                return i != 4 ? getFULL() : getBOTTOM();
            }
            return getRIGHT();
        }

        public final OutlineParams getBOTTOM() {
            return OutlineParams.BOTTOM;
        }

        public final OutlineParams getFULL() {
            return OutlineParams.FULL;
        }

        public final OutlineParams getLEFT() {
            return OutlineParams.LEFT;
        }

        public final OutlineParams getMEDIA_ONE_LIGHT() {
            return OutlineParams.MEDIA_ONE_LIGHT;
        }

        public final OutlineParams getMEDIA_ONE_NIGHT() {
            return OutlineParams.MEDIA_ONE_NIGHT;
        }

        public final OutlineParams getMEDIA_TWO() {
            return OutlineParams.MEDIA_TWO;
        }

        public final OutlineParams getMEDIA_TWO_DEFAULT_LIGHT() {
            return OutlineParams.MEDIA_TWO_DEFAULT_LIGHT;
        }

        public final OutlineParams getMEDIA_TWO_DEFAULT_NIGHT() {
            return OutlineParams.MEDIA_TWO_DEFAULT_NIGHT;
        }

        public final OutlineParams getRIGHT() {
            return OutlineParams.RIGHT;
        }

        public final OutlineParams getTOP() {
            return OutlineParams.TOP;
        }
    }

    public /* synthetic */ OutlineParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this(-2.6f, 20.0f, f, f2, f3, f4, f5, f6, f7, f8, f9, f10, 0.0f, 1148.0f, 300.0f);
    }

    public final void applyTo(MultiLightShaderParams multiLightShaderParams) {
        multiLightShaderParams.setRectLineNear(Float.valueOf(this.rectLineNearX), Float.valueOf(this.rectLineNearY));
        multiLightShaderParams.setRectLineFar(Float.valueOf(this.rectLineFarX), Float.valueOf(this.rectLineFarY));
        multiLightShaderParams.setRectLineAlpha(Float.valueOf(this.rectLineAlphaX), Float.valueOf(this.rectLineAlphaY));
        multiLightShaderParams.setRectLineFade(Float.valueOf(this.rectLineFadeX), Float.valueOf(this.rectLineFadeY), Float.valueOf(this.rectLineFadeZ), Float.valueOf(this.rectLineFadeW));
        multiLightShaderParams.setRectLinePara(this.rectLinePara);
        multiLightShaderParams.setAngle(this.angle);
        multiLightShaderParams.setRectLineColorPara(this.rectLineColorPara);
    }

    public final OutlineParams copy(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        return new OutlineParams(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OutlineParams)) {
            return false;
        }
        OutlineParams outlineParams = (OutlineParams) obj;
        return Float.compare(this.rectLineNearX, outlineParams.rectLineNearX) == 0 && Float.compare(this.rectLineNearY, outlineParams.rectLineNearY) == 0 && Float.compare(this.rectLineFarX, outlineParams.rectLineFarX) == 0 && Float.compare(this.rectLineFarY, outlineParams.rectLineFarY) == 0 && Float.compare(this.rectLineAlphaX, outlineParams.rectLineAlphaX) == 0 && Float.compare(this.rectLineAlphaY, outlineParams.rectLineAlphaY) == 0 && Float.compare(this.rectLineFadeX, outlineParams.rectLineFadeX) == 0 && Float.compare(this.rectLineFadeY, outlineParams.rectLineFadeY) == 0 && Float.compare(this.rectLineFadeZ, outlineParams.rectLineFadeZ) == 0 && Float.compare(this.rectLineFadeW, outlineParams.rectLineFadeW) == 0 && Float.compare(this.angle, outlineParams.angle) == 0 && Float.compare(this.rectLinePara, outlineParams.rectLinePara) == 0 && Float.compare(this.rectLineColorPara, outlineParams.rectLineColorPara) == 0 && Float.compare(this.standardWidth, outlineParams.standardWidth) == 0 && Float.compare(this.standardHeight, outlineParams.standardHeight) == 0;
    }

    public final float getAngle() {
        return this.angle;
    }

    public final float getRectLineAlphaX() {
        return this.rectLineAlphaX;
    }

    public final float getRectLineAlphaY() {
        return this.rectLineAlphaY;
    }

    public final float getRectLineColorPara() {
        return this.rectLineColorPara;
    }

    public final float getRectLineFadeW() {
        return this.rectLineFadeW;
    }

    public final float getRectLineFadeX() {
        return this.rectLineFadeX;
    }

    public final float getRectLineFadeY() {
        return this.rectLineFadeY;
    }

    public final float getRectLineFadeZ() {
        return this.rectLineFadeZ;
    }

    public final float getRectLineFarX() {
        return this.rectLineFarX;
    }

    public final float getRectLineFarY() {
        return this.rectLineFarY;
    }

    public final float getRectLineNearX() {
        return this.rectLineNearX;
    }

    public final float getRectLineNearY() {
        return this.rectLineNearY;
    }

    public final float getRectLinePara() {
        return this.rectLinePara;
    }

    public final float getStandardHeight() {
        return this.standardHeight;
    }

    public final float getStandardWidth() {
        return this.standardWidth;
    }

    public final int hashCode() {
        return Float.hashCode(this.standardHeight) + ChangeSize$.ExternalSyntheticOutline0.m(this.standardWidth, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineColorPara, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLinePara, ChangeSize$.ExternalSyntheticOutline0.m(this.angle, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineFadeW, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineFadeZ, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineFadeY, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineFadeX, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineAlphaY, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineAlphaX, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineFarY, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineFarX, ChangeSize$.ExternalSyntheticOutline0.m(this.rectLineNearY, Float.hashCode(this.rectLineNearX) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("OutlineParams(rectLineNearX=", this.rectLineNearX, ", rectLineNearY=", this.rectLineNearY, ", rectLineFarX=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.rectLineFarX, ", rectLineFarY=", this.rectLineFarY, ", rectLineAlphaX=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.rectLineAlphaX, ", rectLineAlphaY=", this.rectLineAlphaY, ", rectLineFadeX=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.rectLineFadeX, ", rectLineFadeY=", this.rectLineFadeY, ", rectLineFadeZ=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.rectLineFadeZ, ", rectLineFadeW=", this.rectLineFadeW, ", angle=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.angle, ", rectLinePara=", this.rectLinePara, ", rectLineColorPara=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.rectLineColorPara, ", standardWidth=", this.standardWidth, ", standardHeight=");
        return DpCornerSize$.ExternalSyntheticOutline0.m(this.standardHeight, ")", sbM);
    }

    public OutlineParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        this.rectLineNearX = f;
        this.rectLineNearY = f2;
        this.rectLineFarX = f3;
        this.rectLineFarY = f4;
        this.rectLineAlphaX = f5;
        this.rectLineAlphaY = f6;
        this.rectLineFadeX = f7;
        this.rectLineFadeY = f8;
        this.rectLineFadeZ = f9;
        this.rectLineFadeW = f10;
        this.angle = f11;
        this.rectLinePara = f12;
        this.rectLineColorPara = f13;
        this.standardWidth = f14;
        this.standardHeight = f15;
    }
}
