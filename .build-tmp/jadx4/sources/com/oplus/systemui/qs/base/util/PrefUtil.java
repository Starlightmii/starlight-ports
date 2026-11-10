package com.oplus.systemui.qs.base.util;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrefUtil {
    public static final PrefUtil INSTANCE = new PrefUtil();

    public static final SharedPreferences get(Context context) {
        return context.getSharedPreferences(context.getPackageName(), 0);
    }

    public static final int getInt(Context context, String str, int i) {
        return get(context).getInt(str, i);
    }

    public static final void putInt(Context context, String str, int i) {
        get(context).edit().putInt(str, i).apply();
    }
}
