package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import com.oplus.systemui.plugins.qs.customize.data.model.TileTypeConfig;
import java.util.ArrayList;
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
final class SepMoreTileConfigRepository$loadDataFromXMLByLock$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SepMoreTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepMoreTileConfigRepository$loadDataFromXMLByLock$2(SepMoreTileConfigRepository sepMoreTileConfigRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sepMoreTileConfigRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SepMoreTileConfigRepository$loadDataFromXMLByLock$2(this.this$0, continuation);
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
        ArrayList arrayList = new ArrayList();
        SepMoreTileConfigRepository sepMoreTileConfigRepository = this.this$0;
        arrayList.add(new TileTypeConfig(SepTileType.RECOMMEND, SepMoreTileConfigRepository.access$toTileData(sepMoreTileConfigRepository, sepMoreTileConfigRepository.getRecommendTileSpecs())));
        arrayList.add(new TileTypeConfig(SepTileType.CONNECTIVITY, SepMoreTileConfigRepository.access$toTileData(sepMoreTileConfigRepository, sepMoreTileConfigRepository.getConnectivityTileSpecs())));
        arrayList.add(new TileTypeConfig(SepTileType.DISPLAY, SepMoreTileConfigRepository.access$toTileData(sepMoreTileConfigRepository, sepMoreTileConfigRepository.getDisplayTileSpecs())));
        arrayList.add(new TileTypeConfig(SepTileType.TOOLS, SepMoreTileConfigRepository.access$toTileData(sepMoreTileConfigRepository, sepMoreTileConfigRepository.getToolsTilesSpec())));
        arrayList.add(new TileTypeConfig(SepTileType.OTHERS, SepMoreTileConfigRepository.access$toTileData(sepMoreTileConfigRepository, sepMoreTileConfigRepository.getOtherSystemTilesSpec())));
        return arrayList;
    }
}
