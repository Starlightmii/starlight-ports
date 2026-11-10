package com.oplus.systemui.plugins.qs.customize.component;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.component.proxy.CellSizeChangeProxy;
import com.oplus.systemui.plugins.qs.customize.view.EditTileAdapter;
import com.oplus.systemui.plugins.qs.customize.view.animation.pull.ContainerType;
import com.oplus.systemui.plugins.qs.customize.view.animation.pull.PullAnimationProxy;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.proxy.EmptyAreaGestureProxy;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusPagerStaggerSnapHelper;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.view.ViewExtKt;
import com.oplusos.systemui.common.viewholder.GridData;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSLeftBTilesComponent extends ViewController implements IQsComponent, IQSExpandOrCollapseAnimation {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSLeftBTilesComponent";
    public final /* synthetic */ PullAnimationProxy $$delegate_0;
    private final Lazy cellSizeChangeProxy$delegate;
    private final Lazy customLayoutManager$delegate;
    private final TileEditViewModel editViewModel;
    private IQSExpandOrCollapseAnimation.QSNodeChangeListener nodeChangedListener;
    private final List<GridData> otherAddedTiles;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final COUIRecyclerView recyclerView;
    private final Lazy tileAdapter$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static CellSizeChangeProxy $r8$lambda$G7C47hZ6jdLE3F81MBgD1KuQKus(OplusQSLeftBTilesComponent oplusQSLeftBTilesComponent) {
        return new CellSizeChangeProxy(oplusQSLeftBTilesComponent.getContext(), (RecyclerView) oplusQSLeftBTilesComponent.recyclerView, false, (Function1) new OplusQSLeftBTilesComponent$cellSizeChangeProxy$2$1(1, oplusQSLeftBTilesComponent, OplusQSLeftBTilesComponent.class, "updateRecyclerViewVisible", "updateRecyclerViewVisible(I)V", 0), (CellAreaProvider) null, 32);
    }

    /* JADX INFO: renamed from: $r8$lambda$S0hGauSeA9n9aVkyU-HAksJZu5k, reason: not valid java name */
    public static EditTileAdapter m126$r8$lambda$S0hGauSeA9n9aVkyUHAksJZu5k(OplusQSLeftBTilesComponent oplusQSLeftBTilesComponent) {
        EditTileAdapter editTileAdapter = new EditTileAdapter(oplusQSLeftBTilesComponent.recyclerView.getContext(), oplusQSLeftBTilesComponent.editViewModel, false);
        oplusQSLeftBTilesComponent.getCustomLayoutManager().getLayoutCallbackWrapper().addLayoutCallback(editTileAdapter, true);
        return editTileAdapter;
    }

    public OplusQSLeftBTilesComponent(COUIRecyclerView cOUIRecyclerView, TileEditViewModel tileEditViewModel, QSPanelAnimatorManager qSPanelAnimatorManager) {
        super(cOUIRecyclerView);
        this.$$delegate_0 = new PullAnimationProxy(cOUIRecyclerView, ContainerType.NO_PERSONAL_LAND_LEFT, qSPanelAnimatorManager);
        this.recyclerView = cOUIRecyclerView;
        this.editViewModel = tileEditViewModel;
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        OplusQSLeftBTilesComponent$$ExternalSyntheticLambda1 oplusQSLeftBTilesComponent$$ExternalSyntheticLambda1 = new OplusQSLeftBTilesComponent$$ExternalSyntheticLambda1(0);
        oplusQSLeftBTilesComponent$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.cellSizeChangeProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSLeftBTilesComponent$$ExternalSyntheticLambda1);
        OplusQSLeftBTilesComponent$$ExternalSyntheticLambda1 oplusQSLeftBTilesComponent$$ExternalSyntheticLambda2 = new OplusQSLeftBTilesComponent$$ExternalSyntheticLambda1(1);
        oplusQSLeftBTilesComponent$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.customLayoutManager$delegate = LazyKt__LazyJVMKt.lazy(oplusQSLeftBTilesComponent$$ExternalSyntheticLambda2);
        OplusQSLeftBTilesComponent$$ExternalSyntheticLambda1 oplusQSLeftBTilesComponent$$ExternalSyntheticLambda3 = new OplusQSLeftBTilesComponent$$ExternalSyntheticLambda1(2);
        oplusQSLeftBTilesComponent$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.tileAdapter$delegate = LazyKt__LazyJVMKt.lazy(oplusQSLeftBTilesComponent$$ExternalSyntheticLambda3);
        this.otherAddedTiles = new ArrayList();
    }

    private final CellSizeChangeProxy getCellSizeChangeProxy() {
        return (CellSizeChangeProxy) this.cellSizeChangeProxy$delegate.getValue();
    }

    private final StaggeredPagerLayoutManager getCustomLayoutManager() {
        return (StaggeredPagerLayoutManager) this.customLayoutManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EditTileAdapter getTileAdapter() {
        return (EditTileAdapter) this.tileAdapter$delegate.getValue();
    }

    public final void bindData$1(List list) {
        int colCount = getCustomLayoutManager().getLayoutConfig().getColCount() * getCustomLayoutManager().getLayoutConfig().getRowCount();
        int size = list.size();
        if (colCount > size) {
            colCount = size;
        }
        getCustomLayoutManager().autoGenLoc(colCount);
        getTileAdapter().submitList(list.subList(0, colCount), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList());
        StaggeredPagerLayoutManager.refreshPageCount$default(getCustomLayoutManager(), false, 3);
        updateRecyclerViewVisible$2(getCustomLayoutManager().getLayoutConfig().getRowCount());
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        OplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1 oplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1 = new OplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1(0);
        oplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUIRecyclerView.addOnLayoutChangeListener(oplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        super.bindTargetProperties(set);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return super.createSpringTransition(str);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void destroyComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusQSLeftBTilesComponent:start-----------------------------------------------");
        getCustomLayoutManager().dump(printWriter, strArr);
        getTileAdapter().dump(printWriter, strArr);
        printWriter.println("OplusQSLeftBTilesComponent:end-----------------------------------------------");
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return this.$$delegate_0.getAnimatorView(i);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return this.$$delegate_0.getSubSize();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        super.injectHostAbility(iComponentHostAbility);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        this.$$delegate_0.getClass();
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        super.onEnterAddingMode(z, z2, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        super.onEnterDragMode(z, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
        getTileAdapter().onThemeApplied(z);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        getCellSizeChangeProxy().onViewAttached();
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        getCellSizeChangeProxy().onViewDetached();
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
        this.$$delegate_0.resetNonCurrPageToExpanded();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        this.$$delegate_0.saveLatestNoEditModeLocList(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
        this.$$delegate_0.setCusTranslationY(f, i, i2);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setEmptyTouchListener(Function1 function1) {
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        OplusQSLeftBTilesComponent$$ExternalSyntheticLambda0 oplusQSLeftBTilesComponent$$ExternalSyntheticLambda0 = new OplusQSLeftBTilesComponent$$ExternalSyntheticLambda0();
        oplusQSLeftBTilesComponent$$ExternalSyntheticLambda0.f$0 = function1;
        VarHandle.storeStoreFence();
        ViewExtKt.addOnTouchListener(this.recyclerView, new EmptyAreaGestureProxy(cOUIRecyclerView, oplusQSLeftBTilesComponent$$ExternalSyntheticLambda0), false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setLayerType(boolean z) {
        int i = z ? 2 : 0;
        Iterator it = ViewGroupKt.getChildren(this.recyclerView).iterator();
        while (it.hasNext()) {
            ((View) it.next()).setLayerType(i, null);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setListening(boolean z) {
        getTileAdapter().setListening(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        this.nodeChangedListener = qSNodeChangeListener;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        this.$$delegate_0.setPageSelectedListener(qSNodePageSelectedListener);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController, com.oplus.systemui.plugins.qs.IQsComponent
    public final void startComponent() {
        init();
        COUIThemeOverlay.getInstance().applyThemeOverlays(this.recyclerView.getContext());
        new OplusPagerStaggerSnapHelper().attachToRecyclerView(this.recyclerView);
        RecyclerView recyclerView = this.recyclerView;
        updateRecyclerViewVisible$2(getCustomLayoutManager().getLayoutConfig().getRowCount());
        recyclerView.setAdapter(getTileAdapter());
        recyclerView.setLayoutManager(getCustomLayoutManager());
        getCustomLayoutManager().setRecyclerView(recyclerView);
        recyclerView.setFlingRatio(0.0f);
        OplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1 oplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1 = new OplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1(1);
        oplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1.this$0 = this;
        VarHandle.storeStoreFence();
        recyclerView.addOnLayoutChangeListener(oplusQSLeftBTilesComponent$bindData$$inlined$doOnNextLayout$1);
        RepeatWhenAttachedKt.repeatWhenAttached(this.recyclerView, Dispatchers.getMain().getImmediate(), new OplusQSLeftBTilesComponent$collectFlow$1(this, null));
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        getTileAdapter().updateColor(colorType);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateDrawable(List<SpanSize> list, boolean z) {
        getTileAdapter().updateDrawable(list, z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconDrawable(boolean z) {
        getTileAdapter().updateIconDrawable(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconTint() {
        getTileAdapter().updateIconTint();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateNodeTranslationY() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateOutline(List<SpanSize> list) {
        getTileAdapter().updateOutline(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updatePivotXY(int i, int i2, boolean z) {
        this.$$delegate_0.updatePivotXY(i, i2, z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateQuickEntranceDrawable() {
    }

    public final void updateRecyclerViewVisible$2(int i) {
        boolean z = this.recyclerView.getVisibility() == 4;
        this.recyclerView.setVisibility(i <= 0 || getTileAdapter().getDataList().isEmpty() ? 4 : 0);
        if ((this.recyclerView.getVisibility() == 4) == z || this.recyclerView.getVisibility() != 0) {
            return;
        }
        int childCount = getCustomLayoutManager().getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getCustomLayoutManager().getChildAt(i2);
            if (childAt != null) {
                childAt.setTransitionAlpha(1.0f);
            }
            View childAt2 = getCustomLayoutManager().getChildAt(i2);
            if (childAt2 != null) {
                childAt2.setScaleX(1.0f);
            }
            View childAt3 = getCustomLayoutManager().getChildAt(i2);
            if (childAt3 != null) {
                childAt3.setScaleY(1.0f);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        if (z) {
            getCellSizeChangeProxy().refreshGridNum();
            bindData$1(this.otherAddedTiles);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.$$delegate_0.updateState(i, i2, f, f2, f3);
    }
}
