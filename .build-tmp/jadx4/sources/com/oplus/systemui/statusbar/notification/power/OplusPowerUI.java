package com.oplus.systemui.statusbar.notification.power;

import android.app.ActivityManagerNative;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.drawable.Icon;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.fragment.app.Fragment$5$;
import androidx.slice.widget.RowView$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.OplusStatusBarDependencyEx;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.coui.appcompat.dialog.COUISecurityAlertDialogBuilder;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.ocs.icdf.utils.a$;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.qs.helper.OtgHelper;
import com.oplus.systemui.statusbar.notification.helper.LowBatteryWarningDelayHelper;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper$$ExternalSyntheticLambda0;
import com.oplus.systemui.statusbar.notification.power.supersave.OplusSuperSaveControllerImpl;
import com.oplus.systemui.statusbar.receiver.CustomPowerReceiver;
import com.oplus.systemui.statusbar.receiver.PowerReceiver;
import com.oplus.systemui.statusbar.receiver.VolumeReceiver;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import com.oplus.transition.OplusAnimationController;
import com.oplus.view.OplusWindowAttributesManager;
import com.oplus.zoom.common.ZoomDCSManager;
import com.oplusos.systemui.common.feature.ChargingFeatureOption;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.observer.SOSBatteryLevelSettingObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPowerUI implements CoreStartable, ConfigurationController.ConfigurationListener {
    public final AnonymousClass1 mBatteryChangeCallback;
    public final Context mContext;
    public final AnonymousClass2 mCustomPowerReceiver;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final Executor mExecutor;
    public final KeyguardStateController mKeyguardStateController;
    public int mLowBatteryAlertCloseLevel;
    public LowBatteryWarningDelayHelper mLowBatteryWarningDelayHelper;
    public AlertDialog mNonOriginalDialog;
    public final NotificationKeyguardHelper mNotificationKeyguardHelper;
    public OtgHelper mOtgHelper;
    public AlertDialog mOverVolumDialog;
    public final AnonymousClass4 mPowerReceiver;
    public ProvisionedObserver mProvisionedObserver;
    public int mScreenHeight;
    public int mScreenWidth;
    public OplusSuperSaveControllerImpl mSuperSaveController;
    public int mUiMode;
    public final UserTracker mUserTracker;
    public final AnonymousClass3 mVolumeReceiver;
    public int mWarningLevel;
    public OplusPowerNotificationWarnings mWarnings;
    public final Handler mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
    public final BatteryHandler mHandler = new BatteryHandler();
    public KeyguardUpdateMonitorCallback mInfoCallback = null;
    public NotificationManager mNotificationManager = null;
    public OplusPowerUISoundUtil mPowerUISoundUtil = null;
    public int mBatteryLevel = 100;
    public int mBatteryStatus = 1;
    public int mPlugType = 0;
    public int mOplusPlugType = 0;
    public int mInvalidCharger = 0;
    public int mCurrentOrientation = 0;
    public final int[] mLowBatteryReminderLevels = new int[4];
    public boolean mHideLowBDialog = true;
    public boolean mSOSBroadcastFlag = false;
    public boolean mIsLowBatteryFistBoot = false;
    public volatile boolean mIsBatteryOriginal = true;
    public volatile boolean mNeedShowNonOriginalDialogOnUnlock = false;
    public volatile boolean mIsBatteryOriginalChecked = false;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerUI$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements BatteryController.BatteryStateChangeCallback {
        public /* synthetic */ OplusPowerUI this$0;

        public final void onPowerSaveChanged(boolean z) {
            LowBatteryDialog lowBatteryDialog;
            Button button;
            NotificationManager notificationManager;
            OplusPowerUI oplusPowerUI = this.this$0;
            if (z && (notificationManager = oplusPowerUI.mNotificationManager) != null) {
                notificationManager.cancelAsUser(null, 10007, UserHandle.ALL);
            }
            OplusPowerNotificationWarnings oplusPowerNotificationWarnings = oplusPowerUI.mWarnings;
            if (oplusPowerNotificationWarnings != null) {
                oplusPowerNotificationWarnings.mIsSavePowerMode = z;
                if (z && (lowBatteryDialog = oplusPowerNotificationWarnings.mLowBatteryDialog) != null && lowBatteryDialog.isShowing()) {
                    AlertDialog dialog = oplusPowerUI.mWarnings.mLowBatteryDialog.getDialog();
                    if ((dialog == null || (button = dialog.mAlert.mButtonPositive) == null) ? false : dialog.getContext().getText(2131956544).equals(button.getText())) {
                        oplusPowerUI.mWarnings.mLowBatteryDialog.dismiss();
                        oplusPowerUI.mWarnings.mLowBatteryDialog = null;
                        LowBatteryUiController.getInstance().dismissBatteryWarningOnSecondDisplay();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerUI$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ OplusPowerUI this$0;

        public final void onUserSwitching(int i) {
            NotificationManager notificationManager;
            OplusPowerNotificationWarnings oplusPowerNotificationWarnings = this.this$0.mWarnings;
            if (oplusPowerNotificationWarnings == null || (notificationManager = oplusPowerNotificationWarnings.mNotificationManager) == null) {
                return;
            }
            notificationManager.cancelAsUser(null, 10007, UserHandle.ALL);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BatteryHandler extends Handler {
        public BatteryHandler() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            OplusPowerUI oplusPowerUI = OplusPowerUI.this;
            switch (i) {
                case 10:
                    OplusPowerUISoundUtil oplusPowerUISoundUtil = oplusPowerUI.mPowerUISoundUtil;
                    if (oplusPowerUISoundUtil != null) {
                        int i2 = oplusPowerUI.mWarningLevel;
                        int[] iArr = oplusPowerUI.mLowBatteryReminderLevels;
                        if (i2 == iArr[0]) {
                            oplusPowerUISoundUtil.playPowerUISound(0);
                        } else if (i2 <= iArr[1]) {
                            oplusPowerUISoundUtil.playPowerUISound(2);
                        }
                    }
                    break;
                case 11:
                    oplusPowerUI.mIsLowBatteryFistBoot = false;
                    oplusPowerUI.mWarnings.showLowBatteryDialog();
                    break;
                case 12:
                    oplusPowerUI.mIsLowBatteryFistBoot = false;
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ProvisionedObserver extends ContentObserver {
        public int mWarningLevel;

        public ProvisionedObserver(BatteryHandler batteryHandler, int i) {
            super(batteryHandler);
            this.mWarningLevel = i;
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor("device_provisioned"), this);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            boolean z2 = Settings.Global.getInt(OplusPowerUI.this.mContext.getContentResolver(), "device_provisioned", 0) != 0;
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("ProvisionedObserver onChange: isProvisioned=", ", mPlugType=", z2);
            sbM.append(OplusPowerUI.this.mPlugType);
            sbM.append(", mWarningLevel=");
            sbM.append(this.mWarningLevel);
            sbM.append(", mBatteryLevel=");
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(OplusPowerUI.this.mBatteryLevel, LogUtil.TAG_COMMON, "Oplus_PowerUI", sbM);
            if (z2) {
                ObserverDispatcher.getInstance().unregisterObserver(this);
                OplusPowerUI oplusPowerUI = OplusPowerUI.this;
                if (oplusPowerUI.mPlugType != 0 || oplusPowerUI.mBatteryLevel > this.mWarningLevel) {
                    return;
                }
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "ProvisionedObserver onChange: to showLowBatteryWarning");
                OplusPowerUI oplusPowerUI2 = OplusPowerUI.this;
                if (oplusPowerUI2.mWarnings.showLowBatteryWarning(this.mWarningLevel, false, oplusPowerUI2.mBatteryLevel, oplusPowerUI2.mIsLowBatteryFistBoot, oplusPowerUI2.mHandler)) {
                    OplusPowerUI.this.playLowBatterySound();
                }
            }
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleAdditionBatteryChange, reason: not valid java name */
    public static void m442$$Nest$mhandleAdditionBatteryChange(OplusPowerUI oplusPowerUI, Intent intent) {
        int i;
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        oplusPowerUI.mOplusPlugType = intent.getIntExtra("chargeplugged", 0);
        int intExtra = intent.getIntExtra(ZoomDCSManager.STATUS, 1);
        oplusPowerUI.mBatteryStatus = intExtra;
        oplusPowerUI.mWarnings.setBatteryStatus(intExtra);
        int i3 = oplusPowerUI.mOplusPlugType;
        OplusPowerNotificationWarnings oplusPowerNotificationWarnings = oplusPowerUI.mWarnings;
        if (i3 == 0) {
            oplusPowerNotificationWarnings.mOldChargeVolStatus = 3;
            oplusPowerNotificationWarnings.mOldChargeTimeout = false;
            oplusPowerNotificationWarnings.mBatteryPlugged = false;
        } else {
            oplusPowerNotificationWarnings.mBatteryPlugged = true;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isPlatformQCOM()) {
            OplusPowerNotificationWarnings oplusPowerNotificationWarnings2 = oplusPowerUI.mWarnings;
            int i4 = oplusPowerUI.mOplusPlugType;
            oplusPowerNotificationWarnings2.getClass();
            int intExtra2 = intent.getIntExtra("chargervolstatus", -1);
            StringBuilder sb = new StringBuilder("updateChargerVoltage: mpluged = ");
            sb.append(i4 != 0);
            sb.append(", charger voltage = ");
            sb.append(intExtra2);
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", sb.toString());
            if (intExtra2 == 2 || intExtra2 == 3) {
                if (oplusPowerNotificationWarnings2.mOldChargeVolStatus != intExtra2 && oplusPowerNotificationWarnings2.mShowChargerVoltageDialog) {
                    oplusPowerNotificationWarnings2.mShowChargerVoltageDialog = false;
                    oplusPowerNotificationWarnings2.dismissChargeErrorWarring(1);
                }
            } else if (intExtra2 == 4 && i4 != 0 && oplusPowerNotificationWarnings2.mOldChargeVolStatus != intExtra2) {
                PowerUIStatistics powerUIStatistics = PowerUIStatistics.getInstance();
                Context context = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics.getClass();
                PowerUIStatistics.collectPowerUIErrorDialogTimes(1, context);
                oplusPowerNotificationWarnings2.mShowChargerVoltageDialog = true;
                oplusPowerNotificationWarnings2.showChargeErrorDialog(1);
            }
            oplusPowerNotificationWarnings2.mOldChargeVolStatus = intExtra2;
            OplusPowerNotificationWarnings oplusPowerNotificationWarnings3 = oplusPowerUI.mWarnings;
            oplusPowerNotificationWarnings3.getClass();
            oplusPowerNotificationWarnings3.mBatteryVolStatus = intent.getIntExtra("batteryvolstatus", 3);
            StringBuilder sb2 = new StringBuilder("updateBatteryVoltage:mBatteryVolStatus = ");
            sb2.append(oplusPowerNotificationWarnings3.mBatteryVolStatus);
            sb2.append(" mOldBatteryVolStatus = ");
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusPowerNotificationWarnings3.mOldBatteryVolStatus, LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", sb2);
            int i5 = oplusPowerNotificationWarnings3.mBatteryVolStatus;
            if (i5 == 2 || i5 == 3) {
                if (oplusPowerNotificationWarnings3.mOldBatteryVolStatus != i5 && oplusPowerNotificationWarnings3.mShowBatteryVoltageDialog) {
                    oplusPowerNotificationWarnings3.mShowBatteryVoltageDialog = false;
                    oplusPowerNotificationWarnings3.dismissChargeErrorWarring(2);
                }
            } else if (i5 == 4 && (oplusPowerNotificationWarnings3.mOldBatteryVolStatus != i5 || (oplusPowerNotificationWarnings3.mBatteryPlugged && !oplusPowerNotificationWarnings3.mOldBatteryPlugged))) {
                oplusPowerNotificationWarnings3.mShowBatteryVoltageDialog = true;
                PowerUIStatistics powerUIStatistics2 = PowerUIStatistics.getInstance();
                Context context2 = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics2.getClass();
                PowerUIStatistics.collectPowerUIErrorDialogTimes(2, context2);
            }
            oplusPowerNotificationWarnings3.mOldBatteryPlugged = oplusPowerNotificationWarnings3.mBatteryPlugged;
            oplusPowerNotificationWarnings3.mOldBatteryVolStatus = oplusPowerNotificationWarnings3.mBatteryVolStatus;
            oplusPowerUI.mWarnings.mOldBatteryPlugged = OplusPowerNotificationWarnings.getInstance(oplusPowerUI.mContext).mBatteryPlugged;
            if (oplusPowerUI.mOplusPlugType != 0) {
                OplusPowerNotificationWarnings oplusPowerNotificationWarnings4 = oplusPowerUI.mWarnings;
                oplusPowerNotificationWarnings4.getClass();
                oplusPowerNotificationWarnings4.mChargeTimeout = intent.getBooleanExtra("chargetimeout", false);
                StringBuilder sb3 = new StringBuilder("updateChargeTimeout mChargeTimeout = ");
                sb3.append(oplusPowerNotificationWarnings4.mChargeTimeout);
                sb3.append(" mOldChargeTimeout = ");
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", sb3, oplusPowerNotificationWarnings4.mOldChargeTimeout);
                boolean z6 = oplusPowerNotificationWarnings4.mChargeTimeout;
                if (z6 && oplusPowerNotificationWarnings4.mOldChargeTimeout != z6) {
                    oplusPowerNotificationWarnings4.mShowChargeTimeoutDialog = true;
                    PowerUIStatistics powerUIStatistics3 = PowerUIStatistics.getInstance();
                    Context context3 = OplusPowerNotificationWarnings.sContext;
                    powerUIStatistics3.getClass();
                    PowerUIStatistics.collectPowerUIErrorDialogTimes(6, context3);
                } else if (oplusPowerNotificationWarnings4.mOldChargeTimeout != z6 && oplusPowerNotificationWarnings4.mShowChargeTimeoutDialog) {
                    oplusPowerNotificationWarnings4.mShowChargeTimeoutDialog = false;
                    oplusPowerNotificationWarnings4.dismissChargeErrorWarring(6);
                }
                oplusPowerNotificationWarnings4.mOldChargeTimeout = oplusPowerNotificationWarnings4.mChargeTimeout;
            }
        }
        int intExtra3 = intent.getIntExtra("notifycode", 0);
        StringBuilder sb4 = new StringBuilder("mOplusPlugType = ");
        sb4.append(oplusPowerUI.mOplusPlugType);
        sb4.append(" mBatteryStatus = ");
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", Fragment$5$.ExternalSyntheticOutline0.m(sb4, oplusPowerUI.mBatteryStatus, intExtra3, " notify = "));
        boolean z7 = oplusPowerUI.mOplusPlugType != 0;
        if (oplusPowerUI.mNotificationManager == null) {
            oplusPowerUI.mNotificationManager = (NotificationManager) oplusPowerUI.mContext.getSystemService("notification");
        }
        if (!z7) {
            oplusPowerUI.mNotificationManager.cancelAsUser("standardChargeTAG", 10015, UserHandle.ALL);
        } else if (((intExtra3 >> 30) & 1) == 1) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Unofficial charger detected");
            NotificationStatisticUtil.chargingNotificationReminder(0, oplusPowerUI.mContext);
            int intForUser = Settings.System.getIntForUser(oplusPowerUI.mContext.getContentResolver(), "non_standard_charge_reminder", 0, 0);
            Context context4 = oplusPowerUI.mContext;
            if (intForUser != 1) {
                List<ResolveInfo> listQueryIntentActivities = context4.getPackageManager().queryIntentActivities(new Intent("com.oplus.operationManual.FEEDBACK_AND_TIPS"), 0);
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "resolves = " + listQueryIntentActivities);
                Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        NotificationStatisticUtil.chargingNotificationReminder(3, oplusPowerUI.mContext);
                        break;
                    }
                    if (it.next().activityInfo.packageName.equals("com.coloros.operationManual")) {
                        NotificationStatisticUtil.chargingNotificationReminder(1, oplusPowerUI.mContext);
                        Intent intent2 = new Intent();
                        intent2.setPackage("com.android.systemui");
                        intent2.setAction("android.intent.action.ACTION_CLOSE_CHANGED_NOTIFY");
                        Notification.Action actionBuild = new Notification.Action.Builder((Icon) null, oplusPowerUI.mContext.getString(2131954801), PendingIntent.getBroadcast(oplusPowerUI.mContext, 0, intent2, 67108864)).build();
                        Intent intent3 = new Intent("android.intent.action.MAIN");
                        intent3.setPackage("com.android.systemui");
                        intent3.setAction("android.intent.action.ACTION_DETAIL_CHANGED_NOTIFY");
                        PendingIntent broadcast = PendingIntent.getBroadcast(oplusPowerUI.mContext, 0, intent3, 67108864);
                        oplusPowerUI.mNotificationManager.createNotificationChannel(new NotificationChannel(OplusSuperSaveControllerImpl.UN_STANDARD_CHARGE, oplusPowerUI.mContext.getString(2131954805), 4));
                        oplusPowerUI.mNotificationManager.notifyAsUser("standardChargeTAG", 10015, new Notification.Builder(oplusPowerUI.mContext, OplusSuperSaveControllerImpl.UN_STANDARD_CHARGE).setContentTitle(oplusPowerUI.mContext.getString(2131954807)).setSmallIcon(2131232662).setContentText(oplusPowerUI.mContext.getString(2131954806)).setVisibility(1).setCategory("sys").addAction(actionBuild).setContentIntent(broadcast).setAutoCancel(true).build(), UserHandle.ALL);
                        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Send non-standard charging notification");
                        break;
                    }
                }
            } else {
                NotificationStatisticUtil.chargingNotificationReminder(2, context4);
            }
        }
        OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl = oplusPowerUI.mSuperSaveController;
        if (oplusSuperSaveControllerImpl == null || !oplusSuperSaveControllerImpl.isSupportSuperSaveScene()) {
            OplusPowerNotificationWarnings oplusPowerNotificationWarnings5 = oplusPowerUI.mWarnings;
            oplusPowerNotificationWarnings5.getClass();
            oplusPowerNotificationWarnings5.mEvent = intent.getIntExtra("notifycode", 0);
            oplusPowerNotificationWarnings5.mPlugged = intent.getIntExtra("chargeplugged", 0);
            StringBuilder sb5 = new StringBuilder("updateBattery : event code is ");
            sb5.append(oplusPowerNotificationWarnings5.mEvent);
            sb5.append(" mPlugged is ");
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusPowerNotificationWarnings5.mPlugged, LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", sb5);
            boolean z8 = 32 == (oplusPowerNotificationWarnings5.mEvent & 32);
            oplusPowerNotificationWarnings5.mBatteryConnect = z8;
            if (z8 && !oplusPowerNotificationWarnings5.mBatteryConnectShow && z8 != oplusPowerNotificationWarnings5.mLastBatteryConnect) {
                if (!NotificationUtil.isATOVersion()) {
                    PowerUIStatistics powerUIStatistics4 = PowerUIStatistics.getInstance();
                    Context context5 = OplusPowerNotificationWarnings.sContext;
                    powerUIStatistics4.getClass();
                    PowerUIStatistics.collectPowerUIErrorDialogTimes(5, context5);
                    oplusPowerNotificationWarnings5.showChargeErrorDialog(5);
                }
                oplusPowerNotificationWarnings5.mBatteryConnectShow = true;
            }
            if ((oplusPowerNotificationWarnings5.mBatteryConnectShow && !oplusPowerNotificationWarnings5.mBatteryConnect) || oplusPowerNotificationWarnings5.mPlugged == 0) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(5);
                oplusPowerNotificationWarnings5.mBatteryConnectShow = false;
            }
            oplusPowerNotificationWarnings5.mLastBatteryConnect = oplusPowerNotificationWarnings5.mBatteryConnect;
            oplusPowerNotificationWarnings5.mBatteryLowTemperature = 16 == (oplusPowerNotificationWarnings5.mEvent & 16);
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", new StringBuilder("mBatteryLowTemperature = "), oplusPowerNotificationWarnings5.mBatteryLowTemperature);
            if (oplusPowerNotificationWarnings5.mBatteryLowTmpShow || !(z5 = oplusPowerNotificationWarnings5.mBatteryLowTemperature) || z5 == oplusPowerNotificationWarnings5.mLastBatteryLowTemperature) {
                i = 3;
            } else {
                PowerUIStatistics powerUIStatistics5 = PowerUIStatistics.getInstance();
                Context context6 = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics5.getClass();
                i = 3;
                PowerUIStatistics.collectPowerUIErrorDialogTimes(3, context6);
                oplusPowerNotificationWarnings5.showChargeErrorDialog(3);
                oplusPowerNotificationWarnings5.mBatteryLowTmpShow = true;
            }
            if (oplusPowerNotificationWarnings5.mBatteryLowTmpShow && !oplusPowerNotificationWarnings5.mBatteryLowTemperature) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(i);
                oplusPowerNotificationWarnings5.mBatteryLowTmpShow = false;
            }
            oplusPowerNotificationWarnings5.mLastBatteryLowTemperature = oplusPowerNotificationWarnings5.mBatteryLowTemperature;
            oplusPowerNotificationWarnings5.mBatteryOverTemperature = 8 == (oplusPowerNotificationWarnings5.mEvent & 8);
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", new StringBuilder("mBatteryOverTemperature = "), oplusPowerNotificationWarnings5.mBatteryOverTemperature);
            if (!oplusPowerNotificationWarnings5.mBatteryOverTmpShow && (z4 = oplusPowerNotificationWarnings5.mBatteryOverTemperature) && z4 != oplusPowerNotificationWarnings5.mLastBatteryOverTemperature) {
                PowerUIStatistics powerUIStatistics6 = PowerUIStatistics.getInstance();
                Context context7 = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics6.getClass();
                PowerUIStatistics.collectPowerUIErrorDialogTimes(4, context7);
                oplusPowerNotificationWarnings5.mBatteryOverTmpShow = true;
            }
            if ((oplusPowerNotificationWarnings5.mBatteryOverTmpShow && !oplusPowerNotificationWarnings5.mBatteryOverTemperature) || oplusPowerNotificationWarnings5.mPlugged == 0) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(4);
                oplusPowerNotificationWarnings5.mBatteryOverTmpShow = false;
            }
            oplusPowerNotificationWarnings5.mLastBatteryOverTemperature = oplusPowerNotificationWarnings5.mBatteryOverTemperature;
            oplusPowerNotificationWarnings5.mBatteryTimeout = 512 == (oplusPowerNotificationWarnings5.mEvent & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION);
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", new StringBuilder("mBatteryTimeout = "), oplusPowerNotificationWarnings5.mBatteryTimeout);
            if (!oplusPowerNotificationWarnings5.mBatteryTimeoutShow && (z3 = oplusPowerNotificationWarnings5.mBatteryTimeout) && z3 != oplusPowerNotificationWarnings5.mLastBatteryTimeout) {
                PowerUIStatistics powerUIStatistics7 = PowerUIStatistics.getInstance();
                Context context8 = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics7.getClass();
                PowerUIStatistics.collectPowerUIErrorDialogTimes(6, context8);
                oplusPowerNotificationWarnings5.mBatteryTimeoutShow = true;
            }
            if ((oplusPowerNotificationWarnings5.mBatteryTimeoutShow && !oplusPowerNotificationWarnings5.mBatteryTimeout) || oplusPowerNotificationWarnings5.mPlugged == 0) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(6);
                oplusPowerNotificationWarnings5.mBatteryTimeoutShow = false;
            }
            oplusPowerNotificationWarnings5.mLastBatteryTimeout = oplusPowerNotificationWarnings5.mBatteryTimeout;
            oplusPowerNotificationWarnings5.mBatteryFull = (oplusPowerNotificationWarnings5.mEvent & 19584) != 0;
            if (LogUtil.getSInternal()) {
                StringBuilder sb6 = new StringBuilder("mBatteryFull = ");
                sb6.append(oplusPowerNotificationWarnings5.mBatteryFull);
                sb6.append(", mBatteryFullShow = ");
                sb6.append(oplusPowerNotificationWarnings5.mBatteryFullShow);
                sb6.append(", mLastBatteryFull = ");
                sb6.append(oplusPowerNotificationWarnings5.mLastBatteryFull);
                sb6.append(", mEvent = ");
                ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusPowerNotificationWarnings5.mEvent, LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", sb6);
            }
            int i6 = oplusPowerNotificationWarnings5.mBatteryStatus;
            boolean z9 = i6 == 5 && oplusPowerNotificationWarnings5.mLastBatteryStatus != i6;
            if (!oplusPowerNotificationWarnings5.mBatteryFullShow && (z2 = oplusPowerNotificationWarnings5.mBatteryFull) && (z2 != oplusPowerNotificationWarnings5.mLastBatteryFull || z9)) {
                int i7 = oplusPowerNotificationWarnings5.mEvent;
                if (2048 == (i7 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT)) {
                    PowerUIStatistics powerUIStatistics8 = PowerUIStatistics.getInstance();
                    Context context9 = OplusPowerNotificationWarnings.sContext;
                    powerUIStatistics8.getClass();
                    PowerUIStatistics.collectPowerUIErrorDialogTimes(9, context9);
                    oplusPowerNotificationWarnings5.mBatteryFullShow = true;
                } else if (1024 == (i7 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE)) {
                    PowerUIStatistics powerUIStatistics9 = PowerUIStatistics.getInstance();
                    Context context10 = OplusPowerNotificationWarnings.sContext;
                    powerUIStatistics9.getClass();
                    PowerUIStatistics.collectPowerUIErrorDialogTimes(8, context10);
                    oplusPowerNotificationWarnings5.mBatteryFullShow = true;
                } else if (16384 == (i7 & 16384)) {
                    if (NotificationUtil.isBatteryOriginal()) {
                        oplusPowerNotificationWarnings5.showChargeErrorDialog(10);
                        oplusPowerNotificationWarnings5.mBatteryFullShow = true;
                    }
                } else if (i6 == 5) {
                    oplusPowerNotificationWarnings5.showChargeErrorDialog(7);
                    PowerUIStatistics powerUIStatistics10 = PowerUIStatistics.getInstance();
                    Context context11 = OplusPowerNotificationWarnings.sContext;
                    powerUIStatistics10.getClass();
                    PowerUIStatistics.collectPowerUIErrorDialogTimes(7, context11);
                    oplusPowerNotificationWarnings5.mBatteryFullShow = true;
                }
                if (oplusPowerNotificationWarnings5.mBatteryFullShow) {
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "mBatteryFullShow set true ");
                }
            }
            if (oplusPowerNotificationWarnings5.mBatteryFullShow && !oplusPowerNotificationWarnings5.mBatteryFull) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(7);
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(8);
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(9);
                oplusPowerNotificationWarnings5.mBatteryFullShow = false;
            }
            oplusPowerNotificationWarnings5.mLastBatteryFull = oplusPowerNotificationWarnings5.mBatteryFull;
            oplusPowerNotificationWarnings5.mLastBatteryStatus = oplusPowerNotificationWarnings5.mBatteryStatus;
            oplusPowerNotificationWarnings5.mBatteryOverVol = 64 == (oplusPowerNotificationWarnings5.mEvent & 64) && 1 == (oplusPowerNotificationWarnings5.mPlugged & 1);
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", new StringBuilder(", bBatteryOverVol = "), oplusPowerNotificationWarnings5.mBatteryOverVol);
            boolean z10 = oplusPowerNotificationWarnings5.mBatteryOverVol;
            if (!z10 || z10 == oplusPowerNotificationWarnings5.mLastBatteryOverVol) {
                i2 = 2;
            } else {
                PowerUIStatistics powerUIStatistics11 = PowerUIStatistics.getInstance();
                Context context12 = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics11.getClass();
                i2 = 2;
                PowerUIStatistics.collectPowerUIErrorDialogTimes(2, context12);
            }
            if (oplusPowerNotificationWarnings5.mPlugged == 0) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(i2);
            }
            oplusPowerNotificationWarnings5.mLastBatteryOverVol = oplusPowerNotificationWarnings5.mBatteryOverVol;
            oplusPowerNotificationWarnings5.mChargeOverVol = i2 == (oplusPowerNotificationWarnings5.mEvent & i2);
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", new StringBuilder("bChargeOverVol = "), oplusPowerNotificationWarnings5.mChargeOverVol);
            if (!oplusPowerNotificationWarnings5.mBatteryOverCVShow && (z = oplusPowerNotificationWarnings5.mChargeOverVol) && z != oplusPowerNotificationWarnings5.mLastChargeOverVol) {
                PowerUIStatistics powerUIStatistics12 = PowerUIStatistics.getInstance();
                Context context13 = OplusPowerNotificationWarnings.sContext;
                powerUIStatistics12.getClass();
                PowerUIStatistics.collectPowerUIErrorDialogTimes(1, context13);
                oplusPowerNotificationWarnings5.showChargeErrorDialog(1);
                oplusPowerNotificationWarnings5.mBatteryOverCVShow = true;
            }
            if ((oplusPowerNotificationWarnings5.mBatteryOverCVShow && !oplusPowerNotificationWarnings5.mChargeOverVol) || oplusPowerNotificationWarnings5.mPlugged == 0) {
                oplusPowerNotificationWarnings5.dismissChargeErrorWarring(1);
                oplusPowerNotificationWarnings5.mBatteryOverCVShow = false;
            }
            oplusPowerNotificationWarnings5.mLastChargeOverVol = oplusPowerNotificationWarnings5.mChargeOverVol;
            if (oplusPowerNotificationWarnings5.mPlugged == 0) {
                oplusPowerNotificationWarnings5.mBatteryTimeoutShow = false;
                oplusPowerNotificationWarnings5.mBatteryOverCVShow = false;
                oplusPowerNotificationWarnings5.mBatteryConnectShow = false;
                oplusPowerNotificationWarnings5.mBatteryOverTmpShow = false;
            }
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleBatteryActionChange, reason: not valid java name */
    public static void m443$$Nest$mhandleBatteryActionChange(OplusPowerUI oplusPowerUI, Intent intent) {
        int i;
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI", new StringBuilder("Intent start() mHideLowBDialog= "), oplusPowerUI.mHideLowBDialog);
        if (oplusPowerUI.mHideLowBDialog) {
            return;
        }
        int i2 = oplusPowerUI.mBatteryLevel;
        oplusPowerUI.mBatteryLevel = intent.getIntExtra("level", 100);
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusPowerUI.mBatteryLevel, LogUtil.TAG_COMMON, "Oplus_PowerUI", ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "oldBatteryLevel = ", "mBatteryLevel = "));
        int i3 = oplusPowerUI.mBatteryStatus;
        int intExtra = intent.getIntExtra(ZoomDCSManager.STATUS, 1);
        oplusPowerUI.mBatteryStatus = intExtra;
        oplusPowerUI.mWarnings.setBatteryStatus(intExtra);
        int i4 = oplusPowerUI.mPlugType;
        oplusPowerUI.mPlugType = intent.getIntExtra("plugged", 0);
        int i5 = oplusPowerUI.mInvalidCharger;
        oplusPowerUI.mInvalidCharger = intent.getIntExtra("invalid_charger", 0);
        int intExtra2 = intent.getIntExtra("notifycode", 0);
        boolean z = oplusPowerUI.mPlugType != 0;
        boolean z2 = i4 != 0;
        int iFindBatteryLevelBucket$1 = oplusPowerUI.findBatteryLevelBucket$1(i2);
        int iFindBatteryLevelBucket$2 = oplusPowerUI.findBatteryLevelBucket$1(oplusPowerUI.mBatteryLevel);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() && !oplusPowerUI.mSOSBroadcastFlag && !z && oplusPowerUI.mBatteryLevel == SOSBatteryLevelSettingObserver.getInstance().getSosLowBatteryLevel() && oplusPowerUI.mBatteryLevel < i2) {
            Intent intent2 = new Intent();
            intent2.setPackage(oplusPowerUI.mContext.getString(2131956495));
            intent2.setAction("oplus.intent.action.SOS_LOW_BATTERY_NOTIFICATION");
            oplusPowerUI.mContext.sendBroadcastAsUser(intent2, UserHandle.CURRENT, "com.oplus.permission.safe.SECURITY");
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Send SOS low battery broadcast!");
            oplusPowerUI.mSOSBroadcastFlag = true;
        } else if (oplusPowerUI.mSOSBroadcastFlag && oplusPowerUI.mBatteryLevel != SOSBatteryLevelSettingObserver.getInstance().getSosLowBatteryLevel()) {
            oplusPowerUI.mSOSBroadcastFlag = false;
        }
        StringBuilder sb = new StringBuilder("buckets, ");
        sb.append(oplusPowerUI.mLowBatteryAlertCloseLevel);
        sb.append(" .. ");
        sb.append(oplusPowerUI.mLowBatteryReminderLevels[0]);
        sb.append(" .. ");
        RectList$.ExternalSyntheticOutline0.m(oplusPowerUI.mLowBatteryReminderLevels[1], i2, ", level          ", " --> ", sb);
        RectList$.ExternalSyntheticOutline0.m(oplusPowerUI.mBatteryLevel, i3, ", status         ", " --> ", sb);
        RectList$.ExternalSyntheticOutline0.m(oplusPowerUI.mBatteryStatus, i4, ", plugType       ", " --> ", sb);
        RectList$.ExternalSyntheticOutline0.m(oplusPowerUI.mPlugType, i5, ", invalidCharger ", " --> ", sb);
        RectList$.ExternalSyntheticOutline0.m(oplusPowerUI.mInvalidCharger, iFindBatteryLevelBucket$1, ", bucket         ", " --> ", sb);
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(iFindBatteryLevelBucket$2, ", plugged        ", " --> ", sb, z2);
        sb.append(z);
        sb.append(", notify        ");
        sb.append(intExtra2);
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", sb.toString());
        if (!z2 && z && oplusPowerUI.mNonOriginalDialog == null && !oplusPowerUI.mIsBatteryOriginal && Settings.Global.getInt(oplusPowerUI.mContext.getContentResolver(), "is_non_original_battery_dialog_never_remind", 0) == 0) {
            oplusPowerUI.showNonOriginalDialog();
        }
        if (i5 == 0 && oplusPowerUI.mInvalidCharger != 0) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "showing invalid charger warning");
            oplusPowerUI.mWarnings.showInvalidChargerDialog(oplusPowerUI.mContext);
            return;
        }
        if (i5 != 0 && oplusPowerUI.mInvalidCharger == 0) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "dismissInvalidChargerDialog***************");
            AlertDialog alertDialog = oplusPowerUI.mWarnings.mInvalidChargerDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        } else if (oplusPowerUI.mWarnings.mInvalidChargerDialog != null) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "mWarnings.mInvalidChargerDialog != null");
            return;
        }
        OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl = oplusPowerUI.mSuperSaveController;
        if (oplusSuperSaveControllerImpl == null || !oplusSuperSaveControllerImpl.isSupportSuperSaveScene()) {
            int i6 = oplusPowerUI.mBatteryLevel;
            int[] iArr = oplusPowerUI.mLowBatteryReminderLevels;
            int i7 = iArr[3];
            if (i6 > i7 && i6 > (i7 = iArr[2]) && i6 > (i7 = iArr[1])) {
                i = iArr[0];
                if (i6 > i) {
                    i = 0;
                }
            } else {
                i = i7;
            }
            oplusPowerUI.mWarningLevel = i;
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(oplusPowerUI.mWarningLevel, LogUtil.TAG_COMMON, "Oplus_PowerUI", new StringBuilder("warningLevel = "));
            BatteryHandler batteryHandler = oplusPowerUI.mHandler;
            if (FeatureOption.isBattWarningAllowed()) {
                oplusPowerUI.mWarnings.setBatteryLowDialogPowerLevel(oplusPowerUI.mBatteryLevel);
                boolean z3 = (oplusPowerUI.mWarningLevel == 0 || z || ((iFindBatteryLevelBucket$2 >= iFindBatteryLevelBucket$1 || iFindBatteryLevelBucket$2 >= 0) && !z2)) ? false : true;
                if (oplusPowerUI.mBatteryStatus != 1 && z3) {
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Warning Level Down, or Charger plug out*************");
                    if (Settings.Global.getInt(oplusPowerUI.mContext.getContentResolver(), "device_provisioned", 0) == 0) {
                        int i8 = oplusPowerUI.mWarningLevel;
                        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i8, "bootRegHandle: warningLevel=", LogUtil.TAG_COMMON, "Oplus_PowerUI");
                        ProvisionedObserver provisionedObserver = oplusPowerUI.mProvisionedObserver;
                        if (provisionedObserver == null) {
                            oplusPowerUI.mProvisionedObserver = oplusPowerUI.new ProvisionedObserver(batteryHandler, i8);
                        } else {
                            provisionedObserver.mWarningLevel = i8;
                        }
                    } else if (oplusPowerUI.mWarnings.showLowBatteryWarning(oplusPowerUI.mWarningLevel, z, oplusPowerUI.mBatteryLevel, oplusPowerUI.mIsLowBatteryFistBoot, batteryHandler)) {
                        oplusPowerUI.playLowBatterySound();
                    }
                } else if (iFindBatteryLevelBucket$2 > iFindBatteryLevelBucket$1 || (z && !z2)) {
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Warning Level Up, or Charger plug in*************");
                    oplusPowerUI.mWarnings.dismissLowBatteryWarning();
                }
            } else {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "showBatteryWarning not isBattWarningAllowed ");
            }
        }
        OtgHelper otgHelper = oplusPowerUI.mOtgHelper;
        if (otgHelper.mBgHandler == null) {
            otgHelper.mBgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
        }
        otgHelper.mBgHandler.removeCallbacks(otgHelper.mInitOtgRunnable);
        otgHelper.mBgHandler.post(otgHelper.mInitOtgRunnable);
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleLowBatteryActionChange, reason: not valid java name */
    public static void m444$$Nest$mhandleLowBatteryActionChange(OplusPowerUI oplusPowerUI, String str) {
        String str2;
        String str3;
        PendingIntent pendingIntent;
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleLowBatteryActionChange action : ".concat(str));
        if (LowBatteryWarningDelayHelper.ACTION_LOW_BATTERY_WARNING_HOLD.equals(str)) {
            oplusPowerUI.mLowBatteryWarningDelayHelper.setTimeOut(true);
        }
        OplusPowerNotificationWarnings oplusPowerNotificationWarnings = oplusPowerUI.mWarnings;
        if (!oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper.getNeedShowDialog() || oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper.getHasHoldWarningShow() || oplusPowerNotificationWarnings.mLowBatteryDialog == null) {
            LowBatteryWarningDelayHelper lowBatteryWarningDelayHelper = oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper;
            lowBatteryWarningDelayHelper.timeOut = true;
            lowBatteryWarningDelayHelper.holdLowBatteryWarning = false;
            lowBatteryWarningDelayHelper.needShowDialog = false;
            lowBatteryWarningDelayHelper.hasHoldWarningShow = true;
            return;
        }
        if (oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper.getTimeOut()) {
            LowBatteryDialog lowBatteryDialogCreatePlugChargerDialog = oplusPowerNotificationWarnings.createPlugChargerDialog(oplusPowerNotificationWarnings.mBatteryLevel, OplusPowerNotificationWarnings.sContext);
            oplusPowerNotificationWarnings.mLowBatteryDialog = lowBatteryDialogCreatePlugChargerDialog;
            Window window = lowBatteryDialogCreatePlugChargerDialog.getWindow();
            window.getAttributes();
            OplusWindowAttributesManager.setIgnoreHomeMenuKeyState(window, 1);
        }
        if ((!AppInfoUtil.getTopIsFullscreen() && !NotificationUtil.isCurrentAppForcingFullScreenBanner(OplusPowerNotificationWarnings.sContext)) || (str2 = oplusPowerNotificationWarnings.mDlgNotificationTitle) == null || (str3 = oplusPowerNotificationWarnings.mDlgNotificationContent) == null || (pendingIntent = oplusPowerNotificationWarnings.mDlgNotificationContentIntnet) == null) {
            oplusPowerNotificationWarnings.showLowBatteryDialog();
        } else {
            oplusPowerNotificationWarnings.createNotification(str2, str3, pendingIntent, oplusPowerNotificationWarnings.mDlgActions, OplusPowerNotificationWarnings.sContext, true);
        }
        LowBatteryWarningDelayHelper lowBatteryWarningDelayHelper2 = oplusPowerNotificationWarnings.mLowBatteryWarningDelayHelper;
        lowBatteryWarningDelayHelper2.timeOut = true;
        lowBatteryWarningDelayHelper2.holdLowBatteryWarning = false;
        lowBatteryWarningDelayHelper2.needShowDialog = false;
        lowBatteryWarningDelayHelper2.hasHoldWarningShow = true;
        if (oplusPowerUI.mLowBatteryWarningDelayHelper.getWarningBeforeUnlock()) {
            return;
        }
        oplusPowerUI.playLowBatterySound();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: -$$Nest$mhandlePowerConnectStateChange, reason: not valid java name */
    public static void m445$$Nest$mhandlePowerConnectStateChange(OplusPowerUI oplusPowerUI, String str, Context context) {
        OplusPowerUISoundUtil oplusPowerUISoundUtil;
        int i = 1;
        if (!str.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
            if (str.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                oplusPowerUI.powerConnectStateChange(1);
                return;
            }
            return;
        }
        if (context == null) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "notifyCharging: context is null");
        } else {
            int ringerMode = ((AudioManager) context.getSystemService("audio")).getRingerMode();
            if (ringerMode == 2) {
                boolean z = MultiUserContentHelper.getSystemIntValue(oplusPowerUI.mContext, CommonSettingsValueProxy.KEY_SUPPORT_CHARGE, 1) != 0;
                if (LogUtil.getNormalDebug()) {
                    MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("play sound for charging. chargingBeepEnabled = ", LogUtil.TAG_COMMON, "Oplus_PowerUI", z);
                }
                if (z && (oplusPowerUISoundUtil = oplusPowerUI.mPowerUISoundUtil) != null) {
                    oplusPowerUISoundUtil.playPowerUISound(3);
                }
                if (CommonSettingsValueProxy.isHapticFeedbackOn(oplusPowerUI.mContext)) {
                    FeatureOption.INSTANCE.getClass();
                    int i2 = FeatureOption.isLinearVibratorSupport() ? 100 : 170;
                    BatteryHandler batteryHandler = oplusPowerUI.mHandler;
                    OplusPowerUI$$ExternalSyntheticLambda0 oplusPowerUI$$ExternalSyntheticLambda0 = new OplusPowerUI$$ExternalSyntheticLambda0(i);
                    oplusPowerUI$$ExternalSyntheticLambda0.f$0 = oplusPowerUI;
                    VarHandle.storeStoreFence();
                    batteryHandler.postDelayed(oplusPowerUI$$ExternalSyntheticLambda0, i2);
                }
            } else if ((ringerMode == 1 || ringerMode == 0) && CommonSettingsValueProxy.isHapticFeedbackOn(oplusPowerUI.mContext)) {
                VibrationHelper.getInstance(oplusPowerUI.mContext).vibrateCustomized(oplusPowerUI.mContext, 2, true);
            }
        }
        if (oplusPowerUI.mNotificationManager == null) {
            oplusPowerUI.mNotificationManager = (NotificationManager) oplusPowerUI.mContext.getSystemService("notification");
        }
        NotificationManager notificationManager = oplusPowerUI.mNotificationManager;
        if (notificationManager != null) {
            notificationManager.cancelAsUser(null, 10007, UserHandle.ALL);
        }
        oplusPowerUI.powerConnectStateChange(0);
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleUnStandardClose, reason: not valid java name */
    public static void m446$$Nest$mhandleUnStandardClose(OplusPowerUI oplusPowerUI) {
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Click the Don’t Remind Button");
        Settings.System.putIntForUser(oplusPowerUI.mContext.getContentResolver(), "non_standard_charge_reminder", 1, 0);
        oplusPowerUI.mNotificationManager.cancelAsUser("standardChargeTAG", 10015, UserHandle.ALL);
        Context context = oplusPowerUI.mContext;
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        HashMap map = new HashMap();
        map.put("click_key", String.valueOf(0));
        OplusTrackProxyUtil.onCommon(context, "20082006", "charging_do_not_remind_again", map);
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleUnStandardDetails, reason: not valid java name */
    public static void m447$$Nest$mhandleUnStandardDetails(OplusPowerUI oplusPowerUI) {
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Click the view details button");
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setAction("android.intent.action.VIEW");
            intent.addFlags(335544320);
            intent.setData(Uri.parse("fat://fb/home?target=FAQDetailPage&detailID=7023"));
            intent.setPackage("com.coloros.operationManual");
            oplusPowerUI.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
            oplusPowerUI.mNotificationManager.cancel("standardChargeTAG", 10015);
            Context context = oplusPowerUI.mContext;
            Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
            HashMap map = new HashMap();
            map.put("click_method", String.valueOf(0));
            OplusTrackProxyUtil.onCommon(context, "20082006", "charging_check_details", map);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, "Oplus_PowerUI", "chargeNotifyDetail  error=" + e);
            oplusPowerUI.mNotificationManager.cancel("standardChargeTAG", 10015);
            Context context2 = oplusPowerUI.mContext;
            Lock lock2 = NotificationStatisticUtil.NOTIFICATION_LOCK;
            HashMap map2 = new HashMap();
            map2.put("click_method", String.valueOf(1));
            OplusTrackProxyUtil.onCommon(context2, "20082006", "charging_check_details", map2);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleUserUnlockedForNonOriginalBattery, reason: not valid java name */
    public static void m448$$Nest$mhandleUserUnlockedForNonOriginalBattery(OplusPowerUI oplusPowerUI) {
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI", new StringBuilder("handleUserUnlockedForNonOriginalBattery: mNeedShowNonOriginalDialogOnUnlock="), oplusPowerUI.mNeedShowNonOriginalDialogOnUnlock);
        if (oplusPowerUI.mNeedShowNonOriginalDialogOnUnlock) {
            oplusPowerUI.mNeedShowNonOriginalDialogOnUnlock = false;
            if (oplusPowerUI.mIsBatteryOriginal) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleUserUnlockedForNonOriginalBattery: is original battery, skip");
                return;
            }
            if (Settings.Global.getInt(oplusPowerUI.mContext.getContentResolver(), "is_non_original_battery_dialog_never_remind", 0) != 0) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleUserUnlockedForNonOriginalBattery: user set never mind, skip");
                return;
            }
            if (oplusPowerUI.mNonOriginalDialog != null) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleUserUnlockedForNonOriginalBattery: dialog already showing, skip");
            } else if (!oplusPowerUI.isCurrentlyPlugged()) {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleUserUnlockedForNonOriginalBattery: not plugged anymore, skip");
            } else {
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleUserUnlockedForNonOriginalBattery: show non original dialog");
                oplusPowerUI.showNonOriginalDialog();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v7, types: [com.oplus.systemui.statusbar.notification.power.OplusPowerUI$2] */
    /* JADX WARN: Type inference failed for: r4v8, types: [com.oplus.systemui.statusbar.notification.power.OplusPowerUI$3] */
    /* JADX WARN: Type inference failed for: r4v9, types: [com.oplus.systemui.statusbar.notification.power.OplusPowerUI$4] */
    public OplusPowerUI(Context context, NotificationCenterManager notificationCenterManager, NotificationKeyguardHelper notificationKeyguardHelper, KeyguardStateController keyguardStateController, DeviceProvisionedController deviceProvisionedController, Executor executor, UserTracker userTracker) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mBatteryChangeCallback = anonymousClass1;
        this.mCustomPowerReceiver = new CustomPowerReceiver() { // from class: com.oplus.systemui.statusbar.notification.power.OplusPowerUI.2
            @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
            public void onReceive(Context context2, Intent intent) {
                super.onReceive(context2, intent);
                boolean zEquals = TextUtils.equals(intent.getAction(), CustomPowerReceiver.ACTION_TURN_ON_SAVE_POWER_MODE);
                OplusPowerUI oplusPowerUI = OplusPowerUI.this;
                if (zEquals) {
                    oplusPowerUI.mWarnings.turnOnPowerSaveMode();
                } else if (TextUtils.equals(intent.getAction(), CustomPowerReceiver.SUPER_POWER_SAVE_FUNC)) {
                    Settings.System.putIntForUser(oplusPowerUI.mContext.getContentResolver(), "super_powersave_launcher_enter", 1, 0);
                }
            }
        };
        this.mVolumeReceiver = new VolumeReceiver() { // from class: com.oplus.systemui.statusbar.notification.power.OplusPowerUI.3
            @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("game_volume_void", "game_space_over");
                OplusStatusBarDependencyEx oplusStatusBarDependencyEx = (OplusStatusBarDependencyEx) DependencyEx.sDependency.getDependency(OplusStatusBarDependencyEx.class);
                boolean z = oplusStatusBarDependencyEx != null && oplusStatusBarDependencyEx.isFullscreen();
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", KeyguardLogger$.ExternalSyntheticOutline0.m("action_volume = ", action, "topScreen ", z));
                if ("android.media.action.SET_SAFE_VOLUME".equals(action)) {
                    int intExtra = intent.getIntExtra("android.intent.extra.user_handle", 0);
                    int currentUserId = OsBinderCacheUtils.getCurrentUserId();
                    if (intExtra != currentUserId) {
                        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", MutableVectorKt$.ExternalSyntheticOutline0.m("do not show dialog due to userId (intent: ", intExtra, currentUserId, " current: ", " ) mis-match."));
                        return;
                    }
                    if (FeatureOption.isGameMlbbSupport()) {
                        LogUtil.i(LogUtil.TAG_COMMON, "Oplus_PowerUI", "ignore show dialog for MLBB version");
                        return;
                    }
                    OplusPowerUI oplusPowerUI = OplusPowerUI.this;
                    if (z) {
                        oplusPowerUI.mNotificationManager.notifyAsUser(null, 10006, new Notification.Builder(oplusPowerUI.mContext, "ALR").setExtras(bundleM).setSmallIcon(2131232662).setContentText(oplusPowerUI.mContext.getString(2131953533)).setVisibility(1).setCategory("sys").setAutoCancel(true).build(), UserHandle.ALL);
                        return;
                    }
                    COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(oplusPowerUI.mContext, 2132017612);
                    cOUIAlertDialogBuilder.setTitle(2131955255);
                    cOUIAlertDialogBuilder.setMessage(2131955253);
                    6 r9 = new 6();
                    r9.this$0 = oplusPowerUI;
                    VarHandle.storeStoreFence();
                    cOUIAlertDialogBuilder.setPositiveButton(2131955957, r9);
                    cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
                    AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
                    alertDialogCreate.setCanceledOnTouchOutside(true);
                    alertDialogCreate.getWindow().setType(2003);
                    alertDialogCreate.setCancelable(true);
                    alertDialogCreate.getWindow().setGravity(80);
                    alertDialogCreate.getWindow().getAttributes().privateFlags |= 16;
                    alertDialogCreate.show();
                    cOUIAlertDialogBuilder.updateViewAfterShown();
                    OplusPowerUI.volumeDialogFlag("OPLUS_AUDIO_SET_DIALOG:ON");
                    oplusPowerUI.mOverVolumDialog = alertDialogCreate;
                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "createVolumeDialog ");
                }
            }
        };
        this.mPowerReceiver = new PowerReceiver() { // from class: com.oplus.systemui.statusbar.notification.power.OplusPowerUI.4
            @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "action = " + action);
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                OplusPowerUI oplusPowerUI = OplusPowerUI.this;
                OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl = oplusPowerUI.mSuperSaveController;
                if (oplusSuperSaveControllerImpl != null) {
                    oplusSuperSaveControllerImpl.onReceiverChange(intent);
                }
                try {
                    if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                        OplusPowerUI.m443$$Nest$mhandleBatteryActionChange(oplusPowerUI, intent);
                        return;
                    }
                    if (!action.equals("android.intent.action.ACTION_POWER_CONNECTED") && !action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        if ("android.intent.action.CONFIGURATION_CHANGED".equals(action)) {
                            LowBatteryDialog lowBatteryDialog = oplusPowerUI.mWarnings.mLowBatteryDialog;
                            if (lowBatteryDialog == null || !lowBatteryDialog.isShowing()) {
                                return;
                            }
                            oplusPowerUI.mContext.getString(2131952241, Integer.valueOf(oplusPowerUI.mBatteryLevel));
                            oplusPowerUI.mWarnings.getClass();
                            return;
                        }
                        if (!action.equals("android.intent.action.BOOT_COMPLETED") && !"android.intent.action.ACTION_BOOT_IPO".equals(action)) {
                            if (action.equals("android.intent.action.ACTION_SHUTDOWN_IPO")) {
                                LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Intent android.intent.action.ACTION_SHUTDOWN_IPO mHideLowBDialog = " + oplusPowerUI.mHideLowBDialog);
                                oplusPowerUI.mHideLowBDialog = true;
                                oplusPowerUI.mBatteryLevel = 100;
                                return;
                            }
                            if (action.equals("android.intent.action.ADDITIONAL_BATTERY_CHANGED")) {
                                OplusPowerUI.m442$$Nest$mhandleAdditionBatteryChange(oplusPowerUI, intent);
                                return;
                            }
                            if (action.equals("android.intent.action.PHONE_STATE")) {
                                ThreadManager threadManager = ThreadManager.getInstance();
                                OplusPowerUI$$ExternalSyntheticLambda3 oplusPowerUI$$ExternalSyntheticLambda3 = new OplusPowerUI$$ExternalSyntheticLambda3();
                                oplusPowerUI$$ExternalSyntheticLambda3.f$0 = oplusPowerUI;
                                VarHandle.storeStoreFence();
                                threadManager.postInThread(oplusPowerUI$$ExternalSyntheticLambda3);
                                return;
                            }
                            if ("oplus.intent.action.CANCEL_NOTIFICAITON".equals(action)) {
                                NotificationManager notificationManager = oplusPowerUI.mNotificationManager;
                                if (notificationManager != null) {
                                    notificationManager.cancelAsUser(null, 10007, UserHandle.ALL);
                                    return;
                                }
                                return;
                            }
                            if ("android.intent.action.USER_UNLOCKED".equals(action)) {
                                oplusPowerUI.mLowBatteryWarningDelayHelper.startHoldDelay();
                                OplusPowerUI.m448$$Nest$mhandleUserUnlockedForNonOriginalBattery(oplusPowerUI);
                                return;
                            }
                            if (!LowBatteryWarningDelayHelper.ACTION_LOW_BATTERY_WARNING_HOLD.equals(action) && !LowBatteryWarningDelayHelper.ACTION_SUPER_POWER_READY_NOTIFY.equals(action)) {
                                if ("android.intent.action.ACTION_CLOSE_CHANGED_NOTIFY".equals(action)) {
                                    OplusPowerUI.m446$$Nest$mhandleUnStandardClose(oplusPowerUI);
                                    return;
                                } else {
                                    if ("android.intent.action.ACTION_DETAIL_CHANGED_NOTIFY".equals(action)) {
                                        OplusPowerUI.m447$$Nest$mhandleUnStandardDetails(oplusPowerUI);
                                        return;
                                    }
                                    LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "unknown intent: " + intent);
                                    return;
                                }
                            }
                            OplusPowerUI.m444$$Nest$mhandleLowBatteryActionChange(oplusPowerUI, action);
                            return;
                        }
                        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "Intetnt BOOT mHideLowBDialog = " + oplusPowerUI.mHideLowBDialog);
                        oplusPowerUI.mHideLowBDialog = false;
                        oplusPowerUI.mLowBatteryWarningDelayHelper.setHadBootCompleted(true);
                        if (oplusPowerUI.mLowBatteryWarningDelayHelper.getNeedShowDialog() && oplusPowerUI.mLowBatteryWarningDelayHelper.getWarningBeforeUnlock()) {
                            oplusPowerUI.playLowBatterySound();
                            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "handleBootCompeletedAction playLowBatterySound");
                        }
                        oplusPowerUI.checkNonOriginalBatteryOnBoot();
                        return;
                    }
                    OplusPowerUI.m445$$Nest$mhandlePowerConnectStateChange(oplusPowerUI, action, context2);
                } catch (Exception e) {
                    LogUtil.w(LogUtil.TAG_COMMON, "Oplus_PowerUI", "onReceive " + Log.getStackTraceString(e));
                    CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("onReceive "), LogUtil.TAG_COMMON, "Oplus_PowerUI");
                }
            }
        };
        this.mContext = context;
        this.mNotificationKeyguardHelper = notificationKeyguardHelper;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mKeyguardStateController = keyguardStateController;
        this.mUserTracker = userTracker;
        this.mExecutor = executor;
    }

    private void getIsBatteryOriginal() {
        OplusPowerUI$$ExternalSyntheticLambda0 oplusPowerUI$$ExternalSyntheticLambda0 = new OplusPowerUI$$ExternalSyntheticLambda0(0);
        oplusPowerUI$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(oplusPowerUI$$ExternalSyntheticLambda0);
    }

    public static void volumeDialogFlag(String str) {
        try {
            AudioManager audioManager = new AudioManager();
            audioManager.getClass().getMethod("setParameters", String.class).invoke(audioManager, str);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }

    public final void checkNonOriginalBatteryOnBoot() {
        if (!this.mIsBatteryOriginalChecked) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "checkNonOriginalBatteryOnBoot: battery original status not checked yet");
            return;
        }
        if (this.mIsBatteryOriginal) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "checkNonOriginalBatteryOnBoot: is original battery, skip");
            return;
        }
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "is_non_original_battery_dialog_never_remind", 0) != 0) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "checkNonOriginalBatteryOnBoot: user set never mind, skip");
            return;
        }
        boolean zIsCurrentlyPlugged = isCurrentlyPlugged();
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI", RowView$.ExternalSyntheticOutline0.m("checkNonOriginalBatteryOnBoot: isPlugged=", ", mIsBatteryOriginal=", zIsCurrentlyPlugged), this.mIsBatteryOriginal);
        if (zIsCurrentlyPlugged) {
            this.mNeedShowNonOriginalDialogOnUnlock = true;
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "checkNonOriginalBatteryOnBoot: set mNeedShowNonOriginalDialogOnUnlock=true");
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (strArr.length != 0 && strArr.length != 1) {
            if (!strArr[1].equals("ShowChargeErrorDialg") || strArr.length <= 2) {
                if (!strArr[1].equals("showInvalidChargerDialog")) {
                    printWriter.println("show charge error dialog argument error");
                    return;
                } else {
                    this.mWarnings.showInvalidChargerDialog(this.mContext);
                    printWriter.println("show charge invalid dialog!");
                    return;
                }
            }
            int i = Integer.parseInt(strArr[2]);
            this.mWarnings.showChargeErrorDialog(i);
            printWriter.println("show charge error dialog type " + i);
            return;
        }
        printWriter.print("mLowBatteryAlertCloseLevel=");
        printWriter.println(this.mLowBatteryAlertCloseLevel);
        printWriter.print("mLowBatteryReminderLevels=");
        printWriter.println(Arrays.toString(this.mLowBatteryReminderLevels));
        printWriter.print("mInvalidChargerDialog=");
        AlertDialog alertDialog = this.mWarnings.mInvalidChargerDialog;
        if (alertDialog == null) {
            printWriter.println("null");
        } else {
            printWriter.println(alertDialog.toString());
        }
        printWriter.print("mLowBatteryDialog=");
        LowBatteryDialog lowBatteryDialog = this.mWarnings.mLowBatteryDialog;
        if (lowBatteryDialog == null) {
            printWriter.println("null");
        } else {
            printWriter.println(lowBatteryDialog.toString());
        }
        printWriter.print("mBatteryLevel=");
        printWriter.println(Integer.toString(this.mBatteryLevel));
        printWriter.print("mBatteryStatus=");
        printWriter.println(Integer.toString(this.mBatteryStatus));
        printWriter.print("mPlugType=");
        printWriter.println(Integer.toString(this.mPlugType));
        printWriter.print("mInvalidCharger=");
        printWriter.println(Integer.toString(this.mInvalidCharger));
        printWriter.print("bucket: ");
        printWriter.println(Integer.toString(findBatteryLevelBucket$1(this.mBatteryLevel)));
    }

    public final int findBatteryLevelBucket$1(int i) {
        if (i >= this.mLowBatteryAlertCloseLevel) {
            return 1;
        }
        int[] iArr = this.mLowBatteryReminderLevels;
        if (i > iArr[0]) {
            return 0;
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (i <= iArr[length]) {
                return (-1) - length;
            }
        }
        a$.ExternalSyntheticBUOutline0.m("not possible!");
        return 0;
    }

    public int getBatteryLevel() {
        return this.mBatteryLevel;
    }

    public final void initScreenSize(Configuration configuration) {
        this.mScreenWidth = configuration.screenWidthDp;
        this.mScreenHeight = configuration.screenHeightDp;
        if (LogUtil.getNormalDebug()) {
            StringBuilder sb = new StringBuilder("initScreenSize mScreenWdith * mScreenHeight :");
            sb.append(this.mScreenWidth);
            sb.append("*");
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mScreenHeight, LogUtil.TAG_COMMON, "Oplus_PowerUI", sb);
        }
    }

    public final boolean isCurrentlyPlugged() {
        try {
            Intent intentRegisterReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return (intentRegisterReceiver == null || intentRegisterReceiver.getIntExtra("plugged", 0) == 0) ? false : true;
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("isCurrentlyPlugged error: "), LogUtil.TAG_COMMON, "Oplus_PowerUI");
        }
        return false;
    }

    public final void onConfigChanged(Configuration configuration) {
        int i = configuration.orientation;
        if (LogUtil.getNormalDebug()) {
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mCurrentOrientation, LogUtil.TAG_COMMON, "Oplus_PowerUI", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onConfigurationChanged newOrientation = ", " mCurrentOrientation = "));
        }
        if (this.mCurrentOrientation != i || this.mScreenWidth != configuration.screenWidthDp || this.mScreenHeight != configuration.screenHeightDp) {
            this.mCurrentOrientation = i;
            LowBatteryDialog lowBatteryDialog = this.mWarnings.mLowBatteryDialog;
            if (lowBatteryDialog != null && lowBatteryDialog.isShowing()) {
                this.mWarnings.mLowBatteryDialog.dismiss();
                LowBatteryUiController.getInstance().dismissBatteryWarningOnSecondDisplay();
                this.mWarnings.showLowBatteryWarning(this.mWarningLevel, false, this.mBatteryLevel, this.mIsLowBatteryFistBoot, this.mHandler);
            }
        }
        initScreenSize(configuration);
        int i2 = configuration.uiMode & 48;
        if (this.mUiMode != i2) {
            this.mUiMode = i2;
            onUiModeChanged();
        }
    }

    public final void onUiModeChanged() {
        LowBatteryDialog lowBatteryDialog = this.mWarnings.mLowBatteryDialog;
        if (lowBatteryDialog != null && lowBatteryDialog.isShowing()) {
            this.mWarnings.mLowBatteryDialog.dismiss();
            LowBatteryUiController.getInstance().dismissBatteryWarningOnSecondDisplay();
            this.mWarnings.showLowBatteryWarning(this.mWarningLevel, false, this.mBatteryLevel, this.mIsLowBatteryFistBoot, this.mHandler);
        }
        AlertDialog alertDialog = this.mNonOriginalDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        AlertDialog alertDialog2 = this.mNonOriginalDialog;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
            this.mNonOriginalDialog = null;
        }
        showNonOriginalDialog();
    }

    public final void playLowBatterySound() {
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "playing low battery sound. WOMP-WOMP!");
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isLowBatteryNoSound()) {
            return;
        }
        if (FeatureOption.isCmccCustomizedVersion() && SystemClock.elapsedRealtime() < 50000) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "system bootup. no playing low battery sound. time=" + SystemClock.elapsedRealtime());
        } else {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "playLowBatterySound");
            Message messageObtain = Message.obtain();
            messageObtain.what = 10;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public final void powerConnectStateChange(int i) {
        boolean zIsDefaultKeyguardInSettings = KeyguardUtils.isDefaultKeyguardInSettings(this.mContext);
        NotificationKeyguardHelper notificationKeyguardHelper = this.mNotificationKeyguardHelper;
        if (notificationKeyguardHelper != null) {
            NotificationKeyguardHelper$$ExternalSyntheticLambda0 notificationKeyguardHelper$$ExternalSyntheticLambda0 = notificationKeyguardHelper.mResetChargeStateRunnable;
            StatusBarStateController statusBarStateController = notificationKeyguardHelper.mStatusBarStateController;
            if (!zIsDefaultKeyguardInSettings) {
                notificationKeyguardHelper.resetNotificationForCharge();
                return;
            }
            if (i != 0) {
                notificationKeyguardHelper.resetNotificationForCharge();
                return;
            }
            Handler handler = notificationKeyguardHelper.mMainHandler;
            ChargingFeatureOption.INSTANCE.getClass();
            if (ChargingFeatureOption.isSupportFullscreenChargeAnim()) {
                return;
            }
            StringBuilder sb = new StringBuilder("hideNotificationForCharge  !mHideForCharge ");
            sb.append(!notificationKeyguardHelper.mHideForCharge);
            sb.append(" getState ");
            sb.append(statusBarStateController.getState());
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationKeyguardHel", sb.toString());
            int state = statusBarStateController.getState();
            if ((state == 1 || state == 2) && !notificationKeyguardHelper.mHideForCharge) {
                notificationKeyguardHelper.mHideForCharge = true;
                notificationKeyguardHelper.mNotifFilter.invalidateList("updateNotificationsNoneFilter");
                if (handler.hasCallbacks(notificationKeyguardHelper$$ExternalSyntheticLambda0)) {
                    handler.removeCallbacks(notificationKeyguardHelper$$ExternalSyntheticLambda0);
                }
            }
        }
    }

    public final void showNonOriginalDialog() {
        COUISecurityAlertDialogBuilder cOUISecurityAlertDialogBuilder = new COUISecurityAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.mContext));
        cOUISecurityAlertDialogBuilder.setTitle(2131954804);
        String string = cOUISecurityAlertDialogBuilder.mContext.getString(2131954803);
        if (TextUtils.isEmpty(string) || !(string.contains("%1$s") || string.contains("%s"))) {
            cOUISecurityAlertDialogBuilder.mStatementId = -1;
        } else {
            cOUISecurityAlertDialogBuilder.mStatementId = 2131954803;
        }
        cOUISecurityAlertDialogBuilder.mLinkId = 2131954802;
        cOUISecurityAlertDialogBuilder.setShowStatementText(true);
        cOUISecurityAlertDialogBuilder.setCheckBoxString(2131954801);
        cOUISecurityAlertDialogBuilder.setChecked(false);
        cOUISecurityAlertDialogBuilder.setPositiveString(2131954800);
        cOUISecurityAlertDialogBuilder.setWindowType(2003);
        OplusPowerUI$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusPowerUI$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        cOUISecurityAlertDialogBuilder.setOnLinkTextClickListener(externalSyntheticLambda1);
        cOUISecurityAlertDialogBuilder.setBlurBackgroundDrawable(true);
        OplusPowerUI$.ExternalSyntheticLambda1 externalSyntheticLambda2 = new OplusPowerUI$.ExternalSyntheticLambda1();
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        cOUISecurityAlertDialogBuilder.setOnSelectedListener(externalSyntheticLambda2);
        this.mNonOriginalDialog = cOUISecurityAlertDialogBuilder.show();
    }

    public final void start() {
        Context context = this.mContext;
        if (OtgHelper.sInstance == null) {
            synchronized (OtgHelper.class) {
                try {
                    if (OtgHelper.sInstance == null) {
                        OtgHelper.sInstance = new OtgHelper(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.mOtgHelper = OtgHelper.sInstance;
        this.mNotificationKeyguardHelper.addFinalizeFilter();
        this.mLowBatteryAlertCloseLevel = this.mContext.getResources().getInteger(2131427398);
        this.mLowBatteryReminderLevels[0] = this.mContext.getResources().getInteger(2131427399);
        this.mLowBatteryReminderLevels[1] = this.mContext.getResources().getInteger(2131427421);
        this.mLowBatteryReminderLevels[2] = this.mContext.getResources().getInteger(2131427710);
        this.mLowBatteryReminderLevels[3] = this.mContext.getResources().getInteger(2131427376);
        this.mIsLowBatteryFistBoot = true;
        this.mHandler.removeMessages(12);
        this.mHandler.sendEmptyMessageDelayed(12, 10000L);
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        }
        this.mHideLowBDialog = !ActivityManagerNative.isSystemReady();
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "Oplus_PowerUI", new StringBuilder("start mHideLowBDialog :"), this.mHideLowBDialog);
        register(this.mContext, this.mHandler);
        AnonymousClass2 anonymousClass2 = this.mCustomPowerReceiver;
        Context context2 = this.mContext;
        UserHandle userHandle = UserHandle.ALL;
        anonymousClass2.register(context2, userHandle, this.mHandler);
        ((BatteryController) DependencyEx.sDependency.getDependency(BatteryController.class)).addCallback(this.mBatteryChangeCallback);
        register(this.mContext, userHandle, true);
        this.mWarnings = OplusPowerNotificationWarnings.getInstance(this.mContext);
        boolean zEquals = "1".equals(SystemProperties.get("sys.boot_completed"));
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(" isSystemReboot = ", LogUtil.TAG_COMMON, "Oplus_PowerUI", !zEquals);
        LowBatteryWarningDelayHelper companion = LowBatteryWarningDelayHelper.Companion.getInstance(this.mContext);
        this.mLowBatteryWarningDelayHelper = companion;
        if (companion != null && !zEquals) {
            companion.holdLowBatteryWarning = true;
            companion.beforeUnlock = true;
        }
        Configuration configuration = this.mContext.getResources().getConfiguration();
        if (configuration != null) {
            this.mCurrentOrientation = configuration.orientation;
            initScreenSize(configuration);
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mCurrentOrientation, LogUtil.TAG_COMMON, "Oplus_PowerUI", new StringBuilder("mCurrentOrientation: "));
        }
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mInfoCallback = anonymousClass5;
        if (keyguardUpdateMonitor != null) {
            keyguardUpdateMonitor.registerCallback(anonymousClass5);
        }
        OplusPowerUISoundUtil oplusPowerUISoundUtil = new OplusPowerUISoundUtil(this.mContext, this.mBgHandler);
        this.mPowerUISoundUtil = oplusPowerUISoundUtil;
        oplusPowerUISoundUtil.init();
        if (CustomizeFeatureOption.sIsSupportMode) {
            this.mSuperSaveController = new OplusSuperSaveControllerImpl(this.mContext, this.mUserTracker, this.mExecutor, this.mKeyguardStateController, this.mPowerUISoundUtil, this.mDeviceProvisionedController);
        }
        getIsBatteryOriginal();
    }
}
