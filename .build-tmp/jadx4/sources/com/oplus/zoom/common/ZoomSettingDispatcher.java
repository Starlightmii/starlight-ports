package com.oplus.zoom.common;

import android.app.ActivityManager;
import android.app.UserSwitchObserver;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.content.PackageMonitor;
import com.oplus.systemui.volume.observer.VolumeSettingsValueProxy;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomSettingDispatcher {
    public static final String CHILDREN_MODE = "children_mode_on";
    private static final String OPLUS_COMPONENT_SAFE = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final String SUPER_POWER_MODE = "super_powersave_launcher_enter";
    public static final String SYSTEM_FOLDING_ANGLE = "system_folding_angle_for_oplus";
    public static final String SYSTEM_FOLDING_STATE = "oplus_system_device_state";
    private static final String TAG = "ZoomSettingObserver";
    public static final String ZEN_MODE_STATUS = "op_breath_mode_status";
    public static final String ZOOM_ALPHA_ADJUST = "alpha_adjust";
    public static final String ZOOM_EXCHANGE_APPLICATION = "exchange_application";
    public static final String ZOOM_GAME_REDIRECT_BEHAVIOR = "flexible_freeform_light_open";
    public static final String ZOOM_GAME_SHRINK_SETTINGS = "setting_zoomwindow_game_shrink";
    public static final String ZOOM_SHARE_BEHAVIOR = "setting_zoomwindow_open_from_share";
    public static final String ZOOM_SIMPLE_MODE = "setting_zoom_simple_mode";
    private static volatile ZoomSettingDispatcher sInstance;
    private Context mContext;
    private Handler mHandler;
    private SettingsObserver mSettingsObserver;
    private final ArrayList<CallbackListener> mCallbackListeners = new ArrayList<>();
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.zoom.common.ZoomSettingDispatcher.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                return;
            }
            LogD.d("ZoomSettingObserver", "onBroadcastReceive, intent = " + intent);
            ZoomSettingDispatcher.this.notifyBroadCastReceive(context, intent);
        }
    };
    private final BroadcastReceiver mUserBroadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.zoom.common.ZoomSettingDispatcher.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogD.d("ZoomSettingObserver", "mUserBroadcastReceiver, intent = " + intent);
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                return;
            }
            ZoomSettingDispatcher.this.notifyBroadCastReceive(context, intent);
        }
    };
    private final PackageMonitor mPackageMonitor = new PackageMonitor() { // from class: com.oplus.zoom.common.ZoomSettingDispatcher.3
        public void onPackageRemoved(String str, int i) {
            super.onPackageRemoved(str, i);
            LogD.d("ZoomSettingObserver", "onPackageRemoved, pkg = " + str);
            ZoomSettingDispatcher.this.notifyPackageRemove(str, i);
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CallbackListener {
        default void onBroadCastReceive(Context context, Intent intent) {
        }

        default void onPackageRemove(String str, int i) {
        }

        default void onSettingChanged(boolean z, Uri uri, int i) {
        }

        default void onUserSwitched(int i) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri, int i) {
            super.onChange(z, uri, i);
            LogD.d("ZoomSettingObserver", "SettingsObserver onChange, uri = " + uri);
            if (uri != null) {
                ZoomSettingDispatcher.this.notifySettingChange(z, uri, i);
            }
        }
    }

    public static ZoomSettingDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (ZoomSettingDispatcher.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ZoomSettingDispatcher();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private void registerBroadcastObserver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("oplus.intent.action.MULTI_APP_PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter, "oplus.permission.OPLUS_COMPONENT_SAFE", null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.USER_REMOVED");
        this.mContext.registerReceiver(this.mUserBroadcastReceiver, intentFilter2);
    }

    private void registerPackageMonitor() {
        this.mPackageMonitor.register(this.mContext, this.mHandler.getLooper(), UserHandle.ALL, true);
    }

    private void registerSettingObserver() {
        try {
            Context context = this.mContext;
            if (context == null || context.getContentResolver() == null) {
                return;
            }
            Uri uriFor = Settings.System.getUriFor(ZOOM_SIMPLE_MODE);
            Uri uriFor2 = Settings.System.getUriFor(ZOOM_GAME_SHRINK_SETTINGS);
            Uri uriFor3 = Settings.System.getUriFor(ZOOM_SHARE_BEHAVIOR);
            Uri uriFor4 = Settings.System.getUriFor(ZOOM_GAME_REDIRECT_BEHAVIOR);
            Uri uriFor5 = Settings.System.getUriFor(ZOOM_EXCHANGE_APPLICATION);
            Uri uriFor6 = Settings.System.getUriFor(ZOOM_ALPHA_ADJUST);
            Uri uriFor7 = Settings.Global.getUriFor(VolumeSettingsValueProxy.SETTINGS_ANIMATION_SCALE);
            Uri uriFor8 = Settings.Global.getUriFor("children_mode_on");
            Uri uriFor9 = Settings.System.getUriFor("super_powersave_launcher_enter");
            Uri uriFor10 = Settings.Secure.getUriFor("op_breath_mode_status");
            Uri uriFor11 = Settings.Global.getUriFor(SYSTEM_FOLDING_ANGLE);
            Uri uriFor12 = Settings.Global.getUriFor(SYSTEM_FOLDING_STATE);
            ContentResolver contentResolver = this.mContext.getContentResolver();
            contentResolver.registerContentObserver(uriFor, false, this.mSettingsObserver, -1);
            contentResolver.registerContentObserver(uriFor2, false, this.mSettingsObserver, -1);
            contentResolver.registerContentObserver(uriFor3, false, this.mSettingsObserver, -1);
            contentResolver.registerContentObserver(uriFor4, false, this.mSettingsObserver, -1);
            contentResolver.registerContentObserver(uriFor5, false, this.mSettingsObserver, -1);
            contentResolver.registerContentObserver(uriFor6, false, this.mSettingsObserver, -1);
            contentResolver.registerContentObserver(uriFor7, false, this.mSettingsObserver);
            contentResolver.registerContentObserver(uriFor8, false, this.mSettingsObserver);
            contentResolver.registerContentObserver(uriFor9, false, this.mSettingsObserver, 0);
            contentResolver.registerContentObserver(uriFor10, false, this.mSettingsObserver);
            if (ZoomUtil.isFoldDevice()) {
                contentResolver.registerContentObserver(uriFor11, false, this.mSettingsObserver);
                contentResolver.registerContentObserver(uriFor12, false, this.mSettingsObserver);
            }
        } catch (Exception e) {
            LogD.e("ZoomSettingObserver", "registerSettingObserver failed e: " + e);
        }
    }

    private void registerUserSwitchObserver() {
        try {
            ActivityManager.getService().registerUserSwitchObserver(new ZoomUserSwitchObserver(this, 0), "ZoomSettingObserver");
        } catch (RemoteException e) {
            LogD.e("ZoomSettingObserver", "registerUserSwitchObserver error, e: " + e);
        }
    }

    public void addCallbackListener(CallbackListener callbackListener) {
        if (callbackListener != null) {
            this.mCallbackListeners.add(callbackListener);
        }
    }

    public void init(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        this.mSettingsObserver = new SettingsObserver(this.mHandler);
        LogD.i("ZoomSettingObserver", "registerBroadcastObserver ");
        registerBroadcastObserver();
        LogD.i("ZoomSettingObserver", "registerUserSwitchObserver ");
        registerUserSwitchObserver();
        LogD.i("ZoomSettingObserver", "registerSettingObserver ");
        registerSettingObserver();
        LogD.i("ZoomSettingObserver", "registerPackageMonitor ");
        registerPackageMonitor();
    }

    public void notifyBroadCastReceive(Context context, Intent intent) {
        ArrayList<CallbackListener> arrayList = this.mCallbackListeners;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            CallbackListener callbackListener = arrayList.get(i);
            i++;
            callbackListener.onBroadCastReceive(context, intent);
        }
    }

    public void notifyPackageRemove(String str, int i) {
        ArrayList<CallbackListener> arrayList = this.mCallbackListeners;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            CallbackListener callbackListener = arrayList.get(i2);
            i2++;
            callbackListener.onPackageRemove(str, i);
        }
    }

    public void notifySettingChange(boolean z, Uri uri, int i) {
        ArrayList<CallbackListener> arrayList = this.mCallbackListeners;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            CallbackListener callbackListener = arrayList.get(i2);
            i2++;
            callbackListener.onSettingChanged(z, uri, i);
        }
    }

    public void notifyUserSwitch(int i) {
        ArrayList<CallbackListener> arrayList = this.mCallbackListeners;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            CallbackListener callbackListener = arrayList.get(i2);
            i2++;
            callbackListener.onUserSwitched(i);
        }
    }

    public void removeCallbackListener(CallbackListener callbackListener) {
        if (callbackListener != null) {
            this.mCallbackListeners.remove(callbackListener);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class ZoomUserSwitchObserver extends UserSwitchObserver {
        private ZoomUserSwitchObserver() {
        }

        public void onUserSwitchComplete(int i) {
            super.onUserSwitchComplete(i);
            LogD.d("ZoomSettingObserver", "onUserSwitchComplete, userId = " + i);
            ZoomSettingDispatcher.this.notifyUserSwitch(i);
        }

        public /* synthetic */ ZoomUserSwitchObserver(ZoomSettingDispatcher zoomSettingDispatcher, int i) {
            this();
        }
    }
}
