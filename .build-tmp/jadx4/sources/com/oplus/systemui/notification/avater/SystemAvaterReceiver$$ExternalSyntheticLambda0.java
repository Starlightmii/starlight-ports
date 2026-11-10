package com.oplus.systemui.notification.avater;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SystemAvaterReceiver$$ExternalSyntheticLambda0 implements IReceiverListener {
    public /* synthetic */ SystemAvaterReceiver f$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        SystemAvaterReceiver systemAvaterReceiver = this.f$0;
        systemAvaterReceiver.getClass();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "SystemAvaterReceiver", "action = " + action);
        action.hashCode();
        switch (action) {
            case "android.intent.action.USER_REMOVED":
                int intExtra = intent.getIntExtra("android.intent.extra.user_handle", -10000);
                SystemAvaterNotificationHelper systemAvaterNotificationHelper = SystemAvaterNotificationHelper.getInstance(context);
                if (intExtra == systemAvaterNotificationHelper.mMultiSystemUserId) {
                    systemAvaterNotificationHelper.mMultiSystemUserId = -10000;
                    systemAvaterNotificationHelper.clearNotificationCount(true);
                    break;
                }
                break;
            case "android.intent.switch_user":
                try {
                    systemAvaterReceiver.mClickAvatarNotificationFlag = false;
                    int intExtra2 = intent.getIntExtra("android.intent.extra.user_id", 0);
                    boolean z = SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "SystemAvaterReceiver", "switchUserId = " + intExtra2 + ",isSuperSaveMode = " + z + ",mStatusBarState = " + systemAvaterReceiver.mStatusBarState);
                    int barState = ((NotificationCenterManager) DependencyEx.sDependency.getDependency(NotificationCenterManagerEx.class)).getBarState();
                    systemAvaterReceiver.mStatusBarState = barState;
                    if (barState == 0) {
                        if (!z) {
                            ActivityManager.getService().switchUser(intExtra2);
                            if (intExtra2 == 0 && !SystemAvaterNotificationHelper.getInstance(context).mLockPatternUtils.isSecure(intExtra2)) {
                                systemAvaterReceiver.mClickAvatarNotificationFlag = true;
                                break;
                            }
                        } else {
                            Toast.makeText(context, 2131952926, 1).show();
                            break;
                        }
                    }
                } catch (Exception e) {
                    LogUtil.e(LogUtil.TAG_NOTIFICATION, "SystemAvaterReceiver", "Couldn't switch user " + e);
                    return;
                }
                break;
            case "android.intent.action.USER_SWITCHED":
                int intExtra3 = intent.getIntExtra("android.intent.extra.user_handle", -1);
                SystemAvaterNotificationHelper.getInstance(context).setCurrentUserId(intExtra3);
                StringBuilder sb = new StringBuilder("currentId = ");
                sb.append(intExtra3);
                sb.append(",clickAvatarNotificationFlag = ");
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_NOTIFICATION, "SystemAvaterReceiver", sb, systemAvaterReceiver.mClickAvatarNotificationFlag);
                if (intExtra3 == 0 && systemAvaterReceiver.mClickAvatarNotificationFlag) {
                    SystemAvaterNotificationHelper.getInstance(context).clearNotificationCount(false);
                    systemAvaterReceiver.mClickAvatarNotificationFlag = false;
                    break;
                }
                break;
        }
    }
}
