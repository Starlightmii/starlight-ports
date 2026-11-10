package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileIconInteractorImpl$oplusIsNrType$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusMobileIconInteractorImpl$oplusIsNrType$1 oplusMobileIconInteractorImpl$oplusIsNrType$1 = new OplusMobileIconInteractorImpl$oplusIsNrType$1(3, (Continuation) obj3);
        oplusMobileIconInteractorImpl$oplusIsNrType$1.L$0 = (NetworkTypeIconModel) obj;
        oplusMobileIconInteractorImpl$oplusIsNrType$1.L$1 = (SubscriptionModel) obj2;
        return oplusMobileIconInteractorImpl$oplusIsNrType$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        NetworkTypeIconModel networkTypeIconModel = (NetworkTypeIconModel) this.L$0;
        SubscriptionModel subscriptionModel = (SubscriptionModel) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Integer numBoxInt = subscriptionModel != null ? Boxing.boxInt(subscriptionModel.getPhoneId()) : null;
            Log.d("SystemUi--Statusbar", "OplusMobileIconInteractorImpl-->".concat("oplusIsNrType:" + numBoxInt + ", name:" + networkTypeIconModel.getName()));
        }
        return Boxing.boxBoolean(ArraysKt___ArraysKt.contains(OplusMobileIconInteractorImpl.nrTelephonyIcons, Boxing.boxInt(networkTypeIconModel.getIconId())));
    }
}
