package com.oplus.systemui.statusbar.pipeline.shared.ui.binder;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import androidx.core.animation.Animator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.data.repository.HomeStatusBarComponentsRepository;
import com.android.systemui.statusbar.events.shared.model.SystemEventAnimationState;
import com.android.systemui.statusbar.phone.fragment.StatusBarSystemEventDefaultAnimator;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.HomeStatusBarViewBinder;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.HomeStatusBarViewBinderImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.StatusBarVisibilityChangeListener;
import com.android.systemui.statusbar.pipeline.shared.ui.model.VisibilityModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.HomeStatusBarViewModel;
import com.android.systemui.statusbar.policy.ShouldShowForDisplay;
import com.android.systemui.statusbar.window.data.repository.StatusBarWindowStatePerDisplayRepository;
import com.android.systemui.statusbar.window.shared.model.StatusBarWindowState;
import com.oplus.systemui.dagger.OplusHomeStatusBarComponent;
import com.oplus.systemui.seamless.FakeType;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel;
import com.oplus.systemui.statusbar.seeding.CapsulePluginContainerController;
import com.oplus.systemui.statusbar.widget.StatClock;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHomeStatusBarViewBinderImpl implements OplusHomeStatusBarViewBinder, HomeStatusBarViewBinder {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusHomeStatusBarViewBinder";
    private final Context context;
    private final HomeStatusBarComponentsRepository homeStatusBarComponentRepository;
    private final HomeStatusBarViewBinderImpl homeStatusBarViewBinder;
    private final Lazy oplusFakeViewProvider;
    private final ShouldShowForDisplay shouldShowForDisplay;
    private final StatusBarWindowStatePerDisplayRepository statusBarWindowStateRepository;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ Ref.ObjectRef<View> $animateStatusBarView;
        final /* synthetic */ StatClock $clockView;
        final /* synthetic */ int $displayId;
        final /* synthetic */ View $dynamicIconContent;
        final /* synthetic */ View $endSideView;
        final /* synthetic */ StatusBarSystemEventDefaultAnimator $eventAnimator;
        final /* synthetic */ View $seedlingContainer;
        final /* synthetic */ View $statusBarContent;
        final /* synthetic */ View $systemPromptView;
        final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01601 extends SuspendLambda implements Function2 {
            final /* synthetic */ Ref.ObjectRef<View> $animateStatusBarView;
            final /* synthetic */ StatClock $clockView;
            final /* synthetic */ int $displayId;
            final /* synthetic */ View $dynamicIconContent;
            final /* synthetic */ View $endSideView;
            final /* synthetic */ StatusBarSystemEventDefaultAnimator $eventAnimator;
            final /* synthetic */ View $seedlingContainer;
            final /* synthetic */ View $statusBarContent;
            final /* synthetic */ View $systemPromptView;
            final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01611 extends SuspendLambda implements Function2 {
                final /* synthetic */ StatClock $clockView;
                final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
                int label;
                final /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01611(OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, StatClock statClock, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusHomeStatusBarViewBinderImpl;
                    this.$clockView = statClock;
                    this.$viewModel = oplusHomeStatusBarViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01611(this.this$0, this.$viewModel, this.$clockView, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl = this.this$0;
                        StatClock statClock = this.$clockView;
                        OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel = this.$viewModel;
                        this.label = 1;
                        if (OplusHomeStatusBarViewBinderImpl.access$bindClock(oplusHomeStatusBarViewBinderImpl, oplusHomeStatusBarViewModel, statClock, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $systemPromptView;
                final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
                int label;
                final /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$2$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01621 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ Object $systemPromptView;
                    public /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                    public /* synthetic */ C01621(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                this.this$0.homeStatusBarViewBinder.animate((View) this.$systemPromptView, (VisibilityModel) obj);
                                break;
                            default:
                                this.this$0.homeStatusBarViewBinder.animate((StatClock) this.$systemPromptView, (VisibilityModel) obj);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = oplusHomeStatusBarViewModel;
                    this.this$0 = oplusHomeStatusBarViewBinderImpl;
                    this.$systemPromptView = view;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$viewModel, this.this$0, this.$systemPromptView, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowIsPromptViewVisible = this.$viewModel.isPromptViewVisible();
                        OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl = this.this$0;
                        View view = this.$systemPromptView;
                        C01621 c01621 = new C01621(0);
                        c01621.this$0 = oplusHomeStatusBarViewBinderImpl;
                        c01621.$systemPromptView = view;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (flowIsPromptViewVisible.collect(c01621, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ int $displayId;
                final /* synthetic */ View $seedlingContainer;
                final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
                int label;
                final /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$3$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01631 implements FlowCollector {
                    public /* synthetic */ int $displayId;
                    public /* synthetic */ View $seedlingContainer;
                    public /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        CapsulePluginContainerController statusBarCapsuleContainerController;
                        VisibilityModel visibilityModel = (VisibilityModel) obj;
                        OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl = this.this$0;
                        Object obj2 = ((Map) oplusHomeStatusBarViewBinderImpl.homeStatusBarComponentRepository.getComponentsByDisplayId().getValue()).get(Boxing.boxInt(this.$displayId));
                        OplusHomeStatusBarComponent oplusHomeStatusBarComponent = obj2 instanceof OplusHomeStatusBarComponent ? (OplusHomeStatusBarComponent) obj2 : null;
                        if (oplusHomeStatusBarComponent != null && (statusBarCapsuleContainerController = oplusHomeStatusBarComponent.getStatusBarCapsuleContainerController()) != null) {
                            statusBarCapsuleContainerController.setCapsuleFakeContainerVisibility(visibilityModel.getVisibility() == 0);
                        }
                        oplusHomeStatusBarViewBinderImpl.homeStatusBarViewBinder.animate(this.$seedlingContainer, visibilityModel);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, int i, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = oplusHomeStatusBarViewModel;
                    this.this$0 = oplusHomeStatusBarViewBinderImpl;
                    this.$displayId = i;
                    this.$seedlingContainer = view;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, this.this$0, this.$displayId, this.$seedlingContainer, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowIsSeedlingContainerVisible = this.$viewModel.isSeedlingContainerVisible();
                        OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl = this.this$0;
                        int i2 = this.$displayId;
                        View view = this.$seedlingContainer;
                        C01631 c01631 = new C01631();
                        c01631.this$0 = oplusHomeStatusBarViewBinderImpl;
                        c01631.$displayId = i2;
                        c01631.$seedlingContainer = view;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (flowIsSeedlingContainerVisible.collect(c01631, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $dynamicIconContent;
                final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$4$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01641 implements FlowCollector {
                    public /* synthetic */ Object $dynamicIconContent;
                    public final /* synthetic */ int $r8$classId;

                    public /* synthetic */ C01641(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                ((View) this.$dynamicIconContent).setVisibility(((Boolean) obj).booleanValue() ? 0 : 4);
                                break;
                            case 1:
                                ((StatClock) this.$dynamicIconContent).applyShowSecondsFromController(((Boolean) obj).booleanValue());
                                break;
                            default:
                                ((StatClock) this.$dynamicIconContent).setHideSecBySeeding(((Boolean) obj).booleanValue());
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = oplusHomeStatusBarViewModel;
                    this.$dynamicIconContent = view;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$viewModel, this.$dynamicIconContent, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowIsDynamicIconVisible = this.$viewModel.isDynamicIconVisible();
                        View view = this.$dynamicIconContent;
                        C01641 c01641 = new C01641(0);
                        c01641.$dynamicIconContent = view;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (flowIsDynamicIconVisible.collect(c01641, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$5, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                final /* synthetic */ Ref.ObjectRef<View> $animateStatusBarView;
                final /* synthetic */ View $endSideView;
                final /* synthetic */ StatusBarSystemEventDefaultAnimator $eventAnimator;
                final /* synthetic */ View $statusBarContent;
                final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
                int label;
                final /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinderImpl$bind$1$1$5$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01651 implements FlowCollector {
                    public /* synthetic */ Ref.ObjectRef $animateStatusBarView;
                    public /* synthetic */ View $endSideView;
                    public /* synthetic */ StatusBarSystemEventDefaultAnimator $eventAnimator;
                    public /* synthetic */ View $statusBarContent;
                    public /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
                    public /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        SystemEventAnimationState systemEventAnimationState = (SystemEventAnimationState) obj;
                        Ref.ObjectRef objectRef = this.$animateStatusBarView;
                        StatusBarWindowState statusBarWindowState = (StatusBarWindowState) this.this$0.statusBarWindowStateRepository.getWindowState().getValue();
                        StatusBarSystemEventDefaultAnimator statusBarSystemEventDefaultAnimator = this.$eventAnimator;
                        if (!statusBarSystemEventDefaultAnimator.isAnimationRunning) {
                            objectRef.element = (statusBarWindowState == StatusBarWindowState.Hidden || statusBarWindowState == StatusBarWindowState.Hiding) ? this.$statusBarContent : this.$endSideView;
                        }
                        boolean zAreEqual = Intrinsics.areEqual(objectRef.element, this.$statusBarContent);
                        View view = (View) objectRef.element;
                        OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel = this.$viewModel;
                        View view2 = this.$statusBarContent;
                        int iOrdinal = systemEventAnimationState.ordinal();
                        if (iOrdinal != 0) {
                            if (iOrdinal == 2) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                    Log.d("SystemUi--Statusbar", "OplusHomeStatusBarViewBinder-->Start AnimatingIn to hide status bar");
                                }
                                statusBarSystemEventDefaultAnimator.onSystemEventAnimationBegin().start();
                                oplusHomeStatusBarViewModel.getHasFullscreenPrivacyIcon().setValue(Boolean.valueOf(Intrinsics.areEqual(objectRef.element, view2)));
                            } else if (iOrdinal == 4) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                    Log.d("SystemUi--Statusbar", "OplusHomeStatusBarViewBinder-->Start AnimatingOut to resume status bar");
                                }
                                Animator animatorOnSystemEventAnimationFinish = statusBarSystemEventDefaultAnimator.onSystemEventAnimationFinish(false);
                                if (zAreEqual) {
                                    animatorOnSystemEventAnimationFinish.setStartDelay(700L);
                                }
                                animatorOnSystemEventAnimationFinish.start();
                                if (((Boolean) oplusHomeStatusBarViewModel.getHasFullscreenPrivacyIcon().getValue()).booleanValue()) {
                                    oplusHomeStatusBarViewModel.getHasFullscreenPrivacyIcon().setValue(Boolean.FALSE);
                                }
                            }
                        } else if (view.getAlpha() == 0.0f) {
                            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, OplusHomeStatusBarViewBinderImpl.TAG, "animateStatusBarView.alpha == 0fAnimatingOut is uncollected or interrupted");
                            view.setAlpha(1.0f);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, StatusBarSystemEventDefaultAnimator statusBarSystemEventDefaultAnimator, Ref.ObjectRef objectRef, View view, View view2, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = oplusHomeStatusBarViewModel;
                    this.this$0 = oplusHomeStatusBarViewBinderImpl;
                    this.$eventAnimator = statusBarSystemEventDefaultAnimator;
                    this.$animateStatusBarView = objectRef;
                    this.$statusBarContent = view;
                    this.$endSideView = view2;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.$viewModel, this.this$0, this.$eventAnimator, this.$animateStatusBarView, this.$statusBarContent, this.$endSideView, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow animationState = this.$viewModel.getAnimationState();
                        OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl = this.this$0;
                        StatusBarSystemEventDefaultAnimator statusBarSystemEventDefaultAnimator = this.$eventAnimator;
                        Ref.ObjectRef<View> objectRef = this.$animateStatusBarView;
                        View view = this.$statusBarContent;
                        View view2 = this.$endSideView;
                        OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel = this.$viewModel;
                        C01651 c01651 = new C01651();
                        c01651.this$0 = oplusHomeStatusBarViewBinderImpl;
                        c01651.$eventAnimator = statusBarSystemEventDefaultAnimator;
                        c01651.$animateStatusBarView = objectRef;
                        c01651.$statusBarContent = view;
                        c01651.$endSideView = view2;
                        c01651.$viewModel = oplusHomeStatusBarViewModel;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (animationState.collect(c01651, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    Regex$.ExternalSyntheticBUOutline0.m$1();
                    return null;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01601(OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, StatClock statClock, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, View view, int i, View view2, View view3, StatusBarSystemEventDefaultAnimator statusBarSystemEventDefaultAnimator, Ref.ObjectRef objectRef, View view4, View view5, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusHomeStatusBarViewBinderImpl;
                this.$clockView = statClock;
                this.$viewModel = oplusHomeStatusBarViewModel;
                this.$systemPromptView = view;
                this.$displayId = i;
                this.$seedlingContainer = view2;
                this.$dynamicIconContent = view3;
                this.$eventAnimator = statusBarSystemEventDefaultAnimator;
                this.$animateStatusBarView = objectRef;
                this.$statusBarContent = view4;
                this.$endSideView = view5;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C01601 c01601 = new C01601(this.this$0, this.$clockView, this.$viewModel, this.$systemPromptView, this.$displayId, this.$seedlingContainer, this.$dynamicIconContent, this.$eventAnimator, this.$animateStatusBarView, this.$statusBarContent, this.$endSideView, continuation);
                c01601.L$0 = obj;
                return c01601;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01611(this.this$0, this.$viewModel, this.$clockView, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$viewModel, this.this$0, this.$systemPromptView, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$viewModel, this.this$0, this.$displayId, this.$seedlingContainer, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.$viewModel, this.$dynamicIconContent, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.$viewModel, this.this$0, this.$eventAnimator, this.$animateStatusBarView, this.$statusBarContent, this.$endSideView, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StatClock statClock, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, View view, int i, View view2, View view3, StatusBarSystemEventDefaultAnimator statusBarSystemEventDefaultAnimator, Ref.ObjectRef objectRef, View view4, View view5, Continuation continuation) {
            super(3, continuation);
            this.$clockView = statClock;
            this.$viewModel = oplusHomeStatusBarViewModel;
            this.$systemPromptView = view;
            this.$displayId = i;
            this.$seedlingContainer = view2;
            this.$dynamicIconContent = view3;
            this.$eventAnimator = statusBarSystemEventDefaultAnimator;
            this.$animateStatusBarView = objectRef;
            this.$statusBarContent = view4;
            this.$endSideView = view5;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = OplusHomeStatusBarViewBinderImpl.this.new AnonymousClass1(this.$clockView, this.$viewModel, this.$systemPromptView, this.$displayId, this.$seedlingContainer, this.$dynamicIconContent, this.$eventAnimator, this.$animateStatusBarView, this.$statusBarContent, this.$endSideView, (Continuation) obj3);
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
                C01601 c01601 = new C01601(OplusHomeStatusBarViewBinderImpl.this, this.$clockView, this.$viewModel, this.$systemPromptView, this.$displayId, this.$seedlingContainer, this.$dynamicIconContent, this.$eventAnimator, this.$animateStatusBarView, this.$statusBarContent, this.$endSideView, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01601, this) == coroutine_suspended) {
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

    public static Unit $r8$lambda$o2XYSmXpyMoPrvpLkuGC5y_NW4M(Ref.ObjectRef objectRef, View view, OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, float f) {
        ((View) objectRef.element).setAlpha(f);
        boolean zAreEqual = Intrinsics.areEqual(objectRef.element, view);
        Lazy lazy = oplusHomeStatusBarViewBinderImpl.oplusFakeViewProvider;
        if (zAreEqual) {
            ArrayList<FakeType> fakeAlphaAnimateArray = ((OplusFakeViewProvider) lazy.get()).getFakeAlphaAnimateArray();
            int size = fakeAlphaAnimateArray.size();
            int i = 0;
            while (i < size) {
                FakeType fakeType = fakeAlphaAnimateArray.get(i);
                i++;
                FakeType fakeType2 = fakeType;
                ((OplusFakeViewProvider) oplusHomeStatusBarViewBinderImpl.oplusFakeViewProvider.get()).getSimpleFakeView(fakeType2).setAlpha(f);
                ((OplusFakeViewProvider) oplusHomeStatusBarViewBinderImpl.oplusFakeViewProvider.get()).getQsFakeView(fakeType2).setAlpha(f);
            }
        } else {
            OplusFakeViewProvider oplusFakeViewProvider = (OplusFakeViewProvider) lazy.get();
            FakeType fakeType3 = FakeType.FAKE_SYSTEM_ICON;
            oplusFakeViewProvider.getQsFakeView(fakeType3).setAlpha(f);
            ((OplusFakeViewProvider) oplusHomeStatusBarViewBinderImpl.oplusFakeViewProvider.get()).getSimpleFakeView(fakeType3).setAlpha(f);
        }
        return Unit.INSTANCE;
    }

    public OplusHomeStatusBarViewBinderImpl(int i, Context context, StatusBarWindowStatePerDisplayRepository statusBarWindowStatePerDisplayRepository, HomeStatusBarViewBinderImpl homeStatusBarViewBinderImpl, Lazy lazy, HomeStatusBarComponentsRepository homeStatusBarComponentsRepository, ShouldShowForDisplay shouldShowForDisplay) {
        this.context = context;
        this.statusBarWindowStateRepository = statusBarWindowStatePerDisplayRepository;
        this.homeStatusBarViewBinder = homeStatusBarViewBinderImpl;
        this.oplusFakeViewProvider = lazy;
        this.homeStatusBarComponentRepository = homeStatusBarComponentsRepository;
        this.shouldShowForDisplay = shouldShowForDisplay;
    }

    public static final Object access$bindClock(OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, StatClock statClock, Continuation continuation) {
        statClock.setBinding(oplusHomeStatusBarViewBinderImpl.shouldShowForDisplay.getShowSeedling() ? new StatClockViewBindingImpl(oplusHomeStatusBarViewModel) : null);
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new OplusHomeStatusBarViewBinderImpl$bindClock$2(oplusHomeStatusBarViewBinderImpl, oplusHomeStatusBarViewModel, statClock, null), continuation);
        return objCoroutineScope == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.binder.OplusHomeStatusBarViewBinder
    public void bind(int i, View view, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, Function1 function1, Function1 function2, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener) {
        View viewRequireViewById = view.requireViewById(2131364693);
        View viewRequireViewById2 = view.requireViewById(2131365117);
        View viewRequireViewById3 = view.requireViewById(2131362993);
        View viewRequireViewById4 = view.requireViewById(2131365363);
        View viewRequireViewById5 = view.requireViewById(2131365365);
        StatClock statClock = (StatClock) view.requireViewById(2131362566);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = viewRequireViewById5;
        Resources resources = this.context.getResources();
        OplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0 oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0 = new OplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0();
        oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0.f$0 = objectRef;
        oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0.f$1 = viewRequireViewById4;
        oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0.f$2 = this;
        VarHandle.storeStoreFence();
        OplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda1 oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda1 = new OplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda1();
        oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda1.f$0 = objectRef;
        VarHandle.storeStoreFence();
        RepeatWhenAttachedKt.repeatWhenAttached$default(view, (CoroutineContext) null, new AnonymousClass1(statClock, oplusHomeStatusBarViewModel, viewRequireViewById, i, viewRequireViewById2, viewRequireViewById3, new StatusBarSystemEventDefaultAnimator(resources, oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda0, oplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda1, false), objectRef, viewRequireViewById4, viewRequireViewById5, null), 3);
    }

    public void bind(int i, View view, HomeStatusBarViewModel homeStatusBarViewModel, Function1 function1, Function1 function2, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener) {
        this.homeStatusBarViewBinder.bind(i, view, homeStatusBarViewModel, function1, function2, statusBarVisibilityChangeListener);
        if (homeStatusBarViewModel instanceof OplusHomeStatusBarViewModel) {
            bind(i, view, (OplusHomeStatusBarViewModel) homeStatusBarViewModel, function1, function2, statusBarVisibilityChangeListener);
        }
    }
}
