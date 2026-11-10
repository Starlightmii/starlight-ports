package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.util.Log;
import androidx.collection.FloatList$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSHostAdapter$currentTileAndUiConfig$1$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $it;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSHostAdapter$currentTileAndUiConfig$1$2(int i, Continuation continuation) {
        super(3, continuation);
        this.$it = i;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepQSHostAdapter$currentTileAndUiConfig$1$2 sepQSHostAdapter$currentTileAndUiConfig$1$2 = new SepQSHostAdapter$currentTileAndUiConfig$1$2(this.$it, (Continuation) obj3);
        sepQSHostAdapter$currentTileAndUiConfig$1$2.L$0 = (Pair) obj;
        sepQSHostAdapter$currentTileAndUiConfig$1$2.L$1 = (List) obj2;
        return sepQSHostAdapter$currentTileAndUiConfig$1$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        List list = (List) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        int i = this.$it;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(((List) pair.getSecond()).size(), list.size(), " ", "SepQSHostAdapter-->", FloatList$.ExternalSyntheticOutline0.m("combine: ", i, ((List) pair.getFirst()).size(), " ", OplusPreventModeData.SEPARATOR)));
        }
        return new UserQSTileAndConfig(this.$it, list, CollectionsKt___CollectionsKt.plus((Collection) pair.getFirst(), (Iterable) pair.getSecond()));
    }
}
