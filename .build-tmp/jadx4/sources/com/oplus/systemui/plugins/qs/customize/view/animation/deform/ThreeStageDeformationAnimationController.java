package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageBackgroundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageBoundSharedTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageFadeShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageIconScaleShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageLayoutBoundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageRadiusShareProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageSelectSharedTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageWidthSharedTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageDeformationAnimationController extends BaseDeformationAnimationController implements ThreeStageShareTransitionProperty.DeformAnimateLister, BaseDeformationAnimationController.OnAnimateListener {
    private ThreeStageShareTransitionProperty bgBoundTransitionProperty;
    private final List<ShareTransitionProperty> animShareProperties = new ArrayList();
    private Rect bgRect = new Rect();

    private final int getTileBgId() {
        return 2131364808;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public IDeformationAnimationController.ComputeResult calculateAnimationStartAndEndView(int i, int i2, int i3, int i4) {
        ViewGroup viewGroup = null;
        ViewGroup viewGroup2 = null;
        for (Map.Entry<SpanSize, Size> entry : getAllValidSize().entrySet()) {
            int width = entry.getValue().getWidth();
            if (width == i) {
                viewGroup = getAllSupportHost().get(entry.getKey());
            } else if (width == i3) {
                viewGroup2 = getAllSupportHost().get(entry.getKey());
            }
        }
        getComputeResult().reset();
        IDeformationAnimationController.ComputeResult computeResult = getComputeResult();
        computeResult.setStartHost(viewGroup);
        computeResult.setEndHost(viewGroup2);
        return computeResult;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController
    public Map<ShareTransitionProperty, ViewGroup> createShareTransitionProperties(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map) {
        View childAt;
        SpanCache spanCache = SpanCache.INSTANCE;
        ViewGroup viewGroup2 = map.get(spanCache.spanSize(2, 1));
        ViewGroup viewGroup3 = map.get(spanCache.spanSize(1, 1));
        int tileBgId = getTileBgId();
        Map mapCreateMapBuilder = MapsKt__MapsJVMKt.createMapBuilder();
        if (viewGroup2 != null) {
            ThreeStageWidthSharedTransitionProperty threeStageWidthSharedTransitionProperty = new ThreeStageWidthSharedTransitionProperty(viewGroup, 2131364411);
            threeStageWidthSharedTransitionProperty.setOnAnimateListener(this);
            mapCreateMapBuilder.put(threeStageWidthSharedTransitionProperty, viewGroup2);
            mapCreateMapBuilder.put(new ThreeStageLayoutBoundShareTransitionProperty(viewGroup, 2131364403), viewGroup2);
            mapCreateMapBuilder.put(new ThreeStageSelectSharedTransitionProperty(viewGroup, 2131363447, viewGroup2), viewGroup2);
            mapCreateMapBuilder.put(new ThreeStageFadeShareTransitionProperty(viewGroup, 2131363447), viewGroup2);
            this.bgBoundTransitionProperty = new ThreeStageBoundSharedTransitionProperty(viewGroup, tileBgId, false);
            mapCreateMapBuilder.put(new ThreeStageBackgroundShareTransitionProperty(viewGroup, tileBgId), viewGroup2);
            ArrayList arrayList = new ArrayList();
            Iterator it = CollectionsKt__CollectionsKt.listOf(new Integer[]{2131363700, 2131363954, 2131364914}).iterator();
            int i = -1;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int iIntValue = ((Number) it.next()).intValue();
                Object tag = viewGroup2.findViewById(iIntValue).getTag(2131364404);
                if (Intrinsics.areEqual(tag instanceof Boolean ? (Boolean) tag : null, Boolean.TRUE)) {
                    i = iIntValue;
                } else {
                    arrayList.add(Integer.valueOf(iIntValue));
                }
            }
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                mapCreateMapBuilder.put(new ThreeStageFadeShareTransitionProperty(viewGroup, ((Number) obj).intValue()), viewGroup2);
            }
            mapCreateMapBuilder.put(new ThreeStageSelectSharedTransitionProperty(viewGroup, i, viewGroup2), viewGroup2);
            View viewFindViewById = viewGroup2.findViewById(i);
            ViewGroup viewGroup4 = viewFindViewById instanceof ViewGroup ? (ViewGroup) viewFindViewById : null;
            if (viewGroup4 != null && (childAt = viewGroup4.getChildAt(0)) != null) {
                View viewFindViewById2 = viewGroup3 != null ? viewGroup3.findViewById(2131364774) : null;
                if (childAt.getId() != -1 && viewFindViewById2 != null) {
                    mapCreateMapBuilder.put(new ThreeStageIconScaleShareTransitionProperty(viewGroup, childAt.getId(), viewFindViewById2.getWidth()), viewGroup2);
                }
            }
            mapCreateMapBuilder.put(new ThreeStageRadiusShareProperty(viewGroup, tileBgId), viewGroup2);
        }
        return MapsKt__MapsJVMKt.build(mapCreateMapBuilder);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty.DeformAnimateLister
    public void onAnimateEnd(ThreeStageShareTransitionProperty threeStageShareTransitionProperty) {
        this.animShareProperties.remove(threeStageShareTransitionProperty);
        if (this.animShareProperties.isEmpty()) {
            onAnimationEnd(false);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController.OnAnimateListener
    public final void onAnimationEnd(boolean z) {
        BaseDeformationAnimationController.OnAnimateListener onAnimateListener = getOnAnimateListener();
        if (onAnimateListener != null) {
            onAnimateListener.onAnimationEnd(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController.OnAnimateListener
    public final void onViewHostBoundChanged() {
        BaseDeformationAnimationController.OnAnimateListener onAnimateListener = getOnAnimateListener();
        if (onAnimateListener != null) {
            onAnimateListener.onViewHostBoundChanged();
        }
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        if (currTargetViewHost != null) {
            this.bgRect.set(0, 0, currTargetViewHost.getWidth(), currTargetViewHost.getHeight());
            ThreeStageShareTransitionProperty threeStageShareTransitionProperty = this.bgBoundTransitionProperty;
            if (threeStageShareTransitionProperty != null) {
                threeStageShareTransitionProperty.setValue(this.bgRect);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void prepare(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2, boolean z, boolean z2) {
        super.prepare(viewGroup, map, map2, z, z2);
        this.animShareProperties.addAll(getShareTransitionProperties());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void squeezeOrStretch(int i, int i2, int i3) {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void stop(Function1 function1) {
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        ViewParent parent = currTargetViewHost != null ? currTargetViewHost.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.suppressLayout(false);
        }
        this.animShareProperties.clear();
        super.stop(function1);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void transferFrom(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2) {
        Size size;
        if (iDeformationAnimationController instanceof ThreeStageDeformationAnimationController) {
            ThreeStageDeformationAnimationController threeStageDeformationAnimationController = (ThreeStageDeformationAnimationController) iDeformationAnimationController;
            ViewGroup currTargetViewHost = threeStageDeformationAnimationController.getCurrTargetViewHost();
            setCurrTargetViewHost(viewGroup);
            Iterator<T> it = threeStageDeformationAnimationController.getShareTransitionProperties().iterator();
            while (it.hasNext()) {
                ((ShareTransitionProperty) it.next()).onTransitionEnd(false);
            }
            if (currTargetViewHost != null) {
                resetChildrenFromOverlay(currTargetViewHost);
            }
            getAllSupportHost().clear();
            getAllSupportHost().putAll(map);
            getAllValidSize().clear();
            getAllValidSize().putAll(map2);
            for (Map.Entry<SpanSize, ? extends ViewGroup> entry : map.entrySet()) {
                if (!Intrinsics.areEqual(entry.getValue(), viewGroup) && (size = map2.get(entry.getKey())) != null) {
                    entry.getValue().measure(View.MeasureSpec.makeMeasureSpec(size.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(size.getHeight(), 1073741824));
                    entry.getValue().layout(0, 0, size.getWidth(), size.getHeight());
                }
            }
            ViewGroup currTargetViewHost2 = getCurrTargetViewHost();
            ViewParent parent = currTargetViewHost2 != null ? currTargetViewHost2.getParent() : null;
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.suppressLayout(true);
            }
            this.animShareProperties.clear();
            Set<ShareTransitionProperty> shareTransitionProperties = threeStageDeformationAnimationController.getShareTransitionProperties();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(shareTransitionProperties, 10));
            Iterator<T> it2 = shareTransitionProperties.iterator();
            while (it2.hasNext()) {
                ShareTransitionProperty shareTransitionPropertyMoveTo = ((ShareTransitionProperty) it2.next()).moveTo(viewGroup);
                this.animShareProperties.add(shareTransitionPropertyMoveTo);
                ThreeStageShareTransitionProperty threeStageShareTransitionProperty = shareTransitionPropertyMoveTo instanceof ThreeStageShareTransitionProperty ? (ThreeStageShareTransitionProperty) shareTransitionPropertyMoveTo : null;
                if (threeStageShareTransitionProperty != null) {
                    threeStageShareTransitionProperty.setDeformAnimateLister(this);
                }
                if (shareTransitionPropertyMoveTo instanceof ThreeStageWidthSharedTransitionProperty) {
                    ((ThreeStageWidthSharedTransitionProperty) shareTransitionPropertyMoveTo).setOnAnimateListener(this);
                }
                arrayList.add(shareTransitionPropertyMoveTo);
            }
            getShareTransitionProperties().clear();
            getShareTransitionProperties().addAll(arrayList);
            this.bgBoundTransitionProperty = new ThreeStageBoundSharedTransitionProperty(viewGroup, getTileBgId(), false);
            ViewGroup currTargetViewHost3 = getCurrTargetViewHost();
            if (currTargetViewHost3 != null) {
                this.bgRect.set(0, 0, currTargetViewHost3.getWidth(), currTargetViewHost3.getHeight());
                ThreeStageShareTransitionProperty threeStageShareTransitionProperty2 = this.bgBoundTransitionProperty;
                if (threeStageShareTransitionProperty2 != null) {
                    threeStageShareTransitionProperty2.setValue(this.bgRect);
                }
            }
            if (iDeformationAnimationController != this) {
                threeStageDeformationAnimationController.onAnimationEnd(true);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void updateFrame(IDeformationAnimationController.ComputeResult computeResult) {
        ViewGroup endHost;
        ViewGroup startHost = computeResult.getStartHost();
        if (startHost == null || (endHost = computeResult.getEndHost()) == null) {
            return;
        }
        for (ShareTransitionProperty shareTransitionProperty : getShareTransitionProperties()) {
            ThreeStageShareTransitionProperty threeStageShareTransitionProperty = shareTransitionProperty instanceof ThreeStageShareTransitionProperty ? (ThreeStageShareTransitionProperty) shareTransitionProperty : null;
            if (threeStageShareTransitionProperty != null) {
                threeStageShareTransitionProperty.setDeformAnimateLister(this);
            }
            shareTransitionProperty.setProgress(endHost, computeResult.getXProgressFromCurr(), computeResult.getYProgressFromCurr(), startHost);
        }
    }
}
