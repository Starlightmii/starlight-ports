package com.oplus.systemui.qs.tileex;

import com.android.systemui.util.JankUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DarkModeTileEx extends QSTileExImpl {
    public DarkModeTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final void onClick() {
        QSTileExImpl.requestUxForTileThread();
        JankUtils.darkModeChangeSceneBegin(getContext());
    }
}
