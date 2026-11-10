package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.util.Log;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.qs.QSHostWrapper;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.Dumpable;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSHostAdapter implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String PREFIX = "custom(";
    private static final String TAG = "SepQSHostAdapter";
    public UserQSTileAndConfig _currentTileAndUiConfig;
    private final Flow currentTileAndUiConfig;
    private final QSHostWrapper hostWrapper;
    private final CoroutineScope mainScope;
    private final TileConfigRepoSwitcher repoSwitcherInteractor;
    private final SeparateQsTileListLogBuffer separateQsTileLogger;
    private Job trackingJob;
    public volatile int currentUserId = -1;
    private final Map<String, QSTile> qsTileMapForEditMode = new LinkedHashMap();
    private final Map<String, QSTile> removeTileMap = new LinkedHashMap();

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00551 implements FlowCollector {
            public /* synthetic */ SepQSHostAdapter this$0;

            public final Object emit(Object obj, Continuation continuation) {
                List list = (List) obj;
                SepQSHostAdapter sepQSHostAdapter = this.this$0;
                if (((Number) sepQSHostAdapter.repoSwitcherInteractor.getScene().getValue()).intValue() == 2) {
                    Map map = sepQSHostAdapter.removeTileMap;
                    List list2 = list;
                    int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                    if (iMapCapacity < 16) {
                        iMapCapacity = 16;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
                    for (Object obj2 : list2) {
                        linkedHashMap.put(((QSTile) obj2).getTileSpec(), obj2);
                    }
                    map.putAll(linkedHashMap);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepQSHostAdapter-->".concat("removeTiles updated in background: " + sepQSHostAdapter.removeTileMap.keySet()));
                    }
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SepQSHostAdapter.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow removeTiles = SepQSHostAdapter.this.hostWrapper.getRemoveTiles();
                SepQSHostAdapter sepQSHostAdapter = SepQSHostAdapter.this;
                C00551 c00551 = new C00551();
                c00551.this$0 = sepQSHostAdapter;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (removeTiles.collect(c00551, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$enterEditMode$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SepQSHostAdapter.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SepQSHostAdapter-->[enterEditMode] create cache QSTile collection ");
                }
                TileConfigRepoSwitcher tileConfigRepoSwitcher = SepQSHostAdapter.this.repoSwitcherInteractor;
                this.label = 1;
                if (tileConfigRepoSwitcher.setCurrentScene(2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            SepQSHostAdapter.this.qsTileMapForEditMode.clear();
            Map map = SepQSHostAdapter.this.qsTileMapForEditMode;
            UserQSTileAndConfig userQSTileAndConfig = SepQSHostAdapter.this._currentTileAndUiConfig;
            List<QSTile> qsTiles = (userQSTileAndConfig != null ? userQSTileAndConfig : null).getQsTiles();
            int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(qsTiles, 10));
            if (iMapCapacity < 16) {
                iMapCapacity = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
            for (Object obj2 : qsTiles) {
                linkedHashMap.put(((QSTile) obj2).getTileSpec(), obj2);
            }
            map.putAll(linkedHashMap);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$exitEditMode$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02352 extends SuspendLambda implements Function2 {
        int label;

        public C02352(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SepQSHostAdapter.this.new C02352(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SepQSHostAdapter-->[exitEditMode] clear cache QSTile collection");
                }
                TileConfigRepoSwitcher tileConfigRepoSwitcher = SepQSHostAdapter.this.repoSwitcherInteractor;
                this.label = 1;
                if (tileConfigRepoSwitcher.setCurrentScene(1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            SepQSHostAdapter.this.qsTileMapForEditMode.clear();
            SepQSHostAdapter.this.removeTileMap.clear();
            return Unit.INSTANCE;
        }
    }

    public SepQSHostAdapter(QSHostWrapper qSHostWrapper, TileConfigRepoSwitcher tileConfigRepoSwitcher, CoroutineScope coroutineScope, SeparateQsTileListLogBuffer separateQsTileListLogBuffer) {
        this.hostWrapper = qSHostWrapper;
        this.repoSwitcherInteractor = tileConfigRepoSwitcher;
        this.mainScope = coroutineScope;
        this.separateQsTileLogger = separateQsTileListLogBuffer;
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        Flow flowTransformLatest = FlowKt.transformLatest(qSHostWrapper.getUserId(), new SepQSHostAdapter$special$$inlined$flatMapLatest$1(this, null));
        SepQSHostAdapter$special$$inlined$mapNotNull$1 sepQSHostAdapter$special$$inlined$mapNotNull$1 = new SepQSHostAdapter$special$$inlined$mapNotNull$1();
        sepQSHostAdapter$special$$inlined$mapNotNull$1.$this_unsafeTransform$inlined = flowTransformLatest;
        sepQSHostAdapter$special$$inlined$mapNotNull$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.currentTileAndUiConfig = FlowExtKt.traceAs(sepQSHostAdapter$special$$inlined$mapNotNull$1, "SepQSHostAdapter-flow-currentTileAndUiConfig");
    }

    public static final void access$trackingMatchedIfNeeded(SepQSHostAdapter sepQSHostAdapter, List list, ISepTileConfigRepository iSepTileConfigRepository) {
        Job job = sepQSHostAdapter.trackingJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        if (iSepTileConfigRepository instanceof SepUserTileConfigRepository) {
            SepUserTileConfigRepository sepUserTileConfigRepository = (SepUserTileConfigRepository) iSepTileConfigRepository;
            if (sepUserTileConfigRepository.defaultTilesMatched) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                QSTile qSTile = (QSTile) obj;
                if (!qSTile.isTileReady() && StringsKt__StringsJVMKt.startsWith(qSTile.getTileSpec(), PREFIX, false)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                sepUserTileConfigRepository.updateMatchedState(true);
                return;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(sepQSHostAdapter.currentUserId, arrayList.size(), "trackingMatchedIfNeeded: ", " "), null, 8);
            Job jobStartTracking = new TileReadyTracker(arrayList).startTracking(sepQSHostAdapter.mainScope, 8000L);
            sepQSHostAdapter.trackingJob = jobStartTracking;
            if (jobStartTracking != null) {
                SepQSHostAdapter$$ExternalSyntheticLambda0 sepQSHostAdapter$$ExternalSyntheticLambda0 = new SepQSHostAdapter$$ExternalSyntheticLambda0();
                sepQSHostAdapter$$ExternalSyntheticLambda0.f$0 = iSepTileConfigRepository;
                VarHandle.storeStoreFence();
                jobStartTracking.invokeOnCompletion(sepQSHostAdapter$$ExternalSyntheticLambda0);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SepQSHostAdapter:");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    userId: ", this.currentUserId, printWriter);
        UserQSTileAndConfig userQSTileAndConfig = this._currentTileAndUiConfig;
        if (userQSTileAndConfig != null) {
            if (userQSTileAndConfig == null) {
                userQSTileAndConfig = null;
            }
            printWriter.println("    configs: " + userQSTileAndConfig);
        }
        printWriter.println("    removeTileMap keys: " + this.removeTileMap.keySet());
        this.repoSwitcherInteractor.dump(printWriter, strArr);
    }

    public final Object enterEditMode(Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new AnonymousClass2(null), continuation);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final Object exitEditMode(Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new C02352(null), continuation);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final UserQSTileAndConfig getCurrentTileAndUiConfig() {
        UserQSTileAndConfig userQSTileAndConfig = this._currentTileAndUiConfig;
        if (userQSTileAndConfig == null) {
            return null;
        }
        return userQSTileAndConfig;
    }

    /* JADX INFO: renamed from: getCurrentTileAndUiConfig, reason: collision with other method in class */
    public final Flow m175getCurrentTileAndUiConfig() {
        return this.currentTileAndUiConfig;
    }
}
