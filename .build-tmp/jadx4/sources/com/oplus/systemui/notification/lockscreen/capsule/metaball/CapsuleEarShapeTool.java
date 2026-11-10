package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.util.Log;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.posteffect.params.ClipShader;
import com.oplus.posteffect.params.ClipShaderParams;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleEarShapeTool {
    public static final CapsuleEarShapeTool INSTANCE = new CapsuleEarShapeTool();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EarArcOrientation.values().length];
            try {
                EarArcOrientation earArcOrientation = EarArcOrientation.EAR_ARC_ORIENTATION_LEFT;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ClipShaderParams buildClipShaderParams(float[] fArr, boolean z) {
        return new ClipShaderParams(EarDrawableShaderStringKt.EAR_DRAWABLE_SHADER_UNIFORM_EAR_PARAMS, fArr, z);
    }

    public final float[] buildEarDrawableShaderParams(float f, EarArcOrientation earArcOrientation, float f2) {
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        int earWidth = capsuleConfig.getEarWidth();
        float defaultRadius = capsuleConfig.getDefaultRadius();
        float earEndHeightForCapsule = getEarEndHeightForCapsule();
        float f3 = earWidth;
        return EarDrawableShaderStringKt.buildEarParamsArray(new EarParams(f, new float[]{(f3 / earEndHeightForCapsule) * 2.0f, 1.0f}, new float[]{0.4f, 0.4f, 0.4f}, 1.143f, 0.0f, getArcAngle(earArcOrientation), new float[]{defaultRadius, f3 / 5.0f, 0.47f}, new float[]{defaultRadius, f3 / 2.15f, 0.47f}, new float[]{defaultRadius, earEndHeightForCapsule / 2.0f, 0.0f}, new float[]{getArcPos(earArcOrientation, f2), 0.0f}));
    }

    public final float getArcAngle(EarArcOrientation earArcOrientation) {
        return WhenMappings.$EnumSwitchMapping$0[earArcOrientation.ordinal()] == 1 ? 0.0f : 3.14f;
    }

    public final float getArcPos(EarArcOrientation earArcOrientation, float f) {
        return WhenMappings.$EnumSwitchMapping$0[earArcOrientation.ordinal()] == 1 ? f : (-1.0f) * f;
    }

    public final int getCapsuleStartHeight() {
        return (int) (CapsuleConfig.INSTANCE.getItemHeight() * 0.8f);
    }

    public final int getEarEndHeightForCapsule() {
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        int itemWidthMultiCard = capsuleConfig.getItemWidthMultiCard();
        int itemHeight = capsuleConfig.getItemHeight();
        int capsuleStartHeight = getCapsuleStartHeight();
        return (int) ((itemWidthMultiCard * capsuleStartHeight) / ((itemWidthMultiCard - itemHeight) + capsuleStartHeight));
    }

    public final Pair<ClipShader, ClipShaderParams> getInitEarShapeParams(boolean z) {
        return new Pair<>(new ClipShader(EarDrawableShaderStringKt.getEarShaderHead(), EarDrawableShaderStringKt.getEarShaderMethod(), EarDrawableShaderStringKt.getEarShaderMainMethod(), "EarShape"), buildClipShaderParams(buildEarDrawableShaderParams(0.3f, z ? EarArcOrientation.EAR_ARC_ORIENTATION_RIGHT : EarArcOrientation.EAR_ARC_ORIENTATION_LEFT, 0.0f), false));
    }

    public final void initMetaBallParams(MetaBallBlurDrawable metaBallBlurDrawable, float f, boolean z) {
        metaBallBlurDrawable.setMainBallIndex(1);
        metaBallBlurDrawable.setBlendMode(0);
        metaBallBlurDrawable.setCornerParams(SmoothRoundEx.INSTANCE.createCapsuleCornerParams(CapsuleConfig.INSTANCE.getDefaultRadius()));
        Pair<ClipShader, ClipShaderParams> initEarShapeParams = INSTANCE.getInitEarShapeParams(z);
        metaBallBlurDrawable.setCustomClipShader((ClipShader) initEarShapeParams.getFirst(), (ClipShaderParams) initEarShapeParams.getSecond());
    }

    public final void updateEarShape(MetaBallBlurDrawable metaBallBlurDrawable, int i, float f, boolean z) {
        CapsuleEarShapeTool capsuleEarShapeTool = INSTANCE;
        Pair<ClipShader, ClipShaderParams> initEarShapeParams = capsuleEarShapeTool.getInitEarShapeParams(z);
        metaBallBlurDrawable.setCornerParams(SmoothRoundEx.INSTANCE.createCornerParams(0.0f, false));
        ClipShaderParams clipShaderParams = (ClipShaderParams) initEarShapeParams.getSecond();
        float f2 = i * 0.5f;
        EarArcOrientation earArcOrientation = EarArcOrientation.EAR_ARC_ORIENTATION_LEFT;
        EarArcOrientation earArcOrientation2 = z ? EarArcOrientation.EAR_ARC_ORIENTATION_RIGHT : EarArcOrientation.EAR_ARC_ORIENTATION_LEFT;
        clipShaderParams.setEnableClipShape(true);
        clipShaderParams.setParamsArray(capsuleEarShapeTool.buildEarDrawableShaderParams(f, earArcOrientation2, f2));
        Log.d("CapsuleEarShapeTool", "updateEarShape earWidth: " + i + " process:" + f);
        Log.d("CapsuleEarShapeTool", "drawable ear to " + earArcOrientation2 + " updateParams:" + clipShaderParams);
        metaBallBlurDrawable.setCustomClipShaderParams(clipShaderParams);
    }
}
