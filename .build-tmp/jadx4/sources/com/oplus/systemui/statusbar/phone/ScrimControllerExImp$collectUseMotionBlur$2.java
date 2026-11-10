package com.oplus.systemui.statusbar.phone;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimControllerExImp$collectUseMotionBlur$2 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ScrimControllerExImp this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$collectUseMotionBlur$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ ScrimControllerExImp this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ScrimControllerExImp scrimControllerExImp, Continuation continuation) {
            super(2, continuation);
            this.this$0 = scrimControllerExImp;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
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
            this.this$0.updateBehindMixConfig();
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ ScrimControllerExImp$collectUseMotionBlur$2(int i) {
        this.$r8$classId = i;
    }

    public final Object emit(Object obj, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                ((Boolean) obj).booleanValue();
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new AnonymousClass1(this.this$0, null), continuation);
                return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
            default:
                ((Number) obj).floatValue();
                ScrimControllerExImp scrimControllerExImp = this.this$0;
                if (scrimControllerExImp.oplusLockscreenShadeTransitionController.isAnimRunning()) {
                    scrimControllerExImp.getScrimController().applyAndDispatchState();
                }
                return Unit.INSTANCE;
        }
    }
}
