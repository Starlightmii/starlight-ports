package com.oplus.systemui.notification.lockscreen.notification;

import android.content.res.Configuration;
import android.util.Log;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationDataController$configurationCallback$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ CapsuleNotificationDataController this$0;

    public final void onDensityOrFontScaleChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->onDensityOrFontScaleChanged");
        }
        this.this$0.onConfigChanged();
    }

    public final void onFontChanged(Configuration configuration) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->onFontChanged");
        }
        this.this$0.onConfigChanged();
    }

    public final void onFontWeightChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->onFontWeightChanged");
        }
        this.this$0.onConfigChanged();
    }

    public final void onLocaleListChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->onLocaleListChanged");
        }
        this.this$0.onConfigChanged();
    }

    public final void onOrientationChanged(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->onOrientationChanged");
        }
        this.this$0.onConfigChanged();
    }

    public final void onUiModeChanged() {
        CapsuleNotificationCardView currentView;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->onUiModeChanged");
        }
        CapsuleNotificationContainerView capsuleNotificationContainerView = this.this$0.currentContainer;
        if (capsuleNotificationContainerView == null || (currentView = capsuleNotificationContainerView.getCurrentView()) == null) {
            return;
        }
        currentView.updateColor();
    }
}
