package com.oplus.systemui.statusbar.util;

import android.app.NotificationManager;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import java.util.HashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStatisticUtil$$ExternalSyntheticLambda1 implements Function0 {
    public /* synthetic */ NotificationEntry f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ int f$2;

    public final Object invoke() {
        NotificationEntry notificationEntry = this.f$0;
        Context context = this.f$1;
        int i = this.f$2;
        if (notificationEntry != null && notificationEntry.getSbn() != null) {
            if (NotificationStatisticUtil.getNotificationData(notificationEntry.getSbn()) == null) {
                boolean zShouldVibrate = notificationEntry.getChannel() != null ? notificationEntry.getChannel().shouldVibrate() : false;
                StatusBarNotification sbn = notificationEntry.getSbn();
                int importance = notificationEntry.getImportance();
                if (sbn != null) {
                    boolean z = ((NotificationManager) context.getSystemService("notification")).getZenMode() != 0;
                    if (LogUtil.getNormalDebug()) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "notification post:" + NotificationStatisticUtil.getNotificationKey(sbn));
                    }
                    String strConvertSbState2Location = NotificationStatisticUtil.convertSbState2Location(i);
                    HashMap map = new HashMap();
                    map.put("pkg", sbn.getPackageName());
                    map.put("channel_id", sbn.getNotification().getChannelId());
                    map.put("channel_name", "");
                    map.put("post_screen_orientation", NotificationStatisticUtil.getScreenOrientation(context));
                    map.put("notification_id", String.valueOf(sbn.getId()));
                    map.put("push_id", OplusAnimationController.PACKAGE_SYSTEM.equals(sbn.getOpPkg()) ? sbn.getNotification().extras.getString("msgId", "") : null);
                    boolean z2 = zShouldVibrate;
                    map.put("resident_notification", String.valueOf(NotificationStatisticUtil.isResidentNotification(sbn)));
                    map.put("post_time", NotificationStatisticUtil.formatDate(sbn.getPostTime()));
                    map.put("update_times", "0");
                    map.put("display_location", strConvertSbState2Location);
                    NotificationStatisticUtil.sNotifications.put(NotificationStatisticUtil.getNotificationKey(sbn), map);
                    Map map2 = NotificationStatisticUtil.sPostSliderTimes;
                    String notificationKey = NotificationStatisticUtil.getNotificationKey(sbn);
                    StringBuilder sb = new StringBuilder();
                    boolean z3 = z;
                    sb.append(NotificationStatisticUtil.sDownExpansionTime);
                    sb.append("_");
                    sb.append(NotificationStatisticUtil.sUpCollapseTime);
                    map2.put(notificationKey, sb.toString());
                    if (!sbn.getNotification().isGroupSummary()) {
                        HashMap map3 = new HashMap(map);
                        String appNameByPkgName = AppInfoUtil.getAppNameByPkgName(context, sbn.getPackageName());
                        map3.put("user_id", String.valueOf(sbn.getUser().getIdentifier()));
                        map3.put(KeyBaseController.MAGIC_KEY_APP_NAME, appNameByPkgName);
                        map3.put("group_key", sbn.getNotification().getGroup());
                        map3.put("pkg", sbn.getPackageName());
                        map3.put("channel_name", "");
                        map3.put("is_top", NotificationUtil.getForceTop(sbn));
                        map3.put("is_unfold", NotificationUtil.getForceExpand(sbn));
                        map3.put("notification_type", NotificationStatisticUtil.getNotificationType(sbn));
                        map3.put("push_notification_type", NotificationStatisticUtil.getOpushNotificationType(sbn));
                        map3.put("grouped", String.valueOf(sbn.isGroup()));
                        map3.put("notification_source", MCSUtil.isMCSPkg(sbn.getPackageName(), sbn.getOpPkg()) ? "opush" : "local");
                        map3.put("channel_id", sbn.getNotification().getChannelId());
                        map3.put("notification_id", String.valueOf(sbn.getId()));
                        map3.put("post_time", NotificationStatisticUtil.formatDate(sbn.getPostTime()));
                        map3.put("push_id", OplusAnimationController.PACKAGE_SYSTEM.equals(sbn.getOpPkg()) ? sbn.getNotification().extras.getString("msgId", "") : null);
                        map3.put("importance", String.valueOf(importance));
                        map3.put("system_state", NotificationTrackHelper.getInstance().getSystemState(sbn, z3, z2));
                        map3.put("receiveTime", NotificationStatisticUtil.formatDate(System.currentTimeMillis()));
                        boolean zIsFilterTrackEvent = NotificationTrackHelper.getInstance().isFilterTrackEvent(context, sbn);
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "report:notification post new3 ->" + map3 + ", isFilter: " + zIsFilterTrackEvent);
                        if (!"android.permission.cts.appthatrequestpermission".equals(appNameByPkgName) && !zIsFilterTrackEvent) {
                            if (!NotificationUtil.isMscTopNotification((String) map3.get("is_top"), sbn)) {
                                NotificationStatisticUtil.sNotificationPostCache.add(map3);
                                NotificationStatisticUtil.uploadData(context);
                            } else if (map3.isEmpty()) {
                                LogUtil.w(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "onNotificationPostRealtime: no data");
                            } else {
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "onNotificationPostRealtime->" + map3);
                                OplusTrackProxyUtil.onCommon(context, "20082", "notification_post", map3, OplusTrackProxyUtil.FLAG_SEND_TO_DCS);
                            }
                        }
                    }
                }
            } else {
                StatusBarNotification sbn2 = notificationEntry.getSbn();
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "notification update:" + NotificationStatisticUtil.getNotificationKey(sbn2));
                Map notificationData = NotificationStatisticUtil.getNotificationData(sbn2);
                if (notificationData != null) {
                    try {
                        notificationData.put("update_times", String.valueOf(Integer.parseInt((String) notificationData.get("update_times")) + 1));
                    } catch (Exception e) {
                        if (LogUtil.getNormalDebug()) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "update notification failed:" + NotificationStatisticUtil.getNotificationKey(sbn2) + "->" + e.getMessage());
                        }
                    }
                } else {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "notification has not posted,or removed:" + NotificationStatisticUtil.getNotificationKey(sbn2));
                }
            }
        }
        return Unit.INSTANCE;
    }
}
