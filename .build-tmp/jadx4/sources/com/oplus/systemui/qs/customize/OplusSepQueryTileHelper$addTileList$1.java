package com.oplus.systemui.qs.customize;

import android.util.Log;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.external.CustomTile;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSepQueryTileHelper$addTileList$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $tilesToAdd;
    public /* synthetic */ OplusSepQueryTileHelper this$0;

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                for (CustomTile customTile : (List) this.$tilesToAdd) {
                    OplusSepQueryTileHelper oplusSepQueryTileHelper = this.this$0;
                    OplusSepQueryTileHelper.Companion companion = OplusSepQueryTileHelper.Companion;
                    QSTile.State stateCopy = customTile.getState().copy();
                    stateCopy.label = customTile.getTileLabel();
                    stateCopy.state = 1;
                    if (customTile instanceof CustomTile) {
                        stateCopy.iconSupplier = customTile.getTileIconSupplier();
                    } else {
                        stateCopy.icon = customTile.getDefaultIcon();
                    }
                    oplusSepQueryTileHelper.addTile(customTile.getTileSpec(), null, stateCopy, true, false);
                    customTile.destroy();
                }
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusSepQueryTileHelper-->addCurrentTiles bg start ");
                }
                ArrayList arrayList = (ArrayList) this.$tilesToAdd;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    CustomTile customTile2 = (QSTile) obj;
                    QSTile.State stateCopy2 = customTile2.getState().copy();
                    stateCopy2.state = 1;
                    if (customTile2 instanceof CustomTile) {
                        CustomTile customTile3 = customTile2;
                        stateCopy2.label = customTile3.getQsTile().getLabel();
                        stateCopy2.iconSupplier = customTile3.getTileIconSupplier();
                    } else {
                        stateCopy2.label = customTile2.getTileLabel();
                        stateCopy2.icon = customTile2.getDefaultIcon();
                    }
                    OplusSepQueryTileHelper oplusSepQueryTileHelper2 = this.this$0;
                    String tileSpec = customTile2.getTileSpec();
                    OplusSepQueryTileHelper.Companion companion2 = OplusSepQueryTileHelper.Companion;
                    oplusSepQueryTileHelper2.addTile(tileSpec, null, stateCopy2, true, true);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusSepQueryTileHelper-->addCurrentTiles bg end ");
                    break;
                }
                break;
        }
    }
}
