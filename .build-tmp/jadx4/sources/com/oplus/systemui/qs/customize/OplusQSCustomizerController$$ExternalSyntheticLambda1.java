package com.oplus.systemui.qs.customize;

import android.view.View;
import android.widget.AdapterView;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSCustomizerController$$ExternalSyntheticLambda1 implements AdapterView.OnItemClickListener {
    public /* synthetic */ OplusQSCustomizerController f$0;

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        OplusQSCustomizerController oplusQSCustomizerController = this.f$0;
        oplusQSCustomizerController.mCustomizerToolbar.dismissPopupWindow();
        ((OplusQSCustomizer) ((ViewController) oplusQSCustomizerController).mView).initAdapter();
        LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizerController", "reset OplusQSCustomizer");
        OplusTileAdapter oplusTileAdapter = oplusQSCustomizerController.mTileAdapterTop;
        OplusTileAdapter oplusTileAdapter2 = oplusQSCustomizerController.mTileAdapterHighLight;
        OplusQuickSettingsEx oplusQuickSettingsEx = (OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class);
        List<String> arrayList = new ArrayList();
        if (oplusQuickSettingsEx != null) {
            arrayList = oplusQuickSettingsEx.getQsTileHostHelperEx().getDefaultTileSpecs(oplusQSCustomizerController.getContext());
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(oplusTileAdapter2.getCurrentSpecs());
        arrayList2.addAll(oplusTileAdapter.getCurrentSpecs());
        oplusQSCustomizerController.mQsTileHost.changeTilesByUser(arrayList2, arrayList);
        List<String> listSubList = arrayList.subList(0, 2);
        List<String> listSubList2 = arrayList.subList(2, arrayList.size());
        oplusTileAdapter2.resetTileSpecs(listSubList);
        oplusTileAdapter.resetTileSpecs(listSubList2);
        List<QSTileQueryHelper.OplusTileInfo> allTiles = oplusQSCustomizerController.mTileQueryHelper.getAllTiles();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (String str : arrayList) {
            for (QSTileQueryHelper.OplusTileInfo oplusTileInfo : allTiles) {
                if (str.equals(oplusTileInfo.getSpec())) {
                    arrayList3.add(oplusTileInfo);
                    break;
                }
            }
        }
        for (QSTileQueryHelper.OplusTileInfo oplusTileInfo2 : allTiles) {
            if (!arrayList.contains(oplusTileInfo2.getSpec())) {
                arrayList4.add(oplusTileInfo2);
            }
        }
        ((OplusQSCustomizer) ((ViewController) oplusQSCustomizerController).mView).onCurrentTilesChanged(arrayList3);
        ((OplusQSCustomizer) ((ViewController) oplusQSCustomizerController).mView).onStockTilesChanged(arrayList4);
        QsStatisticUtil.collectEditTitleTypeEvent(oplusQSCustomizerController.getContext(), "edit_tile_reset");
    }
}
