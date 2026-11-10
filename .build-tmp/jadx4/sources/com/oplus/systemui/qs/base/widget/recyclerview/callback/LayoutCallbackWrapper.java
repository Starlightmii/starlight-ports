package com.oplus.systemui.qs.base.widget.recyclerview.callback;

import com.oplusos.systemui.common.viewholder.CellInfo;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LayoutCallbackWrapper {
    private final CopyOnWriteArraySet<ILayoutCallback> callbacks = new CopyOnWriteArraySet<>();
    public int currColCount;
    public int currRowCount;
    private CellInfo currentCellInfo;

    public final void addLayoutCallback(ILayoutCallback iLayoutCallback, boolean z) {
        int i;
        this.callbacks.add(iLayoutCallback);
        if (z) {
            CellInfo cellInfo = this.currentCellInfo;
            if (cellInfo != null) {
                iLayoutCallback.onCellInfoChanged(cellInfo);
            }
            int i2 = this.currColCount;
            if (i2 <= 0 || (i = this.currRowCount) <= 0) {
                return;
            }
            iLayoutCallback.onCellNumChanged(i2, i);
        }
    }

    public final void dispatchCellInfoChanged$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(int i, int i2) {
        CellInfo cellInfo = this.currentCellInfo;
        if (cellInfo != null && cellInfo.getCellWidth() == i && cellInfo.getCellHeight() == i2 && cellInfo.getHorizontalSpace() == 0 && cellInfo.getVerticalSpace() == 0) {
            return;
        }
        CellInfo cellInfo2 = new CellInfo(i, i2, 0, 0);
        for (ILayoutCallback iLayoutCallback : CollectionsKt___CollectionsKt.toList(this.callbacks)) {
            iLayoutCallback.getClass();
            iLayoutCallback.onCellInfoChanged(cellInfo2);
        }
        this.currentCellInfo = cellInfo2;
    }

    public final void dispatchCellNumChanged$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(int i, int i2) {
        if (this.currColCount == i && this.currRowCount == i2) {
            return;
        }
        this.currColCount = i;
        this.currRowCount = i2;
        for (ILayoutCallback iLayoutCallback : CollectionsKt___CollectionsKt.toList(this.callbacks)) {
            iLayoutCallback.getClass();
            iLayoutCallback.onCellNumChanged(i, i2);
        }
    }

    public final void removeLayoutCallback(ILayoutCallback iLayoutCallback) {
        this.callbacks.remove(iLayoutCallback);
    }
}
