package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewGroupKt;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.ViewOverlayManagerKt;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseDeformationAnimationController implements IDeformationAnimationController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BaseDeformationAnimationController";
    private final Lazy allSupportHost$delegate;
    private final Lazy allValidSize$delegate;
    private final Lazy computeResult$delegate;
    private ViewGroup currTargetViewHost;
    public boolean isForDrag = true;
    public boolean isRTL;
    private OnAnimateListener onAnimateListener;
    private final Lazy shareTransitionProperties$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnAnimateListener {
        void onAnimationEnd(boolean z);

        void onViewHostBoundChanged();
    }

    public BaseDeformationAnimationController() {
        final int i = 0;
        this.allSupportHost$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new LinkedHashMap();
                    case 1:
                        return new LinkedHashMap();
                    case 2:
                        return new LinkedHashSet();
                    default:
                        return new IDeformationAnimationController.ComputeResult();
                }
            }
        });
        final int i2 = 1;
        this.allValidSize$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new LinkedHashMap();
                    case 1:
                        return new LinkedHashMap();
                    case 2:
                        return new LinkedHashSet();
                    default:
                        return new IDeformationAnimationController.ComputeResult();
                }
            }
        });
        final int i3 = 2;
        this.shareTransitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return new LinkedHashMap();
                    case 1:
                        return new LinkedHashMap();
                    case 2:
                        return new LinkedHashSet();
                    default:
                        return new IDeformationAnimationController.ComputeResult();
                }
            }
        });
        final int i4 = 3;
        this.computeResult$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return new LinkedHashMap();
                    case 1:
                        return new LinkedHashMap();
                    case 2:
                        return new LinkedHashSet();
                    default:
                        return new IDeformationAnimationController.ComputeResult();
                }
            }
        });
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public abstract /* synthetic */ IDeformationAnimationController.ComputeResult calculateAnimationStartAndEndView(int i, int i2, int i3, int i4);

    public abstract Map<ShareTransitionProperty, ViewGroup> createShareTransitionProperties(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map);

    public List<Pair<BlurDrawable, BlurDrawable>> findAllBlurDrawableMapper(ViewGroup viewGroup, ViewGroup viewGroup2) {
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public final Map<SpanSize, ViewGroup> getAllSupportHost() {
        return (Map) this.allSupportHost$delegate.getValue();
    }

    public final Map<SpanSize, Size> getAllValidSize() {
        return (Map) this.allValidSize$delegate.getValue();
    }

    public final IDeformationAnimationController.ComputeResult getComputeResult() {
        return (IDeformationAnimationController.ComputeResult) this.computeResult$delegate.getValue();
    }

    public final ViewGroup getCurrTargetViewHost() {
        return this.currTargetViewHost;
    }

    public final OnAnimateListener getOnAnimateListener() {
        return this.onAnimateListener;
    }

    public final Set<ShareTransitionProperty> getShareTransitionProperties() {
        return (Set) this.shareTransitionProperties$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void onFlingStarted(ViewGroup viewGroup, SpanSize spanSize) {
        ViewGroup viewGroup2;
        if (Intrinsics.areEqual(viewGroup, this.currTargetViewHost)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "BaseDeformationAnimationController-->[onFlingStarted] targetViewHost doesn't change, return");
                return;
            }
            return;
        }
        if (!viewGroup.isAttachedToWindow() || !getAllSupportHost().containsValue(viewGroup)) {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "[onFlingStarted] " + viewGroup + " attached=" + viewGroup.isAttachedToWindow());
            return;
        }
        ViewGroup viewGroup3 = this.currTargetViewHost;
        this.currTargetViewHost = viewGroup;
        Iterator<T> it = getShareTransitionProperties().iterator();
        while (it.hasNext()) {
            ((ShareTransitionProperty) it.next()).onTransitionEnd(false);
        }
        if (viewGroup3 != null) {
            resetChildrenFromOverlay(viewGroup3);
        }
        Set<ShareTransitionProperty> shareTransitionProperties = getShareTransitionProperties();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(shareTransitionProperties, 10));
        Iterator<T> it2 = shareTransitionProperties.iterator();
        while (it2.hasNext()) {
            arrayList.add(((ShareTransitionProperty) it2.next()).moveTo(viewGroup));
        }
        getShareTransitionProperties().clear();
        getShareTransitionProperties().addAll(arrayList);
        List<Pair<BlurDrawable, BlurDrawable>> listFindAllBlurDrawableMapper = findAllBlurDrawableMapper(viewGroup3, this.currTargetViewHost);
        if (listFindAllBlurDrawableMapper.isEmpty() || (viewGroup2 = this.currTargetViewHost) == null) {
            return;
        }
        BaseDeformationAnimationController$onFlingStarted$.inlined.doOnPreDraw.1 r5 = new BaseDeformationAnimationController$onFlingStarted$.inlined.doOnPreDraw.1();
        r5.$blurDrawableMapper$inlined = listFindAllBlurDrawableMapper;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(viewGroup2, r5);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void prepare(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2, boolean z, boolean z2) {
        Size size;
        this.isForDrag = z2;
        if (!viewGroup.isAttachedToWindow() || !map.containsValue(viewGroup)) {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "[prepare] " + viewGroup + " attached=" + viewGroup.isAttachedToWindow());
            return;
        }
        if (getAllSupportHost().size() == getAllValidSize().size()) {
            Set<SpanSize> setKeySet = getAllSupportHost().keySet();
            if (!(setKeySet instanceof Collection) || !setKeySet.isEmpty()) {
                Iterator<T> it = setKeySet.iterator();
                while (it.hasNext()) {
                    if (getAllValidSize().keySet().contains((SpanSize) it.next())) {
                    }
                }
            }
            this.isRTL = z;
            getAllSupportHost().clear();
            getAllSupportHost().putAll(map);
            getAllValidSize().clear();
            getAllValidSize().putAll(map2);
            getShareTransitionProperties().clear();
            Map<ShareTransitionProperty, ViewGroup> mapCreateShareTransitionProperties = createShareTransitionProperties(viewGroup, map);
            this.currTargetViewHost = viewGroup;
            getShareTransitionProperties().addAll(mapCreateShareTransitionProperties.keySet());
            for (Map.Entry<SpanSize, ? extends ViewGroup> entry : map.entrySet()) {
                if (!Intrinsics.areEqual(entry.getValue(), viewGroup) && (size = map2.get(entry.getKey())) != null) {
                    entry.getValue().measure(View.MeasureSpec.makeMeasureSpec(size.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(size.getHeight(), 1073741824));
                    entry.getValue().layout(0, 0, size.getWidth(), size.getHeight());
                }
            }
            List<? extends ViewGroup> list = CollectionsKt___CollectionsKt.toList(map.values());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "BaseDeformationAnimationController-->".concat("[prepare] allSupportHostViewGroup " + list));
            }
            Iterator<T> it2 = mapCreateShareTransitionProperties.keySet().iterator();
            while (it2.hasNext()) {
                ((ShareTransitionProperty) it2.next()).captureValues(list);
            }
            Iterator<T> it3 = mapCreateShareTransitionProperties.entrySet().iterator();
            while (it3.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it3.next();
                ((ShareTransitionProperty) entry2.getKey()).ensureSharedElementAndInit((ViewGroup) entry2.getValue());
            }
            return;
        }
        Path$.ExternalSyntheticBUOutline0.m("allSupportHost doesn't match allValidSize!");
    }

    public void recoverChildToSrcParent(List<? extends View> list) {
        if (list == null) {
            return;
        }
        for (View view : list) {
            ViewGroup srcParent = ViewOverlayManagerKt.getSrcParent(view);
            if (srcParent != null) {
                srcParent.addView(view);
            }
        }
    }

    public final void resetChildrenFromOverlay(ViewGroup viewGroup) {
        Sequence children;
        Object value = ReflectionUtils.getValue(ViewOverlay.class, viewGroup.getOverlay(), "mOverlayViewGroup");
        List<? extends View> list = null;
        ViewGroup viewGroup2 = value instanceof ViewGroup ? (ViewGroup) value : null;
        if (viewGroup2 != null && (children = ViewGroupKt.getChildren(viewGroup2)) != null) {
            list = SequencesKt___SequencesKt.toList(children);
        }
        viewGroup.getOverlay().clear();
        recoverChildToSrcParent(list);
    }

    public final void setCurrTargetViewHost(ViewGroup viewGroup) {
        this.currTargetViewHost = viewGroup;
    }

    public final void setForDrag(boolean z) {
        this.isForDrag = z;
    }

    public final void setOnAnimateListener(OnAnimateListener onAnimateListener) {
        this.onAnimateListener = onAnimateListener;
    }

    public final void setRTL(boolean z) {
        this.isRTL = z;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void stop(Function1 function1) {
        Iterator<T> it = getShareTransitionProperties().iterator();
        while (it.hasNext()) {
            ((ShareTransitionProperty) it.next()).onTransitionEnd(false);
        }
        getShareTransitionProperties().clear();
        ViewGroup viewGroup = this.currTargetViewHost;
        if (viewGroup != null) {
            resetChildrenFromOverlay(viewGroup);
        }
        this.currTargetViewHost = null;
        if (function1 != null) {
            Iterator<T> it2 = getAllSupportHost().values().iterator();
            while (it2.hasNext()) {
                function1.invoke((ViewGroup) it2.next());
            }
        }
        getAllSupportHost().clear();
        getAllValidSize().clear();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void transferFrom(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2) {
        super.transferFrom(iDeformationAnimationController, viewGroup, map, map2);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void updateFrame(IDeformationAnimationController.ComputeResult computeResult) {
        ViewGroup startHost;
        ViewGroup endHost = computeResult.getEndHost();
        if (endHost == null || (startHost = computeResult.getStartHost()) == null) {
            return;
        }
        if (Math.abs(computeResult.getXProgressFromCurr()) <= Float.MAX_VALUE && Math.abs(computeResult.getYProgressFromCurr()) <= Float.MAX_VALUE) {
            Iterator<T> it = getShareTransitionProperties().iterator();
            while (it.hasNext()) {
                ((ShareTransitionProperty) it.next()).setProgress(endHost, computeResult.getXProgressFromCurr(), computeResult.getYProgressFromCurr(), startHost);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "BaseDeformationAnimationController-->".concat("updateFrame:  startHost: " + startHost + ", endHost: " + endHost + ", info.xProgress:{" + computeResult.getEndHost() + "}"));
        }
    }
}
