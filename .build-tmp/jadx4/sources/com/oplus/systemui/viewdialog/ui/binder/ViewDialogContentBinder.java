package com.oplus.systemui.viewdialog.ui.binder;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContainerViewModel;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
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
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContentBinder {
    private static final String TAG = "ViewDialogContentBinder";
    public static final ViewDialogContentBinder INSTANCE = new ViewDialogContentBinder();
    public static final IOplusGcSupressionExt gcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);

    /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContentBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ ViewDialogAdapter $adapter;
        final /* synthetic */ ViewDialogContainerViewModel $containerViewModel;
        final /* synthetic */ View $content;
        final /* synthetic */ ViewDialogContentViewModel.Factory $viewModelFactory;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContentBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01731 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewDialogAdapter $adapter;
            final /* synthetic */ ViewDialogContainerViewModel $containerViewModel;
            final /* synthetic */ View $content;
            final /* synthetic */ ViewDialogContentViewModel.Factory $viewModelFactory;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContentBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01741 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewDialogAnimationManager $animationManager;
                final /* synthetic */ View $content;
                final /* synthetic */ ViewDialogContentViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01741(View view, ViewDialogAnimationManager viewDialogAnimationManager, ViewDialogContentViewModel viewDialogContentViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$content = view;
                    this.$animationManager = viewDialogAnimationManager;
                    this.$viewModel = viewDialogContentViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01741(this.$content, this.$animationManager, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContentBinder viewDialogContentBinder = ViewDialogContentBinder.INSTANCE;
                        View view = this.$content;
                        ViewDialogAnimationManager viewDialogAnimationManager = this.$animationManager;
                        ViewDialogContentViewModel viewDialogContentViewModel = this.$viewModel;
                        this.label = 1;
                        viewDialogContentBinder.getClass();
                        Object objCollect = FlowKt.collect(FlowConflatedKt.conflatedCallbackFlow(new ViewDialogContentBinder$collectViewLifecycle$2(view, viewDialogAnimationManager, viewDialogContentViewModel, null)), this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContentBinder$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewDialogAnimationManager $animationManager;
                final /* synthetic */ ViewDialogContentViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(ViewDialogAnimationManager viewDialogAnimationManager, ViewDialogContentViewModel viewDialogContentViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$animationManager = viewDialogAnimationManager;
                    this.$viewModel = viewDialogContentViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$animationManager, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContentBinder viewDialogContentBinder = ViewDialogContentBinder.INSTANCE;
                        ViewDialogAnimationManager viewDialogAnimationManager = this.$animationManager;
                        ViewDialogContentViewModel viewDialogContentViewModel = this.$viewModel;
                        this.label = 1;
                        if (ViewDialogContentBinder.access$collectDismissDialogEvent(viewDialogContentBinder, viewDialogAnimationManager, viewDialogContentViewModel, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContentBinder$bind$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewDialogContentViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(ViewDialogContentViewModel viewDialogContentViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = viewDialogContentViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContentBinder viewDialogContentBinder = ViewDialogContentBinder.INSTANCE;
                        ViewDialogContentViewModel viewDialogContentViewModel = this.$viewModel;
                        this.label = 1;
                        viewDialogContentBinder.getClass();
                        Object objCollect = FlowKt.collect(viewDialogContentViewModel.getBackActionHandler(), this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.binder.ViewDialogContentBinder$bind$1$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewDialogAnimationManager $animationManager;
                final /* synthetic */ ViewDialogContentViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(ViewDialogAnimationManager viewDialogAnimationManager, ViewDialogContentViewModel viewDialogContentViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$animationManager = viewDialogAnimationManager;
                    this.$viewModel = viewDialogContentViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$animationManager, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ViewDialogContentBinder viewDialogContentBinder = ViewDialogContentBinder.INSTANCE;
                        ViewDialogAnimationManager viewDialogAnimationManager = this.$animationManager;
                        ViewDialogContentViewModel viewDialogContentViewModel = this.$viewModel;
                        this.label = 1;
                        if (ViewDialogContentBinder.access$collectCancelAnimationEvent(viewDialogContentBinder, viewDialogAnimationManager, viewDialogContentViewModel, this) == coroutine_suspended) {
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
            public C01731(ViewDialogAdapter viewDialogAdapter, View view, ViewDialogContentViewModel.Factory factory, ViewDialogContainerViewModel viewDialogContainerViewModel, Continuation continuation) {
                super(2, continuation);
                this.$adapter = viewDialogAdapter;
                this.$content = view;
                this.$viewModelFactory = factory;
                this.$containerViewModel = viewDialogContainerViewModel;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C01731 c01731 = new C01731(this.$adapter, this.$content, this.$viewModelFactory, this.$containerViewModel, continuation);
                c01731.L$0 = obj;
                return c01731;
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
                ViewDialogAnimationManager viewDialogAnimationManagerCreateAnimationManager = this.$adapter.createAnimationManager(this.$content);
                if (viewDialogAnimationManagerCreateAnimationManager == null) {
                    viewDialogAnimationManagerCreateAnimationManager = new DetailAnimationManager(this.$content);
                }
                ViewDialogContentViewModel viewDialogContentViewModelCreate = this.$viewModelFactory.create(coroutineScope, this.$adapter, this.$containerViewModel, ViewDialogContentBinder.gcSupressionExt);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01741(this.$content, viewDialogAnimationManagerCreateAnimationManager, viewDialogContentViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(viewDialogAnimationManagerCreateAnimationManager, viewDialogContentViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(viewDialogContentViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(viewDialogAnimationManagerCreateAnimationManager, viewDialogContentViewModelCreate, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ViewDialogAdapter viewDialogAdapter, View view, ViewDialogContentViewModel.Factory factory, ViewDialogContainerViewModel viewDialogContainerViewModel, Continuation continuation) {
            super(3, continuation);
            this.$adapter = viewDialogAdapter;
            this.$content = view;
            this.$viewModelFactory = factory;
            this.$containerViewModel = viewDialogContainerViewModel;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$adapter, this.$content, this.$viewModelFactory, this.$containerViewModel, (Continuation) obj3);
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
                C01731 c01731 = new C01731(this.$adapter, this.$content, this.$viewModelFactory, this.$containerViewModel, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01731, this) == coroutine_suspended) {
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

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectCancelAnimationEvent(ViewDialogContentBinder viewDialogContentBinder, ViewDialogAnimationManager viewDialogAnimationManager, ViewDialogContentViewModel viewDialogContentViewModel, ContinuationImpl continuationImpl) {
        ViewDialogContentBinder$collectCancelAnimationEvent$1 viewDialogContentBinder$collectCancelAnimationEvent$1;
        viewDialogContentBinder.getClass();
        if (continuationImpl instanceof ViewDialogContentBinder$collectCancelAnimationEvent$1) {
            viewDialogContentBinder$collectCancelAnimationEvent$1 = (ViewDialogContentBinder$collectCancelAnimationEvent$1) continuationImpl;
            int i = viewDialogContentBinder$collectCancelAnimationEvent$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                viewDialogContentBinder$collectCancelAnimationEvent$1.label = i - Integer.MIN_VALUE;
            } else {
                viewDialogContentBinder$collectCancelAnimationEvent$1 = new ViewDialogContentBinder$collectCancelAnimationEvent$1(viewDialogContentBinder, continuationImpl);
            }
        } else {
            viewDialogContentBinder$collectCancelAnimationEvent$1 = new ViewDialogContentBinder$collectCancelAnimationEvent$1(viewDialogContentBinder, continuationImpl);
        }
        Object obj = viewDialogContentBinder$collectCancelAnimationEvent$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = viewDialogContentBinder$collectCancelAnimationEvent$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow cancelAnimationActionEvent = viewDialogContentViewModel.getCancelAnimationActionEvent();
            ViewDialogContentBinder$collectCancelAnimationEvent$2 viewDialogContentBinder$collectCancelAnimationEvent$2 = new ViewDialogContentBinder$collectCancelAnimationEvent$2();
            viewDialogContentBinder$collectCancelAnimationEvent$2.$animationManager = viewDialogAnimationManager;
            VarHandle.storeStoreFence();
            viewDialogContentBinder$collectCancelAnimationEvent$1.L$0 = SpillingKt.nullOutSpilledVariable(viewDialogAnimationManager);
            viewDialogContentBinder$collectCancelAnimationEvent$1.L$1 = SpillingKt.nullOutSpilledVariable(viewDialogContentViewModel);
            viewDialogContentBinder$collectCancelAnimationEvent$1.label = 1;
            if (cancelAnimationActionEvent.collect(viewDialogContentBinder$collectCancelAnimationEvent$2, viewDialogContentBinder$collectCancelAnimationEvent$1) == coroutine_suspended) {
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
    public static final Object access$collectDismissDialogEvent(ViewDialogContentBinder viewDialogContentBinder, ViewDialogAnimationManager viewDialogAnimationManager, ViewDialogContentViewModel viewDialogContentViewModel, ContinuationImpl continuationImpl) {
        ViewDialogContentBinder$collectDismissDialogEvent$1 viewDialogContentBinder$collectDismissDialogEvent$1;
        viewDialogContentBinder.getClass();
        if (continuationImpl instanceof ViewDialogContentBinder$collectDismissDialogEvent$1) {
            viewDialogContentBinder$collectDismissDialogEvent$1 = (ViewDialogContentBinder$collectDismissDialogEvent$1) continuationImpl;
            int i = viewDialogContentBinder$collectDismissDialogEvent$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                viewDialogContentBinder$collectDismissDialogEvent$1.label = i - Integer.MIN_VALUE;
            } else {
                viewDialogContentBinder$collectDismissDialogEvent$1 = new ViewDialogContentBinder$collectDismissDialogEvent$1(viewDialogContentBinder, continuationImpl);
            }
        } else {
            viewDialogContentBinder$collectDismissDialogEvent$1 = new ViewDialogContentBinder$collectDismissDialogEvent$1(viewDialogContentBinder, continuationImpl);
        }
        Object obj = viewDialogContentBinder$collectDismissDialogEvent$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = viewDialogContentBinder$collectDismissDialogEvent$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow dismissDialogEvent = viewDialogContentViewModel.getDismissDialogEvent();
            ViewDialogContentBinder$collectDismissDialogEvent$2 viewDialogContentBinder$collectDismissDialogEvent$2 = new ViewDialogContentBinder$collectDismissDialogEvent$2();
            viewDialogContentBinder$collectDismissDialogEvent$2.$animationManager = viewDialogAnimationManager;
            viewDialogContentBinder$collectDismissDialogEvent$2.$viewModel = viewDialogContentViewModel;
            VarHandle.storeStoreFence();
            viewDialogContentBinder$collectDismissDialogEvent$1.L$0 = SpillingKt.nullOutSpilledVariable(viewDialogAnimationManager);
            viewDialogContentBinder$collectDismissDialogEvent$1.L$1 = SpillingKt.nullOutSpilledVariable(viewDialogContentViewModel);
            viewDialogContentBinder$collectDismissDialogEvent$1.label = 1;
            if (dismissDialogEvent.collect(viewDialogContentBinder$collectDismissDialogEvent$2, viewDialogContentBinder$collectDismissDialogEvent$1) == coroutine_suspended) {
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

    public final DisposableHandle bind(View view, ViewDialogAdapter viewDialogAdapter, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "bind: viewTag=" + viewDialogAdapter.getViewTag() + ", content=" + view, null, 8);
        view.setVisibility(4);
        return RepeatWhenAttachedKt.repeatWhenAttached$default(view, Dispatchers.getMain().getImmediate(), new AnonymousClass1(viewDialogAdapter, view, factory, viewDialogContainerViewModel, null), 2);
    }
}
