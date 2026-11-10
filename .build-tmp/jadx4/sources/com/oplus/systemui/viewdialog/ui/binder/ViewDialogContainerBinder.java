package com.oplus.systemui.viewdialog.ui.binder;

import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContainerViewModel;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder {
    private static final String TAG = "ViewDialogContainerBinder";
    private static final MutableStateFlow _windowInsetsEnabled;
    private static final View.OnApplyWindowInsetsListener windowInsetsDisableListener;
    private static final StateFlow windowInsetsEnabled;
    private static final View.OnApplyWindowInsetsListener windowInsetsListener;
    public static final ViewDialogContainerBinder INSTANCE = new ViewDialogContainerBinder();
    private static final DisposableHandle stubDisposableHandle = ViewDialogContainerBinder$stubDisposableHandle$1.INSTANCE;

    /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ ViewGroup $container;
        final /* synthetic */ ViewDialogContentViewModel.Factory $contentViewModelFactory;
        final /* synthetic */ ViewDialogContainerViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01711 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewGroup $container;
            final /* synthetic */ ViewDialogContentViewModel.Factory $contentViewModelFactory;
            final /* synthetic */ ViewDialogContainerViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01721 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewGroup $container;
                final /* synthetic */ ViewDialogContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01721(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$container = viewGroup;
                    this.$viewModel = viewDialogContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01721(this.$container, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContainerBinder viewDialogContainerBinder = ViewDialogContainerBinder.INSTANCE;
                        ViewGroup viewGroup = this.$container;
                        ViewDialogContainerViewModel viewDialogContainerViewModel = this.$viewModel;
                        this.label = 1;
                        viewDialogContainerBinder.getClass();
                        Object objCollect = FlowKt.collect(FlowConflatedKt.conflatedCallbackFlow(new ViewDialogContainerBinder$collectViewLifecycle$2(viewGroup, viewDialogContainerViewModel, null)), this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewGroup $container;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(ViewGroup viewGroup, Continuation continuation) {
                    super(2, continuation);
                    this.$container = viewGroup;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$container, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContainerBinder viewDialogContainerBinder = ViewDialogContainerBinder.INSTANCE;
                        ViewGroup viewGroup = this.$container;
                        this.label = 1;
                        if (ViewDialogContainerBinder.access$collectInsetsRefresh(viewDialogContainerBinder, viewGroup, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder$bind$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewGroup $container;
                final /* synthetic */ ViewDialogContentViewModel.Factory $contentViewModelFactory;
                final /* synthetic */ ViewDialogContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory, Continuation continuation) {
                    super(2, continuation);
                    this.$container = viewGroup;
                    this.$viewModel = viewDialogContainerViewModel;
                    this.$contentViewModelFactory = factory;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$container, this.$viewModel, this.$contentViewModelFactory, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContainerBinder viewDialogContainerBinder = ViewDialogContainerBinder.INSTANCE;
                        ViewGroup viewGroup = this.$container;
                        ViewDialogContainerViewModel viewDialogContainerViewModel = this.$viewModel;
                        ViewDialogContentViewModel.Factory factory = this.$contentViewModelFactory;
                        this.label = 1;
                        if (ViewDialogContainerBinder.access$collectShowDialogEvent(viewDialogContainerBinder, viewGroup, viewDialogContainerViewModel, factory, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder$bind$1$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewGroup $container;
                final /* synthetic */ ViewDialogContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$container = viewGroup;
                    this.$viewModel = viewDialogContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$container, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContainerBinder viewDialogContainerBinder = ViewDialogContainerBinder.INSTANCE;
                        ViewGroup viewGroup = this.$container;
                        ViewDialogContainerViewModel viewDialogContainerViewModel = this.$viewModel;
                        this.label = 1;
                        if (ViewDialogContainerBinder.access$collectDismissDialogEvent(viewDialogContainerBinder, viewGroup, viewDialogContainerViewModel, this) == coroutine_suspended) {
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
            public C01711(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory, Continuation continuation) {
                super(2, continuation);
                this.$container = viewGroup;
                this.$viewModel = viewDialogContainerViewModel;
                this.$contentViewModelFactory = factory;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C01711 c01711 = new C01711(this.$container, this.$viewModel, this.$contentViewModelFactory, continuation);
                c01711.L$0 = obj;
                return c01711;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01721(this.$container, this.$viewModel, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$container, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$container, this.$viewModel, this.$contentViewModelFactory, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.$container, this.$viewModel, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory, Continuation continuation) {
            super(3, continuation);
            this.$container = viewGroup;
            this.$viewModel = viewDialogContainerViewModel;
            this.$contentViewModelFactory = factory;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$container, this.$viewModel, this.$contentViewModelFactory, (Continuation) obj3);
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
                C01711 c01711 = new C01711(this.$container, this.$viewModel, this.$contentViewModelFactory, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01711, this) == coroutine_suspended) {
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

    static {
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        _windowInsetsEnabled = MutableStateFlow;
        windowInsetsEnabled = FlowKt.asStateFlow(MutableStateFlow);
        windowInsetsListener = ViewDialogContainerBinder$windowInsetsListener$1.INSTANCE;
        windowInsetsDisableListener = ViewDialogContainerBinder$windowInsetsDisableListener$1.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectDismissDialogEvent(ViewDialogContainerBinder viewDialogContainerBinder, ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, ContinuationImpl continuationImpl) {
        ViewDialogContainerBinder$collectDismissDialogEvent$1 viewDialogContainerBinder$collectDismissDialogEvent$1;
        viewDialogContainerBinder.getClass();
        if (continuationImpl instanceof ViewDialogContainerBinder$collectDismissDialogEvent$1) {
            viewDialogContainerBinder$collectDismissDialogEvent$1 = (ViewDialogContainerBinder$collectDismissDialogEvent$1) continuationImpl;
            int i = viewDialogContainerBinder$collectDismissDialogEvent$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                viewDialogContainerBinder$collectDismissDialogEvent$1.label = i - Integer.MIN_VALUE;
            } else {
                viewDialogContainerBinder$collectDismissDialogEvent$1 = new ViewDialogContainerBinder$collectDismissDialogEvent$1(viewDialogContainerBinder, continuationImpl);
            }
        } else {
            viewDialogContainerBinder$collectDismissDialogEvent$1 = new ViewDialogContainerBinder$collectDismissDialogEvent$1(viewDialogContainerBinder, continuationImpl);
        }
        Object obj = viewDialogContainerBinder$collectDismissDialogEvent$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = viewDialogContainerBinder$collectDismissDialogEvent$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            MutableSharedFlow dismissDialogEvent = viewDialogContainerViewModel.getDismissDialogEvent();
            ViewDialogContainerBinder$collectDismissDialogEvent$2 viewDialogContainerBinder$collectDismissDialogEvent$2 = new ViewDialogContainerBinder$collectDismissDialogEvent$2();
            viewDialogContainerBinder$collectDismissDialogEvent$2.$container = viewGroup;
            viewDialogContainerBinder$collectDismissDialogEvent$2.$viewModel = viewDialogContainerViewModel;
            VarHandle.storeStoreFence();
            viewDialogContainerBinder$collectDismissDialogEvent$1.L$0 = SpillingKt.nullOutSpilledVariable(viewGroup);
            viewDialogContainerBinder$collectDismissDialogEvent$1.L$1 = SpillingKt.nullOutSpilledVariable(viewDialogContainerViewModel);
            viewDialogContainerBinder$collectDismissDialogEvent$1.label = 1;
            if (dismissDialogEvent.collect(viewDialogContainerBinder$collectDismissDialogEvent$2, viewDialogContainerBinder$collectDismissDialogEvent$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectInsetsRefresh(ViewDialogContainerBinder viewDialogContainerBinder, ViewGroup viewGroup, ContinuationImpl continuationImpl) {
        ViewDialogContainerBinder$collectInsetsRefresh$1 viewDialogContainerBinder$collectInsetsRefresh$1;
        viewDialogContainerBinder.getClass();
        if (continuationImpl instanceof ViewDialogContainerBinder$collectInsetsRefresh$1) {
            viewDialogContainerBinder$collectInsetsRefresh$1 = (ViewDialogContainerBinder$collectInsetsRefresh$1) continuationImpl;
            int i = viewDialogContainerBinder$collectInsetsRefresh$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                viewDialogContainerBinder$collectInsetsRefresh$1.label = i - Integer.MIN_VALUE;
            } else {
                viewDialogContainerBinder$collectInsetsRefresh$1 = new ViewDialogContainerBinder$collectInsetsRefresh$1(viewDialogContainerBinder, continuationImpl);
            }
        } else {
            viewDialogContainerBinder$collectInsetsRefresh$1 = new ViewDialogContainerBinder$collectInsetsRefresh$1(viewDialogContainerBinder, continuationImpl);
        }
        Object obj = viewDialogContainerBinder$collectInsetsRefresh$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = viewDialogContainerBinder$collectInsetsRefresh$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow stateFlow = windowInsetsEnabled;
            ViewDialogContainerBinder$collectInsetsRefresh$2 viewDialogContainerBinder$collectInsetsRefresh$2 = new ViewDialogContainerBinder$collectInsetsRefresh$2();
            viewDialogContainerBinder$collectInsetsRefresh$2.$container = viewGroup;
            VarHandle.storeStoreFence();
            viewDialogContainerBinder$collectInsetsRefresh$1.L$0 = SpillingKt.nullOutSpilledVariable(viewGroup);
            viewDialogContainerBinder$collectInsetsRefresh$1.label = 1;
            if (stateFlow.collect(viewDialogContainerBinder$collectInsetsRefresh$2, viewDialogContainerBinder$collectInsetsRefresh$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectShowDialogEvent(ViewDialogContainerBinder viewDialogContainerBinder, ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory, ContinuationImpl continuationImpl) {
        ViewDialogContainerBinder$collectShowDialogEvent$1 viewDialogContainerBinder$collectShowDialogEvent$1;
        viewDialogContainerBinder.getClass();
        if (continuationImpl instanceof ViewDialogContainerBinder$collectShowDialogEvent$1) {
            viewDialogContainerBinder$collectShowDialogEvent$1 = (ViewDialogContainerBinder$collectShowDialogEvent$1) continuationImpl;
            int i = viewDialogContainerBinder$collectShowDialogEvent$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                viewDialogContainerBinder$collectShowDialogEvent$1.label = i - Integer.MIN_VALUE;
            } else {
                viewDialogContainerBinder$collectShowDialogEvent$1 = new ViewDialogContainerBinder$collectShowDialogEvent$1(viewDialogContainerBinder, continuationImpl);
            }
        } else {
            viewDialogContainerBinder$collectShowDialogEvent$1 = new ViewDialogContainerBinder$collectShowDialogEvent$1(viewDialogContainerBinder, continuationImpl);
        }
        Object obj = viewDialogContainerBinder$collectShowDialogEvent$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = viewDialogContainerBinder$collectShowDialogEvent$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow showDialogEvent = viewDialogContainerViewModel.getShowDialogEvent();
            ViewDialogContainerBinder$collectShowDialogEvent$2 viewDialogContainerBinder$collectShowDialogEvent$2 = new ViewDialogContainerBinder$collectShowDialogEvent$2();
            viewDialogContainerBinder$collectShowDialogEvent$2.$container = viewGroup;
            viewDialogContainerBinder$collectShowDialogEvent$2.$viewModel = viewDialogContainerViewModel;
            viewDialogContainerBinder$collectShowDialogEvent$2.$contentViewModelFactory = factory;
            VarHandle.storeStoreFence();
            viewDialogContainerBinder$collectShowDialogEvent$1.L$0 = SpillingKt.nullOutSpilledVariable(viewGroup);
            viewDialogContainerBinder$collectShowDialogEvent$1.L$1 = SpillingKt.nullOutSpilledVariable(viewDialogContainerViewModel);
            viewDialogContainerBinder$collectShowDialogEvent$1.L$2 = SpillingKt.nullOutSpilledVariable(factory);
            viewDialogContainerBinder$collectShowDialogEvent$1.label = 1;
            if (showDialogEvent.collect(viewDialogContainerBinder$collectShowDialogEvent$2, viewDialogContainerBinder$collectShowDialogEvent$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    public final DisposableHandle bind(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory) {
        DisposableHandle disposableHandleRepeatWhenAttached$default;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "bind: container=" + viewGroup, null, 8);
        return (viewGroup == null || (disposableHandleRepeatWhenAttached$default = RepeatWhenAttachedKt.repeatWhenAttached$default(viewGroup, Dispatchers.getMain().getImmediate(), new AnonymousClass1(viewGroup, viewDialogContainerViewModel, factory, null), 2)) == null) ? stubDisposableHandle : disposableHandleRepeatWhenAttached$default;
    }

    public final StateFlow getWindowInsetsEnabled() {
        return windowInsetsEnabled;
    }
}
