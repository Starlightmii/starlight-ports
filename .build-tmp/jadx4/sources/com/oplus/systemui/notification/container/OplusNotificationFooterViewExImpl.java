package com.oplus.systemui.notification.container;

import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import com.android.systemui.util.ui.AnimatedValue;
import com.oplus.systemui.notification.clearall.ClearAllController;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationFooterViewExImpl extends OplusNotificationFooterViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationFooter";
    private final ClearAllController clearAllController;
    private final NotificationListViewModel viewModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.container.OplusNotificationFooterViewExImpl$bindClearAll$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterViewModel $footerViewModel;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusNotificationFooterViewExImpl this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.container.OplusNotificationFooterViewExImpl$bindClearAll$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ FooterViewModel $footerViewModel;
            int label;
            final /* synthetic */ OplusNotificationFooterViewExImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.container.OplusNotificationFooterViewExImpl$bindClearAll$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00141 extends SuspendLambda implements Function4 {
                /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                /* synthetic */ boolean Z$0;
                int label;

                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    boolean zBooleanValue = ((Boolean) obj3).booleanValue();
                    C00141 c00141 = new C00141(4, (Continuation) obj4);
                    c00141.L$0 = (AnimatedValue) obj;
                    c00141.L$1 = (AnimatedValue) obj2;
                    c00141.Z$0 = zBooleanValue;
                    return c00141.invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    AnimatedValue.Animating animating = (AnimatedValue) this.L$0;
                    AnimatedValue animatedValue = (AnimatedValue) this.L$1;
                    boolean z = this.Z$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        StringBuilder sb = new StringBuilder("onClearAllChanged:");
                        sb.append(animating);
                        sb.append(OplusPreventModeData.SEPARATOR);
                        sb.append(animatedValue);
                        sb.append(OplusPreventModeData.SEPARATOR);
                        SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusNotificationFooter-->", "SystemUi--Notification", sb, z);
                    }
                    if (animating instanceof AnimatedValue.Animating) {
                        return animating.getValue();
                    }
                    if (animating instanceof AnimatedValue.NotAnimating) {
                        return ((AnimatedValue.NotAnimating) animating).getValue();
                    }
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return null;
                }
            }

            /* JADX INFO: renamed from: com.oplus.systemui.notification.container.OplusNotificationFooterViewExImpl$bindClearAll$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C00152 implements FlowCollector {
                public /* synthetic */ OplusNotificationFooterViewExImpl this$0;

                public final Object emit(Object obj, Continuation continuation) {
                    this.this$0.clearAllController.setShowDismiss(((Boolean) obj).booleanValue());
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(FooterViewModel footerViewModel, OplusNotificationFooterViewExImpl oplusNotificationFooterViewExImpl, Continuation continuation) {
                super(2, continuation);
                this.$footerViewModel = footerViewModel;
                this.this$0 = oplusNotificationFooterViewExImpl;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$footerViewModel, this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flowCombine = FlowKt.combine(this.$footerViewModel.getClearAllButton().isVisible(), this.this$0.viewModel.getShouldIncludeFooterView(), this.this$0.viewModel.getShouldHideFooterView(), new C00141(4, null));
                    OplusNotificationFooterViewExImpl oplusNotificationFooterViewExImpl = this.this$0;
                    C00152 c00152 = new C00152();
                    c00152.this$0 = oplusNotificationFooterViewExImpl;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (flowCombine.collect(c00152, this) == coroutine_suspended) {
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
        public AnonymousClass2(FooterViewModel footerViewModel, OplusNotificationFooterViewExImpl oplusNotificationFooterViewExImpl, Continuation continuation) {
            super(2, continuation);
            this.$footerViewModel = footerViewModel;
            this.this$0 = oplusNotificationFooterViewExImpl;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$footerViewModel, this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.$footerViewModel, this.this$0, null), 3);
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public OplusNotificationFooterViewExImpl(ClearAllController clearAllController, NotificationListViewModel notificationListViewModel) {
        this.clearAllController = clearAllController;
        this.viewModel = notificationListViewModel;
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationFooterViewEx
    public Object bindClearAll(FooterViewModel footerViewModel, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(footerViewModel, this, null), continuation);
        return objCoroutineScope == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationFooterViewEx
    public boolean hideFooterView(FooterView footerView) {
        footerView.setVisible(false, false, (NotificationListViewBinder.bindEmptyShade.2.2.1.1) null);
        return true;
    }
}
