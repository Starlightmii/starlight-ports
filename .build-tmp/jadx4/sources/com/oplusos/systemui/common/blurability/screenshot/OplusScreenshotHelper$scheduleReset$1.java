package com.oplusos.systemui.common.blurability.screenshot;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusScreenshotHelper$scheduleReset$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $duration;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusScreenshotHelper$scheduleReset$1(long j, Continuation continuation) {
        super(2, continuation);
        this.$duration = j;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusScreenshotHelper$scheduleReset$1(this.$duration, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long j = this.$duration;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        OplusScreenshotHelper oplusScreenshotHelper = OplusScreenshotHelper.INSTANCE;
        OplusScreenshotHelper.needCheckPureBlack = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "OplusScreenshotHelper-->[needCheckPureBlack reset]");
        }
        return Unit.INSTANCE;
    }
}
