package com.oplus.systemui.plugins.qs.guide;

import android.content.Context;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioAssistantGuideProvider {
    public static final ScenarioAssistantGuideProvider INSTANCE = new ScenarioAssistantGuideProvider();
    private static Map<Integer, Integer> isQsScenarioAssistantGuideTotalCountValue = new LinkedHashMap();
    private static Map<Integer, Long> qsScenarioAssistantGuideDateValue = new LinkedHashMap();
    private static Integer testGuideModeCache;

    private final int getUserId() {
        return ((Number) OplusQSSpecialModeProvider.Companion.getInstance().getUserId().getValue()).intValue();
    }

    public final int getIsQsScenarioAssistantGuideTotalCount(Context context) {
        Integer num = isQsScenarioAssistantGuideTotalCountValue.get(Integer.valueOf(getUserId()));
        if (num != null) {
            return num.intValue();
        }
        int guideSpIntValue = OplusQsGuideSpUtils.getGuideSpIntValue(context, Integer.valueOf(getUserId()), OplusQsGuideSpUtils.QUICK_SETTINGS_SCENARIO_ASSISTANT_SHOW_GUIDE_TOTAL_COUNT, 0);
        isQsScenarioAssistantGuideTotalCountValue.put(Integer.valueOf(getUserId()), Integer.valueOf(guideSpIntValue));
        return guideSpIntValue;
    }

    public final long getQsScenarioAssistantGuideDate(Context context) {
        Long l = qsScenarioAssistantGuideDateValue.get(Integer.valueOf(getUserId()));
        if (l != null) {
            return l.longValue();
        }
        long guideSpLongValue = OplusQsGuideSpUtils.getGuideSpLongValue(context, Integer.valueOf(getUserId()), OplusQsGuideSpUtils.QUICK_SETTINGS_SCENARIO_ASSISTANT_SHOW_GUIDE_LAST_DATE, 0L);
        qsScenarioAssistantGuideDateValue.put(Integer.valueOf(getUserId()), Long.valueOf(guideSpLongValue));
        return guideSpLongValue;
    }

    public final int getTestGuideMode(Context context) {
        Integer num = testGuideModeCache;
        if (num != null) {
            return num.intValue();
        }
        int testGuideMode = OplusQsGuideSpUtils.getTestGuideMode(context);
        testGuideModeCache = Integer.valueOf(testGuideMode);
        return testGuideMode;
    }

    public final boolean isQsScenarioAssistantGuideCompleted(Context context) {
        return getIsQsScenarioAssistantGuideTotalCount(context) >= 6 && getTestGuideMode(context) < 1;
    }

    public final boolean isQsScenarioAssistantGuideTodayCompleted(Context context) {
        long qsScenarioAssistantGuideDate = getQsScenarioAssistantGuideDate(context);
        return qsScenarioAssistantGuideDate > 0 && System.currentTimeMillis() - qsScenarioAssistantGuideDate < 7200000 && getTestGuideMode(context) < 1;
    }

    public final void setIsQsScenarioAssistantGuideTotalCount(Context context, int i) {
        isQsScenarioAssistantGuideTotalCountValue.put(Integer.valueOf(getUserId()), Integer.valueOf(i));
        OplusQsGuideSpUtils.setGuideSpIntValue(context, Integer.valueOf(getUserId()), OplusQsGuideSpUtils.QUICK_SETTINGS_SCENARIO_ASSISTANT_SHOW_GUIDE_TOTAL_COUNT, i);
    }

    public final void setQsScenarioAssistantGuideDate(Context context, long j) {
        qsScenarioAssistantGuideDateValue.put(Integer.valueOf(getUserId()), Long.valueOf(j));
        OplusQsGuideSpUtils.setGuideSpLongValue(context, Integer.valueOf(getUserId()), OplusQsGuideSpUtils.QUICK_SETTINGS_SCENARIO_ASSISTANT_SHOW_GUIDE_LAST_DATE, j);
    }
}
