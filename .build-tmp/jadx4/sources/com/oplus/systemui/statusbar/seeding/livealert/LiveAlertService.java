package com.oplus.systemui.statusbar.seeding.livealert;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import androidx.room.util.CursorUtil$;
import com.oplus.systemui.statusbar.seeding.OplusLiveAlertViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LiveAlertService extends Service {
    public static final Companion Companion = new Companion();
    public static final String EXTRA_CHIP_TYPE = "chip_type";
    public static final String EXTRA_PACKAGE_NAME = "packageName";
    public static final String EXTRA_STOP_ID = "stopId";
    private static final String KEY_CALL_ARG = "arg";
    private static final String KEY_CALL_AUTHORITY = "authority";
    private static final String KEY_CALL_EXTRAS = "extras";
    private static final String KEY_CALL_METHOD = "method";
    public static final String LIVE_ALERT_SERVICE_ACTION = "com.oplusos.livealert.action.LIVE_ALERT";
    private static final String TAG = "LiveAlertService";
    private final Handler bgHandler;
    private final OplusLiveAlertViewModel oplusLiveAlertViewModel;

    public LiveAlertService(OplusLiveAlertViewModel oplusLiveAlertViewModel, Handler handler) {
        this.oplusLiveAlertViewModel = oplusLiveAlertViewModel;
        this.bgHandler = handler;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String str;
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, LIVE_ALERT_SERVICE_ACTION)) {
            String stringExtra = intent.getStringExtra(KEY_CALL_AUTHORITY);
            if (stringExtra == null) {
                stringExtra = "";
            }
            if (stringExtra.length() > 0) {
                String stringExtra2 = intent.getStringExtra(KEY_CALL_METHOD);
                if (stringExtra2 == null) {
                    stringExtra2 = "";
                }
                Bundle bundleExtra = intent.getBundleExtra(KEY_CALL_EXTRAS);
                String stringExtra3 = intent.getStringExtra(KEY_CALL_ARG);
                str = stringExtra3 != null ? stringExtra3 : "";
                Handler handler = this.bgHandler;
                onStartCommand.1 r5 = new onStartCommand.1();
                r5.this$0 = this;
                r5.$authority = stringExtra;
                r5.$method = stringExtra2;
                r5.$arg = str;
                r5.$extras = bundleExtra;
                VarHandle.storeStoreFence();
                handler.post(r5);
            } else {
                String stringExtra4 = intent.getStringExtra(EXTRA_STOP_ID);
                if (stringExtra4 == null) {
                    stringExtra4 = "";
                }
                String stringExtra5 = intent.getStringExtra(EXTRA_PACKAGE_NAME);
                str = stringExtra5 != null ? stringExtra5 : "";
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, CursorUtil$.ExternalSyntheticOutline0.m("stopId: ", stringExtra4, ", package: ", str), null, 8);
                Function1 stopAction = this.oplusLiveAlertViewModel.getStopAction(stringExtra4);
                if (stopAction != null) {
                    stopAction.invoke(str.length() != 0 ? str : null);
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "getStopAction returned null for stopId=".concat(stringExtra4));
                }
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
