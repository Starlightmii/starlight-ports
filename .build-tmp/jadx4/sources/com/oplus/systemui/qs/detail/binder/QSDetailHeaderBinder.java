package com.oplus.systemui.qs.detail.binder;

import android.R;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.coui.appcompat.couiswitch.COUISwitch;
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailHeaderViewModel;
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
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailHeaderBinder {
    private static final String TAG = "QSDetailHeaderBinder";
    public static final QSDetailHeaderBinder INSTANCE = new QSDetailHeaderBinder();
    private static final DisposableHandle stubDisposableHandle = QSDetailHeaderBinder$stubDisposableHandle$1.INSTANCE;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailHeaderBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ DetailAdapter $adapter;
        final /* synthetic */ View $detailHeader;
        final /* synthetic */ QSDetailRepository $detailRepository;
        final /* synthetic */ QSDetailHeaderViewModel.Factory $viewModelFactory;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailHeaderBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00721 extends SuspendLambda implements Function2 {
            final /* synthetic */ DetailAdapter $adapter;
            final /* synthetic */ View $detailHeader;
            final /* synthetic */ QSDetailRepository $detailRepository;
            final /* synthetic */ QSDetailHeaderViewModel.Factory $viewModelFactory;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailHeaderBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00731 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailHeader;
                final /* synthetic */ QSDetailHeaderViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00731(View view, QSDetailHeaderViewModel qSDetailHeaderViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailHeader = view;
                    this.$viewModel = qSDetailHeaderViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00731(this.$detailHeader, this.$viewModel, continuation);
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
                        QSDetailHeaderBinder qSDetailHeaderBinder = QSDetailHeaderBinder.INSTANCE;
                        View view = this.$detailHeader;
                        QSDetailHeaderViewModel qSDetailHeaderViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailHeaderBinder.getClass();
                        TextView textView = (TextView) view.findViewById(R.id.title);
                        if (textView == null) {
                            LogUtil.i$default(LogUtil.TAG_QS, QSDetailHeaderBinder.TAG, "collectTitleState: detail headerTitle is null!", null, 8);
                            objCollect = Unit.INSTANCE;
                        } else {
                            textView.setText(qSDetailHeaderViewModel.getTitle());
                            Flow titleTextColor = qSDetailHeaderViewModel.getTitleTextColor();
                            QSDetailHeaderBinder$collectTitleState$2 qSDetailHeaderBinder$collectTitleState$2 = new QSDetailHeaderBinder$collectTitleState$2();
                            qSDetailHeaderBinder$collectTitleState$2.$headerTitle = textView;
                            VarHandle.storeStoreFence();
                            objCollect = titleTextColor.collect(qSDetailHeaderBinder$collectTitleState$2, this);
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

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailHeaderBinder$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailHeader;
                final /* synthetic */ QSDetailHeaderViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(View view, QSDetailHeaderViewModel qSDetailHeaderViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailHeader = view;
                    this.$viewModel = qSDetailHeaderViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$detailHeader, this.$viewModel, continuation);
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
                        QSDetailHeaderBinder qSDetailHeaderBinder = QSDetailHeaderBinder.INSTANCE;
                        View view = this.$detailHeader;
                        QSDetailHeaderViewModel qSDetailHeaderViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailHeaderBinder.getClass();
                        COUISwitch cOUISwitchFindViewById = view.findViewById(2131362846);
                        if (cOUISwitchFindViewById == null) {
                            LogUtil.i$default(LogUtil.TAG_QS, QSDetailHeaderBinder.TAG, "collectSwitchState: detail switch is null!", null, 8);
                            objCollect = Unit.INSTANCE;
                        } else {
                            cOUISwitchFindViewById.setLoadingStyle(true);
                            QSDetailHeaderBinder$collectSwitchState$2 qSDetailHeaderBinder$collectSwitchState$2 = new QSDetailHeaderBinder$collectSwitchState$2();
                            qSDetailHeaderBinder$collectSwitchState$2.$viewModel = qSDetailHeaderViewModel;
                            qSDetailHeaderBinder$collectSwitchState$2.$switch = cOUISwitchFindViewById;
                            VarHandle.storeStoreFence();
                            cOUISwitchFindViewById.setOnLoadingStateChangedListener(qSDetailHeaderBinder$collectSwitchState$2);
                            QSDetailHeaderBinder$collectSwitchState$3 qSDetailHeaderBinder$collectSwitchState$3 = new QSDetailHeaderBinder$collectSwitchState$3();
                            qSDetailHeaderBinder$collectSwitchState$3.$viewModel = qSDetailHeaderViewModel;
                            qSDetailHeaderBinder$collectSwitchState$3.$switch = cOUISwitchFindViewById;
                            VarHandle.storeStoreFence();
                            cOUISwitchFindViewById.setOnClickListener(qSDetailHeaderBinder$collectSwitchState$3);
                            Flow flowOnCompletion = FlowKt.onCompletion(qSDetailHeaderViewModel.getSwitchState(), new QSDetailHeaderBinder$collectSwitchState$4(cOUISwitchFindViewById, null));
                            QSDetailHeaderBinder$collectSwitchState$5 qSDetailHeaderBinder$collectSwitchState$5 = new QSDetailHeaderBinder$collectSwitchState$5();
                            qSDetailHeaderBinder$collectSwitchState$5.$detailHeader = view;
                            qSDetailHeaderBinder$collectSwitchState$5.$switch = cOUISwitchFindViewById;
                            qSDetailHeaderBinder$collectSwitchState$5.$viewModel = qSDetailHeaderViewModel;
                            VarHandle.storeStoreFence();
                            objCollect = flowOnCompletion.collect(qSDetailHeaderBinder$collectSwitchState$5, this);
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00721(QSDetailHeaderViewModel.Factory factory, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModelFactory = factory;
                this.$adapter = detailAdapter;
                this.$detailRepository = qSDetailRepository;
                this.$detailHeader = view;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00721 c00721 = new C00721(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailHeader, continuation);
                c00721.L$0 = obj;
                return c00721;
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
                QSDetailHeaderViewModel qSDetailHeaderViewModelCreate = this.$viewModelFactory.create(this.$adapter, this.$detailRepository);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00731(this.$detailHeader, qSDetailHeaderViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$detailHeader, qSDetailHeaderViewModelCreate, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QSDetailHeaderViewModel.Factory factory, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, View view, Continuation continuation) {
            super(3, continuation);
            this.$viewModelFactory = factory;
            this.$adapter = detailAdapter;
            this.$detailRepository = qSDetailRepository;
            this.$detailHeader = view;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailHeader, (Continuation) obj3);
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
                C00721 c00721 = new C00721(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailHeader, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00721, this) == coroutine_suspended) {
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

    public final DisposableHandle bind(View view, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, QSDetailHeaderViewModel.Factory factory) {
        DisposableHandle disposableHandleRepeatWhenAttached$default;
        View viewFindViewById = view.findViewById(2131362835);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ChildHelper$.ExternalSyntheticOutline0.m(viewFindViewById, "bind: detailHeader="), null, 8);
        return (viewFindViewById == null || (disposableHandleRepeatWhenAttached$default = RepeatWhenAttachedKt.repeatWhenAttached$default(viewFindViewById, (CoroutineContext) null, new AnonymousClass1(factory, detailAdapter, qSDetailRepository, viewFindViewById, null), 3)) == null) ? stubDisposableHandle : disposableHandleRepeatWhenAttached$default;
    }

    public final boolean isFirstShow(View view) {
        Object tag = view.getTag(2131364395);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public final void setFirstShow(View view, boolean z) {
        view.setTag(2131364395, Boolean.valueOf(z));
    }
}
