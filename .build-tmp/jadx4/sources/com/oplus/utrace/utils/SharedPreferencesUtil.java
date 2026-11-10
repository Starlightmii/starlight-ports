package com.oplus.utrace.utils;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SharedPreferencesUtil {
    public static final SharedPreferencesUtil INSTANCE = new SharedPreferencesUtil();
    private static SharedPreferences sps;

    public static final boolean getBoolean(Context context, String str) {
        SharedPreferences sps2;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null) {
            return false;
        }
        return sps2.getBoolean(str, false);
    }

    public static final int getInt(Context context, String str) {
        SharedPreferences sps2;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null) {
            return 0;
        }
        return sps2.getInt(str, 0);
    }

    public static final long getLong(Context context, String str) {
        SharedPreferences sps2;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null) {
            return 0L;
        }
        return sps2.getLong(str, 0L);
    }

    public static final SharedPreferences getSps(Context context) {
        if (sps == null) {
            int i = UtilsKt.$r8$clinit;
            UserUnlockManager.INSTANCE.getClass();
            if (UserUnlockManager.isUnlocked()) {
                synchronized (INSTANCE) {
                    if (sps == null) {
                        sps = context.getSharedPreferences("utrace", 0);
                    }
                }
            }
        }
        return sps;
    }

    public static final String getString(Context context, String str) {
        String string;
        if (StringsKt__StringsKt.isBlank(str)) {
            return null;
        }
        SharedPreferences sps2 = getSps(context);
        return (sps2 == null || (string = sps2.getString(str, "")) == null) ? "" : string;
    }

    public static final void putBoolean(Context context, String str, boolean z) {
        SharedPreferences sps2;
        SharedPreferences.Editor editorEdit;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null || (editorEdit = sps2.edit()) == null) {
            return;
        }
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public static final void putInt(Context context, String str, int i, boolean z) {
        SharedPreferences sps2;
        SharedPreferences.Editor editorEdit;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null || (editorEdit = sps2.edit()) == null) {
            return;
        }
        editorEdit.putInt(str, i);
        if (z) {
            editorEdit.commit();
        } else {
            editorEdit.apply();
        }
    }

    public static final void putLong(Context context, String str, long j) {
        SharedPreferences sps2;
        SharedPreferences.Editor editorEdit;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null || (editorEdit = sps2.edit()) == null) {
            return;
        }
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    public static final void putString(Context context, String str, String str2) {
        SharedPreferences sps2;
        SharedPreferences.Editor editorEdit;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null || (editorEdit = sps2.edit()) == null) {
            return;
        }
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public static final void remove(Context context, String str) {
        SharedPreferences sps2;
        SharedPreferences.Editor editorEdit;
        if (StringsKt__StringsKt.isBlank(str) || (sps2 = getSps(context)) == null || (editorEdit = sps2.edit()) == null) {
            return;
        }
        editorEdit.remove(str);
        editorEdit.apply();
    }
}
