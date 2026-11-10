package com.oplus.systemui.notification.lockscreen.seedling;

import android.util.Log;
import android.util.Property;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ImmersiveViewEntry {
    public static final Companion Companion = new Companion();
    private static final Property<ImmersiveViewEntry, Integer> IMMERSIVE_PROPERTY = new ImmersiveViewEntry$Companion$IMMERSIVE_PROPERTY$1("IMMERSIVE_PROPERTY");
    private static final String TAG = "ImmersiveBgEntry";
    private AnimationInfo animatorInfo;
    private final CustomRowEntry entry;
    public int immersiveState;
    private final SeedlingImmersiveViewState viewState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimationInfo {
        private final SeedingImmersiveAnimator animator;
        private Runnable delayRunnable;
        public int endExtState;
        public int endState;

        public AnimationInfo(int i, int i2, SeedingImmersiveAnimator seedingImmersiveAnimator, Runnable runnable) {
            this.endState = i;
            this.endExtState = i2;
            this.animator = seedingImmersiveAnimator;
            this.delayRunnable = runnable;
        }

        public final SeedingImmersiveAnimator component3() {
            return this.animator;
        }

        public final Runnable component4() {
            return this.delayRunnable;
        }

        public final AnimationInfo copy(int i, int i2, SeedingImmersiveAnimator seedingImmersiveAnimator, Runnable runnable) {
            return new AnimationInfo(i, i2, seedingImmersiveAnimator, runnable);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimationInfo)) {
                return false;
            }
            AnimationInfo animationInfo = (AnimationInfo) obj;
            return this.endState == animationInfo.endState && this.endExtState == animationInfo.endExtState && Intrinsics.areEqual(this.animator, animationInfo.animator) && Intrinsics.areEqual(this.delayRunnable, animationInfo.delayRunnable);
        }

        public final SeedingImmersiveAnimator getAnimator() {
            return this.animator;
        }

        public final Runnable getDelayRunnable() {
            return this.delayRunnable;
        }

        public final int getEndExtState() {
            return this.endExtState;
        }

        public final int getEndState() {
            return this.endState;
        }

        public final int hashCode() {
            int iHashCode = (this.animator.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.endExtState, Integer.hashCode(this.endState) * 31, 31)) * 31;
            Runnable runnable = this.delayRunnable;
            return iHashCode + (runnable == null ? 0 : runnable.hashCode());
        }

        public final void setDelayRunnable(Runnable runnable) {
            this.delayRunnable = runnable;
        }

        public final void setEndExtState(int i) {
            this.endExtState = i;
        }

        public final void setEndState(int i) {
            this.endState = i;
        }

        public String toString() {
            int i = this.endState;
            int i2 = this.endExtState;
            SeedingImmersiveAnimator seedingImmersiveAnimator = this.animator;
            Runnable runnable = this.delayRunnable;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("AnimationInfo(endState=", i, i2, ", endExtState=", ", animator=");
            sbM.append(seedingImmersiveAnimator);
            sbM.append(", delayRunnable=");
            sbM.append(runnable);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Property<ImmersiveViewEntry, Integer> getIMMERSIVE_PROPERTY() {
            return ImmersiveViewEntry.IMMERSIVE_PROPERTY;
        }
    }

    public ImmersiveViewEntry(CustomRowEntry customRowEntry, SeedlingImmersiveViewState seedlingImmersiveViewState) {
        this.entry = customRowEntry;
        this.viewState = seedlingImmersiveViewState;
    }

    public final AnimationInfo getAnimatorInfo() {
        return this.animatorInfo;
    }

    public CustomRowEntry getEntry() {
        return this.entry;
    }

    public final int getImmersiveState() {
        return this.immersiveState;
    }

    public final String getKey() {
        return getEntry().getKey();
    }

    public SeedlingImmersiveViewState getViewState() {
        return this.viewState;
    }

    public final void setAnimatorInfo(AnimationInfo animationInfo) {
        this.animatorInfo = animationInfo;
    }

    public final void setImmersiveState(int i) {
        if (this.immersiveState != i) {
            this.immersiveState = i;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ImmersiveBgEntry-->".concat(getKey() + " type =" + getViewState().getCardType() + ",immersiveState= " + i + " " + hashCode()));
            }
        }
    }
}
