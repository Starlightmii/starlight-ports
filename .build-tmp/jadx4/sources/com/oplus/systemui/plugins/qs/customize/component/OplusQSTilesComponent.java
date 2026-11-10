package com.oplus.systemui.plugins.qs.customize.component;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.collection.FloatList$;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.component.proxy.CellSizeChangeProxy;
import com.oplus.systemui.plugins.qs.customize.component.proxy.DragCallbackProxy;
import com.oplus.systemui.plugins.qs.customize.view.EditTileAdapter;
import com.oplus.systemui.plugins.qs.customize.view.IHostContainerAbility;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.editmodeswitch.EditModeSwitchAnimationProxyForPager;
import com.oplus.systemui.plugins.qs.customize.view.animation.pull.ContainerType;
import com.oplus.systemui.plugins.qs.customize.view.animation.pull.PullAnimationProxy;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.plugins.qs.proxy.EmptyAreaGestureProxy;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.springtransition.AlphaTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.springtransition.SizeChangeBoundsTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusEditItemAnimator;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusPagerStaggerSnapHelper;
import com.oplus.systemui.qs.base.widget.recyclerview.RecyclerViewHeightChangeAnimation;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplus.systemui.qs.base.widget.recyclerview.ViewWithSizeChange;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.PagerCallbackWrapper;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import com.oplusos.systemui.common.viewholder.CellInfo;
import com.oplusos.systemui.common.viewholder.GridData;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent extends ViewController implements IQsComponent, IQSExpandOrCollapseAnimation, ITransitionDesc, IEditModeSwitcher, ILayoutCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSTilesComponent";
    public final /* synthetic */ PullAnimationProxy $$delegate_0;
    private final IHostContainerAbility abilityForViewHolder;
    private IntentTarget addingArea;
    private final OplusQSTilesComponent$animationListener$1 animationListener;
    private final Lazy cellSizeChangeProxy$delegate;
    public float curPositionOffset;
    private final Lazy customLayoutManager$delegate;
    private final Lazy dragCallbackProxy$delegate;
    private final RecyclerViewDragHelper dragHelper;
    private final EditModeSwitchAnimationManager editModeSwitchAnimationManager;
    private final TileEditViewModel editViewModel;
    private final Lazy emptyPlaceHolderAction$delegate;
    private IComponentHostAbility hostAbility;
    private final OplusQSPageIndicator indicatorView;
    public boolean isCollapseQSPanelStart;
    public boolean isNeedItemAnimator;
    private final Lazy itemSpringAnimator$delegate;
    private IQSExpandOrCollapseAnimation.QSNodeChangeListener nodeChangedListener;
    private IQSExpandOrCollapseAnimation.QSNodePageSelectedListener nodePageSelectedListener;
    private Function2 onAdd;
    private Runnable onAddingTransitionEnd;
    private final IOplusGcSupressionExt oplusGcSupressionExt;
    private final Lazy oplusItemShowHideAnimator$delegate;
    private final List<GridData> otherAddedTiles;
    public int pageIndexBeforeEnterAdding;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final OplusQSRootView qsRootView;
    private final COUIRecyclerView recyclerView;
    private final Runnable removeAnimateEnd;
    private final Lazy sceneSwitcher$delegate;
    public boolean shouldInterceptScroll;
    private final OplusSeparateQSPlugin.IStub stub;
    private final Lazy tileAdapter$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final long getAdding_mode_transition_duration() {
            return OliveLinkConnector.INSTANCE.safeScalar(OliveLinkConnector.SEP_QS_SERVER_NAME, "adding_mode_transition_duration", "adding_mode_transition", 400L, "进入待添加页面翻页时延").get().longValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long getRemove_animate_duration() {
            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "REMOVE_ANIMATE_DURATION", null, 300L, 4).get().longValue();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent$onViewAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnScrollChangeListener {
        public /* synthetic */ OplusQSTilesComponent this$0;

        @Override // android.view.View.OnScrollChangeListener
        public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
            int scrollState = this.this$0.recyclerView.getScrollState();
            this.this$0.qsRootView.setDisallowChildIntercept(!OplusQSTilesComponent.access$isEditMode(this.this$0) && i <= 0 && ((scrollState == 2 && (this.this$0.curPositionOffset > 0.3f ? 1 : (this.this$0.curPositionOffset == 0.3f ? 0 : -1)) < 0) || scrollState == 0));
            if (this.this$0.qsRootView.getDisallowChildIntercept()) {
                this.this$0.qsRootView.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent$onViewAttached$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnTouchListener {
        public /* synthetic */ OplusQSTilesComponent this$0;

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1) {
                this.this$0.setShouldInterceptScroll(false);
            }
            return motionEvent.getAction() == 2 && this.this$0.getShouldInterceptScroll();
        }
    }

    public static RecyclerViewHeightChangeAnimation $r8$lambda$2za9LQtwjyZQr6DAJ25eQnzxbI4(OplusQSTilesComponent oplusQSTilesComponent) {
        return new RecyclerViewHeightChangeAnimation(oplusQSTilesComponent.recyclerView, oplusQSTilesComponent.getCustomLayoutManager());
    }

    /* JADX INFO: renamed from: $r8$lambda$7x-7OwQdhXfDCW2WrekrPTAd4a0, reason: not valid java name */
    public static CellSizeChangeProxy m136$r8$lambda$7x7OwQdhXfDCW2WrekrPTAd4a0(OplusQSTilesComponent oplusQSTilesComponent) {
        return new CellSizeChangeProxy(oplusQSTilesComponent.getContext(), (RecyclerView) oplusQSTilesComponent.recyclerView, false, (Function1) new OplusQSTilesComponent$cellSizeChangeProxy$2$1(1, oplusQSTilesComponent, OplusQSTilesComponent.class, "updateRecyclerViewVisible", "updateRecyclerViewVisible(I)V", 0), (CellAreaProvider) null, 48);
    }

    public static DragCallbackProxy $r8$lambda$NolZBkGq9f_XUvSWIgXKDckcBaE(OplusQSTilesComponent oplusQSTilesComponent) {
        COUIRecyclerView cOUIRecyclerView = oplusQSTilesComponent.recyclerView;
        IntentTarget intentTarget = IntentTarget.OTHER_LIST;
        EditModeSwitchAnimationManager editModeSwitchAnimationManager = oplusQSTilesComponent.editModeSwitchAnimationManager;
        IComponentHostAbility iComponentHostAbility = oplusQSTilesComponent.hostAbility;
        OplusQSTilesComponent$dragCallbackProxy$2$1 oplusQSTilesComponent$dragCallbackProxy$2$1 = new OplusQSTilesComponent$dragCallbackProxy$2$1();
        oplusQSTilesComponent$dragCallbackProxy$2$1.this$0 = oplusQSTilesComponent;
        VarHandle.storeStoreFence();
        return new DragCallbackProxy(cOUIRecyclerView, intentTarget, editModeSwitchAnimationManager, iComponentHostAbility, oplusQSTilesComponent$dragCallbackProxy$2$1, "B");
    }

    /* JADX INFO: renamed from: $r8$lambda$dmAUnaNumvexSA3Tm8BQKCx-ejk, reason: not valid java name */
    public static EditModeSwitchAnimationProxyForPager m137$r8$lambda$dmAUnaNumvexSA3Tm8BQKCxejk(OplusQSTilesComponent oplusQSTilesComponent) {
        COUIRecyclerView cOUIRecyclerView = oplusQSTilesComponent.recyclerView;
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(oplusQSTilesComponent);
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda0 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(8);
        oplusQSTilesComponent$$ExternalSyntheticLambda0.f$0 = oplusQSTilesComponent;
        VarHandle.storeStoreFence();
        return new EditModeSwitchAnimationProxyForPager(cOUIRecyclerView, listListOf, oplusQSTilesComponent$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: renamed from: $r8$lambda$iODuodBQnBPQRLJ4i-6bkgsAxTQ, reason: not valid java name */
    public static EmptyPlaceHolderTilesAction m138$r8$lambda$iODuodBQnBPQRLJ4i6bkgsAxTQ(OplusQSTilesComponent oplusQSTilesComponent) {
        return new EmptyPlaceHolderTilesAction(oplusQSTilesComponent.recyclerView);
    }

    /* JADX INFO: renamed from: $r8$lambda$kJEOkrbJN0Y9mwOd-goiopIPesA, reason: not valid java name */
    public static OplusItemSpringAnimator m139$r8$lambda$kJEOkrbJN0Y9mwOdgoiopIPesA(OplusQSTilesComponent oplusQSTilesComponent) {
        OplusItemSpringAnimator oplusItemSpringAnimator = new OplusItemSpringAnimator(new OplusEditItemAnimator(), oplusQSTilesComponent.animationListener);
        oplusItemSpringAnimator.setMoveDuration(150L);
        oplusItemSpringAnimator.setChangeDuration(300L);
        return oplusItemSpringAnimator;
    }

    /* JADX INFO: renamed from: $r8$lambda$x-yfeCWNQKP31aO5k04MRUx7eww, reason: not valid java name */
    public static EditTileAdapter m140$r8$lambda$xyfeCWNQKP31aO5k04MRUx7eww(OplusQSTilesComponent oplusQSTilesComponent) {
        EditTileAdapter editTileAdapter = new EditTileAdapter(oplusQSTilesComponent.recyclerView.getContext(), oplusQSTilesComponent.editViewModel, false);
        oplusQSTilesComponent.getCustomLayoutManager().getLayoutCallbackWrapper().addLayoutCallback(editTileAdapter, true);
        OplusQSTilesComponent$tileAdapter$2$1$1 oplusQSTilesComponent$tileAdapter$2$1$1 = new OplusQSTilesComponent$tileAdapter$2$1$1();
        oplusQSTilesComponent$tileAdapter$2$1$1.this$0 = oplusQSTilesComponent;
        VarHandle.storeStoreFence();
        editTileAdapter.registerAdapterDataObserver(oplusQSTilesComponent$tileAdapter$2$1$1);
        return editTileAdapter;
    }

    public OplusQSTilesComponent(COUIRecyclerView cOUIRecyclerView, OplusQSPageIndicator oplusQSPageIndicator, TileEditViewModel tileEditViewModel, OplusQSRootView oplusQSRootView, QSPanelAnimatorManager qSPanelAnimatorManager, EditModeSwitchAnimationManager editModeSwitchAnimationManager, RecyclerViewDragHelper recyclerViewDragHelper, OplusSeparateQSPlugin.IStub iStub) {
        super(cOUIRecyclerView);
        this.$$delegate_0 = new PullAnimationProxy(cOUIRecyclerView, ContainerType.NO_PERSONAL_PORT_OR_LAND_RIGHT, qSPanelAnimatorManager);
        this.recyclerView = cOUIRecyclerView;
        this.indicatorView = oplusQSPageIndicator;
        this.editViewModel = tileEditViewModel;
        this.qsRootView = oplusQSRootView;
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        this.editModeSwitchAnimationManager = editModeSwitchAnimationManager;
        this.dragHelper = recyclerViewDragHelper;
        this.stub = iStub;
        int i = 0;
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda0 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(i);
        oplusQSTilesComponent$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.emptyPlaceHolderAction$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda0);
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda1 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(1);
        oplusQSTilesComponent$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.sceneSwitcher$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda1);
        OplusQSTilesComponent$removeAnimateEnd$1 oplusQSTilesComponent$removeAnimateEnd$1 = new OplusQSTilesComponent$removeAnimateEnd$1(i);
        oplusQSTilesComponent$removeAnimateEnd$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.removeAnimateEnd = oplusQSTilesComponent$removeAnimateEnd$1;
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda2 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(2);
        oplusQSTilesComponent$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.cellSizeChangeProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda2);
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda3 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(3);
        oplusQSTilesComponent$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusItemShowHideAnimator$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda3);
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda4 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(4);
        oplusQSTilesComponent$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.customLayoutManager$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda4);
        OplusQSTilesComponent$dragCallbackProxy$2$1 oplusQSTilesComponent$dragCallbackProxy$2$1 = new OplusQSTilesComponent$dragCallbackProxy$2$1();
        oplusQSTilesComponent$dragCallbackProxy$2$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.abilityForViewHolder = oplusQSTilesComponent$dragCallbackProxy$2$1;
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda5 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(5);
        oplusQSTilesComponent$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.tileAdapter$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda5);
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda6 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(6);
        oplusQSTilesComponent$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragCallbackProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda6);
        OplusQSTilesComponent$animationListener$1 oplusQSTilesComponent$animationListener$1 = new OplusQSTilesComponent$animationListener$1();
        oplusQSTilesComponent$animationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationListener = oplusQSTilesComponent$animationListener$1;
        OplusQSTilesComponent$$ExternalSyntheticLambda0 oplusQSTilesComponent$$ExternalSyntheticLambda7 = new OplusQSTilesComponent$$ExternalSyntheticLambda0(7);
        oplusQSTilesComponent$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.itemSpringAnimator$delegate = LazyKt__LazyJVMKt.lazy(oplusQSTilesComponent$$ExternalSyntheticLambda7);
        this.addingArea = IntentTarget.NON;
        this.pageIndexBeforeEnterAdding = -1;
        this.otherAddedTiles = new ArrayList();
        this.oplusGcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);
    }

    public static final boolean access$isEditMode(OplusQSTilesComponent oplusQSTilesComponent) {
        return oplusQSTilesComponent.getTileAdapter().isEditMode;
    }

    public static final void access$scrollToTarget(OplusQSTilesComponent oplusQSTilesComponent, long j, boolean z) {
        Iterator<Object> it = oplusQSTilesComponent.getTileAdapter().getDataList().iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (((GridData) it.next()).getUniqueId() == j) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            if (z) {
                oplusQSTilesComponent.getTileAdapter().notifyItemChanged(i, oplusQSTilesComponent);
            }
            Integer itemPageIndex = oplusQSTilesComponent.getCustomLayoutManager().getItemPageIndex(i);
            if (itemPageIndex != null) {
                oplusQSTilesComponent.getCustomLayoutManager().smoothScrollToPage(itemPageIndex.intValue());
            }
            oplusQSTilesComponent.pageIndexBeforeEnterAdding = -1;
            return;
        }
        COUIRecyclerView cOUIRecyclerView = oplusQSTilesComponent.recyclerView;
        scrollToTarget.2 r1 = new scrollToTarget.2();
        r1.this$0 = oplusQSTilesComponent;
        r1.$targetUniqueId = j;
        r1.$highlight = z;
        VarHandle.storeStoreFence();
        cOUIRecyclerView.postDelayed(r1, 50L);
    }

    private final CellSizeChangeProxy getCellSizeChangeProxy() {
        return (CellSizeChangeProxy) this.cellSizeChangeProxy$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StaggeredPagerLayoutManager getCustomLayoutManager() {
        return (StaggeredPagerLayoutManager) this.customLayoutManager$delegate.getValue();
    }

    private final DragCallbackProxy getDragCallbackProxy() {
        return (DragCallbackProxy) this.dragCallbackProxy$delegate.getValue();
    }

    private final EmptyPlaceHolderTilesAction getEmptyPlaceHolderAction() {
        return (EmptyPlaceHolderTilesAction) this.emptyPlaceHolderAction$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusItemSpringAnimator getItemSpringAnimator() {
        return (OplusItemSpringAnimator) this.itemSpringAnimator$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecyclerViewHeightChangeAnimation getOplusItemShowHideAnimator() {
        return (RecyclerViewHeightChangeAnimation) this.oplusItemShowHideAnimator$delegate.getValue();
    }

    private final EditModeSwitchAnimationProxyForPager getSceneSwitcher() {
        return (EditModeSwitchAnimationProxyForPager) this.sceneSwitcher$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EditTileAdapter getTileAdapter() {
        return (EditTileAdapter) this.tileAdapter$delegate.getValue();
    }

    public static /* synthetic */ void refreshEmptyCell$default(OplusQSTilesComponent oplusQSTilesComponent) {
        oplusQSTilesComponent.refreshEmptyCell(null, oplusQSTilesComponent.getCustomLayoutManager().getLayoutConfig().getSpecifyLoc());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNeedItemAnimator(boolean z) {
        if (this.isNeedItemAnimator != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isNeedItemAnimator : ", z), null, 8);
            this.isNeedItemAnimator = z;
            this.recyclerView.setItemAnimator(z ? getItemSpringAnimator() : null);
        }
    }

    public static /* synthetic */ void updateRecyclerViewVisible$default(OplusQSTilesComponent oplusQSTilesComponent) {
        oplusQSTilesComponent.updateRecyclerViewVisible$1(oplusQSTilesComponent.getCustomLayoutManager().getLayoutConfig().getRowCount());
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0047  */
    public final void bindData(List list) {
        int colSpan;
        int i = 0;
        if (((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) {
            List<SpanSize> gridInfo = CellCalculatorManager.Companion.getInstance(getContext()).getGridInfo(true, false);
            if (gridInfo.get(0).getRowSpan() > 0) {
                colSpan = gridInfo.get(0).getColSpan() * gridInfo.get(0).getRowSpan();
                int size = list.size();
                if (colSpan > size) {
                    colSpan = size;
                }
            } else {
                colSpan = 0;
            }
        } else {
            colSpan = 0;
        }
        List list2 = CollectionsKt___CollectionsKt.toList(getCustomLayoutManager().getLayoutConfig().getSpecifyLoc());
        getCustomLayoutManager().autoGenLoc(list.size() - colSpan);
        List list3 = CollectionsKt___CollectionsKt.toList(getCustomLayoutManager().getLayoutConfig().getSpecifyLoc());
        getTileAdapter().submitList(list.subList(colSpan, list.size()), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList());
        refreshEmptyCell(list2, list3);
        updateRecyclerViewVisible$default(this);
        if (getTileAdapter().isEditMode) {
            return;
        }
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        OplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1 oplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1 = new OplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1(i);
        oplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUIRecyclerView.addOnLayoutChangeListener(oplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        getSceneSwitcher().bindTargetProperties(set);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return getTileAdapter().checkEditModeAbnormal();
    }

    public final void clickTile(ComponentName componentName) {
        this.editViewModel.sendIntent(new TileEditIntent.CustomTileClick(componentName), IntentTarget.PERSONAL_LIST);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if ("parent_translation_transition".equals(str)) {
            list.add(new Pair<>(ViewWithSizeChange.Companion.injectOnSizeChange(this.recyclerView, null), SizeChangeBoundsTransition.class));
            return list;
        }
        if (Intrinsics.areEqual(str, "adding_transition_position")) {
            list.add(new Pair<>(ViewWithSizeChange.Companion.injectOnSizeChange(this.recyclerView, null), SizeChangeBoundsTransition.class));
            return list;
        }
        if (Intrinsics.areEqual(str, "adding_transition_tile_alpha")) {
            list.add(new Pair<>(this.recyclerView, FadeTransition.class));
            return list;
        }
        if (Intrinsics.areEqual(str, "adding_transition") && OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            list.add(new Pair<>(this.recyclerView, AlphaTransition.class));
        }
        return list;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return getSceneSwitcher().createSpringTransition(str);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void destroyComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusQSTilesComponent:start-----------------------------------------------");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("pageIndicator dotsCount: ", this.indicatorView.getDotsCount(), printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("page size: ", getCustomLayoutManager().getPageCount(), printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "shouldInterceptScroll: ", this.shouldInterceptScroll);
        getCustomLayoutManager().dump(printWriter, strArr);
        getTileAdapter().dump(printWriter, strArr);
        printWriter.println("OplusQSTilesComponent:end-----------------------------------------------");
    }

    public final void enableRecyclerViewHeightAnimation() {
        getOplusItemShowHideAnimator().enable(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return this.$$delegate_0.getAnimatorView(i);
    }

    public final int getCurrentPage() {
        return getCustomLayoutManager().getCurrentPageIndex();
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0007  */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b1, code lost:
    
        if ((r5.getColCount() * r5.getRowCount()) == r1) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int getNewPageCount(java.util.List r6) {
        /*
            r5 = this;
            boolean r0 = r6.isEmpty()
            r1 = 0
            if (r0 == 0) goto L9
        L7:
            r0 = r1
            goto L37
        L9:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto Lbb
            java.lang.Object r0 = r6.next()
            com.oplusos.systemui.common.model.Loc r0 = (com.oplusos.systemui.common.model.Loc) r0
            int r0 = r0.getPageIndex()
        L1f:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L33
            java.lang.Object r2 = r6.next()
            com.oplusos.systemui.common.model.Loc r2 = (com.oplusos.systemui.common.model.Loc) r2
            int r2 = r2.getPageIndex()
            if (r0 >= r2) goto L1f
            r0 = r2
            goto L1f
        L33:
            if (r0 < 0) goto L7
            int r0 = r0 + 1
        L37:
            com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r6 = r5.addingArea
            com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r2 = com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget.OTHER_LIST
            if (r6 != r2) goto Lba
            com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager r5 = r5.getCustomLayoutManager()
            com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager$LayoutConfig r5 = r5.getLayoutConfig()
            java.util.List r6 = r5.getSpecifyLoc()
            boolean r2 = r6.isEmpty()
            if (r2 == 0) goto L50
            goto Lb3
        L50:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r2 = r6.iterator()
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lb6
            java.lang.Object r3 = r2.next()
            com.oplusos.systemui.common.model.Loc r3 = (com.oplusos.systemui.common.model.Loc) r3
            int r3 = r3.getPageIndex()
        L66:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L7a
            java.lang.Object r4 = r2.next()
            com.oplusos.systemui.common.model.Loc r4 = (com.oplusos.systemui.common.model.Loc) r4
            int r4 = r4.getPageIndex()
            if (r3 >= r4) goto L66
            r3 = r4
            goto L66
        L7a:
            boolean r2 = r6 instanceof java.util.Collection
            if (r2 == 0) goto L88
            r2 = r6
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L88
            goto La8
        L88:
            java.util.Iterator r6 = r6.iterator()
        L8c:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto La8
            java.lang.Object r2 = r6.next()
            com.oplusos.systemui.common.model.Loc r2 = (com.oplusos.systemui.common.model.Loc) r2
            int r2 = r2.getPageIndex()
            if (r2 != r3) goto L8c
            int r1 = r1 + 1
            if (r1 < 0) goto La3
            goto L8c
        La3:
            kotlin.collections.CollectionsKt__CollectionsKt.throwCountOverflow()
            r5 = 0
            throw r5
        La8:
            int r6 = r5.getRowCount()
            int r5 = r5.getColCount()
            int r5 = r5 * r6
            if (r5 != r1) goto Lba
        Lb3:
            int r0 = r0 + 1
            return r0
        Lb6:
            kotlin.text.Regex$.ExternalSyntheticBUOutline0.m()
            return r1
        Lba:
            return r0
        Lbb:
            kotlin.text.Regex$.ExternalSyntheticBUOutline0.m()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent.getNewPageCount(java.util.List):int");
    }

    public final boolean getShouldInterceptScroll() {
        return this.shouldInterceptScroll;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return this.$$delegate_0.getSubSize();
    }

    public final void initExitAddingMode(int i, boolean z) {
        Iterator<Loc> it = getCustomLayoutManager().getLayoutConfig().getSpecifyLoc().iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (it.next().getPageIndex() == i) {
                break;
            } else {
                i2++;
            }
        }
        if (!getTileAdapter().isEditMode || this.isCollapseQSPanelStart) {
            this.recyclerView.scrollToPosition(i2);
        } else {
            this.recyclerView.smoothScrollToPosition(i2);
        }
        this.pageIndexBeforeEnterAdding = -1;
        refreshEmptyCell$default(this);
        getEmptyPlaceHolderAction().onExitAddingMode(z);
        this.isCollapseQSPanelStart = false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        this.hostAbility = iComponentHostAbility;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        this.$$delegate_0.getClass();
        return true;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback
    public void onCellInfoChanged(CellInfo cellInfo) {
        super.onCellInfoChanged(cellInfo);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback
    public final void onCellNumChanged(int i, int i2) {
        IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener;
        if (getTileAdapter().isEditMode || (qSNodeChangeListener = this.nodeChangedListener) == null) {
            return;
        }
        ((QSPanelAnimatorManager.AnonymousClass1) qSNodeChangeListener).onNodeNumChanged();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onCollapseQSPanelStart] ", "OplusQSTilesComponent-->", "SystemUi--QuickSettings", z);
        }
        this.recyclerView.removeCallbacks(this.onAddingTransitionEnd);
        getOplusItemShowHideAnimator().enable(false);
        Runnable runnable = this.onAddingTransitionEnd;
        if (runnable != null) {
            this.isCollapseQSPanelStart = true;
            runnable.run();
        }
        this.onAddingTransitionEnd = null;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onEnterAddingMode] isPersonalArea=", "OplusQSTilesComponent-->", "SystemUi--QuickSettings", z);
        }
        int i = 1;
        getTileAdapter().setImmersiveOperation(true);
        this.addingArea = z ? IntentTarget.PERSONAL_LIST : IntentTarget.OTHER_LIST;
        updateRecyclerViewVisible$default(this);
        setShouldInterceptScroll(true);
        GlobalUtils.sendCancelMotionEvent(this.recyclerView, "onEnterAddingMode");
        IntentTarget intentTarget = this.addingArea;
        IntentTarget intentTarget2 = IntentTarget.OTHER_LIST;
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        Runnable runnable = this.onAddingTransitionEnd;
        if (intentTarget == intentTarget2) {
            cOUIRecyclerView.removeCallbacks(runnable);
            this.onAddingTransitionEnd = null;
            this.pageIndexBeforeEnterAdding = -1;
            getCustomLayoutManager().refreshAllEmptyCell();
            getEmptyPlaceHolderAction().onEnterAddingMode(z2);
            boolean z3 = getNewPageCount(getCustomLayoutManager().getLayoutConfig().getSpecifyLoc()) - 1 == getCurrentPage();
            if (z3 || OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
                if (!z3) {
                    this.pageIndexBeforeEnterAdding = getCurrentPage();
                }
                refreshEmptyCell$default(this);
                this.recyclerView.smoothScrollToPosition(getCustomLayoutManager().getLayoutConfig().getSpecifyLoc().size());
            } else {
                OplusQSTilesComponent$removeAnimateEnd$1 oplusQSTilesComponent$removeAnimateEnd$1 = new OplusQSTilesComponent$removeAnimateEnd$1(i);
                oplusQSTilesComponent$removeAnimateEnd$1.this$0 = this;
                VarHandle.storeStoreFence();
                this.onAddingTransitionEnd = oplusQSTilesComponent$removeAnimateEnd$1;
                this.recyclerView.postDelayed(oplusQSTilesComponent$removeAnimateEnd$1, Companion.getAdding_mode_transition_duration());
            }
        } else {
            cOUIRecyclerView.removeCallbacks(runnable);
            Runnable runnable2 = this.onAddingTransitionEnd;
            if (runnable2 != null) {
                runnable2.run();
            }
            this.onAddingTransitionEnd = null;
        }
        getOplusItemShowHideAnimator().enable(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        getTileAdapter().setImmersiveOperation(true);
        getTileAdapter().setDragMode(true);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->[onEnterEditMode]");
        }
        Context context = this.recyclerView.getContext();
        context.getClass();
        if (QsColorUtil.isLowGaussianLevelColor(context, false) || QsColorUtil.isLowGaussianLevelColorWhenDarkMode(context, false)) {
            this.indicatorView.setPageIndicatorDotsColor(context.getColor(2131103085));
            this.indicatorView.setTraceDotColor(context.getColor(2131103084));
        }
        getEmptyPlaceHolderAction().onEnterEditMode(z);
        getOplusItemShowHideAnimator().enable(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
        getTileAdapter().setImmersiveOperation(false);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->".concat("[onExitAddingMode] " + this.addingArea));
        }
        boolean z2 = this.addingArea == IntentTarget.OTHER_LIST;
        this.addingArea = IntentTarget.NON;
        updateRecyclerViewVisible$default(this);
        setShouldInterceptScroll(false);
        this.recyclerView.removeCallbacks(this.onAddingTransitionEnd);
        this.onAddingTransitionEnd = null;
        if (z2) {
            int i = this.pageIndexBeforeEnterAdding;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(getCustomLayoutManager().getPageCount(), "OplusQSTilesComponent-->", "SystemUi--QuickSettings", FloatList$.ExternalSyntheticOutline0.m("[onExitAddingMode] pageIndex ", getCurrentPage(), i, " -> ", ", "));
            }
            if (this.pageIndexBeforeEnterAdding == -1 || OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
                initExitAddingMode(i, z);
                return;
            }
            Runnable runnable = new onExitAddingMode.3();
            ((onExitAddingMode.3) runnable).this$0 = this;
            ((onExitAddingMode.3) runnable).$withAnimation = z;
            ((onExitAddingMode.3) runnable).$originalPageIndex = i;
            VarHandle.storeStoreFence();
            this.onAddingTransitionEnd = runnable;
            this.recyclerView.postDelayed(runnable, Companion.getAdding_mode_transition_duration());
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
        getTileAdapter().setImmersiveOperation(false);
        getTileAdapter().setDragMode(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->[onExitEditMode]");
        }
        getEmptyPlaceHolderAction().onExitEditMode(z);
        getOplusItemShowHideAnimator().enable(z);
        setShouldInterceptScroll(false);
        this.recyclerView.removeCallbacks(this.onAddingTransitionEnd);
        Runnable runnable = this.onAddingTransitionEnd;
        if (runnable != null) {
            runnable.run();
        }
        this.onAddingTransitionEnd = null;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
        getTileAdapter().onThemeApplied(z);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        COUIRecyclerView cOUIRecyclerView = this.mView;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUIRecyclerView.setOnScrollChangeListener(anonymousClass1);
        View view = this.mView;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        ViewExtKt.addOnTouchListener(view, anonymousClass2, false);
        getCellSizeChangeProxy().onViewAttached();
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        getCellSizeChangeProxy().onViewDetached();
        ViewWithSizeChange.Companion.removeOnSizeChange(this.recyclerView, null);
    }

    public final void refreshEmptyCell(List list, List list2) {
        this.recyclerView.removeCallbacks(this.removeAnimateEnd);
        boolean z = list == null || list.size() != list2.size();
        int newPageCount = getNewPageCount(list2);
        int currentPage = getCurrentPage();
        int i = newPageCount - 1;
        if (i < 0) {
            i = 0;
        }
        if (currentPage > i && list != null) {
            List list3 = list;
            if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    if (((Loc) it.next()).getPageIndex() == getCurrentPage()) {
                        if (!this.isNeedItemAnimator || !getTileAdapter().isEditMode) {
                            break;
                            break;
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->needWaitRemoveEnd ");
                        }
                        this.recyclerView.postDelayed(this.removeAnimateEnd, Companion.getRemove_animate_duration());
                        return;
                    }
                }
            }
        }
        if (this.addingArea == IntentTarget.OTHER_LIST) {
            getCustomLayoutManager().refreshEmptyCell(newPageCount, z);
        } else {
            StaggeredPagerLayoutManager.refreshPageCount$default(getCustomLayoutManager(), getTileAdapter().isEditMode && z, 2);
        }
    }

    public final void reset() {
        getCustomLayoutManager().scrollToPage(0);
    }

    public final void resetCurrentPage() {
        getCustomLayoutManager().scrollToPage(getCurrentPage());
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
        OplusQSTilesComponent$$ExternalSyntheticLambda8 oplusQSTilesComponent$$ExternalSyntheticLambda8 = new OplusQSTilesComponent$$ExternalSyntheticLambda8();
        oplusQSTilesComponent$$ExternalSyntheticLambda8.f$0 = function1;
        VarHandle.storeStoreFence();
        ViewExtKt.addOnTouchListener(this.recyclerView, new EmptyAreaGestureProxy(cOUIRecyclerView, oplusQSTilesComponent$$ExternalSyntheticLambda8), false);
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
        getOplusItemShowHideAnimator().enable(false);
        getTileAdapter().setListening(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        this.nodeChangedListener = qSNodeChangeListener;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        this.nodePageSelectedListener = qSNodePageSelectedListener;
    }

    public final void setShouldInterceptScroll(boolean z) {
        if (this.shouldInterceptScroll != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("shouldInterceptScroll : ", z), null, 8);
            this.shouldInterceptScroll = z;
        }
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController, com.oplus.systemui.plugins.qs.IQsComponent
    public final void startComponent() {
        init();
        COUIThemeOverlay.getInstance().applyThemeOverlays(this.recyclerView.getContext());
        new OplusPagerStaggerSnapHelper().attachToRecyclerView(this.recyclerView);
        RecyclerView recyclerView = this.recyclerView;
        updateRecyclerViewVisible$default(this);
        recyclerView.setItemViewCacheSize(50);
        recyclerView.setAdapter(getTileAdapter());
        recyclerView.setLayoutManager(getCustomLayoutManager());
        getCustomLayoutManager().setRecyclerView(recyclerView);
        getTileAdapter().setPressFeedBack(this.dragHelper);
        getTileAdapter().setHostContainerAbility(this.abilityForViewHolder);
        recyclerView.setFlingRatio(0.1f);
        OplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1 oplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1 = new OplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1(1);
        oplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1.this$0 = this;
        VarHandle.storeStoreFence();
        recyclerView.addOnLayoutChangeListener(oplusQSTilesComponent$bindData$$inlined$doOnNextLayout$1);
        this.recyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.indicatorView.setUseOriginalRTL(true);
        PagerCallbackWrapper pagerCallbackWrapper = getCustomLayoutManager().getPagerCallbackWrapper();
        OplusQSTilesComponent$addPagerCallback$1 oplusQSTilesComponent$addPagerCallback$1 = new OplusQSTilesComponent$addPagerCallback$1();
        oplusQSTilesComponent$addPagerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        pagerCallbackWrapper.addPagerCallback(oplusQSTilesComponent$addPagerCallback$1);
        this.dragHelper.registerRecyclerView(this.recyclerView, getDragCallbackProxy());
        getCustomLayoutManager().getLayoutCallbackWrapper().addLayoutCallback(this, false);
        getEmptyPlaceHolderAction().init();
        RepeatWhenAttachedKt.repeatWhenAttached(this.recyclerView, Dispatchers.getMain().getImmediate(), new OplusQSTilesComponent$collectFlow$1(this, null));
    }

    public final void stopDrag() {
        this.dragHelper.stopDrag();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        getTileAdapter().updateColor(colorType);
        getEmptyPlaceHolderAction().updatePlaceHolder$1();
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

    public final void updateRecyclerViewVisible$1(int i) {
        boolean zIsSplitAdd = OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd();
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        IntentTarget intentTarget = this.addingArea;
        boolean z = true;
        if (intentTarget == IntentTarget.NON || zIsSplitAdd ? i <= 0 || (!getTileAdapter().isEditMode && getTileAdapter().getDataList().isEmpty()) : intentTarget != IntentTarget.OTHER_LIST) {
            z = false;
        }
        cOUIRecyclerView.setVisibility(z ? 0 : 4);
        if (zIsSplitAdd) {
            this.recyclerView.setAlpha(this.addingArea == IntentTarget.PERSONAL_LIST ? 0.3f : 1.0f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        if (z) {
            setNeedItemAnimator(false);
            getCellSizeChangeProxy().refreshGridNum();
            bindData(this.otherAddedTiles);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateResource", null, 8);
        getEmptyPlaceHolderAction().updatePlaceHolder$1();
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.$$delegate_0.updateState(i, i2, f, f2, f3);
    }
}
