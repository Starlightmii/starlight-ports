package com.oplusos.systemui.common.util;

import com.oplus.posteffect.agsl.ShaderBlendParam;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColorWithShader;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShaderBlendParamHelper {
    public static final ShaderBlendParamHelper INSTANCE = new ShaderBlendParamHelper();
    private static final Function2 colorWithBlurAmount = new ShaderBlendParamHelper$$ExternalSyntheticLambda0();

    public static void update(ShaderBlendParam shaderBlendParam, int i, int i2) {
        shaderBlendParam.setMode(i);
        shaderBlendParam.setColor(i2);
    }

    public final void updateMixMultiShaderParams(BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader, float f) {
        MixColorWithShader backgroundShaderParam = blurMixMultiWithShader.getBackgroundShaderParam();
        MixColorWithShader foregroundShaderParam = blurMixMultiWithShader.getForegroundShaderParam();
        if (blurMixMultiWithShader.getMixMultiShaderParams().size() != 4) {
            ArrayList<ShaderBlendParam> mixMultiShaderParams = blurMixMultiWithShader.getMixMultiShaderParams();
            mixMultiShaderParams.clear();
            int topMode = backgroundShaderParam.getTopMode();
            Function2 function2 = colorWithBlurAmount;
            mixMultiShaderParams.add(new ShaderBlendParam(topMode, ((Number) function2.invoke(Integer.valueOf(backgroundShaderParam.getTopLayerColor()), Float.valueOf(f))).intValue()));
            mixMultiShaderParams.add(new ShaderBlendParam(backgroundShaderParam.getBottomMode(), ((Number) function2.invoke(Integer.valueOf(backgroundShaderParam.getBottomLayerColor()), Float.valueOf(f))).intValue()));
            mixMultiShaderParams.add(new ShaderBlendParam(foregroundShaderParam.getTopMode(), foregroundShaderParam.getTopLayerColor()));
            mixMultiShaderParams.add(new ShaderBlendParam(foregroundShaderParam.getBottomMode(), foregroundShaderParam.getBottomLayerColor()));
            return;
        }
        ArrayList<ShaderBlendParam> mixMultiShaderParams2 = blurMixMultiWithShader.getMixMultiShaderParams();
        ShaderBlendParamHelper shaderBlendParamHelper = INSTANCE;
        ShaderBlendParam shaderBlendParam = mixMultiShaderParams2.get(0);
        int topMode2 = backgroundShaderParam.getTopMode();
        Function2 function3 = colorWithBlurAmount;
        int iIntValue = ((Number) function3.invoke(Integer.valueOf(backgroundShaderParam.getTopLayerColor()), Float.valueOf(f))).intValue();
        shaderBlendParamHelper.getClass();
        update(shaderBlendParam, topMode2, iIntValue);
        update(mixMultiShaderParams2.get(1), backgroundShaderParam.getBottomMode(), ((Number) function3.invoke(Integer.valueOf(backgroundShaderParam.getBottomLayerColor()), Float.valueOf(f))).intValue());
        update(mixMultiShaderParams2.get(2), foregroundShaderParam.getTopMode(), foregroundShaderParam.getTopLayerColor());
        update(mixMultiShaderParams2.get(3), foregroundShaderParam.getBottomMode(), foregroundShaderParam.getBottomLayerColor());
    }

    public final void updateMixSingleShaderParams(BlurMixConfig.BlurMixSingleWithShader blurMixSingleWithShader, float f) {
        MixColorWithShader backgroundShaderParam = blurMixSingleWithShader.getBackgroundShaderParam();
        if (blurMixSingleWithShader.getMixSingleShaderParams().size() != 2) {
            ArrayList<ShaderBlendParam> mixSingleShaderParams = blurMixSingleWithShader.getMixSingleShaderParams();
            mixSingleShaderParams.clear();
            int topMode = backgroundShaderParam.getTopMode();
            Function2 function2 = colorWithBlurAmount;
            mixSingleShaderParams.add(new ShaderBlendParam(topMode, ((Number) function2.invoke(Integer.valueOf(backgroundShaderParam.getTopLayerColor()), Float.valueOf(f))).intValue()));
            mixSingleShaderParams.add(new ShaderBlendParam(backgroundShaderParam.getBottomMode(), ((Number) function2.invoke(Integer.valueOf(backgroundShaderParam.getBottomLayerColor()), Float.valueOf(f))).intValue()));
            return;
        }
        ArrayList<ShaderBlendParam> mixSingleShaderParams2 = blurMixSingleWithShader.getMixSingleShaderParams();
        ShaderBlendParamHelper shaderBlendParamHelper = INSTANCE;
        ShaderBlendParam shaderBlendParam = mixSingleShaderParams2.get(0);
        int topMode2 = backgroundShaderParam.getTopMode();
        Function2 function3 = colorWithBlurAmount;
        int iIntValue = ((Number) function3.invoke(Integer.valueOf(backgroundShaderParam.getTopLayerColor()), Float.valueOf(f))).intValue();
        shaderBlendParamHelper.getClass();
        update(shaderBlendParam, topMode2, iIntValue);
        update(mixSingleShaderParams2.get(1), backgroundShaderParam.getBottomMode(), ((Number) function3.invoke(Integer.valueOf(backgroundShaderParam.getBottomLayerColor()), Float.valueOf(f))).intValue());
    }

    public final void updateOverlayColorShaderParams(BlurMixConfig.OverlayColor overlayColor, int i) {
        if (overlayColor.getMixOverlayColorShaderParams().size() != 1) {
            ArrayList<ShaderBlendParam> mixOverlayColorShaderParams = overlayColor.getMixOverlayColorShaderParams();
            mixOverlayColorShaderParams.clear();
            mixOverlayColorShaderParams.add(new ShaderBlendParam(0, i));
        } else {
            ArrayList<ShaderBlendParam> mixOverlayColorShaderParams2 = overlayColor.getMixOverlayColorShaderParams();
            ShaderBlendParamHelper shaderBlendParamHelper = INSTANCE;
            ShaderBlendParam shaderBlendParam = mixOverlayColorShaderParams2.get(0);
            shaderBlendParamHelper.getClass();
            update(shaderBlendParam, 0, i);
        }
    }
}
