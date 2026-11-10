package com.oplus.systemui.statusbar.notification.power;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.util.NotificationChannels;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.helper.LowBatteryWarningDelayHelper;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.receiver.CustomPowerReceiver;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import com.oplus.view.OplusWindowAttributesManager;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.observer.HighTempSafetyObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.statusbar.StatusBarWindowStateControllerForSubDisplay;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPowerNotificationWarnings {
    public static Context sContext;
    public static volatile OplusPowerNotificationWarnings sInstance;
    public int mBatteryLowTitleId;
    public Notification.Action[] mDlgActions;
    public String mDlgNotificationContent;
    public PendingIntent mDlgNotificationContentIntnet;
    public String mDlgNotificationTitle;
    public final OplusPowerNotificationWarnings$$ExternalSyntheticLambda1 mHighTempSafetyListener;
    public AlertDialog mInvalidChargerDialog;
    public boolean mIsSavePowerMode;
    public LowBatteryDialog mLowBatteryDialog;
    public LowBatteryDialog mLowBatteryDialogForSubdisplay;
    public LowBatteryWarningDelayHelper mLowBatteryWarningDelayHelper;
    public final OplusPowerNotificationWarningsForSubDisplay mOplusPowerNotificationWarningsForSubDisplay;
    public int mChargeErrorDialogType = -1;
    public int mOldChargeVolStatus = 3;
    public boolean mOldChargeTimeout = false;
    public boolean mOldBatteryPlugged = false;
    public boolean mBatteryPlugged = false;
    public NotificationManager mNotificationManager = null;
    public AlertDialog mChargeErrorDialog = null;
    public int mOldBatteryVolStatus = 3;
    public int mEvent = 0;
    public int mPlugged = 0;
    public int mBatteryStatus = 1;
    public int mLastBatteryStatus = 1;
    public int mBatteryVolStatus = -1;
    public int mBatteryLevel = 0;
    public boolean mShowChargeTimeoutDialog = false;
    public boolean mLastBatteryConnect = false;
    public boolean mBatteryConnect = false;
    public boolean mBatteryLowTemperature = false;
    public boolean mLastBatteryLowTemperature = false;
    public boolean mBatteryConnectShow = false;
    public boolean mBatteryLowTmpShow = false;
    public boolean mBatteryOverTmpShow = false;
    public boolean mBatteryTimeout = false;
    public boolean mLastBatteryTimeout = false;
    public boolean mBatteryFull = false;
    public boolean mBatteryOverVol = false;
    public boolean mLastBatteryOverVol = false;
    public boolean mLastBatteryFull = false;
    public boolean mChargeOverVol = false;
    public boolean mLastChargeOverVol = false;
    public boolean mBatteryOverTemperature = false;
    public boolean mLastBatteryOverTemperature = false;
    public boolean mBatteryTimeoutShow = false;
    public boolean mBatteryFullShow = false;
    public boolean mBatteryOverCVShow = false;
    public boolean mShowChargerVoltageDialog = false;
    public boolean mShowBatteryVoltageDialog = false;
    public boolean mChargeTimeout = false;
    public final PowerManager mPowerManager = (PowerManager) sContext.getSystemService("power");

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerNotificationWarnings$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements DialogInterface.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;
        public /* synthetic */ Context val$context;

        public /* synthetic */ AnonymousClass4(int i) {
            this.$r8$classId = i;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            switch (this.$r8$classId) {
                case 0:
                    ((OplusPowerNotificationWarnings) this.this$0).turnOnPowerSaveMode();
                    PowerUIStatistics.collectPowerSaveClickData(18, 1, this.val$context);
                    LowBatteryUiController.getInstance().dismissBatteryWarningOnSecondDisplay();
                    break;
                default:
                    try {
                        LowBatteryUiController.getInstance().dismissAll();
                        this.val$context.startActivityAsUser((Intent) this.this$0, UserHandle.CURRENT);
                    } catch (Exception e) {
                        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createPlugChargerDialog: Exception " + e);
                    }
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createPlugChargerDialog: startActivity power consumption");
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerNotificationWarnings$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements DialogInterface.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Context val$context;

        public /* synthetic */ AnonymousClass5(int i) {
            this.$r8$classId = i;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            switch (this.$r8$classId) {
                case 0:
                    PowerUIStatistics.collectPowerSaveClickData(18, 2, this.val$context);
                    LowBatteryUiController.getInstance().dismissBatteryWarningOnSecondDisplay();
                    break;
                case 1:
                    PowerUIStatistics.collectPowerSaveClickData(17, 2, this.val$context);
                    break;
                default:
                    Settings.System.putIntForUser(this.val$context.getContentResolver(), "super_powersave_launcher_enter", 1, 0);
                    Context context = this.val$context;
                    HashMap map = new HashMap();
                    map.put("user_action", String.valueOf(true));
                    OplusTrackProxyUtil.onCommon(context, "20082", "super_save_power_mode_action", map, 2);
                    PowerUIStatistics.collectPowerSaveClickData(17, 1, OplusPowerNotificationWarnings.sContext);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerNotificationWarnings$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements DialogInterface.OnDismissListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object val$context;

        public /* synthetic */ AnonymousClass6(int i) {
            this.$r8$classId = i;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            switch (this.$r8$classId) {
                case 0:
                    PowerUIStatistics.collectPowerSaveClickData(18, 3, (Context) this.val$context);
                    break;
                case 1:
                    PowerUIStatistics.collectPowerSaveClickData(17, 3, (Context) this.val$context);
                    break;
                default:
                    ((OplusPowerNotificationWarnings) this.val$context).mInvalidChargerDialog = null;
                    break;
            }
        }
    }

    public OplusPowerNotificationWarnings() {
        OplusPowerNotificationWarningsForSubDisplay oplusPowerNotificationWarningsForSubDisplay = null;
        this.mIsSavePowerMode = Settings.Global.getInt(sContext.getContentResolver(), CommonSettingsValueProxy.KEY_POWER_SAVING_MODE, 0) == 1;
        COUIThemeOverlay.getInstance().applyThemeOverlays(new ContextThemeWrapper(sContext, 2132019084));
        OplusPowerNotificationWarnings$$ExternalSyntheticLambda1 oplusPowerNotificationWarnings$$ExternalSyntheticLambda1 = new OplusPowerNotificationWarnings$$ExternalSyntheticLambda1();
        oplusPowerNotificationWarnings$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        HighTempSafetyObserver.getInstance().addListener(oplusPowerNotificationWarnings$$ExternalSyntheticLambda1);
        Context context = sContext;
        synchronized (OplusPowerNotificationWarningsForSubDisplay.class) {
            try {
                if (OplusSubDisplayUtils.Companion.getInstance(context).getSupportSubDisplay()) {
                    if (OplusPowerNotificationWarningsForSubDisplay.mInstance == null) {
                        OplusPowerNotificationWarningsForSubDisplay.mInstance = new OplusPowerNotificationWarningsForSubDisplay(context);
                    }
                    oplusPowerNotificationWarningsForSubDisplay = OplusPowerNotificationWarningsForSubDisplay.mInstance;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mOplusPowerNotificationWarningsForSubDisplay = oplusPowerNotificationWarningsForSubDisplay;
    }

    public static OplusPowerNotificationWarnings getInstance(Context context) {
        sContext = context;
        if (sInstance == null) {
            synchronized (OplusPowerNotificationWarnings.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new OplusPowerNotificationWarnings();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (sInstance != null) {
            OplusPowerNotificationWarnings oplusPowerNotificationWarnings = sInstance;
            Context context2 = sContext;
            if (oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper == null) {
                oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper = LowBatteryWarningDelayHelper.Companion.getInstance(context2);
            }
        }
        return sInstance;
    }

    public static boolean isCurrentFullscreen() {
        if (!CustomizeFeatureOption.sIsSupportMiniApp || !OplusSubDisplayUtils.Companion.getInstance(sContext).getSupportSubDisplay() || !DeviceStateController.getInstance().isFolding()) {
            return AppInfoUtil.getTopIsFullscreen();
        }
        int statusBarState = StatusBarWindowStateControllerForSubDisplay.Companion.getInstance(sContext).getStatusBarState();
        return statusBarState == 1 || statusBarState == 2;
    }

    public final void createNotification(String str, String str2, PendingIntent pendingIntent, Notification.Action[] actionArr, Context context, boolean z) {
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        }
        if (SystemProperties.getBoolean(sContext.getString(2131955572), false) || HighTempSafetyObserver.getInstance().mHighTempSafetyState) {
            return;
        }
        Context context2 = sContext;
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isSupportMiniCapsule() && Settings.System.getIntForUser(context2.getContentResolver(), "mini_capsule_switch", 1, -2) == 1 && Settings.System.getInt(context2.getContentResolver(), "mini_capsule_battery_switch", 1) == 1) {
            return;
        }
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createNotification:" + str);
        Bundle bundle = new Bundle();
        bundle.putBoolean("force_show", true);
        bundle.putBoolean("force_simple_banner", z);
        Notification.Builder builderAddExtras = new Notification.Builder(context, "ALR").setSmallIcon(2131232662).setContentIntent(pendingIntent).setContentTitle(str).setContentText(str2).setShowWhen(false).setAutoCancel(true).addExtras(bundle);
        if (actionArr != null) {
            for (Notification.Action action : actionArr) {
                builderAddExtras.addAction(action);
            }
        } else {
            Intent intent = new Intent("oplus.intent.action.CANCEL_NOTIFICAITON");
            intent.setPackage(sContext.getPackageName());
            builderAddExtras.addAction(new Notification.Action.Builder((Icon) null, context.getString(2131952526), PendingIntent.getBroadcast(context, 0, intent, 335544320)).build());
        }
        NotificationManager notificationManager = this.mNotificationManager;
        UserHandle userHandle = UserHandle.ALL;
        notificationManager.cancelAsUser(null, 10007, userHandle);
        this.mNotificationManager.notifyAsUser(null, 10007, builderAddExtras.build(), userHandle);
    }

    public final LowBatteryDialog createPlugChargerDialog(int i, Context context) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "createPlugChargerDialog: percent= ", LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification");
        this.mBatteryLowTitleId = 2131954292;
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(context), 2132017612);
        cOUIAlertDialogBuilder.setTitle(context.getString(2131954292, Integer.valueOf(i)));
        cOUIAlertDialogBuilder.setPositiveButton(2131952526, new 2(2));
        Intent intentMakeRestartActivityTask = Intent.makeRestartActivityTask(new ComponentName("com.oplus.battery", "com.oplus.powermanager.fuelgaue.PowerConsumptionActivity"));
        intentMakeRestartActivityTask.removeCategory("android.intent.category.LAUNCHER");
        intentMakeRestartActivityTask.addFlags(335544320);
        intentMakeRestartActivityTask.putExtra("source", "Dialog");
        AnonymousClass4 anonymousClass4 = new AnonymousClass4(1);
        anonymousClass4.val$context = context;
        anonymousClass4.this$0 = intentMakeRestartActivityTask;
        VarHandle.storeStoreFence();
        cOUIAlertDialogBuilder.setNegativeButton(2131952532, anonymousClass4);
        cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
        alertDialogCreate.setCancelable(false);
        this.mDlgNotificationTitle = context.getString(2131954299, Integer.valueOf(i));
        this.mDlgNotificationContent = context.getString(2131954293);
        this.mDlgNotificationContentIntnet = PendingIntent.getActivity(context, 0, intentMakeRestartActivityTask, 335544320);
        this.mDlgActions = null;
        return new LowBatteryDialog(alertDialogCreate, cOUIAlertDialogBuilder);
    }

    public final void dismissChargeErrorWarring(int i) {
        int i2;
        if (this.mChargeErrorDialog != null && ((i2 = this.mChargeErrorDialogType) == i || -1 == i || i2 == -1)) {
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mChargeErrorDialogType, LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "dismissChargeErrorWarring type:", " mChargeErrorDialogType:"));
            this.mChargeErrorDialog.dismiss();
            this.mChargeErrorDialog = null;
        }
        try {
            this.mNotificationManager.cancelAsUser(null, 10006, UserHandle.ALL);
        } catch (Exception unused) {
        }
        this.mChargeErrorDialogType = -1;
    }

    public final void dismissLowBatteryWarning() {
        LowBatteryDialog lowBatteryDialog = this.mLowBatteryDialog;
        if (lowBatteryDialog != null) {
            lowBatteryDialog.dismiss();
            this.mLowBatteryDialog = null;
        }
        LowBatteryUiController.getInstance().dismissBatteryWarningOnSecondDisplay();
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            notificationManager.cancelAsUser(null, 10007, UserHandle.ALL);
        }
        LowBatteryWarningDelayHelper lowBatteryWarningDelayHelper = this.mLowBatteryWarningDelayHelper;
        if (lowBatteryWarningDelayHelper == null || !lowBatteryWarningDelayHelper.getHoldLowBatteryWarning()) {
            return;
        }
        this.mLowBatteryWarningDelayHelper.setNeedShowDialog(false);
    }

    public void setBatteryLowDialogPowerLevel(int i) {
        OplusPowerNotificationWarningsForSubDisplay oplusPowerNotificationWarningsForSubDisplay = this.mOplusPowerNotificationWarningsForSubDisplay;
        if (oplusPowerNotificationWarningsForSubDisplay != null) {
            oplusPowerNotificationWarningsForSubDisplay.mBatteryLevel = i;
        }
        this.mBatteryLevel = i;
        LowBatteryDialog lowBatteryDialog = this.mLowBatteryDialog;
        if (lowBatteryDialog != null && lowBatteryDialog.isShowing()) {
            this.mLowBatteryDialog.getDialog().setTitle(sContext.getString(this.mBatteryLowTitleId, Integer.valueOf(i)));
        }
        LowBatteryDialog lowBatteryDialog2 = this.mLowBatteryDialogForSubdisplay;
        if (lowBatteryDialog2 == null || !lowBatteryDialog2.isShowing()) {
            return;
        }
        this.mLowBatteryDialogForSubdisplay.getDialog().setTitle(sContext.getString(this.mBatteryLowTitleId, Integer.valueOf(i)));
    }

    public void setBatteryStatus(int i) {
        this.mLastBatteryStatus = this.mBatteryStatus;
        this.mBatteryStatus = i;
    }

    public final void showChargeErrorDialog(int i) {
        int i2;
        int i3;
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "showChargeErrorDialog type = ", LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification");
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isChargingErrorNoAlert() || SystemProperties.getBoolean("sys.batterylow.hidetips", false) || SystemProperties.getBoolean(sContext.getString(2131955572), false)) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "showChargeErrorDialog isChargingErrorNoAlert  return ");
            return;
        }
        int i4 = 2131952260;
        switch (i) {
            case 1:
                i2 = 2131952602;
                break;
            case 2:
                i2 = 2131952261;
                break;
            case 3:
                i3 = 2131952253;
                i2 = i3;
                i4 = 2131952251;
                break;
            case 4:
                i3 = 2131952252;
                i2 = i3;
                i4 = 2131952251;
                break;
            case 5:
                i4 = 2131952223;
                i2 = 2131952224;
                break;
            case 6:
                i4 = 2131955078;
                i2 = 2131955077;
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                i2 = 2131955079;
                i4 = 2131955082;
                break;
            case 8:
                i2 = 2131955080;
                i4 = 2131955082;
                break;
            case 9:
                i2 = 2131955081;
                i4 = 2131955082;
                break;
            case 10:
                i4 = 2131954799;
                i2 = 2131954798;
                break;
            default:
                i4 = 0;
                i2 = 0;
                break;
        }
        this.mChargeErrorDialogType = i;
        if (i2 == 0) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "showChargeErrorDialogOrNotification content is null  return ");
            return;
        }
        if (i == 1 || i == 3 || i == 5 || i == 7) {
            if (this.mNotificationManager == null) {
                this.mNotificationManager = (NotificationManager) sContext.getSystemService("notification");
            }
            if (SystemProperties.getBoolean(sContext.getString(2131955572), false)) {
                return;
            }
            Notification.Builder autoCancel = new Notification.Builder(sContext, NotificationChannels.BATTERY).setSmallIcon(2131232662).setContentTitle(sContext.getString(i4)).setContentText(sContext.getString(i2)).setShowWhen(true).setVisibility(1).setCategory("sys").setAutoCancel(true);
            if (i == 7) {
                Intent intentMakeRestartActivityTask = Intent.makeRestartActivityTask(new ComponentName("com.oplus.battery", "com.oplus.powermanager.fuelgaue.PowerConsumptionActivity"));
                intentMakeRestartActivityTask.removeCategory("android.intent.category.LAUNCHER");
                intentMakeRestartActivityTask.addFlags(335544320);
                intentMakeRestartActivityTask.putExtra("source", "Dialog");
                autoCancel.setContentIntent(PendingIntent.getActivity(sContext, 0, intentMakeRestartActivityTask, 335544320));
            }
            this.mNotificationManager.notifyAsUser(null, 10006, autoCancel.build(), UserHandle.ALL);
            return;
        }
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(sContext, 2132017612);
        cOUIAlertDialogBuilder.setTitle(i2).setPositiveButton(2131952526, new 2(0));
        cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
        dismissChargeErrorWarring(-1);
        this.mChargeErrorDialog = alertDialogCreate;
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) sContext.getSystemService("power")).newWakeLock(805306394, "OplusPowerUI : dialog");
        wakeLockNewWakeLock.setReferenceCounted(false);
        wakeLockNewWakeLock.acquire();
        this.mChargeErrorDialog.setCancelable(false);
        Window window = this.mChargeErrorDialog.getWindow();
        window.getAttributes();
        OplusWindowAttributesManager.setIgnoreHomeMenuKeyState(window, 1);
        this.mChargeErrorDialog.show();
        cOUIAlertDialogBuilder.updateViewAfterShown();
        wakeLockNewWakeLock.release();
    }

    public final void showInvalidChargerDialog(Context context) {
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "showing invalid charger dialog");
        if (SystemProperties.getBoolean(context.getString(2131955572), false)) {
            return;
        }
        dismissLowBatteryWarning();
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(context, 2132017612);
        cOUIAlertDialogBuilder.setCancelable(false);
        cOUIAlertDialogBuilder.setTitle(2131953620);
        cOUIAlertDialogBuilder.setIconAttribute(R.attr.alertDialogIcon);
        cOUIAlertDialogBuilder.setPositiveButton(2131952526, (DialogInterface.OnClickListener) null);
        cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
        AnonymousClass6 anonymousClass6 = new AnonymousClass6(2);
        anonymousClass6.val$context = this;
        VarHandle.storeStoreFence();
        alertDialogCreate.setOnDismissListener(anonymousClass6);
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
        OplusWindowAttributesManager.setIgnoreHomeMenuKeyState(alertDialogCreate.getWindow(), 1);
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isChargingErrorNoAlert()) {
            alertDialogCreate.show();
            cOUIAlertDialogBuilder.updateViewAfterShown();
        }
        this.mInvalidChargerDialog = alertDialogCreate;
    }

    public final void showLowBatteryDialog() {
        if (this.mLowBatteryDialog == null || HighTempSafetyObserver.getInstance().mHighTempSafetyState) {
            return;
        }
        this.mLowBatteryDialog.show();
        LowBatteryUiController lowBatteryUiController = LowBatteryUiController.getInstance();
        LowBatteryDialog lowBatteryDialog = lowBatteryUiController.mLowBatteryDialogOnSecondDisplay;
        if (lowBatteryDialog != null && !lowBatteryDialog.isShowing()) {
            lowBatteryUiController.mLowBatteryDialogOnSecondDisplay.show();
        }
        TextView textView = (TextView) this.mLowBatteryDialog.getDialog().findViewById(2131362005);
        if (textView != null) {
            textView.setMaxLines(3);
        }
        TextView textView2 = (TextView) this.mLowBatteryDialog.getDialog().findViewById(R.id.message);
        if (textView2 != null) {
            textView2.setGravity(17);
        }
    }

    /* JADX WARN: Code duplicated, block: B:75:0x032a  */
    /* JADX WARN: Code duplicated, block: B:77:0x0335  */
    /* JADX WARN: Code duplicated, block: B:79:0x0340  */
    /* JADX WARN: Code duplicated, block: B:81:0x034b  */
    /* JADX WARN: Code duplicated, block: B:85:0x035c A[LOOP:0: B:83:0x0354->B:85:0x035c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:88:0x0383  */
    /* JADX WARN: Code duplicated, block: B:89:0x038d  */
    /* JADX WARN: Instruction removed from duplicated block: B:85:0x035c, please report this as an issue */
    public final boolean showLowBatteryWarning(int i, boolean z, int i2, boolean z2, OplusPowerUI.BatteryHandler batteryHandler) {
        LowBatteryDialog lowBatteryDialogCreatePlugChargerDialog;
        boolean z3;
        boolean z4;
        int i3;
        boolean z5;
        int i4 = i2;
        String str = LogUtil.TAG_COMMON;
        String str2 = "Oplus_PowerUI.Notification";
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "showLowBatteryWarning*************");
        dismissLowBatteryWarning();
        Context context = sContext;
        if (i == 2) {
            if (z || FeatureOption.isGameMlbbSupport()) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createBatteryLowDialog: warningLevel is 2, and plug is in. do nothing.");
                lowBatteryDialogCreatePlugChargerDialog = null;
            } else {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createCritcalLowDialog");
                this.mBatteryLowTitleId = 2131952916;
                COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(context), 2132017612);
                cOUIAlertDialogBuilder.setTitle(context.getString(2131952916, Integer.valueOf(i4)));
                cOUIAlertDialogBuilder.setPositiveButton(2131952526, new 2(1));
                cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
                AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
                alertDialogCreate.getWindow().setType(2003);
                alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
                alertDialogCreate.setCancelable(false);
                this.mDlgNotificationTitle = null;
                this.mDlgNotificationContent = null;
                this.mDlgNotificationContentIntnet = null;
                this.mDlgActions = null;
                lowBatteryDialogCreatePlugChargerDialog = new LowBatteryDialog(alertDialogCreate, cOUIAlertDialogBuilder);
            }
        } else if (KeyguardUpdateMonitor.getCurrentUser() != 0 || (!(i == 10 || i == 5) || NotificationFeatureOption.INSTANCE.isSuperPowerModeDisabled(context))) {
            if (i != 20 && i != 10 && i != 5) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createBatteryLowDialog: warningLevel(" + i + ") is ivalid");
            } else if (FeatureOption.INSTANCE.isDisablePowerSave(context)) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createBatteryLowDialog: FEATURE_DISABLE_POWER_SAVE is true.");
                if (z) {
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createBatteryLowDialog: save power is on, and plug is in. do nothing.");
                } else if (FluidCloudStateObserver.Companion.getInstance().mCurrentFluidCloudState != 0) {
                    lowBatteryDialogCreatePlugChargerDialog = createPlugChargerDialog(i4, context);
                } else {
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createPlugChargerDialog: savePower on, unplugged and isDisablePowerSave, dialog replaced by seed");
                }
            } else if (this.mIsSavePowerMode || (AutoSaveModeSwitchObserver.Companion.getInstance().isAutoSaveModeOn && AutoSaveModeLevelObserver.Companion.getInstance().getAutoSaveModeLevel() == i4)) {
                if (z) {
                    i4 = i2;
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createBatteryLowDialog: save power is on, and plug is in. do nothing.");
                } else if (FluidCloudStateObserver.Companion.getInstance().mCurrentFluidCloudState != 0) {
                    i4 = i2;
                    lowBatteryDialogCreatePlugChargerDialog = createPlugChargerDialog(i4, context);
                } else {
                    i4 = i2;
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createPlugChargerDialog: savePower on, unplugged, dialog replaced by seed");
                }
            } else if (FluidCloudStateObserver.Companion.getInstance().mCurrentFluidCloudState != 0) {
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i4, "createSavePowerDialog: percent= ", LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification");
                this.mBatteryLowTitleId = 2131954295;
                COUIAlertDialogBuilder cOUIAlertDialogBuilder2 = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(context), 2132017613);
                cOUIAlertDialogBuilder2.setTitle(context.getString(2131954295, Integer.valueOf(i4)));
                if (FeatureOption.isCmccCustomizedVersion()) {
                    cOUIAlertDialogBuilder2.setMessage(2131954296);
                } else {
                    View viewInflate = LayoutInflater.from(context).inflate(2131559318, (ViewGroup) null);
                    TextView textView = (TextView) viewInflate.findViewById(2131365512);
                    textView.setMovementMethod(ScrollingMovementMethod.getInstance());
                    TextView textView2 = (TextView) viewInflate.findViewById(2131365839);
                    String[] stringArray = context.getResources().getStringArray(2130903261);
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    boolean z6 = Settings.System.getIntForUser(context.getContentResolver(), "power_save_backlight_switch_state", 1, 0) == 1;
                    boolean z7 = Settings.System.getIntForUser(context.getContentResolver(), "power_save_screenoff_time_state", 1, 0) == 1;
                    boolean z8 = Settings.System.getIntForUser(context.getContentResolver(), "power_save_sync_state", 1, 0) == 1;
                    if (FeatureOption.isDynamicFPSSupport()) {
                        z3 = z8;
                        boolean z9 = Settings.System.getIntForUser(context.getContentResolver(), "power_save_screen_refresh_rate", 1, 0) == 1;
                        z4 = z9;
                        if (Settings.System.getIntForUser(context.getContentResolver(), "oplus_support_fiveg_status", 0, -2) == 1 && Settings.Global.getInt(context.getContentResolver(), "power_save_disable_five_g_state", 1) == 1) {
                            arrayList.add(6);
                        }
                        if (z6) {
                            arrayList.add(0);
                        }
                        if (z7) {
                            arrayList.add(1);
                        }
                        if (z3) {
                            arrayList.add(2);
                        }
                        if (z4) {
                            arrayList.add(4);
                        }
                        for (i3 = 0; i3 < arrayList.size(); i3++) {
                            sb.append(stringArray[((Integer) arrayList.get(i3)).intValue()] + "\n");
                        }
                        if (arrayList.size() > 0) {
                            sb.append("\n");
                            textView2.setText(2131955478);
                        } else {
                            textView2.setText(2131955479);
                        }
                        sb.append(context.getResources().getString(2131955481));
                        textView.setText(sb.toString());
                        cOUIAlertDialogBuilder2.setView(viewInflate);
                    } else {
                        z3 = z8;
                    }
                    z4 = z9;
                    if (Settings.System.getIntForUser(context.getContentResolver(), "oplus_support_fiveg_status", 0, -2) == 1) {
                        arrayList.add(6);
                    }
                    if (z6) {
                        arrayList.add(0);
                    }
                    if (z7) {
                        arrayList.add(1);
                    }
                    if (z3) {
                        arrayList.add(2);
                    }
                    if (z4) {
                        arrayList.add(4);
                    }
                    while (i3 < arrayList.size()) {
                        sb.append(stringArray[((Integer) arrayList.get(i3)).intValue()] + "\n");
                    }
                    if (arrayList.size() > 0) {
                        sb.append("\n");
                        textView2.setText(2131955478);
                    } else {
                        textView2.setText(2131955479);
                    }
                    sb.append(context.getResources().getString(2131955481));
                    textView.setText(sb.toString());
                    cOUIAlertDialogBuilder2.setView(viewInflate);
                }
                Intent intent = new Intent(CustomPowerReceiver.ACTION_TURN_ON_SAVE_POWER_MODE);
                int i5 = 0;
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(i5);
                anonymousClass4.this$0 = this;
                anonymousClass4.val$context = context;
                VarHandle.storeStoreFence();
                cOUIAlertDialogBuilder2.setPositiveButton(2131956544, anonymousClass4);
                AnonymousClass5 anonymousClass5 = new AnonymousClass5(i5);
                anonymousClass5.val$context = context;
                VarHandle.storeStoreFence();
                cOUIAlertDialogBuilder2.setNegativeButton(2131952524, anonymousClass5);
                AnonymousClass6 anonymousClass6 = new AnonymousClass6(i5);
                anonymousClass6.val$context = context;
                VarHandle.storeStoreFence();
                cOUIAlertDialogBuilder2.setOnDismissListener(anonymousClass6);
                cOUIAlertDialogBuilder2.setBlurBackgroundDrawable(true);
                AlertDialog alertDialogCreate2 = cOUIAlertDialogBuilder2.create();
                alertDialogCreate2.getWindow().setType(2003);
                alertDialogCreate2.getWindow().getAttributes().privateFlags |= 16;
                alertDialogCreate2.setCancelable(false);
                Intent intentMakeRestartActivityTask = Intent.makeRestartActivityTask(new ComponentName("com.oplus.battery", "com.oplus.powermanager.fuelgaue.PowerConsumptionActivity"));
                intentMakeRestartActivityTask.removeCategory("android.intent.category.LAUNCHER");
                intentMakeRestartActivityTask.addFlags(335544320);
                intentMakeRestartActivityTask.putExtra("source", "Dialog");
                this.mDlgNotificationTitle = context.getString(2131954299, Integer.valueOf(i2));
                this.mDlgNotificationContent = context.getString(2131954294);
                this.mDlgNotificationContentIntnet = PendingIntent.getActivity(context, 0, intentMakeRestartActivityTask, 335544320);
                this.mDlgActions = new Notification.Action[]{new Notification.Action.Builder((Icon) null, context.getString(2131956543), PendingIntent.getBroadcast(context, 0, intent, 335544320)).build()};
                lowBatteryDialogCreatePlugChargerDialog = new LowBatteryDialog(alertDialogCreate2, cOUIAlertDialogBuilder2);
                i4 = i2;
                str = LogUtil.TAG_COMMON;
                str2 = "Oplus_PowerUI.Notification";
            } else {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createSavePowerDialog: the current dialog has been replaced by seed");
                i4 = i2;
            }
            lowBatteryDialogCreatePlugChargerDialog = null;
        } else if (SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState) {
            if (z) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createBatteryLowDialog: save power is on, and plug is in. do nothing.");
            } else if (FluidCloudStateObserver.Companion.getInstance().mCurrentFluidCloudState != 0) {
                lowBatteryDialogCreatePlugChargerDialog = createPlugChargerDialog(i4, context);
            } else {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createPlugChargerDialog: save power is on, dialog has been replaced by seed");
            }
            lowBatteryDialogCreatePlugChargerDialog = null;
        } else if (FluidCloudStateObserver.Companion.getInstance().mCurrentFluidCloudState != 0) {
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i4, "createSuperSavePowerDialog  percent= ", LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification");
            this.mBatteryLowTitleId = 2131954297;
            COUIAlertDialogBuilder cOUIAlertDialogBuilder3 = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(context), 2132017613);
            cOUIAlertDialogBuilder3.setTitle(context.getString(2131954297, Integer.valueOf(i4)));
            View viewInflate2 = LayoutInflater.from(context).inflate(2131559413, (ViewGroup) null);
            ((TextView) viewInflate2.findViewById(2131365512)).setMovementMethod(ScrollingMovementMethod.getInstance());
            cOUIAlertDialogBuilder3.setView(viewInflate2);
            AnonymousClass5 anonymousClass7 = new AnonymousClass5(2);
            anonymousClass7.val$context = context;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder3.setPositiveButton(2131956543, anonymousClass7);
            OplusPowerNotificationWarnings$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusPowerNotificationWarnings$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = context;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder3.setNegativeButton(2131952524, externalSyntheticLambda0);
            int i6 = 1;
            AnonymousClass5 anonymousClass8 = new AnonymousClass5(i6);
            anonymousClass8.val$context = context;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder3.setNegativeButton(2131952524, anonymousClass8);
            AnonymousClass6 anonymousClass9 = new AnonymousClass6(i6);
            anonymousClass9.val$context = context;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder3.setOnDismissListener(anonymousClass9);
            cOUIAlertDialogBuilder3.setBlurBackgroundDrawable(true);
            AlertDialog alertDialogCreate3 = cOUIAlertDialogBuilder3.create();
            alertDialogCreate3.getWindow().setType(2003);
            alertDialogCreate3.getWindow().getAttributes().privateFlags |= 16;
            alertDialogCreate3.setCancelable(false);
            Intent intent2 = new Intent(CustomPowerReceiver.SUPER_POWER_SAVE_FUNC);
            Intent intentMakeRestartActivityTask2 = Intent.makeRestartActivityTask(new ComponentName("com.oplus.battery", "com.oplus.powermanager.fuelgaue.PowerConsumptionActivity"));
            intentMakeRestartActivityTask2.removeCategory("android.intent.category.LAUNCHER");
            intentMakeRestartActivityTask2.addFlags(335544320);
            intentMakeRestartActivityTask2.putExtra("source", "Dialog");
            this.mDlgNotificationTitle = context.getString(2131954299, Integer.valueOf(i4));
            this.mDlgNotificationContent = context.getString(2131954298);
            this.mDlgNotificationContentIntnet = PendingIntent.getActivity(context, 0, intentMakeRestartActivityTask2, 335544320);
            this.mDlgActions = new Notification.Action[]{new Notification.Action.Builder((Icon) null, context.getString(2131956543), PendingIntent.getBroadcast(context, 0, intent2, 335544320)).build()};
            lowBatteryDialogCreatePlugChargerDialog = new LowBatteryDialog(alertDialogCreate3, cOUIAlertDialogBuilder3);
        } else {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "createSuperSavePowerDialog: the current dialog has been replaced by seed");
            lowBatteryDialogCreatePlugChargerDialog = null;
        }
        this.mLowBatteryDialog = lowBatteryDialogCreatePlugChargerDialog;
        LogUtil.d(str, str2, "showLowBatteryWarning::createBatteryLowDialog = " + this.mLowBatteryDialog);
        if (this.mLowBatteryDialog != null) {
            LowBatteryUiController.getInstance().mLowBatteryDialogOnMainDisplay = this.mLowBatteryDialog;
        } else {
            LogUtil.d(str, str2, "showLowBatteryWarning::mLowBatteryDialog = null");
        }
        OplusPowerNotificationWarningsForSubDisplay oplusPowerNotificationWarningsForSubDisplay = this.mOplusPowerNotificationWarningsForSubDisplay;
        if (oplusPowerNotificationWarningsForSubDisplay != null) {
            LowBatteryDialog lowBatteryDialogCreateBatteryLowDialogForSubDisplay = oplusPowerNotificationWarningsForSubDisplay.createBatteryLowDialogForSubDisplay(i, i4, z);
            this.mLowBatteryDialogForSubdisplay = lowBatteryDialogCreateBatteryLowDialogForSubDisplay;
            if (lowBatteryDialogCreateBatteryLowDialogForSubDisplay != null) {
                LowBatteryUiController.getInstance().mLowBatteryDialogOnSecondDisplay = this.mLowBatteryDialogForSubdisplay;
            }
        }
        if (SystemProperties.getBoolean(sContext.getString(2131955572), false)) {
            LogUtil.d(str, str2, "isAgingProperties:true");
            return false;
        }
        FeatureOption featureOption = FeatureOption.INSTANCE;
        featureOption.getClass();
        boolean z10 = FeatureOption.isChargingErrorNoAlert() || FeatureOption.mIsDisableLowBatteryDialog || featureOption.isSuperPowerModeDisabled(sContext) || featureOption.isDisablePowerSave(sContext) || HighTempSafetyObserver.getInstance().mHighTempSafetyState;
        LowBatteryDialog lowBatteryDialog = this.mLowBatteryDialog;
        if (lowBatteryDialog == null || z10) {
            this.mLowBatteryDialog = null;
            this.mLowBatteryDialogForSubdisplay = null;
            if (LogUtil.getNormalDebug()) {
                StringBuilder sb2 = new StringBuilder("FeatureOption.isChargingErrorNoAlert(): ");
                sb2.append(FeatureOption.isChargingErrorNoAlert());
                sb2.append(",FeatureOption.isLowBatteryDialogDisabled():");
                sb2.append(FeatureOption.mIsDisableLowBatteryDialog);
                sb2.append(",FeatureOption.isSuperPowerModeDisabled(sContext):");
                sb2.append(featureOption.isSuperPowerModeDisabled(sContext));
                sb2.append(",FeatureOption.isDisablePowerSave(sContext):");
                sb2.append(featureOption.isDisablePowerSave(sContext));
                sb2.append(",HighTemperatureSafety:");
                ExpandHelper$.ExternalSyntheticOutline0.m(str, str2, sb2, HighTempSafetyObserver.getInstance().mHighTempSafetyState);
                return false;
            }
        } else {
            Window window = lowBatteryDialog.getWindow();
            window.getAttributes();
            OplusWindowAttributesManager.setIgnoreHomeMenuKeyState(window, 1);
            if (!this.mLowBatteryWarningDelayHelper.getHoldLowBatteryWarning() || !this.mLowBatteryWarningDelayHelper.getBeforeUnlock()) {
                if (this.mLowBatteryWarningDelayHelper.getHoldLowBatteryWarning()) {
                    LogUtil.d(str, str2, "showLowBatteryWarning::getHoldLowBatteryWarning");
                    this.mLowBatteryWarningDelayHelper.setNeedShowDialog(true);
                    this.mLowBatteryWarningDelayHelper.setWarningBeforeUnlock(false);
                    return false;
                }
                if ((!isCurrentFullscreen() && !NotificationUtil.isCurrentAppForcingFullScreenBanner(sContext)) || this.mDlgNotificationTitle == null) {
                    FlavorOneFeatureOption.INSTANCE.getClass();
                    if (!FlavorOneFeatureOption.isFlavorOneDevice() || this.mDlgNotificationTitle == null) {
                        if (z2) {
                            LogUtil.d(str, str2, "showLowBatteryWarning::mIsLowBatteryFistBoot");
                            batteryHandler.removeMessages(11);
                            batteryHandler.sendEmptyMessageDelayed(11, 8000L);
                        } else {
                            LogUtil.d(str, str2, "showLowBatteryWarning::showLowBatteryDialog");
                            showLowBatteryDialog();
                        }
                        z5 = true;
                    } else {
                        LogUtil.d(str, str2, "showLowBatteryWarning::createNotification::isFlavorOneDevice()");
                        createNotification(this.mDlgNotificationTitle, this.mDlgNotificationContent, this.mDlgNotificationContentIntnet, this.mDlgActions, sContext, false);
                    }
                    MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("showLowBatteryWarning isDialogShowed: ", str, str2, z5);
                    return z5;
                }
                LogUtil.d(str, str2, "showLowBatteryWarning::createNotification::isCurrentFullscreen()=" + isCurrentFullscreen());
                createNotification(this.mDlgNotificationTitle, this.mDlgNotificationContent, this.mDlgNotificationContentIntnet, this.mDlgActions, sContext, true);
                z5 = false;
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("showLowBatteryWarning isDialogShowed: ", str, str2, z5);
                return z5;
            }
            this.mLowBatteryWarningDelayHelper.setNeedShowDialog(true);
            this.mLowBatteryWarningDelayHelper.setWarningBeforeUnlock(true);
            LogUtil.d(str, str2, "showLowBatteryWarning hadBootCompleted:" + this.mLowBatteryWarningDelayHelper.getHadBootCompleted());
            if (this.mLowBatteryWarningDelayHelper.getHadBootCompleted()) {
                return true;
            }
        }
        return false;
    }

    public final void turnOnPowerSaveMode() {
        if (this.mIsSavePowerMode) {
            return;
        }
        this.mPowerManager.setPowerSaveModeEnabled(true);
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "turnOnPowerSaveMode: turn on Power Save Mode!");
    }

    public static OplusPowerNotificationWarnings getInstance() {
        return sInstance;
    }
}
