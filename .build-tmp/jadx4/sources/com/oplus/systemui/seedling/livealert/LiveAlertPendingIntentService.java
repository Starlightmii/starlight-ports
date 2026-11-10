package com.oplus.systemui.seedling.livealert;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LiveAlertPendingIntentService extends Service {
    public static final String EVENT_TYPE = "EventType";
    private static final String TAG = "LiveAlertPendingIntentService";
    public static final Companion Companion = new Companion();
    private static ConcurrentHashMap<String, Function1> serverEventMap = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void registerServerEvent(String str, Function1 function1) {
            LogUtil.d(LiveAlertPendingIntentService.TAG, "registerServerEvent, event:" + str);
            if (function1 != null) {
                LiveAlertPendingIntentService.serverEventMap.put(str, function1);
            }
        }
    }

    public static final void registerServerEvent(String str, Function1 function1) {
        Companion.registerServerEvent(str, function1);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Bundle extras;
        LogUtil.i(TAG, "onStartCommand, intent:" + intent + ", flags:" + i + ", startId:" + i2);
        if (intent == null || (extras = intent.getExtras()) == null) {
            return 1;
        }
        Bundle bundle = new Bundle(extras);
        LogUtil.d(TAG, "onStartCommand, serverEventMap:" + serverEventMap + "  ++++");
        Set<String> setKeySet = bundle.keySet();
        if (setKeySet != null) {
            for (String str : setKeySet) {
                LogUtil.d(TAG, "extra key:" + str + ", value:" + bundle.get(str));
            }
        }
        LogUtil.d(TAG, "onStartCommand, serverEventMap   ----");
        Function1 function1 = serverEventMap.get(bundle.getString("EventType"));
        if (function1 == null) {
            return 1;
        }
        function1.invoke(bundle);
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
