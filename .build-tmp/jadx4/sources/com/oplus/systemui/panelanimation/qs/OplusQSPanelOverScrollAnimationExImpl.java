package com.oplus.systemui.panelanimation.qs;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.animation.PathInterpolator;
import androidx.core.view.insets.Protection$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.TransCalculator;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.qs.QSPanelOverScrollAnimationEx;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.util.LargeScreenUtils;
import com.android.wm.shell.animation.FlingAnimationUtils;
import com.coui.appcompat.animation.COUIMoveEaseInterpolator;
import com.oplus.systemui.qs.OplusQSContainerImpl;
import com.oplus.systemui.qs.OplusQSContainerImplController;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPanelOverScrollAnimationExImpl extends QSPanelOverScrollAnimationEx {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private static final PathInterpolator INTERPOLATOR;
    private static final String TAG = "OplusQSOverScrollAnimation";
    public int activePointerId = -1;
    private Context context;
    private ValueAnimator feedBackAnimator;
    private FlingAnimationUtils flingAnimationUtils;
    public float initialHeight;
    public float initialMotionX;
    public float initialMotionY;
    public boolean isBeingDragged;
    public boolean isKeyguardShowing;
    private final ReadWriteProperty maxVelocity$delegate;
    public float overScrollHeight;
    private final PanelAnimationEx panelAnimationEx;
    public float panelExpansion;
    public boolean showCollapsedOnKeyguard;
    private final ReadWriteProperty touchSlop$delegate;
    private VelocityTracker velocityTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(OplusQSPanelOverScrollAnimationExImpl.class, "touchSlop", "getTouchSlop()I", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(OplusQSPanelOverScrollAnimationExImpl.class, "maxVelocity", "getMaxVelocity()F", 0)};
        Companion = new Companion();
        INTERPOLATOR = new COUIMoveEaseInterpolator();
    }

    public OplusQSPanelOverScrollAnimationExImpl(Context context, FlingAnimationUtils.Builder builder, PanelAnimationEx panelAnimationEx) {
        this.panelAnimationEx = panelAnimationEx;
        Delegates delegates = Delegates.INSTANCE;
        this.touchSlop$delegate = delegates.notNull();
        this.maxVelocity$delegate = delegates.notNull();
        setMaxVelocity(ViewConfiguration.get(context).getScaledMaximumFlingVelocity());
        setTouchSlop(ViewConfiguration.get(context).getScaledTouchSlop());
        this.flingAnimationUtils = builder.setMaxLengthSeconds(0.5f).setSpeedUpFactor(0.6f).build();
        this.context = context;
    }

    private final float getMaxVelocity() {
        return ((Number) this.maxVelocity$delegate.getValue(this, $$delegatedProperties[1])).floatValue();
    }

    private final int getTouchSlop() {
        return ((Number) this.touchSlop$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    private final void setMaxVelocity(float f) {
        this.maxVelocity$delegate.setValue(this, $$delegatedProperties[1], Float.valueOf(f));
    }

    private final void setTouchSlop(int i) {
        this.touchSlop$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    public final boolean enableOverScroll() {
        if (!QSFragmentHelper.getInstance().isFullExpanded() || this.panelExpansion < 1.0f) {
            return this.panelAnimationEx.isElementShowing() && isOnKeyguardAndExpanded();
        }
        return true;
    }

    public final boolean forbidQsExpansion() {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        boolean z = false;
        if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            return false;
        }
        if (QSFragmentHelper.getInstance().isFullExpanded()) {
            ValueAnimator valueAnimator = this.feedBackAnimator;
            if ((valueAnimator != null ? valueAnimator.isRunning() : false) || this.overScrollHeight != 0.0f) {
                z = true;
            }
        }
        if (z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(this.overScrollHeight, "forbidQsExpansion=true overScrollHeight="), null, 8);
        }
        return z;
    }

    public boolean handleQSTouch(MotionEvent motionEvent) {
        Resources resources;
        Context context = this.context;
        if ((context == null || (resources = context.getResources()) == null || !LargeScreenUtils.shouldUseSplitNotificationShade(resources)) && QSFragmentHelper.getInstance().getQsContainer() != null) {
            int actionMasked = motionEvent.getActionMasked() & 255;
            if (enableOverScroll()) {
                SuperPowerSaveRepositoryImpl.Companion.getClass();
                if (!SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                    if (this.velocityTracker == null) {
                        this.velocityTracker = VelocityTracker.obtain();
                    }
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.activePointerId);
                    if (iFindPointerIndex < 0) {
                        this.activePointerId = motionEvent.getPointerId(0);
                        iFindPointerIndex = 0;
                    }
                    if (actionMasked == 0) {
                        ValueAnimator valueAnimator = this.feedBackAnimator;
                        if (valueAnimator != null) {
                            valueAnimator.cancel();
                        }
                        initMotionEvent(motionEvent, iFindPointerIndex);
                    } else if (actionMasked != 1) {
                        if (actionMasked == 2) {
                            if (this.initialMotionY == -1000.0f) {
                                initMotionEvent(motionEvent, iFindPointerIndex);
                            }
                            if (!this.isBeingDragged) {
                                float rawX = motionEvent.getRawX(iFindPointerIndex);
                                float fAbs = Math.abs(rawX - this.initialMotionX);
                                float rawY = motionEvent.getRawY(iFindPointerIndex);
                                float fAbs2 = Math.abs(rawY - this.initialMotionY);
                                if (fAbs2 > getTouchSlop() && fAbs2 > fAbs && (rawY - this.initialMotionY) + this.initialHeight > 0.0f) {
                                    this.isBeingDragged = true;
                                    this.initialMotionX = rawX;
                                    this.initialMotionY = rawY;
                                }
                            }
                            if (this.isBeingDragged) {
                                float rawY2 = motionEvent.getRawY(iFindPointerIndex) - this.initialMotionY;
                                TransCalculator transCalculator = TransCalculator.INSTANCE;
                                float f = this.initialHeight + rawY2;
                                float fSin = this.overScrollHeight;
                                QS qsFragment = QSFragmentHelper.getInstance().getQsFragment();
                                float desiredHeight = (qsFragment != null ? qsFragment.getDesiredHeight() : 0.0f) * 1.5f;
                                transCalculator.getClass();
                                float f2 = 0.45f * desiredHeight;
                                float f3 = 0.25f * desiredHeight;
                                if (f <= desiredHeight) {
                                    fSin = f > f3 ? (float) ((Math.sin(((double) ((f - f3) / desiredHeight)) * 1.5707963267948966d) * ((double) f2)) + ((double) f3)) : MathUtils.constrain(f, 0.0f, f3);
                                } else if (f2 >= fSin) {
                                    fSin = f2;
                                }
                                this.overScrollHeight = fSin;
                                scrollPanel();
                            }
                        } else if (actionMasked == 3) {
                            if (this.overScrollHeight > 0.0f) {
                                this.overScrollHeight = 0.0f;
                                scrollPanel();
                            }
                            if (this.isBeingDragged) {
                                resetTouch();
                                return false;
                            }
                        } else if (actionMasked == 6) {
                            int actionIndex = motionEvent.getActionIndex();
                            if (motionEvent.getPointerId(actionIndex) == this.activePointerId) {
                                int i = actionIndex == 0 ? 1 : 0;
                                this.activePointerId = motionEvent.getPointerId(i);
                                VelocityTracker velocityTracker = this.velocityTracker;
                                if (velocityTracker != null) {
                                    velocityTracker.clear();
                                }
                                initMotionEvent(motionEvent, i);
                            }
                        }
                    } else if (this.isBeingDragged) {
                        VelocityTracker velocityTracker2 = this.velocityTracker;
                        if (velocityTracker2 != null) {
                            velocityTracker2.computeCurrentVelocity(1000, getMaxVelocity());
                        }
                        VelocityTracker velocityTracker3 = this.velocityTracker;
                        if (velocityTracker3 != null) {
                            velocityTracker3.getYVelocity(this.activePointerId);
                        }
                        boolean z = this.isBeingDragged;
                        float f4 = this.overScrollHeight;
                        boolean zIsFullExpanded = QSFragmentHelper.getInstance().isFullExpanded();
                        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("isBeingDragged: ", z, ", overScrollHeight: ", f4, ", isFullyExpanded: ");
                        sbM.append(zIsFullExpanded);
                        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
                        float f5 = this.overScrollHeight;
                        if (f5 > 0.0f) {
                            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f5, 0.0f);
                            this.feedBackAnimator = valueAnimatorOfFloat;
                            if (valueAnimatorOfFloat != null) {
                                valueAnimatorOfFloat.setInterpolator(INTERPOLATOR);
                            }
                            ValueAnimator valueAnimator2 = this.feedBackAnimator;
                            if (valueAnimator2 != null) {
                                valueAnimator2.setDuration(500L);
                            }
                            ValueAnimator valueAnimator3 = this.feedBackAnimator;
                            if (valueAnimator3 != null) {
                                springBack.1 r0 = new springBack.1();
                                r0.this$0 = this;
                                VarHandle.storeStoreFence();
                                valueAnimator3.addUpdateListener(r0);
                            }
                            ValueAnimator valueAnimator4 = this.feedBackAnimator;
                            if (valueAnimator4 != null) {
                                valueAnimator4.addListener(new springBack.2(this));
                            }
                            ValueAnimator valueAnimator5 = this.feedBackAnimator;
                            if (valueAnimator5 != null) {
                                valueAnimator5.start();
                            }
                        }
                        resetTouch();
                    }
                    if (this.isBeingDragged && this.overScrollHeight > 0.0f && (!isOnKeyguardAndExpanded() || QSFragmentHelper.getInstance().isFullExpanded())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void initMotionEvent(MotionEvent motionEvent, int i) {
        this.initialMotionX = motionEvent.getRawX(i);
        this.initialMotionY = motionEvent.getRawY(i);
        TransCalculator transCalculator = TransCalculator.INSTANCE;
        float fAsin = this.overScrollHeight;
        transCalculator.getClass();
        if (fAsin > 0.0f) {
            fAsin = fAsin <= ((float) ((Math.sin(((double) (0.0f / 0.0f)) * 1.5707963267948966d) * 0.0d) + 0.0d)) ? ((((float) Math.asin((fAsin - 0.0f) / 0.0f)) / 1.5707964f) * 0.0f) + 0.0f : 0.0f;
        }
        this.initialHeight = fAsin;
        ((LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class)).updateFraction(0.0f, false, false);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initMotionEvent, initialMotionY: " + this.initialMotionY + ", overScrollHeight: " + this.overScrollHeight, null, 8);
    }

    public final boolean isOnKeyguardAndExpanded() {
        return this.isKeyguardShowing && !this.showCollapsedOnKeyguard;
    }

    public final void resetTouch() {
        this.activePointerId = -1;
        this.isBeingDragged = false;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.velocityTracker = null;
    }

    public final void resetTouchState() {
        ValueAnimator valueAnimator = this.feedBackAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.overScrollHeight > 0.0f) {
            this.overScrollHeight = 0.0f;
            scrollPanel();
        }
        resetTouch();
    }

    public final void scrollPanel() {
        OplusQSContainerImplController controller;
        OplusQSContainerImpl qsContainer = QSFragmentHelper.getInstance().getQsContainer();
        if (qsContainer == null || (controller = qsContainer.getController()) == null) {
            return;
        }
        controller.mPanelHeight = this.overScrollHeight;
        controller.updatePosition$2();
    }

    public boolean updateIsBeingDraggedOnTouchDown(MotionEvent motionEvent) {
        Resources resources;
        Context context = this.context;
        boolean z = false;
        if (context != null && (resources = context.getResources()) != null && LargeScreenUtils.shouldUseSplitNotificationShade(resources)) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (enableOverScroll()) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.activePointerId);
                if (iFindPointerIndex < 0) {
                    this.activePointerId = motionEvent.getPointerId(0);
                    iFindPointerIndex = 0;
                }
                initMotionEvent(motionEvent, iFindPointerIndex);
            } else {
                this.initialMotionY = -1000.0f;
            }
            ValueAnimator valueAnimator = this.feedBackAnimator;
            if (valueAnimator != null ? valueAnimator.isRunning() : false) {
                ValueAnimator valueAnimator2 = this.feedBackAnimator;
                if (valueAnimator2 != null) {
                    valueAnimator2.cancel();
                }
                z = true;
            }
            this.isBeingDragged = z;
        } else if (action == 1 || action == 3) {
            resetTouch();
            return false;
        }
        return this.isBeingDragged;
    }

    public final void updatePanelExpansion(float f) {
        this.panelExpansion = f;
    }

    public final void updateQSState(boolean z, boolean z2) {
        this.isKeyguardShowing = z;
        this.showCollapsedOnKeyguard = z2;
        resetTouchState();
    }
}
