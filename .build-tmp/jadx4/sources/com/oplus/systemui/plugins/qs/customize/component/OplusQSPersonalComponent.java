package com.oplus.systemui.plugins.qs.customize.component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.ViewKt$;
import androidx.lifecycle.ViewModelKt;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.plugins.qs.CellAreaConfig;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.component.proxy.CellSizeChangeProxy;
import com.oplus.systemui.plugins.qs.customize.component.proxy.DragCallbackProxy;
import com.oplus.systemui.plugins.qs.customize.component.proxy.EmptyPlaceHolderAction;
import com.oplus.systemui.plugins.qs.customize.component.proxy.TileOperationHintAction;
import com.oplus.systemui.plugins.qs.customize.view.EditTileAdapter;
import com.oplus.systemui.plugins.qs.customize.view.IHostContainerAbility;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.editmodeswitch.EditModeSwitchAnimationProxyForPager;
import com.oplus.systemui.plugins.qs.customize.view.animation.editmodeswitch.EditModeSwitchAnimationProxyForPager$$ExternalSyntheticLambda0;
import com.oplus.systemui.plugins.qs.customize.view.animation.pull.ContainerType;
import com.oplus.systemui.plugins.qs.customize.view.animation.pull.PullAnimationProxy;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.GridDataCache;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.plugins.qs.edittop.EditTopComponent;
import com.oplus.systemui.plugins.qs.proxy.EmptyAreaGestureProxy;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.springtransition.AlphaTransition;
import com.oplus.systemui.qs.base.springtransition.BoundsTransition;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaProperty;
import com.oplus.systemui.qs.base.springtransition.DrawableBoundsTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusEditItemAnimator;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusPagerStaggerSnapHelper;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.util.OplusAccessibilityUtil;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
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
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPersonalComponent extends ViewController implements IQsComponent, IQSExpandOrCollapseAnimation, IEditModeSwitcher, ITransitionDesc {
    private static final String ACTION_TALKBACK_SWITCH_CHAGNED = "app.intent.action.TALKBACK_SWITCH_CHAGNED";
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSPersonalComponent";
    public final /* synthetic */ PullAnimationProxy $$delegate_0;
    private final IHostContainerAbility abilityForViewHolder;
    private IntentTarget addingArea;
    private final OplusQSPersonalComponent$animationListener$1 animationListener;
    private final CellAreaProvider cellAreaProvider;
    private final Lazy cellSizeChangeProxy$delegate;
    private final Lazy customLayoutManager$delegate;
    private final Lazy dragCallbackProxy$delegate;
    private final RecyclerViewDragHelper dragHelper;
    private final EditModeSwitchAnimationManager editModeSwitchAnimationManager;
    private final TileEditViewModel editViewModel;
    private final Lazy emptyPlaceHolderAction$delegate;
    private IComponentHostAbility hostAbility;
    public boolean isNeedItemAnimator;
    private final Lazy itemSpringAnimator$delegate;
    private IQSExpandOrCollapseAnimation.QSNodeChangeListener nodeChangedListener;
    private Function2 onAdd;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final Lazy qsPersonalBorderDrawable$delegate;
    private final COUIRecyclerView recyclerView;
    private final Lazy resizeCallBack$delegate;
    private final Lazy sceneSwitcher$delegate;
    private final OplusQSPersonalComponent$talkbackStateObserver$1 talkbackStateObserver;
    private final Lazy tileAdapter$delegate;
    private final TileOperationHintAction tileOperationHintAction;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static CellSizeChangeProxy $r8$lambda$GR8D_DMEyiR0ZJP1jfym0G_IPWg(OplusQSPersonalComponent oplusQSPersonalComponent) {
        return new CellSizeChangeProxy(oplusQSPersonalComponent.getContext(), (RecyclerView) oplusQSPersonalComponent.recyclerView, true, (Function1) new OplusQSPersonalComponent$cellSizeChangeProxy$2$1(1, oplusQSPersonalComponent, OplusQSPersonalComponent.class, "updateRecyclerViewVisible", "updateRecyclerViewVisible(I)V", 0), oplusQSPersonalComponent.cellAreaProvider, 16);
    }

    /* JADX INFO: renamed from: $r8$lambda$GSky4fhWx-TK8zbZWwuYXMl7WdU, reason: not valid java name */
    public static Unit m130$r8$lambda$GSky4fhWxTK8zbZWwuYXMl7WdU(OplusQSPersonalComponent oplusQSPersonalComponent, Loc loc) {
        IComponentHostAbility iComponentHostAbility = oplusQSPersonalComponent.hostAbility;
        if (iComponentHostAbility != null) {
            Bundle bundle = new Bundle();
            EditTopComponent.Companion.addEnterLoc(bundle, loc);
            iComponentHostAbility.enterAddingMode(true, true, bundle);
        }
        return Unit.INSTANCE;
    }

    public static DragCallbackProxy $r8$lambda$Q_i7CPCITN1CEfflkTNOklM3n0Q(OplusQSPersonalComponent oplusQSPersonalComponent) {
        COUIRecyclerView cOUIRecyclerView = oplusQSPersonalComponent.recyclerView;
        IntentTarget intentTarget = IntentTarget.PERSONAL_LIST;
        EditModeSwitchAnimationManager editModeSwitchAnimationManager = oplusQSPersonalComponent.editModeSwitchAnimationManager;
        IComponentHostAbility iComponentHostAbility = oplusQSPersonalComponent.hostAbility;
        OplusQSPersonalComponent$dragCallbackProxy$2$1 oplusQSPersonalComponent$dragCallbackProxy$2$1 = new OplusQSPersonalComponent$dragCallbackProxy$2$1();
        oplusQSPersonalComponent$dragCallbackProxy$2$1.this$0 = oplusQSPersonalComponent;
        VarHandle.storeStoreFence();
        return new DragCallbackProxy(cOUIRecyclerView, intentTarget, editModeSwitchAnimationManager, iComponentHostAbility, oplusQSPersonalComponent$dragCallbackProxy$2$1, "A");
    }

    /* JADX INFO: renamed from: $r8$lambda$Vh_tTdcAR9E9ID-ba24WilogCo4, reason: not valid java name */
    public static EmptyPlaceHolderAction m131$r8$lambda$Vh_tTdcAR9E9IDba24WilogCo4(OplusQSPersonalComponent oplusQSPersonalComponent) {
        return new EmptyPlaceHolderAction(oplusQSPersonalComponent.recyclerView);
    }

    /* JADX INFO: renamed from: $r8$lambda$W6QGl1-RxfCi2FME2j7mCT8LsIQ, reason: not valid java name */
    public static EditModeSwitchAnimationProxyForPager m132$r8$lambda$W6QGl1RxfCi2FME2j7mCT8LsIQ(OplusQSPersonalComponent oplusQSPersonalComponent) {
        return new EditModeSwitchAnimationProxyForPager(oplusQSPersonalComponent.recyclerView, CollectionsKt__CollectionsKt.listOf(new Object[]{oplusQSPersonalComponent.getEmptyPlaceHolderAction(), oplusQSPersonalComponent}), new EditModeSwitchAnimationProxyForPager$$ExternalSyntheticLambda0(1));
    }

    /* JADX INFO: renamed from: $r8$lambda$_xgnxFmNYv7K2Rd-M-kageftqho, reason: not valid java name */
    public static EditTileAdapter m133$r8$lambda$_xgnxFmNYv7K2RdMkageftqho(OplusQSPersonalComponent oplusQSPersonalComponent) {
        EditTileAdapter editTileAdapter = new EditTileAdapter(oplusQSPersonalComponent.recyclerView.getContext(), oplusQSPersonalComponent.editViewModel, true);
        oplusQSPersonalComponent.getCustomLayoutManager().getLayoutCallbackWrapper().addLayoutCallback(editTileAdapter, true);
        return editTileAdapter;
    }

    /* JADX INFO: renamed from: $r8$lambda$f0CyF-S3CdYqjTSvpKCxZ-rez0M, reason: not valid java name */
    public static boolean m134$r8$lambda$f0CyFS3CdYqjTSvpKCxZrez0M(Function1 function1, OplusQSPersonalComponent oplusQSPersonalComponent, boolean z, MotionEvent motionEvent) {
        Loc locFindUnderCapsule;
        if (!((Boolean) function1.invoke(Boolean.valueOf(z))).booleanValue()) {
            if (!oplusQSPersonalComponent.getTileAdapter().isEditMode || oplusQSPersonalComponent.getTileAdapter().isImmersiveOperation || (locFindUnderCapsule = oplusQSPersonalComponent.getCustomLayoutManager().findUnderCapsule((int) motionEvent.getX(), (int) motionEvent.getY())) == null) {
                return false;
            }
            IComponentHostAbility iComponentHostAbility = oplusQSPersonalComponent.hostAbility;
            if (iComponentHostAbility != null) {
                Bundle bundle = new Bundle();
                EditTopComponent.Companion.addEnterLoc(bundle, locFindUnderCapsule);
                iComponentHostAbility.enterAddingMode(true, true, bundle);
            }
        }
        return true;
    }

    public static OplusItemSpringAnimator $r8$lambda$glWe3Pm2IX7q8se92DJVZkXmQhU(OplusQSPersonalComponent oplusQSPersonalComponent) {
        OplusItemSpringAnimator oplusItemSpringAnimator = new OplusItemSpringAnimator(new OplusEditItemAnimator(), oplusQSPersonalComponent.animationListener);
        oplusItemSpringAnimator.setMoveDuration(150L);
        oplusItemSpringAnimator.setChangeDuration(300L);
        oplusItemSpringAnimator.setAddAnimatorDelay(0L);
        oplusItemSpringAnimator.setChangeAnimatorDelay(0L);
        return oplusItemSpringAnimator;
    }

    /* JADX WARN: Type inference failed for: r3v22, types: [com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$talkbackStateObserver$1] */
    public OplusQSPersonalComponent(COUIRecyclerView cOUIRecyclerView, TileEditViewModel tileEditViewModel, TileOperationHintAction tileOperationHintAction, EditModeSwitchAnimationManager editModeSwitchAnimationManager, QSPanelAnimatorManager qSPanelAnimatorManager, RecyclerViewDragHelper recyclerViewDragHelper, CellAreaProvider cellAreaProvider) {
        super(cOUIRecyclerView);
        this.$$delegate_0 = new PullAnimationProxy(cOUIRecyclerView, ContainerType.PERSONAL, qSPanelAnimatorManager);
        this.recyclerView = cOUIRecyclerView;
        this.editViewModel = tileEditViewModel;
        this.tileOperationHintAction = tileOperationHintAction;
        this.editModeSwitchAnimationManager = editModeSwitchAnimationManager;
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        this.dragHelper = recyclerViewDragHelper;
        this.cellAreaProvider = cellAreaProvider;
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda0 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(0);
        oplusQSPersonalComponent$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.emptyPlaceHolderAction$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda0);
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda1 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(1);
        oplusQSPersonalComponent$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.sceneSwitcher$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda1);
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda2 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(2);
        oplusQSPersonalComponent$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.cellSizeChangeProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda2);
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda3 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(3);
        oplusQSPersonalComponent$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.customLayoutManager$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda3);
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda4 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(4);
        oplusQSPersonalComponent$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.tileAdapter$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda4);
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda5 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(5);
        oplusQSPersonalComponent$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragCallbackProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda5);
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda6 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(6);
        oplusQSPersonalComponent$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.resizeCallBack$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda6);
        OplusQSPersonalComponent$dragCallbackProxy$2$1 oplusQSPersonalComponent$dragCallbackProxy$2$1 = new OplusQSPersonalComponent$dragCallbackProxy$2$1();
        oplusQSPersonalComponent$dragCallbackProxy$2$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.abilityForViewHolder = oplusQSPersonalComponent$dragCallbackProxy$2$1;
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda7 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(7);
        oplusQSPersonalComponent$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.itemSpringAnimator$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda7);
        OplusQSPersonalComponent$animationListener$1 oplusQSPersonalComponent$animationListener$1 = new OplusQSPersonalComponent$animationListener$1();
        oplusQSPersonalComponent$animationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationListener = oplusQSPersonalComponent$animationListener$1;
        this.addingArea = IntentTarget.NON;
        OplusQSPersonalComponent$$ExternalSyntheticLambda0 oplusQSPersonalComponent$$ExternalSyntheticLambda8 = new OplusQSPersonalComponent$$ExternalSyntheticLambda0(8);
        oplusQSPersonalComponent$$ExternalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        this.qsPersonalBorderDrawable$delegate = LazyKt__LazyJVMKt.lazy(oplusQSPersonalComponent$$ExternalSyntheticLambda8);
        this.talkbackStateObserver = new BroadcastReceiver() { // from class: com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$talkbackStateObserver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                OplusQSPersonalComponent oplusQSPersonalComponent = this.this$0;
                OplusQSPersonalComponent.Companion companion = OplusQSPersonalComponent.Companion;
                oplusQSPersonalComponent.refreshEmptyViewHolder();
            }
        };
    }

    public static final void access$refreshEmptyCell(OplusQSPersonalComponent oplusQSPersonalComponent, List list, List list2, List list3, List list4) {
        boolean z;
        oplusQSPersonalComponent.getClass();
        boolean zAreEqual = Intrinsics.areEqual(list, list2);
        if (zAreEqual) {
            z = list3.size() != list4.size();
            if (!z) {
                int i = 0;
                for (Object obj : list3) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                        throw null;
                    }
                    if (!Intrinsics.areEqual(((GridData) obj).getSize(), ((GridData) list4.get(i)).getSize())) {
                        z = true;
                    }
                    i = i2;
                }
            }
        } else {
            z = false;
        }
        oplusQSPersonalComponent.getCustomLayoutManager().refreshEmptyCell(1, !zAreEqual || z);
    }

    public static final void access$scrollToTarget(OplusQSPersonalComponent oplusQSPersonalComponent, long j, boolean z) {
        Iterator<Object> it = oplusQSPersonalComponent.getTileAdapter().getDataList().iterator();
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
                oplusQSPersonalComponent.getTileAdapter().notifyItemChanged(i, oplusQSPersonalComponent);
                return;
            }
            return;
        }
        COUIRecyclerView cOUIRecyclerView = oplusQSPersonalComponent.recyclerView;
        scrollToTarget.1 r1 = new scrollToTarget.1();
        r1.this$0 = oplusQSPersonalComponent;
        r1.$targetUniqueId = j;
        r1.$highlight = z;
        VarHandle.storeStoreFence();
        cOUIRecyclerView.postDelayed(r1, 50L);
    }

    public static final void access$updateContainerRowWithReallyUsed(OplusQSPersonalComponent oplusQSPersonalComponent) {
        int rowSpan = 0;
        if (!oplusQSPersonalComponent.getTileAdapter().getDataList().isEmpty()) {
            IntIterator it = RangesKt___RangesKt.until(0, oplusQSPersonalComponent.getTileAdapter().getDataList().size()).iterator();
            if (!it.hasNext()) {
                Regex$.ExternalSyntheticBUOutline0.m();
                return;
            }
            int iNextInt = it.nextInt();
            rowSpan = ((GridData) oplusQSPersonalComponent.getTileAdapter().getDataList().get(iNextInt)).getSize().getRowSpan() + oplusQSPersonalComponent.getCustomLayoutManager().getLayoutConfig().getSpecifyLoc().get(iNextInt).getStartRow();
            while (it.hasNext()) {
                int iNextInt2 = it.nextInt();
                int rowSpan2 = ((GridData) oplusQSPersonalComponent.getTileAdapter().getDataList().get(iNextInt2)).getSize().getRowSpan() + oplusQSPersonalComponent.getCustomLayoutManager().getLayoutConfig().getSpecifyLoc().get(iNextInt2).getStartRow();
                if (rowSpan < rowSpan2) {
                    rowSpan = rowSpan2;
                }
            }
        }
        LogUtil.i$default(OplusFakeViewProvider.QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(rowSpan, oplusQSPersonalComponent.getTileAdapter().getDataList().size(), "updateContainerRowWithReallyUsed rowCount:", ", size:"), null, 8);
        oplusQSPersonalComponent.getCellSizeChangeProxy().updateAreaRow(rowSpan);
    }

    private final CellSizeChangeProxy getCellSizeChangeProxy() {
        return (CellSizeChangeProxy) this.cellSizeChangeProxy$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StaggeredPagerLayoutManager getCustomLayoutManager() {
        return (StaggeredPagerLayoutManager) this.customLayoutManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DragCallbackProxy getDragCallbackProxy() {
        return (DragCallbackProxy) this.dragCallbackProxy$delegate.getValue();
    }

    private final EmptyPlaceHolderAction getEmptyPlaceHolderAction() {
        return (EmptyPlaceHolderAction) this.emptyPlaceHolderAction$delegate.getValue();
    }

    private final OplusItemSpringAnimator getItemSpringAnimator() {
        return (OplusItemSpringAnimator) this.itemSpringAnimator$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QSPersonalBorderDrawable getQsPersonalBorderDrawable() {
        return (QSPersonalBorderDrawable) this.qsPersonalBorderDrawable$delegate.getValue();
    }

    private final OplusQSPersonalComponent$resizeCallBack$2$1 getResizeCallBack() {
        return (OplusQSPersonalComponent$resizeCallBack$2$1) this.resizeCallBack$delegate.getValue();
    }

    private final EditModeSwitchAnimationProxyForPager getSceneSwitcher() {
        return (EditModeSwitchAnimationProxyForPager) this.sceneSwitcher$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EditTileAdapter getTileAdapter() {
        return (EditTileAdapter) this.tileAdapter$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNeedItemAnimator(boolean z) {
        if (this.isNeedItemAnimator != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isNeedItemAnimator : ", z), null, 8);
            this.isNeedItemAnimator = z;
            this.recyclerView.setItemAnimator(z ? getItemSpringAnimator() : null);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        getSceneSwitcher().bindTargetProperties(set);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return getTileAdapter().checkEditModeAbnormal();
    }

    @Override // com.oplus.systemui.qs.base.springtransition.ITransitionDesc
    public List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list) {
        if ("parent_translation_transition".equals(str)) {
            list.add(new Pair<>(this.recyclerView, BoundsTransition.class));
            list.add(new Pair<>(getQsPersonalBorderDrawable(), DrawableAlphaProperty.class));
            list.add(new Pair<>(getQsPersonalBorderDrawable(), DrawableBoundsTransition.class));
            return list;
        }
        if (Intrinsics.areEqual(str, "adding_transition_tile_alpha")) {
            list.add(new Pair<>(this.recyclerView, FadeTransition.class));
            return list;
        }
        if (Intrinsics.areEqual(str, "adding_transition")) {
            if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
                list.add(new Pair<>(this.recyclerView, AlphaTransition.class));
            }
            list.add(new Pair<>(getQsPersonalBorderDrawable(), DrawableAlphaProperty.class));
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
        printWriter.println();
        printWriter.println("OplusQSPersonalComponent:start-----------------------------------------------");
        this.editViewModel.dump(printWriter, strArr);
        getCustomLayoutManager().dump(printWriter, strArr);
        getTileAdapter().dump(printWriter, strArr);
        printWriter.println("OplusQSPersonalComponent:end-----------------------------------------------");
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
        this.hostAbility = iComponentHostAbility;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        this.$$delegate_0.getClass();
        return true;
    }

    public final void onBorderStatusChange(boolean z) {
        getQsPersonalBorderDrawable().setAlpha(z ? 255 : 0);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onCollapseQSPanelStart] ", "OplusQSPersonalComponent-->", "SystemUi--QuickSettings", z);
        }
        onBorderStatusChange(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        getTileAdapter().setImmersiveOperation(true);
        this.addingArea = z ? IntentTarget.PERSONAL_LIST : IntentTarget.OTHER_LIST;
        updateRecyclerViewVisible(getCustomLayoutManager().getLayoutConfig().getRowCount());
        getEmptyPlaceHolderAction().onEnterAddingMode();
        onBorderStatusChange(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        getTileAdapter().setImmersiveOperation(true);
        getTileAdapter().setDragMode(true);
        getEmptyPlaceHolderAction().onEnterAddingMode();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSPersonalComponent-->[onEnterEditMode]");
        }
        this.editViewModel.sendIntent(TileEditIntent.EnterEditIntent.INSTANCE, IntentTarget.PERSONAL_LIST);
        getCellSizeChangeProxy().updateAreaRow(4);
        updateBorderSize();
        getEmptyPlaceHolderAction().onEnterEditMode(z);
        onBorderStatusChange(true);
        refreshEmptyViewHolder();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
        getTileAdapter().setImmersiveOperation(false);
        this.addingArea = IntentTarget.NON;
        updateRecyclerViewVisible(getCustomLayoutManager().getLayoutConfig().getRowCount());
        getEmptyPlaceHolderAction().onExitAddingMode();
        onBorderStatusChange(true);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
        getTileAdapter().setImmersiveOperation(false);
        getTileAdapter().setDragMode(false);
        getEmptyPlaceHolderAction().onExitAddingMode();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSPersonalComponent-->[onExitEditMode]");
        }
        this.editViewModel.sendIntent(TileEditIntent.ExitEditIntent.INSTANCE, IntentTarget.PERSONAL_LIST);
        updateBorderSize();
        getEmptyPlaceHolderAction().onExitEditMode(z);
        this.tileOperationHintAction.onExitEditMode();
        onBorderStatusChange(false);
        refreshEmptyViewHolder();
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
        EditableTileViewHolder.Companion.clear();
        EditTileAdapter.Companion.getClass();
        RecyclerView.RecycledViewPool recycledViewPool = EditTileAdapter.sharedRecyclerPool;
        if (recycledViewPool != null) {
            recycledViewPool.clear();
        }
        TileEditViewModel tileEditViewModel = this.editViewModel;
        tileEditViewModel.getClass();
        CoroutineScopeKt.cancel(ViewModelKt.getViewModelScope(tileEditViewModel), "clear", (Throwable) null);
        GridDataCache.INSTANCE.getClass();
        GridDataCache.clear();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        OplusQSPersonalComponent$talkbackStateObserver$1 oplusQSPersonalComponent$talkbackStateObserver$1 = this.talkbackStateObserver;
        synchronized (localBroadcastManager.mReceivers) {
            try {
                ArrayList arrayList = (ArrayList) localBroadcastManager.mReceivers.remove(oplusQSPersonalComponent$talkbackStateObserver$1);
                if (arrayList == null) {
                    return;
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    LocalBroadcastManager.ReceiverRecord receiverRecord = (LocalBroadcastManager.ReceiverRecord) arrayList.get(size);
                    receiverRecord.dead = true;
                    for (int i = 0; i < receiverRecord.filter.countActions(); i++) {
                        String action = receiverRecord.filter.getAction(i);
                        ArrayList arrayList2 = (ArrayList) localBroadcastManager.mActions.get(action);
                        if (arrayList2 != null) {
                            for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                                LocalBroadcastManager.ReceiverRecord receiverRecord2 = (LocalBroadcastManager.ReceiverRecord) arrayList2.get(size2);
                                if (receiverRecord2.receiver == oplusQSPersonalComponent$talkbackStateObserver$1) {
                                    receiverRecord2.dead = true;
                                    arrayList2.remove(size2);
                                }
                            }
                            if (arrayList2.size() <= 0) {
                                localBroadcastManager.mActions.remove(action);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void refreshEmptyViewHolder() {
        if (!getTileAdapter().isEditMode || !OplusAccessibilityUtil.isTalkbackEnabled(getContext())) {
            getCustomLayoutManager().getLayoutConfig().setCreateEmptyViewHolder(null);
            return;
        }
        StaggeredPagerLayoutManager.LayoutConfig layoutConfig = getCustomLayoutManager().getLayoutConfig();
        OplusQSPersonalComponent$$ExternalSyntheticLambda10 oplusQSPersonalComponent$$ExternalSyntheticLambda10 = new OplusQSPersonalComponent$$ExternalSyntheticLambda10(0);
        oplusQSPersonalComponent$$ExternalSyntheticLambda10.f$0 = this;
        VarHandle.storeStoreFence();
        layoutConfig.setCreateEmptyViewHolder(oplusQSPersonalComponent$$ExternalSyntheticLambda10);
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
        OplusQSPersonalComponent$$ExternalSyntheticLambda9 oplusQSPersonalComponent$$ExternalSyntheticLambda9 = new OplusQSPersonalComponent$$ExternalSyntheticLambda9();
        oplusQSPersonalComponent$$ExternalSyntheticLambda9.f$0 = function1;
        oplusQSPersonalComponent$$ExternalSyntheticLambda9.f$1 = this;
        VarHandle.storeStoreFence();
        ViewExtKt.addOnTouchListener(this.recyclerView, new EmptyAreaGestureProxy(cOUIRecyclerView, oplusQSPersonalComponent$$ExternalSyntheticLambda9), false);
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
        updateRecyclerViewVisible(getCustomLayoutManager().getLayoutConfig().getRowCount());
        recyclerView.setItemViewCacheSize(50);
        recyclerView.setBackground(getQsPersonalBorderDrawable());
        recyclerView.setAdapter(getTileAdapter());
        recyclerView.setLayoutManager(getCustomLayoutManager());
        getCustomLayoutManager().setRecyclerView(recyclerView);
        getTileAdapter().setPressFeedBack(this.dragHelper);
        getTileAdapter().setDeformStateChangedListener(getResizeCallBack());
        getTileAdapter().setHostContainerAbility(this.abilityForViewHolder);
        recyclerView.setOverScrollEnable(false);
        OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 = new OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1(2);
        oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1.this$0 = this;
        VarHandle.storeStoreFence();
        recyclerView.addOnLayoutChangeListener(oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1);
        this.recyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.dragHelper.registerRecyclerView(this.recyclerView, getDragCallbackProxy());
        onBorderStatusChange(false);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        OplusQSPersonalComponent$talkbackStateObserver$1 oplusQSPersonalComponent$talkbackStateObserver$1 = this.talkbackStateObserver;
        IntentFilter intentFilter = new IntentFilter("app.intent.action.TALKBACK_SWITCH_CHAGNED");
        synchronized (localBroadcastManager.mReceivers) {
            try {
                LocalBroadcastManager.ReceiverRecord receiverRecord = new LocalBroadcastManager.ReceiverRecord();
                receiverRecord.filter = intentFilter;
                receiverRecord.receiver = oplusQSPersonalComponent$talkbackStateObserver$1;
                VarHandle.storeStoreFence();
                ArrayList arrayList = (ArrayList) localBroadcastManager.mReceivers.get(oplusQSPersonalComponent$talkbackStateObserver$1);
                if (arrayList == null) {
                    arrayList = new ArrayList(1);
                    localBroadcastManager.mReceivers.put(oplusQSPersonalComponent$talkbackStateObserver$1, arrayList);
                }
                arrayList.add(receiverRecord);
                for (int i = 0; i < intentFilter.countActions(); i++) {
                    String action = intentFilter.getAction(i);
                    ArrayList arrayList2 = (ArrayList) localBroadcastManager.mActions.get(action);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                        localBroadcastManager.mActions.put(action, arrayList2);
                    }
                    arrayList2.add(receiverRecord);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        getEmptyPlaceHolderAction().init();
        RepeatWhenAttachedKt.repeatWhenAttached(this.recyclerView, Dispatchers.getMain().getImmediate(), new OplusQSPersonalComponent$collectFlow$1(this, null));
    }

    public final void stopDrag() {
        this.dragHelper.stopDrag();
    }

    public final void updateBorderSize() {
        CellSizeChangeProxy.tryAdjustHostSize$default(getCellSizeChangeProxy());
        View view = this.mView;
        int i = 0;
        if (!view.isLaidOut() || view.isLayoutRequested()) {
            OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 = new OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1(i);
            oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1.this$0 = this;
            VarHandle.storeStoreFence();
            view.addOnLayoutChangeListener(oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1);
        } else {
            CellAreaConfig cellAreaConfig = this.cellAreaProvider.getCellAreaConfig();
            int measuredWidth = (((this.mView.getMeasuredWidth() != 0 ? this.mView.getMeasuredWidth() : cellAreaConfig.getWidth()) - (CellCalculatorManager.Companion.getInstance(getContext()).getCellSize(true).getCellWidth() * 2)) / 2) - cellAreaConfig.getEditALeftBorderMargin();
            QSPersonalBorderDrawable qsPersonalBorderDrawable = getQsPersonalBorderDrawable();
            qsPersonalBorderDrawable.leftRightBorderMargin = measuredWidth;
            qsPersonalBorderDrawable.updateBounds();
            qsPersonalBorderDrawable.invalidateSelf();
        }
        this.recyclerView.setPadding(0, this.cellAreaProvider.getCellAreaConfig().getEditATopBorderMargin(), 0, 0);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE) {
            if (QsColorUtil.isNeedDarkThemeColor(getContext(), false)) {
                getQsPersonalBorderDrawable().setBorderColor(getContext().getResources().getColor(2131103214));
            } else {
                getQsPersonalBorderDrawable().setBorderColor(getContext().getResources().getColor(2131103213));
            }
        }
        getTileAdapter().updateColor(colorType);
        getEmptyPlaceHolderAction().onConfigurationChanged();
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

    public final void updateRecyclerViewVisible(int i) {
        boolean zIsSplitAdd = OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd();
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        IntentTarget intentTarget = this.addingArea;
        boolean z = true;
        if (intentTarget == IntentTarget.NON || zIsSplitAdd ? i <= 0 || (!getTileAdapter().isEditMode && getTileAdapter().getDataList().isEmpty()) : intentTarget != IntentTarget.PERSONAL_LIST) {
            z = false;
        }
        cOUIRecyclerView.setVisibility(z ? 0 : 8);
        if (zIsSplitAdd) {
            this.recyclerView.setAlpha(this.addingArea == IntentTarget.OTHER_LIST ? 0.3f : 1.0f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        int rowCount = getCustomLayoutManager().getLayoutConfig().getRowCount();
        int c2Height = rowCount > 0 ? this.cellAreaProvider.getCellAreaConfig().getC2Height() : 0;
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        ViewGroup.LayoutParams layoutParams = cOUIRecyclerView.getLayoutParams();
        if (layoutParams == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.topMargin = c2Height;
        cOUIRecyclerView.setLayoutParams(marginLayoutParams);
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        if (z) {
            getCellSizeChangeProxy().refreshGridNum();
        }
        getEmptyPlaceHolderAction().onConfigurationChanged();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateResource isLandSplit:" + zBooleanValue + ", updateLandSplitMode:" + z + ", rowSpan: " + rowCount, null, 8);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.$$delegate_0.updateState(i, i2, f, f2, f3);
    }
}
