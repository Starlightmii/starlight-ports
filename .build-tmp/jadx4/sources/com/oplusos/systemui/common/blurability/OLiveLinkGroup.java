package com.oplusos.systemui.common.blurability;

import android.graphics.Color;
import com.oplusos.systemui.common.olivelink.AndroidColor;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OLiveLinkGroup {
    private final String groupName;
    private final String serverName;

    public OLiveLinkGroup(String str, String str2) {
        this.serverName = str;
        this.groupName = str2;
    }

    public final AndroidColor color(String str, Color color, String str2) {
        return OliveLinkConnector.INSTANCE.color(this.serverName, str, this.groupName, color, str2);
    }

    public final String getGroupName() {
        return this.groupName;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final Scalar scalar(String str, float f, String str2) {
        return OliveLinkConnector.INSTANCE.safeScalar(this.serverName, str, this.groupName, Float.valueOf(f), str2);
    }
}
