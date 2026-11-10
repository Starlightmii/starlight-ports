package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.runtime.MutableState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ScenarioCapsuleViewBinder$FirstLaunchProvider$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableState $isFirstLaunch$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScenarioCapsuleViewBinder$FirstLaunchProvider$1$1(MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$isFirstLaunch$delegate = mutableState;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new ScenarioCapsuleViewBinder$FirstLaunchProvider$1$1(this.$isFirstLaunch$delegate, continuation);
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
        MutableState mutableState = this.$isFirstLaunch$delegate;
        ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = ScenarioCapsuleViewBinder.INSTANCE;
        mutableState.setValue(Boolean.FALSE);
        return Unit.INSTANCE;
    }
}
