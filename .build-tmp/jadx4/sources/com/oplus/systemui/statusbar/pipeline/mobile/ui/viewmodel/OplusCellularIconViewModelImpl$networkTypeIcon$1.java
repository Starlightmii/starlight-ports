package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusCellularIconViewModelImpl$networkTypeIcon$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        OplusCellularIconViewModelImpl$networkTypeIcon$1 oplusCellularIconViewModelImpl$networkTypeIcon$1 = new OplusCellularIconViewModelImpl$networkTypeIcon$1(5, (Continuation) obj5);
        oplusCellularIconViewModelImpl$networkTypeIcon$1.L$0 = (NetworkTypeIconModel) obj;
        oplusCellularIconViewModelImpl$networkTypeIcon$1.Z$0 = zBooleanValue;
        oplusCellularIconViewModelImpl$networkTypeIcon$1.L$1 = (SignalIconModel) obj3;
        oplusCellularIconViewModelImpl$networkTypeIcon$1.L$2 = (MobileIconSets) obj4;
        return oplusCellularIconViewModelImpl$networkTypeIcon$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Icon.Resource resource;
        NetworkTypeIconModel networkTypeIconModel = (NetworkTypeIconModel) this.L$0;
        boolean z = this.Z$0;
        SignalIconModel.CellularTypeIconModel.Cellular cellular = (SignalIconModel) this.L$1;
        MobileIconSets mobileIconSets = (MobileIconSets) this.L$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        ContentDescription.Resource resource2 = networkTypeIconModel.getContentDescription() != 0 ? new ContentDescription.Resource(networkTypeIconModel.getContentDescription()) : null;
        if (networkTypeIconModel.getIconId() != 0) {
            resource = new Icon.Resource(cellular instanceof SignalIconModel.CellularTypeIconModel.Cellular ? mobileIconSets.getDataTypeIcon(networkTypeIconModel.getIconId(), cellular.isSoftSimCard) : networkTypeIconModel.getIconId(), resource2);
        } else {
            resource = null;
        }
        if (z) {
            return resource;
        }
        return null;
    }
}
