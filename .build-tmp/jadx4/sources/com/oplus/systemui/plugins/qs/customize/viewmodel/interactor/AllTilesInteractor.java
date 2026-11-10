package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.content.Context;
import android.content.res.Resources;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.SepQSTileQueryHelper;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.addtile.AddTileCalculator;
import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TilePreviewsData;
import com.oplus.systemui.plugins.qs.customize.data.model.TileTypeConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.ResourcePreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.TilePreviewData;
import com.oplus.systemui.plugins.qs.customize.viewmodel.AllTilesState;
import com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.UserQSTileAndConfig;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.DialogPreviewConfig;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.GridViewHolderKt;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AllTilesInteractor {
    public static final Companion Companion = new Companion();
    private static final String PREFIX = "custom(";
    private static final String TAG = "AllTilesInteractor";
    private final MutableStateFlow _allTileData;
    private final MutableStateFlow _tileTypeConfigFlow;
    private final Flow allSelectableData;
    private final CoroutineDispatcher bgDispatcher;
    private final ContextInstance contextInstance;
    private final CoroutineScope mainScope;
    private final SepMoreTileConfigRepository moreTileConfigRepo;
    private final PanelPluginInteractor panelPluginInteractor;
    private final SepQSHostAdapter separateQSHostAdapter;
    private final OplusSeparateQSPlugin.IStub separateQsPluginStub;
    private final SepStaticTileConfigRepository staticTileConfigRepo;
    public final SepQSTileQueryHelper tileQueryHelper;
    public List tileTypeConfig;
    private final StateFlow tileTypeConfigFlow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getResourceStringByType(Context context, SepTileType sepTileType) {
            Resources resources = context.getResources();
            int iOrdinal = sepTileType.ordinal();
            if (iOrdinal == 0) {
                return resources.getString(2131955235);
            }
            if (iOrdinal == 1) {
                return resources.getString(2131955230);
            }
            if (iOrdinal == 2) {
                return resources.getString(2131955233);
            }
            if (iOrdinal == 3) {
                return resources.getString(2131955236);
            }
            if (iOrdinal == 4) {
                return resources.getString(2131955234);
            }
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor$startQueryTileByPM$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AllTilesInteractor.this.startQueryTileByPM(this);
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$yzs-kr5Lm56w2Alh5x2YDgwfjOI, reason: not valid java name */
    public static Unit m179$r8$lambda$yzskr5Lm56w2Alh5x2YDgwfjOI(AllTilesInteractor allTilesInteractor, List list) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onStockTilesChanged: tiles: " + (list != null ? Integer.valueOf(list.size()) : null), null, 8);
        CoroutineTracingKt.launchTraced$default(allTilesInteractor.mainScope, "AllTilesInteractor-launch-setScanTileData", allTilesInteractor.bgDispatcher, (CoroutineStart) null, new AllTilesInteractor$startQueryTileByPM$3$1(allTilesInteractor, list, null), 4);
        return Unit.INSTANCE;
    }

    public AllTilesInteractor(ContextInstance contextInstance, OplusSeparateQSPlugin.IStub iStub, PanelPluginInteractor panelPluginInteractor, SepQSHostAdapter sepQSHostAdapter, SepMoreTileConfigRepository sepMoreTileConfigRepository, SepStaticTileConfigRepository sepStaticTileConfigRepository, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.contextInstance = contextInstance;
        this.separateQsPluginStub = iStub;
        this.panelPluginInteractor = panelPluginInteractor;
        this.separateQSHostAdapter = sepQSHostAdapter;
        this.moreTileConfigRepo = sepMoreTileConfigRepository;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
        this.mainScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        MutableStateFlow mutableStateFlowTraceAs = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(AllTilesState.Loading.INSTANCE), "AllTilesInteractor-flow-allTileData");
        this._allTileData = mutableStateFlowTraceAs;
        MutableStateFlow mutableStateFlowTraceAs2 = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(CollectionsKt__CollectionsKt.emptyList()), "AllTilesInteractor-flow-tileTypeConfigFlow");
        this._tileTypeConfigFlow = mutableStateFlowTraceAs2;
        this.tileTypeConfigFlow = FlowKt.asStateFlow(mutableStateFlowTraceAs2);
        this.tileQueryHelper = iStub.bindSepQSTileQueryHelper();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "startLoadPreViewConfig", null, 8);
        CoroutineTracingKt.launchTraced$default(coroutineScope, "AllTilesInteractor-launch-loadPreViewConfig", coroutineDispatcher, (CoroutineStart) null, new AllTilesInteractor$loadPreViewConfig$1(this, null), 4);
        this.allSelectableData = FlowKt.transformLatest(mutableStateFlowTraceAs, new AllTilesInteractor$special$$inlined$flatMapLatest$1(this, null));
    }

    public static final List access$convertDataToUIConfig(AllTilesInteractor allTilesInteractor, List list) {
        Resources resources = allTilesInteractor.contextInstance.getSysUiContext().getResources();
        List<TileTypeConfig> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        for (TileTypeConfig tileTypeConfig : list2) {
            List<TilePreviewsData> tileList = tileTypeConfig.getTileList();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tileList, 10));
            for (TilePreviewsData tilePreviewsData : tileList) {
                String tileSpec = tilePreviewsData.getTileSpec();
                arrayList2.add(new GridData(GridViewHolderKt.toUniqueLong(tilePreviewsData.getTileSpec()), tilePreviewsData.getDefaultSize(), Intrinsics.areEqual(tileSpec, OplusInnerFakeTile.SPEC_BRIGHTNESS_VOLUME) ? new ResourcePreviewData(AddTileCalculator.INSTANCE.getBrightnessVolumePreviewDrawable(allTilesInteractor.contextInstance.getSysUiContext()), resources.getString(2131955238), tilePreviewsData.getTileSpec()) : Intrinsics.areEqual(tileSpec, OplusInnerFakeTile.SPEC_MEDIA) ? new ResourcePreviewData(AddTileCalculator.INSTANCE.getMediaPreviewDrawable(allTilesInteractor.contextInstance.getSysUiContext()), resources.getString(2131955241), tilePreviewsData.getTileSpec()) : new TilePreviewData(tilePreviewsData.getIcon(), tilePreviewsData.getLabel(), tilePreviewsData.getTileSpec(), tilePreviewsData.getSecondaryLabel())));
            }
            arrayList.add(new DialogPreviewConfig(Companion.getResourceStringByType(allTilesInteractor.contextInstance.getSysUiContext(), tileTypeConfig.getTileType()), arrayList2));
        }
        return arrayList;
    }

    public static final Object access$setScanTileData(AllTilesInteractor allTilesInteractor, List list, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(allTilesInteractor.bgDispatcher, new AllTilesInteractor$setScanTileData$2(allTilesInteractor, list, null), continuation);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public static final List access$toTilePreviewsDataForConfig(AllTilesInteractor allTilesInteractor, List list, List list2) {
        allTilesInteractor.getClass();
        List<TilePreviewsData> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
        for (TilePreviewsData tilePreviewsData : list3) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                SepQSTileQueryHelper.TileInfo tileInfo = (SepQSTileQueryHelper.TileInfo) it.next();
                if (Intrinsics.areEqual(tilePreviewsData.getTileSpec(), tileInfo.getSpec())) {
                    tilePreviewsData.setLabel(tileInfo.getState().label.toString());
                    tilePreviewsData.setIcon((!StringsKt__StringsKt.contains(tilePreviewsData.getTileSpec(), PREFIX, false) || tileInfo.getState().iconSupplier == null) ? tileInfo.getState().icon : (QSTile.Icon) tileInfo.getState().iconSupplier.get());
                }
            }
            arrayList.add(tilePreviewsData);
        }
        return arrayList;
    }

    public static /* synthetic */ void getAllSelectableData$annotations() {
    }

    public final Flow getAllSelectableData() {
        return this.allSelectableData;
    }

    public final CharSequence getLabelOfSpec(String str) {
        Object obj;
        BasePreviewData basePreviewData;
        CharSequence label;
        if (this._allTileData.getValue() instanceof AllTilesState.Normal) {
            List<DialogPreviewConfig> dialogPreviewConfig = ((AllTilesState.Normal) this._allTileData.getValue()).getDialogPreviewConfig();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = dialogPreviewConfig.iterator();
            while (it.hasNext()) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((DialogPreviewConfig) it.next()).getTiles());
            }
            int size = arrayList.size();
            int i = 0;
            do {
                if (i >= size) {
                    obj = null;
                    break;
                }
                obj = arrayList.get(i);
                i++;
            } while (!Intrinsics.areEqual(((BasePreviewData) ((GridData) obj).getData()).getSpec(), str));
            GridData gridData = (GridData) obj;
            if (gridData != null && (basePreviewData = (BasePreviewData) gridData.getData()) != null && (label = basePreviewData.getLabel()) != null) {
                return label;
            }
        }
        return "";
    }

    public final StateFlow getTileTypeConfigFlow() {
        return this.tileTypeConfigFlow;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object startQueryTileByPM(Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        UserQSTileAndConfig userQSTileAndConfig;
        SepQSTileQueryHelper sepQSTileQueryHelper;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "startQueryTileByPM", null, 8);
            UserQSTileAndConfig currentTileAndUiConfig = this.separateQSHostAdapter.getCurrentTileAndUiConfig();
            SepQSTileQueryHelper sepQSTileQueryHelper2 = this.tileQueryHelper;
            SepQSTileQueryHelper sepQSTileQueryHelper3 = sepQSTileQueryHelper2 != null ? sepQSTileQueryHelper2 : null;
            SepStaticTileConfigRepository sepStaticTileConfigRepository = this.staticTileConfigRepo;
            anonymousClass1.L$0 = currentTileAndUiConfig;
            anonymousClass1.L$1 = sepQSTileQueryHelper3;
            anonymousClass1.label = 1;
            Object tileConfigs = sepStaticTileConfigRepository.getTileConfigs(anonymousClass1);
            if (tileConfigs == coroutine_suspended) {
                return coroutine_suspended;
            }
            userQSTileAndConfig = currentTileAndUiConfig;
            obj = tileConfigs;
            sepQSTileQueryHelper = sepQSTileQueryHelper3;
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            sepQSTileQueryHelper = (SepQSTileQueryHelper) anonymousClass1.L$1;
            userQSTileAndConfig = (UserQSTileAndConfig) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        List list = CollectionsKt___CollectionsKt.toList(((Map) obj).keySet());
        List<TileConfig> tileConfigs2 = userQSTileAndConfig.getTileConfigs();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tileConfigs2, 10));
        Iterator<T> it = tileConfigs2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileConfig) it.next()).getTileSpec());
        }
        AllTilesInteractor$$ExternalSyntheticLambda0 allTilesInteractor$$ExternalSyntheticLambda0 = new AllTilesInteractor$$ExternalSyntheticLambda0();
        allTilesInteractor$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        sepQSTileQueryHelper.querySepTile(list, arrayList, allTilesInteractor$$ExternalSyntheticLambda0);
        return Unit.INSTANCE;
    }

    public final void stopQueryTileByPM() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "stopQueryTileByPM", null, 8);
        List list = this.tileTypeConfig;
        if (list != null) {
            List<TileTypeConfig> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            for (TileTypeConfig tileTypeConfig : list2) {
                List<TilePreviewsData> tileList = tileTypeConfig.getTileList();
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tileList, 10));
                for (TilePreviewsData tilePreviewsData : tileList) {
                    arrayList2.add(tilePreviewsData.copy(tilePreviewsData.tileSpec, "", null, null, tilePreviewsData.defaultSize, tilePreviewsData.secondaryLabel));
                }
                arrayList.add(tileTypeConfig.copy(tileTypeConfig.tileType, arrayList2));
            }
            this.tileTypeConfig = arrayList;
        } else {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "tileTypeConfig not initialized, must be initialized before use", null, 8);
        }
        this._allTileData.setValue(AllTilesState.Loading.INSTANCE);
    }
}
