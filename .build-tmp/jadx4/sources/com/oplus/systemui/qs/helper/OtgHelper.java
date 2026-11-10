package com.oplus.systemui.qs.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.oplus.systemui.common.settings.hardwarenode.Charger;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OtgHelper {
    public static volatile OtgHelper sInstance;
    public static long sLastLogTimeStamp;
    public Handler mBgHandler;
    public final Charger mCharger;
    public final Context mContext;
    public final BatteryHandler mHandler = new BatteryHandler();
    public final AnonymousClass1 mInitOtgRunnable;
    public final AnonymousClass2 mMigrateUsbReceiver;
    public boolean mMigrateUsbStarted;
    public final AnonymousClass4 mObserver;
    public int mOtgLastState;
    public final AnonymousClass2 mReceiver;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.helper.OtgHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OtgHelper this$0;

        @Override // java.lang.Runnable
        public final void run() {
            int psyOtgOnline = this.this$0.getPsyOtgOnline();
            if (psyOtgOnline != -1) {
                EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.this$0.mOtgLastState, LogUtil.TAG, "OtgHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(psyOtgOnline, "OtgCurrentState = ", ", mOtgLastState = "));
                if (psyOtgOnline != this.this$0.mOtgLastState) {
                    EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.this$0.mOtgLastState, LogUtil.TAG, "OtgHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(psyOtgOnline, "State of OTG has Changed, and otgCurrentState = ", ", mOtgLastState = "));
                    this.this$0.mOtgLastState = psyOtgOnline;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 11;
                    messageObtain.arg1 = psyOtgOnline;
                    this.this$0.mHandler.removeMessages(11);
                    this.this$0.mHandler.handleMessage(messageObtain);
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BatteryHandler extends Handler {
        public BatteryHandler() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 11) {
                return;
            }
            int i = message.arg1;
            OtgHelper otgHelper = OtgHelper.this;
            if (i != 0) {
                if (i == 1) {
                    otgHelper.cancelAutoCloseAlarm();
                }
            } else {
                int psyOtgSwitch = otgHelper.getPsyOtgSwitch();
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyOtgSwitch, "EVENT_OTG_STATE_CHANGE_otgSwitchStage = ", LogUtil.TAG, "OtgHelper");
                if (psyOtgSwitch == 1) {
                    otgHelper.setAutoCloseAlarm();
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [com.oplus.systemui.qs.helper.OtgHelper$2] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.oplus.systemui.qs.helper.OtgHelper$4] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.oplus.systemui.qs.helper.OtgHelper$2] */
    public OtgHelper(Context context) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mInitOtgRunnable = anonymousClass1;
        final int i = 0;
        this.mMigrateUsbStarted = false;
        this.mOtgLastState = 0;
        this.mContext = context;
        this.mCharger = new Charger();
        FlavorOneFeatureOption.INSTANCE.getClass();
        final int i2 = 1;
        if (FlavorOneFeatureOption.sOtgAutoCloseAlarmDisable) {
            Settings.Global.putInt(context.getContentResolver(), "persist.sys.oplus.otg_support", 1);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "Otg auto close alarm is disable");
        }
        this.mObserver = new ContentObserver(new Handler()) { // from class: com.oplus.systemui.qs.helper.OtgHelper.4
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int psyOtgOnline = OtgHelper.this.getPsyOtgOnline();
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyOtgOnline, "useState:", LogUtil.TAG_NOTIFICATION, "OtgHelper");
                OtgHelper otgHelper = OtgHelper.this;
                if (psyOtgOnline != 1) {
                    otgHelper.setAutoCloseAlarm();
                } else {
                    otgHelper.cancelAutoCloseAlarm();
                }
            }
        };
        ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor("persist.sys.oplus.otg_support"), this.mObserver);
        if (!CustomizeFeatureOption.sIsDisableOtgAutoClose) {
            this.mReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.qs.helper.OtgHelper.2
                public final /* synthetic */ OtgHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context2, Intent intent) {
                    switch (i) {
                        case 0:
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "receive: " + intent);
                            int psyOtgOnline = this.this$0.getPsyOtgOnline();
                            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyOtgOnline, "otg state:", LogUtil.TAG_NOTIFICATION, "OtgHelper");
                            if (psyOtgOnline == 0 && !this.this$0.mMigrateUsbStarted) {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "close otg for time out");
                                this.this$0.setOtgEnable(false);
                                break;
                            }
                            break;
                        default:
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "MigrateUsbReceiver: " + intent);
                            if ("com.google.android.apps.pixelmigrate.USB_LISTENING_STARTED".equals(intent.getAction())) {
                                this.this$0.setOtgEnable(true);
                                this.this$0.mMigrateUsbStarted = true;
                            } else if ("com.google.android.apps.pixelmigrate.USB_LISTENING_FINISHED".equals(intent.getAction())) {
                                this.this$0.setOtgEnable(false);
                                this.this$0.mMigrateUsbStarted = false;
                            }
                            break;
                    }
                }
            };
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(this.mReceiver, new IntentFilter("com.oplus.intent.action.ACTION_CLOSE_OTG"), (Executor) null, (UserHandle) null, 2, ProviderUtil.OPLUS_COMPONENT_SAFE);
        }
        this.mMigrateUsbReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.qs.helper.OtgHelper.2
            public final /* synthetic */ OtgHelper this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "receive: " + intent);
                        int psyOtgOnline = this.this$0.getPsyOtgOnline();
                        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyOtgOnline, "otg state:", LogUtil.TAG_NOTIFICATION, "OtgHelper");
                        if (psyOtgOnline == 0 && !this.this$0.mMigrateUsbStarted) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "close otg for time out");
                            this.this$0.setOtgEnable(false);
                            break;
                        }
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "MigrateUsbReceiver: " + intent);
                        if ("com.google.android.apps.pixelmigrate.USB_LISTENING_STARTED".equals(intent.getAction())) {
                            this.this$0.setOtgEnable(true);
                            this.this$0.mMigrateUsbStarted = true;
                        } else if ("com.google.android.apps.pixelmigrate.USB_LISTENING_FINISHED".equals(intent.getAction())) {
                            this.this$0.setOtgEnable(false);
                            this.this$0.mMigrateUsbStarted = false;
                        }
                        break;
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.apps.pixelmigrate.USB_LISTENING_STARTED");
        intentFilter.addAction("com.google.android.apps.pixelmigrate.USB_LISTENING_FINISHED");
        ((BroadcastDispatcher) DependencyEx.sDependency.getDependency(BroadcastDispatcher.class)).registerReceiver(this.mMigrateUsbReceiver, intentFilter, (Executor) null, (UserHandle) null, 2, "android.permission.MANAGE_USB");
        if (Settings.Global.getInt(context.getContentResolver(), "persist.sys.oplus.otg_support", 0) == 1) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "restoreAlarm");
            setAutoCloseAlarm();
        }
    }

    private AlarmManager getAlarmManager() {
        return (AlarmManager) this.mContext.getSystemService("alarm");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOtgEnable(boolean z) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.sOtgAutoCloseAlarmDisable) {
            return;
        }
        if (this.mContext == null) {
            Log.d("OtgHelper", "context not init while set otg enable");
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "context not init while set otg enable");
            return;
        }
        this.mCharger.setPsyOtgSwitch(String.valueOf(z ? 1 : 0));
        Settings.Global.putInt(this.mContext.getContentResolver(), "persist.sys.oplus.otg_support", z ? 1 : 0);
        Log.d("OtgHelper", "set Otg Enable:" + z);
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "set Otg Enable:" + z);
    }

    public final void cancelAutoCloseAlarm() {
        if (this.mContext == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "context not init while cancelling alarm");
        } else {
            getAlarmManager().cancel(getPendingIntent());
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "cancel auto-close alarm!");
        }
    }

    public PendingIntent getPendingIntent() {
        if (this.mContext == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "context not init while cancelling alarm");
            return null;
        }
        Intent intent = new Intent("com.oplus.intent.action.ACTION_CLOSE_OTG");
        intent.setPackage(this.mContext.getPackageName());
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 335544320);
    }

    public int getPsyOtgOnline() {
        int psyOtgOnline = this.mCharger.getPsyOtgOnline();
        if (System.currentTimeMillis() - sLastLogTimeStamp > 60000) {
            sLastLogTimeStamp = System.currentTimeMillis();
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyOtgOnline, "getPsyOtgOnline = ", LogUtil.TAG_NOTIFICATION, "OtgHelper");
        }
        return psyOtgOnline;
    }

    public int getPsyOtgSwitch() {
        int psyOtgSwitch = this.mCharger.getPsyOtgSwitch();
        if (System.currentTimeMillis() - sLastLogTimeStamp > 60000) {
            sLastLogTimeStamp = System.currentTimeMillis();
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyOtgSwitch, "getPsyOtgSwitch = ", LogUtil.TAG_NOTIFICATION, "OtgHelper");
        }
        return psyOtgSwitch;
    }

    public final void setAutoCloseAlarm() {
        if (!this.mMigrateUsbStarted) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (!FlavorOneFeatureOption.sOtgAutoCloseAlarmDisable) {
                if (this.mContext == null) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "context not init while setting alarm");
                    return;
                }
                AlarmManager alarmManager = getAlarmManager();
                PendingIntent pendingIntent = getPendingIntent();
                alarmManager.cancel(pendingIntent);
                alarmManager.setExact(2, SystemClock.elapsedRealtime() + 600000, pendingIntent);
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "set auto-close alarm!");
                return;
            }
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "OtgHelper", "do not set auto-close alarm. Migrate usb in use");
    }
}
