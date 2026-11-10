package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailContainerViewModel;
import com.oplusos.systemui.common.inline.OplusQsInlineHostView;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailContainerBinder {
    private static final String TAG = "QSDetailContainerBinder";
    public static final QSDetailContainerBinder INSTANCE = new QSDetailContainerBinder();
    private static final DisposableHandle stubDisposableHandle = QSDetailContainerBinder$stubDisposableHandle$1.INSTANCE;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ DetailAdapter $adapter;
        final /* synthetic */ View $detailContainer;
        final /* synthetic */ QSDetailRepository $detailRepository;
        final /* synthetic */ QSDetailContainerViewModel.Factory $viewModelFactory;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00681 extends SuspendLambda implements Function2 {
            final /* synthetic */ DetailAdapter $adapter;
            final /* synthetic */ View $detailContainer;
            final /* synthetic */ QSDetailRepository $detailRepository;
            final /* synthetic */ QSDetailContainerViewModel.Factory $viewModelFactory;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00691 extends SuspendLambda implements Function2 {
                final /* synthetic */ QSDetailContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00691(QSDetailContainerViewModel qSDetailContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = qSDetailContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00691(this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        QSDetailContainerBinder qSDetailContainerBinder = QSDetailContainerBinder.INSTANCE;
                        QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailContainerBinder.getClass();
                        Object objCollect = FlowKt.collect(FlowConflatedKt.conflatedCallbackFlow(new QSDetailContainerBinder$collectLifecycleState$2(qSDetailContainerViewModel, null)), this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailContainer;
                final /* synthetic */ QSDetailContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(View view, QSDetailContainerViewModel qSDetailContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailContainer = view;
                    this.$viewModel = qSDetailContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$detailContainer, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    QSDetailContainerBinder qSDetailContainerBinder = QSDetailContainerBinder.INSTANCE;
                    View view = this.$detailContainer;
                    QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
                    qSDetailContainerBinder.getClass();
                    QSDetailContainerBinder$collectAccessibilityState$1 qSDetailContainerBinder$collectAccessibilityState$1 = new QSDetailContainerBinder$collectAccessibilityState$1();
                    qSDetailContainerBinder$collectAccessibilityState$1.$container = view;
                    qSDetailContainerBinder$collectAccessibilityState$1.$viewModel = qSDetailContainerViewModel;
                    VarHandle.storeStoreFence();
                    view.post(qSDetailContainerBinder$collectAccessibilityState$1);
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailContainer;
                final /* synthetic */ QSDetailContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(View view, QSDetailContainerViewModel qSDetailContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailContainer = view;
                    this.$viewModel = qSDetailContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$detailContainer, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object objCollect;
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        QSDetailContainerBinder qSDetailContainerBinder = QSDetailContainerBinder.INSTANCE;
                        View view = this.$detailContainer;
                        QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailContainerBinder.getClass();
                        TextView textView = (TextView) view.findViewById(2131362837);
                        if (textView == null) {
                            LogUtil.i$default(LogUtil.TAG_QS, QSDetailContainerBinder.TAG, "collectHintTextState: hintTextView is null!", null, 8);
                            objCollect = Unit.INSTANCE;
                        } else {
                            Flow updateConfigEvent = qSDetailContainerViewModel.getUpdateConfigEvent();
                            QSDetailContainerBinder$collectHintTextState$2 qSDetailContainerBinder$collectHintTextState$2 = new QSDetailContainerBinder$collectHintTextState$2();
                            qSDetailContainerBinder$collectHintTextState$2.$viewModel = qSDetailContainerViewModel;
                            qSDetailContainerBinder$collectHintTextState$2.$hintTextView = textView;
                            VarHandle.storeStoreFence();
                            objCollect = updateConfigEvent.collect(qSDetailContainerBinder$collectHintTextState$2, this);
                            if (objCollect != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                objCollect = Unit.INSTANCE;
                            }
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

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailContainer;
                final /* synthetic */ QSDetailContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(View view, QSDetailContainerViewModel qSDetailContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailContainer = view;
                    this.$viewModel = qSDetailContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$detailContainer, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        QSDetailContainerBinder qSDetailContainerBinder = QSDetailContainerBinder.INSTANCE;
                        View view = this.$detailContainer;
                        QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailContainerBinder.getClass();
                        TileDrawableWrapper tileDrawableWrapper = new TileDrawableWrapper(null);
                        view.setBackground(tileDrawableWrapper);
                        Object objCollect = FlowKt.collect(FlowKt.combine(qSDetailContainerViewModel.getBackgroundBuilder(), qSDetailContainerViewModel.getBackgroundOutline(), new QSDetailContainerBinder$collectBackgroundState$2(tileDrawableWrapper, view, null)), this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1$5, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailContainer;
                final /* synthetic */ QSDetailContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(View view, QSDetailContainerViewModel qSDetailContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailContainer = view;
                    this.$viewModel = qSDetailContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.$detailContainer, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        QSDetailContainerBinder qSDetailContainerBinder = QSDetailContainerBinder.INSTANCE;
                        View view = this.$detailContainer;
                        QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailContainerBinder.getClass();
                        view.setPadding(0, 0, 0, qSDetailContainerViewModel.getPaddingBottom(view.getResources()));
                        Flow marginLayoutParams = qSDetailContainerViewModel.getMarginLayoutParams();
                        QSDetailContainerBinder$collectLayoutParamsState$2 qSDetailContainerBinder$collectLayoutParamsState$2 = new QSDetailContainerBinder$collectLayoutParamsState$2();
                        qSDetailContainerBinder$collectLayoutParamsState$2.$container = view;
                        VarHandle.storeStoreFence();
                        Object objCollect = marginLayoutParams.collect(qSDetailContainerBinder$collectLayoutParamsState$2, this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$bind$1$1$6, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailContainer;
                final /* synthetic */ QSDetailContainerViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(View view, QSDetailContainerViewModel qSDetailContainerViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailContainer = view;
                    this.$viewModel = qSDetailContainerViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.$detailContainer, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object objCollect;
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        QSDetailContainerBinder qSDetailContainerBinder = QSDetailContainerBinder.INSTANCE;
                        View view = this.$detailContainer;
                        QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailContainerBinder.getClass();
                        OplusQsInlineHostView oplusQsInlineHostView = (OplusQsInlineHostView) view.findViewById(2131364773);
                        if (oplusQsInlineHostView == null || (objCollect = FlowKt.collect(FlowConflatedKt.conflatedCallbackFlow(new QSDetailContainerBinder$collectInlineViewRequest$2(oplusQsInlineHostView, qSDetailContainerViewModel, view, null)), this)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
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
            public C00681(QSDetailContainerViewModel.Factory factory, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModelFactory = factory;
                this.$adapter = detailAdapter;
                this.$detailRepository = qSDetailRepository;
                this.$detailContainer = view;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00681 c00681 = new C00681(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailContainer, continuation);
                c00681.L$0 = obj;
                return c00681;
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
                QSDetailContainerViewModel qSDetailContainerViewModelCreate = this.$viewModelFactory.create(this.$adapter, this.$detailRepository);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00691(qSDetailContainerViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$detailContainer, qSDetailContainerViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$detailContainer, qSDetailContainerViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.$detailContainer, qSDetailContainerViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.$detailContainer, qSDetailContainerViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(this.$detailContainer, qSDetailContainerViewModelCreate, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QSDetailContainerViewModel.Factory factory, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, View view, Continuation continuation) {
            super(3, continuation);
            this.$viewModelFactory = factory;
            this.$adapter = detailAdapter;
            this.$detailRepository = qSDetailRepository;
            this.$detailContainer = view;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailContainer, (Continuation) obj3);
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
                C00681 c00681 = new C00681(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailContainer, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00681, this) == coroutine_suspended) {
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

    public final DisposableHandle bind(View view, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, QSDetailContainerViewModel.Factory factory) {
        DisposableHandle disposableHandleRepeatWhenAttached$default;
        View viewFindViewById = view.findViewById(2131362831);
        return (viewFindViewById == null || (disposableHandleRepeatWhenAttached$default = RepeatWhenAttachedKt.repeatWhenAttached$default(viewFindViewById, Dispatchers.getMain().getImmediate(), new AnonymousClass1(factory, detailAdapter, qSDetailRepository, viewFindViewById, null), 2)) == null) ? stubDisposableHandle : disposableHandleRepeatWhenAttached$default;
    }
}
