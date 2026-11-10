package com.oplus.systemui.qs.base.widget.recyclerview;

import androidx.compose.animation.ChangeSize$;
import com.oplusos.systemui.common.olivelink.Spring;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusItemSpringAnimationHolder$AnimationConfig {
    public float finalPosition;
    private Spring springConfig;
    private Float startValue;

    public OplusItemSpringAnimationHolder$AnimationConfig(Float f, float f2, Spring spring) {
        this.startValue = f;
        this.finalPosition = f2;
        this.springConfig = spring;
    }

    public final Float component1() {
        return this.startValue;
    }

    public final Spring component3() {
        return this.springConfig;
    }

    public final OplusItemSpringAnimationHolder$AnimationConfig copy(Float f, float f2, Spring spring) {
        return new OplusItemSpringAnimationHolder$AnimationConfig(f, f2, spring);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusItemSpringAnimationHolder$AnimationConfig)) {
            return false;
        }
        OplusItemSpringAnimationHolder$AnimationConfig oplusItemSpringAnimationHolder$AnimationConfig = (OplusItemSpringAnimationHolder$AnimationConfig) obj;
        return Intrinsics.areEqual(this.startValue, oplusItemSpringAnimationHolder$AnimationConfig.startValue) && Float.compare(this.finalPosition, oplusItemSpringAnimationHolder$AnimationConfig.finalPosition) == 0 && Intrinsics.areEqual(this.springConfig, oplusItemSpringAnimationHolder$AnimationConfig.springConfig);
    }

    public final float getFinalPosition() {
        return this.finalPosition;
    }

    public final Spring getSpringConfig() {
        return this.springConfig;
    }

    public final Float getStartValue() {
        return this.startValue;
    }

    public final int hashCode() {
        Float f = this.startValue;
        return this.springConfig.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.finalPosition, (f == null ? 0 : f.hashCode()) * 31, 31);
    }

    public final void setFinalPosition(float f) {
        this.finalPosition = f;
    }

    public final void setSpringConfig(Spring spring) {
        this.springConfig = spring;
    }

    public final void setStartValue(Float f) {
        this.startValue = f;
    }

    public String toString() {
        return "AnimationConfig(startValue=" + this.startValue + ", finalPosition=" + this.finalPosition + ", springConfig=" + this.springConfig + ")";
    }

    public /* synthetic */ OplusItemSpringAnimationHolder$AnimationConfig(Spring spring) {
        this(null, 0.0f, spring);
    }
}
