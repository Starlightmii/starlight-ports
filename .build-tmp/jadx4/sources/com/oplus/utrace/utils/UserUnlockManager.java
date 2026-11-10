package com.oplus.utrace.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.os.UserManager;
import androidx.core.app.NotificationCompat$Builder$;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UserUnlockManager {
    private static final String TAG = "UTrace.Lib.UserUnlock";
    private static Context context;
    public static final UserUnlockManager INSTANCE = new UserUnlockManager();
    private static final Set<UserUnlockListener> mObservers = new LinkedHashSet();
    private static Long unlockCacheTime = 0L;
    private static final BroadcastReceiver userUnlockReceiver = new BroadcastReceiver() { // from class: com.oplus.utrace.utils.UserUnlockManager$userUnlockReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context2, Intent intent) {
            Logs.INSTANCE.i("UTrace.Lib.UserUnlock", "Broadcast received " + ((Object) intent.getAction()) + ", user unlock! observers count:" + UserUnlockManager.mObservers.size());
            UserUnlockManager.access$notifyAllObserver(UserUnlockManager.INSTANCE);
        }
    };

    public static final void access$notifyAllObserver(UserUnlockManager userUnlockManager) {
        userUnlockManager.getClass();
        Iterator it = CollectionsKt___CollectionsKt.toList(mObservers).iterator();
        if (it.hasNext()) {
            throw NotificationCompat$Builder$.ExternalSyntheticOutline0.m(it);
        }
    }

    public static boolean isUnlocked() {
        Object obj;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Long l = unlockCacheTime;
        if (l != null && jElapsedRealtime - l.longValue() < 5000) {
            return true;
        }
        try {
            Context context2 = context;
            Object systemService = context2 == null ? null : context2.getSystemService("user");
            UserManager userManager = systemService instanceof UserManager ? (UserManager) systemService : null;
            obj = Result.constructor-impl(userManager == null ? null : Boolean.valueOf(userManager.isUserUnlocked()));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Boolean bool = (Boolean) obj;
        Logs logs = Logs.INSTANCE;
        StringBuilder sb = new StringBuilder("isUnlocked() result=");
        sb.append(bool);
        sb.append(" pkg=");
        Context context3 = context;
        sb.append((Object) (context3 == null ? null : context3.getPackageName()));
        logs.i(TAG, sb.toString());
        boolean zBooleanValue = bool == null ? false : bool.booleanValue();
        unlockCacheTime = zBooleanValue ? Long.valueOf(jElapsedRealtime) : null;
        return zBooleanValue;
    }

    public static void release() {
        Object obj;
        Unit unit;
        Logs.INSTANCE.i(TAG, "Start release, unregister receiver");
        try {
            mObservers.clear();
            Context context2 = context;
            if (context2 == null) {
                unit = null;
            } else {
                context2.unregisterReceiver(userUnlockReceiver);
                unit = Unit.INSTANCE;
            }
            obj = Result.constructor-impl(unit);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th2, "release error, exception: "));
    }

    public final void init(Context context2) {
        Object obj;
        context = context2;
        if (isUnlocked()) {
            return;
        }
        Logs.INSTANCE.i(TAG, "Start init, register receiver, context=" + context2 + " pkg=" + ((Object) context2.getPackageName()));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        try {
            obj = Result.constructor-impl(context2.registerReceiver(userUnlockReceiver, intentFilter));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th2, "init error, exception: "));
    }

    public final void registerListener(UserUnlockListener userUnlockListener) {
        mObservers.add(userUnlockListener);
    }

    public final void unregisterListener(UserUnlockListener userUnlockListener) {
        mObservers.remove(userUnlockListener);
    }
}
