package com.oplus.systemui.separate;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.coui.animatekit.progressive.HorizontalProgressiveAnimHelper;
import com.coui.animatekit.progressive.HorizontalProgressiveConfig;
import com.coui.animatekit.progressive.RubberbandConfig;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HorizontalProgressiveAnimController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HorizontalProgressiveAnimController";
    private LinkedHashSet<View> dynamicChildViews;
    private List<View> lastPrimaryView;
    private List<View> lastVisibleChildren;
    private MotionEvent progressiveDownEvent;
    private HorizontalProgressiveAnimHelper progressiveHelper;
    private final RubberbandConfig rubberbandConfig = new RubberbandConfig(0.55f, -1.0f);
    private Function2 translationChangedCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void addGroup(View view, List<? extends View> list) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper;
        List<View> list2 = this.lastPrimaryView;
        if (list2 == null) {
            this.lastPrimaryView = CollectionsKt__CollectionsKt.mutableListOf(new View[]{view});
        } else if (list2 != null) {
            list2.add(view);
        }
        if (list != null) {
            if (list.isEmpty()) {
                list = null;
            }
            if (list == null || (horizontalProgressiveAnimHelper = this.progressiveHelper) == null) {
                return;
            }
            View[] viewArr = (View[]) list.toArray(new View[0]);
            horizontalProgressiveAnimHelper.addGroup(view, (View[]) Arrays.copyOf(viewArr, viewArr.length));
        }
    }

    public final float calculateRubberband(float f, float f2) {
        HorizontalProgressiveAnimHelper.Companion companion = HorizontalProgressiveAnimHelper.Companion;
        float f3 = -f;
        float curveRatio = this.rubberbandConfig.getCurveRatio();
        float maxDistancePx = this.rubberbandConfig.getMaxDistancePx() > 0.0f ? this.rubberbandConfig.getMaxDistancePx() : f2 / 2.0f;
        companion.getClass();
        if (f3 <= 0.0f) {
            return 0.0f;
        }
        return (1.0f - (1.0f / (((curveRatio * f3) / maxDistancePx) + 1.0f))) * maxDistancePx;
    }

    public final void clearDownEvent() {
        MotionEvent motionEvent = this.progressiveDownEvent;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        this.progressiveDownEvent = null;
    }

    public final void downTo(float f) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper;
        MotionEvent motionEvent = this.progressiveDownEvent;
        if (motionEvent == null || (horizontalProgressiveAnimHelper = this.progressiveHelper) == null) {
            return;
        }
        horizontalProgressiveAnimHelper.downTo(motionEvent, (int) f);
    }

    public final void ensureReady(List<? extends ViewGroup> list, Context context) {
        if (list.isEmpty()) {
            LogUtil.w$default(8, LogUtil.TAG, TAG, "ensureReady ignored: empty containers");
            return;
        }
        if (this.progressiveHelper != null) {
            return;
        }
        FeatureOption.INSTANCE.getClass();
        boolean zIsFoldDevice = FeatureOption.isFoldDevice();
        boolean zIsTablet = FeatureOption.isTablet();
        boolean zIsLandscape = DeviceUtil.isLandscape(context);
        boolean zIsLightOsGaussianLevel = ScrimUtil.isLightOsGaussianLevel();
        boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel(context);
        boolean zShouldUseLargeFoldConfig = DeviceUtil.shouldUseLargeFoldConfig(context.getResources());
        float f = 0.66f;
        if (!zIsTablet) {
            if (zShouldUseLargeFoldConfig) {
                f = 0.62f;
            } else if (!zIsLandscape) {
                f = zIsLowGaussianLevel ? 0.45f : 0.5f;
            }
        }
        float f2 = f;
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = new HorizontalProgressiveAnimHelper(list, zIsLowGaussianLevel ? new HorizontalProgressiveConfig(0.2f, 0.2f, 0.0f, 1.0f, f2, 0.0f, 0.0f, 1.0f, 12000.0f, 0.2f, 0.2f, 0.12f, 0.12f) : new HorizontalProgressiveConfig(0.2f, 0.25f, 0.02f, 0.65f, f2, 0.1f, 0.015f, f2 + 0.14999999f, 12000.0f, 0.2f, 0.2f, 0.12f, 0.12f));
        Function2 function2 = this.translationChangedCallback;
        if (function2 != null) {
            horizontalProgressiveAnimHelper.setOnTranslationChangedCallback(function2);
        }
        this.progressiveHelper = horizontalProgressiveAnimHelper;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG)) {
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("ensureReady progressive helper initialized size=", list.size(), " isFoldOpen=", " isFoldDevice=", zShouldUseLargeFoldConfig);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" isTablet=", " isLand=", sbM, zIsFoldDevice, zIsTablet);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" isLightOs=", " isLow=", sbM, zIsLandscape, zIsLightOsGaussianLevel);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("HorizontalProgressiveAnimController-->", "SystemUi--SystemUi--", sbM, zIsLowGaussianLevel);
        }
    }

    public final void flingTo(float f, Function1 function1) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.flingTo((int) f, function1);
        }
    }

    public final void flingToRubberband(Function1 function1) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.flingToRubberband(function1);
        }
    }

    public final HorizontalProgressiveAnimHelper getProgressiveHelper() {
        return this.progressiveHelper;
    }

    public final boolean isDynamicChildViewsEqual(List<? extends View> list) {
        LinkedHashSet<View> linkedHashSet = this.dynamicChildViews;
        if (linkedHashSet == null && list == null) {
            return true;
        }
        if (!Intrinsics.areEqual(linkedHashSet != null ? Integer.valueOf(linkedHashSet.size()) : null, list != null ? Integer.valueOf(list.size()) : null) || list == null) {
            return false;
        }
        List<? extends View> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (View view : list2) {
                LinkedHashSet<View> linkedHashSet2 = this.dynamicChildViews;
                if (linkedHashSet2 == null || !linkedHashSet2.contains(view)) {
                    return false;
                }
            }
        }
        return true;
    }

    public final boolean isLogicRunning() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            return horizontalProgressiveAnimHelper.isLogicRunning();
        }
        return false;
    }

    public final boolean isRubberbandLogicRunning() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            return horizontalProgressiveAnimHelper.isRubberbandLogicRunning();
        }
        return false;
    }

    public final boolean isRubberbandRunning() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            return horizontalProgressiveAnimHelper.isRubberbandRunning();
        }
        return false;
    }

    public final boolean isRunning() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            return horizontalProgressiveAnimHelper.isRunning();
        }
        return false;
    }

    public final void moveTo(float f) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.moveTo((int) f);
        }
    }

    public final void moveToRubberband(float f, int i) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.moveToRubberband(f, i);
        }
    }

    public final void onDown(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.progressiveDownEvent;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.progressiveDownEvent = MotionEvent.obtain(motionEvent);
    }

    public final void register(List<? extends View> list) {
        List<View> list2;
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            List<View> list3 = this.lastVisibleChildren;
            if (list3 == null) {
                this.lastVisibleChildren = list != null ? CollectionsKt___CollectionsKt.toMutableList(list) : null;
            } else {
                if (list3 != null) {
                    list3.clear();
                }
                if (list != null && (list2 = this.lastVisibleChildren) != null) {
                    list2.addAll(list);
                }
            }
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    horizontalProgressiveAnimHelper.register(new View[]{(View) it.next()});
                }
            }
        }
    }

    public final void registerContainer(ViewGroup viewGroup, View view) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.registerContainer(viewGroup, view);
        }
    }

    public final void registerDynamicView(List<? extends View> list) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            if (this.dynamicChildViews == null) {
                this.dynamicChildViews = new LinkedHashSet<>();
            }
            LinkedHashSet<View> linkedHashSet = this.dynamicChildViews;
            if (linkedHashSet != null) {
                linkedHashSet.clear();
                if (list != null) {
                    linkedHashSet.addAll(list);
                }
            }
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    horizontalProgressiveAnimHelper.register(new View[]{(View) it.next()});
                }
            }
        }
    }

    public final void removeGroup() {
        List<View> list;
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper == null || (list = this.lastPrimaryView) == null) {
            return;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            horizontalProgressiveAnimHelper.removeGroup((View) it.next());
        }
    }

    public final void resetHelper() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            List<View> list = this.lastVisibleChildren;
            if (list != null) {
                HorizontalProgressiveAnimController$resetHelper$1$1 horizontalProgressiveAnimController$resetHelper$1$1 = new HorizontalProgressiveAnimController$resetHelper$1$1();
                horizontalProgressiveAnimController$resetHelper$1$1.$tmp0 = horizontalProgressiveAnimHelper;
                VarHandle.storeStoreFence();
                list.forEach(horizontalProgressiveAnimController$resetHelper$1$1);
            }
            List<View> list2 = this.lastPrimaryView;
            if (list2 != null) {
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    horizontalProgressiveAnimHelper.removeGroup((View) it.next());
                }
            }
            horizontalProgressiveAnimHelper.release();
        }
        this.progressiveHelper = null;
        List<View> list3 = this.lastVisibleChildren;
        if (list3 != null) {
            list3.clear();
        }
        List<View> list4 = this.lastPrimaryView;
        if (list4 != null) {
            list4.clear();
        }
        LinkedHashSet<View> linkedHashSet = this.dynamicChildViews;
        if (linkedHashSet != null) {
            linkedHashSet.clear();
        }
        this.dynamicChildViews = null;
        this.lastVisibleChildren = null;
        this.lastPrimaryView = null;
    }

    public final void setOnTranslationChangedCallback(Function2 function2) {
        this.translationChangedCallback = function2;
    }

    public final void setProgressiveHelper(HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper) {
        this.progressiveHelper = horizontalProgressiveAnimHelper;
    }

    public final void skipRubberbandToEnd() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.skipRubberbandToEnd();
        }
    }

    public final void skipToEnd() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.skipToEnd();
        }
    }

    public final void unRegister(View view) {
        if (view != null) {
            view.setTranslationX(0.0f);
            HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
            if (horizontalProgressiveAnimHelper != null) {
                horizontalProgressiveAnimHelper.unregister(new View[]{view});
            }
            List<View> list = this.lastVisibleChildren;
            if (list != null) {
                list.remove(view);
            }
        }
    }

    public final void unRegisterContainer(ViewGroup viewGroup) {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.unregisterContainer(viewGroup);
        }
    }

    public final void unRegisterDynamicView() {
        LinkedHashSet<View> linkedHashSet = this.dynamicChildViews;
        if (linkedHashSet != null) {
            if (linkedHashSet.isEmpty()) {
                linkedHashSet = null;
            }
            if (linkedHashSet != null) {
                for (View view : linkedHashSet) {
                    view.setTranslationX(0.0f);
                    HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
                    if (horizontalProgressiveAnimHelper != null) {
                        horizontalProgressiveAnimHelper.unregister(new View[]{view});
                    }
                }
            }
        }
    }

    public final void updateSDKLogEnabled() {
        HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
        if (horizontalProgressiveAnimHelper != null) {
            horizontalProgressiveAnimHelper.setLogEnabled(LogUtil.getNormalDebug());
        }
    }

    public final void register(View view) {
        if (view != null) {
            if (this.lastVisibleChildren == null) {
                this.lastVisibleChildren = new ArrayList();
            }
            List<View> list = this.lastVisibleChildren;
            if (list == null || !list.contains(view)) {
                HorizontalProgressiveAnimHelper horizontalProgressiveAnimHelper = this.progressiveHelper;
                if (horizontalProgressiveAnimHelper != null) {
                    horizontalProgressiveAnimHelper.register(new View[]{view});
                }
                List<View> list2 = this.lastVisibleChildren;
                if (list2 != null) {
                    list2.add(view);
                }
            }
        }
    }
}
