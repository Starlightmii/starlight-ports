package com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator;

import android.content.Context;
import android.graphics.PointF;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector;
import com.oplus.systemui.navigationbar.gesture.utils.RubberbandKt;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RubberBandBezierCalculator extends BaseBezierCalculator {
    public static final Companion Companion = new Companion();
    private COUISpringAnimation followHandXAnim;
    private COUISpringAnimation followHandYAnim;
    private final Spring springFollowHandConfig;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public RubberBandBezierCalculator(Context context, int i, int i2, SideGestureConfigurationEx sideGestureConfigurationEx, BezierCalculationCallback bezierCalculationCallback) {
        super(context, i, i2, sideGestureConfigurationEx, bezierCalculationCallback);
        this.springFollowHandConfig = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧滑手势弹性跟手Spring参数", 0.0f, 0.1f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public PointF calculateTargetPoint(PointF pointF) {
        PointF pointF2 = new PointF();
        float downY = pointF.y - getDownY();
        NavBarOliveLinkConnector navBarOliveLinkConnector = NavBarOliveLinkConnector.INSTANCE;
        pointF2.y = getConfiguration().boundToRange((int) (getDownY() + RubberbandKt.calculateRubberBand(navBarOliveLinkConnector.getRUBBER_BAND_RATIO_Y().get().floatValue(), downY, getConfiguration().getRubberbandMaxDistanceY())), getConfiguration().getAnimRadius() + ((int) (getScreenHeight() * 0.0f)), getScreenHeight() - getConfiguration().getAnimRadius());
        if (getPosition() == 0) {
            pointF2.x = RubberbandKt.calculateRubberBand(navBarOliveLinkConnector.getRUBBER_BAND_RATIO_X().get().floatValue(), pointF.x, getConfiguration().getRubberbandMaxDistance());
            return pointF2;
        }
        if (getPosition() == 1) {
            pointF2.x = getScreenWidth() - RubberbandKt.calculateRubberBand(navBarOliveLinkConnector.getRUBBER_BAND_RATIO_X().get().floatValue(), getScreenWidth() - pointF.x, getConfiguration().getRubberbandMaxDistance());
        }
        return pointF2;
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public void gestureFinished(PointF pointF) {
        COUISpringAnimation cOUISpringAnimation = this.followHandXAnim;
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        COUISpringAnimation cOUISpringAnimation2 = this.followHandYAnim;
        if (cOUISpringAnimation2 != null) {
            cOUISpringAnimation2.cancel();
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public final void initAnim() {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(this.springFollowHandConfig.bounce());
        cOUISpringForce.setResponse(this.springFollowHandConfig.response());
        cOUISpringAnimation.setEnableNonMainThread(true);
        cOUISpringAnimation.setSpring(cOUISpringForce);
        RubberBandBezierCalculator$initAnim$1$2 rubberBandBezierCalculator$initAnim$1$2 = new RubberBandBezierCalculator$initAnim$1$2(0);
        rubberBandBezierCalculator$initAnim$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(rubberBandBezierCalculator$initAnim$1$2);
        this.followHandYAnim = cOUISpringAnimation;
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce2 = new COUISpringForce();
        cOUISpringForce2.setBounce(this.springFollowHandConfig.bounce());
        cOUISpringForce2.setResponse(this.springFollowHandConfig.response());
        cOUISpringAnimation2.setEnableNonMainThread(true);
        cOUISpringAnimation2.setSpring(cOUISpringForce2);
        RubberBandBezierCalculator$initAnim$1$2 rubberBandBezierCalculator$initAnim$1$3 = new RubberBandBezierCalculator$initAnim$1$2(1);
        rubberBandBezierCalculator$initAnim$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(rubberBandBezierCalculator$initAnim$1$3);
        this.followHandXAnim = cOUISpringAnimation2;
        if (this.chainFromPreviousExit) {
            cOUISpringAnimation2.setStartValue(getCurrentPoint().x);
            COUISpringAnimation cOUISpringAnimation3 = this.followHandYAnim;
            if (cOUISpringAnimation3 != null) {
                cOUISpringAnimation3.setStartValue(getCurrentPoint().y);
                return;
            }
            return;
        }
        int position = getPosition();
        COUISpringAnimation cOUISpringAnimation4 = this.followHandXAnim;
        if (position == 0) {
            if (cOUISpringAnimation4 != null) {
                cOUISpringAnimation4.setStartValue(0.0f);
            }
        } else if (cOUISpringAnimation4 != null) {
            cOUISpringAnimation4.setStartValue(getScreenWidth());
        }
        COUISpringAnimation cOUISpringAnimation5 = this.followHandYAnim;
        if (cOUISpringAnimation5 != null) {
            cOUISpringAnimation5.setStartValue(getDownY());
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public final void reset() {
        COUISpringAnimation cOUISpringAnimation = this.followHandXAnim;
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        COUISpringAnimation cOUISpringAnimation2 = this.followHandYAnim;
        if (cOUISpringAnimation2 != null) {
            cOUISpringAnimation2.cancel();
        }
        this.followHandXAnim = null;
        this.followHandYAnim = null;
    }

    @Override // com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator
    public void updateTargetPoint(PointF pointF) {
        COUISpringAnimation cOUISpringAnimation = this.followHandXAnim;
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.animateToFinalPosition(pointF.x);
        }
        COUISpringAnimation cOUISpringAnimation2 = this.followHandYAnim;
        if (cOUISpringAnimation2 != null) {
            cOUISpringAnimation2.animateToFinalPosition(pointF.y);
        }
    }
}
