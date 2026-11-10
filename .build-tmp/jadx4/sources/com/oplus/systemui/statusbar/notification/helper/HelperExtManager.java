package com.oplus.systemui.statusbar.notification.helper;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class HelperExtManager {
    public final Map mHelpers = new HashMap();
    public final Map mExts = new HashMap();

    public Map<Class<?>, Object> getExts() {
        return this.mExts;
    }

    public Map<Class<?>, Object> getHelpers() {
        return this.mHelpers;
    }
}
