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
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailFooterViewModel;
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
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailFooterBinder {
    private static final String TAG = "QSDetailFooterBinder";
    public static final QSDetailFooterBinder INSTANCE = new QSDetailFooterBinder();
    private static final DisposableHandle stubDisposableHandle = QSDetailFooterBinder$stubDisposableHandle$1.INSTANCE;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailFooterBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ DetailAdapter $adapter;
        final /* synthetic */ View $detailContainer;
        final /* synthetic */ View $detailFooter;
        final /* synthetic */ QSDetailRepository $detailRepository;
        final /* synthetic */ QSDetailFooterViewModel.Factory $viewModelFactory;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailFooterBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00701 extends SuspendLambda implements Function2 {
            final /* synthetic */ DetailAdapter $adapter;
            final /* synthetic */ View $detailContainer;
            final /* synthetic */ View $detailFooter;
            final /* synthetic */ QSDetailRepository $detailRepository;
            final /* synthetic */ QSDetailFooterViewModel.Factory $viewModelFactory;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailFooterBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00711 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailFooter;
                final /* synthetic */ QSDetailFooterViewModel $viewModel;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00711(View view, QSDetailFooterViewModel qSDetailFooterViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailFooter = view;
                    this.$viewModel = qSDetailFooterViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    C00711 c00711 = new C00711(this.$detailFooter, this.$viewModel, continuation);
                    c00711.L$0 = obj;
                    return c00711;
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
                    QSDetailFooterBinder qSDetailFooterBinder = QSDetailFooterBinder.INSTANCE;
                    View view = this.$detailFooter;
                    QSDetailFooterViewModel qSDetailFooterViewModel = this.$viewModel;
                    qSDetailFooterBinder.getClass();
                    View viewFindViewById = view.findViewById(2131364726);
                    if (viewFindViewById == null) {
                        LogUtil.i$default(LogUtil.TAG_QS, QSDetailFooterBinder.TAG, "collectDividerState: footerDivider is null!", null, 8);
                    } else {
                        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new QSDetailFooterBinder$collectFooterDividerState$1(viewFindViewById, qSDetailFooterViewModel, null), 3);
                        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new QSDetailFooterBinder$collectFooterDividerState$2(viewFindViewById, qSDetailFooterViewModel, null), 3);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailFooterBinder$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $detailContainer;
                final /* synthetic */ QSDetailFooterViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(View view, QSDetailFooterViewModel qSDetailFooterViewModel, Continuation continuation) {
                    super(2, continuation);
                    this.$detailContainer = view;
                    this.$viewModel = qSDetailFooterViewModel;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$detailContainer, this.$viewModel, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                public final Object invokeSuspend(Object obj) {
                    Object objCollect;
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        QSDetailFooterBinder qSDetailFooterBinder = QSDetailFooterBinder.INSTANCE;
                        View view = this.$detailContainer;
                        QSDetailFooterViewModel qSDetailFooterViewModel = this.$viewModel;
                        this.label = 1;
                        qSDetailFooterBinder.getClass();
                        TextView textView = (TextView) view.findViewById(R.id.button2);
                        if (textView == null) {
                            LogUtil.i$default(LogUtil.TAG_QS, QSDetailFooterBinder.TAG, "collectSettingsBtnState: settingsButton is null!", null, 8);
                            objCollect = Unit.INSTANCE;
                        } else {
                            QSDetailFooterBinder$collectSettingsBtnState$2 qSDetailFooterBinder$collectSettingsBtnState$2 = new QSDetailFooterBinder$collectSettingsBtnState$2();
                            qSDetailFooterBinder$collectSettingsBtnState$2.$viewModel = qSDetailFooterViewModel;
                            qSDetailFooterBinder$collectSettingsBtnState$2.$detailContainer = view;
                            VarHandle.storeStoreFence();
                            textView.setOnClickListener(qSDetailFooterBinder$collectSettingsBtnState$2);
                            qSDetailFooterViewModel.setDetailSettingsButton(textView);
                            Flow settingsBtnTextColor = qSDetailFooterViewModel.getSettingsBtnTextColor();
                            QSDetailFooterBinder$collectSettingsBtnState$3 qSDetailFooterBinder$collectSettingsBtnState$3 = new QSDetailFooterBinder$collectSettingsBtnState$3(0);
                            qSDetailFooterBinder$collectSettingsBtnState$3.$settingsButton = textView;
                            VarHandle.storeStoreFence();
                            objCollect = settingsBtnTextColor.collect(qSDetailFooterBinder$collectSettingsBtnState$3, this);
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
            public C00701(QSDetailFooterViewModel.Factory factory, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, View view, View view2, Continuation continuation) {
                super(2, continuation);
                this.$viewModelFactory = factory;
                this.$adapter = detailAdapter;
                this.$detailRepository = qSDetailRepository;
                this.$detailFooter = view;
                this.$detailContainer = view2;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00701 c00701 = new C00701(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailFooter, this.$detailContainer, continuation);
                c00701.L$0 = obj;
                return c00701;
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
                QSDetailFooterViewModel qSDetailFooterViewModelCreate = this.$viewModelFactory.create(this.$adapter, this.$detailRepository);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00711(this.$detailFooter, qSDetailFooterViewModelCreate, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$detailContainer, qSDetailFooterViewModelCreate, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QSDetailFooterViewModel.Factory factory, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, View view, View view2, Continuation continuation) {
            super(3, continuation);
            this.$viewModelFactory = factory;
            this.$adapter = detailAdapter;
            this.$detailRepository = qSDetailRepository;
            this.$detailFooter = view;
            this.$detailContainer = view2;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailFooter, this.$detailContainer, (Continuation) obj3);
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
                C00701 c00701 = new C00701(this.$viewModelFactory, this.$adapter, this.$detailRepository, this.$detailFooter, this.$detailContainer, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00701, this) == coroutine_suspended) {
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

    public final DisposableHandle bind(View view, DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, QSDetailFooterViewModel.Factory factory) {
        DisposableHandle disposableHandleRepeatWhenAttached$default;
        View viewFindViewById = view.findViewById(2131362833);
        View viewFindViewById2 = view.findViewById(2131362831);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ChildHelper$.ExternalSyntheticOutline0.m(viewFindViewById, "bind: detailFooter="), null, 8);
        return (viewFindViewById == null || (disposableHandleRepeatWhenAttached$default = RepeatWhenAttachedKt.repeatWhenAttached$default(viewFindViewById, (CoroutineContext) null, new AnonymousClass1(factory, detailAdapter, qSDetailRepository, viewFindViewById, viewFindViewById2, null), 3)) == null) ? stubDisposableHandle : disposableHandleRepeatWhenAttached$default;
    }
}
