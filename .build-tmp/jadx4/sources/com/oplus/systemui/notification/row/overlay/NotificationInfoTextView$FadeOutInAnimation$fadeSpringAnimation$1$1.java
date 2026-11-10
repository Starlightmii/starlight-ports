package com.oplus.systemui.notification.row.overlay;

import androidx.car.app.serialization.Bundler$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ COUISpringAnimation $this_apply;
    public /* synthetic */ NotificationInfoTextView.FadeOutInAnimation this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        NotificationInfoTextView.FadeOutInAnimation fadeOutInAnimation = this.this$0;
        fadeOutInAnimation.onUpdate.invoke(Float.valueOf(f));
        if (f > 0.05f || !fadeOutInAnimation.isFadeOut) {
            return;
        }
        boolean zBooleanValue = ((Boolean) fadeOutInAnimation.onFadeOutFinish.invoke()).booleanValue();
        String str = fadeOutInAnimation.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("isFadeOut: needAnimationFadeIn=", "SystemUi--Notification", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), zBooleanValue);
        }
        if (zBooleanValue) {
            fadeOutInAnimation.isFadeOut = false;
            this.$this_apply.animateToFinalPosition(1.0f);
        }
    }
}
