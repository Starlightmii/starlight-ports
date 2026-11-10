package com.oplus.systemui.statusbar.notification.keymagicservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class KeyBaseService extends Service {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyBaseService";
    private final KeyBaseController keyBaseController;
    private final Messenger messenger;

    public KeyBaseService(Looper looper, KeyBaseController keyBaseController) {
        this.keyBaseController = keyBaseController;
        this.messenger = new Messenger((Handler) new messenger.1(looper, this));
    }

    public final Messenger getMessenger() {
        return this.messenger;
    }

    public Message handleActionButtonClick() {
        return this.keyBaseController.handleActionButtonClick();
    }

    public Message handleActionButtonLongPress() {
        return this.keyBaseController.handleActionButtonLongPress();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "onBind", null, 8);
        this.keyBaseController.onCrate();
        return this.messenger.getBinder();
    }

    @Override // android.app.Service
    public final void onCreate() {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "onCreate", null, 8);
        super.onCreate();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "onUnbind", null, 8);
        this.keyBaseController.onDestroy();
        return super.onUnbind(intent);
    }
}
