package com.oplus.transition;

import android.animation.Animator;
import android.content.ComponentName;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Choreographer;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.window.TransitionInfo;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.android.internal.dynamicanimation.animation.FloatValueHolder;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.internal.dynamicanimation.animation.SpringForce;
import com.android.wm.shell.R;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.startingsurface.OplusShellStartingWindowUtils;
import com.android.wm.shell.transition.CounterRotatorHelper;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.Transitions;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpringSlideAnimationController {
    private static final float DAMPING_RATIO = 1.0f;
    public static final boolean ENABLE_SPRING_SLIDE_ANIM;
    public static final int INVALID_RES_ID = -1;
    public static final String KEY_RES_ID = "resId";
    public static final String KEY_SPRING_ANIM_PARAMETER = "springAnimParameter";
    private static final int MATRIX_ARRAY_LEN = 9;
    private static final float MIN_VIS_CHANGE = 1.3f;
    public static final int SPRING_ANIMATION_MATCH_CHANGE_SIZE = 2;
    private static final float STIFFNESS = 350.0f;
    public static final String TAG = "SpringSlideAnimationController";
    private ShellExecutor mAnimExecutor;
    private ShellExecutor mMainExecutor;
    public TransactionPool mPool;
    private ArrayList<SpringSlideAnim> mSpringAnimations = new ArrayList<>();
    private ArrayMap<Integer, String> mResMap = new ArrayMap<>();
    private ArrayMap<IBinder, ArrayList<SpringSlideAnim>> mContinueInfo = new ArrayMap<>(1);
    private final CounterRotatorHelper mRotator = new CounterRotatorHelper();
    private ArrayMap<IBinder, ArrayList<ContinueValue>> mContinueInfoWithChange = new ArrayMap<>(1);

    static {
        ENABLE_SPRING_SLIDE_ANIM = SystemProperties.getBoolean("persist.wm.debug.spring_slide_anim", true) && !OplusShellStartingWindowUtils.LIGHT_OS_ANIM_LEVEL;
    }

    public SpringSlideAnimationController(ShellExecutor shellExecutor, ShellExecutor shellExecutor2, TransactionPool transactionPool) {
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mPool = transactionPool;
        initSupportsResMap();
    }

    private boolean canUseSpringSlideAnim(TransitionInfo transitionInfo) {
        return true;
    }

    private ArrayList<SpringSlideAnim> getTargetSpringSlideAnim(IBinder iBinder) {
        ArrayList<SpringSlideAnim> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mSpringAnimations.size(); i++) {
            if (this.mSpringAnimations.get(i).match(iBinder)) {
                arrayList.add(this.mSpringAnimations.get(i));
            }
        }
        return arrayList;
    }

    private float[] getXValueFromResId(int i) {
        float f = 1.0f;
        float f2 = 0.0f;
        if (i != R.anim.oplus_open_slide_enter) {
            if (i == R.anim.oplus_open_slide_exit || i == R.anim.oplus_open_slide_exit_no_alpha) {
                f = 0.0f;
                f2 = -0.3f;
            } else if (i == R.anim.oplus_close_slide_enter || i == R.anim.oplus_close_slide_enter_no_alpha) {
                f = -0.3f;
            } else if (i == R.anim.oplus_close_slide_exit) {
                f2 = 1.0f;
                f = 0.0f;
            }
        }
        return new float[]{f, f2};
    }

    private void initSupportsResMap() {
        this.mResMap.put(Integer.valueOf(R.anim.oplus_open_slide_enter), "oplus_open_slide_enter");
        this.mResMap.put(Integer.valueOf(R.anim.oplus_open_slide_exit), "oplus_open_slide_exit");
        this.mResMap.put(Integer.valueOf(R.anim.oplus_close_slide_enter), "oplus_close_slide_enter");
        this.mResMap.put(Integer.valueOf(R.anim.oplus_close_slide_exit), "oplus_close_slide_exit");
        this.mResMap.put(Integer.valueOf(R.anim.oplus_open_slide_exit_no_alpha), "oplus_open_slide_exit_no_alpha");
        this.mResMap.put(Integer.valueOf(R.anim.oplus_close_slide_enter_no_alpha), "oplus_close_slide_enter_no_alpha");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInSpringSlideAnimChange(TransitionInfo.Change change, ArrayList<SpringSlideAnim> arrayList) {
        if (change == null || arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            SpringSlideAnim springSlideAnim = arrayList.get(i);
            i++;
            SpringSlideAnim springSlideAnim2 = springSlideAnim;
            if (springSlideAnim2 != null && springSlideAnim2.match(change)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchResId(int i) {
        return this.mResMap.containsKey(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSpringSlideAnim(SpringSlideAnim springSlideAnim) {
        this.mSpringAnimations.remove(springSlideAnim);
    }

    public void buildSpringSlideAnim(Animator animator, Runnable runnable, Bundle bundle) {
        if (bundle == null || bundle.getParcelable(KEY_SPRING_ANIM_PARAMETER) == null) {
            return;
        }
        this.mSpringAnimations.add(new SpringSlideAnim(animator, runnable, bundle));
    }

    public boolean endTargetSpringSlideAnim(IBinder iBinder, Animator animator) {
        SpringSlideAnim targetSpringSlideAnim = getTargetSpringSlideAnim(iBinder, animator);
        if (targetSpringSlideAnim != null) {
            return targetSpringSlideAnim.endSpringSlideAnim();
        }
        return false;
    }

    public ContinueValue getMatchedContinueValue(IBinder iBinder, TransitionInfo.Change change, boolean z) {
        ArrayList<SpringSlideAnim> arrayList;
        if (iBinder == null || (arrayList = this.mContinueInfo.get(iBinder)) == null || arrayList.isEmpty()) {
            return null;
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            SpringSlideAnim springSlideAnim = arrayList.get(i);
            i++;
            SpringSlideAnim springSlideAnim2 = springSlideAnim;
            if ((springSlideAnim2.matchForDefaultContinue(change, arrayList) && !z) || ((springSlideAnim2.matchForPredictiveBackContinue(change) && z) || springSlideAnim2.match(change))) {
                float lastValue = springSlideAnim2.getLastValue();
                float lastPosX = springSlideAnim2.getLastPosX();
                float lastAlpha = springSlideAnim2.getLastAlpha();
                float cornerRadius = springSlideAnim2.getCornerRadius();
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("getMatchedContinueValue, lastValue=", lastValue, ", lastPosX=", lastPosX, ",lastAlpha=");
                sbM.append(lastAlpha);
                sbM.append(", cornerRadius=");
                sbM.append(cornerRadius);
                Log.d(TAG, sbM.toString());
                return new ContinueValue(lastValue, lastPosX, lastAlpha, cornerRadius);
            }
        }
        return null;
    }

    public ContinueValue getMatchedContinueValueWithChange(IBinder iBinder, TransitionInfo.Change change) {
        ArrayList<ContinueValue> arrayList;
        TransitionInfo.Change change2;
        if (iBinder != null && change != null && (arrayList = this.mContinueInfoWithChange.get(iBinder)) != null && !arrayList.isEmpty()) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                ContinueValue continueValue = arrayList.get(i);
                i++;
                ContinueValue continueValue2 = continueValue;
                if (continueValue2 != null && (change2 = continueValue2.change) != null && change2.equals(change)) {
                    StringBuilder sb = new StringBuilder("getMatchedContinueValueWithChange, find match value=");
                    sb.append(continueValue2.value);
                    sb.append(", posX=");
                    sb.append(continueValue2.posX);
                    sb.append(", alpha=");
                    sb.append(continueValue2.alpha);
                    sb.append(", cornerRadius=");
                    ChildHelper$.ExternalSyntheticOutline0.m(continueValue2.cornerRadius, TAG, sb);
                    return continueValue2;
                }
            }
        }
        return null;
    }

    public float getSpringWindowCornerRadius(TransitionInfo.Change change, Animation animation, float f) {
        ComponentName componentNameFromChange = change != null ? TransitionAnimationUtil.getComponentNameFromChange(change) : null;
        boolean zIsSystemApp = componentNameFromChange != null ? TransitionAnimationUtil.isSystemApp(componentNameFromChange.getPackageName()) : true;
        if (f <= 0.0f || animation == null || !animation.hasRoundedCorners() || !zIsSystemApp) {
            return 0.0f;
        }
        return f;
    }

    public int getUseSpringSlideAnimResId(TransitionInfo.Change change) {
        Bundle windowInfoBundleFromChange;
        if (!ENABLE_SPRING_SLIDE_ANIM || change == null || (windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change)) == null) {
            return -1;
        }
        return windowInfoBundleFromChange.getInt(KEY_RES_ID, -1);
    }

    public boolean hasContinueInfo(IBinder iBinder) {
        ArrayList<SpringSlideAnim> arrayList;
        return (iBinder == null || (arrayList = this.mContinueInfo.get(iBinder)) == null || arrayList.isEmpty()) ? false : true;
    }

    public void markUseSpringSlideAnim(TransitionInfo transitionInfo, int i, TransitionInfo.Change change) {
        Boolean bool;
        Bundle windowInfoBundleFromChange;
        if (!ENABLE_SPRING_SLIDE_ANIM || transitionInfo == null || change == null || (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, "getCanUseSpringSlideAnim", null, new Object[0])) == null || !bool.booleanValue() || !canUseSpringSlideAnim(transitionInfo) || !matchResId(i) || (windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change)) == null) {
            return;
        }
        windowInfoBundleFromChange.putInt(KEY_RES_ID, i);
        StringBuilder sb = new StringBuilder("markUseSpringSlideAnim, resId=");
        sb.append(i);
        sb.append(", res=");
        ExifInterface$.ExternalSyntheticOutline1.m(sb, this.mResMap.get(Integer.valueOf(i)), TAG);
    }

    public boolean saveBackAnimContinuePair(IBinder iBinder, IBinder iBinder2, ConcurrentHashMap<String, Float> concurrentHashMap) {
        TransitionInfo.Change change;
        int i = 0;
        if (iBinder == null || iBinder2 == null || concurrentHashMap == null) {
            return false;
        }
        ArrayList<SpringSlideAnim> targetSpringSlideAnim = getTargetSpringSlideAnim(iBinder);
        if (targetSpringSlideAnim.isEmpty() || !((SpringSlideAnim) targetSpringSlideAnim.getLast()).isRunning()) {
            return false;
        }
        this.mContinueInfo.clear();
        this.mContinueInfo.put(iBinder2, targetSpringSlideAnim);
        int size = targetSpringSlideAnim.size();
        while (i < size) {
            SpringSlideAnim springSlideAnim = targetSpringSlideAnim.get(i);
            i++;
            SpringSlideAnim springSlideAnim2 = springSlideAnim;
            ComponentName componentNameFromChange = TransitionAnimationUtil.getComponentNameFromChange(springSlideAnim2.mChange);
            if (componentNameFromChange != null && (change = springSlideAnim2.mChange) != null) {
                concurrentHashMap.put(TransitionAnimationUtil.getMapKeyByComponentAndMode(componentNameFromChange, 1 ^ (TransitionUtil.isClosingMode(change.getMode()) ? 1 : 0)), Float.valueOf(springSlideAnim2.getCornerRadius()));
            }
        }
        return true;
    }

    public boolean shouldUseSpringSlideAnim(Bundle bundle) {
        return ENABLE_SPRING_SLIDE_ANIM && bundle != null && (bundle.getBoolean(ShellContinuousTransitionUtils.SKIP_DEFAULT_CONTINUE_TRANSFORMATION, false) || bundle.getParcelable(KEY_SPRING_ANIM_PARAMETER) != null);
    }

    public boolean startSpringSlideAnimInstead(IBinder iBinder, Animator animator) {
        SpringSlideAnim targetSpringSlideAnim;
        if (ENABLE_SPRING_SLIDE_ANIM && (targetSpringSlideAnim = getTargetSpringSlideAnim(iBinder, animator)) != null) {
            return targetSpringSlideAnim.startSpringSlideAnim();
        }
        return false;
    }

    public void transferDefaultAnimToSpringInfo(IBinder iBinder, TransitionInfo.Change change, float f, int i) {
        Transformation transformation;
        if (iBinder == null || change == null || this.mRotator == null) {
            Log.d(TAG, "Invalid parameters for animation transfer");
            return;
        }
        ArrayMap backAnimContinueTransformMap = Transitions.getBackAnimContinueTransformMap(iBinder);
        if (backAnimContinueTransformMap == null) {
            return;
        }
        int mode = change.getMode();
        if (TransitionUtil.isClosingMode(mode)) {
            transformation = (Transformation) backAnimContinueTransformMap.get(1);
        } else {
            transformation = TransitionUtil.isOpeningMode(mode) ? (Transformation) backAnimContinueTransformMap.get(0) : null;
        }
        if (transformation == null) {
            return;
        }
        ArrayList<ContinueValue> arrayList = this.mContinueInfoWithChange.get(iBinder);
        if (arrayList == null) {
            this.mContinueInfoWithChange.clear();
            arrayList = new ArrayList<>();
            this.mContinueInfoWithChange.put(iBinder, arrayList);
        }
        ArrayList<ContinueValue> arrayList2 = arrayList;
        float fWidth = (TransitionUtil.isClosingType(change.getMode()) ? this.mRotator.getEndBoundsInStartRotation(change) : change.getEndAbsBounds()).width();
        float[] xValueFromResId = getXValueFromResId(i);
        float[] fArr = new float[9];
        transformation.getMatrix().getValues(fArr);
        float f2 = fArr[2];
        float alpha = transformation.getAlpha();
        float f3 = (xValueFromResId[1] - xValueFromResId[0]) * fWidth;
        if (Math.abs(f3) < Float.MIN_VALUE) {
            Log.w(TAG, "Denominator too small for calculation");
        } else {
            arrayList2.add(new ContinueValue(fWidth * ((f2 - (xValueFromResId[0] * fWidth)) / f3), f2, alpha, f, change));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class SpringSlideAnim {
        Animator mAnimator;
        TransitionInfo.Change mChange;
        Rect mClipRect;
        float mCornerRadius;
        Rect mEndBounds;
        float mFinalAlpha;
        float mFinalPosX;
        float mFinishValue;
        float mLastAlpha;
        float mLastPosX;
        float mLastValue;
        Point mPosition;
        SpringAnimation mSpringAnimation;
        float mStartAlpha;
        float mStartPosX;
        long mStartTime;
        float mStartValue;
        PointF mTempPoint = new PointF();
        SurfaceControl.Transaction mTransaction;
        IBinder mTransition;

        public SpringSlideAnim(Animator animator, Runnable runnable, Bundle bundle) {
            this.mTransaction = SpringSlideAnimationController.this.mPool.acquire();
            this.mAnimator = animator;
            this.mTransition = bundle.getBinder("transitionToken");
            this.mChange = bundle.getParcelable("change");
            SpringSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda1 springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda1 = new SpringSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda1();
            springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda1.f$0 = this;
            springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda1.f$1 = runnable;
            VarHandle.storeStoreFence();
            buildSpringSlideAnimInner(springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda1, (SpringAnimParameter) bundle.getParcelable(SpringSlideAnimationController.KEY_SPRING_ANIM_PARAMETER));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$buildSpringSlideAnimInner$1(DynamicAnimation dynamicAnimation, float f, float f2) {
            PointF pointF = this.mTempPoint;
            pointF.set(f, pointF.y);
            applyTransformForTarget(this.mChange.getLeash());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$buildSpringSlideAnimInner$2(Runnable runnable, DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
            Log.d(SpringSlideAnimationController.TAG, "anim duration " + (System.currentTimeMillis() - this.mStartTime) + " " + f);
            PointF pointF = this.mTempPoint;
            pointF.set(f, pointF.y);
            applyTransformForTarget(this.mChange.getLeash());
            if (runnable != null) {
                SpringSlideAnimationController.this.mMainExecutor.execute(runnable);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$endSpringSlideAnim$3() {
            this.mSpringAnimation.cancel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(Runnable runnable) {
            SpringSlideAnimationController.this.mPool.release(this.mTransaction);
            SpringSlideAnimationController.this.removeSpringSlideAnim(this);
            if (runnable != null) {
                runnable.run();
            }
        }

        public void applyTransformForTarget(SurfaceControl surfaceControl) {
            if (surfaceControl == null || !surfaceControl.isValid()) {
                return;
            }
            float f = this.mTempPoint.x;
            float f2 = this.mStartValue;
            float f3 = this.mFinishValue;
            float f4 = this.mStartAlpha;
            float fM = this.mFinalAlpha;
            float f5 = this.mStartPosX;
            float f6 = this.mFinalPosX;
            if (f4 != fM) {
                fM = DrawerArrowDrawable$.ExternalSyntheticOutline0.m(fM, f4, (f - f2) / (f3 - f2), f4);
            }
            float fM2 = DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f6, f5, (f - f2) / (f3 - f2), f5);
            Point point = this.mPosition;
            if (point != null) {
                fM2 += point.x;
            }
            this.mLastPosX = fM2;
            this.mLastAlpha = fM;
            this.mLastValue = f;
            this.mTransaction.setPosition(surfaceControl, fM2, 0.0f).setAlpha(surfaceControl, fM);
            float f7 = this.mCornerRadius;
            if (f7 != 0.0f && this.mClipRect != null) {
                OplusTransitionReflectionHelper.setCornerRadiusExt(this.mTransaction, surfaceControl, f7, 2.5f, 1);
                this.mTransaction.setCrop(surfaceControl, this.mClipRect);
            }
            this.mTransaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
            this.mTransaction.apply();
        }

        public void buildSpringSlideAnimInner(Runnable runnable, SpringAnimParameter springAnimParameter) {
            SpringForce springForce = new SpringForce();
            springForce.setStiffness(SpringSlideAnimationController.STIFFNESS).setDampingRatio(1.0f);
            SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder(this.mTempPoint.x));
            this.mSpringAnimation = springAnimation;
            springAnimation.setSpring(springForce);
            this.mSpringAnimation.setMinimumVisibleChange(SpringSlideAnimationController.MIN_VIS_CHANGE);
            SpringSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda2 springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda2 = new SpringSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda2();
            springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            SpringSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda3 springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda3 = new SpringSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda3();
            springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda3.f$0 = this;
            springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda3.f$1 = runnable;
            VarHandle.storeStoreFence();
            this.mSpringAnimation.addUpdateListener(springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda2);
            this.mSpringAnimation.addEndListener(springSlideAnimationController$SpringSlideAnim$$ExternalSyntheticLambda3);
            setStartAndEndState(springAnimParameter);
        }

        public boolean endSpringSlideAnim() {
            if (this.mSpringAnimation == null) {
                return false;
            }
            ShellExecutor shellExecutor = SpringSlideAnimationController.this.mAnimExecutor;
            SpringSlideAnimationController$SpringSlideAnim$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new SpringSlideAnimationController$SpringSlideAnim$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            shellExecutor.execute(externalSyntheticLambda0);
            Log.d(SpringSlideAnimationController.TAG, "endSpringSlideAnim for " + this);
            return true;
        }

        public float getCornerRadius() {
            return this.mCornerRadius;
        }

        public float getLastAlpha() {
            return this.mLastAlpha;
        }

        public float getLastPosX() {
            return this.mLastPosX;
        }

        public float getLastValue() {
            return this.mLastValue;
        }

        public boolean isRunning() {
            SpringAnimation springAnimation = this.mSpringAnimation;
            return springAnimation != null && springAnimation.isRunning();
        }

        public boolean match(IBinder iBinder, Animator animator) {
            return iBinder == this.mTransition && animator == this.mAnimator;
        }

        public boolean matchForDefaultContinue(TransitionInfo.Change change, ArrayList<SpringSlideAnim> arrayList) {
            if (change == null || this.mChange == null || SpringSlideAnimationController.this.isInSpringSlideAnimChange(change, arrayList)) {
                return false;
            }
            return !(change.getActivityComponent() == null && change.getTaskInfo() == null && change.getTaskFragmentToken() == null) && matchForTarget(change, this.mChange) && ShellContinuousTransitionUtils.isChangeModeOpposite(change, this.mChange);
        }

        public boolean matchForPredictiveBackContinue(TransitionInfo.Change change) {
            if ((change.getActivityComponent() != null || change.getTaskInfo() != null) && matchForTarget(change, this.mChange) && change.getLeash() != null && this.mChange.getLeash() != null && change.getLeash().isSameSurface(this.mChange.getLeash())) {
                if (TransitionUtil.isOpeningMode(change.getMode()) && TransitionUtil.isClosingMode(this.mChange.getMode())) {
                    return true;
                }
                if (change.getMode() == 6 && TransitionUtil.isOpeningMode(this.mChange.getMode())) {
                    return true;
                }
            }
            return false;
        }

        public boolean matchForTarget(TransitionInfo.Change change, TransitionInfo.Change change2) {
            ComponentName componentNameFromChange = TransitionAnimationUtil.getComponentNameFromChange(change);
            return componentNameFromChange != null && componentNameFromChange.equals(TransitionAnimationUtil.getComponentNameFromChange(change2));
        }

        public void setStartAndEndState(SpringAnimParameter springAnimParameter) {
            float f;
            float f2;
            float f3;
            int resId = springAnimParameter.getResId();
            float f4 = 1.0f;
            if (resId == R.anim.oplus_open_slide_enter) {
                f2 = 0.0f;
                f3 = 1.0f;
                f = 1.0f;
            } else {
                f = 0.5f;
                f2 = -0.3f;
                if (resId == R.anim.oplus_open_slide_exit) {
                    f3 = 0.5f;
                    f = 1.0f;
                } else {
                    if (resId == R.anim.oplus_close_slide_enter) {
                        f3 = 1.0f;
                    } else if (resId == R.anim.oplus_close_slide_exit) {
                        f3 = 1.0f;
                        f = 1.0f;
                        f2 = 1.0f;
                    } else if (resId == R.anim.oplus_close_slide_enter_no_alpha) {
                        f3 = 1.0f;
                        f = 1.0f;
                    } else if (resId == R.anim.oplus_open_slide_exit_no_alpha) {
                        f3 = 1.0f;
                        f = 1.0f;
                    } else {
                        f2 = 0.0f;
                        f3 = 1.0f;
                        f = 1.0f;
                        f4 = 0.0f;
                    }
                    f4 = -0.3f;
                    f2 = 0.0f;
                }
                f4 = 0.0f;
            }
            Rect animationRange = springAnimParameter.getAnimationRange();
            this.mCornerRadius = springAnimParameter.getCornerRadius();
            this.mEndBounds = springAnimParameter.getEndBounds();
            this.mClipRect = springAnimParameter.getClipRect();
            this.mPosition = springAnimParameter.getPosition();
            ContinueValue matchedContinueValue = SpringSlideAnimationController.this.getMatchedContinueValue(this.mTransition, this.mChange, false);
            if (matchedContinueValue == null) {
                matchedContinueValue = SpringSlideAnimationController.this.getMatchedContinueValueWithChange(this.mTransition, this.mChange);
                this.mStartValue = matchedContinueValue != null ? matchedContinueValue.value : 0.0f;
            } else {
                this.mStartValue = this.mEndBounds.width() - matchedContinueValue.value;
            }
            this.mFinishValue = this.mEndBounds.width();
            this.mStartPosX = matchedContinueValue != null ? matchedContinueValue.posX : this.mEndBounds.width() * f4;
            this.mFinalPosX = f2 * this.mEndBounds.width();
            if (matchedContinueValue != null) {
                f = matchedContinueValue.alpha;
            }
            this.mStartAlpha = f;
            this.mFinalAlpha = f3;
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(resId, "setStartAndEndState, resId=", ", position=");
            sbM.append(this.mPosition);
            sbM.append(", clipRect=");
            sbM.append(this.mClipRect);
            sbM.append(", cornerRadius=");
            sbM.append(this.mCornerRadius);
            sbM.append(", animationRange=");
            sbM.append(animationRange);
            sbM.append(", endBounds=");
            sbM.append(this.mEndBounds);
            sbM.append(", startValue=");
            sbM.append(this.mStartValue);
            sbM.append(", finishValue=");
            sbM.append(this.mFinishValue);
            sbM.append(", startPosX=");
            sbM.append(this.mStartPosX);
            sbM.append(", finalPosX=");
            sbM.append(this.mFinalPosX);
            sbM.append(", startAlpha=");
            sbM.append(this.mStartAlpha);
            sbM.append(", finalAlpha=");
            ChildHelper$.ExternalSyntheticOutline0.m(this.mFinalAlpha, SpringSlideAnimationController.TAG, sbM);
            this.mSpringAnimation.setStartValue(this.mStartValue);
        }

        public boolean startSpringSlideAnim() {
            if (this.mSpringAnimation == null) {
                return false;
            }
            this.mStartTime = System.currentTimeMillis();
            this.mSpringAnimation.animateToFinalPosition(this.mFinishValue);
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SpringSlideAnim{");
            sb.append(System.identityHashCode(this));
            if (this.mChange != null) {
                sb.append(" ");
                sb.append(this.mChange);
            }
            sb.append("}");
            return sb.toString();
        }

        public boolean match(TransitionInfo.Change change) {
            return change == this.mChange;
        }

        public boolean match(IBinder iBinder) {
            return iBinder == this.mTransition;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ContinueValue {
        float alpha;
        TransitionInfo.Change change;
        float cornerRadius;
        float posX;
        float value;

        public ContinueValue(float f, float f2, float f3, float f4, TransitionInfo.Change change) {
            this.value = f;
            this.posX = f2;
            this.alpha = f3;
            this.cornerRadius = f4;
            this.change = change;
        }

        public float getAlpha() {
            return this.alpha;
        }

        public float getPosX() {
            return this.posX;
        }

        public ContinueValue(float f, float f2, float f3, float f4) {
            this(f, f2, f3, f4, null);
        }
    }

    private SpringSlideAnim getTargetSpringSlideAnim(IBinder iBinder, Animator animator) {
        for (int i = 0; i < this.mSpringAnimations.size(); i++) {
            if (this.mSpringAnimations.get(i).match(iBinder, animator)) {
                return this.mSpringAnimations.get(i);
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpringAnimParameter implements Parcelable {
        public static final Parcelable.Creator<SpringAnimParameter> CREATOR = new Parcelable.Creator<SpringAnimParameter>() { // from class: com.oplus.transition.SpringSlideAnimationController.SpringAnimParameter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SpringAnimParameter createFromParcel(Parcel parcel) {
                return new SpringAnimParameter(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SpringAnimParameter[] newArray(int i) {
                return new SpringAnimParameter[i];
            }
        };
        private Rect mAnimationRange;
        private TransitionInfo.Change mChange;
        private Rect mClipRect;
        private float mCornerRadius;
        private Rect mEndBounds;
        private Point mPosition;
        private int mResId;

        public SpringAnimParameter(Parcel parcel) {
            this.mResId = parcel.readInt();
            this.mAnimationRange = (Rect) parcel.readParcelable(Rect.class.getClassLoader(), Rect.class);
            this.mEndBounds = (Rect) parcel.readParcelable(Rect.class.getClassLoader(), Rect.class);
            this.mPosition = (Point) parcel.readParcelable(Point.class.getClassLoader(), Point.class);
            this.mCornerRadius = parcel.readFloat();
            this.mClipRect = (Rect) parcel.readParcelable(Rect.class.getClassLoader(), Rect.class);
            this.mChange = (TransitionInfo.Change) parcel.readParcelable(TransitionInfo.Change.class.getClassLoader(), TransitionInfo.Change.class);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Rect getAnimationRange() {
            return this.mAnimationRange;
        }

        public TransitionInfo.Change getChange() {
            return this.mChange;
        }

        public Rect getClipRect() {
            return this.mClipRect;
        }

        public float getCornerRadius() {
            return this.mCornerRadius;
        }

        public Rect getEndBounds() {
            return this.mEndBounds;
        }

        public Point getPosition() {
            return this.mPosition;
        }

        public int getResId() {
            return this.mResId;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mResId);
            parcel.writeParcelable(this.mAnimationRange, i);
            parcel.writeParcelable(this.mEndBounds, i);
            parcel.writeParcelable(this.mPosition, i);
            parcel.writeFloat(this.mCornerRadius);
            parcel.writeParcelable(this.mClipRect, i);
            parcel.writeParcelable(this.mChange, i);
        }

        public SpringAnimParameter(int i, Rect rect, Rect rect2, Point point, float f, Rect rect3, TransitionInfo.Change change) {
            this.mResId = i;
            this.mAnimationRange = rect;
            this.mEndBounds = rect2;
            this.mPosition = point;
            this.mCornerRadius = f;
            this.mClipRect = rect3;
            this.mChange = change;
        }
    }
}
