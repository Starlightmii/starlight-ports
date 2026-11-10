package com.oplus.systemui.qs.utils;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import com.coui.appcompat.scroll.COUIIOverScroller;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpringOverScroller extends OverScroller implements COUIIOverScroller {
    public static float sRefreshTime = 0.016f;
    public Interpolator mInterpolator;
    public int mMode;
    public final ReboundOverScroller mScrollerX;
    public final ReboundOverScroller mScrollerY;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ColorViscousFluidInterpolator implements Interpolator {
        public static final float VISCOUS_FLUID_NORMALIZE;
        public static final float VISCOUS_FLUID_OFFSET;

        static {
            float fViscousFluid = 1.0f / viscousFluid(1.0f);
            VISCOUS_FLUID_NORMALIZE = fViscousFluid;
            VISCOUS_FLUID_OFFSET = 1.0f - (viscousFluid(1.0f) * fViscousFluid);
        }

        public static float viscousFluid(float f) {
            float f2 = f * 8.0f;
            return f2 < 1.0f ? f2 - (1.0f - ((float) Math.exp(-f2))) : DrawerArrowDrawable$.ExternalSyntheticOutline0.m(1.0f, (float) Math.exp(1.0f - f2), 0.63212055f, 0.36787945f);
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float fViscousFluid = viscousFluid(f) * VISCOUS_FLUID_NORMALIZE;
            return fViscousFluid > 0.0f ? fViscousFluid + VISCOUS_FLUID_OFFSET : fViscousFluid;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ReboundOverScroller {
        public static float sTimeIncrease = 1.0f;
        public ReboundConfig mConfig;
        public double mEndValue;
        public final ReboundConfig mFlingConfig;
        public boolean mIsSpringBack;
        public int mScrollDuration;
        public int mScrollFinal;
        public int mScrollStart;
        public long mScrollStartTime;
        public final ReboundConfig mSpringBackConfig;
        public double mStartValue;
        public boolean mTensionAdjusted;
        public final PhysicsState mCurrentState = new PhysicsState();
        public final PhysicsState mPreviousState = new PhysicsState();
        public final PhysicsState mTempState = new PhysicsState();
        public float mFlingFriction = 1.06f;
        public int mCount = 1;
        public boolean mIsScrollView = false;
        public float mSpringBackTensionMultiple = 2.15f;
        public float mSpringBackFriction = 20.0f;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class PhysicsState {
            public double mPosition;
            public double mVelocity;
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class ReboundConfig {
            public double mFriction;
            public double mTension;

            public ReboundConfig(double d, double d2) {
                float f = (float) d;
                this.mFriction = f == 0.0f ? 0.0f : DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f, 8.0f, 3.0f, 25.0f);
                float f2 = (float) d2;
                this.mTension = f2 == 0.0f ? 0.0d : DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f2, 30.0f, 3.62f, 194.0f);
            }

            public void setFriction(double d) {
                float f = (float) d;
                this.mFriction = f != 0.0f ? DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f, 8.0f, 3.0f, 25.0f) : 0.0f;
            }

            public void setTension(double d) {
                float f = (float) d;
                this.mTension = f == 0.0f ? 0.0d : DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f, 30.0f, 3.62f, 194.0f);
            }
        }

        public ReboundOverScroller() {
            ReboundConfig reboundConfig = new ReboundConfig(1.059999942779541d, 0.0d);
            this.mFlingConfig = reboundConfig;
            this.mSpringBackConfig = new ReboundConfig(20.0d, 30.0d);
            setConfig(reboundConfig);
        }

        public final void fling(int i, int i2) {
            this.mCount = 1;
            sTimeIncrease = 1.0f;
            double d = this.mFlingFriction;
            ReboundConfig reboundConfig = this.mFlingConfig;
            reboundConfig.setFriction(d);
            reboundConfig.setTension(0.0d);
            setConfig(reboundConfig);
            double d2 = i;
            this.mStartValue = d2;
            if (!this.mIsScrollView) {
                this.mPreviousState.mPosition = 0.0d;
                this.mTempState.mPosition = 0.0d;
            }
            this.mCurrentState.mPosition = d2;
            setAtRest();
            setVelocity(i2);
        }

        public double getCurrentValue() {
            return this.mCurrentState.mPosition;
        }

        public double getEndValue() {
            return this.mEndValue;
        }

        public double getVelocity() {
            return this.mCurrentState.mVelocity;
        }

        public final boolean isAtRest() {
            PhysicsState physicsState = this.mCurrentState;
            if (Math.abs(physicsState.mVelocity) <= 100.0d) {
                return Math.abs(this.mEndValue - physicsState.mPosition) <= 0.05d || this.mConfig.mTension == 0.0d;
            }
            return false;
        }

        public final void setAtRest() {
            PhysicsState physicsState = this.mCurrentState;
            double d = physicsState.mPosition;
            this.mEndValue = d;
            this.mTempState.mPosition = d;
            physicsState.mVelocity = 0.0d;
            this.mIsSpringBack = false;
        }

        public final void setConfig(ReboundConfig reboundConfig) {
            if (reboundConfig != null) {
                this.mConfig = reboundConfig;
            } else {
                Path$.ExternalSyntheticBUOutline0.m("springConfig is required");
            }
        }

        public void setEndValue(double d) {
            if (this.mEndValue == d) {
                return;
            }
            this.mStartValue = getCurrentValue();
            this.mEndValue = d;
        }

        public void setVelocity(double d) {
            PhysicsState physicsState = this.mCurrentState;
            if (Math.abs(d - physicsState.mVelocity) < 9.999999747378752E-5d) {
                return;
            }
            physicsState.mVelocity = d;
        }

        public final boolean springBack(int i, int i2, int i3) {
            double d = i;
            this.mStartValue = d;
            if (!this.mIsScrollView) {
                this.mPreviousState.mPosition = 0.0d;
                this.mTempState.mPosition = 0.0d;
            }
            this.mCurrentState.mPosition = d;
            if (i <= i3 && i >= i2) {
                setConfig(new ReboundConfig(this.mFlingFriction, 0.0d));
                return false;
            }
            if (i > i3) {
                setEndValue(i3);
            } else if (i < i2) {
                setEndValue(i2);
            }
            this.mIsSpringBack = true;
            double d2 = this.mSpringBackFriction;
            ReboundConfig reboundConfig = this.mSpringBackConfig;
            reboundConfig.setFriction(d2);
            reboundConfig.setTension(this.mSpringBackTensionMultiple * 30.0f);
            setConfig(reboundConfig);
            return true;
        }

        public final boolean update() {
            double d;
            if (isAtRest()) {
                return false;
            }
            PhysicsState physicsState = this.mCurrentState;
            double d2 = physicsState.mPosition;
            double d3 = physicsState.mVelocity;
            PhysicsState physicsState2 = this.mTempState;
            double d4 = physicsState2.mPosition;
            if (this.mIsSpringBack) {
                d = 2.0d;
                double dAbs = Math.abs(this.mEndValue - d2);
                if (!this.mTensionAdjusted && dAbs < 180.0d) {
                    this.mConfig.mTension += 100.0d;
                    this.mTensionAdjusted = true;
                } else if (dAbs < 2.0d) {
                    physicsState.mPosition = this.mEndValue;
                    this.mTensionAdjusted = false;
                    this.mIsSpringBack = false;
                    return false;
                }
            } else if (this.mCount < 60) {
                sTimeIncrease += 0.020000001f;
                this.mConfig.mFriction += 0.020000001415610313d;
                d = 2.0d;
            } else {
                float f = sTimeIncrease;
                float f2 = f - ((f - 0.6f) / 60.0f);
                sTimeIncrease = f2;
                d = 2.0d;
                this.mConfig.mFriction -= (double) ((f2 - 0.6f) / 60.0f);
            }
            ReboundConfig reboundConfig = this.mConfig;
            double d5 = reboundConfig.mTension;
            double d6 = this.mEndValue;
            double d7 = reboundConfig.mFriction;
            double d8 = ((d6 - d4) * d5) - (this.mPreviousState.mVelocity * d7);
            double d9 = SpringOverScroller.sRefreshTime;
            double d10 = ((d8 * d9) / d) + d3;
            double d11 = ((d6 - (((d3 * d9) / d) + d2)) * d5) - (d7 * d10);
            double d12 = ((d11 * d9) / d) + d3;
            double d13 = ((d6 - (((d10 * d9) / d) + d2)) * d5) - (d7 * d12);
            double d14 = (d12 * d9) + d2;
            double d15 = (d13 * d9) + d3;
            physicsState2.mVelocity = d15;
            physicsState2.mPosition = d14;
            physicsState.mVelocity = ((((d11 + d13) * d) + d8 + (((d6 - d14) * d5) - (d7 * d15))) * 0.16699999570846558d * d9) + d3;
            physicsState.mPosition = ((((d10 + d12) * d) + d3 + d15) * 0.16699999570846558d * d9) + d2;
            this.mCount++;
            return true;
        }

        public final void updateScroll(float f) {
            int i = this.mScrollStart;
            this.mCurrentState.mPosition = Math.round(f * (this.mScrollFinal - i)) + i;
        }
    }

    public SpringOverScroller(Context context) {
        super(context, null);
        this.mMode = 2;
        this.mScrollerX = new ReboundOverScroller();
        this.mScrollerY = new ReboundOverScroller();
        this.mInterpolator = new ColorViscousFluidInterpolator();
    }

    @Override // android.widget.OverScroller
    public final void abortAnimation() {
        this.mMode = 2;
        this.mScrollerX.setAtRest();
        this.mScrollerY.setAtRest();
    }

    @Override // android.widget.OverScroller
    public final boolean computeScrollOffset() {
        if (isCOUIFinished()) {
            return false;
        }
        int i = this.mMode;
        if (i != 0) {
            if (i == 1 && !this.mScrollerX.update() && !this.mScrollerY.update()) {
                abortAnimation();
            }
            return true;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        ReboundOverScroller reboundOverScroller = this.mScrollerX;
        long j = jCurrentAnimationTimeMillis - reboundOverScroller.mScrollStartTime;
        int i2 = reboundOverScroller.mScrollDuration;
        if (j < i2) {
            float interpolation = this.mInterpolator.getInterpolation(j / i2);
            this.mScrollerX.updateScroll(interpolation);
            this.mScrollerY.updateScroll(interpolation);
            return true;
        }
        reboundOverScroller.updateScroll(1.0f);
        this.mScrollerY.updateScroll(1.0f);
        abortAnimation();
        return true;
    }

    @Override // android.widget.OverScroller
    public final void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        if (i2 > i8 || i2 < i7) {
            springBack(i, i2, i5, i6, i7, i8);
        } else {
            fling(i, i2, i3, i4, i5, i6, i7, i8);
        }
    }

    public int getCOUICurrX() {
        return (int) Math.round(this.mScrollerX.getCurrentValue());
    }

    public int getCOUICurrY() {
        return (int) Math.round(this.mScrollerY.getCurrentValue());
    }

    public int getCOUIFinalX() {
        return (int) this.mScrollerX.getEndValue();
    }

    public int getCOUIFinalY() {
        return (int) this.mScrollerY.getEndValue();
    }

    @Override // android.widget.OverScroller
    public float getCurrVelocity() {
        double velocity = this.mScrollerX.getVelocity();
        double velocity2 = this.mScrollerY.getVelocity();
        return (int) Math.sqrt((velocity2 * velocity2) + (velocity * velocity));
    }

    public float getCurrVelocityX() {
        return (float) this.mScrollerX.getVelocity();
    }

    public float getCurrVelocityY() {
        return (float) this.mScrollerY.getVelocity();
    }

    public final boolean isCOUIFinished() {
        return this.mScrollerX.isAtRest() && this.mScrollerY.isAtRest() && this.mMode != 0;
    }

    public final boolean isScrollingInDirection(float f, float f2) {
        ReboundOverScroller reboundOverScroller = this.mScrollerX;
        int i = (int) (reboundOverScroller.mEndValue - reboundOverScroller.mStartValue);
        ReboundOverScroller reboundOverScroller2 = this.mScrollerY;
        return !isFinished() && Math.signum(f) == Math.signum((float) i) && Math.signum(f2) == Math.signum((float) ((int) (reboundOverScroller2.mEndValue - reboundOverScroller2.mStartValue)));
    }

    @Override // android.widget.OverScroller
    public final void notifyHorizontalEdgeReached(int i, int i2, int i3) {
        ReboundOverScroller reboundOverScroller = this.mScrollerX;
        reboundOverScroller.mCurrentState.mPosition = i;
        ReboundOverScroller.PhysicsState physicsState = reboundOverScroller.mPreviousState;
        physicsState.mPosition = 0.0d;
        physicsState.mVelocity = 0.0d;
        ReboundOverScroller.PhysicsState physicsState2 = reboundOverScroller.mTempState;
        physicsState2.mPosition = 0.0d;
        physicsState2.mVelocity = 0.0d;
        springBack(i, 0, 0, 0, 0, 0);
    }

    @Override // android.widget.OverScroller
    public final void notifyVerticalEdgeReached(int i, int i2, int i3) {
        ReboundOverScroller reboundOverScroller = this.mScrollerY;
        reboundOverScroller.mCurrentState.mPosition = i;
        ReboundOverScroller.PhysicsState physicsState = reboundOverScroller.mPreviousState;
        physicsState.mPosition = 0.0d;
        physicsState.mVelocity = 0.0d;
        ReboundOverScroller.PhysicsState physicsState2 = reboundOverScroller.mTempState;
        physicsState2.mPosition = 0.0d;
        physicsState2.mVelocity = 0.0d;
        springBack(0, i, 0, 0, 0, 0);
    }

    public void setCOUIFriction(float f) {
    }

    public void setCurrVelocityX(float f) {
        this.mScrollerX.mCurrentState.mVelocity = f;
    }

    public void setCurrVelocityY(float f) {
        this.mScrollerY.mCurrentState.mVelocity = f;
    }

    public void setDurationRatio(float f) {
    }

    public void setFinalX(int i) {
    }

    public void setFinalY(int i) {
    }

    public void setFlingFriction(float f) {
        this.mScrollerX.mFlingFriction = f;
        this.mScrollerY.mFlingFriction = f;
    }

    public void setInterpolator(Interpolator interpolator) {
        if (interpolator == null) {
            this.mInterpolator = new ColorViscousFluidInterpolator();
        } else {
            this.mInterpolator = interpolator;
        }
    }

    public void setIsScrollView(boolean z) {
        this.mScrollerX.mIsScrollView = z;
        this.mScrollerY.mIsScrollView = z;
    }

    public void setRefreshRate(float f) {
        sRefreshTime = Math.round(10000.0f / f) / 10000.0f;
    }

    public void setSpringBackFriction(float f) {
        this.mScrollerX.mSpringBackFriction = f;
        this.mScrollerY.mSpringBackFriction = f;
    }

    public void setSpringBackTensionMultiple(float f) {
        this.mScrollerX.mSpringBackTensionMultiple = f;
        this.mScrollerY.mSpringBackTensionMultiple = f;
    }

    public void setVelocityXRatio(float f) {
    }

    public void setVelocityYRatio(float f) {
    }

    @Override // android.widget.OverScroller
    public final boolean springBack(int i, int i2, int i3, int i4, int i5, int i6) {
        boolean zSpringBack = this.mScrollerX.springBack(i, i3, i4);
        boolean zSpringBack2 = this.mScrollerY.springBack(i2, i5, i6);
        if (zSpringBack || zSpringBack2) {
            this.mMode = 1;
        }
        return zSpringBack || zSpringBack2;
    }

    @Override // android.widget.OverScroller
    public final void startScroll(int i, int i2, int i3, int i4, int i5) {
        this.mMode = 0;
        ReboundOverScroller reboundOverScroller = this.mScrollerX;
        reboundOverScroller.mScrollStart = i;
        reboundOverScroller.mScrollFinal = i + i3;
        reboundOverScroller.mScrollDuration = i5;
        reboundOverScroller.mScrollStartTime = AnimationUtils.currentAnimationTimeMillis();
        reboundOverScroller.setConfig(reboundOverScroller.mFlingConfig);
        ReboundOverScroller reboundOverScroller2 = this.mScrollerY;
        reboundOverScroller2.mScrollStart = i2;
        reboundOverScroller2.mScrollFinal = i2 + i4;
        reboundOverScroller2.mScrollDuration = i5;
        reboundOverScroller2.mScrollStartTime = AnimationUtils.currentAnimationTimeMillis();
        reboundOverScroller2.setConfig(reboundOverScroller2.mFlingConfig);
    }

    @Override // android.widget.OverScroller
    public final void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mMode = 1;
        this.mScrollerX.fling(i, i3);
        this.mScrollerY.fling(i2, i4);
    }

    @Override // android.widget.OverScroller
    public final void startScroll(int i, int i2, int i3, int i4) {
        startScroll(i, i2, i3, i4, 250);
    }
}
