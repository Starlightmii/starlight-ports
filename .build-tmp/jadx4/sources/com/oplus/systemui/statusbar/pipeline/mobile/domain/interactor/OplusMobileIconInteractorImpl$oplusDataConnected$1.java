package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileIconInteractorImpl$oplusDataConnected$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusMobileIconInteractorImpl$oplusDataConnected$1 oplusMobileIconInteractorImpl$oplusDataConnected$1 = new OplusMobileIconInteractorImpl$oplusDataConnected$1(5, (Continuation) obj5);
        oplusMobileIconInteractorImpl$oplusDataConnected$1.Z$0 = zBooleanValue;
        oplusMobileIconInteractorImpl$oplusDataConnected$1.L$0 = (Integer) obj2;
        oplusMobileIconInteractorImpl$oplusDataConnected$1.Z$1 = zBooleanValue2;
        oplusMobileIconInteractorImpl$oplusDataConnected$1.L$1 = (SubscriptionModel) obj4;
        return oplusMobileIconInteractorImpl$oplusDataConnected$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0031  */
    public final Object invokeSuspend(Object obj) {
        boolean z;
        boolean z2 = this.Z$0;
        Integer num = (Integer) this.L$0;
        boolean z3 = this.Z$1;
        SubscriptionModel subscriptionModel = (SubscriptionModel) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (z3) {
            z = z2;
        } else if (z2) {
            if (Intrinsics.areEqual(num, subscriptionModel != null ? Boxing.boxInt(subscriptionModel.getSubscriptionId()) : null)) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sb = new StringBuilder("OplusMobileIconInteractorImpl-->");
            sb.append("oplusDataConnected dataConnected:" + z + " subId:" + (subscriptionModel != null ? Boxing.boxInt(subscriptionModel.getSubscriptionId()) : null) + " isDualSimOpen:" + z3 + " activeDataSubId:" + num + " isDataConnected:" + z2);
            Log.d("SystemUi--Statusbar", sb.toString());
        }
        return Boxing.boxBoolean(z);
    }
}
