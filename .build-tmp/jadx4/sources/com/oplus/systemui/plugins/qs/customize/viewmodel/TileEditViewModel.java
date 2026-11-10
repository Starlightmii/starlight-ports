package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.content.ComponentName;
import android.os.Trace;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.compose.ui.spatial.RectList$;
import androidx.lifecycle.ViewModelKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.oplus.systemui.aod.utils.NoneLocalHbmAodRusManager$;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.Dumpable;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.data.exception.ExceptionHandler;
import com.oplus.systemui.plugins.qs.customize.data.exception.ExceptionInfo;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.TileViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.GridDataCache;
import com.oplus.systemui.qs.tiles.AliPayTile;
import com.oplus.systemui.qs.tiles.AliScanTile;
import com.oplus.systemui.qs.tiles.CalculatorTile;
import com.oplus.systemui.qs.tiles.LockTile;
import com.oplus.systemui.qs.tiles.ScreenshotTile;
import com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile;
import com.oplus.systemui.qs.tiles.WeChatScanTile;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.GridViewHolderKt;
import com.oplusos.systemui.common.viewmodel.BaseViewModel;
import com.oplusos.systemui.common.viewmodel.IUiState;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileEditViewModel extends BaseViewModel implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String PREFIX = "custom(";
    private static final String TAG = "TileEditViewModel";
    private final Flow allSelectable;
    private final AllTilesInteractor allTilesInteractor;
    private final ContextInstance contextInstance;
    private final List<String> entryTile = CollectionsKt__CollectionsKt.listOf(new String[]{AliPayTile.TILE_SPEC, AliScanTile.TILE_SPEC, CalculatorTile.TILE_SPEC, "clear", LockTile.TILE_SPEC, ScreenshotTile.TILE_SPEC, SuperPowerSaveModeTile.TILE_SPEC, WeChatScanTile.TILE_SPEC, "weixinpay", "QRCodeScanner"});
    private final PanelPluginInteractor panelPluginInteractor;
    private final SepQSHostAdapter separateQSHostAdapter;
    private final SeparateQsTileListLogBuffer separateQsTileLogger;
    private final StatisticHandler statisticHandler;
    private final TileConfigRepoSwitcher tileConfigRepoSwitcher;
    private final TileDataViewModelMapper tileDataViewModelMapper;
    private final TileEditInteractor tileEditInteractor;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00561 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ TileEditViewModel this$0;

            public /* synthetic */ C00561(int i) {
                this.$r8$classId = i;
            }

            /* JADX WARN: Code duplicated, block: B:59:0x0109  */
            /* JADX WARN: Code duplicated, block: B:60:0x010e  */
            /* JADX WARN: Code duplicated, block: B:63:0x0113  */
            /* JADX WARN: Code duplicated, block: B:64:0x0116  */
            public final Object emit(Object obj, Continuation continuation) {
                Object next;
                GridData gridData;
                Object data;
                TileViewModel tileViewModel;
                QSTile qsTile;
                TileViewModel tileViewModel2;
                QSTile qsTile2;
                switch (this.$r8$classId) {
                    case 0:
                        TileEditIntentWrapper tileEditIntentWrapper = (TileEditIntentWrapper) obj;
                        TileEditViewModel tileEditViewModel = this.this$0;
                        TileEditIntent intent = tileEditIntentWrapper.getIntent();
                        if (intent instanceof TileEditIntent.EnterEditIntent) {
                            Object objAccess$enterEdit = TileEditViewModel.access$enterEdit(tileEditViewModel, continuation);
                            return objAccess$enterEdit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAccess$enterEdit : Unit.INSTANCE;
                        }
                        if (intent instanceof TileEditIntent.ExitEditIntent) {
                            Object objAccess$exitEdit = TileEditViewModel.access$exitEdit(tileEditViewModel, continuation);
                            return objAccess$exitEdit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAccess$exitEdit : Unit.INSTANCE;
                        }
                        if (!(intent instanceof TileEditIntent.CustomTileClick)) {
                            if (intent instanceof TileEditIntent.MoveTile) {
                                Object objAccess$moveTile = TileEditViewModel.access$moveTile(tileEditViewModel, (TileEditIntent.MoveTile) tileEditIntentWrapper.getIntent(), continuation);
                                return objAccess$moveTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAccess$moveTile : Unit.INSTANCE;
                            }
                            if (intent instanceof TileEditIntent.SwapTile) {
                                Object objAccess$swapTile = TileEditViewModel.access$swapTile(tileEditViewModel, (TileEditIntent.SwapTile) tileEditIntentWrapper.getIntent(), continuation);
                                return objAccess$swapTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAccess$swapTile : Unit.INSTANCE;
                            }
                            if (tileEditIntentWrapper.getTarget() == IntentTarget.PERSONAL_LIST) {
                                TileEditIntent intent2 = tileEditIntentWrapper.getIntent();
                                tileEditViewModel.getClass();
                                return BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new processIntentOfPersonal.2(intent2, tileEditViewModel, (Continuation) null), continuation);
                            }
                            TileEditIntent intent3 = tileEditIntentWrapper.getIntent();
                            tileEditViewModel.getClass();
                            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new processIntentOfOthers.2(intent3, tileEditViewModel, (Continuation) null), continuation);
                            if (objWithContext != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                objWithContext = Unit.INSTANCE;
                            }
                            return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
                        }
                        ComponentName tile = ((TileEditIntent.CustomTileClick) tileEditIntentWrapper.getIntent()).getTile();
                        List<GridData> otherAddedTiles = tileEditViewModel.getNormalState().getOtherAddedTiles();
                        String strM = Bundler$.ExternalSyntheticOutline0.m(TileEditViewModel.PREFIX, tile.flattenToShortString(), ")");
                        Iterator<T> it = otherAddedTiles.iterator();
                        do {
                            if (it.hasNext()) {
                                next = it.next();
                                Object data2 = ((GridData) next).getData();
                                tileViewModel2 = data2 instanceof TileViewModel ? (TileViewModel) data2 : null;
                            } else {
                                next = null;
                            }
                            gridData = (GridData) next;
                            if (gridData != null) {
                                data = gridData.getData();
                            } else {
                                data = null;
                            }
                            if (data instanceof TileViewModel) {
                                tileViewModel = (TileViewModel) data;
                            } else {
                                tileViewModel = null;
                            }
                            if (tileViewModel != null && (qsTile = tileViewModel.getQsTile()) != null) {
                                qsTile.click((Expandable) null);
                            }
                            return Unit.INSTANCE;
                        } while (!Intrinsics.areEqual((tileViewModel2 == null || (qsTile2 = tileViewModel2.getQsTile()) == null) ? null : qsTile2.getTileSpec(), strM));
                        gridData = (GridData) next;
                        if (gridData != null) {
                            data = gridData.getData();
                        } else {
                            data = null;
                        }
                        if (data instanceof TileViewModel) {
                            tileViewModel = (TileViewModel) data;
                        } else {
                            tileViewModel = null;
                        }
                        if (tileViewModel != null) {
                            qsTile.click((Expandable) null);
                        }
                        return Unit.INSTANCE;
                    case 1:
                        ExceptionInfo exceptionInfo = (ExceptionInfo) obj;
                        if (exceptionInfo.getExceptionId() == 100) {
                            TileEditViewModel tileEditViewModel2 = this.this$0;
                            Object objSendUiEffectToTarget = tileEditViewModel2.sendUiEffectToTarget(IntentTarget.PERSONAL_LIST, new AnonymousClass2.1.1(exceptionInfo, tileEditViewModel2, (Continuation) null), continuation);
                            return objSendUiEffectToTarget == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSendUiEffectToTarget : Unit.INSTANCE;
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "TileEditViewModel-->".concat(String.valueOf(exceptionInfo)));
                        }
                        return Unit.INSTANCE;
                    default:
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("[enableShowTileName] ", "TileEditViewModel-->", "SystemUi--QuickSettings", zBooleanValue);
                        }
                        TileEditViewModel tileEditViewModel3 = this.this$0;
                        Object objSendUiState = tileEditViewModel3.sendUiState(TileEditState.Normal.copy$default(tileEditViewModel3.getNormalState(), null, null, null, zBooleanValue, false, 23), continuation);
                        return objSendUiState == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSendUiState : Unit.INSTANCE;
                }
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileEditViewModel.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow intentFlow = TileEditViewModel.this.getIntentFlow();
                TileEditViewModel tileEditViewModel = TileEditViewModel.this;
                C00561 c00561 = new C00561(0);
                c00561.this$0 = tileEditViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (FlowExtKt.collectTraced(intentFlow, "TileEditViewModel-collect-intentFlow", c00561, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileEditViewModel.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow exceptionFlow = ExceptionHandler.INSTANCE.getExceptionFlow();
                TileEditViewModel tileEditViewModel = TileEditViewModel.this;
                AnonymousClass1.C00561 c00561 = new AnonymousClass1.C00561(i2);
                c00561.this$0 = tileEditViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (FlowExtKt.collectTraced(exceptionFlow, "TileEditViewModel-collect-exception", c00561, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileEditViewModel.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow enableShowTileName = OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName();
                TileEditViewModel tileEditViewModel = TileEditViewModel.this;
                AnonymousClass1.C00561 c00561 = new AnonymousClass1.C00561(2);
                c00561.this$0 = tileEditViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (FlowExtKt.collectTraced(enableShowTileName, "TileEditViewModel-collect-enableShowTileName", c00561, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function3 {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;

            public final Object invoke(Object obj, Object obj2, Object obj3) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
                anonymousClass1.L$0 = (List) obj;
                anonymousClass1.L$1 = (List) obj2;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                List list = (List) this.L$0;
                List list2 = (List) this.L$1;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return new Pair(list, list2);
                }
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$4$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function3 {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;

            public final Object invoke(Object obj, Object obj2, Object obj3) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(3, (Continuation) obj3);
                anonymousClass2.L$0 = (Pair) obj;
                anonymousClass2.L$1 = (List) obj2;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Pair pair = (Pair) this.L$0;
                List list = (List) this.L$1;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return new Pair(pair, list);
                }
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$4$3, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass3 implements FlowCollector {
            public /* synthetic */ TileEditViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:7:0x0017  */
            public final Object emit(Pair pair, Continuation continuation) {
                TileEditViewModel$4$3$emit$1 tileEditViewModel$4$3$emit$1;
                Object obj;
                long j;
                if (continuation instanceof TileEditViewModel$4$3$emit$1) {
                    tileEditViewModel$4$3$emit$1 = (TileEditViewModel$4$3$emit$1) continuation;
                    int i = tileEditViewModel$4$3$emit$1.label;
                    if ((i & Integer.MIN_VALUE) != 0) {
                        tileEditViewModel$4$3$emit$1.label = i - Integer.MIN_VALUE;
                    } else {
                        tileEditViewModel$4$3$emit$1 = new TileEditViewModel$4$3$emit$1(this, continuation);
                    }
                } else {
                    tileEditViewModel$4$3$emit$1 = new TileEditViewModel$4$3$emit$1(this, continuation);
                }
                Object obj2 = tileEditViewModel$4$3$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i2 = tileEditViewModel$4$3$emit$1.label;
                if (i2 == 0) {
                    Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj2);
                    TileEditViewModel tileEditViewModel = this.this$0;
                    long jNanoTime = System.nanoTime();
                    Trace.traceBegin(8L, "VM map data");
                    Pair<List<GridData>, List<Loc>> map = tileEditViewModel.tileDataViewModelMapper.map((Pair) pair.getFirst());
                    objectRefM.element = map;
                    GridDataCache.INSTANCE.refreshCache((List) map.getFirst());
                    List listSubList = ((List) ((Pair) objectRefM.element).getFirst()).subList(0, ((List) ((Pair) objectRefM.element).getSecond()).size());
                    List<GridData> list = listSubList;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            if (((GridData) it.next()).getData() == null) {
                                listSubList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                                for (GridData gridDataCopy$default : list) {
                                    if (gridDataCopy$default.getData() == null) {
                                        Iterator it2 = ((Iterable) pair.getSecond()).iterator();
                                        while (true) {
                                            if (!it2.hasNext()) {
                                                obj = null;
                                                break;
                                            }
                                            Object next = it2.next();
                                            if (GridViewHolderKt.toUniqueLong(((QsPanelPlugin) next).getSpec()) == gridDataCopy$default.getUniqueId()) {
                                                obj = next;
                                                break;
                                            }
                                        }
                                        gridDataCopy$default = GridData.copy$default(gridDataCopy$default, null, obj, 27);
                                    }
                                    listSubList.add(gridDataCopy$default);
                                }
                                break;
                            }
                        }
                    }
                    TileEditState.Normal normalCopy$default = TileEditState.Normal.copy$default(tileEditViewModel.getNormalState(), ViewDataExtKt.mergeTwoColsForListGridData(listSubList), ViewDataExtKt.mergeTwoColsForListLoc((List) ((Pair) objectRefM.element).getSecond()), ((List) ((Pair) objectRefM.element).getFirst()).subList(((List) ((Pair) objectRefM.element).getSecond()).size(), ((List) ((Pair) objectRefM.element).getFirst()).size()), false, false, 24);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "TileEditViewModel-->".concat("[sendUiState] " + normalCopy$default));
                    }
                    tileEditViewModel$4$3$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(pair);
                    tileEditViewModel$4$3$emit$1.L$1 = SpillingKt.nullOutSpilledVariable(objectRefM);
                    tileEditViewModel$4$3$emit$1.L$2 = SpillingKt.nullOutSpilledVariable(listSubList);
                    tileEditViewModel$4$3$emit$1.I$0 = 0;
                    tileEditViewModel$4$3$emit$1.J$0 = jNanoTime;
                    tileEditViewModel$4$3$emit$1.I$1 = 0;
                    tileEditViewModel$4$3$emit$1.label = 1;
                    if (tileEditViewModel.sendUiState((IUiState) normalCopy$default, (Continuation) tileEditViewModel$4$3$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j = jNanoTime;
                } else {
                    if (i2 != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    j = tileEditViewModel$4$3$emit$1.J$0;
                    ResultKt.throwOnFailure(obj2);
                }
                Trace.traceEnd(8L);
                long jNanoTime2 = System.nanoTime() - j;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jNanoTime2, " tileDataViewModelMapper.map cost ", " in nano", "TileEditViewModel-->"));
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileEditViewModel.this.new AnonymousClass4(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowFlowCombine = FlowKt.flowCombine(FlowKt.flowCombine(TileEditViewModel.this.separateQSHostAdapter.m175getCurrentTileAndUiConfig(), TileEditViewModel.this.allTilesInteractor.getTileTypeConfigFlow(), new AnonymousClass1(3, null)), TileEditViewModel.this.panelPluginInteractor.getDataFlow(), new AnonymousClass2(3, null));
                TileEditViewModel tileEditViewModel = TileEditViewModel.this;
                AnonymousClass3 anonymousClass3 = new AnonymousClass3();
                anonymousClass3.this$0 = tileEditViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (FlowExtKt.collectTraced(flowFlowCombine, "TileEditViewModel-collect-separateQSHostAdapter", anonymousClass3, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$addOneTile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02361 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C02361(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileEditViewModel.this.addOneTile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02371 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C02371(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileEditViewModel.this.getAddedTileAndGoToTargetPage(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$sendIntent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02381 extends SuspendLambda implements Function2 {
        final /* synthetic */ TileEditIntent $intent;
        final /* synthetic */ IntentTarget $target;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02381(TileEditIntent tileEditIntent, IntentTarget intentTarget, Continuation continuation) {
            super(2, continuation);
            this.$intent = tileEditIntent;
            this.$target = intentTarget;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileEditViewModel.this.new C02381(this.$intent, this.$target, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Job jobSendIntent = TileEditViewModel.this.sendIntent(new TileEditIntentWrapper(this.$intent, this.$target));
                this.label = 1;
                if (jobSendIntent.join(this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$sendUiEffectToTarget$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02391 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C02391(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileEditViewModel.this.sendUiEffectToTarget(null, null, this);
        }
    }

    public TileEditViewModel(PanelPluginInteractor panelPluginInteractor, TileEditInteractor tileEditInteractor, SepQSHostAdapter sepQSHostAdapter, TileDataViewModelMapper tileDataViewModelMapper, AllTilesInteractor allTilesInteractor, TileConfigRepoSwitcher tileConfigRepoSwitcher, StatisticHandler statisticHandler, ContextInstance contextInstance, SeparateQsTileListLogBuffer separateQsTileListLogBuffer) {
        this.panelPluginInteractor = panelPluginInteractor;
        this.tileEditInteractor = tileEditInteractor;
        this.separateQSHostAdapter = sepQSHostAdapter;
        this.tileDataViewModelMapper = tileDataViewModelMapper;
        this.allTilesInteractor = allTilesInteractor;
        this.tileConfigRepoSwitcher = tileConfigRepoSwitcher;
        this.statisticHandler = statisticHandler;
        this.contextInstance = contextInstance;
        this.separateQsTileLogger = separateQsTileListLogBuffer;
        this.allSelectable = FlowKt.flowCombine(allTilesInteractor.getAllSelectableData(), getUiStateFlow(), new TileEditViewModel$allSelectable$1(3, null));
        CoroutineTracingKt.launchTraced$default(ViewModelKt.getViewModelScope(this), "TileEditViewModel-launch-for-intentFlow", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 6);
        CoroutineTracingKt.launchTraced$default(ViewModelKt.getViewModelScope(this), "TileEditViewModel-launch-for-exception", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 6);
        CoroutineTracingKt.launchTraced$default(ViewModelKt.getViewModelScope(this), "TileEditViewModel-launch-for-enableShowTileName", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(null), 6);
        CoroutineTracingKt.launchTraced$default(ViewModelKt.getViewModelScope(this), "TileEditViewModel-launch-for-separateQSHostAdapter", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(null), 6);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x009a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b1, code lost:
    
        if (r14.startQueryTileByPM(r0) == r1) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$enterEdit(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel r14, kotlin.coroutines.Continuation r15) {
        /*
            r14.getClass()
            boolean r0 = r15 instanceof com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$enterEdit$1
            if (r0 == 0) goto L16
            r0 = r15
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$enterEdit$1 r0 = (com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$enterEdit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$enterEdit$1 r0 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$enterEdit$1
            r0.<init>(r14, r15)
        L1b:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L49
            if (r2 == r6) goto L45
            if (r2 == r5) goto L41
            if (r2 == r4) goto L3d
            if (r2 != r3) goto L36
            kotlin.ResultKt.throwOnFailure(r15)
            goto Lb4
        L36:
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r14)
            r14 = 0
            return r14
        L3d:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L9a
        L41:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L8f
        L45:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L84
        L49:
            kotlin.ResultKt.throwOnFailure(r15)
            boolean r15 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r15 == 0) goto L61
            java.lang.String r15 = "QuickSettings"
            boolean r15 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r15)
            if (r15 == 0) goto L61
            java.lang.String r15 = "SystemUi--QuickSettings"
            java.lang.String r2 = "TileEditViewModel-->[enterEdit]"
            android.util.Log.d(r15, r2)
        L61:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r15 = r14.getNormalState()
            boolean r15 = r15.isEditMode
            if (r15 == 0) goto L6c
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L6c:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r7 = r14.getNormalState()
            r12 = 1
            r13 = 15
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r15 = com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState.Normal.copy$default(r7, r8, r9, r10, r11, r12, r13)
            r0.label = r6
            java.lang.Object r15 = r14.sendUiState(r15, r0)
            if (r15 != r1) goto L84
            goto Lb3
        L84:
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r15 = r14.separateQSHostAdapter
            r0.label = r5
            java.lang.Object r15 = r15.enterEditMode(r0)
            if (r15 != r1) goto L8f
            goto Lb3
        L8f:
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor r15 = r14.tileEditInteractor
            r0.label = r4
            java.lang.Object r15 = r15.enterEditMode(r0)
            if (r15 != r1) goto L9a
            goto Lb3
        L9a:
            com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler r15 = r14.statisticHandler
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r2 = r14.getNormalState()
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor r4 = r14.panelPluginInteractor
            java.lang.String r2 = r15.getTilesMessage(r2, r4)
            r15.setBeforeTilesMessage(r2)
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor r14 = r14.allTilesInteractor
            r0.label = r3
            java.lang.Object r14 = r14.startQueryTileByPM(r0)
            if (r14 != r1) goto Lb4
        Lb3:
            return r1
        Lb4:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.access$enterEdit(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a0, code lost:
    
        if (r11.sendUiState((com.oplusos.systemui.common.viewmodel.IUiState) r12, (kotlin.coroutines.Continuation) r0) == r1) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$exitEdit(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel r11, kotlin.coroutines.Continuation r12) {
        /*
            r11.getClass()
            boolean r0 = r12 instanceof com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$exitEdit$1
            if (r0 == 0) goto L16
            r0 = r12
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$exitEdit$1 r0 = (com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$exitEdit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$exitEdit$1 r0 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$exitEdit$1
            r0.<init>(r11, r12)
        L1b:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L42
            if (r2 == r5) goto L3e
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L33
            kotlin.ResultKt.throwOnFailure(r12)
            goto La3
        L33:
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r11)
            r11 = 0
            return r11
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L7b
        L3e:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L70
        L42:
            kotlin.ResultKt.throwOnFailure(r12)
            boolean r12 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r12 == 0) goto L5a
            java.lang.String r12 = "QuickSettings"
            boolean r12 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r12)
            if (r12 == 0) goto L5a
            java.lang.String r12 = "SystemUi--QuickSettings"
            java.lang.String r2 = "TileEditViewModel-->[exitEdit]"
            android.util.Log.d(r12, r2)
        L5a:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r12 = r11.getNormalState()
            boolean r12 = r12.isEditMode
            if (r12 != 0) goto L65
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L65:
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor r12 = r11.tileEditInteractor
            r0.label = r5
            java.lang.Object r12 = r12.exitEditMode(r0)
            if (r12 != r1) goto L70
            goto La2
        L70:
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r12 = r11.separateQSHostAdapter
            r0.label = r4
            java.lang.Object r12 = r12.exitEditMode(r0)
            if (r12 != r1) goto L7b
            goto La2
        L7b:
            com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler r12 = r11.statisticHandler
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r2 = r11.getNormalState()
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor r4 = r11.panelPluginInteractor
            r12.uploadStatistic(r2, r4)
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor r12 = r11.allTilesInteractor
            r12.stopQueryTileByPM()
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r4 = r11.getNormalState()
            r9 = 0
            r10 = 15
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal r12 = com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState.Normal.copy$default(r4, r5, r6, r7, r8, r9, r10)
            r0.label = r3
            java.lang.Object r11 = r11.sendUiState(r12, r0)
            if (r11 != r1) goto La3
        La2:
            return r1
        La3:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.access$exitEdit(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a1, code lost:
    
        if (r7.sendUiEffectToTarget(r2, r4, r0) == r1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bd, code lost:
    
        if (r7.moveTile(r9, r2, r3, r0) == r1) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$moveTile(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel r7, com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent.MoveTile r8, kotlin.coroutines.Continuation r9) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.access$moveTile(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel, com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent$MoveTile, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object access$swapTile(TileEditViewModel tileEditViewModel, TileEditIntent.SwapTile swapTile, Continuation continuation) {
        Object objSwapTile = tileEditViewModel.tileEditInteractor.swapTile(swapTile.getSpecOfPersonalArea(), swapTile.getSpecOfOthersArea(), continuation);
        return objSwapTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSwapTile : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0127  */
    /* JADX WARN: Code duplicated, block: B:51:0x013b  */
    /* JADX WARN: Code duplicated, block: B:53:0x0143  */
    /* JADX WARN: Code duplicated, block: B:54:0x0146  */
    /* JADX WARN: Code duplicated, block: B:78:0x021d A[PHI: r1 r2 r12 r14 r15
      0x021d: PHI (r1v3 com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget) = 
      (r1v1 com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget)
      (r1v1 com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget)
      (r1v5 com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget)
     binds: [B:55:0x0147, B:57:0x014d, B:74:0x01e9] A[DONT_GENERATE, DONT_INLINE]
      0x021d: PHI (r2v8 boolean) = (r2v7 boolean), (r2v7 boolean), (r2v10 boolean) binds: [B:55:0x0147, B:57:0x014d, B:74:0x01e9] A[DONT_GENERATE, DONT_INLINE]
      0x021d: PHI (r12v6 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent$AddOneTileIntent) = 
      (r12v5 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent$AddOneTileIntent)
      (r12v5 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent$AddOneTileIntent)
      (r12v7 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent$AddOneTileIntent)
     binds: [B:55:0x0147, B:57:0x014d, B:74:0x01e9] A[DONT_GENERATE, DONT_INLINE]
      0x021d: PHI (r14v6 com.oplusos.systemui.common.viewholder.GridData) = 
      (r14v5 com.oplusos.systemui.common.viewholder.GridData)
      (r14v5 com.oplusos.systemui.common.viewholder.GridData)
      (r14v7 com.oplusos.systemui.common.viewholder.GridData)
     binds: [B:55:0x0147, B:57:0x014d, B:74:0x01e9] A[DONT_GENERATE, DONT_INLINE]
      0x021d: PHI (r15v3 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal) = 
      (r15v2 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal)
      (r15v2 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal)
      (r15v4 com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState$Normal)
     binds: [B:55:0x0147, B:57:0x014d, B:74:0x01e9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:8:0x001a  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e8, code lost:
    
        if (sendUiEffectToTarget(r1, r4, r9) == r3) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01e0, code lost:
    
        if (r4.addTile(r5, 0, r7, r10, r9) == r3) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01e4, code lost:
    
        r6 = r1;
        r1 = r2;
        r7 = r12;
        r4 = r14;
        r5 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x021a, code lost:
    
        if (r4.addTileNonLoc(r6, r7, r9) == r3) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0248, code lost:
    
        if (sendUiEffectToTarget(r1, r4, r9) == r3) goto L80;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addOneTile(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent.AddOneTileIntent r17, com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r18, kotlin.coroutines.jvm.internal.ContinuationImpl r19) {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.addOneTile(com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent$AddOneTileIntent, com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        this.tileEditInteractor.dump(printWriter, strArr);
    }

    public final List<SpanSize> getActualSupportSize(int i, List<SpanSize> list) {
        List<SpanSize> actualSupportSize = this.tileEditInteractor.getActualSupportSize(i);
        return actualSupportSize == null ? list : actualSupportSize;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b7, code lost:
    
        if (sendUiEffectToTarget(r13, r14, r0) == r1) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAddedTileAndGoToTargetPage(com.oplusos.systemui.common.viewholder.GridData r11, java.util.List r12, com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.C02371
            if (r0 == 0) goto L13
            r0 = r14
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$1 r0 = (com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.C02371) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$1 r0 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L52
            if (r2 == r5) goto L40
            if (r2 != r4) goto L3a
            java.lang.Object r10 = r0.L$2
            com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r10 = (com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget) r10
            java.lang.Object r10 = r0.L$1
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r10 = r0.L$0
            com.oplusos.systemui.common.viewholder.GridData r10 = (com.oplusos.systemui.common.viewholder.GridData) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lbb
        L3a:
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r10)
            return r3
        L40:
            java.lang.Object r11 = r0.L$2
            r13 = r11
            com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r13 = (com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget) r13
            java.lang.Object r11 = r0.L$1
            r12 = r11
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r11 = r0.L$0
            com.oplusos.systemui.common.viewholder.GridData r11 = (com.oplusos.systemui.common.viewholder.GridData) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L9a
        L52:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r12
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            boolean r2 = r14 instanceof java.util.Collection
            if (r2 == 0) goto L66
            r2 = r14
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L66
            goto Lba
        L66:
            java.util.Iterator r14 = r14.iterator()
        L6a:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto Lba
            java.lang.Object r2 = r14.next()
            com.oplusos.systemui.common.viewholder.GridData r2 = (com.oplusos.systemui.common.viewholder.GridData) r2
            long r6 = r2.getUniqueId()
            long r8 = r11.getUniqueId()
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L6a
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$3 r14 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$3
            r14.<init>(r11, r3)
            r0.L$0 = r11
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$1 = r2
            r0.L$2 = r13
            r0.label = r5
            java.lang.Object r14 = r10.sendUiEffectToTarget(r13, r14, r0)
            if (r14 != r1) goto L9a
            goto Lb9
        L9a:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$4 r14 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$getAddedTileAndGoToTargetPage$4
            r14.<init>(r11, r3)
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r0.L$0 = r11
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$1 = r11
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r0.L$2 = r11
            r0.label = r4
            java.lang.Object r10 = r10.sendUiEffectToTarget(r13, r14, r0)
            if (r10 != r1) goto Lbb
        Lb9:
            return r1
        Lba:
            r5 = 0
        Lbb:
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.getAddedTileAndGoToTargetPage(com.oplusos.systemui.common.viewholder.GridData, java.util.List, com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Flow getAllSelectable() {
        return this.allSelectable;
    }

    public final TileEditState.Normal getNormalState() {
        Object value = getUiStateFlow().getValue();
        TileEditState.Normal normal = value instanceof TileEditState.Normal ? (TileEditState.Normal) value : null;
        return normal == null ? new TileEditState.Normal(CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), false, false) : normal;
    }

    public final SeparateQsTileListLogBuffer getSeparateQsTileLogger() {
        return this.separateQsTileLogger;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0066  */
    /* JADX WARN: Code duplicated, block: B:49:0x00f5  */
    public final Pair<String, String> getSpecSizeAndLoc(String str) {
        int i;
        String strM;
        boolean zAreEqual;
        boolean zAreEqual2;
        TileEditState.Normal normalState = getNormalState();
        List<GridData> allPersonalTiles = normalState.getAllPersonalTiles();
        List<Loc> allPersonalTilesLoc = normalState.getAllPersonalTilesLoc();
        List<GridData> otherAddedTiles = normalState.getOtherAddedTiles();
        Iterator<GridData> it = allPersonalTiles.iterator();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = -1;
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            try {
                zAreEqual2 = Intrinsics.areEqual(TileDataViewModelMapper.Companion.getSpecFromGridData(it.next()), str);
            } catch (IllegalStateException unused) {
                zAreEqual2 = true;
            }
            if (zAreEqual2) {
                break;
            }
            i3++;
        }
        if (i3 != -1) {
            GridData gridData = allPersonalTiles.get(i3);
            if (gridData.getData() != null) {
                TileDataViewModelMapper.Companion companion = TileDataViewModelMapper.Companion;
                String specFromGridData = companion.getSpecFromGridData(gridData);
                int stateFromGridData = companion.getStateFromGridData(gridData);
                if (!this.entryTile.contains(specFromGridData)) {
                    i = stateFromGridData;
                } else if (stateFromGridData == 0) {
                    i = 3;
                } else if (stateFromGridData != 1) {
                    i = stateFromGridData;
                } else {
                    i = 4;
                }
            }
            Loc loc = allPersonalTilesLoc.get(i3);
            int pageIndex = loc.getPageIndex();
            int startCol = loc.getStartCol();
            int startRow = loc.getStartRow();
            int rowSpan = gridData.getSize().getRowSpan();
            int colSpan = DataConvertorKt.revertToTwoCols(gridData.getSize()).getColSpan();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("-", pageIndex, startCol, "-", "-");
            RectList$.ExternalSyntheticOutline0.m(startRow, rowSpan, "--", "-", sbM);
            sbM.append(colSpan);
            strM = Bundler$.ExternalSyntheticOutline0.m(str, sbM.toString());
        } else {
            Iterator<GridData> it2 = otherAddedTiles.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    i2 = -1;
                    break;
                }
                try {
                    zAreEqual = Intrinsics.areEqual(TileDataViewModelMapper.Companion.getSpecFromGridData(it2.next()), str);
                } catch (IllegalStateException unused2) {
                    zAreEqual = true;
                }
                if (zAreEqual) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return new Pair<>(str, ShadeExpansionHelperImpl.VAL_INVALID);
            }
            GridData gridData2 = otherAddedTiles.get(i2);
            if (gridData2.getData() != null) {
                TileDataViewModelMapper.Companion companion2 = TileDataViewModelMapper.Companion;
                String specFromGridData2 = companion2.getSpecFromGridData(gridData2);
                int stateFromGridData2 = companion2.getStateFromGridData(gridData2);
                if (!this.entryTile.contains(specFromGridData2)) {
                    i = stateFromGridData2;
                } else if (stateFromGridData2 == 0) {
                    i = 3;
                } else if (stateFromGridData2 != 1) {
                    i = stateFromGridData2;
                } else {
                    i = 4;
                }
            }
            strM = str + "-position=" + i2 + "--" + gridData2.getSize().getRowSpan() + "-" + gridData2.getSize().getColSpan();
        }
        return new Pair<>(strM, String.valueOf(i));
    }

    public final Object removeOneTile(TileEditIntent.RemoveOneTileIntent removeOneTileIntent, IntentTarget intentTarget, SuspendLambda suspendLambda) {
        Object next;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileEditViewModel-->".concat("[removeOneTile] " + removeOneTileIntent));
        }
        GridData gridTileInfo = removeOneTileIntent.getGridTileInfo();
        if (gridTileInfo.getData() == null) {
            Iterator it = ((Iterable) this.panelPluginInteractor.getDataFlow().getValue()).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (GridViewHolderKt.toUniqueLong(((QsPanelPlugin) next).getSpec()) != gridTileInfo.getUniqueId());
            gridTileInfo = GridData.copy$default(gridTileInfo, null, next, 27);
        }
        TileDataViewModelMapper.Companion companion = TileDataViewModelMapper.Companion;
        this.statisticHandler.removeOneTile(companion.getSpecFromGridData(gridTileInfo), gridTileInfo.getSize(), intentTarget);
        Object objRemoveTiles = this.tileEditInteractor.removeTiles(CollectionsKt__CollectionsJVMKt.listOf(companion.getSpecFromGridData(gridTileInfo)), intentTarget, suspendLambda);
        return objRemoveTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveTiles : Unit.INSTANCE;
    }

    public final void sendIntent(TileEditIntent tileEditIntent, IntentTarget intentTarget) {
        BuildersKt.runBlocking$default(new C02381(tileEditIntent, intentTarget, null));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0084, code lost:
    
        if (sendUiEffect((com.oplusos.systemui.common.viewmodel.IUiEffect) r2, (kotlin.coroutines.Continuation) r0) == r1) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendUiEffectToTarget(com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.C02391
            if (r0 == 0) goto L13
            r0 = r8
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$sendUiEffectToTarget$1 r0 = (com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.C02391) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$sendUiEffectToTarget$1 r0 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel$sendUiEffectToTarget$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3f
            if (r2 != r3) goto L38
            java.lang.Object r5 = r0.L$2
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect r5 = (com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect) r5
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r5 = r0.L$0
            com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r5 = (com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L87
        L38:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
            r5 = 0
            return r5
        L3f:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r6 = r0.L$0
            com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget r6 = (com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L60
        L4c:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r6
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r8 = r7.invoke(r0)
            if (r8 != r1) goto L60
            goto L86
        L60:
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect r8 = (com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect) r8
            if (r8 == 0) goto L87
            com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffectWrapper r2 = new com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffectWrapper
            r2.<init>(r8, r6)
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$2 = r6
            r6 = 0
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.sendUiEffect(r2, r0)
            if (r5 != r1) goto L87
        L86:
            return r1
        L87:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel.sendUiEffectToTarget(com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.oplusos.systemui.common.viewmodel.BaseViewModel
    public TileEditState initUIState() {
        return TileEditState.Loading.INSTANCE;
    }
}
