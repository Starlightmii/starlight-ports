package com.oplus.systemui.statusbar.notification.stack;

import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutExtImpl$stateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ NotificationStackScrollLayoutExtImpl this$0;

    public final void onStateChanged(int i) {
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = this.this$0;
        if (i == 0) {
            NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
            notificationStackScrollLayoutExtImpl.ensureNsslHiddenWhenElementNotShowing();
        } else if (i == 1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->KEYGUARD overScrollAnimator.cancel");
            }
            notificationStackScrollLayoutExtImpl.cancelScrollAnimator();
            notificationStackScrollLayoutExtImpl.resetOverDistance("onStateChanged");
            NotificationStackScrollLayoutExtImpl.access$cancelScrollScaleAnimator(notificationStackScrollLayoutExtImpl);
        }
        String strM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onStateChanged-");
        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
        notificationStackScrollLayoutExtImpl.tryRequestRenderBoosterAfterRenderList(strM);
    }
}
