package com.oplus.systemui.plugins.qs.customize.viewmodel;

import androidx.car.app.hardware.common.CarZone$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UserQSTileAndConfig {
    private final List<QSTile> qsTiles;
    private final List<TileConfig> tileConfigs;
    public final int userId;

    public UserQSTileAndConfig(int i, List<? extends QSTile> list, List<TileConfig> list2) {
        this.userId = i;
        this.qsTiles = list;
        this.tileConfigs = list2;
    }

    public final List<QSTile> component2() {
        return this.qsTiles;
    }

    public final List<TileConfig> component3() {
        return this.tileConfigs;
    }

    public final UserQSTileAndConfig copy(int i, List<? extends QSTile> list, List<TileConfig> list2) {
        return new UserQSTileAndConfig(i, list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserQSTileAndConfig)) {
            return false;
        }
        UserQSTileAndConfig userQSTileAndConfig = (UserQSTileAndConfig) obj;
        return this.userId == userQSTileAndConfig.userId && Intrinsics.areEqual(this.qsTiles, userQSTileAndConfig.qsTiles) && Intrinsics.areEqual(this.tileConfigs, userQSTileAndConfig.tileConfigs);
    }

    public final List<QSTile> getQsTiles() {
        return this.qsTiles;
    }

    public final List<TileConfig> getTileConfigs() {
        return this.tileConfigs;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final int hashCode() {
        return this.tileConfigs.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.userId) * 31, 31, this.qsTiles);
    }

    public String toString() {
        List<QSTile> list = this.qsTiles;
        List<TileConfig> list2 = this.tileConfigs;
        StringBuilder sb = new StringBuilder("UserQSTileAndConfig(userId=");
        sb.append(this.userId);
        sb.append(", qsTiles=");
        sb.append(list);
        sb.append(", tileConfigs=");
        return CarZone$.ExternalSyntheticOutline0.m(sb, list2, ")");
    }
}
