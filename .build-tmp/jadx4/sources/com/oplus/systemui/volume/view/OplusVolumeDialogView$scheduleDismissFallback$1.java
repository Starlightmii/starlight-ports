package com.oplus.systemui.volume.view;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusVolumeDialogView$scheduleDismissFallback$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $reason;
    final /* synthetic */ long $timeout;
    int I$0;
    int label;
    final /* synthetic */ OplusVolumeDialogView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusVolumeDialogView$scheduleDismissFallback$1(long j, OplusVolumeDialogView oplusVolumeDialogView, int i, Continuation continuation) {
        super(2, continuation);
        this.$timeout = j;
        this.this$0 = oplusVolumeDialogView;
        this.$reason = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusVolumeDialogView$scheduleDismissFallback$1(this.$timeout, this.this$0, this.$reason, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x008a, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(r9, r1, r8) == r0) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L1e
            if (r1 == r4) goto L1a
            if (r1 != r3) goto L14
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8d
        L14:
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
            return r2
        L1a:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L2c
        L1e:
            kotlin.ResultKt.throwOnFailure(r9)
            long r5 = r8.$timeout
            r8.label = r4
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r5, r8)
            if (r9 != r0) goto L2c
            goto L8c
        L2c:
            com.oplus.systemui.volume.view.OplusVolumeDialogView r9 = r8.this$0
            boolean r9 = r9.isDismissing()
            if (r9 != 0) goto L37
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L37:
            com.oplus.systemui.volume.view.OplusVolumeDialogView r9 = r8.this$0
            com.oplus.systemui.volume.view.OplusVolumeDialogView$CustomDialog r9 = r9.getVolumeDialog()
            r1 = 0
            if (r9 == 0) goto L47
            boolean r9 = r9.isShowing()
            if (r9 != r4) goto L47
            goto L48
        L47:
            r4 = r1
        L48:
            if (r4 != 0) goto L52
            com.oplus.systemui.volume.view.OplusVolumeDialogView r8 = r8.this$0
            r8.resetSliderState()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L52:
            long r5 = r8.$timeout
            int r9 = r8.$reason
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r7 = "dismiss fallback triggered after "
            r1.<init>(r7)
            r1.append(r5)
            java.lang.String r5 = "ms, force finish hide reason="
            r1.append(r5)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r1 = 8
            java.lang.String r5 = "Volume"
            java.lang.String r6 = "OplusVolumeDialogView"
            com.oplusos.systemui.common.util.LogUtil.w$default(r1, r5, r6, r9)
            kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getMain()
            com.oplus.systemui.volume.view.OplusVolumeDialogView$scheduleDismissFallback$1$1 r1 = new com.oplus.systemui.volume.view.OplusVolumeDialogView$scheduleDismissFallback$1$1
            com.oplus.systemui.volume.view.OplusVolumeDialogView r5 = r8.this$0
            int r6 = r8.$reason
            r1.<init>(r5, r6, r2)
            r8.I$0 = r4
            r8.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r9, r1, r8)
            if (r8 != r0) goto L8d
        L8c:
            return r0
        L8d:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.volume.view.OplusVolumeDialogView$scheduleDismissFallback$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
