package com.oplus.systemui.qs.base.customizer;

import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.plugins.qs.QSTileQueryHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusQSBaseDragAdapter extends RecyclerView.Adapter {
    public abstract void addTile(QSTileQueryHelper.OplusTileInfo oplusTileInfo, int i);

    public abstract IQSCustomizer$EditType getEditType();

    public abstract QSTileQueryHelper.OplusTileInfo getTile(int i);

    public abstract int getTilePosition(QSTileQueryHelper.OplusTileInfo oplusTileInfo);

    public abstract void removeTile(int i);

    public abstract void setTileVisible(int i, boolean z);

    public abstract void swapTile(int i, int i2);
}
