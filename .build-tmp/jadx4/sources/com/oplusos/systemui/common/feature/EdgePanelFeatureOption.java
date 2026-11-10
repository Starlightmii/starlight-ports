package com.oplusos.systemui.common.feature;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EdgePanelFeatureOption {
    public static final ArrayList APP_FEATURE_CACHE_LIST = new ArrayList(Arrays.asList("com.oplus.smartsidebar.default.off", "com.oplus.smartsidebar_disable", "com.oplus.smartsidebar.light_os"));

    public static ArrayList<String> getAppFeatureCacheList() {
        return APP_FEATURE_CACHE_LIST;
    }
}
