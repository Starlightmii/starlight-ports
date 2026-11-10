package com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator;

import android.content.Context;
import android.graphics.PointF;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NonRubberBandBezierCalculator extends BaseBezierCalculator {
    public NonRubberBandBezierCalculator(Context context, int i, int i2, SideGestureConfigurationEx sideGestureConfigurationEx, BezierCalculationCallback bezierCalculationCallback) {
        super(context, i, i2, sideGestureConfigurationEx, bezierCalculationCallback);
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public PointF calculateTargetPoint(PointF pointF) {
        PointF pointF2 = new PointF();
        pointF2.y = getConfiguration().boundToRange(MathKt.roundToInt(pointF.y), getConfiguration().getAnimRadius(), getScreenHeight() - getConfiguration().getAnimRadius());
        if (getPosition() == 0) {
            pointF2.x = getConfiguration().boundToRange(MathKt.roundToInt(pointF.x), 0, getConfiguration().getAnimSwitchMaxDistanceX());
            return pointF2;
        }
        if (getPosition() == 1) {
            pointF2.x = getConfiguration().boundToRange(MathKt.roundToInt(pointF.x), getScreenWidth() - getConfiguration().getAnimSwitchMaxDistanceX(), getScreenWidth());
        }
        return pointF2;
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public void gestureFinished(PointF pointF) {
        calculateTargetPoint(pointF);
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public final void initAnim() {
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public final void reset() {
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public void updateTargetPoint(PointF pointF) {
        getCurrentPoint().x = pointF.x;
        getCurrentPoint().y = pointF.y;
        updateView();
    }
}
