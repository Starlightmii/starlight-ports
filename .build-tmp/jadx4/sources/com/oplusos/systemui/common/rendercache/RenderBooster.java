package com.oplusos.systemui.common.rendercache;

import android.os.Trace;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import androidx.compose.ui.spatial.RectList$;
import androidx.slice.widget.RowView$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderBooster {
    private static final String TAG = "RenderBooster";
    public int backupInvalidateType;
    public int invalidateType;
    public boolean isDirty;
    private final Function1 isRenderExpensiveView;
    private final RenderBooster$onAttachStateChangeListener$1 onAttachStateChangeListener;
    private final View targetView;
    public static final Companion Companion = new Companion();
    private static final ArraySet<View> hwCollectExcludedScratch = new ArraySet<>(64);
    private static final ArrayList<View> hwCollectPathScratch = new ArrayList<>(32);
    private static final ArrayList<Object> hwCollectDfsStack = new ArrayList<>(50);
    private static final Object HW_COLLECT_DFS_EXIT_MARKER = new Object();
    private static final ArraySet<View> candidateScratch = new ArraySet<>(16);
    private static final ArrayList<RenderBooster> boosterApplyOrder = new ArrayList<>(8);
    private static int[] boosterDepthScratch = new int[8];
    private static final ArrayList<View> cachedViewNonWeakRef = new ArrayList<>(20);
    private final Set<WeakReference<View>> cachedViews = new LinkedHashSet();
    public boolean onlyScanVisible = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final void access$addBoosterTarget(Companion companion, ViewRootImpl viewRootImpl, RenderBooster renderBooster) {
            ArrayList<WeakReference<RenderBooster>> arrayListBoosterViews = companion.boosterViews(viewRootImpl);
            if (arrayListBoosterViews == null || !arrayListBoosterViews.isEmpty()) {
                int size = arrayListBoosterViews.size();
                int i = 0;
                while (i < size) {
                    WeakReference<RenderBooster> weakReference = arrayListBoosterViews.get(i);
                    i++;
                    if (Intrinsics.areEqual(weakReference.get(), renderBooster)) {
                        return;
                    }
                }
            }
            arrayListBoosterViews.add(new WeakReference<>(renderBooster));
        }

        public static final void access$applyRenderBoosterOnPreDraw(Companion companion, ViewRootImpl viewRootImpl) {
            companion.getClass();
            Object tag = viewRootImpl.getView().getTag(2131365583);
            ViewTreeObserver.OnPreDrawListener onPreDrawListener = tag instanceof ViewTreeObserver.OnPreDrawListener ? (ViewTreeObserver.OnPreDrawListener) tag : null;
            if (companion.boosterViews(viewRootImpl).isEmpty()) {
                viewRootImpl.getView().setTag(2131365583, null);
                if (onPreDrawListener != null) {
                    viewRootImpl.getView().getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
                    return;
                }
                return;
            }
            if (onPreDrawListener != null) {
                return;
            }
            RenderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1 renderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1 = new RenderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1();
            renderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1.$this_applyRenderBoosterOnPreDraw = viewRootImpl;
            VarHandle.storeStoreFence();
            viewRootImpl.getView().getViewTreeObserver().addOnPreDrawListener(renderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1);
            viewRootImpl.getView().setTag(2131365583, renderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1);
        }

        public static void addChildToDfsStack(ViewGroup viewGroup, boolean z) {
            int childCount = viewGroup.getChildCount();
            while (true) {
                childCount--;
                if (-1 >= childCount) {
                    return;
                }
                if (viewGroup.getChildAt(childCount).getVisibility() == 0 || !z) {
                    RenderBooster.hwCollectDfsStack.add(viewGroup.getChildAt(childCount));
                }
            }
        }

        public static boolean canResueCache(int i) {
            return (i & 24) == 0;
        }

        public static final boolean collectHardwareLayerCandidates$isSubtreeBlocker(ArrayList arrayList, List list, View view) {
            if (arrayList == null || !arrayList.isEmpty()) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    if (Intrinsics.areEqual(((RenderBooster) obj).getTargetView(), view)) {
                        return true;
                    }
                }
            }
            return (view.getLayerType() == 0 || list.contains(view)) ? false : true;
        }

        public static /* synthetic */ RenderBooster ensureRenderBooster$default(Companion companion, View view, Function1 function1, int i) {
            if ((i & 2) != 0) {
                function1 = null;
            }
            return companion.ensureRenderBooster(view, false, function1);
        }

        public final ArrayList<WeakReference<RenderBooster>> boosterViews(ViewRootImpl viewRootImpl) {
            Object tag = viewRootImpl.getView().getTag(2131365585);
            ArrayList<WeakReference<RenderBooster>> arrayList = tag instanceof ArrayList ? (ArrayList) tag : null;
            if (arrayList != null) {
                return arrayList;
            }
            ArrayList<WeakReference<RenderBooster>> arrayList2 = new ArrayList<>();
            viewRootImpl.getView().setTag(2131365585, arrayList2);
            return arrayList2;
        }

        public final RenderBooster ensureRenderBooster(View view, boolean z, Function1 function1) {
            Object tag = view.getTag(2131365584);
            RenderBooster renderBooster = tag instanceof RenderBooster ? (RenderBooster) tag : null;
            if (renderBooster != null) {
                return renderBooster;
            }
            RenderBooster renderBooster2 = new RenderBooster(view, function1, z);
            view.setTag(2131365584, renderBooster2);
            return renderBooster2;
        }

        public final RenderBooster renderBooster(View view) {
            Object tag = view.getTag(2131365584);
            if (tag instanceof RenderBooster) {
                return (RenderBooster) tag;
            }
            return null;
        }
    }

    public RenderBooster(View view, Function1 function1, boolean z) {
        this.targetView = view;
        this.isRenderExpensiveView = function1;
        RenderBooster$onAttachStateChangeListener$1 renderBooster$onAttachStateChangeListener$1 = new RenderBooster$onAttachStateChangeListener$1();
        renderBooster$onAttachStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onAttachStateChangeListener = renderBooster$onAttachStateChangeListener$1;
        if (z) {
            view.addOnAttachStateChangeListener(renderBooster$onAttachStateChangeListener$1);
        }
    }

    public static void requestBooster$default(RenderBooster renderBooster, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        renderBooster.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sb = new StringBuilder("[requestBooster], isBoosted=");
            PromptIconState$.ExternalSyntheticOutline0.m(i, ", type=", " for ", sb, renderBooster.invalidateType != 0);
            sb.append(renderBooster);
            Log.d("SystemUi--Common", "RenderBooster-->".concat(sb.toString()));
        }
        View view = renderBooster.targetView;
        if (!view.isAttachedToWindow()) {
            RenderBooster$requestBooster$$inlined$doOnAttach$1 renderBooster$requestBooster$$inlined$doOnAttach$1 = new RenderBooster$requestBooster$$inlined$doOnAttach$1();
            renderBooster$requestBooster$$inlined$doOnAttach$1.$this_doOnAttach = view;
            renderBooster$requestBooster$$inlined$doOnAttach$1.this$0 = renderBooster;
            renderBooster$requestBooster$$inlined$doOnAttach$1.$type$inlined = i;
            renderBooster$requestBooster$$inlined$doOnAttach$1.$onlyScanVisible$inlined = z;
            VarHandle.storeStoreFence();
            view.addOnAttachStateChangeListener(renderBooster$requestBooster$$inlined$doOnAttach$1);
            return;
        }
        ViewRootImpl viewRootImpl = renderBooster.getTargetView().getViewRootImpl();
        if (viewRootImpl == null) {
            return;
        }
        int invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon = renderBooster.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
        renderBooster.invalidateType = i | renderBooster.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
        if (renderBooster.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() != 0) {
            if (invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon != 0) {
                Companion.getClass();
                if (Companion.canResueCache(invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon) == Companion.canResueCache(renderBooster.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon())) {
                    return;
                }
            }
            renderBooster.onlyScanVisible = z;
            renderBooster.markAncestorsDirty(true);
            Companion companion = Companion;
            Companion.access$applyRenderBoosterOnPreDraw(companion, viewRootImpl);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "RenderBooster-->".concat("[requestBooster] request rebuild boost network from " + renderBooster));
            }
            if (invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon == 0) {
                Companion.access$addBoosterTarget(companion, viewRootImpl, renderBooster);
            }
            renderBooster.setDirty$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(true);
        }
    }

    public static void setLayerType(View view, boolean z) {
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("setHardwareLayer ", " for ", z);
        sbM.append(view.getClass().getSimpleName());
        sbM.append('@');
        sbM.append(view.hashCode());
        Trace.traceBegin(8L, sbM.toString());
        view.setLayerType(z ? 2 : 0, null);
        Trace.traceEnd(8L);
    }

    /* JADX WARN: Code duplicated, block: B:55:0x011d  */
    public final void apply$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        RenderBooster renderBooster;
        ArrayList<Object> arrayList;
        ArrayList<Object> arrayList2;
        RenderBooster renderBooster2;
        if (this.invalidateType == 0 || !this.isDirty) {
            return;
        }
        candidateScratch.clear();
        ArrayList<View> arrayList3 = cachedViewNonWeakRef;
        arrayList3.clear();
        Iterator<T> it = this.cachedViews.iterator();
        while (it.hasNext()) {
            View view = (View) ((WeakReference) it.next()).get();
            if (view != null) {
                arrayList3.add(view);
            }
        }
        Companion companion = Companion;
        View view2 = this.targetView;
        ArrayList<RenderBooster> arrayList4 = boosterApplyOrder;
        ArrayList<View> arrayList5 = cachedViewNonWeakRef;
        ArraySet<View> arraySet = candidateScratch;
        boolean z = this.onlyScanVisible;
        Function1 renderBooster$$ExternalSyntheticLambda0 = this.isRenderExpensiveView;
        if (renderBooster$$ExternalSyntheticLambda0 == null) {
            renderBooster$$ExternalSyntheticLambda0 = new RenderBooster$$ExternalSyntheticLambda0();
        }
        companion.getClass();
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            if (viewGroup.getChildCount() == 0) {
                renderBooster = companion.renderBooster(view2);
                if (renderBooster != null && Companion.canResueCache(renderBooster.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon())) {
                    arraySet.add(view2);
                }
            } else {
                Companion.addChildToDfsStack(viewGroup, z);
                while (true) {
                    arrayList = hwCollectDfsStack;
                    if (arrayList.isEmpty()) {
                        break;
                    }
                    Object objRemove = arrayList.remove(arrayList.size() - 1);
                    Object obj = HW_COLLECT_DFS_EXIT_MARKER;
                    if (objRemove == obj) {
                        ArrayList<View> arrayList6 = hwCollectPathScratch;
                        arrayList6.remove(arrayList6.size() - 1);
                    } else {
                        View view3 = (View) objRemove;
                        ArrayList<View> arrayList7 = hwCollectPathScratch;
                        arrayList7.add(view3);
                        if (Companion.collectHardwareLayerCandidates$isSubtreeBlocker(arrayList4, arrayList5, view3)) {
                            hwCollectExcludedScratch.addAll(arrayList7);
                            arrayList7.remove(arrayList7.size() - 1);
                        } else {
                            arrayList.add(obj);
                            if (view3 instanceof ViewGroup) {
                                Companion.addChildToDfsStack((ViewGroup) view3, z);
                            }
                        }
                    }
                }
                ArraySet<View> arraySet2 = hwCollectExcludedScratch;
                if (arraySet2.isEmpty() && (renderBooster2 = companion.renderBooster(view2)) != null && Companion.canResueCache(renderBooster2.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon())) {
                    arraySet.add(view2);
                    arraySet2.clear();
                    hwCollectPathScratch.clear();
                    arrayList.clear();
                } else {
                    arrayList.clear();
                    Companion.addChildToDfsStack(viewGroup, z);
                    while (true) {
                        arrayList2 = hwCollectDfsStack;
                        if (arrayList2.isEmpty()) {
                            break;
                        }
                        View view4 = (View) arrayList2.remove(arrayList2.size() - 1);
                        if (!Companion.collectHardwareLayerCandidates$isSubtreeBlocker(arrayList4, arrayList5, view4)) {
                            if (((Boolean) renderBooster$$ExternalSyntheticLambda0.invoke(view4)).booleanValue() && !hwCollectExcludedScratch.contains(view4)) {
                                arraySet.add(view4);
                            } else if (view4 instanceof ViewGroup) {
                                Companion.addChildToDfsStack((ViewGroup) view4, z);
                            }
                        }
                    }
                    hwCollectExcludedScratch.clear();
                    hwCollectPathScratch.clear();
                    arrayList2.clear();
                }
            }
        } else {
            renderBooster = companion.renderBooster(view2);
            if (renderBooster != null) {
                arraySet.add(view2);
            }
        }
        ArrayList<View> arrayList8 = cachedViewNonWeakRef;
        if (arrayList8.isEmpty()) {
            ArraySet<View> arraySet3 = candidateScratch;
            int size = arraySet3.size();
            for (int i = 0; i < size; i++) {
                View viewValueAt = arraySet3.valueAt(i);
                if (viewValueAt.getLayerType() == 0) {
                    setLayerType(viewValueAt, true);
                }
                this.cachedViews.add(new WeakReference<>(viewValueAt));
            }
        } else {
            ArraySet<View> arraySet4 = candidateScratch;
            int size2 = arrayList8.size();
            int i2 = 0;
            while (i2 < size2) {
                View view5 = arrayList8.get(i2);
                i2++;
                View view6 = view5;
                if (!arraySet4.contains(view6)) {
                    setLayerType(view6, false);
                }
            }
            int size3 = arraySet4.size();
            for (int i3 = 0; i3 < size3; i3++) {
                View viewValueAt2 = arraySet4.valueAt(i3);
                if (!arrayList8.contains(viewValueAt2) && viewValueAt2.getLayerType() == 0) {
                    setLayerType(viewValueAt2, true);
                }
            }
            this.cachedViews.clear();
            Set<WeakReference<View>> set = this.cachedViews;
            Iterator<View> it2 = arraySet4.iterator();
            while (it2.hasNext()) {
                set.add(new WeakReference<>(it2.next()));
            }
        }
        if ((this.invalidateType & 1) != 0) {
            ArrayList<View> arrayList9 = cachedViewNonWeakRef;
            arrayList9.clear();
            Iterator<T> it3 = this.cachedViews.iterator();
            while (it3.hasNext()) {
                View view7 = (View) ((WeakReference) it3.next()).get();
                if (view7 != null) {
                    arrayList9.add(view7);
                }
            }
            if (!cachedViewNonWeakRef.contains(this.targetView)) {
                this.targetView.forceHasOverlappingRendering(false);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "RenderBooster-->".concat("[tryForceOverlapFalse] for " + this.targetView.getClass().getSimpleName() + '@' + this.targetView.hashCode()));
                }
            }
        }
        candidateScratch.clear();
        cachedViewNonWeakRef.clear();
        this.isDirty = false;
    }

    public final int getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.invalidateType;
    }

    public final View getTargetView() {
        return this.targetView;
    }

    public final void markAncestorsDirty(boolean z) {
        Object parent = this.targetView.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            RenderBooster renderBooster = Companion.renderBooster(view);
            if (renderBooster != null) {
                renderBooster.isDirty = true;
                if (z) {
                    Iterator<T> it = renderBooster.cachedViews.iterator();
                    while (it.hasNext()) {
                        View view2 = (View) ((WeakReference) it.next()).get();
                        if (view2 != null) {
                            setLayerType(view2, false);
                        }
                    }
                    renderBooster.cachedViews.clear();
                    return;
                }
                return;
            }
            parent = view.getParent();
        }
    }

    public final void requestStop(int i) {
        ViewRootImpl viewRootImpl;
        if (this.invalidateType == 0 || (viewRootImpl = this.targetView.getViewRootImpl()) == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sb = new StringBuilder("[requestStop]isBoosted=");
            sb.append(this.invalidateType != 0);
            sb.append(",lastType=");
            RectList$.ExternalSyntheticOutline0.m(this.invalidateType, i, ",type=", " for ", sb);
            sb.append(this);
            Log.d("SystemUi--Common", "RenderBooster-->".concat(sb.toString()));
        }
        int i2 = this.invalidateType;
        if (i2 != i && (i2 & i) != 0) {
            this.invalidateType = (~i) & i2;
            Companion companion = Companion;
            companion.getClass();
            if (Companion.canResueCache(i2) == Companion.canResueCache(this.invalidateType)) {
                return;
            }
            Companion.access$applyRenderBoosterOnPreDraw(companion, viewRootImpl);
            this.isDirty = true;
            return;
        }
        if (i == 0 || (i & i2) != 0) {
            Iterator<T> it = this.cachedViews.iterator();
            while (it.hasNext()) {
                View view = (View) ((WeakReference) it.next()).get();
                if (view != null) {
                    setLayerType(view, false);
                }
            }
            this.cachedViews.clear();
            markAncestorsDirty(false);
            Companion companion2 = Companion;
            ArrayList<WeakReference<RenderBooster>> arrayListBoosterViews = companion2.boosterViews(viewRootImpl);
            RenderBooster$Companion$removeBoosterTarget$1 renderBooster$Companion$removeBoosterTarget$1 = new RenderBooster$Companion$removeBoosterTarget$1();
            renderBooster$Companion$removeBoosterTarget$1.$target = this;
            VarHandle.storeStoreFence();
            arrayListBoosterViews.removeIf(renderBooster$Companion$removeBoosterTarget$1);
            Companion.access$applyRenderBoosterOnPreDraw(companion2, viewRootImpl);
            this.invalidateType = 0;
            this.isDirty = false;
        }
    }

    public final void setDirty$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(boolean z) {
        this.isDirty = z;
    }

    public String toString() {
        View view = this.targetView;
        return "RenderBooster(" + (view.getClass().getSimpleName() + '@' + view.hashCode() + '[' + view.getWidth() + ',' + view.getHeight() + ']') + ')';
    }
}
