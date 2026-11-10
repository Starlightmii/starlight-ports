package com.oplus.systemui.plugins.qs.quickentrance;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioPreviewShowState {
    public static final Companion Companion = new Companion();
    private static volatile ScenarioPreviewShowState instance;
    private volatile String currentScenarioName;
    public volatile long lastShowTime;
    private final ConcurrentHashMap<String, Boolean> scenarioNameMap = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ScenarioPreviewShowState getInstance() {
            ScenarioPreviewShowState scenarioPreviewShowState;
            ScenarioPreviewShowState scenarioPreviewShowState2 = ScenarioPreviewShowState.instance;
            if (scenarioPreviewShowState2 != null) {
                return scenarioPreviewShowState2;
            }
            synchronized (this) {
                scenarioPreviewShowState = ScenarioPreviewShowState.instance;
                if (scenarioPreviewShowState == null) {
                    scenarioPreviewShowState = new ScenarioPreviewShowState();
                    ScenarioPreviewShowState.instance = scenarioPreviewShowState;
                }
            }
            return scenarioPreviewShowState;
        }
    }

    public static final ScenarioPreviewShowState getInstance() {
        return Companion.getInstance();
    }

    public final String getCurrentScenarioName() {
        return this.currentScenarioName;
    }

    public final long getLastShowTime() {
        return this.lastShowTime;
    }

    public final Boolean getValue(String str) {
        return this.scenarioNameMap.get(str);
    }

    public final boolean isFirstShow(String str) {
        return (str == null || str.length() == 0 || !Intrinsics.areEqual(this.scenarioNameMap.get(str), Boolean.FALSE)) ? false : true;
    }

    public final void setCurrentScenarioName(String str) {
        this.currentScenarioName = str;
    }

    public final void setValue(String str, boolean z) {
        this.scenarioNameMap.put(str, Boolean.valueOf(z));
    }
}
