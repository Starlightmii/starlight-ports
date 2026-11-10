package com.oplus.systemui.notification.row;

import android.view.KeyEvent;
import android.view.View;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ View $deleteView;
    public final /* synthetic */ int $r8$classId;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                float fCoerceIn = 1.0f - RangesKt___RangesKt.coerceIn(f / 100.0f, 0.0f, 1.0f);
                float f3 = 15.0f * fCoerceIn;
                float fCoerceIn2 = RangesKt___RangesKt.coerceIn(fCoerceIn, 0.0f, 1.0f);
                KeyEvent.Callback callback = this.$deleteView;
                IOplusMenuRowItemView iOplusMenuRowItemView = callback instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) callback : null;
                if (iOplusMenuRowItemView != null) {
                    iOplusMenuRowItemView.updateBlurRatio(f3);
                }
                IOplusMenuRowItemView iOplusMenuRowItemView2 = Float.valueOf(fCoerceIn2) instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) Float.valueOf(fCoerceIn2) : null;
                if (iOplusMenuRowItemView2 != null) {
                    iOplusMenuRowItemView2.updateAlpha(fCoerceIn2);
                }
                View view = this.$deleteView;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("侧滑动效管理-", "child " + NotificationMenuRowLog.simpleNameAndHash(view) + " 更新blurRatio " + f3, "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
                    break;
                }
                break;
            default:
                float fCoerceIn3 = RangesKt___RangesKt.coerceIn(f / 100.0f, 0.0f, 1.0f);
                this.$deleteView.setAlpha(fCoerceIn3);
                View view2 = this.$deleteView;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("侧滑动效管理-", "child " + NotificationMenuRowLog.simpleNameAndHash(view2) + " 更新Alpha " + fCoerceIn3, "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
                    break;
                }
                break;
        }
    }
}
