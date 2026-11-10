package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragSwapAlg implements ILocationAlg {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DragSwapAlg";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public List<TileConfig> process(DragInputData dragInputData) {
        List<TileConfig> tiles = dragInputData.getTiles();
        int draggingIndex = dragInputData.getDraggingIndex();
        Loc endLoc = dragInputData.getEndLoc();
        GridInfoEntity gridInfo = dragInputData.getGridInfo();
        TileConfig tileConfig = dragInputData.getTiles().get(draggingIndex);
        LocationAlgHelper locationAlgHelper = LocationAlgHelper.INSTANCE;
        LocationAlgHelper.TargetArea targetAreaCalculateTargetArea = locationAlgHelper.calculateTargetArea(tileConfig, endLoc, gridInfo);
        if (locationAlgHelper.matchBasicSwapScene(targetAreaCalculateTargetArea, tileConfig, gridInfo)) {
            locationAlgHelper.performBasicSwap(tiles, tileConfig, targetAreaCalculateTargetArea);
            return tiles;
        }
        LocationAlgHelper.TargetArea targetAreaExcludeDraggingTargetAre = locationAlgHelper.excludeDraggingTargetAre(targetAreaCalculateTargetArea.getInvolvedTiles(), tileConfig, endLoc);
        int iIsTilesContiguous = locationAlgHelper.isTilesContiguous(targetAreaExcludeDraggingTargetAre, tileConfig);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iIsTilesContiguous, "process DragSwapAlg dragDirection: "), null, 8);
        if (iIsTilesContiguous != 0) {
            if (locationAlgHelper.matchBigAccordionSwapScene(targetAreaExcludeDraggingTargetAre, tileConfig, gridInfo, iIsTilesContiguous)) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iIsTilesContiguous, "matchBigAccordionSwapScene dragDirection: "), null, 8);
                locationAlgHelper.processBigAccordionSwap(tiles, tileConfig, targetAreaExcludeDraggingTargetAre, iIsTilesContiguous);
                return tiles;
            }
            if (locationAlgHelper.matchSmallAccordionSwapScene(targetAreaExcludeDraggingTargetAre, tileConfig, iIsTilesContiguous)) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iIsTilesContiguous, "matchSmallAccordionSwapScene dragDirection: "), null, 8);
                locationAlgHelper.processSmallAccordionSwap(tiles, tileConfig, targetAreaExcludeDraggingTargetAre, iIsTilesContiguous);
                return tiles;
            }
            if (locationAlgHelper.matchPushSwapScene(targetAreaExcludeDraggingTargetAre, tileConfig, iIsTilesContiguous)) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iIsTilesContiguous, "matchPushSwapScene dragDirection: "), null, 8);
                locationAlgHelper.processPushSwap(tiles, tileConfig, targetAreaExcludeDraggingTargetAre, iIsTilesContiguous);
                return tiles;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "Not Match Any Swap Alg", null, 8);
        }
        return tiles;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public boolean match(DragInputData dragInputData) {
        return true;
    }
}
