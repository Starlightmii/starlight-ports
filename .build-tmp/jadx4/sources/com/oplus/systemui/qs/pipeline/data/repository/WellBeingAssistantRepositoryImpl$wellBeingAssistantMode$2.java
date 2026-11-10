package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class WellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        WellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2 wellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2 = new WellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2(2, continuation);
        wellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2.Z$0 = ((Boolean) obj).booleanValue();
        return wellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((WellBeingAssistantRepositoryImpl$wellBeingAssistantMode$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("wellBeingAssistantMode: changed to ", "WellBeingAssistantRepositoryImpl-->", "SystemUi--QuickSettings", z);
        }
        WellBeingAssistantRepositoryImpl.isWellBeingAssistantState = z;
        return Unit.INSTANCE;
    }
}
