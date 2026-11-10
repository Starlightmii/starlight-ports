package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileIconsInteractorImpl$mobileIconSets$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileIconsInteractorImpl$mobileIconSets$2 oplusMobileIconsInteractorImpl$mobileIconSets$2 = new OplusMobileIconsInteractorImpl$mobileIconSets$2(2, continuation);
        oplusMobileIconsInteractorImpl$mobileIconSets$2.L$0 = obj;
        return oplusMobileIconsInteractorImpl$mobileIconSets$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((MobileIconSets) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MobileIconSets mobileIconSets = (MobileIconSets) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("resolveMobileIconSets -> ", Reflection.getOrCreateKotlinClass(mobileIconSets.getClass()).getSimpleName(), "OplusMobileIconsInteractorImpl-->", "SystemUi--Statusbar");
        }
        return Unit.INSTANCE;
    }
}
