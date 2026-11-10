package com.oplus.systemui.plugins.qs.util;

import android.view.View;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function3 $block;
    final /* synthetic */ ViewLifecycleOwner $this_apply;
    final /* synthetic */ View $view;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(Function3 function3, ViewLifecycleOwner viewLifecycleOwner, View view, Continuation continuation) {
        super(2, continuation);
        this.$block = function3;
        this.$this_apply = viewLifecycleOwner;
        this.$view = view;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(this.$block, this.$this_apply, this.$view, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function3 function3 = this.$block;
            ViewLifecycleOwner viewLifecycleOwner = this.$this_apply;
            View view = this.$view;
            this.label = 1;
            if (function3.invoke(viewLifecycleOwner, view, this) == coroutine_suspended) {
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
