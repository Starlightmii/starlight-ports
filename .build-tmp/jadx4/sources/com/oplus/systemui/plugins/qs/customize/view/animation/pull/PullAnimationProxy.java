package com.oplus.systemui.plugins.qs.customize.view.animation.pull;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.fragment.app.Fragment$5$;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.view.EditTileAdapter;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditablePluginViewHolder;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapWithDefaultKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Ref;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PullAnimationProxy implements IQSExpandOrCollapseAnimation {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PullAnimationProxy";
    private final ContainerType containerType;
    public StaggeredPagerLayoutManager customLayoutManager;
    private List<Loc> latestNoEditModeLocList;
    private final Map<Integer, Float> negProgressMap;
    private final Map<Integer, Float> overTransYMap;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final COUIRecyclerView recyclerView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PullAnimationProxy(COUIRecyclerView cOUIRecyclerView, ContainerType containerType, QSPanelAnimatorManager qSPanelAnimatorManager) {
        this.recyclerView = cOUIRecyclerView;
        this.containerType = containerType;
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        if (cOUIRecyclerView.isAttachedToWindow()) {
            RecyclerView.LayoutManager layoutManager = cOUIRecyclerView.getLayoutManager();
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
            if (staggeredPagerLayoutManager == null) {
                Path$.ExternalSyntheticBUOutline0.m("recyclerview layoutManager is not StaggeredPagerLayoutManager");
                throw null;
            }
            this.customLayoutManager = staggeredPagerLayoutManager;
        } else {
            PullAnimationProxy$special$$inlined$doOnAttach$1 pullAnimationProxy$special$$inlined$doOnAttach$1 = new PullAnimationProxy$special$$inlined$doOnAttach$1();
            pullAnimationProxy$special$$inlined$doOnAttach$1.$this_doOnAttach = cOUIRecyclerView;
            pullAnimationProxy$special$$inlined$doOnAttach$1.this$0 = this;
            VarHandle.storeStoreFence();
            cOUIRecyclerView.addOnAttachStateChangeListener(pullAnimationProxy$special$$inlined$doOnAttach$1);
        }
        this.latestNoEditModeLocList = CollectionsKt__CollectionsKt.emptyList();
        this.negProgressMap = MapsKt__MapWithDefaultKt.withDefaultMutable(new LinkedHashMap(), new PullAnimationProxy$$ExternalSyntheticLambda0());
        this.overTransYMap = MapsKt__MapWithDefaultKt.withDefaultMutable(new LinkedHashMap(), new PullAnimationProxy$$ExternalSyntheticLambda0());
    }

    private final int getCurrentPage() {
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.customLayoutManager;
        if (staggeredPagerLayoutManager == null) {
            return -1;
        }
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        return staggeredPagerLayoutManager.getCurrentPageIndex();
    }

    private final List<Loc> getSpecifyLoc() {
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.customLayoutManager;
        if (staggeredPagerLayoutManager == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        return staggeredPagerLayoutManager.getLayoutConfig().getSpecifyLoc();
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        SpanSize size;
        if (isTileDataEmpty()) {
            return null;
        }
        Iterator<Loc> it = getSpecifyLoc().iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            Loc next = it.next();
            if (next.getStartRow() == i && next.getPageIndex() == getCurrentPage()) {
                break;
            }
            i2++;
        }
        if (i2 != -1) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(i2);
            if (viewHolderFindViewHolderForAdapterPosition instanceof EditablePluginViewHolder) {
                return ((RecyclerView.ViewHolder) ((EditablePluginViewHolder) viewHolderFindViewHolderForAdapterPosition)).itemView;
            }
            if (viewHolderFindViewHolderForAdapterPosition != null) {
                return viewHolderFindViewHolderForAdapterPosition.itemView;
            }
            return null;
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        PullAnimationProxy$$ExternalSyntheticLambda1 pullAnimationProxy$$ExternalSyntheticLambda1 = new PullAnimationProxy$$ExternalSyntheticLambda1();
        pullAnimationProxy$$ExternalSyntheticLambda1.f$0 = objectRef;
        VarHandle.storeStoreFence();
        List<Loc> specifyLoc = getSpecifyLoc();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (Object obj : specifyLoc) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            Loc loc = (Loc) obj;
            Integer numValueOf = (loc.getStartRow() >= i || loc.getPageIndex() != getCurrentPage()) ? null : Integer.valueOf(i3);
            if (numValueOf != null) {
                arrayList.add(numValueOf);
            }
            i3 = i4;
        }
        int size2 = arrayList.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj2 = arrayList.get(i5);
            i5++;
            int iIntValue = ((Number) obj2).intValue();
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition2 = this.recyclerView.findViewHolderForAdapterPosition(iIntValue);
            if (viewHolderFindViewHolderForAdapterPosition2 instanceof EditablePluginViewHolder) {
                GridData gridData = ((EditablePluginViewHolder) viewHolderFindViewHolderForAdapterPosition2).getGridData();
                if (i - getSpecifyLoc().get(iIntValue).getStartRow() < ((gridData == null || (size = gridData.getSize()) == null) ? 0 : size.getRowSpan())) {
                    pullAnimationProxy$$ExternalSyntheticLambda1.invoke(Integer.valueOf(iIntValue), viewHolderFindViewHolderForAdapterPosition2);
                }
            }
        }
        return (View) objectRef.element;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.customLayoutManager;
        if (staggeredPagerLayoutManager == null) {
            return 0;
        }
        if (staggeredPagerLayoutManager == null) {
            staggeredPagerLayoutManager = null;
        }
        return staggeredPagerLayoutManager.getLayoutConfig().getRowCount();
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        return true;
    }

    public final boolean isTileDataEmpty() {
        return getSpecifyLoc().isEmpty();
    }

    public final float negativeSpringOffsetY(int i, int i2) {
        if (!((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue() || this.containerType != ContainerType.NO_PERSONAL_PORT_OR_LAND_RIGHT) {
            i2 = i;
        }
        return (1.0f - ((Number) MapsKt__MapsKt.getValue(this.negProgressMap, Integer.valueOf(i))).floatValue()) * (-60.0f) * i2;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
        SpanSize size;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(getCurrentPage(), "[resetNonCurrPageToExpanded], currentPage="), null, 8);
        int i = 0;
        for (Object obj : getSpecifyLoc()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (((Loc) obj).getPageIndex() != getCurrentPage()) {
                View childAt = this.recyclerView.getChildAt(i);
                if (childAt != null) {
                    childAt.setTransitionAlpha(1.0f);
                    childAt.setScaleX(1.0f);
                    childAt.setScaleY(1.0f);
                    childAt.setTranslationY(0.0f);
                    this.qsPanelAnimatorManager.cancelElementAnimators(childAt);
                }
                if (this.recyclerView.findViewHolderForAdapterPosition(i) instanceof EditablePluginViewHolder) {
                    EditablePluginViewHolder editablePluginViewHolder = (EditablePluginViewHolder) this.recyclerView.findViewHolderForAdapterPosition(i);
                    GridData gridData = editablePluginViewHolder.getGridData();
                    int rowSpan = (gridData == null || (size = gridData.getSize()) == null) ? 0 : size.getRowSpan();
                    for (int i3 = 0; i3 < rowSpan; i3++) {
                        editablePluginViewHolder.updateInnerViewState(i3, 1.0f, 1.0f, 1.0f);
                        editablePluginViewHolder.setCusTranslationY(i3, 0.0f);
                    }
                }
            }
            i = i2;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        this.latestNoEditModeLocList = list;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
        int iNextIndex;
        View view;
        RecyclerView.Adapter adapter = this.recyclerView.getAdapter();
        EditTileAdapter editTileAdapter = adapter instanceof EditTileAdapter ? (EditTileAdapter) adapter : null;
        List<Loc> specifyLoc = (editTileAdapter != null && editTileAdapter.isEditMode && this.containerType == ContainerType.PERSONAL) ? this.latestNoEditModeLocList : getSpecifyLoc();
        Iterator<Loc> it = specifyLoc.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            Loc next = it.next();
            if (next.getStartRow() == i2 && next.getPageIndex() == getCurrentPage()) {
                break;
            } else {
                i3++;
            }
        }
        ListIterator<Loc> listIterator = specifyLoc.listIterator(specifyLoc.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            Loc locPrevious = listIterator.previous();
            if (locPrevious.getStartRow() == i2 && locPrevious.getPageIndex() == getCurrentPage()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        Map<Integer, Float> map = this.overTransYMap;
        if (i3 == -1) {
            int i4 = (i + i2) - 1;
            map.put(Integer.valueOf(i4 >= 0 ? i4 : 0), Float.valueOf(0.0f));
            return;
        }
        int i5 = (i + i2) - 1;
        map.put(Integer.valueOf(i5 < 0 ? 0 : i5), Float.valueOf(f));
        float fNegativeSpringOffsetY = negativeSpringOffsetY(i5 >= 0 ? i5 : 0, i2) + f;
        int i6 = iNextIndex + 1;
        while (i3 < i6) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(i3);
            if (viewHolderFindViewHolderForAdapterPosition != null && (view = viewHolderFindViewHolderForAdapterPosition.itemView) != null) {
                view.setTranslationY(fNegativeSpringOffsetY);
            }
            i3++;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x010a, code lost:
    
        if (r11 != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x010c, code lost:
    
        r1 = -r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0124, code lost:
    
        if (r11 != false) goto L52;
     */
    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updatePivotXY(int r17, int r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.view.animation.pull.PullAnimationProxy.updatePivotXY(int, int, boolean):void");
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        int iNextIndex;
        COUIRecyclerView cOUIRecyclerView;
        List<Drawable> allEmptyDrawables;
        if (f3 > -1.0f) {
            this.negProgressMap.put(Integer.valueOf(i2), Float.valueOf(f3));
        }
        Iterator<Loc> it = getSpecifyLoc().iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            Loc next = it.next();
            if (next.getStartRow() == i && next.getPageIndex() == getCurrentPage()) {
                break;
            } else {
                i3++;
            }
        }
        List<Loc> specifyLoc = getSpecifyLoc();
        ListIterator<Loc> listIterator = specifyLoc.listIterator(specifyLoc.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            Loc locPrevious = listIterator.previous();
            if (locPrevious.getStartRow() == i && locPrevious.getPageIndex() == getCurrentPage()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (i3 == -1) {
            if (this.containerType != ContainerType.PERSONAL) {
                int currentPage = getCurrentPage();
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateState start == -1, subIndex = ", i, i2, ", nodeIndex = ", ", alpha = ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", scale = ", f2, ", currentPage = ");
                sbM.append(currentPage);
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, sbM.toString());
                return;
            }
            return;
        }
        int i4 = iNextIndex + 1;
        while (true) {
            cOUIRecyclerView = this.recyclerView;
            if (i3 >= i4) {
                break;
            }
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = cOUIRecyclerView.findViewHolderForAdapterPosition(i3);
            View view = viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null;
            if (view == null) {
                int currentPage2 = getCurrentPage();
                StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("updateState itemView == null, subIndex = ", i, i2, ", nodeIndex = ", ", alpha = ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f, ", scale = ", f2, ", currentPage = ");
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(sbM2, currentPage2, i3, ", position = "));
            }
            if (view != null) {
                view.setTransitionAlpha(f);
            }
            if (view != null) {
                view.setScaleX(f2);
            }
            if (view != null) {
                view.setScaleY(f2);
            }
            if (view != null) {
                view.setTranslationY(negativeSpringOffsetY(i2, i) + ((Number) MapsKt__MapsKt.getValue(this.overTransYMap, Integer.valueOf(i2))).floatValue());
            }
            i3++;
            i4 = i4;
        }
        RecyclerView.Adapter adapter = cOUIRecyclerView.getAdapter();
        EditTileAdapter editTileAdapter = adapter instanceof EditTileAdapter ? (EditTileAdapter) adapter : null;
        if (editTileAdapter != null) {
            if (!editTileAdapter.isEditMode) {
                editTileAdapter = null;
            }
            if (editTileAdapter != null) {
                Drawable background = this.recyclerView.getBackground();
                if (background != null) {
                    background.setAlpha((int) (f * 255.0f));
                }
                RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
                StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
                if (staggeredPagerLayoutManager == null || (allEmptyDrawables = staggeredPagerLayoutManager.getAllEmptyDrawables()) == null) {
                    return;
                }
                Iterator<T> it2 = allEmptyDrawables.iterator();
                while (it2.hasNext()) {
                    ((Drawable) it2.next()).setAlpha((int) (f * 255.0f));
                }
            }
        }
    }
}
