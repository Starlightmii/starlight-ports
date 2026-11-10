package com.oplus.systemui.statusbar.icon.ui.binder;

import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel;
import com.oplus.systemui.utils.OplusContrastColorUtil;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconContainerViewBinder {
    public static final OplusNotificationIconContainerViewBinder INSTANCE = new OplusNotificationIconContainerViewBinder();

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconContainerViewBinder$bind$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ ConfigurationState $configuration;
        final /* synthetic */ int $displayId;
        final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
        final /* synthetic */ OplusNotificationIconAreaViewModel $oplusViewModel;
        final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconContainerViewBinder$bind$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ ConfigurationState $configuration;
            final /* synthetic */ int $displayId;
            final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
            final /* synthetic */ OplusNotificationIconAreaViewModel $oplusViewModel;
            final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
            final /* synthetic */ NotificationIconContainer $view;
            final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
            final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconContainerViewBinder$bind$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01172 extends SuspendLambda implements Function3 {
                final /* synthetic */ ContrastColorUtil $contrastColorUtil;
                final /* synthetic */ StateFlow $iconColors;
                final /* synthetic */ OplusContrastColorUtil $oplusContrastColorUtil;
                /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01172(StateFlow stateFlow, ContrastColorUtil contrastColorUtil, OplusContrastColorUtil oplusContrastColorUtil, Continuation continuation) {
                    super(3, continuation);
                    this.$iconColors = stateFlow;
                    this.$contrastColorUtil = contrastColorUtil;
                    this.$oplusContrastColorUtil = oplusContrastColorUtil;
                }

                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    C01172 c01172 = new C01172(this.$iconColors, this.$contrastColorUtil, this.$oplusContrastColorUtil, (Continuation) obj3);
                    c01172.L$0 = (StatusBarIconView) obj2;
                    return c01172.invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    StatusBarIconView statusBarIconView = (StatusBarIconView) this.L$0;
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        OplusNotificationIconContainerViewBinder oplusNotificationIconContainerViewBinder = OplusNotificationIconContainerViewBinder.INSTANCE;
                        StateFlow stateFlow = this.$iconColors;
                        this.$contrastColorUtil.getClass();
                        OplusContrastColorUtil oplusContrastColorUtil = this.$oplusContrastColorUtil;
                        oplusContrastColorUtil.getClass();
                        this.L$0 = SpillingKt.nullOutSpilledVariable(statusBarIconView);
                        this.label = 1;
                        oplusNotificationIconContainerViewBinder.getClass();
                        OplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1 oplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1 = new OplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1();
                        oplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1.$view$inlined = statusBarIconView;
                        oplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1.$oplusContrastColorUtil$inlined = oplusContrastColorUtil;
                        VarHandle.storeStoreFence();
                        Object objCollect = stateFlow.collect(oplusNotificationIconContainerViewBinder$bindIconColors$$inlined$collectTracingEach$1, this);
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
            public AnonymousClass1(int i, NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
                super(2, continuation);
                this.$view = notificationIconContainer;
                this.$viewModel = notificationIconContainerStatusBarViewModel;
                this.$displayId = i;
                this.$configuration = configurationState;
                this.$systemBarUtilsState = systemBarUtilsState;
                this.$oplusViewModel = oplusNotificationIconAreaViewModel;
                this.$viewStore = iconViewStore;
                this.$failureTracker = statusBarIconViewBindingFailureTracker;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                NotificationIconContainer notificationIconContainer = this.$view;
                NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel = this.$viewModel;
                int i = this.$displayId;
                ConfigurationState configurationState = this.$configuration;
                SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(i, notificationIconContainer, notificationIconContainerStatusBarViewModel, this.$oplusViewModel, configurationState, systemBarUtilsState, this.$failureTracker, this.$viewStore, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:26:0x0145, code lost:
            
                if (r0.bindIcons(r12, "statusbar", r14, r4, r5, r15, r3, r18) == r9) goto L27;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r19) {
                /*
                    Method dump skipped, instruction units count: 342
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconContainerViewBinder.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconContainerViewBinder$bind$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01182 extends SuspendLambda implements Function2 {
            final /* synthetic */ NotificationIconContainer $view;
            final /* synthetic */ NotificationIconContainerStatusBarViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01182(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, NotificationIconContainer notificationIconContainer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = notificationIconContainerStatusBarViewModel;
                this.$view = notificationIconContainer;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01182(this.$viewModel, this.$view, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    OplusNotificationIconContainerViewBinder oplusNotificationIconContainerViewBinder = OplusNotificationIconContainerViewBinder.INSTANCE;
                    Flow animationsEnabled = this.$viewModel.getAnimationsEnabled();
                    NotificationIconContainer notificationIconContainer = this.$view;
                    this.label = 1;
                    oplusNotificationIconContainerViewBinder.getClass();
                    OplusNotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1 oplusNotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1 = new OplusNotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1();
                    oplusNotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1.$receiver$inlined = notificationIconContainer;
                    VarHandle.storeStoreFence();
                    Object objCollect = animationsEnabled.collect(oplusNotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1, this);
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
        public AnonymousClass2(int i, NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
            super(2, continuation);
            this.$view = notificationIconContainer;
            this.$viewModel = notificationIconContainerStatusBarViewModel;
            this.$displayId = i;
            this.$configuration = configurationState;
            this.$systemBarUtilsState = systemBarUtilsState;
            this.$oplusViewModel = oplusNotificationIconAreaViewModel;
            this.$viewStore = iconViewStore;
            this.$failureTracker = statusBarIconViewBindingFailureTracker;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            NotificationIconContainer notificationIconContainer = this.$view;
            NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel = this.$viewModel;
            int i = this.$displayId;
            ConfigurationState configurationState = this.$configuration;
            SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(i, notificationIconContainer, notificationIconContainerStatusBarViewModel, this.$oplusViewModel, configurationState, systemBarUtilsState, this.$failureTracker, this.$viewStore, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
            NotificationIconContainer notificationIconContainer = this.$view;
            NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel = this.$viewModel;
            int i = this.$displayId;
            ConfigurationState configurationState = this.$configuration;
            SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(i, notificationIconContainer, notificationIconContainerStatusBarViewModel, this.$oplusViewModel, configurationState, systemBarUtilsState, this.$failureTracker, this.$viewStore, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01182(this.$viewModel, this.$view, null), 3);
            return Unit.INSTANCE;
        }
    }

    public final Object bind(int i, NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(i, notificationIconContainer, notificationIconContainerStatusBarViewModel, oplusNotificationIconAreaViewModel, configurationState, systemBarUtilsState, statusBarIconViewBindingFailureTracker, iconViewStore, null), continuation);
        return objCoroutineScope == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }
}
