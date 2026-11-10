package com.oplus.systemui.qs.pipeline.data.event;

import android.content.Context;
import android.os.UserHandle;
import com.android.systemui.common.data.repository.PackageChangeRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent;
import com.oplus.systemui.qs.pipeline.data.model.TileModifySignal;
import com.oplus.systemui.qs.tiles.AliPayTile;
import com.oplus.systemui.qs.tiles.AliScanTile;
import com.oplus.systemui.qs.tiles.CalculatorTile;
import com.oplus.systemui.qs.tiles.WeChatScanTile;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlatformTileRemoveEvent implements TileModifyEvent {
    private static final String ALI_PACKAGE_NAME = "com.eg.android.AlipayGphone";
    public static final Companion Companion = new Companion();
    private static final String TAG = "PlatformTileRemoveEvent";
    private static final String TENCENT_PACKAGE_NAME = "com.tencent.mm";
    private final CoroutineContext bgCoroutineContext;
    private final String calculatorPackage;
    private final PackageChangeRepository packageChangeRepository;
    private final Map<String, List<TileSpec>> packageToTileSpecs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.event.PlatformTileRemoveEvent$modifyTilesSignal$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(2, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((TileModifySignal.Remove) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            TileModifySignal.Remove remove = (TileModifySignal.Remove) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            LogUtil.i$default(LogUtil.TAG_QS, PlatformTileRemoveEvent.TAG, "modifyTilesSignal: " + remove.getSpecs(), null, 8);
            return Unit.INSTANCE;
        }
    }

    public PlatformTileRemoveEvent(Context context, CoroutineContext coroutineContext, PackageChangeRepository packageChangeRepository) {
        this.bgCoroutineContext = coroutineContext;
        this.packageChangeRepository = packageChangeRepository;
        String string = context.getString(2131952540);
        this.calculatorPackage = string;
        TileSpec.Companion companion = TileSpec.Companion;
        this.packageToTileSpecs = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(ALI_PACKAGE_NAME, CollectionsKt__CollectionsKt.listOf(new TileSpec[]{companion.create(AliPayTile.TILE_SPEC), companion.create(AliScanTile.TILE_SPEC)})), TuplesKt.to(TENCENT_PACKAGE_NAME, CollectionsKt__CollectionsKt.listOf(new TileSpec[]{companion.create("weixinpay"), companion.create(WeChatScanTile.TILE_SPEC)})), TuplesKt.to(string, CollectionsKt__CollectionsJVMKt.listOf(companion.create(CalculatorTile.TILE_SPEC)))});
    }

    @Override // com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent
    public Flow modifyTilesSignal(int i) {
        Flow flowPackageChanged = this.packageChangeRepository.packageChanged(UserHandle.of(i));
        PlatformTileRemoveEvent$modifyTilesSignal$$inlined$filter$1 platformTileRemoveEvent$modifyTilesSignal$$inlined$filter$1 = new PlatformTileRemoveEvent$modifyTilesSignal$$inlined$filter$1();
        platformTileRemoveEvent$modifyTilesSignal$$inlined$filter$1.$this_unsafeTransform$inlined = flowPackageChanged;
        VarHandle.storeStoreFence();
        PlatformTileRemoveEvent$modifyTilesSignal$$inlined$mapNotNull$1 platformTileRemoveEvent$modifyTilesSignal$$inlined$mapNotNull$1 = new PlatformTileRemoveEvent$modifyTilesSignal$$inlined$mapNotNull$1();
        platformTileRemoveEvent$modifyTilesSignal$$inlined$mapNotNull$1.$this_unsafeTransform$inlined = platformTileRemoveEvent$modifyTilesSignal$$inlined$filter$1;
        platformTileRemoveEvent$modifyTilesSignal$$inlined$mapNotNull$1.this$0 = this;
        VarHandle.storeStoreFence();
        return FlowKt.flowOn(FlowKt.onEach(platformTileRemoveEvent$modifyTilesSignal$$inlined$mapNotNull$1, new AnonymousClass3(2, null)), this.bgCoroutineContext);
    }
}
