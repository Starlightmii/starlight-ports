package com.oplus.systemui.statusbar.util;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;
import java.lang.invoke.VarHandle;
import java.util.Map;
import java.util.Vector;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStatisticUtil$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ NotificationEntry f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ int f$3;
    public /* synthetic */ boolean f$4;

    public final Object invoke() {
        String strM;
        int i;
        NotificationEntry notificationEntry = this.f$0;
        Context context = this.f$1;
        int i2 = this.f$2;
        int i3 = this.f$3;
        boolean z = this.f$4;
        if (LogUtil.getNormalDebug() && notificationEntry != null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "notification removed:" + NotificationStatisticUtil.getNotificationKey(notificationEntry.getSbn()));
        }
        if (notificationEntry != null) {
            StatusBarNotification sbn = notificationEntry.getSbn();
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "remove::reason ", OplusPreventModeData.SEPARATOR);
            sbM.append(NotificationStatisticUtil.getNotificationKey(sbn));
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", sbM.toString());
            Map notificationData = NotificationStatisticUtil.getNotificationData(sbn);
            if (notificationData != null) {
                if (i2 != 1) {
                    if (i2 == 2) {
                        strM = NotificationStatisticUtil.sReasonWhenRemoving == 11 ? "canceled_by_menu_delete" : "swipe_single";
                    } else if (i2 == 3) {
                        strM = "clear_all";
                    } else if (i2 != 12) {
                        switch (i2) {
                            case 6:
                                strM = "app_stopped";
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                strM = "canceled_by_package_banned";
                                break;
                            case 8:
                            case 9:
                                strM = "canceled_by_app";
                                break;
                            default:
                                switch (i2) {
                                    case 17:
                                        strM = "canceled_by_channel_banned";
                                        break;
                                    case 18:
                                        strM = "snoozed_" + NotificationStatisticUtil.sSnoozedTimeWhenRemoving;
                                        break;
                                    case 19:
                                        strM = "canceled_by_timeout";
                                        break;
                                    default:
                                        strM = ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "others_");
                                        break;
                                }
                                break;
                        }
                    } else {
                        strM = "delete_group";
                    }
                } else if (i3 == 1000) {
                    strM = "canceled_by_banner_clicked";
                } else {
                    strM = i3 == 1 ? "canceled_by_keyguard_clicked" : "canceled_by_notification_bar_clicked";
                }
                notificationData.put("event", strM);
                long postTime = sbn.getPostTime();
                String strConvertSbState2Location = NotificationStatisticUtil.convertSbState2Location(i3);
                notificationData.put("grouped", String.valueOf(z));
                notificationData.put("event_location", strConvertSbState2Location);
                notificationData.put("event_screen_orientation", NotificationStatisticUtil.getScreenOrientation(context));
                notificationData.put("event_time", NotificationStatisticUtil.getEventTime());
                notificationData.put("duration", ((System.currentTimeMillis() - postTime) / 1000) + "s");
                notificationData.put("display_location", strConvertSbState2Location);
                if (MCSUtil.isMCSPkg(sbn.getPackageName(), sbn.getOpPkg())) {
                    try {
                        i = Integer.parseInt(sbn.getNotification().extras.getString("sort_priority", "0"));
                    } catch (NumberFormatException unused) {
                        i = 0;
                    }
                } else {
                    i = 0;
                }
                if (i == 2) {
                    String string = sbn.getNotification().extras.getString("msgId", "");
                    String string2 = sbn.getNotification().extras.getString("regId", "");
                    notificationData.put("push_msg_id", string);
                    notificationData.put("push_reg_id", string2);
                    notificationData.put("push_priority", String.valueOf(i));
                }
                notificationData.put("user_id", String.valueOf(sbn.getUser().getIdentifier()));
                notificationData.put("is_top", NotificationUtil.getForceTop(sbn));
                notificationData.put("channel_name", "");
                notificationData.put("notification_type", NotificationStatisticUtil.getNotificationType(sbn));
                notificationData.put("push_notification_type", NotificationStatisticUtil.getOpushNotificationType(sbn));
                String key = sbn.getKey();
                Vector vector = NotificationStatisticUtil.sBackgroundNotificationKeyCollection;
                if (vector.contains(key)) {
                    vector.remove(key);
                }
                Vector vector2 = NotificationStatisticUtil.sLabelNotificationKeyCollection;
                if (vector2.contains(key)) {
                    vector2.remove(key);
                }
                notificationData.put("is_unfold", NotificationUtil.getForceExpand(sbn));
                notificationData.put("notification_source", MCSUtil.isMCSPkg(sbn.getPackageName(), sbn.getOpPkg()) ? "opush" : "local");
                notificationData.put("is_bottom", String.valueOf(CommercialBlackListManager.getInstance(context).isInBlackList(sbn) ? 1 : 0));
                Map map = NotificationStatisticUtil.sPostSliderTimes;
                String str = (String) map.get(NotificationStatisticUtil.getNotificationKey(sbn));
                notificationData.put("isExpose", NotificationStatisticUtil.sShownNotifications.contains(NotificationStatisticUtil.getNotificationKey(sbn)) ? "1" : "0");
                Map map2 = NotificationStatisticUtil.sShowNotificationIsReports;
                notificationData.put("isReport", (String) map2.get(NotificationStatisticUtil.getNotificationKey(sbn)));
                notificationData.put("notExposeReason", notificationEntry.mNotificationEntryEx.getNotificationIndex() + "_" + str);
                boolean zIsFilterTrackEvent = NotificationTrackHelper.getInstance().isFilterTrackEvent(context, sbn);
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "report:notification flow->" + notificationData + ", isFilter: " + zIsFilterTrackEvent);
                if (!"android.permission.cts.appthatrequestpermission".equals(notificationData.get("pkg")) && !zIsFilterTrackEvent) {
                    NotificationStatisticUtil.sNotificationRemoveCache.add(notificationData);
                    NotificationStatisticUtil.uploadData(context);
                }
                NotificationStatisticUtil.sNotifications.remove(NotificationStatisticUtil.getNotificationKey(sbn));
                map.remove(NotificationStatisticUtil.getNotificationKey(sbn));
                map2.remove(NotificationStatisticUtil.getNotificationKey(sbn));
                NotificationStatisticUtil.sReasonWhenRemoving = -1;
                NotificationStatisticUtil.sSnoozedTimeWhenRemoving = -1L;
            } else {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "notification to be removed does not exist");
            }
            ThreadManager threadManager = ThreadManager.getInstance();
            NotificationStatisticUtil$$ExternalSyntheticLambda2 notificationStatisticUtil$$ExternalSyntheticLambda2 = new NotificationStatisticUtil$$ExternalSyntheticLambda2(0);
            notificationStatisticUtil$$ExternalSyntheticLambda2.f$0 = sbn;
            VarHandle.storeStoreFence();
            threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda2);
            if (NotificationStatisticUtil.isResidentNotification(sbn)) {
                NotificationStatisticUtil.sShownResidentNotifications.remove(NotificationStatisticUtil.getNotificationKey(sbn));
            }
        }
        return Unit.INSTANCE;
    }
}
