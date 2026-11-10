package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import android.support.v4.content.FileProvider$;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseActionForA extends BaseAction {

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForA$apply$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseActionForA.this.apply(null, null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseAction
    public final Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objApply = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objApply);
            if (gridInfoEntity == null) {
                Path$.ExternalSyntheticBUOutline0.m(FileProvider$.ExternalSyntheticOutline0.m("[apply] gridInfo is null for ", ShareTransitionProperty.Companion.simpleNameAndHash(this)));
                return null;
            }
            List<TileConfig> listEmptyList = list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(list);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
            anonymousClass1.L$2 = list2;
            anonymousClass1.label = 1;
            objApply = apply(listEmptyList, gridInfoEntity, anonymousClass1);
            if (objApply == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            list2 = (List) anonymousClass1.L$2;
            ResultKt.throwOnFailure(objApply);
        }
        List list3 = (List) objApply;
        if (list2 == null) {
            list2 = CollectionsKt__CollectionsKt.emptyList();
        }
        return new Pair(list3, list2);
    }

    public abstract Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, Continuation continuation);
}
