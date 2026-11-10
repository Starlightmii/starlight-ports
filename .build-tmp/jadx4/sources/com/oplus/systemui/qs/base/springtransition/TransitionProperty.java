package com.oplus.systemui.qs.base.springtransition;

import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TransitionProperty {
    public static final Companion Companion = new Companion();
    public Object endValue;
    public final Object invalidValue;
    public Object startValue;
    private final WeakReference<Object> targetObjectWeakRef;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int hashCode(Class<?> cls, Object obj) {
            return obj.hashCode() + (cls.hashCode() * 31);
        }
    }

    public TransitionProperty(Object obj, Object obj2) {
        this.invalidValue = obj2;
        this.targetObjectWeakRef = new WeakReference<>(obj);
        this.startValue = obj2;
        this.endValue = obj2;
    }

    public void captureEndValue() {
        this.endValue = getValue();
    }

    public void captureStartValue() {
        this.startValue = getValue();
    }

    public boolean equals(Object obj) {
        if (!Intrinsics.areEqual(obj != null ? obj.getClass() : null, getClass())) {
            return false;
        }
        TransitionProperty transitionProperty = obj instanceof TransitionProperty ? (TransitionProperty) obj : null;
        return Intrinsics.areEqual(transitionProperty != null ? transitionProperty.getTargetObject() : null, getTargetObject());
    }

    public final Object getEndValue() {
        return this.endValue;
    }

    public final Object getInvalidValue() {
        return this.invalidValue;
    }

    public final Object getStartValue() {
        return this.startValue;
    }

    public final Object getTargetObject() {
        return this.targetObjectWeakRef.get();
    }

    public abstract Object getValue();

    public final int hashCode() {
        int iHashCode = getClass().hashCode();
        Object targetObject = getTargetObject();
        if (targetObject == null) {
            return iHashCode;
        }
        return targetObject.hashCode() + (iHashCode * 31);
    }

    public boolean isValid() {
        if (getTargetObject() == null) {
            return false;
        }
        Object obj = this.startValue;
        Object obj2 = this.invalidValue;
        return (Intrinsics.areEqual(obj, obj2) || Intrinsics.areEqual(this.endValue, obj2) || Intrinsics.areEqual(this.startValue, this.endValue)) ? false : true;
    }

    public void onTransitionEnd(boolean z) {
        if (z || Intrinsics.areEqual(this.invalidValue, this.endValue)) {
            return;
        }
        setValue(this.endValue);
    }

    public final void setEndValue(Object obj) {
        this.endValue = obj;
    }

    public abstract void setProgress(float f);

    public final void setStartValue(Object obj) {
        this.startValue = obj;
    }

    public abstract void setValue(Object obj);

    public String toString() {
        ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
        String strSimpleNameAndHash = companion.simpleNameAndHash(this);
        Object targetObject = getTargetObject();
        return strSimpleNameAndHash + "[" + (targetObject != null ? companion.simpleNameAndHash(targetObject) : null) + OplusPreventModeData.SEPARATOR + this.startValue + OplusPreventModeData.SEPARATOR + this.endValue + "]";
    }
}
