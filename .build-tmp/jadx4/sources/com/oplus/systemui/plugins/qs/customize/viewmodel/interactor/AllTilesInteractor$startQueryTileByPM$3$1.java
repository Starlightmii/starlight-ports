package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.android.systemui.plugins.qs.SepQSTileQueryHelper;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AllTilesInteractor$startQueryTileByPM$3$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ List<SepQSTileQueryHelper.TileInfo> $it;
    int label;
    final /* synthetic */ AllTilesInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllTilesInteractor$startQueryTileByPM$3$1(AllTilesInteractor allTilesInteractor, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = allTilesInteractor;
        this.$it = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AllTilesInteractor$startQueryTileByPM$3$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AllTilesInteractor allTilesInteractor = this.this$0;
            List<SepQSTileQueryHelper.TileInfo> list = this.$it;
            this.label = 1;
            if (AllTilesInteractor.access$setScanTileData(allTilesInteractor, list, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
