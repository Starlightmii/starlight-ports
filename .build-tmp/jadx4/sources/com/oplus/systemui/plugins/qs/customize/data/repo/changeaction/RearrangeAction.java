package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import android.util.Log;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgManager;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.RearrangeInputData;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RearrangeAction extends BaseActionForA {
    public static final RearrangeAction INSTANCE = new RearrangeAction(true);
    private static final String TAG = "RearrangeAction";

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForA
    public Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "RearrangeAction-->".concat("[apply] input currentTiles=" + list + ", currentGridInfo=" + gridInfoEntity));
        }
        List<TileConfig> listStartLocationAlgorithm = LocationAlgManager.INSTANCE.startLocationAlgorithm(LocationAlgManager.ACTION_REARRANGE, new RearrangeInputData(CollectionsKt___CollectionsKt.toMutableList(list), gridInfoEntity));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "RearrangeAction-->".concat("[apply] output " + listStartLocationAlgorithm));
        }
        return listStartLocationAlgorithm;
    }

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof RearrangeAction);
    }

    public final int hashCode() {
        return -1847403345;
    }

    public String toString() {
        return TAG;
    }
}
