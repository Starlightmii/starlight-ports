package com.oplusos.systemui.common.blurability;

import androidx.car.app.serialization.Bundler$;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplusos.systemui.common.olivelink.AndroidColor;
import com.oplusos.systemui.common.olivelink.Scalar;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class StrokeLineGroup extends OLiveLinkGroup {
    private final Scalar alpha;
    private final Scalar ratio;
    private final Scalar strokeLineAlphaFar;
    private final Scalar strokeLineAlphaNear;
    private final AndroidColor strokeLineColor;
    private final Scalar strokeLineMix;
    private final Scalar strokeLinePow;
    private final Scalar strokeLineTransverseFarFade;
    private final Scalar strokeLineTransverseFarSolid;
    private final Scalar strokeLineTransverseNearFade;
    private final Scalar strokeLineTransverseNearSolid;
    private final Scalar strokeLineVerticalFarFade;
    private final Scalar strokeLineVerticalFarSolid;
    private final Scalar strokeLineVerticalNearFade;
    private final Scalar strokeLineVerticalNearSolid;

    public StrokeLineGroup(String str, String str2, GradientStrokeLineParams gradientStrokeLineParams, float f) {
        super(str, str2);
        this.alpha = scalar(Bundler$.ExternalSyntheticOutline0.m("00-", str2, ":描边整体alpha"), f, "描边整体alpha");
        this.strokeLineColor = color(Bundler$.ExternalSyntheticOutline0.m("01-", str2, ":strokeLineColor"), gradientStrokeLineParams.getStrokeLineColor(), "描边颜色");
        this.strokeLineVerticalNearSolid = scalar(Bundler$.ExternalSyntheticOutline0.m("02-", str2, ":strokeLineVerticalNearSolid"), gradientStrokeLineParams.getStrokeLineVerticalNearSolid(), "纵向·顶部·实部描边粗细");
        this.strokeLineVerticalNearFade = scalar(Bundler$.ExternalSyntheticOutline0.m("03-", str2, ":strokeLineVerticalNearFade"), gradientStrokeLineParams.getStrokeLineVerticalNearFade(), "纵向·顶部·虚部描边粗细");
        this.strokeLineVerticalFarSolid = scalar(Bundler$.ExternalSyntheticOutline0.m("04-", str2, ":strokeLineVerticalFarSolid"), gradientStrokeLineParams.getStrokeLineVerticalFarSolid(), "纵向·底部·实部描边粗细");
        this.strokeLineVerticalFarFade = scalar(Bundler$.ExternalSyntheticOutline0.m("05-", str2, ":strokeLineVerticalFarFade"), gradientStrokeLineParams.getStrokeLineVerticalFarFade(), "纵向·底部·虚部描边粗细");
        this.strokeLineAlphaNear = scalar(Bundler$.ExternalSyntheticOutline0.m("06-", str2, ":strokeLineAlphaNear"), gradientStrokeLineParams.getStrokeLineAlphaNear(), "顶部描边Alpha");
        this.strokeLineAlphaFar = scalar(Bundler$.ExternalSyntheticOutline0.m("07-", str2, ":strokeLineAlphaFar"), gradientStrokeLineParams.getStrokeLineAlphaFar(), "底部描边Alpha");
        this.strokeLineTransverseNearSolid = scalar(Bundler$.ExternalSyntheticOutline0.m("08-", str2, ":strokeLineTransverseNearSolid"), gradientStrokeLineParams.getStrokeLineTransverseNearSolid(), "横向·顶部·实部描边长度占周长比例");
        this.strokeLineTransverseNearFade = scalar(Bundler$.ExternalSyntheticOutline0.m("09-", str2, ":strokeLineTransverseNearFade"), gradientStrokeLineParams.getStrokeLineTransverseNearFade(), "横向·顶部·虚部描边长度占周长比例");
        this.strokeLineTransverseFarSolid = scalar(Bundler$.ExternalSyntheticOutline0.m("10-", str2, ":strokeLineTransverseFarSolid"), gradientStrokeLineParams.getStrokeLineTransverseFarSolid(), "横向·底部·实部描边长度占周长比例");
        this.strokeLineTransverseFarFade = scalar(Bundler$.ExternalSyntheticOutline0.m("11-", str2, ":strokeLineTransverseFarFade"), gradientStrokeLineParams.getStrokeLineTransverseFarFade(), "横向·底部·虚部描边长度占周长比例");
        this.ratio = scalar(Bundler$.ExternalSyntheticOutline0.m("12-", str2, ":ratio"), gradientStrokeLineParams.getRatio(), "旋转角度");
        this.strokeLinePow = scalar(Bundler$.ExternalSyntheticOutline0.m("13-", str2, ":strokeLinePow"), gradientStrokeLineParams.getStrokeLinePow(), "渐变Pow");
        this.strokeLineMix = scalar(Bundler$.ExternalSyntheticOutline0.m("14-", str2, ":strokeLineMix"), gradientStrokeLineParams.getStrokeLineMix(), "渐变Mix");
    }

    public final GradientStrokeLineParams createStrokeLineParams() {
        float fFloatValue = this.alpha.get().floatValue();
        return new GradientStrokeLineParams(this.strokeLineColor.get(), this.strokeLineVerticalNearSolid.get().floatValue(), this.strokeLineVerticalNearFade.get().floatValue(), this.strokeLineVerticalFarSolid.get().floatValue(), this.strokeLineVerticalFarFade.get().floatValue(), this.strokeLineAlphaNear.get().floatValue() * fFloatValue, this.strokeLineAlphaFar.get().floatValue() * fFloatValue, this.strokeLineTransverseNearSolid.get().floatValue(), this.strokeLineTransverseNearFade.get().floatValue(), this.strokeLineTransverseFarSolid.get().floatValue(), this.strokeLineTransverseFarFade.get().floatValue(), this.ratio.get().floatValue(), this.strokeLinePow.get().floatValue(), this.strokeLineMix.get().floatValue());
    }

    public final Scalar getAlpha() {
        return this.alpha;
    }

    public final Scalar getRatio() {
        return this.ratio;
    }

    public final Scalar getStrokeLineAlphaFar() {
        return this.strokeLineAlphaFar;
    }

    public final Scalar getStrokeLineAlphaNear() {
        return this.strokeLineAlphaNear;
    }

    public final AndroidColor getStrokeLineColor() {
        return this.strokeLineColor;
    }

    public final Scalar getStrokeLineMix() {
        return this.strokeLineMix;
    }

    public final Scalar getStrokeLinePow() {
        return this.strokeLinePow;
    }

    public final Scalar getStrokeLineTransverseFarFade() {
        return this.strokeLineTransverseFarFade;
    }

    public final Scalar getStrokeLineTransverseFarSolid() {
        return this.strokeLineTransverseFarSolid;
    }

    public final Scalar getStrokeLineTransverseNearFade() {
        return this.strokeLineTransverseNearFade;
    }

    public final Scalar getStrokeLineTransverseNearSolid() {
        return this.strokeLineTransverseNearSolid;
    }

    public final Scalar getStrokeLineVerticalFarFade() {
        return this.strokeLineVerticalFarFade;
    }

    public final Scalar getStrokeLineVerticalFarSolid() {
        return this.strokeLineVerticalFarSolid;
    }

    public final Scalar getStrokeLineVerticalNearFade() {
        return this.strokeLineVerticalNearFade;
    }

    public final Scalar getStrokeLineVerticalNearSolid() {
        return this.strokeLineVerticalNearSolid;
    }

    public void registerChangeListener(Object obj, Function0 function0) {
        this.strokeLineColor.registerChangeListener(obj, function0);
        this.strokeLineVerticalNearSolid.registerChangeListener(obj, function0);
        this.strokeLineVerticalNearFade.registerChangeListener(obj, function0);
        this.strokeLineTransverseNearSolid.registerChangeListener(obj, function0);
        this.strokeLineTransverseNearFade.registerChangeListener(obj, function0);
        this.strokeLineAlphaNear.registerChangeListener(obj, function0);
        this.alpha.registerChangeListener(obj, function0);
        this.strokeLineAlphaFar.registerChangeListener(obj, function0);
        this.strokeLineVerticalFarSolid.registerChangeListener(obj, function0);
        this.strokeLineVerticalFarFade.registerChangeListener(obj, function0);
        this.strokeLineTransverseFarSolid.registerChangeListener(obj, function0);
        this.strokeLineTransverseFarFade.registerChangeListener(obj, function0);
        this.ratio.registerChangeListener(obj, function0);
        this.strokeLinePow.registerChangeListener(obj, function0);
        this.strokeLineMix.registerChangeListener(obj, function0);
    }
}
