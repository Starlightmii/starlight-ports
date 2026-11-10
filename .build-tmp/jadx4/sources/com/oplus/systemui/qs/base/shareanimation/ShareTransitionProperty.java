package com.oplus.systemui.qs.base.shareanimation;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import androidx.car.app.serialization.Bundler$;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.google.protobuf.Internal$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ShareTransitionProperty {
    public static final Companion Companion = new Companion();
    public final Object defValue;
    private ViewGroup expectedFromHost;
    public boolean inOverlay;
    public final Object invalidValue;
    private Object savedValueWhenEnd;
    public final int sharedElementId;
    private final WeakReference<ViewGroup> targetViewHostWeakRef;
    public final String TAG = getClass().getSimpleName();
    private final Lazy valuesOfDiffState$delegate = LazyKt__LazyJVMKt.lazy(new ShareTransitionProperty$.ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void clearIdCache(ViewGroup viewGroup, int i) {
            viewGroup.setTag(i, null);
            Object value = ReflectionUtils.getValue(ViewOverlay.class, viewGroup.getOverlay(), "mOverlayViewGroup");
            ViewGroup viewGroup2 = value instanceof ViewGroup ? (ViewGroup) value : null;
            if (viewGroup2 != null) {
                viewGroup2.setTag(i, null);
            }
        }

        public final View findViewByIdUseCache(ViewGroup viewGroup, int i, boolean z) {
            ViewGroup viewGroup2;
            View viewFindViewById;
            Object tag = viewGroup.getTag(i);
            WeakReference weakReference = tag instanceof WeakReference ? (WeakReference) tag : null;
            View view = weakReference != null ? (View) weakReference.get() : null;
            if (view != null) {
                return view;
            }
            if (z) {
                Object value = ReflectionUtils.getValue(ViewOverlay.class, viewGroup.getOverlay(), "mOverlayViewGroup");
                viewGroup2 = value instanceof ViewGroup ? (ViewGroup) value : null;
                Object tag2 = viewGroup2 != null ? viewGroup2.getTag(i) : null;
                WeakReference weakReference2 = tag2 instanceof WeakReference ? (WeakReference) tag2 : null;
                View view2 = weakReference2 != null ? (View) weakReference2.get() : null;
                if (view2 != null) {
                    return view2;
                }
            } else {
                viewGroup2 = null;
            }
            View viewFindViewById2 = viewGroup.findViewById(i);
            if (viewFindViewById2 != null) {
                viewGroup.setTag(i, new WeakReference(viewFindViewById2));
                return viewFindViewById2;
            }
            if (!z || viewGroup2 == null || (viewFindViewById = viewGroup2.findViewById(i)) == null) {
                return null;
            }
            viewGroup2.setTag(i, new WeakReference(viewFindViewById));
            return viewFindViewById;
        }

        public final String simpleNameAndHash(Object obj) {
            return PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(obj.hashCode(), obj.getClass().getSimpleName(), "@");
        }
    }

    public ShareTransitionProperty(ViewGroup viewGroup, int i, Object obj, Object obj2) {
        this.sharedElementId = i;
        this.defValue = obj;
        this.invalidValue = obj2;
        this.targetViewHostWeakRef = new WeakReference<>(viewGroup);
    }

    public void captureValues(List<? extends ViewGroup> list) {
        Object value;
        getValuesOfDiffState().clear();
        for (ViewGroup viewGroup : list) {
            HashMap<WeakReference<ViewGroup>, Object> valuesOfDiffState = getValuesOfDiffState();
            WeakReference<ViewGroup> weakReference = new WeakReference<>(viewGroup);
            View viewFindViewByIdUseCache = Companion.findViewByIdUseCache(viewGroup, this.sharedElementId, false);
            if (viewFindViewByIdUseCache == null || (value = getValue(viewFindViewByIdUseCache)) == null) {
                value = this.invalidValue;
            }
            valuesOfDiffState.put(weakReference, value);
        }
    }

    public void ensureSharedElementAndInit(ViewGroup viewGroup) {
        Object next;
        Companion companion;
        View viewFindViewByIdUseCache;
        boolean normalDebug = LogUtil.getNormalDebug();
        String str = this.TAG;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            Companion companion2 = Companion;
            sbM.append("[ensureSharedElementInTargetHost]" + companion2.simpleNameAndHash(this) + " " + (viewGroup != null ? companion2.simpleNameAndHash(viewGroup) : null));
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.expectedFromHost = viewGroup;
        ViewGroup targetViewHost = getTargetViewHost();
        if (targetViewHost == null) {
            return;
        }
        Companion companion3 = Companion;
        int i = this.sharedElementId;
        if (companion3.findViewByIdUseCache(targetViewHost, i, true) != null) {
            viewGroup = targetViewHost;
        } else if (viewGroup == null) {
            Iterator<T> it = getValuesOfDiffState().keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                ViewGroup viewGroup2 = (ViewGroup) ((WeakReference) next).get();
                if (viewGroup2 != null && Companion.findViewByIdUseCache(viewGroup2, i, false) != null) {
                    break;
                }
            }
            WeakReference weakReference = (WeakReference) next;
            viewGroup = weakReference != null ? (ViewGroup) weakReference.get() : null;
        }
        if (viewGroup == null || (viewFindViewByIdUseCache = (companion = Companion).findViewByIdUseCache(viewGroup, i, true)) == null) {
            Path$.ExternalSyntheticBUOutline0.m$1(KeyguardLogger$.ExternalSyntheticOutline0.m("[ensureSharedElementInTargetHost] cannot find ", i, " child, ", Companion.simpleNameAndHash(this)));
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM2.append("[ensureSharedElementInTargetHost]" + companion.simpleNameAndHash(this) + " selectedFromHost=" + companion.simpleNameAndHash(viewGroup));
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        if (ViewOverlayManager.INSTANCE.moveChildToOverlay(viewFindViewByIdUseCache, viewGroup, targetViewHost)) {
            companion.clearIdCache(viewGroup, i);
        }
        Object tag = targetViewHost.getTag(i);
        WeakReference weakReference2 = tag instanceof WeakReference ? (WeakReference) tag : null;
        boolean z = (weakReference2 != null ? (View) weakReference2.get() : null) == null;
        this.inOverlay = z;
        initValue(targetViewHost, viewFindViewByIdUseCache, z);
    }

    public boolean equals(Object obj) {
        if (!Intrinsics.areEqual(obj != null ? obj.getClass() : null, getClass())) {
            return false;
        }
        boolean z = obj instanceof ShareTransitionProperty;
        ShareTransitionProperty shareTransitionProperty = z ? (ShareTransitionProperty) obj : null;
        if (!Intrinsics.areEqual(shareTransitionProperty != null ? shareTransitionProperty.getTargetViewHost() : null, getTargetViewHost())) {
            return false;
        }
        ShareTransitionProperty shareTransitionProperty2 = z ? (ShareTransitionProperty) obj : null;
        return shareTransitionProperty2 != null && shareTransitionProperty2.sharedElementId == this.sharedElementId;
    }

    public final Object getDefValue() {
        return this.defValue;
    }

    public final ViewGroup getExpectedFromHost() {
        return this.expectedFromHost;
    }

    public final boolean getInOverlay() {
        return this.inOverlay;
    }

    public final Object getInvalidValue() {
        return this.invalidValue;
    }

    public final Object getSavedValueWhenEnd() {
        return this.savedValueWhenEnd;
    }

    public final int getSharedElementId() {
        return this.sharedElementId;
    }

    public final ViewGroup getTargetViewHost() {
        return this.targetViewHostWeakRef.get();
    }

    public final Object getValue() {
        View viewFindViewByIdUseCache;
        Object value;
        ViewGroup targetViewHost = getTargetViewHost();
        return (targetViewHost == null || (viewFindViewByIdUseCache = Companion.findViewByIdUseCache(targetViewHost, this.sharedElementId, true)) == null || (value = getValue(viewFindViewByIdUseCache)) == null) ? this.invalidValue : value;
    }

    public abstract Object getValue(View view);

    public final Object getValueFrom(ViewGroup viewGroup) {
        Object next;
        Object value;
        Iterator<T> it = getValuesOfDiffState().entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((WeakReference) ((Map.Entry) next).getKey()).get(), viewGroup));
        Map.Entry entry = (Map.Entry) next;
        if (entry != null && (value = entry.getValue()) != null) {
            return value.equals(this.invalidValue) ? this.defValue : value;
        }
        Internal$.ExternalSyntheticBUOutline0.m("[getValueFrom] ", viewGroup, " happen exception");
        return null;
    }

    public final HashMap<WeakReference<ViewGroup>, Object> getValuesOfDiffState() {
        return (HashMap) this.valuesOfDiffState$delegate.getValue();
    }

    public final int hashCode() {
        int iHashCode = getClass().hashCode();
        ViewGroup targetViewHost = getTargetViewHost();
        if (targetViewHost != null) {
            iHashCode = (iHashCode * 31) + targetViewHost.hashCode();
        }
        return Integer.hashCode(this.sharedElementId) + (iHashCode * 31);
    }

    public void initValue(ViewGroup viewGroup, View view, boolean z) {
        if (z) {
            setValue(view, this.defValue);
        }
    }

    public abstract ShareTransitionProperty moveTo(ViewGroup viewGroup);

    public void onTransitionEnd(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.TAG, LogUtil.SEPARATOR);
            sbM.append("[onTransitionEnd]" + Companion.simpleNameAndHash(this) + " " + z);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        Object value = getValue();
        if (Intrinsics.areEqual(value, this.invalidValue)) {
            value = this.defValue;
        }
        this.savedValueWhenEnd = value;
        Iterator<T> it = getValuesOfDiffState().keySet().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = (ViewGroup) ((WeakReference) it.next()).get();
            if (viewGroup != null) {
                Companion.clearIdCache(viewGroup, this.sharedElementId);
            }
        }
    }

    public final void setExpectedFromHost(ViewGroup viewGroup) {
        this.expectedFromHost = viewGroup;
    }

    public final void setInOverlay(boolean z) {
        this.inOverlay = z;
    }

    public abstract void setProgress(float f, float f2, Object obj, Object obj2);

    public final void setProgress(ViewGroup viewGroup, float f, float f2, ViewGroup viewGroup2) {
        setProgress(f, f2, getValueFrom(viewGroup), viewGroup2 != null ? getValueFrom(viewGroup2) : this.invalidValue);
    }

    public final void setSavedValueWhenEnd(Object obj) {
        this.savedValueWhenEnd = obj;
    }

    public abstract void setValue(View view, Object obj);

    public final void setValue(Object obj) {
        View viewFindViewByIdUseCache;
        ViewGroup targetViewHost = getTargetViewHost();
        if (targetViewHost == null || (viewFindViewByIdUseCache = Companion.findViewByIdUseCache(targetViewHost, this.sharedElementId, true)) == null) {
            return;
        }
        setValue(viewFindViewByIdUseCache, obj);
    }
}
