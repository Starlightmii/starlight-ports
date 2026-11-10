package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.DynamicIconModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class DynamicIconSetViewModel$combineFlow$1 extends SuspendLambda implements Function6 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    /* synthetic */ Object L$3;
    /* synthetic */ Object L$4;
    int label;

    public DynamicIconSetViewModel$combineFlow$1(Continuation continuation) {
        super(6, continuation);
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        DynamicIconSetViewModel$combineFlow$1 dynamicIconSetViewModel$combineFlow$1 = new DynamicIconSetViewModel$combineFlow$1((Continuation) obj6);
        dynamicIconSetViewModel$combineFlow$1.L$0 = (DynamicIconModel) obj;
        dynamicIconSetViewModel$combineFlow$1.L$1 = (DynamicIconModel) obj2;
        dynamicIconSetViewModel$combineFlow$1.L$2 = (DynamicIconModel) obj3;
        dynamicIconSetViewModel$combineFlow$1.L$3 = (DynamicIconModel) obj4;
        dynamicIconSetViewModel$combineFlow$1.L$4 = (DynamicIconModel) obj5;
        return dynamicIconSetViewModel$combineFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        DynamicIconModel dynamicIconModel = (DynamicIconModel) this.L$0;
        DynamicIconModel dynamicIconModel2 = (DynamicIconModel) this.L$1;
        DynamicIconModel dynamicIconModel3 = (DynamicIconModel) this.L$2;
        DynamicIconModel dynamicIconModel4 = (DynamicIconModel) this.L$3;
        DynamicIconModel dynamicIconModel5 = (DynamicIconModel) this.L$4;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return SetsKt__SetsKt.setOfNotNull(new DynamicIconModel[]{dynamicIconModel, dynamicIconModel2, dynamicIconModel3, dynamicIconModel4, dynamicIconModel5});
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
