package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.SepQSTileQueryHelper;
import com.oplus.systemui.plugins.qs.customize.data.model.TilePreviewsData;
import com.oplusos.systemui.common.model.SpanCache;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AllTilesInteractor$toTilePreviewsData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ List<SepQSTileQueryHelper.TileInfo> $configs;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllTilesInteractor$toTilePreviewsData$2(List list, Continuation continuation) {
        super(2, continuation);
        this.$configs = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AllTilesInteractor$toTilePreviewsData$2(this.$configs, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        List<SepQSTileQueryHelper.TileInfo> list = this.$configs;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (SepQSTileQueryHelper.TileInfo tileInfo : list) {
            QSTile.Icon icon = (!StringsKt__StringsKt.contains(tileInfo.getSpec(), "custom(", false) || tileInfo.getState().iconSupplier == null) ? tileInfo.getState().icon : (QSTile.Icon) tileInfo.getState().iconSupplier.get();
            Object obj2 = tileInfo.getState().secondaryLabel;
            if (obj2 == null) {
                obj2 = "";
            }
            arrayList.add(new TilePreviewsData(tileInfo.getSpec(), tileInfo.getState().label.toString(), icon, SpanCache.INSTANCE.spanSize(1, 1), obj2.toString(), 8));
        }
        return arrayList;
    }
}
