package com.oplus.systemui.plugins.qs.customize.component;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSEmptyListTipComponent implements IQsComponent {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final TileEditViewModel editViewModel;
    private final View emptyListTextView;
    public boolean isEditMode;
    public boolean listIsEmpty;
    private final Lazy transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new QSEmptyListTipComponent$$ExternalSyntheticLambda0());

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.QSEmptyListTipComponent$startComponent$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.QSEmptyListTipComponent$startComponent$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00511 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ QSEmptyListTipComponent this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.QSEmptyListTipComponent$startComponent$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00521 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ QSEmptyListTipComponent this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.QSEmptyListTipComponent$startComponent$1$1$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class AnonymousClass2 implements FlowCollector {
                    public /* synthetic */ QSEmptyListTipComponent this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        TileEditState tileEditState = (TileEditState) obj;
                        if (tileEditState instanceof TileEditState.Normal) {
                            TileEditState.Normal normal = (TileEditState.Normal) tileEditState;
                            this.this$0.setListIsEmpty(normal.getAllPersonalTiles().isEmpty() && normal.getOtherAddedTiles().isEmpty());
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00521(QSEmptyListTipComponent qSEmptyListTipComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = qSEmptyListTipComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00521(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow uiStateFlow = this.this$0.editViewModel.getUiStateFlow();
                        QSEmptyListTipComponent qSEmptyListTipComponent = this.this$0;
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                        anonymousClass2.this$0 = qSEmptyListTipComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        QSEmptyListTipComponent$startComponent$1$1$1$invokeSuspend$$inlined$filter$1$2 qSEmptyListTipComponent$startComponent$1$1$1$invokeSuspend$$inlined$filter$1$2 = new QSEmptyListTipComponent$startComponent$1$1$1$invokeSuspend$$inlined$filter$1$2();
                        qSEmptyListTipComponent$startComponent$1$1$1$invokeSuspend$$inlined$filter$1$2.$this_unsafeFlow = anonymousClass2;
                        VarHandle.storeStoreFence();
                        Object objCollect = uiStateFlow.collect(qSEmptyListTipComponent$startComponent$1$1$1$invokeSuspend$$inlined$filter$1$2, this);
                        if (objCollect != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            objCollect = Unit.INSTANCE;
                        }
                        if (objCollect == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00511(QSEmptyListTipComponent qSEmptyListTipComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = qSEmptyListTipComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00511 c00511 = new C00511(this.this$0, continuation);
                c00511.L$0 = obj;
                return c00511;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00521(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = QSEmptyListTipComponent.this.new AnonymousClass1((Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                C00511 c00511 = new C00511(QSEmptyListTipComponent.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00511, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public QSEmptyListTipComponent(TileEditViewModel tileEditViewModel, OplusQSRootView oplusQSRootView) {
        this.editViewModel = tileEditViewModel;
        this.emptyListTextView = oplusQSRootView.requireViewById(2131365823);
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    private final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            this.emptyListTextView.setVisibility((z || !this.listIsEmpty) ? 8 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setListIsEmpty(boolean z) {
        if (this.listIsEmpty != z) {
            this.listIsEmpty = z;
            this.emptyListTextView.setVisibility((this.isEditMode || !z) ? 8 : 0);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        Object next;
        super.bindTargetProperties(set);
        Iterator<T> it = set.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next).getTransitionName(), "enter_exit_edit_mode_main_transition"));
        SpringTransition springTransition = (SpringTransition) next;
        if (springTransition != null) {
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.emptyListTextView, FadeTransition.class));
        }
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
        getTransitionProperties().destroy();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return this.emptyListTextView;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return this.emptyListTextView.getVisibility() == 0 ? 1 : 0;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        super.injectHostAbility(iComponentHostAbility);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
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
        setEditMode(true);
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
        setEditMode(false);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        super.saveLatestNoEditModeLocList(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setEmptyTouchListener(Function1 function1) {
        super.setEmptyTouchListener(function1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setLayerType(boolean z) {
        super.setLayerType(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setListening(boolean z) {
        super.setListening(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void startComponent() {
        RepeatWhenAttachedKt.repeatWhenAttached(this.emptyListTextView, Dispatchers.getMain().getImmediate(), new AnonymousClass1(null));
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        super.updateColor(colorType);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateDrawable(List<SpanSize> list, boolean z) {
        super.updateDrawable(list, z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconDrawable(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconTint() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateNodeTranslationY() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateOutline(List<SpanSize> list) {
        super.updateOutline(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updatePivotXY(int i, int i2, boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateQuickEntranceDrawable() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        super.updateResource(z, context);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.emptyListTextView.setTransitionAlpha(f);
    }
}
