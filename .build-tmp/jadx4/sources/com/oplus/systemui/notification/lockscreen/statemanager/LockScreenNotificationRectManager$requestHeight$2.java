package com.oplus.systemui.notification.lockscreen.statemanager;

import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationRectManager$requestHeight$2 implements Runnable {
    public /* synthetic */ int $newExpectedHeight;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ LockScreenNotificationRectManager this$0;

    public /* synthetic */ LockScreenNotificationRectManager$requestHeight$2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LockScreenNotificationRectManager lockScreenNotificationRectManager = this.this$0;
                lockScreenNotificationRectManager.rectMayChangeNextFrame = false;
                ((NotificationStackScrollLayout) lockScreenNotificationRectManager.nssl.get()).mAnimateNextTopPaddingChange = true;
                NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) this.this$0.npvc.get();
                notificationPanelViewController.mExt.onNotificationExpectedHeightChanged(this.$newExpectedHeight);
                notificationPanelViewController.positionClockAndNotifications(true);
                LockScreenNotificationRectManager lockScreenNotificationRectManager2 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileInputLogger$.ExternalSyntheticOutline0.m(((NotificationPanelViewController) lockScreenNotificationRectManager2.npvc.get()).getExt().getKeyguardNotificationBottomPadding(), "LockScreenNotificationRectManager-->", "SystemUi--Notification", KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(NotificationStackScrollLayoutExt.getLockscreenNotificationDisplayHeight$default((NotificationStackScrollLayoutExt) lockScreenNotificationRectManager2.nsslExt.get()), ((AmbientState) lockScreenNotificationRectManager2.ambientState.get()).getTopPadding(), "[requestHeight]result in next frame: newDisplayHeight=", ",ambientState.get().topPadding=", ",keyguardNotificationBottomPadding="));
                }
                this.this$0.expectedHeight = this.$newExpectedHeight;
                break;
            default:
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "LockScreenNotificationRectManager", TextFieldBuffer$.ExternalSyntheticOutline0.m(this.$newExpectedHeight, this.this$0.nextRequestedBasedHighFingerprintIconTop, "[updateCurrHighFingerprintIconTop] requestChildrenUpdateWithAnimation, last=", ", new="), null, 8);
                NotificationStackScrollLayout notificationStackScrollLayout = ((NotificationStackScrollLayoutController) this.this$0.nsslc.get()).mView;
                if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled) {
                    notificationStackScrollLayout.mEverythingNeedsAnimation = true;
                    notificationStackScrollLayout.mNeedsAnimation = true;
                    notificationStackScrollLayout.requestChildrenUpdate$1();
                    break;
                }
                break;
        }
    }
}
