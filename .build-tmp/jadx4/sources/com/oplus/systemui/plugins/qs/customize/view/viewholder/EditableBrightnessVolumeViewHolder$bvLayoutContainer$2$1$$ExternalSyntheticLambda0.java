package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.view.OplusSmoothRoundedManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.view.SmoothCornersGradientDrawable;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1$$ExternalSyntheticLambda0 implements Function2 {
    public /* synthetic */ EditableBrightnessVolumeViewHolder f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1 f$2;
    public /* synthetic */ CellCalculatorManager.LayoutConstraint f$3;

    public final Object invoke(Object obj, Object obj2) {
        float capsuleWidth;
        EditableBrightnessVolumeViewHolder editableBrightnessVolumeViewHolder = this.f$0;
        Context context = this.f$1;
        EditableBrightnessVolumeViewHolder$bvLayoutContainer$2$1 editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1 = this.f$2;
        CellCalculatorManager.LayoutConstraint layoutConstraint = this.f$3;
        float fFloatValue = ((Float) obj).floatValue();
        OplusRoundParams oplusRoundParams = (OplusRoundParams) obj2;
        OplusQsSmoothRoundUtil oplusQsSmoothRoundUtil = OplusQsSmoothRoundUtil.INSTANCE;
        boolean zIsSmoothRadiusOn = OplusSmoothRoundedManager.isSmoothRadiusOn();
        View view = editableBrightnessVolumeViewHolder.strokeBgView;
        if (view == null) {
            view = null;
        }
        SmoothCornersGradientDrawable smoothCornersGradientDrawable = (SmoothCornersGradientDrawable) view.getBackground();
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, fFloatValue);
        if (roundParams == null || !zIsSmoothRadiusOn) {
            smoothCornersGradientDrawable.setSmoothRound(fFloatValue, 0.0f);
        } else {
            smoothCornersGradientDrawable.setSmoothRound(roundParams.getRadius() + 11.0f, roundParams.getWeight());
            editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1.lastWeight = roundParams.getWeight();
        }
        if (roundParams != null) {
            capsuleWidth = roundParams.getRadius();
        } else {
            capsuleWidth = layoutConstraint.getCapsuleWidth() / 2.0f;
            if (fFloatValue <= capsuleWidth) {
                capsuleWidth = fFloatValue;
            }
        }
        editableBrightnessVolumeViewHolder$bvLayoutContainer$2$1.lastRadius = capsuleWidth;
        LogUtil.i$default(LogUtil.TAG_QS, "EditableBrightnessVolumeViewHolder", "[init] radius = " + fFloatValue + ", smoothRadiusOn = " + zIsSmoothRadiusOn + ", roundParams = " + oplusRoundParams + ",outlineRoundParams = " + roundParams, null, 8);
        return Unit.INSTANCE;
    }
}
