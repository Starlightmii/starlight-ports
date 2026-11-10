package io.github.andrea_lyz.assistrestore;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class TargetIntents {
    private static final String ACTION_ASSIST = "android.intent.action.ASSIST";
    private static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD = "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";

    private TargetIntents() {
    }

    public static Context appContext() {
        try {
            Object objInvoke = Class.forName("android.app.ActivityThread").getMethod("currentApplication", null).invoke(null, null);
            if (objInvoke instanceof Context) {
                return (Context) objInvoke;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static void applyArgs(Intent intent, String str, String str2, String str3) {
        String strTrim = "";
        String strTrim2 = str2 == null ? "" : str2.trim();
        if (str3 != null && str3.contains("/")) {
            int iIndexOf = str3.indexOf(47);
            String strTrim3 = str3.substring(0, iIndexOf).trim();
            strTrim = str3.substring(iIndexOf + 1).trim();
            strTrim2 = strTrim3;
        } else if (str3 != null && !str3.trim().isEmpty()) {
            strTrim = str3.trim();
        }
        if (str != null && !str.isEmpty()) {
            for (String str4 : str.replace('{', ' ').replace('}', ' ').replace('\"', ' ').replace('\n', ' ').replace('\r', ' ').split("[&,\\u00b7\\s]+")) {
                if (!str4.isEmpty()) {
                    int iIndexOf2 = str4.indexOf(61);
                    if (iIndexOf2 < 0) {
                        iIndexOf2 = str4.indexOf(58);
                    }
                    if (iIndexOf2 > 0) {
                        String strTrim4 = str4.substring(0, iIndexOf2).trim();
                        String strTrim5 = str4.substring(iIndexOf2 + 1).trim();
                        if (!strTrim5.isEmpty()) {
                            if ("action".equals(strTrim4)) {
                                intent.setAction(strTrim5);
                            } else if ("category".equals(strTrim4)) {
                                intent.addCategory(strTrim5);
                            } else if ("packageName".equals(strTrim4) || "package".equals(strTrim4)) {
                                strTrim2 = strTrim5;
                            } else if ("className".equals(strTrim4) || "component".equals(strTrim4)) {
                                strTrim = strTrim5;
                            } else if (!"extra".equals(strTrim4)) {
                                try {
                                    intent.putExtra(strTrim4, Integer.parseInt(strTrim5));
                                } catch (NumberFormatException unused) {
                                    intent.putExtra(strTrim4, strTrim5);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (strTrim2.isEmpty() || strTrim.isEmpty()) {
            return;
        }
        if (strTrim.startsWith(".")) {
            strTrim = strTrim2.concat(strTrim);
        }
        intent.setComponent(new ComponentName(strTrim2, strTrim));
    }

    public static Intent build(Context context, SharedPreferences sharedPreferences, String str) {
        String strTargetPackage = AssistConfig.targetPackage(sharedPreferences, str);
        if (strTargetPackage == null || strTargetPackage.isEmpty()) {
            return null;
        }
        if (AssistConfig.METHOD_INTENT.equals(AssistConfig.targetMethod(sharedPreferences, str))) {
            String strTargetComponent = AssistConfig.targetComponent(sharedPreferences, str);
            Intent intent = new Intent();
            applyArgs(intent, AssistConfig.targetArgs(sharedPreferences, str), strTargetPackage, strTargetComponent);
            if (intent.getComponent() == null && intent.getAction() == null) {
                return null;
            }
            intent.addFlags(268435456);
            return intent;
        }
        String strTargetComponent2 = AssistConfig.targetComponent(sharedPreferences, str);
        if (strTargetComponent2 != null && !strTargetComponent2.trim().isEmpty()) {
            Intent intent2 = new Intent();
            applyArgs(intent2, AssistConfig.targetArgs(sharedPreferences, str), strTargetPackage, strTargetComponent2);
            if (intent2.getComponent() != null) {
                intent2.addFlags(268435456);
                return intent2;
            }
        }
        Intent intentResolveAssistActivity = resolveAssistActivity(context, strTargetPackage);
        if (intentResolveAssistActivity != null) {
            return intentResolveAssistActivity;
        }
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strTargetPackage);
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268435456);
        }
        return launchIntentForPackage;
    }

    private static Intent resolveAssistActivity(Context context, String str) {
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(ACTION_ASSIST).setPackage(str), 0);
            if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty()) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(0);
                Intent intent = new Intent(ACTION_ASSIST);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
                intent.addFlags(268435456);
                Bundle bundle = new Bundle();
                bundle.putBoolean(EXTRA_ASSIST_INPUT_HINT_KEYBOARD, false);
                intent.putExtras(bundle);
                return intent;
            }
        } catch (Throwable unused) {
        }
        return null;
    }
}
