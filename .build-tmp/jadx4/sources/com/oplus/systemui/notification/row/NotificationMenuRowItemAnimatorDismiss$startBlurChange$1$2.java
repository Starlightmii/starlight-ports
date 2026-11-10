package com.oplus.systemui.notification.row;

import android.view.KeyEvent;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ View $deleteView;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationMenuRowItemAnimatorDismiss this$0;

    public /* synthetic */ NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                KeyEvent.Callback callback = this.$deleteView;
                IOplusMenuRowItemView iOplusMenuRowItemView = callback instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) callback : null;
                if (iOplusMenuRowItemView != null) {
                    iOplusMenuRowItemView.updateBlurRatio(15.0f);
                }
                View view = this.$deleteView;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("侧滑动效管理-", Bundler$.ExternalSyntheticOutline0.m("child ", NotificationMenuRowLog.simpleNameAndHash(view), " 更新blurRatio 完成"), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
                }
                this.this$0.dismissAnimMap.remove("startBlurChange");
                break;
            default:
                this.$deleteView.setAlpha(0.0f);
                View view2 = this.$deleteView;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("侧滑动效管理-", Bundler$.ExternalSyntheticOutline0.m("child ", NotificationMenuRowLog.simpleNameAndHash(view2), " 更新Alpha 完成"), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
                }
                this.this$0.dismissAnimMap.remove("startAlphaChange");
                break;
        }
    }
}
