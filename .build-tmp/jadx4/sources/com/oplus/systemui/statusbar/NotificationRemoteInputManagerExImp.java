package com.oplus.systemui.statusbar;

import android.app.ActivityOptions;
import android.app.OplusActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Pair;
import com.android.systemui.statusbar.NotificationRemoteInputManagerEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationRemoteInputManagerExImp extends NotificationRemoteInputManagerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationRemoteInputManagerExImp";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationRemoteInputManagerExImp(Context context) {
        super(context);
        this.context = context;
    }

    public void handleAppForNotification(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            return;
        }
        OplusActivityManager oplusActivityManager = OplusActivityManager.getInstance();
        String packageName = statusBarNotification.getPackageName();
        int uid = statusBarNotification.getUid();
        int initialPid = statusBarNotification.getInitialPid();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("handleAppForNotification , uid: ", uid, initialPid, " pid : ", "NotificationRemoteInputManagerExImp-->"));
        }
        try {
            oplusActivityManager.handleAppForNotification(packageName, uid, initialPid);
        } catch (RemoteException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "OplusActivityManager handleAppForNotification failed: ", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    public Pair<Intent, ActivityOptions> notificationStartWithoutAnim(Pair<Intent, ActivityOptions> pair) {
        if (pair == null) {
            return null;
        }
        Intent intent = (Intent) pair.first;
        Intent intent2 = intent != null ? new Intent(intent) : new Intent();
        intent2.putExtra("notificationStartFrom", "startWithoutAnim");
        ActivityOptions activityOptionsMakeBasic = (ActivityOptions) pair.second;
        if (activityOptionsMakeBasic == null) {
            activityOptionsMakeBasic = ActivityOptions.makeBasic();
        }
        return Pair.create(intent2, activityOptionsMakeBasic);
    }

    public void resetNSSLAlpha(ExpandableNotificationRow expandableNotificationRow) {
        if (!SeparateModeHelper.INSTANCE.getSeparateMode() && expandableNotificationRow.getParent() != null && (expandableNotificationRow.getParent() instanceof NotificationStackScrollLayout) && expandableNotificationRow.getParent().getVisibility() == 0 && expandableNotificationRow.getParent().getAlpha() == 0.0f) {
            expandableNotificationRow.getParent().setAlpha(1.0f);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "Set NSSL alpha = 1, in NotificationRemoteInputManagerExImp", null, 8);
        }
    }
}
