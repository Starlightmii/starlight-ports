package com.oplus.systemui.shade;

import android.view.View;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ boolean $isShow;
    public /* synthetic */ View $view;
    public /* synthetic */ NotificationPanelChildAppearAnimationManagerImpl this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        boolean z2 = this.$isShow;
        View view = this.$view;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("[animateTo] animationEnd  isShow=", ", canceled=", ", ", z2, z);
        sbM.append(view);
        sbM.append(" ");
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationPanelChildAppearAnimationManager", sbM.toString(), null, 8);
        NotificationPanelChildAppearAnimationManagerImpl.cancelAllAnimation(this.$view);
    }
}
