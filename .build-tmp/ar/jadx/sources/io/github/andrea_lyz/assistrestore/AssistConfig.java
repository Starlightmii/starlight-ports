package io.github.andrea_lyz.assistrestore;

import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AssistConfig {
    public static final boolean DEFAULT_DOUBLE_PRESS_WALLET = false;
    public static final boolean DEFAULT_ENABLED = true;
    public static final boolean DEFAULT_HANDLE_WHEN_BAR_HIDDEN = true;
    public static final boolean DEFAULT_SKIP_OCR_PRELOAD = true;
    public static final boolean DEFAULT_SPOOF_GOOGLE_BUILD = true;
    public static final boolean DEFAULT_UNBLOCK_PAGE_FLAGS = true;
    public static final String ENTRY_CORNER = "corner";
    public static final String ENTRY_HANDLE = "handle";
    public static final String ENTRY_POWER = "power";
    public static final String KEY_DOUBLE_PRESS_WALLET = "double_press_wallet";
    public static final String KEY_ENABLED = "enabled";
    public static final String KEY_HANDLE_WHEN_BAR_HIDDEN = "handle_when_bar_hidden";
    public static final String KEY_SKIP_OCR_PRELOAD = "skip_ocr_preload";
    public static final String KEY_SPOOF_GOOGLE_BUILD = "spoof_google_build";
    public static final String KEY_UNBLOCK_PAGE_FLAGS = "unblock_page_flags";
    public static final String METHOD_ASSIST = "assist";
    public static final String METHOD_AUTO = "auto";
    public static final String METHOD_INTENT = "intent";
    public static final String MODE_APP = "app";
    public static final String MODE_CTS = "cts";
    public static final String MODE_CUSTOM = "custom";
    public static final String MODE_DEFAULT = "default";
    public static final String MODE_NONE = "none";
    public static final String MODE_OEM = "oem";
    public static final boolean PAGE_FLAGS_SUPPORTED;
    public static final String PREFS = "assist_restore";

    static {
        PAGE_FLAGS_SUPPORTED = Build.VERSION.SDK_INT < 37;
    }

    private AssistConfig() {
    }

    public static boolean doublePressWallet(SharedPreferences sharedPreferences) {
        return sharedPreferences != null && sharedPreferences.getBoolean(KEY_DOUBLE_PRESS_WALLET, false);
    }

    public static boolean handleWhenBarHidden(SharedPreferences sharedPreferences) {
        return sharedPreferences == null || sharedPreferences.getBoolean(KEY_HANDLE_WHEN_BAR_HIDDEN, true);
    }

    public static boolean isEnabled(SharedPreferences sharedPreferences) {
        return sharedPreferences == null || sharedPreferences.getBoolean(KEY_ENABLED, true);
    }

    public static String mode(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences == null) {
            return MODE_DEFAULT;
        }
        return sharedPreferences.getString(str + "_mode", MODE_DEFAULT);
    }

    public static boolean skipOcrPreload(SharedPreferences sharedPreferences) {
        return sharedPreferences == null || sharedPreferences.getBoolean(KEY_SKIP_OCR_PRELOAD, true);
    }

    public static boolean spoofGoogleBuild(SharedPreferences sharedPreferences) {
        return sharedPreferences == null || sharedPreferences.getBoolean(KEY_SPOOF_GOOGLE_BUILD, true);
    }

    public static String targetArgs(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(str + "_args", "");
    }

    public static String targetComponent(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(str + "_component", "");
    }

    public static String targetMethod(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences == null) {
            return METHOD_AUTO;
        }
        return sharedPreferences.getString(str + "_method", METHOD_AUTO);
    }

    public static String targetPackage(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(str + "_package", "");
    }

    public static boolean unblockPageFlags(SharedPreferences sharedPreferences) {
        return sharedPreferences == null || sharedPreferences.getBoolean(KEY_UNBLOCK_PAGE_FLAGS, true);
    }
}
