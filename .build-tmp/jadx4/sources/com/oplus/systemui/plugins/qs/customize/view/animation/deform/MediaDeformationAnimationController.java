package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableMediaViewHolder;
import com.oplus.systemui.qs.base.shareanimation.BoundShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.FadeXShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaDeformationAnimationController extends BaseDeformationAnimationController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaDeformationAnimationController";
    private BoundShareTransitionProperty bgBoundTransitionProperty;
    private final Lazy lazySharedElementIdMutableSet$delegate;
    private final Lazy lazyViewIds$delegate;
    private final Rect rectHost = new Rect();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MediaDeformationAnimationController() {
        final int i = 0;
        this.lazySharedElementIdMutableSet$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.animation.deform.MediaDeformationAnimationController$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return SetsKt__SetsKt.mutableSetOf(new Integer[]{2131362254, 2131363752, 2131364338, 2131364373, 2131364372, 2131364368, 2131364329, 2131364344, 2131364343, 2131364342, 2131364313});
                    default:
                        return new int[][]{new int[]{2131364368, 2131364373, 2131364372}, new int[]{2131364329, 2131364338}, new int[]{2131364365, 2131364360, 2131364351}, new int[]{2131364344, 2131364343, 2131364313, 2131364342}};
                }
            }
        });
        final int i2 = 1;
        this.lazyViewIds$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.customize.view.animation.deform.MediaDeformationAnimationController$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return SetsKt__SetsKt.mutableSetOf(new Integer[]{2131362254, 2131363752, 2131364338, 2131364373, 2131364372, 2131364368, 2131364329, 2131364344, 2131364343, 2131364342, 2131364313});
                    default:
                        return new int[][]{new int[]{2131364368, 2131364373, 2131364372}, new int[]{2131364329, 2131364338}, new int[]{2131364365, 2131364360, 2131364351}, new int[]{2131364344, 2131364343, 2131364313, 2131364342}};
                }
            }
        });
    }

    private final Set<Integer> getLazySharedElementIdMutableSet() {
        return (Set) this.lazySharedElementIdMutableSet$delegate.getValue();
    }

    private final int[][] getLazyViewIds() {
        return (int[][]) this.lazyViewIds$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public IDeformationAnimationController.ComputeResult calculateAnimationStartAndEndView(int i, int i2, int i3, int i4) {
        Object next;
        SpanSize default_size;
        Map<SpanSize, Size> allValidSize = getAllValidSize();
        EditableMediaViewHolder.Companion companion = EditableMediaViewHolder.Companion;
        if (!allValidSize.containsKey(companion.getDEFAULT_SIZE()) || !getAllValidSize().containsKey(companion.getBIGGER_SIZE())) {
            return getComputeResult();
        }
        getComputeResult().reset();
        int i5 = i3 - i;
        if (i5 == 0) {
            return getComputeResult();
        }
        ViewGroup viewGroup = getAllSupportHost().get(companion.getBIGGER_SIZE());
        ViewGroup viewGroup2 = getAllSupportHost().get(companion.getDEFAULT_SIZE());
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        Object next2 = null;
        SpanSize size = currTargetViewHost != null ? DeformationFrameLayout.Companion.getSize(currTargetViewHost) : null;
        if (viewGroup == null || viewGroup2 == null || size == null) {
            return getComputeResult();
        }
        Iterator<T> it = getAllValidSize().values().iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int width = ((Size) next).getWidth();
                do {
                    Object next3 = it.next();
                    int width2 = ((Size) next3).getWidth();
                    if (width > width2) {
                        next = next3;
                        width = width2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Size size2 = (Size) next;
        int width3 = size2 != null ? size2.getWidth() : 0;
        Iterator<T> it2 = getAllValidSize().values().iterator();
        if (it2.hasNext()) {
            next2 = it2.next();
            if (it2.hasNext()) {
                int width4 = ((Size) next2).getWidth();
                do {
                    Object next4 = it2.next();
                    int width5 = ((Size) next4).getWidth();
                    if (width4 < width5) {
                        next2 = next4;
                        width4 = width5;
                    }
                } while (it2.hasNext());
            }
        }
        Size size3 = (Size) next2;
        int width6 = size3 != null ? size3.getWidth() : 0;
        if (i5 > 0 && i3 > width3) {
            default_size = EditableMediaViewHolder.Companion.getBIGGER_SIZE();
        } else {
            if (i5 >= 0 || i3 >= width6) {
                return getComputeResult();
            }
            default_size = EditableMediaViewHolder.Companion.getDEFAULT_SIZE();
        }
        Size size4 = getAllValidSize().get(default_size);
        if (size4 != null) {
            float fComputeProgress = DeformUtils.computeProgress(i, size4.getWidth(), i3);
            IDeformationAnimationController.ComputeResult computeResult = getComputeResult();
            computeResult.setEndHost(getAllSupportHost().get(default_size));
            computeResult.setXProgressFromCurr(fComputeProgress);
            computeResult.setYProgressFromCurr(fComputeProgress);
        }
        IDeformationAnimationController.ComputeResult computeResult2 = getComputeResult();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "MediaDeformationAnimationController-->".concat("[calculateAnimationStartAndEndView] return " + computeResult2));
        }
        return computeResult2;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController
    public Map<ShareTransitionProperty, ViewGroup> createShareTransitionProperties(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map) {
        Map mapCreateMapBuilder = MapsKt__MapsJVMKt.createMapBuilder();
        ViewGroup viewGroup2 = map.get(EditableMediaViewHolder.Companion.getBIGGER_SIZE());
        if (viewGroup2 == null) {
            BoundShareTransitionProperty boundShareTransitionProperty = new BoundShareTransitionProperty(viewGroup, 2131362254, false);
            this.bgBoundTransitionProperty = boundShareTransitionProperty;
            mapCreateMapBuilder.put(boundShareTransitionProperty, viewGroup);
        } else {
            Iterator<T> it = getLazySharedElementIdMutableSet().iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                if (iIntValue == 2131363752) {
                    mapCreateMapBuilder.put(new FadeXShareTransitionProperty(viewGroup, iIntValue), viewGroup2);
                } else {
                    BoundShareTransitionProperty boundShareTransitionProperty2 = new BoundShareTransitionProperty(viewGroup, iIntValue, false);
                    if (iIntValue == 2131362254) {
                        this.bgBoundTransitionProperty = boundShareTransitionProperty2;
                    }
                }
            }
        }
        return MapsKt__MapsJVMKt.build(mapCreateMapBuilder);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void onFlingStarted(ViewGroup viewGroup, SpanSize spanSize) {
        Object next;
        super.onFlingStarted(viewGroup, spanSize);
        Iterator<T> it = getShareTransitionProperties().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            ShareTransitionProperty shareTransitionProperty = (ShareTransitionProperty) next;
            if ((shareTransitionProperty instanceof BoundShareTransitionProperty) && ((BoundShareTransitionProperty) shareTransitionProperty).getSharedElementId() == 2131362254) {
                break;
            }
        }
        this.bgBoundTransitionProperty = next instanceof BoundShareTransitionProperty ? (BoundShareTransitionProperty) next : null;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:58:0x00e4  */
    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void squeezeOrStretch(int i, int i2, int i3) {
        int i4;
        float f;
        int i5;
        float f2;
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        if (currTargetViewHost == null || getLazyViewIds().length == 0) {
            return;
        }
        int length = getLazyViewIds().length;
        int i6 = 1;
        while (true) {
            if (i6 >= length) {
                break;
            }
            int[] iArr = getLazyViewIds()[i6];
            if (iArr.length != 0) {
                int length2 = iArr.length;
                for (int i7 = 0; i7 < length2; i7++) {
                    View viewFindViewByIdUseCache = ShareTransitionProperty.Companion.findViewByIdUseCache(currTargetViewHost, iArr[i7], true);
                    if (viewFindViewByIdUseCache != null) {
                        viewFindViewByIdUseCache.setTranslationX(i / (iArr.length - i7));
                    }
                }
                for (int i8 : iArr) {
                    View viewFindViewByIdUseCache2 = ShareTransitionProperty.Companion.findViewByIdUseCache(currTargetViewHost, i8, true);
                    if (viewFindViewByIdUseCache2 != null) {
                        if (i6 != 1) {
                            if (i6 != 2) {
                                f2 = i2;
                            } else {
                                i5 = i2 / 2;
                            }
                            viewFindViewByIdUseCache2.setTranslationY(f2);
                        } else {
                            i5 = i2 / 3;
                        }
                        f2 = i5;
                        viewFindViewByIdUseCache2.setTranslationY(f2);
                    }
                }
            }
            i6++;
        }
        int length3 = getLazyViewIds()[0].length;
        for (int i9 = 0; i9 < length3; i9++) {
            ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
            View viewFindViewByIdUseCache3 = companion.findViewByIdUseCache(currTargetViewHost, getLazyViewIds()[0][i9], true);
            if (viewFindViewByIdUseCache3 != null) {
                Map<SpanSize, Size> allValidSize = getAllValidSize();
                EditableMediaViewHolder.Companion companion2 = EditableMediaViewHolder.Companion;
                if (allValidSize.containsKey(companion2.getBIGGER_SIZE())) {
                    Size size = getAllValidSize().get(companion2.getBIGGER_SIZE());
                    if (i3 >= (size != null ? size.getWidth() : 0)) {
                        f = i / 2;
                    } else {
                        f = i;
                    }
                } else {
                    f = i;
                }
                viewFindViewByIdUseCache3.setTranslationX(f);
            }
            View viewFindViewByIdUseCache4 = companion.findViewByIdUseCache(currTargetViewHost, getLazyViewIds()[0][i9], true);
            if (viewFindViewByIdUseCache4 != null) {
                Map<SpanSize, Size> allValidSize2 = getAllValidSize();
                EditableMediaViewHolder.Companion companion3 = EditableMediaViewHolder.Companion;
                if (allValidSize2.containsKey(companion3.getBIGGER_SIZE())) {
                    Size size2 = getAllValidSize().get(companion3.getBIGGER_SIZE());
                    if (i3 >= (size2 != null ? size2.getWidth() : 0)) {
                        i4 = i2 / 3;
                    } else {
                        i4 = i2 / 2;
                    }
                } else {
                    i4 = i2 / 2;
                }
                viewFindViewByIdUseCache4.setTranslationY(i4);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void updateFrame(IDeformationAnimationController.ComputeResult computeResult) {
        super.updateFrame(computeResult);
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        if (currTargetViewHost != null) {
            this.rectHost.set(0, 0, currTargetViewHost.getWidth(), currTargetViewHost.getHeight());
            BoundShareTransitionProperty boundShareTransitionProperty = this.bgBoundTransitionProperty;
            if (boundShareTransitionProperty != null) {
                boundShareTransitionProperty.setValue(this.rectHost);
            }
        }
    }
}
