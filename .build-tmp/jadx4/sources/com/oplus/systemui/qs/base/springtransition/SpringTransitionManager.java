package com.oplus.systemui.qs.base.springtransition;

import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.car.app.serialization.Bundler$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpringTransitionManager implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener, COUIDynamicAnimation.OnAnimationEndListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SpringTransitionManager";
    private final Map<SpringTransition, Pair<Float, Float>> _transitions;
    private final ViewGroup hostViewGroup;
    private final String tag;
    private final String tagName;
    private final Set<SpringTransition> transitions;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static Set attachedTransitionManager(ViewGroup viewGroup) {
            Object tag = viewGroup.getTag(2131365581);
            Set set = tag instanceof Set ? (Set) tag : null;
            if (set != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : set) {
                    if (obj instanceof SpringTransitionManager) {
                        arrayList.add(obj);
                    }
                }
                Set mutableSet = CollectionsKt___CollectionsKt.toMutableSet(arrayList);
                if (mutableSet != null) {
                    return mutableSet;
                }
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            viewGroup.setTag(2131365581, linkedHashSet);
            return linkedHashSet;
        }
    }

    public SpringTransitionManager(ViewGroup viewGroup, String str) {
        this.hostViewGroup = viewGroup;
        this.tagName = str;
        this.tag = FileProvider$.ExternalSyntheticOutline0.m("SpringTransitionManager_", str);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this._transitions = linkedHashMap;
        this.transitions = linkedHashMap.keySet();
        viewGroup.addOnAttachStateChangeListener(this);
    }

    public boolean addTransition(SpringTransition springTransition) {
        Float fValueOf = Float.valueOf(Float.MIN_VALUE);
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[addTransition] " + springTransition);
            Log.d("SystemUi--Common", sbM.toString());
        }
        return this._transitions.putIfAbsent(springTransition, new Pair<>(fValueOf, fValueOf)) == null;
    }

    public final void beginDelayedTransition(Map<SpringTransition, Pair<Float, Float>> map) {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->[beginDelayedTransition]", "SystemUi--Common");
        }
        Iterator<T> it = this.transitions.iterator();
        while (it.hasNext()) {
            ((SpringTransition) it.next()).cancel();
        }
        for (Map.Entry<SpringTransition, Pair<Float, Float>> entry : map.entrySet()) {
            this._transitions.put(entry.getKey(), entry.getValue());
            entry.getKey().captureStartValues(((Number) entry.getValue().getFirst()).floatValue());
        }
        if (this.hostViewGroup.isAttachedToWindow()) {
            this.hostViewGroup.getViewTreeObserver().addOnPreDrawListener(this);
        } else {
            Path$.ExternalSyntheticBUOutline0.m$1("beginDelayedTransition when hostViewGroup is not attached to window");
        }
    }

    public void cancelTransition() {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->[cancelTransition]", "SystemUi--Common");
        }
        for (SpringTransition springTransition : this._transitions.keySet()) {
            if (springTransition.isRunning()) {
                springTransition.cancel();
            }
        }
    }

    public final void endTransition() {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->[endTransition]", "SystemUi--Common");
        }
        for (SpringTransition springTransition : this._transitions.keySet()) {
            if (springTransition.isRunning()) {
                springTransition.reset();
                Iterator<TransitionProperty> allTargetProperties = springTransition.getAllTargetProperties();
                while (allTargetProperties.hasNext()) {
                    TransitionProperty next = allTargetProperties.next();
                    next.setProgress(1.0f);
                    next.onTransitionEnd(false);
                }
            }
        }
    }

    public final ViewGroup getHostViewGroup() {
        return this.hostViewGroup;
    }

    public final String getTagName() {
        return this.tagName;
    }

    public final Set<SpringTransition> getTransitions() {
        return this.transitions;
    }

    public final boolean isRunning() {
        Set<SpringTransition> setKeySet = this._transitions.keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            return false;
        }
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            if (((SpringTransition) it.next()).isRunning()) {
                return true;
            }
        }
        return false;
    }

    public void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        boolean z2;
        if (isRunning()) {
            return;
        }
        ViewGroup viewGroup = this.hostViewGroup;
        Companion.getClass();
        Companion.attachedTransitionManager(viewGroup).remove(this);
        Set setAttachedTransitionManager = Companion.attachedTransitionManager(viewGroup);
        if (!(setAttachedTransitionManager instanceof Collection) || !setAttachedTransitionManager.isEmpty()) {
            Iterator it = setAttachedTransitionManager.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                } else if (((SpringTransitionManager) it.next()).isRunning()) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
            break;
        }
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[onAnimationEnd]isLayoutSuppressed=" + viewGroup.isLayoutSuppressed() + ", isTransitionRunning=" + z2);
            Log.d("SystemUi--Common", sbM.toString());
        }
        if (!viewGroup.isLayoutSuppressed() || z2) {
            return;
        }
        viewGroup.suppressLayout(false);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean z = false;
        for (Map.Entry<SpringTransition, Pair<Float, Float>> entry : this._transitions.entrySet()) {
            Iterator<TransitionProperty> allTargetProperties = entry.getKey().getAllTargetProperties();
            while (allTargetProperties.hasNext()) {
                allTargetProperties.next().captureEndValue();
            }
            if (((Number) entry.getValue().getSecond()).floatValue() != Float.MIN_VALUE) {
                boolean zStartTransition = startTransition(entry.getKey(), entry.getValue());
                if (zStartTransition) {
                    entry.getKey().addOnEndListener(this);
                    z = true;
                }
                LogUtil.i$default(LogUtil.TAG_COMMON, this.tag, "[onPreDraw] " + entry.getKey() + " animationTo " + entry.getValue().getSecond() + ", result:" + zStartTransition, null, 8);
            }
        }
        if (z) {
            String str = this.tag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->[onPreDraw]animationStarted suppressLayout(true)", "SystemUi--Common");
            }
            this.hostViewGroup.suppressLayout(true);
            Companion companion = Companion;
            ViewGroup viewGroup = this.hostViewGroup;
            companion.getClass();
            Companion.attachedTransitionManager(viewGroup).add(this);
        }
        this.hostViewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.hostViewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
        cancelTransition();
    }

    public boolean removeTransition(SpringTransition springTransition) {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("[removeTransition] " + springTransition);
            Log.d("SystemUi--Common", sbM.toString());
        }
        return this._transitions.remove(springTransition) != null;
    }

    public boolean startTransition(SpringTransition springTransition, Pair<Float, Float> pair) {
        return springTransition.animationToFinalPosition(((Number) pair.getSecond()).floatValue());
    }

    public /* synthetic */ SpringTransitionManager(ViewGroup viewGroup) {
        this(viewGroup, "");
    }
}
