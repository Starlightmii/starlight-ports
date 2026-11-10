package com.oplus.systemui.separate.data.repository;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OnePlusQsColorUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusPanelModeRepository$enableSeparateNotificationAndQS$2 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ OplusPanelModeRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusPanelModeRepository$enableSeparateNotificationAndQS$2(OplusPanelModeRepository oplusPanelModeRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusPanelModeRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusPanelModeRepository$enableSeparateNotificationAndQS$2 oplusPanelModeRepository$enableSeparateNotificationAndQS$2 = new OplusPanelModeRepository$enableSeparateNotificationAndQS$2(this.this$0, continuation);
        oplusPanelModeRepository$enableSeparateNotificationAndQS$2.Z$0 = ((Boolean) obj).booleanValue();
        return oplusPanelModeRepository$enableSeparateNotificationAndQS$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((OplusPanelModeRepository$enableSeparateNotificationAndQS$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        OnePlusQsColorUtil.INSTANCE.setEnableSeparateNotificationAndQS(z);
        BaseQSResPool.updateSeparateNotificationAndQS(z);
        LogUtil.i$default(LogUtil.TAG_QS, "OplusPanelModeRepository", KeyguardLogger$.ExternalSyntheticOutline0.m("enableSeparateNotificationAndQS: ", z, ", userId=", this.this$0.lastPublishedUserId), null, 8);
        return Unit.INSTANCE;
    }
}
