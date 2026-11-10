package com.oplus.systemui.statusbar.notification.helper;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.service.notification.ScheduleCalendar;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import androidx.slice.widget.RowView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.DndInfoUtil;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.common.utils.ValidRuleInfo;
import java.lang.invoke.VarHandle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class DndAlertHelper implements ZenModeController.Callback {
    public ZenModeConfig mConfig;
    public final Context mContext;
    public final AnonymousClass2 mDndReceiver;
    public final Handler mHandler;
    public final AnonymousClass1 mMultiUserListener;
    public final NotificationManager mNotificationManager;
    public int mZenMode;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.DndAlertHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IMultiUserListener {
        public /* synthetic */ DndAlertHelper this$0;

        @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
        public final void onSwitched(int i) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "DndAlertHelper", "on user switched update dnd settings");
            DndAlertHelper dndAlertHelper = this.this$0;
            dndAlertHelper.mConfig = dndAlertHelper.mNotificationManager.getZenModeConfig();
            int zenMode = dndAlertHelper.mNotificationManager.getZenMode();
            dndAlertHelper.mZenMode = zenMode;
            dndAlertHelper.operateNotification(zenMode, dndAlertHelper.getZenEndTime(zenMode, dndAlertHelper.mConfig));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.DndAlertHelper$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Runnable {
        public /* synthetic */ DndAlertHelper this$0;

        @Override // java.lang.Runnable
        public final void run() {
            DndAlertHelper dndAlertHelper = this.this$0;
            long zenEndTime = dndAlertHelper.getZenEndTime(dndAlertHelper.mZenMode, dndAlertHelper.mConfig);
            DndAlertHelper dndAlertHelper2 = this.this$0;
            dndAlertHelper2.operateNotification(dndAlertHelper2.mZenMode, zenEndTime);
        }
    }

    public DndAlertHelper(Context context, ZenModeController zenModeController) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.notification.helper.DndAlertHelper.2
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if (intent != null) {
                    String action = intent.getAction();
                    LogUtil.i(LogUtil.TAG_QS, "DndAlertHelper", "mDndReceiver.onReceive: action is " + action);
                    if ("action_click_close_dnd".equals(action)) {
                        DndAlertHelper.this.mNotificationManager.setZenMode(0, null, "DndAlertHelper");
                    } else if ("action_agree_dnd".equals(action)) {
                        DndAlertHelper.this.mNotificationManager.setZenMode(1, null, "DndAlertHelper");
                    }
                }
            }
        };
        this.mContext = context;
        this.mHandler = new Handler();
        IntentFilter intentFilter = new IntentFilter("action_click_close_dnd");
        intentFilter.addAction("action_agree_dnd");
        ((BroadcastDispatcher) DependencyEx.sDependency.getDependency(BroadcastDispatcher.class)).registerReceiver(broadcastReceiver, intentFilter, (Executor) null, UserHandle.ALL, 2, ProviderUtil.OPLUS_COMPONENT_SAFE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        this.mNotificationManager = notificationManager;
        ((ZenModeControllerImpl) zenModeController).addCallback(this);
        this.mConfig = notificationManager.getZenModeConfig();
        int zenMode = notificationManager.getZenMode();
        this.mZenMode = zenMode;
        operateNotification(zenMode, getZenEndTime(zenMode, this.mConfig));
        MultiUserManager.getInstance().addListener(anonymousClass1);
    }

    public final String formatRemainingTimeWithCalendar(long j, String str) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        Calendar calendar3 = (Calendar) calendar.clone();
        calendar3.set(11, 0);
        calendar3.set(12, 0);
        calendar3.set(13, 0);
        calendar3.set(14, 0);
        Calendar calendar4 = (Calendar) calendar2.clone();
        calendar4.set(11, 0);
        calendar4.set(12, 0);
        calendar4.set(13, 0);
        calendar4.set(14, 0);
        long timeInMillis = (calendar4.getTimeInMillis() - calendar3.getTimeInMillis()) / 86400000;
        Context context = this.mContext;
        if (timeInMillis == 0) {
            DndInfoUtil dndInfoUtil = DndInfoUtil.getInstance();
            Context context2 = this.mContext;
            long time = calendar2.getTime().getTime();
            dndInfoUtil.getClass();
            return context.getString(2131953640, DndInfoUtil.getFormattedTime12or24(context2, time, false), str);
        }
        if (timeInMillis == 1) {
            DndInfoUtil dndInfoUtil2 = DndInfoUtil.getInstance();
            Context context3 = this.mContext;
            long time2 = calendar2.getTime().getTime();
            dndInfoUtil2.getClass();
            return context.getString(2131953641, DndInfoUtil.getFormattedTime12or24(context3, time2, false), str);
        }
        DndInfoUtil dndInfoUtil3 = DndInfoUtil.getInstance();
        Context context4 = this.mContext;
        long time3 = calendar2.getTime().getTime();
        dndInfoUtil3.getClass();
        return context.getString(2131953640, DndInfoUtil.getFormattedTime12or24(context4, time3, true), str);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0028  */
    public String getNotificationContent() {
        String appNameByPkgName;
        ZenModeConfig.ZenRule zenRule;
        ZenModeConfig.ZenRule zenRule2;
        DndInfoUtil dndInfoUtil = DndInfoUtil.getInstance();
        Context context = this.mContext;
        ZenModeConfig zenModeConfig = this.mConfig;
        dndInfoUtil.getClass();
        String string = "";
        if (zenModeConfig == null || (zenRule2 = zenModeConfig.manualRule) == null) {
            appNameByPkgName = "";
        } else {
            String str = zenRule2.enabler;
            if (TextUtils.isEmpty(str) || AppInfoUtil.BLACK_DND_PACKAGES.contains(str)) {
                appNameByPkgName = "";
            } else {
                appNameByPkgName = AppInfoUtil.getAppNameByPkgName(context, str);
            }
        }
        if (TextUtils.isEmpty(appNameByPkgName)) {
            ZenModeConfig zenModeConfig2 = this.mConfig;
            if (zenModeConfig2 != null && (zenRule = zenModeConfig2.manualRule) != null) {
                Uri uri = zenRule.conditionId;
                ValidRuleInfo validRule = DndInfoUtil.getValidRule(zenModeConfig2);
                if (uri == null || Uri.EMPTY.equals(uri)) {
                    string = ("zen_mode_manual_duration".equals(this.mConfig.manualRule.name) || validRule == null) ? this.mContext.getString(2131953639) : formatRemainingTimeWithCalendar(validRule.endTime, validRule.name);
                } else {
                    Context context2 = this.mContext;
                    string = context2.getString(2131957441, dndInfoUtil.getFormatEndTime(context2, dndInfoUtil.mEndTime));
                }
            }
        } else {
            string = this.mContext.getString(2131957473, appNameByPkgName);
        }
        return (TextUtils.isEmpty(dndInfoUtil.getFormatEndTime(this.mContext, dndInfoUtil.mEndTime)) || !dndInfoUtil.mIsAutomaticActive) ? string : this.mContext.getString(2131952112, dndInfoUtil.getAutomaticRuleTitle(), dndInfoUtil.getFormatEndTime(this.mContext, dndInfoUtil.mEndTime));
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0039  */
    public final long getZenEndTime(int i, ZenModeConfig zenModeConfig) {
        long jTryParseCountdownConditionId;
        long j;
        long nextChangeTime;
        long triggerTime;
        long j2 = 0;
        if (i == 0 || zenModeConfig == null) {
            return 0L;
        }
        if (zenModeConfig.manualRule != null) {
            DndInfoUtil dndInfoUtil = DndInfoUtil.getInstance();
            dndInfoUtil.mIsAutomaticActive = false;
            dndInfoUtil.mEndTime = 0L;
            Uri uri = zenModeConfig.manualRule.conditionId;
            if (uri == null || Uri.EMPTY.equals(uri)) {
                jTryParseCountdownConditionId = -1;
            } else {
                jTryParseCountdownConditionId = ZenModeConfig.tryParseCountdownConditionId(uri);
                DndInfoUtil dndInfoUtil2 = DndInfoUtil.getInstance();
                dndInfoUtil2.mIsAutomaticActive = false;
                dndInfoUtil2.mEndTime = jTryParseCountdownConditionId;
                if (jTryParseCountdownConditionId == 0) {
                    return jTryParseCountdownConditionId;
                }
            }
        } else {
            jTryParseCountdownConditionId = -1;
        }
        for (ZenModeConfig.ZenRule zenRule : zenModeConfig.automaticRules.values()) {
            if (zenRule.isActive() && ZenModeConfig.isValidScheduleConditionId(zenRule.conditionId)) {
                Context context = this.mContext;
                Uri uri2 = zenRule.conditionId;
                if (ZenModeConfig.isValidScheduleConditionId(uri2)) {
                    ScheduleCalendar scheduleCalendar = ZenModeConfig.toScheduleCalendar(uri2);
                    nextChangeTime = scheduleCalendar.getNextChangeTime(System.currentTimeMillis());
                    if (scheduleCalendar.exitAtAlarm()) {
                        AlarmManager.AlarmClockInfo nextAlarmClock = ((AlarmManager) context.getSystemService("alarm")).getNextAlarmClock(context.getUserId());
                        if (nextAlarmClock != null) {
                            triggerTime = nextAlarmClock.getTriggerTime();
                            j = j2;
                        } else {
                            triggerTime = j2;
                            j = triggerTime;
                        }
                        scheduleCalendar.maybeSetNextAlarm(System.currentTimeMillis(), triggerTime);
                        if (scheduleCalendar.shouldExitForAlarm(nextChangeTime)) {
                            nextChangeTime = triggerTime;
                        }
                    } else {
                        j = j2;
                    }
                } else {
                    j = j2;
                    nextChangeTime = -1;
                }
                if (nextChangeTime > jTryParseCountdownConditionId) {
                    String strEmptyIfNull = TextUtils.emptyIfNull(zenRule.name);
                    DndInfoUtil dndInfoUtil3 = DndInfoUtil.getInstance();
                    dndInfoUtil3.mAutomaticRuleTitle = strEmptyIfNull;
                    dndInfoUtil3.mIsAutomaticActive = true;
                    dndInfoUtil3.mEndTime = nextChangeTime;
                    jTryParseCountdownConditionId = nextChangeTime;
                }
            } else {
                j = j2;
            }
            j2 = j;
        }
        long j3 = j2;
        return jTryParseCountdownConditionId > j3 ? jTryParseCountdownConditionId : j3;
    }

    public int getZenMode() {
        return this.mZenMode;
    }

    public final boolean isDNDNoticeChannelNotification(NotificationEntry notificationEntry) {
        boolean z = false;
        if (notificationEntry != null && notificationEntry.getSbn() != null && notificationEntry.getSbn().getNotification() != null) {
            if (this.mContext.getPackageName().equals(notificationEntry.getSbn().getPackageName()) && "channel_dnd_notice".equals(notificationEntry.getSbn().getNotification().getChannelId())) {
                z = true;
            }
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("Notification--isDNDNoticeChannelNotification:", ",notification:", z);
            sbM.append(notificationEntry.getEx().getEncryptionKey());
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "DndAlertHelper", sbM.toString());
        }
        return z;
    }

    public final void onConfigChanged(ZenModeConfig zenModeConfig) {
        this.mConfig = zenModeConfig;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mHandler.postDelayed(anonymousClass3, 100L);
    }

    public final void onZenChanged(int i) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, " zen = ", LogUtil.TAG_NOTIFICATION, "DndAlertHelper");
        operateNotification(i, getZenEndTime(i, this.mConfig));
        this.mZenMode = i;
        Context context = this.mContext;
        String strValueOf = String.valueOf(i);
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        HashMap map = new HashMap();
        map.put("zen_status", strValueOf);
        OplusTrackProxyUtil.onCommon(context, "20082", "event_zen_mode_status", map);
    }

    public final void operateNotification(int i, long j) {
        if (i == 0 || "com.oplus.engineermode".equals(Util.getCurrentTopAppPackageName())) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "DndAlertHelper", " cancelNotification");
            this.mNotificationManager.cancelAsUser(null, 10001, UserHandle.CURRENT);
            return;
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "DndAlertHelper", "sendNotification : endtime = " + j);
        String string = this.mContext.getString(2131953639);
        String notificationContent = getNotificationContent();
        if (!TextUtils.isEmpty(notificationContent)) {
            string = notificationContent;
        }
        Intent intent = new Intent("android.settings.ZEN_MODE_SETTINGS");
        intent.setPackage("com.android.settings");
        intent.addFlags(67108864);
        Context context = this.mContext;
        UserHandle userHandle = UserHandle.CURRENT;
        PendingIntent activityAsUser = PendingIntent.getActivityAsUser(context, 101, intent, 201326592, null, userHandle);
        String string2 = this.mContext.getString(2131953073);
        Intent intent2 = new Intent("action_click_close_dnd");
        intent2.setPackage(this.mContext.getPackageName());
        Notification.Action actionBuild = new Notification.Action.Builder((Icon) null, string2, PendingIntent.getBroadcast(this.mContext, 102, intent2, 201326592)).build();
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", this.mContext.getString(2131956559));
        Notification.Builder contentText = new Notification.Builder(this.mContext, "channel_dnd_notice").addAction(actionBuild).setContentIntent(activityAsUser).setExtras(bundle).setSmallIcon(2131237617).setContentTitle(this.mContext.getString(2131953074)).setVisibility(1).setStyle(new Notification.BigTextStyle()).setContentText(string);
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            notificationManager.notifyAsUser(null, 10001, contentText.build(), userHandle);
        }
    }
}
