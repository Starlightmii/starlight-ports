package com.oplus.systemui.navigationbar.gesture.sidegesture.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.navigationbar.GesturePointContainerEx;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.navigationbar.gesture.sidegesture.GesturePointContainer;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BaseBezierCalculator;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.BezierCalculationCallback;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.NonRubberBandBezierCalculator;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator.RubberBandBezierCalculator;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureBackVibrateObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SideGestureNavView extends View implements BezierCalculationCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SideGestureNavView";
    private Bitmap backIcon;
    private final Lazy bezierCalculator$delegate;
    private final Paint bezierPaint;
    private final Paint borderPaint;
    public boolean exitAnimProcessing;
    public int gestureState;
    public boolean hasDownEvent;
    private final Paint iconPaint;
    public int lastGestureState;
    public final int position;
    private final SideGestureConfigurationEx sideGestureConfiguration;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static BaseBezierCalculator $r8$lambda$LttBaVaORHCKk5chkbZvcYxwpDU(SideGestureNavView sideGestureNavView, Context context, int i) {
        int animLevel = ScrimUtil.getAnimLevel();
        int i2 = sideGestureNavView.position;
        SideGestureConfigurationEx sideGestureConfigurationEx = sideGestureNavView.sideGestureConfiguration;
        return animLevel <= 2 ? new RubberBandBezierCalculator(context, i, i2, sideGestureConfigurationEx, sideGestureNavView) : new NonRubberBandBezierCalculator(context, i, i2, sideGestureConfigurationEx, sideGestureNavView);
    }

    public SideGestureNavView(Context context, int i, int i2, SideGestureConfigurationEx sideGestureConfigurationEx) {
        super(context);
        this.position = i;
        this.sideGestureConfiguration = sideGestureConfigurationEx;
        SideGestureNavView$$ExternalSyntheticLambda0 sideGestureNavView$$ExternalSyntheticLambda0 = new SideGestureNavView$$ExternalSyntheticLambda0();
        sideGestureNavView$$ExternalSyntheticLambda0.f$0 = this;
        sideGestureNavView$$ExternalSyntheticLambda0.f$1 = context;
        sideGestureNavView$$ExternalSyntheticLambda0.f$2 = i2;
        VarHandle.storeStoreFence();
        this.bezierCalculator$delegate = LazyKt__LazyJVMKt.lazy(sideGestureNavView$$ExternalSyntheticLambda0);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(12.0f);
        paint.setColor(context.getColor(2131102823));
        paint.setAlpha(191);
        paint.setAntiAlias(true);
        this.bezierPaint = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(0.5f);
        paint2.setColor(-1);
        paint2.setAlpha(77);
        paint2.setAntiAlias(true);
        this.borderPaint = paint2;
        Paint paint3 = new Paint();
        paint3.setAntiAlias(true);
        this.iconPaint = paint3;
        this.lastGestureState = -1;
        this.gestureState = -1;
    }

    private final BaseBezierCalculator getBezierCalculator() {
        return (BaseBezierCalculator) this.bezierCalculator$delegate.getValue();
    }

    public final void onConfigChanged(int i) {
        getBezierCalculator().calculateOffset(i);
        resetState();
    }

    public final void onDownEvent(MotionEvent motionEvent, int i) {
        boolean z = this.exitAnimProcessing;
        this.exitAnimProcessing = false;
        getBezierCalculator().chainFromPreviousExit = z;
        if (z) {
            this.hasDownEvent = true;
            getBezierCalculator().cancelExitSpringAnimation();
            this.lastGestureState = -1;
            this.gestureState = -1;
            setVisibility(0);
        } else {
            resetState();
            this.hasDownEvent = true;
            setVisibility(0);
        }
        getBezierCalculator().onDownEvent(motionEvent, getLayoutDirection(), i);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Bitmap bitmap;
        super.onDraw(canvas);
        Path path = getBezierCalculator().getPath();
        canvas.drawPath(path, this.bezierPaint);
        canvas.drawPath(path, this.borderPaint);
        int i = this.gestureState;
        if ((i == 2 || i == 1) && (bitmap = this.backIcon) != null) {
            if (!bitmap.isRecycled()) {
                canvas.drawBitmap(bitmap, getBezierCalculator().getBackIconMatrix(), this.iconPaint);
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "SideGestureNavView-->back icon is recycled");
            }
        }
    }

    public final void onGestureFinished(GesturePointContainer gesturePointContainer) {
        if (this.hasDownEvent) {
            this.exitAnimProcessing = true;
            PointF point = gesturePointContainer.getPoint();
            if (point != null) {
                getBezierCalculator().onGestureFinished(point);
            }
        }
    }

    public final void onUpEvent() {
        this.hasDownEvent = false;
        if (!this.exitAnimProcessing) {
            resetState();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.position, "SideGestureNavView1 : onUpEvent mPosition : ", "SideGestureNavView-->", "SystemUi--NavBar");
        }
    }

    public final void onUpdateGestureView(GesturePointContainerEx gesturePointContainerEx) {
        PointF point;
        if (this.hasDownEvent) {
            this.lastGestureState = this.gestureState;
            int state = gesturePointContainerEx.getState();
            this.gestureState = state;
            if (state != this.lastGestureState && state == 2 && SwipeSideGestureBackVibrateObserver.INSTANCE.getSwipeSideEnableBackVibrate() == 1) {
                VibrationHelper vibrationHelper = VibrationHelper.getInstance(getContext());
                vibrationHelper.getClass();
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isLinearVibratorSupport()) {
                    vibrationHelper.doVibrateCustomized(vibrationHelper.mContext, 1, false);
                } else {
                    vibrationHelper.performKeyboardTouchFeedback();
                }
            }
            if (this.exitAnimProcessing || (point = gesturePointContainerEx.getPoint()) == null) {
                return;
            }
            getBezierCalculator().onTouchEvent(point);
        }
    }

    public final void resetState() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "SideGestureNavView-->resetState");
        }
        this.lastGestureState = -1;
        this.gestureState = -1;
        setVisibility(8);
        getBezierCalculator().resetState();
    }

    public final void setBackIcon(Bitmap bitmap) {
        this.backIcon = bitmap;
    }
}
