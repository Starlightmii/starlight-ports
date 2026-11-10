package com.oplus.utrace.lib;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PackageNames {
    public static final String ASSISTANT_SCREEN = "com.coloros.assistantscreen";
    public static final String LAUNCHER = "com.android.launcher";
    public static final String METIS = "com.oplus.metis";
    public static final String SCENE_SERVICE = "com.coloros.sceneservice";
    public static final String SECONDARY_HOME = "com.oplus.secondaryhome";
    public static final String SYSTEM_UI = "com.android.systemui";
    public static final PackageNames INSTANCE = new PackageNames();
    public static final String UTRACE_MGR = "com.oplus.utrace.agent";
    public static final String UMS = "com.oplus.pantanal.ums";
    private static final String[] CORE_APPS = {UTRACE_MGR, UMS};
    public static final String UTRACE_APP = "com.oplus.utrace";
    private static final String[] UTRACE_DEMO_APPS = {UTRACE_APP, UTRACE_MGR};

    public final String[] getCORE_APPS() {
        return CORE_APPS;
    }

    public final String[] getUTRACE_DEMO_APPS() {
        return UTRACE_DEMO_APPS;
    }
}
