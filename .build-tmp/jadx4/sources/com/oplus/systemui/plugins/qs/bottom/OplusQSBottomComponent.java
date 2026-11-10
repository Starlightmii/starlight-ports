package com.oplus.systemui.plugins.qs.bottom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.core.view.OneShotPreDrawListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.coui.appcompat.indicator.COUIPageIndicator;
import com.oplus.systemui.plugins.qs.CellAreaConfig;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.BottomIndicatorAnimator;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.springtransition.AlphaTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.VerticalBoundsTransition;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSBottomComponent extends ViewController implements IQsComponent {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSBottomViewController";
    private final CellAreaProvider cellAreaProvider;
    private final COUIPageIndicator indicator;
    private final BottomIndicatorAnimator indicatorAnimator;
    private final ViewGroup indicatorContainer;
    public boolean isEditMode;
    public float negProgress;
    public float overTransY;
    private Function0 panelNegYGetter;
    private IntentTarget tileAddingArea;
    private final Lazy transitionProperties$delegate;
    private final FrameLayout view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent$onViewAttached$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01981 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent$onViewAttached$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00451 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusQSBottomComponent this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent$onViewAttached$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00461 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSBottomComponent this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent$onViewAttached$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00471 implements FlowCollector {
                    public /* synthetic */ OplusQSBottomComponent this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        ((Boolean) obj).booleanValue();
                        OplusQSBottomComponent oplusQSBottomComponent = this.this$0;
                        Companion companion = OplusQSBottomComponent.Companion;
                        oplusQSBottomComponent.updateVisibility$5();
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00461(OplusQSBottomComponent oplusQSBottomComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSBottomComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00461(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow superPowerSaveMode = OplusQSSpecialModeProvider.Companion.getInstance().getSuperPowerSaveMode();
                        OplusQSBottomComponent oplusQSBottomComponent = this.this$0;
                        C00471 c00471 = new C00471();
                        c00471.this$0 = oplusQSBottomComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (FlowExtKt.collectTraced(superPowerSaveMode, "OplusQSBottomViewController-collect-superPowerSaveMode", c00471, this) == coroutine_suspended) {
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
            public C00451(OplusQSBottomComponent oplusQSBottomComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSBottomComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00451 c00451 = new C00451(this.this$0, continuation);
                c00451.L$0 = obj;
                return c00451;
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
                CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSBottomViewController-launch-superPowerSaveMode", (CoroutineContext) null, (CoroutineStart) null, new C00461(this.this$0, null), 6);
                return Unit.INSTANCE;
            }
        }

        public C01981(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            C01981 c01981 = OplusQSBottomComponent.this.new C01981((Continuation) obj3);
            c01981.L$0 = (LifecycleOwner) obj;
            return c01981.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                C00451 c00451 = new C00451(OplusQSBottomComponent.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00451, this) == coroutine_suspended) {
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

    public OplusQSBottomComponent(FrameLayout frameLayout, CellAreaProvider cellAreaProvider) {
        super(frameLayout);
        this.view = frameLayout;
        this.cellAreaProvider = cellAreaProvider;
        ViewGroup viewGroup = (ViewGroup) frameLayout.requireViewById(2131364788);
        this.indicatorContainer = viewGroup;
        this.indicator = frameLayout.requireViewById(2131364787);
        this.transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSBottomComponent$$ExternalSyntheticLambda0());
        this.tileAddingArea = IntentTarget.NON;
        this.indicatorAnimator = new BottomIndicatorAnimator(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    private final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
        }
    }

    private final void setTileAddingArea(IntentTarget intentTarget) {
        float f;
        if (this.tileAddingArea != intentTarget) {
            this.tileAddingArea = intentTarget;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "tileAddingArea : " + intentTarget, null, 8);
            updateBottomSpacing();
            ViewGroup viewGroup = this.indicatorContainer;
            int iOrdinal = this.tileAddingArea.ordinal();
            if (iOrdinal != 0) {
                f = iOrdinal != 1 ? 1.0f : 0.4f;
            } else {
                f = 0.0f;
            }
            viewGroup.setAlpha(f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        Object obj;
        Object next;
        Object next2;
        boolean zIsSplitAdd = OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd();
        Set<? extends SpringTransition> set2 = set;
        Iterator<T> it = set2.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next).getTransitionName(), "parent_translation_transition"));
        SpringTransition springTransition = (SpringTransition) next;
        if (springTransition != null) {
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.view, VerticalBoundsTransition.class));
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.indicatorContainer, FadeTransition.class));
        }
        Iterator<T> it2 = set2.iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next2).getTransitionName(), "adding_transition_position"));
        SpringTransition springTransition2 = (SpringTransition) next2;
        if (springTransition2 != null && !zIsSplitAdd) {
            springTransition2.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.view, VerticalBoundsTransition.class));
        }
        for (Object obj2 : set2) {
            if (Intrinsics.areEqual(((SpringTransition) obj2).getTransitionName(), "adding_transition")) {
                obj = obj2;
                break;
            }
        }
        SpringTransition springTransition3 = (SpringTransition) obj;
        if (springTransition3 != null) {
            springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.indicatorContainer, AlphaTransition.class));
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
        this.mView.removeOnAttachStateChangeListener(this.mOnAttachStateListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return this.indicator;
    }

    public final Function0 getPanelNegYGetter() {
        return this.panelNegYGetter;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return super.getSubSize();
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void init() {
        super.init();
        ViewGroup viewGroup = this.indicatorContainer;
        PressFeedbackHelper.attachPressFeedback(viewGroup, viewGroup);
        this.indicatorContainer.setOnClickListener(AnonymousClass1.INSTANCE);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        super.injectHostAbility(iComponentHostAbility);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        return false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        setTileAddingArea(z ? IntentTarget.PERSONAL_LIST : IntentTarget.OTHER_LIST);
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
        FrameLayout frameLayout = this.view;
        OplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1 oplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1 = new OplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1(1);
        oplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1.this$0 = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(frameLayout, oplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
        setTileAddingArea(IntentTarget.NON);
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
        FrameLayout frameLayout = this.view;
        OplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1 oplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1 = new OplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1(0);
        oplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1.this$0 = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(frameLayout, oplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        RepeatWhenAttachedKt.repeatWhenAttached(this.view, Dispatchers.getMain().getImmediate(), new C01981(null));
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
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
        this.overTransY = f;
        updateBottomTranslationY();
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

    public final void setPanelNegYGetter(Function0 function0) {
        this.panelNegYGetter = function0;
    }

    public final void updateBottomSpacing() {
        int gHeight;
        boolean zIsSplitAdd = OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd();
        CellAreaConfig cellAreaConfig = this.cellAreaProvider.getCellAreaConfig();
        if (this.tileAddingArea != IntentTarget.OTHER_LIST || zIsSplitAdd) {
            gHeight = cellAreaConfig.getGHeight() + cellAreaConfig.getC1Height();
        } else {
            gHeight = cellAreaConfig.getAPersonalHeight() + cellAreaConfig.getC1Height() + cellAreaConfig.getGHeight();
        }
        int iLockDpi = CellCalculatorManager.Companion.lockDpi(getContext(), 2131172024);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(gHeight, "updateBottomSpacing: bottom:"), null, 8);
        FrameLayout frameLayout = this.view;
        frameLayout.setPaddingRelative(frameLayout.getPaddingStart(), iLockDpi, frameLayout.getPaddingEnd(), gHeight);
    }

    public final void updateBottomTranslationY() {
        int personalRowCount;
        FrameLayout frameLayout = (FrameLayout) this.mView;
        if (((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) {
            personalRowCount = CellCalculatorManager.Companion.getInstance(getContext()).getNoPersonalRowCountLandRight();
        } else {
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            personalRowCount = companion.getInstance(getContext()).getPersonalRowCount() + companion.getInstance(getContext()).getNoPersonalRowCountPort();
        }
        float f = (1.0f - this.negProgress) * (-60.0f) * (personalRowCount + 1) * 1.0f;
        Function0 function0 = this.panelNegYGetter;
        frameLayout.setTranslationY(f + (function0 != null ? ((Number) function0.invoke()).floatValue() : 0.0f) + this.overTransY);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE) {
            boolean zIsNeedDarkThemeColor = QsColorUtil.isNeedDarkThemeColor(getContext(), false);
            COUIPageIndicator cOUIPageIndicator = this.indicator;
            if (zIsNeedDarkThemeColor) {
                cOUIPageIndicator.setTraceDotColor(getContext().getResources().getColor(2131103318));
                this.indicator.setPageIndicatorDotsColor(getContext().getResources().getColor(2131103316));
            } else {
                cOUIPageIndicator.setTraceDotColor(getContext().getResources().getColor(2131103317));
                this.indicator.setPageIndicatorDotsColor(getContext().getResources().getColor(2131103315));
            }
        }
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
        updateBottomTranslationY();
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
        if (z) {
            if (((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) {
                if (this.indicatorContainer.indexOfChild(this.indicator) >= 0) {
                    this.indicatorContainer.removeView(this.indicator);
                    this.indicatorContainer.setVisibility(8);
                    this.view.addView(this.indicator);
                    updateVisibility$5();
                }
            } else if (this.view.indexOfChild(this.indicator) >= 0) {
                this.view.removeView(this.indicator);
                this.indicatorContainer.setVisibility(this.indicator.getDotsCount() > 1 ? 0 : 8);
                this.indicatorContainer.addView(this.indicator);
                updateVisibility$5();
            }
        }
        View view = this.mView;
        ((FrameLayout) view).setPaddingRelative(((FrameLayout) view).getPaddingStart(), CellCalculatorManager.Companion.lockDpi(context, 2131172024), ((FrameLayout) this.mView).getPaddingEnd(), ((FrameLayout) this.mView).getPaddingBottom());
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.indicator.setTransitionAlpha(f);
        this.indicator.setScaleX(f2);
        this.indicator.setScaleY(f2);
        if (f3 > -1.0f) {
            this.negProgress = f3;
        }
        updateBottomTranslationY();
    }

    public final void updateVisibility$5() {
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        if (((Boolean) companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue()) {
            this.view.setVisibility(8);
            return;
        }
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        FrameLayout frameLayout = this.view;
        if (zBooleanValue) {
            frameLayout.setVisibility(this.indicator.getDotsCount() > 1 ? 0 : 8);
        } else {
            frameLayout.setVisibility(0);
        }
    }
}
