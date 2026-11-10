package com.oplus.systemui.qs.base.widget.recyclerview.callback;

import com.oplusos.systemui.common.viewholder.CellInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ILayoutCallback {
    default void onCellInfoChanged(CellInfo cellInfo) {
    }

    void onCellNumChanged(int i, int i2);
}
