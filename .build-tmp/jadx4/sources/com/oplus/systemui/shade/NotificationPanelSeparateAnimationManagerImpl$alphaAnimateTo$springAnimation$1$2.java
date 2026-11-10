package com.oplus.systemui.shade;

import android.view.View;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ Function3 $onAnimationEnd;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ View $view;
    public /* synthetic */ NotificationPanelSeparateAnimationManagerImpl this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                View view = this.$view;
                COUISpringAnimation showAlphaAnimation = NotificationPanelSeparateAnimationManagerImpl.getShowAlphaAnimation(view);
                if (showAlphaAnimation != null) {
                    showAlphaAnimation.cancel();
                }
                COUISpringAnimation hideAlphaAnimation = NotificationPanelSeparateAnimationManagerImpl.getHideAlphaAnimation(view);
                if (hideAlphaAnimation != null) {
                    hideAlphaAnimation.cancel();
                }
                view.setTag(2131365564, null);
                view.setTag(2131365559, null);
                Function3 function3 = this.$onAnimationEnd;
                if (function3 != null) {
                    function3.invoke(this.$view, AnimationType.ALPHA, Float.valueOf(f));
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationPanelSeparateAnimation", "alphaAnimateTo end canceled=" + z + ", value=" + f, null, 8);
                break;
            default:
                View view2 = this.$view;
                COUISpringAnimation showScaleAnimation = NotificationPanelSeparateAnimationManagerImpl.getShowScaleAnimation(view2);
                if (showScaleAnimation != null) {
                    showScaleAnimation.cancel();
                }
                COUISpringAnimation hideScaleAnimation = NotificationPanelSeparateAnimationManagerImpl.getHideScaleAnimation(view2);
                if (hideScaleAnimation != null) {
                    hideScaleAnimation.cancel();
                }
                view2.setTag(2131365567, null);
                view2.setTag(2131365562, null);
                Function3 function4 = this.$onAnimationEnd;
                if (function4 != null) {
                    function4.invoke(this.$view, AnimationType.SCALE, Float.valueOf(f));
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationPanelSeparateAnimation", "scaleAnimateTo end canceled=" + z + ", value=" + f, null, 8);
                break;
        }
    }
}
