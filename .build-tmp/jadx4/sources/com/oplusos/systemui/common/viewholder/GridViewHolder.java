package com.oplusos.systemui.common.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class GridViewHolder extends BaseViewHolder {
    private CellInfo cellInfo;

    public GridViewHolder(Context context, int i, ViewGroup viewGroup, ViewModel viewModel) {
        super(context, i, viewGroup, viewModel);
    }

    public final CellInfo getCellInfo() {
        return this.cellInfo;
    }

    public final GridData getGridData() {
        return (GridData) getData();
    }

    public final boolean isCellInfoValid() {
        CellInfo cellInfo = this.cellInfo;
        return cellInfo != null && cellInfo.getCellWidth() > 0 && cellInfo.getCellHeight() > 0;
    }

    public void onCellInfoChanged(CellInfo cellInfo) {
    }

    public final void setCellInfo(CellInfo cellInfo) {
        if (Intrinsics.areEqual(this.cellInfo, cellInfo)) {
            return;
        }
        this.cellInfo = cellInfo;
        if (cellInfo != null) {
            onCellInfoChanged(cellInfo);
        }
    }
}
