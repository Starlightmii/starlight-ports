package io.github.andrea_lyz.assistrestore.ui;

import android.content.Context;
import android.content.SharedPreferences;
import io.github.andrea_lyz.assistrestore.AssistConfig;
import io.github.libxposed.service.XposedService;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class SettingsStore {
    public static final int $stable = 8;
    private final SharedPreferences fallback;
    private boolean frameworkConnected;
    private volatile SharedPreferences remote;

    public SettingsStore(Context context) {
        b4.l.e("context", context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(AssistConfig.PREFS, 0);
        b4.l.d("getSharedPreferences(...)", sharedPreferences);
        this.fallback = sharedPreferences;
    }

    private final SharedPreferences current() {
        SharedPreferences sharedPreferences = this.remote;
        return sharedPreferences == null ? this.fallback : sharedPreferences;
    }

    private final void migrateLocalValues(SharedPreferences sharedPreferences) {
        Map<String, ?> all = this.fallback.getAll();
        if (all.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Integer) {
                editorEdit.putInt(key, ((Number) value).intValue());
            } else if (value instanceof Long) {
                editorEdit.putLong(key, ((Number) value).longValue());
            } else if (value instanceof Float) {
                editorEdit.putFloat(key, ((Number) value).floatValue());
            } else if (value instanceof String) {
                editorEdit.putString(key, (String) value);
            }
        }
        editorEdit.apply();
    }

    private final void put(String str, Object obj) {
        SharedPreferences.Editor editorEdit = current().edit();
        if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Number) obj).intValue());
        } else {
            editorEdit.putString(str, obj.toString());
        }
        editorEdit.apply();
    }

    public static /* synthetic */ void setTarget$default(SettingsStore settingsStore, String str, String str2, String str3, String str4, String str5, String str6, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            str3 = null;
        }
        if ((i5 & 8) != 0) {
            str4 = null;
        }
        if ((i5 & 16) != 0) {
            str5 = null;
        }
        if ((i5 & 32) != 0) {
            str6 = null;
        }
        settingsStore.setTarget(str, str2, str3, str4, str5, str6);
    }

    public final void bind(XposedService xposedService) {
        SharedPreferences remotePreferences = null;
        if (xposedService != null) {
            try {
                remotePreferences = xposedService.getRemotePreferences(AssistConfig.PREFS);
            } catch (Throwable unused) {
            }
        }
        this.remote = remotePreferences;
        this.frameworkConnected = remotePreferences != null;
        if (remotePreferences != null) {
            migrateLocalValues(remotePreferences);
        }
    }

    public final boolean doublePressWallet() {
        return current().getBoolean(AssistConfig.KEY_DOUBLE_PRESS_WALLET, false);
    }

    public final boolean getFrameworkConnected() {
        return this.frameworkConnected;
    }

    public final boolean handleWhenBarHidden() {
        return current().getBoolean(AssistConfig.KEY_HANDLE_WHEN_BAR_HIDDEN, true);
    }

    public final boolean isEnabled() {
        return current().getBoolean(AssistConfig.KEY_ENABLED, true);
    }

    public final String mode(String str) {
        b4.l.e("entry", str);
        String string = current().getString(str.concat("_mode"), AssistConfig.MODE_DEFAULT);
        return string == null ? AssistConfig.MODE_DEFAULT : string;
    }

    public final void setDoublePressWallet(boolean z2) {
        put(AssistConfig.KEY_DOUBLE_PRESS_WALLET, Boolean.valueOf(z2));
    }

    public final void setEnabled(boolean z2) {
        put(AssistConfig.KEY_ENABLED, Boolean.valueOf(z2));
    }

    public final void setHandleWhenBarHidden(boolean z2) {
        put(AssistConfig.KEY_HANDLE_WHEN_BAR_HIDDEN, Boolean.valueOf(z2));
    }

    public final void setSkipOcrPreload(boolean z2) {
        put(AssistConfig.KEY_SKIP_OCR_PRELOAD, Boolean.valueOf(z2));
    }

    public final void setSpoofGoogleBuild(boolean z2) {
        put(AssistConfig.KEY_SPOOF_GOOGLE_BUILD, Boolean.valueOf(z2));
    }

    public final void setTarget(String str, String str2, String str3, String str4, String str5, String str6) {
        b4.l.e("entry", str);
        b4.l.e("mode", str2);
        SharedPreferences.Editor editorEdit = current().edit();
        editorEdit.putString(str.concat("_mode"), str2);
        if (str3 != null) {
            editorEdit.putString(str.concat("_package"), str3);
        }
        if (str4 != null) {
            editorEdit.putString(str.concat("_component"), str4);
        }
        if (str5 != null) {
            editorEdit.putString(str.concat("_method"), str5);
        }
        if (str6 != null) {
            editorEdit.putString(str.concat("_args"), str6);
        }
        editorEdit.apply();
    }

    public final void setUnblockPageFlags(boolean z2) {
        put(AssistConfig.KEY_UNBLOCK_PAGE_FLAGS, Boolean.valueOf(z2));
    }

    public final boolean skipOcrPreload() {
        return current().getBoolean(AssistConfig.KEY_SKIP_OCR_PRELOAD, true);
    }

    public final boolean spoofGoogleBuild() {
        return current().getBoolean(AssistConfig.KEY_SPOOF_GOOGLE_BUILD, true);
    }

    public final String targetArgs(String str) {
        b4.l.e("entry", str);
        String string = current().getString(str.concat("_args"), "");
        return string == null ? "" : string;
    }

    public final String targetComponent(String str) {
        b4.l.e("entry", str);
        String string = current().getString(str.concat("_component"), "");
        return string == null ? "" : string;
    }

    public final String targetMethod(String str) {
        b4.l.e("entry", str);
        String string = current().getString(str.concat("_method"), AssistConfig.METHOD_AUTO);
        return string == null ? AssistConfig.METHOD_AUTO : string;
    }

    public final String targetPackage(String str) {
        b4.l.e("entry", str);
        String string = current().getString(str.concat("_package"), "");
        return string == null ? "" : string;
    }

    public final boolean unblockPageFlags() {
        return current().getBoolean(AssistConfig.KEY_UNBLOCK_PAGE_FLAGS, true);
    }
}
