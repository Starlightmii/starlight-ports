package com.oplus.systemui.statusbar.notification.smallapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SmallAppUtil {
    public static AnonymousClass1 sRunnable;
    public static final Map SMALL_APP_MAP = new ConcurrentHashMap();
    public static final Handler HANDLER = ThreadManager.getInstance().getThreadHandler();

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.smallapp.SmallAppUtil$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public volatile boolean mIsCancelled;
        public /* synthetic */ Context val$context;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.mIsCancelled) {
                return;
            }
            SmallAppUtil.SMALL_APP_MAP.clear();
            Cursor cursorQuery = null;
            try {
                try {
                    Context context = this.val$context;
                    Context currentUserContext = MultiUserContentHelper.getCurrentUserContext(context);
                    if (currentUserContext != null) {
                        context = currentUserContext;
                    }
                    cursorQuery = context.getContentResolver().query(Uri.parse("content://com.nearme.instant.setting/notification"), null, null, null, null);
                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                        do {
                            if (this.mIsCancelled) {
                                SmallAppUtil.SMALL_APP_MAP.clear();
                                cursorQuery.close();
                                return;
                            } else {
                                SmallApp smallAppCreateByCursor = SmallApp.createByCursor(cursorQuery);
                                SmallAppUtil.SMALL_APP_MAP.put(smallAppCreateByCursor.getPackageName(), smallAppCreateByCursor);
                            }
                        } while (cursorQuery.moveToNext());
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e) {
                    LogUtil.e(LogUtil.TAG_COMMON, "SystemUi_SmallAppUtil", "initSmallAppInfo-e:" + e.getMessage(), e);
                    if (cursorQuery != null) {
                    }
                }
                if (LogUtil.getNormalDebug()) {
                    Log.i("SystemUi_SmallAppUtil", "initSmallAppInfo:" + SmallAppUtil.SMALL_APP_MAP);
                }
            } catch (Throwable th) {
                if (cursorQuery == null) {
                    throw th;
                }
                cursorQuery.close();
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0096  */
    /* JADX WARN: Code duplicated, block: B:45:? A[SYNTHETIC] */
    public static SmallApp getSmallAppForCache(Context context, String str) {
        Throwable th;
        Exception exc;
        Cursor cursorQuery;
        Cursor cursor = null;
        smallAppCreateByCursor = null;
        smallAppCreateByCursor = null;
        smallAppCreateByCursor = null;
        SmallApp smallAppCreateByCursor = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SmallApp smallApp = (SmallApp) SMALL_APP_MAP.get(str);
        if (smallApp != null) {
            return smallApp;
        }
        if (context == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "SystemUi_SmallAppUtil", "getSmallApp for cache failed --> context = null");
            return null;
        }
        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("getSmallApp for cache before init --> ", str, LogUtil.TAG_NOTIFICATION, "SystemUi_SmallAppUtil");
        try {
            Context currentUserContext = MultiUserContentHelper.getCurrentUserContext(context);
            if (currentUserContext != null) {
                context = currentUserContext;
            }
            cursorQuery = context.getContentResolver().query(Uri.parse("content://com.nearme.instant.setting/notification/" + str), null, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            smallAppCreateByCursor = SmallApp.createByCursor(cursorQuery);
                        }
                    } catch (Exception e) {
                        exc = e;
                        LogUtil.e(LogUtil.TAG_COMMON, "SystemUi_SmallAppUtil", "getSmallApp-e:" + exc.getMessage(), exc);
                        if (cursorQuery != null) {
                        }
                        Log.i("SystemUi_SmallAppUtil", "getSmallApp:" + smallAppCreateByCursor);
                        return smallAppCreateByCursor;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        throw th;
                    }
                    cursor.close();
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            exc = e2;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                throw th;
            }
            cursor.close();
            throw th;
        }
        Log.i("SystemUi_SmallAppUtil", "getSmallApp:" + smallAppCreateByCursor);
        return smallAppCreateByCursor;
    }

    public static void initSmallAppInfo(Context context) {
        Handler handler = HANDLER;
        if (handler == null) {
            return;
        }
        AnonymousClass1 anonymousClass1 = sRunnable;
        if (anonymousClass1 != null) {
            anonymousClass1.mIsCancelled = true;
            handler.removeCallbacks(sRunnable);
        }
        AnonymousClass1 anonymousClass2 = new AnonymousClass1();
        anonymousClass2.val$context = context;
        anonymousClass2.mIsCancelled = false;
        VarHandle.storeStoreFence();
        sRunnable = anonymousClass2;
        handler.post(anonymousClass2);
    }
}
