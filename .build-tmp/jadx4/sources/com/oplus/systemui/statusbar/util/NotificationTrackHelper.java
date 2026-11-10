package com.oplus.systemui.statusbar.util;

import android.app.INotificationManager;
import android.app.OplusNotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.ServiceManager;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.notification.collection.brickmode.BrickModeHelper;
import com.oplus.systemui.notification.collection.sleepmode.SleepModeHelper;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.statusbar.notification.helper.SmartDriverHelper;
import com.oplus.systemui.statusbar.observer.FilterNotificationTrackObserver;
import com.oplus.systemui.statusbar.observer.GameModeObserver;
import com.oplus.systemui.statusbar.observer.NotificationTrackControllerObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTrackHelper {
    private static final String STATE_APP_NOTIFICATIONS_OFF = "7";
    private static final String STATE_APP_VIBRATION_PERMISSION_GRANTED = "6";
    private static final String STATE_BRICK_MODE = "2";
    private static final String STATE_CHANNEL_VIBRATOR_OFF = "5";
    private static final String STATE_DRIVING_MODE = "3";
    private static final String STATE_GAME_MODE = "1";
    private static final String STATE_RINGER_MODE_SILENT = "8";
    private static final String STATE_SLEEP_MODE = "4";
    private static final String STATE_ZEN_MODE = "0";
    private static final String TAG = "NotificationTrackHelper";
    private AudioManager audioManager;
    private ArrayList<String> backList;
    private Context context;
    private GameModeObserver gameModeObserver;
    private Handler handler;
    private final INotificationManager mINM = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    private OplusNotificationManager manager;
    private SmartDriverHelper smartDriverHelper;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new NotificationTrackHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final NotificationTrackHelper getInstance() {
            return (NotificationTrackHelper) NotificationTrackHelper.instance$delegate.getValue();
        }
    }

    public static final NotificationTrackHelper getInstance() {
        return Companion.getInstance();
    }

    public final String getSystemState(StatusBarNotification statusBarNotification, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        int uid = statusBarNotification.getUid();
        String packageName = statusBarNotification.getPackageName();
        if (z) {
            sb.append("0");
        }
        GameModeObserver gameModeObserver = this.gameModeObserver;
        if (gameModeObserver != null && gameModeObserver.isInGameMode) {
            sb.append("|1");
        }
        if (BrickModeHelper.Companion.getInstance().isBrickMode) {
            sb.append("|2");
        }
        SmartDriverHelper smartDriverHelper = this.smartDriverHelper;
        if (smartDriverHelper != null && smartDriverHelper.isNavigationMode()) {
            sb.append("|3");
        }
        if (SleepModeHelper.Companion.getInstance().isSleepMode) {
            sb.append("|4");
        }
        if (!z2) {
            sb.append("|5");
        }
        OplusNotificationManager oplusNotificationManager = this.manager;
        if (oplusNotificationManager != null && !oplusNotificationManager.isAppVibrationPermissionGranted(packageName, uid)) {
            sb.append("|6");
        }
        if (!this.mINM.areNotificationsEnabledForPackage(packageName, uid)) {
            sb.append("|7");
        }
        AudioManager audioManager = this.audioManager;
        if (audioManager != null && audioManager.getRingerModeInternal() == 0) {
            sb.append("|8");
        }
        if (StringsKt__StringsKt.startsWith(sb, "|", false)) {
            sb.replace(0, 1, "");
        }
        return sb.toString();
    }

    public final void init(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        GameModeObserver gameModeObserver = new GameModeObserver();
        this.gameModeObserver = gameModeObserver;
        gameModeObserver.register(context, false);
        this.manager = new OplusNotificationManager();
        this.smartDriverHelper = (SmartDriverHelper) ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getHelper();
    }

    public final boolean isFilterTrackEvent(Context context, StatusBarNotification statusBarNotification) {
        if (!FilterNotificationTrackObserver.Companion.getInstance().getMFilterNotificationTrackStatus()) {
            return false;
        }
        if (MCSUtil.isMCSPkg(statusBarNotification)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "NotificationTrackHelper-->isFilterTrackEvent isMCSPkg");
            }
            return true;
        }
        ArrayList<String> arrayList = this.backList;
        if (arrayList == null || arrayList.isEmpty()) {
            this.backList = new ArrayList<>(ArraysKt___ArraysKt.toList(context.getResources().getStringArray(2130903340)));
        }
        ArrayList<String> arrayList2 = this.backList;
        if (arrayList2 == null || arrayList2.isEmpty() || !arrayList2.contains(statusBarNotification.getPackageName())) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "NotificationTrackHelper-->isFilterTrackEvent by backList");
        }
        return true;
    }

    public final boolean isReportTrackEvent(StatusBarNotification statusBarNotification) {
        int notificationTrackControllerValue;
        int notificationTrackControllerValue2;
        int i = statusBarNotification.getNotification().extras != null ? statusBarNotification.getNotification().extras.getInt("is_commercial", 0) : 0;
        if (!MCSUtil.isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationTrackHelper-->local notification");
            }
            i = 0;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("isCommercial: ", i, NotificationTrackControllerObserver.Companion.getInstance().getNotificationTrackControllerValue(), " mNotificationTrackControllerValue : ", "NotificationTrackHelper-->"));
        }
        NotificationTrackControllerObserver.Companion companion = NotificationTrackControllerObserver.Companion;
        if (companion.getInstance().getNotificationTrackControllerValue() == -1) {
            return false;
        }
        if (i == 1 && ((notificationTrackControllerValue2 = companion.getInstance().getNotificationTrackControllerValue()) == 0 || notificationTrackControllerValue2 == 1)) {
            return true;
        }
        return i == 0 && ((notificationTrackControllerValue = companion.getInstance().getNotificationTrackControllerValue()) == 0 || notificationTrackControllerValue == 2);
    }
}
