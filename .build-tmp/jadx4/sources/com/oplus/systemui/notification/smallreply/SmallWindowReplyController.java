package com.oplus.systemui.notification.smallreply;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.statusbar.notification.collection.EntryAdapter;
import com.android.systemui.statusbar.notification.collection.EntryAdapterFactory;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManager;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplus.systemui.statusbar.seeding.livealert.LiveAlertService;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmallWindowReplyController {
    public static final Companion Companion = new Companion();
    private static final String PACKAGE_WX = "com.tencent.mm";
    private static final String[] SMALL_REPLY_LIST = {PACKAGE_WX, "com.tencent.mobileqq"};
    private static final String TAG = "SmallWindowReplyController";
    private final Context context;
    private final EntryAdapterFactory entryAdapterFactory;
    private final GroupMembershipManager groupMembershipManager;
    private final CommonNotifCollection notifCollection;
    private final SmallWindowReplyController$notifListener$1 notifListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SmallWindowReplyController(Context context, CommonNotifCollection commonNotifCollection, GroupMembershipManager groupMembershipManager, EntryAdapterFactory entryAdapterFactory) {
        this.context = context;
        this.notifCollection = commonNotifCollection;
        this.groupMembershipManager = groupMembershipManager;
        this.entryAdapterFactory = entryAdapterFactory;
        SmallWindowReplyController$notifListener$1 smallWindowReplyController$notifListener$1 = new SmallWindowReplyController$notifListener$1();
        smallWindowReplyController$notifListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifListener = smallWindowReplyController$notifListener$1;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00ec  */
    public final void onNotificationPosted(NotificationEntry notificationEntry, boolean z) {
        CharSequence charSequence;
        Notification.Action[] actionArr;
        StatusBarNotification sbn = notificationEntry.getSbn();
        Notification notification = sbn.getNotification();
        String[] strArr = SMALL_REPLY_LIST;
        if (ArraysKt___ArraysKt.contains(strArr, sbn.getPackageName())) {
            CharSequence charSequence2 = sbn.getNotification().extras.getCharSequence("android.text");
            CharSequence charSequence3 = sbn.getNotification().extras.getCharSequence("android.title");
            boolean z2 = (sbn.getNotification().flags & 2) != 0;
            boolean z3 = (sbn.getNotification().flags & 32) != 0;
            boolean z4 = (sbn.getNotification().flags & 64) != 0;
            GroupMembershipManagerImpl groupMembershipManagerImpl = this.groupMembershipManager;
            EntryAdapter entryAdapterCreate = this.entryAdapterFactory.create(notificationEntry);
            groupMembershipManagerImpl.getClass();
            boolean z5 = z2 || z3 || z4 || entryAdapterCreate.isGroupRoot() || sbn.getNotification().contentIntent == null || !sbn.getNotification().contentIntent.isActivity();
            if ((sbn.getNotification().contentView != null && TextUtils.isEmpty(charSequence2) && TextUtils.isEmpty(charSequence3)) || z5 || sbn.getPackageName().length() <= 0) {
                charSequence = null;
            } else {
                NotificationFeatureOption.INSTANCE.getClass();
                if (NotificationFeatureOption.isSmallReplyEnabled && SmallReplyObserver.Companion.getInstance().getSmallReplyEnable()) {
                    String packageName = sbn.getPackageName();
                    Context context = this.context;
                    PendingIntent pendingIntent = sbn.getNotification().contentIntent;
                    if (!ArraysKt___ArraysKt.contains(strArr, packageName) || ((PACKAGE_WX.equals(packageName) && !OpNotificationUtils.isBackgroundIntentInternal(context, sbn.getUserId(), pendingIntent)) || !OpNotificationUtils.isSupportZoonByIntent(this.context, notificationEntry, null, true, true))) {
                        charSequence = null;
                    } else {
                        PendingIntent pendingIntent2 = notification.contentIntent;
                        if (pendingIntent2 == null) {
                            pendingIntent2 = notification.fullScreenIntent;
                        }
                        Intent intent = new Intent("oppo.intent.action.NOTIFICATION_ACTION_CLICK");
                        intent.setPackage(this.context.getPackageName());
                        intent.putExtra("pendingIntent", pendingIntent2);
                        intent.putExtra(LiveAlertService.EXTRA_PACKAGE_NAME, sbn.getPackageName());
                        intent.putExtra("opPkg", sbn.getOpPkg());
                        intent.putExtra("userId", sbn.getUserId());
                        intent.putExtra("uId", sbn.getUid());
                        intent.putExtra("entryKey", sbn.getKey());
                        charSequence = null;
                        Notification.Action actionBuild = new Notification.Action.Builder((Icon) null, this.context.getResources().getString(2131955395), PendingIntent.getBroadcast(this.context, (int) System.currentTimeMillis(), intent, 301989888)).addExtras(CommunalWidgetHost$.ExternalSyntheticOutline0.m(1, "tag_smart_reply")).setAuthenticationRequired(true).build();
                        Notification.Action[] actionArr2 = notification.actions;
                        if (actionArr2 == null || z) {
                            actionArr = new Notification.Action[]{actionBuild};
                        } else {
                            int length = actionArr2.length;
                            actionArr = new Notification.Action[length + 1];
                            actionArr[0] = actionBuild;
                            int i = 0;
                            while (i < length) {
                                int i2 = i + 1;
                                actionArr[i2] = notification.actions[i];
                                i = i2;
                            }
                        }
                        notification.actions = actionArr;
                    }
                } else {
                    charSequence = null;
                }
            }
        } else {
            charSequence = null;
        }
        if (LogUtil.getNormalDebug() && sbn.getNotification() != null && sbn.getNotification().contentView == null) {
            Bundle bundle = sbn.getNotification().extras;
            Object obj = bundle != null ? bundle.get("android.title") : charSequence;
            CharSequence charSequence4 = obj instanceof CharSequence ? (CharSequence) obj : charSequence;
            Bundle bundle2 = sbn.getNotification().extras;
            Object obj2 = bundle2 != null ? bundle2.get("android.text") : charSequence;
            CharSequence charSequence5 = obj2 instanceof CharSequence ? (CharSequence) obj2 : charSequence;
            boolean zIsEmpty = TextUtils.isEmpty(charSequence4);
            boolean zIsEmpty2 = TextUtils.isEmpty(charSequence5);
            if (zIsEmpty || zIsEmpty2) {
                String encryptionKey = notificationEntry.getEx().getEncryptionKey();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onNotificationPosted isTitleEmpty: ", " isTextEmpty: ", " isUpdate: ", zIsEmpty, zIsEmpty2);
                sbM.append(z);
                sbM.append(" key: ");
                sbM.append(encryptionKey);
                String string = sbM.toString();
                Handler handler = OplusNotificationLogger.LOG_HANDLER;
                LogUtil.i(LogUtil.TAG_NOTIFICATION, TAG, "EMPTY_NOTIFICATION-->".concat(string));
            }
        }
    }

    public final void start() {
        this.notifCollection.addCollectionListener(this.notifListener);
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isSmallReplyEnabled) {
            DefaultFloatReplyStateObserver.Companion.getInstance().registerForUserSwitch(this.context, false);
            SmallReplyObserver.Companion.getInstance().registerForUserSwitch(this.context, false);
        }
        NotificationActionReceiver.getInstance().register(this.context, UserHandle.ALL, (Handler) null);
    }
}
