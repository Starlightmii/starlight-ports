package com.oplus.systemui.notification.row.overlay;

import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationOverlayControllerImpl$hideOverlayAction$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationOverlayControllerImpl this$0;

    public /* synthetic */ OplusNotificationOverlayControllerImpl$hideOverlayAction$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(oplusNotificationOverlayControllerImpl.hashCode(), "hideOverlayAction(", ")", "OplusNotificationOverlayController-->", "SystemUi--Notification");
                }
                this.this$0.fadeSpringAnimation.animateToFinalPosition(0.0f);
                break;
            case 1:
                OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl2 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(oplusNotificationOverlayControllerImpl2.hashCode(), "hideOverlayActionNoAnimation(", ")", "OplusNotificationOverlayController-->", "SystemUi--Notification");
                }
                this.this$0.setOverlayAlpha(0.0f);
                this.this$0.fadeSpringAnimation.setStartValue(0.0f);
                this.this$0.fadeSpringAnimation.animateToFinalPosition(0.0f);
                this.this$0.fadeSpringAnimation.skipToEnd();
                break;
            case 2:
                OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl3 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(oplusNotificationOverlayControllerImpl3.hashCode(), "showOverlayAction(", ")", "OplusNotificationOverlayController-->", "SystemUi--Notification");
                }
                this.this$0.fadeSpringAnimation.animateToFinalPosition(1.0f);
                break;
            default:
                OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl4 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(oplusNotificationOverlayControllerImpl4.hashCode(), "showOverlayActionNoAnimation(", ")", "OplusNotificationOverlayController-->", "SystemUi--Notification");
                }
                this.this$0.setOverlayAlpha(1.0f);
                this.this$0.fadeSpringAnimation.setStartValue(1.0f);
                this.this$0.fadeSpringAnimation.animateToFinalPosition(1.0f);
                this.this$0.fadeSpringAnimation.skipToEnd();
                break;
        }
    }
}
