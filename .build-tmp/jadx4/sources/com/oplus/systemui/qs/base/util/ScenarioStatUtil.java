package com.oplus.systemui.qs.base.util;

import android.hidl.base.V1_0.DebugInfo$;
import android.text.TextUtils;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioStatUtil {
    public static final ScenarioStatUtil INSTANCE = new ScenarioStatUtil();
    private static final CopyOnWriteArrayList<ScenarioStat> statsList = new CopyOnWriteArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScenarioStat {
        private volatile String scenarioType = "";
        private volatile String entryMode = "";
        private AtomicInteger entryCount = new AtomicInteger(0);
        private final ConcurrentHashMap<String, Integer> functionStats = new ConcurrentHashMap<>();

        public final AtomicInteger getEntryCount() {
            return this.entryCount;
        }

        public final String getEntryMode() {
            return this.entryMode;
        }

        public final ConcurrentHashMap<String, Integer> getFunctionStats() {
            return this.functionStats;
        }

        public final String getScenarioType() {
            return this.scenarioType;
        }

        public final void setEntryCount(AtomicInteger atomicInteger) {
            this.entryCount = atomicInteger;
        }

        public final void setEntryMode(String str) {
            this.entryMode = str;
        }

        public final void setScenarioType(String str) {
            this.scenarioType = str;
        }

        public String toString() {
            String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.functionStats.entrySet(), OplusPreventModeData.SEPARATOR, (CharSequence) null, (CharSequence) null, new ScenarioStatUtil$$ExternalSyntheticLambda0(1), 30);
            String str = this.scenarioType;
            String str2 = this.entryMode;
            AtomicInteger atomicInteger = this.entryCount;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("-");
            sb.append(str2);
            sb.append("-");
            sb.append(atomicInteger);
            return DebugInfo$.ExternalSyntheticOutline0.m(sb, "-", strJoinToString$default);
        }
    }

    public final void add(ScenarioStat scenarioStat) {
        Object next;
        if (TextUtils.isEmpty(scenarioStat.getScenarioType())) {
            return;
        }
        Iterator<T> it = statsList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            ScenarioStat scenarioStat2 = (ScenarioStat) next;
            if (Intrinsics.areEqual(scenarioStat2.getScenarioType(), scenarioStat.getScenarioType()) && Intrinsics.areEqual(scenarioStat2.getEntryMode(), scenarioStat.getEntryMode())) {
                break;
            }
        }
        ScenarioStat scenarioStat3 = (ScenarioStat) next;
        if (scenarioStat3 == null) {
            ScenarioStat scenarioStat4 = new ScenarioStat();
            scenarioStat4.setScenarioType(scenarioStat.getScenarioType());
            scenarioStat4.setEntryMode(scenarioStat.getEntryMode());
            scenarioStat4.getEntryCount().set(1);
            scenarioStat4.getFunctionStats().putAll(scenarioStat.getFunctionStats());
            statsList.add(scenarioStat4);
            return;
        }
        scenarioStat3.getEntryCount().incrementAndGet();
        for (Map.Entry<String, Integer> entry : scenarioStat.getFunctionStats().entrySet()) {
            String key = entry.getKey();
            scenarioStat3.getFunctionStats().put(key, Integer.valueOf(scenarioStat3.getFunctionStats().getOrDefault(key, 0).intValue() + entry.getValue().intValue()));
        }
    }

    public final String getStatsString() {
        CopyOnWriteArrayList<ScenarioStat> copyOnWriteArrayList = statsList;
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(copyOnWriteArrayList, ";", (CharSequence) null, (CharSequence) null, new ScenarioStatUtil$$ExternalSyntheticLambda0(0), 30);
        copyOnWriteArrayList.clear();
        return strJoinToString$default;
    }
}
