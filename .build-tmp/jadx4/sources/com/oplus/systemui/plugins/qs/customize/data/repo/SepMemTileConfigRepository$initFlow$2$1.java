package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.util.Log;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseAction;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepMemTileConfigRepository$initFlow$2$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ SepMemTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepMemTileConfigRepository$initFlow$2$1(SepMemTileConfigRepository sepMemTileConfigRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepMemTileConfigRepository;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepMemTileConfigRepository$initFlow$2$1 sepMemTileConfigRepository$initFlow$2$1 = new SepMemTileConfigRepository$initFlow$2$1(this.this$0, (Continuation) obj3);
        sepMemTileConfigRepository$initFlow$2$1.L$0 = (Pair) obj;
        sepMemTileConfigRepository$initFlow$2$1.L$1 = (BaseAction) obj2;
        return sepMemTileConfigRepository$initFlow$2$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        BaseAction baseAction = (BaseAction) this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            List<TileConfig> list = (List) pair.getFirst();
            GridInfoEntity gridInfoEntity = this.this$0.initGridInfoEntity;
            List<TileConfig> list2 = (List) pair.getSecond();
            this.L$0 = SpillingKt.nullOutSpilledVariable(pair);
            this.L$1 = baseAction;
            this.label = 1;
            obj = baseAction.apply(list, gridInfoEntity, list2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SepMemTileConfigRepository-->".concat("changeEvents " + baseAction));
        }
        return obj;
    }
}
