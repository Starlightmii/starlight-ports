package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import android.support.v4.content.FileProvider$;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocationAlgManager {
    public static final String ACTION_ADD = "ACTION_ADD";
    public static final String ACTION_ADD_BY_INDEX = "ACTION_ADD_BY_INDEX";
    public static final String ACTION_DEFORM = "ACTION_DEFORM";
    public static final String ACTION_DRAG = "ACTION_DRAG";
    public static final String ACTION_LAND_MAP = "ACTION_LAND_MAP";
    public static final String ACTION_REARRANGE = "ACTION_REARRANGE";
    public static final String ACTION_REMOVE_EMPTY_PAGE = "ACTION_REMOVE_EMPTY_PAGE";
    public static final String ACTION_VACANCY_COMPLEMENT = "ACTION_VACANCY_COMPLEMENT";
    public static final LocationAlgManager INSTANCE = new LocationAlgManager();
    private static final String TAG = "LocationAlgManager";
    private static final Map<String, List<ILocationAlg>> algorithms;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        algorithms = linkedHashMap;
        linkedHashMap.put(ACTION_DRAG, CollectionsKt__CollectionsKt.mutableListOf(new DragSwapAlg[]{new DragSwapAlg()}));
        linkedHashMap.put(ACTION_DEFORM, CollectionsKt__CollectionsKt.mutableListOf(new DeformTileAlg[]{new DeformTileAlg()}));
        linkedHashMap.put(ACTION_ADD, CollectionsKt__CollectionsKt.mutableListOf(new AddOneTileAlg[]{new AddOneTileAlg()}));
        linkedHashMap.put(ACTION_REMOVE_EMPTY_PAGE, CollectionsKt__CollectionsKt.mutableListOf(new RemoveEmptyPageAlg[]{new RemoveEmptyPageAlg()}));
        LandscapeMappingAlg landscapeMappingAlg = new LandscapeMappingAlg();
        landscapeMappingAlg.landscapeRowSize = 4;
        landscapeMappingAlg.landscapeAEndCol = 4;
        landscapeMappingAlg.landscapeBEndCol = 8;
        landscapeMappingAlg.firstBestSplitRow = 3;
        landscapeMappingAlg.secondBestSplitRow = 2;
        linkedHashMap.put(ACTION_LAND_MAP, CollectionsKt__CollectionsKt.mutableListOf(new LandscapeMappingAlg[]{landscapeMappingAlg}));
        linkedHashMap.put(ACTION_REARRANGE, CollectionsKt__CollectionsKt.mutableListOf(new RearrangeLocAlg[]{new RearrangeLocAlg()}));
        linkedHashMap.put(ACTION_ADD_BY_INDEX, CollectionsKt__CollectionsKt.mutableListOf(new AddTilesByIndexAlg[]{new AddTilesByIndexAlg()}));
        linkedHashMap.put(ACTION_VACANCY_COMPLEMENT, CollectionsKt__CollectionsKt.mutableListOf(new VacancyComplementAlg[]{new VacancyComplementAlg()}));
    }

    public final <INPUT_DATA extends BaseInputData> List<TileConfig> startLocationAlgorithm(String str, INPUT_DATA input_data) {
        List<ILocationAlg> list = algorithms.get(str);
        if (list == null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("startLocationAlgorithm no such action: ", str), null, 8);
            return input_data.getTiles();
        }
        BaseInputData baseInputDataMergeTwoCols = input_data.mergeTwoCols();
        for (ILocationAlg iLocationAlg : list) {
            if (iLocationAlg.match(baseInputDataMergeTwoCols)) {
                long jNanoTime = System.nanoTime();
                List<TileConfig> listRevertToTwoCols = DataConvertorKt.revertToTwoCols(LocationAlgHelper.INSTANCE.sortTilesByLocation(iLocationAlg.process(baseInputDataMergeTwoCols)));
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "process " + iLocationAlg + " end costTime: " + (System.nanoTime() - jNanoTime) + " ns", null, 8);
                return listRevertToTwoCols;
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "No matching algorithm found", null, 8);
        return input_data.getTiles();
    }
}
