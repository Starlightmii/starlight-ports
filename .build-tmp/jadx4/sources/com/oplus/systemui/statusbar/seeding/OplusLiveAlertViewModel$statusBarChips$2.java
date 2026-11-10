package com.oplus.systemui.statusbar.seeding;

import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusLiveAlertViewModel$statusBarChips$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        OplusLiveAlertViewModel$statusBarChips$2 oplusLiveAlertViewModel$statusBarChips$2 = new OplusLiveAlertViewModel$statusBarChips$2(2, continuation);
        oplusLiveAlertViewModel$statusBarChips$2.L$0 = obj;
        return oplusLiveAlertViewModel$statusBarChips$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((List) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        List list = (List) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        List list2 = list;
        boolean z = true;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (!(((LiveAlertChipModel.LiveAlertSbnChipModel) it.next()).getMode() instanceof OngoingActivityChipModel.Inactive)) {
                    z = false;
                    break;
                }
            }
        }
        return Boxing.boxBoolean(z);
    }
}
