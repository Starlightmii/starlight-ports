package com.oplusos.systemui.common.blurability;

import androidx.car.app.serialization.Bundler$;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplusos.systemui.common.olivelink.AndroidColor;
import com.oplusos.systemui.common.olivelink.Scalar;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class InnerShadowGroup extends OLiveLinkGroup {
    private final Scalar alpha;
    private final Scalar noOffsetShadowAlpha;
    private final Scalar noOffsetShadowMix;
    private final Scalar noOffsetShadowPow;
    private final Scalar noOffsetShadowRange;
    private final Scalar offsetShadowMaxAlpha;
    private final Scalar offsetShadowMinAlpha;
    private final Scalar offsetShadowMix;
    private final Scalar offsetShadowPow;
    private final Scalar offsetShadowRange;
    private final Scalar offsetShadowScaleX;
    private final Scalar offsetShadowScaleY;
    private final Scalar offsetShadowX;
    private final Scalar offsetShadowY;
    private final AndroidColor shadowColor;

    public InnerShadowGroup(String str, String str2, InnerShadowParams innerShadowParams, float f) {
        super(str, str2);
        this.alpha = scalar(Bundler$.ExternalSyntheticOutline0.m("00-", str2, ":内阴影整体alpha"), f, "内阴影整体alpha");
        this.shadowColor = color(Bundler$.ExternalSyntheticOutline0.m("01-", str2, ":shadowColor"), innerShadowParams.getShadowColor(), "阴影颜色");
        this.offsetShadowMaxAlpha = scalar(Bundler$.ExternalSyntheticOutline0.m("02-", str2, ":offsetShadowMaxAlpha"), innerShadowParams.getOffsetShadowMaxAlpha(), "有偏移内阴影：最强强度，边缘区域的alpha值");
        this.offsetShadowMinAlpha = scalar(Bundler$.ExternalSyntheticOutline0.m("03-", str2, ":offsetShadowMinAlpha"), innerShadowParams.getOffsetShadowMinAlpha(), "有偏移内阴影：最弱强度，中心区域的alpha值");
        this.noOffsetShadowAlpha = scalar(Bundler$.ExternalSyntheticOutline0.m("04-", str2, ":noOffsetShadowAlpha"), innerShadowParams.getNoOffsetShadowAlpha(), "无偏移阴影强度Alpha");
        this.offsetShadowRange = scalar(Bundler$.ExternalSyntheticOutline0.m("05-", str2, ":offsetShadowRange"), innerShadowParams.getOffsetShadowRange(), "有偏移·内阴影的作用范围");
        this.offsetShadowX = scalar(Bundler$.ExternalSyntheticOutline0.m("06-", str2, ":offsetShadowX"), innerShadowParams.getOffsetShadowX(), "有偏移·内阴影在X上的偏移量");
        this.offsetShadowY = scalar(Bundler$.ExternalSyntheticOutline0.m("07-", str2, ":offsetShadowY"), innerShadowParams.getOffsetShadowY(), "有偏移·内阴影在Y上的偏移量");
        this.offsetShadowScaleX = scalar(Bundler$.ExternalSyntheticOutline0.m("08-", str2, ":offsetShadowScaleX"), innerShadowParams.getOffsetShadowScaleX(), "有偏移·内阴影在X上的缩放");
        this.offsetShadowScaleY = scalar(Bundler$.ExternalSyntheticOutline0.m("09-", str2, ":offsetShadowScaleY"), innerShadowParams.getOffsetShadowScaleY(), "有偏移·内阴影在Y上的缩放");
        this.offsetShadowPow = scalar(Bundler$.ExternalSyntheticOutline0.m("10-", str2, ":offsetShadowPow"), innerShadowParams.getOffsetShadowPow(), "调节有偏移阴影渐变POW");
        this.offsetShadowMix = scalar(Bundler$.ExternalSyntheticOutline0.m("11-", str2, ":offsetShadowMix"), innerShadowParams.getOffsetShadowMix(), "调节有偏移阴影渐变Mix");
        this.noOffsetShadowRange = scalar(Bundler$.ExternalSyntheticOutline0.m("12-", str2, ":noOffsetShadowRange"), innerShadowParams.getNoOffsetShadowRange(), "无偏移·内阴影的作用范围");
        this.noOffsetShadowPow = scalar(Bundler$.ExternalSyntheticOutline0.m("13-", str2, ":noOffsetShadowPow"), innerShadowParams.getNoOffsetShadowPow(), "调节无偏移阴影渐变POW");
        this.noOffsetShadowMix = scalar(Bundler$.ExternalSyntheticOutline0.m("14-", str2, ":noOffsetShadowMix"), innerShadowParams.getNoOffsetShadowMix(), "调节无偏移阴影渐变·Mix");
    }

    public final InnerShadowParams createInnerShadowParams() {
        float fFloatValue = this.alpha.get().floatValue();
        return new InnerShadowParams(this.shadowColor.get(), this.offsetShadowMaxAlpha.get().floatValue() * fFloatValue, this.offsetShadowMinAlpha.get().floatValue() * fFloatValue, this.noOffsetShadowAlpha.get().floatValue() * fFloatValue, this.offsetShadowRange.get().floatValue(), this.offsetShadowX.get().floatValue(), this.offsetShadowY.get().floatValue(), this.offsetShadowScaleX.get().floatValue(), this.offsetShadowScaleY.get().floatValue(), this.offsetShadowPow.get().floatValue(), this.offsetShadowMix.get().floatValue(), this.noOffsetShadowRange.get().floatValue(), this.noOffsetShadowPow.get().floatValue(), this.noOffsetShadowMix.get().floatValue());
    }

    public final Scalar getAlpha() {
        return this.alpha;
    }

    public final Scalar getNoOffsetShadowAlpha() {
        return this.noOffsetShadowAlpha;
    }

    public final Scalar getNoOffsetShadowMix() {
        return this.noOffsetShadowMix;
    }

    public final Scalar getNoOffsetShadowPow() {
        return this.noOffsetShadowPow;
    }

    public final Scalar getNoOffsetShadowRange() {
        return this.noOffsetShadowRange;
    }

    public final Scalar getOffsetShadowMaxAlpha() {
        return this.offsetShadowMaxAlpha;
    }

    public final Scalar getOffsetShadowMinAlpha() {
        return this.offsetShadowMinAlpha;
    }

    public final Scalar getOffsetShadowMix() {
        return this.offsetShadowMix;
    }

    public final Scalar getOffsetShadowPow() {
        return this.offsetShadowPow;
    }

    public final Scalar getOffsetShadowRange() {
        return this.offsetShadowRange;
    }

    public final Scalar getOffsetShadowScaleX() {
        return this.offsetShadowScaleX;
    }

    public final Scalar getOffsetShadowScaleY() {
        return this.offsetShadowScaleY;
    }

    public final Scalar getOffsetShadowX() {
        return this.offsetShadowX;
    }

    public final Scalar getOffsetShadowY() {
        return this.offsetShadowY;
    }

    public final AndroidColor getShadowColor() {
        return this.shadowColor;
    }

    public void registerChangeListener(Object obj, Function0 function0) {
        this.alpha.registerChangeListener(obj, function0);
        this.shadowColor.registerChangeListener(obj, function0);
        this.offsetShadowMaxAlpha.registerChangeListener(obj, function0);
        this.offsetShadowMinAlpha.registerChangeListener(obj, function0);
        this.noOffsetShadowAlpha.registerChangeListener(obj, function0);
        this.offsetShadowRange.registerChangeListener(obj, function0);
        this.offsetShadowX.registerChangeListener(obj, function0);
        this.offsetShadowY.registerChangeListener(obj, function0);
        this.offsetShadowScaleX.registerChangeListener(obj, function0);
        this.offsetShadowScaleY.registerChangeListener(obj, function0);
        this.offsetShadowPow.registerChangeListener(obj, function0);
        this.offsetShadowMix.registerChangeListener(obj, function0);
        this.noOffsetShadowRange.registerChangeListener(obj, function0);
        this.noOffsetShadowPow.registerChangeListener(obj, function0);
        this.noOffsetShadowMix.registerChangeListener(obj, function0);
    }
}
