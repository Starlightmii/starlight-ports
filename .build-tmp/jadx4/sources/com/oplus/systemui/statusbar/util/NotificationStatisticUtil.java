package com.oplus.systemui.statusbar.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.android.systemui.DependencyEx;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.compatui.OplusCompatDcsUtils$;
import com.oplus.dispatch.OCDHandler;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.notification.log.OplusNotifiEventLog;
import com.oplus.systemui.notification.statistic.NotificationTrackerLinkConfig;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController;
import com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl;
import com.oplus.systemui.statusbar.seeding.livealert.LiveAlertService;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.utrace.lib.HLogConst;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;
import java.lang.invoke.VarHandle;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationStatisticUtil {
    public static String sDownExpansionTime;
    public static String sUpCollapseTime;
    public static final Lock NOTIFICATION_LOCK = new ReentrantLock();
    public static long sUploadTime = 0;
    public static int sReasonWhenRemoving = -1;
    public static long sSnoozedTimeWhenRemoving = -1;
    public static final Map sNotifications = new HashMap();
    public static final Map sPostSliderTimes = new HashMap();
    public static final Map sShowNotificationIsReports = new HashMap();
    public static final List sShownNotifications = new ArrayList();
    public static final List sShownResidentNotifications = new ArrayList();
    public static boolean sIsSwipeLeftToShowMenu = false;
    public static final NotificationCenterHelper sHelper = (NotificationCenterHelper) DependencyEx.sDependency.getDependency(NotificationCenterHelper.class);
    public static final Vector sLabelNotificationKeyCollection = new Vector();
    public static final Vector sBackgroundNotificationKeyCollection = new Vector();
    public static final List sNotificationShowCache = new ArrayList();
    public static final List sNotificationPostCache = new ArrayList();
    public static final List sNotificationRemoveCache = new ArrayList();
    public static final Map sAddNotExposeNotifications = new HashMap();
    public static final UpdateTask sUpdateTask = new UpdateTask();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UpdateTask implements Runnable {
        public Context mContext;

        @Override // java.lang.Runnable
        public final void run() {
            Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
            ((ReentrantLock) lock).lock();
            try {
                StringBuilder sb = new StringBuilder("report:notification shown->");
                List list = NotificationStatisticUtil.sNotificationShowCache;
                sb.append(list);
                sb.append(HLogConst.KEY_SIZE);
                sb.append(((ArrayList) list).size());
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", sb.toString());
                NotificationStatisticUtil.reportNotificationShowImmediately(this.mContext);
                this.mContext = null;
                ((ReentrantLock) lock).unlock();
            } catch (Exception unused) {
                this.mContext = null;
                ((ReentrantLock) NotificationStatisticUtil.NOTIFICATION_LOCK).unlock();
            } catch (Throwable th) {
                this.mContext = null;
                ((ReentrantLock) NotificationStatisticUtil.NOTIFICATION_LOCK).unlock();
                throw th;
            }
        }

        public void setContext(Context context) {
            this.mContext = context.getApplicationContext();
        }
    }

    public static Unit $r8$lambda$UBJCRiHHuFjWo5qiqI78a596UXw(Context context, StatusBarNotification statusBarNotification, int i, boolean z, long j) {
        if (statusBarNotification != null) {
            String strShouldReject = shouldReject(statusBarNotification, z);
            if (MCSUtil.isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg()) || strShouldReject.isEmpty()) {
                if (isResidentNotification(statusBarNotification)) {
                    sShownResidentNotifications.add(getNotificationKey(statusBarNotification));
                }
                String strConvertSbState2Location = convertSbState2Location(i);
                if (strConvertSbState2Location.equals("notification_bar") || strConvertSbState2Location.equals(SeparateQSStatisicUtil.VALUE_KEYGUARD)) {
                    sShownNotifications.add(getNotificationKey(statusBarNotification));
                }
                Map<String, String> shownNotificationData = getShownNotificationData(context, statusBarNotification, i, j);
                String appNameByPkgName = AppInfoUtil.getAppNameByPkgName(context, statusBarNotification.getPackageName());
                boolean zIsReportTrackEvent = NotificationTrackHelper.getInstance().isReportTrackEvent(statusBarNotification);
                sShowNotificationIsReports.put(getNotificationKey(statusBarNotification), String.valueOf(zIsReportTrackEvent));
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "report:notification shown->" + shownNotificationData + ", state: " + i + ", appName: " + appNameByPkgName + ", isReport:" + zIsReportTrackEvent);
                if (zIsReportTrackEvent) {
                    ReentrantLock reentrantLock = (ReentrantLock) NOTIFICATION_LOCK;
                    reentrantLock.lock();
                    try {
                        String notificationKey = getNotificationKey(statusBarNotification);
                        if (!TextUtils.isEmpty(notificationKey)) {
                            sAddNotExposeNotifications.remove(notificationKey);
                        }
                        if (NotificationUtil.isMscTopNotification(shownNotificationData.get("is_top"), statusBarNotification)) {
                            onNotificationExposeRealtime(context, shownNotificationData);
                        } else {
                            List list = sNotificationShowCache;
                            list.add(shownNotificationData);
                            if (((ArrayList) list).size() == 10) {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "report:notification shown->" + list + HLogConst.KEY_SIZE + ((ArrayList) list).size() + ", reportNotificationShowImmediately");
                                SystemUIThreadUtil.sWorkHandler.removeCallbacks(sUpdateTask);
                                reportNotificationShowImmediately(context);
                            } else {
                                postNotificationTrack(context);
                            }
                        }
                        reentrantLock.unlock();
                    } catch (Exception unused) {
                        ((ReentrantLock) NOTIFICATION_LOCK).unlock();
                    } catch (Throwable th) {
                        ((ReentrantLock) NOTIFICATION_LOCK).unlock();
                        throw th;
                    }
                }
            } else {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "reject to show notification_show, reason: ".concat(strShouldReject));
            }
        }
        return Unit.INSTANCE;
    }

    static {
        OplusBatchTrack.addMap("notification_slide_action_batch", "type", "location", "event_time", "is_oversea_support");
        OplusBatchTrack.addMap("notification_flow_batch", "channel_id", "channel_name", "display_location", "duration", "event", "event_screen_orientation", "event_time", "grouped", "notification_id", "pkg", "post_screen_orientation", "post_time", "push_id", "push_notification_type", "resident_notification", "update_times", "push_msg_id", "push_reg_id", "push_priority", "event_location", "notification_type", "is_top", "is_unfold", "notification_source", "is_bottom", "importance", "user_id", "isExpose", "isReport", "notExposeReason");
        OplusBatchTrack.addMap("notification_menu_batch", "event", "auto_event", "pkg", "channel_id", "channel_name", "notification_id", "push_id", "operation_type", "user_id", "push_notification_type", "notification_type", "display_location", "notification_source");
        OplusBatchTrack.addMap("notification_expose_batch", "user_id", "channel_id", "channel_name", "display_location", "exPolicy", "group_key", "grouped", "is_commercial", "msgTime", "notification_id", "push_id", "opush_show_type", "pkg", "post_screen_orientation", "post_time", "pushTime", "resident_notification", "showTime", "update_times", "type", "location", "notification_type", "push_notification_type", "is_top", "is_bottom", "is_unfold", "notification_source", KeyBaseController.MAGIC_KEY_APP_NAME, "exposeTime");
        OplusBatchTrack.addMap("clear_all_notifications_batch", "event_screen_orientation", "event_time", "notifications_count");
    }

    public static void chargingNotificationReminder(int i, Context context) {
        HashMap map = new HashMap();
        map.put("detection_status", String.valueOf(i));
        OplusTrackProxyUtil.onCommon(context, "20082006", "charging_notification_reminder", map);
    }

    public static void collectFluidReplaceNotification(Context context, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m(LiveAlertService.EXTRA_PACKAGE_NAME, str);
        mapM.put("scene", String.valueOf(i));
        OplusTrackProxyUtil.onCommon(context, "20082", "fluid_replace_notification", mapM);
    }

    public static void collectNotificationMenuEvent(Context context, NotificationEntry notificationEntry, String str, String str2, int i) {
        if (notificationEntry == null) {
            return;
        }
        StatusBarNotification sbn = notificationEntry.getSbn();
        String string = sbn.getNotification().extras.getString("PushGlobalId", "");
        HashMap map = new HashMap();
        NotificationChannel channel = sHelper.getChannel(notificationEntry);
        if (channel == null) {
            if (LogUtil.getNormalDebug()) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "collectNotificationMenuEvent notificationChannel is null --> " + LogUtil.encryptionNotificationKey(sbn));
                return;
            }
            return;
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "collectNotificationMenuEvent --> \n packageName : " + sbn.getPackageName() + "\n channelName : " + ((Object) channel.getName()) + "\n channleID : " + channel.getId() + "\n notificationID : " + sbn.getId() + "\n strPushGlobalId : " + string + "\n eventType: " + str);
        map.put("user_id", String.valueOf(sbn.getUser().getIdentifier()));
        map.put("pkg", sbn.getPackageName());
        map.put("channel_name", String.valueOf(channel.getName()));
        map.put("channel_id", String.valueOf(channel.getId()));
        map.put("notification_id", String.valueOf(sbn.getId()));
        map.put("push_id", string);
        map.put("notification_source", MCSUtil.isMCSPkg(sbn.getPackageName(), sbn.getOpPkg()) ? "opush" : "local");
        map.put("notification_type", getNotificationType(sbn));
        map.put("push_notification_type", getOpushNotificationType(sbn));
        map.put("display_location", convertSbState2Location(i));
        map.put("event", str);
        map.put("operation_type", str2);
        OplusBatchTrack.onCommon(context, "20082", "notification_menu_batch", map);
    }

    public static String convertSbState2Location(int i) {
        if (i == 1) {
            return SeparateQSStatisicUtil.VALUE_KEYGUARD;
        }
        return i == 1000 ? "banner" : "notification_bar";
    }

    public static String formatDate(long j) {
        return j == 0 ? "" : LocalDateTime.ofInstant(Instant.ofEpochMilli(j), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getEventTime() {
        return formatDate(System.currentTimeMillis());
    }

    public static Map getNotificationData(StatusBarNotification statusBarNotification) {
        return (Map) sNotifications.get(getNotificationKey(statusBarNotification));
    }

    public static String getNotificationKey(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            return null;
        }
        return statusBarNotification.getUser() + statusBarNotification.getPackageName() + statusBarNotification.getId();
    }

    public static String getNotificationType(StatusBarNotification statusBarNotification) {
        if (statusBarNotification.getNotification().contentView != null) {
            return "CustomRemoteView";
        }
        Class notificationStyle = statusBarNotification.getNotification().getNotificationStyle();
        if (notificationStyle == null) {
            return "normal";
        }
        if (Notification.BigPictureStyle.class.equals(notificationStyle)) {
            return "big_picture";
        }
        if (Notification.BigTextStyle.class.equals(notificationStyle)) {
            return "big_text";
        }
        if (Notification.MessagingStyle.class.equals(notificationStyle)) {
            return "message";
        }
        if (Notification.InboxStyle.class.equals(notificationStyle)) {
            return OplusNotificationTemplateViewWrapperExImpl.INBOX_TAG;
        }
        if (Notification.MediaStyle.class.equals(notificationStyle)) {
            return OplusNotificationTemplateViewWrapperExImpl.MEDIA_TAG;
        }
        if (Notification.DecoratedCustomViewStyle.class.equals(notificationStyle)) {
            return "DecoratedCustomView";
        }
        if (Notification.DecoratedMediaCustomViewStyle.class.equals(notificationStyle)) {
            return "DecoratedMediaCustomViewStyle";
        }
        if (Notification.CallStyle.class.equals(notificationStyle)) {
            return "call";
        }
        if (Notification.ProgressStyle.class.equals(notificationStyle)) {
            return "progress";
        }
        return Notification.MetricStyle.class.equals(notificationStyle) ? "metric" : "others";
    }

    public static String getOpushNotificationType(StatusBarNotification statusBarNotification) {
        boolean zBooleanValue = (MCSUtil.isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg()) ? Boolean.valueOf(sLabelNotificationKeyCollection.contains(statusBarNotification.getKey())) : Boolean.FALSE).booleanValue();
        boolean zBooleanValue2 = (MCSUtil.isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg()) ? Boolean.valueOf(sBackgroundNotificationKeyCollection.contains(statusBarNotification.getKey())) : Boolean.FALSE).booleanValue();
        if (zBooleanValue && zBooleanValue2) {
            return "label_and_skin";
        }
        if (zBooleanValue) {
            return "Label";
        }
        return zBooleanValue2 ? "background_skin" : "normal";
    }

    public static String getScreenOrientation(Context context) {
        return context.getResources().getConfiguration().orientation != 2 ? "portrait" : "landscape";
    }

    private static Map<String, String> getShownNotificationData(Context context, StatusBarNotification statusBarNotification, int i, long j) {
        Map notificationData = getNotificationData(statusBarNotification);
        if (notificationData == null) {
            return new HashMap();
        }
        HashMap map = new HashMap(notificationData);
        map.put("user_id", String.valueOf(statusBarNotification.getUser().getIdentifier()));
        map.put("group_key", statusBarNotification.getNotification().getGroup());
        map.put("grouped", String.valueOf(statusBarNotification.isGroup()));
        map.put("location", i == 1 ? SeparateQSStatisicUtil.VALUE_KEYGUARD : getScreenOrientation(context));
        map.put("notification_type", getNotificationType(statusBarNotification));
        map.put("push_notification_type", getOpushNotificationType(statusBarNotification));
        map.put("type", statusBarNotification.isGroup() ? OplusNotifiEventLog.TYPE_GROUP : "single");
        map.put("channel_name", "");
        map.put("pkg", statusBarNotification.getPackageName());
        map.put("is_top", NotificationUtil.getForceTop(statusBarNotification));
        map.put("display_location", convertSbState2Location(i));
        map.put("is_unfold", NotificationUtil.getForceExpand(statusBarNotification));
        map.put("notification_source", MCSUtil.isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg()) ? "opush" : "local");
        map.put("is_bottom", String.valueOf(CommercialBlackListManager.getInstance(context).isInBlackList(statusBarNotification) ? 1 : 0));
        if (statusBarNotification.getNotification().extras != null) {
            map.put("opush_show_type", String.valueOf(statusBarNotification.getNotification().extras.getLong("opush_show_type", -1L)));
            map.put("is_commercial", String.valueOf(statusBarNotification.getNotification().extras.getInt("is_commercial", 0)));
        } else {
            map.put("opush_show_type", String.valueOf(-1L));
            map.put("is_commercial", String.valueOf(0));
        }
        Bundle bundle = statusBarNotification.getNotification().extras;
        if (bundle != null) {
            map.put("msgTime", formatDate(bundle.getLong("msgTime", 0L)));
            map.put("pushTime", formatDate(bundle.getLong("pushTime", 0L)));
            map.put("post_time", formatDate(statusBarNotification.getPostTime()));
            map.put("showTime", formatDate(j));
            map.put("exPolicy", bundle.getString("exPolicy", ""));
        }
        map.put("exposeTime", formatDate(System.currentTimeMillis()));
        return map;
    }

    public static boolean isResidentNotification(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            return false;
        }
        return !statusBarNotification.isClearable() || statusBarNotification.isOngoing();
    }

    public static void notificationAonSmartHide(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap map = new HashMap();
        map.put("trigger_type", str);
        OplusTrackProxyUtil.onCommon(context, "20082", "aon_notification_hide", map);
    }

    public static void onNotificationClickToZoom(Context context, StatusBarNotification statusBarNotification, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap map = new HashMap();
        map.put("channel_id", statusBarNotification.getNotification().getChannelId());
        map.put("channel_name", "");
        map.put("notification_source", MCSUtil.isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg()) ? "opush" : "local");
        map.put("notification_id", String.valueOf(statusBarNotification.getId()));
        map.put("pkg", statusBarNotification.getPackageName());
        map.put(KeyBaseController.MAGIC_KEY_APP_NAME, AppInfoUtil.getAppNameByPkgName(context, statusBarNotification.getPackageName()));
        map.put("event_screen_orientation", getScreenOrientation(context));
        map.put("oplus_notification_zoom_pkg", str);
        OplusTrackProxyUtil.onCommon(context, "20082", "oplus_notification_click_zoom", map);
    }

    public static void onNotificationExposeRealtime(Context context, Map map) {
        if (context == null || map.isEmpty()) {
            LogUtil.w(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "onNotificationExposeRealtime: no data");
            return;
        }
        int intForUser = Settings.System.getIntForUser(context.getContentResolver(), "is_mcs_upload", 0, -2);
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(intForUser, "onNotificationExposeRealtime: isMcsUpload:", LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil");
        if (intForUser == 0) {
            OplusTrackProxyUtil.onCommon(context, "20082", "notification_expose", (Map<String, String>) map, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(map);
        MCSUtil.sendNotificationExposeDataMsc(context, arrayList);
    }

    public static void onNotificationPosted(Context context, NotificationEntry notificationEntry, int i) {
        ThreadManager threadManager = ThreadManager.getInstance();
        NotificationStatisticUtil$$ExternalSyntheticLambda1 notificationStatisticUtil$$ExternalSyntheticLambda1 = new NotificationStatisticUtil$$ExternalSyntheticLambda1();
        notificationStatisticUtil$$ExternalSyntheticLambda1.f$0 = notificationEntry;
        notificationStatisticUtil$$ExternalSyntheticLambda1.f$1 = context;
        notificationStatisticUtil$$ExternalSyntheticLambda1.f$2 = i;
        VarHandle.storeStoreFence();
        threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda1);
    }

    public static void onNotificationRemoved(Context context, NotificationEntry notificationEntry, int i, int i2, boolean z) {
        ThreadManager threadManager = ThreadManager.getInstance();
        NotificationStatisticUtil$$ExternalSyntheticLambda0 notificationStatisticUtil$$ExternalSyntheticLambda0 = new NotificationStatisticUtil$$ExternalSyntheticLambda0();
        notificationStatisticUtil$$ExternalSyntheticLambda0.f$0 = notificationEntry;
        notificationStatisticUtil$$ExternalSyntheticLambda0.f$1 = context;
        notificationStatisticUtil$$ExternalSyntheticLambda0.f$2 = i;
        notificationStatisticUtil$$ExternalSyntheticLambda0.f$3 = i2;
        notificationStatisticUtil$$ExternalSyntheticLambda0.f$4 = z;
        VarHandle.storeStoreFence();
        threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda0);
    }

    public static void postNotificationTrack(Context context) {
        OCDHandler oCDHandler = SystemUIThreadUtil.sWorkHandler;
        UpdateTask updateTask = sUpdateTask;
        if (oCDHandler.hasCallbacks(updateTask)) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "postNotificationTrack-> not end");
        } else {
            updateTask.setContext(context);
            oCDHandler.postDelayed(updateTask, 60000L);
        }
    }

    public static void putAddNotificationData(Context context, StatusBarNotification statusBarNotification, long j) {
        Map<String, String> shownNotificationData = getShownNotificationData(context, statusBarNotification, 0, j);
        String notificationKey = getNotificationKey(statusBarNotification);
        if (TextUtils.isEmpty(notificationKey) || shownNotificationData == null || shownNotificationData.isEmpty()) {
            return;
        }
        sAddNotExposeNotifications.put(notificationKey, shownNotificationData);
        reportNotExportNotificationsInner(context, Boolean.FALSE);
    }

    public static void reportNotExportNotificationsInner(Context context, Boolean bool) {
        Map map = sAddNotExposeNotifications;
        if (map.isEmpty()) {
            return;
        }
        if (bool.booleanValue() || map.size() >= NotificationTrackerLinkConfig.getExposeReportSize()) {
            ArrayList arrayList = new ArrayList(map.values());
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "reportNotExportNotifications : cacheData:" + arrayList.size());
            try {
                OplusTrackProxyUtil.onCommonBatch(context, "20082", "notification_show_not_expose", arrayList, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
            } catch (Exception e) {
                CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("Not expose data upload fail:"), LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil");
            }
            sAddNotExposeNotifications.clear();
        }
    }

    public static void reportNotificationShowImmediately(Context context) {
        int intForUser = Settings.System.getIntForUser(context.getContentResolver(), "is_mcs_upload", 0, -2);
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(intForUser, "isMcsUpload:", LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil");
        if (intForUser == 0) {
            OplusBatchTrack.onCommonBatch(context, "20082", "notification_expose_batch", sNotificationShowCache, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
        } else {
            MCSUtil.sendNotificationExposeDataMsc(context, sNotificationShowCache);
        }
        sNotificationShowCache.clear();
    }

    public static void setReasonWhenRemoving(int i) {
        sReasonWhenRemoving = i;
    }

    public static void setSwipeLeftToShowMenu(boolean z) {
        sIsSwipeLeftToShowMenu = z;
    }

    private static String shouldReject(StatusBarNotification statusBarNotification, boolean z) {
        if (statusBarNotification.getNotification().isGroupSummary()) {
            return "notification is a group summary";
        }
        if (sShownResidentNotifications.contains(getNotificationKey(statusBarNotification))) {
            return "notification is an already shown resident notification";
        }
        if (getNotificationData(statusBarNotification) == null) {
            return "notification shown without post data";
        }
        if (!z) {
            return "";
        }
        ThreadManager threadManager = ThreadManager.getInstance();
        NotificationStatisticUtil$$ExternalSyntheticLambda2 notificationStatisticUtil$$ExternalSyntheticLambda2 = new NotificationStatisticUtil$$ExternalSyntheticLambda2(0);
        notificationStatisticUtil$$ExternalSyntheticLambda2.f$0 = statusBarNotification;
        VarHandle.storeStoreFence();
        threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda2);
        return "notification is encrypted: " + getNotificationKey(statusBarNotification);
    }

    public static void ubiquitousNotificationClick(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OplusTrackProxyUtil.onCommon(context, "20082", "smart_service_notification_operate", OplusCompatDcsUtils$.ExternalSyntheticOutline0.m("smart_service_notification_service_id", str, "smart_service_notification_behavior", "click"));
    }

    public static void uploadData(Context context) {
        if (System.currentTimeMillis() - sUploadTime < 60000) {
            if (((ArrayList) sNotificationRemoveCache).size() + ((ArrayList) sNotificationPostCache).size() < 20) {
                return;
            }
        }
        try {
            StringBuilder sb = new StringBuilder("uploadData post=");
            List list = sNotificationPostCache;
            sb.append(((ArrayList) list).size());
            sb.append(" remove=");
            List list2 = sNotificationRemoveCache;
            sb.append(((ArrayList) list2).size());
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", sb.toString());
            if (!list.isEmpty()) {
                OplusTrackProxyUtil.onCommonBatch(context, "20082", "notification_post", list, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
                list.clear();
            }
            if (!list2.isEmpty()) {
                OplusBatchTrack.onCommonBatch(context, "20082", "notification_flow_batch", list2, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
                list2.clear();
            }
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("uploadData fail:"), LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil");
        }
        sUploadTime = System.currentTimeMillis();
    }
}
