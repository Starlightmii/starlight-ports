package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseActionForB extends BaseAction {
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseAction
    public final Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation) {
        apply.1 r0;
        if (continuation instanceof apply.1) {
            r0 = (apply.1) continuation;
            int i = r0.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                r0.label = i - Integer.MIN_VALUE;
            } else {
                r0 = new apply.1(this, continuation);
            }
        } else {
            r0 = new apply.1(this, continuation);
        }
        Object objApply = r0.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = r0.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objApply);
            List<TileConfig> listEmptyList = list2 == null ? CollectionsKt__CollectionsKt.emptyList() : list2;
            r0.L$0 = list;
            r0.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
            r0.L$2 = SpillingKt.nullOutSpilledVariable(list2);
            r0.label = 1;
            objApply = apply(listEmptyList, r0);
            if (objApply == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            list = (List) r0.L$0;
            ResultKt.throwOnFailure(objApply);
        }
        List list3 = (List) objApply;
        if (list == null) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        return new Pair(list, list3);
    }

    public abstract Object apply(List<TileConfig> list, Continuation continuation);
}
