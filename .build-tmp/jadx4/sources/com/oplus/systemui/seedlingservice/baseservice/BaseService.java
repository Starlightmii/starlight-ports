package com.oplus.systemui.seedlingservice.baseservice;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.oplus.systemui.seedlingservice.datamanager.SeedlingDataManager;
import com.oplus.systemui.seedlingservice.dnd.DndTip;
import com.oplus.systemui.seedlingservice.eventmanager.EventDispatch;
import com.oplus.systemui.seedlingservice.eventmanager.EventReceiver;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import com.oplus.systemui.seedlingservice.mediaControl.MediaControlTip;
import com.oplus.systemui.seedlingservice.opthreekey.RingModeTip;
import com.oplus.systemui.seedlingservice.statemanager.CardStateManager;
import com.oplus.systemui.seedlingservice.statemanager.StateManager;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import com.oplus.systemui.seedlingservice.utils.ThreadUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BaseService extends Service {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_EVENT = "event";
    private static final String KEY_GUARD_OCCLUDE_STATE = "isKeyguardOccluded";
    private static final String KEY_GUARD_SHOWING_STATE = "isKeyguardShowing";
    private static final int MSG_WHAT_SEEDLING_DESTROY = 4;
    private static final int MSG_WHAT_SEEDLING_KEY_GUARD_STATE_CHANGE = 3;
    private static final int MSG_WHAT_SEEDLING_UPDATA = 1;
    private static final String TAG = "BaseService";
    private Locale curLanguage;
    private EventReceiver eventReceiver;
    private final Messenger messenger = new Messenger(new ServiceHandler(ThreadUtil.INSTANCE.getWorkThread().getLooper()));
    private final CardStateManager cardStateManager = CardStateManager.Companion.getInstance();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String string;
            LogUtil.d(BaseService.TAG, "handleMessage msg=" + message.what);
            int i = message.what;
            if (i == 1) {
                Bundle data = message.getData();
                EventReceiver eventReceiver = BaseService.this.eventReceiver;
                if (eventReceiver != null) {
                    data.getClass();
                    eventReceiver.onReceive(data);
                }
                BaseService.this.cardStateManager.initClientMessenger(data.getString("seedling_event"), message.replyTo);
                return;
            }
            if (i != 3) {
                if (i != 4 || (string = message.getData().getString(BaseService.KEY_EVENT)) == null) {
                    return;
                }
                StateManager.INSTANCE.destroyEventCard(string);
                return;
            }
            Bundle data2 = message.getData();
            LogUtil.d(BaseService.TAG, "on Keyguard Change. data=" + data2);
            if (data2 != null) {
                StateManager.INSTANCE.onKeyGuardStatusChange(data2.getBoolean(BaseService.KEY_GUARD_SHOWING_STATE), data2.getBoolean(BaseService.KEY_GUARD_OCCLUDE_STATE));
            }
        }
    }

    private final Locale getLanguage() {
        Configuration configuration = getApplicationContext().getResources().getConfiguration();
        if (configuration.getLocales().isEmpty()) {
            return null;
        }
        return configuration.getLocales().get(0);
    }

    private final void initReceiver() {
        this.eventReceiver = new EventReceiver();
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (printWriter != null) {
            SeedlingDataManager.INSTANCE.dump(printWriter);
            EventDispatch.INSTANCE.dump(printWriter);
            FlashlightTip.INSTANCE.dump(printWriter);
            RingModeTip.INSTANCE.dump(printWriter);
            DndTip.INSTANCE.dump(printWriter);
            MediaControlTip.INSTANCE.dump(printWriter);
            CardStateManager.Companion.getInstance().dump(printWriter);
            StateManager.INSTANCE.dump(printWriter);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.i(TAG, "onBind");
        return this.messenger.getBinder();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.getLocales().isEmpty()) {
            return;
        }
        Locale locale = configuration.getLocales().get(0);
        if (Intrinsics.areEqual(this.curLanguage, locale)) {
            return;
        }
        this.curLanguage = locale;
        ThreadUtil.INSTANCE.getWorkHandler().post(new onConfigurationChanged.1(this));
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate");
        initReceiver();
        StateManager.INSTANCE.init(this);
        SeedlingDataManager.INSTANCE.start(this);
        LogUtil.registerLogObserver(this);
        this.curLanguage = getLanguage();
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.i(TAG, "onDestroy");
        super.onDestroy();
        this.eventReceiver = null;
        StateManager.INSTANCE.onDestroy();
        LogUtil.unRegisterLogObserver(this);
        SeedlingDataManager.INSTANCE.stop(this);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.i(TAG, "onUnbind");
        ThreadUtil.INSTANCE.getWorkHandler().post(new onUnbind.1(this));
        return super.onUnbind(intent);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
