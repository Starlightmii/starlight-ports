package com.oplus.systemui.statusbar.seeding;

import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusLiveAlertViewModel$internalChips$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        OplusLiveAlertViewModel$internalChips$1 oplusLiveAlertViewModel$internalChips$1 = new OplusLiveAlertViewModel$internalChips$1(4, (Continuation) obj4);
        oplusLiveAlertViewModel$internalChips$1.L$0 = (OngoingActivityChipModel) obj;
        oplusLiveAlertViewModel$internalChips$1.L$1 = (OngoingActivityChipModel) obj2;
        oplusLiveAlertViewModel$internalChips$1.L$2 = (OngoingActivityChipModel) obj3;
        return oplusLiveAlertViewModel$internalChips$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        OngoingActivityChipModel ongoingActivityChipModel = (OngoingActivityChipModel) this.L$0;
        OngoingActivityChipModel ongoingActivityChipModel2 = (OngoingActivityChipModel) this.L$1;
        OngoingActivityChipModel ongoingActivityChipModel3 = (OngoingActivityChipModel) this.L$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LiveAlertChipModel.ScreenRecordChipModel(OplusLiveAlertViewModel.OplusChipType.ScreenRecord, ongoingActivityChipModel));
        arrayList.add(new LiveAlertChipModel.ShareToAppChipModel(OplusLiveAlertViewModel.OplusChipType.ShareToApp, ongoingActivityChipModel2));
        arrayList.add(new LiveAlertChipModel.CastToOtherDeviceChipModel(OplusLiveAlertViewModel.OplusChipType.CastToOtherDevice, ongoingActivityChipModel3));
        return arrayList;
    }
}
