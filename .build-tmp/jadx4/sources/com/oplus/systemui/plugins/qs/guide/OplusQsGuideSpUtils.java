package com.oplus.systemui.plugins.qs.guide;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpUtils {
    public static final String GUIDE_ANIMATION_END_TIMES = "guide_animation_end_times";
    public static final String NEED_TEST_GUIDE = "need_test_guide";
    public static final String NOTIFICATION_SHOW_GUIDE = "notification_show_guide";
    public static final String QUICK_SETTINGS_SCENARIO_ASSISTANT_SHOW_GUIDE_LAST_DATE = "quick_settings_scenario_assistant_show_guide_last_date";
    public static final String QUICK_SETTINGS_SCENARIO_ASSISTANT_SHOW_GUIDE_TOTAL_COUNT = "quick_settings_scenario_assistant_show_guide_total_count";
    public static final String QUICK_SETTINGS_SHOW_GUIDE = "quick_settings_show_guide";
    private static final String SP_FILE_NAME = "qs_guide_sp";
    public static final String SUPPORT_SEPARATE_GUIDE = "support_separate_guide";
    private static final String TAG = "OplusQsGuideSpUtils";
    public static final OplusQsGuideSpUtils INSTANCE = new OplusQsGuideSpUtils();
    private static ConcurrentHashMap<Integer, Boolean> isSupportQsGuide = new ConcurrentHashMap<>();

    public static final void clearScenarioAssistantSharedPreferences(Context context, int i) {
        if (i == 0) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, " clearScenarioAssistantSharedPreferences userId="), null, 8);
        SharedPreferences sharedPreferences = getSharedPreferences(context, Integer.valueOf(i));
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().clear().apply();
    }

    public static final boolean getGuideSpBooleanValue(Context context, Integer num, String str, boolean z) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, num);
        if (sharedPreferences == null) {
            return z;
        }
        boolean z2 = sharedPreferences.getBoolean(str, z);
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(" getGuideSpValue: ", str, ", ", z2, " userId=");
        sbM.append(num);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        return z2;
    }

    public static final int getGuideSpIntValue(Context context, Integer num, String str, int i) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, num);
        if (sharedPreferences == null) {
            return i;
        }
        int i2 = sharedPreferences.getInt(str, i);
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i2, " getGuideSpValue: ", str, ", ", ", ");
        sbM.append(num);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        return i2;
    }

    public static final long getGuideSpLongValue(Context context, Integer num, String str, long j) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, num);
        if (sharedPreferences == null) {
            return j;
        }
        long j2 = sharedPreferences.getLong(str, j);
        StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(j2, " getGuideSpLongValue: ", str, ", ");
        sbM.append(" userId=");
        sbM.append(num);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        return j2;
    }

    public static final SharedPreferences getSharedPreferences(Context context, Integer num) {
        if (num == null || num.intValue() == 0) {
            return getSharedPreferences(context);
        }
        return context.getSharedPreferences(SP_FILE_NAME + num, 0);
    }

    public static final int getTestGuideMode(Context context) {
        int intForUser = Settings.System.getIntForUser(context.getContentResolver(), NEED_TEST_GUIDE, 0, -2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(intForUser, " getTestGuideMode： "), null, 8);
        return intForUser;
    }

    public static final boolean isNeedGuide(Context context) {
        if (getTestGuideMode(context) >= 1) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " isNeedGuide true ,is Test Guide mode", null, 8);
            return true;
        }
        if (!getGuideSpBooleanValue(context, NOTIFICATION_SHOW_GUIDE, false)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "isNeedGuide:true ,for support_separate_guide is 0", null, 8);
            return true;
        }
        if (!getGuideSpBooleanValue(context, QUICK_SETTINGS_SHOW_GUIDE, false)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "isNeedGuide:true ,for support_separate_guide is 0", null, 8);
            return true;
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isSupportScenarioAssistant() || ScenarioAssistantGuideProvider.INSTANCE.isQsScenarioAssistantGuideCompleted(context)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "isNeedGuide: return false", null, 8);
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "isNeedGuide:true ,for quick_settings_scenario_assistant_show_guide_clicked is 0", null, 8);
        return true;
    }

    public static final boolean isSupportGuide(Context context) {
        int iIntValue = ((Number) OplusQSSpecialModeProvider.Companion.getInstance().getUserId().getValue()).intValue();
        Boolean bool = isSupportQsGuide.get(Integer.valueOf(iIntValue));
        if (bool != null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " isSupportGuide cache=" + bool + " userId=" + iIntValue, null, 8);
            return bool.booleanValue();
        }
        int intForUser = Settings.System.getIntForUser(context.getContentResolver(), SUPPORT_SEPARATE_GUIDE, 1, -2);
        isSupportQsGuide.put(Integer.valueOf(iIntValue), Boolean.valueOf(intForUser == 1));
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " isSupportGuide value=" + intForUser + " userId=" + iIntValue, null, 8);
        Boolean bool2 = isSupportQsGuide.get(Integer.valueOf(iIntValue));
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return true;
    }

    public static final void setGuideSpBooleanValue(Context context, Integer num, String str, boolean z) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, num);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean(str, z);
            editorEdit.apply();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " setGuideSpValue: " + str + ", " + z + " userId=" + num, null, 8);
        }
    }

    public static final void setGuideSpIntValue(Context context, Integer num, String str, int i) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, num);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(str, i);
            editorEdit.apply();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " setGuideSpValue: " + str + ",  " + i + " userId=" + num, null, 8);
        }
    }

    public static final void setGuideSpLongValue(Context context, Integer num, String str, long j) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, num);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putLong(str, j);
            editorEdit.apply();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " setGuideSpLongValue: " + str + ",  " + j + " userId=" + num, null, 8);
        }
    }

    public static final void setSupportGuide(Context context, boolean z) {
        int iIntValue = ((Number) OplusQSSpecialModeProvider.Companion.getInstance().getUserId().getValue()).intValue();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" setSupportGuide： ", z, " userId=", iIntValue), null, 8);
        isSupportQsGuide.put(Integer.valueOf(iIntValue), Boolean.valueOf(z));
        Settings.System.putIntForUser(context.getContentResolver(), SUPPORT_SEPARATE_GUIDE, z ? 1 : 0, -2);
    }

    public static final SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_FILE_NAME, 0);
    }

    public static final int getGuideSpIntValue(Context context, String str, int i) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences == null) {
            return i;
        }
        int i2 = sharedPreferences.getInt(str, i);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" getGuideSpValue: ", str, ", ", i2), null, 8);
        return i2;
    }

    public static final boolean getGuideSpBooleanValue(Context context, String str, boolean z) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences == null) {
            return z;
        }
        boolean z2 = sharedPreferences.getBoolean(str, z);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" getGuideSpValue: ", str, ", ", z2), null, 8);
        return z2;
    }

    public static final void setGuideSpBooleanValue(Context context, String str, boolean z) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean(str, z);
            editorEdit.apply();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " setGuideSpValue: " + str + ", " + z, null, 8);
        }
    }

    public static final void setGuideSpIntValue(Context context, String str, int i) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(str, i);
            editorEdit.apply();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " setGuideSpValue: " + str + ",  " + i, null, 8);
        }
    }
}
