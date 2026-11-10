package com.oplus.systemui.notification.headsup.windowframe;

import android.util.Log;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.touchanimation.COUIIComposeMotionUnit;
import com.coui.appcompat.animation.touchanimation.OnAnimationEndListener;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$2 implements OnAnimationEndListener {
    public /* synthetic */ HeadsUpCOUItouchMotionAnimator this$0;

    public final void onAnimationEnd(COUIIComposeMotionUnit cOUIIComposeMotionUnit, boolean z, float f, float f2) {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            float baseTranslationY = headsUpCOUItouchMotionAnimator.getBaseTranslationY();
            float f3 = headsUpCOUItouchMotionAnimator.touchMotionTranslationY;
            float f4 = headsUpCOUItouchMotionAnimator.touchMotionScaleX;
            float f5 = headsUpCOUItouchMotionAnimator.touchMotionScaleY;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("animEnd: canceled=", z, " value=", f, " velocity=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, " baseY=", baseTranslationY, " deltas=(0.0,");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f3, ") scales=(", f4, OplusPreventModeData.SEPARATOR);
            sbM.append(f5);
            sbM.append(")");
            Log.d("SystemUi--Notification", "HeadsUpCOUIMotion-->".concat(sbM.toString()));
        }
        headsUpCOUItouchMotionAnimator.touchMotionTranslationY = 0.0f;
        headsUpCOUItouchMotionAnimator.touchMotionScaleX = 1.0f;
        headsUpCOUItouchMotionAnimator.touchMotionScaleY = 1.0f;
        if (headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal) {
            return;
        }
        headsUpCOUItouchMotionAnimator.applyCurrentViewTransform();
    }
}
