package com.oplus.utrace.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExceptionProtectUtil {
    public static final ExceptionProtectUtil INSTANCE = new ExceptionProtectUtil();
    public static boolean isTesting;
    private static Context mContext;
    public static volatile long queryTime;
    public static volatile boolean userExperienceProgram;

    public static final boolean checkUserSwitch() {
        Object obj;
        ContentResolver contentResolver;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - queryTime > 60000) {
            try {
                Context context = mContext;
                obj = Result.constructor-impl((context == null || (contentResolver = context.getContentResolver()) == null) ? null : Integer.valueOf(Settings.System.getInt(contentResolver, "oplus_customize_cta_user_experience")));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Logs.INSTANCE.w("UTrace.Lib.ProtectUtil", Intrinsics.stringPlus(th2.getMessage(), "isUserExperienceToggleOn Exception: "), th2);
            }
            Integer num = (Integer) (obj instanceof Result.Failure ? null : obj);
            Logs logs = Logs.INSTANCE;
            logs.d("UTrace.Lib.ProtectUtil", Intrinsics.stringPlus(num, "isUserExperienceToggleOn result="));
            userExperienceProgram = num != null && num.intValue() == 1;
            logs.i("UTrace.Lib.ProtectUtil", Intrinsics.stringPlus(Boolean.valueOf(userExperienceProgram), "userExperienceProgram: "));
            queryTime = jCurrentTimeMillis;
        }
        return userExperienceProgram || isTesting;
    }

    public static final void setContext(Context context) {
        mContext = context;
    }

    public final void setTesting(boolean z) {
        isTesting = z;
    }
}
