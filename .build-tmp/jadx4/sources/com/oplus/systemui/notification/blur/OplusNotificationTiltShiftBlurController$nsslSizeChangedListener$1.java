package com.oplus.systemui.notification.blur;

import android.util.Log;
import android.view.View;
import androidx.collection.FloatList$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationTiltShiftBlurController$nsslSizeChangedListener$1 implements NotificationStackScrollLayoutControllerExt.OnSizeChangedListener {
    public /* synthetic */ OplusNotificationTiltShiftBlurController this$0;

    public final void onSizeChanged(int i, int i2) {
        Integer numComputeTargetHeight;
        OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController = this.this$0;
        if (i == i2) {
            return;
        }
        View view = oplusNotificationTiltShiftBlurController.lockIconContainer;
        int iIntValue = (view == null || (numComputeTargetHeight = oplusNotificationTiltShiftBlurController.computeTargetHeight(oplusNotificationTiltShiftBlurController.lastQsSimpleHeaderHeight, view)) == null) ? 0 : numComputeTargetHeight.intValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(iIntValue, oplusNotificationTiltShiftBlurController.lastQsSimpleHeaderHeight, " ", "OplusNotificationTiltShiftBlurController-->", FloatList$.ExternalSyntheticOutline0.m("nsslSizeChanged width ", i2, i, " -> ", "; height=")));
        }
        OplusNotificationTiltShiftBlurContainer oplusNotificationTiltShiftBlurContainer = oplusNotificationTiltShiftBlurController.container;
        if (oplusNotificationTiltShiftBlurContainer != null) {
            oplusNotificationTiltShiftBlurContainer.updateTiltShiftBlurtSize(i, iIntValue);
        }
        oplusNotificationTiltShiftBlurController.updateNsslAlphaAreaHeight(iIntValue);
    }
}
