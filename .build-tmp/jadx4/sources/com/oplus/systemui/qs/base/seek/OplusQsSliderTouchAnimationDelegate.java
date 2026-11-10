package com.oplus.systemui.qs.base.seek;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.content.FileProvider$;
import android.view.MotionEvent;
import androidx.collection.FloatList$;
import androidx.fragment.app.Fragment$5$;
import com.coui.appcompat.animation.dynamicanimation.COUIComposeMotionAnimator;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.coui.appcompat.animation.touchanimation.TouchMotionParamConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsSliderTouchAnimationDelegate {
    public static final Companion Companion = new Companion();
    private final TouchMotionParamConfig animationParam;
    private final COUIComposeMotionAnimator.OnAnimationUpdateCallback animationUpdateCallback;
    public boolean isDownEventLocatedValidBounds;
    public boolean isLastEventValidBounds;
    public boolean isSimulating;
    public boolean pendingSimulateUp;
    public float simulateCurrentY;
    public boolean simulateDirection;
    public long simulateDownTime;
    public long simulateEventTime;
    private final Handler simulateHandler = new Handler(Looper.getMainLooper());
    public int simulateMoveCount;
    private final Runnable simulateMoveRunnable;
    public float simulateStartX;
    public float simulateStartY;
    private final OplusQsVerticalSeekBar slider;
    private String tag;
    private Function3 touchAnimationCallback;
    private COUITouchMotionAnimator touchMotionAnimation;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQsSliderTouchAnimationDelegate(String str, OplusQsVerticalSeekBar oplusQsVerticalSeekBar, TouchMotionParamConfig touchMotionParamConfig) {
        this.slider = oplusQsVerticalSeekBar;
        this.animationParam = touchMotionParamConfig;
        this.tag = FileProvider$.ExternalSyntheticOutline0.m("OplusQsSliderTouchAnimationDelegate-", str);
        OplusQsSliderTouchAnimationDelegate$simulateMoveRunnable$1 oplusQsSliderTouchAnimationDelegate$simulateMoveRunnable$1 = new OplusQsSliderTouchAnimationDelegate$simulateMoveRunnable$1();
        oplusQsSliderTouchAnimationDelegate$simulateMoveRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.simulateMoveRunnable = oplusQsSliderTouchAnimationDelegate$simulateMoveRunnable$1;
        OplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1 oplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1 = new OplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1();
        oplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationUpdateCallback = oplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1;
        COUITouchMotionAnimator cOUITouchMotionAnimator = new COUITouchMotionAnimator(oplusQsVerticalSeekBar.getContext());
        cOUITouchMotionAnimator.setDeformationEnable(true);
        cOUITouchMotionAnimator.setRubberEnable(true);
        cOUITouchMotionAnimator.setPressScaleEnable(false);
        cOUITouchMotionAnimator.addOnAnimationUpdateCallback(oplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1);
        cOUITouchMotionAnimator.config(touchMotionParamConfig);
        this.touchMotionAnimation = cOUITouchMotionAnimator;
    }

    public static final void access$dispatchSimulateMove(OplusQsSliderTouchAnimationDelegate oplusQsSliderTouchAnimationDelegate) {
        if (oplusQsSliderTouchAnimationDelegate.isSimulating) {
            float f = ((oplusQsSliderTouchAnimationDelegate.simulateDirection ? -1.0f : 1.0f) * 30.0f) + oplusQsSliderTouchAnimationDelegate.simulateCurrentY;
            float f2 = oplusQsSliderTouchAnimationDelegate.simulateStartY;
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f, f2 - 240.0f, f2 + 240.0f);
            oplusQsSliderTouchAnimationDelegate.simulateCurrentY = fCoerceIn;
            oplusQsSliderTouchAnimationDelegate.simulateEventTime += 16;
            oplusQsSliderTouchAnimationDelegate.dispatchSimulateTouchEvent(oplusQsSliderTouchAnimationDelegate.simulateStartX, fCoerceIn, 2);
            int i = oplusQsSliderTouchAnimationDelegate.simulateMoveCount + 1;
            oplusQsSliderTouchAnimationDelegate.simulateMoveCount = i;
            if (!oplusQsSliderTouchAnimationDelegate.pendingSimulateUp || i < 4) {
                oplusQsSliderTouchAnimationDelegate.simulateHandler.postDelayed(oplusQsSliderTouchAnimationDelegate.simulateMoveRunnable, 16L);
            } else {
                oplusQsSliderTouchAnimationDelegate.dispatchSimulateUp();
            }
        }
    }

    public final void destroy() {
        this.simulateHandler.removeCallbacksAndMessages(null);
        this.simulateHandler.removeCallbacks(this.simulateMoveRunnable);
        this.isSimulating = false;
        this.pendingSimulateUp = false;
        this.simulateMoveCount = 0;
        this.touchMotionAnimation.cancel();
        COUITouchMotionAnimator cOUITouchMotionAnimator = this.touchMotionAnimation;
        COUIComposeMotionAnimator.OnAnimationUpdateCallback onAnimationUpdateCallback = this.animationUpdateCallback;
        ArrayList arrayList = ((COUIComposeMotionAnimator) cOUITouchMotionAnimator).mUpdateCallbacks;
        int iIndexOf = arrayList.indexOf(onAnimationUpdateCallback);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }

    public final void dispatchNewTouchEvent(MotionEvent motionEvent, int i) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setAction(i);
        if (i == 0) {
            this.touchMotionAnimation.config(this.animationParam);
        }
        this.touchMotionAnimation.handleTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final void dispatchSimulateTouchEvent(float f, float f2, int i) {
        MotionEvent motionEventObtain = MotionEvent.obtain(this.simulateDownTime, this.simulateEventTime, i, f, f2, 0);
        motionEventObtain.getClass();
        handleTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final void dispatchSimulateUp() {
        if (this.isSimulating) {
            this.simulateHandler.removeCallbacks(this.simulateMoveRunnable);
            this.simulateEventTime += 16;
            dispatchSimulateTouchEvent(this.simulateStartX, this.simulateCurrentY, 1);
            this.isSimulating = false;
            this.pendingSimulateUp = false;
        }
    }

    public final Function3 getTouchAnimationCallback() {
        return this.touchAnimationCallback;
    }

    public final void handleSimulateEventCode(int i) {
        if (i < 0) {
            return;
        }
        this.simulateDirection = (i & 2) == 2;
        if ((i & 1) != 1) {
            if (this.isSimulating) {
                if (this.simulateMoveCount >= 4) {
                    dispatchSimulateUp();
                    return;
                } else {
                    this.pendingSimulateUp = true;
                    return;
                }
            }
            return;
        }
        this.simulateHandler.removeCallbacks(this.simulateMoveRunnable);
        this.isSimulating = true;
        this.pendingSimulateUp = false;
        this.simulateMoveCount = 0;
        this.simulateStartX = this.slider.getWidth() / 2.0f;
        float height = this.simulateDirection ? 0.0f : this.slider.getHeight();
        this.simulateStartY = height;
        this.simulateCurrentY = height;
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.simulateDownTime = jUptimeMillis;
        this.simulateEventTime = jUptimeMillis;
        dispatchSimulateTouchEvent(this.simulateStartX, this.simulateCurrentY, 0);
        this.simulateHandler.postDelayed(this.simulateMoveRunnable, 16L);
    }

    public final void handleSizeChanged(int i, int i2, int i3, int i4) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, Fragment$5$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("onSizeChanged: w=", i, i2, ", h=", ", oldw="), i3, i4, ", oldh="), null, 8);
        this.touchMotionAnimation.onSizeChange(i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0055 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005d  */
    public final void handleTouchEvent(MotionEvent motionEvent) {
        float progress = this.slider.getProgress();
        float max = this.slider.getMax();
        boolean z = progress <= 0.25f * max || progress >= max * 0.75f;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.isDownEventLocatedValidBounds = z;
            if (z) {
                this.touchMotionAnimation.config(this.animationParam);
                this.touchMotionAnimation.handleTouchEvent(motionEvent);
            }
        } else if (actionMasked == 1) {
            if (z) {
                this.touchMotionAnimation.handleTouchEvent(motionEvent);
            } else {
                dispatchNewTouchEvent(motionEvent, 1);
            }
        } else if (actionMasked == 2) {
            boolean z2 = this.isDownEventLocatedValidBounds;
            if (z) {
                if (z2 || this.isLastEventValidBounds) {
                    this.touchMotionAnimation.handleTouchEvent(motionEvent);
                } else {
                    dispatchNewTouchEvent(motionEvent, 0);
                }
            } else if (z2) {
                this.touchMotionAnimation.handleTouchEvent(motionEvent);
            } else if (this.isLastEventValidBounds) {
                dispatchNewTouchEvent(motionEvent, 1);
            }
        } else if (actionMasked == 3) {
            if (z) {
                this.touchMotionAnimation.handleTouchEvent(motionEvent);
            } else {
                dispatchNewTouchEvent(motionEvent, 1);
            }
        }
        this.isLastEventValidBounds = z;
    }

    public final void setTouchAnimationCallback(Function3 function3) {
        this.touchAnimationCallback = function3;
    }
}
