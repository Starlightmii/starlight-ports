package com.oplus.systemui.qs.tileex;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DndTileEx extends QSTileExImpl {
    public DndTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        return CustomizeFeatureOption.sIsNoDisturbDisabled;
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isTileDisable() {
        return CustomizeFeatureOption.sIsNoDisturbDisabled;
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public void resetIconForDisable(QSTile.State state) {
        super.resetIconForDisable(state);
        state.icon = QSTileImpl.ResourceIcon.get(2131237150);
    }
}
