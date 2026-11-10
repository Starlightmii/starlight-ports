package com.oplus.systemui.statusbar.phone.workfile;

import android.app.ActivityManager;
import android.app.INotificationManager;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.ColorStateList;
import android.database.ContentObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.phone.worklife.OPWLBHelper;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.coui.appcompat.cardview.COUICardView;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OPWLBHelperImpl extends OPWLBHelper {
    public static final String ACCEPT = "1";
    public static final String ACTION_REFRESH_MUTED_APPS = "com.oneplus.wlb.intent.ACTION_REFRESH_MUTED_APPS";
    private static final String ACTION_REFRESH_NOTIFICATIONS = "com.oneplus.wlb.intent.ACTION_RELOAD_NOTIFICATION";
    private static final String ACTION_WLB_FEATURE_DISABLE = "com.oneplus.intent.action_DISABLE_WLB_FEATURE";
    public static final Uri APPLICATIONS_CONTENT_URI;
    private static final String APP_STARTED_TIMESTAMP = "app_started_timestamp";
    private static final String AUTHORITY = "com.oplus.opwlb.DB.WLBContentProvider";
    private static final String COLUMN_DEACTIVATED_FOR = "active_for";
    private static final String COLUMN_PACKAGE_NAME = "package_name";
    public static final Companion Companion = new Companion();
    public static final String DISTRACTING_APP_NOTIFICATION = "distracting_life_on";
    private static final String EXTRA_ENABLE = "enable";
    private static final String EXTRA_FIRST_BOOT_TIME = "first_boot_time";
    private static final String EXTRA_IS_CONNECTED = "is_connected";
    private static final String EXTRA_PACKAGE_NAME = "package_name";
    public static final String FEATURE_WLB = "oplus.software.wlb";
    public static final String FRICTION_POPUP_ADD_TO_MODE = "friction_popup_add_to_mode";
    public static final String FRICTION_POPUP_GO_BACK = "friction_popup_go_back";
    public static final String FRICTION_POPUP_OPEN_ANYWAY = "friction_popup_open_anyway";
    public static final String FRICTION_POPUP_WLB = "friction_popup_wlb";
    public static final String FRICTION_POPUP_WLB_EV_001 = "friction_popup_wlb_ev_001";
    public static final String SETTINGS_FEATURE_DISABLE = "worklife_feature_enable";
    public static final String SETTINGS_GETAPPS = "get_recent_app_list";
    public static final String SETTINGS_MODE_ACTIVATED = "oplus_wlb_activated_mode";
    public static final String SETTINGS_MODE_ON_OFF = "oneplus_wlb_mode";
    public static final String SETTINGS_MODE_SETUP_DONE = "oneplus_wlb_setup_done";
    public static final String SETTINGS_WLB_TILE_ADDED = "sysui_wlb_tile_added";
    private static final String TABLE_APPLICATIONS = "wlb_application";
    private static final String TABLE_HIDDEN_NOTIFICATIONS = "hidden_notification";
    private static final String TAG = "OPSystemUIWLBHelper";
    public static final String WLB_APPID = "105904";
    public static final String WLB_PACKAGE_NAME = "com.oneplus.opwlb";
    private static Handler sBackHandler;
    private static OPWLBHelper sOpwlbHelper;
    private final Context context;
    public boolean isAdded;
    private final ContentObserver mAppTableObserver;
    private final BroadcastReceiver mBroadcastReceiver;
    private FrameLayout mContainer;
    private ContentObserver mDeviceProvisionedObserver;
    private final ContentObserver mGetRecentApps;
    private String mLastPackageName;
    public boolean mLastWifiConnected;
    private NotificationMediaManager mNotificationMediaManager;
    private HashMap<String, Integer> mPackageMutedMap;
    private final ContentObserver mSettingsObserver;
    private OPWLBHelper.IStatusBarIconChange mStatusBarIconChangeListener;
    private final ContentObserver mStatusBarIconChangeObserver;
    private final BroadcastReceiver mStatusBarIconChangeReceiver;
    private final TaskStackChangeListener mTaskListener;
    private IWLBModeChangeListener mWLBModeChangeListener;
    private WindowManager mWindowManager;
    private View testView;
    private final Set<String> mMediaNotificationKeys = new HashSet();
    private HashSet<Integer> mBlockedNotifications = new HashSet<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private INotificationManager mINotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    public int currentMode = getIntSystemSettings(SETTINGS_MODE_ACTIVATED, 0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OPWLBHelper getInstance(Context context) {
            if (OPWLBHelperImpl.sOpwlbHelper == null) {
                OPWLBHelperImpl.sOpwlbHelper = new OPWLBHelperImpl(context);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "Wlb cursor-->refreshMutedStarted object creation");
                }
            }
            return OPWLBHelperImpl.sOpwlbHelper;
        }
    }

    static {
        Uri uri = Uri.parse("content://com.oplus.opwlb.DB.WLBContentProvider");
        APPLICATIONS_CONTENT_URI = Uri.withAppendedPath(uri, TABLE_APPLICATIONS);
        Uri.withAppendedPath(uri, TABLE_HIDDEN_NOTIFICATIONS);
    }

    public OPWLBHelperImpl(Context context) {
        this.context = context;
        final int i = 0;
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.sWlbFeatureSupport) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "refreshMutedStarted ", null, 8);
            Companion.getClass();
            if (sBackHandler == null) {
                HandlerThread handlerThreadBuildNewThread = OplusThreadFactoryImpl.INSTANCE.buildNewThread("wlbbackhandler");
                sBackHandler = new Handler(handlerThreadBuildNewThread.getLooper());
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "Wlb cursor-->".concat("refreshMutedStarted object creation looper created" + handlerThreadBuildNewThread.getLooper()));
                }
            }
            refreshMutedPackages();
            try {
                ContentResolver contentResolver = context.getContentResolver();
                Uri uri = APPLICATIONS_CONTENT_URI;
                contentResolver.registerContentObserver(uri, false, this.mAppTableObserver);
                context.getContentResolver().registerContentObserver(Settings.System.getUriFor(SETTINGS_GETAPPS), false, this.mGetRecentApps);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("registered observer for:" + uri));
                }
            } catch (Exception e) {
                GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "e: ", e.getMessage(), LogUtil.TAG_COMMON, TAG);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->refreshMutedStarted BackHandled End");
            }
        }
        StatusBarFeatureOption.INSTANCE.getClass();
        if (!StatusBarFeatureOption.sWlbFeatureSupport) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "WLB feature not supported", null, 8);
        }
        final Handler handler = new Handler();
        final int i2 = 4;
        this.mStatusBarIconChangeObserver = new ContentObserver(this, handler) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mGetRecentApps$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        Handler handler2 = this.this$0.mHandler;
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.refreshMutedPackages.2.2 r0 = new OPWLBHelperImpl.refreshMutedPackages.2.2(1);
                        r0.this$0 = oPWLBHelperImpl;
                        VarHandle.storeStoreFence();
                        handler2.post(r0);
                        break;
                    case 1:
                    default:
                        super.onChange(z);
                        break;
                    case 2:
                        super.onChange(z);
                        boolean zAccess$isDeviceProvisionedInSettingsDb = OPWLBHelperImpl.access$isDeviceProvisionedInSettingsDb(this.this$0);
                        if (zAccess$isDeviceProvisionedInSettingsDb) {
                            OPWLBHelperImpl.access$persistAppStartMillis(this.this$0);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("DeviceProvisionedObserver onChange isProvisioned ", "OPSystemUIWLBHelper-->", "SystemUi--Common", zAccess$isDeviceProvisionedInSettingsDb);
                            break;
                        }
                        break;
                    case 3:
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                        oPWLBHelperImpl2.currentMode = oPWLBHelperImpl2.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ACTIVATED, 0);
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl3 = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oPWLBHelperImpl3.getCurrentMode(), "Current Mode changed to : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                    case 4:
                        int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                        if (this.this$0.mStatusBarIconChangeListener != null) {
                            Handler handler3 = this.this$0.mHandler;
                            OPWLBHelperImpl oPWLBHelperImpl4 = this.this$0;
                            OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1 r1 = new OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1();
                            r1.$wlbEnabled = intSystemSettings;
                            r1.this$0 = oPWLBHelperImpl4;
                            VarHandle.storeStoreFence();
                            handler3.post(r1);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(intSystemSettings, "wlb enabled changed to : ", " ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri2) {
                switch (i2) {
                    case 1:
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("onContent changed:" + uri2));
                        }
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                        oPWLBHelperImpl.refreshMutedPackages();
                        break;
                    default:
                        super.onChange(z, uri2);
                        break;
                }
            }
        };
        final int i3 = 1;
        this.mStatusBarIconChangeReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mBroadcastReceiver$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i3) {
                    case 0:
                        String action = intent.getAction();
                        if (OPWLBHelperImpl.ACTION_REFRESH_MUTED_APPS.equals(action)) {
                            OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                            OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                            oPWLBHelperImpl.refreshMutedPackages();
                        } else if ("com.oneplus.wlb.intent.ACTION_RELOAD_NOTIFICATION".equals(action)) {
                            OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                            OPWLBHelperImpl.Companion companion2 = OPWLBHelperImpl.Companion;
                            oPWLBHelperImpl2.refreshMutedPackages();
                        } else if ("com.oneplus.intent.action_DISABLE_WLB_FEATURE".equals(action)) {
                            int intExtra = intent.getIntExtra(PictorialAppInfo.TAG_ENABLE, 0);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(intExtra, "FeatureEnable : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            }
                            this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_FEATURE_DISABLE, intExtra);
                        }
                        break;
                    default:
                        if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                            UserInfo userInfo = ((UserManager) context2.getSystemService("user")).getUserInfo(intent.getIntExtra("android.intent.extra.user_handle", -1));
                            int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                Log.d("SystemUi--Common", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("onReceive: isAdmin user ", intSystemSettings, " wlb enabled:", "OPSystemUIWLBHelper-->", userInfo.isAdmin()));
                            }
                            if (this.this$0.mStatusBarIconChangeListener != null) {
                                if (userInfo.isAdmin() && intSystemSettings == 1) {
                                    OPWLBHelper.IStatusBarIconChange iStatusBarIconChange = this.this$0.mStatusBarIconChangeListener;
                                    if (iStatusBarIconChange != null) {
                                        ((OplusPhoneStatusBarPolicyExImpl) iStatusBarIconChange).onShowWLBStatusBarIcon();
                                    }
                                } else {
                                    OPWLBHelper.IStatusBarIconChange iStatusBarIconChange2 = this.this$0.mStatusBarIconChangeListener;
                                    if (iStatusBarIconChange2 != null) {
                                        ((OplusPhoneStatusBarPolicyExImpl) iStatusBarIconChange2).onHideWLBStatusBarIcon();
                                    }
                                }
                            }
                        }
                        break;
                }
            }
        };
        final Handler handler2 = new Handler();
        final int i4 = 3;
        this.mSettingsObserver = new ContentObserver(this, handler2) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mGetRecentApps$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i4) {
                    case 0:
                        Handler handler3 = this.this$0.mHandler;
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.refreshMutedPackages.2.2 r0 = new OPWLBHelperImpl.refreshMutedPackages.2.2(1);
                        r0.this$0 = oPWLBHelperImpl;
                        VarHandle.storeStoreFence();
                        handler3.post(r0);
                        break;
                    case 1:
                    default:
                        super.onChange(z);
                        break;
                    case 2:
                        super.onChange(z);
                        boolean zAccess$isDeviceProvisionedInSettingsDb = OPWLBHelperImpl.access$isDeviceProvisionedInSettingsDb(this.this$0);
                        if (zAccess$isDeviceProvisionedInSettingsDb) {
                            OPWLBHelperImpl.access$persistAppStartMillis(this.this$0);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("DeviceProvisionedObserver onChange isProvisioned ", "OPSystemUIWLBHelper-->", "SystemUi--Common", zAccess$isDeviceProvisionedInSettingsDb);
                            break;
                        }
                        break;
                    case 3:
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                        oPWLBHelperImpl2.currentMode = oPWLBHelperImpl2.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ACTIVATED, 0);
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl3 = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oPWLBHelperImpl3.getCurrentMode(), "Current Mode changed to : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                    case 4:
                        int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                        if (this.this$0.mStatusBarIconChangeListener != null) {
                            Handler handler4 = this.this$0.mHandler;
                            OPWLBHelperImpl oPWLBHelperImpl4 = this.this$0;
                            OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1 r1 = new OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1();
                            r1.$wlbEnabled = intSystemSettings;
                            r1.this$0 = oPWLBHelperImpl4;
                            VarHandle.storeStoreFence();
                            handler4.post(r1);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(intSystemSettings, "wlb enabled changed to : ", " ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri2) {
                switch (i4) {
                    case 1:
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("onContent changed:" + uri2));
                        }
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                        oPWLBHelperImpl.refreshMutedPackages();
                        break;
                    default:
                        super.onChange(z, uri2);
                        break;
                }
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mBroadcastReceiver$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        String action = intent.getAction();
                        if (OPWLBHelperImpl.ACTION_REFRESH_MUTED_APPS.equals(action)) {
                            OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                            OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                            oPWLBHelperImpl.refreshMutedPackages();
                        } else if ("com.oneplus.wlb.intent.ACTION_RELOAD_NOTIFICATION".equals(action)) {
                            OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                            OPWLBHelperImpl.Companion companion2 = OPWLBHelperImpl.Companion;
                            oPWLBHelperImpl2.refreshMutedPackages();
                        } else if ("com.oneplus.intent.action_DISABLE_WLB_FEATURE".equals(action)) {
                            int intExtra = intent.getIntExtra(PictorialAppInfo.TAG_ENABLE, 0);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(intExtra, "FeatureEnable : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            }
                            this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_FEATURE_DISABLE, intExtra);
                        }
                        break;
                    default:
                        if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                            UserInfo userInfo = ((UserManager) context2.getSystemService("user")).getUserInfo(intent.getIntExtra("android.intent.extra.user_handle", -1));
                            int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                Log.d("SystemUi--Common", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("onReceive: isAdmin user ", intSystemSettings, " wlb enabled:", "OPSystemUIWLBHelper-->", userInfo.isAdmin()));
                            }
                            if (this.this$0.mStatusBarIconChangeListener != null) {
                                if (userInfo.isAdmin() && intSystemSettings == 1) {
                                    OPWLBHelper.IStatusBarIconChange iStatusBarIconChange = this.this$0.mStatusBarIconChangeListener;
                                    if (iStatusBarIconChange != null) {
                                        ((OplusPhoneStatusBarPolicyExImpl) iStatusBarIconChange).onShowWLBStatusBarIcon();
                                    }
                                } else {
                                    OPWLBHelper.IStatusBarIconChange iStatusBarIconChange2 = this.this$0.mStatusBarIconChangeListener;
                                    if (iStatusBarIconChange2 != null) {
                                        ((OplusPhoneStatusBarPolicyExImpl) iStatusBarIconChange2).onHideWLBStatusBarIcon();
                                    }
                                }
                            }
                        }
                        break;
                }
            }
        };
        final Handler handler3 = new Handler();
        this.mAppTableObserver = new ContentObserver(this, handler3) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mGetRecentApps$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i3) {
                    case 0:
                        Handler handler4 = this.this$0.mHandler;
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.refreshMutedPackages.2.2 r0 = new OPWLBHelperImpl.refreshMutedPackages.2.2(1);
                        r0.this$0 = oPWLBHelperImpl;
                        VarHandle.storeStoreFence();
                        handler4.post(r0);
                        break;
                    case 1:
                    default:
                        super.onChange(z);
                        break;
                    case 2:
                        super.onChange(z);
                        boolean zAccess$isDeviceProvisionedInSettingsDb = OPWLBHelperImpl.access$isDeviceProvisionedInSettingsDb(this.this$0);
                        if (zAccess$isDeviceProvisionedInSettingsDb) {
                            OPWLBHelperImpl.access$persistAppStartMillis(this.this$0);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("DeviceProvisionedObserver onChange isProvisioned ", "OPSystemUIWLBHelper-->", "SystemUi--Common", zAccess$isDeviceProvisionedInSettingsDb);
                            break;
                        }
                        break;
                    case 3:
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                        oPWLBHelperImpl2.currentMode = oPWLBHelperImpl2.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ACTIVATED, 0);
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl3 = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oPWLBHelperImpl3.getCurrentMode(), "Current Mode changed to : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                    case 4:
                        int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                        if (this.this$0.mStatusBarIconChangeListener != null) {
                            Handler handler5 = this.this$0.mHandler;
                            OPWLBHelperImpl oPWLBHelperImpl4 = this.this$0;
                            OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1 r1 = new OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1();
                            r1.$wlbEnabled = intSystemSettings;
                            r1.this$0 = oPWLBHelperImpl4;
                            VarHandle.storeStoreFence();
                            handler5.post(r1);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(intSystemSettings, "wlb enabled changed to : ", " ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri2) {
                switch (i3) {
                    case 1:
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("onContent changed:" + uri2));
                        }
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                        oPWLBHelperImpl.refreshMutedPackages();
                        break;
                    default:
                        super.onChange(z, uri2);
                        break;
                }
            }
        };
        final Handler handler4 = this.mHandler;
        final int i5 = 2;
        this.mDeviceProvisionedObserver = new ContentObserver(this, handler4) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mGetRecentApps$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i5) {
                    case 0:
                        Handler handler5 = this.this$0.mHandler;
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.refreshMutedPackages.2.2 r0 = new OPWLBHelperImpl.refreshMutedPackages.2.2(1);
                        r0.this$0 = oPWLBHelperImpl;
                        VarHandle.storeStoreFence();
                        handler5.post(r0);
                        break;
                    case 1:
                    default:
                        super.onChange(z);
                        break;
                    case 2:
                        super.onChange(z);
                        boolean zAccess$isDeviceProvisionedInSettingsDb = OPWLBHelperImpl.access$isDeviceProvisionedInSettingsDb(this.this$0);
                        if (zAccess$isDeviceProvisionedInSettingsDb) {
                            OPWLBHelperImpl.access$persistAppStartMillis(this.this$0);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("DeviceProvisionedObserver onChange isProvisioned ", "OPSystemUIWLBHelper-->", "SystemUi--Common", zAccess$isDeviceProvisionedInSettingsDb);
                            break;
                        }
                        break;
                    case 3:
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                        oPWLBHelperImpl2.currentMode = oPWLBHelperImpl2.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ACTIVATED, 0);
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl3 = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oPWLBHelperImpl3.getCurrentMode(), "Current Mode changed to : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                    case 4:
                        int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                        if (this.this$0.mStatusBarIconChangeListener != null) {
                            Handler handler6 = this.this$0.mHandler;
                            OPWLBHelperImpl oPWLBHelperImpl4 = this.this$0;
                            OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1 r1 = new OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1();
                            r1.$wlbEnabled = intSystemSettings;
                            r1.this$0 = oPWLBHelperImpl4;
                            VarHandle.storeStoreFence();
                            handler6.post(r1);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(intSystemSettings, "wlb enabled changed to : ", " ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri2) {
                switch (i5) {
                    case 1:
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("onContent changed:" + uri2));
                        }
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                        oPWLBHelperImpl.refreshMutedPackages();
                        break;
                    default:
                        super.onChange(z, uri2);
                        break;
                }
            }
        };
        OPWLBHelperImpl$mTaskListener$1 oPWLBHelperImpl$mTaskListener$1 = new OPWLBHelperImpl$mTaskListener$1();
        oPWLBHelperImpl$mTaskListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mTaskListener = oPWLBHelperImpl$mTaskListener$1;
        final Handler handler5 = new Handler();
        this.mGetRecentApps = new ContentObserver(this, handler5) { // from class: com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl$mGetRecentApps$1
            public final /* synthetic */ OPWLBHelperImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i) {
                    case 0:
                        Handler handler6 = this.this$0.mHandler;
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.refreshMutedPackages.2.2 r0 = new OPWLBHelperImpl.refreshMutedPackages.2.2(1);
                        r0.this$0 = oPWLBHelperImpl;
                        VarHandle.storeStoreFence();
                        handler6.post(r0);
                        break;
                    case 1:
                    default:
                        super.onChange(z);
                        break;
                    case 2:
                        super.onChange(z);
                        boolean zAccess$isDeviceProvisionedInSettingsDb = OPWLBHelperImpl.access$isDeviceProvisionedInSettingsDb(this.this$0);
                        if (zAccess$isDeviceProvisionedInSettingsDb) {
                            OPWLBHelperImpl.access$persistAppStartMillis(this.this$0);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("DeviceProvisionedObserver onChange isProvisioned ", "OPSystemUIWLBHelper-->", "SystemUi--Common", zAccess$isDeviceProvisionedInSettingsDb);
                            break;
                        }
                        break;
                    case 3:
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl2 = this.this$0;
                        oPWLBHelperImpl2.currentMode = oPWLBHelperImpl2.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ACTIVATED, 0);
                        this.this$0.getCurrentMode();
                        OPWLBHelperImpl oPWLBHelperImpl3 = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oPWLBHelperImpl3.getCurrentMode(), "Current Mode changed to : ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                    case 4:
                        int intSystemSettings = this.this$0.getIntSystemSettings(OPWLBHelperImpl.SETTINGS_MODE_ON_OFF, 0);
                        if (this.this$0.mStatusBarIconChangeListener != null) {
                            Handler handler7 = this.this$0.mHandler;
                            OPWLBHelperImpl oPWLBHelperImpl4 = this.this$0;
                            OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1 r1 = new OPWLBHelperImpl.mStatusBarIconChangeObserver.1.onChange.1();
                            r1.$wlbEnabled = intSystemSettings;
                            r1.this$0 = oPWLBHelperImpl4;
                            VarHandle.storeStoreFence();
                            handler7.post(r1);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(intSystemSettings, "wlb enabled changed to : ", " ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
                            break;
                        }
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri2) {
                switch (i) {
                    case 1:
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("onContent changed:" + uri2));
                        }
                        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
                        OPWLBHelperImpl.Companion companion = OPWLBHelperImpl.Companion;
                        oPWLBHelperImpl.refreshMutedPackages();
                        break;
                    default:
                        super.onChange(z, uri2);
                        break;
                }
            }
        };
    }

    public static final void access$checkAndShowPopup(OPWLBHelperImpl oPWLBHelperImpl, int i) {
        ComponentName componentName;
        String packageName;
        String string;
        Drawable applicationIcon;
        int iIntValue;
        ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
        if (runningTask == null || (componentName = runningTask.baseActivity) == null || (packageName = componentName.getPackageName()) == null || packageName.equals(oPWLBHelperImpl.mLastPackageName)) {
            return;
        }
        try {
            PackageManager packageManager = oPWLBHelperImpl.context.getPackageManager();
            string = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)).toString();
            try {
                applicationIcon = packageManager.getApplicationIcon(packageName);
            } catch (PackageManager.NameNotFoundException e) {
                e = e;
                LogUtil.e$default(8, LogUtil.TAG_COMMON, "ERROR", CursorUtil$.ExternalSyntheticOutline0.m("Unable to find icon for package '", packageName, "': ", e.getMessage()));
                applicationIcon = null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
            string = null;
        }
        oPWLBHelperImpl.mLastPackageName = packageName;
        HashMap<String, Integer> map = oPWLBHelperImpl.mPackageMutedMap;
        if (map == null || map.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->mPackageMutedMap is null");
            }
            oPWLBHelperImpl.refreshMutedPackages();
        }
        HashMap<String, Integer> map2 = oPWLBHelperImpl.mPackageMutedMap;
        if (map2 != null) {
            iIntValue = map2.getOrDefault(packageName, 0).intValue();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("pkg name:" + packageName + " mode:" + iIntValue));
            }
        } else {
            iIntValue = -1;
        }
        if (oPWLBHelperImpl.currentMode != 2 ? !(iIntValue == 1 || iIntValue == 3) : !(iIntValue == 2 || iIntValue == 3)) {
            FrameLayout frameLayout = oPWLBHelperImpl.mContainer;
            if (frameLayout == null || !oPWLBHelperImpl.isAdded || oPWLBHelperImpl.mWindowManager == null) {
                return;
            }
            oPWLBHelperImpl.removeView(frameLayout);
            return;
        }
        oPWLBHelperImpl.mWindowManager = (WindowManager) oPWLBHelperImpl.context.getSystemService("window");
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(oPWLBHelperImpl.context, 2132019067);
        COUIThemeOverlay.getInstance().applyThemeOverlays(contextThemeWrapper);
        View viewInflate = LayoutInflater.from(contextThemeWrapper).inflate(2131559249, (ViewGroup) null);
        oPWLBHelperImpl.testView = viewInflate;
        COUICardView cOUICardView = (COUICardView) (viewInflate != null ? viewInflate.findViewById(2131362486) : null);
        ViewGroup.LayoutParams layoutParams = cOUICardView.getLayoutParams();
        layoutParams.width = (int) ((oPWLBHelperImpl.context.getResources().getDimensionPixelOffset(2131171250) * 3) / oPWLBHelperImpl.context.getResources().getDisplayMetrics().density);
        cOUICardView.setLayoutParams(layoutParams);
        int iRgb = Color.rgb(229, 229, 229);
        if (OplusDarkModeUtil.isNightMode(oPWLBHelperImpl.context)) {
            iRgb = Color.rgb(56, 56, 56);
        }
        cOUICardView.setCardBackgroundColor(new ColorStateList(new int[][]{new int[0]}, new int[]{iRgb}));
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(-1, -1, 2038, 2, -3);
        layoutParams2.dimAmount = 0.7f;
        layoutParams2.gravity = 17;
        View view = oPWLBHelperImpl.testView;
        TextView textView = view != null ? (TextView) view.findViewById(2131365646) : null;
        View view2 = oPWLBHelperImpl.testView;
        ImageView imageView = view2 != null ? (ImageView) view2.findViewById(2131362118) : null;
        View view3 = oPWLBHelperImpl.testView;
        TextView textView2 = view3 != null ? (TextView) view3.findViewById(2131363334) : null;
        int i2 = oPWLBHelperImpl.currentMode;
        Context context = oPWLBHelperImpl.context;
        String str = i2 == 2 ? String.format(context.getString(2131955311), Arrays.copyOf(new Object[]{oPWLBHelperImpl.context.getString(2131955310)}, 1)) : String.format(context.getString(2131955311), Arrays.copyOf(new Object[]{oPWLBHelperImpl.context.getString(2131955312)}, 1));
        if (textView2 != null) {
            textView2.setText(str);
        }
        if (textView != null) {
            textView.setText(String.format(oPWLBHelperImpl.context.getString(2131955307), Arrays.copyOf(new Object[]{string}, 1)));
        }
        if (applicationIcon != null && imageView != null) {
            imageView.setImageDrawable(applicationIcon);
        }
        View view4 = oPWLBHelperImpl.testView;
        TextView textView3 = view4 != null ? (TextView) view4.findViewById(2131364260) : null;
        View view5 = oPWLBHelperImpl.testView;
        TextView textView4 = view5 != null ? (TextView) view5.findViewById(2131361991) : null;
        View view6 = oPWLBHelperImpl.testView;
        TextView textView5 = view6 != null ? (TextView) view6.findViewById(2131363245) : null;
        int i3 = oPWLBHelperImpl.currentMode;
        Context context2 = oPWLBHelperImpl.context;
        String str2 = i3 == 2 ? String.format(context2.getString(2131955306), Arrays.copyOf(new Object[]{oPWLBHelperImpl.context.getString(2131955310)}, 1)) : String.format(context2.getString(2131955306), Arrays.copyOf(new Object[]{oPWLBHelperImpl.context.getString(2131955312)}, 1));
        if (textView4 != null) {
            textView4.setText(str2);
        }
        setOnClickListener.1 r9 = new setOnClickListener.1(contextThemeWrapper, oPWLBHelperImpl);
        oPWLBHelperImpl.mContainer = r9;
        r9.addView(oPWLBHelperImpl.testView, new FrameLayout.LayoutParams(-1, -1));
        oPWLBHelperImpl.isAdded = true;
        WindowManager windowManager = oPWLBHelperImpl.mWindowManager;
        if (windowManager != null) {
            windowManager.addView(oPWLBHelperImpl.mContainer, layoutParams2);
        }
        if (textView3 != null) {
            setOnClickListener.2 r2 = new setOnClickListener.2();
            r2.this$0 = oPWLBHelperImpl;
            VarHandle.storeStoreFence();
            textView3.setOnClickListener(r2);
        }
        if (textView4 != null) {
            setOnClickListener.3 r0 = new setOnClickListener.3();
            r0.this$0 = oPWLBHelperImpl;
            r0.$mode = iIntValue;
            r0.$packageName = packageName;
            VarHandle.storeStoreFence();
            textView4.setOnClickListener(r0);
        }
        if (textView5 != null) {
            setOnClickListener.4 r1 = new setOnClickListener.4();
            r1.this$0 = oPWLBHelperImpl;
            r1.$taskId = i;
            VarHandle.storeStoreFence();
            textView5.setOnClickListener(r1);
        }
    }

    public static final boolean access$isAdminUser(OPWLBHelperImpl oPWLBHelperImpl) {
        long serialNumberForUser = ((UserManager) oPWLBHelperImpl.context.getSystemService("user")).getSerialNumberForUser(Process.myUserHandle());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(serialNumberForUser, "uId:", "OPSystemUIWLBHelper-->", "SystemUi--Common");
        }
        return serialNumberForUser == 0;
    }

    public static final boolean access$isDeviceProvisionedInSettingsDb(OPWLBHelperImpl oPWLBHelperImpl) {
        return Settings.Global.getInt(oPWLBHelperImpl.context.getContentResolver(), "device_provisioned", 0) != 0;
    }

    public static final boolean access$isPopUpEnabled(OPWLBHelperImpl oPWLBHelperImpl) {
        oPWLBHelperImpl.currentMode = oPWLBHelperImpl.getIntSystemSettings(SETTINGS_MODE_ACTIVATED, 0);
        int intSystemSettings = oPWLBHelperImpl.getIntSystemSettings(DISTRACTING_APP_NOTIFICATION, 0);
        int i = oPWLBHelperImpl.currentMode;
        return i != 1 ? i == 2 && (intSystemSettings == 2 || intSystemSettings == 3) : intSystemSettings == 1 || intSystemSettings == 3;
    }

    public static final void access$persistAppStartMillis(OPWLBHelperImpl oPWLBHelperImpl) {
        if (oPWLBHelperImpl.getAppStartedTimestamp() != 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(System.currentTimeMillis(), "persistAppStartMillis ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
        }
        Settings.System.putLong(oPWLBHelperImpl.context.getContentResolver(), APP_STARTED_TIMESTAMP, System.currentTimeMillis());
    }

    private final long getAppStartedTimestamp() {
        return Settings.System.getIntForUser(this.context.getContentResolver(), APP_STARTED_TIMESTAMP, 0, ActivityManager.getCurrentUser());
    }

    /* JADX WARN: Code duplicated, block: B:24:0x007a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:25:0x007c  */
    public final void checkAndIncludeWLBTile() {
        int i = Settings.Secure.getInt(this.context.getContentResolver(), SETTINGS_WLB_TILE_ADDED, -1);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, " SETTINGS_WLB_TILE_ADDED ", "OPSystemUIWLBHelper-->", "SystemUi--Common");
        }
        if (i == -1) {
            return;
        }
        boolean z = i == 2;
        String stringForUser = Settings.Secure.getStringForUser(this.context.getContentResolver(), "sysui_qs_tiles", -2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("qs_tile list:", stringForUser, " \n isWLBTileAdded:", z, "OPSystemUIWLBHelper-->"));
        }
        if (!TextUtils.isEmpty(stringForUser)) {
            stringForUser.getClass();
            if (!StringsKt__StringsKt.contains(stringForUser, "custom(com.oneplus.opwlb/com.oneplus.opwlb.services.WLBTileService)", false) && !StringsKt__StringsKt.contains(stringForUser, "custom(com.oneplus.opwlb/.services.WLBTileService)", false)) {
                if (!z) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                        Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->adding WLB tile fresh:");
                    }
                    Intent intent = new Intent("com.oplus.systemui.qs.hide_tile");
                    intent.putExtra("tile", "custom(com.oneplus.opwlb/.services.WLBTileWorkService)");
                    this.context.sendBroadcast(intent);
                    Intent intent2 = new Intent("com.oplus.systemui.qs.hide_tile");
                    intent2.putExtra("tile", "custom(com.oneplus.opwlb/.services.WLBTileLifeService)");
                    this.context.sendBroadcast(intent2);
                    Settings.Secure.putInt(this.context.getContentResolver(), SETTINGS_WLB_TILE_ADDED, 2);
                    return;
                }
            }
        } else if (!z) {
            if (LogUtil.getNormalDebug()) {
                Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->adding WLB tile fresh:");
            }
            Intent intent3 = new Intent("com.oplus.systemui.qs.hide_tile");
            intent3.putExtra("tile", "custom(com.oneplus.opwlb/.services.WLBTileWorkService)");
            this.context.sendBroadcast(intent3);
            Intent intent4 = new Intent("com.oplus.systemui.qs.hide_tile");
            intent4.putExtra("tile", "custom(com.oneplus.opwlb/.services.WLBTileLifeService)");
            this.context.sendBroadcast(intent4);
            Settings.Secure.putInt(this.context.getContentResolver(), SETTINGS_WLB_TILE_ADDED, 2);
            return;
        }
        if (TextUtils.isEmpty(stringForUser)) {
            return;
        }
        stringForUser.getClass();
        if (StringsKt__StringsKt.contains(stringForUser, "custom(com.oneplus.opwlb/com.oneplus.opwlb.services.WLBTileService)", false) || StringsKt__StringsKt.contains(stringForUser, "custom(com.oneplus.opwlb/.services.WLBTileService)", false)) {
            Intent intent5 = new Intent("com.oplus.systemui.qs.hide_tile");
            intent5.putExtra("tile", "custom(com.oneplus.opwlb/.services.WLBTileWorkService)");
            this.context.sendBroadcast(intent5);
            Intent intent6 = new Intent("com.oplus.systemui.qs.hide_tile");
            intent6.putExtra("tile", "custom(com.oneplus.opwlb/.services.WLBTileLifeService)");
            this.context.sendBroadcast(intent6);
            boolean zPutString = Settings.Secure.putString(this.context.getContentResolver(), "sysui_qs_tiles", stringForUser);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("replacing tilelist:", stringForUser, " \n status: ", zPutString, "OPSystemUIWLBHelper-->"));
            }
            Settings.Secure.putInt(this.context.getContentResolver(), SETTINGS_WLB_TILE_ADDED, 2);
        }
    }

    public final int getCurrentMode() {
        return this.currentMode;
    }

    public final int getIntSystemSettings(String str, int i) {
        return AbsSettingsValueProxy.Companion.getSystemIntValue(this.context, str, i, OsBinderCacheUtils.getCurrentUserId());
    }

    public final ContentObserver getMDeviceProvisionedObserver() {
        return this.mDeviceProvisionedObserver;
    }

    public final List<UsageStats> getUsageStatistics() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(7, -1);
        return ((UsageStatsManager) this.context.getSystemService("usagestats")).queryUsageStats(0, calendar.getTimeInMillis(), System.currentTimeMillis());
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0042  */
    public boolean isApplicationBlocked(StatusBarNotification statusBarNotification, NotificationChannel notificationChannel) {
        boolean zContains;
        if (!OplusFeatureConfigManager.getInstance().hasFeature(FEATURE_WLB)) {
            return false;
        }
        String packageName = statusBarNotification.getPackageName();
        if (statusBarNotification.getPackageName() != null) {
            String key = statusBarNotification.getKey();
            NotificationMediaManager notificationMediaManager = (NotificationMediaManager) Dependency.sDependency.getDependencyInner(NotificationMediaManager.class);
            this.mNotificationMediaManager = notificationMediaManager;
            if (notificationMediaManager != null) {
                String mediaNotificationKey = notificationMediaManager.getMediaNotificationKey();
                if (mediaNotificationKey != null && mediaNotificationKey.equals(key)) {
                    this.mMediaNotificationKeys.add(key);
                }
                zContains = this.mMediaNotificationKeys.contains(key);
            } else {
                zContains = false;
            }
        } else {
            zContains = false;
        }
        if (TextUtils.isEmpty(packageName) || this.currentMode == 0 || zContains) {
            if (this.currentMode != 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OPSystemUIWLBHelper-->", "SystemUi--Common", ConstraintSet$.ExternalSyntheticOutline0.m(this.currentMode, "not blocking notifications: preconditions: packageName:", packageName, " current mode:", " isMediaAllowed:"), zContains);
            }
            return false;
        }
        if (notificationChannel != null) {
            if (this.mINotificationManager != null) {
                String packageName2 = statusBarNotification.getPackageName();
                int uid = statusBarNotification.getUid();
                String group = notificationChannel.getGroup();
                if (group != null) {
                    try {
                        INotificationManager iNotificationManager = this.mINotificationManager;
                        NotificationChannelGroup notificationChannelGroupForPackage = iNotificationManager != null ? iNotificationManager.getNotificationChannelGroupForPackage(group, packageName2, uid) : null;
                        if (notificationChannelGroupForPackage != null && !TextUtils.isEmpty(notificationChannelGroupForPackage.getName())) {
                            int intSystemSettings = getIntSystemSettings(notificationChannelGroupForPackage.getName().toString(), 0);
                            int i = this.currentMode;
                            if ((intSystemSettings & i) == i) {
                                HashSet<Integer> hashSet = this.mBlockedNotifications;
                                if (hashSet != null) {
                                    hashSet.add(Integer.valueOf(statusBarNotification.getId()));
                                }
                                return true;
                            }
                        }
                    } catch (RemoteException e) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("unable to find the notification channel:", e.getMessage(), "OPSystemUIWLBHelper-->", "SystemUi--Common");
                        }
                    }
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->notification manager is null");
            }
        }
        HashMap<String, Integer> map = this.mPackageMutedMap;
        if (map == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->mPackageMutedMap is null");
            }
            return false;
        }
        int iIntValue = map.getOrDefault(packageName, 0).intValue();
        int i2 = this.currentMode;
        if ((iIntValue & i2) != i2) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "OPSystemUIWLBHelper-->".concat("pkg blocked:" + packageName + " " + iIntValue));
        }
        HashSet<Integer> hashSet2 = this.mBlockedNotifications;
        if (hashSet2 != null) {
            hashSet2.add(Integer.valueOf(statusBarNotification.getId()));
        }
        return true;
    }

    public final void processWifiConnectivity(boolean z) {
        StatusBarFeatureOption.INSTANCE.getClass();
        if (!StatusBarFeatureOption.sWlbFeatureSupport || this.mLastWifiConnected == z) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("wificonnected ", "OPSystemUIWLBHelper-->", "SystemUi--Common", z);
        }
        try {
            Intent intent = new Intent("com.oneplus.intent.ACTION_WIFI_CONNECTED");
            intent.setPackage(WLB_PACKAGE_NAME);
            intent.putExtra(EXTRA_IS_CONNECTED, z);
            this.context.sendBroadcast(intent);
        } catch (Exception e) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("error while sending broadcast:", e.getMessage(), "OPSystemUIWLBHelper-->", "SystemUi--Common");
            }
        }
        this.mLastWifiConnected = z;
    }

    public final void refreshMutedPackages() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "Wlb cursor-->refreshMutedStarted  :: 1");
        }
        Handler handler = sBackHandler;
        if (handler != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "Wlb cursor-->".concat("refreshMutedStarted  :: " + handler));
            }
            refreshMutedPackages.2.2 r1 = new refreshMutedPackages.2.2(0);
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(r1);
        }
    }

    public final void registerChanges(IWLBModeChangeListener iWLBModeChangeListener) {
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(SETTINGS_MODE_ACTIVATED), false, this.mSettingsObserver);
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor("device_provisioned"), false, this.mDeviceProvisionedObserver);
        IntentFilter intentFilter = new IntentFilter(ACTION_REFRESH_NOTIFICATIONS);
        intentFilter.addAction(ACTION_WLB_FEATURE_DISABLE);
        this.context.registerReceiver(this.mBroadcastReceiver, intentFilter, 2);
        this.context.registerReceiver(this.mBroadcastReceiver, new IntentFilter(ACTION_REFRESH_MUTED_APPS), 2);
    }

    public void registerStatusBarObserver(OPWLBHelper.IStatusBarIconChange iStatusBarIconChange) {
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(SETTINGS_MODE_ON_OFF), false, this.mStatusBarIconChangeObserver);
        this.context.registerReceiver(this.mStatusBarIconChangeReceiver, new IntentFilter("android.intent.action.USER_SWITCHED"), 2);
        this.mStatusBarIconChangeListener = iStatusBarIconChange;
        TaskStackChangeListeners.getInstance().registerTaskStackListener(this.mTaskListener);
    }

    public final void removeNotificationKey(String str) {
        if (this.mMediaNotificationKeys.isEmpty()) {
            return;
        }
        this.mMediaNotificationKeys.remove(str);
    }

    public final void removeView(FrameLayout frameLayout) {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || !this.isAdded) {
            return;
        }
        windowManager.removeView(frameLayout);
        this.isAdded = false;
        this.mLastPackageName = null;
    }

    public final void setMDeviceProvisionedObserver(ContentObserver contentObserver) {
        this.mDeviceProvisionedObserver = contentObserver;
    }

    public final void setNotificationMediaManager(NotificationMediaManager notificationMediaManager) {
        if (notificationMediaManager != null) {
            this.mNotificationMediaManager = notificationMediaManager;
        }
    }
}
