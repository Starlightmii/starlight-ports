package com.oplus.systemui.shade;

import android.view.View;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ View $view;
    public /* synthetic */ NotificationPanelSeparateAnimationManagerImpl this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        View view = this.$view;
        COUISpringAnimation showSpacingAnimation = NotificationPanelSeparateAnimationManagerImpl.getShowSpacingAnimation(view);
        if (showSpacingAnimation != null) {
            showSpacingAnimation.cancel();
        }
        COUISpringAnimation hideSpacingAnimation = NotificationPanelSeparateAnimationManagerImpl.getHideSpacingAnimation(view);
        if (hideSpacingAnimation != null) {
            hideSpacingAnimation.cancel();
        }
        view.setTag(2131365566, null);
        view.setTag(2131365561, null);
        View view2 = this.$view;
        if (view2 instanceof ExpandableNotificationRow) {
            Object tag = view2.getTag(2131365568);
            Float f3 = tag instanceof Float ? (Float) tag : null;
            float fFloatValue = f3 != null ? f3.floatValue() : 0.0f;
            String key = this.$view.getKey();
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("spacingAnimateTo end canceled=", z, ", value=", f, " negSpacing=");
            sbM.append(fFloatValue);
            sbM.append(" ");
            sbM.append(key);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationPanelSeparateAnimation", sbM.toString(), null, 8);
        }
    }
}
