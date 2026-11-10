package com.oplus.systemui.statusbar.icon.ui.binder;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.display.dagger.SystemUIDisplaySubcomponent;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.android.systemui.util.view.ViewUtilKt;
import com.oplus.systemui.statusbar.icon.ui.viewmodel.NotificationBadgeViewColorLookup;
import com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel;
import com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel$badgeViewColors$1$1;
import com.oplus.systemui.statusbar.widget.NotificationBadgeView;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationIconAreaBinder$bindWhileAttached$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $displayId;
    final /* synthetic */ NotificationBadgeView $notificationBadgeView;
    final /* synthetic */ NotificationIconContainer $view;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OplusNotificationIconAreaBinder this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ConfigurationState $configurationState;
        final /* synthetic */ int $displayId;
        final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        int label;
        final /* synthetic */ OplusNotificationIconAreaBinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i, NotificationIconContainer notificationIconContainer, OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder, ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
            super(2, continuation);
            this.$displayId = i;
            this.$view = notificationIconContainer;
            this.this$0 = oplusNotificationIconAreaBinder;
            this.$configurationState = configurationState;
            this.$systemBarUtilsState = systemBarUtilsState;
            this.$viewStore = iconViewStore;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$displayId, this.$view, this.this$0, this.$configurationState, this.$systemBarUtilsState, this.$viewStore, continuation);
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
                int i2 = this.$displayId;
                NotificationIconContainer notificationIconContainer = this.$view;
                NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel = this.this$0.viewModel;
                OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel = this.this$0.oplusViewModel;
                ConfigurationState configurationState = this.$configurationState;
                SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
                StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = this.this$0.failureTracker;
                NotificationIconContainerViewBinder.IconViewStore iconViewStore = this.$viewStore;
                this.label = 1;
                if (oplusNotificationIconContainerViewBinder.bind(i2, notificationIconContainer, notificationIconContainerStatusBarViewModel, oplusNotificationIconAreaViewModel, configurationState, systemBarUtilsState, statusBarIconViewBindingFailureTracker, iconViewStore, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainer $view;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusNotificationIconAreaBinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder, NotificationIconContainer notificationIconContainer, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusNotificationIconAreaBinder;
            this.$view = notificationIconContainer;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$view, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0058, code lost:
        
            if (((kotlinx.coroutines.flow.StateFlow) r8).collect(r2, r7) == r1) goto L15;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = r7.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r7.label
                r3 = 0
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L21
                if (r2 == r5) goto L1d
                if (r2 == r4) goto L19
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
                return r3
            L19:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5b
            L1d:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3d
            L21:
                kotlin.ResultKt.throwOnFailure(r8)
                com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder r8 = r7.this$0
                com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel r8 = com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder.access$getOplusViewModel$p(r8)
                kotlinx.coroutines.flow.Flow r8 = r8.getNotificationIconsViewVisibility()
                java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r2
                r7.label = r5
                java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.stateIn(r8, r0, r7)
                if (r8 != r1) goto L3d
                goto L5a
            L3d:
                kotlinx.coroutines.flow.StateFlow r8 = (kotlinx.coroutines.flow.StateFlow) r8
                com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1 r2 = new com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1
                com.android.systemui.statusbar.phone.NotificationIconContainer r5 = r7.$view
                r6 = 3
                r2.<init>(r6)
                r2.$notificationBadgeView = r5
                java.lang.invoke.VarHandle.storeStoreFence()
                java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r0
                r7.label = r4
                java.lang.Object r7 = r8.collect(r2, r7)
                if (r7 != r1) goto L5b
            L5a:
                return r1
            L5b:
                kotlin.text.Regex$.ExternalSyntheticBUOutline0.m$1()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationBadgeView $notificationBadgeView;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusNotificationIconAreaBinder this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ Object $notificationBadgeView;
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ AnonymousClass1(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        ((NotificationBadgeView) this.$notificationBadgeView).setVisibility(((Number) obj).intValue());
                        break;
                    case 1:
                        int iIntValue = ((Number) obj).intValue();
                        NotificationBadgeView notificationBadgeView = (NotificationBadgeView) this.$notificationBadgeView;
                        notificationBadgeView.setText(iIntValue);
                        notificationBadgeView.getCurrentLocale();
                        break;
                    case 2:
                        NotificationBadgeView notificationBadgeView2 = (NotificationBadgeView) this.$notificationBadgeView;
                        notificationBadgeView2.mBgPaint.setColor(((OplusNotificationIconAreaViewModel$badgeViewColors$1$1) ((NotificationBadgeViewColorLookup) obj)).iconColors(ViewUtilKt.viewBoundsOnScreen(notificationBadgeView2)));
                        notificationBadgeView2.invalidate();
                        break;
                    default:
                        ((NotificationIconContainer) this.$notificationBadgeView).setVisibility(((Number) obj).intValue());
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder, NotificationBadgeView notificationBadgeView, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusNotificationIconAreaBinder;
            this.$notificationBadgeView = notificationBadgeView;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, this.$notificationBadgeView, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0058, code lost:
        
            if (((kotlinx.coroutines.flow.StateFlow) r8).collect(r2, r7) == r1) goto L15;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = r7.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r7.label
                r3 = 0
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L21
                if (r2 == r5) goto L1d
                if (r2 == r4) goto L19
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
                return r3
            L19:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5b
            L1d:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3d
            L21:
                kotlin.ResultKt.throwOnFailure(r8)
                com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder r8 = r7.this$0
                com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel r8 = com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder.access$getOplusViewModel$p(r8)
                kotlinx.coroutines.flow.Flow r8 = r8.getNotificationBadgeViewVisibility()
                java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r2
                r7.label = r5
                java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.stateIn(r8, r0, r7)
                if (r8 != r1) goto L3d
                goto L5a
            L3d:
                kotlinx.coroutines.flow.StateFlow r8 = (kotlinx.coroutines.flow.StateFlow) r8
                com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1 r2 = new com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1
                com.oplus.systemui.statusbar.widget.NotificationBadgeView r5 = r7.$notificationBadgeView
                r6 = 0
                r2.<init>(r6)
                r2.$notificationBadgeView = r5
                java.lang.invoke.VarHandle.storeStoreFence()
                java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r0
                r7.label = r4
                java.lang.Object r7 = r8.collect(r2, r7)
                if (r7 != r1) goto L5b
            L5a:
                return r1
            L5b:
                kotlin.text.Regex$.ExternalSyntheticBUOutline0.m$1()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationBadgeView $notificationBadgeView;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusNotificationIconAreaBinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder, NotificationBadgeView notificationBadgeView, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusNotificationIconAreaBinder;
            this.$notificationBadgeView = notificationBadgeView;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, this.$notificationBadgeView, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0057, code lost:
        
            if (((kotlinx.coroutines.flow.StateFlow) r8).collect(r2, r7) == r1) goto L15;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = r7.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r7.label
                r3 = 0
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L21
                if (r2 == r5) goto L1d
                if (r2 == r4) goto L19
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
                return r3
            L19:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5a
            L1d:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3d
            L21:
                kotlin.ResultKt.throwOnFailure(r8)
                com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder r8 = r7.this$0
                com.oplus.systemui.statusbar.icon.ui.viewmodel.OplusNotificationIconAreaViewModel r8 = com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder.access$getOplusViewModel$p(r8)
                kotlinx.coroutines.flow.Flow r8 = r8.getNotificationNum()
                java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r2
                r7.label = r5
                java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.stateIn(r8, r0, r7)
                if (r8 != r1) goto L3d
                goto L59
            L3d:
                kotlinx.coroutines.flow.StateFlow r8 = (kotlinx.coroutines.flow.StateFlow) r8
                com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1 r2 = new com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$3$1
                com.oplus.systemui.statusbar.widget.NotificationBadgeView r6 = r7.$notificationBadgeView
                r2.<init>(r5)
                r2.$notificationBadgeView = r6
                java.lang.invoke.VarHandle.storeStoreFence()
                java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r0
                r7.label = r4
                java.lang.Object r7 = r8.collect(r2, r7)
                if (r7 != r1) goto L5a
            L59:
                return r1
            L5a:
                kotlin.text.Regex$.ExternalSyntheticBUOutline0.m$1()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.icon.ui.binder.OplusNotificationIconAreaBinder$bindWhileAttached$1$1$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass5 extends SuspendLambda implements Function2 {
        final /* synthetic */ int $displayId;
        final /* synthetic */ NotificationBadgeView $notificationBadgeView;
        int label;
        final /* synthetic */ OplusNotificationIconAreaBinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder, int i, NotificationBadgeView notificationBadgeView, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusNotificationIconAreaBinder;
            this.$displayId = i;
            this.$notificationBadgeView = notificationBadgeView;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass5(this.this$0, this.$displayId, this.$notificationBadgeView, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowBadgeViewColors = this.this$0.oplusViewModel.badgeViewColors(this.$displayId);
                NotificationBadgeView notificationBadgeView = this.$notificationBadgeView;
                AnonymousClass3.AnonymousClass1 anonymousClass1 = new AnonymousClass3.AnonymousClass1(2);
                anonymousClass1.$notificationBadgeView = notificationBadgeView;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowBadgeViewColors.collect(anonymousClass1, this) == coroutine_suspended) {
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
    public OplusNotificationIconAreaBinder$bindWhileAttached$1$1(int i, OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder, NotificationIconContainer notificationIconContainer, NotificationBadgeView notificationBadgeView, Continuation continuation) {
        super(3, continuation);
        this.$displayId = i;
        this.this$0 = oplusNotificationIconAreaBinder;
        this.$view = notificationIconContainer;
        this.$notificationBadgeView = notificationBadgeView;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusNotificationIconAreaBinder$bindWhileAttached$1$1 oplusNotificationIconAreaBinder$bindWhileAttached$1$1 = new OplusNotificationIconAreaBinder$bindWhileAttached$1$1(this.$displayId, this.this$0, this.$view, this.$notificationBadgeView, (Continuation) obj3);
        oplusNotificationIconAreaBinder$bindWhileAttached$1$1.L$0 = (LifecycleOwner) obj;
        return oplusNotificationIconAreaBinder$bindWhileAttached$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        StatusBarNotificationIconViewStore statusBarNotificationIconViewStoreCreate;
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        int i = this.$displayId;
        OplusNotificationIconAreaBinder oplusNotificationIconAreaBinder = this.this$0;
        if (i == 0) {
            statusBarNotificationIconViewStoreCreate = oplusNotificationIconAreaBinder.defaultDisplayViewStore;
        } else {
            statusBarNotificationIconViewStoreCreate = oplusNotificationIconAreaBinder.connectedDisplaysViewStoreFactory.create(this.$displayId);
            BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new viewStore.1.1(statusBarNotificationIconViewStoreCreate, (Continuation) null), 3);
        }
        StatusBarNotificationIconViewStore statusBarNotificationIconViewStore = statusBarNotificationIconViewStoreCreate;
        SystemUIDisplaySubcomponent systemUIDisplaySubcomponent = (SystemUIDisplaySubcomponent) this.this$0.perDisplaySubcomponentRepository.get(this.$displayId);
        if (systemUIDisplaySubcomponent == null) {
            Object obj2 = this.this$0.perDisplaySubcomponentRepository.get(0);
            obj2.getClass();
            systemUIDisplaySubcomponent = (SystemUIDisplaySubcomponent) obj2;
        }
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.$displayId, this.$view, this.this$0, systemUIDisplaySubcomponent.getConfigurationState(), systemUIDisplaySubcomponent.getSystemBarUtilsState(), statusBarNotificationIconViewStore, null), 3);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, this.$view, null), 3);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, this.$notificationBadgeView, null), 3);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.this$0, this.$notificationBadgeView, null), 3);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.this$0, this.$displayId, this.$notificationBadgeView, null), 3);
        return Unit.INSTANCE;
    }
}
