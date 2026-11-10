package com.oplus.systemui.panelanimation;

import android.view.animation.BaseInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpringInterpolator extends BaseInterpolator {
    public final double mAngularFreq;
    public final float mCutRatio;
    public final double mDampingRatio;
    public float mFinalValue = -1.0f;
    public final double mImpulse;
    public final double mInitialVel;
    public final double mUnDampedAngularFreq;

    public SpringInterpolator(double d, double d2, double d3, float f) {
        double dPow = Math.pow(6.283185307179586d / d, 2.0d);
        double d4 = 1.0d - d2;
        double dSqrt = Math.sqrt(dPow <= 0.0d ? 40.0d : dPow);
        this.mUnDampedAngularFreq = dSqrt;
        d4 = d4 <= 0.0d ? 1.15d : d4;
        this.mDampingRatio = d4;
        double dAbs = Math.abs(d3) / ((double) (f <= 0.0f ? 15000.0f : f));
        this.mInitialVel = dAbs;
        this.mCutRatio = 1.0f;
        if (d4 < 1.0d) {
            double dSqrt2 = Math.sqrt(1.0d - (d4 * d4)) * dSqrt;
            this.mAngularFreq = dSqrt2;
            this.mImpulse = ((d4 * dSqrt) - dAbs) / dSqrt2;
        } else if (Double.compare(1.0d, d4) == 0) {
            this.mImpulse = (-dAbs) + dSqrt;
        } else {
            this.mImpulse = (d4 * dSqrt) + (-dAbs);
        }
    }

    public float getCutRatio() {
        return this.mCutRatio;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        if (this.mFinalValue == -1.0f) {
            float originInterpolation = getOriginInterpolation(1.0f);
            this.mFinalValue = originInterpolation != 0.0f ? originInterpolation : 1.0f;
        }
        return getOriginInterpolation(f) / this.mFinalValue;
    }

    public final float getOriginInterpolation(float f) {
        double dCosh;
        float f2 = (f >= 0.0f ? f : 0.0f) * this.mCutRatio;
        double d = f2;
        double dExp = Math.exp((-this.mDampingRatio) * this.mUnDampedAngularFreq * d);
        double d2 = this.mDampingRatio;
        if (d2 < 1.0d) {
            dCosh = ((Math.sin(this.mAngularFreq * d) * this.mImpulse) + Math.cos(this.mAngularFreq * d)) * dExp;
        } else if (Double.compare(1.0d, d2) == 0) {
            dCosh = ExternalSyntheticOutline0.m(-f2, this.mUnDampedAngularFreq, (this.mImpulse * d) + 1.0d);
        } else {
            double d3 = this.mUnDampedAngularFreq;
            double d4 = this.mDampingRatio;
            double dSqrt = Math.sqrt((d4 * d4) - 1.0d) * d3;
            double d5 = ((double) f) * dSqrt;
            dCosh = ((Math.cosh(d5) * dSqrt) + (Math.sinh(d5) * ((this.mDampingRatio * this.mUnDampedAngularFreq) + (-this.mInitialVel)))) * (dExp / dSqrt);
        }
        return (float) (1.0d - dCosh);
    }
}
