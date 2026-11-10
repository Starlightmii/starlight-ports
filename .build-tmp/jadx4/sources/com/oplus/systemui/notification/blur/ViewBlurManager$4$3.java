package com.oplus.systemui.notification.blur;

import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ViewBlurManager$4$3 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ViewBlurManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewBlurManager$4$3(ViewBlurManager viewBlurManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = viewBlurManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new ViewBlurManager$4$3(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow useMotionBlur = ((PlatformBlurManager) this.this$0.platformBlurManager.get()).getUseMotionBlur();
            ViewBlurManager viewBlurManager = this.this$0;
            ViewBlurManager.AnonymousClass3.AnonymousClass1 anonymousClass1 = new ViewBlurManager.AnonymousClass3.AnonymousClass1(3);
            anonymousClass1.this$0 = viewBlurManager;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (useMotionBlur.collect(anonymousClass1, this) == coroutine_suspended) {
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
