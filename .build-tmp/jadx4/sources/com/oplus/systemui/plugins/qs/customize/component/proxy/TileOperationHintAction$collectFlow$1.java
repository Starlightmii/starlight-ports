package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffectWrapper;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileOperationHintAction$collectFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TileOperationHintAction this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.proxy.TileOperationHintAction$collectFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TileOperationHintAction this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.proxy.TileOperationHintAction$collectFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00531 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ TileOperationHintAction this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.proxy.TileOperationHintAction$collectFlow$1$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C00541 implements FlowCollector {
                public /* synthetic */ TileOperationHintAction this$0;

                public final Object emit(Object obj, Continuation continuation) {
                    TileEditEffectWrapper tileEditEffectWrapper = (TileEditEffectWrapper) obj;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "TileOperationHintAction-->".concat("[collect-uiEffectFlow] " + tileEditEffectWrapper));
                    }
                    TileOperationHintAction.access$processEffect(this.this$0, tileEditEffectWrapper.getEffect());
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00531(TileOperationHintAction tileOperationHintAction, Continuation continuation) {
                super(2, continuation);
                this.this$0 = tileOperationHintAction;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00531(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SharedFlow uiEffectFlow = this.this$0.editViewModel.getUiEffectFlow();
                    TileOperationHintAction tileOperationHintAction = this.this$0;
                    C00541 c00541 = new C00541();
                    c00541.this$0 = tileOperationHintAction;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (FlowExtKt.collectTraced(uiEffectFlow, "TileOperationHintAction-collect-uiEffectFlow", c00541, this) == coroutine_suspended) {
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
        public AnonymousClass1(TileOperationHintAction tileOperationHintAction, Continuation continuation) {
            super(2, continuation);
            this.this$0 = tileOperationHintAction;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
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
            CoroutineTracingKt.launchTraced$default(coroutineScope, "TileOperationHintAction-launch-for-collect", (CoroutineContext) null, (CoroutineStart) null, new C00531(this.this$0, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileOperationHintAction$collectFlow$1(TileOperationHintAction tileOperationHintAction, Continuation continuation) {
        super(3, continuation);
        this.this$0 = tileOperationHintAction;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TileOperationHintAction$collectFlow$1 tileOperationHintAction$collectFlow$1 = new TileOperationHintAction$collectFlow$1(this.this$0, (Continuation) obj3);
        tileOperationHintAction$collectFlow$1.L$0 = (LifecycleOwner) obj;
        return tileOperationHintAction$collectFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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
