package com.oplus.systemui.plugins.qs.customize.viewmodel;

import androidx.collection.FloatList$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.QSTileAndConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSHostAdapter$special$$inlined$mapNotNull$1 implements Flow {
    public /* synthetic */ Flow $this_unsafeTransform$inlined;
    public /* synthetic */ SepQSHostAdapter this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$special$$inlined$mapNotNull$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ SepQSHostAdapter this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$special$$inlined$mapNotNull$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0015  */
        public final Object emit(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            QSTileAndConfig qSTileAndConfig;
            SepQSHostAdapter sepQSHostAdapter = this.this$0;
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
            Object obj2 = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = anonymousClass1.label;
            ArrayList arrayList = null;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                FlowCollector flowCollector = this.$this_unsafeFlow;
                UserQSTileAndConfig userQSTileAndConfig = (UserQSTileAndConfig) obj;
                sepQSHostAdapter._currentTileAndUiConfig = userQSTileAndConfig;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    MobileInputLogger$.ExternalSyntheticOutline0.m(userQSTileAndConfig.getQsTiles().size(), "SepQSHostAdapter-->", "SystemUi--QuickSettings", FloatList$.ExternalSyntheticOutline0.m("tileChanged: ", userQSTileAndConfig.getUserId(), userQSTileAndConfig.getTileConfigs().size(), " ", " "));
                }
                List<QSTile> qsTiles = userQSTileAndConfig.getQsTiles();
                int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(qsTiles, 10));
                if (iMapCapacity < 16) {
                    iMapCapacity = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
                for (Object obj3 : qsTiles) {
                    linkedHashMap.put(((QSTile) obj3).getTileSpec(), obj3);
                }
                Map map = sepQSHostAdapter.qsTileMapForEditMode;
                if (map.isEmpty()) {
                    map = linkedHashMap;
                }
                ArrayList arrayList2 = new ArrayList();
                List<TileConfig> tileConfigs = userQSTileAndConfig.getTileConfigs();
                ArrayList arrayList3 = new ArrayList();
                for (TileConfig tileConfig : tileConfigs) {
                    QSTile qSTile = (QSTile) map.get(tileConfig.getTileSpec());
                    if (qSTile == null && (qSTile = (QSTile) linkedHashMap.get(tileConfig.getTileSpec())) == null) {
                        qSTile = (QSTile) sepQSHostAdapter.removeTileMap.get(tileConfig.getTileSpec());
                    }
                    if (qSTile != null) {
                        qSTileAndConfig = new QSTileAndConfig(qSTile, tileConfig);
                    } else {
                        arrayList2.add(tileConfig.getTileSpec());
                        qSTileAndConfig = null;
                    }
                    if (qSTileAndConfig != null) {
                        arrayList3.add(qSTileAndConfig);
                    }
                }
                if (arrayList2.isEmpty()) {
                    if (!sepQSHostAdapter.removeTileMap.isEmpty()) {
                        sepQSHostAdapter.separateQsTileLogger.logTile("SepQSHostAdapter", "find remove tiles: " + sepQSHostAdapter.removeTileMap.keySet());
                    }
                    SepQSHostAdapter.access$trackingMatchedIfNeeded(sepQSHostAdapter, userQSTileAndConfig.getQsTiles(), sepQSHostAdapter.repoSwitcherInteractor.getSrcTileConfigRepo());
                    arrayList = arrayList3;
                } else {
                    SeparateQsTileListLogBuffer separateQsTileListLogBuffer = sepQSHostAdapter.separateQsTileLogger;
                    StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("Data mismatch, Config size: ", userQSTileAndConfig.getTileConfigs().size(), arrayList3.size(), ", qsTile size: ", ". Missing Specs: ");
                    sbM.append(arrayList2);
                    separateQsTileListLogBuffer.logTile("SepQSHostAdapter", sbM.toString());
                }
                if (arrayList != null) {
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                    anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                    anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(arrayList);
                    anonymousClass1.I$0 = 0;
                    anonymousClass1.label = 1;
                    if (flowCollector.emit(arrayList, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        SepQSHostAdapter sepQSHostAdapter = this.this$0;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        anonymousClass2.this$0 = sepQSHostAdapter;
        VarHandle.storeStoreFence();
        Object objCollect = flow.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
