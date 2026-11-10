package com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.SideGestureNavView;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseBezierCalculator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BaseBezierCalculator";
    private final BezierCalculationCallback callback;
    private COUISpringAnimation cancelAnim;
    public boolean chainFromPreviousExit;
    private final SideGestureConfigurationEx configuration;
    private final Context context;
    public float downY;
    public int layoutDirection;
    public final int position;
    public int rotation;
    public int screenHeight;
    public int screenWidth;
    public int viewOffsetX;
    public int viewOffsetY;
    private final Spring springCancelXConfig = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧滑手势横向返回Spring参数", 0.0f, 0.1f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
    private final PointF currentPoint = new PointF();
    private Matrix backIconMatrix = new Matrix();
    private Path path = new Path();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BaseBezierCalculator(Context context, int i, int i2, SideGestureConfigurationEx sideGestureConfigurationEx, BezierCalculationCallback bezierCalculationCallback) {
        this.context = context;
        this.rotation = i;
        this.position = i2;
        this.configuration = sideGestureConfigurationEx;
        this.callback = bezierCalculationCallback;
        calculateOffset(this.rotation);
    }

    public final void calculateMatrix() {
        this.backIconMatrix.reset();
        int iconSize = this.configuration.getIconSize();
        PointF pointF = this.currentPoint;
        int animCancelDistance = (int) (this.position == 0 ? (pointF.x - this.configuration.getAnimCancelDistance()) - iconSize : (pointF.x - this.viewOffsetX) + this.configuration.getAnimCancelDistance());
        float f = iconSize / 2.0f;
        this.backIconMatrix.setTranslate(animCancelDistance, (this.currentPoint.y - this.viewOffsetY) - f);
        this.backIconMatrix.preScale(1.0f, 1.0f, f, f);
        if (this.layoutDirection == 1) {
            this.backIconMatrix.preRotate(180.0f, f, f);
        }
    }

    public final void calculateOffset(int i) {
        int i2 = this.position;
        if (i2 == 0) {
            this.viewOffsetX = 0;
            this.viewOffsetY = this.configuration.getScreenHeight(i) - this.configuration.getWindowHeight(i);
        } else if (i2 == 1) {
            this.viewOffsetX = this.configuration.getScreenWidth(i) - this.configuration.getWindowWidth();
            this.viewOffsetY = this.configuration.getScreenHeight(i) - this.configuration.getWindowHeight(i);
        }
    }

    public abstract PointF calculateTargetPoint(PointF pointF);

    public final void cancelExitSpringAnimation() {
        COUISpringAnimation cOUISpringAnimation = this.cancelAnim;
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        this.cancelAnim = null;
    }

    public abstract void gestureFinished(PointF pointF);

    public final Matrix getBackIconMatrix() {
        return this.backIconMatrix;
    }

    public final BezierCalculationCallback getCallback() {
        return this.callback;
    }

    public final SideGestureConfigurationEx getConfiguration() {
        return this.configuration;
    }

    public final Context getContext() {
        return this.context;
    }

    public final PointF getCurrentPoint() {
        return this.currentPoint;
    }

    public final float getDownY() {
        return this.downY;
    }

    public final Path getPath() {
        return this.path;
    }

    public final int getPosition() {
        return this.position;
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final int getScreenHeight() {
        return this.screenHeight;
    }

    public final int getScreenWidth() {
        return this.screenWidth;
    }

    public abstract void initAnim();

    public final void onDownEvent(MotionEvent motionEvent, int i, int i2) {
        this.downY = motionEvent.getY();
        this.screenWidth = this.configuration.getScreenWidth(i2);
        this.screenHeight = this.configuration.getScreenHeight(i2);
        this.layoutDirection = i;
        this.rotation = i2;
        initAnim();
        this.chainFromPreviousExit = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "BaseBezierCalculator-->".concat("SideGestureNavView1 : onDownEvent mPosition : " + this.position + " mDownY :" + this.downY));
        }
    }

    public final void onGestureFinished(PointF pointF) {
        COUISpringAnimation cOUISpringAnimation;
        gestureFinished(pointF);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            PointF pointF2 = this.currentPoint;
            Log.d("SystemUi--NavBar", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("SideGestureNavView1 : onGestureFinished mPosit mCurrentPoint.x: ", pointF2.x, " mCurrentPoint.y: ", pointF2.y, "BaseBezierCalculator-->"));
        }
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(this.springCancelXConfig.bounce());
        cOUISpringForce.setResponse(this.springCancelXConfig.response());
        cOUISpringAnimation2.setEnableNonMainThread(true);
        cOUISpringAnimation2.setSpring(cOUISpringForce);
        BaseBezierCalculator$startCancelAnimation$1$2 baseBezierCalculator$startCancelAnimation$1$2 = new BaseBezierCalculator$startCancelAnimation$1$2();
        baseBezierCalculator$startCancelAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(baseBezierCalculator$startCancelAnimation$1$2);
        BaseBezierCalculator$startCancelAnimation$1$3 baseBezierCalculator$startCancelAnimation$1$3 = new BaseBezierCalculator$startCancelAnimation$1$3();
        baseBezierCalculator$startCancelAnimation$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addEndListener(baseBezierCalculator$startCancelAnimation$1$3);
        this.cancelAnim = cOUISpringAnimation2;
        cOUISpringAnimation2.setStartValue(this.currentPoint.x);
        int i = this.position;
        if (i == 0) {
            COUISpringAnimation cOUISpringAnimation3 = this.cancelAnim;
            if (cOUISpringAnimation3 != null) {
                cOUISpringAnimation3.animateToFinalPosition(0.0f);
                return;
            }
            return;
        }
        if (i != 1 || (cOUISpringAnimation = this.cancelAnim) == null) {
            return;
        }
        cOUISpringAnimation.animateToFinalPosition(this.screenWidth);
    }

    public final void onTouchEvent(PointF pointF) {
        updateTargetPoint(calculateTargetPoint(pointF));
    }

    public abstract void reset();

    public final void resetState() {
        cancelExitSpringAnimation();
        reset();
        PointF pointFCalculateTargetPoint = calculateTargetPoint(new PointF(this.position == 0 ? 0.0f : this.screenWidth, 0.0f));
        PointF pointF = this.currentPoint;
        pointF.x = pointFCalculateTargetPoint.x;
        pointF.y = pointFCalculateTargetPoint.y;
        calculateMatrix();
    }

    public final void setBackIconMatrix(Matrix matrix) {
        this.backIconMatrix = matrix;
    }

    public final void setDownY(float f) {
        this.downY = f;
    }

    public final void setPath(Path path) {
        this.path = path;
    }

    public final void setRotation(int i) {
        this.rotation = i;
    }

    public final void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public final void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public abstract void updateTargetPoint(PointF pointF);

    public final void updateView() {
        int animRadius = this.configuration.getAnimRadius();
        int bezierControlOffset1 = this.configuration.getBezierControlOffset1();
        int bezierControlOffsetSwitch2 = this.configuration.getBezierControlOffsetSwitch2();
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        PointF pointF3 = new PointF();
        PointF pointF4 = new PointF();
        PointF pointF5 = new PointF();
        PointF pointF6 = new PointF();
        int i = this.position;
        if (i == 0) {
            pointF.x = 0.0f;
            PointF pointF7 = this.currentPoint;
            float f = animRadius;
            pointF.y = pointF7.y - f;
            pointF2.x = 0.0f;
            pointF2.y = pointF7.y + f;
            pointF3.x = 0.0f;
            float f2 = animRadius - bezierControlOffset1;
            pointF3.y = pointF7.y - f2;
            pointF4.x = pointF7.x;
            float f3 = bezierControlOffsetSwitch2;
            pointF4.y = pointF7.y - f3;
            pointF5.x = pointF7.x;
            pointF5.y = pointF7.y + f3;
            pointF6.x = 0.0f;
            pointF6.y = pointF7.y + f2;
        } else if (i == 1) {
            float f4 = this.screenWidth;
            pointF.x = f4;
            PointF pointF8 = this.currentPoint;
            float f5 = animRadius;
            pointF.y = pointF8.y - f5;
            pointF2.x = f4;
            pointF2.y = pointF8.y + f5;
            pointF3.x = f4;
            float f6 = animRadius - bezierControlOffset1;
            pointF3.y = pointF8.y - f6;
            pointF4.x = pointF8.x;
            float f7 = bezierControlOffsetSwitch2;
            pointF4.y = pointF8.y - f7;
            pointF5.x = pointF8.x;
            pointF5.y = pointF8.y + f7;
            pointF6.x = f4;
            pointF6.y = pointF8.y + f6;
        }
        Path path = new Path();
        this.path = path;
        path.moveTo(pointF.x - this.viewOffsetX, pointF.y - this.viewOffsetY);
        Path path2 = this.path;
        float f8 = pointF3.x;
        float f9 = this.viewOffsetX;
        float f10 = f8 - f9;
        float f11 = pointF3.y;
        float f12 = this.viewOffsetY;
        float f13 = f11 - f12;
        float f14 = pointF4.x - f9;
        float f15 = pointF4.y - f12;
        PointF pointF9 = this.currentPoint;
        path2.cubicTo(f10, f13, f14, f15, pointF9.x - f9, pointF9.y - f12);
        Path path3 = this.path;
        float f16 = pointF5.x;
        float f17 = this.viewOffsetX;
        float f18 = f16 - f17;
        float f19 = pointF5.y;
        float f20 = this.viewOffsetY;
        path3.cubicTo(f18, f19 - f20, pointF6.x - f17, pointF6.y - f20, pointF2.x - f17, pointF2.y - f20);
        calculateMatrix();
        ((SideGestureNavView) this.callback).invalidate();
    }
}
