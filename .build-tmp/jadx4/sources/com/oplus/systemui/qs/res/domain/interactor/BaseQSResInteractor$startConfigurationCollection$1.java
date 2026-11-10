package com.oplus.systemui.qs.res.domain.interactor;

import android.content.res.Configuration;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class BaseQSResInteractor$startConfigurationCollection$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BaseQSResInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.BaseQSResInteractor$startConfigurationCollection$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(2, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((Configuration) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Configuration configuration = (Configuration) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            BaseQSResPool.notifyConfigurationChanged(configuration);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseQSResInteractor$startConfigurationCollection$1(BaseQSResInteractor baseQSResInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseQSResInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BaseQSResInteractor$startConfigurationCollection$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow sharedFlow = this.this$0.configuration;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(2, null);
            this.label = 1;
            if (FlowKt.collectLatest(sharedFlow, anonymousClass1, this) == coroutine_suspended) {
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
