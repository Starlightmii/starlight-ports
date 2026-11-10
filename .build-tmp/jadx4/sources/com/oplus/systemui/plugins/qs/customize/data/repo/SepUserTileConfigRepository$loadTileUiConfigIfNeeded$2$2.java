package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.util.Log;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseAction;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2 extends SuspendLambda implements Function3 {
    int I$0;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ SepUserTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2(SepUserTileConfigRepository sepUserTileConfigRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepUserTileConfigRepository;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2 sepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2 = new SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2(this.this$0, (Continuation) obj3);
        sepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2.L$0 = (Pair) obj;
        sepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2.L$1 = (BaseAction) obj2;
        return sepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        BaseAction baseAction = (BaseAction) this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            List<TileConfig> list = (List) pair.getFirst();
            MutableStateFlow mutableStateFlow = this.this$0._gridInfo;
            GridInfoEntity gridInfoEntity = (GridInfoEntity) (mutableStateFlow != null ? mutableStateFlow : null).getValue();
            List<TileConfig> list2 = (List) pair.getSecond();
            this.L$0 = SpillingKt.nullOutSpilledVariable(pair);
            this.L$1 = baseAction;
            this.label = 1;
            obj = baseAction.apply(list, gridInfoEntity, list2, this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            Object obj2 = this.L$2;
            ResultKt.throwOnFailure(obj);
            return obj2;
        }
        ResultKt.throwOnFailure(obj);
        SepUserTileConfigRepository sepUserTileConfigRepository = this.this$0;
        Pair pair2 = (Pair) obj;
        if (baseAction.getNeedPersist()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SepUserTileConfigRepository-->".concat("persist " + baseAction));
            }
            MutableStateFlow mutableStateFlow2 = sepUserTileConfigRepository.persistFlow;
            List listPlus = CollectionsKt___CollectionsKt.plus((Collection) pair2.getFirst(), (Iterable) pair2.getSecond());
            this.L$0 = SpillingKt.nullOutSpilledVariable(pair);
            this.L$1 = SpillingKt.nullOutSpilledVariable(baseAction);
            this.L$2 = obj;
            this.L$3 = SpillingKt.nullOutSpilledVariable(pair2);
            this.I$0 = 0;
            this.label = 2;
            if (mutableStateFlow2.emit(listPlus, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return obj;
    }
}
