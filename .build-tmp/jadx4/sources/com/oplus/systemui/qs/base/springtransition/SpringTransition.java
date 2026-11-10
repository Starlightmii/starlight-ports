package com.oplus.systemui.qs.base.springtransition;

import android.util.Log;
import android.util.SparseArray;
import androidx.car.app.SurfaceContainer$;
import androidx.car.app.serialization.Bundler$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.core.util.SparseArrayKt;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SpringTransition {
    public static final Companion Companion = new Companion();
    private final List<Integer> _allSkipToEndPropertyHash;
    private final SparseArray<TransitionProperty> _allTargetProperties = new SparseArray<>();
    private final List<Integer> allSkipToEndPropertyHash;
    public float endValue;
    private final COUISpringAnimation springAnimation;
    public float startValue;
    private final String transitionName;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final <T> List<T> toList(Iterator<? extends T> it) {
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList;
        }
    }

    public SpringTransition(String str) {
        this.transitionName = str;
        ArrayList arrayList = new ArrayList();
        this._allSkipToEndPropertyHash = arrayList;
        this.allSkipToEndPropertyHash = arrayList;
        this.startValue = Float.MIN_VALUE;
        this.endValue = Float.MIN_VALUE;
        COUISpringAnimation cOUISpringAnimationCreateSpringAnimation = createSpringAnimation();
        this.springAnimation = cOUISpringAnimationCreateSpringAnimation;
        SpringTransition$1$1 springTransition$1$1 = new SpringTransition$1$1();
        springTransition$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimationCreateSpringAnimation.addUpdateListener(springTransition$1$1);
        SpringTransition$1$2 springTransition$1$2 = new SpringTransition$1$2();
        springTransition$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimationCreateSpringAnimation.addEndListener(springTransition$1$2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTag() {
        return toString();
    }

    public final void addOnEndListener(COUIDynamicAnimation.OnAnimationEndListener onAnimationEndListener) {
        this.springAnimation.addEndListener(onAnimationEndListener);
    }

    public final void addSkipToEndPropertyHash(int i) {
        this._allSkipToEndPropertyHash.add(Integer.valueOf(i));
    }

    public final void addTargetProperty(TransitionProperty transitionProperty) {
        this._allTargetProperties.put(transitionProperty.hashCode(), transitionProperty);
    }

    public final void addUpdateListener(COUIDynamicAnimation.OnAnimationUpdateListener onAnimationUpdateListener) {
        this.springAnimation.addUpdateListener(onAnimationUpdateListener);
    }

    public final boolean animationToFinalPosition(float f) {
        this.endValue = f;
        releaseInvalidProperty();
        if (this._allTargetProperties.size() == 0) {
            return false;
        }
        float f2 = this.startValue;
        float f3 = this.endValue;
        if (f2 == f3) {
            return false;
        }
        if (f2 != Float.MIN_VALUE && f3 != Float.MIN_VALUE && f3 != f2) {
            f2 = (f2 - f2) / (f3 - f2);
        }
        Iterator<TransitionProperty> allTargetProperties = getAllTargetProperties();
        while (allTargetProperties.hasNext()) {
            allTargetProperties.next().setProgress(f2);
        }
        setStartValue(this.startValue);
        this.springAnimation.animateToFinalPosition(f);
        String tag = getTag();
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            return true;
        }
        StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
        sbM.append("[animationToFinalPosition] " + f);
        Log.d("SystemUi--Common", sbM.toString());
        return true;
    }

    public final void cancel() {
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag, "-->[cancel]", "SystemUi--Common");
        }
        this.springAnimation.cancel();
    }

    public final void captureStartValues(float f) {
        if (((COUIDynamicAnimation) this.springAnimation).mRunning) {
            f = getCurrAnimationValue();
        }
        this.startValue = f;
        Iterator<TransitionProperty> allTargetProperties = getAllTargetProperties();
        while (allTargetProperties.hasNext()) {
            allTargetProperties.next().captureStartValue();
        }
    }

    public final void clearSkipToEndPropertyHash() {
        this._allSkipToEndPropertyHash.clear();
    }

    public abstract COUISpringAnimation createSpringAnimation();

    public boolean equals(Object obj) {
        return (obj instanceof SpringTransition) && Intrinsics.areEqual(this.transitionName, ((SpringTransition) obj).transitionName);
    }

    public final List<Integer> getAllSkipToEndPropertyHash() {
        return this.allSkipToEndPropertyHash;
    }

    public final Iterator<TransitionProperty> getAllTargetProperties() {
        return SparseArrayKt.valueIterator(this._allTargetProperties);
    }

    public final float getCurrAnimationValue() {
        return ((Float) ReflectionUtils.methodInvoke(this.springAnimation, COUIDynamicAnimation.class, "getPropertyValue", null, new Object[0])).floatValue();
    }

    public final String getTransitionName() {
        return this.transitionName;
    }

    public final int hashCode() {
        return this.transitionName.hashCode() + ("SpringTransition_".hashCode() * 31);
    }

    public final boolean isRunning() {
        return ((COUIDynamicAnimation) this.springAnimation).mRunning;
    }

    public final void releaseInvalidProperty() {
        for (TransitionProperty transitionProperty : Companion.toList(getAllTargetProperties())) {
            if (!transitionProperty.isValid()) {
                String tag = getTag();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
                    sbM.append("[releaseInvalidProperty] invalid property: " + transitionProperty);
                    Log.d("SystemUi--Common", sbM.toString());
                }
                this._allTargetProperties.remove(transitionProperty.hashCode());
            }
        }
    }

    public final void removeOnEndListener(COUIDynamicAnimation.OnAnimationEndListener onAnimationEndListener) {
        this.springAnimation.removeEndListener(onAnimationEndListener);
    }

    public final boolean removeTargetProperty(int i, boolean z) {
        TransitionProperty transitionProperty = this._allTargetProperties.get(i);
        if (transitionProperty == null) {
            return false;
        }
        this._allTargetProperties.remove(i);
        if (!((COUIDynamicAnimation) this.springAnimation).mRunning) {
            return true;
        }
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            sbM.append("[removeTargetProperty] " + transitionProperty + " [cancel] " + z);
            Log.d("SystemUi--Common", sbM.toString());
        }
        transitionProperty.onTransitionEnd(z);
        return true;
    }

    public final void removeUpdateListener(COUIDynamicAnimation.OnAnimationUpdateListener onAnimationUpdateListener) {
        this.springAnimation.removeUpdateListener(onAnimationUpdateListener);
    }

    public final void reset() {
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag, "-->[reset]", "SystemUi--Common");
        }
        this.springAnimation.reset();
    }

    public final void setBounce(float f) {
        this.springAnimation.getSpring().setBounce(f);
    }

    public final void setResponse(float f) {
        this.springAnimation.getSpring().setResponse(f);
    }

    public final void setStartValue(float f) {
        this.springAnimation.setStartValue(f);
    }

    public final void setStartVelocity(float f) {
        this.springAnimation.setStartVelocity(f);
    }

    public String toString() {
        String str = this.transitionName;
        int iHashCode = hashCode();
        float f = this.startValue;
        float f2 = this.endValue;
        int size = this._allTargetProperties.size();
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(iHashCode, "SpringTransition.", str, "@", "{[");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", ", f2, "], ");
        return SurfaceContainer$.ExternalSyntheticOutline0.m(size, "}", sbM);
    }

    public final boolean removeTargetProperty(TransitionProperty transitionProperty, boolean z) {
        return removeTargetProperty(transitionProperty.hashCode(), z);
    }
}
