package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileIconViewModel$vmProvider$1 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ OplusMobileIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileIconViewModel$vmProvider$1(OplusMobileIconViewModel oplusMobileIconViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusMobileIconViewModel;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileIconViewModel$vmProvider$1 oplusMobileIconViewModel$vmProvider$1 = new OplusMobileIconViewModel$vmProvider$1(this.this$0, continuation);
        oplusMobileIconViewModel$vmProvider$1.Z$0 = ((Boolean) obj).booleanValue();
        return oplusMobileIconViewModel$vmProvider$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((OplusMobileIconViewModel$vmProvider$1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        OplusMobileIconViewModel oplusMobileIconViewModel = this.this$0;
        return z ? oplusMobileIconViewModel.getSatelliteProvider() : oplusMobileIconViewModel.getCellProvider();
    }
}
