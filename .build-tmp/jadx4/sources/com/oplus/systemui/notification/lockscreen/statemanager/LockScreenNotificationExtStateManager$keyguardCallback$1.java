package com.oplus.systemui.notification.lockscreen.statemanager;

import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.statusbar.util.KeyguardNotificationUploadUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationExtStateManager$keyguardCallback$1 implements KeyguardStateController.Callback {
    public /* synthetic */ LockScreenNotificationExtStateManager this$0;

    public final void onKeyguardShowingChanged() {
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.this$0;
        boolean z = ((KeyguardStateController) lockScreenNotificationExtStateManager.keyguardStateController.get()).mShowing;
        boolean z2 = ((KeyguardStateController) lockScreenNotificationExtStateManager.keyguardStateController.get()).mOccluded;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("LockScreenExtStateController-->", "SystemUi--Notification", TextFieldImplKt$.ExternalSyntheticOutline0.m("[onKeyguardShowingChanged] ", ",isOccluded ", ", lastOccluded ", z, z2), lockScreenNotificationExtStateManager.lastKeyguardOccluded);
        }
        if (!z2 && lockScreenNotificationExtStateManager.lastKeyguardOccluded) {
            lockScreenNotificationExtStateManager.updateKeyguardFpSuppressionState();
        }
        lockScreenNotificationExtStateManager.lastKeyguardOccluded = z2;
        if (!z) {
            KeyguardNotificationUploadUtil.Companion.getInstance().uploadData(((NotificationStackScrollLayoutController) lockScreenNotificationExtStateManager.nsslController.get()).getView().getContext());
        }
        if (((KeyguardStateController) lockScreenNotificationExtStateManager.keyguardStateController.get()).mShowing || lockScreenNotificationExtStateManager.statusBarStateController.getState() != 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "LockScreenExtStateController-->[onKeyguardShowingChanged]: handleExitKeyguard");
        }
        LockScreenNotificationExtStateManager.access$handleExitKeyguard(lockScreenNotificationExtStateManager);
    }
}
