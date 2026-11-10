package io.github.andrea_lyz.assistrestore;

import android.content.SharedPreferences;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class HookPrefs {
    private static volatile boolean attached;
    private static volatile SharedPreferences prefs;

    private HookPrefs() {
    }

    public static void attach(AssistRestoreModule assistRestoreModule) {
        try {
            prefs = assistRestoreModule.getRemotePreferences(AssistConfig.PREFS);
            attached = true;
            assistRestoreModule.logInfo("hook_prefs_attached group=assist_restore");
        } catch (Throwable th) {
            prefs = null;
            attached = false;
            assistRestoreModule.logWarn("hook_prefs_failed " + th);
        }
    }

    public static SharedPreferences get() {
        return prefs;
    }

    public static boolean isAttached() {
        return attached;
    }
}
