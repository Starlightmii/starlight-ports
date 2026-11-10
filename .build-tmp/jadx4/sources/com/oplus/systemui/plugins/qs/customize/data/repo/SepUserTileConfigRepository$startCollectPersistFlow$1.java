package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntityCache;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepUserTileConfigRepository$startCollectPersistFlow$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SepUserTileConfigRepository this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository$startCollectPersistFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SepUserTileConfigRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SepUserTileConfigRepository sepUserTileConfigRepository, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepUserTileConfigRepository;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((List) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:28:0x00d9  */
        public final Object invokeSuspend(Object obj) {
            List<SepTileEntity> list;
            String tileSpec;
            List list2 = (List) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (list2 == null) {
                    return Unit.INSTANCE;
                }
                SeparateQsTileListLogBuffer separateQsTileListLogBuffer = this.this$0.separateQsTileLogger;
                SepUserTileConfigRepository sepUserTileConfigRepository = this.this$0;
                int i2 = sepUserTileConfigRepository.userId;
                sepUserTileConfigRepository.getClass();
                StringBuilder sb = new StringBuilder();
                List list3 = list2;
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    sb.append(((TileConfig) it.next()).shortString());
                    sb.append(" ");
                }
                separateQsTileListLogBuffer.logTile("SepUserTileConfigRepository", KeyguardLogger$.ExternalSyntheticOutline0.m("storeTiles: ", i2, " ", sb.toString()));
                SepUserTileConfigRepository sepUserTileConfigRepository2 = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
                int i3 = 0;
                for (Object obj2 : list3) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                        throw null;
                    }
                    TileConfig tileConfig = (TileConfig) obj2;
                    SepTileEntity sepTileEntity = SepTileEntityCache.INSTANCE.sepTileEntity(sepUserTileConfigRepository2.userId, tileConfig.getTileSpec());
                    sepTileEntity.setTileLoc(tileConfig.getLocation());
                    sepTileEntity.setTileSize(tileConfig.getSize());
                    Loc tileLoc = sepTileEntity.getTileLoc();
                    LocCache locCache = LocCache.INSTANCE;
                    if (Intrinsics.areEqual(tileLoc, locCache.getINVALID_LOC())) {
                        if (i3 > 0) {
                            int i5 = i3 - 1;
                            if (Intrinsics.areEqual(((TileConfig) list2.get(i5)).getLocation(), locCache.getINVALID_LOC())) {
                                tileSpec = ((TileConfig) list2.get(i5)).getTileSpec();
                            } else {
                                tileSpec = null;
                            }
                        } else {
                            tileSpec = null;
                        }
                        String tileSpec2 = i4 >= list2.size() ? null : ((TileConfig) list2.get(i4)).getTileSpec();
                        SepUserTileConfigRepository.Companion.getClass();
                        sepTileEntity.setExpandInfo(tileSpec + OplusPreventModeData.SEPARATOR + tileSpec2);
                    }
                    arrayList.add(sepTileEntity);
                    i3 = i4;
                }
                SepEditDBTileRepository sepEditDBTileRepository = this.this$0.sepEditDBTileRepository;
                int i6 = this.this$0.userId;
                this.L$0 = SpillingKt.nullOutSpilledVariable(list2);
                this.L$1 = arrayList;
                this.label = 1;
                if (sepEditDBTileRepository.setTilesByTransaction(i6, arrayList, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                list = arrayList;
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                list = (List) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            SepTileEntityCache.INSTANCE.fillCache(this.this$0.userId, list);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepUserTileConfigRepository$startCollectPersistFlow$1(SepUserTileConfigRepository sepUserTileConfigRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sepUserTileConfigRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SepUserTileConfigRepository$startCollectPersistFlow$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow = this.this$0.persistFlow;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            if (FlowExtKt.collectLatestTraced(mutableStateFlow, anonymousClass1, this) == coroutine_suspended) {
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
