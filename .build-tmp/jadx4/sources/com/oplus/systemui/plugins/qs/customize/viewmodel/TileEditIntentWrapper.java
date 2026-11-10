package com.oplus.systemui.plugins.qs.customize.viewmodel;

import com.oplusos.systemui.common.viewmodel.IUiIntent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileEditIntentWrapper implements IUiIntent {
    private final TileEditIntent intent;
    private final IntentTarget target;

    public TileEditIntentWrapper(TileEditIntent tileEditIntent, IntentTarget intentTarget) {
        this.intent = tileEditIntent;
        this.target = intentTarget;
    }

    public final TileEditIntent getIntent() {
        return this.intent;
    }

    public final IntentTarget getTarget() {
        return this.target;
    }
}
