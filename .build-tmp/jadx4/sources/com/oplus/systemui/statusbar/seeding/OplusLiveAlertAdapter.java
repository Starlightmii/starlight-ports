package com.oplus.systemui.statusbar.seeding;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.FileProvider$;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertAdapter {
    public static final OplusLiveAlertAdapter INSTANCE = new OplusLiveAlertAdapter();
    private static final String KEY_CHIPS = "content";
    private static final String METHOD_CANCEL_LIVE_ALERT = "cancelLiveAlert";
    private static final String METHOD_SEND_LIVE_ALERT = "sendLiveAlert";
    private static final String SYSTEM_UI_SEEDLING_CARD_AUTHORITY = "com.oplus.card.server.systemui.provider";
    private static final String TAG = "OplusLiveAlertAdapter";

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.OplusLiveAlertAdapter$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ View $view;
        final /* synthetic */ OplusLiveAlertViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.OplusLiveAlertAdapter$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01671 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $view;
            final /* synthetic */ OplusLiveAlertViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.OplusLiveAlertAdapter$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01681 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $view;
                final /* synthetic */ OplusLiveAlertViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.OplusLiveAlertAdapter$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01691 implements FlowCollector {
                    public /* synthetic */ View $view;

                    public final Object emit(Object obj, Continuation continuation) {
                        OplusLiveAlertAdapter oplusLiveAlertAdapter = OplusLiveAlertAdapter.INSTANCE;
                        Context context = this.$view.getContext();
                        oplusLiveAlertAdapter.getClass();
                        for (LiveAlertChipModel.LiveAlertSbnChipModel liveAlertSbnChipModel : (List) obj) {
                            StatusBarNotification sbn = liveAlertSbnChipModel.getSbn();
                            if (sbn != null) {
                                try {
                                    String str = liveAlertSbnChipModel.getMode() instanceof OngoingActivityChipModel.Active ? OplusLiveAlertAdapter.METHOD_SEND_LIVE_ALERT : OplusLiveAlertAdapter.METHOD_CANCEL_LIVE_ALERT;
                                    ContentResolver contentResolver = context.getContentResolver();
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(OplusLiveAlertAdapter.KEY_CHIPS, sbn);
                                    contentResolver.call(OplusLiveAlertAdapter.SYSTEM_UI_SEEDLING_CARD_AUTHORITY, str, "", bundle);
                                } catch (IllegalArgumentException e) {
                                    LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusLiveAlertAdapter.TAG, FileProvider$.ExternalSyntheticOutline0.m("call error: ", e.getMessage()), null, 8);
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01681(OplusLiveAlertViewModel oplusLiveAlertViewModel, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = oplusLiveAlertViewModel;
                    this.$view = view;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01681(this.$viewModel, this.$view, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow statusBarChips = this.$viewModel.getStatusBarChips();
                        View view = this.$view;
                        C01691 c01691 = new C01691();
                        c01691.$view = view;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (statusBarChips.collect(c01691, this) == coroutine_suspended) {
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
            public C01671(OplusLiveAlertViewModel oplusLiveAlertViewModel, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = oplusLiveAlertViewModel;
                this.$view = view;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C01671 c01671 = new C01671(this.$viewModel, this.$view, continuation);
                c01671.L$0 = obj;
                return c01671;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01681(this.$viewModel, this.$view, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OplusLiveAlertViewModel oplusLiveAlertViewModel, View view, Continuation continuation) {
            super(3, continuation);
            this.$viewModel = oplusLiveAlertViewModel;
            this.$view = view;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$view, (Continuation) obj3);
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
                C01671 c01671 = new C01671(this.$viewModel, this.$view, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01671, this) == coroutine_suspended) {
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

    public final DisposableHandle bind(OplusLiveAlertViewModel oplusLiveAlertViewModel, View view) {
        return RepeatWhenAttachedKt.repeatWhenAttached$default(view, (CoroutineContext) null, new AnonymousClass1(oplusLiveAlertViewModel, view, null), 3);
    }
}
