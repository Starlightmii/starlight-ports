package com.oplus.systemui.plugins.qs.customize.viewmodel.model;

import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileAndConfig {
    private final QSTile qsTile;
    private final TileConfig uiConfig;

    public QSTileAndConfig(QSTile qSTile, TileConfig tileConfig) {
        this.qsTile = qSTile;
        this.uiConfig = tileConfig;
    }

    public final QSTile component1() {
        return this.qsTile;
    }

    public final TileConfig component2() {
        return this.uiConfig;
    }

    public final QSTileAndConfig copy(QSTile qSTile, TileConfig tileConfig) {
        return new QSTileAndConfig(qSTile, tileConfig);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QSTileAndConfig)) {
            return false;
        }
        QSTileAndConfig qSTileAndConfig = (QSTileAndConfig) obj;
        return Intrinsics.areEqual(this.qsTile, qSTileAndConfig.qsTile) && Intrinsics.areEqual(this.uiConfig, qSTileAndConfig.uiConfig);
    }

    public final QSTile getQsTile() {
        return this.qsTile;
    }

    public final TileConfig getUiConfig() {
        return this.uiConfig;
    }

    public final int hashCode() {
        return this.uiConfig.hashCode() + (this.qsTile.hashCode() * 31);
    }

    public String toString() {
        return "QSTileAndConfig(qsTile=" + this.qsTile + ", uiConfig=" + this.uiConfig + ")";
    }
}
