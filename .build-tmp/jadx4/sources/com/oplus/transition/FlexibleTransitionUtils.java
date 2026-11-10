package com.oplus.transition;

import android.os.Bundle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FlexibleTransitionUtils {
    public static final String KEY_FLEXIBLE_ACTIVITY_IN_SUBSCENARIO = "flexible_activity_in_subscenario";
    public static final String KEY_FLEXIBLE_TASK_SCALE = "flexible_scale";
    public static final String KEY_FLEXIBLE_TASK_SCENARIO = "flexible_scenario";
    public static final String KEY_PS_CORNER_RADIUS = "ps_embedded_activity_corner_radius";
    public static final String KEY_PS_EMBEDDED_ACTIVITY_TRANSITION = "ps_embedded_activity_transition";
    public static final int LAUNCH_SCENARIO_SUB_DISPLAY = 3;

    public static float getFlexibleScale(Bundle bundle) {
        float f = bundle != null ? bundle.getFloat(KEY_FLEXIBLE_TASK_SCALE) : 0.0f;
        if (getFlexibleScenario(bundle) == 3) {
            return f;
        }
        return 0.0f;
    }

    public static int getFlexibleScenario(Bundle bundle) {
        if (bundle != null) {
            return bundle.getInt(KEY_FLEXIBLE_TASK_SCENARIO);
        }
        return -1;
    }

    public static boolean isActivityInSubscenario(Bundle bundle) {
        return bundle != null && bundle.getBoolean(KEY_FLEXIBLE_ACTIVITY_IN_SUBSCENARIO);
    }

    public static boolean isFlexibleActivity(Bundle bundle) {
        return bundle != null && bundle.getBoolean("isFlexibleActivity");
    }

    public static boolean isFlexibleActivityTransition(Bundle bundle) {
        return bundle != null && bundle.getBoolean("flexible_activity_transition");
    }

    public static boolean isPsEmbeddedActivityTransition(Bundle bundle) {
        return bundle != null && bundle.getBoolean(KEY_PS_EMBEDDED_ACTIVITY_TRANSITION);
    }
}
